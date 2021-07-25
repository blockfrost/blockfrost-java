package io.blockfrost.sdk.api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

/**
 * TransactionStakeCertificate
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)

public class TransactionPoolUpdate {
    private Integer certIndex;
    private String poolId;
    private String vrfKey;
    private String pledge;
    private BigDecimal marginCost;
    private String fixedCost;
    private String rewardAccount;
    private List<String> owners;
    private PoolMetadata metadata;
    private List<PoolRelays> relays;
    private Integer activeEpoch;

}
