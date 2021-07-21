package io.blockfrost.sdk.api;

import io.blockfrost.sdk.api.exception.APIException;
import io.blockfrost.sdk.impl.model.EndpointUsageMetric;
import io.blockfrost.sdk.impl.model.UsageMetric;

import java.util.List;

public interface Metrics {

    List<UsageMetric> getUsageMetrics() throws APIException;

    List<EndpointUsageMetric> getEndpointUsageMetrics() throws APIException;

}
