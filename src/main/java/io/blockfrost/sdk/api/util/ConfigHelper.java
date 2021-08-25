package io.blockfrost.sdk.api.util;

public class ConfigHelper {

    public static int threadCount(){

        String strThreadCount = System.getProperty("BF_API_MAX_THREADS");
        if (strThreadCount == null || strThreadCount.isEmpty()) {
            strThreadCount = System.getenv("BF_PROJECT_ID");
        }

        int threadCount = 10;

        try {
            threadCount = Integer.parseInt(strThreadCount);
        } catch (Exception exp){
            //Use the default value 10
        }
        return threadCount;

    }
}
