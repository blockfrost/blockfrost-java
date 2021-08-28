package io.blockfrost.sdk.impl.retrofit;

import io.blockfrost.sdk.api.model.PinItem;
import io.blockfrost.sdk.api.model.PinResponse;
import io.blockfrost.sdk.api.model.IPFSObject;
import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.List;

public interface IPFSApi {

    /**
     * Add a file or directory to IPFS
     *
     * @return
     */
    @Multipart
    @POST("ipfs/add")
    Call<IPFSObject> add(
            @Header("project_id") String projectId,
            @Part MultipartBody.Part file
    );

    /**
     * Relay to an IPFS gateway
     * Retrieve an object from the IFPS gateway (useful if you do not want to rely on a public gateway, such as &#x60;ipfs.blockfrost.dev&#x60;).
     * @param ipFSPath  (required)
     * @return Call&lt;ResponseBody&gt;
     */
    @GET("ipfs/gateway/{IPFS_path}")
    Call<ResponseBody> get(
            @Header("project_id") String projectId,
            @Path("IPFS_path") String ipFSPath
    );

    /**
     * Pin an object
     * Pinned objects are counted in your user storage quota.
     * @param ipFSPath  (required)
     * @return Call&lt;IPFSAddPinResponse&gt;
     */
    @POST("ipfs/pin/add/{IPFS_path}")
    Call<PinResponse> pinAdd(
            @Header("project_id") String projectId,
            @Path("IPFS_path") String ipFSPath
    );

    /**
     *
     * List objects pinned to local storage
     * @param count The number of results displayed on one page. (optional, default to 100)
     * @param page The page number for listing the results. (optional, default to 1)
     * @param order The ordering of items from the point of view of the blockchain, not the page listing itself. By default, we return oldest first, newest last.  (optional, default to asc)
     * @return Call&lt;List&lt;PinItem&gt;&gt;
     */
    @GET("ipfs/pin/list/")
    Call<List<PinItem>> pinList(
            @Header("project_id") String projectId,
            @Query("count") Integer count, @Query("page") Integer page, @Query("order") String order
    );


    /**
     *
     * List objects pinned to local storage
     * @param ipFSPath  (required)
     * @return Call&lt;PinItem&gt;
     */
    @GET("ipfs/pin/list/{IPFS_path}")
    Call<PinItem> pinListByIpfsPath(
            @Header("project_id") String projectId,
            @Path("IPFS_path") String ipFSPath
    );

    /**
     *
     * Remove pinned objects from local storage
     * @param ipFSPath  (required)
     * @return Call&lt;PinItem&gt;
     */
    @POST("ipfs/pin/remove/{IPFS_path}")
    Call<PinItem> pinRemove(
            @Header("project_id") String projectId,
            @Path("IPFS_path") String ipFSPath
    );
}
