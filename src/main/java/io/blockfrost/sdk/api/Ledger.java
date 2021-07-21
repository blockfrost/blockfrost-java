package io.blockfrost.sdk.api;

import io.blockfrost.sdk.api.exception.APIException;
import io.blockfrost.sdk.impl.model.Genesis;

public interface Ledger {

    Genesis getGenesis() throws APIException;

}
