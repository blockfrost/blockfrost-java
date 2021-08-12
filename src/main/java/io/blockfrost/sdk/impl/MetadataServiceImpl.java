package io.blockfrost.sdk.impl;

import io.blockfrost.sdk.api.MetadataService;
import io.blockfrost.sdk.api.exception.APIException;
import io.blockfrost.sdk.api.model.TransactionMetadataLabel;
import io.blockfrost.sdk.api.model.TransactionMetadataLabelCbor;
import io.blockfrost.sdk.api.model.TransactionMetadataLabelJson;
import io.blockfrost.sdk.api.util.OrderEnum;
import io.blockfrost.sdk.impl.helper.ValidationHelper;
import io.blockfrost.sdk.impl.retrofit.MetadataApi;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;
import java.util.List;

public class MetadataServiceImpl extends BaseImpl implements MetadataService {

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

    //TODO: Implement
    @Override
    public List<TransactionMetadataLabel> getTransactionMetadataLabels(OrderEnum order) throws APIException {
        return null;
    }

    @Override
    public List<TransactionMetadataLabel> getTransactionMetadataLabels() throws APIException {
        return getTransactionMetadataLabels(OrderEnum.asc);
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

    //TODO: Implement
    @Override
    public List<TransactionMetadataLabelCbor> getTransactionMetadataCborForLabel(String label, OrderEnum order) throws APIException {
        validateLabel(label);
        return null;
    }

    @Override
    public List<TransactionMetadataLabelCbor> getTransactionMetadataCborForLabel(String label) throws APIException {
        return getTransactionMetadataCborForLabel(label, OrderEnum.asc);
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

    //TODO: Implement
    @Override
    public List<TransactionMetadataLabelJson> getTransactionMetadataJsonForLabel(String label, OrderEnum order) throws APIException {
        validateLabel(label);
        return null;
    }

    @Override
    public List<TransactionMetadataLabelJson> getTransactionMetadataJsonForLabel(String label) throws APIException {
        return getTransactionMetadataJsonForLabel(label, OrderEnum.asc);
    }
}
