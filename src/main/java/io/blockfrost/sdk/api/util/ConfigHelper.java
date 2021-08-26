package io.blockfrost.sdk.api.util;

import static io.blockfrost.sdk.api.util.ConfigConstants.*;

/**
 * Use this to set config parameters
 */
public enum ConfigHelper {
    INSTANCE();

    private int threadCount;
    private int rateLimitForPeriod;
    private int rateLimitRefreshPeriodInSec;
    private int timeoutDurationInMillis;

    ConfigHelper() {
        initEnv();
    }

    private ConfigHelper getInstance() {
        return INSTANCE;
    }

    public void initEnv() {
        //Init threadCount
        threadCount = getPropertyIntValue(BF_API_MAX_THREADS, 10);
        rateLimitForPeriod = getPropertyIntValue(BF_RATE_LIMIT_FOR_PERIOD, 10);
        rateLimitRefreshPeriodInSec = getPropertyIntValue(BF_RATE_LIMIT_REFRESH_PERIOD_IN_SEC, 1);
        timeoutDurationInMillis = getPropertyIntValue(BF_TIMEOUT_DURATION_IN_MILLIS, 500);
    }

    public int getThreadCount() {
        return threadCount;
    }

    public void setThreadCount(int threadCount) {
        this.threadCount = threadCount;
    }

    public int getRateLimitForPeriod() {
        return rateLimitForPeriod;
    }

    public void setRateLimitForPeriod(int rateLimitForPeriod) {
        this.rateLimitForPeriod = rateLimitForPeriod;
    }

    public int getRateLimitRefreshPeriodInSec() {
        return rateLimitRefreshPeriodInSec;
    }

    public void setRateLimitRefreshPeriodInSec(int rateLimitRefreshPeriodInSec) {
        this.rateLimitRefreshPeriodInSec = rateLimitRefreshPeriodInSec;
    }

    public int getTimeoutDurationInMillis() {
        return timeoutDurationInMillis;
    }

    public void setTimeoutDurationInMillis(int timeoutDurationInMillis) {
        this.timeoutDurationInMillis = timeoutDurationInMillis;
    }

    private int getPropertyIntValue(String envName, int defaultValue) {
        String strValue = System.getProperty(envName);
        if (strValue == null || strValue.isEmpty()) {
            strValue = System.getenv(envName);
        }

        if(strValue == null || strValue.isEmpty())
            return defaultValue;

        try {
            return Integer.parseInt(strValue);
        } catch (Exception exp){
            return defaultValue;
        }
    }
}
