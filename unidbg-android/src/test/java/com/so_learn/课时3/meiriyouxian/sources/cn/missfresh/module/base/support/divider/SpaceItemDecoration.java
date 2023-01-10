package cn.missfresh.module.base.support.divider;

import android.graphics.Rect;
import android.view.View;
import androidx.recyclerview.widget.RecyclerView;
import cn.missfresh.module.base.utils.aw;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;

public class SpaceItemDecoration extends RecyclerView.ItemDecoration {
    private int a;
    private int b;

    public SpaceItemDecoration() {
        this(0);
    }

    public SpaceItemDecoration(int i) {
        this(i, 3.0f);
    }

    public SpaceItemDecoration(float f) {
        this(0, f);
    }

    public SpaceItemDecoration(int i, float f) {
        AppMethodBeat.i(21918, false);
        this.a = 0;
        a(i);
        a(f);
        AppMethodBeat.o(21918);
    }

    private void a(int i) {
        this.a = i;
    }

    private void a(float f) {
        AppMethodBeat.i(21920, false);
        if (f < 0.0f) {
            AppMethodBeat.o(21920);
            return;
        }
        this.b = aw.a(f);
        AppMethodBeat.o(21920);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.ItemDecoration
    public void getItemOffsets(Rect rect, View view, RecyclerView recyclerView, RecyclerView.State state) {
        AppMethodBeat.i(21922, false);
        if (recyclerView.getChildAdapterPosition(view) == recyclerView.getAdapter().getItemCount() - 1) {
            AppMethodBeat.o(21922);
            return;
        }
        if (this.a == 0) {
            rect.set(0, 0, this.b, 0);
        } else {
            rect.set(0, 0, 0, this.b);
        }
        AppMethodBeat.o(21922);
    }
}
