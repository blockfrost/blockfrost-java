package io.blockfrost.sdk.api;

public class TestBase {

    public static String projectId;
    public static String ipfsProjectId;

    static {
        projectId = System.getProperty("BF_PROJECT_ID");
        if (projectId == null || projectId.isEmpty()) {
            projectId = System.getenv("BF_PROJECT_ID");
        }

        ipfsProjectId = System.getProperty("BF_IPFS_PROJECT_ID");
        if (ipfsProjectId == null || ipfsProjectId.isEmpty()) {
            ipfsProjectId = System.getenv("BF_IPFS_PROJECT_ID");
        }
    }

    public TestBase() {

    }

}
