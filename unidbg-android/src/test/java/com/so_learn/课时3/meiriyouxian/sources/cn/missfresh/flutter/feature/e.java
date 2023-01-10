package cn.missfresh.flutter.feature;

import android.accounts.AccountManager;
import android.content.Intent;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.Observer;
import cn.missfresh.flutter.FlutterPageActivity;
import cn.missfresh.flutter.bean.AddCartBean;
import cn.missfresh.flutter.bean.FlutterReflushShoppingCartEvent;
import cn.missfresh.flutter.c;
import cn.missfresh.flutter.feature.base.a;
import cn.missfresh.module.base.b.b;
import cn.missfresh.module.base.bean.AddResult;
import cn.missfresh.module.base.bean.BannerEntity;
import cn.missfresh.module.base.bean.OrderDetail;
import cn.missfresh.module.base.bean.PayInfo;
import cn.missfresh.module.base.common.providers.IOrderDetailsService;
import cn.missfresh.module.base.common.providers.IShoppingCartService2;
import cn.missfresh.module.base.common.providers.ISurveyService;
import cn.missfresh.module.base.im.IMManager;
import cn.missfresh.module.base.manager.g;
import cn.missfresh.module.base.network.i;
import cn.missfresh.module.base.payment.pwd.view.BindPayPhoneActivity;
import cn.missfresh.module.base.utils.j;
import cn.missfresh.module.base.utils.q;
import cn.missfresh.module.order.orderdetails_v2.bean.QueryPayResultBean;
import cn.missfresh.module.order.orderdetails_v2.provider.IQuickPayService;
import cn.missfresh.module.other.market.bean.MarketEvalBean;
import cn.missfresh.module.other.provider.IMarketEvaluationService;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.taobao.accs.common.Constants;
import de.greenrobot.event.EventBus;
import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* compiled from: OrderFlutterFeature */
public class e extends a {
    IQuickPayService a;
    private IMarketEvaluationService b;
    private HashMap<String, MethodChannel.Result> d = new HashMap<>();

    static /* synthetic */ FragmentActivity a(e eVar) {
        AppMethodBeat.i(21153, false);
        FragmentActivity b = eVar.b();
        AppMethodBeat.o(21153);
        return b;
    }

    static /* synthetic */ FragmentActivity b(e eVar) {
        AppMethodBeat.i(21154, false);
        FragmentActivity b = eVar.b();
        AppMethodBeat.o(21154);
        return b;
    }

    static /* synthetic */ FragmentActivity c(e eVar) {
        AppMethodBeat.i(21156, false);
        FragmentActivity b = eVar.b();
        AppMethodBeat.o(21156);
        return b;
    }

    static /* synthetic */ FragmentActivity d(e eVar) {
        AppMethodBeat.i(21158, false);
        FragmentActivity b = eVar.b();
        AppMethodBeat.o(21158);
        return b;
    }

    static /* synthetic */ MethodChannel e(e eVar) {
        AppMethodBeat.i(21159, false);
        MethodChannel c = eVar.c();
        AppMethodBeat.o(21159);
        return c;
    }

    static /* synthetic */ MethodChannel f(e eVar) {
        AppMethodBeat.i(21161, false);
        MethodChannel c = eVar.c();
        AppMethodBeat.o(21161);
        return c;
    }

    static /* synthetic */ MethodChannel g(e eVar) {
        AppMethodBeat.i(21164, false);
        MethodChannel c = eVar.c();
        AppMethodBeat.o(21164);
        return c;
    }

    static /* synthetic */ FragmentActivity h(e eVar) {
        AppMethodBeat.i(21166, false);
        FragmentActivity b = eVar.b();
        AppMethodBeat.o(21166);
        return b;
    }

    public e(FragmentActivity fragmentActivity, MethodChannel methodChannel) {
        super(fragmentActivity, methodChannel);
        AppMethodBeat.i(20965, false);
        AppMethodBeat.o(20965);
    }

    public void a(MethodCall methodCall, MethodChannel.Result result) {
        AppMethodBeat.i(20969, false);
        if (methodCall.method.equals("prePayOrder")) {
            String str = (String) methodCall.argument("tipOrderId");
            String str2 = (String) methodCall.argument("payMethod");
            c.a = str2;
            if (str2.equals("\u5fae\u4fe1")) {
                b.a("FlutterReward", str, "wxpay", (OrderDetail.ChooseBalance) null);
            } else if (str2.equals("\u652f\u4ed8\u5b9d")) {
                b.a("FlutterReward", str, "alipay", (OrderDetail.ChooseBalance) null);
            }
            result.success("");
        } else if (methodCall.method.equals("addShoppingCart")) {
            c(methodCall, result);
        } else if (methodCall.method.equals("addCart")) {
            b(methodCall, result);
        } else if (methodCall.method.equals("fetchLocalShopCart")) {
            a(result);
        } else if (methodCall.method.equals("startPay")) {
            String str3 = (String) methodCall.argument("payType");
            PayInfo payInfo = (PayInfo) JSON.parseObject((String) methodCall.argument("payModel"), PayInfo.class);
            if ("wxpay".equals(str3)) {
                c.a = "\u5fae\u4fe1";
            } else if ("alipay".equals(str3)) {
                c.a = "\u652f\u4ed8\u5b9d";
            }
            EventBus.getDefault().post(new cn.missfresh.module.base.common.event.e(payInfo));
        } else if (methodCall.method.equals("payFinish")) {
            if (((Integer) methodCall.argument("fromBalance")).intValue() == 2) {
                c.b = true;
            }
        } else if (methodCall.method.equals("switchCouponCallBack")) {
            String str4 = methodCall.argument("voucherId") + "";
            String str5 = methodCall.argument("voucherCode") + "";
            String str6 = methodCall.argument("queryType") + "";
            Intent intent = new Intent();
            intent.putExtra("key_voucher_type", str6);
            intent.putExtra("key_voucher_select_id", str4);
            intent.putExtra("key_voucher_voucher_code", str5);
            intent.putExtra("key_voucher_select_group", methodCall.argument("saleGroupType") + "");
            b().setResult(100, intent);
        } else if (methodCall.method.equals("orderListBtnClick")) {
            cn.missfresh.flutter.helper.b.a(b(), methodCall, c());
        } else if (methodCall.method.equals("showSurvey")) {
            int intValue = ((Integer) methodCall.argument("fromPage")).intValue();
            String str7 = (String) methodCall.argument("orderId");
            if (str7 == null) {
                str7 = "";
            }
            ISurveyService iSurveyService = (ISurveyService) com.alibaba.android.arouter.b.a.a().a("/order/survey_service").navigation();
            if (iSurveyService != null) {
                iSurveyService.a(intValue, b(), str7).observe(b(), new AnonymousClass1());
            }
        } else if (methodCall.method.equals("syncShopCartData")) {
            ((IShoppingCartService2) com.alibaba.android.arouter.b.a.a().a("/order/new_shoppingcart_service").navigation()).c((String) methodCall.argument("shopCartData"));
        } else if (methodCall.method.equals("presentCouponPage")) {
            ((IShoppingCartService2) com.alibaba.android.arouter.b.a.a().a("/order/new_shoppingcart_service").navigation()).a(b().getSupportFragmentManager(), (String) methodCall.argument("saleGroupType"), (String) methodCall.argument("chooseVoucherListJson"));
        } else if (methodCall.method.equals("startPay_new")) {
            String str8 = (String) methodCall.argument("payType");
            String str9 = (String) methodCall.argument("page");
            this.c.put(str9, result);
            PayInfo payInfo2 = (PayInfo) JSON.parseObject((String) methodCall.argument("payModel"), PayInfo.class);
            if (b() != null && (b() instanceof FlutterPageActivity)) {
                b().runOnUiThread(new 2(this, str8, str9, payInfo2));
            }
        } else if (methodCall.method.equals("orderDetailSkip")) {
            cn.missfresh.flutter.helper.a.a(b(), methodCall, c(), result, this.c);
        } else if ("queryQuickPayResult".equals(methodCall.method)) {
            String str10 = (String) methodCall.argument("methodName");
            this.c.put(str10, result);
            a((String) methodCall.argument("orderNo"), str10);
        } else if ("dialPhone".equals(methodCall.method)) {
            q.a(b(), (String) methodCall.argument("phone"));
        } else if ("openIm".equals(methodCall.method)) {
            String str11 = (String) methodCall.argument("groupNo");
            if (IMManager.a().e() || !IMManager.a().f()) {
                cn.missfresh.ui.a.a.a("\u9a91\u624b\u6b63\u5728\u9001\u8d27\uff0c\u8bf7\u7a0d\u540e\u8054\u7cfb");
                AppMethodBeat.o(20969);
                return;
            }
            IMManager.a().a(str11);
        } else if ("showImageDialog".equals(methodCall.method)) {
            ((IOrderDetailsService) com.alibaba.android.arouter.b.a.a().a("/order/details_service").navigation()).a(b(), (List) methodCall.argument("images"));
        } else if ("verifyPhoneAndSettingPwd".equals(methodCall.method)) {
            BindPayPhoneActivity.a(b(), (String) methodCall.argument("phoneNum"));
        } else if ("payMryx".equals(methodCall.method)) {
            String str12 = (String) methodCall.argument("tradeOrderNo");
            String str13 = (String) methodCall.argument("phoneNum");
            this.c.put("payMryx", result);
            if (b() != null && (b() instanceof FlutterPageActivity)) {
                ((FlutterPageActivity) b()).a(str12, str13, "payMryx");
            }
        } else if ("skipToCustom".equals(methodCall.method)) {
            com.alibaba.android.arouter.b.a.a().a("/order/custom_service").withString("customer_service_order_no", (String) methodCall.argument("orderNo")).navigation();
        } else if ("showOrderTimeLine".equals(methodCall.method)) {
            ((IOrderDetailsService) com.alibaba.android.arouter.b.a.a().a("/order/details_service").navigation()).a(b(), (String) methodCall.argument("orderTrace"));
        } else if ("getImInfo".equals(methodCall.method)) {
            String str14 = (String) methodCall.argument("groupNo");
            HashMap hashMap = new HashMap();
            hashMap.put("isInitIm", Boolean.valueOf(IMManager.a().e(str14)));
            hashMap.put("unReadImCount", Long.valueOf(IMManager.a().b(str14)));
            result.success(hashMap);
        } else if ("loginIm".equals(methodCall.method)) {
            this.c.put("imLogin", result);
            ((IOrderDetailsService) com.alibaba.android.arouter.b.a.a().a("/order/details_service").navigation()).a((String) methodCall.argument("jsonData"));
        } else if ("presentVipChangeProduct".equals(methodCall.method)) {
            ((IShoppingCartService2) com.alibaba.android.arouter.b.a.a().a("/order/new_shoppingcart_service").navigation()).a(b().getSupportFragmentManager());
            this.d.put("presentVipChangeProduct", result);
        } else if ("goToFirstShopCart".equals(methodCall.method)) {
            BannerEntity bannerEntity = new BannerEntity();
            bannerEntity.setType("FIRST_SHOPPING_CART");
            j.a(bannerEntity);
        } else if ("showMarketEvaluation".equalsIgnoreCase(methodCall.method)) {
            a((String) methodCall.argument("pageKey"), result);
        } else if ("paySuccess".equalsIgnoreCase(methodCall.method)) {
            ((IShoppingCartService2) com.alibaba.android.arouter.b.a.a().a("/order/new_shoppingcart_service").navigation()).d();
            EventBus.getDefault().post(new FlutterReflushShoppingCartEvent());
        }
        AppMethodBeat.o(20969);
    }

    /* compiled from: OrderFlutterFeature */
    /* renamed from: cn.missfresh.flutter.feature.e$1  reason: invalid class name */
    class AnonymousClass1 implements Observer<DialogFragment> {
        AnonymousClass1() {
        }

        @Override // androidx.lifecycle.Observer
        public /* synthetic */ void onChanged(Object obj) {
            AppMethodBeat.i(20928, false);
            a((DialogFragment) obj);
            AppMethodBeat.o(20928);
        }

        public void a(DialogFragment dialogFragment) {
            AppMethodBeat.i(20926, false);
            if (dialogFragment == null || e.a(e.this).getSupportFragmentManager() == null || dialogFragment.isAdded()) {
                AppMethodBeat.o(20926);
            } else if (e.b(e.this).getSupportFragmentManager().findFragmentByTag("survey") != null) {
                AppMethodBeat.o(20926);
            } else {
                dialogFragment.showNow(e.c(e.this).getSupportFragmentManager(), "survey");
                AppMethodBeat.o(20926);
            }
        }
    }

    private void b(MethodCall methodCall, MethodChannel.Result result) {
        AppMethodBeat.i(20973, false);
        IShoppingCartService2 iShoppingCartService2 = (IShoppingCartService2) com.alibaba.android.arouter.b.a.a().a("/order/new_shoppingcart_service").navigation();
        List<AddCartBean> list = (List) new Gson().fromJson((String) methodCall.argument("addProducts"), new AnonymousClass3().getType());
        if (cn.missfresh.utils.b.a(list)) {
            AppMethodBeat.o(20973);
            return;
        }
        String str = (String) methodCall.argument("header");
        String str2 = (String) methodCall.argument("from");
        String str3 = (String) methodCall.argument("uniqueCallback");
        Map<String, Object> map = (Map) methodCall.argument("extra");
        HashMap hashMap = new HashMap(list.size());
        HashMap hashMap2 = new HashMap(list.size());
        HashMap hashMap3 = new HashMap(list.size());
        for (AddCartBean addCartBean : list) {
            hashMap.put(addCartBean.sku, Integer.valueOf(addCartBean.count));
            hashMap3.put(addCartBean.sku, Integer.valueOf(iShoppingCartService2.b(addCartBean.sku) + addCartBean.count));
            hashMap2.put(addCartBean.sku, addCartBean.services);
        }
        if (iShoppingCartService2 != null) {
            HashMap hashMap4 = new HashMap();
            hashMap4.put("resultProducts", hashMap3);
            result.success(hashMap4);
            iShoppingCartService2.a(str, hashMap, hashMap2, str2, map).a(io.reactivex.a.b.a.a()).subscribe(new AnonymousClass4(null, str3, iShoppingCartService2));
        }
        AppMethodBeat.o(20973);
    }

    /* compiled from: OrderFlutterFeature */
    /* access modifiers changed from: package-private */
    /* renamed from: cn.missfresh.flutter.feature.e$3  reason: invalid class name */
    public class AnonymousClass3 extends TypeToken<List<AddCartBean>> {
        AnonymousClass3() {
        }
    }

    /* compiled from: OrderFlutterFeature */
    /* access modifiers changed from: package-private */
    /* renamed from: cn.missfresh.flutter.feature.e$4  reason: invalid class name */
    public class AnonymousClass4 extends i<AddResult> {
        final /* synthetic */ String a;
        final /* synthetic */ IShoppingCartService2 b;

        @Override // io.reactivex.v
        public void onComplete() {
        }

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        AnonymousClass4(cn.missfresh.module.mvp.a.a aVar, String str, IShoppingCartService2 iShoppingCartService2) {
            super(aVar);
            this.a = str;
            this.b = iShoppingCartService2;
        }

        @Override // io.reactivex.v
        public /* synthetic */ void onNext(Object obj) {
            AppMethodBeat.i(20943, false);
            a((AddResult) obj);
            AppMethodBeat.o(20943);
        }

        public void a(AddResult addResult) {
            AppMethodBeat.i(20940, false);
            if (addResult != null) {
                if (!cn.missfresh.utils.b.a(addResult.msg)) {
                    cn.missfresh.ui.a.a.a(addResult.msg);
                }
                HashMap hashMap = new HashMap();
                hashMap.put(Constants.KEY_HTTP_CODE, 0);
                hashMap.put("uniqueCallback", this.a);
                hashMap.put("resultProducts", addResult.products);
                e.e(e.this).invokeMethod("remoteAddCartCompleted", hashMap);
                e.f(e.this).invokeMethod("updateLocalShopCartData", this.b.e());
            }
            AppMethodBeat.o(20940);
        }

        /* access modifiers changed from: protected */
        @Override // cn.missfresh.module.base.network.i
        public void a(int i, String str) {
            AppMethodBeat.i(20941, false);
            if (!cn.missfresh.utils.b.a(str)) {
                cn.missfresh.ui.a.a.a(str);
            }
            HashMap hashMap = new HashMap();
            hashMap.put(Constants.KEY_HTTP_CODE, 1);
            hashMap.put("uniqueCallback", this.a);
            hashMap.put(AccountManager.KEY_ERROR_MESSAGE, str);
            e.g(e.this).invokeMethod("remoteAddCartCompleted", hashMap);
            AppMethodBeat.o(20941);
        }
    }

    private void c(MethodCall methodCall, MethodChannel.Result result) {
        AppMethodBeat.i(21146, false);
        String str = (String) methodCall.argument("sku");
        String str2 = (String) methodCall.argument("stock");
        String str3 = (String) methodCall.argument("limit");
        IShoppingCartService2 iShoppingCartService2 = (IShoppingCartService2) com.alibaba.android.arouter.b.a.a().a("/order/new_shoppingcart_service").navigation();
        if (iShoppingCartService2 != null && j.a(iShoppingCartService2.b(str) + 1, Integer.parseInt(str2), Integer.parseInt(str3))) {
            iShoppingCartService2.a("", str, 1).a(io.reactivex.a.b.a.a()).subscribe(new AnonymousClass5(null, result));
        }
        AppMethodBeat.o(21146);
    }

    /* compiled from: OrderFlutterFeature */
    /* access modifiers changed from: package-private */
    /* renamed from: cn.missfresh.flutter.feature.e$5  reason: invalid class name */
    public class AnonymousClass5 extends i<AddResult> {
        final /* synthetic */ MethodChannel.Result a;

        @Override // io.reactivex.v
        public void onComplete() {
        }

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        AnonymousClass5(cn.missfresh.module.mvp.a.a aVar, MethodChannel.Result result) {
            super(aVar);
            this.a = result;
        }

        @Override // io.reactivex.v
        public /* synthetic */ void onNext(Object obj) {
            AppMethodBeat.i(20951, false);
            a((AddResult) obj);
            AppMethodBeat.o(20951);
        }

        public void a(AddResult addResult) {
            AppMethodBeat.i(20946, false);
            if (addResult != null && !cn.missfresh.utils.b.a(addResult.msg)) {
                cn.missfresh.ui.a.a.a(addResult.msg);
            }
            this.a.success("");
            AppMethodBeat.o(20946);
        }

        /* access modifiers changed from: protected */
        @Override // cn.missfresh.module.base.network.i
        public void a(int i, String str) {
            AppMethodBeat.i(20949, false);
            if (!cn.missfresh.utils.b.a(str)) {
                cn.missfresh.ui.a.a.a(str);
            }
            AppMethodBeat.o(20949);
        }
    }

    private void a(MethodChannel.Result result) {
        AppMethodBeat.i(21148, false);
        IShoppingCartService2 iShoppingCartService2 = (IShoppingCartService2) com.alibaba.android.arouter.b.a.a().a("/order/new_shoppingcart_service").navigation();
        if (iShoppingCartService2 != null) {
            result.success(iShoppingCartService2.e());
        }
        AppMethodBeat.o(21148);
    }

    public void a(String str, String str2) {
        AppMethodBeat.i(21149, false);
        if (this.a == null) {
            this.a = (IQuickPayService) com.alibaba.android.arouter.b.a.a().a("/order/quick_service").navigation();
            this.a.e().observeForever(new AnonymousClass6(str2));
        }
        this.a.c(str);
        AppMethodBeat.o(21149);
    }

    /* compiled from: OrderFlutterFeature */
    /* access modifiers changed from: package-private */
    /* renamed from: cn.missfresh.flutter.feature.e$6  reason: invalid class name */
    public class AnonymousClass6 implements Observer<QueryPayResultBean> {
        final /* synthetic */ String a;

        AnonymousClass6(String str) {
            this.a = str;
        }

        @Override // androidx.lifecycle.Observer
        public /* synthetic */ void onChanged(Object obj) {
            AppMethodBeat.i(20958, false);
            a((QueryPayResultBean) obj);
            AppMethodBeat.o(20958);
        }

        public void a(QueryPayResultBean queryPayResultBean) {
            AppMethodBeat.i(20956, false);
            MethodChannel.Result a = e.this.a(this.a);
            if (a != null) {
                a.success(JSON.toJSONString(queryPayResultBean));
                e.this.b(this.a);
            }
            AppMethodBeat.o(20956);
        }
    }

    public void a() {
        AppMethodBeat.i(21150, false);
        MethodChannel.Result result = this.d.get("presentVipChangeProduct");
        if (result != null) {
            result.success(true);
            this.d.remove(result);
        }
        AppMethodBeat.o(21150);
    }

    private void a(String str, Object... objArr) {
        AppMethodBeat.i(21152, false);
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("pageKey", str);
        JSONObject jSONObject2 = new JSONObject();
        jSONObject2.put("orderNo", "");
        jSONObject2.put("intoPage", "IS_NOT_EMPTY");
        jSONObject2.put("quitPage", "IS_NOT_EMPTY");
        jSONObject.put("conditionValueMap", jSONObject2);
        if (this.b == null) {
            this.b = (IMarketEvaluationService) com.alibaba.android.arouter.b.a.a().a("/other/market_service").navigation();
            this.b.a().a(b(), new AnonymousClass7());
        }
        this.b.a(jSONObject, objArr);
        AppMethodBeat.o(21152);
    }

    /* compiled from: OrderFlutterFeature */
    /* access modifiers changed from: package-private */
    /* renamed from: cn.missfresh.flutter.feature.e$7  reason: invalid class name */
    public class AnonymousClass7 implements Observer<MarketEvalBean> {
        AnonymousClass7() {
        }

        @Override // androidx.lifecycle.Observer
        public /* synthetic */ void onChanged(Object obj) {
            AppMethodBeat.i(20962, false);
            a((MarketEvalBean) obj);
            AppMethodBeat.o(20962);
        }

        public void a(MarketEvalBean marketEvalBean) {
            AppMethodBeat.i(20960, false);
            e.this.b.a(e.h(e.this), marketEvalBean, g.a);
            AppMethodBeat.o(20960);
        }
    }
}
