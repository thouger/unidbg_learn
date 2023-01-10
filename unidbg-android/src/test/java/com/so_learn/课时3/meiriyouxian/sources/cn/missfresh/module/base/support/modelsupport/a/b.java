package cn.missfresh.module.base.support.modelsupport.a;

import cn.missfresh.module.base.common.config.i;
import cn.missfresh.module.base.manager.e;
import cn.missfresh.module.base.network.a;
import cn.missfresh.module.base.network.c;
import cn.missfresh.module.base.network.m;
import cn.missfresh.module.base.support.modelsupport.event.EventProductReqData;
import cn.missfresh.module.base.support.modelsupport.event.EventProductResData;
import cn.missfresh.module.base.support.modelsupport.event.base.BaseReqEvent;
import cn.missfresh.module.base.support.modelsupport.event.bean.ProductBeans;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import cn.missfresh.utils.a.d;
import com.alibaba.fastjson.JSON;
import com.huawei.hms.support.api.push.pushselfshow.prepare.NotificationIconUtil;
import com.tencent.connect.common.Constants;
import java.util.Map;
import okhttp3.Request;

/* compiled from: ProductDataDealer */
public class b {
    public void a(BaseReqEvent baseReqEvent) {
        AppMethodBeat.i(21994, false);
        d.d("ProductDataDealer", "doAction req:" + baseReqEvent);
        if (baseReqEvent == null) {
            IllegalArgumentException illegalArgumentException = new IllegalArgumentException("reqEvent is null");
            AppMethodBeat.o(21994);
            throw illegalArgumentException;
        } else if (baseReqEvent instanceof EventProductReqData) {
            EventProductReqData eventProductReqData = (EventProductReqData) baseReqEvent;
            int reqDetailType = eventProductReqData.getReqDetailType();
            if (reqDetailType == 1) {
                b(eventProductReqData);
            } else if (reqDetailType == 2) {
                c(eventProductReqData);
            } else if (reqDetailType != 3 && reqDetailType == 4) {
                a(eventProductReqData);
            }
            AppMethodBeat.o(21994);
        } else {
            IllegalArgumentException illegalArgumentException2 = new IllegalArgumentException("reqEvent is" + baseReqEvent.getClass().getName() + ",but " + EventProductReqData.class.getName() + " wanted");
            AppMethodBeat.o(21994);
            throw illegalArgumentException2;
        }
    }

    private void a(EventProductReqData eventProductReqData) {
        AppMethodBeat.i(21997, false);
        c.a("ProductDataDealer", i.bF, c.a("sku", eventProductReqData.getProductSku(), Constants.PARAM_ACCESS_TOKEN, e.n()), new AnonymousClass1(new EventProductResData(eventProductReqData)));
        AppMethodBeat.o(21997);
    }

    /* compiled from: ProductDataDealer */
    /* access modifiers changed from: package-private */
    /* renamed from: cn.missfresh.module.base.support.modelsupport.a.b$1  reason: invalid class name */
    public class AnonymousClass1 extends m {
        final /* synthetic */ EventProductResData a;

        AnonymousClass1(EventProductResData eventProductResData) {
            this.a = eventProductResData;
        }

        @Override // cn.missfresh.module.base.network.m, cn.missfresh.module.base.network.c.b
        public void a(int i) {
            AppMethodBeat.i(21978, false);
            this.a.setErrCode(i);
            this.a.post();
            AppMethodBeat.o(21978);
        }

        @Override // cn.missfresh.module.base.network.m, cn.missfresh.module.base.network.c.b
        public void a(Request request, Exception exc) {
            AppMethodBeat.i(21979, false);
            this.a.setNetErr();
            this.a.post();
            AppMethodBeat.o(21979);
        }

        @Override // cn.missfresh.module.base.network.m, cn.missfresh.module.base.network.c.b
        public void a(String str) {
            AppMethodBeat.i(21980, false);
            if (cn.missfresh.utils.b.a(str)) {
                this.a.setDataErr();
                this.a.post();
                AppMethodBeat.o(21980);
                return;
            }
            try {
                a c = m.c(str);
                if (c.a != 0) {
                    this.a.setErrCode(c.a);
                    this.a.post();
                } else {
                    this.a.setIfsuc(true);
                    this.a.post();
                }
            } catch (Exception e) {
                this.a.setDataErr();
                this.a.post();
                d.a("ProductDataDealer", e);
            }
            AppMethodBeat.o(21980);
        }
    }

    private void b(EventProductReqData eventProductReqData) {
        AppMethodBeat.i(21998, false);
        c.a("ProductDataDealer", i.aB, c.a("page", String.valueOf(eventProductReqData.getToGetPage())), new AnonymousClass2(new EventProductResData(eventProductReqData)));
        AppMethodBeat.o(21998);
    }

    /* compiled from: ProductDataDealer */
    /* access modifiers changed from: package-private */
    /* renamed from: cn.missfresh.module.base.support.modelsupport.a.b$2  reason: invalid class name */
    public class AnonymousClass2 extends m {
        final /* synthetic */ EventProductResData a;

        AnonymousClass2(EventProductResData eventProductResData) {
            this.a = eventProductResData;
        }

        @Override // cn.missfresh.module.base.network.m, cn.missfresh.module.base.network.c.b
        public void a(int i) {
            AppMethodBeat.i(21984, false);
            this.a.setErrCode(i);
            this.a.post();
            AppMethodBeat.o(21984);
        }

        @Override // cn.missfresh.module.base.network.m, cn.missfresh.module.base.network.c.b
        public void a(Request request, Exception exc) {
            AppMethodBeat.i(21985, false);
            this.a.setNetErr();
            this.a.post();
            AppMethodBeat.o(21985);
        }

        @Override // cn.missfresh.module.base.network.m, cn.missfresh.module.base.network.c.b
        public void a(String str) {
            AppMethodBeat.i(21986, false);
            if (cn.missfresh.utils.b.a(str)) {
                d.c("ProductDataDealer", "res data empty");
                this.a.setDataErr();
                this.a.post();
                AppMethodBeat.o(21986);
                return;
            }
            try {
                a c = m.c(str);
                if (c.a != 0) {
                    this.a.setErrCode(c.a);
                    this.a.post();
                } else {
                    this.a.setIfsuc(true);
                    this.a.setPreSaleListRes((ProductBeans.GetPreSaleListRes) JSON.parseObject(str, ProductBeans.GetPreSaleListRes.class));
                    this.a.post();
                }
            } catch (Exception e) {
                this.a.setDataErr();
                this.a.post();
                d.a("ProductDataDealer", e);
            }
            AppMethodBeat.o(21986);
        }
    }

    private void c(EventProductReqData eventProductReqData) {
        AppMethodBeat.i(21999, false);
        EventProductResData eventProductResData = new EventProductResData(eventProductReqData);
        c.a("ProductDataDealer", i.aC + eventProductReqData.getProductSku() + NotificationIconUtil.SPLIT_CHAR, (Map<String, String>) null, new AnonymousClass3(eventProductResData));
        AppMethodBeat.o(21999);
    }

    /* compiled from: ProductDataDealer */
    /* access modifiers changed from: package-private */
    /* renamed from: cn.missfresh.module.base.support.modelsupport.a.b$3  reason: invalid class name */
    public class AnonymousClass3 extends m {
        final /* synthetic */ EventProductResData a;

        AnonymousClass3(EventProductResData eventProductResData) {
            this.a = eventProductResData;
        }

        @Override // cn.missfresh.module.base.network.m, cn.missfresh.module.base.network.c.b
        public void a(int i) {
            AppMethodBeat.i(21989, false);
            this.a.setErrCode(i);
            this.a.post();
            AppMethodBeat.o(21989);
        }

        @Override // cn.missfresh.module.base.network.m, cn.missfresh.module.base.network.c.b
        public void a(Request request, Exception exc) {
            AppMethodBeat.i(21990, false);
            this.a.setNetErr();
            this.a.post();
            AppMethodBeat.o(21990);
        }

        @Override // cn.missfresh.module.base.network.m, cn.missfresh.module.base.network.c.b
        public void a(String str) {
            AppMethodBeat.i(21992, false);
            if (cn.missfresh.utils.b.a(str)) {
                d.c("ProductDataDealer", "res data empty");
                this.a.setDataErr();
                this.a.post();
                AppMethodBeat.o(21992);
                return;
            }
            try {
                a c = m.c(str);
                if (c.a != 0) {
                    this.a.setErrCode(c.a);
                    this.a.post();
                } else {
                    this.a.setIfsuc(true);
                    this.a.setPreSaleProductDetail((ProductBeans.PreSaleProductDetail) JSON.parseObject(str, ProductBeans.PreSaleProductDetail.class));
                    this.a.post();
                }
            } catch (Exception e) {
                this.a.setDataErr();
                this.a.post();
                d.a("ProductDataDealer", e);
            }
            AppMethodBeat.o(21992);
        }
    }
}
