package cn.missfresh.module.user.address.presenter;

import android.mtp.MtpConstants;
import cn.missfresh.module.base.bean.UserAddress;
import cn.missfresh.module.base.network.m;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import com.alibaba.fastjson.JSONObject;
import okhttp3.Request;

/* compiled from: EditAddressPresenter */
class a$1 extends m {
    final /* synthetic */ a a;

    a$1(a aVar) {
        this.a = aVar;
    }

    @Override // cn.missfresh.module.base.network.m, cn.missfresh.module.base.network.c.b
    public void a(int i) {
        AppMethodBeat.i(MtpConstants.OPERATION_SELF_TEST, false);
        super.a(i);
        a.a(this.a).w();
        AppMethodBeat.o(MtpConstants.OPERATION_SELF_TEST);
    }

    @Override // cn.missfresh.module.base.network.m, cn.missfresh.module.base.network.c.b
    public void a(Request request, Exception exc) {
        AppMethodBeat.i(MtpConstants.OPERATION_GET_DEVICE_PROP_DESC, false);
        super.a(request, exc);
        a.a(this.a).w();
        AppMethodBeat.o(MtpConstants.OPERATION_GET_DEVICE_PROP_DESC);
    }

    @Override // cn.missfresh.module.base.network.m, cn.missfresh.module.base.network.c.b
    public void a(String str) {
        AppMethodBeat.i(MtpConstants.OPERATION_TERMINATE_OPEN_CAPTURE, false);
        super.a(str);
        if (c(str).a == 0) {
            try {
                a.a(this.a).a((UserAddress) JSONObject.parseObject(JSONObject.parseObject(str).getString("address_info"), UserAddress.class));
            } catch (Exception e) {
                e.printStackTrace();
                a.a(this.a).w();
            }
        } else {
            a.a(this.a).w();
        }
        AppMethodBeat.o(MtpConstants.OPERATION_TERMINATE_OPEN_CAPTURE);
    }
}
