package io.blockfrost.sdk.api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;

/**
 * Account
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class Account {
    private String stakeAddress;
    private Boolean active;
    private Integer activeEpoch;
    private String controlledAmount;
    private String rewardsSum;
    private String withdrawalsSum;
    private String reservesSum;
    private String treasurySum;
    private String withdrawableAmount;
    private String poolId;
}
