package com.sina.weibo.sdk.statistic;

import java.util.Map;

/* compiled from: EventLog */
/* access modifiers changed from: package-private */
public class a extends e {
    private String d;
    private Map<String, String> e;

    public a(String str, String str2, Map<String, String> map) {
        super(str);
        this.d = str2;
        this.e = map;
    }

    public String a() {
        return this.d;
    }

    public Map<String, String> b() {
        return this.e;
    }
}
