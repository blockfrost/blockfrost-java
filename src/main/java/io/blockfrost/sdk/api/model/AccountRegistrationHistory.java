package io.blockfrost.sdk.api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;

/**
 * AccountRegistrationHistory
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class AccountRegistrationHistory {
    /**
     * Hash of the transaction containing the (de)registration certificate
     */
    private String txHash;
    /**
     * Enum: "registered" "deregistered"
     * Action in the certificate
     */
    private AccountRegistrationAction action;
}
