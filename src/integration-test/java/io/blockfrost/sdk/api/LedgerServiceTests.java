package io.blockfrost.sdk.api;

import io.blockfrost.sdk.api.exception.APIException;
import io.blockfrost.sdk.impl.LedgerServiceImpl;
import io.blockfrost.sdk.api.util.Constants;
import io.blockfrost.sdk.api.model.Genesis;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class LedgerServiceTests extends TestBase {

    LedgerService ledgerService;

    @BeforeEach
    public void setup(){
        ledgerService = new LedgerServiceImpl(Constants.BLOCKFROST_TESTNET_URL, projectId);
    }

    //TODO: Network magic and system start are different from the Api Documentation
    @Test
    public void genesis_willReturn_blockchainGenesis() throws APIException {

        Genesis expectedGenesis = Genesis.builder()
                .activeSlotsCoefficient(new BigDecimal("0.05"))
                .updateQuorum(5)
                .maxLovelaceSupply("45000000000000000")
                .networkMagic(1097911063)
                .epochLength(432000)
                .systemStart(1563999616)
                .slotsPerKesPeriod(129600)
                .slotLength(1)
                .maxKesEvolutions(62)
                .securityParam(2160)
                .build();

        Genesis genesisResponse = ledgerService.getGenesis();
        assertThat(genesisResponse, is(expectedGenesis));
    }

}
