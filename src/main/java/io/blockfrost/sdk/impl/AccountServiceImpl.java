package io.blockfrost.sdk.impl;

import io.blockfrost.sdk.api.AccountService;
import io.blockfrost.sdk.api.exception.APIException;
import io.blockfrost.sdk.api.model.*;
import io.blockfrost.sdk.api.util.OrderEnum;
import io.blockfrost.sdk.impl.retrofit.AccountsApi;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;
import java.util.List;

public class AccountServiceImpl extends BaseService implements AccountService {

    private AccountsApi accountsApi;

    public AccountServiceImpl(String baseUrl, String projectId) {
        super(baseUrl, projectId);
        this.accountsApi = getApiClient(AccountsApi.class);
    }

    private void validateStakeAddress(String address) throws APIException {
        if (address == null || address.equals("")) {
            throw new APIException("Stake address cannot be null or empty");
        }
    }

    @Override
    public Account getAccountByStakeAddress(String stakeAddress) throws APIException {

        validateStakeAddress(stakeAddress);

        Call<Account> call = accountsApi.accountsStakeAddressGet(getProjectId(), stakeAddress);

        try {
            Response<Account> response = call.execute();
            return processResponse(response);
        } catch (IOException exp) {
            throw new APIException("Exception while fetching account info by stakeAddress", exp);
        }
    }

    @Override
    public List<AccountHistory> getAccountHistory(String stakeAddress, int count, int page, OrderEnum order) throws APIException {

        validateStakeAddress(stakeAddress);

        Call<List<AccountHistory>> call = accountsApi.accountsStakeAddressHistoryGet(getProjectId(), stakeAddress, count, page, order.name());

        try {
            Response<List<AccountHistory>> response = call.execute();
            return processResponse(response);
        } catch (IOException exp) {
            throw new APIException("Exception while fetching account history for stakeAddress: " + stakeAddress, exp);
        }
    }

    @Override
    public List<AccountHistory> getAccountHistory(String stakeAddress, int count, int page) throws APIException {
        return getAccountHistory(stakeAddress, count, page, OrderEnum.asc);
    }

    //TODO: Implement
    @Override
    public List<AccountHistory> getAccountHistory(String stakeAddress, OrderEnum order) throws APIException {
        return null;
    }

    @Override
    public List<AccountHistory> getAccountHistory(String stakeAddress) throws APIException {
        return getAccountHistory(stakeAddress, OrderEnum.asc);
    }

    @Override
    public List<AccountRewardHistory> getAccountRewardHistory(String stakeAddress, int count, int page, OrderEnum order) throws APIException {

        validateStakeAddress(stakeAddress);

        Call<List<AccountRewardHistory>> call = accountsApi.accountsStakeAddressRewardsGet(getProjectId(), stakeAddress, count, page, order.name());

        try {
            Response<List<AccountRewardHistory>> response = call.execute();
            return processResponse(response);
        } catch (IOException exp) {
            throw new APIException("Exception while fetching account reward history for stakeAddress: " + stakeAddress, exp);
        }
    }

    @Override
    public List<AccountRewardHistory> getAccountRewardHistory(String stakeAddress, int count, int page) throws APIException {
        return getAccountRewardHistory(stakeAddress, count, page, OrderEnum.asc);
    }

    //TODO: Implement
    @Override
    public List<AccountRewardHistory> getAccountRewardHistory(String stakeAddress, OrderEnum order) throws APIException {
        return null;
    }

    @Override
    public List<AccountRewardHistory> getAccountRewardHistory(String stakeAddress) throws APIException {
        return getAccountRewardHistory(stakeAddress, OrderEnum.asc);
    }

    @Override
    public List<AccountDelegationHistory> getAccountDelegationHistory(String stakeAddress, int count, int page, OrderEnum order) throws APIException {
        validateStakeAddress(stakeAddress);

        Call<List<AccountDelegationHistory>> call = accountsApi.accountsStakeAddressDelegationsGet(getProjectId(), stakeAddress, count, page, order.name());

        try {
            Response<List<AccountDelegationHistory>> response = call.execute();
            return processResponse(response);
        } catch (IOException exp) {
            throw new APIException("Exception while fetching account delegation history for stakeAddress: " + stakeAddress, exp);
        }
    }

    @Override
    public List<AccountDelegationHistory> getAccountDelegationHistory(String stakeAddress, int count, int page) throws APIException {
        return getAccountDelegationHistory(stakeAddress, count, page, OrderEnum.asc);
    }

    //TODO: Implement
    @Override
    public List<AccountDelegationHistory> getAccountDelegationHistory(String stakeAddress, OrderEnum order) throws APIException {
        return null;
    }

    @Override
    public List<AccountDelegationHistory> getAccountDelegationHistory(String stakeAddress) throws APIException {
        return getAccountDelegationHistory(stakeAddress, OrderEnum.asc);
    }

    @Override
    public List<AccountRegistrationHistory> getAccountRegistrationHistory(String stakeAddress, int count, int page, OrderEnum order) throws APIException {
        validateStakeAddress(stakeAddress);

        Call<List<AccountRegistrationHistory>> call = accountsApi.accountsStakeAddressRegistrationsGet(getProjectId(), stakeAddress, count, page, order.name());

        try {
            Response<List<AccountRegistrationHistory>> response = call.execute();
            return processResponse(response);
        } catch (IOException exp) {
            throw new APIException("Exception while fetching account registration history for stakeAddress: " + stakeAddress, exp);
        }
    }

    @Override
    public List<AccountRegistrationHistory> getAccountRegistrationHistory(String stakeAddress, int count, int page) throws APIException {
        return getAccountRegistrationHistory(stakeAddress, count, page, OrderEnum.asc);
    }

    //TODO: Implement
    @Override
    public List<AccountRegistrationHistory> getAccountRegistrationHistory(String stakeAddress, OrderEnum order) throws APIException {
        return null;
    }

    @Override
    public List<AccountRegistrationHistory> getAccountRegistrationHistory(String stakeAddress) throws APIException {
        return getAccountRegistrationHistory(stakeAddress, OrderEnum.asc);
    }

    @Override
    public List<AccountWithdrawalHistory> getAccountWithdrawalHistory(String stakeAddress, int count, int page, OrderEnum order) throws APIException {
        validateStakeAddress(stakeAddress);

        Call<List<AccountWithdrawalHistory>> call = accountsApi.accountsStakeAddressWithdrawalsGet(getProjectId(), stakeAddress, count, page, order.name());

        try {
            Response<List<AccountWithdrawalHistory>> response = call.execute();
            return processResponse(response);
        } catch (IOException exp) {
            throw new APIException("Exception while fetching account withdrawal history for stakeAddress: " + stakeAddress, exp);
        }
    }

    @Override
    public List<AccountWithdrawalHistory> getAccountWithdrawalHistory(String stakeAddress, int count, int page) throws APIException {
        return getAccountWithdrawalHistory(stakeAddress, count, page, OrderEnum.asc);
    }

    //TODO: Implement
    @Override
    public List<AccountWithdrawalHistory> getAccountWithdrawalHistory(String stakeAddress, OrderEnum order) throws APIException {
        return null;
    }

    @Override
    public List<AccountWithdrawalHistory> getAccountWithdrawalHistory(String stakeAddress) throws APIException {
        return getAccountWithdrawalHistory(stakeAddress, OrderEnum.asc);
    }

    @Override
    public List<AccountMirHistory> getAccountMirHistory(String stakeAddress, int count, int page, OrderEnum order) throws APIException {
        validateStakeAddress(stakeAddress);

        Call<List<AccountMirHistory>> call = accountsApi.accountsStakeAddressMirsGet(getProjectId(), stakeAddress, count, page, order.name());

        try {
            Response<List<AccountMirHistory>> response = call.execute();
            return processResponse(response);
        } catch (IOException exp) {
            throw new APIException("Exception while fetching account MIR history for stakeAddress: " + stakeAddress, exp);
        }
    }

    @Override
    public List<AccountMirHistory> getAccountMirHistory(String stakeAddress, int count, int page) throws APIException {
        return getAccountMirHistory(stakeAddress, count, page, OrderEnum.asc);
    }

    //TODO: Implement
    @Override
    public List<AccountMirHistory> getAccountMirHistory(String stakeAddress, OrderEnum order) throws APIException {
        return null;
    }

    @Override
    public List<AccountMirHistory> getAccountMirHistory(String stakeAddress) throws APIException {
        return getAccountMirHistory(stakeAddress, OrderEnum.asc);
    }

    @Override
    public List<AccountAddress> getAccountAddresses(String stakeAddress, int count, int page, OrderEnum order) throws APIException {
        validateStakeAddress(stakeAddress);

        Call<List<AccountAddress>> call = accountsApi.accountsStakeAddressAddressesGet(getProjectId(), stakeAddress, count, page, order.name());

        try {
            Response<List<AccountAddress>> response = call.execute();
            return processResponse(response);
        } catch (IOException exp) {
            throw new APIException("Exception while fetching account addresses for stakeAddress: " + stakeAddress, exp);
        }
    }

    @Override
    public List<AccountAddress> getAccountAddresses(String stakeAddress, int count, int page) throws APIException {
        return getAccountAddresses(stakeAddress, count, page, OrderEnum.asc);
    }

    //TODO: Implement
    @Override
    public List<AccountAddress> getAccountAddresses(String stakeAddress, OrderEnum order) throws APIException {
        return null;
    }

    @Override
    public List<AccountAddress> getAccountAddresses(String stakeAddress) throws APIException {
        return getAccountAddresses(stakeAddress, OrderEnum.asc);
    }

    @Override
    public List<AccountAsset> getAccountAssets(String stakeAddress, int count, int page, OrderEnum order) throws APIException {
        validateStakeAddress(stakeAddress);

        Call<List<AccountAsset>> call = accountsApi.accountsStakeAddressAddressesAssetsGet(getProjectId(), stakeAddress, count, page, order.name());

        try {
            Response<List<AccountAsset>> response = call.execute();
            return processResponse(response);
        } catch (IOException exp) {
            throw new APIException("Exception while fetching account assets for stakeAddress: " + stakeAddress, exp);
        }
    }

    @Override
    public List<AccountAsset> getAccountAssets(String stakeAddress, int count, int page) throws APIException {
        return getAccountAssets(stakeAddress, count, page, OrderEnum.asc);
    }

    //TODO: Implement
    @Override
    public List<AccountAsset> getAccountAssets(String stakeAddress, OrderEnum order) throws APIException {
        return null;
    }

    @Override
    public List<AccountAsset> getAccountAssets(String stakeAddress) throws APIException {
        return getAccountAssets(stakeAddress, OrderEnum.asc);
    }
}
