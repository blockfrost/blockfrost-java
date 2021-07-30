package io.blockfrost.sdk.api;

import io.blockfrost.sdk.api.exception.APIException;
import io.blockfrost.sdk.api.model.Network;

public interface NetworkService {

    Network getNetwork() throws APIException;

}
