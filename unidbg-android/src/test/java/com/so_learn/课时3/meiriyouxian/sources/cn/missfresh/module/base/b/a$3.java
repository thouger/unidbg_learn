package cn.missfresh.module.base.b;

import cn.missfresh.buttomline.logtrace.bean.LogBean;
import cn.missfresh.module.base.manager.e;
import cn.missfresh.module.base.network.i;
import cn.missfresh.module.base.utils.ac;
import cn.missfresh.module.base.utils.r;
import cn.missfresh.module.base.utils.v;
import cn.missfresh.module.mvp.a.a;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import cn.missfresh.utils.a.d;
import cn.missfresh.utils.b;
import com.unionpay.tsmservice.data.Constant;

/* compiled from: DataInteractor */
class a$3 extends i<Long> {
    a$3(a aVar) {
        super(aVar);
    }

    @Override // io.reactivex.v
    public /* synthetic */ void onNext(Object obj) {
        AppMethodBeat.i(13543, false);
        a((Long) obj);
        AppMethodBeat.o(13543);
    }

    public void a(Long l) {
        AppMethodBeat.i(13538, false);
        v.a(l.longValue());
        AppMethodBeat.o(13538);
    }

    @Override // io.reactivex.v
    public void onComplete() {
        AppMethodBeat.i(13540, false);
        if (!b.a(r.c) && r.c.startsWith(Constant.DEFAULT_CVN2) && !e.aE()) {
            try {
                e.D(true);
                LogBean logBean = new LogBean();
                logBean.setType("oaIdException");
                logBean.setStr_value_0("code:" + r.c);
                d.b(logBean);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        AppMethodBeat.o(13540);
    }

    /* access modifiers changed from: protected */
    @Override // cn.missfresh.module.base.network.i
    public void a(int i, String str) {
        AppMethodBeat.i(13542, false);
        d.b("DataInteractor", "code=" + i + ", " + str);
        ac.a("\u8bbe\u5907\u4e2d\u5fc3", "\u4e0a\u62a5\u8bbe\u5907\u4fe1\u606f", "\u4e0a\u62a5\u5931\u8d25");
        AppMethodBeat.o(13542);
    }
}
