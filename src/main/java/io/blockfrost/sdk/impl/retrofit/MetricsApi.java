package io.blockfrost.sdk.impl.retrofit;

import io.blockfrost.sdk.impl.model.EndpointUsageMetric;
import io.blockfrost.sdk.impl.model.UsageMetric;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;

import java.util.List;

public interface MetricsApi {
  /**
   * Blockfrost endpoint usage metrics
   * History of your Blockfrost usage metrics per endpoint in the past 30 days. 
   * @return Call&lt;List&lt;EndpointUsageMetric&gt;&gt;
   */
  @GET("metrics/endpoints")
  Call<List<EndpointUsageMetric>> metricsEndpointsGet(@Header("project_id") String projectId);


  /**
   * Blockfrost usage metrics
   * History of your Blockfrost usage metrics in the past 30 days.
   * @return Call&lt;List&lt;UsageMetric&gt;&gt;
   */
  @GET("metrics/")
  Call<List<UsageMetric>> metricsGet(@Header("project_id") String projectId);
    

}
