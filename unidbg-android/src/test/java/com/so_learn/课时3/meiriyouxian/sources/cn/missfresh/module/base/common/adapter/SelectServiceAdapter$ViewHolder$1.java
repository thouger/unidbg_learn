package cn.missfresh.module.base.common.adapter;

import android.os.health.UidHealthStats;
import android.view.View;
import cn.missfresh.module.base.common.adapter.SelectServiceAdapter;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;

/* access modifiers changed from: package-private */
public class SelectServiceAdapter$ViewHolder$1 implements View.OnClickListener {
    final /* synthetic */ int a;
    final /* synthetic */ SelectServiceAdapter.b b;

    SelectServiceAdapter$ViewHolder$1(SelectServiceAdapter.b bVar, int i) {
        this.b = bVar;
        this.a = i;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        AppMethodBeat.i(UidHealthStats.MEASUREMENT_BUTTON_USER_ACTIVITY_COUNT, false);
        if (SelectServiceAdapter.this.a != null) {
            SelectServiceAdapter.this.a.a(this.a);
        }
        AppMethodBeat.onClick(this, view);
        AppMethodBeat.o(UidHealthStats.MEASUREMENT_BUTTON_USER_ACTIVITY_COUNT);
    }
}
