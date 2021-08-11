package io.blockfrost.sdk.api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

/**
 * TxContent
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)

public class Transaction {
    private String hash;
    private String block;
    private Integer blockHeight;
    private Integer slot;
    private Integer index;
    private List<TransactionOutputAmount> outputAmount = new ArrayList<TransactionOutputAmount>();
    private String fees;
    private String deposit;
    private Integer size;
    private String invalidBefore;
    private String invalidHereafter;
    private Integer utxoCount;
    private Integer withdrawalCount;
    private Integer mirCertCount;
    private Integer delegationCount;
    private Integer stakeCertCount;
    private Integer poolUpdateCount;
    private Integer poolRetireCount;
    private Integer assetMintOrBurnCount;

}

