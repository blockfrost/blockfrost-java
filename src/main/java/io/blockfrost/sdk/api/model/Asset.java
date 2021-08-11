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
    private String asset;
    private String policyId;
    private String assetName;
    private String fingerprint;
    private String quantity;
    private String initialMintTxHash;
    private Integer mintOrBurnCount;
    private Map<String, String> onchainMetadata;
    private AssetMetadata metadata;
}

