package io.blockfrost.sdk.api;

import io.blockfrost.sdk.api.exception.APIException;
import io.blockfrost.sdk.impl.model.Transaction;
import io.blockfrost.sdk.impl.model.TransactionUtxo;

public interface Transactions {

    Transaction getTransaction(String transactionHash) throws APIException;

    TransactionUtxo getTransactionUtxo(String transactionHash) throws APIException;
}
