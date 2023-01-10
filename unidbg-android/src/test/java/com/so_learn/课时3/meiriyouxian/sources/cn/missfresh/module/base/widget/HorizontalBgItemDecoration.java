package cn.missfresh.module.base.widget;

import android.graphics.Rect;
import android.view.View;
import androidx.recyclerview.widget.RecyclerView;
import cn.missfresh.module.base.utils.aw;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;

public class HorizontalBgItemDecoration extends RecyclerView.ItemDecoration {
    private int a;
    private int b;

    public HorizontalBgItemDecoration() {
        this.a = 2;
        this.b = 11;
    }

    public HorizontalBgItemDecoration(int i, int i2) {
        this.a = i;
        this.b = i2;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.ItemDecoration
    public void getItemOffsets(Rect rect, View view, RecyclerView recyclerView, RecyclerView.State state) {
        AppMethodBeat.i(23687, false);
        if (a(view, recyclerView)) {
            rect.set(aw.b(this.b), 0, aw.b(this.a), 0);
        } else if (b(view, recyclerView)) {
            rect.set(0, 0, 0, 0);
        } else {
            rect.set(aw.b(this.a), 0, aw.b(this.a), 0);
        }
        AppMethodBeat.o(23687);
    }

    private boolean a(View view, RecyclerView recyclerView) {
        boolean z = false;
        AppMethodBeat.i(23688, false);
        if (view == null || recyclerView == null) {
            AppMethodBeat.o(23688);
            return false;
        }
        if (recyclerView.getChildLayoutPosition(view) == 0) {
            z = true;
        }
        AppMethodBeat.o(23688);
        return z;
    }

    private boolean b(View view, RecyclerView recyclerView) {
        boolean z = false;
        AppMethodBeat.i(23689, false);
        if (view == null || recyclerView == null || recyclerView.getAdapter() == null) {
            AppMethodBeat.o(23689);
            return false;
        }
        if (recyclerView.getChildLayoutPosition(view) == recyclerView.getAdapter().getItemCount() - 1) {
            z = true;
        }
        AppMethodBeat.o(23689);
        return z;
    }
}
