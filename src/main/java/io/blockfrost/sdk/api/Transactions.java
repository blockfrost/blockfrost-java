package io.blockfrost.sdk.api;

import io.blockfrost.sdk.api.exception.APIException;
import io.blockfrost.sdk.impl.model.*;

import java.util.List;

public interface Transactions {

    Transaction getTransaction(String transactionHash) throws APIException;

    TransactionUtxo getTransactionUtxo(String transactionHash) throws APIException;

    List<TransactionStake> getTransactionStakes(String transactionHash) throws APIException;

    List<TransactionDelegation> getTransactionDelegations(String transactionHash) throws APIException;

    List<TransactionWithdrawal> getTransactionWithdrawals(String transactionHash) throws APIException;
    
    List<TransactionMir> getTransactionMirs(String transactionHash) throws APIException;
}

