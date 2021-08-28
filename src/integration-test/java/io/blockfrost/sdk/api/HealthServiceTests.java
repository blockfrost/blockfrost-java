package io.blockfrost.sdk.api;

import io.blockfrost.sdk.api.exception.APIException;
import io.blockfrost.sdk.api.model.Clock;
import io.blockfrost.sdk.api.model.Health;
import io.blockfrost.sdk.api.util.Constants;
import io.blockfrost.sdk.impl.HealthServiceImpl;
import org.exparity.hamcrest.date.DateMatchers;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class HealthServiceTests extends TestBase {

    static HealthService healthService;

    @BeforeAll
    public static void setup() {
        healthService = new HealthServiceImpl(Constants.BLOCKFROST_TESTNET_URL, projectId);
    }

    @Test
    public void health_willReturn_healthStatus() throws APIException {
        Health healthResponse = healthService.getHealth();
        assertThat(healthResponse.getIsHealthy(), is(true));
    }

    //TODO: Need to check how much error range need to be defined.
    @Test
    public void clock_willReturn_currentBackEndTime() throws APIException {
        Clock clock = healthService.getCurrentBackendTime();
        Date date = new Date(clock.getServerTime());
        assertThat(date, DateMatchers.within(2, ChronoUnit.SECONDS, new Date()));
    }

    @Test
    @Disabled
    public void rateLimit_willThrow_RequestNotPermitted() throws APIException, ExecutionException, InterruptedException {

        List<CompletableFuture<String>> completableFutures = new ArrayList<>();
        for (int i = 0; i < 11; i++) {
            CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
                try {
                    return healthService.getApiRoot();
                } catch (APIException e) {
                    e.printStackTrace();
                }
                return "";
            });
            completableFutures.add(future);
        }

        CompletableFuture<Void> combinedFuture = CompletableFuture.allOf(completableFutures.toArray(new CompletableFuture[completableFutures.size()]));
        combinedFuture.get();

    }

}
