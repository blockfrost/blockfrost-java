package io.blockfrost.sdk.api;

import io.blockfrost.sdk.api.exception.APIException;
import io.blockfrost.sdk.api.model.Address;
import io.blockfrost.sdk.api.model.AddressTotal;
import io.blockfrost.sdk.api.model.AddressUtxo;
import io.blockfrost.sdk.api.util.OrderEnum;

import java.util.List;

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

    /**
     * Address UTXOs
     * UTXOs of the address.
     *
     * @param address Bech32 address. (required)
     * @param count   The number of results displayed on one page.
     * @param page    The page number for listing the results.
     * @param order   Ordered by tx index in the block. The ordering of items from the point of view of the blockchain, not the page listing itself. By default, we return oldest first, newest last.
     * @return List&lt;AddressUtxo&gt;
     */
    List<AddressUtxo> getAddressUtxos(String address, int count, int page, OrderEnum order) throws APIException;

    /**
     * Address UTXOs
     * UTXOs of the address ascending ordered by tx index in the block.
     *
     * @param address Bech32 address. (required)
     * @param count   The number of results displayed on one page.
     * @param page    The page number for listing the results.
     * @return List&lt;AddressUtxo&gt;
     */
    List<AddressUtxo> getAddressUtxos(String address, int count, int page) throws APIException;

    /**
     * Address UTXOs
     * All UTXOs of the address.
     *
     * @param address Bech32 address. (required)
     * @param order   Ordered by tx index in the block. The ordering of items from the point of view of the blockchain, not the page listing itself. By default, we return oldest first, newest last.     *
     * @return List&lt;AddressUtxo&gt;
     */
    List<AddressUtxo> getAddressUtxos(String address, OrderEnum order) throws APIException;

    /**
     * Address UTXOs
     * All UTXOs of the address ascending ordered by tx index in the block.
     *
     * @param address Bech32 address. (required)
     * @return List&lt;AddressUtxo&gt;
     */
    List<AddressUtxo> getAddressUtxos(String address) throws APIException;

}
