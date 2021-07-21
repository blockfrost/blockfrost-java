package io.blockfrost.sdk.impl;

import io.blockfrost.sdk.api.exception.APIException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class BaseImpl {

    private final static Logger LOG = LoggerFactory.getLogger(BaseImpl.class);

    private String baseUrl;
    private String projectId;

    public BaseImpl(String baseUrl, String projectId) {
        this.baseUrl = baseUrl;
        this.projectId = projectId;

        if(LOG.isDebugEnabled()) {
            LOG.debug("Blockfrost URL : " + baseUrl);
        }
    }

    protected Retrofit getRetrofit() {
        return new Retrofit.Builder()
                .baseUrl(getBaseUrl())
                .addConverterFactory(JacksonConverterFactory.create())
                .build();
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public String getProjectId() {
        return projectId;
    }

    protected  <T> T processResponse(Response<T> response) throws APIException {
        if (response.isSuccessful()){
            return response.body();
        } else {
            throw new APIException(response.message());
        }
    }

}
