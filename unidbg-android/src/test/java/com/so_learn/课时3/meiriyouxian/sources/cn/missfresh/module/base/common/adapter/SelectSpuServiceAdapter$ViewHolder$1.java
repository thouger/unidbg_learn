package cn.missfresh.module.base.common.adapter;

import android.view.View;
import cn.missfresh.module.base.common.adapter.SelectSpuServiceAdapter;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;

/* access modifiers changed from: package-private */
public class SelectSpuServiceAdapter$ViewHolder$1 implements View.OnClickListener {
    final /* synthetic */ int a;
    final /* synthetic */ SelectSpuServiceAdapter.b b;

    SelectSpuServiceAdapter$ViewHolder$1(SelectSpuServiceAdapter.b bVar, int i) {
        this.b = bVar;
        this.a = i;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        AppMethodBeat.i(10157, false);
        if (SelectSpuServiceAdapter.this.a != null) {
            SelectSpuServiceAdapter.this.a.a(this.a);
        }
        AppMethodBeat.onClick(this, view);
        AppMethodBeat.o(10157);
    }
}
