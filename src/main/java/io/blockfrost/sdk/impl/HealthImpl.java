package io.blockfrost.sdk.impl;

import io.blockfrost.sdk.api.Health;
import io.blockfrost.sdk.api.exception.BlockfrostAPIException;
import io.blockfrost.sdk.impl.model.Clock;
import io.blockfrost.sdk.impl.model.HealthStatus;
import io.blockfrost.sdk.impl.retrofit.HealthApi;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;

public class HealthImpl extends BaseImpl implements Health {

    private HealthApi healthApi;

    public HealthImpl(String baseUrl, String projectId){
        super(baseUrl, projectId);
        healthApi = getRetrofit().create(HealthApi.class);
    }

    @Override
    public HealthStatus getHealth() throws BlockfrostAPIException {

        Call<HealthStatus> healthCall = healthApi.healthGet(getProjectId());

        try{
            Response<HealthStatus> healthResponse = healthCall.execute();
            return processResponse(healthResponse);
        } catch (IOException exp){
            throw new BlockfrostAPIException("Exception while fetching health status", exp);
        }
    }

    @Override
    public Clock getCurrentBackendTime() throws BlockfrostAPIException {
        Call<Clock> clockCall = healthApi.healthClockGet(getProjectId());

        try{
            Response<Clock> clockResponse = clockCall.execute();
            return processResponse(clockResponse);
        } catch (IOException exp){
            throw new BlockfrostAPIException("Exception while fetching current backend time", exp);
        }
    }

    @Override
    public String getApiRoot() throws BlockfrostAPIException {

        Call<ResponseBody> apiRootCall = healthApi.rootGet(getProjectId());

        try{
            Response<ResponseBody> apiRootResponse = apiRootCall.execute();
            ResponseBody responseBody = apiRootResponse.raw().body();
            //return processResponse(apiRootResponse);
            return "done";
        } catch (Exception exp){
            throw new BlockfrostAPIException("Exception while fetching Api Root", exp);
        }

    }

}
