package io.blockfrost.sdk.api;

import io.blockfrost.sdk.api.exception.APIException;
import io.blockfrost.sdk.api.model.EndpointUsageMetric;
import io.blockfrost.sdk.api.model.UsageMetric;

import java.util.List;

public interface MetricsService {

    List<UsageMetric> getUsageMetrics() throws APIException;

    List<EndpointUsageMetric> getEndpointUsageMetrics() throws APIException;

}
