package cn.missfresh.module.base.common.config;

import android.Manifest;
import android.os.Build;
import cn.missfresh.module.base.utils.f;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;

/* compiled from: OtherConfig */
public final class h {
    public static final String[] a = {"android.permission.WRITE_EXTERNAL_STORAGE"};
    public static final String[] b = {"android.permission.WRITE_EXTERNAL_STORAGE"};

    public static String[] a() {
        AppMethodBeat.i(11181, false);
        if (Build.VERSION.SDK_INT >= 29) {
            String[] strArr = {Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION};
            AppMethodBeat.o(11181);
            return strArr;
        } else if (f.A()) {
            String[] strArr2 = {Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION};
            AppMethodBeat.o(11181);
            return strArr2;
        } else {
            String[] strArr3 = {"android.permission.READ_PHONE_STATE", Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION};
            AppMethodBeat.o(11181);
            return strArr3;
        }
    }

    public static String[] b() {
        return Build.VERSION.SDK_INT < 29 ? new String[0] : new String[0];
    }
}
