package io.blockfrost.sdk.api;

import io.blockfrost.sdk.api.exception.APIException;
import io.blockfrost.sdk.api.model.PoolRetirementInfo;
import io.blockfrost.sdk.api.util.OrderEnum;

import java.util.List;

public interface PoolService {

    /**
     * List of stake pools
     * List of registered stake pools.
     * @param count The numbers of pools per page.
     * @param page The page number for listing the results.
     * @param order The ordering of items from the point of view of the blockchain, not the page listing itself. We return oldest first, newest last.
     * @return Call&lt;List&lt;String&gt;&gt;
     */
    List<String> getPools(int count, int page, OrderEnum order) throws APIException;

    /**
     * List of stake pools
     * List of registered stake pools ordered from the point of view of the blockchain, not the page listing itself. By default, we return oldest first, newest last.
     * @param count The numbers of pools per page (&lt;=100).
     * @param page The page number for listing the results.
     * @return List&lt;String&gt;
     */
    List<String> getPools(int count, int page) throws APIException;


    /**
     * List of stake pools
     * List of all registered stake pools.
     * @param order The ordering of items from the point of view of the blockchain.
     * @return List&lt;String&gt;
     */
    //TODO: Implement
    List<String> getPools(OrderEnum order) throws APIException;

    /**
     * List of stake pools
     * List of all registered stake pools in ascending order from the point of view of the blockchain. We return oldest first, newest last
     * @return List&lt;String&gt;
     */
    List<String> getPools() throws APIException;


    /**
     * List of retired pools
     * List of retired stake pools.
     * @param count The numbers of pools per page.
     * @param page The page number for listing the results.
     * @param order The ordering of items from the point of view of the blockchain, not the page listing itself. We return oldest first, newest last.
     * @return Call&lt;List&lt;PoolRetirementInfo&gt;&gt;
     */
    List<PoolRetirementInfo> getRetiredPools(int count, int page, OrderEnum order) throws APIException;

    /**
     * List of retired pools
     * List of retired stake pools ordered from the point of view of the blockchain, not the page listing itself. By default, we return oldest first, newest last.
     * @param count The numbers of pools per page (&lt;=100).
     * @param page The page number for listing the results.
     * @return List&lt;PoolRetirementInfo&gt;
     */
    List<PoolRetirementInfo> getRetiredPools(int count, int page) throws APIException;


    /**
     * List of retired pools
     * List of all retired stake pools.
     * @param order The ordering of items from the point of view of the blockchain.
     * @return List&lt;PoolRetirementInfo&gt;
     */
    //TODO: Implement
    List<PoolRetirementInfo> getRetiredPools(OrderEnum order) throws APIException;

    /**
     * List of retired pools
     * List of all retired stake pools in ascending order from the point of view of the blockchain. We return oldest first, newest last
     * @return List&lt;PoolRetirementInfo&gt;
     */
    List<PoolRetirementInfo> getRetiredPools() throws APIException;

}
