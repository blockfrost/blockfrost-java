package io.blockfrost.sdk.api;

import io.blockfrost.sdk.api.exception.APIException;
import io.blockfrost.sdk.impl.model.Clock;
import io.blockfrost.sdk.impl.model.HealthStatus;

public interface Health {

    HealthStatus getHealth() throws APIException;

    Clock getCurrentBackendTime() throws APIException;

    String getApiRoot() throws APIException;

}
