package cn.missfresh.module.base.support.modelsupport.a;

import cn.missfresh.module.base.common.config.i;
import cn.missfresh.module.base.network.c;
import cn.missfresh.module.base.network.m;
import cn.missfresh.module.base.support.modelsupport.event.EventOrderReqData;
import cn.missfresh.module.base.support.modelsupport.event.EventOrderResData;
import cn.missfresh.module.base.support.modelsupport.event.base.BaseReqEvent;
import cn.missfresh.module.base.utils.ac;
import cn.missfresh.module.base.utils.aq;
import cn.missfresh.module.base.utils.r;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import cn.missfresh.utils.a.d;
import cn.missfresh.utils.b;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.xiaomi.mipush.sdk.Constants;
import java.util.Map;
import okhttp3.Request;

/* compiled from: OrderDataDealer */
public class a {
    public void a(BaseReqEvent baseReqEvent) {
        AppMethodBeat.i(21971, false);
        d.d("OrderDataDealer", "doAction req:" + baseReqEvent);
        if (baseReqEvent == null) {
            IllegalArgumentException illegalArgumentException = new IllegalArgumentException("reqEvent is null");
            AppMethodBeat.o(21971);
            throw illegalArgumentException;
        } else if (baseReqEvent instanceof EventOrderReqData) {
            EventOrderReqData eventOrderReqData = (EventOrderReqData) baseReqEvent;
            int reqDetailType = eventOrderReqData.getReqDetailType();
            if (reqDetailType == 2) {
                a(eventOrderReqData);
            } else if (reqDetailType == 3) {
                b(eventOrderReqData);
            } else if (reqDetailType == 4) {
                c(eventOrderReqData);
            }
            AppMethodBeat.o(21971);
        } else {
            IllegalArgumentException illegalArgumentException2 = new IllegalArgumentException("reqEvent is" + baseReqEvent.getClass().getName() + ",but " + EventOrderReqData.class.getName() + " wanted");
            AppMethodBeat.o(21971);
            throw illegalArgumentException2;
        }
    }

    private void a(EventOrderReqData eventOrderReqData) {
        AppMethodBeat.i(21973, false);
        d.d("OrderDataDealer", "mryxPay....tmpEvent:" + eventOrderReqData);
        EventOrderResData eventOrderResData = new EventOrderResData(eventOrderReqData);
        String str = i.au;
        JSONObject b = c.b("inter_order_no", eventOrderReqData.getOrderNo(), "password_v2", aq.a(eventOrderReqData.getPayPassword()));
        Request.Builder builder = new Request.Builder();
        builder.addHeader("platform", "android");
        builder.addHeader("version", r.d());
        c.a("OrderDataDealer", str, null, b, builder, new AnonymousClass1(eventOrderResData));
        AppMethodBeat.o(21973);
    }

    /* compiled from: OrderDataDealer */
    /* access modifiers changed from: package-private */
    /* renamed from: cn.missfresh.module.base.support.modelsupport.a.a$1  reason: invalid class name */
    public class AnonymousClass1 extends m {
        final /* synthetic */ EventOrderResData a;

        AnonymousClass1(EventOrderResData eventOrderResData) {
            this.a = eventOrderResData;
        }

        @Override // cn.missfresh.module.base.network.m, cn.missfresh.module.base.network.c.b
        public void a(int i) {
            AppMethodBeat.i(21954, false);
            this.a.setErrCode(i);
            this.a.post();
            AppMethodBeat.o(21954);
        }

        @Override // cn.missfresh.module.base.network.m, cn.missfresh.module.base.network.c.b
        public void a(Request request, Exception exc) {
            AppMethodBeat.i(21956, false);
            this.a.setNetErr();
            this.a.post();
            AppMethodBeat.o(21956);
        }

        @Override // cn.missfresh.module.base.network.m, cn.missfresh.module.base.network.c.b
        public void a(String str) {
            AppMethodBeat.i(21958, false);
            if (b.a(str)) {
                d.c("OrderDataDealer", "res data empty");
                this.a.setDataErr();
                this.a.post();
                AppMethodBeat.o(21958);
                return;
            }
            try {
                EventOrderResData.MryxPayRes mryxPayRes = (EventOrderResData.MryxPayRes) JSON.parseObject(str, EventOrderResData.MryxPayRes.class);
                if (mryxPayRes.getStatus() == 0) {
                    this.a.setIfsuc(true);
                    this.a.post();
                } else {
                    this.a.setIfsuc(false);
                    this.a.setErrMsg(mryxPayRes.getError_msg());
                    this.a.post();
                    ac.a("\u4ea4\u6613\u94fe\u8def", "\u6bcf\u65e5\u4f18\u9c9c\u4f59\u989d\u652f\u4ed8\u5931\u8d25", mryxPayRes.getError_code() + Constants.ACCEPT_TIME_SEPARATOR_SP + mryxPayRes.getError_msg());
                }
            } catch (Exception e) {
                this.a.setDataErr();
                this.a.post();
                d.a("OrderDataDealer", e);
            }
            AppMethodBeat.o(21958);
        }
    }

    private void b(EventOrderReqData eventOrderReqData) {
        AppMethodBeat.i(21974, false);
        c.a("getChargeSucInfo", i.av, null, c.b("internal_id", eventOrderReqData.getInternalId()), new AnonymousClass2(new EventOrderResData(eventOrderReqData)));
        AppMethodBeat.o(21974);
    }

    /* compiled from: OrderDataDealer */
    /* access modifiers changed from: package-private */
    /* renamed from: cn.missfresh.module.base.support.modelsupport.a.a$2  reason: invalid class name */
    public class AnonymousClass2 extends m {
        final /* synthetic */ EventOrderResData a;

        AnonymousClass2(EventOrderResData eventOrderResData) {
            this.a = eventOrderResData;
        }

        @Override // cn.missfresh.module.base.network.m, cn.missfresh.module.base.network.c.b
        public void a(int i) {
            AppMethodBeat.i(21960, false);
            this.a.setErrCode(i);
            this.a.post();
            AppMethodBeat.o(21960);
        }

        @Override // cn.missfresh.module.base.network.m, cn.missfresh.module.base.network.c.b
        public void a(Request request, Exception exc) {
            AppMethodBeat.i(21962, false);
            this.a.setNetErr();
            this.a.post();
            AppMethodBeat.o(21962);
        }

        @Override // cn.missfresh.module.base.network.m, cn.missfresh.module.base.network.c.b
        public void a(String str) {
            AppMethodBeat.i(21963, false);
            if (b.a(str)) {
                d.c("OrderDataDealer", "res data empty");
                this.a.setDataErr();
                this.a.post();
                AppMethodBeat.o(21963);
                return;
            }
            try {
                cn.missfresh.module.base.network.a c = m.c(str);
                EventOrderResData.ChargeSucInfo chargeSucInfo = (EventOrderResData.ChargeSucInfo) JSON.parseObject(str, EventOrderResData.ChargeSucInfo.class);
                this.a.setErrCode(c.a);
                this.a.setErrMsg(c.b);
                if (c.a == 0) {
                    this.a.setChargeSucInfo(chargeSucInfo);
                    this.a.setIfsuc(true);
                    this.a.post();
                } else {
                    this.a.setIfsuc(false);
                    this.a.post();
                }
            } catch (Exception e) {
                this.a.setDataErr();
                this.a.post();
                d.a("OrderDataDealer", e);
            }
            AppMethodBeat.o(21963);
        }
    }

    private void c(EventOrderReqData eventOrderReqData) {
        AppMethodBeat.i(21976, false);
        c.a("getVipPopupTips", i.aw, (Map<String, String>) null, new AnonymousClass3(new EventOrderResData(eventOrderReqData)));
        AppMethodBeat.o(21976);
    }

    /* compiled from: OrderDataDealer */
    /* access modifiers changed from: package-private */
    /* renamed from: cn.missfresh.module.base.support.modelsupport.a.a$3  reason: invalid class name */
    public class AnonymousClass3 extends m {
        final /* synthetic */ EventOrderResData a;

        AnonymousClass3(EventOrderResData eventOrderResData) {
            this.a = eventOrderResData;
        }

        @Override // cn.missfresh.module.base.network.m, cn.missfresh.module.base.network.c.b
        public void a(int i) {
            AppMethodBeat.i(21965, false);
            this.a.setErrCode(i);
            this.a.post();
            AppMethodBeat.o(21965);
        }

        @Override // cn.missfresh.module.base.network.m, cn.missfresh.module.base.network.c.b
        public void a(Request request, Exception exc) {
            AppMethodBeat.i(21966, false);
            this.a.setNetErr();
            this.a.post();
            AppMethodBeat.o(21966);
        }

        @Override // cn.missfresh.module.base.network.m, cn.missfresh.module.base.network.c.b
        public void a(String str) {
            AppMethodBeat.i(21968, false);
            if (b.a(str)) {
                d.c("OrderDataDealer", "res data empty");
                this.a.setDataErr();
                this.a.post();
                AppMethodBeat.o(21968);
                return;
            }
            try {
                cn.missfresh.module.base.network.a c = m.c(str);
                EventOrderResData.VipTipsRes vipTipsRes = (EventOrderResData.VipTipsRes) JSON.parseObject(str, EventOrderResData.VipTipsRes.class);
                this.a.setErrCode(c.a);
                this.a.setErrMsg(c.b);
                if (c.a == 0) {
                    this.a.setVipTipsRes(vipTipsRes);
                    this.a.setIfsuc(true);
                    this.a.post();
                } else {
                    this.a.setIfsuc(false);
                    this.a.post();
                }
            } catch (Exception e) {
                this.a.setDataErr();
                this.a.post();
                d.a("OrderDataDealer", e);
            }
            AppMethodBeat.o(21968);
        }
    }
}
