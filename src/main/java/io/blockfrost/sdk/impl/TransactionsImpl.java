package io.blockfrost.sdk.impl;

import io.blockfrost.sdk.api.Transactions;
import io.blockfrost.sdk.api.exception.APIException;
import io.blockfrost.sdk.impl.model.*;
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

    @Override
    public List<TransactionDelegation> getTransactionDelegations(String transactionHash) throws APIException {
        Call<List<TransactionDelegation>> transactionDelegationCall = transactionsApi.txsHashDelegationsGet(getProjectId(), transactionHash);

        try{
            Response<List<TransactionDelegation>> transactionDelegationsResponse = transactionDelegationCall.execute();
            return processResponse(transactionDelegationsResponse);
        } catch (IOException exp){
            throw new APIException("Exception while fetching transaction delegations for hash " + transactionHash , exp);
        }
    }

    @Override
    public List<TransactionWithdrawal> getTransactionWithdrawals(String transactionHash) throws APIException {
        Call<List<TransactionWithdrawal>> transactionWithdrawalCall = transactionsApi.txsHashWithdrawalsGet(getProjectId(), transactionHash);

        try{
            Response<List<TransactionWithdrawal>> transactionWithdrawalsResponse = transactionWithdrawalCall.execute();
            return processResponse(transactionWithdrawalsResponse);
        } catch (IOException exp){
            throw new APIException("Exception while fetching transaction withdrawals for hash " + transactionHash , exp);
        }
    }

    @Override
    public List<TransactionMir> getTransactionMirs(String transactionHash) throws APIException {
        Call<List<TransactionMir>> transactionMirCall = transactionsApi.txsHashMirsGet(getProjectId(), transactionHash);

        try{
            Response<List<TransactionMir>> transactionMirsResponse = transactionMirCall.execute();
            return processResponse(transactionMirsResponse);
        } catch (IOException exp){
            throw new APIException("Exception while fetching transaction mirs for hash " + transactionHash , exp);
        }
    }

    @Override
    public List<TransactionPoolUpdate> getTransactionPoolUpdates(String transactionHash) throws APIException {
        Call<List<TransactionPoolUpdate>> transactionPoolUpdateCall = transactionsApi.txsHashPoolUpdatesGet(getProjectId(), transactionHash);

        try{
            Response<List<TransactionPoolUpdate>> transactionPoolUpdatesResponse = transactionPoolUpdateCall.execute();
            return processResponse(transactionPoolUpdatesResponse);
        } catch (IOException exp){
            throw new APIException("Exception while fetching transaction PoolUpdates for hash " + transactionHash , exp);
        }
    }


}
