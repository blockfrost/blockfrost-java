package io.blockfrost.sdk.impl;

import io.blockfrost.sdk.api.BlockService;
import io.blockfrost.sdk.api.exception.APIException;
import io.blockfrost.sdk.api.model.BlockContent;
import io.blockfrost.sdk.impl.retrofit.BlocksApi;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;

public class BlockServiceImpl extends BaseImpl implements BlockService {

    BlocksApi blocksApi;

    public BlockServiceImpl(String baseUrl, String projectId){
        super(baseUrl, projectId);
        blocksApi = getRetrofit().create(BlocksApi.class);
    }


    @Override
    public BlockContent getLatestBlock() throws APIException {
        Call<BlockContent> latestBlockCall = blocksApi.blocksLatestGet(getProjectId());

        try{
            Response<BlockContent> latestBlockResponse = latestBlockCall.execute();
            return processResponse(latestBlockResponse);
        } catch (IOException exp){
            throw new APIException("Exception while fetching latest block", exp);
        }
    }

    @Override
    public BlockContent getBlock(String hashOrNumber) throws APIException {
        
        if ( hashOrNumber == null || hashOrNumber.equals("")){
            throw new APIException("Hash cannot be null or empty");
        }
        
        Call<BlockContent> blockCall = blocksApi.blocksHashOrNumberGet(getProjectId(), hashOrNumber);

        try{
            Response<BlockContent> blockResponse = blockCall.execute();
            return processResponse(blockResponse);
        } catch (IOException exp){
            throw new APIException("Exception while fetching block for hash: " + hashOrNumber, exp);
        }
    }

    @Override
    public BlockContent getBlockInSlot(int slotNumber) throws APIException {

        Call<BlockContent> blockInSlotCall = blocksApi.blocksSlotSlotNumberGet(getProjectId(), slotNumber);

        try{
            Response<BlockContent> blockInSlotResponse = blockInSlotCall.execute();
            return processResponse(blockInSlotResponse);
        } catch (IOException exp){
            throw new APIException("Exception while fetching block in slot: " + slotNumber, exp);
        }
    }

    @Override
    public BlockContent getBlockInEpochInSlot(int epochNumber, int slotNumber) throws APIException {

        Call<BlockContent> blockInEpochInSlotCall = blocksApi.blocksEpochEpochNumberSlotSlotNumberGet(getProjectId(), epochNumber, slotNumber);

        try{
            Response<BlockContent> blockInEpochInSlotResponse = blockInEpochInSlotCall.execute();
            return processResponse(blockInEpochInSlotResponse);
        } catch (IOException exp){
            throw new APIException("Exception while fetching block in epoch: " + epochNumber + " in slot: " + slotNumber, exp);
        }
    }
}
