package cn.missfresh.flutter.helper;

import androidx.lifecycle.Lifecycle;
import cn.missfresh.flutter.R;
import cn.missfresh.module.base.bean.EventFlutterFlushOrderDetail;
import cn.missfresh.module.base.order.widget.b;
import cn.missfresh.module.mvp.mvp.interfaces.IModel;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import cn.missfresh.ui.a.a;
import com.unionpay.tsmservice.data.Constant;
import io.flutter.plugin.common.MethodChannel;

/* compiled from: OrderDetailSkipHelper */
class a$3 implements IModel.a {
    final /* synthetic */ MethodChannel.Result a;
    final /* synthetic */ Lifecycle b;

    a$3(MethodChannel.Result result, Lifecycle lifecycle) {
        this.a = result;
        this.b = lifecycle;
    }

    public void a() {
        AppMethodBeat.i(21599, false);
        a.a();
        b.a(R.drawable.food_ic_upload_succesee, "\u5df2\u786e\u8ba4\u6536\u8d27\uff01");
        this.a.success(new EventFlutterFlushOrderDetail(Constant.CASH_LOAD_CANCEL, true).toJSonString());
        AppMethodBeat.o(21599);
    }

    public void a(int i, String str) {
        AppMethodBeat.i(21602, false);
        a.a();
        a.a("\u786e\u8ba4\u6536\u8d27\u5931\u8d25\uff0c\u8bf7\u7a0d\u540e\u518d\u8bd5");
        AppMethodBeat.o(21602);
    }

    public void b() {
        AppMethodBeat.i(21604, false);
        a.a();
        AppMethodBeat.o(21604);
    }

    public Lifecycle c() {
        return this.b;
    }
}
