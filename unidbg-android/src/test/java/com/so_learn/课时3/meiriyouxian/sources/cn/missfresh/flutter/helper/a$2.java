package cn.missfresh.flutter.helper;

import android.app.Activity;
import cn.missfresh.flutter.helper.OrderDetailSkipHelper;
import cn.missfresh.module.base.network.m;
import cn.missfresh.module.base.order.widget.c;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import cn.missfresh.ui.a.a;
import cn.missfresh.utils.b;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.taobao.accs.common.Constants;
import okhttp3.Request;

/* compiled from: OrderDetailSkipHelper */
class a$2 extends m {
    final /* synthetic */ Activity a;

    a$2(Activity activity) {
        this.a = activity;
    }

    @Override // cn.missfresh.module.base.network.m, cn.missfresh.module.base.network.c.b
    public void a() {
        AppMethodBeat.i(21587, false);
        super.a();
        a.b("\u60a8\u8fd8\u6ca1\u6709\u767b\u5f55");
        AppMethodBeat.o(21587);
    }

    @Override // cn.missfresh.module.base.network.m, cn.missfresh.module.base.network.c.b
    public void a(int i) {
        AppMethodBeat.i(21588, false);
        super.a(i);
        a.b("\u7cfb\u7edf\u51fa\u73b0\u6545\u969c\uff0c\u8bf7\u7a0d\u540e\u91cd\u8bd5\u3002");
        AppMethodBeat.o(21588);
    }

    @Override // cn.missfresh.module.base.network.m, cn.missfresh.module.base.network.c.b
    public void a(Request request, Exception exc) {
        AppMethodBeat.i(21590, false);
        super.a(request, exc);
        a.b("\u60a8\u7684\u7f51\u7edc\u51fa\u73b0\u95ee\u9898\uff0c\u8bf7\u8bbe\u7f6e\u7f51\u7edc\u540e\u91cd\u8bd5\u3002");
        AppMethodBeat.o(21590);
    }

    @Override // cn.missfresh.module.base.network.m, cn.missfresh.module.base.network.c.b
    public void a(String str) {
        AppMethodBeat.i(21592, false);
        super.a(str);
        if (!b.a(str)) {
            try {
                JSONObject parseObject = JSON.parseObject(str);
                if (parseObject != null) {
                    parseObject.getIntValue(Constants.KEY_HTTP_CODE);
                    String string = parseObject.getString("msg");
                    boolean booleanValue = parseObject.getBoolean("success").booleanValue();
                    if (!b.a(string)) {
                        if (booleanValue) {
                            new c(this.a, "\u5df2\u6536\u5230\u60a8\u7684\u50ac\u5355\u6d88\u606f", string, "\u77e5\u9053\u4e86", new OrderDetailSkipHelper.3.1(this)).show();
                        } else {
                            a.b(string);
                        }
                        AppMethodBeat.o(21592);
                        return;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        a.b("\u7cfb\u7edf\u51fa\u73b0\u6545\u969c\uff0c\u8bf7\u7a0d\u540e\u91cd\u8bd5\u3002");
        AppMethodBeat.o(21592);
    }

    @Override // cn.missfresh.module.base.network.m, cn.missfresh.module.base.network.c.b
    public void b() {
        AppMethodBeat.i(21594, false);
        super.b();
        a.b("\u60a8\u8fd8\u6ca1\u6709\u7ed1\u5b9a\u624b\u673a\u53f7");
        AppMethodBeat.o(21594);
    }
}
