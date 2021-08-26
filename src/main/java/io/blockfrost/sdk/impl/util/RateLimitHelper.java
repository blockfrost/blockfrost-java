package io.blockfrost.sdk.impl.util;

import io.blockfrost.sdk.api.util.ConfigHelper;
import io.github.resilience4j.ratelimiter.RateLimiter;
import io.github.resilience4j.ratelimiter.RateLimiterConfig;

import java.time.Duration;

public class RateLimitHelper {

    public static RateLimiter rateLimiter(){

        RateLimiterConfig rateLimiterConfig = RateLimiterConfig.custom().
                limitForPeriod(ConfigHelper.INSTANCE.getRateLimitForPeriod()).
                limitRefreshPeriod(Duration.ofSeconds(ConfigHelper.INSTANCE.getRateLimitRefreshPeriodInSec())).
                timeoutDuration(Duration.ofMillis(ConfigHelper.INSTANCE.getTimeoutDurationInMillis())).build();

        return RateLimiter.of("blockfrostAPI", rateLimiterConfig );

    }

}
