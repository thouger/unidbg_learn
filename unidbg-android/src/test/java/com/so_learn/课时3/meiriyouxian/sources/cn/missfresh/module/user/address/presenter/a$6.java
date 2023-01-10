package cn.missfresh.module.user.address.presenter;

import cn.missfresh.module.base.network.a;
import cn.missfresh.module.base.network.m;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import cn.missfresh.utils.b;
import com.alibaba.fastjson.JSONObject;
import okhttp3.Request;

/* compiled from: EditAddressPresenter */
class a$6 extends m {
    final /* synthetic */ a a;

    a$6(a aVar) {
        this.a = aVar;
    }

    @Override // cn.missfresh.module.base.network.m, cn.missfresh.module.base.network.c.b
    public void a(int i) {
        AppMethodBeat.i(4176, false);
        super.a(i);
        a.a(this.a).a("\u7f51\u7edc\u72b6\u6001\u4e0d\u597d\uff0c\u8bf7\u91cd\u8bd5", false, "", "");
        AppMethodBeat.o(4176);
    }

    @Override // cn.missfresh.module.base.network.m, cn.missfresh.module.base.network.c.b
    public void a(Request request, Exception exc) {
        AppMethodBeat.i(4178, false);
        super.a(request, exc);
        a.a(this.a).a("\u7f51\u7edc\u72b6\u6001\u4e0d\u597d\uff0c\u8bf7\u91cd\u8bd5", false, "", "");
        AppMethodBeat.o(4178);
    }

    @Override // cn.missfresh.module.base.network.m, cn.missfresh.module.base.network.c.b
    public void a(String str) {
        AppMethodBeat.i(4183, false);
        super.a(str);
        try {
            a c = c(str);
            String str2 = b.a(c.b) ? "\u4fee\u6539\u5730\u5740\u5931\u8d25" : c.b;
            JSONObject parseObject = JSONObject.parseObject(str);
            if (c.a == 0) {
                a.a(this.a).c(c.b);
            } else if (c.a == 1) {
                a.a(this.a).a(str2, true, parseObject.getString("tip_title"), parseObject.getString("tip_content"));
            } else {
                a.a(this.a).a("\u670d\u52a1\u5668\u51fa\u9519\uff0c\u8bf7\u91cd\u8bd5", false, "", "");
            }
        } catch (Exception e) {
            e.printStackTrace();
            a.a(this.a).a("\u670d\u52a1\u5668\u51fa\u9519\uff0c\u8bf7\u91cd\u8bd5", false, "", "");
        }
        AppMethodBeat.o(4183);
    }
}
