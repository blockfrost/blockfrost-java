package io.blockfrost.sdk.impl.retrofit;

import io.blockfrost.sdk.api.model.Network;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;

public interface NetworkApi {
    /**
     * Blockchain network
     * Return the information about network.
     *
     * @return Call&lt;Network&gt;
     */
    @GET("network")
    Call<Network> networkGet(@Header("project_id") String projectId);

}
