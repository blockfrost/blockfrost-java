package io.blockfrost.sdk.api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;

/**
 * AccountHistory
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class AccountHistory {
    /**
     * Epoch in which the stake was active.
     */
    private Integer activeEpoch;
    /**
     * Stake amount in Lovelaces
     */
    private String amount;
    /**
     * Bech32 ID of pool being delegated to
     */
    private String poolId;
}
