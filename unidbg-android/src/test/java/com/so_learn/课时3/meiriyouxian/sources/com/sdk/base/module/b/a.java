package com.sdk.base.module.b;

import android.content.Context;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import com.sdk.base.framework.a.a.c;
import com.sdk.base.framework.c.e;
import com.sdk.base.framework.c.f;
import com.sdk.base.framework.c.g;
import com.sdk.base.module.manager.SDKManager;

public class a<T> extends com.sdk.base.framework.d.a<T> {
    public a(Context context, com.sdk.base.framework.b.a<T> aVar, e eVar) {
        super(context, aVar, eVar);
        AppMethodBeat.i(20792, false);
        if (f.c) {
            String testHost = SDKManager.getTestHost();
            if (!c.b(SDKManager.getStatisticalTestHost()).booleanValue()) {
                g.a.a();
            }
            if (c.b(testHost).booleanValue()) {
                this.b = testHost;
            } else {
                this.b = g.b.a();
                AppMethodBeat.o(20792);
                return;
            }
        } else {
            this.b = g.b.a();
            g.a.a();
        }
        AppMethodBeat.o(20792);
    }
}
