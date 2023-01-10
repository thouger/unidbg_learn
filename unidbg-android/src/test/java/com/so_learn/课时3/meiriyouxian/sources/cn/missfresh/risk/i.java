package cn.missfresh.risk;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import cn.missfresh.risk.f.h;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import com.android.internal.logging.nano.MetricsProto;
import io.reactivex.disposables.b;
import io.reactivex.q;
import io.reactivex.v;
import java.util.concurrent.TimeUnit;

/* compiled from: RiskNetManger */
public class i {
    private static i f;
    private boolean a = false;
    private String b;
    private String c;
    private String d;
    private BroadcastReceiver e;
    private b g;

    private i() {
    }

    public static i a() {
        AppMethodBeat.i(MetricsProto.MetricsEvent.APP_TRANSITION, false);
        if (f == null) {
            f = new i();
        }
        i iVar = f;
        AppMethodBeat.o(MetricsProto.MetricsEvent.APP_TRANSITION);
        return iVar;
    }

    public void b() {
        AppMethodBeat.i(MetricsProto.MetricsEvent.ACTION_CLICK_SETTINGS_SEARCH_RESULT, false);
        b bVar = this.g;
        if (bVar != null && !bVar.isDisposed()) {
            this.g.dispose();
            this.g = null;
        }
        f();
        f = null;
        AppMethodBeat.o(MetricsProto.MetricsEvent.ACTION_CLICK_SETTINGS_SEARCH_RESULT);
    }

    public void a(String str) {
        this.b = str;
    }

    public String c() {
        return this.b;
    }

    public void b(String str) {
        this.d = str;
    }

    public String d() {
        return this.d;
    }

    public void c(String str) {
        this.c = str;
    }

    public void e() {
        AppMethodBeat.i(770, false);
        if (!this.a) {
            this.a = true;
            this.e = new a(this, null);
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
            h.c.registerReceiver(this.e, intentFilter);
        }
        AppMethodBeat.o(770);
    }

    public void f() {
        AppMethodBeat.i(772, false);
        try {
            if (this.a) {
                h.c.unregisterReceiver(this.e);
                this.a = false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        AppMethodBeat.o(772);
    }

    public void g() {
        AppMethodBeat.i(776, false);
        if (h.a < 1) {
            f();
            AppMethodBeat.o(776);
            return;
        }
        if (cn.missfresh.risk.f.b.a(h.c)) {
            q.a(3, TimeUnit.SECONDS).a(cn.missfresh.basiclib.net.e.a.a).subscribe(new AnonymousClass1());
        } else {
            e();
        }
        AppMethodBeat.o(776);
    }

    /* compiled from: RiskNetManger */
    /* access modifiers changed from: package-private */
    /* renamed from: cn.missfresh.risk.i$1  reason: invalid class name */
    public class AnonymousClass1 implements v {
        @Override // io.reactivex.v
        public void onComplete() {
        }

        @Override // io.reactivex.v
        public void onError(Throwable th) {
        }

        AnonymousClass1() {
        }

        @Override // io.reactivex.v
        public void onSubscribe(b bVar) {
            AppMethodBeat.i(MetricsProto.MetricsEvent.ACTION_PERMISSION_DENIED_RECEIVE_WAP_PUSH, false);
            i.this.g = bVar;
            AppMethodBeat.o(MetricsProto.MetricsEvent.ACTION_PERMISSION_DENIED_RECEIVE_WAP_PUSH);
        }

        @Override // io.reactivex.v
        public void onNext(Object obj) {
            AppMethodBeat.i(MetricsProto.MetricsEvent.ACTION_PERMISSION_DENIED_RECEIVE_MMS, false);
            new h().a(i.this.b, i.this.c);
            h.a--;
            AppMethodBeat.o(MetricsProto.MetricsEvent.ACTION_PERMISSION_DENIED_RECEIVE_MMS);
        }
    }

    public void h() {
        AppMethodBeat.i(MetricsProto.MetricsEvent.APP_SPECIAL_PERMISSION_NOTIVIEW_DENY, false);
        if (h.b < 1) {
            f();
            AppMethodBeat.o(MetricsProto.MetricsEvent.APP_SPECIAL_PERMISSION_NOTIVIEW_DENY);
            return;
        }
        if (cn.missfresh.risk.f.b.a(h.c)) {
            q.a(3, TimeUnit.SECONDS).a(cn.missfresh.basiclib.net.e.a.a).subscribe(new AnonymousClass2());
        } else {
            e();
        }
        AppMethodBeat.o(MetricsProto.MetricsEvent.APP_SPECIAL_PERMISSION_NOTIVIEW_DENY);
    }

    /* compiled from: RiskNetManger */
    /* access modifiers changed from: package-private */
    /* renamed from: cn.missfresh.risk.i$2  reason: invalid class name */
    public class AnonymousClass2 implements v {
        @Override // io.reactivex.v
        public void onComplete() {
        }

        @Override // io.reactivex.v
        public void onError(Throwable th) {
        }

        AnonymousClass2() {
        }

        @Override // io.reactivex.v
        public void onSubscribe(b bVar) {
            AppMethodBeat.i(MetricsProto.MetricsEvent.ACTION_PERMISSION_REQUEST_READ_PHONE_NUMBERS, false);
            i.this.g = bVar;
            AppMethodBeat.o(MetricsProto.MetricsEvent.ACTION_PERMISSION_REQUEST_READ_PHONE_NUMBERS);
        }

        @Override // io.reactivex.v
        public void onNext(Object obj) {
            AppMethodBeat.i(740, false);
            new h().a(i.this.d);
            h.b--;
            AppMethodBeat.o(740);
        }
    }

    /* compiled from: RiskNetManger */
    /* access modifiers changed from: private */
    public class a extends BroadcastReceiver {
        private a() {
        }

        /* synthetic */ a(i iVar, AnonymousClass1 r2) {
            this();
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            AppMethodBeat.i(MetricsProto.MetricsEvent.SETTINGS_INPUT_CATEGORY, false);
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            if (connectivityManager == null) {
                AppMethodBeat.o(MetricsProto.MetricsEvent.SETTINGS_INPUT_CATEGORY);
                return;
            }
            NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
            if (activeNetworkInfo == null) {
                AppMethodBeat.o(MetricsProto.MetricsEvent.SETTINGS_INPUT_CATEGORY);
                return;
            }
            if (activeNetworkInfo.isConnected()) {
                if (h.a > 0) {
                    i.this.g();
                } else if (h.b > 0) {
                    i.this.h();
                } else {
                    i.this.f();
                }
            }
            AppMethodBeat.o(MetricsProto.MetricsEvent.SETTINGS_INPUT_CATEGORY);
        }
    }
}
