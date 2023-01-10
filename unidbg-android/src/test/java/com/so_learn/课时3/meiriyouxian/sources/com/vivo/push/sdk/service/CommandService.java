package com.vivo.push.sdk.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import com.vivo.push.sdk.a;
import com.vivo.push.util.ContextDelegate;
import com.vivo.push.util.n;

public class CommandService extends Service {
    @Override // android.app.Service
    public void onCreate() {
        AppMethodBeat.i(954, false);
        n.c("CommandService", getClass().getSimpleName() + " -- oncreate " + getPackageName());
        super.onCreate();
        a.a().a(ContextDelegate.getContext(getApplicationContext()));
        AppMethodBeat.o(954);
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int i, int i2) {
        AppMethodBeat.i(957, false);
        n.c("CommandService", getClass().getSimpleName() + " -- onStartCommand " + getPackageName());
        if (intent == null) {
            AppMethodBeat.o(957);
            return 2;
        } else if (!a(intent.getAction())) {
            n.a("CommandService", getPackageName() + " receive invalid action " + intent.getAction());
            AppMethodBeat.o(957);
            return 2;
        } else {
            try {
                a.a().a(intent);
            } catch (Exception e) {
                n.a("CommandService", "onStartCommand -- error", e);
            }
            AppMethodBeat.o(957);
            return 2;
        }
    }

    /* access modifiers changed from: protected */
    public boolean a(String str) {
        AppMethodBeat.i(958, false);
        boolean equals = "com.vivo.pushservice.action.RECEIVE".equals(str);
        AppMethodBeat.o(958);
        return equals;
    }

    @Override // android.app.Service
    public void onDestroy() {
        AppMethodBeat.i(960, false);
        super.onDestroy();
        n.c("CommandService", getClass().getSimpleName() + " -- onDestroy " + getPackageName());
        AppMethodBeat.o(960);
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        AppMethodBeat.i(964, false);
        n.c("CommandService", "onBind initSuc: ");
        AppMethodBeat.o(964);
        return null;
    }

    @Override // android.app.Service
    public boolean onUnbind(Intent intent) {
        AppMethodBeat.i(968, false);
        n.c("CommandService", "onUnbind");
        boolean onUnbind = super.onUnbind(intent);
        AppMethodBeat.o(968);
        return onUnbind;
    }
}
