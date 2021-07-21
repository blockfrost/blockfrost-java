package io.blockfrost.sdk.api;

import io.blockfrost.sdk.api.exception.BlockfrostAPIException;
import io.blockfrost.sdk.impl.model.EndpointUsageMetric;
import io.blockfrost.sdk.impl.model.UsageMetric;

import java.util.List;

public interface Metrics {

    List<UsageMetric> getUsageMetrics() throws BlockfrostAPIException;

    List<EndpointUsageMetric> getEndpointUsageMetrics() throws BlockfrostAPIException;

}
