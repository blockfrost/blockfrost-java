package io.blockfrost.sdk.api;

import io.blockfrost.sdk.api.exception.APIException;
import io.blockfrost.sdk.api.model.Genesis;

public interface LedgerService {

    Genesis getGenesis() throws APIException;

}
