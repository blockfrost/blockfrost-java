package io.blockfrost.sdk.api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Pool
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class Pool {
    private String poolId;
    private String hex;
    private String vrfKey;
    private Integer blocksMinted;
    private String liveStake;
    private BigDecimal liveSize;
    private BigDecimal liveSaturation;
    private BigDecimal liveDelegators;
    private String activeStake;
    private BigDecimal activeSize;
    private String declaredPledge;
    private String livePledge;
    private BigDecimal marginCost;
    private String fixedCost;
    private String rewardAccount;
    private List<String> owners = new ArrayList<String>();
    private List<String> registration = new ArrayList<String>();
    private List<String> retirement = new ArrayList<String>();

}

