package io.blockfrost.sdk.api;

import io.blockfrost.sdk.api.exception.APIException;
import io.blockfrost.sdk.api.model.Account;
import io.blockfrost.sdk.api.model.AccountHistory;
import io.blockfrost.sdk.api.util.OrderEnum;

import java.util.List;

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

    /**
     * Account history
     * Obtain information about the history of a specific account.
     *
     * @param stakeAddress Bech32 stake address. (required)
     * @param count        The number of results displayed on one page.
     * @param page         The page number for listing the results.
     * @param order        The ordering of items from the point of view of the blockchain, not the page listing itself. By default, we return oldest first, newest last.
     * @return List&lt;AccountHistory&gt;
     */
    public List<AccountHistory> getAccountHistory(String stakeAddress, int count, int page, OrderEnum order) throws APIException;

    /**
     * Account history
     * Obtain information about the history of a specific account in ascending order from the point of view of the blockchain, not the page listing itself. By default, we return oldest first, newest last.
     *
     * @param stakeAddress Bech32 stake address. (required)
     * @param count        The number of results displayed on one page.
     * @param page         The page number for listing the results.
     * @return List&lt;AccountHistory&gt;
     */
    public List<AccountHistory> getAccountHistory(String stakeAddress, int count, int page) throws APIException;

    /**
     * Account history
     * Obtain information about the entire history of a specific account.
     *
     * @param stakeAddress Bech32 stake address. (required)
     * @param order        The ordering of items from the point of view of the blockchain, not the page listing itself. By default, we return oldest first, newest last.
     * @return List&lt;AccountHistory&gt;
     */
    public List<AccountHistory> getAccountHistory(String stakeAddress, OrderEnum order) throws APIException;

    /**
     * Account history
     * Obtain information about the entire history of a specific account in ascending order from the point of view of the blockchain, not the page listing itself. By default, we return oldest first, newest last.
     *
     * @param stakeAddress Bech32 stake address. (required)
     * @return List&lt;AccountHistory&gt;
     */
    public List<AccountHistory> getAccountHistory(String stakeAddress) throws APIException;
}
