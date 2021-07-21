package io.blockfrost.sdk.api;

import io.blockfrost.sdk.api.exception.APIException;
import io.blockfrost.sdk.impl.TransactionsImpl;
import io.blockfrost.sdk.impl.common.Constants;
import io.blockfrost.sdk.impl.model.Transaction;
import io.blockfrost.sdk.impl.model.TransactionOutputAmount;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class TransactionsTests extends TestBase {

    Transactions transactions;

    @BeforeEach
    public void setup(){
        transactions = new TransactionsImpl(Constants.BLOCKFROST_TESTNET_URL, projectId);
    }

    @Test
    public void transaction_willReturn_transactionForAHash() throws APIException {

        Transaction expectedTransaction = Transaction.builder()
                .hash("0089e962584516378463141d853f7102852e7f2a8546c2a2af6cc05c6e628a5b")
                .block("dc6258ab8b0cbbe04343f31c71062058fcb9bc9f7ae47085d43a39ee4688a878")
                .blockHeight(2750303)
                .slot(31814522)
                .index(3)
                .outputAmount(Arrays.asList(
                        TransactionOutputAmount
                                .builder()
                                .unit("lovelace")
                                .quantity("9605414089839")
                                .build()
                ))
                .fees("168801")
                .deposit("0")
                .size(293)
                .invalidBefore(null)
                .invalidHereafter("31821502")
                .utxoCount(3)
                .withdrawalCount(0)
                .mirCertCount(0)
                .delegationCount(0)
                .stakeCertCount(0)
                .poolRetireCount(0)
                .poolUpdateCount(0)
                .assetMintOrBurnCount(0)
                .build();

        String transactionHash = "0089e962584516378463141d853f7102852e7f2a8546c2a2af6cc05c6e628a5b";
        Transaction transactionResponse = transactions.getTransaction(transactionHash);
        assertThat(transactionResponse, is(expectedTransaction));
    }

}
