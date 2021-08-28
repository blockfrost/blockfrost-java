package io.blockfrost.sdk.api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;

/**
 * AssetHistory
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class AssetHistory {
    /**
     * Hash of the transaction containing the asset action
     */
    private String txHash;
    /**
     * Action executed upon the asset policy
     */
    private String amount;
    /**
     * Asset amount of the specific action
     */
    private String action;
}

