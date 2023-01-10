package cn.missfresh.module.base.widget.recycleview.divider;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.view.View;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;

public class DividerItemDecoration extends RecyclerView.ItemDecoration {
    private Drawable a;
    private boolean b = false;
    private boolean c = false;
    private int d = 0;
    private int e = 0;

    public DividerItemDecoration(Drawable drawable) {
        this.a = drawable;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.ItemDecoration
    public void getItemOffsets(Rect rect, View view, RecyclerView recyclerView, RecyclerView.State state) {
        AppMethodBeat.i(24266, false);
        super.getItemOffsets(rect, view, recyclerView, state);
        if (this.a == null) {
            AppMethodBeat.o(24266);
        } else if (recyclerView.getChildPosition(view) < 1) {
            AppMethodBeat.o(24266);
        } else {
            if (a(recyclerView) == 1) {
                rect.top = this.a.getIntrinsicHeight();
            } else {
                rect.left = this.a.getIntrinsicWidth();
            }
            AppMethodBeat.o(24266);
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.ItemDecoration
    public void onDrawOver(Canvas canvas, RecyclerView recyclerView, RecyclerView.State state) {
        int i;
        int i2;
        int i3;
        int i4;
        int i5 = 0;
        AppMethodBeat.i(24267, false);
        if (this.a == null) {
            super.onDrawOver(canvas, recyclerView, state);
            AppMethodBeat.o(24267);
            return;
        }
        int a = a(recyclerView);
        int childCount = recyclerView.getChildCount();
        if (a == 1) {
            int intrinsicHeight = this.a.getIntrinsicHeight();
            int paddingLeft = recyclerView.getPaddingLeft() + this.d;
            i2 = (recyclerView.getWidth() - recyclerView.getPaddingRight()) - this.e;
            i = intrinsicHeight;
            i4 = 0;
            i5 = paddingLeft;
            i3 = 0;
        } else {
            int intrinsicWidth = this.a.getIntrinsicWidth();
            i3 = recyclerView.getPaddingTop();
            i = intrinsicWidth;
            i4 = recyclerView.getHeight() - recyclerView.getPaddingBottom();
            i2 = 0;
        }
        for (int i6 = !this.b ? 1 : 0; i6 < childCount; i6++) {
            View childAt = recyclerView.getChildAt(i6);
            RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) childAt.getLayoutParams();
            if (a == 1) {
                int top = childAt.getTop() - layoutParams.topMargin;
                i3 = top;
                i4 = top + i;
            } else {
                i5 = childAt.getLeft() - layoutParams.leftMargin;
                i2 = i5 + i;
            }
            this.a.setBounds(i5, i3, i2, i4);
            this.a.draw(canvas);
        }
        if (this.c && childCount > 0) {
            View childAt2 = recyclerView.getChildAt(childCount - 1);
            RecyclerView.LayoutParams layoutParams2 = (RecyclerView.LayoutParams) childAt2.getLayoutParams();
            if (a == 1) {
                i3 = childAt2.getBottom() + layoutParams2.bottomMargin;
                i4 = i3 + i;
            } else {
                i5 = childAt2.getRight() + layoutParams2.rightMargin;
                i2 = i5 + i;
            }
            this.a.setBounds(i5, i3, i2, i4);
            this.a.draw(canvas);
        }
        AppMethodBeat.o(24267);
    }

    private int a(RecyclerView recyclerView) {
        AppMethodBeat.i(24268, false);
        if (recyclerView.getLayoutManager() instanceof LinearLayoutManager) {
            int orientation = ((LinearLayoutManager) recyclerView.getLayoutManager()).getOrientation();
            AppMethodBeat.o(24268);
            return orientation;
        }
        IllegalStateException illegalStateException = new IllegalStateException("DividerItemDecoration can only be used with a LinearLayoutManager.");
        AppMethodBeat.o(24268);
        throw illegalStateException;
    }
}
