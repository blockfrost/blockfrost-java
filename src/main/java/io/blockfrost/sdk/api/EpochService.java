package io.blockfrost.sdk.api;

import io.blockfrost.sdk.api.exception.APIException;
import io.blockfrost.sdk.api.model.Epoch;
import io.blockfrost.sdk.api.model.EpochParam;

public interface EpochService {

    Epoch getLatestEpoch() throws APIException;

    EpochParam getLatestEpochParam() throws APIException;

    Epoch getEpoch(int epochNumber) throws APIException;

}
