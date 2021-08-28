package io.blockfrost.sdk.api.model.nutlink;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;

/*
Ticker
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class Ticker {

    /**
     * Name of the ticker
     */
    private String name;

    /**
     * Number of ticker records
     */
    private Long count;

    /**
     * Block height of the latest record
     */
    private Long latestBlock;
}
