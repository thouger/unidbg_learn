package com.sdk.base.framework.a;

import android.os.SystemClock;
import android.util.Log;
import anet.channel.util.HttpConstant;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import com.sdk.base.framework.a.b.d;
import com.sdk.base.framework.a.c.c;
import com.sdk.base.framework.b.b;
import com.sdk.base.framework.f.g.a;
import com.sdk.base.module.manager.SDKManager;
import com.taobao.accs.common.Constants;
import java.io.File;
import java.net.HttpURLConnection;
import java.util.Map;
import java.util.TreeMap;
import org.json.JSONException;
import org.json.JSONObject;

public final class f<T> extends c<Object, Object, Void> implements com.sdk.base.framework.a.b.c {
    private static d b = new d();
    private static Map<String, Long> r = new TreeMap();
    private long c = d.a();
    private b<T> d;
    private String e;
    private String f;
    private g g = g.a;
    private int h;
    private boolean i = true;
    private long j;
    private String k = null;
    private boolean l = false;
    private Boolean m = false;
    private Boolean n = false;
    private Boolean o = false;
    private k<T> p;
    private long q;

    static {
        AppMethodBeat.i(20427, false);
        AppMethodBeat.o(20427);
    }

    public f(h<T> hVar) {
        AppMethodBeat.i(20402, false);
        this.p = hVar.b();
        k<T> kVar = this.p;
        if (kVar != null) {
            this.e = kVar.a();
            this.f = this.p.d();
            this.h = this.p.f();
            this.d = this.p.g();
        }
        AppMethodBeat.o(20402);
    }

    private l<T> a(h<T> hVar, HttpURLConnection httpURLConnection) {
        String a;
        AppMethodBeat.i(20405, false);
        l<T> lVar = null;
        try {
            if (!d.b(this.e) || (a = b.a(this.f)) == null) {
                if (this.m.booleanValue() && this.l) {
                    File file = new File(this.k);
                    long length = (!file.isFile() || !file.exists()) ? 0 : file.length();
                    if (length > 0) {
                        httpURLConnection.setRequestProperty("RANGE", "bytes=" + length + "-");
                    }
                }
                if (!b()) {
                    this.q = System.currentTimeMillis();
                    lVar = b(hVar, hVar.a(httpURLConnection));
                }
                if (lVar == null) {
                    lVar = new l<>(1, "\u7f51\u7edc\u8bbf\u95ee\u5f02\u5e38", false);
                }
                AppMethodBeat.o(20405);
                return lVar;
            }
            l<T> lVar2 = new l<>(0, a, true);
            AppMethodBeat.o(20405);
            return lVar2;
        } catch (Throwable th) {
            a.b(th.toString());
            com.sdk.base.framework.a.a.c.b("PriorityAsyncTask", "\u7f51\u7edc\u8bbf\u95ee\u5f02\u5e38\uff1a" + th.toString(), this.a);
            int i = this.h;
            if (i > 0) {
                this.h = i - 1;
                lVar = a(hVar, httpURLConnection);
            }
        }
    }

    private l<T> b(h<T> hVar, HttpURLConnection httpURLConnection) {
        l<T> lVar;
        AppMethodBeat.i(20418, false);
        if (b()) {
            lVar = new l<>(1, "\u7f51\u7edc\u8bbf\u95ee\u5df2\u53d6\u6d88", false);
        } else {
            int i = -1;
            try {
                a.a(httpURLConnection.getURL().toString(), System.currentTimeMillis() - this.q);
                if (httpURLConnection != null) {
                    i = httpURLConnection.getResponseCode();
                    String str = "net\u8bf7\u6c42host\uff1a" + httpURLConnection.getURL().getHost() + "\n net\u8bf7\u6c42path\uff1a" + httpURLConnection.getURL().getPath() + "\n  net\u8bf7\u6c42\u7801\uff1a" + i;
                    Boolean bool = this.a;
                    if (str == null) {
                        str = "";
                    }
                    if (bool.booleanValue()) {
                        Log.d("PriorityAsyncTask", str);
                    }
                }
                if (this.a.booleanValue()) {
                    r.put(httpURLConnection.getURL().toString(), Long.valueOf(System.currentTimeMillis() - this.q));
                    com.sdk.base.framework.a.a.c.a("PriorityAsyncTask", "\u54cd\u5e94\u8fd4\u56de\uff1acode=" + i + ";\u8017\u65f6=" + (System.currentTimeMillis() - this.q), this.a);
                }
                if (i < 300) {
                    Object obj = null;
                    String str2 = null;
                    if (httpURLConnection != null) {
                        this.i = false;
                        if (this.l) {
                            this.m = Boolean.valueOf(this.m.booleanValue() && com.sdk.base.framework.f.d.a.a(httpURLConnection));
                            if (this.n.booleanValue()) {
                                str2 = com.sdk.base.framework.f.d.a.b(httpURLConnection);
                            }
                            new com.sdk.base.framework.a.b.b();
                            com.sdk.base.framework.a.b.b.a(httpURLConnection, this, this.k, this.m.booleanValue(), str2);
                        }
                        if (this.o.booleanValue()) {
                            new com.sdk.base.framework.a.b.a();
                            obj = com.sdk.base.framework.a.b.a.a(httpURLConnection);
                        } else {
                            new d();
                            obj = d.a(httpURLConnection, this, "UTF-8");
                            if (d.b(this.e)) {
                                b.a(this.f, (String) obj, this.c);
                            }
                        }
                    }
                    l<T> lVar2 = new l<>(0, obj, false);
                    AppMethodBeat.o(20418);
                    return lVar2;
                }
                if (i == 301 || i == 302) {
                    String headerField = httpURLConnection.getHeaderField("Location");
                    String headerField2 = httpURLConnection.getHeaderField(HttpConstant.SET_COOKIE);
                    String path = httpURLConnection.getURL().getPath();
                    if (com.sdk.base.framework.a.a.c.b(headerField).booleanValue() && hVar != null) {
                        hVar.b().b(headerField);
                        HttpURLConnection a = com.sdk.base.framework.f.d.a.a(headerField) ? hVar.a(headerField, true) : hVar.a(headerField, false);
                        if (com.sdk.base.framework.a.a.c.b(headerField2).booleanValue()) {
                            if ("/ctcnet/gctcmc.do".equals(path)) {
                                com.sdk.base.framework.f.b.a.b(SDKManager.getContext(), "ctc", headerField2);
                                com.sdk.base.framework.a.a.c.a("PriorityAsyncTask", "mdb Cookie cache", this.a);
                            }
                            a.setRequestProperty(HttpConstant.COOKIE, headerField2);
                        } else {
                            a.setRequestProperty(HttpConstant.COOKIE, com.sdk.base.framework.f.b.a.b(SDKManager.getContext(), "ctc"));
                        }
                        if (a == null) {
                            l<T> lVar3 = new l<>(0, c(), false);
                            AppMethodBeat.o(20418);
                            return lVar3;
                        } else if (a != null) {
                            hVar.c();
                            l<T> a2 = a(hVar, a);
                            AppMethodBeat.o(20418);
                            return a2;
                        }
                    }
                }
                a.b("\u670d\u52a1\u5f02\u5e38 ResponseCode = " + i);
                com.sdk.base.framework.a.a.c.b("PriorityAsyncTask", "\u670d\u52a1\u5f02\u5e38 ResponseCode = " + i, this.a);
                lVar = new l<>(0, "\u670d\u52a1\u7aef\u6570\u636e\u683c\u5f0f\u51fa\u9519", false);
            } catch (Exception e) {
                a.b(e.toString());
                com.sdk.base.framework.a.a.c.b("PriorityAsyncTask", e.toString(), this.a);
                lVar = new l<>(1, "\u7f51\u7edc\u8bbf\u95ee\u5f02\u5e38", false);
            }
        }
        AppMethodBeat.o(20418);
        return lVar;
    }

    private static String c() {
        AppMethodBeat.i(20410, false);
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put(Constants.KEY_HTTP_CODE, 1);
            jSONObject.put("status", 102001);
            jSONObject.put("msg", "\u9009\u62e9\u6d41\u91cf\u901a\u9053\u5931\u8d25");
            String jSONObject2 = jSONObject.toString();
            AppMethodBeat.o(20410);
            return jSONObject2;
        } catch (JSONException unused) {
            AppMethodBeat.o(20410);
            return null;
        }
    }

    private Void e(Object... objArr) {
        AppMethodBeat.i(20408, false);
        if (this.g == g.e || objArr == null || objArr.length == 0) {
            AppMethodBeat.o(20408);
            return null;
        }
        if (objArr.length == 4) {
            this.k = String.valueOf(objArr[1]);
            this.l = this.k != null;
            this.m = (Boolean) objArr[2];
            this.n = (Boolean) objArr[3];
        }
        if (objArr.length == 2) {
            this.o = (Boolean) objArr[1];
        }
        try {
            this.j = SystemClock.uptimeMillis();
            d(new Object[]{1});
            h hVar = (h) objArr[0];
            this.f = hVar.a();
            HttpURLConnection a = hVar.a(this.f, false);
            if (a == null) {
                d(new Object[]{4, new l(0, c(), false)});
                AppMethodBeat.o(20408);
                return null;
            }
            l<T> a2 = a(hVar, a);
            if (a2 != null) {
                if (a2.a() == 0) {
                    d(new Object[]{4, a2});
                } else {
                    d(new Object[]{3, Integer.valueOf(a2.a()), a2.b()});
                }
                AppMethodBeat.o(20408);
                return null;
            }
            AppMethodBeat.o(20408);
            return null;
        } catch (Exception e) {
            a.b(e.toString());
            com.sdk.base.framework.a.a.c.b("PriorityAsyncTask", "\u7f51\u7edc\u8bbf\u95ee\u5f02\u5e38\uff1a\n" + e.toString(), this.a);
            d(new Object[]{3, 302002, "\u7f51\u7edc\u8bbf\u95ee\u5f02\u5e38"});
        }
    }

    public final void a() {
        AppMethodBeat.i(20420, false);
        this.g = g.e;
        if (!b()) {
            try {
                a(true);
                AppMethodBeat.o(20420);
                return;
            } catch (Throwable th) {
                com.sdk.base.framework.a.a.c.b("PriorityAsyncTask", th.getMessage(), this.a);
            }
        }
        AppMethodBeat.o(20420);
    }

    /* access modifiers changed from: protected */
    public final void a(Object... objArr) {
        AppMethodBeat.i(20413, false);
        if (this.g == g.e || objArr == null || objArr.length == 0 || this.d == null) {
            AppMethodBeat.o(20413);
            return;
        }
        int intValue = ((Integer) objArr[0]).intValue();
        if (intValue == 1) {
            this.g = g.b;
        } else if (intValue != 2) {
            if (intValue != 3) {
                if (intValue == 4) {
                    if (objArr.length != 2) {
                        AppMethodBeat.o(20413);
                        return;
                    }
                    this.g = g.f;
                    this.p.d();
                    this.d.a((l) objArr[1]);
                }
                AppMethodBeat.o(20413);
                return;
            } else if (objArr.length != 3) {
                AppMethodBeat.o(20413);
                return;
            } else {
                this.g = g.d;
                this.d.a(((Integer) objArr[1]).intValue(), (String) objArr[2]);
            }
        } else if (objArr.length != 3) {
            AppMethodBeat.o(20413);
            return;
        } else {
            this.g = g.c;
            Long.parseLong(String.valueOf(objArr[1]));
            Long.parseLong(String.valueOf(objArr[2]));
        }
        AppMethodBeat.o(20413);
    }

    public final boolean a(long j, long j2, boolean z) {
        AppMethodBeat.i(20415, false);
        if (!(this.d == null || this.g == g.e)) {
            if (z) {
                d(new Object[]{2, Long.valueOf(j), Long.valueOf(j2)});
            } else {
                long uptimeMillis = SystemClock.uptimeMillis();
                if (uptimeMillis - this.j >= ((long) this.d.a())) {
                    this.j = uptimeMillis;
                    d(new Object[]{2, Long.valueOf(j), Long.valueOf(j2)});
                }
            }
        }
        g gVar = this.g;
        g gVar2 = g.e;
        AppMethodBeat.o(20415);
        return gVar != gVar2;
    }

    /* access modifiers changed from: protected */
    public final /* synthetic */ Object b(Object[] objArr) {
        AppMethodBeat.i(20422, false);
        Void e = e(objArr);
        AppMethodBeat.o(20422);
        return e;
    }
}
