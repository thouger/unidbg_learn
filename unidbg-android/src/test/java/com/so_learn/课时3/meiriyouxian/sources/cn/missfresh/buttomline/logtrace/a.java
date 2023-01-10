package cn.missfresh.buttomline.logtrace;

import android.app.Application;
import android.content.Context;
import android.text.TextUtils;
import cn.missfresh.buttomline.abtest.ABTest;
import cn.missfresh.buttomline.logtrace.b.b;
import cn.missfresh.buttomline.logtrace.bean.LogBean;
import cn.missfresh.buttomline.logtrace.bean.RequestBaseBean;
import cn.missfresh.buttomline.logtrace.e.d;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import cn.missfresh.utils.c;
import java.util.HashMap;
import java.util.Map;

/* compiled from: Census */
public class a implements cn.missfresh.utils.a.a {
    private static Context a;
    private static boolean b;
    private static b c;
    private static boolean d;

    static /* synthetic */ void a(a aVar, LogBean logBean) {
        AppMethodBeat.i(16616, false);
        aVar.a(logBean);
        AppMethodBeat.o(16616);
    }

    public a(Context context, boolean z) {
        AppMethodBeat.i(16584, false);
        b = z;
        if (!d) {
            a = context.getApplicationContext();
            c = new cn.missfresh.buttomline.logtrace.a.a();
            ((Application) a).registerActivityLifecycleCallbacks(cn.missfresh.buttomline.logtrace.e.a.a());
            d = true;
        }
        AppMethodBeat.o(16584);
    }

    public static cn.missfresh.basiclib.thread.b a() {
        AppMethodBeat.i(16587, false);
        cn.missfresh.basiclib.thread.b a2 = cn.missfresh.basiclib.thread.b.a("CensusConfig");
        AppMethodBeat.o(16587);
        return a2;
    }

    public static void a(boolean z) {
        AppMethodBeat.i(16590, false);
        b bVar = c;
        if (bVar != null) {
            bVar.a();
        }
        if (z) {
            c.b(cn.missfresh.buttomline.logtrace.a.b.c, cn.missfresh.buttomline.logtrace.a.b.b, false);
        }
        AppMethodBeat.o(16590);
    }

    public static void a(HashMap<String, String> hashMap) {
        b.a = hashMap;
    }

    /* compiled from: Census */
    /* renamed from: cn.missfresh.buttomline.logtrace.a$1  reason: invalid class name */
    class AnonymousClass1 extends cn.missfresh.basiclib.thread.b.a {
        final /* synthetic */ Object a;

        AnonymousClass1(Object obj) {
            this.a = obj;
        }

        @Override // cn.missfresh.basiclib.thread.b.a
        public void runInTryCatch() {
            AppMethodBeat.i(16546, false);
            LogBean logBean = (LogBean) this.a;
            a.a(a.this, logBean);
            cn.missfresh.buttomline.logtrace.b.a a = cn.missfresh.buttomline.logtrace.c.c.a(2);
            logBean.setTag(RequestBaseBean.TAG_TRACE);
            a.a(logBean);
            a.a(d.a(logBean));
            AppMethodBeat.o(16546);
        }
    }

    @Override // cn.missfresh.utils.a.a
    public void a(Object obj) {
        AppMethodBeat.i(16594, false);
        a().a(new AnonymousClass1(obj));
        AppMethodBeat.o(16594);
    }

    /* compiled from: Census */
    /* renamed from: cn.missfresh.buttomline.logtrace.a$2  reason: invalid class name */
    class AnonymousClass2 extends cn.missfresh.basiclib.thread.b.a {
        final /* synthetic */ Object a;

        AnonymousClass2(Object obj) {
            this.a = obj;
        }

        @Override // cn.missfresh.basiclib.thread.b.a
        public void runInTryCatch() {
            AppMethodBeat.i(16552, false);
            LogBean logBean = (LogBean) this.a;
            a.a(a.this, logBean);
            cn.missfresh.buttomline.logtrace.b.a a = cn.missfresh.buttomline.logtrace.c.c.a(2);
            logBean.setTag(RequestBaseBean.TAG_TRACE);
            a.a(logBean);
            Map<String, Object> a2 = cn.missfresh.basiclib.utils.b.a(logBean);
            HashMap hashMap = new HashMap();
            if (a2 != null) {
                for (String str : a2.keySet()) {
                    Object obj = a2.get(str);
                    if (obj != null && !TextUtils.isEmpty(String.valueOf(obj))) {
                        hashMap.put(str, String.valueOf(obj));
                    }
                }
            }
            a.c.a(RequestBaseBean.TAG_TRACE, hashMap);
            AppMethodBeat.o(16552);
        }
    }

    @Override // cn.missfresh.utils.a.a
    @Deprecated
    public void b(Object obj) {
        AppMethodBeat.i(16597, false);
        a().a(new AnonymousClass2(obj));
        AppMethodBeat.o(16597);
    }

    /* compiled from: Census */
    /* renamed from: cn.missfresh.buttomline.logtrace.a$3  reason: invalid class name */
    class AnonymousClass3 extends cn.missfresh.basiclib.thread.b.a {
        final /* synthetic */ String a;
        final /* synthetic */ String b;

        AnonymousClass3(String str, String str2) {
            this.a = str;
            this.b = str2;
        }

        @Override // cn.missfresh.basiclib.thread.b.a
        public void runInTryCatch() {
            AppMethodBeat.i(16571, false);
            cn.missfresh.buttomline.logtrace.b.a a = cn.missfresh.buttomline.logtrace.c.c.a(0);
            LogBean logBean = new LogBean();
            a.a(a.this, logBean);
            logBean.setTag(RequestBaseBean.TAG_LOG);
            logBean.setType(this.a);
            logBean.setInfo_str(this.b);
            a.a(logBean);
            a.b(d.a(logBean));
            AppMethodBeat.o(16571);
        }
    }

    @Override // cn.missfresh.utils.a.a
    public void a(String str, String str2) {
        AppMethodBeat.i(16607, false);
        a().a(new AnonymousClass3(str, str2));
        AppMethodBeat.o(16607);
    }

    private void a(LogBean logBean) {
        AppMethodBeat.i(16612, false);
        if (TextUtils.isEmpty(logBean.getMobile())) {
            logBean.setMobile(b.a.get(ABTest.KEY_MOBILE));
        }
        if (TextUtils.isEmpty(logBean.getUid())) {
            logBean.setUid(b.a.get("userId"));
        }
        AppMethodBeat.o(16612);
    }

    public static Context b() {
        return a;
    }

    public static boolean c() {
        return b;
    }
}
