package io.blockfrost.sdk.api;

import io.blockfrost.sdk.api.exception.BlockfrostAPIException;
import io.blockfrost.sdk.impl.model.Transaction;

public interface Transactions {

    Transaction getTransaction(String transactionHash) throws BlockfrostAPIException;
}
