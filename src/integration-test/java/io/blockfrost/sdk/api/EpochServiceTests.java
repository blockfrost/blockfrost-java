package io.blockfrost.sdk.api;

import io.blockfrost.sdk.api.exception.APIException;
import io.blockfrost.sdk.api.model.Epoch;
import io.blockfrost.sdk.api.model.EpochParam;
import io.blockfrost.sdk.api.model.Stake;
import io.blockfrost.sdk.api.util.Constants;
import io.blockfrost.sdk.api.util.OrderEnum;
import io.blockfrost.sdk.impl.EpochServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class EpochServiceTests extends TestBase {

    EpochService epochService;

    @BeforeEach
    public void setup(){
        epochService = new EpochServiceImpl(Constants.BLOCKFROST_TESTNET_URL, projectId);
    }

    @Test
    public void latestEpoch_willReturn_latestEpoch() throws APIException {

        Epoch latestEpoch = epochService.getLatestEpoch();
        assertThat(latestEpoch, is(notNullValue()));

    }

    @Test
    public void latestEpochParam_willReturn_latestEpochParam() throws APIException {

        EpochParam latestEpochParam = epochService.getLatestEpochParam();
        assertThat(latestEpochParam, is(notNullValue()));

    }

    @Test
    public void getEpoch_willReturn_epochForEpochNumber() throws APIException {

        Epoch expectedEpoch = Epoch.builder()
                .epoch(1)
                .startTime(1564431616)
                .endTime(1564863616)
                .firstBlockTime(1564431616)
                .lastBlockTime(1564863596)
                .blockCount(21601)
                .txCount(305)
                .output("152336265877919")
                .fees("54105620")
                .activeStake(null)
                .build();

        Epoch epoch = epochService.getEpoch(1);
        assertThat(epoch, is(expectedEpoch));

    }

    @Test
    public void nextEpochs_willReturn_nextEpochsForCountAndPage() throws APIException {

        Epoch expectedEpoch = Epoch.builder()
                .epoch(2)
                .startTime(1564863616)
                .endTime(1565295616)
                .firstBlockTime(1564863616)
                .lastBlockTime(1565295596)
                .blockCount(21601)
                .txCount(182)
                .output("35581408008991")
                .fees("40548594")
                .activeStake(null)
                .build();

        List<Epoch> nextEpochs = epochService.getNextEpochs(1, 5, 1);

        assertThat(nextEpochs, hasSize(5));
        assertThat(nextEpochs, hasItem(samePropertyValuesAs(expectedEpoch)));
    }

    @Test
    public void previousEpochs_willReturn_previousEpochsForCountAndPage() throws APIException {

        Epoch expectedEpoch = Epoch.builder()
                .epoch(1)
                .startTime(1564431616)
                .endTime(1564863616)
                .firstBlockTime(1564431616)
                .lastBlockTime(1564863596)
                .blockCount(21601)
                .txCount(305)
                .output("152336265877919")
                .fees("54105620")
                .activeStake(null)
                .build();

        List<Epoch> previousEpochs = epochService.getPreviousEpochs(2, 1, 1);

        assertThat(previousEpochs, hasSize(1));
        assertThat(previousEpochs, hasItem(samePropertyValuesAs(expectedEpoch)));
    }

    @Test
    public void activeStakesForEpoch_willReturn_activeStakesForEpochForCountAndPage() throws APIException {

        Epoch latestEpoch = epochService.getLatestEpoch();
        List<Stake> activeStakes = epochService.getActiveStakesForEpoch(latestEpoch.getEpoch(), 5, 1);

        assertThat(activeStakes, hasSize(lessThanOrEqualTo(5)));

    }

    @Test
    public void activeStakesForEpochAndPool_willReturn_activeStakesForEpochAndPoolForCountAndPage() throws APIException {

        Epoch latestEpoch = epochService.getLatestEpoch();
        List<Stake> activeStakes = epochService.getActiveStakesForEpoch(latestEpoch.getEpoch(), 1, 1);
        List<Stake> activeStakesForPool = epochService.getActiveStakesForEpochAndPool(latestEpoch.getEpoch(), activeStakes.get(0).getPoolId(), 5, 1);

        assertThat(activeStakes, hasSize(lessThanOrEqualTo(5)));

    }

    @Test
    public void activeStakesForEpochAndPool_willThrowAPIException_onNullPoolId() {

        Exception exception = assertThrows(APIException.class, () -> epochService.getActiveStakesForEpochAndPool(1, null, 5, 1));
        assertThat(exception.getMessage(), is("PoolId cannot be null or empty"));
    }

    @Test
    public void epochParam_willReturn_epochParamForEpoch() throws APIException {

        EpochParam expectedEpochParam = EpochParam.builder()
                .epoch(140)
                .minFeeA(44)
                .minFeeB(155381)
                .maxBlockSize(65536)
                .maxTxSize(16384)
                .maxBlockHeaderSize(1100)
                .keyDeposit("2000000")
                .poolDeposit("500000000")
                .eMax(null)
                .nOpt(null)
                .a0(new BigDecimal("0.3"))
                .rho(new BigDecimal("0.003"))
                .tau(new BigDecimal("0.2"))
                .decentralisationParam(new BigDecimal("0"))
                .extraEntropy(null)
                .protocolMajorVer(4)
                .protocolMinorVer(0)
                .minUtxo("1000000")
                .minPoolCost("340000000")
                .nonce("02b62200eca66c5896fab9a3f5421c72958df1f3279aa2f5fc72c514cec16ed9")
                .build();

        EpochParam latestEpochParam = epochService.getEpochParam(140);
        assertThat(latestEpochParam, is(expectedEpochParam));

    }

    @Test
    public void blocksForEpoch_willReturn_blocksForEpochForCountPageAndOrder() throws APIException {

        String blockHash = "7e8b2df7730261d8831fe0206591570734d353c15d5266b7fe77097090d33cbd";

        List<String> blocksForEpoch = epochService.getBlocksForEpoch(1, 5, 1, OrderEnum.asc);

        assertThat(blocksForEpoch, hasSize(lessThanOrEqualTo(5)));
        assertThat(blocksForEpoch, hasItem(blockHash));

    }

    @Test
    public void blocksForEpoch_willReturn_blocksForEpochForCountPage() throws APIException {

        String blockHash = "7e8b2df7730261d8831fe0206591570734d353c15d5266b7fe77097090d33cbd";

        List<String> blocksForEpoch = epochService.getBlocksForEpoch(1, 5, 1);

        assertThat(blocksForEpoch, hasSize(lessThanOrEqualTo(5)));
        assertThat(blocksForEpoch, hasItem(blockHash));

    }
}

