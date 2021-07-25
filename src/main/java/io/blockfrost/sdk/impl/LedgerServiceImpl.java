package io.blockfrost.sdk.impl;

import io.blockfrost.sdk.api.LedgerService;
import io.blockfrost.sdk.api.exception.APIException;
import io.blockfrost.sdk.api.model.Genesis;
import io.blockfrost.sdk.impl.retrofit.LedgerApi;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;

public class LedgerServiceImpl extends BaseImpl implements LedgerService {

    LedgerApi ledgerApi;

    public LedgerServiceImpl(String baseUrl, String projectId){
        super(baseUrl, projectId);
        ledgerApi = getRetrofit().create(LedgerApi.class);
    }

    @Override
    public Genesis getGenesis() throws APIException {
        Call<Genesis> genesisCall = ledgerApi.genesisGet(getProjectId());

        try{
            Response<Genesis> genesisResponse = genesisCall.execute();
            return processResponse(genesisResponse);
        } catch (IOException exp){
            throw new APIException("Exception while fetching blockchain genesis", exp);
        }
    }
}
