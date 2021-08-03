package io.blockfrost.sdk.impl.retrofit;

import io.blockfrost.sdk.api.model.Account;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;

public interface AccountsApi {

    /**
     * Specific account address
     * Obtain information about a specific stake account.
     *
     * @param stakeAddress Bech32 stake address. (required)
     * @return Call&lt;Account&gt;
     */
    @GET("accounts/{stake_address}")
    Call<Account> accountsStakeAddressGet(@Header("project_id") String projectId, @Path("stake_address") String stakeAddress);

}
