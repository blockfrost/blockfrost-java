package io.blockfrost.sdk.api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;

/**
 * AccountRewardHistory
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class AccountRewardHistory {
    /**
     * Epoch of the associated reward
     */
    private Integer epoch;
    /**
     * Rewards for given epoch in Lovelaces
     */
    private String amount;
    /**
     * Bech32 pool ID being delegated to
     */
    private String poolId;
}
