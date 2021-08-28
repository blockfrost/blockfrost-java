package io.blockfrost.sdk.impl;

import io.blockfrost.sdk.api.AssetService;
import io.blockfrost.sdk.api.exception.APIException;
import io.blockfrost.sdk.api.exception.RuntimeAPIException;
import io.blockfrost.sdk.api.model.*;
import io.blockfrost.sdk.api.util.ConfigHelper;
import io.blockfrost.sdk.api.util.OrderEnum;
import io.blockfrost.sdk.impl.retrofit.AssetsApi;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class AssetServiceImpl extends BaseService implements AssetService {

    AssetsApi assetsApi;

    public AssetServiceImpl(String baseUrl, String projectId) {
        super(baseUrl, projectId);
        assetsApi = getRetrofit().create(AssetsApi.class);
    }

    private void validateAsset(String asset) throws APIException {
        if (asset == null || asset.equals("")) {
            throw new APIException("Asset cannot be null or empty");
        }
    }

    @Override
    public Asset getAsset(String asset) throws APIException {

        validateAsset(asset);

        Call<Asset> assetCall = assetsApi.assetsAssetGet(getProjectId(), asset);

        try {
            Response<Asset> assetResponse = assetCall.execute();
            return processResponse(assetResponse);
        } catch (IOException exp) {
            throw new APIException("Exception while fetching asset: " + asset, exp);
        }
    }

    @Override
    public List<Asset> getAssets(int count, int page, OrderEnum order) throws APIException {
        Call<List<Asset>> assetsCall = assetsApi.assetsGet(getProjectId(), count, page, order.name());

        try {
            Response<List<Asset>> assetsResponse = assetsCall.execute();
            return processResponse(assetsResponse);
        } catch (IOException exp) {
            throw new APIException("Exception while fetching assets", exp);
        }
    }

    @Override
    public List<Asset> getAssets(int count, int page) throws APIException {
        return getAssets(count, page, OrderEnum.asc);
    }

    @Override
    public List<AssetHistory> getAssetHistory(String asset, int count, int page, OrderEnum order) throws APIException {

        validateAsset(asset);

        Call<List<AssetHistory>> assetHistoryCall = assetsApi.assetsAssetHistoryGet(getProjectId(), asset, count, page, order.name());

        try {
            Response<List<AssetHistory>> assetHistoryResponse = assetHistoryCall.execute();
            return processResponse(assetHistoryResponse);
        } catch (IOException exp) {
            throw new APIException("Exception while fetching asset history for asset: " + asset, exp);
        }
    }

    @Override
    public List<AssetHistory> getAssetHistory(String asset, int count, int page) throws APIException {
        return getAssetHistory(asset, count, page, OrderEnum.asc);
    }

    @Override
    public List<AssetHistory> getAssetHistory(String asset, OrderEnum order) throws APIException {

        List<AssetHistory> responseList = new ArrayList<>();
        boolean stopExecution = false;
        int currentPageCount = 1;
        int numThreads = ConfigHelper.INSTANCE.getThreadCount();

        while (!stopExecution) {

            List<CompletableFuture<List<AssetHistory>>> completableFutures = new ArrayList<>();

            for (int i = 0; i < numThreads; i++) {

                int finalCurrentPageCount = currentPageCount + i;

                completableFutures.add(CompletableFuture.supplyAsync(() -> {
                    try {
                        return getAssetHistory(asset, getDefaultFetchSize(), finalCurrentPageCount, order);
                    } catch (APIException e) {
                        throw new RuntimeAPIException(e);
                    }
                }));
            }

            try {
                stopExecution = fetchData(completableFutures, responseList);
            } catch (Exception e) {
                throw new APIException("Exception while fetching entire asset history", e);
            }

            currentPageCount += numThreads;
        }

        return responseList;

    }

    @Override
    public List<AssetHistory> getAssetHistory(String asset) throws APIException {
        return getAssetHistory(asset, OrderEnum.asc);
    }

    @Override
    public List<AssetTransaction> getAssetTransactions(String asset, int count, int page, OrderEnum order) throws APIException {

        validateAsset(asset);

        Call<List<AssetTransaction>> assetTransactionCall = assetsApi.assetsAssetTransactionsGet(getProjectId(), asset, count, page, order.name());

        try {
            Response<List<AssetTransaction>> assetTransactionResponse = assetTransactionCall.execute();
            return processResponse(assetTransactionResponse);
        } catch (IOException exp) {
            throw new APIException("Exception while fetching transactions for asset: " + asset, exp);
        }
    }

    @Override
    public List<AssetTransaction> getAssetTransactions(String asset, int count, int page) throws APIException {
        return getAssetTransactions(asset, count, page, OrderEnum.asc);
    }

    @Override
    public List<AssetTransaction> getAssetTransactions(String asset, OrderEnum order) throws APIException {

        List<AssetTransaction> responseList = new ArrayList<>();
        boolean stopExecution = false;
        int currentPageCount = 1;
        int numThreads = ConfigHelper.INSTANCE.getThreadCount();

        while (!stopExecution) {

            List<CompletableFuture<List<AssetTransaction>>> completableFutures = new ArrayList<>();

            for (int i = 0; i < numThreads; i++) {

                int finalCurrentPageCount = currentPageCount + i;

                completableFutures.add(CompletableFuture.supplyAsync(() -> {
                    try {
                        return getAssetTransactions(asset, getDefaultFetchSize(), finalCurrentPageCount, order);
                    } catch (APIException e) {
                        throw new RuntimeAPIException(e);
                    }
                }));
            }

            try {
                stopExecution = fetchData(completableFutures, responseList);
            } catch (Exception e) {
                throw new APIException("Exception while fetching asset transactions", e);
            }

            currentPageCount += numThreads;
        }

        return responseList;

    }

    @Override
    public List<AssetTransaction> getAssetTransactions(String asset) throws APIException {
        return getAssetTransactions(asset, OrderEnum.asc);
    }

    @Override
    public List<AssetAddress> getAssetAddresses(String asset, int count, int page, OrderEnum order) throws APIException {
        validateAsset(asset);

        Call<List<AssetAddress>> assetAddressCall = assetsApi.assetsAssetAddressesGet(getProjectId(), asset, count, page, order.name());

        try {
            Response<List<AssetAddress>> assetAddressResponse = assetAddressCall.execute();
            return processResponse(assetAddressResponse);
        } catch (IOException exp) {
            throw new APIException("Exception while fetching addresses for asset: " + asset, exp);
        }
    }

    @Override
    public List<AssetAddress> getAssetAddresses(String asset, int count, int page) throws APIException {
        return getAssetAddresses(asset, count, page, OrderEnum.asc);
    }

    @Override
    public List<AssetAddress> getAssetAddresses(String asset, OrderEnum order) throws APIException {

        List<AssetAddress> responseList = new ArrayList<>();
        boolean stopExecution = false;
        int currentPageCount = 1;
        int numThreads = ConfigHelper.INSTANCE.getThreadCount();

        while (!stopExecution) {

            List<CompletableFuture<List<AssetAddress>>> completableFutures = new ArrayList<>();

            for (int i = 0; i < numThreads; i++) {

                int finalCurrentPageCount = currentPageCount + i;

                completableFutures.add(CompletableFuture.supplyAsync(() -> {
                    try {
                        return getAssetAddresses(asset, getDefaultFetchSize(), finalCurrentPageCount, order);
                    } catch (APIException e) {
                        throw new RuntimeAPIException(e);
                    }
                }));
            }

            try {
                stopExecution = fetchData(completableFutures, responseList);
            } catch (Exception e) {
                throw new APIException("Exception while fetching asset addresses", e);
            }

            currentPageCount += numThreads;
        }

        return responseList;

    }

    @Override
    public List<AssetAddress> getAssetAddresses(String asset) throws APIException {
        return getAssetAddresses(asset, OrderEnum.asc);
    }
}
