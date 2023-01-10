package com.vivo.push.model;

import android.text.TextUtils;
import android.view.WindowManager;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;

/* compiled from: PushPackageInfo */
public final class b {
    public String a;
    public long b = -1;
    public int c = -1;
    public String d;
    public boolean e = false;
    public boolean f = false;

    public b(String str) {
        AppMethodBeat.i(WindowManager.LayoutParams.TYPE_POINTER, false);
        if (!TextUtils.isEmpty(str)) {
            this.a = str;
            AppMethodBeat.o(WindowManager.LayoutParams.TYPE_POINTER);
            return;
        }
        IllegalAccessError illegalAccessError = new IllegalAccessError("PushPackageInfo need a non-null pkgName.");
        AppMethodBeat.o(WindowManager.LayoutParams.TYPE_POINTER);
        throw illegalAccessError;
    }

    public final String toString() {
        AppMethodBeat.i(WindowManager.LayoutParams.TYPE_VOLUME_OVERLAY, false);
        String str = "PushPackageInfo{mPackageName=" + this.a + ", mPushVersion=" + this.b + ", mPackageVersion=" + this.c + ", mInBlackList=" + this.e + ", mPushEnable=" + this.f + "}";
        AppMethodBeat.o(WindowManager.LayoutParams.TYPE_VOLUME_OVERLAY);
        return str;
    }
}
