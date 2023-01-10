package cn.missfresh.module.search.decoration;

import android.graphics.Rect;
import android.view.View;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import cn.missfresh.utils.f;

public class SearchResultDecoration extends RecyclerView.ItemDecoration {
    private int a;
    private int b;

    public SearchResultDecoration(int i, int i2) {
        this.a = i;
        this.b = i2;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.ItemDecoration
    public void getItemOffsets(Rect rect, View view, RecyclerView recyclerView, RecyclerView.State state) {
        GridLayoutManager gridLayoutManager;
        int itemViewType;
        AppMethodBeat.i(11692, false);
        int viewLayoutPosition = ((RecyclerView.LayoutParams) view.getLayoutParams()).getViewLayoutPosition();
        if (recyclerView.getLayoutManager() != null && ((itemViewType = (gridLayoutManager = (GridLayoutManager) recyclerView.getLayoutManager()).getItemViewType(view)) == 1 || itemViewType == 5 || itemViewType == 6)) {
            if (gridLayoutManager.getSpanSizeLookup().getSpanIndex(viewLayoutPosition, gridLayoutManager.getSpanCount()) == 0) {
                rect.left = f.c(view.getContext(), 10);
                rect.right = this.a;
            } else {
                rect.left = this.a;
                rect.right = f.c(view.getContext(), 10);
            }
            if (viewLayoutPosition == 0 || viewLayoutPosition == 1) {
                rect.top = f.c(view.getContext(), 10);
            } else {
                rect.top = this.a;
            }
            rect.bottom = this.a;
        }
        AppMethodBeat.o(11692);
    }
}
