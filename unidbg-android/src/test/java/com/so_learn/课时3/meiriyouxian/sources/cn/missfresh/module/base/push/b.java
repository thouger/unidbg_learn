package cn.missfresh.module.base.push;

import android.app.Notification;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import cn.missfresh.module.base.common.providers.ILocationService;
import cn.missfresh.module.base.common.providers.IUserStartLocationService;
import cn.missfresh.module.base.datastatistics.StatisticsManager;
import cn.missfresh.module.base.manager.e;
import cn.missfresh.module.base.manager.h;
import cn.missfresh.module.base.utils.ag;
import cn.missfresh.module.base.utils.j;
import cn.missfresh.module.base.utils.r;
import cn.missfresh.push.MiPushActivity;
import cn.missfresh.pushlib.a;
import cn.missfresh.pushlib.c;
import cn.missfresh.pushlib.d;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import com.umeng.analytics.pro.ai;

/* compiled from: PushDelegate */
public class b {
    private static String d() {
        return "mf_device_id";
    }

    private static String e() {
        return "mf_user_id";
    }

    static /* synthetic */ a a(String str, String str2, String str3, String str4, String str5) {
        AppMethodBeat.i(18855, false);
        a b = b(str, str2, str3, str4, str5);
        AppMethodBeat.o(18855);
        return b;
    }

    static /* synthetic */ String a() {
        AppMethodBeat.i(18845, false);
        String e = e();
        AppMethodBeat.o(18845);
        return e;
    }

    static /* synthetic */ String a(int i) {
        AppMethodBeat.i(18850, false);
        String b = b(i);
        AppMethodBeat.o(18850);
        return b;
    }

    static /* synthetic */ String b() {
        AppMethodBeat.i(18846, false);
        String d = d();
        AppMethodBeat.o(18846);
        return d;
    }

    static /* synthetic */ String c() {
        AppMethodBeat.i(18852, false);
        String f = f();
        AppMethodBeat.o(18852);
        return f;
    }

    public static void a(Context context) {
        AppMethodBeat.i(18818, false);
        d a = d.a(context);
        try {
            a.a(new c.a().a("5a6edcef8f4a9d7b940000e4").b("b9e49234712181d54c3d5efd5c520f26").c("2882303761517407322").d("5811740743322").e("112399").f("e7cd5f50afa347a7ba92003ef4ae8a44").g("2i9nj03A6gkKcOKoOw88C40c8").h("640E024fAc3e32Bd37b077370C73f534").a(e.e()).b(true).a());
        } catch (Exception e) {
            e.printStackTrace();
        }
        cn.missfresh.pushlib.b c = c(context);
        a.a(c);
        MiPushActivity.a(c);
        AppMethodBeat.o(18818);
    }

    /* compiled from: PushDelegate */
    /* access modifiers changed from: package-private */
    /* renamed from: cn.missfresh.module.base.push.b$1  reason: invalid class name */
    public static class AnonymousClass1 implements cn.missfresh.pushlib.b {
        final /* synthetic */ Context a;

        AnonymousClass1(Context context) {
            this.a = context;
        }

        @Override // cn.missfresh.pushlib.b
        public void a(Context context, Bundle bundle) {
            AppMethodBeat.i(18770, false);
            a(context);
            if (bundle != null) {
                String string = bundle.getString("bucketId");
                j.a(context, bundle, 1);
                StatisticsManager.f("open_app", "open_type", "2", "push_id", string, "push_access", Integer.valueOf(ag.a(context)));
            } else {
                j.c(context);
                StatisticsManager.f("open_app", "open_type", "2", "push_access", Integer.valueOf(ag.a(context)));
            }
            AppMethodBeat.o(18770);
        }

        @Override // cn.missfresh.pushlib.b
        public void a(String str) {
            AppMethodBeat.i(18775, false);
            cn.missfresh.utils.a.d.d("PushDelegate", "----Push Token----\uff1a" + str);
            String a = e.o() ? b.a() : b.b();
            String a2 = e.o() ? b.a(e.p().getUser_id()) : b.c();
            d.a(this.a).a(a, a2, b.a(str, a, a2, "", ""));
            h.a().a(str, e.o(), e.n());
            AppMethodBeat.o(18775);
        }

        @Override // cn.missfresh.pushlib.b
        public void a(String str, String str2) {
            AppMethodBeat.i(18780, false);
            String a = e.o() ? b.a() : b.b();
            String a2 = e.o() ? b.a(e.p().getUser_id()) : b.c();
            d.a(this.a).a(a, a2, b.a("", a, a2, str, str2));
            h.a().a("", e.o(), e.n());
            AppMethodBeat.o(18780);
        }

        @Override // cn.missfresh.pushlib.b
        public void a(Notification.Builder builder, String str) {
            AppMethodBeat.i(18783, false);
            if (Build.VERSION.SDK_INT >= 16) {
                builder.setStyle(new Notification.BigPictureStyle().bigPicture((Bitmap) cn.missfresh.lib.image.c.b(this.a).a(str).d().k()));
            }
            AppMethodBeat.o(18783);
        }

        private void a(Context context) {
            IUserStartLocationService iUserStartLocationService;
            AppMethodBeat.i(18789, false);
            if (context != null) {
                try {
                    if (cn.missfresh.utils.b.a(cn.missfresh.risk.h.b())) {
                        cn.missfresh.risk.h.a(context);
                        cn.missfresh.risk.h.a(new cn.missfresh.module.base.c.a());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            ILocationService iLocationService = (ILocationService) com.alibaba.android.arouter.b.a.a().a("/user/location_service").navigation();
            if (iLocationService != null && ((iLocationService.d() == null || iLocationService.d().a() == null) && (iUserStartLocationService = (IUserStartLocationService) com.alibaba.android.arouter.b.a.a().a("/user/location_address_service").navigation()) != null)) {
                iUserStartLocationService.a();
            }
            AppMethodBeat.o(18789);
        }
    }

    private static cn.missfresh.pushlib.b c(Context context) {
        AppMethodBeat.i(18821, false);
        AnonymousClass1 r1 = new AnonymousClass1(context);
        AppMethodBeat.o(18821);
        return r1;
    }

    public static void a(Context context, int i) {
        AppMethodBeat.i(18824, false);
        d.a(context).a(d(), f());
        String e = e();
        String b = b(i);
        String a = d.a(context).a();
        d.a(context).a(e, b, a(a, e, b));
        h.a().a(a, true, e.n());
        AppMethodBeat.o(18824);
    }

    public static void b(Context context) {
        AppMethodBeat.i(18827, false);
        d.a(context).a(e(), b(e.p().getUser_id()));
        String d = d();
        String f = f();
        String a = d.a(context).a();
        d.a(context).a(d, f, a(a, d, f));
        h.a().a(a, false, e.n());
        AppMethodBeat.o(18827);
    }

    private static String f() {
        AppMethodBeat.i(18832, false);
        String i = r.i();
        AppMethodBeat.o(18832);
        return i;
    }

    private static String b(int i) {
        AppMethodBeat.i(18836, false);
        String str = "user_id_" + i;
        AppMethodBeat.o(18836);
        return str;
    }

    /* compiled from: PushDelegate */
    /* access modifiers changed from: package-private */
    /* renamed from: cn.missfresh.module.base.push.b$2  reason: invalid class name */
    public static class AnonymousClass2 implements a {
        final /* synthetic */ String a;
        final /* synthetic */ String b;
        final /* synthetic */ String c;
        final /* synthetic */ String d;
        final /* synthetic */ String e;

        AnonymousClass2(String str, String str2, String str3, String str4, String str5) {
            this.a = str;
            this.b = str2;
            this.c = str3;
            this.d = str4;
            this.e = str5;
        }

        @Override // cn.missfresh.pushlib.a
        public void a(boolean z, String str) {
            AppMethodBeat.i(18797, false);
            if (z) {
                StatisticsManager.X("succ", "pushToken", this.a, "aliasType", this.b, "alias", this.c);
            } else {
                StatisticsManager.X("fail", ai.az, this.d, "s1", this.e, "aliasType", this.b, "alias", this.c);
            }
            AppMethodBeat.o(18797);
        }
    }

    private static a b(String str, String str2, String str3, String str4, String str5) {
        AppMethodBeat.i(18841, false);
        AnonymousClass2 r1 = new AnonymousClass2(str, str2, str3, str4, str5);
        AppMethodBeat.o(18841);
        return r1;
    }

    /* compiled from: PushDelegate */
    /* access modifiers changed from: package-private */
    /* renamed from: cn.missfresh.module.base.push.b$3  reason: invalid class name */
    public static class AnonymousClass3 implements a {
        final /* synthetic */ String a;
        final /* synthetic */ String b;
        final /* synthetic */ String c;

        AnonymousClass3(String str, String str2, String str3) {
            this.a = str;
            this.b = str2;
            this.c = str3;
        }

        @Override // cn.missfresh.pushlib.a
        public void a(boolean z, String str) {
            AppMethodBeat.i(18810, false);
            if (z) {
                StatisticsManager.X("succ", "pushToken", this.a, "aliasType", this.b, "alias", this.c);
            } else {
                StatisticsManager.X("fail", "aliasType", this.b, "alias", this.c);
            }
            AppMethodBeat.o(18810);
        }
    }

    private static a a(String str, String str2, String str3) {
        AppMethodBeat.i(18843, false);
        AnonymousClass3 r1 = new AnonymousClass3(str, str2, str3);
        AppMethodBeat.o(18843);
        return r1;
    }
}
