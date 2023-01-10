package cn.missfresh.flutter.helper;

import android.app.Activity;
import cn.missfresh.flutter.FlutterPageActivity;
import cn.missfresh.flutter.R;
import cn.missfresh.module.base.bean.AddResult;
import cn.missfresh.module.base.network.i;
import cn.missfresh.module.base.utils.j;
import cn.missfresh.module.mvp.a.a;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;

/* compiled from: OrderListSkipHelper */
class b$4 extends i<AddResult> {
    final /* synthetic */ Activity a;

    @Override // io.reactivex.v
    public void onComplete() {
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    b$4(a aVar, Activity activity) {
        super(aVar);
        this.a = activity;
    }

    @Override // io.reactivex.v
    public /* synthetic */ void onNext(Object obj) {
        AppMethodBeat.i(21666, false);
        a((AddResult) obj);
        AppMethodBeat.o(21666);
    }

    public void a(AddResult addResult) {
        AppMethodBeat.i(21661, false);
        Activity activity = this.a;
        if (activity != null && ((FlutterPageActivity) activity).g()) {
            ((FlutterPageActivity) this.a).e();
            j.c("/order/shoppingcart_activity").withInt("fromType", 1).navigation();
            cn.missfresh.ui.a.a.b(this.a.getResources().getString(R.string.buy_again_succeed));
        }
        AppMethodBeat.o(21661);
    }

    /* access modifiers changed from: protected */
    @Override // cn.missfresh.module.base.network.i
    public void a(int i, String str) {
        AppMethodBeat.i(21664, false);
        cn.missfresh.ui.a.a.b(str);
        Activity activity = this.a;
        if (activity != null && ((FlutterPageActivity) activity).g()) {
            ((FlutterPageActivity) this.a).e();
        }
        AppMethodBeat.o(21664);
    }
}
