package io.blockfrost.sdk.api;

import io.blockfrost.sdk.api.exception.APIException;
import io.blockfrost.sdk.api.model.nutlink.NutLinkAddress;
import io.blockfrost.sdk.api.model.nutlink.Ticker;
import io.blockfrost.sdk.api.model.nutlink.TickerRecord;
import io.blockfrost.sdk.api.util.Constants;
import io.blockfrost.sdk.api.util.OrderEnum;
import io.blockfrost.sdk.impl.NutLinkServiceImpl;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.*;

public class NutLinkServiceTests extends TestBase {

    static NutLinkService nutLinkService;
    String address = "addr_test1qpktdfrey07xa2shqe8vjn6rl4mh4xmspccjw3mcvgu67xdk0tx7w22wsvj28pjnx4gygulgex4um9ke3hckwvk8tm9s66tglc";

    @BeforeAll
    public static void setup() {
        nutLinkService = new NutLinkServiceImpl(Constants.BLOCKFROST_TESTNET_URL, projectId);
    }

    @Test
    public void getNutLinkAddress_willReturn_metadataOfAddress() throws APIException {
        NutLinkAddress nutLinkAddress = nutLinkService.getNutLinkAddress(address);
        System.out.println(nutLinkAddress);

        assertEquals(address, nutLinkAddress.getAddress());
        assertEquals("https://cardanocanucks.com/oracle-metadata-testnet.canuk.json", nutLinkAddress.getMetadataUrl());
        assertNotNull(nutLinkAddress.getMetadataHash());
        assertEquals(nutLinkAddress.getMetadata().get("name"), "Cardano Canucks Oracles");
        assertEquals(nutLinkAddress.getMetadata().get("ticker"), "CANUK");
    }

    @Test
    public void getTickersByAddress_willReturn_tickersForAnAddress() throws APIException {
        List<Ticker> tickers = nutLinkService.getTickersByAddress(address, 5, 1, OrderEnum.asc);
        System.out.println(tickers);

        assertEquals(5, tickers.size());
        assertNotNull(tickers.get(0).getName());
        assertNotEquals(0, tickers.get(0).getCount(), "Ticker count cannot be 0");
        assertNotEquals(0, tickers.get(0).getCount(), "Ticker latestBlock cannot be 0");
    }

    @Test
    public void getTickersByAddress_willReturn_allTickers_whenOnlyAddress() throws APIException {
        List<Ticker> tickers = nutLinkService.getAllTickersByAddress(address, OrderEnum.desc);
        System.out.println(tickers);

        assertTrue(tickers.size() > 5, "Not all tickers are fetched");
        assertNotNull(tickers.get(0).getName());
        assertNotEquals(0, tickers.get(0).getCount(), "Ticker count cannot be 0");
        assertNotEquals(0, tickers.get(0).getCount(), "Ticker latestBlock cannot be 0");
    }

    @Test
    public void getTickerRecordsByAddressAndTicker_willReturn_tickerRecords() throws APIException {
        List<TickerRecord> tickerRecords = nutLinkService.getTickerRecordsByAddressAndTicker(address, "ADABTC", 5, 1, OrderEnum.desc);

        assertThat(tickerRecords, hasSize(greaterThanOrEqualTo(1)));
        assertNotNull(tickerRecords.get(0).getBlockHeight());
        assertNotNull(tickerRecords.get(0).getTxHash());
        assertNotNull(tickerRecords.get(0).getPayload());
    }

    @Test
    public void getAllTickerRecordsByAddressAndTicker_willReturn_tickerRecords() throws APIException {
        List<TickerRecord> tickerRecords = nutLinkService.getAllTickerRecordsByAddressAndTicker(address, "ADABTC");

        assertThat(tickerRecords, hasSize(greaterThanOrEqualTo(1)));
        assertNotNull(tickerRecords.get(0).getBlockHeight());
        assertNotNull(tickerRecords.get(0).getTxHash());
        assertNotNull(tickerRecords.get(0).getPayload());
    }

    @Test
    public void getTickerRecordsByTicker_willReturn_tickerRecords() throws APIException {
        List<TickerRecord> tickerRecords = nutLinkService.getTickerRecordsByTicker("ADABTC", 50, 2, OrderEnum.desc);

        assertThat(tickerRecords, hasSize(50));
        assertNotNull(tickerRecords.get(0).getBlockHeight());
        assertNotNull(tickerRecords.get(0).getTxHash());
        assertNotNull(tickerRecords.get(0).getPayload());
    }

    @Test
    @Disabled
    //TODO timeout too many records
    public void getAllTickerRecordsByTicker_willReturn_tickerRecords() throws APIException {
        List<TickerRecord> tickerRecords = nutLinkService.getAllTickerRecordsByTicker("ADABTC");

        assertThat(tickerRecords, hasSize(greaterThanOrEqualTo(1)));
        assertNotNull(tickerRecords.get(0).getBlockHeight());
        assertNotNull(tickerRecords.get(0).getTxHash());
        assertNotNull(tickerRecords.get(0).getPayload());
    }
}
