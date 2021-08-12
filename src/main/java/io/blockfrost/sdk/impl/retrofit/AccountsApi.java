package io.blockfrost.sdk.impl.retrofit;

import io.blockfrost.sdk.api.model.Account;
import io.blockfrost.sdk.api.model.AccountHistory;
import io.blockfrost.sdk.api.model.AccountRewardHistory;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;
import retrofit2.http.Query;

import java.util.List;

public interface AccountsApi {

    /**
     * Specific account address
     * Obtain information about a specific networkStake account.
     *
     * @param stakeAddress Bech32 networkStake address. (required)
     * @return Call&lt;Account&gt;
     */
    @GET("accounts/{stake_address}")
    Call<Account> accountsStakeAddressGet(@Header("project_id") String projectId, @Path("stake_address") String stakeAddress);

    /**
     * Assets associated with the account addresses
     * Obtain information about assets associated with addresses of a specific account.  &lt;b&gt;Be careful&lt;/b&gt;, as an account could be part of a mangled address and does not necessarily mean the addresses are owned by user as the account.
     *
     * @param stakeAddress Bech32 stake address. (required)
     * @param count        The number of results displayed on one page. (optional, default to 100)
     * @param page         The page number for listing the results. (optional, default to 1)
     * @param order        The ordering of items from the point of view of the blockchain, not the page listing itself. By default, we return oldest first, newest last.  (optional, default to asc)
     * @return Call&lt;List&lt;Object&gt;&gt;
     */
    @GET("accounts/{stake_address}/addresses/assets")
    Call<List<Object>> accountsStakeAddressAddressesAssetsGet(
            @Header("project_id") String projectId,
            @Path("stake_address") String stakeAddress,
            @Query("count") Integer count,
            @Query("page") Integer page,
            @Query("order") String order
    );

    /**
     * Account associated addresses
     * Obtain information about the addresses of a specific account.
     *
     * @param stakeAddress Bech32 stake address. (required)
     * @param count        The number of results displayed on one page. (optional, default to 100)
     * @param page         The page number for listing the results. (optional, default to 1)
     * @param order        The ordering of items from the point of view of the blockchain, not the page listing itself. By default, we return oldest first, newest last.  (optional, default to asc)
     * @return Call&lt;List&lt;Object&gt;&gt;
     */
    @GET("accounts/{stake_address}/addresses")
    Call<List<Object>> accountsStakeAddressAddressesGet(
            @Header("project_id") String projectId,
            @Path("stake_address") String stakeAddress,
            @Query("count") Integer count,
            @Query("page") Integer page,
            @Query("order") String order
    );

    /**
     * Account delegation history
     * Obtain information about the delegation of a specific account.
     *
     * @param stakeAddress Bech32 stake address. (required)
     * @param count        The number of results displayed on one page. (optional, default to 100)
     * @param page         The page number for listing the results. (optional, default to 1)
     * @param order        The ordering of items from the point of view of the blockchain, not the page listing itself. By default, we return oldest first, newest last.  (optional, default to asc)
     * @return Call&lt;List&lt;Object&gt;&gt;
     */
    @GET("accounts/{stake_address}/delegations")
    Call<List<Object>> accountsStakeAddressDelegationsGet(
            @Header("project_id") String projectId,
            @Path("stake_address") String stakeAddress,
            @Query("count") Integer count,
            @Query("page") Integer page,
            @Query("order") String order
    );

    /**
     * Account history
     * Obtain information about the history of a specific account.
     *
     * @param stakeAddress Bech32 stake address. (required)
     * @param count        The number of results displayed on one page. (optional, default to 100)
     * @param page         The page number for listing the results. (optional, default to 1)
     * @param order        The ordering of items from the point of view of the blockchain, not the page listing itself. By default, we return oldest first, newest last.  (optional, default to asc)
     * @return List&lt;AccountHistory&gt;
     */
    @GET("accounts/{stake_address}/history")
    Call<List<AccountHistory>> accountsStakeAddressHistoryGet(
            @Header("project_id") String projectId,
            @Path("stake_address") String stakeAddress,
            @Query("count") Integer count,
            @Query("page") Integer page,
            @Query("order") String order
    );

    /**
     * Account MIR history
     * Obtain information about the MIRs of a specific account.
     *
     * @param stakeAddress Bech32 stake address. (required)
     * @param count        The number of results displayed on one page. (optional, default to 100)
     * @param page         The page number for listing the results. (optional, default to 1)
     * @param order        The ordering of items from the point of view of the blockchain, not the page listing itself. By default, we return oldest first, newest last.  (optional, default to asc)
     * @return Call&lt;List&lt;Object&gt;&gt;
     */
    @GET("accounts/{stake_address}/mirs")
    Call<List<Object>> accountsStakeAddressMirsGet(
            @Header("project_id") String projectId,
            @Path("stake_address") String stakeAddress,
            @Query("count") Integer count,
            @Query("page") Integer page,
            @Query("order") String order
    );

    /**
     * Account registration history
     * Obtain information about the registrations and deregistrations of a specific account.
     *
     * @param stakeAddress Bech32 stake address. (required)
     * @param count        The number of results displayed on one page. (optional, default to 100)
     * @param page         The page number for listing the results. (optional, default to 1)
     * @param order        The ordering of items from the point of view of the blockchain, not the page listing itself. By default, we return oldest first, newest last.  (optional, default to asc)
     * @return Call&lt;List&lt;Object&gt;&gt;
     */
    @GET("accounts/{stake_address}/registrations")
    Call<List<Object>> accountsStakeAddressRegistrationsGet(
            @Header("project_id") String projectId,
            @Path("stake_address") String stakeAddress,
            @Query("count") Integer count,
            @Query("page") Integer page,
            @Query("order") String order
    );

    /**
     * Account reward history
     * Obtain information about the history of a specific account.
     *
     * @param stakeAddress Bech32 stake address. (required)
     * @param count        The number of results displayed on one page. (optional, default to 100)
     * @param page         The page number for listing the results. (optional, default to 1)
     * @param order        The ordering of items from the point of view of the blockchain, not the page listing itself. By default, we return oldest first, newest last.  (optional, default to asc)
     * @return Call&lt;List&lt;Object&gt;&gt;
     */
    @GET("accounts/{stake_address}/rewards")
    Call<List<AccountRewardHistory>> accountsStakeAddressRewardsGet(
            @Header("project_id") String projectId,
            @Path("stake_address") String stakeAddress,
            @Query("count") Integer count,
            @Query("page") Integer page,
            @Query("order") String order
    );

    /**
     * Account withdrawal history
     * Obtain information about the withdrawals of a specific account.
     *
     * @param stakeAddress Bech32 stake address. (required)
     * @param count        The number of results displayed on one page. (optional, default to 100)
     * @param page         The page number for listing the results. (optional, default to 1)
     * @param order        The ordering of items from the point of view of the blockchain, not the page listing itself. By default, we return oldest first, newest last.  (optional, default to asc)
     * @return Call&lt;List&lt;Object&gt;&gt;
     */
    @GET("accounts/{stake_address}/withdrawals")
    Call<List<Object>> accountsStakeAddressWithdrawalsGet(
            @Header("project_id") String projectId,
            @Path("stake_address") String stakeAddress,
            @Query("count") Integer count,
            @Query("page") Integer page,
            @Query("order") String order
    );

}
