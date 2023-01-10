package cn.missfresh.module.base.push;

import android.content.Context;
import android.text.format.DateUtils;
import cn.missfresh.module.base.R;
import cn.missfresh.module.base.datastatistics.StatisticsManager;
import cn.missfresh.module.base.manager.e;
import cn.missfresh.module.base.utils.ag;
import cn.missfresh.module.base.utils.f;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import cn.missfresh.utils.a.d;
import com.umeng.analytics.pro.ai;
import io.reactivex.disposables.b;
import io.reactivex.v;

/* compiled from: PushCheckUtils */
public class a {
    private static boolean a;
    private static b b;

    public static void a(boolean z) {
        a = z;
    }

    public static void a(boolean z, Context context) {
        AppMethodBeat.i(18750, false);
        a();
        if (!z) {
            if (ag.a(context) != 0) {
                AppMethodBeat.o(18750);
                return;
            } else if (System.currentTimeMillis() + DateUtils.MINUTE_IN_MILLIS < e.aA() + (((long) f.m()) * 86400000)) {
                AppMethodBeat.o(18750);
                return;
            } else {
                a(context);
            }
        }
        AppMethodBeat.o(18750);
    }

    private static void a(Context context) {
        AppMethodBeat.i(18752, false);
        if (context == null) {
            AppMethodBeat.o(18752);
            return;
        }
        cn.missfresh.module.base.utils.a.a.a().b().a(cn.missfresh.module.base.utils.a.a.a(2000, DateUtils.MINUTE_IN_MILLIS)).subscribe(new AnonymousClass1(context));
        AppMethodBeat.o(18752);
    }

    /* compiled from: PushCheckUtils */
    /* access modifiers changed from: package-private */
    /* renamed from: cn.missfresh.module.base.push.a$1  reason: invalid class name */
    public static class AnonymousClass1 implements v<Long> {
        final /* synthetic */ Context a;

        public void a(Long l) {
        }

        @Override // io.reactivex.v
        public void onError(Throwable th) {
        }

        AnonymousClass1(Context context) {
            this.a = context;
        }

        @Override // io.reactivex.v
        public /* synthetic */ void onNext(Object obj) {
            AppMethodBeat.i(18739, false);
            a((Long) obj);
            AppMethodBeat.o(18739);
        }

        @Override // io.reactivex.v
        public void onSubscribe(b bVar) {
            AppMethodBeat.i(18731, false);
            b unused = a.b = bVar;
            AppMethodBeat.o(18731);
        }

        @Override // io.reactivex.v
        public void onComplete() {
            AppMethodBeat.i(18736, false);
            a.a(this.a, "category");
            d.d("PushCheckUtils", "2 minite onComplete");
            AppMethodBeat.o(18736);
        }
    }

    public static void a() {
        AppMethodBeat.i(18755, false);
        b bVar = b;
        if (bVar != null && !bVar.isDisposed()) {
            b.dispose();
        }
        AppMethodBeat.o(18755);
    }

    public static void a(Context context, String str) {
        AppMethodBeat.i(18758, false);
        if (ag.a(context) != 0) {
            d.d("PushCheckUtils", "NotificationEnabled(context) != 0");
            AppMethodBeat.o(18758);
        } else if (!a) {
            AppMethodBeat.o(18758);
        } else {
            long aA = e.aA();
            long currentTimeMillis = System.currentTimeMillis();
            if (currentTimeMillis - aA > ((long) f.m()) * 86400000) {
                e.d(currentTimeMillis);
                new PushNoticeDialog(context, R.style.translucent, str).show();
                if ("home".equals(str)) {
                    StatisticsManager.c("exposure_zdypush_pop", ai.e, "zdypush_pop");
                } else if ("category".equals(str)) {
                    StatisticsManager.Y("exposure_zdypush_pop", ai.e, "zdypush_pop");
                } else if ("pay_success".equals(str)) {
                    StatisticsManager.i("exposure_zdypush_pop", ai.e, "zdypush_pop");
                }
            }
            AppMethodBeat.o(18758);
        }
    }
}
