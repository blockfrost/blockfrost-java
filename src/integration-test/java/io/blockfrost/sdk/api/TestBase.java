package io.blockfrost.sdk.api;

public class TestBase {

    public String projectId;

    public TestBase() {
        projectId = System.getProperty("BF_PROJECT_ID");
        if (projectId == null || projectId.isEmpty()) {
            projectId = System.getenv("BF_PROJECT_ID");
        }
    }

}
