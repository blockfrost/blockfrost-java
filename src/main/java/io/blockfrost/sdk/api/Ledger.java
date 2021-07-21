package io.blockfrost.sdk.api;

import io.blockfrost.sdk.api.exception.BlockfrostAPIException;
import io.blockfrost.sdk.impl.model.Genesis;

public interface Ledger {

    Genesis getGenesis() throws BlockfrostAPIException;

}
