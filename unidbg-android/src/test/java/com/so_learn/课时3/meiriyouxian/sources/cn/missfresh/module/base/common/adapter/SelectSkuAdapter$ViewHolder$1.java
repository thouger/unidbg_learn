package cn.missfresh.module.base.common.adapter;

import android.view.View;
import cn.missfresh.module.base.common.adapter.SelectSkuAdapter;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;

/* access modifiers changed from: package-private */
public class SelectSkuAdapter$ViewHolder$1 implements View.OnClickListener {
    final /* synthetic */ int a;
    final /* synthetic */ SelectSkuAdapter.b b;

    SelectSkuAdapter$ViewHolder$1(SelectSkuAdapter.b bVar, int i) {
        this.b = bVar;
        this.a = i;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        AppMethodBeat.i(10092, false);
        if (SelectSkuAdapter.this.a != null) {
            SelectSkuAdapter.this.a.a(this.a);
        }
        AppMethodBeat.onClick(this, view);
        AppMethodBeat.o(10092);
    }
}
