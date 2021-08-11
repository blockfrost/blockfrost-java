package io.blockfrost.sdk.api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;

/**
 * Block
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class Block {
    private Integer time;
    private Integer height;
    private String hash;
    private Integer slot;
    private Integer epoch;
    private Integer epochSlot;
    private String slotLeader;
    private Integer size;
    private Integer txCount;
    private String output;
    private String fees;
    private String blockVrf;
    private String previousBlock;
    private String nextBlock;
    private Integer confirmations;
}

