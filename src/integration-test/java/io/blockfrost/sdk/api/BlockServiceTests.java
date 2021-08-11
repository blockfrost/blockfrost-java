package io.blockfrost.sdk.api;

import io.blockfrost.sdk.api.exception.APIException;
import io.blockfrost.sdk.api.model.Block;
import io.blockfrost.sdk.api.util.Constants;
import io.blockfrost.sdk.api.util.OrderEnum;
import io.blockfrost.sdk.impl.BlockServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class BlockServiceTests extends TestBase {

    BlockService blockService;

    @BeforeEach
    public void setup(){
        blockService = new BlockServiceImpl(Constants.BLOCKFROST_TESTNET_URL, projectId);
    }

    @Test
    public void latestBlock_willReturn_latestBlock() throws APIException {

        Block latestBlock = blockService.getLatestBlock();
        assertThat(latestBlock, is(notNullValue()));

    }

    @Test
    public void block_willReturn_BlockForHash() throws APIException {

        Block expectedBlock = Block.builder()
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

        Block blockForHash = blockService.getBlock("1");
        assertThat(blockForHash, is(notNullValue()));
        assertThat(blockForHash, samePropertyValuesAs(expectedBlock, "confirmations"));

    }

    @Test
    public void blockForSlot_willReturn_blockForGivenSlot() throws APIException {

        Block expectedBlock = Block.builder()
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

        Block blockInSlot = blockService.getBlockInSlot(1031);
        assertThat(blockInSlot, is(notNullValue()));
        assertThat(blockInSlot, samePropertyValuesAs(expectedBlock, "confirmations"));

    }

    @Test
    public void blockForEpochSlot_willReturn_blockForGivenEpochAndSlot() throws APIException {

        Block expectedBlock = Block.builder()
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

        Block blockInSlot = blockService.getBlockInEpochInSlot(0, 1031);
        assertThat(blockInSlot, is(notNullValue()));
        assertThat(blockInSlot, samePropertyValuesAs(expectedBlock, "confirmations"));

    }

    @Nested
    @DisplayName("GetTransactionsInLatestBlock Tests")
    class GetTransactionsInLatestBlockTests {

        @Test
        public void transactionsInLatestBlock_willReturn_transactionsInLatestBlockForCountPageAndAscendingOrder() throws APIException {

            List<String> transactionsInLatestBlock = blockService.getTransactionsInLatestBlock(5, 1, OrderEnum.asc);

            assertThat(transactionsInLatestBlock, hasSize(lessThanOrEqualTo(5)));
        }

        @Test
        public void transactionsInLatestBlock_willReturn_transactionsInLatestBlockForCountPage() throws APIException {

            List<String> transactionsInLatestBlock = blockService.getTransactionsInLatestBlock(5, 1);

            assertThat(transactionsInLatestBlock, hasSize(lessThanOrEqualTo(5)));
        }

    }

    @Nested
    @DisplayName("GetNextBlocks Tests")
    class GetNextBlocks {

        @Test
        public void nextBlocks_willReturn_nextBlocksForCountAndPage() throws APIException {

            Block expectedBlock = Block.builder()
                    .time(1564020256)
                    .height(2)
                    .hash("f4e96309537d15682211fcac4c249c2bdff8464476e047be99d80edf97bcf3ff")
                    .slot(1032)
                    .epoch(0)
                    .epochSlot(1032)
                    .slotLeader("ByronGenesis-42186a6a0079ef39")
                    .size(5799)
                    .txCount(0)
                    .previousBlock("388a82f053603f3552717d61644a353188f2d5500f4c6354cc1ad27a36a7ea91")
                    .nextBlock("067e773e6ffd66ea06f7f1c967e18a1ee0916797f6a1c1abdf410379eb8b1dbe")
                    .confirmations(2803485)
                    .build();

            List<Block> nextBlocks = blockService.getNextBlocks("1", 1, 1);

            assertThat(nextBlocks, hasSize(1));
            assertThat(nextBlocks, hasItem(samePropertyValuesAs(expectedBlock, "confirmations")));
        }

        @Test
        public void nextBlocks_willThrowAPIException_onNullHash() {

            Exception exception = assertThrows(APIException.class, () -> blockService.getNextBlocks(null, 5, 1));
            assertThat(exception.getMessage(), is("Hash cannot be null or empty"));
        }

    }

    @Nested
    @DisplayName("GetPreviousBlocks Tests")
    class GetPreviousBlocks {

        @Test
        public void previousBlocks_willReturn_previousBlocksForCountAndPage() throws APIException {

            Block expectedBlock = Block.builder()
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

            List<Block> previousBlocks = blockService.getPreviousBlocks("2", 1, 1);

            assertThat(previousBlocks, hasSize(1));
            assertThat(previousBlocks, hasItem(samePropertyValuesAs(expectedBlock, "confirmations")));
        }

        @Test
        public void previousBlocks_willThrowAPIException_onNullHash() {

            Exception exception = assertThrows(APIException.class, () -> blockService.getPreviousBlocks(null, 5, 1));
            assertThat(exception.getMessage(), is("Hash cannot be null or empty"));
        }

    }    

}
