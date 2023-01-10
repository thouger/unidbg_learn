package cn.missfresh.picture.internal.ui.widget;

import android.graphics.Rect;
import android.view.View;
import androidx.recyclerview.widget.RecyclerView;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;

public class MediaGridInset extends RecyclerView.ItemDecoration {
    private int a;
    private int b;
    private boolean c;

    public MediaGridInset(int i, int i2, boolean z) {
        this.a = i;
        this.b = i2;
        this.c = z;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.ItemDecoration
    public void getItemOffsets(Rect rect, View view, RecyclerView recyclerView, RecyclerView.State state) {
        AppMethodBeat.i(18108, false);
        int childAdapterPosition = recyclerView.getChildAdapterPosition(view);
        int i = this.a;
        int i2 = childAdapterPosition % i;
        if (this.c) {
            int i3 = this.b;
            rect.left = i3 - ((i2 * i3) / i);
            rect.right = ((i2 + 1) * i3) / i;
            if (childAdapterPosition < i) {
                rect.top = i3;
            }
            rect.bottom = this.b;
        } else {
            int i4 = this.b;
            rect.left = (i2 * i4) / i;
            rect.right = i4 - (((i2 + 1) * i4) / i);
            if (childAdapterPosition >= i) {
                rect.top = i4;
            }
        }
        AppMethodBeat.o(18108);
    }
}
