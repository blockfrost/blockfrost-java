package io.blockfrost.sdk.impl;

import io.blockfrost.sdk.api.Transactions;
import io.blockfrost.sdk.api.exception.APIException;
import io.blockfrost.sdk.impl.model.Transaction;
import io.blockfrost.sdk.impl.model.TransactionStake;
import io.blockfrost.sdk.impl.model.TransactionUtxo;
import io.blockfrost.sdk.impl.retrofit.TransactionsApi;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;
import java.util.List;

public class TransactionsImpl extends BaseImpl implements Transactions {

    TransactionsApi transactionsApi;

    public TransactionsImpl(String baseUrl, String projectId){
        super(baseUrl, projectId);
        transactionsApi = getRetrofit().create(TransactionsApi.class);
    }

    @Override
    public Transaction getTransaction(String transactionHash) throws APIException {
        Call<Transaction> transactionCall = transactionsApi.txsHashGet(getProjectId(), transactionHash);

        try{
            Response<Transaction> transactionResponse = transactionCall.execute();
            return processResponse(transactionResponse);
        } catch (IOException exp){
            throw new APIException("Exception while fetching transaction for hash " + transactionHash , exp);
        }
    }

    @Override
    public TransactionUtxo getTransactionUtxo(String transactionHash) throws APIException {
        Call<TransactionUtxo> transactionUtxoCall = transactionsApi.txsHashUtxosGet(getProjectId(), transactionHash);

        try{
            Response<TransactionUtxo> transactionUtxoResponse = transactionUtxoCall.execute();
            return processResponse(transactionUtxoResponse);
        } catch (IOException exp){
            throw new APIException("Exception while fetching transaction utxos for hash " + transactionHash , exp);
        }
    }

    @Override
    public List<TransactionStake> getTransactionStakes(String transactionHash) throws APIException {
        Call<List<TransactionStake>> transactionStakesCall = transactionsApi.txsHashStakesGet(getProjectId(), transactionHash);

        try{
            Response<List<TransactionStake>> transactionStakesResponse = transactionStakesCall.execute();
            return processResponse(transactionStakesResponse);
        } catch (IOException exp){
            throw new APIException("Exception while fetching transaction stakes for hash " + transactionHash , exp);
        }
    }
}
