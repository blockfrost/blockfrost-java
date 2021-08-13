package io.blockfrost.sdk.impl;

import io.blockfrost.sdk.api.HealthService;
import io.blockfrost.sdk.api.exception.APIException;
import io.blockfrost.sdk.api.model.Clock;
import io.blockfrost.sdk.api.model.Health;
import io.blockfrost.sdk.impl.retrofit.HealthApi;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;

public class HealthServiceImpl extends BaseService implements HealthService {

    private HealthApi healthApi;

    public HealthServiceImpl(String baseUrl, String projectId) {
        super(baseUrl, projectId);
        healthApi = getRetrofit().create(HealthApi.class);
    }

    @Override
    public Health getHealth() throws APIException {

        Call<Health> healthCall = healthApi.healthGet(getProjectId());

        try {
            Response<Health> healthResponse = healthCall.execute();
            return processResponse(healthResponse);
        } catch (IOException exp) {
            throw new APIException("Exception while fetching health status", exp);
        }
    }

    @Override
    public Clock getCurrentBackendTime() throws APIException {
        Call<Clock> clockCall = healthApi.healthClockGet(getProjectId());

        try {
            Response<Clock> clockResponse = clockCall.execute();
            return processResponse(clockResponse);
        } catch (IOException exp) {
            throw new APIException("Exception while fetching current backend time", exp);
        }
    }

    @Override
    public String getApiRoot() throws APIException {

        Call<ResponseBody> apiRootCall = healthApi.rootGet(getProjectId());

        try {
            Response<ResponseBody> apiRootResponse = apiRootCall.execute();
            ResponseBody responseBody = apiRootResponse.raw().body();
            //return processResponse(apiRootResponse);
            return "done";
        } catch (Exception exp) {
            throw new APIException("Exception while fetching Api Root", exp);
        }

    }

}
