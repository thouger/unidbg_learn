package com.sdk.base.module.config;

import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import com.sdk.base.framework.c.e;
import com.sdk.base.framework.f.c.a;

public class BaseConfig implements e {
    public static String apk = "com.cucc.sdk.api_key";
    public static int c = 37;

    /* renamed from: cm  reason: collision with root package name */
    public static String f1337cm = "CUCC";
    public static String n = "SDKFactory";
    public static String v = "\u5b89\u53533.9.1.5\u4e13\u4e1a\u7248Z200417";
    long r = System.currentTimeMillis();

    public BaseConfig() {
        AppMethodBeat.i(20790, false);
        AppMethodBeat.o(20790);
    }

    public String getApiKey() {
        return apk;
    }

    public String getCM() {
        return f1337cm;
    }

    public String toJsonString() {
        AppMethodBeat.i(20791, false);
        String a = a.a(this);
        AppMethodBeat.o(20791);
        return a;
    }
}
