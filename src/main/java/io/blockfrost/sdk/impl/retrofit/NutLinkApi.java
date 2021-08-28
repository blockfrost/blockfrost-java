package io.blockfrost.sdk.impl.retrofit;

import io.blockfrost.sdk.api.model.nutlink.NutLinkAddress;
import io.blockfrost.sdk.api.model.nutlink.Ticker;
import io.blockfrost.sdk.api.model.nutlink.TickerRecord;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;
import retrofit2.http.Query;

import java.util.List;

public interface NutLinkApi {

    /**
     *
     * List metadata about specific address
     * @param address  (required)
     * @return Call&lt;{@link NutLinkAddress}&gt;
     */
    @GET("nutlink/{address}")
    Call<NutLinkAddress> addressGet(
            @Header("project_id") String projectId,
            @Path("address") String address
    );

    /**
     *
     * List tickers for a specific metadata oracle
     * @param address  (required)
     * @param count The number of results displayed on one page. (optional, default to 100)
     * @param page The page number for listing the results. (optional, default to 1)
     * @param order The ordering of items from the point of view of the blockchain, not the page listing itself. By default, we return oldest first, newest last.  (optional, default to asc)
     * @return Call&lt;List&lt;Object&gt;&gt;
     */
    @GET("nutlink/{address}/tickers")
    Call<List<Ticker>> addressTickersGet(
            @Header("project_id") String projectId,
            @Path("address") String address, @Query("count") Integer count, @Query("page") Integer page,
            @Query("order") String order
    );


    /**
     *
     * List of records of a specific ticker
     * @param address  (required)
     * @param ticker  (required)
     * @param count The number of results displayed on one page. (optional, default to 100)
     * @param page The page number for listing the results. (optional, default to 1)
     * @param order The ordering of items from the point of view of the blockchain, not the page listing itself. By default, we return oldest first, newest last.  (optional, default to asc)
     * @return Call&lt;List&lt;Object&gt;&gt;
     */
    @GET("nutlink/{address}/tickers/{ticker}")
    Call<List<TickerRecord>> addressTickerRecords(
            @Header("project_id") String projectId,
            @Path("address") String address, @Path("ticker") String ticker, @Query("count") Integer count,
            @Query("page") Integer page, @Query("order") String order
    );

    /**
     *
     * List of records of a specific ticker
     * @param ticker  (required)
     * @param count The number of results displayed on one page. (optional, default to 100)
     * @param page The page number for listing the results. (optional, default to 1)
     * @param order The ordering of items from the point of view of the blockchain, not the page listing itself. By default, we return oldest first, newest last.  (optional, default to asc)
     * @return Call&lt;List&lt;Object&gt;&gt;
     */
    @GET("nutlink/tickers/{ticker}")
    Call<List<TickerRecord>> tickerRecords(
            @Header("project_id") String projectId,
            @Path("ticker") String ticker, @Query("count") Integer count, @Query("page") Integer page,
            @Query("order") String order
    );
}
