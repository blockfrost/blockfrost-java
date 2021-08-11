package io.blockfrost.sdk.api;

import io.blockfrost.sdk.api.exception.APIException;
import io.blockfrost.sdk.api.model.Address;
import io.blockfrost.sdk.api.model.AddressTotal;

public interface AddressService {

    /**
     * Specific address
     * Obtain information about a specific address.
     *
     * @param address Bech32 address. (required)
     * @return Address
     */
    Address getAddress(String address) throws APIException;

    /**
     * Address total
     * Obtain details about an address.
     *
     * @param address Bech32 address. (required)
     * @return AddressTotal
     */
    AddressTotal getAddressDetails(String address) throws APIException;

}
