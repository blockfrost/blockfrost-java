package io.blockfrost.sdk.api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;

/**
 * AssetMetadata
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class AssetMetadata {
    /**
     * Name
     */
    private String name;
    /**
     * Description
     */
    private String description;
    /**
     * Ticker
     */
    private String ticker;
    /**
     * URL
     */
    private String url;
    /**
     * Logo
     */
    private String logo;
    /**
     * Decimals
     */
    private Integer decimals;
}

