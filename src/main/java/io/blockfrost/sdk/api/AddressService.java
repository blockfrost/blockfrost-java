package io.blockfrost.sdk.api;

import io.blockfrost.sdk.api.exception.APIException;
import io.blockfrost.sdk.api.model.Address;

public interface AddressService {

    Address getAddress(String address) throws APIException;

}
