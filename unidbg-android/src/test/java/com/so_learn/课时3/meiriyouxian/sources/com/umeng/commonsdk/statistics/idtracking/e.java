package com.umeng.commonsdk.statistics.idtracking;

import android.content.Context;
import android.text.TextUtils;
import com.taobao.accs.common.Constants;
import com.umeng.analytics.pro.at;
import com.umeng.analytics.pro.az;
import com.umeng.commonsdk.config.FieldManager;
import com.umeng.commonsdk.statistics.AnalyticsConstants;
import com.umeng.commonsdk.statistics.common.HelperUtils;
import com.umeng.commonsdk.statistics.common.MLog;
import com.umeng.commonsdk.statistics.internal.PreferenceWrapper;
import com.umeng.commonsdk.statistics.proto.c;
import com.umeng.commonsdk.utils.b;
import com.unionpay.tsmservice.mi.data.Constant;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/* compiled from: IdTracker */
public class e {
    public static final long a = 86400000;
    public static e b = null;
    private static final String c = "umeng_it.cache";
    private static Object j = new Object();
    private File d;
    private c e = null;
    private long f;
    private long g;
    private Set<a> h = new HashSet();
    private a i = null;

    public String d() {
        return null;
    }

    public static synchronized void a() {
        synchronized (e.class) {
            if (b != null) {
                b.e();
                b = null;
            }
        }
    }

    e(Context context) {
        this.d = new File(context.getFilesDir(), c);
        this.g = 86400000;
        this.i = new a(context);
        this.i.b();
    }

    public static synchronized e a(Context context) {
        e eVar;
        synchronized (e.class) {
            if (b == null) {
                b = new e(context);
                b.a(new f(context));
                b.a(new b(context));
                b.a(new l(context));
                b.a(new d(context));
                b.a(new c(context));
                b.a(new g(context));
                b.a(new k());
                if (FieldManager.allow(b.G)) {
                    b.a(new i(context));
                }
                j jVar = new j(context);
                if (jVar.g()) {
                    b.a(jVar);
                    b.a(new h(context));
                    jVar.i();
                }
                b.f();
            }
            eVar = b;
        }
        return eVar;
    }

    private boolean a(a aVar) {
        if (this.i.a(aVar.b())) {
            return this.h.add(aVar);
        }
        if (!AnalyticsConstants.UM_DEBUG) {
            return false;
        }
        MLog.w("invalid domain: " + aVar.b());
        return false;
    }

    public void a(long j2) {
        this.g = j2;
    }

    public synchronized void b() {
        long currentTimeMillis = System.currentTimeMillis();
        if (currentTimeMillis - this.f >= this.g) {
            boolean z = false;
            for (a aVar : this.h) {
                if (aVar.c()) {
                    if (aVar.a()) {
                        z = true;
                        if (!aVar.c()) {
                            this.i.b(aVar.b());
                        }
                    }
                }
            }
            if (z) {
                h();
                this.i.a();
                g();
            }
            this.f = currentTimeMillis;
        }
    }

    public synchronized c c() {
        return this.e;
    }

    private synchronized void h() {
        c cVar = new c();
        HashMap hashMap = new HashMap();
        ArrayList arrayList = new ArrayList();
        for (a aVar : this.h) {
            if (aVar.c()) {
                if (aVar.d() != null) {
                    hashMap.put(aVar.b(), aVar.d());
                }
                if (aVar.e() != null && !aVar.e().isEmpty()) {
                    arrayList.addAll(aVar.e());
                }
            }
        }
        cVar.a(arrayList);
        cVar.a(hashMap);
        synchronized (this) {
            this.e = cVar;
        }
    }

    public synchronized void e() {
        if (b != null) {
            boolean z = false;
            for (a aVar : this.h) {
                if (aVar.c() && aVar.e() != null && !aVar.e().isEmpty()) {
                    aVar.a((List<com.umeng.commonsdk.statistics.proto.a>) null);
                    z = true;
                }
            }
            if (z) {
                this.e.b(false);
                g();
            }
        }
    }

    public synchronized void f() {
        c i = i();
        if (i != null) {
            a(i);
            ArrayList<a> arrayList = new ArrayList(this.h.size());
            synchronized (this) {
                this.e = i;
                for (a aVar : this.h) {
                    aVar.a(this.e);
                    if (!aVar.c()) {
                        arrayList.add(aVar);
                    }
                }
                for (a aVar2 : arrayList) {
                    this.h.remove(aVar2);
                }
                h();
            }
        }
    }

    public synchronized void g() {
        if (this.e != null) {
            b(this.e);
        }
    }

    private c i() {
        Throwable th;
        FileInputStream fileInputStream;
        Exception e;
        synchronized (j) {
            if (!this.d.exists()) {
                return null;
            }
            try {
                fileInputStream = new FileInputStream(this.d);
                try {
                    byte[] readStreamToByteArray = HelperUtils.readStreamToByteArray(fileInputStream);
                    c cVar = new c();
                    new at().a(cVar, readStreamToByteArray);
                    HelperUtils.safeClose(fileInputStream);
                    return cVar;
                } catch (Exception e2) {
                    e = e2;
                    try {
                        e.printStackTrace();
                        HelperUtils.safeClose(fileInputStream);
                        return null;
                    } catch (Throwable th2) {
                        th = th2;
                        HelperUtils.safeClose(fileInputStream);
                        throw th;
                    }
                }
            } catch (Exception e3) {
                e = e3;
                fileInputStream = null;
                e.printStackTrace();
                HelperUtils.safeClose(fileInputStream);
                return null;
            } catch (Throwable th3) {
                th = th3;
                fileInputStream = null;
                HelperUtils.safeClose(fileInputStream);
                throw th;
            }
        }
    }

    private void a(c cVar) {
        if (cVar != null && cVar.a != null) {
            if (cVar.a.containsKey(Constant.KEY_MAC) && !FieldManager.allow(b.h)) {
                cVar.a.remove(Constant.KEY_MAC);
            }
            if (cVar.a.containsKey(Constants.KEY_IMEI) && !FieldManager.allow(b.g)) {
                cVar.a.remove(Constants.KEY_IMEI);
            }
            if (cVar.a.containsKey("android_id") && !FieldManager.allow(b.i)) {
                cVar.a.remove("android_id");
            }
            if (cVar.a.containsKey(Context.SERIAL_SERVICE) && !FieldManager.allow(b.j)) {
                cVar.a.remove(Context.SERIAL_SERVICE);
            }
            if (cVar.a.containsKey("idfa") && !FieldManager.allow(b.w)) {
                cVar.a.remove("idfa");
            }
            if (cVar.a.containsKey("oaid") && !FieldManager.allow(b.G)) {
                cVar.a.remove("oaid");
            }
        }
    }

    private void b(c cVar) {
        byte[] a2;
        synchronized (j) {
            if (cVar != null) {
                try {
                    synchronized (this) {
                        a(cVar);
                        a2 = new az().a(cVar);
                    }
                    if (a2 != null) {
                        HelperUtils.writeFile(this.d, a2);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /* compiled from: IdTracker */
    public static class a {
        private Context a;
        private Set<String> b = new HashSet();

        public a(Context context) {
            this.a = context;
        }

        public synchronized boolean a(String str) {
            return !this.b.contains(str);
        }

        public synchronized void b(String str) {
            this.b.add(str);
        }

        public void c(String str) {
            this.b.remove(str);
        }

        public synchronized void a() {
            if (!this.b.isEmpty()) {
                StringBuilder sb = new StringBuilder();
                for (String str : this.b) {
                    sb.append(str);
                    sb.append(',');
                }
                sb.deleteCharAt(sb.length() - 1);
                PreferenceWrapper.getDefault(this.a).edit().putString("invld_id", sb.toString()).commit();
            }
        }

        public synchronized void b() {
            String[] split;
            String string = PreferenceWrapper.getDefault(this.a).getString("invld_id", null);
            if (!TextUtils.isEmpty(string) && (split = string.split(com.xiaomi.mipush.sdk.Constants.ACCEPT_TIME_SEPARATOR_SP)) != null) {
                for (String str : split) {
                    if (!TextUtils.isEmpty(str)) {
                        this.b.add(str);
                    }
                }
            }
        }
    }
}
