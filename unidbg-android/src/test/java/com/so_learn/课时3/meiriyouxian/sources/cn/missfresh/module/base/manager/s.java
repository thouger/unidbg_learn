package cn.missfresh.module.base.manager;

import android.app.Activity;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import androidx.core.app.NotificationCompat;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.Observer;
import cn.missfresh.module.base.R;
import cn.missfresh.module.base.bean.ConfigurationBean;
import cn.missfresh.module.base.common.api.IApplicationDelegateService;
import cn.missfresh.module.base.common.providers.ILocationService;
import cn.missfresh.module.base.common.providers.ISurveyService;
import cn.missfresh.module.base.manager.view.DownloadDialogFragment;
import cn.missfresh.module.base.support.dialog.e;
import cn.missfresh.module.base.utils.r;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import cn.missfresh.utils.a.d;
import com.alibaba.fastjson.JSON;
import java.io.File;
import java.lang.ref.WeakReference;

/* compiled from: UpgradeManager */
public class s {
    public static int a = 0;
    private static String h = "";
    private static String i = "";
    private static volatile s m;
    NotificationManager b;
    NotificationCompat.Builder c;
    Handler d = new a();
    e.c e = new AnonymousClass2();
    DialogInterface.OnCancelListener f = new AnonymousClass3();
    private WeakReference<FragmentActivity> g;
    private int j = 0;
    private int k = 0;
    private DownloadDialogFragment l;
    private c n;
    private b o;
    private boolean p = false;
    private String q = "";
    private ILocationService r = ((ILocationService) com.alibaba.android.arouter.b.a.a().a("/user/location_service").navigation());
    private ConfigurationBean.UpgradeBean s;
    private ISurveyService t;

    /* compiled from: UpgradeManager */
    public interface b {
    }

    /* compiled from: UpgradeManager */
    public interface c {
        void a(String str, boolean z);
    }

    private boolean a(String str) {
        return false;
    }

    static /* synthetic */ void a(s sVar) {
        AppMethodBeat.i(14894, false);
        sVar.e();
        AppMethodBeat.o(14894);
    }

    static /* synthetic */ void a(s sVar, ConfigurationBean.UpgradeBean upgradeBean, boolean z) {
        AppMethodBeat.i(14896, false);
        sVar.a(upgradeBean, z);
        AppMethodBeat.o(14896);
    }

    static /* synthetic */ void a(s sVar, String str) {
        AppMethodBeat.i(14917, false);
        sVar.c(str);
        AppMethodBeat.o(14917);
    }

    static /* synthetic */ void a(s sVar, String str, int i2) {
        AppMethodBeat.i(14899, false);
        sVar.a(str, i2);
        AppMethodBeat.o(14899);
    }

    static /* synthetic */ void c(s sVar) {
        AppMethodBeat.i(14902, false);
        sVar.i();
        AppMethodBeat.o(14902);
    }

    static /* synthetic */ void c(s sVar, int i2) {
        AppMethodBeat.i(14909, false);
        sVar.a(i2);
        AppMethodBeat.o(14909);
    }

    static /* synthetic */ void d(s sVar, int i2) {
        AppMethodBeat.i(14912, false);
        sVar.b(i2);
        AppMethodBeat.o(14912);
    }

    static /* synthetic */ void f(s sVar) {
        AppMethodBeat.i(14914, false);
        sVar.k();
        AppMethodBeat.o(14914);
    }

    private s() {
        AppMethodBeat.i(14841, false);
        AppMethodBeat.o(14841);
    }

    public static s a() {
        AppMethodBeat.i(14844, false);
        d.d("UpgradeManager", "getBaseApplication...begin");
        if (m == null) {
            synchronized (s.class) {
                try {
                    if (m == null) {
                        m = new s();
                    }
                } catch (Throwable th) {
                    AppMethodBeat.o(14844);
                    throw th;
                }
            }
        }
        s sVar = m;
        AppMethodBeat.o(14844);
        return sVar;
    }

    public void a(c cVar) {
        this.n = cVar;
    }

    public void a(b bVar) {
        this.o = bVar;
    }

    public void a(boolean z) {
        AppMethodBeat.i(14848, false);
        this.p = z;
        if (z) {
            g();
        }
        AppMethodBeat.o(14848);
    }

    private void a(Context context) {
        AppMethodBeat.i(14850, false);
        h = r.a(context);
        i = h + File.separator + "MissFresh.apk";
        StringBuilder sb = new StringBuilder();
        sb.append("UpgradeManager init() saveFileName:");
        sb.append(i);
        d.d("UpgradeManager", sb.toString());
        AppMethodBeat.o(14850);
    }

    public void a(FragmentActivity fragmentActivity, boolean z) {
        AppMethodBeat.i(14853, false);
        a(fragmentActivity);
        this.g = new WeakReference<>(fragmentActivity);
        int d = r.d(fragmentActivity);
        d.d("UpgradeManager", "checkUpgrade...begin.  netType:" + d);
        if (a != 0) {
            d.d("UpgradeManager", "checkUpgrade.... curr_status:" + a);
            AppMethodBeat.o(14853);
            return;
        }
        d();
        this.r.c().observe(this.g.get(), new AnonymousClass1(z));
        AppMethodBeat.o(14853);
    }

    /* compiled from: UpgradeManager */
    /* access modifiers changed from: package-private */
    /* renamed from: cn.missfresh.module.base.manager.s$1  reason: invalid class name */
    public class AnonymousClass1 implements Observer<ConfigurationBean.UpgradeBean> {
        final /* synthetic */ boolean a;

        AnonymousClass1(boolean z) {
            this.a = z;
        }

        @Override // androidx.lifecycle.Observer
        public /* synthetic */ void onChanged(Object obj) {
            AppMethodBeat.i(14808, false);
            a((ConfigurationBean.UpgradeBean) obj);
            AppMethodBeat.o(14808);
        }

        public void a(ConfigurationBean.UpgradeBean upgradeBean) {
            AppMethodBeat.i(14807, false);
            s.this.s = upgradeBean;
            d.c("UpgradeManager", "checkUpgrade");
            if (upgradeBean != null) {
                if (!upgradeBean.isSuccess) {
                    d.c("UpgradeManager", "checkUpgrade failed");
                    s.a(s.this);
                } else {
                    if (upgradeBean.hotfix != null) {
                        cn.missfresh.tinkerlib.a aVar = new cn.missfresh.tinkerlib.a();
                        aVar.a = upgradeBean.hotfix.patchUrl;
                        aVar.b = upgradeBean.hotfix.patchVersion;
                        aVar.c = upgradeBean.hotfix.restartNow;
                        cn.missfresh.module.base.a.a.a(((IApplicationDelegateService) com.alibaba.android.arouter.b.a.a().a("/common/application_delegate_service").navigation()).getApplication()).a(((IApplicationDelegateService) com.alibaba.android.arouter.b.a.a().a("/common/application_delegate_service").navigation()).getPackageName(), aVar);
                    }
                    StringBuilder sb = new StringBuilder();
                    sb.append("checkUpgrade hotfix ");
                    sb.append(upgradeBean.hotfix == null ? " is null " : JSON.toJSONString(upgradeBean.hotfix));
                    d.c("UpgradeManager", sb.toString());
                    s.a(s.this, upgradeBean, this.a);
                    s.a(s.this);
                }
            }
            AppMethodBeat.o(14807);
        }
    }

    /* compiled from: UpgradeManager */
    /* renamed from: cn.missfresh.module.base.manager.s$2  reason: invalid class name */
    class AnonymousClass2 implements e.c {
        AnonymousClass2() {
        }

        @Override // cn.missfresh.module.base.support.dialog.e.c
        public void a() {
            AppMethodBeat.i(14812, false);
            s.this.a(false);
            s.a(s.this);
            AppMethodBeat.o(14812);
        }

        @Override // cn.missfresh.module.base.support.dialog.e.c
        public void b() {
            AppMethodBeat.i(14813, false);
            s.this.a(false);
            if (s.this.s != null) {
                s sVar = s.this;
                s.a(sVar, sVar.s.url, s.this.s.upgradeType);
            }
            s.c(s.this);
            AppMethodBeat.o(14813);
        }

        @Override // cn.missfresh.module.base.support.dialog.e.c
        public void c() {
            AppMethodBeat.i(14814, false);
            s.this.a(false);
            if (s.this.s != null) {
                s sVar = s.this;
                s.a(sVar, sVar.s.url, s.this.s.upgradeType);
            }
            s.c(s.this);
            AppMethodBeat.o(14814);
        }
    }

    private void a(ConfigurationBean.UpgradeBean upgradeBean, boolean z) {
        AppMethodBeat.i(14857, false);
        if (upgradeBean == null) {
            AppMethodBeat.o(14857);
        } else if (upgradeBean.upgradeDisabled == 1) {
            e.m(false);
            e();
            AppMethodBeat.o(14857);
        } else {
            if (upgradeBean.upgradeType == 1 && z) {
                String H = e.H();
                if (!TextUtils.isEmpty(H) && TextUtils.equals(H, r.p())) {
                    AppMethodBeat.o(14857);
                    return;
                }
            }
            int i2 = upgradeBean.upgradeType;
            if (i2 == 0) {
                e.m(false);
                e();
            } else if (i2 == 1) {
                this.q = "adviceupdate";
                if (a(upgradeBean.upToVer)) {
                    c(0);
                } else {
                    c();
                    if (a(upgradeBean)) {
                        d.d("UpgradeManager", "SkipPopDialog");
                        e();
                    } else {
                        d.d("UpgradeManager", "not SkipPopDialog");
                        e.a((Activity) this.g.get(), false, upgradeBean.verDesc, this.e).d().setOnCancelListener(this.f);
                        a(true);
                        c(1);
                    }
                }
            } else if (i2 != 2) {
                e();
            } else {
                this.q = "forceupdate";
                c();
                if (a(upgradeBean)) {
                    e();
                } else {
                    e.a((Activity) this.g.get(), true, upgradeBean.verDesc, this.e);
                    a(true);
                    c(1);
                }
            }
            AppMethodBeat.o(14857);
        }
    }

    private void c() {
        AppMethodBeat.i(14859, false);
        e.E();
        e.m(true);
        AppMethodBeat.o(14859);
    }

    private boolean a(ConfigurationBean.UpgradeBean upgradeBean) {
        AppMethodBeat.i(14861, false);
        if (upgradeBean == null || upgradeBean.upgradeDisabled == 1) {
            AppMethodBeat.o(14861);
            return true;
        }
        int D = e.D();
        if (upgradeBean.popupFrequency == 0) {
            upgradeBean.popupFrequency++;
        }
        d.d("UpgradeManager", "ifSkipPopDialog...popCnt:" + D + ",content.getPopup_frequency():" + upgradeBean.popupFrequency);
        if (D % upgradeBean.popupFrequency == 0 || !"MainActivity".equals(this.g.get().getClass().getSimpleName())) {
            AppMethodBeat.o(14861);
            return false;
        }
        AppMethodBeat.o(14861);
        return true;
    }

    /* compiled from: UpgradeManager */
    /* renamed from: cn.missfresh.module.base.manager.s$3  reason: invalid class name */
    class AnonymousClass3 implements DialogInterface.OnCancelListener {
        AnonymousClass3() {
        }

        @Override // android.content.DialogInterface.OnCancelListener
        public void onCancel(DialogInterface dialogInterface) {
            AppMethodBeat.i(14818, false);
            s.a(s.this);
            if (s.this.o != null) {
                s.this.a(false);
            }
            AppMethodBeat.o(14818);
        }
    }

    private void a(String str, int i2) {
        AppMethodBeat.i(14866, false);
        d.d("UpgradeManager", "onClickUpgrade begin....");
        if (this.g.get() == null || this.g.get().isFinishing()) {
            AppMethodBeat.o(14866);
            return;
        }
        this.l = (DownloadDialogFragment) this.g.get().getSupportFragmentManager().findFragmentByTag(Context.DOWNLOAD_SERVICE);
        DownloadDialogFragment downloadDialogFragment = this.l;
        if (downloadDialogFragment == null) {
            this.l = DownloadDialogFragment.a(i2);
            this.g.get().getSupportFragmentManager().beginTransaction().add(this.l, Context.DOWNLOAD_SERVICE).commitNowAllowingStateLoss();
        } else {
            downloadDialogFragment.b(i2);
            this.g.get().getSupportFragmentManager().beginTransaction().show(this.l).commitNowAllowingStateLoss();
        }
        b(str);
        d.d("UpgradeManager", "onClickUpgrade end....");
        AppMethodBeat.o(14866);
    }

    /* compiled from: UpgradeManager */
    /* access modifiers changed from: package-private */
    /* renamed from: cn.missfresh.module.base.manager.s$4  reason: invalid class name */
    public class AnonymousClass4 extends Thread {
        final /* synthetic */ String a;

        AnonymousClass4(String str) {
            this.a = str;
        }

        /* JADX WARNING: Removed duplicated region for block: B:35:0x00a9 A[SYNTHETIC, Splitter:B:35:0x00a9] */
        /* JADX WARNING: Removed duplicated region for block: B:40:0x00b3  */
        /* JADX WARNING: Removed duplicated region for block: B:49:0x00c4 A[SYNTHETIC, Splitter:B:49:0x00c4] */
        /* JADX WARNING: Removed duplicated region for block: B:54:0x00ce A[SYNTHETIC, Splitter:B:54:0x00ce] */
        @Override // java.lang.Thread, java.lang.Runnable
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void run() {
            /*
            // Method dump skipped, instructions count: 218
            */
            throw new UnsupportedOperationException("Method not decompiled: cn.missfresh.module.base.manager.s.AnonymousClass4.run():void");
        }
    }

    private void b(String str) {
        AppMethodBeat.i(14868, false);
        d.d("UpgradeManager", "downloadFile begin....urlStr:" + str);
        new AnonymousClass4(str).start();
        AppMethodBeat.o(14868);
    }

    private void a(int i2) {
        AppMethodBeat.i(14869, false);
        this.d.obtainMessage(i2).sendToTarget();
        AppMethodBeat.o(14869);
    }

    /* compiled from: UpgradeManager */
    class a extends Handler {
        a() {
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            AppMethodBeat.i(14831, false);
            super.handleMessage(message);
            int i = message.what;
            if (i == 1) {
                s.d(s.this, 0);
                s.f(s.this);
            } else if (i == 2) {
                s.d(s.this, (int) (((((double) s.this.k) * 1.0d) / ((double) s.this.j)) * 100.0d));
            } else if (i == 3) {
                s.d(s.this, 100);
                s.a(s.this, s.i);
            } else if (i == 4) {
                s.a(s.this);
            }
            AppMethodBeat.o(14831);
        }
    }

    private void b(int i2) {
        AppMethodBeat.i(14871, false);
        if (i2 > this.l.a() || this.k == 0) {
            d.d("UpgradeManager", "onFIleDownloadProcess percent:" + i2);
            this.l.c(i2);
            this.l.a((float) this.k, (float) this.j);
            if (i2 != 0) {
                d(i2);
            }
        }
        AppMethodBeat.o(14871);
    }

    private void c(String str) {
        AppMethodBeat.i(14873, false);
        d.d("UpgradeManager", "onFileDownloadSuc ... filePath:" + str);
        l();
        e.F();
        this.l.b();
        AppMethodBeat.o(14873);
    }

    private void d() {
        AppMethodBeat.i(14875, false);
        d.d("UpgradeManager", "setStatusChecking...");
        a = 1;
        AppMethodBeat.o(14875);
    }

    private void e() {
        AppMethodBeat.i(14876, false);
        d.d("UpgradeManager", "resetStatus");
        a = 0;
        c(0);
        f();
        AppMethodBeat.o(14876);
    }

    private void f() {
        AppMethodBeat.i(14877, false);
        h();
        ISurveyService iSurveyService = this.t;
        if (iSurveyService != null) {
            iSurveyService.b(1, 3);
        }
        AppMethodBeat.o(14877);
    }

    private void g() {
        AppMethodBeat.i(14878, false);
        h();
        ISurveyService iSurveyService = this.t;
        if (iSurveyService != null) {
            iSurveyService.a(1, 3);
        }
        AppMethodBeat.o(14878);
    }

    private void h() {
        AppMethodBeat.i(14880, false);
        if (this.t == null) {
            this.t = (ISurveyService) com.alibaba.android.arouter.b.a.a().a("/order/survey_service").navigation();
        }
        AppMethodBeat.o(14880);
    }

    private void c(int i2) {
        boolean z = false;
        AppMethodBeat.i(14881, false);
        c cVar = this.n;
        if (cVar != null) {
            if (i2 == 1) {
                z = true;
            }
            cVar.a("upgrade", z);
        }
        AppMethodBeat.o(14881);
    }

    private void i() {
        a = 2;
    }

    private void j() {
        AppMethodBeat.i(14884, false);
        d.d("UpgradeManager", "initNoticeBuilder ...");
        this.c = new NotificationCompat.Builder(this.g.get());
        this.c.setContentTitle("\u4e0b\u8f7d\u8fdb\u5ea6");
        this.c.setSmallIcon(R.mipmap.ic_launcher);
        this.c.setContentText("1 %");
        this.c.setProgress(100, 1, false);
        Intent intent = new Intent();
        intent.setClassName(this.g.get(), "cn.missfresh.module.main.view.MainActivity");
        this.c.setContentIntent(PendingIntent.getActivity(this.g.get(), 1, intent, 134217728));
        this.b = (NotificationManager) this.g.get().getSystemService("notification");
        AppMethodBeat.o(14884);
    }

    private void k() {
        AppMethodBeat.i(14885, false);
        d.d("UpgradeManager", "addNotifiction ....");
        if (this.g.get() == null) {
            AppMethodBeat.o(14885);
            return;
        }
        if (this.c == null) {
            j();
        }
        this.b.notify(10, this.c.build());
        AppMethodBeat.o(14885);
    }

    private void d(int i2) {
        AppMethodBeat.i(14888, false);
        d.d("UpgradeManager", "updateNotifictionProcess ....percent:" + i2);
        if (this.g.get() == null) {
            AppMethodBeat.o(14888);
            return;
        }
        if (this.c == null) {
            j();
        }
        this.c.setProgress(100, i2, false);
        NotificationCompat.Builder builder = this.c;
        builder.setContentText(i2 + " %");
        this.b.notify(10, this.c.build());
        AppMethodBeat.o(14888);
    }

    private void l() {
        AppMethodBeat.i(14891, false);
        d.d("UpgradeManager", "cancelNotification....");
        this.b.cancel(10);
        AppMethodBeat.o(14891);
    }
}
