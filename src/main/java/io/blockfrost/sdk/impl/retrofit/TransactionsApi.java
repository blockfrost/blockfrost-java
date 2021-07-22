package io.blockfrost.sdk.impl.retrofit;

import io.blockfrost.sdk.impl.model.*;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

import java.util.List;

public interface TransactionsApi {
  /**
   * Submit a transaction
   * Submit an already serialized transaction to the network.
   * @param contentType  (required)
   * @return Call&lt;String&gt;
   */
  @POST("tx/submit")
  Call<String> txSubmitPost(
    @Header("Content-Type") String contentType
  );

  /**
   * Transaction delegation certificates
   * Obtain information about delegation certificates of a specific transaction. 
   * @param hash Hash of the requested transaction. (required)
   * @return Call&lt;List&lt;Object&gt;&gt;
   */
  @GET("txs/{hash}/delegations")
  Call<List<TransactionDelegation>> txsHashDelegationsGet(
    @Header("project_id") String projectId,
    @Path("hash") String hash
  );

  /**
   * Specific transaction
   * Return content of the requested transaction.
   * @param hash Hash of the requested transaction (required)
   * @return Call&lt;TxContent&gt;
   */
  @GET("txs/{hash}")
  Call<Transaction> txsHashGet(
    @Header("project_id") String projectId,
    @Path("hash") String hash
  );

  /**
   * Transaction metadata in CBOR
   * Obtain the transaction metadata in CBOR.
   * @param hash Hash of the requested transaction (required)
   * @return Call&lt;List&lt;Object&gt;&gt;
   */
  @GET("txs/{hash}/metadata/cbor")
  Call<List<Object>> txsHashMetadataCborGet(
    @Path("hash") String hash
  );

  /**
   * Transaction metadata
   * Obtain the transaction metadata.
   * @param hash Hash of the requested transaction (required)
   * @return Call&lt;List&lt;Object&gt;&gt;
   */
  @GET("txs/{hash}/metadata")
  Call<List<Object>> txsHashMetadataGet(
    @Path("hash") String hash
  );

  /**
   * Transaction MIRs
   * Obtain information about Move Instantaneous Rewards (MIRs) of a specific transaction.
   * @param hash Hash of the requested transaction. (required)
   * @return Call&lt;List&lt;Object&gt;&gt;
   */
  @GET("txs/{hash}/mirs")
  Call<List<TransactionMir>> txsHashMirsGet(
    @Header("project_id") String projectId,
    @Path("hash") String hash
  );

  /**
   * Transaction stake pool retirement certificates
   * Obtain information about stake pool retirements within a specific transaction. 
   * @param hash Hash of the requested transaction (required)
   * @return Call&lt;List&lt;Object&gt;&gt;
   */
  @GET("txs/{hash}/pool_retires")
  Call<List<Object>> txsHashPoolRetiresGet(
    @Path("hash") String hash
  );

  /**
   * Transaction stake pool registration and update certificates
   * Obtain information about stake pool registration and update certificates of a specific transaction. 
   * @param hash Hash of the requested transaction (required)
   * @return Call&lt;List&lt;Object&gt;&gt;
   */
  @GET("txs/{hash}/pool_updates")
  Call<List<Object>> txsHashPoolUpdatesGet(
    @Path("hash") String hash
  );

  /**
   * Transaction stake addresses certificates
   * Obtain information about (de)registration of stake addresses within a transaction. 
   * @param hash Hash of the requested transaction. (required)
   * @return Call&lt;List&lt;Object&gt;&gt;
   */
  @GET("txs/{hash}/stakes")
  Call<List<TransactionStake>> txsHashStakesGet(
    @Header("project_id") String projectId,
    @Path("hash") String hash
  );

  /**
   * Transaction UTXOs
   * Return the inputs and UTXOs of the specific transaction.
   * @param hash Hash of the requested transaction (required)
   * @return Call&lt;TxContentUtxo&gt;
   */
  @GET("txs/{hash}/utxos")
  Call<TransactionUtxo> txsHashUtxosGet(
    @Header("project_id") String projectId,
    @Path("hash") String hash
  );

  /**
   * Transaction withdrawal
   * Obtain information about withdrawals of a specific transaction.
   * @param hash Hash of the requested transaction. (required)
   * @return Call&lt;List&lt;Object&gt;&gt;
   */
  @GET("txs/{hash}/withdrawals")
  Call<List<TransactionWithdrawal>> txsHashWithdrawalsGet(
    @Header("project_id") String projectId,
    @Path("hash") String hash
  );

}
