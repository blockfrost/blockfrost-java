package io.blockfrost.sdk.impl;

import io.blockfrost.sdk.api.NetworkService;
import io.blockfrost.sdk.api.exception.APIException;
import io.blockfrost.sdk.api.model.Network;
import io.blockfrost.sdk.impl.retrofit.NetworkApi;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;

public class NetworkServiceImpl extends BaseImpl implements NetworkService {

    NetworkApi networkApi;

    public NetworkServiceImpl(String baseUrl, String projectId) {
        super(baseUrl, projectId);
        networkApi = getRetrofit().create(NetworkApi.class);
    }

    @Override
    public Network getNetwork() throws APIException {
        Call<Network> networkCall = networkApi.networkGet(getProjectId());

        try {
            Response<Network> networkResponse = networkCall.execute();
            return processResponse(networkResponse);
        } catch (IOException exp) {
            throw new APIException("Exception while fetching network", exp);
        }
    }
}
