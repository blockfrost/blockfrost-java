package io.blockfrost.sdk.api;

import io.blockfrost.sdk.api.exception.APIException;
import io.blockfrost.sdk.api.model.nutlink.NutLinkAddress;
import io.blockfrost.sdk.api.model.nutlink.Ticker;
import io.blockfrost.sdk.api.model.nutlink.TickerRecord;
import io.blockfrost.sdk.api.util.OrderEnum;

import java.util.List;

public interface NutLinkService {

    /**
     * List metadata about specific address
     * @param address
     * @return NutLinkAddress
     * @throws APIException
     */
    public NutLinkAddress getNutLinkAddress(String address) throws APIException;

    /**
     *
     * List tickers for a specific metadata oracle
     * @param address
     * @param count The number of results displayed on one page.
     * @param page The page number for listing the results.
     * @param order The ordering of items from the point of view of the blockchain, not the page listing itself.
     * @return List&lt;Ticker&gt;
     * @throws APIException
     */
    public List<Ticker> getTickersByAddress(String address, int count, int page, OrderEnum order) throws APIException;

    /**
     *
     * List all tickers for a specific metadata oracle
     * @param address
     * @return List&lt;Ticker&gt;
     * @throws APIException
     */
    public List<Ticker> getAllTickersByAddress(String address) throws APIException;

    /**
     *
     * List all tickers for a specific metadata oracle
     * @param address
     * @param order
     * @return List&lt;Ticker&gt;
     * @throws APIException
     */
    public List<Ticker> getAllTickersByAddress(String address, OrderEnum order) throws APIException;

    /**
     * List of records of a specific ticker
     * @param address
     * @param ticker
     * @param count The number of results displayed on one page.
     * @param page The page number for listing the results.
     * @param order The ordering of items from the point of view of the blockchain, not the page listing itself. By default, we return oldest first, newest last.
     * @return
     */
    public List<TickerRecord> getTickerRecordsByAddressAndTicker(String address, String ticker, int count, int page, OrderEnum order) throws APIException;

    /**
     * List of all records of a specific ticker
     * @param address
     * @param ticker
     * @return
     */
    public List<TickerRecord> getAllTickerRecordsByAddressAndTicker(String address, String ticker) throws APIException;

    /**
     * List of all records of a specific ticker
     * @param address
     * @param ticker
     * @param order
     * @return
     */
    public List<TickerRecord> getAllTickerRecordsByAddressAndTicker(String address, String ticker, OrderEnum order) throws APIException;

    /**
     * List of records of a specific ticker
     * @param ticker
     * @param count The number of results displayed on one page.
     * @param page The page number for listing the results.
     * @param order The ordering of items from the point of view of the blockchain, not the page listing itself. By default, we return oldest first, newest last.
     * @return
     */
    public List<TickerRecord> getTickerRecordsByTicker(String ticker, int count, int page, OrderEnum order) throws APIException;

    /**
     * List of records of a specific ticker
     * @param ticker
     * @return
     */
    public List<TickerRecord> getAllTickerRecordsByTicker(String ticker) throws APIException;

    /**
     * List of records of a specific ticker
     * @param ticker
     * @param order
     * @return
     */
    public List<TickerRecord> getAllTickerRecordsByTicker(String ticker, OrderEnum order) throws APIException;

}
