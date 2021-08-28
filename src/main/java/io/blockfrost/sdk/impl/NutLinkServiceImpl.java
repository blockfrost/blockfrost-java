package io.blockfrost.sdk.impl;

import io.blockfrost.sdk.api.NutLinkService;
import io.blockfrost.sdk.api.exception.APIException;
import io.blockfrost.sdk.api.exception.RuntimeAPIException;
import io.blockfrost.sdk.api.model.nutlink.NutLinkAddress;
import io.blockfrost.sdk.api.model.nutlink.Ticker;
import io.blockfrost.sdk.api.model.nutlink.TickerRecord;
import io.blockfrost.sdk.api.util.ConfigHelper;
import io.blockfrost.sdk.api.util.OrderEnum;
import io.blockfrost.sdk.impl.retrofit.NutLinkApi;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class NutLinkServiceImpl extends BaseService implements NutLinkService {

    NutLinkApi nutLinkApi;

    public NutLinkServiceImpl(String baseUrl, String projectId) {
        super(baseUrl, projectId);
        nutLinkApi = getRetrofit().create(NutLinkApi.class);
    }

    @Override
    public NutLinkAddress getNutLinkAddress(String address) throws APIException {
        Call<NutLinkAddress> call = nutLinkApi.addressGet(getProjectId(), address);

        try {
            Response<NutLinkAddress> response = call.execute();
            return processResponse(response);
        } catch (IOException exp) {
            throw new APIException("Exception while getting NutLink metadata about address : " + address, exp);
        }
    }

    @Override
    public List<Ticker> getTickersByAddress(String address, int count, int page, OrderEnum order) throws APIException {
        Call<List<Ticker>> call = nutLinkApi.addressTickersGet(getProjectId(), address, count, page, order.name());

        try {
            Response<List<Ticker>> response = call.execute();
            return processResponse(response);
        } catch (IOException exp) {
            throw new APIException("Exception while getting tickers for an address : " + address, exp);
        }
    }

    @Override
    public List<Ticker> getAllTickersByAddress(String address) throws APIException {
        return getAllTickersByAddress(address, OrderEnum.asc);
    }

    @Override
    public List<Ticker> getAllTickersByAddress(String address, OrderEnum order) throws APIException {

        List<Ticker> responseList = new ArrayList<>();
        boolean stopExecution = false;
        int currentPageCount = 1;
        int numThreads = ConfigHelper.INSTANCE.getThreadCount();

        while (!stopExecution) {

            List<CompletableFuture<List<Ticker>>> completableFutures = new ArrayList<>();

            for (int i = 0; i < numThreads; i++) {

                int finalCurrentPageCount = currentPageCount + i;

                completableFutures.add(CompletableFuture.supplyAsync(() -> {
                    try {
                        return getTickersByAddress(address, getDefaultFetchSize(), finalCurrentPageCount, order);
                    } catch (APIException e) {
                        throw new RuntimeAPIException(e);
                    }
                }));
            }

            try {
                stopExecution = fetchData(completableFutures, responseList);
            } catch (Exception e) {
                throw new APIException("Exception while getting all tickers at address : " + address, e);
            }

            currentPageCount += numThreads;
        }

        return responseList;
    }

    @Override
    public List<TickerRecord> getTickerRecordsByAddressAndTicker(String address, String ticker, int count, int page, OrderEnum order) throws APIException {
        Call<List<TickerRecord>> call = nutLinkApi.addressTickerRecords(getProjectId(), address, ticker, count, page, order.name());

        try {
            Response<List<TickerRecord>> response = call.execute();
            List<TickerRecord> tickerRecords = processResponse(response);
            if(tickerRecords != null)
                tickerRecords.stream().forEach(tickerRecord -> tickerRecord.setAddress(address));

            return tickerRecords;
        } catch (IOException exp) {
            throw new APIException("Exception while getting ticker records for the ticker : " + ticker, exp);
        }
    }

    @Override
    public List<TickerRecord> getAllTickerRecordsByAddressAndTicker(String address, String ticker) throws APIException {
        return getAllTickerRecordsByAddressAndTicker(address, ticker, OrderEnum.asc);
    }

    @Override
    public List<TickerRecord> getAllTickerRecordsByAddressAndTicker(String address, String ticker, OrderEnum order) throws APIException {

        List<TickerRecord> responseList = new ArrayList<>();
        boolean stopExecution = false;
        int currentPageCount = 1;
        int numThreads = ConfigHelper.INSTANCE.getThreadCount();

        while (!stopExecution) {

            List<CompletableFuture<List<TickerRecord>>> completableFutures = new ArrayList<>();

            for (int i = 0; i < numThreads; i++) {

                int finalCurrentPageCount = currentPageCount + i;

                completableFutures.add(CompletableFuture.supplyAsync(() -> {
                    try {
                        return getTickerRecordsByAddressAndTicker(address, ticker, getDefaultFetchSize(), finalCurrentPageCount, order);
                    } catch (APIException e) {
                        if(e.getErrorCode() == 404) { //TODO If no content in the page, it throws 404 error
                            return Collections.EMPTY_LIST;
                        } else {
                            throw new RuntimeAPIException(e);
                        }
                    }
                }));
            }

            try {
                stopExecution = fetchData(completableFutures, responseList);
            } catch (Exception e) {
                throw new APIException("Exception while getting all ticker records for the ticker : " + ticker, e);
            }

            currentPageCount += numThreads;
        }

        return responseList;
    }

    @Override
    public List<TickerRecord> getTickerRecordsByTicker(String ticker, int count, int page, OrderEnum order) throws APIException {
        Call<List<TickerRecord>> call = nutLinkApi.tickerRecords(getProjectId(), ticker, count, page, order.name());

        try {
            Response<List<TickerRecord>> response = call.execute();
            return processResponse(response);
        } catch (IOException exp) {
            throw new APIException("Exception while getting ticker records for the ticker : " + ticker, exp);
        }
    }

    @Override
    public List<TickerRecord> getAllTickerRecordsByTicker(String ticker) throws APIException {
        return getAllTickerRecordsByTicker(ticker, OrderEnum.asc);
    }

    @Override
    public List<TickerRecord> getAllTickerRecordsByTicker(String ticker, OrderEnum order) throws APIException {
        List<TickerRecord> responseList = new ArrayList<>();
        boolean stopExecution = false;
        int currentPageCount = 1;
        int numThreads = ConfigHelper.INSTANCE.getThreadCount();

        while (!stopExecution) {

            List<CompletableFuture<List<TickerRecord>>> completableFutures = new ArrayList<>();

            for (int i = 0; i < numThreads; i++) {

                int finalCurrentPageCount = currentPageCount + i;

                completableFutures.add(CompletableFuture.supplyAsync(() -> {
                    try {
                        return getTickerRecordsByTicker(ticker, getDefaultFetchSize(), finalCurrentPageCount, order);
                    } catch (APIException e) {
                        if(e.getErrorCode() == 404) { //TODO If no content in the page, it throws 404 error
                            return Collections.EMPTY_LIST;
                        } else {
                            throw new RuntimeAPIException(e);
                        }
                    }
                }));
            }

            try {
                stopExecution = fetchData(completableFutures, responseList);
            } catch (Exception e) {
                throw new APIException("Exception while getting all ticker records for the ticker : " + ticker, e);
            }

            currentPageCount += numThreads;
        }

        return responseList;
    }
}
