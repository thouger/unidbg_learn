package com.sina.weibo.sdk.component;

import android.content.Context;
import android.text.TextUtils;
import com.sina.weibo.sdk.auth.c;
import com.sina.weibo.sdk.component.WidgetRequestParam;
import java.util.HashMap;
import java.util.Map;

/* compiled from: WeiboCallbackManager */
public class d {
    private static d a;
    private Context b;
    private Map<String, c> c = new HashMap();
    private Map<String, WidgetRequestParam.a> d = new HashMap();

    private d(Context context) {
        this.b = context;
    }

    public static synchronized d a(Context context) {
        d dVar;
        synchronized (d.class) {
            if (a == null) {
                a = new d(context);
            }
            dVar = a;
        }
        return dVar;
    }

    public synchronized c a(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        return this.c.get(str);
    }

    public synchronized void a(String str, c cVar) {
        if (!TextUtils.isEmpty(str)) {
            if (cVar != null) {
                this.c.put(str, cVar);
            }
        }
    }

    public synchronized void b(String str) {
        if (!TextUtils.isEmpty(str)) {
            this.c.remove(str);
        }
    }

    public synchronized WidgetRequestParam.a c(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        return this.d.get(str);
    }

    public synchronized void a(String str, WidgetRequestParam.a aVar) {
        if (!TextUtils.isEmpty(str)) {
            if (aVar != null) {
                this.d.put(str, aVar);
            }
        }
    }

    public synchronized void d(String str) {
        if (!TextUtils.isEmpty(str)) {
            this.d.remove(str);
        }
    }

    public String a() {
        return String.valueOf(System.currentTimeMillis());
    }
}
