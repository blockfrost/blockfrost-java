package io.blockfrost.sdk.impl.retrofit;

import io.blockfrost.sdk.api.model.IPFSObject;
import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

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
}
