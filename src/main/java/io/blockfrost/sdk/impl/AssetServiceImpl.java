package io.blockfrost.sdk.impl;

import io.blockfrost.sdk.api.AssetService;
import io.blockfrost.sdk.api.exception.APIException;
import io.blockfrost.sdk.api.model.Asset;
import io.blockfrost.sdk.api.model.AssetHistory;
import io.blockfrost.sdk.api.model.AssetTransaction;
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

    private void validateAsset(String asset) throws APIException {
        if ( asset == null || asset.equals("" )){
            throw new APIException("Asset cannot be null or empty");
        }
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

    @Override
    public List<AssetHistory> getAssetHistory(String asset, int count, int page, OrderEnum order) throws APIException {

        validateAsset(asset);

        Call<List<AssetHistory>> assetHistoryCall = assetsApi.assetsAssetHistoryGet(getProjectId(), asset, count, page, order.name());

        try{
            Response<List<AssetHistory>> assetHistoryResponse = assetHistoryCall.execute();
            return processResponse(assetHistoryResponse);
        } catch (IOException exp){
            throw new APIException("Exception while fetching asset history for asset: " + asset, exp);
        }
    }

    @Override
    public List<AssetHistory> getAssetHistory(String asset, int count, int page) throws APIException {
        return getAssetHistory(asset, count, page, OrderEnum.asc);
    }

    //TODO: Implement
    @Override
    public List<AssetHistory> getAssetHistory(String asset, OrderEnum order) throws APIException {
        return null;
    }

    @Override
    public List<AssetHistory> getAssetHistory(String asset) throws APIException {
        return getAssetHistory(asset, OrderEnum.asc);
    }

    @Override
    public List<AssetTransaction> getAssetTransactions(String asset, int count, int page, OrderEnum order) throws APIException {

        validateAsset(asset);

        Call<List<AssetTransaction>> assetTransactionCall = assetsApi.assetsAssetTransactionsGet(getProjectId(), asset, count, page, order.name());

        try{
            Response<List<AssetTransaction>> assetTransactionResponse = assetTransactionCall.execute();
            return processResponse(assetTransactionResponse);
        } catch (IOException exp){
            throw new APIException("Exception while fetching transactions for asset: " + asset, exp);
        }
    }

    @Override
    public List<AssetTransaction> getAssetTransactions(String asset, int count, int page) throws APIException {
        return getAssetTransactions(asset, count, page, OrderEnum.asc);
    }

    //TODO: Implement
    @Override
    public List<AssetTransaction> getAssetTransactions(String asset, OrderEnum order) throws APIException {
        return null;
    }

    @Override
    public List<AssetTransaction> getAssetTransactions(String asset) throws APIException {
        return getAssetTransactions(asset, OrderEnum.asc);
    }
}
