package io.blockfrost.sdk.impl.retrofit;

import io.blockfrost.sdk.api.model.Clock;
import io.blockfrost.sdk.api.model.Health;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;

public interface HealthApi {
    /**
     * Current backend time
     * This endpoint provides the current UNIX time. Your application might use this to verify if the client clock is not out of sync.
     *
     * @return Call&lt;Clock&gt;
     */
    @GET("health/clock")
    Call<Clock> healthClockGet(@Header("project_id") String projectId);


    /**
     * Backend health status
     * Return backend status as a boolean. Your application  should handle situations when backend for the given chain is unavailable.
     *
     * @return Call&lt;Health&gt;
     */
    @GET("health")
    Call<Health> healthGet(@Header("project_id") String projectId);


    /**
     * Root endpoint
     * Root endpoint has no other function than to point end users to documentation.
     *
     * @return Call&lt;ApiRoot&gt;
     */
    @GET("/")
    Call<ResponseBody> rootGet(@Header("project_id") String projectId);


}
