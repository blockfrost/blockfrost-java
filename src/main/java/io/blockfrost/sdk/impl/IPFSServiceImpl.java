package io.blockfrost.sdk.impl;

import io.blockfrost.sdk.api.IPFSService;
import io.blockfrost.sdk.api.exception.APIException;
import io.blockfrost.sdk.api.model.IPFSObject;
import io.blockfrost.sdk.impl.retrofit.IPFSApi;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Response;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

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
}
