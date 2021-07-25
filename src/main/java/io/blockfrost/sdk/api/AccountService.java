package io.blockfrost.sdk.api;

import io.blockfrost.sdk.api.exception.APIException;
import io.blockfrost.sdk.api.model.Account;

/**
 * Get account specific information
 */
public interface AccountService {

    /**
     * Obtain information about a specific stake account.
     * @param stakeAddress Bech32 stake address. (required)
     * @return Account
     */
    public Account getAccountByStakeAddress(String stakeAddress) throws APIException;
}
