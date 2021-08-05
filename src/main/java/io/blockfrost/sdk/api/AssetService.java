package io.blockfrost.sdk.api;

import io.blockfrost.sdk.api.exception.APIException;
import io.blockfrost.sdk.api.model.Asset;

public interface AssetService {

    Asset getAsset(String asset) throws APIException;

}
