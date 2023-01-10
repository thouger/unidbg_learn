package cn.missfresh.module.base.b;

import cn.missfresh.module.base.bean.PayInfo;
import cn.missfresh.module.base.common.event.e;
import cn.missfresh.module.base.network.a;
import cn.missfresh.module.base.network.m;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import cn.missfresh.utils.a.d;
import com.alibaba.fastjson.JSONObject;
import de.greenrobot.event.EventBus;
import okhttp3.Request;

/* compiled from: OrderPayInteractor */
class b$1 extends m {
    final /* synthetic */ String a;

    b$1(String str) {
        this.a = str;
    }

    @Override // cn.missfresh.module.base.network.m, cn.missfresh.module.base.network.c.b
    public void b() {
        AppMethodBeat.i(13606, false);
        super.b();
        EventBus.getDefault().post(new e(false));
        AppMethodBeat.o(13606);
    }

    @Override // cn.missfresh.module.base.network.m, cn.missfresh.module.base.network.c.b
    public void a() {
        AppMethodBeat.i(13610, false);
        super.a();
        EventBus.getDefault().post(new e(false));
        AppMethodBeat.o(13610);
    }

    @Override // cn.missfresh.module.base.network.m, cn.missfresh.module.base.network.c.b
    public void a(int i) {
        AppMethodBeat.i(13612, false);
        EventBus.getDefault().post(new e(false));
        AppMethodBeat.o(13612);
    }

    @Override // cn.missfresh.module.base.network.m, cn.missfresh.module.base.network.c.b
    public void a(Request request, Exception exc) {
        AppMethodBeat.i(13613, false);
        EventBus.getDefault().post(new e(false));
        AppMethodBeat.o(13613);
    }

    @Override // cn.missfresh.module.base.network.m, cn.missfresh.module.base.network.c.b
    public void a(String str) {
        AppMethodBeat.i(13616, false);
        super.a(str);
        try {
            a c = c(str);
            if (c.a == 0) {
                EventBus.getDefault().post(new e((PayInfo) JSONObject.parseObject(str, PayInfo.class)));
            } else {
                EventBus.getDefault().post(new e(false));
                cn.missfresh.ui.a.a.a(c.b);
            }
        } catch (Exception e) {
            d.a(this.a, e);
            EventBus.getDefault().post(new e(false));
        }
        AppMethodBeat.o(13616);
    }
}
