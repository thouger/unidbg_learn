package cn.missfresh.flutter.helper;

import androidx.lifecycle.Lifecycle;
import cn.missfresh.flutter.R;
import cn.missfresh.module.base.order.widget.b;
import cn.missfresh.module.mvp.mvp.interfaces.IModel;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import cn.missfresh.ui.a.a;
import io.flutter.plugin.common.MethodChannel;

/* compiled from: OrderListSkipHelper */
class b$1 implements IModel.a {
    final /* synthetic */ MethodChannel a;
    final /* synthetic */ Lifecycle b;

    b$1(MethodChannel methodChannel, Lifecycle lifecycle) {
        this.a = methodChannel;
        this.b = lifecycle;
    }

    public void a() {
        AppMethodBeat.i(21629, false);
        b.a();
        b.a(R.drawable.food_ic_upload_succesee, "\u5df2\u786e\u8ba4\u6536\u8d27\uff01");
        this.a.invokeMethod("refreshOrderList", null);
        AppMethodBeat.o(21629);
    }

    public void a(int i, String str) {
        AppMethodBeat.i(21631, false);
        b.a();
        a.a("\u786e\u8ba4\u6536\u8d27\u5931\u8d25\uff0c\u8bf7\u7a0d\u540e\u518d\u8bd5");
        AppMethodBeat.o(21631);
    }

    public void b() {
        AppMethodBeat.i(21633, false);
        b.a();
        AppMethodBeat.o(21633);
    }

    public Lifecycle c() {
        return this.b;
    }
}
