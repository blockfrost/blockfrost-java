package io.blockfrost.sdk.impl;

import io.blockfrost.sdk.api.EpochService;
import io.blockfrost.sdk.api.exception.APIException;
import io.blockfrost.sdk.api.model.EpochContent;
import io.blockfrost.sdk.impl.retrofit.EpochsApi;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;

public class EpochServiceImpl extends BaseImpl implements EpochService {

    EpochsApi epochsApi;

    public EpochServiceImpl(String baseUrl, String projectId){
        super(baseUrl, projectId);
        epochsApi = getRetrofit().create(EpochsApi.class);
    }


    @Override
    public EpochContent getLatestEpoch() throws APIException {
        Call<EpochContent> latestBlockCall = epochsApi.epochsLatestGet(getProjectId());

        try{
            Response<EpochContent> latestBlockResponse = latestBlockCall.execute();
            return processResponse(latestBlockResponse);
        } catch (IOException exp){
            throw new APIException("Exception while fetching latest epoch", exp);
        }
    }
}
