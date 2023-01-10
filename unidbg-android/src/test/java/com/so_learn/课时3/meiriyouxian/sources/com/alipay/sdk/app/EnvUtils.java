package com.alipay.sdk.app;

public class EnvUtils {
    private static EnvEnum a = EnvEnum.ONLINE;

    public enum EnvEnum {
        ONLINE,
        SANDBOX
    }

    public static boolean a() {
        return a == EnvEnum.SANDBOX;
    }
}
