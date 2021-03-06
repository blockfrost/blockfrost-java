package io.blockfrost.sdk.impl.retrofit;

import io.blockfrost.sdk.api.model.*;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;
import retrofit2.http.Query;

import java.util.List;

public interface PoolsApi {
    /**
     * List of networkStake pools
     * List of registered networkStake pools.
     *
     * @param count The numbers of pools per page. (optional, default to 100)
     * @param page  The page number for listing the results. (optional, default to 1)
     * @param order The ordering of items from the point of view of the blockchain, not the page listing itself. By default, we return oldest first, newest last.  (optional, default to asc)
     * @return Call&lt;List&lt;String&gt;&gt;
     */
    @GET("pools")
    Call<List<String>> poolsGet(
            @Header("project_id") String projectId,
            @Query("count") Integer count,
            @Query("page") Integer page,
            @Query("order") String order
    );

    /**
     * NetworkStake pool blocks
     * List of networkStake pools blocks.
     *
     * @param poolId Bech32 or hexadecimal pool ID. (required)
     * @param count  The number of results displayed on one page. (optional, default to 100)
     * @param page   The page number for listing the results. (optional, default to 1)
     * @param order  The ordering of items from the point of view of the blockchain, not the page listing itself. By default, we return oldest first, newest last.  (optional, default to asc)
     * @return Call&lt;List&lt;String&gt;&gt;
     */
    @GET("pools/{pool_id}/blocks")
    Call<List<String>> poolsPoolIdBlocksGet(
            @Header("project_id") String projectId,
            @Path("pool_id") String poolId,
            @Query("count") Integer count,
            @Query("page") Integer page,
            @Query("order") String order
    );

    /**
     * NetworkStake pool delegators
     * List of current networkStake pools delegators.
     *
     * @param poolId Bech32 or hexadecimal pool ID. (required)
     * @param count  The number of results displayed on one page. (optional, default to 100)
     * @param page   The page number for listing the results. (optional, default to 1)
     * @param order  The ordering of items from the point of view of the blockchain, not the page listing itself. By default, we return oldest first, newest last.  (optional, default to asc)
     * @return Call&lt;List&lt;Object&gt;&gt;
     */
    @GET("pools/{pool_id}/delegators")
    Call<List<PoolDelegator>> poolsPoolIdDelegatorsGet(
            @Header("project_id") String projectId,
            @Path("pool_id") String poolId,
            @Query("count") Integer count,
            @Query("page") Integer page,
            @Query("order") String order
    );

    /**
     * Specific networkStake pool
     * Pool information.
     *
     * @param poolId Bech32 or hexadecimal pool ID. (required)
     * @return Call&lt;Pool&gt;
     */
    @GET("pools/{pool_id}")
    Call<Pool> poolsPoolIdGet(
            @Header("project_id") String projectId,
            @Path("pool_id") String poolId
    );

    /**
     * NetworkStake pool history
     * History of networkStake pool parameters over epochs.
     *
     * @param poolId Bech32 or hexadecimal pool ID. (required)
     * @param count  The number of results displayed on one page. (optional, default to 100)
     * @param page   The page number for listing the results (optional, default to 1)
     * @param order  The ordering of items from the point of view of the blockchain, not the page listing itself. By default, we return oldest first, newest last.  (optional, default to asc)
     * @return Call&lt;List&lt;PoolHistory&gt;&gt;
     */
    @GET("pools/{pool_id}/history")
    Call<List<PoolHistory>> poolsPoolIdHistoryGet(
            @Header("project_id") String projectId,
            @Path("pool_id") String poolId,
            @Query("count") Integer count,
            @Query("page") Integer page,
            @Query("order") String order
    );

    /**
     * NetworkStake pool metadata
     * NetworkStake pool registration metadata.
     *
     * @param poolId Bech32 or hexadecimal pool ID. (required)
     * @return Call&lt;PoolMetadata&gt;
     */
    @GET("pools/{pool_id}/metadata")
    Call<PoolMetadata> poolsPoolIdMetadataGet(
            @Header("project_id") String projectId,
            @Path("pool_id") String poolId
    );

    /**
     * NetworkStake pool relays
     * Relays of a networkStake pool.
     *
     * @param poolId Bech32 or hexadecimal pool ID. (required)
     * @return Call&lt;List&lt;Object&gt;&gt;
     */
    @GET("pools/{pool_id}/relays")
    Call<List<PoolRelay>> poolsPoolIdRelaysGet(
            @Header("project_id") String projectId,
            @Path("pool_id") String poolId
    );

    /**
     * NetworkStake pool updates
     * List of certificate updates to the networkStake pool.
     *
     * @param poolId Bech32 or hexadecimal pool ID. (required)
     * @param count  The number of results displayed on one page. (optional, default to 100)
     * @param page   The page number for listing the results. (optional, default to 1)
     * @param order  The ordering of items from the point of view of the blockchain, not the page listing itself. By default, we return oldest first, newest last.  (optional, default to asc)
     * @return Call&lt;List&lt;Object&gt;&gt;
     */
    @GET("pools/{pool_id}/updates")
    Call<List<PoolUpdate>> poolsPoolIdUpdatesGet(
            @Header("project_id") String projectId,
            @Path("pool_id") String poolId,
            @Query("count") Integer count,
            @Query("page") Integer page,
            @Query("order") String order
    );

    /**
     * List of retired networkStake pools
     * List of already retired pools.
     *
     * @param count The numbers of pools per page. (optional, default to 100)
     * @param page  The page number for listing the results. (optional, default to 1)
     * @param order The ordering of items from the point of view of the blockchain, not the page listing itself. By default, we return oldest first, newest last.  (optional, default to asc)
     * @return Call&lt;List&lt;Object&gt;&gt;
     */
    @GET("pools/retired")
    Call<List<PoolRetirementInfo>> poolsRetiredGet(
            @Header("project_id") String projectId,
            @Query("count") Integer count,
            @Query("page") Integer page,
            @Query("order") String order
    );

    /**
     * List of retiring networkStake pools
     * List of networkStake pools retiring in the upcoming epochs
     *
     * @param count The number of results displayed on one page. (optional, default to 100)
     * @param page  The page number for listing the results. (optional, default to 1)
     * @param order The ordering of items from the point of view of the blockchain, not the page listing itself. By default, we return oldest first, newest last.  (optional, default to asc)
     * @return Call&lt;List&lt;Object&gt;&gt;
     */
    @GET("pools/retiring")
    Call<List<PoolRetirementInfo>> poolsRetiringGet(
            @Header("project_id") String projectId,
            @Query("count") Integer count,
            @Query("page") Integer page,
            @Query("order") String order
    );

}
