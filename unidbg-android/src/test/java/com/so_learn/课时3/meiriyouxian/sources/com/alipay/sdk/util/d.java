package com.alipay.sdk.util;

public enum d {
    WIFI(0, "WIFI"),
    NETWORK_TYPE_1(1, "unicom2G"),
    NETWORK_TYPE_2(2, "mobile2G"),
    NETWORK_TYPE_4(4, "telecom2G"),
    NETWORK_TYPE_5(5, "telecom3G"),
    NETWORK_TYPE_6(6, "telecom3G"),
    NETWORK_TYPE_12(12, "telecom3G"),
    NETWORK_TYPE_8(8, "unicom3G"),
    NETWORK_TYPE_3(3, "unicom3G"),
    NETWORK_TYPE_13(13, "LTE"),
    NETWORK_TYPE_11(11, "IDEN"),
    NETWORK_TYPE_9(9, "HSUPA"),
    NETWORK_TYPE_10(10, "HSPA"),
    NETWORK_TYPE_15(15, "HSPAP"),
    NETWORK_TYPE_20(20, "5G"),
    NONE(-1, "none");
    
    private int q;
    private String r;

    private d(int i, String str) {
        this.q = i;
        this.r = str;
    }

    public final int a() {
        return this.q;
    }

    public final String b() {
        return this.r;
    }

    public static d a(int i) {
        d[] values = values();
        for (d dVar : values) {
            if (dVar.a() == i) {
                return dVar;
            }
        }
        return NONE;
    }
}
