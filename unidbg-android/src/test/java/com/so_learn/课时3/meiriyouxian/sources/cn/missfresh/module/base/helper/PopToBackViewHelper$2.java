package cn.missfresh.module.base.helper;

import android.view.View;
import cn.missfresh.module.base.R;
import cn.missfresh.module.base.utils.r;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;

/* access modifiers changed from: package-private */
public class PopToBackViewHelper$2 implements View.OnClickListener {
    final /* synthetic */ h a;

    PopToBackViewHelper$2(h hVar) {
        this.a = hVar;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        AppMethodBeat.i(13082, false);
        int id = view.getId();
        if (id == R.id.v_toback_box) {
            this.a.e = System.currentTimeMillis();
            this.a.b();
            r.a(this.a.f, this.a.l);
        } else if (id == R.id.v_close) {
            this.a.e = System.currentTimeMillis();
            this.a.b();
        }
        AppMethodBeat.onClick(this, view);
        AppMethodBeat.o(13082);
    }
}
