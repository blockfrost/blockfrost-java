package io.blockfrost.sdk.api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

/**
 * TxContentUtxo
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)

public class TransactionUtxo {
    private String hash;
    private List<TransactionUtxoInputs> inputs = new ArrayList<TransactionUtxoInputs>();
    private List<TransactionUtxoOutputs> outputs = new ArrayList<TransactionUtxoOutputs>();

}

