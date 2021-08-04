package io.blockfrost.sdk.impl.retrofit;

import io.blockfrost.sdk.api.model.Epoch;
import io.blockfrost.sdk.api.model.EpochParam;
import io.blockfrost.sdk.api.model.Stake;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;
import retrofit2.http.Query;

import java.util.List;

public interface EpochsApi {
    /**
     * Latest epoch
     * Return the information about the latest, therefore current, epoch.
     *
     * @return Call&lt;Epoch&gt;
     */
    @GET("epochs/latest")
    Call<Epoch> epochsLatestGet(
            @Header("project_id") String projectId
    );


    /**
     * Latest epoch protocol parameters
     * Return the protocol parameters for the latest epoch.
     *
     * @return Call&lt;EpochParam&gt;
     */
    @GET("epochs/latest/parameters")
    Call<EpochParam> epochsLatestParametersGet(
            @Header("project_id") String projectId
    );


    /**
     * Block distribution
     * Return the blocks minted for the epoch specified.
     *
     * @param number Number of the epoch (required)
     * @param count  The number of results displayed on one page. (optional, default to 100)
     * @param page   The page number for listing the results. (optional, default to 1)
     * @param order  The ordering of items from the point of view of the blockchain, not the page listing itself. By default, we return oldest first, newest last.  (optional, default to asc)
     * @return Call&lt;List&lt;String&gt;&gt;
     */
    @GET("epochs/{number}/blocks")
    Call<List<String>> epochsNumberBlocksGet(
            @Header("project_id") String projectId,
            @Path("number") Integer number,
            @Query("count") Integer count,
            @Query("page") Integer page,
            @Query("order") String order
    );

    /**
     * Block distribution
     * Return the block minted for the epoch specified by networkStake pool.
     *
     * @param number Number of the epoch (required)
     * @param poolId NetworkStake pool ID to filter (required)
     * @param count  The number of results displayed on one page. (optional, default to 100)
     * @param page   The page number for listing the results. (optional, default to 1)
     * @param order  The ordering of items from the point of view of the blockchain, not the page listing itself. By default, we return oldest first, newest last.  (optional, default to asc)
     * @return Call&lt;List&lt;String&gt;&gt;
     */
    @GET("epochs/{number}/blocks/{pool_id}")
    Call<List<String>> epochsNumberBlocksPoolIdGet(
            @Header("project_id") String projectId,
            @Path("number") Integer number,
            @Path("pool_id") String poolId,
            @Query("count") Integer count,
            @Query("page") Integer page,
            @Query("order") String order
    );

    /**
     * Specific epoch
     * Return the content of the requested epoch.
     *
     * @param number Number of the epoch (required)
     * @return Call&lt;Epoch&gt;
     */
    @GET("epochs/{number}")
    Call<Epoch> epochsNumberGet(
            @Header("project_id") String projectId,
            @Path("number") Integer number
    );

    /**
     * Listing of next epochs
     * Return the list of epochs following a specific epoch.
     *
     * @param number Number of the requested epoch. (required)
     * @param count  The number of results displayed on one page. (optional, default to 100)
     * @param page   The page number for listing the results. (optional, default to 1)
     * @return Call&lt;List&lt;Epoch&gt;&gt;
     */
    @GET("epochs/{number}/next")
    Call<List<Epoch>> epochsNumberNextGet(
            @Header("project_id") String projectId,
            @Path("number") Integer number,
            @Query("count") Integer count,
            @Query("page") Integer page
    );

    /**
     * Protocol parameters
     * Return the protocol parameters for the epoch specified.
     *
     * @param number Number of the epoch (required)
     * @return Call&lt;EpochParam&gt;
     */
    @GET("epochs/{number}/parameters")
    Call<EpochParam> epochsNumberParametersGet(
            @Header("project_id") String projectId,
            @Path("number") Integer number
    );

    /**
     * Listing of previous epochs
     * Return the list of epochs preceding a specific epoch.
     *
     * @param number Number of the epoch (required)
     * @param count  The number of results displayed on one page. (optional, default to 100)
     * @param page   The page number for listing the results (optional, default to 1)
     * @return Call&lt;List&lt;Epoch&gt;&gt;
     */
    @GET("epochs/{number}/previous")
    Call<List<Epoch>> epochsNumberPreviousGet(
            @Header("project_id") String projectId,
            @Path("number") Integer number,
            @Query("count") Integer count,
            @Query("page") Integer page
    );

    /**
     * NetworkStake distribution
     * Return the active networkStake distribution for the epoch specified.
     *
     * @param number Number of the epoch (required)
     * @param count  The number of results displayed on one page. (optional, default to 100)
     * @param page   The page number for listing the results. (optional, default to 1)
     * @return Call&lt;List&lt;Object&gt;&gt;
     */
    @GET("epochs/{number}/stakes")
    Call<List<Stake>> epochsNumberStakesGet(
            @Header("project_id") String projectId,
            @Path("number") Integer number,
            @Query("count") Integer count,
            @Query("page") Integer page
    );

    /**
     * NetworkStake distribution by pool
     * Return the active networkStake distribution for the epoch specified by networkStake pool.
     *
     * @param number Number of the epoch (required)
     * @param poolId NetworkStake pool ID to filter (required)
     * @param count  The number of results displayed on one page. (optional, default to 100)
     * @param page   The page number for listing the results. (optional, default to 1)
     * @return Call&lt;List&lt;Object&gt;&gt;
     */
    @GET("epochs/{number}/stakes/{pool_id}")
    Call<List<Stake>> epochsNumberStakesPoolIdGet(
            @Header("project_id") String projectId,
            @Path("number") Integer number,
            @Path("pool_id") String poolId,
            @Query("count") Integer count,
            @Query("page") Integer page
    );

}
