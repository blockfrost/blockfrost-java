package io.blockfrost.sdk.api;

import io.blockfrost.sdk.api.exception.BlockfrostAPIException;
import io.blockfrost.sdk.impl.HealthImpl;
import io.blockfrost.sdk.impl.common.Constants;
import io.blockfrost.sdk.impl.model.Clock;
import io.blockfrost.sdk.impl.model.HealthStatus;
import org.exparity.hamcrest.date.DateMatchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.temporal.ChronoUnit;
import java.util.Date;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class HealthTests extends TestBase {

    Health health;

    @BeforeEach
    public void setup(){
        health = new HealthImpl(Constants.BLOCKFROST_TESTNET_URL, projectId);
    }

    @Test
    public void health_willReturn_healthStatus() throws BlockfrostAPIException {
        HealthStatus healthResponse = health.getHealth();
        assertThat(healthResponse.getIsHealthy(), is(true));
    }

    //TODO: Need to check how much error range need to be defined.
    @Test
    public void clock_willReturn_currentBackEndTime() throws BlockfrostAPIException {
        Clock clock = health.getCurrentBackendTime();
        Date date = new Date(clock.getServerTime());
        assertThat(date, DateMatchers.within(1, ChronoUnit.SECONDS, new Date()));
    }
}
