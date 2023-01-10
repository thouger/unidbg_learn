package cn.missfresh.module.base.widget.recycleview;

import android.view.View;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;

public class ExStaggeredGridLayoutManager extends StaggeredGridLayoutManager {
    GridLayoutManager.SpanSizeLookup a;

    public void a(GridLayoutManager.SpanSizeLookup spanSizeLookup) {
        this.a = spanSizeLookup;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void onMeasure(RecyclerView.Recycler recycler, RecyclerView.State state, int i, int i2) {
        AppMethodBeat.i(24259, false);
        for (int i3 = 0; i3 < getItemCount(); i3++) {
            if (this.a.getSpanSize(i3) > 1) {
                try {
                    View viewForPosition = recycler.getViewForPosition(i3);
                    if (viewForPosition != null) {
                        ((StaggeredGridLayoutManager.LayoutParams) viewForPosition.getLayoutParams()).setFullSpan(true);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        super.onMeasure(recycler, state, i, i2);
        AppMethodBeat.o(24259);
    }
}
