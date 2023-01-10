package com.vivo.push;

import android.content.Context;
import android.os.SystemClock;
import android.telephony.PreciseDisconnectCause;
import android.text.TextUtils;
import android.util.SparseArray;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import com.tencent.imsdk.BaseConstants;
import com.vivo.push.b.b;
import com.vivo.push.b.c;
import com.vivo.push.b.g;
import com.vivo.push.b.z;
import com.vivo.push.util.ContextDelegate;
import com.vivo.push.util.n;
import com.vivo.push.util.r;
import com.vivo.push.util.v;
import com.vivo.push.util.y;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: PushClientManager */
public final class d {
    private static volatile d m;
    long a = -1;
    long b = -1;
    long c = -1;
    long d = -1;
    public Context e;
    public boolean f = true;
    public com.vivo.push.util.a g;
    String h;
    public String i;
    public boolean j;
    public IPushClientFactory k = new c();
    public int l;
    private long n = -1;
    private long o = -1;
    private SparseArray<a> p = new SparseArray<>();
    private int q = 0;
    private Boolean r;
    private Long s;

    private d() {
        AppMethodBeat.i(PreciseDisconnectCause.REMOTE_CALL_DECLINE, false);
        AppMethodBeat.o(PreciseDisconnectCause.REMOTE_CALL_DECLINE);
    }

    public static synchronized d a() {
        d dVar;
        synchronized (d.class) {
            AppMethodBeat.i(PreciseDisconnectCause.DATA_DISABLED, false);
            if (m == null) {
                m = new d();
            }
            dVar = m;
            AppMethodBeat.o(PreciseDisconnectCause.DATA_DISABLED);
        }
        return dVar;
    }

    public final synchronized void a(Context context) {
        AppMethodBeat.i(2508, false);
        if (this.e == null) {
            this.e = ContextDelegate.getContext(context).getApplicationContext();
            this.j = r.a(context, context.getPackageName(), "com.vivo.pushclient.action.RECEIVE");
            v.b().a(this.e);
            a(new g());
            this.g = new com.vivo.push.util.a();
            this.g.a(context, "com.vivo.push_preferences.appconfig_v1");
            this.h = e();
            this.i = this.g.a("APP_ALIAS");
        }
        AppMethodBeat.o(2508);
    }

    public final List<String> b() {
        AppMethodBeat.i(2510, false);
        String a2 = this.g.a("APP_TAGS");
        ArrayList arrayList = new ArrayList();
        try {
            if (TextUtils.isEmpty(a2)) {
                AppMethodBeat.o(2510);
                return arrayList;
            }
            Iterator<String> keys = new JSONObject(a2).keys();
            while (keys.hasNext()) {
                arrayList.add(keys.next());
            }
            AppMethodBeat.o(2510);
            return arrayList;
        } catch (JSONException unused) {
            this.g.c("APP_TAGS");
            arrayList.clear();
            n.d("PushClientManager", "getTags error");
        }
    }

    public final void a(List<String> list) {
        JSONObject jSONObject;
        AppMethodBeat.i(2512, false);
        try {
            if (list.size() <= 0) {
                AppMethodBeat.o(2512);
                return;
            }
            String a2 = this.g.a("APP_TAGS");
            if (TextUtils.isEmpty(a2)) {
                jSONObject = new JSONObject();
            } else {
                jSONObject = new JSONObject(a2);
            }
            for (String str : list) {
                jSONObject.put(str, System.currentTimeMillis());
            }
            String jSONObject2 = jSONObject.toString();
            if (TextUtils.isEmpty(jSONObject2)) {
                this.g.c("APP_TAGS");
                AppMethodBeat.o(2512);
                return;
            }
            this.g.a("APP_TAGS", jSONObject2);
            AppMethodBeat.o(2512);
        } catch (JSONException e) {
            e.printStackTrace();
            this.g.c("APP_TAGS");
            AppMethodBeat.o(2512);
        }
    }

    public final void b(List<String> list) {
        JSONObject jSONObject;
        AppMethodBeat.i(2514, false);
        try {
            if (list.size() <= 0) {
                AppMethodBeat.o(2514);
                return;
            }
            String a2 = this.g.a("APP_TAGS");
            if (TextUtils.isEmpty(a2)) {
                jSONObject = new JSONObject();
            } else {
                jSONObject = new JSONObject(a2);
            }
            for (String str : list) {
                jSONObject.remove(str);
            }
            String jSONObject2 = jSONObject.toString();
            if (TextUtils.isEmpty(jSONObject2)) {
                this.g.c("APP_TAGS");
                AppMethodBeat.o(2514);
                return;
            }
            this.g.a("APP_TAGS", jSONObject2);
            AppMethodBeat.o(2514);
        } catch (JSONException e) {
            e.printStackTrace();
            this.g.c("APP_TAGS");
            AppMethodBeat.o(2514);
        }
    }

    public final boolean c() {
        AppMethodBeat.i(2515, false);
        if (this.e == null) {
            n.d("PushClientManager", "support:context is null");
            AppMethodBeat.o(2515);
            return false;
        }
        this.r = Boolean.valueOf(f());
        boolean booleanValue = this.r.booleanValue();
        AppMethodBeat.o(2515);
        return booleanValue;
    }

    public final void d() {
        AppMethodBeat.i(2519, false);
        this.i = null;
        this.g.c("APP_ALIAS");
        AppMethodBeat.o(2519);
    }

    /* access modifiers changed from: package-private */
    public final String e() {
        AppMethodBeat.i(2521, false);
        String a2 = this.g.a("APP_TOKEN");
        if (!TextUtils.isEmpty(a2)) {
            Context context = this.e;
            if (y.a(context, context.getPackageName(), a2)) {
                this.g.a();
                a2 = null;
            }
        }
        AppMethodBeat.o(2521);
        return a2;
    }

    public final void a(String str) {
        AppMethodBeat.i(2522, false);
        this.h = str;
        this.g.a("APP_TOKEN", this.h);
        AppMethodBeat.o(2522);
    }

    /* compiled from: PushClientManager */
    /* renamed from: com.vivo.push.d$1  reason: invalid class name */
    class AnonymousClass1 implements IPushActionListener {
        final /* synthetic */ a a;

        AnonymousClass1(a aVar) {
            this.a = aVar;
        }

        @Override // com.vivo.push.IPushActionListener
        public final void onStateChanged(int i) {
            AppMethodBeat.i(2456, false);
            if (i == 0) {
                Object[] objArr = this.a.c;
                if (objArr == null || objArr.length == 0) {
                    n.a("PushClientManager", "bind app result is null");
                    AppMethodBeat.o(2456);
                    return;
                }
                d.this.a((String) this.a.c[0]);
                AppMethodBeat.o(2456);
                return;
            }
            d dVar = d.this;
            dVar.h = null;
            dVar.g.c("APP_TOKEN");
            AppMethodBeat.o(2456);
        }
    }

    /* access modifiers changed from: package-private */
    public final a a(b bVar, IPushActionListener iPushActionListener) {
        AppMethodBeat.i(2523, false);
        a aVar = new a(bVar, iPushActionListener);
        String a2 = a(aVar);
        bVar.e = a2;
        aVar.b = new AnonymousClass2(bVar, a2);
        AppMethodBeat.o(2523);
        return aVar;
    }

    /* compiled from: PushClientManager */
    /* renamed from: com.vivo.push.d$2  reason: invalid class name */
    class AnonymousClass2 implements Runnable {
        final /* synthetic */ b a;
        final /* synthetic */ String b;

        AnonymousClass2(b bVar, String str) {
            this.a = bVar;
            this.b = str;
        }

        @Override // java.lang.Runnable
        public final void run() {
            AppMethodBeat.i(2467, false);
            d.this.a(this.a);
            d.this.c(this.b);
            AppMethodBeat.o(2467);
        }
    }

    /* compiled from: PushClientManager */
    /* renamed from: com.vivo.push.d$3  reason: invalid class name */
    class AnonymousClass3 implements IPushActionListener {
        AnonymousClass3() {
        }

        @Override // com.vivo.push.IPushActionListener
        public final void onStateChanged(int i) {
            AppMethodBeat.i(2476, false);
            if (i == 0) {
                d dVar = d.this;
                dVar.h = "";
                dVar.g.a("APP_TOKEN", "");
                d.this.d();
                d.this.g.c("APP_TAGS");
                AppMethodBeat.o(2476);
                return;
            }
            d dVar2 = d.this;
            dVar2.h = null;
            dVar2.g.c("APP_TOKEN");
            AppMethodBeat.o(2476);
        }
    }

    /* compiled from: PushClientManager */
    /* renamed from: com.vivo.push.d$4  reason: invalid class name */
    class AnonymousClass4 implements Runnable {
        final /* synthetic */ b a;
        final /* synthetic */ String b;

        AnonymousClass4(b bVar, String str) {
            this.a = bVar;
            this.b = str;
        }

        @Override // java.lang.Runnable
        public final void run() {
            AppMethodBeat.i(2483, false);
            d.this.a(this.a);
            d.this.c(this.b);
            AppMethodBeat.o(2483);
        }
    }

    public final void a(String str, int i, Object... objArr) {
        AppMethodBeat.i(2525, false);
        a b = b(str);
        if (b != null) {
            b.a(i, objArr);
            AppMethodBeat.o(2525);
            return;
        }
        n.d("PushClientManager", "notifyApp token is null");
        AppMethodBeat.o(2525);
    }

    static boolean a(long j) {
        AppMethodBeat.i(2725, false);
        long elapsedRealtime = SystemClock.elapsedRealtime();
        if (j == -1 || elapsedRealtime <= j || elapsedRealtime >= j + 2000) {
            AppMethodBeat.o(2725);
            return true;
        }
        AppMethodBeat.o(2725);
        return false;
    }

    public final void a(String str, int i) {
        AppMethodBeat.i(2729, false);
        a b = b(str);
        if (b != null) {
            b.a(i, new Object[0]);
            AppMethodBeat.o(2729);
            return;
        }
        n.d("PushClientManager", "notifyStatusChanged token is null");
        AppMethodBeat.o(2729);
    }

    /* access modifiers changed from: package-private */
    public final synchronized String a(a aVar) {
        String num;
        AppMethodBeat.i(2733, false);
        this.p.put(this.q, aVar);
        int i = this.q;
        this.q = i + 1;
        num = Integer.toString(i);
        AppMethodBeat.o(2733);
        return num;
    }

    /* access modifiers changed from: package-private */
    public final synchronized a b(String str) {
        AppMethodBeat.i(2737, false);
        if (str != null) {
            try {
                int parseInt = Integer.parseInt(str);
                a aVar = (a) this.p.get(parseInt);
                this.p.delete(parseInt);
                AppMethodBeat.o(2737);
                return aVar;
            } catch (Exception unused) {
            }
        }
        AppMethodBeat.o(2737);
        return null;
    }

    /* access modifiers changed from: package-private */
    public final void a(ArrayList<String> arrayList, IPushActionListener iPushActionListener) {
        AppMethodBeat.i(2742, false);
        Context context = this.e;
        if (context == null) {
            if (iPushActionListener != null) {
                iPushActionListener.onStateChanged(102);
            }
            AppMethodBeat.o(2742);
            return;
        }
        z zVar = new z(true, context.getPackageName(), arrayList);
        zVar.h = 500;
        if (!this.j) {
            a(zVar);
            if (iPushActionListener != null) {
                iPushActionListener.onStateChanged(0);
            }
            AppMethodBeat.o(2742);
        } else if (!f()) {
            if (iPushActionListener != null) {
                iPushActionListener.onStateChanged(101);
            }
            AppMethodBeat.o(2742);
        } else if (!a(this.n)) {
            if (iPushActionListener != null) {
                iPushActionListener.onStateChanged(1002);
            }
            AppMethodBeat.o(2742);
        } else {
            this.n = SystemClock.elapsedRealtime();
            String a2 = a(new a(zVar, iPushActionListener));
            zVar.e = a2;
            if (TextUtils.isEmpty(this.h)) {
                a(a2, 20001);
                AppMethodBeat.o(2742);
            } else if (arrayList.size() < 0) {
                a(a2, 20002);
                AppMethodBeat.o(2742);
            } else {
                if (arrayList.size() + b().size() > 500) {
                    a(a2, BaseConstants.ERR_SVR_MSG_NET_ERROR);
                    AppMethodBeat.o(2742);
                    return;
                }
                Iterator<String> it2 = arrayList.iterator();
                while (it2.hasNext()) {
                    if (((long) it2.next().length()) > 70) {
                        a(a2, 20003);
                        AppMethodBeat.o(2742);
                        return;
                    }
                }
                a(zVar);
                c(a2);
                AppMethodBeat.o(2742);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public final void b(ArrayList<String> arrayList, IPushActionListener iPushActionListener) {
        AppMethodBeat.i(2747, false);
        Context context = this.e;
        if (context == null) {
            if (iPushActionListener != null) {
                iPushActionListener.onStateChanged(102);
            }
            AppMethodBeat.o(2747);
            return;
        }
        z zVar = new z(false, context.getPackageName(), arrayList);
        zVar.h = 500;
        if (!this.j) {
            a(zVar);
            if (iPushActionListener != null) {
                iPushActionListener.onStateChanged(0);
            }
            AppMethodBeat.o(2747);
        } else if (!f()) {
            if (iPushActionListener != null) {
                iPushActionListener.onStateChanged(101);
            }
            AppMethodBeat.o(2747);
        } else if (!a(this.o)) {
            if (iPushActionListener != null) {
                iPushActionListener.onStateChanged(1002);
            }
            AppMethodBeat.o(2747);
        } else {
            this.o = SystemClock.elapsedRealtime();
            String a2 = a(new a(zVar, iPushActionListener));
            zVar.e = a2;
            if (TextUtils.isEmpty(this.h)) {
                a(a2, 20001);
                AppMethodBeat.o(2747);
            } else if (arrayList.size() < 0) {
                a(a2, 20002);
                AppMethodBeat.o(2747);
            } else if (arrayList.size() > 500) {
                a(a2, BaseConstants.ERR_SVR_MSG_NET_ERROR);
                AppMethodBeat.o(2747);
            } else {
                Iterator<String> it2 = arrayList.iterator();
                while (it2.hasNext()) {
                    if (((long) it2.next().length()) > 70) {
                        a(a2, 20003);
                        AppMethodBeat.o(2747);
                        return;
                    }
                }
                a(zVar);
                c(a2);
                AppMethodBeat.o(2747);
            }
        }
    }

    public final void a(g gVar) {
        AppMethodBeat.i(2752, false);
        Context context = a().e;
        if (gVar == null) {
            n.a("PushClientManager", "sendCommand, null command!");
            if (context != null) {
                n.c(context, "[\u6267\u884c\u6307\u4ee4\u5931\u8d25]\u6307\u4ee4\u7a7a\uff01");
            }
            AppMethodBeat.o(2752);
            return;
        }
        e createTask = this.k.createTask(gVar);
        if (createTask == null) {
            n.a("PushClientManager", "sendCommand, null command task! pushCommand = " + gVar);
            if (context != null) {
                n.c(context, "[\u6267\u884c\u6307\u4ee4\u5931\u8d25]\u6307\u4ee4" + gVar + "\u4efb\u52a1\u7a7a\uff01");
            }
            AppMethodBeat.o(2752);
            return;
        }
        n.d("PushClientManager", "client--sendCommand, command = " + gVar);
        f.a(createTask);
        AppMethodBeat.o(2752);
    }

    /* compiled from: PushClientManager */
    /* access modifiers changed from: package-private */
    /* renamed from: com.vivo.push.d$5  reason: invalid class name */
    public class AnonymousClass5 implements Runnable {
        final /* synthetic */ String a;

        AnonymousClass5(String str) {
            this.a = str;
        }

        @Override // java.lang.Runnable
        public final void run() {
            AppMethodBeat.i(2490, false);
            a b = d.this.b(this.a);
            if (b != null) {
                b.a(1003, new Object[0]);
            }
            AppMethodBeat.o(2490);
        }
    }

    /* access modifiers changed from: package-private */
    public final void c(String str) {
        AppMethodBeat.i(2756, false);
        f.a(new AnonymousClass5(str));
        AppMethodBeat.o(2756);
    }

    /* access modifiers changed from: package-private */
    public final boolean f() {
        long j;
        boolean z = false;
        AppMethodBeat.i(2759, false);
        if (this.r == null) {
            Context context = this.e;
            if (context == null) {
                j = -1;
            } else {
                if (this.s == null) {
                    this.s = Long.valueOf(y.b(context));
                }
                j = this.s.longValue();
            }
            if (j >= 1230 && y.e(this.e)) {
                z = true;
            }
            this.r = Boolean.valueOf(z);
        }
        boolean booleanValue = this.r.booleanValue();
        AppMethodBeat.o(2759);
        return booleanValue;
    }

    /* compiled from: PushClientManager */
    public static class a {
        IPushActionListener a;
        Runnable b;
        Object[] c;
        private IPushActionListener d;
        private c e;

        public a(c cVar, IPushActionListener iPushActionListener) {
            this.e = cVar;
            this.d = iPushActionListener;
        }

        public final void a(int i, Object... objArr) {
            AppMethodBeat.i(2496, false);
            this.c = objArr;
            IPushActionListener iPushActionListener = this.a;
            if (iPushActionListener != null) {
                iPushActionListener.onStateChanged(i);
            }
            IPushActionListener iPushActionListener2 = this.d;
            if (iPushActionListener2 != null) {
                iPushActionListener2.onStateChanged(i);
            }
            AppMethodBeat.o(2496);
        }

        public final void a() {
            AppMethodBeat.i(2499, false);
            Runnable runnable = this.b;
            if (runnable == null) {
                n.a("PushClientManager", "task is null");
                AppMethodBeat.o(2499);
                return;
            }
            runnable.run();
            AppMethodBeat.o(2499);
        }
    }
}
