package io.blockfrost.sdk.api.util;

import io.blockfrost.sdk.impl.util.BuildVersion;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class NetworkHelper {
    private OkHttpClient okHttpClient;

    // private constructor for singleton
    private NetworkHelper() throws RuntimeException {
        // Prevent form the reflection api.
        if(okHttpClient != null) {
            throw new RuntimeException(
                    "Must use getInstance() method to get the single instance of this class.");
        }
        else {
            buildOkHttpClient();
        }
    }

    public static NetworkHelper getInstance() {
        return NetworkHolder.NETWORK;
    }

    public OkHttpClient getOkHttpClient() {
        return okHttpClient;
    }


    private void buildOkHttpClient() {
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

        httpClient.addInterceptor(new Interceptor() {
            @Override
            public okhttp3.Response intercept(Chain chain) throws IOException {
                Request request = chain.request().newBuilder().addHeader("User-Agent", BuildVersion.getBuildName()).build();
                return chain.proceed(request);
            }
        });

        okHttpClient = httpClient
                .connectTimeout(ConfigHelper.INSTANCE.getConnectionTimeout(), TimeUnit.SECONDS)
                .readTimeout(ConfigHelper.INSTANCE.getConnectionTimeout(), TimeUnit.SECONDS)
                .writeTimeout(ConfigHelper.INSTANCE.getConnectionTimeout(), TimeUnit.SECONDS)
                .build();
    }


    private static class NetworkHolder {
        public static final NetworkHelper NETWORK = new NetworkHelper();
    }
}
