package io.blockfrost.sdk.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.blockfrost.sdk.api.exception.APIException;
import io.blockfrost.sdk.api.model.ResponseError;
import io.blockfrost.sdk.impl.util.RateLimitHelper;
import io.github.resilience4j.retrofit.RateLimiterCallAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class BaseService {

    private final static Logger LOG = LoggerFactory.getLogger(BaseService.class);
    private final static ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    private String baseUrl;
    private String projectId;

    public BaseService(String baseUrl, String projectId) {
        this.baseUrl = baseUrl;
        this.projectId = projectId;

        if (LOG.isDebugEnabled()) {
            LOG.debug("Blockfrost URL : " + baseUrl);
        }
    }

    protected Retrofit getRetrofit() {

        return new Retrofit.Builder()
                .addCallAdapterFactory( RateLimiterCallAdapter.of(RateLimitHelper.rateLimiter()))
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


    protected <T> T processResponse(Response<T> response) throws APIException, IOException {
        if (response.isSuccessful()) {
            return response.body();
        } else {
            String errorMessage = response.errorBody().string();
            try {
                ResponseError responseError = OBJECT_MAPPER.readValue(response.errorBody().bytes(), ResponseError.class);
                errorMessage = responseError.getError() + " : " + responseError.getMessage();
            } catch (Exception exp ){
            }
            throw new APIException(errorMessage);
        }
    }

    protected <T> T getApiClient(Class<T> clazz) {
        return getRetrofit().create(clazz);
    }

    protected <T> boolean fetchData(List<CompletableFuture<List<T>>> completableFutureList, List<T> returnList ) throws Exception {

        boolean noMoreData = false;

        CompletableFuture<Void> combinedFuture
                = CompletableFuture.allOf(completableFutureList.toArray(new CompletableFuture[completableFutureList.size()]));

        combinedFuture.get();

        for ( CompletableFuture<List<T>> completableFuture : completableFutureList ){
           List<T> result = completableFuture.join();
           returnList.addAll(result);

            if ( result.size() < getDefaultFetchSize() ){
                noMoreData = true;
            }
        }

        return noMoreData;

    }

    protected int getDefaultFetchSize() {
        return 100;
    }

}
