package io.blockfrost.sdk.impl;

import io.blockfrost.sdk.api.MetadataService;
import io.blockfrost.sdk.api.exception.APIException;
import io.blockfrost.sdk.api.model.TransactionMetadataLabel;
import io.blockfrost.sdk.api.util.OrderEnum;
import io.blockfrost.sdk.impl.helper.ValidationHelper;
import io.blockfrost.sdk.impl.retrofit.MetadataApi;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;
import java.util.List;

public class MetadataServiceImpl extends BaseImpl implements MetadataService {

    MetadataApi metadataApi;

    public MetadataServiceImpl(String baseUrl, String projectId){
        super(baseUrl, projectId);
        metadataApi = getRetrofit().create(MetadataApi.class);
    }


    @Override
    public List<TransactionMetadataLabel> getTransactionMetadataLabels(int count, int page, OrderEnum order) throws APIException {

        ValidationHelper.validateCount(count);

        Call<List<TransactionMetadataLabel>> metadataLabelCall = metadataApi.metadataTxsLabelsGet(getProjectId(), count, page, order.name());

        try{
            Response<List<TransactionMetadataLabel>> metadataLabelsResponse = metadataLabelCall.execute();
            return processResponse(metadataLabelsResponse);
        } catch (IOException exp){
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
    public List<TransactionMetadataLabel> getTransactionMetadataLabels(String poolId) throws APIException {
        return getTransactionMetadataLabels(OrderEnum.asc);
    }
}
