package cn.missfresh.module.base.support.divider;

import android.graphics.Rect;
import android.view.View;
import androidx.recyclerview.widget.RecyclerView;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;

public class SpacesItemDecoration extends RecyclerView.ItemDecoration {
    private int a;
    private boolean b;
    private boolean c = true;

    public SpacesItemDecoration(int i) {
        this.a = i;
    }

    public void a(boolean z) {
        this.b = z;
    }

    public void b(boolean z) {
        this.c = z;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.ItemDecoration
    public void getItemOffsets(Rect rect, View view, RecyclerView recyclerView, RecyclerView.State state) {
        AppMethodBeat.i(21926, false);
        int childAdapterPosition = recyclerView.getChildAdapterPosition(view);
        int itemCount = recyclerView.getAdapter().getItemCount();
        if (childAdapterPosition != 0 || this.c) {
            rect.left = this.a;
        } else {
            rect.left = 0;
        }
        if (childAdapterPosition != itemCount - 1 || !this.b) {
            rect.right = 0;
        } else {
            rect.right = this.a;
        }
        AppMethodBeat.o(21926);
    }
}
