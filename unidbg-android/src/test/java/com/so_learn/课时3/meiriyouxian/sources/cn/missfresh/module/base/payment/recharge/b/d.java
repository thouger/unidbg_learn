package cn.missfresh.module.base.payment.recharge.b;

import android.app.Activity;
import android.security.keystore.KeyProperties;
import cn.missfresh.module.base.base.b;
import cn.missfresh.module.base.bean.PayInfo;
import cn.missfresh.module.base.common.api.RequestParam;
import cn.missfresh.module.base.common.d.d;
import cn.missfresh.module.base.manager.PayManager;
import cn.missfresh.module.base.network.i;
import cn.missfresh.module.base.network.j;
import cn.missfresh.module.base.payment.pwd.view.c;
import cn.missfresh.module.base.payment.recharge.api.RechargApiManager;
import cn.missfresh.module.base.payment.recharge.api.RechargRequestParam;
import cn.missfresh.module.base.payment.recharge.bean.RechargStatusBean;
import cn.missfresh.module.base.payment.recharge.bean.StoreCardBean;
import cn.missfresh.module.base.payment.recharge.bean.StoreCreateOrderBean;
import cn.missfresh.module.base.utils.ao;
import cn.missfresh.module.mvp.a.a;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import com.android.internal.logging.nano.MetricsProto;
import com.bangcle.andjni.JniLib;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

/* compiled from: RechargePresenter */
public class d extends b implements d.a, PayManager.a, c, a {
    private cn.missfresh.module.base.payment.recharge.view.b a;
    private cn.missfresh.module.base.payment.recharge.model.b b = new cn.missfresh.module.base.payment.recharge.model.b();
    private PayManager c;
    private cn.missfresh.module.base.payment.pwd.a.a d;
    private cn.missfresh.module.base.common.d.d e = new cn.missfresh.module.base.common.d.d(this);
    private io.reactivex.disposables.a f = new io.reactivex.disposables.a();

    private void a(PayInfo payInfo) {
        JniLib.cV(this, payInfo, 401);
    }

    private void t() {
        JniLib.cV(this, 402);
    }

    private void u() {
        JniLib.cV(this, 403);
    }

    public void a(int i) {
        JniLib.cV(this, Integer.valueOf(i), Integer.valueOf((int) MetricsProto.MetricsEvent.SUW_ACCESSIBILITY_TOGGLE_SCREEN_READER));
    }

    public void a(Activity activity) {
        JniLib.cV(this, activity, Integer.valueOf((int) MetricsProto.MetricsEvent.ACTION_SETTINGS_CONDITION_DISMISS));
    }

    public void a(StoreCardBean storeCardBean) {
        JniLib.cV(this, storeCardBean, Integer.valueOf((int) MetricsProto.MetricsEvent.ACTION_SETTINGS_CONDITION_EXPAND));
    }

    public void a(io.reactivex.disposables.b bVar) {
        JniLib.cV(this, bVar, Integer.valueOf((int) MetricsProto.MetricsEvent.ACTION_SETTINGS_CONDITION_COLLAPSE));
    }

    @Override // cn.missfresh.module.base.common.d.d.a
    public void a(String str) {
        JniLib.cV(this, str, Integer.valueOf((int) MetricsProto.MetricsEvent.ACTION_SETTINGS_CONDITION_CLICK));
    }

    @Override // cn.missfresh.module.base.common.d.d.a
    public void a(List<StoreCardBean> list) {
        JniLib.cV(this, list, Integer.valueOf((int) MetricsProto.MetricsEvent.ACTION_SETTINGS_CONDITION_BUTTON));
    }

    @Override // cn.missfresh.module.base.payment.pwd.view.c
    public void a(boolean z, String str, String str2) {
        JniLib.cV(this, Boolean.valueOf(z), str, str2, Integer.valueOf((int) MetricsProto.MetricsEvent.SETTINGS_CONDITION_AIRPLANE_MODE));
    }

    @Override // cn.missfresh.module.base.payment.pwd.view.c
    public void a(boolean z, boolean z2, String str, String str2, String str3) {
        JniLib.cV(this, Boolean.valueOf(z), Boolean.valueOf(z2), str, str2, str3, Integer.valueOf((int) MetricsProto.MetricsEvent.SETTINGS_CONDITION_BACKGROUND_DATA));
    }

    public void b(int i) {
        JniLib.cV(this, Integer.valueOf(i), Integer.valueOf((int) MetricsProto.MetricsEvent.SETTINGS_CONDITION_BATTERY_SAVER));
    }

    public void b(String str) {
        JniLib.cV(this, str, Integer.valueOf((int) MetricsProto.MetricsEvent.SETTINGS_CONDITION_CELLULAR_DATA));
    }

    @Override // cn.missfresh.module.base.payment.pwd.view.c
    public void b_(String str) {
        JniLib.cV(this, str, Integer.valueOf((int) MetricsProto.MetricsEvent.SETTINGS_CONDITION_DND));
    }

    public void c(String str) {
        JniLib.cV(this, str, Integer.valueOf((int) MetricsProto.MetricsEvent.SETTINGS_CONDITION_HOTSPOT));
    }

    public void d(String str) {
        JniLib.cV(this, str, Integer.valueOf((int) MetricsProto.MetricsEvent.SETTINGS_CONDITION_WORK_MODE));
    }

    @Override // cn.missfresh.module.base.manager.PayManager.a
    public void d_() {
        JniLib.cV(this, Integer.valueOf((int) MetricsProto.MetricsEvent.ACTION_SHOW_SETTINGS_SUGGESTION));
    }

    public void e(String str) {
        JniLib.cV(this, str, Integer.valueOf((int) MetricsProto.MetricsEvent.ACTION_HIDE_SETTINGS_SUGGESTION));
    }

    @Override // cn.missfresh.module.base.manager.PayManager.a
    public void e_() {
        JniLib.cV(this, Integer.valueOf((int) MetricsProto.MetricsEvent.ACTION_SETTINGS_SUGGESTION));
    }

    public List<StoreCardBean> f() {
        return (List) JniLib.cL(this, Integer.valueOf((int) MetricsProto.MetricsEvent.ACTION_SETTINGS_DISMISS_SUGGESTION));
    }

    public StoreCardBean g() {
        return (StoreCardBean) JniLib.cL(this, Integer.valueOf((int) MetricsProto.MetricsEvent.PREMIUM_SMS_ACCESS));
    }

    public String h() {
        return (String) JniLib.cL(this, Integer.valueOf((int) MetricsProto.MetricsEvent.ACTION_WINDOW_DOCK_RESIZE));
    }

    public int i() {
        return JniLib.cI(this, Integer.valueOf((int) MetricsProto.MetricsEvent.ACTION_WINDOW_UNDOCK_MAX));
    }

    public String j() {
        return (String) JniLib.cL(this, Integer.valueOf((int) MetricsProto.MetricsEvent.ACTION_WINDOW_DOCK_UNRESIZABLE));
    }

    public void k() {
        JniLib.cV(this, Integer.valueOf((int) MetricsProto.MetricsEvent.TUNER_POWER_NOTIFICATION_CONTROLS));
    }

    public void l() {
        JniLib.cV(this, Integer.valueOf((int) MetricsProto.MetricsEvent.ACTION_TUNER_POWER_NOTIFICATION_CONTROLS));
    }

    public String m() {
        return (String) JniLib.cL(this, Integer.valueOf((int) MetricsProto.MetricsEvent.ACTION_DATA_SAVER_MODE));
    }

    public void n() {
        JniLib.cV(this, Integer.valueOf((int) MetricsProto.MetricsEvent.ACTION_DATA_SAVER_WHITELIST));
    }

    public int o() {
        return JniLib.cI(this, Integer.valueOf((int) MetricsProto.MetricsEvent.ACTION_DATA_SAVER_BLACKLIST));
    }

    public String p() {
        return (String) JniLib.cL(this, Integer.valueOf((int) MetricsProto.MetricsEvent.ACTION_REMOTE_INPUT_OPEN));
    }

    public void q() {
        JniLib.cV(this, Integer.valueOf((int) MetricsProto.MetricsEvent.ACTION_REMOTE_INPUT_SEND));
    }

    public void r() {
        JniLib.cV(this, Integer.valueOf((int) MetricsProto.MetricsEvent.ACTION_REMOTE_INPUT_FAIL));
    }

    public void s() {
        JniLib.cV(this, 400);
    }

    static /* synthetic */ void a(d dVar, PayInfo payInfo) {
        AppMethodBeat.i(17289, false);
        dVar.a(payInfo);
        AppMethodBeat.o(17289);
    }

    static /* synthetic */ void b(d dVar) {
        AppMethodBeat.i(17286, false);
        dVar.t();
        AppMethodBeat.o(17286);
    }

    public d(cn.missfresh.module.base.payment.recharge.view.b bVar) {
        AppMethodBeat.i(17198, false);
        this.a = bVar;
        AppMethodBeat.o(17198);
    }

    /* compiled from: RechargePresenter */
    /* renamed from: cn.missfresh.module.base.payment.recharge.b.d$1  reason: invalid class name */
    class AnonymousClass1 extends i<StoreCreateOrderBean> {
        /* access modifiers changed from: protected */
        @Override // cn.missfresh.module.base.network.i
        public void a(int i, String str) {
            JniLib.cV(this, Integer.valueOf(i), str, 363);
        }

        public void a(StoreCreateOrderBean storeCreateOrderBean) {
            JniLib.cV(this, storeCreateOrderBean, 364);
        }

        @Override // io.reactivex.v
        public void onComplete() {
            JniLib.cV(this, Integer.valueOf((int) MetricsProto.MetricsEvent.ACTION_QS_EDIT_MOVE));
        }

        AnonymousClass1(a aVar) {
            super(aVar);
        }

        @Override // io.reactivex.v
        public /* synthetic */ void onNext(Object obj) {
            AppMethodBeat.i(17162, false);
            a((StoreCreateOrderBean) obj);
            AppMethodBeat.o(17162);
        }
    }

    /* compiled from: RechargePresenter */
    /* renamed from: cn.missfresh.module.base.payment.recharge.b.d$2  reason: invalid class name */
    class AnonymousClass2 extends i<PayInfo> {
        /* access modifiers changed from: protected */
        @Override // cn.missfresh.module.base.network.i
        public void a(int i, String str) {
            JniLib.cV(this, Integer.valueOf(i), str, Integer.valueOf((int) MetricsProto.MetricsEvent.ACTION_QS_LONG_PRESS));
        }

        public void a(PayInfo payInfo) {
            JniLib.cV(this, payInfo, Integer.valueOf((int) MetricsProto.MetricsEvent.SUW_ACCESSIBILITY));
        }

        @Override // io.reactivex.v
        public void onComplete() {
            JniLib.cV(this, Integer.valueOf((int) MetricsProto.MetricsEvent.SUW_ACCESSIBILITY_TOGGLE_SCREEN_MAGNIFICATION));
        }

        AnonymousClass2(a aVar) {
            super(aVar);
        }

        @Override // io.reactivex.v
        public /* synthetic */ void onNext(Object obj) {
            AppMethodBeat.i(17175, false);
            a((PayInfo) obj);
            AppMethodBeat.o(17175);
        }
    }

    @Override // cn.missfresh.module.base.manager.PayManager.a
    public void ab_() {
        AppMethodBeat.i(17255, false);
        cn.missfresh.module.base.payment.recharge.model.b bVar = this.b;
        if (!(bVar == null || bVar.f() == null)) {
            String orderId = this.b.f().getOrderId();
            String a = ao.a(32);
            String substring = a.substring(0, 16);
            String substring2 = a.substring(16, 32);
            String f = f(substring + orderId + substring2);
            RequestParam<RechargRequestParam> requestParam = new RequestParam<>();
            RechargRequestParam rechargRequestParam = new RechargRequestParam();
            rechargRequestParam.setOrderNo(orderId);
            rechargRequestParam.setNonceStr(a);
            rechargRequestParam.setSign(cn.missfresh.utils.b.a(f) ? "" : f.toUpperCase());
            requestParam.setParam(rechargRequestParam);
            RechargApiManager.getRechargApi().checkRechargStatus(requestParam).a(cn.missfresh.basiclib.net.e.a.a).subscribe(new cn.missfresh.basiclib.net.c(new AnonymousClass3()));
        }
        AppMethodBeat.o(17255);
    }

    /* compiled from: RechargePresenter */
    /* renamed from: cn.missfresh.module.base.payment.recharge.b.d$3  reason: invalid class name */
    class AnonymousClass3 extends j<RechargStatusBean> {
        @Override // cn.missfresh.module.base.network.j
        public void a(int i, String str) {
            JniLib.cV(this, Integer.valueOf(i), str, Integer.valueOf((int) MetricsProto.MetricsEvent.SUW_ACCESSIBILITY_FONT_SIZE));
        }

        public void a(RechargStatusBean rechargStatusBean) {
            JniLib.cV(this, rechargStatusBean, Integer.valueOf((int) MetricsProto.MetricsEvent.SUW_ACCESSIBILITY_DISPLAY_SIZE));
        }

        AnonymousClass3() {
        }

        @Override // cn.missfresh.basiclib.net.a.a
        public /* synthetic */ void onSuccess(Object obj) {
            AppMethodBeat.i(17188, false);
            a((RechargStatusBean) obj);
            AppMethodBeat.o(17188);
        }
    }

    private String f(String str) {
        AppMethodBeat.i(17261, false);
        try {
            MessageDigest instance = MessageDigest.getInstance(KeyProperties.DIGEST_MD5);
            instance.update(str.getBytes());
            byte[] digest = instance.digest();
            StringBuffer stringBuffer = new StringBuffer();
            for (byte b : digest) {
                int i = b;
                if (b < 0) {
                    i = b + 256;
                }
                if (i < 16) {
                    stringBuffer.append("0");
                }
                stringBuffer.append(Integer.toHexString(i == 1 ? 1 : 0));
            }
            String stringBuffer2 = stringBuffer.toString();
            AppMethodBeat.o(17261);
            return stringBuffer2;
        } catch (NoSuchAlgorithmException e) {
            cn.missfresh.utils.a.d.b(getClass().getSimpleName(), "rechargMD5 e=" + e);
            AppMethodBeat.o(17261);
            return "";
        }
    }
}
