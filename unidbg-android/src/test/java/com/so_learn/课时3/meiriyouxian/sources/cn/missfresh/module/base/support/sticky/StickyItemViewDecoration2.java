package cn.missfresh.module.base.support.sticky;

import android.graphics.Canvas;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;

public class StickyItemViewDecoration2 extends RecyclerView.ItemDecoration {
    private b a;
    private a b;
    private LinearLayoutManager c;
    private int d = -1;
    private int e;

    @Override // androidx.recyclerview.widget.RecyclerView.ItemDecoration
    public void onDrawOver(Canvas canvas, RecyclerView recyclerView, RecyclerView.State state) {
        AppMethodBeat.i(22562, false);
        super.onDrawOver(canvas, recyclerView, state);
        if (this.a == null) {
            AppMethodBeat.o(22562);
        } else if (recyclerView.getAdapter().getItemCount() <= 0 || this.e == -1) {
            this.a.a(8);
            AppMethodBeat.o(22562);
        } else {
            if (this.c == null) {
                this.c = (LinearLayoutManager) recyclerView.getLayoutManager();
            }
            if (this.c.findFirstVisibleItemPosition() >= this.e) {
                this.a.a(0);
                a(this.e);
                AppMethodBeat.o(22562);
                return;
            }
            this.a.a(8);
            AppMethodBeat.o(22562);
        }
    }

    private void a(int i) {
        a aVar;
        AppMethodBeat.i(22563, false);
        if (this.d == i || (aVar = this.b) == null) {
            AppMethodBeat.o(22563);
            return;
        }
        this.d = i;
        this.a.a(aVar.a(i));
        AppMethodBeat.o(22563);
    }
}
