package io.blockfrost.sdk.impl;

import io.blockfrost.sdk.api.Metrics;
import io.blockfrost.sdk.api.exception.BlockfrostAPIException;
import io.blockfrost.sdk.impl.model.EndpointUsageMetric;
import io.blockfrost.sdk.impl.model.UsageMetric;
import io.blockfrost.sdk.impl.retrofit.MetricsApi;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;
import java.util.List;

public class MetricsImpl extends BaseImpl implements Metrics {

    private MetricsApi metricsApi;

    public MetricsImpl(String baseUrl, String projectId){
        super(baseUrl, projectId);
        metricsApi = getRetrofit().create(MetricsApi.class);
    }

    @Override
    public List<UsageMetric> getUsageMetrics() throws BlockfrostAPIException {
        Call<List<UsageMetric>> usageMetricsCall = metricsApi.metricsGet(getProjectId());

        try{
            Response<List<UsageMetric>> usageMetricsResponse = usageMetricsCall.execute();
            return processResponse(usageMetricsResponse);
        } catch (IOException exp){
            throw new BlockfrostAPIException("Exception while fetching usage metrics", exp);
        }
    }

    @Override
    public List<EndpointUsageMetric> getEndpointUsageMetrics() throws BlockfrostAPIException {
        Call<List<EndpointUsageMetric>> endpointUsageMetricsCall = metricsApi.metricsEndpointsGet(getProjectId());

        try{
            Response<List<EndpointUsageMetric>> endpointUsageMetricsResponse = endpointUsageMetricsCall.execute();
            return processResponse(endpointUsageMetricsResponse);
        } catch (IOException exp){
            throw new BlockfrostAPIException("Exception while fetching endpoint usage metrics", exp);
        }
    }
}
