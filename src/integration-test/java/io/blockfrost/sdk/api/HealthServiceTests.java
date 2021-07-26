package io.blockfrost.sdk.api;

import io.blockfrost.sdk.api.exception.APIException;
import io.blockfrost.sdk.impl.HealthServiceImpl;
import io.blockfrost.sdk.api.util.Constants;
import io.blockfrost.sdk.api.model.Clock;
import io.blockfrost.sdk.api.model.Health;
import org.exparity.hamcrest.date.DateMatchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.temporal.ChronoUnit;
import java.util.Date;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class HealthServiceTests extends TestBase {

    HealthService healthService;

    @BeforeEach
    public void setup(){
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
}
