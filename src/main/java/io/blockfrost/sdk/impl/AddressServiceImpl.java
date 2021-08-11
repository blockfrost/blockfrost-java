package io.blockfrost.sdk.impl;

import io.blockfrost.sdk.api.AddressService;
import io.blockfrost.sdk.api.exception.APIException;
import io.blockfrost.sdk.api.model.Address;
import io.blockfrost.sdk.api.model.AddressTotal;
import io.blockfrost.sdk.impl.retrofit.AddressesApi;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;

public class AddressServiceImpl extends BaseImpl implements AddressService {

    AddressesApi addressesApi;

    public AddressServiceImpl(String baseUrl, String projectId){
        super(baseUrl, projectId);
        addressesApi = getRetrofit().create(AddressesApi.class);
    }


    @Override
    public Address getAddress(String address) throws APIException {
        Call<Address> addressCall = addressesApi.addressesAddressGet(getProjectId(), address);

        try{
            Response<Address> addressResponse = addressCall.execute();
            return processResponse(addressResponse);
        } catch (IOException exp){
            throw new APIException("Exception while fetching address data for address: " + address, exp);
        }
    }

    @Override
    public AddressTotal getAddressDetails(String address) throws APIException {
        Call<AddressTotal> addressTotalCall = addressesApi.addressesAddressTotalGet(getProjectId(), address);

        try{
            Response<AddressTotal> addressTotalResponse = addressTotalCall.execute();
            return processResponse(addressTotalResponse);
        } catch (IOException exp){
            throw new APIException("Exception while fetching address details for address: " + address, exp);
        }
    }
}
