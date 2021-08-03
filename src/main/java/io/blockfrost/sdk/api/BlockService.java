package io.blockfrost.sdk.api;

import io.blockfrost.sdk.api.exception.APIException;
import io.blockfrost.sdk.api.model.BlockContent;
import io.blockfrost.sdk.api.util.OrderEnum;

import java.util.List;

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

    /**
     * Latest block transactions
     * Return the transactions within the latest block.
     * @param count The number of results displayed on one page. (&lt;=100).
     * @param page The page number for listing the results.
     * @param order Ordered by tx index in the block. The ordering of items from the point of view of the blockchain, not the page listing itself. By default, we return oldest first, newest last
     * @return List&lt;String&gt;
     */
    List<String> getTransactionsInLatestBlock(int count, int page, OrderEnum order) throws APIException;

    /**
     * Latest block transactions
     * Return the transactions within the latest block ordered from the point of view of the blockchain, not the page listing itself.
     * @param count The number of results displayed on one page. (&lt;=100).
     * @param page The page number for listing the results.
     * @return List&lt;String&gt;
     */
    List<String> getTransactionsInLatestBlock(int count, int page) throws APIException;


    /**
     * Latest block transactions
     * Return all the transactions within the latest block.
     * @param order Ordered by tx index in the block. The ordering of items from the point of view of the blockchain, not the page listing itself. By default, we return oldest first, newest last
     * @return List&lt;String&gt;
     */
    List<String> getTransactionsInLatestBlock(OrderEnum order) throws APIException;

    /**
     * Latest block transactions
     * Return all the transactions within the latest block ordered from the point of view of the blockchain, not the page listing itself.
     * @return List&lt;String&gt;
     */
    List<String> getTransactionsInLatestBlock() throws APIException;

    /**
     * Listing of next blocks
     * Return the list of blocks following a specific block.
     * @param hashOrNumber Hash of the requested block.
     * @param count The number of results displayed on one page. (&lt;=100).
     * @param page The page number for listing the results.
     * @return List&lt;BlockContent&gt
     */
    List<BlockContent> getNextBlocks(String hashOrNumber, int count, int page) throws APIException;

    /**
     * Listing of next blocks
     * Return the list of all blocks following a specific block.
     * @param hashOrNumber Hash of the requested block.
     * @return List&lt;BlockContent&gt
     */
    List<BlockContent> getNextBlocks(String hashOrNumber) throws APIException;

    /**
     * Listing of previous blocks
     * Return the list of blocks following a specific block.
     * @param hashOrNumber Hash of the requested block.
     * @param count The number of results displayed on one page. (&lt;=100).
     * @param page The page number for listing the results.
     * @return List&lt;BlockContent&gt
     */
    List<BlockContent> getPreviousBlocks(String hashOrNumber, int count, int page) throws APIException;

    /**
     * Listing of previous blocks
     * Return the list of all blocks following a specific block.
     * @param hashOrNumber Hash of the requested block.
     * @return List&lt;BlockContent&gt
     */
    List<BlockContent> getPreviousBlocks(String hashOrNumber) throws APIException;
}
