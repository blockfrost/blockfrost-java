package io.blockfrost.sdk.api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;

import java.math.BigDecimal;

/**
 * GenesisContent
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class Genesis {
    private BigDecimal activeSlotsCoefficient;
    private Integer updateQuorum;
    private String maxLovelaceSupply;
    private Integer networkMagic;
    private Integer epochLength;
    private Integer systemStart;
    private Integer slotsPerKesPeriod;
    private Integer slotLength;
    private Integer maxKesEvolutions;
    private Integer securityParam;

}

