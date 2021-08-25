package io.blockfrost.sdk.impl;

import io.blockfrost.sdk.api.BlockService;
import io.blockfrost.sdk.api.exception.APIException;
import io.blockfrost.sdk.api.model.Block;
import io.blockfrost.sdk.api.util.OrderEnum;
import io.blockfrost.sdk.impl.retrofit.BlocksApi;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;
import java.util.List;

public class BlockServiceImpl extends BaseService implements BlockService {

    BlocksApi blocksApi;

    public BlockServiceImpl(String baseUrl, String projectId) {
        super(baseUrl, projectId);
        blocksApi = getRetrofit().create(BlocksApi.class);
    }

    private void validateHashOrNumber(String hashOrNumber) throws APIException {
        if (hashOrNumber == null || hashOrNumber.equals("")) {
            throw new APIException("Hash cannot be null or empty");
        }
    }

    @Override
    public Block getLatestBlock() throws APIException {
        Call<Block> latestBlockCall = blocksApi.blocksLatestGet(getProjectId());

        try {
            Response<Block> latestBlockResponse = latestBlockCall.execute();
            return processResponse(latestBlockResponse);
        } catch (IOException exp) {
            throw new APIException("Exception while fetching latest block", exp);
        }
    }

    @Override
    public Block getBlock(String hashOrNumber) throws APIException {

        validateHashOrNumber(hashOrNumber);

        Call<Block> blockCall = blocksApi.blocksHashOrNumberGet(getProjectId(), hashOrNumber);

        try {
            Response<Block> blockResponse = blockCall.execute();
            return processResponse(blockResponse);
        } catch (IOException exp) {
            throw new APIException("Exception while fetching block for hash: " + hashOrNumber, exp);
        }
    }

    @Override
    public Block getBlockInSlot(int slotNumber) throws APIException {

        Call<Block> blockInSlotCall = blocksApi.blocksSlotSlotNumberGet(getProjectId(), slotNumber);

        try {
            Response<Block> blockInSlotResponse = blockInSlotCall.execute();
            return processResponse(blockInSlotResponse);
        } catch (IOException exp) {
            throw new APIException("Exception while fetching block in slot: " + slotNumber, exp);
        }
    }

    @Override
    public Block getBlockInEpochInSlot(int epochNumber, int slotNumber) throws APIException {

        Call<Block> blockInEpochInSlotCall = blocksApi.blocksEpochEpochNumberSlotSlotNumberGet(getProjectId(), epochNumber, slotNumber);

        try {
            Response<Block> blockInEpochInSlotResponse = blockInEpochInSlotCall.execute();
            return processResponse(blockInEpochInSlotResponse);
        } catch (IOException exp) {
            throw new APIException("Exception while fetching block in epoch: " + epochNumber + " in slot: " + slotNumber, exp);
        }
    }

    @Override
    public List<String> getTransactionsInLatestBlock(int count, int page, OrderEnum order) throws APIException {
        Call<List<String>> transactionsInLatestBlockCall = blocksApi.blocksLatestTxsGet(getProjectId(), count, page, order.name());

        try {
            Response<List<String>> transactionsInLatestBlockResponse = transactionsInLatestBlockCall.execute();
            return processResponse(transactionsInLatestBlockResponse);
        } catch (IOException exp) {
            throw new APIException("Exception while fetching transactions in latest block", exp);
        }
    }

    @Override
    public List<String> getTransactionsInLatestBlock(int count, int page) throws APIException {
        return getTransactionsInLatestBlock(count, page, OrderEnum.asc);
    }

    //TODO: Implement
    @Override
    public List<String> getTransactionsInLatestBlock(OrderEnum order) throws APIException {
        return null;
    }

    @Override
    public List<String> getTransactionsInLatestBlock() throws APIException {
        return getTransactionsInLatestBlock(OrderEnum.asc);
    }

    @Override
    public List<Block> getNextBlocks(String hashOrNumber, int count, int page) throws APIException {

        validateHashOrNumber(hashOrNumber);

        Call<List<Block>> nextBlocksCall = blocksApi.blocksHashOrNumberNextGet(getProjectId(), hashOrNumber, count, page);

        try {
            Response<List<Block>> nextBlocksResponse = nextBlocksCall.execute();
            return processResponse(nextBlocksResponse);
        } catch (IOException exp) {
            throw new APIException("Exception while fetching next blocks for block: " + hashOrNumber, exp);
        }
    }

    //TODO: Implement
    @Override
    public List<Block> getNextBlocks(String hashOrNumber) throws APIException {
        return null;
    }

    @Override
    public List<Block> getPreviousBlocks(String hashOrNumber, int count, int page) throws APIException {
        validateHashOrNumber(hashOrNumber);

        Call<List<Block>> previousBlocksCall = blocksApi.blocksHashOrNumberPreviousGet(getProjectId(), hashOrNumber, count, page);

        try {
            Response<List<Block>> previousBlocksResponse = previousBlocksCall.execute();
            return processResponse(previousBlocksResponse);
        } catch (IOException exp) {
            throw new APIException("Exception while fetching previous blocks for block: " + hashOrNumber, exp);
        }
    }

    //TODO: Implement
    @Override
    public List<Block> getPreviousBlocks(String hashOrNumber) throws APIException {
        return null;
    }
}
