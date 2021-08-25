package io.blockfrost.sdk.api.util;

import io.github.resilience4j.ratelimiter.RateLimiter;
import io.github.resilience4j.ratelimiter.RateLimiterConfig;

import java.time.Duration;

public class RateLimitHelper {

    public static RateLimiter rateLimiter(){

        RateLimiterConfig rateLimiterConfig = RateLimiterConfig.custom().
                limitForPeriod(10).
                limitRefreshPeriod(Duration.ofSeconds(1)).
                timeoutDuration(Duration.ofMillis(500)).build();

        return RateLimiter.of("blockfrostAPI", rateLimiterConfig );

    }

}
