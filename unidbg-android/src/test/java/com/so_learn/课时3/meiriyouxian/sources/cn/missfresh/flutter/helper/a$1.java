package cn.missfresh.flutter.helper;

import androidx.fragment.app.FragmentActivity;
import cn.missfresh.flutter.FlutterPageActivity;
import cn.missfresh.module.base.bean.AddResult;
import cn.missfresh.module.base.network.i;
import cn.missfresh.module.base.utils.j;
import cn.missfresh.module.mvp.a.a;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;

/* compiled from: OrderDetailSkipHelper */
class a$1 extends i<AddResult> {
    final /* synthetic */ FragmentActivity a;
    final /* synthetic */ boolean b;

    @Override // io.reactivex.v
    public void onComplete() {
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    a$1(a aVar, FragmentActivity fragmentActivity, boolean z) {
        super(aVar);
        this.a = fragmentActivity;
        this.b = z;
    }

    @Override // io.reactivex.v
    public /* synthetic */ void onNext(Object obj) {
        AppMethodBeat.i(21580, false);
        a((AddResult) obj);
        AppMethodBeat.o(21580);
    }

    public void a(AddResult addResult) {
        AppMethodBeat.i(21576, false);
        FragmentActivity fragmentActivity = this.a;
        if (fragmentActivity != null && !fragmentActivity.isFinishing()) {
            FragmentActivity fragmentActivity2 = this.a;
            if (fragmentActivity2 instanceof FlutterPageActivity) {
                ((FlutterPageActivity) fragmentActivity2).e();
            }
        }
        if (this.b) {
            j.c("/order/shoppingcart_activity").withInt("fromType", 1).navigation();
        }
        AppMethodBeat.o(21576);
    }

    /* access modifiers changed from: protected */
    @Override // cn.missfresh.module.base.network.i
    public void a(int i, String str) {
        AppMethodBeat.i(21577, false);
        FragmentActivity fragmentActivity = this.a;
        if (fragmentActivity != null && (fragmentActivity instanceof FlutterPageActivity)) {
            ((FlutterPageActivity) fragmentActivity).e();
        }
        cn.missfresh.ui.a.a.a(str);
        AppMethodBeat.o(21577);
    }
}
