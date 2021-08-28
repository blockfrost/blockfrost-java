package io.blockfrost.sdk.api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Address
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class Address {
    /**
     * Bech32 encoded addresses
     */
    private String address;
    /**
     * Array of TransactionOutputAmount objects
     */
    private List<TransactionOutputAmount> amount = new ArrayList<TransactionOutputAmount>();
    /**
     * Stake address that controls the key
     */
    private String stakeAddress;
    /**
     * Enum: "byron" "shelley"
     * Address era
     */
    private AddressType type;
}

