package io.blockfrost.sdk.impl;

import io.blockfrost.sdk.api.AssetService;
import io.blockfrost.sdk.api.exception.APIException;
import io.blockfrost.sdk.api.model.Asset;
import io.blockfrost.sdk.impl.retrofit.AssetsApi;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;

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
}
