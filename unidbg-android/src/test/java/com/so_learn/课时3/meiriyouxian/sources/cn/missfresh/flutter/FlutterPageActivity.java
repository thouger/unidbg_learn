package cn.missfresh.flutter;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import cn.missfresh.flutter.bean.FlutterReflushShoppingCartEvent;
import cn.missfresh.module.base.bean.EventFlutterPayResultBean;
import cn.missfresh.module.base.bean.LoginEvent;
import cn.missfresh.module.base.common.config.BottomTabEnum;
import cn.missfresh.module.base.common.providers.IProductDetailService;
import cn.missfresh.module.base.common.providers.IShoppingCartService2;
import cn.missfresh.module.base.helper.o;
import cn.missfresh.module.base.manager.PayManager;
import cn.missfresh.module.base.payment.pwd.view.BindPayPhoneActivity;
import cn.missfresh.module.base.support.dialog.c;
import cn.missfresh.module.base.support.dialog.e;
import cn.missfresh.module.base.utils.as;
import cn.missfresh.module.base.utils.j;
import cn.missfresh.module.order.shoppingcartnew.providers.bean.EventShoppingCartMergeBean;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import cn.missfresh.utils.a.d;
import cn.missfresh.utils.b;
import com.alibaba.android.arouter.b.a;
import com.bangcle.andjni.JniLib;
import com.huawei.hms.support.api.push.pushselfshow.prepare.NotificationIconUtil;
import de.greenrobot.event.EventBus;
import de.greenrobot.event.Subscribe;
import de.greenrobot.event.ThreadMode;
import io.flutter.embedding.android.FlutterActivityLaunchConfigs;
import io.flutter.embedding.android.FlutterFragmentActivity;
import io.flutter.embedding.engine.FlutterEngine;
import io.flutter.plugin.common.MethodChannel;
import java.util.HashMap;
import java.util.Map;

public class FlutterPageActivity extends FlutterFragmentActivity implements IProductDetailService.a, PayManager.a {
    public static String c = "cn.missfresh.flutter/plugin";
    String a = null;
    public String b;
    String d;
    boolean e = false;
    boolean f = false;
    Context g;
    e.a h = new AnonymousClass3(this);
    DialogInterface.OnDismissListener i = new 4(this);
    private ProgressDialog j;
    private final String k = "FlutterPageActivity";
    private PayManager l;
    private Map<String, Object> m;
    private FlutterCallAndroid n;
    private String o;
    private c p;
    private String q;
    private String r;
    private boolean s;
    private boolean t = false;
    private IShoppingCartService2 u = ((IShoppingCartService2) a.a().a("/order/new_shoppingcart_service").navigation());

    @Override // android.app.Activity, android.view.Window.Callback
    public void onWindowFocusChanged(boolean z) {
        super.onWindowFocusChanged(z);
        AppMethodBeat.at(this, z);
    }

    public FlutterPageActivity() {
        AppMethodBeat.i(20734, false);
        AppMethodBeat.o(20734);
    }

    static /* synthetic */ void a(FlutterPageActivity flutterPageActivity, String str, Object obj) {
        AppMethodBeat.i(20789, false);
        flutterPageActivity.a(str, obj);
        AppMethodBeat.o(20789);
    }

    /* access modifiers changed from: protected */
    @Override // io.flutter.embedding.android.FlutterFragmentActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        AppMethodBeat.i(20737, false);
        this.g = this;
        a.a().a(this);
        setContentView(R.layout.layout_flutter_activity);
        as.a(this, getResources().getColor(R.color.gray_81));
        EventBus.getDefault().register(this);
        Bundle extras = getIntent().getExtras();
        if (extras != null && !extras.isEmpty()) {
            this.m = new HashMap();
            for (String str : extras.keySet()) {
                this.m.put(str, extras.get(str));
            }
        }
        this.n = new FlutterCallAndroid(this, this.m);
        if (!b.a(this.d) && this.d.startsWith(NotificationIconUtil.SPLIT_CHAR)) {
            String str2 = this.d;
            this.d = str2.substring(1, str2.length());
        }
        this.d = "flutter://missfresh.cn/" + this.d;
        super.onCreate(bundle);
        a((LifecycleOwner) this);
        o.a(new AnonymousClass1(this));
        AppMethodBeat.o(20737);
    }

    /* renamed from: cn.missfresh.flutter.FlutterPageActivity$1  reason: invalid class name */
    class AnonymousClass1 implements Observer<LoginEvent> {
        final /* synthetic */ FlutterPageActivity a;

        AnonymousClass1(FlutterPageActivity flutterPageActivity) {
            JniLib.cV(this, flutterPageActivity, 1);
        }

        @Override // androidx.lifecycle.Observer
        public /* synthetic */ void onChanged(Object obj) {
            AppMethodBeat.i(20717, false);
            a((LoginEvent) obj);
            AppMethodBeat.o(20717);
        }

        public void a(LoginEvent loginEvent) {
            AppMethodBeat.i(20715, false);
            if (loginEvent == null) {
                AppMethodBeat.o(20715);
                return;
            }
            if (200 == loginEvent.getType()) {
                FlutterPageActivity.a(this.a, "loginSuccess", (Object) null);
            }
            AppMethodBeat.o(20715);
        }
    }

    private void a(String str, Object obj) {
        AppMethodBeat.i(20738, false);
        if (h() == null) {
            d.c("FlutterPageActivity", "Method channel is null");
            AppMethodBeat.o(20738);
            return;
        }
        h().invokeMethod(str, obj);
        AppMethodBeat.o(20738);
    }

    private MethodChannel h() {
        AppMethodBeat.i(20739, false);
        FlutterCallAndroid flutterCallAndroid = this.n;
        if (flutterCallAndroid == null) {
            AppMethodBeat.o(20739);
            return null;
        }
        MethodChannel a = flutterCallAndroid.a();
        AppMethodBeat.o(20739);
        return a;
    }

    @Override // io.flutter.embedding.android.FlutterFragmentActivity, io.flutter.embedding.android.FlutterEngineConfigurator
    public void configureFlutterEngine(FlutterEngine flutterEngine) {
        AppMethodBeat.i(20742, false);
        this.n.a(this, flutterEngine);
        flutterEngine.getPlugins().add(new cn.missfresh.flutter.flutter_map.e());
        flutterEngine.getPlugins().add(new cn.missfresh.flutter.flutter_map.b());
        flutterEngine.getPlugins().add(new cn.missfresh.flutter.flutter_video.c(this));
        a(flutterEngine);
        AppMethodBeat.o(20742);
    }

    private static void a(FlutterEngine flutterEngine) {
        AppMethodBeat.i(20744, false);
        try {
            Class.forName("io.flutter.plugins.GeneratedPluginRegistrant").getDeclaredMethod("registerWith", FlutterEngine.class).invoke(null, flutterEngine);
        } catch (Exception unused) {
        }
        AppMethodBeat.o(20744);
    }

    public void a(LifecycleOwner lifecycleOwner) {
        AppMethodBeat.i(20746, false);
        if (lifecycleOwner == null) {
            AppMethodBeat.o(20746);
            return;
        }
        this.u.b().observe(lifecycleOwner, new AnonymousClass2(this));
        AppMethodBeat.o(20746);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: cn.missfresh.flutter.FlutterPageActivity$2  reason: invalid class name */
    public class AnonymousClass2 implements Observer<Integer> {
        final /* synthetic */ FlutterPageActivity a;

        AnonymousClass2(FlutterPageActivity flutterPageActivity) {
            JniLib.cV(this, flutterPageActivity, 2);
        }

        @Override // androidx.lifecycle.Observer
        public /* synthetic */ void onChanged(Object obj) {
            AppMethodBeat.i(20723, false);
            a((Integer) obj);
            AppMethodBeat.o(20723);
        }

        public void a(Integer num) {
            AppMethodBeat.i(20722, false);
            HashMap hashMap = new HashMap();
            hashMap.put("count", String.valueOf(this.a.u.a()));
            FlutterPageActivity.a(this.a, "refreshCommentSCCount", hashMap);
            AppMethodBeat.o(20722);
        }
    }

    /* access modifiers changed from: protected */
    @Override // android.app.Activity
    public void onPostCreate(Bundle bundle) {
        AppMethodBeat.i(20748, false);
        super.onPostCreate(bundle);
        AppMethodBeat.o(20748);
    }

    @Override // io.flutter.embedding.android.FlutterFragmentActivity
    public String getInitialRoute() {
        return this.d;
    }

    @Override // io.flutter.embedding.android.FlutterFragmentActivity
    public String getCachedEngineId() {
        return this.a;
    }

    /* access modifiers changed from: protected */
    @Override // io.flutter.embedding.android.FlutterFragmentActivity
    public FlutterActivityLaunchConfigs.BackgroundMode getBackgroundMode() {
        AppMethodBeat.i(20753, false);
        String str = this.b;
        if (str != null) {
            FlutterActivityLaunchConfigs.BackgroundMode valueOf = FlutterActivityLaunchConfigs.BackgroundMode.valueOf(str);
            AppMethodBeat.o(20753);
            return valueOf;
        }
        FlutterActivityLaunchConfigs.BackgroundMode backgroundMode = FlutterActivityLaunchConfigs.BackgroundMode.opaque;
        AppMethodBeat.o(20753);
        return backgroundMode;
    }

    /* access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        AppMethodBeat.i(20756, false);
        super.onDestroy();
        e();
        PayManager payManager = this.l;
        if (payManager != null) {
            payManager.e();
        }
        if (this.e && c.b) {
            Intent intent = new Intent();
            intent.putExtra("recharge_state", true);
            intent.putExtra("store_changed", true);
            setResult(11, intent);
            c.b = false;
        }
        cn.missfresh.module.base.manager.e.h("");
        EventBus.getDefault().unregister(this);
        if (this.f) {
            j.a((Activity) this, BottomTabEnum.INDEX.getPos(), false);
        }
        IProductDetailService iProductDetailService = (IProductDetailService) a.a().a("/product/product_detail_service").navigation();
        if (iProductDetailService != null) {
            iProductDetailService.unRegisterProductShareListener();
        }
        AppMethodBeat.o(20756);
    }

    @Subscribe(threadMode = ThreadMode.MainThread)
    public void onHandleEvent(cn.missfresh.module.base.common.event.e eVar) {
        AppMethodBeat.i(20758, false);
        if (!this.t) {
            AppMethodBeat.o(20758);
            return;
        }
        if (eVar.isSuccess) {
            if (this.l == null) {
                this.l = new PayManager(this, this);
                this.l.d();
            }
            if (c.a.equals("\u5fae\u4fe1")) {
                this.l.a(eVar.a);
            } else if (c.a.equals("\u652f\u4ed8\u5b9d")) {
                this.l.b(eVar.a);
            }
        } else {
            cn.missfresh.ui.a.a.a("\u64cd\u4f5c\u5931\u8d25");
        }
        AppMethodBeat.o(20758);
    }

    @Subscribe(threadMode = ThreadMode.MainThread)
    public void onHandleEvent(FlutterReflushShoppingCartEvent flutterReflushShoppingCartEvent) {
        AppMethodBeat.i(20759, false);
        h().invokeMethod("refreshShopCart", null);
        AppMethodBeat.o(20759);
    }

    @Subscribe(threadMode = ThreadMode.MainThread)
    public void onHandleEvent(EventShoppingCartMergeBean eventShoppingCartMergeBean) {
        AppMethodBeat.i(20760, false);
        a("shopCartMergeSuccess", (Object) null);
        AppMethodBeat.o(20760);
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x007f, code lost:
        if (r2.equals("mryx_pay") != false) goto L_0x00a5;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onHandleEvent(cn.missfresh.flutter.bean.EventFlutterPayInfo r6) {
        /*
            r5 = this;
            r0 = 0
            r1 = 20761(0x5119, float:2.9092E-41)
            cn.missfresh.sherlock.trace.core.AppMethodBeat.i(r1, r0)
            cn.missfresh.module.base.manager.PayManager r2 = r5.l
            if (r2 != 0) goto L_0x0016
            cn.missfresh.module.base.manager.PayManager r2 = new cn.missfresh.module.base.manager.PayManager
            r2.<init>(r5, r5)
            r5.l = r2
            cn.missfresh.module.base.manager.PayManager r2 = r5.l
            r2.d()
        L_0x0016:
            java.lang.String r2 = r6.payType
            cn.missfresh.module.base.bean.PayInfo r3 = r6.payInfo
            java.lang.String r6 = r6.page
            r5.o = r6
            r6 = -1
            int r4 = r2.hashCode()
            switch(r4) {
                case -1844795824: goto L_0x0099;
                case -1688290705: goto L_0x008e;
                case -1650614320: goto L_0x0082;
                case -1633106579: goto L_0x0078;
                case -1414960566: goto L_0x006d;
                case -722642976: goto L_0x0062;
                case -157281751: goto L_0x0056;
                case 113584679: goto L_0x004b;
                case 393021609: goto L_0x003f;
                case 813554329: goto L_0x0034;
                case 1608604850: goto L_0x0028;
                default: goto L_0x0026;
            }
        L_0x0026:
            goto L_0x00a4
        L_0x0028:
            java.lang.String r0 = "wxpay_plus"
            boolean r0 = r2.equals(r0)
            if (r0 == 0) goto L_0x00a4
            r0 = 1
            goto L_0x00a5
        L_0x0034:
            java.lang.String r0 = "pcredit_alipay_plus"
            boolean r0 = r2.equals(r0)
            if (r0 == 0) goto L_0x00a4
            r0 = 4
            goto L_0x00a5
        L_0x003f:
            java.lang.String r0 = "alipay_contract_pay_plus"
            boolean r0 = r2.equals(r0)
            if (r0 == 0) goto L_0x00a4
            r0 = 10
            goto L_0x00a5
        L_0x004b:
            java.lang.String r0 = "wxpay"
            boolean r0 = r2.equals(r0)
            if (r0 == 0) goto L_0x00a4
            r0 = 2
            goto L_0x00a5
        L_0x0056:
            java.lang.String r0 = "china_union_pay_plus"
            boolean r0 = r2.equals(r0)
            if (r0 == 0) goto L_0x00a4
            r0 = 8
            goto L_0x00a5
        L_0x0062:
            java.lang.String r0 = "pcredit_alipay"
            boolean r0 = r2.equals(r0)
            if (r0 == 0) goto L_0x00a4
            r0 = 3
            goto L_0x00a5
        L_0x006d:
            java.lang.String r0 = "alipay"
            boolean r0 = r2.equals(r0)
            if (r0 == 0) goto L_0x00a4
            r0 = 6
            goto L_0x00a5
        L_0x0078:
            java.lang.String r4 = "mryx_pay"
            boolean r2 = r2.equals(r4)
            if (r2 == 0) goto L_0x00a4
            goto L_0x00a5
        L_0x0082:
            java.lang.String r0 = "alipay_contract_pay"
            boolean r0 = r2.equals(r0)
            if (r0 == 0) goto L_0x00a4
            r0 = 9
            goto L_0x00a5
        L_0x008e:
            java.lang.String r0 = "alipay_plus"
            boolean r0 = r2.equals(r0)
            if (r0 == 0) goto L_0x00a4
            r0 = 5
            goto L_0x00a5
        L_0x0099:
            java.lang.String r0 = "china_union_pay"
            boolean r0 = r2.equals(r0)
            if (r0 == 0) goto L_0x00a4
            r0 = 7
            goto L_0x00a5
        L_0x00a4:
            r0 = r6
        L_0x00a5:
            switch(r0) {
                case 0: goto L_0x00cf;
                case 1: goto L_0x00ca;
                case 2: goto L_0x00ca;
                case 3: goto L_0x00c4;
                case 4: goto L_0x00c4;
                case 5: goto L_0x00c4;
                case 6: goto L_0x00c4;
                case 7: goto L_0x00b8;
                case 8: goto L_0x00b8;
                case 9: goto L_0x00a9;
                case 10: goto L_0x00a9;
                default: goto L_0x00a8;
            }
        L_0x00a8:
            goto L_0x00cf
        L_0x00a9:
            if (r3 == 0) goto L_0x00cf
            boolean r6 = r3.needContract
            if (r6 == 0) goto L_0x00cf
            cn.missfresh.module.base.manager.PayManager r6 = r5.l
            r6.b(r3)
            cn.missfresh.sherlock.trace.core.AppMethodBeat.o(r1)
            return
        L_0x00b8:
            java.lang.String r6 = r3.spId
            java.lang.String r0 = r3.sysProvider
            java.lang.String r2 = r3.orderInfo
            java.lang.String r3 = r3.mode
            com.unionpay.UPPayAssistEx.startPay(r5, r6, r0, r2, r3)
            goto L_0x00cf
        L_0x00c4:
            cn.missfresh.module.base.manager.PayManager r6 = r5.l
            r6.b(r3)
            goto L_0x00cf
        L_0x00ca:
            cn.missfresh.module.base.manager.PayManager r6 = r5.l
            r6.a(r3)
        L_0x00cf:
            cn.missfresh.sherlock.trace.core.AppMethodBeat.o(r1)
            return
            switch-data {-1844795824->0x0099, -1688290705->0x008e, -1650614320->0x0082, -1633106579->0x0078, -1414960566->0x006d, -722642976->0x0062, -157281751->0x0056, 113584679->0x004b, 393021609->0x003f, 813554329->0x0034, 1608604850->0x0028, }
            switch-data {0->0x00cf, 1->0x00ca, 2->0x00ca, 3->0x00c4, 4->0x00c4, 5->0x00c4, 6->0x00c4, 7->0x00b8, 8->0x00b8, 9->0x00a9, 10->0x00a9, }
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.missfresh.flutter.FlutterPageActivity.onHandleEvent(cn.missfresh.flutter.bean.EventFlutterPayInfo):void");
    }

    @Override // cn.missfresh.module.base.manager.PayManager.a
    public void ab_() {
        AppMethodBeat.i(20762, false);
        j();
        PayManager payManager = this.l;
        if (payManager != null) {
            payManager.e();
        }
        a("paySuccess", (Object) null);
        if (this.o != null) {
            d();
            this.l.e();
            EventBus.getDefault().post(new EventFlutterPayResultBean(true, this.o));
        }
        AppMethodBeat.o(20762);
    }

    @Override // cn.missfresh.module.base.manager.PayManager.a
    public void d_() {
        AppMethodBeat.i(20763, false);
        PayManager payManager = this.l;
        if (payManager != null) {
            payManager.e();
        }
        if (this.s) {
            AppMethodBeat.o(20763);
            return;
        }
        EventBus.getDefault().post(new EventFlutterPayResultBean(false, this.o));
        AppMethodBeat.o(20763);
    }

    @Override // cn.missfresh.module.base.manager.PayManager.a
    public void e_() {
        AppMethodBeat.i(20765, false);
        PayManager payManager = this.l;
        if (payManager != null) {
            payManager.e();
        }
        if (this.s) {
            AppMethodBeat.o(20765);
            return;
        }
        EventBus.getDefault().post(new EventFlutterPayResultBean(false, this.o));
        AppMethodBeat.o(20765);
    }

    /* JADX WARNING: Removed duplicated region for block: B:24:0x0050  */
    @Override // io.flutter.embedding.android.FlutterFragmentActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onActivityResult(int r5, int r6, android.content.Intent r7) {
        /*
            r4 = this;
            r0 = 0
            r1 = 20769(0x5121, float:2.9104E-41)
            cn.missfresh.sherlock.trace.core.AppMethodBeat.i(r1, r0)
            super.onActivityResult(r5, r6, r7)
            r2 = 257(0x101, float:3.6E-43)
            if (r2 != r5) goto L_0x0018
            r5 = 0
            java.lang.String r6 = "refreshOrderList"
            r4.a(r6, r5)
            cn.missfresh.sherlock.trace.core.AppMethodBeat.o(r1)
            return
        L_0x0018:
            r2 = 258(0x102, float:3.62E-43)
            java.lang.String r3 = ""
            if (r5 != r2) goto L_0x0073
            r5 = -1
            if (r6 != r5) goto L_0x0073
            if (r7 == 0) goto L_0x006f
            android.net.Uri r5 = r7.getData()
            java.lang.String[] r5 = cn.missfresh.module.base.utils.j.b(r4, r5)
            r6 = r5[r0]     // Catch:{ Exception -> 0x0039 }
            boolean r6 = android.text.TextUtils.isEmpty(r6)     // Catch:{ Exception -> 0x0039 }
            if (r6 != 0) goto L_0x0037
            r6 = r5[r0]     // Catch:{ Exception -> 0x0039 }
            goto L_0x003e
        L_0x0037:
            r6 = r3
            goto L_0x003e
        L_0x0039:
            r6 = move-exception
            r6.printStackTrace()
            goto L_0x0037
        L_0x003e:
            r7 = 1
            r5 = r5[r7]     // Catch:{ Exception -> 0x0046 }
            java.lang.String r3 = cn.missfresh.module.base.utils.at.f(r5)     // Catch:{ Exception -> 0x0046 }
            goto L_0x004a
        L_0x0046:
            r5 = move-exception
            r5.printStackTrace()
        L_0x004a:
            io.flutter.plugin.common.MethodChannel r5 = r4.h()
            if (r5 == 0) goto L_0x006f
            com.alibaba.fastjson.JSONObject r5 = new com.alibaba.fastjson.JSONObject
            r5.<init>()
            java.lang.String r7 = "name"
            r5.put(r7, r6)
            java.lang.String r6 = "phone"
            r5.put(r6, r3)
            io.flutter.plugin.common.MethodChannel r6 = r4.h()
            java.lang.String r5 = r5.toJSONString()
            java.lang.String r7 = "updateContact"
            r6.invokeMethod(r7, r5)
        L_0x006f:
            cn.missfresh.sherlock.trace.core.AppMethodBeat.o(r1)
            return
        L_0x0073:
            if (r7 == 0) goto L_0x00cc
            android.os.Bundle r5 = r7.getExtras()
            if (r5 == 0) goto L_0x00cc
            android.os.Bundle r5 = r7.getExtras()
            java.lang.String r6 = "pay_result"
            java.lang.String r5 = r5.getString(r6)
            boolean r6 = cn.missfresh.utils.b.a(r5)
            if (r6 != 0) goto L_0x00cc
            if (r5 == 0) goto L_0x009e
            java.lang.String r6 = "success"
            boolean r6 = r5.equalsIgnoreCase(r6)
            if (r6 == 0) goto L_0x009e
            r4.ab_()
            java.lang.String r3 = "\u652f\u4ed8\u6210\u529f\uff01"
            goto L_0x00c6
        L_0x009e:
            java.lang.String r6 = "fail"
            boolean r6 = r5.equalsIgnoreCase(r6)
            if (r6 == 0) goto L_0x00b7
            r4.e_()
            java.lang.String r5 = "\u4ea4\u6613\u94fe\u8def"
            java.lang.String r6 = "\u94f6\u8054\u4e91\u95ea\u4ed8\u652f\u4ed8\u5931\u8d25"
            cn.missfresh.module.base.utils.ac.a(r5, r6, r3)
            java.lang.String r3 = "\u652f\u4ed8\u5931\u8d25\uff01"
            goto L_0x00c6
        L_0x00b7:
            java.lang.String r6 = "cancel"
            boolean r5 = r5.equalsIgnoreCase(r6)
            if (r5 == 0) goto L_0x00c6
            r4.d_()
            java.lang.String r3 = "\u7528\u6237\u53d6\u6d88\u4e86\u652f\u4ed8"
        L_0x00c6:
            java.lang.String r5 = "FlutterPageActivity"
            cn.missfresh.utils.a.d.d(r5, r3)
        L_0x00cc:
            cn.missfresh.sherlock.trace.core.AppMethodBeat.o(r1)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.missfresh.flutter.FlutterPageActivity.onActivityResult(int, int, android.content.Intent):void");
    }

    /* access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        AppMethodBeat.i(20772, false);
        super.onResume();
        this.t = true;
        a("addonRefreshSettle", (Object) null);
        IShoppingCartService2 iShoppingCartService2 = (IShoppingCartService2) a.a().a("/order/new_shoppingcart_service").navigation();
        if (iShoppingCartService2 != null) {
            a("updateLocalShopCartData", iShoppingCartService2.e());
        }
        AppMethodBeat.o(20772);
    }

    /* access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onPause() {
        AppMethodBeat.i(20774, false);
        super.onPause();
        this.t = false;
        AppMethodBeat.o(20774);
    }

    @Override // io.flutter.embedding.android.FlutterFragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        AppMethodBeat.i(20776, false);
        a("navigatorPop", (Object) null);
        AppMethodBeat.o(20776);
    }

    @Override // cn.missfresh.module.base.common.providers.IProductDetailService.a
    public void d() {
        AppMethodBeat.i(20779, false);
        i();
        if (g() && !this.j.isShowing()) {
            try {
                this.j.show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        AppMethodBeat.o(20779);
    }

    @Override // cn.missfresh.module.base.common.providers.IProductDetailService.a
    public void e() {
        AppMethodBeat.i(20780, false);
        i();
        if (g() && this.j.isShowing()) {
            try {
                this.j.dismiss();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        AppMethodBeat.o(20780);
    }

    @Override // cn.missfresh.module.base.common.providers.IProductDetailService.a
    public boolean f() {
        AppMethodBeat.i(20782, false);
        ProgressDialog progressDialog = this.j;
        if (progressDialog == null) {
            AppMethodBeat.o(20782);
            return false;
        }
        boolean isShowing = progressDialog.isShowing();
        AppMethodBeat.o(20782);
        return isShowing;
    }

    private void i() {
        AppMethodBeat.i(20784, false);
        if (this.j == null) {
            this.j = new ProgressDialog(this);
            this.j.setCanceledOnTouchOutside(false);
            this.j.setMessage("\u8bf7\u7a0d\u5019...");
            this.j.setCancelable(true);
            this.j.dismiss();
        }
        AppMethodBeat.o(20784);
    }

    public boolean g() {
        AppMethodBeat.i(20785, false);
        boolean z = !isFinishing();
        AppMethodBeat.o(20785);
        return z;
    }

    public void a(String str, String str2, String str3) {
        AppMethodBeat.i(20786, false);
        if (this.l == null) {
            this.l = new PayManager(this, this);
            this.l.d();
            d.d("flutter", "init payManager");
        }
        this.r = str;
        this.q = str2;
        this.o = str3;
        this.s = true;
        c cVar = this.p;
        if (cVar == null || !cVar.c()) {
            this.p = e.a(this, this.h);
        }
        AppMethodBeat.o(20786);
    }

    private void j() {
        AppMethodBeat.i(20788, false);
        c cVar = this.p;
        if (cVar != null && cVar.c()) {
            this.p.b();
        }
        AppMethodBeat.o(20788);
    }

    /* renamed from: cn.missfresh.flutter.FlutterPageActivity$3  reason: invalid class name */
    class AnonymousClass3 implements e.a {
        final /* synthetic */ FlutterPageActivity a;

        AnonymousClass3(FlutterPageActivity flutterPageActivity) {
            JniLib.cV(this, flutterPageActivity, 3);
        }

        @Override // cn.missfresh.module.base.support.dialog.e.a
        public void a(String str) {
            AppMethodBeat.i(20727, false);
            this.a.l.a(this.a.r, str);
            AppMethodBeat.o(20727);
        }

        @Override // cn.missfresh.module.base.support.dialog.e.a
        public void a() {
            AppMethodBeat.i(20728, false);
            FlutterPageActivity flutterPageActivity = this.a;
            BindPayPhoneActivity.a(flutterPageActivity, flutterPageActivity.q);
            AppMethodBeat.o(20728);
        }
    }
}
