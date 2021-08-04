package io.blockfrost.sdk.api;

import io.blockfrost.sdk.api.exception.APIException;
import io.blockfrost.sdk.api.model.Account;

/**
 * Get account specific information
 */
public interface AccountService {

    /**
     * Obtain information about a specific networkStake account.
     * @param stakeAddress Bech32 networkStake address. (required)
     * @return Account
     */
    public Account getAccountByStakeAddress(String stakeAddress) throws APIException;
}
