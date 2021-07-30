package io.blockfrost.sdk.impl.retrofit;

import io.blockfrost.sdk.api.model.TransactionMetadataLabel;
import io.blockfrost.sdk.api.model.TransactionMetadataLabelCbor;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;
import retrofit2.http.Query;

import java.util.List;

public interface MetadataApi {
  /**
   * Transaction metadata labels
   * List of all used transaction metadata labels. 
   * @param count The number of results displayed on one page. (optional, default to 100)
   * @param page The page number for listing the results. (optional, default to 1)
   * @param order The ordering of items from the point of view of the blockchain, not the page listing itself. By default, we return oldest first, newest last.  (optional, default to asc)
   * @return Call&lt;List&lt;Object&gt;&gt;
   */
  @GET("metadata/txs/labels")
  Call<List<TransactionMetadataLabel>> metadataTxsLabelsGet(
          @Header("project_id") String projectId,
          @Query("count") Integer count,
          @Query("page") Integer page,
          @Query("order") String order
  );

  /**
   * Transaction metadata content in CBOR
   * Transaction metadata per label.
   * @param label Metadata label (required)
   * @param count The number of results displayed on one page. (optional, default to 100)
   * @param page The page number for listing the results. (optional, default to 1)
   * @param order The ordering of items from the point of view of the blockchain, not the page listing itself. By default, we return oldest first, newest last.  (optional, default to asc)
   * @return Call&lt;List&lt;Object&gt;&gt;
   */
  @GET("metadata/txs/labels/{label}/cbor")
  Call<List<TransactionMetadataLabelCbor>> metadataTxsLabelsLabelCborGet(
          @Header("project_id") String projectId,
          @Path("label") String label,
          @Query("count") Integer count,
          @Query("page") Integer page,
          @Query("order") String order
  );

  /**
   * Transaction metadata content in JSON
   * Transaction metadata per label.
   * @param label Metadata label (required)
   * @param count The number of results displayed on one page. (optional, default to 100)
   * @param page The page number for listing the results. (optional, default to 1)
   * @param order The ordering of items from the point of view of the blockchain, not the page listing itself. By default, we return oldest first, newest last.  (optional, default to asc)
   * @return Call&lt;List&lt;Object&gt;&gt;
   */
  @GET("metadata/txs/labels/{label}")
  Call<List<Object>> metadataTxsLabelsLabelGet(
          @Header("project_id") String projectId,
          @Path("label") String label,
          @Query("count") Integer count,
          @Query("page") Integer page,
          @Query("order") String order
  );

}
