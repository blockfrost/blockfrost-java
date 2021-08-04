package io.blockfrost.sdk.api;

import io.blockfrost.sdk.api.exception.APIException;
import io.blockfrost.sdk.api.model.Epoch;
import io.blockfrost.sdk.api.model.EpochParam;
import io.blockfrost.sdk.api.util.Constants;
import io.blockfrost.sdk.impl.EpochServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

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
}