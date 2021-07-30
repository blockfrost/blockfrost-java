package io.blockfrost.sdk.api;

import io.blockfrost.sdk.api.exception.APIException;
import io.blockfrost.sdk.api.model.BlockContent;
import io.blockfrost.sdk.api.util.Constants;
import io.blockfrost.sdk.impl.BlockServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class BlockServiceTests extends TestBase {

    BlockService blockService;

    @BeforeEach
    public void setup(){
        blockService = new BlockServiceImpl(Constants.BLOCKFROST_TESTNET_URL, projectId);
    }

    @Test
    public void latestBlock_willReturn_latestBlock() throws APIException {

        BlockContent latestBlock = blockService.getLatestBlock();
        assertThat(latestBlock, is(notNullValue()));

    }

    @Test
    public void block_willReturn_BlockForHash() throws APIException {

        BlockContent expectedBlockContent = BlockContent.builder()
                .time(1564020236)
                .height(1)
                .hash("388a82f053603f3552717d61644a353188f2d5500f4c6354cc1ad27a36a7ea91")
                .slot(1031)
                .epoch(0)
                .epochSlot(1031)
                .slotLeader("ByronGenesis-853b49c9ab5fc52d")
                .size(1950)
                .txCount(0)
                .previousBlock("8f8602837f7c6f8b8867dd1cbc1842cf51a27eaed2c70ef48325d00f8efb320f")
                .nextBlock("f4e96309537d15682211fcac4c249c2bdff8464476e047be99d80edf97bcf3ff")
                .confirmations(2793414)
                .build();

        BlockContent blockForHash = blockService.getBlock("1");
        assertThat(blockForHash, is(notNullValue()));
        assertThat(blockForHash, samePropertyValuesAs(expectedBlockContent, "confirmations"));

    }

    @Test
    public void blockForSlot_willReturn_blockForGivenSlot() throws APIException {

        BlockContent expectedBlockContent = BlockContent.builder()
                .time(1564020236)
                .height(1)
                .hash("388a82f053603f3552717d61644a353188f2d5500f4c6354cc1ad27a36a7ea91")
                .slot(1031)
                .epoch(0)
                .epochSlot(1031)
                .slotLeader("ByronGenesis-853b49c9ab5fc52d")
                .size(1950)
                .txCount(0)
                .previousBlock("8f8602837f7c6f8b8867dd1cbc1842cf51a27eaed2c70ef48325d00f8efb320f")
                .nextBlock("f4e96309537d15682211fcac4c249c2bdff8464476e047be99d80edf97bcf3ff")
                .confirmations(2793414)
                .build();

        BlockContent blockInSlot = blockService.getBlockInSlot(1031);
        assertThat(blockInSlot, is(notNullValue()));
        assertThat(blockInSlot, samePropertyValuesAs(expectedBlockContent, "confirmations"));

    }

}
