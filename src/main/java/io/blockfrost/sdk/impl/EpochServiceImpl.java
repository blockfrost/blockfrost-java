package io.blockfrost.sdk.impl;

import io.blockfrost.sdk.api.EpochService;
import io.blockfrost.sdk.api.exception.APIException;
import io.blockfrost.sdk.api.model.Epoch;
import io.blockfrost.sdk.api.model.EpochParam;
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
    public Epoch getLatestEpoch() throws APIException {
        Call<Epoch> latestEpochCall = epochsApi.epochsLatestGet(getProjectId());

        try{
            Response<Epoch> latestEpochResponse = latestEpochCall.execute();
            return processResponse(latestEpochResponse);
        } catch (IOException exp){
            throw new APIException("Exception while fetching latest epoch", exp);
        }
    }

    @Override
    public EpochParam getLatestEpochParam() throws APIException {
        Call<EpochParam> latestEpochParamCall = epochsApi.epochsLatestParametersGet(getProjectId());

        try{
            Response<EpochParam> latestEpochParamResponse = latestEpochParamCall.execute();
            return processResponse(latestEpochParamResponse);
        } catch (IOException exp){
            throw new APIException("Exception while fetching latest epoch parameters", exp);
        }
    }
}
