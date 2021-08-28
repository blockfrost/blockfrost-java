package io.blockfrost.sdk.impl;

import io.blockfrost.sdk.api.MetadataService;
import io.blockfrost.sdk.api.exception.APIException;
import io.blockfrost.sdk.api.exception.RuntimeAPIException;
import io.blockfrost.sdk.api.model.TransactionMetadataLabel;
import io.blockfrost.sdk.api.model.TransactionMetadataLabelCbor;
import io.blockfrost.sdk.api.model.TransactionMetadataLabelJson;
import io.blockfrost.sdk.api.util.ConfigHelper;
import io.blockfrost.sdk.api.util.OrderEnum;
import io.blockfrost.sdk.impl.helper.ValidationHelper;
import io.blockfrost.sdk.impl.retrofit.MetadataApi;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class MetadataServiceImpl extends BaseService implements MetadataService {

    MetadataApi metadataApi;

    public MetadataServiceImpl(String baseUrl, String projectId) {
        super(baseUrl, projectId);
        metadataApi = getRetrofit().create(MetadataApi.class);
    }

    private void validateLabel(String label) throws APIException {
        if (label == null || label.equals("")) {
            throw new APIException("Label cannot be null or empty");
        }
    }

    @Override
    public List<TransactionMetadataLabel> getTransactionMetadataLabels(int count, int page, OrderEnum order) throws APIException {

        ValidationHelper.validateCount(count);

        Call<List<TransactionMetadataLabel>> metadataLabelCall = metadataApi.metadataTxsLabelsGet(getProjectId(), count, page, order.name());

        try {
            Response<List<TransactionMetadataLabel>> metadataLabelsResponse = metadataLabelCall.execute();
            return processResponse(metadataLabelsResponse);
        } catch (IOException exp) {
            throw new APIException("Exception while fetching metadata labels", exp);
        }

    }

    @Override
    public List<TransactionMetadataLabel> getTransactionMetadataLabels(int count, int page) throws APIException {
        return getTransactionMetadataLabels(count, page, OrderEnum.asc);
    }

    @Override
    public List<TransactionMetadataLabel> getAllTransactionMetadataLabels(OrderEnum order) throws APIException {

        List<TransactionMetadataLabel> responseList = new ArrayList<>();
        boolean stopExecution = false;
        int currentPageCount = 1;
        int numThreads = ConfigHelper.INSTANCE.getThreadCount();

        while (!stopExecution) {

            List<CompletableFuture<List<TransactionMetadataLabel>>> completableFutures = new ArrayList<>();

            for (int i = 0; i < numThreads; i++) {

                int finalCurrentPageCount = currentPageCount + i;

                completableFutures.add(CompletableFuture.supplyAsync(() -> {
                    try {
                        return getTransactionMetadataLabels(getDefaultFetchSize(), finalCurrentPageCount, order);
                    } catch (APIException e) {
                        throw new RuntimeAPIException(e);
                    }
                }));
            }

            try {
                stopExecution = fetchData(completableFutures, responseList);
            } catch (Exception e) {
                throw new APIException("Exception while fetching all transaction metadata labels", e);
            }

            currentPageCount += numThreads;
        }

        return responseList;

    }

    @Override
    public List<TransactionMetadataLabel> getAllTransactionMetadataLabels() throws APIException {
        return getAllTransactionMetadataLabels(OrderEnum.asc);
    }

    @Override
    public List<TransactionMetadataLabelCbor> getTransactionMetadataCborForLabel(String label, int count, int page, OrderEnum order) throws APIException {

        validateLabel(label);

        ValidationHelper.validateCount(count);

        Call<List<TransactionMetadataLabelCbor>> metadataCborCall = metadataApi.metadataTxsLabelsLabelCborGet(getProjectId(), label, count, page, order.name());

        try {
            Response<List<TransactionMetadataLabelCbor>> metadataCborResponse = metadataCborCall.execute();
            return processResponse(metadataCborResponse);
        } catch (IOException exp) {
            throw new APIException("Exception while fetching transaction metadata in CBOR for label: " + label, exp);
        }
    }

    @Override
    public List<TransactionMetadataLabelCbor> getTransactionMetadataCborForLabel(String label, int count, int page) throws APIException {
        return getTransactionMetadataCborForLabel(label, count, page, OrderEnum.asc);
    }

    @Override
    public List<TransactionMetadataLabelCbor> getAllTransactionMetadataCborForLabel(String label, OrderEnum order) throws APIException {
        validateLabel(label);

        List<TransactionMetadataLabelCbor> responseList = new ArrayList<>();
        boolean stopExecution = false;
        int currentPageCount = 1;
        int numThreads = ConfigHelper.INSTANCE.getThreadCount();

        while (!stopExecution) {

            List<CompletableFuture<List<TransactionMetadataLabelCbor>>> completableFutures = new ArrayList<>();

            for (int i = 0; i < numThreads; i++) {

                int finalCurrentPageCount = currentPageCount + i;

                completableFutures.add(CompletableFuture.supplyAsync(() -> {
                    try {
                        return getTransactionMetadataCborForLabel(label, getDefaultFetchSize(), finalCurrentPageCount, order);
                    } catch (APIException e) {
                        throw new RuntimeAPIException(e);
                    }
                }));
            }

            try {
                stopExecution = fetchData(completableFutures, responseList);
            } catch (Exception e) {
                throw new APIException("Exception while fetching all metadata cbor for label: " + label, e);
            }

            currentPageCount += numThreads;
        }

        return responseList;

    }

    @Override
    public List<TransactionMetadataLabelCbor> getAllTransactionMetadataCborForLabel(String label) throws APIException {
        return getAllTransactionMetadataCborForLabel(label, OrderEnum.asc);
    }

    @Override
    public List<TransactionMetadataLabelJson> getTransactionMetadataJsonForLabel(String label, int count, int page, OrderEnum order) throws APIException {
        validateLabel(label);

        ValidationHelper.validateCount(count);

        Call<List<TransactionMetadataLabelJson>> metadataJsonCall = metadataApi.metadataTxsLabelsLabelGet(getProjectId(), label, count, page, order.name());

        try {
            Response<List<TransactionMetadataLabelJson>> metadataJsonResponse = metadataJsonCall.execute();
            return processResponse(metadataJsonResponse);
        } catch (IOException exp) {
            throw new APIException("Exception while fetching transaction metadata in JSON for label: " + label, exp);
        }
    }

    @Override
    public List<TransactionMetadataLabelJson> getTransactionMetadataJsonForLabel(String label, int count, int page) throws APIException {
        return getTransactionMetadataJsonForLabel(label, count, page, OrderEnum.asc);
    }

    @Override
    public List<TransactionMetadataLabelJson> getAllTransactionMetadataJsonForLabel(String label, OrderEnum order) throws APIException {
        validateLabel(label);

        List<TransactionMetadataLabelJson> responseList = new ArrayList<>();
        boolean stopExecution = false;
        int currentPageCount = 1;
        int numThreads = ConfigHelper.INSTANCE.getThreadCount();

        while (!stopExecution) {

            List<CompletableFuture<List<TransactionMetadataLabelJson>>> completableFutures = new ArrayList<>();

            for (int i = 0; i < numThreads; i++) {

                int finalCurrentPageCount = currentPageCount + i;

                completableFutures.add(CompletableFuture.supplyAsync(() -> {
                    try {
                        return getTransactionMetadataJsonForLabel(label, getDefaultFetchSize(), finalCurrentPageCount, order);
                    } catch (APIException e) {
                        throw new RuntimeAPIException(e);
                    }
                }));
            }

            try {
                stopExecution = fetchData(completableFutures, responseList);
            } catch (Exception e) {
                throw new APIException("Exception while fetching all transaction metadata json for label: " + label, e);
            }

            currentPageCount += numThreads;
        }

        return responseList;

    }

    @Override
    public List<TransactionMetadataLabelJson> getAllTransactionMetadataJsonForLabel(String label) throws APIException {
        return getAllTransactionMetadataJsonForLabel(label, OrderEnum.asc);
    }
}
