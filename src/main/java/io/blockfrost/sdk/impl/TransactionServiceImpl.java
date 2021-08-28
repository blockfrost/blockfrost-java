package io.blockfrost.sdk.impl;

import io.blockfrost.sdk.api.TransactionService;
import io.blockfrost.sdk.api.exception.APIException;
import io.blockfrost.sdk.api.model.*;
import io.blockfrost.sdk.impl.retrofit.TransactionsApi;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;
import java.util.List;

public class TransactionServiceImpl extends BaseService implements TransactionService {

    TransactionsApi transactionsApi;

    public TransactionServiceImpl(String baseUrl, String projectId) {
        super(baseUrl, projectId);
        transactionsApi = getRetrofit().create(TransactionsApi.class);
    }

    @Override
    public Transaction getTransaction(String hash) throws APIException {
        Call<Transaction> transactionCall = transactionsApi.txsHashGet(getProjectId(), hash);

        try {
            Response<Transaction> transactionResponse = transactionCall.execute();
            return processResponse(transactionResponse);
        } catch (IOException exp) {
            throw new APIException("Exception while fetching transaction for hash " + hash, exp);
        }
    }

    @Override
    public TransactionUtxo getTransactionUtxo(String hash) throws APIException {
        Call<TransactionUtxo> transactionUtxoCall = transactionsApi.txsHashUtxosGet(getProjectId(), hash);

        try {
            Response<TransactionUtxo> transactionUtxoResponse = transactionUtxoCall.execute();
            return processResponse(transactionUtxoResponse);
        } catch (IOException exp) {
            throw new APIException("Exception while fetching transaction utxos for hash " + hash, exp);
        }
    }

    @Override
    public List<TransactionStake> getTransactionStakes(String hash) throws APIException {
        Call<List<TransactionStake>> transactionStakesCall = transactionsApi.txsHashStakesGet(getProjectId(), hash);

        try {
            Response<List<TransactionStake>> transactionStakesResponse = transactionStakesCall.execute();
            return processResponse(transactionStakesResponse);
        } catch (IOException exp) {
            throw new APIException("Exception while fetching transaction stakes for hash " + hash, exp);
        }
    }

    @Override
    public List<TransactionDelegation> getTransactionDelegations(String hash) throws APIException {
        Call<List<TransactionDelegation>> transactionDelegationCall = transactionsApi.txsHashDelegationsGet(getProjectId(), hash);

        try {
            Response<List<TransactionDelegation>> transactionDelegationsResponse = transactionDelegationCall.execute();
            return processResponse(transactionDelegationsResponse);
        } catch (IOException exp) {
            throw new APIException("Exception while fetching transaction delegations for hash " + hash, exp);
        }
    }

    @Override
    public List<TransactionWithdrawal> getTransactionWithdrawals(String hash) throws APIException {
        Call<List<TransactionWithdrawal>> transactionWithdrawalCall = transactionsApi.txsHashWithdrawalsGet(getProjectId(), hash);

        try {
            Response<List<TransactionWithdrawal>> transactionWithdrawalsResponse = transactionWithdrawalCall.execute();
            return processResponse(transactionWithdrawalsResponse);
        } catch (IOException exp) {
            throw new APIException("Exception while fetching transaction withdrawals for hash " + hash, exp);
        }
    }

    @Override
    public List<TransactionMir> getTransactionMirs(String hash) throws APIException {
        Call<List<TransactionMir>> transactionMirCall = transactionsApi.txsHashMirsGet(getProjectId(), hash);

        try {
            Response<List<TransactionMir>> transactionMirsResponse = transactionMirCall.execute();
            return processResponse(transactionMirsResponse);
        } catch (IOException exp) {
            throw new APIException("Exception while fetching transaction mirs for hash " + hash, exp);
        }
    }

    @Override
    public List<TransactionPoolUpdate> getTransactionPoolUpdates(String hash) throws APIException {
        Call<List<TransactionPoolUpdate>> transactionPoolUpdateCall = transactionsApi.txsHashPoolUpdatesGet(getProjectId(), hash);

        try {
            Response<List<TransactionPoolUpdate>> transactionPoolUpdatesResponse = transactionPoolUpdateCall.execute();
            return processResponse(transactionPoolUpdatesResponse);
        } catch (IOException exp) {
            throw new APIException("Exception while fetching transaction pool updates for hash " + hash, exp);
        }
    }

    @Override
    public List<TransactionPoolRetire> getTransactionPoolRetires(String hash) throws APIException {
        Call<List<TransactionPoolRetire>> transactionPoolRetireCall = transactionsApi.txsHashPoolRetiresGet(getProjectId(), hash);

        try {
            Response<List<TransactionPoolRetire>> transactionPoolRetiresResponse = transactionPoolRetireCall.execute();
            return processResponse(transactionPoolRetiresResponse);
        } catch (IOException exp) {
            throw new APIException("Exception while fetching transaction pool retires for hash " + hash, exp);
        }
    }

    @Override
    public List<TransactionMetadataJson> getTransactionMetadata(String hash) throws APIException {
        Call<List<TransactionMetadataJson>> transactionMetadataCall = transactionsApi.txsHashMetadataGet(getProjectId(), hash);

        try {
            Response<List<TransactionMetadataJson>> transactionMetadataResponse = transactionMetadataCall.execute();
            return processResponse(transactionMetadataResponse);
        } catch (IOException exp) {
            throw new APIException("Exception while fetching transaction metadata for hash " + hash, exp);
        }
    }

    @Override
    public List<TransactionMetadataCbor> getTransactionMetadataCbor(String hash) throws APIException {
        Call<List<TransactionMetadataCbor>> transactionCborMetadataCall = transactionsApi.txsHashMetadataCborGet(getProjectId(), hash);

        try {
            Response<List<TransactionMetadataCbor>> transactionCborMetadataResponse = transactionCborMetadataCall.execute();
            return processResponse(transactionCborMetadataResponse);
        } catch (IOException exp) {
            throw new APIException("Exception while fetching transaction cbor metadata for hash " + hash, exp);
        }
    }

    @Override
    public String submitTransaction(byte[] serializedTransaction) throws APIException {
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/cbor"), serializedTransaction);

        Call<String> transactionSubmitCall = transactionsApi.txSubmitPost(getProjectId(), requestBody);

        try {
            Response<String> transactionSubmitResponse = transactionSubmitCall.execute();
            return processResponse(transactionSubmitResponse);
        } catch (IOException exp) {
            throw new APIException("Exception while submitting transaction  " + new String(serializedTransaction), exp);
        }
    }

}
