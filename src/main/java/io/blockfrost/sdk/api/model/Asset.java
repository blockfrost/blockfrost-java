package io.blockfrost.sdk.api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;

import java.util.Map;

/**
 * Asset
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class Asset {
    /**
     * Hex-encoded asset full name
     */
    private String asset;
    /**
     * Policy ID of the asset
     */
    private String policyId;
    /**
     * Hex-encoded asset name of the asset
     */
    private String assetName;
    /**
     * CIP14 based user-facing fingerprint
     */
    private String fingerprint;
    /**
     * Current asset quantity
     */
    private String quantity;
    /**
     * ID of the initial minting transaction
     */
    private String initialMintTxHash;
    /**
     * Count of mint and burn transactions
     */
    private Integer mintOrBurnCount;
    /**
     * On-chain metadata stored in the minting transaction under label 721,
     * community discussion around the standard ongoing at
     * https://github.com/cardano-foundation/CIPs/pull/85
     */
    private Map<String, Object> onchainMetadata;
    /**
     * Asset metadata
     */
    private AssetMetadata metadata;
}

