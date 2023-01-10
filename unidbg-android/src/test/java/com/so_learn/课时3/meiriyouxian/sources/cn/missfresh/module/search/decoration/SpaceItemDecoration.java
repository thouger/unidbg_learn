package cn.missfresh.module.search.decoration;

import android.graphics.Rect;
import android.view.View;
import androidx.recyclerview.widget.RecyclerView;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;

public class SpaceItemDecoration extends RecyclerView.ItemDecoration {
    private int a;
    private int b;

    @Override // androidx.recyclerview.widget.RecyclerView.ItemDecoration
    public void getItemOffsets(Rect rect, View view, RecyclerView recyclerView, RecyclerView.State state) {
        AppMethodBeat.i(11695, false);
        if (recyclerView.getChildAdapterPosition(view) == recyclerView.getAdapter().getItemCount() - 1) {
            AppMethodBeat.o(11695);
            return;
        }
        if (this.a == 0) {
            rect.set(0, 0, this.b, 0);
        } else {
            rect.set(0, 0, 0, this.b);
        }
        AppMethodBeat.o(11695);
    }
}
