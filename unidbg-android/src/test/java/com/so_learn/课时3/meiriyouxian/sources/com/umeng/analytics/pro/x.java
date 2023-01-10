package com.umeng.analytics.pro;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.util.TimedRemoteCaller;
import com.umeng.commonsdk.config.FieldManager;
import com.umeng.commonsdk.debug.UMRTLog;
import com.umeng.commonsdk.framework.UMWorkDispatch;
import com.umeng.commonsdk.service.UMGlobalContext;
import com.umeng.commonsdk.statistics.internal.PreferenceWrapper;
import com.umeng.commonsdk.utils.b;
import java.util.ArrayList;
import java.util.List;

/* compiled from: SessionIdManager */
public class x {
    private static volatile x c;
    private v a = new w();
    private String b;
    private List<a> d;
    private String e;

    /* compiled from: SessionIdManager */
    public interface a {
        void a(String str, long j, long j2, long j3);

        void a(String str, String str2, long j, long j2, long j3);
    }

    private x() {
    }

    public static x a() {
        if (c == null) {
            synchronized (x.class) {
                if (c == null) {
                    c = new x();
                }
            }
        }
        return c;
    }

    public void a(long j) {
        this.a.a(j);
    }

    public long b() {
        return this.a.a();
    }

    public String a(Context context) {
        Context appContext = UMGlobalContext.getAppContext(context);
        if (appContext == null) {
            return "";
        }
        String str = "";
        try {
            synchronized (x.class) {
                str = PreferenceWrapper.getDefault(appContext).getString(t.d, "");
            }
        } catch (Exception unused) {
        }
        return str;
    }

    public synchronized String b(Context context) {
        Context appContext = UMGlobalContext.getAppContext(context);
        if (appContext == null) {
            return "";
        }
        this.b = d(appContext);
        if (e(appContext)) {
            try {
                this.b = f(appContext);
            } catch (Exception unused) {
            }
        }
        return this.b;
    }

    public String c(Context context) {
        Context appContext = UMGlobalContext.getAppContext(context);
        if (appContext == null) {
            return "";
        }
        try {
            this.b = f(appContext);
        } catch (Exception unused) {
        }
        return this.b;
    }

    public String d(Context context) {
        if (TextUtils.isEmpty(this.b)) {
            try {
                this.b = PreferenceWrapper.getDefault(context).getString("session_id", null);
            } catch (Exception unused) {
            }
        }
        return this.b;
    }

    public String a(Context context, long j) {
        if (TextUtils.isEmpty(this.e)) {
            String str = "SUB" + j;
            StringBuilder sb = new StringBuilder();
            sb.append(str);
            sb.append(String.format("%0" + (32 - str.length()) + "d", 0));
            this.e = sb.toString();
        }
        return this.e;
    }

    public boolean e(Context context) {
        if (TextUtils.isEmpty(this.b)) {
            this.b = d(context);
        }
        return TextUtils.isEmpty(this.b) || j(context) || g(context);
    }

    private String f(Context context) {
        try {
            SharedPreferences.Editor edit = PreferenceWrapper.getDefault(context).edit();
            edit.putString(t.d, d(context));
            edit.commit();
        } catch (Exception unused) {
        }
        long h = h(context);
        long i = i(context);
        String str = this.b;
        long a2 = t.a(context);
        long j = a2 * TimedRemoteCaller.DEFAULT_CALL_TIMEOUT_MILLIS;
        UMRTLog.e(UMRTLog.RTLOG_TAG, "--->>>*** \u8bfb\u53d6 foreground count \u503c\u5b8c\u6210\uff0ccount\u6b21\u6570\uff1a" + a2);
        if (!FieldManager.allow(b.E)) {
            UMRTLog.e(UMRTLog.RTLOG_TAG, "--->>>*** foreground count druation\u4e91\u63a7\u53c2\u6570\u5173\u95ed\u3002");
        } else if (UMWorkDispatch.eventHasExist()) {
            UMRTLog.e(UMRTLog.RTLOG_TAG, "--->>>*** \u8bfb\u53d6 foreground count druation\u503c\u5b8c\u6210\uff0c\u7ec8\u6b62checker timer.");
            UMWorkDispatch.removeEvent();
        } else {
            UMRTLog.e(UMRTLog.RTLOG_TAG, "--->>>*** \u8bfb\u53d6 foreground count druation\u503c\u5b8c\u6210\uff0c\u65e0\u672a\u5904\u7406check timer\u4e8b\u4ef6\u3002");
        }
        a(i, h, j, str, false);
        this.b = this.a.a(context);
        a(i, h, j, str, true);
        this.a.a(context, this.b);
        return this.b;
    }

    private boolean g(Context context) {
        if (!TextUtils.isEmpty(this.b) && h.a(context).a(this.b) > 0) {
            return true;
        }
        return false;
    }

    private long a(Context context, String str) {
        long j;
        try {
            j = PreferenceWrapper.getDefault(context).getLong(str, 0);
        } catch (Exception unused) {
            j = 0;
        }
        return j <= 0 ? System.currentTimeMillis() : j;
    }

    private long h(Context context) {
        return a(context, t.f);
    }

    private long i(Context context) {
        return a(context, t.a);
    }

    private void a(long j, long j2, long j3, String str, boolean z) {
        List<a> list = this.d;
        if (list != null) {
            for (a aVar : list) {
                if (z) {
                    try {
                        aVar.a(str, this.b, j, j2, j3);
                    } catch (Exception unused) {
                    }
                } else {
                    aVar.a(this.b, j, j2, j3);
                }
            }
        }
    }

    private boolean j(Context context) {
        Context appContext = UMGlobalContext.getAppContext(context);
        try {
            SharedPreferences sharedPreferences = PreferenceWrapper.getDefault(appContext);
            long j = sharedPreferences.getLong(t.e, 0);
            long j2 = sharedPreferences.getLong(t.f, 0);
            if (FieldManager.allow(b.E) && j > 0 && j2 == 0) {
                long a2 = t.a(appContext);
                if (a2 > 0) {
                    long j3 = a2 * TimedRemoteCaller.DEFAULT_CALL_TIMEOUT_MILLIS;
                    UMRTLog.e(UMRTLog.RTLOG_TAG, "--->>> last session end time stamp = 0, reconstruct it by foreground count value.");
                    j2 = j + j3;
                }
            }
            UMRTLog.i(UMRTLog.RTLOG_TAG, "--->>> interval of last session is: " + (j2 - j));
            return this.a.a(j, j2);
        } catch (Exception unused) {
            return false;
        }
    }

    public void a(a aVar) {
        if (aVar != null) {
            if (this.d == null) {
                this.d = new ArrayList();
            }
            if (!this.d.contains(aVar)) {
                this.d.add(aVar);
            }
        }
    }

    public void b(a aVar) {
        List<a> list;
        if (aVar != null && (list = this.d) != null && list.size() != 0) {
            this.d.remove(aVar);
        }
    }
}
