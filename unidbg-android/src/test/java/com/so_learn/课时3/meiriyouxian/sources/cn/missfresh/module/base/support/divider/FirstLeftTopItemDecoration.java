package cn.missfresh.module.base.support.divider;

import android.graphics.Rect;
import android.view.View;
import androidx.recyclerview.widget.RecyclerView;
import cn.missfresh.module.base.utils.aw;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;

public class FirstLeftTopItemDecoration extends RecyclerView.ItemDecoration {
    private int a;
    private int b;

    public FirstLeftTopItemDecoration() {
        this(0);
    }

    public FirstLeftTopItemDecoration(int i) {
        this(i, 3.0f);
    }

    public FirstLeftTopItemDecoration(int i, float f) {
        AppMethodBeat.i(21899, false);
        this.a = 0;
        a(i);
        a(f);
        AppMethodBeat.o(21899);
    }

    private void a(int i) {
        this.a = i;
    }

    private void a(float f) {
        AppMethodBeat.i(21901, false);
        if (f < 0.0f) {
            AppMethodBeat.o(21901);
            return;
        }
        this.b = aw.a(f);
        AppMethodBeat.o(21901);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.ItemDecoration
    public void getItemOffsets(Rect rect, View view, RecyclerView recyclerView, RecyclerView.State state) {
        AppMethodBeat.i(21902, false);
        if (recyclerView.getChildAdapterPosition(view) == 0) {
            if (this.a == 0) {
                rect.set(this.b, 0, 0, 0);
            } else {
                rect.set(0, this.b, 0, 0);
            }
        }
        AppMethodBeat.o(21902);
    }
}
