package io.blockfrost.sdk.api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

/**
 * AddressUtxo
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class AddressUtxo {
    /**
     * Transaction hash of the UTXO
     */
    private String txHash;
    /**
     * UTXO index in the transaction
     */
    private Integer outputIndex;
    /**
     * Array of TransactionOutputAmount objects
     */
    private List<TransactionOutputAmount> amount = new ArrayList<TransactionOutputAmount>();
    /**
     * Block number of the UTXO
     */
    private String block;

}

