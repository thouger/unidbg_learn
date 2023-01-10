package cn.missfresh.module.base.support.divider;

import android.graphics.Rect;
import android.view.View;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import cn.missfresh.module.base.utils.aw;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;

public class TopAndBottomItemDecoration extends RecyclerView.ItemDecoration {
    private int a;
    private int b;
    private int c = 1;
    private int d = 0;
    private int e = 0;

    private boolean a(int i, int i2) {
        return i2 > 0 && i == i2 - 1;
    }

    public TopAndBottomItemDecoration(int i, int i2, int i3) {
        AppMethodBeat.i(21928, false);
        this.c = i;
        this.a = aw.b(i2);
        this.b = aw.b(i3);
        AppMethodBeat.o(21928);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.ItemDecoration
    public void getItemOffsets(Rect rect, View view, RecyclerView recyclerView, RecyclerView.State state) {
        int i;
        int i2;
        AppMethodBeat.i(21930, false);
        if (recyclerView.getLayoutManager() instanceof LinearLayoutManager) {
            int childAdapterPosition = recyclerView.getChildAdapterPosition(view);
            int itemCount = recyclerView.getAdapter().getItemCount();
            if (childAdapterPosition == 0 && (i2 = this.a) > 0) {
                this.d = i2;
                this.e = 0;
            } else if (!a(childAdapterPosition, itemCount) || (i = this.b) <= 0) {
                this.d = 0;
                this.e = 0;
            } else {
                this.d = 0;
                this.e = i;
            }
            int i3 = this.c;
            if (i3 == 0) {
                rect.set(this.d, 0, this.e, 0);
            } else if (i3 == 1) {
                rect.set(0, this.d, 0, this.e);
            }
        }
        AppMethodBeat.o(21930);
    }
}
