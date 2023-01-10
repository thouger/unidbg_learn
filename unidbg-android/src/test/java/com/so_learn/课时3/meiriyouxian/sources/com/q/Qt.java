package com.q;

import android.app.Application;
import android.content.Context;
import android.telecom.Logging.Session;
import android.util.Log;
import com.sijla.callback.QtCallBack;
import com.sijla.common.c;
import com.sijla.f.a;
import com.sijla.g.b;
import com.sijla.g.f;
import com.sijla.lj.L;

public class Qt {
    public static boolean _s() {
        return false;
    }

    @Deprecated
    public static void appHidden(Context context) {
    }

    @Deprecated
    public static void appStart(Context context) {
    }

    public static void isAllowNetworkConnections(Context context, boolean z) {
    }

    /* renamed from: com.q.Qt$1  reason: invalid class name */
    static class AnonymousClass1 implements Runnable {
        final /* synthetic */ Context a;
        final /* synthetic */ String b;

        AnonymousClass1(Context context, String str) {
            this.a = context;
            this.b = str;
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                if (this.a != null && !b.a(this.b) && b.a(this.a, "oaid2k", 259200)) {
                    a.a(this.a, this.b, 2);
                }
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
    }

    public static void saveOAID(Context context, String str) {
        com.sijla.a.a.a(new AnonymousClass1(context, str));
    }

    public static String __v__() {
        return com.sijla.common.a.a + Session.SESSION_SEPARATION_CHAR_CHILD + L._sov();
    }

    public static void init(Application application, String str, String str2) {
        init(application, str, str2, null);
    }

    public static void init(Application application, String str, String str2, QtCallBack qtCallBack) {
        init(application, str, str2, application.getPackageName(), true, qtCallBack);
    }

    public static void init(Application application, String str, String str2, String str3, QtCallBack qtCallBack) {
        init(application, str, str2, str3, true, qtCallBack);
    }

    public static void init(Application application, String str, String str2, boolean z, QtCallBack qtCallBack) {
        init(application, str, str2, application.getPackageName(), z, qtCallBack);
    }

    public static void init(Application application, String str, String str2, String str3, boolean z, QtCallBack qtCallBack) {
        if (application == null) {
            c.b("\u4f20\u5165\u7684\u4e0a\u4e0b\u6587\u4e3anull");
            return;
        }
        isAllowNetworkConnections(application, z);
        if (!z) {
            Log.e("QuestMobile", "QuestMobile SDK \u8bbe\u7f6e\u4e3a\u4e0d\u5141\u8bb8\u4f7f\u7528\u7f51\u7edc");
            return;
        }
        boolean a = com.sijla.g.a.a.a();
        Log.d("isAoc", "isAoc = " + a);
        c.a(application, str, str2, str3, z, qtCallBack, a);
        c.d(application);
        com.sijla.common.b.a(application, str, str2, z, a);
        c.a(application, str, str2, z, a);
    }

    public static void setAppkey(Context context, String str) {
        c.a(str);
    }

    public static void onEvent(Context context, String str, String str2) {
        f.a(context, str, str2);
    }

    public static void onEvent(Context context, String str) {
        f.a(context, str, "");
    }

    public static void showLog(boolean z) {
        c.a(z);
    }
}
