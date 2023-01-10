package cn.missfresh.module.base.utils;

import android.view.View;
import cn.missfresh.module.base.utils.ap;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import io.reactivex.r;

class RxView$ViewClickOnSubscribe$1 implements View.OnClickListener {
    final /* synthetic */ r a;
    final /* synthetic */ ap.b b;

    RxView$ViewClickOnSubscribe$1(ap.b bVar, r rVar) {
        this.b = bVar;
        this.a = rVar;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        AppMethodBeat.i(23413, false);
        if (!this.a.isDisposed()) {
            this.a.onNext(this.b.a);
        }
        AppMethodBeat.onClick(this, view);
        AppMethodBeat.o(23413);
    }
}
