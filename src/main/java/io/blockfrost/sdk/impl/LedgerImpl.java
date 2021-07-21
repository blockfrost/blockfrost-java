package io.blockfrost.sdk.impl;

import io.blockfrost.sdk.api.Ledger;
import io.blockfrost.sdk.api.exception.BlockfrostAPIException;
import io.blockfrost.sdk.impl.model.Genesis;
import io.blockfrost.sdk.impl.retrofit.LedgerApi;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;

public class LedgerImpl extends BaseImpl implements Ledger {

    LedgerApi ledgerApi;

    public LedgerImpl(String baseUrl, String projectId){
        super(baseUrl, projectId);
        ledgerApi = getRetrofit().create(LedgerApi.class);
    }

    @Override
    public Genesis getGenesis() throws BlockfrostAPIException {
        Call<Genesis> genesisCall = ledgerApi.genesisGet(getProjectId());

        try{
            Response<Genesis> genesisResponse = genesisCall.execute();
            return processResponse(genesisResponse);
        } catch (IOException exp){
            throw new BlockfrostAPIException("Exception while fetching blockchain genesis", exp);
        }
    }
}
