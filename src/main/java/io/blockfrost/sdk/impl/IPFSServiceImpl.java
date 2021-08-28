package io.blockfrost.sdk.impl;

import io.blockfrost.sdk.api.IPFSService;
import io.blockfrost.sdk.api.exception.APIException;
import io.blockfrost.sdk.api.exception.RuntimeAPIException;
import io.blockfrost.sdk.api.model.ipfs.IPFSObject;
import io.blockfrost.sdk.api.model.ipfs.PinItem;
import io.blockfrost.sdk.api.model.ipfs.PinResponse;
import io.blockfrost.sdk.api.util.ConfigHelper;
import io.blockfrost.sdk.api.util.OrderEnum;
import io.blockfrost.sdk.impl.retrofit.IPFSApi;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class IPFSServiceImpl extends BaseService implements IPFSService {

    IPFSApi ipfsApi;

    public IPFSServiceImpl(String baseUrl, String projectId) {
        super(baseUrl, projectId);
        ipfsApi = getRetrofit().create(IPFSApi.class);
    }

    @Override
    public IPFSObject add(File file) throws APIException, IOException {
        if(file == null) {
            throw new IOException("File cannot be null");
        }

        if(!file.exists()) {
            throw new FileNotFoundException("File doesn't exist");
        }

        RequestBody requestFile =
                RequestBody.create(
                        null,
                        file
                );

        MultipartBody.Part body =
                MultipartBody.Part.createFormData("files[0]", file.getName(), requestFile);
        Call<IPFSObject> addCall = ipfsApi.add(getProjectId(), body);

        try {
            Response<IPFSObject> addResponse = addCall.execute();
            return processResponse(addResponse);
        } catch (IOException exp) {
            throw new APIException("Exception while adding file to IPFS", exp);
        }
    }

    @Override
    public byte[] get(String ipfsPath) throws APIException {
        Call<ResponseBody> getCall = ipfsApi.get(getProjectId(), ipfsPath);

        try {
            Response<ResponseBody> getResponse = getCall.execute();
            return processResponse(getResponse).bytes();
        } catch (IOException exp) {
            throw new APIException("Exception while getting content for ipfsPath : " + ipfsPath, exp);
        }
    }

    @Override
    public PinResponse pinAdd(String ipfsPath) throws APIException {
        Call<PinResponse> pinCall = ipfsApi.pinAdd(getProjectId(), ipfsPath);

        try {
            Response<PinResponse> response = pinCall.execute();
            return processResponse(response);
        } catch (IOException exp) {
            throw new APIException("Exception while adding pin for ipfsPath : " + ipfsPath, exp);
        }
    }

    @Override
    public List<PinItem> getPinnedObjects(int count, int page, OrderEnum order) throws APIException {
        Call<List<PinItem>> listCall = ipfsApi.pinList(getProjectId(), count, page, order.name());

        try {
            Response<List<PinItem>> response = listCall.execute();
            return processResponse(response);
        } catch (IOException exp) {
            throw new APIException("Exception while getting pinned objects", exp);
        }
    }

    @Override
    public List<PinItem> getAllPinnedObjects() throws APIException {
        return getAllPinnedObjects(OrderEnum.asc);
    }

    @Override
    public List<PinItem> getAllPinnedObjects(OrderEnum order) throws APIException {

        List<PinItem> responseList = new ArrayList<>();
        boolean stopExecution = false;
        int currentPageCount = 1;
        int numThreads = ConfigHelper.INSTANCE.getThreadCount();

        while (!stopExecution) {

            List<CompletableFuture<List<PinItem>>> completableFutures = new ArrayList<>();

            for (int i = 0; i < numThreads; i++) {

                int finalCurrentPageCount = currentPageCount + i;

                completableFutures.add(CompletableFuture.supplyAsync(() -> {
                    try {
                        return getPinnedObjects(getDefaultFetchSize(), finalCurrentPageCount, order);
                    } catch (APIException e) {
                        throw new RuntimeAPIException(e);
                    }
                }));
            }

            try {
                stopExecution = fetchData(completableFutures, responseList);
            } catch (Exception e) {
                throw new APIException("Exception while get all pinned object in local storage ", e);
            }

            currentPageCount += numThreads;
        }

        return responseList;
    }

    @Override
    public PinItem getPinnedObjectByIpfsPath(String ipfsPath) throws APIException {
        Call<PinItem> call = ipfsApi.pinListByIpfsPath(getProjectId(), ipfsPath);

        try {
            Response<PinItem> response = call.execute();
            return processResponse(response);
        } catch (IOException exp) {
            throw new APIException("Exception while getting pinned object by ipfspath : " + ipfsPath, exp);
        }
    }

    @Override
    public PinItem removePinnedObject(String ipfsPath) throws APIException {
        Call<PinItem> call = ipfsApi.pinRemove(getProjectId(), ipfsPath);

        try {
            Response<PinItem> response = call.execute();
            return processResponse(response);
        } catch (IOException exp) {
            throw new APIException("Exception while removing pinned object by ipfspath : " + ipfsPath, exp);
        }
    }
}
