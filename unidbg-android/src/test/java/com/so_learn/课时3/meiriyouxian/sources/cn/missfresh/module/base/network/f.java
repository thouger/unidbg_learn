package cn.missfresh.module.base.network;

import com.bangcle.andjni.JniLib;

/* compiled from: MFGlobalErrorHandler */
final class f {
    static void a(int i, String str) {
        JniLib.cV(Integer.valueOf(i), str, 158);
    }

    private static void a(String str) {
        JniLib.cV(str, 159);
    }
}
