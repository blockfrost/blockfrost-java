package io.blockfrost.sdk.api;

import io.blockfrost.sdk.api.exception.APIException;
import io.blockfrost.sdk.api.model.Asset;
import io.blockfrost.sdk.api.util.OrderEnum;

import java.util.List;

public interface AssetService {

    /**
     * Specific asset
     * Information about a specific asset
     * @param asset Concatenation of the policy_id and hex-encoded asset_name (required)
     * @return Asset
     */
    Asset getAsset(String asset) throws APIException;

    /**
     * Assets
     * List of assets.
     * @param count The number of results displayed on one page. (&lt;=100).
     * @param page The page number for listing the results.
     * @param order The ordering of items from the point of view of the blockchain, not the page listing itself. By default, we return oldest first, newest last.
     * @return List&lt;Asset&gt;
     */
    List<Asset> getAssets(int count, int page, OrderEnum order) throws APIException;

    /**
     * Assets
     * List of assets ordered asceding from the point of view of the blockchain, not the page listing itself.
     * @param count The number of results displayed on one page. (&lt;=100).
     * @param page The page number for listing the results.
     * @return List&lt;Asset&gt;
     */
    List<Asset> getAssets(int count, int page) throws APIException;

    /**
     * Assets
     * List of all assets.
     * @param order The ordering of items from the point of view of the blockchain, not the page listing itself. By default, we return oldest first, newest last.
     * @return List&lt;Asset&gt;
     */
    List<Asset> getAssets(OrderEnum order) throws APIException;

    /**
     * Assets
     * List of all assets ordered asceding from the point of view of the blockchain, not the page listing itself.
     * @return List&lt;Asset&gt;
     */
    List<Asset> getAssets() throws APIException;
}
