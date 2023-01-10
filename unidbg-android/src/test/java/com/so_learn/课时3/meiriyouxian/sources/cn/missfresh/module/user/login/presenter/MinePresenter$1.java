package cn.missfresh.module.user.login.presenter;

import cn.missfresh.module.base.manager.e;
import cn.missfresh.module.base.network.m;
import cn.missfresh.module.base.utils.ac;
import cn.missfresh.module.user.login.bean.WxLoginRes;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import cn.missfresh.ui.a.a;
import cn.missfresh.utils.a.d;
import cn.missfresh.utils.b;
import com.alibaba.fastjson.JSONObject;
import okhttp3.Request;

class MinePresenter$1 extends m {
    final /* synthetic */ boolean a;
    final /* synthetic */ MinePresenter b;

    MinePresenter$1(MinePresenter minePresenter, boolean z) {
        this.b = minePresenter;
        this.a = z;
    }

    @Override // cn.missfresh.module.base.network.m, cn.missfresh.module.base.network.c.b
    public void a(int i) {
        AppMethodBeat.i(8159, false);
        super.a(i);
        a.a("\u7f51\u7edc\u8fde\u63a5\u5931\u8d25");
        ac.a("\u7528\u6237\u6a21\u5757", "\u5fae\u4fe1\u767b\u5f55\u5931\u8d25", "\u9519\u8bef\u7801\uff1a" + i);
        AppMethodBeat.o(8159);
    }

    @Override // cn.missfresh.module.base.network.m, cn.missfresh.module.base.network.c.b
    public void a(Request request, Exception exc) {
        AppMethodBeat.i(8162, false);
        super.a(request, exc);
        a.a("\u7f51\u7edc\u8fde\u63a5\u5931\u8d25");
        ac.a("\u7528\u6237\u6a21\u5757", "\u5fae\u4fe1\u767b\u5f55\u5931\u8d25", "\u7f51\u7edc\u8fde\u63a5\u5931\u8d25");
        AppMethodBeat.o(8162);
    }

    @Override // cn.missfresh.module.base.network.m, cn.missfresh.module.base.network.c.b
    public void a(String str) {
        AppMethodBeat.i(8166, false);
        super.a(str);
        d.d("MinePresenter", "loginByWeiChat onRequestSuccess...response:" + str);
        try {
            WxLoginRes wxLoginRes = (WxLoginRes) JSONObject.parseObject(str, WxLoginRes.class);
            d.d("MinePresenter", "loginByWeiChat 2 res:" + wxLoginRes.getAccess_token());
            if (wxLoginRes == null || b.a(wxLoginRes.getAccess_token())) {
                MinePresenter.b(this.b).Z_();
            } else {
                e.g(wxLoginRes.getAccess_token());
                MinePresenter.a(this.b).a(1, this.a, true);
            }
        } catch (Exception e) {
            d.d("MinePresenter", e.getMessage());
        }
        AppMethodBeat.o(8166);
    }
}
