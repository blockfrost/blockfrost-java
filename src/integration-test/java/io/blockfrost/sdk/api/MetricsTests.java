package io.blockfrost.sdk.api;

import io.blockfrost.sdk.api.exception.BlockfrostAPIException;
import io.blockfrost.sdk.impl.MetricsImpl;
import io.blockfrost.sdk.impl.common.Constants;
import io.blockfrost.sdk.impl.model.EndpointUsageMetric;
import io.blockfrost.sdk.impl.model.UsageMetric;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class MetricsTests extends TestBase {

    Metrics metrics;

    @BeforeEach
    public void setup(){
        metrics = new MetricsImpl(Constants.BLOCKFROST_TESTNET_URL, projectId);
    }

    //TODO: How to assert this test results. Should we keep a separate projectId?
    @Test
    public void usageMetrics_willReturn_listOfUsageMetrics() throws BlockfrostAPIException {
        List<UsageMetric> usageMetricsResponse = metrics.getUsageMetrics();
        assertThat(usageMetricsResponse, hasSize(greaterThan(0)));
    }

    //TODO: How to assert this test results. Should we keep a separate projectId?
    //TODO: "date" in Api Documentation has changed to "time"
    @Test
    public void endpointUsageMetrics_willReturn_listOfEndpointUsageMetrics() throws BlockfrostAPIException {
        List<EndpointUsageMetric> endpointUsageMetricsResponse = metrics.getEndpointUsageMetrics();
        assertThat(endpointUsageMetricsResponse, hasSize(greaterThan(0)));
    }
}
