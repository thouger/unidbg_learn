package cn.missfresh.module.user.address.presenter;

import cn.missfresh.module.base.bean.UserAddress;
import cn.missfresh.module.base.network.a;
import cn.missfresh.module.base.network.m;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import cn.missfresh.utils.a.d;
import cn.missfresh.utils.b;
import com.alibaba.fastjson.JSON;
import com.tencent.smtt.sdk.TbsListener;
import okhttp3.Request;

/* compiled from: EditAddressPresenter */
class a$2 extends m {
    final /* synthetic */ a a;

    a$2(a aVar) {
        this.a = aVar;
    }

    @Override // cn.missfresh.module.base.network.m, cn.missfresh.module.base.network.c.b
    public void a() {
        AppMethodBeat.i(4129, false);
        super.a();
        a.a(this.a).a("\u8bf7\u767b\u5f55");
        AppMethodBeat.o(4129);
    }

    @Override // cn.missfresh.module.base.network.m, cn.missfresh.module.base.network.c.b
    public void a(int i) {
        AppMethodBeat.i(TbsListener.ErrorCode.INFO_MISS_SDKEXTENSION_JAR_WITH_FUSION_DEX_WITHOUT_CORE, false);
        super.a(i);
        a.a(this.a).a("\u7f51\u7edc\u72b6\u6001\u4e0d\u597d\uff0c\u8bf7\u91cd\u8bd5");
        AppMethodBeat.o(TbsListener.ErrorCode.INFO_MISS_SDKEXTENSION_JAR_WITH_FUSION_DEX_WITHOUT_CORE);
    }

    @Override // cn.missfresh.module.base.network.m, cn.missfresh.module.base.network.c.b
    public void a(Request request, Exception exc) {
        AppMethodBeat.i(4134, false);
        super.a(request, exc);
        a.a(this.a).a("\u7f51\u7edc\u72b6\u6001\u4e0d\u597d\uff0c\u8bf7\u91cd\u8bd5");
        AppMethodBeat.o(4134);
    }

    @Override // cn.missfresh.module.base.network.m, cn.missfresh.module.base.network.c.b
    public void a(String str) {
        UserAddress userAddress;
        AppMethodBeat.i(4137, false);
        super.a(str);
        a c = c(str);
        String str2 = b.a(c.b) ? "\u6dfb\u52a0\u5730\u5740\u5931\u8d25" : c.b;
        if (c.a == 0) {
            try {
                userAddress = (UserAddress) JSON.parseObject(str, UserAddress.class);
            } catch (Exception e) {
                e.printStackTrace();
                d.c(a.b(this.a), "add user address parse failed");
                d.a(a.b(this.a), e);
                a.a(this.a).a("\u89e3\u6790\u5730\u5740\u5931\u8d25");
                userAddress = null;
            }
            if (userAddress != null) {
                d.c(a.b(this.a), "add user address succeed");
                a.a(this.a).a(userAddress, c.b);
            } else {
                d.c(a.b(this.a), "add user address failed address is null");
                a.a(this.a).a(str2);
            }
        } else {
            a.a(this.a).a(str2);
        }
        AppMethodBeat.o(4137);
    }
}
