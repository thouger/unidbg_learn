package com.sijla.common;

import android.app.Application;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.WifiEnterpriseConfig;
import android.os.Build;
import android.text.TextUtils;
import com.huawei.hms.support.api.push.pushselfshow.prepare.NotificationIconUtil;
import com.sijla.common.f;
import com.sijla.g.b;
import com.sijla.g.i;
import com.sijla.la.LContext;
import com.sijla.la.a;
import com.sijla.lj.L;
import com.sijla.lj.LException;
import com.sijla.lj.c;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.JSONObject;

public class e implements f.b, LContext {
    public static List<c> d = null;
    private static String h = "lib";
    private static Intent i;
    private static long j;
    private static boolean u;
    private static String w;
    private static boolean y;
    public String a;
    public String b;
    boolean c;
    private Application e;
    private boolean f;
    private HashMap<String, String> g = new HashMap<>();
    private ArrayList<a> k = new ArrayList<>();
    private String l;
    private L m;
    private String n;
    private String o;
    private String p;
    private String q = "";
    private String r = "";
    private BroadcastReceiver s;
    private f t;
    private List<com.sijla.d.c> v = new ArrayList();
    private boolean x;
    private StringBuilder z = new StringBuilder();

    public void a(int i2, String str) {
    }

    public void a(int i2, String str, Object[] objArr) {
    }

    @Override // com.sijla.la.LContext
    public Map getGlobalData() {
        return null;
    }

    @Override // com.sijla.la.LContext
    public void sendMsg(String str) {
    }

    public e(Application application, boolean z) {
        this.e = application;
        this.f = true;
    }

    public void a(Context context) {
        File[] listFiles = new File(getLDir() + "/libs").listFiles();
        if (listFiles != null) {
            for (File file : listFiles) {
                if (file.getAbsolutePath().endsWith(".so")) {
                    a(context, file.getName());
                }
            }
        }
    }

    public void a(Context context, String str) {
        int indexOf = str.indexOf(".");
        if (indexOf > 0) {
            str = str.substring(0, indexOf);
        }
        if (str.startsWith("lib")) {
            str = str.substring(3);
        }
        this.g.put(str, this.a + "/libs/lib" + str + ".so");
    }

    @Override // com.sijla.la.LContext
    public HashMap<String, String> getLibrarys() {
        return this.g;
    }

    public Intent a(IntentFilter intentFilter) {
        BroadcastReceiver broadcastReceiver = this.s;
        if (broadcastReceiver != null) {
            try {
                this.e.unregisterReceiver(broadcastReceiver);
            } catch (Throwable unused) {
            }
        }
        this.s = new 1(this);
        return this.e.registerReceiver(this.s, intentFilter);
    }

    @Override // com.sijla.la.LContext
    public void regGc(a aVar) {
        this.k.add(aVar);
    }

    @Override // com.sijla.la.LContext
    public String getLDir() {
        return this.a;
    }

    @Override // com.sijla.la.LContext
    public String getLCpath() {
        return this.b;
    }

    @Override // com.sijla.la.LContext
    public Context getContext() {
        return this.e;
    }

    @Override // com.sijla.la.LContext
    public L getLState() {
        return this.m;
    }

    public void a() {
        String f = com.sijla.g.a.a.f(this.e);
        if (w == null) {
            w = f;
            this.t = new f(this.e);
            this.t.a(this);
            com.sijla.a.a.a(new 5(this, f));
        }
    }

    public void a(Application application) {
        if ((Build.VERSION.SDK_INT < 14) || !this.f) {
            String packageName = application.getPackageName();
            String f = com.sijla.g.a.a.f(application);
            this.c = true;
            com.sijla.a.a.a(new 6(this, f, application, packageName));
        }
    }

    private void s() {
        this.c = false;
    }

    public void b() {
        try {
            if ((Build.VERSION.SDK_INT > 20) && com.sijla.b.c.a.optLong("lua", 1) == 1) {
                if (com.sijla.b.c.a.optLong(L._s3().replace(".", ""), 1) == 1) {
                    h();
                    c(true);
                    c();
                }
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public void c() {
        if (e() && new File(this.p).exists()) {
            a(this.p);
            new File(this.o).delete();
            new File(this.p).delete();
        }
    }

    public void d() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(Intent.ACTION_PACKAGE_ADDED);
        intentFilter.addAction(Intent.ACTION_PACKAGE_REMOVED);
        intentFilter.addAction(Intent.ACTION_PACKAGE_REPLACED);
        intentFilter.addDataScheme("package");
        intentFilter.setPriority(999);
        a(intentFilter);
        IntentFilter intentFilter2 = new IntentFilter();
        intentFilter2.addAction(com.sijla.g.a.a.c(this.e));
        intentFilter2.addAction(com.sijla.g.a.a.b(this.e));
        this.e.registerReceiver(new 7(this), intentFilter2);
    }

    private boolean c(boolean z) {
        if (z || b.a(this.e, "chkdla", com.sijla.b.c.a.optLong("chkdla", 3600))) {
            try {
                String optString = com.sijla.b.c.a.optString("lfcurl", a.e);
                if (!TextUtils.isEmpty(optString)) {
                    JSONObject k = b.k(this.e);
                    k.put("abi", b.a());
                    k.put("lv", f());
                    k.put("sov", L._sov());
                    k.put("sot", L._sot());
                    String str = this.a;
                    String str2 = str + "/Qt.zip";
                    File a = i.a(optString, k, str2, false);
                    if (a != null && a.exists()) {
                        com.sijla.g.a.c.a(str + "/libs");
                        new com.sijla.g.e(a.getAbsolutePath(), str).a();
                        com.sijla.g.a.c.a(str2);
                        String b = com.sijla.g.a.c.b(str + NotificationIconUtil.SPLIT_CHAR + "Qt.ecr");
                        if (!TextUtils.isEmpty(b)) {
                            return com.sijla.g.a.c.a(b, this.q, false);
                        }
                    }
                }
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
        return false;
    }

    public boolean e() {
        String b = com.sijla.g.a.c.b(this.q);
        if (!TextUtils.isEmpty(b.trim())) {
            String c = b.c(b.split("\n")[1]);
            if (!TextUtils.isEmpty(c)) {
                return com.sijla.g.a.c.a(c, this.p, false);
            }
        }
        return false;
    }

    public String f() {
        return com.sijla.g.a.c.b(this.r).trim();
    }

    private void t() {
        try {
            String _iilc = L._iilc();
            com.sijla.g.a.c.a(com.sijla.c.b.b(_iilc.substring(0, 8), _iilc.substring(8, _iilc.length())), this.o, false);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public void g() {
        this.a = this.e.getFilesDir().getAbsolutePath() + "/.qt";
        com.sijla.g.a.c.a(this.a, false);
        this.n = this.a + "/libs";
        com.sijla.g.a.c.a(this.n, false);
        this.b = this.e.getApplicationInfo().nativeLibraryDir + "/lib?.so;" + this.n + "/lib?.so";
        String _s = L._s();
        String _s1 = L._s1();
        String _s2 = L._s2();
        String _s3 = L._s3();
        this.l = this.a + _s1 + this.a + _s + this.a + _s2;
        StringBuilder sb = new StringBuilder();
        sb.append(this.a);
        sb.append("/import");
        sb.append(_s3);
        this.o = sb.toString();
        this.p = this.a + NotificationIconUtil.SPLIT_CHAR + "Qt" + _s3;
        this.q = this.a + NotificationIconUtil.SPLIT_CHAR + "Qt.ecr";
        this.r = this.a + NotificationIconUtil.SPLIT_CHAR + "Qt.ver";
    }

    public void h() {
        g();
        a((Context) this.e);
        t();
        if (this.m == null) {
            try {
                this.m = u();
            } catch (Exception e) {
                e.printStackTrace();
                sendMsg(e.getMessage());
            }
        }
    }

    static {
        d = new ArrayList();
    }

    public void i() {
        try {
            if (this.k != null) {
                Iterator<a> it2 = this.k.iterator();
                while (it2.hasNext()) {
                    it2.next().a();
                }
                this.k.clear();
            }
            if (!(this.m == null || this.m.a() == 0)) {
                this.m.a(2, 1);
                for (c cVar : d) {
                    this.m.b(-1001000, cVar.a());
                }
                d.clear();
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    private L u() {
        L a = com.sijla.lj.f.a();
        a.h();
        a.a(this.e);
        a.b(this);
        a.d("service");
        a.c("Qt");
        a.a(this.a);
        a.a(-2, L._s5());
        a.a(b.k(this.e).toString());
        a.a(-2, "qmheadjson");
        a.x(1);
        a.c("package");
        a.a(this.l);
        a.a(-2, "path");
        a.a(this.b);
        a.a(-2, "cpath");
        a.x(1);
        a.f();
        return a;
    }

    public Object a(String str) {
        return doFile(str, new Object[0]);
    }

    @Override // com.sijla.la.LContext
    public Object doFile(String str, Object[] objArr) {
        L l = this.m;
        if (!(l == null || l.a() == 0)) {
            try {
                this.m.a(0);
                int b = this.m.b(str);
                if (b == 0) {
                    int length = objArr != null ? objArr.length : 0;
                    for (int i2 = 0; i2 < length; i2++) {
                        this.m.c(objArr[i2]);
                    }
                    b = this.m.a(length, 1, 0);
                    if (b == 0) {
                        return null;
                    }
                }
                throw new LException(a(b) + ": " + this.m.q(-1));
            } catch (LException e) {
                sendMsg(e.getMessage());
            }
        }
        return null;
    }

    public Object a(String str, Object... objArr) {
        L l = this.m;
        if (!(l == null || l.a() == 0)) {
            try {
                this.m.a(0);
                if (this.m.c(str) != 0 && this.m.g(-1)) {
                    int length = objArr != null ? objArr.length : 0;
                    for (int i2 = 0; i2 < length; i2++) {
                        this.m.c(objArr[i2]);
                    }
                    if (this.m.a(length, 1, 0) == 0) {
                        return null;
                    }
                }
            } catch (Exception e) {
                sendMsg(str + WifiEnterpriseConfig.CA_CERT_ALIAS_DELIMITER + e.getMessage());
            }
        }
        return null;
    }

    private String a(int i2) {
        return i2 + "";
    }

    @Override // com.sijla.la.LContext
    public void sendError(String str, Exception exc) {
        a("onError", str, exc);
    }

    @Override // com.sijla.la.LContext
    public void call(String str, Object[] objArr) {
        if (objArr.length == 0) {
            a(2, str);
        } else {
            a(3, str, objArr);
        }
    }

    @Override // com.sijla.la.LContext
    public void set(String str, Object obj) {
        a(1, str, new Object[]{obj});
    }

    public void j() {
        com.sijla.a.a.a(new 8(this));
    }

    public void k() {
        com.sijla.a.a.a(new 9(this));
    }

    public void l() {
        u = true;
        a(this.e);
        com.sijla.a.a.a(new 10(this));
    }

    public void m() {
        u = false;
        s();
        com.sijla.a.a.a(new 11(this));
    }

    public void a(Intent intent) {
        com.sijla.a.a.a(new 2(this, intent));
    }

    public void n() {
        com.sijla.a.a.a(new 3(this));
    }

    public void a(long j2) {
        com.sijla.a.a.a(new 4(this, j2));
    }
}
