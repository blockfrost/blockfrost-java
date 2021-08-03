package io.blockfrost.sdk.impl.retrofit;

import io.blockfrost.sdk.api.model.BlockContent;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;
import retrofit2.http.Query;

import java.util.List;

public interface BlocksApi {
  /**
   * Specific block in a slot in an epoch
   * Return the content of a requested block for a specific slot in an epoch 
   * @param epochNumber Epoch for specific epoch slot. (required)
   * @param slotNumber Slot position for requested block. (required)
   * @return Call&lt;BlockContent&gt;
   */
  @GET("blocks/epoch/{epoch_number}/slot/{slot_number}")
  Call<BlockContent> blocksEpochEpochNumberSlotSlotNumberGet(
          @Header("project_id") String projectId,
          @Path("epoch_number") Integer epochNumber,
          @Path("slot_number") Integer slotNumber
  );

  /**
   * Specific block
   * Return the content of a requested block. 
   * @param hashOrNumber Hash of the requested block. (required)
   * @return Call&lt;BlockContent&gt;
   */
  @GET("blocks/{hash_or_number}")
  Call<BlockContent> blocksHashOrNumberGet(
          @Header("project_id") String projectId,
          @Path("hash_or_number") String hashOrNumber
  );

  /**
   * Listing of next blocks
   * Return the list of blocks following a specific block. 
   * @param hashOrNumber Hash of the requested block. (required)
   * @param count The number of results displayed on one page. (optional, default to 100)
   * @param page The page number for listing the results. (optional, default to 1)
   * @return Call&lt;List&lt;BlockContent&gt;&gt;
   */
  @GET("blocks/{hash_or_number}/next")
  Call<List<BlockContent>> blocksHashOrNumberNextGet(
          @Header("project_id") String projectId,
          @Path("hash_or_number") String hashOrNumber,
          @Query("count") Integer count,
          @Query("page") Integer page
  );

  /**
   * Listing of previous blocks
   * Return the list of blocks preceding a specific block. 
   * @param hashOrNumber Hash of the requested block (required)
   * @param count The number of results displayed on one page. (optional, default to 100)
   * @param page The page number for listing the results. (optional, default to 1)
   * @return Call&lt;List&lt;BlockContent&gt;&gt;
   */
  @GET("blocks/{hash_or_number}/previous")
  Call<List<BlockContent>> blocksHashOrNumberPreviousGet(
          @Path("hash_or_number") String hashOrNumber, @Query("count") Integer count, @Query("page") Integer page
  );

  /**
   * Block transactions
   * Return the transactions within the block.
   * @param hashOrNumber Hash of the requested block. (required)
   * @param count The number of results displayed on one page. (optional, default to 100)
   * @param page The page number for listing the results. (optional, default to 1)
   * @param order Ordered by tx index in the block. The ordering of items from the point of view of the blockchain, not the page listing itself. By default, we return oldest first, newest last.  (optional, default to asc)
   * @return Call&lt;List&lt;String&gt;&gt;
   */
  @GET("blocks/{hash_or_number}/txs")
  Call<List<String>> blocksHashOrNumberTxsGet(
          @Path("hash_or_number") String hashOrNumber, @Query("count") Integer count, @Query("page") Integer page, @Query("order") String order
  );

  /**
   * Latest block
   * Return the latest block available to the backends, also known as the tip of the blockchain. 
   * @return Call&lt;BlockContent&gt;
   */
  @GET("blocks/latest")
  Call<BlockContent> blocksLatestGet(@Header("project_id") String projectId);
    

  /**
   * Latest block transactions
   * Return the transactions within the latest block.
   * @param count The number of results displayed on one page. (optional, default to 100)
   * @param page The page number for listing the results. (optional, default to 1)
   * @param order Ordered by tx index in the block. The ordering of items from the point of view of the blockchain, not the page listing itself. By default, we return oldest first, newest last.  (optional, default to asc)
   * @return Call&lt;List&lt;String&gt;&gt;
   */
  @GET("blocks/latest/txs")
  Call<List<String>> blocksLatestTxsGet(
          @Header("project_id") String projectId,
          @Query("count") Integer count,
          @Query("page") Integer page,
          @Query("order") String order
  );

  /**
   * Specific block in a slot
   * Return the content of a requested block for a specific slot. 
   * @param slotNumber Slot position for requested block. (required)
   * @return Call&lt;BlockContent&gt;
   */
  @GET("blocks/slot/{slot_number}")
  Call<BlockContent> blocksSlotSlotNumberGet(
          @Header("project_id") String projectId,
        @Path("slot_number") Integer slotNumber
  );

}
