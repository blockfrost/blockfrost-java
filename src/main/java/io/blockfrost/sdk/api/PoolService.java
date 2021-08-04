package io.blockfrost.sdk.api;

import io.blockfrost.sdk.api.exception.APIException;
import io.blockfrost.sdk.api.model.*;
import io.blockfrost.sdk.api.util.OrderEnum;

import java.util.List;

public interface PoolService {

    /**
     * List of networkStake pools
     * List of registered networkStake pools.
     * @param count The numbers of pools per page.
     * @param page The page number for listing the results.
     * @param order The ordering of items from the point of view of the blockchain, not the page listing itself. We return oldest first, newest last.
     * @return Call&lt;List&lt;String&gt;&gt;
     */
    List<String> getPools(int count, int page, OrderEnum order) throws APIException;

    /**
     * List of networkStake pools
     * List of registered networkStake pools ordered from the point of view of the blockchain, not the page listing itself. By default, we return oldest first, newest last.
     * @param count The numbers of pools per page (&lt;=100).
     * @param page The page number for listing the results.
     * @return List&lt;String&gt;
     */
    List<String> getPools(int count, int page) throws APIException;


    /**
     * List of networkStake pools
     * List of all registered networkStake pools.
     * @param order The ordering of items from the point of view of the blockchain.
     * @return List&lt;String&gt;
     */
    //TODO: Implement
    List<String> getPools(OrderEnum order) throws APIException;

    /**
     * List of networkStake pools
     * List of all registered networkStake pools in ascending order from the point of view of the blockchain. We return oldest first, newest last
     * @return List&lt;String&gt;
     */
    List<String> getPools() throws APIException;


    /**
     * List of retired pools
     * List of retired networkStake pools.
     * @param count The numbers of pools per page.
     * @param page The page number for listing the results.
     * @param order The ordering of items from the point of view of the blockchain, not the page listing itself. We return oldest first, newest last.
     * @return Call&lt;List&lt;PoolRetirementInfo&gt;&gt;
     */
    List<PoolRetirementInfo> getRetiredPools(int count, int page, OrderEnum order) throws APIException;

    /**
     * List of retired pools
     * List of retired networkStake pools ordered from the point of view of the blockchain, not the page listing itself. By default, we return oldest first, newest last.
     * @param count The numbers of pools per page (&lt;=100).
     * @param page The page number for listing the results.
     * @return List&lt;PoolRetirementInfo&gt;
     */
    List<PoolRetirementInfo> getRetiredPools(int count, int page) throws APIException;


    /**
     * List of retired pools
     * List of all retired networkStake pools.
     * @param order The ordering of items from the point of view of the blockchain.
     * @return List&lt;PoolRetirementInfo&gt;
     */
    //TODO: Implement
    List<PoolRetirementInfo> getRetiredPools(OrderEnum order) throws APIException;

    /**
     * List of retired pools
     * List of all retired networkStake pools in ascending order from the point of view of the blockchain. We return oldest first, newest last
     * @return List&lt;PoolRetirementInfo&gt;
     */
    List<PoolRetirementInfo> getRetiredPools() throws APIException;

    /**
     * List of retiring pools
     * List of retiring networkStake pools.
     * @param count The numbers of pools per page.
     * @param page The page number for listing the results.
     * @param order The ordering of items from the point of view of the blockchain, not the page listing itself. We return oldest first, newest last.
     * @return Call&lt;List&lt;PoolRetirementInfo&gt;&gt;
     */
    List<PoolRetirementInfo> getRetiringPools(int count, int page, OrderEnum order) throws APIException;

    /**
     * List of retiring pools
     * List of retiring networkStake pools ordered from the point of view of the blockchain, not the page listing itself. By default, we return oldest first, newest last.
     * @param count The numbers of pools per page (&lt;=100).
     * @param page The page number for listing the results.
     * @return List&lt;PoolRetirementInfo&gt;
     */
    List<PoolRetirementInfo> getRetiringPools(int count, int page) throws APIException;


    /**
     * List of retiring pools
     * List of all retiring networkStake pools.
     * @param order The ordering of items from the point of view of the blockchain.
     * @return List&lt;PoolRetirementInfo&gt;
     */
    //TODO: Implement
    List<PoolRetirementInfo> getRetiringPools(OrderEnum order) throws APIException;

    /**
     * List of retiring pools
     * List of all retiring networkStake pools in ascending order from the point of view of the blockchain. We return oldest first, newest last
     * @return List&lt;PoolRetirementInfo&gt;
     */
    List<PoolRetirementInfo> getRetiringPools() throws APIException;

    /**
     * Specific networkStake pool
     * Pool information.
     * @param poolId Bech32 or hexadecimal pool ID. (required)
     * @return Pool
     */
    Pool getPool(String poolId) throws APIException;

    /**
     * NetworkStake pool history
     * History of networkStake pool parameters over epochs.
     * @param poolId Bech32 or hexadecimal pool ID. (required)
     * @param count The number of results displayed on one page.
     * @param page The page number for listing the results.
     * @param order The ordering of items from the point of view of the blockchain, not the page listing itself. By default, we return oldest first, newest last.  (optional, default to asc)
     * @return List&lt;PoolHistory&gt;
     */
    List<PoolHistory> getPoolHistory(String poolId, int count, int page, OrderEnum order) throws APIException;

    /**
     * List of retiring pools
     * List of retiring networkStake pools ordered from the point of view of the blockchain, not the page listing itself. By default, we return oldest first, newest last.
     * @param poolId Bech32 or hexadecimal pool ID. (required)
     * @param count The numbers of pools per page (&lt;=100).
     * @param page The page number for listing the results.
     * @return List&lt;PoolHistory&gt;
     */
    List<PoolHistory> getPoolHistory(String poolId, int count, int page) throws APIException;


    /**
     * List of retiring pools
     * List of all retiring networkStake pools.
     * @param poolId Bech32 or hexadecimal pool ID. (required)
     * @param order The ordering of items from the point of view of the blockchain.
     * @return List&lt;PoolHistory&gt;
     */
    //TODO: Implement
    List<PoolHistory> getPoolHistory(String poolId, OrderEnum order) throws APIException;

    /**
     * List of retiring pools
     * List of all retiring networkStake pools in ascending order from the point of view of the blockchain. We return oldest first, newest last
     * @param poolId Bech32 or hexadecimal pool ID. (required)
     * @return List&lt;PoolHistory&gt;
     */
    List<PoolHistory> getPoolHistory(String poolId) throws APIException;

    /**
     * NetworkStake pool metadata
     * NetworkStake pool registration metadata.
     * @param poolId Bech32 or hexadecimal pool ID. (required)
     * @return PoolMetadata
     */
    PoolMetadata getPoolMetadata(String poolId) throws APIException;

    /**
     * NetworkStake pool relays
     * Relays of a networkStake pool.
     * @param poolId Bech32 or hexadecimal pool ID. (required)
     * @return List&lt;Object&gt;
     */
    List<PoolRelay> getPoolRelays(String poolId) throws APIException;

    /**
     * NetworkStake pool delegators
     * List of current networkStake pools delegators.
     * @param poolId Bech32 or hexadecimal pool ID. (required)
     * @param count The number of results displayed on one page.
     * @param page The page number for listing the results.
     * @param order The ordering of items from the point of view of the blockchain, not the page listing itself. By default, we return oldest first, newest last.  (optional, default to asc)
     * @return List&lt;PoolDelegator&gt;
     */
    List<PoolDelegator> getPoolDelegators(String poolId, int count, int page, OrderEnum order) throws APIException;

    /**
     * List of networkStake pool delegators
     * List of networkStake pool delegators ordered from the point of view of the blockchain, not the page listing itself. By default, we return oldest first, newest last.
     * @param poolId Bech32 or hexadecimal pool ID. (required)
     * @param count The numbers of pools per page (&lt;=100).
     * @param page The page number for listing the results.
     * @return List&lt;PoolDelegator&gt;
     */
    List<PoolDelegator> getPoolDelegators(String poolId, int count, int page) throws APIException;


    /**
     * List of networkStake pool delegators
     * List of networkStake pool delegators
     * @param poolId Bech32 or hexadecimal pool ID. (required)
     * @param order The ordering of items from the point of view of the blockchain.
     * @return List&lt;PoolDelegator&gt;
     */
    //TODO: Implement
    List<PoolDelegator> getPoolDelegators(String poolId, OrderEnum order) throws APIException;

    /**
     * List of networkStake pool delegators
     * List of networkStake pool delegators in ascending order from the point of view of the blockchain. We return oldest first, newest last
     * @param poolId Bech32 or hexadecimal pool ID. (required)
     * @return List&lt;PoolDelegator&gt;
     */
    List<PoolDelegator> getPoolDelegators(String poolId) throws APIException;

    /**
     * NetworkStake pool blocks
     * List of networkStake pools blocks.
     * @param poolId Bech32 or hexadecimal pool ID. (required)
     * @param count The number of results displayed on one page.
     * @param page The page number for listing the results.
     * @param order The ordering of items from the point of view of the blockchain, not the page listing itself. By default, we return oldest first, newest last.  (optional, default to asc)
     * @return List&lt;String&gt;
     */
    List<String> getPoolBlocks(String poolId, int count, int page, OrderEnum order) throws APIException;

    /**
     * NetworkStake pool blocks
     * List of networkStake pools blocks. ordered from the point of view of the blockchain, not the page listing itself. By default, we return oldest first, newest last.
     * @param poolId Bech32 or hexadecimal pool ID. (required)
     * @param count The numbers of pools per page (&lt;=100).
     * @param page The page number for listing the results.
     * @return List&lt;String&gt;
     */
    List<String> getPoolBlocks(String poolId, int count, int page) throws APIException;


    /**
     * NetworkStake pool blocks
     * List of networkStake pools blocks.
     * @param poolId Bech32 or hexadecimal pool ID. (required)
     * @param order The ordering of items from the point of view of the blockchain.
     * @return List&lt;String&gt;
     */
    //TODO: Implement
    List<String> getPoolBlocks(String poolId, OrderEnum order) throws APIException;

    /**
     * NetworkStake pool blocks
     * List of networkStake pools blocks. in ascending order from the point of view of the blockchain. We return oldest first, newest last
     * @param poolId Bech32 or hexadecimal pool ID. (required)
     * @return List&lt;String&gt;
     */
    List<String> getPoolBlocks(String poolId) throws APIException;

    /**
     * NetworkStake pool updates
     * List of certificate updates to the networkStake pool.
     * @param poolId Bech32 or hexadecimal pool ID. (required)
     * @param count The number of results displayed on one page. (optional, default to 100)
     * @param page The page number for listing the results. (optional, default to 1)
     * @param order The ordering of items from the point of view of the blockchain, not the page listing itself. By default, we return oldest first, newest last.  (optional, default to asc)
     * @return List&lt;PoolUpdate&gt;
     */
    List<PoolUpdate> getPoolUpdates(String poolId, int count, int page, OrderEnum order) throws APIException;

    /**
     * NetworkStake pool updates
     * List of certificate updates to the networkStake pool. ordered from the point of view of the blockchain, not the page listing itself. By default, we return oldest first, newest last.
     * @param poolId Bech32 or hexadecimal pool ID. (required)
     * @param count The numbers of pools per page (&lt;=100).
     * @param page The page number for listing the results.
     * @return List&lt;PoolUpdate&gt;
     */
    List<PoolUpdate> getPoolUpdates(String poolId, int count, int page) throws APIException;


    /**
     * NetworkStake pool updates
     * List of certificate updates to the networkStake pool.
     * @param poolId Bech32 or hexadecimal pool ID. (required)
     * @param order The ordering of items from the point of view of the blockchain.
     * @return List&lt;PoolUpdate&gt;
     */
    //TODO: Implement
    List<PoolUpdate> getPoolUpdates(String poolId, OrderEnum order) throws APIException;

    /**
     * NetworkStake pool updates
     * List of certificate updates to the networkStake pool. in ascending order from the point of view of the blockchain. We return oldest first, newest last
     * @param poolId Bech32 or hexadecimal pool ID. (required)
     * @return List&lt;PoolUpdate&gt;
     */
    List<PoolUpdate> getPoolUpdates(String poolId) throws APIException;

}
