package io.blockfrost.sdk.impl.util;

public class BuildVersion {
    private final static String SDK_NAME = "blockfrost-java";

    public static String getBuildName() {
        return SDK_NAME + "_" + BuildVersion.class.getPackage().getImplementationVersion();
    }

    public static void main(String[] args) {
        System.out.println(BuildVersion.getBuildName());
    }
}
