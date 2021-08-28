package io.blockfrost.sdk.api;

import io.blockfrost.sdk.api.exception.APIException;
import io.blockfrost.sdk.api.model.ipfs.IPFSObject;
import io.blockfrost.sdk.api.model.ipfs.PinItem;
import io.blockfrost.sdk.api.model.ipfs.PinResponse;
import io.blockfrost.sdk.api.util.OrderEnum;

import java.io.File;
import java.io.IOException;
import java.util.List;

public interface IPFSService {

    /**
     * Add a file to IPFS
     * You need to /ipfs/pin/add an object to avoid it being garbage collected. This usage is being counted in your user account quota.
     * @param file
     * @return IPFSObject
     */
    public IPFSObject add(File file) throws APIException, IOException;

    /**
     * Relay to an IPFS gateway
     * Retrieve an object from the IFPS gateway (useful if you do not want to rely on a public gateway, such as &#x60;ipfs.blockfrost.dev&#x60;).
     * @param ipfsPath
     * @return Content as byte[]
     * @throws APIException
     */
    public byte[] get(String ipfsPath) throws APIException;

    /**
     * Pin an object
     * Pinned objects are counted in your user storage quota.
     * @param ipfsPath  (required)
     * @return PinResponse
     */
    public PinResponse pinAdd(String ipfsPath) throws APIException;

    /**
     * List objects pinned to local storage
     * @param count The number of results displayed on one page.
     * @param page The page number for listing the results.
     * @param order The ordering of items from the point of view of the blockchain, not the page listing itself. By default, we return oldest first, newest last.
     * @return List&lt;PinItem&gt;
     */
    public List<PinItem> getPinnedObjects(int count, int page, OrderEnum order) throws APIException;

    /**
     * List objects pinned to local storage with default order asc
     * @return List&lt;PinItem&gt;
     */
    public List<PinItem> getAllPinnedObjects() throws APIException;

    /**
     * List objects pinned to local storage
     * @param order
     * @return List&lt;PinItem&gt;
     */
    public List<PinItem> getAllPinnedObjects(OrderEnum order) throws APIException;

    /**
     *
     * List objects pinned to local storage
     * @param ipfsPath
     * @return PinItem
     */
    public PinItem getPinnedObjectByIpfsPath(String ipfsPath) throws APIException;

    /**
     *
     * Remove pinned objects from local storage
     * @param ipfsPath
     * @return PinItem
     */
    public PinItem removePinnedObject(String ipfsPath) throws APIException;
}
