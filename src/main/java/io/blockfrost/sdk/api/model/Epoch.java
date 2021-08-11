package io.blockfrost.sdk.api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;

/**
 * Epoch
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class Epoch {
    private Integer epoch;
    private Integer startTime;
    private Integer endTime;
    private Integer firstBlockTime;
    private Integer lastBlockTime;
    private Integer blockCount;
    private Integer txCount;
    private String output;
    private String fees;
    private String activeStake;
}

