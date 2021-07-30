package io.blockfrost.sdk.api;

import io.blockfrost.sdk.api.exception.APIException;
import io.blockfrost.sdk.api.model.TransactionMetadataLabel;
import io.blockfrost.sdk.api.util.OrderEnum;

import java.util.List;

public interface MetadataService {

    /**
     * Transaction metadata labels
     * List of all used transaction metadata labels.
     * @param count The number of results displayed on one page.
     * @param page The page number for listing the results.
     * @param order The ordering of items from the point of view of the blockchain, not the page listing itself. By default, we return oldest first, newest last.  (optional, default to asc)
     * @return List&lt;TransactionMetadataLabel&gt;
     */
    List<TransactionMetadataLabel> getTransactionMetadataLabels(int count, int page, OrderEnum order) throws APIException;

    /**
     * Transaction metadata labels
     * List of all used transaction metadata labels ordered from the point of view of the blockchain, not the page listing itself. By default, we return oldest first, newest last.
     * @param count The numbers of pools per page (&lt;=100).
     * @param page The page number for listing the results.
     * @return List&lt;TransactionMetadataLabel&gt;
     */
    List<TransactionMetadataLabel> getTransactionMetadataLabels(int count, int page) throws APIException;


    /**
     * Transaction metadata labels
     * List of all used transaction metadata labels.
     * @param order The ordering of items from the point of view of the blockchain.
     * @return List&lt;TransactionMetadataLabel&gt;
     */
    //TODO: Implement
    List<TransactionMetadataLabel> getTransactionMetadataLabels(OrderEnum order) throws APIException;

    /**
     * Transaction metadata labels
     * List of all used transaction metadata labels in ascending order from the point of view of the blockchain. We return oldest first, newest last
     * @param poolId Bech32 or hexadecimal pool ID. (required)
     * @return List&lt;TransactionMetadataLabel&gt;
     */
    List<TransactionMetadataLabel> getTransactionMetadataLabels(String poolId) throws APIException;

}
