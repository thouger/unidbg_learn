package cn.missfresh.buttomline.logtrace.c;

import cn.missfresh.buttomline.logtrace.bean.LogBean;
import cn.missfresh.buttomline.logtrace.bean.RequestBaseBean;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;

/* compiled from: Crash */
class a extends cn.missfresh.buttomline.logtrace.b.a {
    /* access modifiers changed from: protected */
    @Override // cn.missfresh.buttomline.logtrace.b.a
    public String a() {
        return RequestBaseBean.TAG_CRASH;
    }

    a() {
    }

    @Override // cn.missfresh.buttomline.logtrace.b.a
    public void a(LogBean logBean) {
        AppMethodBeat.i(16925, false);
        super.a(logBean);
        AppMethodBeat.o(16925);
    }
}
