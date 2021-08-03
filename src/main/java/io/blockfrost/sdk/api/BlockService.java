package io.blockfrost.sdk.api;

import io.blockfrost.sdk.api.exception.APIException;
import io.blockfrost.sdk.api.model.BlockContent;

public interface BlockService {

    /**
     * Latest block
     * Return the latest block available to the backends, also known as the tip of the blockchain.
     * @return BlockContent
     */
    BlockContent getLatestBlock() throws APIException;

    /**
     * Specific block
     * Return the content of a requested block.
     * @param hashOrNumber Hash of the requested block. (required)
     * @return BlockContent
     */
    BlockContent getBlock(String hashOrNumber) throws APIException;

    /**
     * Specific block in a slot
     * Return the content of a requested block for a specific slot.
     * @param slotNumber Slot position for requested block. (required)
     * @return BlockContent
     */
    BlockContent getBlockInSlot(int slotNumber) throws APIException;

    /**
     * Specific block in a slot in an epoch
     * Return the content of a requested block for a specific slot in an epoch
     * @param epochNumber Epoch for specific epoch slot. (required)
     * @param slotNumber Slot position for requested block. (required)
     * @return BlockContent
     */
    BlockContent getBlockInEpochInSlot(int epochNumber, int slotNumber) throws APIException;

}
