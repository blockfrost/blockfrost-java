package io.blockfrost.sdk.impl;

import io.blockfrost.sdk.api.EpochService;
import io.blockfrost.sdk.api.exception.APIException;
import io.blockfrost.sdk.api.model.Epoch;
import io.blockfrost.sdk.api.model.EpochParam;
import io.blockfrost.sdk.api.model.Stake;
import io.blockfrost.sdk.impl.retrofit.EpochsApi;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;
import java.util.List;

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

    @Override
    public Epoch getEpoch(int number) throws APIException {
        
        Call<Epoch> epochCall = epochsApi.epochsNumberGet(getProjectId(), number);

        try{
            Response<Epoch> epochResponse = epochCall.execute();
            return processResponse(epochResponse);
        } catch (IOException exp){
            throw new APIException("Exception while fetching epoch for epoch number: " + number, exp);
        }
    }

    @Override
    public List<Epoch> getNextEpochs(int number, int count, int page) throws APIException {
        
        Call<List<Epoch>> nextEpochsCall = epochsApi.epochsNumberNextGet(getProjectId(), number, count, page);

        try{
            Response<List<Epoch>> nextEpochsResponse = nextEpochsCall.execute();
            return processResponse(nextEpochsResponse);
        } catch (IOException exp){
            throw new APIException("Exception while fetching next epochs for epoch number: " + number, exp);
        }
    }

    //TODO: Implement
    @Override
    public List<Epoch> getNextEpochs(int number) throws APIException {
        return null;
    }

    @Override
    public List<Epoch> getPreviousEpochs(int number, int count, int page) throws APIException {

        Call<List<Epoch>> previousEpochsCall = epochsApi.epochsNumberPreviousGet(getProjectId(), number, count, page);

        try{
            Response<List<Epoch>> previousEpochsResponse = previousEpochsCall.execute();
            return processResponse(previousEpochsResponse);
        } catch (IOException exp){
            throw new APIException("Exception while fetching previous epochs for epoch number: " + number, exp);
        }
    }

    //TODO: Implement
    @Override
    public List<Epoch> getPreviousEpochs(int number) throws APIException {
        return null;
    }

    @Override
    public List<Stake> getActiveStakesForEpoch(int number, int count, int page) throws APIException {

        Call<List<Stake>> activeStakesCall = epochsApi.epochsNumberStakesGet(getProjectId(), number, count, page);

        try{
            Response<List<Stake>> activeStakesResponse = activeStakesCall.execute();
            return processResponse(activeStakesResponse);
        } catch (IOException exp){
            throw new APIException("Exception while fetching active stakes for epoch number: " + number, exp);
        }
    }

    //TODO: Implement
    @Override
    public List<Stake> getActiveStakesForEpoch(int number) throws APIException {
        return null;
    }

    @Override
    public List<Stake> getActiveStakesForEpochAndPool(int number, String poolId, int count, int page) throws APIException {

        if ( poolId== null || poolId.equals("") ){
            throw new APIException("PoolId cannot be null or empty");
        }

        Call<List<Stake>> activeStakesCall = epochsApi.epochsNumberStakesPoolIdGet(getProjectId(), number, poolId, count, page);

        try{
            Response<List<Stake>> activeStakesResponse = activeStakesCall.execute();
            return processResponse(activeStakesResponse);
        } catch (IOException exp){
            throw new APIException("Exception while fetching active stakes for epoch number: " + number + " and poolId: " + poolId, exp);
        }
    }

    //TODO: Implement
    @Override
    public List<Stake> getActiveStakesForEpochAndPool(int number, String poolId) throws APIException {
        return null;
    }
}
