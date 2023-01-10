package cn.missfresh.module.base.support.modelsupport.a;

import cn.missfresh.module.base.common.config.i;
import cn.missfresh.module.base.network.a;
import cn.missfresh.module.base.network.c;
import cn.missfresh.module.base.network.m;
import cn.missfresh.module.base.support.modelsupport.event.EventVipReqdata;
import cn.missfresh.module.base.support.modelsupport.event.EventVipResData;
import cn.missfresh.module.base.support.modelsupport.event.base.BaseReqEvent;
import cn.missfresh.module.base.support.modelsupport.event.bean.VipInfoBeans;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import cn.missfresh.utils.b;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import java.util.Map;
import okhttp3.Request;

/* compiled from: VipDataDealer */
public class d {
    public void a(BaseReqEvent baseReqEvent) {
        AppMethodBeat.i(22035, false);
        cn.missfresh.utils.a.d.d("VipDataDealer", "doAction req:" + baseReqEvent);
        if (baseReqEvent == null) {
            IllegalArgumentException illegalArgumentException = new IllegalArgumentException("reqEvent is null");
            AppMethodBeat.o(22035);
            throw illegalArgumentException;
        } else if (baseReqEvent instanceof EventVipReqdata) {
            EventVipReqdata eventVipReqdata = (EventVipReqdata) baseReqEvent;
            int reqDetailType = eventVipReqdata.getReqDetailType();
            if (reqDetailType == 1) {
                a(eventVipReqdata);
            } else if (reqDetailType == 2) {
                b(eventVipReqdata);
            } else if (reqDetailType == 3) {
                c(eventVipReqdata);
            } else if (reqDetailType == 4) {
                d(eventVipReqdata);
            }
            AppMethodBeat.o(22035);
        } else {
            IllegalArgumentException illegalArgumentException2 = new IllegalArgumentException("reqEvent is" + baseReqEvent.getClass().getName() + ",but " + EventVipReqdata.class.getName() + " wanted");
            AppMethodBeat.o(22035);
            throw illegalArgumentException2;
        }
    }

    private void a(EventVipReqdata eventVipReqdata) {
        AppMethodBeat.i(22036, false);
        c.a("VipDataDealer", i.ag, (Map<String, String>) null, new AnonymousClass1(new EventVipResData(eventVipReqdata)));
        AppMethodBeat.o(22036);
    }

    /* compiled from: VipDataDealer */
    /* access modifiers changed from: package-private */
    /* renamed from: cn.missfresh.module.base.support.modelsupport.a.d$1  reason: invalid class name */
    public class AnonymousClass1 extends m {
        final /* synthetic */ EventVipResData a;

        AnonymousClass1(EventVipResData eventVipResData) {
            this.a = eventVipResData;
        }

        @Override // cn.missfresh.module.base.network.m, cn.missfresh.module.base.network.c.b
        public void a(int i) {
            AppMethodBeat.i(22023, false);
            this.a.setErrCode(i);
            this.a.post();
            AppMethodBeat.o(22023);
        }

        @Override // cn.missfresh.module.base.network.m, cn.missfresh.module.base.network.c.b
        public void a(Request request, Exception exc) {
            AppMethodBeat.i(22024, false);
            this.a.setNetErr();
            this.a.post();
            AppMethodBeat.o(22024);
        }

        @Override // cn.missfresh.module.base.network.m, cn.missfresh.module.base.network.c.b
        public void a(String str) {
            AppMethodBeat.i(22025, false);
            if (b.a(str)) {
                cn.missfresh.utils.a.d.c("VipDataDealer", "res data empty");
                this.a.setDataErr();
                this.a.post();
                AppMethodBeat.o(22025);
                return;
            }
            try {
                a c = m.c(str);
                if (c.a != 0) {
                    this.a.setErrCode(c.a);
                    this.a.post();
                } else {
                    this.a.setIfsuc(true);
                    this.a.setVipInfo((EventVipResData.VipInfo) JSON.parseObject(str, EventVipResData.VipInfo.class));
                    this.a.post();
                }
            } catch (Exception e) {
                this.a.setDataErr();
                this.a.post();
                cn.missfresh.utils.a.d.a("VipDataDealer", e);
            }
            AppMethodBeat.o(22025);
        }
    }

    private void b(EventVipReqdata eventVipReqdata) {
        AppMethodBeat.i(22037, false);
        c.a("VipDataDealer", i.bf, (Map<String, String>) null, new AnonymousClass2(new EventVipResData(eventVipReqdata)));
        AppMethodBeat.o(22037);
    }

    /* compiled from: VipDataDealer */
    /* access modifiers changed from: package-private */
    /* renamed from: cn.missfresh.module.base.support.modelsupport.a.d$2  reason: invalid class name */
    public class AnonymousClass2 extends m {
        final /* synthetic */ EventVipResData a;

        AnonymousClass2(EventVipResData eventVipResData) {
            this.a = eventVipResData;
        }

        @Override // cn.missfresh.module.base.network.m, cn.missfresh.module.base.network.c.b
        public void a(int i) {
            AppMethodBeat.i(22026, false);
            this.a.setErrCode(i);
            this.a.post();
            AppMethodBeat.o(22026);
        }

        @Override // cn.missfresh.module.base.network.m, cn.missfresh.module.base.network.c.b
        public void a(Request request, Exception exc) {
            AppMethodBeat.i(22027, false);
            this.a.setNetErr();
            this.a.post();
            AppMethodBeat.o(22027);
        }

        @Override // cn.missfresh.module.base.network.m, cn.missfresh.module.base.network.c.b
        public void a(String str) {
            AppMethodBeat.i(22028, false);
            if (b.a(str)) {
                cn.missfresh.utils.a.d.c("VipDataDealer", "res data empty");
                this.a.setDataErr();
                this.a.post();
                AppMethodBeat.o(22028);
                return;
            }
            try {
                a c = m.c(str);
                if (c.a != 0) {
                    this.a.setErrCode(c.a);
                    this.a.post();
                } else {
                    this.a.setIfsuc(true);
                    this.a.setIntegralPageInfo((VipInfoBeans.IntegralPageInfo) JSON.parseObject(str, VipInfoBeans.IntegralPageInfo.class));
                    this.a.post();
                }
            } catch (Exception e) {
                this.a.setDataErr();
                this.a.post();
                cn.missfresh.utils.a.d.a("VipDataDealer", e);
            }
            AppMethodBeat.o(22028);
        }
    }

    private void c(EventVipReqdata eventVipReqdata) {
        AppMethodBeat.i(22038, false);
        EventVipResData eventVipResData = new EventVipResData(eventVipReqdata);
        String str = i.bh;
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("sku", eventVipReqdata.getSku());
        c.a("VipDataDealer", str, null, jSONObject, new AnonymousClass3(eventVipResData));
        AppMethodBeat.o(22038);
    }

    /* compiled from: VipDataDealer */
    /* access modifiers changed from: package-private */
    /* renamed from: cn.missfresh.module.base.support.modelsupport.a.d$3  reason: invalid class name */
    public class AnonymousClass3 extends m {
        final /* synthetic */ EventVipResData a;

        AnonymousClass3(EventVipResData eventVipResData) {
            this.a = eventVipResData;
        }

        @Override // cn.missfresh.module.base.network.m, cn.missfresh.module.base.network.c.b
        public void a(int i) {
            AppMethodBeat.i(22029, false);
            this.a.setErrCode(i);
            this.a.post();
            AppMethodBeat.o(22029);
        }

        @Override // cn.missfresh.module.base.network.m, cn.missfresh.module.base.network.c.b
        public void a(Request request, Exception exc) {
            AppMethodBeat.i(22030, false);
            this.a.setNetErr();
            cn.missfresh.ui.a.a.a(this.a.getErrMsg());
            this.a.post();
            AppMethodBeat.o(22030);
        }

        @Override // cn.missfresh.module.base.network.m, cn.missfresh.module.base.network.c.b
        public void a(String str) {
            AppMethodBeat.i(22031, false);
            if (b.a(str)) {
                cn.missfresh.utils.a.d.c("VipDataDealer", "res data empty");
                this.a.setDataErr();
                this.a.post();
                AppMethodBeat.o(22031);
                return;
            }
            try {
                a c = m.c(str);
                if (c.a != 0) {
                    cn.missfresh.ui.a.a.a(c.b);
                    this.a.setErrCode(c.a);
                    this.a.post();
                } else {
                    this.a.setIfsuc(true);
                    this.a.setCheckSucInfo((VipInfoBeans.CheckResSuc) JSON.parseObject(JSONObject.parseObject(str).getString("data"), VipInfoBeans.CheckResSuc.class));
                    this.a.post();
                }
            } catch (Exception e) {
                this.a.setDataErr();
                this.a.post();
                cn.missfresh.utils.a.d.a("VipDataDealer", e);
            }
            AppMethodBeat.o(22031);
        }
    }

    private void d(EventVipReqdata eventVipReqdata) {
        AppMethodBeat.i(22039, false);
        EventVipResData eventVipResData = new EventVipResData(eventVipReqdata);
        String str = i.bj;
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("sku", eventVipReqdata.getSku());
        c.a("VipDataDealer", str, null, jSONObject, new AnonymousClass4(eventVipResData));
        AppMethodBeat.o(22039);
    }

    /* compiled from: VipDataDealer */
    /* access modifiers changed from: package-private */
    /* renamed from: cn.missfresh.module.base.support.modelsupport.a.d$4  reason: invalid class name */
    public class AnonymousClass4 extends m {
        final /* synthetic */ EventVipResData a;

        AnonymousClass4(EventVipResData eventVipResData) {
            this.a = eventVipResData;
        }

        @Override // cn.missfresh.module.base.network.m, cn.missfresh.module.base.network.c.b
        public void a(int i) {
            AppMethodBeat.i(22032, false);
            this.a.setErrCode(i);
            this.a.post();
            AppMethodBeat.o(22032);
        }

        @Override // cn.missfresh.module.base.network.m, cn.missfresh.module.base.network.c.b
        public void a(Request request, Exception exc) {
            AppMethodBeat.i(22033, false);
            this.a.setNetErr();
            cn.missfresh.ui.a.a.a(this.a.getErrMsg());
            this.a.post();
            AppMethodBeat.o(22033);
        }

        @Override // cn.missfresh.module.base.network.m, cn.missfresh.module.base.network.c.b
        public void a(String str) {
            AppMethodBeat.i(22034, false);
            if (b.a(str)) {
                cn.missfresh.utils.a.d.c("VipDataDealer", "res data empty");
                this.a.setDataErr();
                this.a.post();
                AppMethodBeat.o(22034);
                return;
            }
            try {
                a c = m.c(str);
                if (c.a != 0) {
                    cn.missfresh.ui.a.a.a(c.b);
                    this.a.setErrCode(c.a);
                    this.a.post();
                } else {
                    this.a.setIfsuc(true);
                    this.a.setVirtualProBuySucInfo((VipInfoBeans.VirtualProBuySuc) JSON.parseObject(JSONObject.parseObject(str).getString("data"), VipInfoBeans.VirtualProBuySuc.class));
                    this.a.post();
                }
            } catch (Exception e) {
                this.a.setDataErr();
                this.a.post();
                cn.missfresh.utils.a.d.a("VipDataDealer", e);
            }
            AppMethodBeat.o(22034);
        }
    }
}
