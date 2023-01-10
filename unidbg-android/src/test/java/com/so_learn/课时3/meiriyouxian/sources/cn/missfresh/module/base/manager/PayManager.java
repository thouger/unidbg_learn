package cn.missfresh.module.base.manager;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.text.TextUtils;
import cn.missfresh.module.base.R;
import cn.missfresh.module.base.bean.OpenWxMianMiBean;
import cn.missfresh.module.base.bean.PayInfo;
import cn.missfresh.module.base.support.modelsupport.event.EventOrderReqData;
import cn.missfresh.module.base.support.modelsupport.event.EventOrderResData;
import cn.missfresh.module.base.utils.ac;
import cn.missfresh.module.base.utils.ai;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import cn.missfresh.utils.a.d;
import com.alipay.sdk.app.PayTask;
import com.tencent.mm.opensdk.modelbiz.WXOpenBusinessView;
import com.tencent.mm.opensdk.modelbiz.WXOpenBusinessWebview;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import de.greenrobot.event.EventBus;
import de.greenrobot.event.Subscribe;
import de.greenrobot.event.ThreadMode;
import java.util.HashMap;

public class PayManager {
    private final String a = "PayManager";
    private a b;
    private Activity c;
    private ProgressDialog d;
    private int e = -1;
    private BroadcastReceiver f = new AnonymousClass1();

    public interface a {
        void ab_();

        void d_();

        void e_();
    }

    public PayManager(a aVar, Activity activity) {
        AppMethodBeat.i(14506, false);
        this.b = aVar;
        this.c = activity;
        d.d("progressDialog===", "payWeixin....payInfo:");
        AppMethodBeat.o(14506);
    }

    /* renamed from: cn.missfresh.module.base.manager.PayManager$1  reason: invalid class name */
    class AnonymousClass1 extends BroadcastReceiver {
        AnonymousClass1() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            AppMethodBeat.i(14503, false);
            if ("wxpay_finish".equalsIgnoreCase(intent.getAction())) {
                PayManager.this.b.ab_();
            } else if ("wxpay_cancel".equalsIgnoreCase(intent.getAction())) {
                PayManager.this.b.d_();
            }
            AppMethodBeat.o(14503);
        }
    }

    public void a(int i) {
        this.e = i;
    }

    public int a() {
        return this.e;
    }

    public void b() {
        this.e = -1;
    }

    public void a(PayInfo payInfo) {
        AppMethodBeat.i(14508, false);
        d.d("PayManager", "payWeixin....payInfo:" + payInfo);
        if (payInfo != null) {
            if (TextUtils.isEmpty(payInfo.appId)) {
                cn.missfresh.module.base.common.config.a.a = "wx31562d0a467cb40d";
            } else {
                cn.missfresh.module.base.common.config.a.a = payInfo.appId;
            }
            try {
                IWXAPI createWXAPI = WXAPIFactory.createWXAPI(this.c, cn.missfresh.module.base.common.config.a.a);
                if (createWXAPI.isWXAppInstalled()) {
                    createWXAPI.registerApp(cn.missfresh.module.base.common.config.a.a);
                    PayReq payReq = new PayReq();
                    payReq.appId = payInfo.appId;
                    payReq.partnerId = payInfo.partnerId;
                    payReq.prepayId = payInfo.prepayId;
                    payReq.nonceStr = payInfo.nonceStr;
                    payReq.timeStamp = payInfo.timeStamp + "";
                    payReq.packageValue = payInfo.pack;
                    payReq.sign = payInfo.sign;
                    createWXAPI.sendReq(payReq);
                } else {
                    cn.missfresh.ui.a.a.a(this.c.getResources().getString(R.string.wechat_client_inavailable_topay));
                    this.b.e_();
                    ac.a("\u4ea4\u6613\u94fe\u8def", "\u5fae\u4fe1\u652f\u4ed8\u5524\u8d77\u5fae\u4fe1\u5931\u8d25", "\u5fae\u4fe1\u7248\u672c\u8fc7\u4f4e");
                }
            } catch (Exception e) {
                d.a("PayManager", e);
                this.b.e_();
            }
        } else {
            this.b.e_();
        }
        AppMethodBeat.o(14508);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: cn.missfresh.module.base.manager.PayManager$2  reason: invalid class name */
    public class AnonymousClass2 implements Runnable {
        final /* synthetic */ PayInfo a;

        AnonymousClass2(PayInfo payInfo) {
            this.a = payInfo;
        }

        @Override // java.lang.Runnable
        public void run() {
            AppMethodBeat.i(14505, false);
            try {
                PayManager.this.c.runOnUiThread(new AnonymousClass1(new PayTask(PayManager.this.c).pay(this.a.url, true)));
            } catch (Exception e) {
                d.a("PayManager", e);
                PayManager.this.b.e_();
            }
            AppMethodBeat.o(14505);
        }

        /* renamed from: cn.missfresh.module.base.manager.PayManager$2$1  reason: invalid class name */
        class AnonymousClass1 implements Runnable {
            final /* synthetic */ String a;

            AnonymousClass1(String str) {
                this.a = str;
            }

            @Override // java.lang.Runnable
            public void run() {
                AppMethodBeat.i(14504, false);
                String a = new ai(this.a).a();
                d.d("PayManager", "resultStatus is " + a);
                if (TextUtils.equals(a, "9000") || TextUtils.equals(a, "8000")) {
                    PayManager.this.b.ab_();
                } else {
                    cn.missfresh.ui.a.a.a("\u652f\u4ed8\u5931\u8d25");
                    PayManager.this.b.e_();
                    ac.a("\u4ea4\u6613\u94fe\u8def", "\u652f\u4ed8\u5b9d\u652f\u4ed8\u5931\u8d25", a);
                }
                AppMethodBeat.o(14504);
            }
        }
    }

    public void b(PayInfo payInfo) {
        AppMethodBeat.i(14509, false);
        if (payInfo != null) {
            try {
                new Thread(new AnonymousClass2(payInfo)).start();
            } catch (Exception e) {
                d.a("PayManager", e);
                this.b.e_();
            }
        }
        ProgressDialog progressDialog = this.d;
        if (progressDialog != null && progressDialog.isShowing()) {
            this.d.dismiss();
        }
        AppMethodBeat.o(14509);
    }

    public void a(String str, String str2) {
        AppMethodBeat.i(14510, false);
        d.d("PayManager", "payMryx...orderId:" + str + ",passWord:" + str2);
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
        EventOrderReqData eventOrderReqData = new EventOrderReqData();
        eventOrderReqData.setReqDetailType(2);
        eventOrderReqData.setOrderNo(String.valueOf(str));
        eventOrderReqData.setPayPassword(str2);
        eventOrderReqData.post();
        AppMethodBeat.o(14510);
    }

    @Subscribe(threadMode = ThreadMode.MainThread)
    public void onHandleEvent(EventOrderResData eventOrderResData) {
        AppMethodBeat.i(14511, false);
        d.d("PayManager", "handlerEvent...EventOrderResData:" + eventOrderResData);
        if (eventOrderResData.getOriginReq().getReqDetailType() == 2) {
            c();
            if (eventOrderResData.isIfsuc()) {
                this.b.ab_();
            } else {
                this.b.d_();
                cn.missfresh.ui.a.a.a(eventOrderResData.getErrMsg());
            }
        }
        AppMethodBeat.o(14511);
    }

    public void c() {
        AppMethodBeat.i(14512, false);
        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
        AppMethodBeat.o(14512);
    }

    public void d() {
        AppMethodBeat.i(14513, false);
        try {
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("wxpay_finish");
            intentFilter.addAction("wxpay_cancel");
            this.c.registerReceiver(this.f, intentFilter);
        } catch (Exception unused) {
        }
        AppMethodBeat.o(14513);
    }

    public void e() {
        AppMethodBeat.i(14514, false);
        try {
            this.c.unregisterReceiver(this.f);
        } catch (Exception unused) {
        }
        AppMethodBeat.o(14514);
    }

    public boolean b(String str, String str2) {
        AppMethodBeat.i(14515, false);
        try {
            OpenWxMianMiBean.from = str2;
            IWXAPI createWXAPI = WXAPIFactory.createWXAPI(this.c, cn.missfresh.module.base.common.config.a.a);
            WXOpenBusinessWebview.Req req = new WXOpenBusinessWebview.Req();
            req.businessType = 12;
            HashMap<String, String> hashMap = new HashMap<>();
            hashMap.put("pre_entrustweb_id", str);
            req.queryInfo = hashMap;
            boolean sendReq = createWXAPI.sendReq(req);
            AppMethodBeat.o(14515);
            return sendReq;
        } catch (Exception e) {
            e.printStackTrace();
            AppMethodBeat.o(14515);
            return false;
        }
    }

    public boolean a(String str) {
        AppMethodBeat.i(14516, false);
        IWXAPI createWXAPI = WXAPIFactory.createWXAPI(this.c, cn.missfresh.module.base.common.config.a.a);
        if (createWXAPI.getWXAppSupportAPI() >= 620889344) {
            WXOpenBusinessView.Req req = new WXOpenBusinessView.Req();
            req.businessType = "wxpayScoreEnable";
            req.query = str;
            req.extInfo = "{\"miniProgramType\": 0}";
            boolean sendReq = createWXAPI.sendReq(req);
            AppMethodBeat.o(14516);
            return sendReq;
        }
        ac.a("\u4ea4\u6613\u94fe\u8def", "\u5fae\u4fe1\u652f\u4ed8\u5206\u5524\u8d77\u5fae\u4fe1\u5931\u8d25", "\u5fae\u4fe1\u7248\u672c\u8fc7\u4f4e");
        cn.missfresh.ui.a.a.a("\u5fae\u4fe1\u7248\u672c\u8fc7\u4f4e");
        AppMethodBeat.o(14516);
        return false;
    }
}
