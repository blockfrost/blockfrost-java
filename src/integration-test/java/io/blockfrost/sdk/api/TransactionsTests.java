package io.blockfrost.sdk.api;

import io.blockfrost.sdk.api.exception.APIException;
import io.blockfrost.sdk.impl.TransactionsImpl;
import io.blockfrost.sdk.impl.common.Constants;
import io.blockfrost.sdk.impl.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
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

    @Test
    public void transactionUtxo_willReturn_transactionUtxoForAHash() throws APIException {

        TransactionUtxo expectedTransactionUtxo = TransactionUtxo.builder()
                .hash("0089e962584516378463141d853f7102852e7f2a8546c2a2af6cc05c6e628a5b")
                .inputs(Arrays.asList(
                        TransactionUtxoInputs.builder()
                                .address("addr_test1qqk2fygtqcusmx9djys2r7m4emslwy8hfjllrakd5777hzcxu2hyfhlkwuxupa9d5085eunq2qywy7hvmvej456flknstkhmyt")
                                .txHash("59b051e130d32fa2d316b0ee50934d10f1a8e9af332166812bd696a755727fec")
                                .outputIndex(new BigDecimal(1))
                                .amount(Arrays.asList(
                                        TransactionOutputAmount.builder()
                                                .unit("lovelace")
                                                .quantity("9605414258640")
                                                .build()
                                ))
                                .build()
                ))
                .outputs(Arrays.asList(
                        TransactionUtxoOutputs.builder()
                                .address("addr_test1qqy07jaue8tru20877ak7wxpuagrqqpm2pdacfjjtv4z3elcn8dnk52656jspgq03ts2sl6jvefwakdacwfy605m9ydselehdg")
                                .amount(Arrays.asList(
                                        TransactionOutputAmount.builder()
                                                .unit("lovelace")
                                                .quantity("1000000000")
                                                .build()
                                ))
                                .build(),
                        TransactionUtxoOutputs.builder()
                                .address("addr_test1qq4sa7kg02ms66nsx6d06q4h697p57ajla2eedfl0aysqrgxu2hyfhlkwuxupa9d5085eunq2qywy7hvmvej456flknsyznauy")
                                .amount(Arrays.asList(
                                        TransactionOutputAmount.builder()
                                                .unit("lovelace")
                                                .quantity("9604414089839")
                                                .build()
                                ))
                                .build()
                ))
                .build();

        String transactionHash = "0089e962584516378463141d853f7102852e7f2a8546c2a2af6cc05c6e628a5b";
        TransactionUtxo transactionUtxoResponse = transactions.getTransactionUtxo(transactionHash);
        assertThat(transactionUtxoResponse, is(expectedTransactionUtxo));
    }


    //TODO: Need to use a Transaction has with actual stake certificate for validation.
    @Test
    public void transactionStakes_willReturn_transactionStakesForAHash() throws APIException {

        String transactionHash = "0089e962584516378463141d853f7102852e7f2a8546c2a2af6cc05c6e628a5b";
        List<TransactionStake> transactionStakeResponse = transactions.getTransactionStakes(transactionHash);
        assertThat(transactionStakeResponse, hasSize(0));
    }

    //TODO: Need to use a Transaction has with actual Delegation certificate for validation.
    @Test
    public void transactionDelegations_willReturn_transactionDelegationsForAHash() throws APIException {

        String transactionHash = "0089e962584516378463141d853f7102852e7f2a8546c2a2af6cc05c6e628a5b";
        List<TransactionDelegation> transactionDelegationResponse = transactions.getTransactionDelegations(transactionHash);
        assertThat(transactionDelegationResponse, hasSize(0));
    }

    //TODO: Need to use a Transaction has with actual Withdrawal certificate for validation.
    @Test
    public void transactionWithdrawals_willReturn_transactionWithdrawalsForAHash() throws APIException {

        String transactionHash = "0089e962584516378463141d853f7102852e7f2a8546c2a2af6cc05c6e628a5b";
        List<TransactionWithdrawal> transactionWithdrawalResponse = transactions.getTransactionWithdrawals(transactionHash);
        assertThat(transactionWithdrawalResponse, hasSize(0));
    }

}
