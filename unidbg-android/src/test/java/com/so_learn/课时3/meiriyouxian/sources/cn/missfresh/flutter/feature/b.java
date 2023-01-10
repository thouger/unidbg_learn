package cn.missfresh.flutter.feature;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;
import cn.missfresh.buttomline.abtest.ABTest;
import cn.missfresh.flutter.R;
import cn.missfresh.flutter.feature.BasisFlutterFeature;
import cn.missfresh.flutter.feature.base.a;
import cn.missfresh.module.base.bean.BannerEntity;
import cn.missfresh.module.base.bean.ProductInfoBean;
import cn.missfresh.module.base.bean.ShareInfo;
import cn.missfresh.module.base.common.api.RequestParam;
import cn.missfresh.module.base.common.providers.IProductDetailService;
import cn.missfresh.module.base.common.resourcemanager.bean.SkipBean;
import cn.missfresh.module.base.datastatistics.StatisticsManager;
import cn.missfresh.module.base.manager.e;
import cn.missfresh.module.base.manager.g;
import cn.missfresh.module.base.manager.r;
import cn.missfresh.module.base.network.d;
import cn.missfresh.module.base.network.i;
import cn.missfresh.module.base.support.dialog.BaseTipDialog;
import cn.missfresh.module.base.support.dialog.FlutterTipsDialog;
import cn.missfresh.module.base.support.dialog.f;
import cn.missfresh.module.base.support.share.MiniRoutineHelper;
import cn.missfresh.module.base.utils.j;
import cn.missfresh.module.base.utils.q;
import cn.missfresh.player.MediaUtils;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.taobao.accs.common.Constants;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.umeng.analytics.pro.ai;
import com.unionpay.tsmservice.data.Constant;
import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.reactivex.c.h;
import io.reactivex.t;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* compiled from: BasisFlutterFeature */
public class b extends a {
    public static List<String> b = new ArrayList();
    private static final Map<String, Object> d = new HashMap();
    IProductDetailService a = ((IProductDetailService) com.alibaba.android.arouter.b.a.a().a("/product/product_detail_service").navigation());

    static /* synthetic */ FragmentActivity a(b bVar) {
        AppMethodBeat.i(20883, false);
        FragmentActivity b2 = bVar.b();
        AppMethodBeat.o(20883);
        return b2;
    }

    static /* synthetic */ t a(b bVar, String str, RequestParam requestParam, String str2) {
        AppMethodBeat.i(20884, false);
        t<String> a = bVar.a(str, requestParam, str2);
        AppMethodBeat.o(20884);
        return a;
    }

    static {
        AppMethodBeat.i(20886, false);
        AppMethodBeat.o(20886);
    }

    public b(FragmentActivity fragmentActivity, MethodChannel methodChannel) {
        super(fragmentActivity, methodChannel);
        AppMethodBeat.i(20857, false);
        b.add("/as/talent");
        b.add("/as/item/live/detail");
        b.add("/as/item/sendVoucher");
        this.a.unRegisterProductShareListener();
        AppMethodBeat.o(20857);
    }

    public void a(MethodCall methodCall, MethodChannel.Result result) {
        int i = 0;
        AppMethodBeat.i(20865, false);
        if (methodCall.method.equals("finish")) {
            b().finish();
        } else if (methodCall.method.equals("toast")) {
            String str = (String) methodCall.argument("msg");
            if (((Boolean) methodCall.argument("isCenter")).booleanValue()) {
                cn.missfresh.ui.a.a.b(str);
            } else {
                cn.missfresh.ui.a.a.a(str);
            }
        } else if (methodCall.method.equals("statistics")) {
            StatisticsManager.onNewEventToMRYXOpt((String) methodCall.argument("event"), (String) methodCall.argument("label"), (String) methodCall.argument(ai.e), (String) methodCall.argument("button"), (String) methodCall.argument("event_type"), (Map) methodCall.argument(Constant.KEY_PARAMS));
            result.success("");
        } else if (methodCall.method.equals("jumpNativePage")) {
            Postcard c = j.c((String) methodCall.argument("path"));
            Map map = (Map) methodCall.argument(Constant.KEY_PARAMS);
            if (map != null && map.size() > 0) {
                for (Map.Entry entry : map.entrySet()) {
                    if (!(entry.getKey() == null || entry.getValue() == null)) {
                        if (entry.getValue() instanceof Integer) {
                            c.withInt((String) entry.getKey(), ((Integer) entry.getValue()).intValue());
                        } else if (entry.getValue() instanceof Boolean) {
                            c.withBoolean((String) entry.getKey(), ((Boolean) entry.getValue()).booleanValue());
                        } else if (entry.getValue() instanceof String) {
                            c.withString((String) entry.getKey(), (String) entry.getValue());
                        } else {
                            c.withString((String) entry.getKey(), entry.getValue().toString());
                        }
                    }
                }
            }
            String str2 = (String) methodCall.argument("action");
            if (methodCall.argument("flag") != null) {
                i = ((Integer) methodCall.argument("flag")).intValue();
            }
            c.withAction(str2);
            if (i == 1) {
                c.addFlags(67108864);
            }
            c.withBoolean("fromFlutter", true);
            c.navigation(b());
        } else if (methodCall.method.equals("callPhone")) {
            String str3 = (String) methodCall.argument("phone");
            if (!TextUtils.isEmpty(str3)) {
                q.a(b(), "\u8054\u7cfb\u914d\u9001\u5458", str3, str3);
            }
        } else if (methodCall.method.equals("resourceSkip")) {
            cn.missfresh.module.base.common.resourcemanager.a.a().a((SkipBean) JSONObject.parseObject((String) methodCall.argument("bannerResource"), SkipBean.class), null);
        } else if (methodCall.method.equals("bannerClick")) {
            a(methodCall);
        } else if (methodCall.method.equals("isAppInstall")) {
            result.success(Integer.valueOf(a(((Integer) methodCall.argument("appType")).intValue())));
        } else if (methodCall.method.equals("doShare")) {
            try {
                String str4 = (String) methodCall.argument("shareInfo");
                int intValue = ((Integer) methodCall.argument("type")).intValue();
                if (!"/product/product_detail".equals((String) methodCall.argument("fromSource")) || !(b() instanceof IProductDetailService.a)) {
                    r.a(b()).a((ShareInfo) JSONObject.parseObject(str4, ShareInfo.class), intValue);
                } else {
                    this.a.showProductShareDialog(b(), str4, (IProductDetailService.a) b());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (methodCall.method.equals("httpGet")) {
            a(methodCall, result, 1);
        } else if (methodCall.method.equals("httpPost")) {
            a(methodCall, result, 2);
        } else if (methodCall.method.equals("statisticsNew")) {
            String str5 = (String) methodCall.argument("event_label");
            String str6 = (String) methodCall.argument("event_id");
            List list = (List) methodCall.argument("service_path");
            Map map2 = (Map) methodCall.argument("event_params");
            if (cn.missfresh.utils.b.a(map2)) {
                map2 = new HashMap();
            }
            if (!cn.missfresh.utils.b.a(list)) {
                map2.put("requestId", StatisticsManager.a((String[]) list.toArray(new String[0])));
            }
            StatisticsManager.onNewEventToMRYX2(str6, str5, map2);
            result.success("");
        } else if (methodCall.method.equals("setShareViewData")) {
            this.a.setShareViewData((ProductInfoBean) JSON.parseObject((String) methodCall.argument("productInfo"), ProductInfoBean.class));
        } else if (methodCall.method.equals("isWifiConnected")) {
            result.success(Boolean.valueOf(MediaUtils.isWifiConnected(b())));
        } else if (methodCall.method.equals("getIndividualization")) {
            result.success(Boolean.valueOf(e.aw()));
        } else if (methodCall.method.equals("getABTestValue")) {
            result.success(Integer.valueOf(ABTest.get((String) methodCall.argument("caseId"))));
        } else if (methodCall.method.equals("setSharedMemory")) {
            for (Map.Entry entry2 : ((Map) methodCall.argument("data")).entrySet()) {
                String str7 = (String) entry2.getKey();
                Object value = entry2.getValue();
                if (value == null) {
                    d.remove(str7);
                } else {
                    d.put(str7, value);
                }
            }
            result.success(d);
        } else if (methodCall.method.equals("shareToMiniApp")) {
            String str8 = (String) methodCall.argument("miniImgUrl");
            d.a(b(), str8, 0, 0, new AnonymousClass1(str8, (String) methodCall.argument("miniOriginalId"), (String) methodCall.argument("miniPath"), (String) methodCall.argument("miniTitle"), (String) methodCall.argument("miniDesc")));
        } else if (methodCall.method.equals("syncSharedMemoryData")) {
            result.success(d);
        } else if (methodCall.method.equals("platformDialog")) {
            b(methodCall, result);
        } else if (methodCall.method.equals("platformDialog")) {
            b(methodCall, result);
        } else if ("routerDidChange".equalsIgnoreCase(methodCall.method)) {
            g.a = (String) methodCall.argument("router");
            cn.missfresh.utils.a.d.d("BasisFlutterFeature", g.a);
        }
        AppMethodBeat.o(20865);
    }

    /* compiled from: BasisFlutterFeature */
    /* renamed from: cn.missfresh.flutter.feature.b$1  reason: invalid class name */
    class AnonymousClass1 implements d.b<Bitmap> {
        final /* synthetic */ String a;
        final /* synthetic */ String b;
        final /* synthetic */ String c;
        final /* synthetic */ String d;
        final /* synthetic */ String e;

        @Override // cn.missfresh.module.base.network.d.b
        public void a(Exception exc, Drawable drawable) {
        }

        AnonymousClass1(String str, String str2, String str3, String str4, String str5) {
            this.a = str;
            this.b = str2;
            this.c = str3;
            this.d = str4;
            this.e = str5;
        }

        @Override // cn.missfresh.module.base.network.d.b
        public /* synthetic */ void a(Object obj) {
            AppMethodBeat.i(20812, false);
            a((Bitmap) obj);
            AppMethodBeat.o(20812);
        }

        public void a(Bitmap bitmap) {
            AppMethodBeat.i(20809, false);
            new MiniRoutineHelper(b.a(b.this).getApplicationContext()).a(this.a, this.b, this.c, this.d, this.e, bitmap);
            AppMethodBeat.o(20809);
        }
    }

    private void b(MethodCall methodCall, MethodChannel.Result result) {
        AppMethodBeat.i(20869, false);
        int intValue = ((Integer) methodCall.argument("style")).intValue();
        String str = (String) methodCall.argument("title");
        String str2 = (String) methodCall.argument("content");
        String str3 = (String) methodCall.argument("confirmText");
        String str4 = (String) methodCall.argument("cancelText");
        if (intValue == 1) {
            new f(b(), str, str3, str4, new BasisFlutterFeature.2(this, result), new BasisFlutterFeature.3(this, result)).show();
        } else if (intValue == 2) {
            FlutterTipsDialog.a a = FlutterTipsDialog.l().a(false).b(str4).c(str3).a(ContextCompat.getColor(b(), R.color.color_474245)).b(1).a(new AnonymousClass2(result));
            if (!cn.missfresh.utils.b.a(str2)) {
                a.a(str2);
            }
            if (!cn.missfresh.utils.b.a(str)) {
                a.d(str);
            }
            a.a(b()).k();
        }
        AppMethodBeat.o(20869);
    }

    /* compiled from: BasisFlutterFeature */
    /* access modifiers changed from: package-private */
    /* renamed from: cn.missfresh.flutter.feature.b$2  reason: invalid class name */
    public class AnonymousClass2 implements BaseTipDialog.a {
        final /* synthetic */ MethodChannel.Result a;

        AnonymousClass2(MethodChannel.Result result) {
            this.a = result;
        }

        @Override // cn.missfresh.module.base.support.dialog.BaseTipDialog.a
        public void a(int i, Object obj) {
            AppMethodBeat.i(20821, false);
            if (i == 100) {
                this.a.success(Constant.CASH_LOAD_CANCEL);
            } else if (i == 101) {
                this.a.success("confirm");
            }
            AppMethodBeat.o(20821);
        }
    }

    private int a(int i) {
        AppMethodBeat.i(20871, false);
        if (i != 1 || !WXAPIFactory.createWXAPI(b(), "wx31562d0a467cb40d", true).isWXAppInstalled()) {
            AppMethodBeat.o(20871);
            return 0;
        }
        AppMethodBeat.o(20871);
        return 1;
    }

    /* compiled from: BasisFlutterFeature */
    /* access modifiers changed from: package-private */
    /* renamed from: cn.missfresh.flutter.feature.b$4  reason: invalid class name */
    public class AnonymousClass4 implements h<MethodCall, t<String>> {
        final /* synthetic */ int a;

        AnonymousClass4(int i) {
            this.a = i;
        }

        @Override // io.reactivex.c.h
        public /* synthetic */ Object apply(Object obj) throws Exception {
            AppMethodBeat.i(20842, false);
            t<String> a = a((MethodCall) obj);
            AppMethodBeat.o(20842);
            return a;
        }

        public t<String> a(MethodCall methodCall) throws Exception {
            Map map;
            boolean z = false;
            AppMethodBeat.i(20838, false);
            String str = (String) methodCall.argument("url");
            Map map2 = methodCall.hasArgument("param") ? (Map) methodCall.argument("param") : null;
            if (methodCall.hasArgument("ignoreCommon")) {
                z = ((Boolean) methodCall.argument("ignoreCommon")).booleanValue();
            }
            String str2 = (!methodCall.hasArgument("extraHeader") || (map = (Map) methodCall.argument("extraHeader")) == null) ? null : (String) map.get("settleFrom");
            if (this.a == 1) {
                if (map2 == null || map2.isEmpty()) {
                    io.reactivex.q a = cn.missfresh.flutter.b.a().a(str);
                    AppMethodBeat.o(20838);
                    return a;
                }
                cn.missfresh.flutter.a a2 = cn.missfresh.flutter.b.a();
                io.reactivex.q a3 = a2.a(str + "#oldURL", map2);
                AppMethodBeat.o(20838);
                return a3;
            } else if (z) {
                cn.missfresh.flutter.a a4 = cn.missfresh.flutter.b.a();
                io.reactivex.q b = a4.b(str + "#oldURL", map2);
                AppMethodBeat.o(20838);
                return b;
            } else {
                RequestParam requestParam = new RequestParam();
                requestParam.setParam(map2);
                if (cn.missfresh.utils.e.a(str2)) {
                    t<String> a5 = b.a(b.this, str, requestParam, null);
                    AppMethodBeat.o(20838);
                    return a5;
                }
                t<String> a6 = b.a(b.this, str, requestParam, str2);
                AppMethodBeat.o(20838);
                return a6;
            }
        }
    }

    private void a(MethodCall methodCall, MethodChannel.Result result, int i) {
        AppMethodBeat.i(20872, false);
        io.reactivex.q.a(methodCall).a((h) new AnonymousClass4(i)).b(io.reactivex.f.a.b()).a(io.reactivex.a.b.a.a()).subscribe(new AnonymousClass3(null, result));
        AppMethodBeat.o(20872);
    }

    /* compiled from: BasisFlutterFeature */
    /* access modifiers changed from: package-private */
    /* renamed from: cn.missfresh.flutter.feature.b$3  reason: invalid class name */
    public class AnonymousClass3 extends i<String> {
        final /* synthetic */ MethodChannel.Result a;

        @Override // io.reactivex.v
        public void onComplete() {
        }

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        AnonymousClass3(cn.missfresh.module.mvp.a.a aVar, MethodChannel.Result result) {
            super(aVar);
            this.a = result;
        }

        @Override // io.reactivex.v
        public /* synthetic */ void onNext(Object obj) {
            AppMethodBeat.i(20829, false);
            a((String) obj);
            AppMethodBeat.o(20829);
        }

        public void a(String str) {
            AppMethodBeat.i(20823, false);
            this.a.success(str);
            AppMethodBeat.o(20823);
        }

        /* access modifiers changed from: protected */
        @Override // cn.missfresh.module.base.network.i
        public void a(int i, String str) {
            AppMethodBeat.i(20826, false);
            MethodChannel.Result result = this.a;
            result.error(i + "", str, null);
            AppMethodBeat.o(20826);
        }
    }

    private t<String> a(String str, RequestParam<Map<String, String>> requestParam, String str2) {
        AppMethodBeat.i(20875, false);
        if (c(str)) {
            if (TextUtils.isEmpty(str2)) {
                io.reactivex.q a = cn.missfresh.flutter.e.a().a(str, requestParam);
                AppMethodBeat.o(20875);
                return a;
            }
            io.reactivex.q a2 = cn.missfresh.flutter.e.a().a(str, requestParam, str2);
            AppMethodBeat.o(20875);
            return a2;
        } else if (TextUtils.isEmpty(str2)) {
            io.reactivex.q a3 = cn.missfresh.flutter.b.a().a(str, requestParam);
            AppMethodBeat.o(20875);
            return a3;
        } else {
            io.reactivex.q a4 = cn.missfresh.flutter.b.a().a(str, requestParam, str2);
            AppMethodBeat.o(20875);
            return a4;
        }
    }

    private boolean c(String str) {
        AppMethodBeat.i(20877, false);
        if (TextUtils.isEmpty(str)) {
            AppMethodBeat.o(20877);
            return false;
        }
        for (String str2 : b) {
            if (str.toLowerCase().contains(str2.toLowerCase())) {
                AppMethodBeat.o(20877);
                return true;
            }
        }
        AppMethodBeat.o(20877);
        return false;
    }

    private void a(MethodCall methodCall) {
        AppMethodBeat.i(20880, false);
        try {
            BannerEntity bannerEntity = new BannerEntity();
            int intValue = ((Integer) methodCall.argument(Constants.KEY_DATA_ID)).intValue();
            int intValue2 = ((Integer) methodCall.argument("width")).intValue();
            int intValue3 = ((Integer) methodCall.argument("height")).intValue();
            bannerEntity.setPath((String) methodCall.argument("path"));
            bannerEntity.setDataId(intValue);
            bannerEntity.setLink((String) methodCall.argument("link"));
            bannerEntity.setName((String) methodCall.argument("name"));
            bannerEntity.setPromotion_id((String) methodCall.argument("promotion_id"));
            bannerEntity.setType((String) methodCall.argument("type"));
            bannerEntity.setWidth(intValue2);
            bannerEntity.setHeight(intValue3);
            bannerEntity.orderConfirmationParam = (String) methodCall.argument("orderConfirmationParam");
            bannerEntity.setCard_type((String) methodCall.argument("card_type"));
            bannerEntity.setActiveSource((String) methodCall.argument("activeSource"));
            j.a(bannerEntity);
        } catch (Exception unused) {
        }
        AppMethodBeat.o(20880);
    }
}
