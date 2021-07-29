package io.blockfrost.sdk.impl;

import io.blockfrost.sdk.api.PoolService;
import io.blockfrost.sdk.api.exception.APIException;
import io.blockfrost.sdk.api.model.Pool;
import io.blockfrost.sdk.api.model.PoolHistory;
import io.blockfrost.sdk.api.model.PoolMetadata;
import io.blockfrost.sdk.api.model.PoolRetirementInfo;
import io.blockfrost.sdk.api.util.OrderEnum;
import io.blockfrost.sdk.impl.helper.ValidationHelper;
import io.blockfrost.sdk.impl.retrofit.PoolsApi;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;
import java.util.List;

public class PoolServiceImpl extends BaseImpl implements PoolService {

    PoolsApi poolsApi;

    public PoolServiceImpl(String baseUrl, String projectId){
        super(baseUrl, projectId);
        poolsApi = getRetrofit().create(PoolsApi.class);
    }

    private void validatePoolId(String poolId) throws APIException {
        if ( poolId == null || poolId.equals("") ){
            throw new APIException("PoolId cannot be null or empty");
        }
    }

    @Override
    public List<String> getPools(int count, int page, OrderEnum order) throws APIException {

        ValidationHelper.validateCount(count);

        Call<List<String>> poolsCall = poolsApi.poolsGet(getProjectId(), count, page, order.name());

        try{
            Response<List<String>> poolsResponse = poolsCall.execute();
            return processResponse(poolsResponse);
        } catch (IOException exp){
            throw new APIException("Exception while fetching pools ", exp);
        }
    }

    @Override
    public List<String> getPools(int count, int page) throws APIException {
        return getPools(count, page, OrderEnum.asc);
    }

    //TODO: Implement using parallel fetch
    @Override
    public List<String> getPools(OrderEnum order) throws APIException {
        return null;
    }

    @Override
    public List<String> getPools() throws APIException {
        return getPools(OrderEnum.asc);
    }

    @Override
    public List<PoolRetirementInfo> getRetiredPools(int count, int page, OrderEnum order) throws APIException {

        ValidationHelper.validateCount(count);

        Call<List<PoolRetirementInfo>> poolsCall = poolsApi.poolsRetiredGet(getProjectId(), count, page, order.name());

        try{
            Response<List<PoolRetirementInfo>> poolsResponse = poolsCall.execute();
            return processResponse(poolsResponse);
        } catch (IOException exp){
            throw new APIException("Exception while fetching retired pools ", exp);
        }
    }

    @Override
    public List<PoolRetirementInfo> getRetiredPools(int count, int page) throws APIException {
        return getRetiredPools(count, page, OrderEnum.asc);
    }

    //TODO: Implement using parallel fetch
    @Override
    public List<PoolRetirementInfo> getRetiredPools(OrderEnum order) throws APIException {
        return null;
    }

    @Override
    public List<PoolRetirementInfo> getRetiredPools() throws APIException {
        return getRetiredPools(OrderEnum.asc);
    }

    @Override
    public List<PoolRetirementInfo> getRetiringPools(int count, int page, OrderEnum order) throws APIException {

        ValidationHelper.validateCount(count);

        Call<List<PoolRetirementInfo>> poolsCall = poolsApi.poolsRetiringGet(getProjectId(), count, page, order.name());

        try{
            Response<List<PoolRetirementInfo>> poolsResponse = poolsCall.execute();
            return processResponse(poolsResponse);
        } catch (IOException exp){
            throw new APIException("Exception while fetching retiring pools ", exp);
        }

    }

    @Override
    public List<PoolRetirementInfo> getRetiringPools(int count, int page) throws APIException {
        return getRetiringPools(count, page, OrderEnum.asc);
    }

    //TODO: Implement using parallel fetch
    @Override
    public List<PoolRetirementInfo> getRetiringPools(OrderEnum order) throws APIException {
        return null;
    }

    @Override
    public List<PoolRetirementInfo> getRetiringPools() throws APIException {
        return getRetiringPools(OrderEnum.asc);
    }

    @Override
    public Pool getPool(String poolId) throws APIException {

        validatePoolId(poolId);

        Call<Pool> poolCall = poolsApi.poolsPoolIdGet(getProjectId(), poolId);

        try{
            Response<Pool> poolResponse = poolCall.execute();
            return processResponse(poolResponse);
        } catch (IOException exp){
            throw new APIException("Exception while fetching pool for poolId: " + poolId, exp);
        }
    }

    @Override
    public List<PoolHistory> getPoolHistory(String poolId, int count, int page, OrderEnum order) throws APIException {

        validatePoolId(poolId);

        ValidationHelper.validateCount(count);

        Call<List<PoolHistory>> poolHistoryCall = poolsApi.poolsPoolIdHistoryGet(getProjectId(), poolId, count, page, order.name());

        try{
            Response<List<PoolHistory>> poolHistoryResponse = poolHistoryCall.execute();
            return processResponse(poolHistoryResponse);
        } catch (IOException exp){
            throw new APIException("Exception while fetching history for poolId: " + poolId, exp);
        }
    }

    @Override
    public List<PoolHistory> getPoolHistory(String poolId, int count, int page) throws APIException {
        return getPoolHistory(poolId, count, page, OrderEnum.asc);
    }

    //TODO: Implement using parallel fetch
    @Override
    public List<PoolHistory> getPoolHistory(String poolId, OrderEnum order) throws APIException {
        return null;
    }

    @Override
    public List<PoolHistory> getPoolHistory(String poolId) throws APIException {
        return getPoolHistory(poolId, OrderEnum.asc);
    }

    @Override
    public PoolMetadata getPoolMetadata(String poolId) throws APIException {
        
        validatePoolId(poolId);

        Call<PoolMetadata> poolMetadataCall = poolsApi.poolsPoolIdMetadataGet(getProjectId(), poolId);

        try{
            Response<PoolMetadata> poolMetadataResponse = poolMetadataCall.execute();
            return processResponse(poolMetadataResponse);
        } catch (IOException exp){
            throw new APIException("Exception while fetching poolMetadata for poolId: " + poolId, exp);
        }
    }


}
