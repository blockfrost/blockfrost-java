package io.blockfrost.sdk.impl.retrofit;

import io.blockfrost.sdk.api.model.IPFSObject;
import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.*;

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
}
