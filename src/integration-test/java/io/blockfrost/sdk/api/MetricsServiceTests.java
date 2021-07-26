package io.blockfrost.sdk.api;

import io.blockfrost.sdk.api.exception.APIException;
import io.blockfrost.sdk.impl.MetricsServiceImpl;
import io.blockfrost.sdk.api.util.Constants;
import io.blockfrost.sdk.api.model.EndpointUsageMetric;
import io.blockfrost.sdk.api.model.UsageMetric;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class MetricsServiceTests extends TestBase {

    MetricsService metricsService;

    @BeforeEach
    public void setup(){
        metricsService = new MetricsServiceImpl(Constants.BLOCKFROST_TESTNET_URL, projectId);
    }

    //TODO: How to assert this test results. Should we keep a separate projectId?
    @Test
    public void usageMetrics_willReturn_listOfUsageMetrics() throws APIException {
        List<UsageMetric> usageMetricsResponse = metricsService.getUsageMetrics();
        assertThat(usageMetricsResponse, hasSize(greaterThan(0)));
    }

    //TODO: How to assert this test results. Should we keep a separate projectId?
    //TODO: "date" in Api Documentation has changed to "time"
    @Test
    public void endpointUsageMetrics_willReturn_listOfEndpointUsageMetrics() throws APIException {
        List<EndpointUsageMetric> endpointUsageMetricsResponse = metricsService.getEndpointUsageMetrics();
        assertThat(endpointUsageMetricsResponse, hasSize(greaterThan(0)));
    }
}
