package io.blockfrost.sdk.api;

import io.blockfrost.sdk.api.exception.BlockfrostAPIException;
import io.blockfrost.sdk.impl.model.Clock;
import io.blockfrost.sdk.impl.model.HealthStatus;

public interface Health {

    HealthStatus getHealth() throws BlockfrostAPIException;

    Clock getCurrentBackendTime() throws BlockfrostAPIException;

    String getApiRoot() throws BlockfrostAPIException;

}
