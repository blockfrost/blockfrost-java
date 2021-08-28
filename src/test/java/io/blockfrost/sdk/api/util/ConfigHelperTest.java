package io.blockfrost.sdk.api.util;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static io.blockfrost.sdk.api.util.ConfigConstants.*;
import static org.junit.jupiter.api.Assertions.*;

class ConfigHelperTest {

    @BeforeEach
    public void setup() {
        System.setProperty(BF_API_MAX_THREADS, "");
        System.setProperty(BF_RATE_LIMIT_FOR_PERIOD, "");
        System.setProperty(BF_RATE_LIMIT_REFRESH_PERIOD_IN_SEC, "");
        System.setProperty(BF_RATE_LIMIT_TIMEOUT_DURATION_IN_MILLIS, "");
        ConfigHelper.INSTANCE.initEnv();
    }

    @Test
    public void getThreadCount_willReturn_threadCountSetAsSystemProperty() {
        System.setProperty(BF_API_MAX_THREADS, "20");
        ConfigHelper.INSTANCE.initEnv();

        int tCount = ConfigHelper.INSTANCE.getThreadCount();
        assertEquals(20, tCount);
    }

    @Test
    public void getThreadCount_willReturn_defaultThreadCount() {
        int tCount = ConfigHelper.INSTANCE.getThreadCount();
        assertEquals(10, tCount);
    }

    @Test
    public void getThreadCount_willReturn_setThreadCountValue() {
        ConfigHelper.INSTANCE.setThreadCount(60);
        assertEquals(60, ConfigHelper.INSTANCE.getThreadCount());
    }

    @Test
    public void getRateLimitForPeriod_willReturn_rateLimitForPeriodSetAsSystemProperty() {
        System.setProperty(BF_RATE_LIMIT_FOR_PERIOD, "50");
        ConfigHelper.INSTANCE.initEnv();

        int rateLimit = ConfigHelper.INSTANCE.getRateLimitForPeriod();
        assertEquals(50, rateLimit);
    }

    @Test
    public void getRateLimitForPeriod_willReturn_defaultRateLimitForPeriod() {
        int rateLimit = ConfigHelper.INSTANCE.getRateLimitForPeriod();
        assertEquals(10, rateLimit);
    }

    @Test
    public void getRateLimitForPeriod_willReturn_setRateLimitForPeriodValue() {
        ConfigHelper.INSTANCE.setRateLimitForPeriod(40);

        int rateLimit = ConfigHelper.INSTANCE.getRateLimitForPeriod();
        assertEquals(40, rateLimit);
    }

    @Test
    public void getRateLimitRefreshPeriod_willReturn_rateLimitRefreshPeriodSetAsSystemProperty() {
        System.setProperty(BF_RATE_LIMIT_REFRESH_PERIOD_IN_SEC, "5");
        ConfigHelper.INSTANCE.initEnv();

        int rateLimitRefreshPeriodInSec = ConfigHelper.INSTANCE.getRateLimitRefreshPeriodInSec();
        assertEquals(5, rateLimitRefreshPeriodInSec);
    }

    @Test
    public void getRateLimitRefreshPeriod_willReturn_defaultgetRateLimitRefreshPeriod() {
        int rateLimitRefreshPeriodInSec = ConfigHelper.INSTANCE.getRateLimitRefreshPeriodInSec();
        assertEquals(1, rateLimitRefreshPeriodInSec);
    }

    @Test
    public void getRateLimitRefreshPeriod_willReturn_setRateLimitRefreshPeriodValue() {
        ConfigHelper.INSTANCE.setRateLimitRefreshPeriodInSec(2000);

        int rateLimitRefreshPeriodInSec = ConfigHelper.INSTANCE.getRateLimitRefreshPeriodInSec();
        assertEquals(2000, rateLimitRefreshPeriodInSec);
    }

    @Test
    public void getTimeoutDuration_willReturn_timeOutDurationSetAsSystemProperty() {
        System.setProperty(BF_RATE_LIMIT_TIMEOUT_DURATION_IN_MILLIS, "1000");
        ConfigHelper.INSTANCE.initEnv();

        int timeoutDurationInMillis = ConfigHelper.INSTANCE.getTimeoutDurationInMillis();
        assertEquals(1000, timeoutDurationInMillis);
    }

    @Test
    public void getTimeoutDuration_willReturn_defaultTimeoutDuration() {
        int timeoutDurationInMillis = ConfigHelper.INSTANCE.getTimeoutDurationInMillis();
        assertEquals(500, timeoutDurationInMillis);
    }

    @Test
    public void getTimeoutDuration_willReturn_setTimeoutDurationValue() {
        ConfigHelper.INSTANCE.setTimeoutDurationInMillis(4000);

        int timeoutDurationInMillis = ConfigHelper.INSTANCE.getTimeoutDurationInMillis();
        assertEquals(4000, timeoutDurationInMillis);
    }
}
