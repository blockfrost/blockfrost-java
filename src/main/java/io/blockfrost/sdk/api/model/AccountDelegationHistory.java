package io.blockfrost.sdk.api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;

/**
 * AccountDelegationHistory
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class AccountDelegationHistory {
    /**
     * Epoch in which the delegation becomes active
     */
    private Integer activeEpoch;
    /**
     * Hash of the transaction containing the delegation
     */
    private String txHash;
    /**
     * Rewards for given epoch in Lovelaces
     */
    private String amount;
    /**
     * Bech32 ID of pool being delegated to
     */
    private String poolId;
}
