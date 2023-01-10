package cn.missfresh.flutter;

import android.content.Context;
import androidx.fragment.app.FragmentActivity;
import cn.missfresh.buttomline.logtrace.bean.LogBean;
import cn.missfresh.flutter.feature.a;
import cn.missfresh.flutter.feature.base.AddressFlutterFeature;
import cn.missfresh.flutter.feature.base.b;
import cn.missfresh.flutter.feature.c;
import cn.missfresh.flutter.feature.d;
import cn.missfresh.flutter.feature.e;
import cn.missfresh.flutter.feature.g;
import cn.missfresh.flutter.feature.h;
import cn.missfresh.module.base.bean.EventFlutterCancelBean;
import cn.missfresh.module.base.bean.EventFlutterFlushOrderDetail;
import cn.missfresh.module.base.bean.EventFlutterImLoginEvent;
import cn.missfresh.module.base.bean.EventFlutterPayResultBean;
import cn.missfresh.module.base.bean.EventFreeProduct;
import cn.missfresh.module.base.utils.as;
import cn.missfresh.module.base.utils.aw;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import cn.missfresh.utils.f;
import com.alibaba.fastjson.JSONObject;
import com.unionpay.tsmservice.data.Constant;
import com.xiaomi.mipush.sdk.Constants;
import de.greenrobot.event.EventBus;
import de.greenrobot.event.Subscribe;
import io.flutter.embedding.engine.FlutterEngine;
import io.flutter.embedding.engine.plugins.FlutterPlugin;
import io.flutter.embedding.engine.plugins.activity.ActivityAware;
import io.flutter.embedding.engine.plugins.activity.ActivityPluginBinding;
import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class FlutterCallAndroid implements FlutterPlugin, ActivityAware, MethodChannel.MethodCallHandler {
    public static String a = "cn.missfresh.flutter/plugin";
    private FragmentActivity b;
    private MethodChannel c;
    private Map<String, Object> d;
    private final Map<String, List<b>> e;

    @Override // io.flutter.embedding.engine.plugins.activity.ActivityAware
    public void onDetachedFromActivity() {
    }

    @Override // io.flutter.embedding.engine.plugins.activity.ActivityAware
    public void onDetachedFromActivityForConfigChanges() {
    }

    @Override // io.flutter.embedding.engine.plugins.activity.ActivityAware
    public void onReattachedToActivityForConfigChanges(ActivityPluginBinding activityPluginBinding) {
    }

    public FlutterCallAndroid(FragmentActivity fragmentActivity, Map<String, Object> map) {
        AppMethodBeat.i(20615, false);
        this.b = fragmentActivity;
        this.d = map;
        if (this.d == null) {
            this.d = new HashMap();
        }
        this.d.put("screenWidth", Double.valueOf((double) f.a(fragmentActivity, aw.a())));
        this.d.put("screenHeight", Double.valueOf((double) f.a(fragmentActivity, aw.b())));
        this.d.put("statusBarHeight", Double.valueOf((double) f.a(fragmentActivity, as.a((Context) fragmentActivity))));
        this.e = new HashMap();
        AppMethodBeat.o(20615);
    }

    public void a(FragmentActivity fragmentActivity, FlutterEngine flutterEngine) {
        AppMethodBeat.i(20619, false);
        this.c = new MethodChannel(flutterEngine.getDartExecutor(), a);
        ArrayList arrayList = new ArrayList();
        arrayList.add(new cn.missfresh.flutter.feature.b(fragmentActivity, this.c));
        this.e.put("feature_basis", arrayList);
        ArrayList arrayList2 = new ArrayList();
        arrayList2.add(new h(fragmentActivity, this.c));
        this.e.put("feature_user", arrayList2);
        ArrayList arrayList3 = new ArrayList();
        arrayList3.add(new cn.missfresh.flutter.feature.f(fragmentActivity, this.c));
        this.e.put("feature_setting", arrayList3);
        ArrayList arrayList4 = new ArrayList();
        arrayList4.add(new e(fragmentActivity, this.c));
        this.e.put("feature_order", arrayList4);
        ArrayList arrayList5 = new ArrayList();
        arrayList5.add(new a(fragmentActivity, this.c));
        this.e.put("feature_apm", arrayList5);
        ArrayList arrayList6 = new ArrayList();
        arrayList6.add(new c(fragmentActivity, this.c));
        this.e.put("feature_comment", arrayList6);
        ArrayList arrayList7 = new ArrayList();
        arrayList7.add(new g(fragmentActivity, this.c));
        this.e.put("feature_talent", arrayList7);
        ArrayList arrayList8 = new ArrayList();
        arrayList8.add(new AddressFlutterFeature(fragmentActivity, this.c));
        this.e.put("feature_address", arrayList8);
        ArrayList arrayList9 = new ArrayList();
        arrayList8.add(new d(fragmentActivity, this.c));
        this.e.put("feature_me_page", arrayList9);
        this.c.setMethodCallHandler(this);
        AppMethodBeat.o(20619);
    }

    public MethodChannel a() {
        return this.c;
    }

    public void a(String str, b bVar) {
        AppMethodBeat.i(20627, false);
        this.e.get(str).add(bVar);
        AppMethodBeat.o(20627);
    }

    @Override // io.flutter.plugin.common.MethodChannel.MethodCallHandler
    public void onMethodCall(MethodCall methodCall, MethodChannel.Result result) {
        AppMethodBeat.i(20635, false);
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
        if (methodCall.method.equals("getInitParams")) {
            result.success(this.d);
        } else if (!methodCall.method.equals("navigatorStateChange")) {
            if (methodCall.method.equals("loadSelectRefundInfo")) {
                result.success(this.d);
            } else if (methodCall.method.equals("mimeType")) {
                result.success(cn.missfresh.utils.c.b((String) methodCall.argument("filePath"), false));
            } else if (methodCall.method.equals("localImageSize")) {
                int[] a2 = cn.missfresh.basiclib.utils.a.a((String) methodCall.argument("filePath"));
                result.success((a2 == null || a2.length < 2) ? "" : a2[0] + Constants.ACCEPT_TIME_SEPARATOR_SP + a2[1]);
            } else {
                List<b> list = this.e.get((String) methodCall.argument("native_feature"));
                if (cn.missfresh.utils.b.a(list)) {
                    AppMethodBeat.o(20635);
                    return;
                }
                for (b bVar : list) {
                    if (bVar != null) {
                        bVar.a(methodCall, result);
                    }
                }
            }
        }
        AppMethodBeat.o(20635);
    }

    @Override // io.flutter.embedding.engine.plugins.FlutterPlugin
    public void onAttachedToEngine(FlutterPlugin.FlutterPluginBinding flutterPluginBinding) {
        AppMethodBeat.i(20638, false);
        this.c = new MethodChannel(flutterPluginBinding.getFlutterEngine().getDartExecutor(), a);
        this.c.setMethodCallHandler(this);
        AppMethodBeat.o(20638);
    }

    @Override // io.flutter.embedding.engine.plugins.FlutterPlugin
    public void onDetachedFromEngine(FlutterPlugin.FlutterPluginBinding flutterPluginBinding) {
        AppMethodBeat.i(20641, false);
        EventBus.getDefault().unregister(this);
        AppMethodBeat.o(20641);
    }

    @Override // io.flutter.embedding.engine.plugins.activity.ActivityAware
    public void onAttachedToActivity(ActivityPluginBinding activityPluginBinding) {
        AppMethodBeat.i(20645, false);
        this.b = (FlutterPageActivity) activityPluginBinding.getActivity();
        AppMethodBeat.o(20645);
    }

    @Subscribe
    public void onHandleEvent(EventFlutterFlushOrderDetail eventFlutterFlushOrderDetail) {
        MethodChannel.Result a2;
        AppMethodBeat.i(20661, false);
        List<b> list = this.e.get("feature_order");
        if (cn.missfresh.utils.b.a(list)) {
            AppMethodBeat.o(20661);
            return;
        }
        for (b bVar : list) {
            if (!(bVar == null || (a2 = bVar.a(eventFlutterFlushOrderDetail.methodName)) == null)) {
                a2.success(eventFlutterFlushOrderDetail.toJSonString());
                bVar.b(eventFlutterFlushOrderDetail.methodName);
            }
        }
        AppMethodBeat.o(20661);
    }

    @Subscribe
    public void onHandleEvent(EventFreeProduct eventFreeProduct) {
        AppMethodBeat.i(20663, false);
        List<b> list = this.e.get("feature_order");
        if (!cn.missfresh.utils.b.a(list)) {
            Iterator<b> it2 = list.iterator();
            while (it2.hasNext()) {
                e eVar = (b) it2.next();
                if (eVar instanceof e) {
                    eVar.a();
                }
            }
        }
        AppMethodBeat.o(20663);
    }

    @Subscribe
    public void onHandleEvent(EventFlutterPayResultBean eventFlutterPayResultBean) {
        MethodChannel.Result a2;
        AppMethodBeat.i(20666, false);
        List<b> list = this.e.get("feature_order");
        if (cn.missfresh.utils.b.a(list)) {
            AppMethodBeat.o(20666);
            return;
        }
        for (b bVar : list) {
            if (!(bVar == null || (a2 = bVar.a(eventFlutterPayResultBean.methodName)) == null)) {
                ((FlutterPageActivity) this.b).e();
                a2.success(Boolean.valueOf(eventFlutterPayResultBean.isPaySuccess));
                bVar.b(eventFlutterPayResultBean.methodName);
            }
        }
        AppMethodBeat.o(20666);
    }

    @Subscribe
    public void onHandleEvent(EventFlutterImLoginEvent eventFlutterImLoginEvent) {
        AppMethodBeat.i(20669, false);
        List<b> list = this.e.get("feature_order");
        if (cn.missfresh.utils.b.a(list)) {
            AppMethodBeat.o(20669);
            return;
        }
        for (b bVar : list) {
            if (bVar != null) {
                MethodChannel.Result a2 = bVar.a("imLogin");
                if (eventFlutterImLoginEvent == null || a2 == null) {
                    AppMethodBeat.o(20669);
                    return;
                } else {
                    a2.success(Boolean.valueOf(eventFlutterImLoginEvent.isLoginSuccess));
                    bVar.b("imLogin");
                }
            }
        }
        AppMethodBeat.o(20669);
    }

    @Subscribe
    public void onHandleEvent(EventFlutterCancelBean eventFlutterCancelBean) {
        AppMethodBeat.i(20675, false);
        List<b> list = this.e.get("feature_order");
        if (cn.missfresh.utils.b.a(list)) {
            AppMethodBeat.o(20675);
            return;
        }
        for (b bVar : list) {
            if (bVar != null) {
                MethodChannel.Result a2 = bVar.a(Constant.CASH_LOAD_CANCEL);
                if (eventFlutterCancelBean == null || a2 == null) {
                    AppMethodBeat.o(20675);
                    return;
                }
                JSONObject jSONObject = new JSONObject();
                JSONObject jSONObject2 = new JSONObject();
                if (eventFlutterCancelBean.isSuccess) {
                    jSONObject.put("orderId", eventFlutterCancelBean.orderId);
                    jSONObject.put("orderNo", eventFlutterCancelBean.orderNo);
                    jSONObject.put("tagId", Long.valueOf(eventFlutterCancelBean.tagId));
                    jSONObject.put("cancelReason", eventFlutterCancelBean.cancelReason);
                    jSONObject2.put("param", jSONObject);
                    jSONObject2.put("isSelect", Boolean.valueOf(eventFlutterCancelBean.isSelect));
                    jSONObject2.put("error", new JSONObject());
                } else {
                    jSONObject.put("msg", eventFlutterCancelBean.error);
                    jSONObject.put(com.taobao.accs.common.Constants.KEY_HTTP_CODE, Integer.valueOf(eventFlutterCancelBean.code));
                    jSONObject2.put("error", jSONObject);
                    jSONObject2.put("isSelect", Boolean.valueOf(eventFlutterCancelBean.isSelect));
                }
                a2.success(jSONObject2.toJSONString());
                bVar.b(Constant.CASH_LOAD_CANCEL);
                a(jSONObject2.toJSONString(), "", "result_to_flutter");
            }
        }
        AppMethodBeat.o(20675);
    }

    public static void a(String str, String str2, String str3) {
        AppMethodBeat.i(20679, false);
        try {
            LogBean logBean = new LogBean();
            logBean.setType("cancel_in_sale");
            logBean.setStr_value_0(str);
            logBean.setStr_value_1(str2);
            logBean.setStr_value_3(str3);
            cn.missfresh.utils.a.d.b(logBean);
        } catch (Exception e) {
            e.printStackTrace();
        }
        AppMethodBeat.o(20679);
    }
}
