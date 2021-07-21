package io.blockfrost.sdk.impl;

import io.blockfrost.sdk.api.Transactions;
import io.blockfrost.sdk.api.exception.BlockfrostAPIException;
import io.blockfrost.sdk.impl.model.Transaction;
import io.blockfrost.sdk.impl.retrofit.TransactionsApi;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;

public class TransactionsImpl extends BaseImpl implements Transactions {

    TransactionsApi transactionsApi;

    public TransactionsImpl(String baseUrl, String projectId){
        super(baseUrl, projectId);
        transactionsApi = getRetrofit().create(TransactionsApi.class);
    }

    @Override
    public Transaction getTransaction(String transactionHash) throws BlockfrostAPIException {
        Call<Transaction> transactionCall = transactionsApi.txsHashGet(getProjectId(), transactionHash);

        try{
            Response<Transaction> transactionResponse = transactionCall.execute();
            return processResponse(transactionResponse);
        } catch (IOException exp){
            throw new BlockfrostAPIException("Exception while fetching transaction for hash " + transactionHash , exp);
        }
    }
}
