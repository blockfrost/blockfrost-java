package io.blockfrost.sdk.impl.retrofit;

import io.blockfrost.sdk.api.model.Asset;
import io.blockfrost.sdk.api.model.AssetAddress;
import io.blockfrost.sdk.api.model.AssetHistory;
import io.blockfrost.sdk.api.model.AssetTransaction;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;
import retrofit2.http.Query;

import java.util.List;

public interface AssetsApi {
    /**
     * Asset addresses
     * List of a addresses containing a specific asset
     *
     * @param asset Concatenation of the policy_id and hex-encoded asset_name (required)
     * @param count The number of results displayed on one page. (optional, default to 100)
     * @param page  The page number for listing the results. (optional, default to 1)
     * @param order The ordering of items from the point of view of the blockchain, not the page listing itself. By default, we return oldest first, newest last.  (optional, default to asc)
     * @return Call&lt;List&lt;Object&gt;&gt;
     */
    @GET("assets/{asset}/addresses")
    Call<List<AssetAddress>> assetsAssetAddressesGet(
            @Header("project_id") String projectId,
            @Path("asset") String asset,
            @Query("count") Integer count,
            @Query("page") Integer page,
            @Query("order") String order
    );

    /**
     * Specific asset
     * Information about a specific asset
     *
     * @param asset Concatenation of the policy_id and hex-encoded asset_name (required)
     * @return Call&lt;Asset&gt;
     */
    @GET("assets/{asset}")
    Call<Asset> assetsAssetGet(
            @Header("project_id") String projectId,
            @Path("asset") String asset
    );

    /**
     * Asset history
     * History of a specific asset
     *
     * @param asset Concatenation of the policy_id and hex-encoded asset_name (required)
     * @param count The number of results displayed on one page. (optional, default to 100)
     * @param page  The page number for listing the results. (optional, default to 1)
     * @param order The ordering of items from the point of view of the blockchain, not the page listing itself. By default, we return oldest first, newest last.  (optional, default to asc)
     * @return Call&lt;List&lt;Object&gt;&gt;
     */
    @GET("assets/{asset}/history")
    Call<List<AssetHistory>> assetsAssetHistoryGet(
            @Header("project_id") String projectId,
            @Path("asset") String asset,
            @Query("count") Integer count,
            @Query("page") Integer page,
            @Query("order") String order
    );

    /**
     * Asset transactions
     * List of a specific asset transactions
     *
     * @param asset Concatenation of the policy_id and hex-encoded asset_name (required)
     * @param count The number of results displayed on one page. (optional, default to 100)
     * @param page  The page number for listing the results. (optional, default to 1)
     * @param order The ordering of items from the point of view of the blockchain, not the page listing itself. By default, we return oldest first, newest last.  (optional, default to asc)
     * @return Call&lt;List&lt;Object&gt;&gt;
     */
    @GET("assets/{asset}/transactions")
    Call<List<AssetTransaction>> assetsAssetTransactionsGet(
            @Header("project_id") String projectId,
            @Path("asset") String asset,
            @Query("count") Integer count,
            @Query("page") Integer page,
            @Query("order") String order
    );


    /**
     * Assets
     * List of assets.
     *
     * @param count The number of results displayed on one page. (optional, default to 100)
     * @param page  The page number for listing the results. (optional, default to 1)
     * @param order The ordering of items from the point of view of the blockchain, not the page listing itself. By default, we return oldest first, newest last.  (optional, default to asc)
     * @return Call&lt;List&lt;Object&gt;&gt;
     */
    @GET("assets")
    Call<List<Asset>> assetsGet(
            @Header("project_id") String projectId,
            @Query("count") Integer count,
            @Query("page") Integer page,
            @Query("order") String order
    );

    /**
     * Assets of a specific policy
     * List of asset minted under a specific policy
     *
     * @param policyId Specific policy_id (required)
     * @param count    The number of results displayed on one page. (optional, default to 100)
     * @param page     The page number for listing the results. (optional, default to 1)
     * @param order    The ordering of items from the point of view of the blockchain, not the page listing itself. By default, we return oldest first, newest last.  (optional, default to asc)
     * @return Call&lt;List&lt;Object&gt;&gt;
     */
    @GET("assets/policy/{policy_id}")
    Call<List<Object>> assetsPolicyPolicyIdGet(
            @Header("project_id") String projectId,
            @Path("policy_id") String policyId,
            @Query("count") Integer count,
            @Query("page") Integer page,
            @Query("order") String order
    );

}
