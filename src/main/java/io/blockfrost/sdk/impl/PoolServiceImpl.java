package io.blockfrost.sdk.impl;

import io.blockfrost.sdk.api.PoolService;
import io.blockfrost.sdk.api.exception.APIException;
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

    @Override
    public List<PoolRetirementInfo> getRetiredPools(OrderEnum order) throws APIException {
        return null;
    }

    @Override
    public List<PoolRetirementInfo> getRetiredPools() throws APIException {
        return getRetiredPools(OrderEnum.asc);
    }


}
