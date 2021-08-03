package io.blockfrost.sdk.impl.retrofit;

import io.blockfrost.sdk.api.model.Genesis;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;

public interface LedgerApi {
    /**
     * Blockchain genesis
     * Return the information about blockchain genesis.
     *
     * @return Call&lt;GenesisContent&gt;
     */
    @GET("genesis")
    Call<Genesis> genesisGet(@Header("project_id") String projectId);

}
