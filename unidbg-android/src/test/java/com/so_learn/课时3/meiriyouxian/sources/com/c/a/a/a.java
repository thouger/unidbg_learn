package com.c.a.a;

import android.app.Application;
import android.content.Context;
import android.os.Build;
import android.util.Log;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/* compiled from: LoadedApkHuaWei */
public class a {
    private static final b a;

    /* compiled from: LoadedApkHuaWei */
    /* access modifiers changed from: private */
    public interface b {
        void a(Context context) throws Throwable;
    }

    static {
        int i = Build.VERSION.SDK_INT;
        if (i >= 26) {
            a = new d();
        } else if (i >= 24) {
            a = new c();
        } else {
            a = new C0070a();
        }
    }

    public static void a(Application application) {
        if (application != null) {
            try {
                a.a(application.getBaseContext());
            } catch (Throwable unused) {
            }
        } else {
            Log.w(a.class.getSimpleName(), "application is null \uff01\uff01\uff01");
        }
    }

    /* compiled from: LoadedApkHuaWei */
    private static class d extends C0070a {
        private d() {
            super();
        }

        @Override // com.c.a.a.a.C0070a, com.c.a.a.a.b
        public void a(Context context) throws Throwable {
            Object a = a(context, "mWhiteListMap");
            if (a instanceof Map) {
                Map map = (Map) a;
                List list = (List) map.get(0);
                if (list == null) {
                    list = new ArrayList();
                    map.put(0, list);
                }
                list.add(context.getPackageName());
            }
        }
    }

    /* compiled from: LoadedApkHuaWei */
    private static class c extends C0070a {
        private c() {
            super();
        }

        @Override // com.c.a.a.a.C0070a, com.c.a.a.a.b
        public void a(Context context) throws Throwable {
            Object a = a(context, "mWhiteList");
            if (a instanceof List) {
                ((List) a).add(context.getPackageName());
            }
        }
    }

    /* compiled from: LoadedApkHuaWei */
    /* renamed from: com.c.a.a.a$a  reason: collision with other inner class name */
    private static class C0070a implements b {
        private C0070a() {
        }

        @Override // com.c.a.a.a.b
        public void a(Context context) throws Throwable {
            Object b = b(context);
            Object a = a(b, "mWhiteList");
            if (a instanceof String[]) {
                ArrayList arrayList = new ArrayList();
                arrayList.add(context.getPackageName());
                Collections.addAll(arrayList, (String[]) a);
                com.c.b.a.a(b, "mWhiteList", arrayList.toArray(new String[arrayList.size()]));
            } else if (b != null) {
                com.c.b.a.a(b, "mResourceConfig", (Object) null);
            }
        }

        /* access modifiers changed from: package-private */
        public Object a(Context context, String str) {
            return a(b(context), str);
        }

        private Object a(Object obj, String str) {
            if (obj == null) {
                return null;
            }
            try {
                return com.c.b.a.a(obj, str);
            } catch (Throwable unused) {
                return null;
            }
        }

        private Object b(Context context) {
            Field a;
            Object a2;
            try {
                Field a3 = com.c.b.a.a("android.app.LoadedApk", "mReceiverResource", true);
                if (a3 == null || (a = com.c.b.a.a("android.app.ContextImpl", "mPackageInfo", true)) == null || (a2 = com.c.b.a.a(a, context)) == null) {
                    return null;
                }
                return com.c.b.a.a(a3, a2, true);
            } catch (Throwable unused) {
                return null;
            }
        }
    }
}
