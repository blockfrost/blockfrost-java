package io.blockfrost.sdk.impl;

import io.blockfrost.sdk.api.MetricsService;
import io.blockfrost.sdk.api.exception.APIException;
import io.blockfrost.sdk.api.model.EndpointUsageMetric;
import io.blockfrost.sdk.api.model.UsageMetric;
import io.blockfrost.sdk.impl.retrofit.MetricsApi;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;
import java.util.List;

public class MetricsServiceImpl extends BaseService implements MetricsService {

    private MetricsApi metricsApi;

    public MetricsServiceImpl(String baseUrl, String projectId) {
        super(baseUrl, projectId);
        metricsApi = getRetrofit().create(MetricsApi.class);
    }

    @Override
    public List<UsageMetric> getUsageMetrics() throws APIException {
        Call<List<UsageMetric>> usageMetricsCall = metricsApi.metricsGet(getProjectId());

        try {
            Response<List<UsageMetric>> usageMetricsResponse = usageMetricsCall.execute();
            return processResponse(usageMetricsResponse);
        } catch (IOException exp) {
            throw new APIException("Exception while fetching usage metrics", exp);
        }
    }

    @Override
    public List<EndpointUsageMetric> getEndpointUsageMetrics() throws APIException {
        Call<List<EndpointUsageMetric>> endpointUsageMetricsCall = metricsApi.metricsEndpointsGet(getProjectId());

        try {
            Response<List<EndpointUsageMetric>> endpointUsageMetricsResponse = endpointUsageMetricsCall.execute();
            return processResponse(endpointUsageMetricsResponse);
        } catch (IOException exp) {
            throw new APIException("Exception while fetching endpoint usage metrics", exp);
        }
    }
}
