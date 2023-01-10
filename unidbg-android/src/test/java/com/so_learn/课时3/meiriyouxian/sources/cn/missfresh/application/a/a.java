package cn.missfresh.application.a;

import android.app.Application;
import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import androidx.vectordrawable.graphics.drawable.PathInterpolatorCompat;
import cn.missfresh.ad.data.a;
import cn.missfresh.buttomline.abtest.ABTest;
import cn.missfresh.buttomline.logtrace.bean.LogBean;
import cn.missfresh.module.abtest.AbTestManager;
import cn.missfresh.module.base.api.CSLineApiManager;
import cn.missfresh.module.base.api.MainApiManager;
import cn.missfresh.module.base.api.TyingProductApiManager;
import cn.missfresh.module.base.bean.ShoppingCartActive;
import cn.missfresh.module.base.bean.ShoppingCartInActive;
import cn.missfresh.module.base.common.api.BusinessApiManager;
import cn.missfresh.module.base.common.ministart.api.MiniStartApiManager;
import cn.missfresh.module.base.datastatistics.StatisticsManager;
import cn.missfresh.module.base.datastatistics.a.d;
import cn.missfresh.module.base.manager.bean.UserInfo;
import cn.missfresh.module.base.network.api.MFApiManager;
import cn.missfresh.module.base.payment.recharge.api.RechargApiManager;
import cn.missfresh.module.base.shoppingcart_settle.api.ShoppingCartSettleApiManager;
import cn.missfresh.module.base.utils.ag;
import cn.missfresh.module.base.utils.ax;
import cn.missfresh.module.food.api.FoodApiManager;
import cn.missfresh.module.homepage.providers.IHomeCacheService;
import cn.missfresh.module.order.aftersale.api.AfterSaleApiManager;
import cn.missfresh.module.order.customservice.api.CSOrderListApiManager;
import cn.missfresh.module.order.invoice.api.InvoiceApiManager;
import cn.missfresh.module.order.orderbase.afterpay.api.OrderApiManager;
import cn.missfresh.module.order.orderbase.detail.api.PostManApiManager;
import cn.missfresh.module.order.orderbase.detail.api.SurveyApiManager;
import cn.missfresh.module.order.orderconfirm.api.OrderConfirmApiManager;
import cn.missfresh.module.order.orderdetails_v2.api.OrderModuleApiManager;
import cn.missfresh.module.order.orderdetails_v2.api.QuickPayApiManager;
import cn.missfresh.module.order.orderlist.api.OrderListApiManager;
import cn.missfresh.module.order.rewarddailog.api.RewardApiManager;
import cn.missfresh.module.order.shoppingcart.api.ShoppingCartApiManager;
import cn.missfresh.module.other.market.api.MFMarketApiManager;
import cn.missfresh.module.product.detailnew.api.ProductDetailNewApiManager;
import cn.missfresh.module.promotion.voucher.api.VoucherApiManager;
import cn.missfresh.module.user.address.api.SupportCityOptApiManager;
import cn.missfresh.module.user.location.api.LocationApiManager;
import cn.missfresh.module.user.login.api.QuickLoginApiManager;
import cn.missfresh.module.user.login.api.WxOpratorApiManager;
import cn.missfresh.module.user.mine.api.MineApiManager;
import cn.missfresh.sherlock.Sherlock;
import cn.missfresh.sherlock.to.CommonTO;
import cn.missfresh.sherlock.to.NetworkTO;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import com.bun.miitmdid.core.IIdentifierListener;
import com.bun.miitmdid.core.JLibrary;
import com.bun.miitmdid.core.MdidSdkHelper;
import com.bun.miitmdid.supplier.IdSupplier;
import com.q.Qt;
import com.sijla.callback.QtCallBack;
import com.tencent.msdk.dns.DnsConfig;
import com.tencent.msdk.dns.DnsService;
import com.tencent.smtt.sdk.QbSdk;
import java.util.ArrayList;
import java.util.HashMap;
import org.json.JSONObject;

/* compiled from: AppTaskFactory */
public class a {
    private static Application a;

    public a(Application application) {
        a = application;
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARNING: Code restructure failed: missing block: B:72:0x0131, code lost:
        if (r4.equals("TASK_AROUTER") != false) goto L_0x0135;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public cn.missfresh.next.e a(java.lang.String r4) {
        /*
        // Method dump skipped, instructions count: 684
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.missfresh.application.a.a.a(java.lang.String):cn.missfresh.next.e");
    }

    /* compiled from: AppTaskFactory */
    public static class b extends cn.missfresh.next.e {
        public b() {
            super("TASK_ABTEST");
        }

        public void a() {
            AppMethodBeat.i(39, false);
            AbTestManager.getInstance(a.a).init();
            AppMethodBeat.o(39);
        }
    }

    /* compiled from: AppTaskFactory */
    public static class s extends cn.missfresh.next.e {
        static /* synthetic */ String a(s sVar, NetworkTO networkTO) {
            AppMethodBeat.i(46, false);
            String b = sVar.b(networkTO);
            AppMethodBeat.o(46);
            return b;
        }

        static /* synthetic */ boolean a(s sVar, String str) {
            AppMethodBeat.i(40, false);
            boolean a = sVar.a(str);
            AppMethodBeat.o(40);
            return a;
        }

        static /* synthetic */ void b(s sVar, NetworkTO networkTO) {
            AppMethodBeat.i(50, false);
            sVar.a(networkTO);
            AppMethodBeat.o(50);
        }

        public s() {
            super("TASK_SHERLOCK");
        }

        public void a() {
            AppMethodBeat.i(17, false);
            UserInfo p = cn.missfresh.module.base.manager.e.p();
            String valueOf = (p == null || p.getUser_id() <= 0) ? "" : String.valueOf(p.getUser_id());
            String phone_number = (p == null || TextUtils.isEmpty(p.getPhone_number())) ? "-1" : p.getPhone_number();
            Sherlock.setSplashActivities("cn.missfresh.module.main.view.SplashActivity");
            Sherlock.registerEventListener(new AnonymousClass1());
            Sherlock.setAppVersionName(cn.missfresh.module.base.utils.r.h(a.a));
            if (cn.missfresh.module.base.manager.e.b(a.a)) {
                Sherlock.init(a.a, "41e715dd4dad4bfa9484ceb30536d1f7", valueOf, phone_number);
            } else {
                Sherlock.init(a.a, "0f6b8910e3f34a74bfd85f5924ef5389", valueOf, phone_number);
            }
            AppMethodBeat.o(17);
        }

        /* compiled from: AppTaskFactory */
        /* renamed from: cn.missfresh.application.a.a$s$1  reason: invalid class name */
        class AnonymousClass1 implements cn.missfresh.sherlock.d {
            AnonymousClass1() {
            }

            @Override // cn.missfresh.sherlock.d
            public void a(CommonTO commonTO) {
                AppMethodBeat.i(38, false);
                if (commonTO instanceof NetworkTO) {
                    NetworkTO networkTO = (NetworkTO) commonTO;
                    if (cn.missfresh.module.base.network.a.d.a().a(networkTO.getUrl()) || s.a(s.this, networkTO.getUrl()) || Sherlock.isApmHost(networkTO.getUrl())) {
                        AppMethodBeat.o(38);
                        return;
                    }
                    if (cn.missfresh.module.base.utils.f.e()) {
                        LogBean logBean = new LogBean();
                        logBean.setType("NetData");
                        logBean.setInfo_str(networkTO.getUrl());
                        logBean.setStr_value_0(networkTO.getRequestHeaders());
                        logBean.setStr_value_1(networkTO.getRequestBody());
                        logBean.setStr_value_2(networkTO.getResponseHeaders());
                        logBean.setStr_value_3(s.a(s.this, networkTO));
                        logBean.setStr_value_4(networkTO.getStatusCode());
                        logBean.setStr_value_5(networkTO.getRequestId());
                        cn.missfresh.utils.a.d.a(logBean);
                    }
                    cn.missfresh.module.base.network.a.a.a(networkTO);
                    s.b(s.this, networkTO);
                }
                AppMethodBeat.o(38);
            }
        }

        private boolean a(String str) {
            AppMethodBeat.i(23, false);
            if (cn.missfresh.utils.b.a(str)) {
                AppMethodBeat.o(23);
                return false;
            } else if (str.contains(cn.missfresh.buttomline.logtrace.b.a())) {
                AppMethodBeat.o(23);
                return true;
            } else {
                AppMethodBeat.o(23);
                return false;
            }
        }

        private void a(NetworkTO networkTO) {
            AppMethodBeat.i(30, false);
            if (networkTO == null) {
                AppMethodBeat.o(30);
                return;
            }
            if (cn.missfresh.module.base.manager.e.e() || cn.missfresh.module.base.manager.e.f()) {
                cn.missfresh.utils.a.d.d("httplog", "url=" + networkTO.getUrl() + ", statusCode=" + networkTO.getStatusCode() + ", responseTime=" + networkTO.getResponseTime() + ", requestId=" + networkTO.getRequestId() + ", requestHeaders=" + networkTO.getRequestHeaders() + ", reqestBody=" + networkTO.getRequestBody() + ", responseCode=" + networkTO.getResponseCode() + ", responseBody=" + b(networkTO));
            }
            AppMethodBeat.o(30);
        }

        private String b(NetworkTO networkTO) {
            AppMethodBeat.i(34, false);
            if (networkTO == null) {
                AppMethodBeat.o(34);
                return "";
            } else if (ax.a(networkTO.getUrl()) || ax.b(networkTO.getUrl())) {
                AppMethodBeat.o(34);
                return "type is media";
            } else {
                String responseBody = networkTO.getResponseBody();
                AppMethodBeat.o(34);
                return responseBody;
            }
        }
    }

    /* compiled from: AppTaskFactory */
    public static class r extends cn.missfresh.next.e {
        public r() {
            super("TASK_SCM");
        }

        public void a() {
            AppMethodBeat.i(68, false);
            cn.missfresh.module.base.datastatistics.a.g.a(new d.a().a(cn.missfresh.module.base.manager.e.b(a.a)).a("missfresh").a(new cn.missfresh.module.base.d.a()).a((cn.missfresh.module.base.datastatistics.a.b.a) null).b(new cn.missfresh.module.base.d.a.j()).b(new cn.missfresh.module.base.d.a.i()).b(new cn.missfresh.module.base.d.a.h()).b(new cn.missfresh.module.base.d.a.d()).b(new cn.missfresh.module.base.d.a.f()).b(new cn.missfresh.module.base.d.a.a()).b(new cn.missfresh.module.base.d.a.g()).b(new cn.missfresh.module.base.d.a.e()).b(new cn.missfresh.module.base.d.a.k()).b(new cn.missfresh.module.base.d.a.b()).a());
            AppMethodBeat.o(68);
        }
    }

    /* compiled from: AppTaskFactory */
    public static class d extends cn.missfresh.next.e {
        public d() {
            super("TASK_BASE");
        }

        public void a() {
            AppMethodBeat.i(13, false);
            c();
            cn.missfresh.module.base.manager.b.a(a.a);
            cn.missfresh.module.base.network.c.a();
            cn.missfresh.lib.image.c.a(a.a);
            cn.missfresh.hotfix.a.a(a.a);
            AppMethodBeat.o(13);
        }

        private void c() {
            AppMethodBeat.i(21, false);
            cn.missfresh.module.base.manager.e.z(true);
            cn.missfresh.module.base.datastatistics.d.a(cn.missfresh.module.base.manager.e.at());
            if (!cn.missfresh.module.base.manager.e.b()) {
                AppMethodBeat.o(21);
                return;
            }
            cn.missfresh.module.base.common.config.b.b = cn.missfresh.module.base.manager.e.Q();
            cn.missfresh.module.base.common.config.b.a = cn.missfresh.module.base.manager.e.R();
            ArrayList arrayList = new ArrayList();
            arrayList.add(cn.missfresh.module.base.common.config.b.b);
            Sherlock.setWhiteList(arrayList);
            MFApiManager.changeMFApi(cn.missfresh.module.base.common.config.b.b);
            MainApiManager.changeMainApi(cn.missfresh.module.base.common.config.b.b);
            MiniStartApiManager.changeMiniStartApi(cn.missfresh.module.base.common.config.b.b);
            RechargApiManager.changeRechargApi(cn.missfresh.module.base.common.config.b.b);
            ShoppingCartApiManager.changeShoppingCartApi(cn.missfresh.module.base.common.config.b.b);
            AfterSaleApiManager.changeAfterSaleApi(cn.missfresh.module.base.common.config.b.b);
            SurveyApiManager.changeSurveyApi(cn.missfresh.module.base.common.config.b.b);
            LocationApiManager.changeLocationApi(cn.missfresh.module.base.common.config.b.b);
            MineApiManager.changeMineApi(cn.missfresh.module.base.common.config.b.b);
            SupportCityOptApiManager.changeSupportCityOptApi(cn.missfresh.module.base.common.config.b.b);
            ProductDetailNewApiManager.changeProductDetailNewApi(cn.missfresh.module.base.common.config.b.b);
            OrderModuleApiManager.changeOrderModuleApi(cn.missfresh.module.base.common.config.b.b);
            FoodApiManager.changeFoodApi(cn.missfresh.module.base.common.config.i.b());
            cn.missfresh.module.homepage.b.c.a(cn.missfresh.module.base.common.config.i.b());
            cn.missfresh.flutter.e.a(cn.missfresh.module.base.common.config.i.b());
            VoucherApiManager.changeVoucherApi(cn.missfresh.module.base.common.config.b.b);
            OrderConfirmApiManager.changeOrderConfirmApi(cn.missfresh.module.base.common.config.b.b);
            BusinessApiManager.changeBusinessApi(cn.missfresh.module.base.common.config.b.b);
            cn.missfresh.module.promotion.h5.widget.a.a(cn.missfresh.module.base.common.config.b.b);
            OrderListApiManager.changeOrderListApi(cn.missfresh.module.base.common.config.b.b);
            InvoiceApiManager.changeInvoiceApi(cn.missfresh.module.base.common.config.b.b);
            cn.missfresh.flutter.b.a(cn.missfresh.module.base.common.config.b.b);
            cn.missfresh.module.order.evaluation.c.a(cn.missfresh.module.base.common.config.b.b);
            RewardApiManager.changeRewardApi(cn.missfresh.module.base.common.config.b.b);
            CSOrderListApiManager.changeCSOrderListApi(cn.missfresh.module.base.common.config.b.a);
            CSLineApiManager.changeCSLineApi(cn.missfresh.module.base.common.config.b.a);
            QuickLoginApiManager.changeQuickLoginApi(cn.missfresh.module.base.common.config.b.b);
            TyingProductApiManager.changeTyingProductApi(cn.missfresh.module.base.common.config.b.b);
            PostManApiManager.changePostManApi(cn.missfresh.module.base.common.config.b.b);
            OrderApiManager.changeOrderApi(cn.missfresh.module.base.common.config.b.b);
            ShoppingCartSettleApiManager.changeShoppingCartSettleApi(cn.missfresh.module.base.common.config.b.b);
            QuickPayApiManager.changeQuickPayApi(cn.missfresh.module.base.common.config.i.a());
            WxOpratorApiManager.changeWxOpratorApi(cn.missfresh.module.base.common.config.b.b);
            MFMarketApiManager.changeMFMarketApi(cn.missfresh.module.base.common.config.b.b);
            AppMethodBeat.o(21);
        }
    }

    /* compiled from: AppTaskFactory */
    public static class v extends cn.missfresh.next.e {
        public v() {
            super("TASK_TENCENT");
        }

        public void a() {
            AppMethodBeat.i(29, false);
            cn.missfresh.module.base.manager.q.a(a.a);
            AppMethodBeat.o(29);
        }
    }

    /* compiled from: AppTaskFactory */
    /* renamed from: cn.missfresh.application.a.a$a  reason: collision with other inner class name */
    public static class C0015a extends cn.missfresh.next.e {
        public C0015a() {
            super("TASK_AD");
        }

        public void a() {
            AppMethodBeat.i(58, false);
            cn.missfresh.ad.c.a(new a.C0014a().a("missfresh").a(a.a).a(cn.missfresh.module.base.utils.f.h()).a());
            AppMethodBeat.o(58);
        }
    }

    /* compiled from: AppTaskFactory */
    class j extends cn.missfresh.next.e {
        j() {
            super("TASK_LINK");
        }

        public void a() {
            AppMethodBeat.i(62, false);
            cn.missfresh.module.base.helper.e.a(a.a);
            AppMethodBeat.o(62);
        }
    }

    /* compiled from: AppTaskFactory */
    class n extends cn.missfresh.next.e {
        n() {
            super("TASK_MAP");
        }

        public void a() {
            AppMethodBeat.i(47, false);
            cn.missfresh.map.d.a().a(a.a, cn.missfresh.map.g.a);
            AppMethodBeat.o(47);
        }
    }

    /* compiled from: AppTaskFactory */
    public static class o extends cn.missfresh.next.e {
        public o() {
            super("TASK_PUSH");
        }

        public void a() {
            AppMethodBeat.i(57, false);
            cn.missfresh.module.base.push.b.a(a.a);
            AppMethodBeat.o(57);
        }
    }

    /* compiled from: AppTaskFactory */
    public static class p extends cn.missfresh.next.e {
        public p() {
            super("TASK_QUEST_MOBILE");
        }

        /* compiled from: AppTaskFactory */
        /* renamed from: cn.missfresh.application.a.a$p$1  reason: invalid class name */
        class AnonymousClass1 implements QtCallBack {
            public void uploadCallBack(JSONObject jSONObject) {
            }

            AnonymousClass1() {
            }
        }

        public void a() {
            AppMethodBeat.i(42, false);
            Qt.init(a.a, com.meituan.android.walle.f.a(a.a), cn.missfresh.module.base.utils.r.g(a.a), new AnonymousClass1());
            Qt.showLog(cn.missfresh.module.base.manager.e.b(a.a));
            AppMethodBeat.o(42);
        }
    }

    /* compiled from: AppTaskFactory */
    public static class k extends cn.missfresh.next.e {
        public k() {
            super("TASK_LOG");
        }

        public void a() {
            AppMethodBeat.i(24, false);
            cn.missfresh.utils.a.b.a(a.a, cn.missfresh.module.base.manager.e.e(), cn.missfresh.module.base.manager.e.f(), "MissFresh");
            AppMethodBeat.o(24);
        }
    }

    /* compiled from: AppTaskFactory */
    public static class w extends cn.missfresh.next.e {
        public w() {
            super("TASK_TOAST");
        }

        public void a() {
            AppMethodBeat.i(16, false);
            cn.missfresh.ui.a.a.a(a.a);
            AppMethodBeat.o(16);
        }
    }

    /* compiled from: AppTaskFactory */
    public static class x extends cn.missfresh.next.e {
        public x() {
            super("TASK_X5");
        }

        /* compiled from: AppTaskFactory */
        /* renamed from: cn.missfresh.application.a.a$x$1  reason: invalid class name */
        class AnonymousClass1 implements QbSdk.PreInitCallback {
            @Override // com.tencent.smtt.sdk.QbSdk.PreInitCallback
            public void onCoreInitFinished() {
            }

            AnonymousClass1() {
            }

            @Override // com.tencent.smtt.sdk.QbSdk.PreInitCallback
            public void onViewInitFinished(boolean z) {
                AppMethodBeat.i(15, false);
                cn.missfresh.utils.a.d.c("AppTaskFactory", " onViewInitFinished is " + z);
                AppMethodBeat.o(15);
            }
        }

        public void a() {
            AppMethodBeat.i(51, false);
            AnonymousClass1 r1 = new AnonymousClass1();
            if (Build.VERSION.SDK_INT >= 29) {
                QbSdk.forceSysWebView();
            }
            QbSdk.initX5Environment(a.a, r1);
            AppMethodBeat.o(51);
        }
    }

    /* compiled from: AppTaskFactory */
    public static class l extends cn.missfresh.next.e {
        public l() {
            super("TASK_LOGTRACE");
        }

        public void a() {
            AppMethodBeat.i(43, false);
            cn.missfresh.utils.a.d.a(cn.missfresh.module.base.manager.e.e(), cn.missfresh.module.base.manager.e.f(), new cn.missfresh.buttomline.logtrace.a(a.a, false), cn.missfresh.module.base.utils.f.d());
            UserInfo p = cn.missfresh.module.base.manager.e.p();
            String str = "-1";
            String valueOf = (p == null || p.getUser_id() <= 0) ? str : String.valueOf(p.getUser_id());
            if (p != null && !TextUtils.isEmpty(p.getPhone_number())) {
                str = p.getPhone_number();
            }
            HashMap hashMap = new HashMap();
            hashMap.put(ABTest.KEY_MOBILE, str);
            hashMap.put("userId", valueOf);
            cn.missfresh.buttomline.logtrace.a.a((HashMap<String, String>) hashMap);
            AppMethodBeat.o(43);
        }
    }

    /* compiled from: AppTaskFactory */
    public static class h extends cn.missfresh.next.e {
        public h() {
            super("TASK_HUAWEI");
        }

        public void a() {
            AppMethodBeat.i(59, false);
            com.c.a.a.a.a(a.a);
            AppMethodBeat.o(59);
        }
    }

    /* compiled from: AppTaskFactory */
    public static class e extends cn.missfresh.next.e {
        public e() {
            super("TASK_CONFIG");
        }

        public void a() {
            AppMethodBeat.i(32, false);
            cn.missfresh.module.base.manager.e.X();
            AppMethodBeat.o(32);
        }
    }

    /* compiled from: AppTaskFactory */
    public static class i extends cn.missfresh.next.e {
        public i() {
            super("TASK_INIT_CONFIG");
        }

        public void a() {
            AppMethodBeat.i(55, false);
            cn.missfresh.module.base.manager.g.a().a(cn.missfresh.module.base.manager.e.A());
            cn.missfresh.module.base.manager.e.q(true);
            cn.missfresh.module.base.manager.f.a((Context) a.a);
            cn.missfresh.module.base.manager.f.c(ShoppingCartActive.class);
            cn.missfresh.module.base.manager.f.c(ShoppingCartInActive.class);
            String f = cn.missfresh.module.base.utils.r.f();
            String s = cn.missfresh.module.base.manager.e.s();
            if (TextUtils.isEmpty(s) || (!s.equals(f) && !"mryx".equals(f))) {
                cn.missfresh.module.base.manager.e.i(f);
            }
            if (cn.missfresh.module.base.manager.e.ad() == 3) {
                cn.missfresh.module.base.manager.e.c(0);
            }
            AppMethodBeat.o(55);
        }
    }

    /* compiled from: AppTaskFactory */
    public static class u extends cn.missfresh.next.e {
        public u() {
            super("TASK_STATISTICS");
        }

        public void a() {
            AppMethodBeat.i(20, false);
            StatisticsManager.f("first_anchor_id", new Object[0]);
            StatisticsManager.f("check_pushAuthority", "push_authority", ag.a(a.a) + "");
            AppMethodBeat.o(20);
        }
    }

    /* compiled from: AppTaskFactory */
    public static class t extends cn.missfresh.next.e {
        public t() {
            super("TASK_SOBOT");
        }

        public void a() {
            AppMethodBeat.i(63, false);
            com.sobot.chat.b.a(a.a, "467b3825638b41198f757326e05b8823", "");
            AppMethodBeat.o(63);
        }
    }

    /* compiled from: AppTaskFactory */
    public static class f extends cn.missfresh.next.e {
        public f() {
            super("TASK_DNS");
        }

        public void a() {
            AppMethodBeat.i(36, false);
            DnsService.init(a.a, new DnsConfig.Builder().logLevel(5).appId("0I000RS1YK3SQYL4").initBuiltInReporters().dnsId("8900").dnsKey("IswJu3qu").timeoutMills(PathInterpolatorCompat.MAX_NUM_POINTS).build());
            AppMethodBeat.o(36);
        }
    }

    /* compiled from: AppTaskFactory */
    public static class c extends cn.missfresh.next.e {
        public c() {
            super("TASK_AROUTER");
        }

        public void a() {
            AppMethodBeat.i(41, false);
            if (cn.missfresh.module.base.manager.e.b(a.a)) {
                com.alibaba.android.arouter.b.a.d();
                com.alibaba.android.arouter.b.a.b();
            }
            com.alibaba.android.arouter.b.a.a(a.a);
            AppMethodBeat.o(41);
        }
    }

    /* compiled from: AppTaskFactory */
    class g extends cn.missfresh.next.e {
        g() {
            super("TASK_HOME_CACHE");
        }

        public void a() {
            AppMethodBeat.i(9, false);
            try {
                IHomeCacheService iHomeCacheService = (IHomeCacheService) com.alibaba.android.arouter.b.a.a().a("/home/product/cache").navigation();
                iHomeCacheService.a("cache_home_index_page");
                iHomeCacheService.a();
                cn.missfresh.module.promotion.h5.widget.a.a(a.a);
            } catch (Exception e) {
                e.printStackTrace();
            }
            AppMethodBeat.o(9);
        }
    }

    /* compiled from: AppTaskFactory */
    private static class q extends cn.missfresh.next.e {
        public void a() {
        }

        q() {
            super("TASK_RISK");
        }
    }

    /* compiled from: AppTaskFactory */
    private class m extends cn.missfresh.next.e {
        public m() {
            super("TASK_MIID");
        }

        public void a() {
            AppMethodBeat.i(45, false);
            JLibrary.InitEntry(a.a);
            MdidSdkHelper.InitSdk(a.a, true, new AnonymousClass1());
            AppMethodBeat.o(45);
        }

        /* compiled from: AppTaskFactory */
        /* renamed from: cn.missfresh.application.a.a$m$1  reason: invalid class name */
        class AnonymousClass1 implements IIdentifierListener {
            AnonymousClass1() {
            }

            @Override // com.bun.miitmdid.core.IIdentifierListener
            public void OnSupport(boolean z, IdSupplier idSupplier) {
                AppMethodBeat.i(65, false);
                if (idSupplier == null) {
                    AppMethodBeat.o(65);
                    return;
                }
                if (idSupplier.isSupported()) {
                    cn.missfresh.module.base.utils.r.b = idSupplier.getAAID();
                    cn.missfresh.module.base.utils.r.c = idSupplier.getOAID();
                    cn.missfresh.module.base.utils.r.a = idSupplier.getVAID();
                }
                idSupplier.shutDown();
                AppMethodBeat.o(65);
            }
        }
    }
}
