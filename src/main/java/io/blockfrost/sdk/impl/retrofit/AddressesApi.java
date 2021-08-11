package io.blockfrost.sdk.impl.retrofit;

import io.blockfrost.sdk.api.model.Address;
import io.blockfrost.sdk.api.model.AddressTotal;
import io.blockfrost.sdk.api.model.AddressUtxo;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;
import retrofit2.http.Query;

import java.util.List;

public interface AddressesApi {
    /**
     * Specific address
     * Obtain information about a specific address.
     *
     * @param address Bech32 address. (required)
     * @return Call&lt;Address&gt;
     */
    @GET("addresses/{address}")
    Call<Address> addressesAddressGet(
            @Header("project_id") String projectId,
            @Path("address") String address
    );

    /**
     * Address details
     * Obtain details about an address.
     *
     * @param address Bech32 address. (required)
     * @return Call&lt;AddressTotal&gt;
     */
    @GET("addresses/{address}/total")
    Call<AddressTotal> addressesAddressTotalGet(
            @Header("project_id") String projectId,
            @Path("address") String address
    );

    /**
     * Address transactions
     * Transactions on the address.
     *
     * @param address Bech32 address. (required)
     * @param count   The numbers of pools per page. (optional, default to 100)
     * @param page    The page number for listing the results. (optional, default to 1)
     * @param order   The ordering of items from the point of view of the blockchain, not the page listing itself. By default, we return oldest first, newest last.  (optional, default to asc)
     * @param from    The block number and optionally also index from which (inclusive) to start search for results, concatenated using colon. Has to be lower than or equal to &#x60;to&#x60; parameter.  (optional)
     * @param to      The block number and optionally also index where (inclusive) to end the search for results, concatenated using colon. Has to be higher than or equal to &#x60;from&#x60; parameter.  (optional)
     * @return Call&lt;List&lt;Object&gt;&gt;
     */
    @GET("addresses/{address}/transactions")
    Call<List<Object>> addressesAddressTransactionsGet(
            @Header("project_id") String projectId,
            @Path("address") String address,
            @Query("count") Integer count,
            @Query("page") Integer page,
            @Query("order") String order,
            @Query("from") String from,
            @Query("to") String to
    );

    /**
     * Address UTXOs
     * UTXOs of the address.
     *
     * @param address Bech32 address. (required)
     * @param count   The number of results displayed on one page. (optional, default to 100)
     * @param page    The page number for listing the results. (optional, default to 1)
     * @param order   Ordered by tx index in the block. The ordering of items from the point of view of the blockchain, not the page listing itself. By default, we return oldest first, newest last.  (optional, default to asc)
     * @return Call&lt;List&lt;Object&gt;&gt;
     */
    @GET("addresses/{address}/utxos")
    Call<List<AddressUtxo>> addressesAddressUtxosGet(
            @Header("project_id") String projectId,
            @Path("address") String address,
            @Query("count") Integer count,
            @Query("page") Integer page,
            @Query("order") String order
    );

}
