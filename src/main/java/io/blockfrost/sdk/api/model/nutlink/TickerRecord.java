package io.blockfrost.sdk.api.model.nutlink;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class TickerRecord {
    /**
     * Address of a metadata oracle
     */
    private String address;

    /**
     * Hash of the transaction
     */
    private String txHash;

    /**
     * Block height of the record
     */
    private Long blockHeight;

    /**
     * Transaction index within the block
     */
    private int txIndex;

    /**
     * Content of the ticker
     */
    private List<TickerPayload> payload;
}
