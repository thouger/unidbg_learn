package cn.missfresh.module.user.location.service;

import android.content.Context;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import cn.missfresh.basiclib.utils.permission.c;
import cn.missfresh.location_api.bean.Location;
import cn.missfresh.location_api.d;
import cn.missfresh.location_api.e;
import cn.missfresh.module.base.datastatistics.StatisticsManager;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import cn.missfresh.tencent.b;
import com.xiaomi.mipush.sdk.Constants;

/* compiled from: LocationManager */
public class a implements e {
    private b a;
    private cn.missfresh.location.a b;
    private c c;
    private MutableLiveData<Integer> d = new MutableLiveData<>();
    private cn.missfresh.basiclib.ui.permission.a e = new AnonymousClass3();

    public a() {
        AppMethodBeat.i(7521, false);
        AppMethodBeat.o(7521);
    }

    public void a(Context context) {
        AppMethodBeat.i(7522, false);
        b(context);
        AppMethodBeat.o(7522);
    }

    private void b(Context context) {
        AppMethodBeat.i(7524, false);
        this.a = new b();
        this.a.a(context);
        this.b = cn.missfresh.location.b.a(this.a);
        this.b.a((d) new AnonymousClass1());
        this.b.a((e) this);
        this.b.b().a(new AnonymousClass2());
        AppMethodBeat.o(7524);
    }

    /* compiled from: LocationManager */
    /* access modifiers changed from: package-private */
    /* renamed from: cn.missfresh.module.user.location.service.a$1  reason: invalid class name */
    public class AnonymousClass1 implements d {
        AnonymousClass1() {
        }

        public void a() {
            AppMethodBeat.i(7486, false);
            cn.missfresh.utils.a.d.b("LocationManager", "onSuccess");
            AppMethodBeat.o(7486);
        }

        public void a(int i, String str) {
            AppMethodBeat.i(7488, false);
            cn.missfresh.utils.a.d.d("LocationManager", "onFail:" + i + Constants.ACCEPT_TIME_SEPARATOR_SP + str);
            AppMethodBeat.o(7488);
        }
    }

    /* compiled from: LocationManager */
    /* access modifiers changed from: package-private */
    /* renamed from: cn.missfresh.module.user.location.service.a$2  reason: invalid class name */
    public class AnonymousClass2 implements Observer<Location> {
        AnonymousClass2() {
        }

        @Override // androidx.lifecycle.Observer
        public /* synthetic */ void onChanged(Object obj) {
            AppMethodBeat.i(7496, false);
            a((Location) obj);
            AppMethodBeat.o(7496);
        }

        public void a(Location location) {
            AppMethodBeat.i(7494, false);
            a.this.b.c();
            if (location != null && location.getCode() == Location.ERROR_OK) {
                cn.missfresh.module.base.manager.c q = cn.missfresh.module.base.manager.c.q();
                q.c(location.getLat() + "");
                cn.missfresh.module.base.manager.c q2 = cn.missfresh.module.base.manager.c.q();
                q2.d(location.getLng() + "");
                StatisticsManager.a();
                cn.missfresh.utils.a.d.d("LocationManager", "\u4e0a\u62a5\u4f4d\u7f6e\u4fe1\u606f");
            }
            AppMethodBeat.o(7494);
        }
    }

    public cn.missfresh.location.a b() {
        return this.b;
    }

    public LiveData<Integer> c() {
        return this.d;
    }

    /* compiled from: LocationManager */
    /* renamed from: cn.missfresh.module.user.location.service.a$3  reason: invalid class name */
    class AnonymousClass3 implements cn.missfresh.basiclib.ui.permission.a {
        @Override // cn.missfresh.basiclib.ui.permission.a
        public void G_() {
        }

        @Override // cn.missfresh.basiclib.ui.permission.a
        public void a(String str, int i) {
        }

        AnonymousClass3() {
        }

        @Override // cn.missfresh.basiclib.ui.permission.b
        public void l_(String str) {
            AppMethodBeat.i(7516, false);
            a.this.d.setValue(0);
            AppMethodBeat.o(7516);
        }

        @Override // cn.missfresh.basiclib.ui.permission.b
        public void t_() {
            AppMethodBeat.i(7517, false);
            a.this.d.setValue(1);
            AppMethodBeat.o(7517);
        }
    }

    public void a() {
        AppMethodBeat.i(7528, false);
        if (this.c == null) {
            this.c = new c();
        }
        FragmentActivity fragmentActivity = (FragmentActivity) cn.missfresh.module.base.manager.a.a().b();
        if (fragmentActivity == null) {
            AppMethodBeat.o(7528);
            return;
        }
        b.a(fragmentActivity, this.e, this.c);
        AppMethodBeat.o(7528);
    }
}
