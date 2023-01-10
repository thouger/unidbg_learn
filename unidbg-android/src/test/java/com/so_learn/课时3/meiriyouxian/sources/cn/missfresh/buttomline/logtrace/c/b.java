package cn.missfresh.buttomline.logtrace.c;

import cn.missfresh.buttomline.logtrace.b.a;
import cn.missfresh.buttomline.logtrace.bean.LogBean;
import cn.missfresh.buttomline.logtrace.bean.RequestBaseBean;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;

/* compiled from: Log */
class b extends a {
    /* access modifiers changed from: protected */
    @Override // cn.missfresh.buttomline.logtrace.b.a
    public String a() {
        return RequestBaseBean.TAG_LOG;
    }

    b() {
    }

    @Override // cn.missfresh.buttomline.logtrace.b.a
    public void a(LogBean logBean) {
        AppMethodBeat.i(16932, false);
        super.a(logBean);
        AppMethodBeat.o(16932);
    }
}
