package io.blockfrost.sdk.api;

import io.blockfrost.sdk.api.exception.APIException;
import io.blockfrost.sdk.api.model.Asset;
import io.blockfrost.sdk.api.model.AssetAddress;
import io.blockfrost.sdk.api.model.AssetHistory;
import io.blockfrost.sdk.api.model.AssetTransaction;
import io.blockfrost.sdk.api.util.OrderEnum;

import java.util.List;

public interface AssetService {

    /**
     * Specific asset
     * Information about a specific asset
     *
     * @param asset Concatenation of the policy_id and hex-encoded asset_name (required)
     * @return Asset
     */
    Asset getAsset(String asset) throws APIException;

    /**
     * Assets
     * List of assets.
     *
     * @param count The number of results displayed on one page. (&lt;=100).
     * @param page  The page number for listing the results.
     * @param order The ordering of items from the point of view of the blockchain, not the page listing itself. By default, we return oldest first, newest last.
     * @return List&lt;Asset&gt;
     */
    List<Asset> getAssets(int count, int page, OrderEnum order) throws APIException;

    /**
     * Assets
     * List of assets ordered ascending from the point of view of the blockchain, not the page listing itself.
     *
     * @param count The number of results displayed on one page. (&lt;=100).
     * @param page  The page number for listing the results.
     * @return List&lt;Asset&gt;
     */
    List<Asset> getAssets(int count, int page) throws APIException;

    /**
     * Assets
     * List of all assets.
     *
     * @param order The ordering of items from the point of view of the blockchain, not the page listing itself. By default, we return oldest first, newest last.
     * @return List&lt;Asset&gt;
     */
    List<Asset> getAssets(OrderEnum order) throws APIException;

    /**
     * Assets
     * List of all assets ordered asceding from the point of view of the blockchain, not the page listing itself.
     *
     * @return List&lt;Asset&gt;
     */
    List<Asset> getAssets() throws APIException;

    /**
     * Asset history
     * History of a specific asset
     *
     * @param asset Concatenation of the policy_id and hex-encoded asset_name (required)
     * @param count The number of results displayed on one page. (&lt;=100).
     * @param page  The page number for listing the results.
     * @param order The ordering of items from the point of view of the blockchain, not the page listing itself. By default, we return oldest first, newest last
     * @return List&lt;AssetHistory&gt;
     */
    List<AssetHistory> getAssetHistory(String asset, int count, int page, OrderEnum order) throws APIException;

    /**
     * Asset history
     * History of a specific asset ordered ascending from the point of view of the blockchain, not the page listing itself.
     *
     * @param asset Concatenation of the policy_id and hex-encoded asset_name (required)
     * @param count The number of results displayed on one page. (&lt;=100).
     * @param page  The page number for listing the results.
     * @return List&lt;AssetHistory&gt;
     */
    List<AssetHistory> getAssetHistory(String asset, int count, int page) throws APIException;

    /**
     * Asset history
     * Entire History of a specific asset
     *
     * @param asset Concatenation of the policy_id and hex-encoded asset_name (required)
     * @param order The ordering of items from the point of view of the blockchain, not the page listing itself. By default, we return oldest first, newest last
     * @return List&lt;AssetHistory&gt;
     */
    List<AssetHistory> getAssetHistory(String asset, OrderEnum order) throws APIException;

    /**
     * Asset history
     * Entire History of a specific asset ordered ascending from the point of view of the blockchain, not the page listing itself.
     *
     * @param asset Concatenation of the policy_id and hex-encoded asset_name (required)
     * @return List&lt;AssetHistory&gt;
     */
    List<AssetHistory> getAssetHistory(String asset) throws APIException;

    /**
     * Asset transactions
     * List of a specific asset transactions
     *
     * @param asset Concatenation of the policy_id and hex-encoded asset_name (required)
     * @param count The number of results displayed on one page. (&lt;=100).
     * @param page  The page number for listing the results.
     * @param order The ordering of items from the point of view of the blockchain, not the page listing itself. By default, we return oldest first, newest last.
     * @return List&lt;AssetTransaction&gt;
     */
    List<AssetTransaction> getAssetTransactions(String asset, int count, int page, OrderEnum order) throws APIException;

    /**
     * Asset transactions
     * List of a specific asset transactions ordered ascending from the point of view of the blockchain, not the page listing itself.
     *
     * @param asset Concatenation of the policy_id and hex-encoded asset_name (required)
     * @param count The number of results displayed on one page. (&lt;=100).
     * @param page  The page number for listing the results.
     * @return List&lt;AssetTransaction&gt;
     */
    List<AssetTransaction> getAssetTransactions(String asset, int count, int page) throws APIException;

    /**
     * Asset transactions
     * A list of all transactions for a specific asset
     *
     * @param asset Concatenation of the policy_id and hex-encoded asset_name (required)
     * @param order The ordering of items from the point of view of the blockchain, not the page listing itself. By default, we return oldest first, newest last.
     * @return List&lt;AssetTransaction&gt;
     */
    List<AssetTransaction> getAssetTransactions(String asset, OrderEnum order) throws APIException;

    /**
     * Asset transactions
     * A list of all transactions for a specific asset ordered ascending from the point of view of the blockchain, not the page listing itself.
     *
     * @param asset Concatenation of the policy_id and hex-encoded asset_name (required)
     * @return List&lt;AssetTransaction&gt;
     */
    List<AssetTransaction> getAssetTransactions(String asset) throws APIException;

    /**
     * Asset addresses
     * List of addresses containing a specific asset
     *
     * @param asset Concatenation of the policy_id and hex-encoded asset_name (required)
     * @param count The number of results displayed on one page. (&lt;=100).
     * @param page  The page number for listing the results.
     * @param order The ordering of items from the point of view of the blockchain, not the page listing itself. By default, we return oldest first, newest last
     * @return List&lt;AssetAddress&gt;
     */
    List<AssetAddress> getAssetAddresses(String asset, int count, int page, OrderEnum order) throws APIException;

    /**
     * Asset addresses
     * List of addresses containing a specific asset ordered ascending from the point of view of the blockchain, not the page listing itself.
     *
     * @param asset Concatenation of the policy_id and hex-encoded asset_name (required)
     * @param count The number of results displayed on one page. (&lt;=100).
     * @param page  The page number for listing the results.
     * @return List&lt;AssetAddress&gt;
     */
    List<AssetAddress> getAssetAddresses(String asset, int count, int page) throws APIException;

    /**
     * Asset addresses
     * List of addresses containing a specific asset
     *
     * @param asset Concatenation of the policy_id and hex-encoded asset_name (required)
     * @param order The ordering of items from the point of view of the blockchain, not the page listing itself. By default, we return oldest first, newest last
     * @return List&lt;AssetAddress&gt;
     */
    List<AssetAddress> getAssetAddresses(String asset, OrderEnum order) throws APIException;

    /**
     * Asset addresses
     * List of addresses containing a specific asset ordered ascending from the point of view of the blockchain, not the page listing itself.
     *
     * @param asset Concatenation of the policy_id and hex-encoded asset_name (required)
     * @return List&lt;AssetAddress&gt;
     */
    List<AssetAddress> getAssetAddresses(String asset) throws APIException;
}
