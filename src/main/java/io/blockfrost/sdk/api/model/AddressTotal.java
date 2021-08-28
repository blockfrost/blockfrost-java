package io.blockfrost.sdk.api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

/**
 * AddressTotal
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class AddressTotal {
    /**
     * Bech32 encoded address
     */
    private String address;
    /**
     * Array of TransactionOutputAmount objects
     */
    private List<TransactionOutputAmount> receivedSum = new ArrayList<TransactionOutputAmount>();
    /**
     * Array of TransactionOutputAmount objects
     */
    private List<TransactionOutputAmount> sentSum = new ArrayList<TransactionOutputAmount>();
    /**
     * Count of all transactions on the address
     */
    private Integer txCount;
}

