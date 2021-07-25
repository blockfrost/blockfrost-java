package io.blockfrost.sdk.api;

import io.blockfrost.sdk.api.exception.APIException;
import io.blockfrost.sdk.api.model.*;

import java.util.List;

public interface TransactionService {

    /**
     * Specific transaction
     * Return content of the requested transaction.
     * @param hash Hash of the requested transaction (required)
     * @return Transaction;
     */
    Transaction getTransaction(String hash) throws APIException;

    /**
     * Transaction UTXOs
     * Return the inputs and UTXOs of the specific transaction.
     * @param hash Hash of the requested transaction (required)
     * @return TransactionUtxo;
     */
    TransactionUtxo getTransactionUtxo(String hash) throws APIException;

    /**
     * Transaction stake addresses certificates
     * Obtain information about (de)registration of stake addresses within a transaction.
     * @param hash Hash of the requested transaction. (required)
     * @return List&lt;TransactionStake&gt;
     */
    List<TransactionStake> getTransactionStakes(String hash) throws APIException;

    /**
     * Transaction delegation certificates
     * Obtain information about delegation certificates of a specific transaction.
     * @param hash Hash of the requested transaction. (required)
     * @return List&lt;TransactionDelegation&gt;
     */
    List<TransactionDelegation> getTransactionDelegations(String hash) throws APIException;

    /**
     * Transaction withdrawal
     * Obtain information about withdrawals of a specific transaction.
     * @param hash Hash of the requested transaction. (required)
     * @return List&lt;TransactionWithdrawal&gt;
     */
    List<TransactionWithdrawal> getTransactionWithdrawals(String hash) throws APIException;

    /**
     * Transaction MIRs
     * Obtain information about Move Instantaneous Rewards (MIRs) of a specific transaction.
     * @param hash Hash of the requested transaction. (required)
     * @return List&lt;TransactionMir&gt;
     */
    List<TransactionMir> getTransactionMirs(String hash) throws APIException;

    /**
     * Transaction stake pool registration and update certificates
     * Obtain information about stake pool registration and update certificates of a specific transaction.
     * @param hash Hash of the requested transaction (required)
     * @return List&lt;TransactionPoolUpdate&gt;
     */
    List<TransactionPoolUpdate> getTransactionPoolUpdates(String hash) throws APIException;

    /**
     * Transaction stake pool retirement certificates
     * Obtain information about stake pool retirements within a specific transaction.
     * @param hash Hash of the requested transaction (required)
     * @return List&lt;TransactionPoolRetire&gt;
     */
    List<TransactionPoolRetire> getTransactionPoolRetires(String hash) throws APIException;

    /**
     * Transaction metadata
     * Obtain the transaction metadata.
     * @param hash Hash of the requested transaction (required)
     * @return List&lt;TransactionMetadata&gt;
     */
    List<TransactionMetadata> getTransactionMetadata(String hash) throws APIException;

    /**
     * Transaction metadata in CBOR
     * Obtain the transaction metadata in CBOR.
     * @param hash Hash of the requested transaction (required)
     * @return List&lt;TransactionMetadataCbor&gt;
     */
    List<TransactionMetadataCbor> getTransactionMetadataCbor(String hash) throws APIException;
}

