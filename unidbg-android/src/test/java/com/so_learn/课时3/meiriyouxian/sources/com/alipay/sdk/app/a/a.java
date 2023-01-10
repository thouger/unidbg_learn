package com.alipay.sdk.app.a;

import android.content.Context;
import android.text.TextUtils;
import com.alipay.sdk.f.a.d;
import com.alipay.sdk.util.e;
import com.alipay.sdk.util.h;
import com.umeng.message.proguard.l;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;
import org.json.JSONArray;

public class a {

    static final class c {
        static synchronized long a(Context context) {
            long j;
            synchronized (c.class) {
                long j2 = 0;
                try {
                    String b = h.b(null, context, "alipay_cashier_statistic_v", null);
                    if (!TextUtils.isEmpty(b)) {
                        j2 = Long.parseLong(b);
                    }
                } catch (Throwable unused) {
                }
                j = j2 + 1;
                try {
                    h.a(null, context, "alipay_cashier_statistic_v", Long.toString(j));
                } catch (Throwable unused2) {
                }
            }
            return j;
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: com.alipay.sdk.app.a.a$a  reason: collision with other inner class name */
    public static final class C0061a {
        static synchronized String a(Context context, String str, String str2) {
            synchronized (C0061a.class) {
                e.a("RecordPref", "stat append " + str2 + l.u + str);
                if (context != null) {
                    if (!TextUtils.isEmpty(str)) {
                        if (TextUtils.isEmpty(str2)) {
                            str2 = UUID.randomUUID().toString();
                        }
                        C0062a b = b(context);
                        if (b.a.size() > 20) {
                            b.a.clear();
                        }
                        b.a.put(str2, str);
                        a(context, b);
                        return str2;
                    }
                }
                return null;
            }
        }

        static synchronized String a(Context context) {
            synchronized (C0061a.class) {
                e.a("RecordPref", "stat peek");
                if (context == null) {
                    return null;
                }
                C0062a b = b(context);
                if (b.a.isEmpty()) {
                    return null;
                }
                try {
                    return b.a.entrySet().iterator().next().getValue();
                } catch (Throwable th) {
                    e.a(th);
                    return null;
                }
            }
        }

        static synchronized int a(Context context, String str) {
            synchronized (C0061a.class) {
                e.a("RecordPref", "stat remove " + str);
                if (context != null) {
                    if (!TextUtils.isEmpty(str)) {
                        C0062a b = b(context);
                        if (b.a.isEmpty()) {
                            return 0;
                        }
                        try {
                            ArrayList arrayList = new ArrayList();
                            for (Map.Entry<String, String> entry : b.a.entrySet()) {
                                if (str.equals(entry.getValue())) {
                                    arrayList.add(entry.getKey());
                                }
                            }
                            Iterator it2 = arrayList.iterator();
                            while (it2.hasNext()) {
                                b.a.remove((String) it2.next());
                            }
                            a(context, b);
                            return arrayList.size();
                        } catch (Throwable th) {
                            e.a(th);
                            int size = b.a.size();
                            a(context, new C0062a());
                            return size;
                        }
                    }
                }
                return 0;
            }
        }

        private static synchronized C0062a b(Context context) {
            synchronized (C0061a.class) {
                try {
                    String b = h.b(null, context, "alipay_cashier_statistic_record", null);
                    if (TextUtils.isEmpty(b)) {
                        return new C0062a();
                    }
                    return new C0062a(b);
                } catch (Throwable th) {
                    e.a(th);
                    return new C0062a();
                }
            }
        }

        private static synchronized void a(Context context, C0062a aVar) {
            synchronized (C0061a.class) {
                if (aVar == null) {
                    try {
                        aVar = new C0062a();
                    } catch (Throwable th) {
                        e.a(th);
                    }
                }
                h.a(null, context, "alipay_cashier_statistic_record", aVar.a());
            }
            return;
        }

        /* access modifiers changed from: private */
        /* renamed from: com.alipay.sdk.app.a.a$a$a  reason: collision with other inner class name */
        public static final class C0062a {
            final LinkedHashMap<String, String> a = new LinkedHashMap<>();

            C0062a() {
            }

            C0062a(String str) {
                try {
                    JSONArray jSONArray = new JSONArray(str);
                    for (int i = 0; i < jSONArray.length(); i++) {
                        JSONArray jSONArray2 = jSONArray.getJSONArray(i);
                        this.a.put(jSONArray2.getString(0), jSONArray2.getString(1));
                    }
                } catch (Throwable th) {
                    e.a(th);
                }
            }

            /* access modifiers changed from: package-private */
            public String a() {
                try {
                    JSONArray jSONArray = new JSONArray();
                    for (Map.Entry<String, String> entry : this.a.entrySet()) {
                        JSONArray jSONArray2 = new JSONArray();
                        jSONArray2.put(entry.getKey()).put(entry.getValue());
                        jSONArray.put(jSONArray2);
                    }
                    return jSONArray.toString();
                } catch (Throwable th) {
                    e.a(th);
                    return new JSONArray().toString();
                }
            }
        }
    }

    /* access modifiers changed from: private */
    public static final class b {
        static synchronized void a(Context context, b bVar, String str, String str2) {
            synchronized (b.class) {
                if (context != null && bVar != null && str != null) {
                    a(context, bVar.a(str), str2);
                }
            }
        }

        private static synchronized void a(Context context, String str, String str2) {
            synchronized (b.class) {
                if (context != null) {
                    if (!TextUtils.isEmpty(str)) {
                        C0061a.a(context, str, str2);
                    }
                    new Thread(new AnonymousClass1(str, context)).start();
                }
            }
        }

        /* access modifiers changed from: package-private */
        /* renamed from: com.alipay.sdk.app.a.a$b$1  reason: invalid class name */
        public static class AnonymousClass1 implements Runnable {
            final /* synthetic */ String a;
            final /* synthetic */ Context b;

            AnonymousClass1(String str, Context context) {
                this.a = str;
                this.b = context;
            }

            @Override // java.lang.Runnable
            public void run() {
                if (TextUtils.isEmpty(this.a) || b.b(this.b, this.a)) {
                    for (int i = 0; i < 4; i++) {
                        String a = C0061a.a(this.b);
                        if (TextUtils.isEmpty(a) || !b.b(this.b, a)) {
                            return;
                        }
                    }
                }
            }
        }

        /* access modifiers changed from: private */
        public static synchronized boolean b(Context context, String str) {
            synchronized (b.class) {
                e.a("mspl", "stat sub " + str);
                try {
                    if ((com.alipay.sdk.b.a.p().g() ? new com.alipay.sdk.f.a.c() : new d()).a((com.alipay.sdk.g.a) null, context, str) == null) {
                        return false;
                    }
                    C0061a.a(context, str);
                    return true;
                } catch (Throwable th) {
                    e.a(th);
                    return false;
                }
            }
        }
    }

    public static synchronized void a(Context context, com.alipay.sdk.g.a aVar, String str, String str2) {
        synchronized (a.class) {
            if (context != null && aVar != null) {
                try {
                    C0061a.a(context, aVar.e.a(str), str2);
                } catch (Throwable th) {
                    e.a(th);
                }
                return;
            }
            return;
        }
    }

    public static synchronized void b(Context context, com.alipay.sdk.g.a aVar, String str, String str2) {
        synchronized (a.class) {
            if (context != null && aVar != null) {
                b.a(context, aVar.e, str, str2);
            }
        }
    }

    public static void a(com.alipay.sdk.g.a aVar, String str, Throwable th) {
        if (aVar != null && th != null && th.getClass() != null) {
            aVar.e.a(str, th.getClass().getSimpleName(), th);
        }
    }

    public static void a(com.alipay.sdk.g.a aVar, String str, String str2, Throwable th, String str3) {
        if (aVar != null) {
            aVar.e.a(str, str2, th, str3);
        }
    }

    public static void a(com.alipay.sdk.g.a aVar, String str, String str2, Throwable th) {
        if (aVar != null) {
            aVar.e.a(str, str2, th);
        }
    }

    public static void a(com.alipay.sdk.g.a aVar, String str, String str2, String str3) {
        if (aVar != null) {
            aVar.e.a(str, str2, str3);
        }
    }

    public static void b(com.alipay.sdk.g.a aVar, String str, String str2, String str3) {
        if (aVar != null) {
            aVar.e.b(str, str2, str3);
        }
    }

    public static void a(com.alipay.sdk.g.a aVar, String str, String str2) {
        if (aVar != null) {
            aVar.e.a(str, str2);
        }
    }
}
