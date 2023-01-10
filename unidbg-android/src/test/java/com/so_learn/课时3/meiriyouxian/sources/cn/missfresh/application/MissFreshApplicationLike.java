package cn.missfresh.application;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.webkit.WebView;
import cn.missfresh.application.a.a;
import cn.missfresh.application.a.b;
import cn.missfresh.application.a.c;
import cn.missfresh.module.base.helper.n;
import cn.missfresh.module.base.manager.e;
import cn.missfresh.module.base.manager.g;
import cn.missfresh.module.base.refresh.MFHeader;
import cn.missfresh.module.base.utils.ae;
import cn.missfresh.module.base.utils.aj;
import cn.missfresh.module.base.utils.f;
import cn.missfresh.module.base.utils.j;
import cn.missfresh.module.base.utils.u;
import cn.missfresh.module.order.shoppingcartnew.bean.ShoppingCartRecommendBean2;
import cn.missfresh.risk.h;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import cn.missfresh.ui.refreshlayout.MFRefreshLayout;
import com.scwang.smartrefresh.layout.api.DefaultRefreshHeaderCreator;
import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshLayout;

public class MissFreshApplicationLike extends AppDelegate {
    private static final String TAG = "MissFreshApplication";
    private static MissFreshApplicationLike sInstacne;
    private a mAppTaskFactory;
    private b mGlobalActivityLifecycleCallbacks = new b();
    private n mUserPrivacyManger = new n();

    static /* synthetic */ void access$100(MissFreshApplicationLike missFreshApplicationLike) {
        AppMethodBeat.i(230, false);
        missFreshApplicationLike.initMIID();
        AppMethodBeat.o(230);
    }

    static /* synthetic */ void access$200(MissFreshApplicationLike missFreshApplicationLike) {
        AppMethodBeat.i(231, false);
        missFreshApplicationLike.initRisk();
        AppMethodBeat.o(231);
    }

    static /* synthetic */ void access$300(MissFreshApplicationLike missFreshApplicationLike) {
        AppMethodBeat.i(232, false);
        missFreshApplicationLike.initX5PreLoad();
        AppMethodBeat.o(232);
    }

    static /* synthetic */ void access$400(MissFreshApplicationLike missFreshApplicationLike) {
        AppMethodBeat.i(234, false);
        missFreshApplicationLike.initSobot();
        AppMethodBeat.o(234);
    }

    static /* synthetic */ void access$500(MissFreshApplicationLike missFreshApplicationLike) {
        AppMethodBeat.i(236, false);
        missFreshApplicationLike.initAD();
        AppMethodBeat.o(236);
    }

    static /* synthetic */ void access$600(MissFreshApplicationLike missFreshApplicationLike) {
        AppMethodBeat.i(238, false);
        missFreshApplicationLike.initHomeCacheData();
        AppMethodBeat.o(238);
    }

    static /* synthetic */ void access$700(MissFreshApplicationLike missFreshApplicationLike) {
        AppMethodBeat.i(239, false);
        missFreshApplicationLike.initHuaWei();
        AppMethodBeat.o(239);
    }

    static /* synthetic */ void access$800(MissFreshApplicationLike missFreshApplicationLike) {
        AppMethodBeat.i(240, false);
        missFreshApplicationLike.initConfigManager();
        AppMethodBeat.o(240);
    }

    static /* synthetic */ void access$900(MissFreshApplicationLike missFreshApplicationLike) {
        AppMethodBeat.i(241, false);
        missFreshApplicationLike.initConfig();
        AppMethodBeat.o(241);
    }

    /* renamed from: cn.missfresh.application.MissFreshApplicationLike$1  reason: invalid class name */
    static class AnonymousClass1 implements DefaultRefreshHeaderCreator {
        AnonymousClass1() {
        }

        public RefreshHeader createRefreshHeader(Context context, RefreshLayout refreshLayout) {
            AppMethodBeat.i(90, false);
            MFHeader mFHeader = new MFHeader(context);
            AppMethodBeat.o(90);
            return mFHeader;
        }
    }

    static {
        AppMethodBeat.i(244, false);
        MFRefreshLayout.setDefaultRefreshHeaderCreator(new AnonymousClass1());
        AppMethodBeat.o(244);
    }

    public MissFreshApplicationLike(Application application, int i, boolean z, long j, long j2, Intent intent) {
        super(application, i, z, j, j2, intent);
        AppMethodBeat.i(92, false);
        AppMethodBeat.o(92);
    }

    public static MissFreshApplicationLike getInstance() {
        return sInstacne;
    }

    public boolean isAppInForeGround() {
        AppMethodBeat.i(102, false);
        boolean a = this.mGlobalActivityLifecycleCallbacks.a();
        AppMethodBeat.o(102);
        return a;
    }

    @Override // cn.missfresh.application.AppDelegate, cn.missfresh.tinkerlib.AbstractTinkerBaseApplicationLike, com.tencent.tinker.entry.DefaultApplicationLike, com.tencent.tinker.entry.ApplicationLike, com.tencent.tinker.entry.ApplicationLifeCycle
    public void onBaseContextAttached(Context context) {
        AppMethodBeat.i(105, false);
        e.a(getApplication());
        if (isMainProcess()) {
            aj.a().a("PERFORMANCE_LAUNCH");
            if (f.u()) {
                initWebViewDataDirectory(context);
            }
        }
        initDependencies();
        super.onBaseContextAttached(context);
        this.mAppTaskFactory = new a(getApplication());
        if (e.o() && e.p().getUser_id() == -1) {
            e.g("");
        }
        initAouter();
        b.a();
        AppMethodBeat.o(105);
    }

    private void initDependencies() {
        u.a = "1.9.9.41.10";
        u.b = "9.9.42.0.0";
        u.c = "9.9.42.0.0";
    }

    @Override // cn.missfresh.application.AppDelegate, com.tencent.tinker.entry.DefaultApplicationLike, com.tencent.tinker.entry.ApplicationLike, com.tencent.tinker.entry.ApplicationLifeCycle
    public void onCreate() {
        AppMethodBeat.i(113, false);
        super.onCreate();
        sInstacne = this;
        if (isMainProcess()) {
            cn.missfresh.basiclib.net.b.c.a aVar = new cn.missfresh.module.homepage.d.a();
            aVar.g().putDeserializer(ShoppingCartRecommendBean2.class, new cn.missfresh.module.order.shoppingcartnew.d.a());
            cn.missfresh.basiclib.net.a.a().a(aVar);
            cn.missfresh.basiclib.net.a.a().b();
            checkPermissionState();
            cn.missfresh.wsg.a.a(getApplication(), cn.missfresh.module.base.common.c.a.c);
            h.a(getApplication());
            if (this.mUserPrivacyManger.a()) {
                new c().a(getApplication(), this.mAppTaskFactory);
                registerLifecycleCallbacks();
            } else {
                initSDK();
                initSDKOnPrivacyAuthorized();
                initSDKInThread();
                initSDKInThreadOnPrivacyAuthorized();
            }
        } else {
            initSDKOnOtherProgress();
        }
        AppMethodBeat.o(113);
    }

    private void initSDK() {
        AppMethodBeat.i(116, false);
        initBase();
        initToast();
        registerLifecycleCallbacks();
        AppMethodBeat.o(116);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: cn.missfresh.application.MissFreshApplicationLike$2  reason: invalid class name */
    public class AnonymousClass2 extends cn.missfresh.basiclib.thread.b.a {
        AnonymousClass2() {
        }

        @Override // cn.missfresh.basiclib.thread.b.a
        public void runInTryCatch() {
            AppMethodBeat.i(101, false);
            if (MissFreshApplicationLike.this.mUserPrivacyManger.a()) {
                MissFreshApplicationLike.access$100(MissFreshApplicationLike.this);
                MissFreshApplicationLike.access$200(MissFreshApplicationLike.this);
                MissFreshApplicationLike.access$300(MissFreshApplicationLike.this);
                MissFreshApplicationLike.access$400(MissFreshApplicationLike.this);
                MissFreshApplicationLike.access$500(MissFreshApplicationLike.this);
                MissFreshApplicationLike.access$600(MissFreshApplicationLike.this);
            }
            AppMethodBeat.o(101);
        }
    }

    private void initSDKInThreadOnPrivacyAuthorized() {
        AppMethodBeat.i(121, false);
        cn.missfresh.basiclib.thread.b.a().a(new AnonymousClass2());
        AppMethodBeat.o(121);
    }

    private void initMIID() {
        AppMethodBeat.i(123, false);
        this.mAppTaskFactory.a("TASK_MIID").a();
        AppMethodBeat.o(123);
    }

    private void initRisk() {
        AppMethodBeat.i(126, false);
        this.mAppTaskFactory.a("TASK_RISK").a();
        AppMethodBeat.o(126);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: cn.missfresh.application.MissFreshApplicationLike$3  reason: invalid class name */
    public class AnonymousClass3 extends cn.missfresh.basiclib.thread.b.a {
        AnonymousClass3() {
        }

        @Override // cn.missfresh.basiclib.thread.b.a
        public void runInTryCatch() {
            AppMethodBeat.i(71, false);
            MissFreshApplicationLike.access$700(MissFreshApplicationLike.this);
            MissFreshApplicationLike.access$800(MissFreshApplicationLike.this);
            MissFreshApplicationLike.access$900(MissFreshApplicationLike.this);
            AppMethodBeat.o(71);
        }
    }

    private void initSDKInThread() {
        AppMethodBeat.i(128, false);
        cn.missfresh.basiclib.thread.b.a().a(new AnonymousClass3());
        AppMethodBeat.o(128);
    }

    private void initSDKOnOtherProgress() {
        AppMethodBeat.i(130, false);
        if (this.mUserPrivacyManger.a()) {
            initLog();
            if (isChannelProcess()) {
                initPush();
            }
        }
        AppMethodBeat.o(130);
    }

    private void initSDKOnPrivacyAuthorized() {
        AppMethodBeat.i(133, false);
        if (this.mUserPrivacyManger.a()) {
            initABTest();
            initLog();
            initSherlock();
            initLink();
            initScm();
            initDNS();
            initQuestMobile();
            initTencent();
            initPush();
            statistics();
            initMap();
        }
        AppMethodBeat.o(133);
    }

    public void initLimitedSDKAfterPrivacyAuthorized() {
        AppMethodBeat.i(136, false);
        initSDKOnPrivacyAuthorized();
        initSDKInThreadOnPrivacyAuthorized();
        AppMethodBeat.o(136);
    }

    private void checkPermissionState() {
        AppMethodBeat.i(139, false);
        aj.a().a(!new ae().a());
        AppMethodBeat.o(139);
    }

    @Override // cn.missfresh.application.AppDelegate, com.tencent.tinker.entry.DefaultApplicationLike, com.tencent.tinker.entry.ApplicationLike, com.tencent.tinker.entry.ApplicationLifeCycle
    public void onTrimMemory(int i) {
        AppMethodBeat.i(143, false);
        cn.missfresh.lib.image.c.a(getApplication(), i);
        super.onTrimMemory(i);
        AppMethodBeat.o(143);
    }

    private void registerLifecycleCallbacks() {
        AppMethodBeat.i(147, false);
        g.a().c(String.valueOf(System.currentTimeMillis()));
        cn.missfresh.module.base.common.ministart.a.a.a().a(getApplication());
        registerActivityLifecycleCallbacks(this.mGlobalActivityLifecycleCallbacks);
        registerActivityLifecycleCallbacks(new cn.missfresh.module.base.base.a.c());
        AppMethodBeat.o(147);
    }

    private void initDNS() {
        AppMethodBeat.i(150, false);
        this.mAppTaskFactory.a("TASK_DNS").a();
        AppMethodBeat.o(150);
    }

    private void initLog() {
        AppMethodBeat.i(154, false);
        this.mAppTaskFactory.a("TASK_LOG").a();
        AppMethodBeat.o(154);
    }

    private void initAouter() {
        AppMethodBeat.i(158, false);
        this.mAppTaskFactory.a("TASK_AROUTER").a();
        AppMethodBeat.o(158);
    }

    private void initSherlock() {
        AppMethodBeat.i(162, false);
        this.mAppTaskFactory.a("TASK_SHERLOCK").a();
        AppMethodBeat.o(162);
    }

    private void initToast() {
        AppMethodBeat.i(164, false);
        this.mAppTaskFactory.a("TASK_TOAST").a();
        AppMethodBeat.o(164);
    }

    private void initX5PreLoad() {
        AppMethodBeat.i(168, false);
        this.mAppTaskFactory.a("TASK_X5").a();
        AppMethodBeat.o(168);
    }

    private void initPush() {
        AppMethodBeat.i(171, false);
        this.mAppTaskFactory.a("TASK_PUSH").a();
        AppMethodBeat.o(171);
    }

    private void initLink() {
        AppMethodBeat.i(174, false);
        this.mAppTaskFactory.a("TASK_LINK").a();
        AppMethodBeat.o(174);
    }

    private void initMap() {
        AppMethodBeat.i(178, false);
        this.mAppTaskFactory.a("TASK_MAP").a();
        AppMethodBeat.o(178);
    }

    private void initABTest() {
        AppMethodBeat.i(181, false);
        this.mAppTaskFactory.a("TASK_ABTEST").a();
        AppMethodBeat.o(181);
    }

    private void initLogTrace() {
        AppMethodBeat.i(185, false);
        this.mAppTaskFactory.a("TASK_LOGTRACE").a();
        AppMethodBeat.o(185);
    }

    private void initAD() {
        AppMethodBeat.i(189, false);
        this.mAppTaskFactory.a("TASK_AD").a();
        AppMethodBeat.o(189);
    }

    private void initBase() {
        AppMethodBeat.i(192, false);
        this.mAppTaskFactory.a("TASK_BASE").a();
        AppMethodBeat.o(192);
    }

    private void initConfigManager() {
        AppMethodBeat.i(195, false);
        this.mAppTaskFactory.a("TASK_INIT_CONFIG").a();
        AppMethodBeat.o(195);
    }

    private void initSobot() {
        AppMethodBeat.i(198, false);
        this.mAppTaskFactory.a("TASK_SOBOT").a();
        AppMethodBeat.o(198);
    }

    private void initScm() {
        AppMethodBeat.i(202, false);
        this.mAppTaskFactory.a("TASK_SCM").a();
        AppMethodBeat.o(202);
    }

    private void statistics() {
        AppMethodBeat.i(205, false);
        this.mAppTaskFactory.a("TASK_STATISTICS").a();
        AppMethodBeat.o(205);
    }

    private void initQuestMobile() {
        AppMethodBeat.i(208, false);
        this.mAppTaskFactory.a("TASK_QUEST_MOBILE").a();
        AppMethodBeat.o(208);
    }

    private void initTencent() {
        AppMethodBeat.i(211, false);
        this.mAppTaskFactory.a("TASK_TENCENT").a();
        AppMethodBeat.o(211);
    }

    private void initHuaWei() {
        AppMethodBeat.i(214, false);
        this.mAppTaskFactory.a("TASK_HUAWEI").a();
        AppMethodBeat.o(214);
    }

    private void initHomeCacheData() {
        AppMethodBeat.i(217, false);
        this.mAppTaskFactory.a("TASK_HOME_CACHE").a();
        AppMethodBeat.o(217);
    }

    private void initConfig() {
        AppMethodBeat.i(219, false);
        this.mAppTaskFactory.a("TASK_CONFIG").a();
        AppMethodBeat.o(219);
    }

    private boolean isMainProcess() {
        AppMethodBeat.i(221, false);
        boolean equals = getApplication().getPackageName().equals(j.a(getApplication()));
        AppMethodBeat.o(221);
        return equals;
    }

    private boolean isChannelProcess() {
        AppMethodBeat.i(223, false);
        boolean equals = "cn.missfresh.application:channel".equals(j.a(getApplication()));
        AppMethodBeat.o(223);
        return equals;
    }

    public void showNewMsgView(String str, String str2) {
        AppMethodBeat.i(225, false);
        this.mGlobalActivityLifecycleCallbacks.a(str, str2);
        AppMethodBeat.o(225);
    }

    public void initWebViewDataDirectory(Context context) {
        AppMethodBeat.i(228, false);
        if (Build.VERSION.SDK_INT >= 28) {
            String a = j.a(context);
            if (!context.getPackageName().equals(a)) {
                WebView.setDataDirectorySuffix(a);
            }
        }
        AppMethodBeat.o(228);
    }
}
