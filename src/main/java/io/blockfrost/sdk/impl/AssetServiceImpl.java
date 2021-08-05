package io.blockfrost.sdk.impl;

import io.blockfrost.sdk.api.AssetService;
import io.blockfrost.sdk.api.exception.APIException;
import io.blockfrost.sdk.api.model.Asset;
import io.blockfrost.sdk.api.util.OrderEnum;
import io.blockfrost.sdk.impl.retrofit.AssetsApi;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;
import java.util.List;

public class AssetServiceImpl extends BaseImpl implements AssetService {

    AssetsApi assetsApi;

    public AssetServiceImpl(String baseUrl, String projectId){
        super(baseUrl, projectId);
        assetsApi = getRetrofit().create(AssetsApi.class);
    }


    @Override
    public Asset getAsset(String asset) throws APIException {
        Call<Asset> assetCall = assetsApi.assetsAssetGet(getProjectId(), asset);

        try{
            Response<Asset> assetResponse = assetCall.execute();
            return processResponse(assetResponse);
        } catch (IOException exp){
            throw new APIException("Exception while fetching asset: " + asset, exp);
        }
    }

    @Override
    public List<Asset> getAssets(int count, int page, OrderEnum order) throws APIException {
        Call<List<Asset>> assetsCall = assetsApi.assetsGet(getProjectId(), count, page, order.name());

        try{
            Response<List<Asset>> assetsResponse = assetsCall.execute();
            return processResponse(assetsResponse);
        } catch (IOException exp){
            throw new APIException("Exception while fetching assets", exp);
        }
    }

    @Override
    public List<Asset> getAssets(int count, int page) throws APIException {
        return getAssets(count, page, OrderEnum.asc);
    }

    //TODO: Implement
    @Override
    public List<Asset> getAssets(OrderEnum order) throws APIException {
        return null;
    }

    @Override
    public List<Asset> getAssets() throws APIException {
        return getAssets(OrderEnum.asc);
    }
}
