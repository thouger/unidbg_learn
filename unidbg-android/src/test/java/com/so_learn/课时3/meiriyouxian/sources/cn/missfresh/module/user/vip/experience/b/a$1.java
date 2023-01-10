package cn.missfresh.module.user.vip.experience.b;

import cn.missfresh.module.base.network.m;
import cn.missfresh.module.user.vip.experience.bean.VipExperience;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import com.alibaba.fastjson.JSONObject;
import okhttp3.Request;

/* compiled from: VipExperiencePresenter */
class a$1 extends m {
    final /* synthetic */ a a;

    a$1(a aVar) {
        this.a = aVar;
    }

    @Override // cn.missfresh.module.base.network.m, cn.missfresh.module.base.network.c.b
    public void a(int i) {
        AppMethodBeat.i(9942, false);
        super.a(i);
        a.a(this.a).a("\u7f51\u7edc\u72b6\u6001\u4e0d\u597d\uff0c\u8bf7\u91cd\u8bd5");
        AppMethodBeat.o(9942);
    }

    @Override // cn.missfresh.module.base.network.m, cn.missfresh.module.base.network.c.b
    public void a(Request request, Exception exc) {
        AppMethodBeat.i(9945, false);
        super.a(request, exc);
        a.a(this.a).a("\u7f51\u7edc\u72b6\u6001\u4e0d\u597d\uff0c\u8bf7\u91cd\u8bd5");
        AppMethodBeat.o(9945);
    }

    @Override // cn.missfresh.module.base.network.m, cn.missfresh.module.base.network.c.b
    public void a(String str) {
        AppMethodBeat.i(9946, false);
        super.a(str);
        if (c(str).a == 0) {
            try {
                VipExperience vipExperience = (VipExperience) JSONObject.parseObject(str, VipExperience.class);
                if (vipExperience != null) {
                    a.b(this.a).a(vipExperience);
                    a.b(this.a).b();
                    a.a(this.a).a(vipExperience);
                } else {
                    a.a(this.a).a("\u670d\u52a1\u5668\u51fa\u9519\uff0c\u8bf7\u91cd\u8bd5");
                }
            } catch (Exception e) {
                e.printStackTrace();
                a.a(this.a).a("\u670d\u52a1\u5668\u51fa\u9519\uff0c\u8bf7\u91cd\u8bd5");
            }
        } else {
            a.a(this.a).a("\u670d\u52a1\u5668\u51fa\u9519\uff0c\u8bf7\u91cd\u8bd5");
        }
        AppMethodBeat.o(9946);
    }
}
