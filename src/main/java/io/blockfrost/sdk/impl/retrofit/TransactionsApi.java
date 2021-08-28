package io.blockfrost.sdk.impl.retrofit;

import io.blockfrost.sdk.api.model.*;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.List;

public interface TransactionsApi {
    /**
     * Submit a transaction
     * Submit an already serialized transaction to the network.
     *
     * @return Call&lt;String&gt;
     */
    @POST("tx/submit")
    Call<String> txSubmitPost(
            @Header("project_id") String projectId,
            @Body RequestBody signedTxn
    );

    /**
     * Transaction delegation certificates
     * Obtain information about delegation certificates of a specific transaction.
     *
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
     *
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
     *
     * @param hash Hash of the requested transaction (required)
     * @return Call&lt;List&lt;Object&gt;&gt;
     */
    @GET("txs/{hash}/metadata/cbor")
    Call<List<TransactionMetadataCbor>> txsHashMetadataCborGet(
            @Header("project_id") String projectId,
            @Path("hash") String hash
    );

    /**
     * Transaction metadata
     * Obtain the transaction metadata.
     *
     * @param hash Hash of the requested transaction (required)
     * @return Call&lt;List&lt;Object&gt;&gt;
     */
    @GET("txs/{hash}/metadata")
    Call<List<TransactionMetadataJson>> txsHashMetadataGet(
            @Header("project_id") String projectId,
            @Path("hash") String hash
    );

    /**
     * Transaction MIRs
     * Obtain information about Move Instantaneous Rewards (MIRs) of a specific transaction.
     *
     * @param hash Hash of the requested transaction. (required)
     * @return Call&lt;List&lt;Object&gt;&gt;
     */
    @GET("txs/{hash}/mirs")
    Call<List<TransactionMir>> txsHashMirsGet(
            @Header("project_id") String projectId,
            @Path("hash") String hash
    );

    /**
     * Transaction networkStake pool retirement certificates
     * Obtain information about networkStake pool retirements within a specific transaction.
     *
     * @param hash Hash of the requested transaction (required)
     * @return Call&lt;List&lt;Object&gt;&gt;
     */
    @GET("txs/{hash}/pool_retires")
    Call<List<TransactionPoolRetire>> txsHashPoolRetiresGet(
            @Header("project_id") String projectId,
            @Path("hash") String hash
    );

    /**
     * Transaction networkStake pool registration and update certificates
     * Obtain information about networkStake pool registration and update certificates of a specific transaction.
     *
     * @param hash Hash of the requested transaction (required)
     * @return Call&lt;List&lt;Object&gt;&gt;
     */
    @GET("txs/{hash}/pool_updates")
    Call<List<TransactionPoolUpdate>> txsHashPoolUpdatesGet(
            @Header("project_id") String projectId,
            @Path("hash") String hash
    );

    /**
     * Transaction networkStake addresses certificates
     * Obtain information about (de)registration of networkStake addresses within a transaction.
     *
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
     *
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
     *
     * @param hash Hash of the requested transaction. (required)
     * @return Call&lt;List&lt;Object&gt;&gt;
     */
    @GET("txs/{hash}/withdrawals")
    Call<List<TransactionWithdrawal>> txsHashWithdrawalsGet(
            @Header("project_id") String projectId,
            @Path("hash") String hash
    );

}
