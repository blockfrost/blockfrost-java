package io.blockfrost.sdk.api;

import io.blockfrost.sdk.api.exception.APIException;
import io.blockfrost.sdk.api.model.Clock;
import io.blockfrost.sdk.api.model.Health;

public interface HealthService {

    Health getHealth() throws APIException;

    Clock getCurrentBackendTime() throws APIException;

    String getApiRoot() throws APIException;

}
