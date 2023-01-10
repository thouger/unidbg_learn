package com.sdk.mobile.config;

import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import com.sdk.base.framework.c.e;
import com.sdk.base.framework.f.c.a;
import com.sdk.base.module.config.BaseConfig;

public class MobileConfig implements e {
    String apk = BaseConfig.apk;
    int c = 1;

    /* renamed from: cm  reason: collision with root package name */
    String f1338cm = BaseConfig.f1337cm;
    String n = "ZzxOAuth";
    long r = System.currentTimeMillis();
    String v = "1.0";

    public MobileConfig() {
        AppMethodBeat.i(18450, false);
        AppMethodBeat.o(18450);
    }

    public String getApiKey() {
        return this.apk;
    }

    public String getCM() {
        return this.f1338cm;
    }

    public String toJsonString() {
        AppMethodBeat.i(18454, false);
        String a = a.a(this);
        AppMethodBeat.o(18454);
        return a;
    }
}
