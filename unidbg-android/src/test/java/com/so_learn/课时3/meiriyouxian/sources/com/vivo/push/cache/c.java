package com.vivo.push.cache;

import android.content.Context;
import android.text.TextUtils;
import com.vivo.push.util.ContextDelegate;
import com.vivo.push.util.f;
import com.vivo.push.util.n;
import com.vivo.push.util.x;
import java.util.ArrayList;
import java.util.List;

/* compiled from: ICacheSettings */
public abstract class c<T> {
    public static final byte[] a = {34, 32, 33, 37, 33, 34, 32, 33, 33, 33, 34, 41, 35, 32, 32, 32};
    public static final byte[] b = {33, 34, 35, 36, 37, 38, 39, 40, 41, 32, 38, 37, 36, 35, 34, 33};
    protected static final Object c = new Object();
    protected List<T> d = new ArrayList();
    protected Context e;

    /* access modifiers changed from: protected */
    public abstract String a();

    /* access modifiers changed from: protected */
    public abstract List<T> a(String str);

    /* access modifiers changed from: package-private */
    public abstract String b(String str) throws Exception;

    protected c(Context context) {
        this.e = ContextDelegate.getContext(context).getApplicationContext();
        c();
    }

    public final void c() {
        synchronized (c) {
            f.a(a());
            this.d.clear();
            String a2 = x.b(this.e).a(a(), null);
            if (TextUtils.isEmpty(a2)) {
                n.d("CacheSettings", "ClientManager init " + a() + " strApps empty.");
            } else if (a2.length() > 10000) {
                n.d("CacheSettings", "sync " + a() + " strApps lenght too large");
                d();
            } else {
                try {
                    n.d("CacheSettings", "ClientManager init " + a() + " strApps : " + a2);
                    List<T> a3 = a(b(a2));
                    if (a3 != null) {
                        this.d.addAll(a3);
                    }
                } catch (Exception e) {
                    d();
                    n.d("CacheSettings", n.a(e));
                }
            }
        }
    }

    public final void d() {
        synchronized (c) {
            this.d.clear();
            x.b(this.e).b(a(), "");
            n.d("CacheSettings", "clear " + a() + " strApps");
        }
    }
}
