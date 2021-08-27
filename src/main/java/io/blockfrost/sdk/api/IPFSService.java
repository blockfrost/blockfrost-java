package io.blockfrost.sdk.api;

import io.blockfrost.sdk.api.exception.APIException;
import io.blockfrost.sdk.api.model.IPFSObject;

import java.io.File;
import java.io.IOException;

public interface IPFSService {

    /**
     * Add a file to IPFS
     * You need to /ipfs/pin/add an object to avoid it being garbage collected. This usage is being counted in your user account quota.
     * @param file
     * @return IPFSObject
     */
    public IPFSObject add(File file) throws APIException, IOException;
}
