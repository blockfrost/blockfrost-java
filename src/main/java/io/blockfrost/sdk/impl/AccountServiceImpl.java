package io.blockfrost.sdk.impl;

import io.blockfrost.sdk.api.AccountService;
import io.blockfrost.sdk.api.exception.APIException;
import io.blockfrost.sdk.api.model.Account;
import io.blockfrost.sdk.impl.retrofit.AccountsApi;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;

public class AccountServiceImpl extends BaseImpl implements AccountService {

    private AccountsApi accountsApi;

    public AccountServiceImpl(String baseUrl, String projectId) {
        super(baseUrl, projectId);
        this.accountsApi = getApiClient(AccountsApi.class);
    }

    @Override
    public Account getAccountByStakeAddress(String stakeAddress) throws APIException {
        Call<Account> call = accountsApi.accountsStakeAddressGet(getProjectId(), stakeAddress);

        try{
            Response<Account> response = call.execute();
            return processResponse(response);
        } catch (IOException exp){
            throw new APIException("Exception while fetching account info by stakeAddress", exp);
        }
    }
}
