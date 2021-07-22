package io.blockfrost.sdk.api;

import io.blockfrost.sdk.api.exception.APIException;
import io.blockfrost.sdk.impl.model.Transaction;
import io.blockfrost.sdk.impl.model.TransactionStake;
import io.blockfrost.sdk.impl.model.TransactionUtxo;

import java.util.List;

public interface Transactions {

    Transaction getTransaction(String transactionHash) throws APIException;

    TransactionUtxo getTransactionUtxo(String transactionHash) throws APIException;

    List<TransactionStake> getTransactionStakes(String transactionHash) throws APIException;
}

