package cn.missfresh.module.base.common.dialog;

import android.content.Context;
import android.graphics.Rect;
import android.util.SparseArray;
import android.view.View;
import androidx.recyclerview.widget.RecyclerView;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;

public class FlowLayoutManager extends RecyclerView.LayoutManager {
    private final String a = getClass().getName();
    private SparseArray<View> b = new SparseArray<>();
    private SparseArray<Rect> c = new SparseArray<>();
    private int d;
    private int e;
    private int f;
    private int g;
    private boolean h;

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public boolean canScrollHorizontally() {
        return true;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public boolean canScrollVertically() {
        return true;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public int scrollHorizontallyBy(int i, RecyclerView.Recycler recycler, RecyclerView.State state) {
        return i;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public boolean supportsPredictiveItemAnimations() {
        return true;
    }

    public FlowLayoutManager(Context context, boolean z) {
        AppMethodBeat.i(11293, false);
        this.h = z;
        AppMethodBeat.o(11293);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
        AppMethodBeat.i(11295, false);
        for (int i = 0; i < getItemCount(); i++) {
            View view = (View) this.b.get(i);
            Rect rect = (Rect) this.c.get(i);
            layoutDecorated(view, rect.left, rect.top, rect.right, rect.bottom);
        }
        AppMethodBeat.o(11295);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public RecyclerView.LayoutParams generateDefaultLayoutParams() {
        AppMethodBeat.i(11297, false);
        RecyclerView.LayoutParams layoutParams = new RecyclerView.LayoutParams(-2, -2);
        AppMethodBeat.o(11297);
        return layoutParams;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public int scrollVerticallyBy(int i, RecyclerView.Recycler recycler, RecyclerView.State state) {
        int i2 = 0;
        AppMethodBeat.i(11299, false);
        int i3 = this.f;
        int i4 = this.e;
        if (i3 - i4 > 0) {
            int i5 = this.g + i;
            if (i5 >= 0) {
                i2 = i5 > i3 - i4 ? i3 - i4 : i5;
            }
            int i6 = i2 - this.g;
            offsetChildrenVertical(-i6);
            this.g = i2;
            i2 = i6;
        }
        if (!this.h) {
            i = i2;
        }
        AppMethodBeat.o(11299);
        return i;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void onAdapterChanged(RecyclerView.Adapter adapter, RecyclerView.Adapter adapter2) {
        AppMethodBeat.i(11300, false);
        removeAllViews();
        AppMethodBeat.o(11300);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void onMeasure(RecyclerView.Recycler recycler, RecyclerView.State state, int i, int i2) {
        int i3 = 0;
        AppMethodBeat.i(11303, false);
        super.onMeasure(recycler, state, i, i2);
        View.MeasureSpec.getMode(i);
        int mode = View.MeasureSpec.getMode(i2);
        int size = View.MeasureSpec.getSize(i);
        int size2 = View.MeasureSpec.getSize(i2);
        removeAndRecycleAllViews(recycler);
        recycler.clear();
        this.b.clear();
        this.f = 0;
        this.d = (size - getPaddingRight()) - getPaddingLeft();
        int paddingLeft = getPaddingLeft();
        int paddingTop = getPaddingTop();
        for (int i4 = 0; i4 < getItemCount(); i4++) {
            View viewForPosition = recycler.getViewForPosition(i4);
            addView(viewForPosition);
            measureChildWithMargins(viewForPosition, 0, 0);
            this.b.put(i4, viewForPosition);
        }
        int i5 = paddingTop;
        while (i3 < getItemCount()) {
            View view = (View) this.b.get(i3);
            int decoratedMeasuredWidth = getDecoratedMeasuredWidth(view);
            int decoratedMeasuredHeight = getDecoratedMeasuredHeight(view);
            if (decoratedMeasuredWidth > this.d - paddingLeft) {
                paddingLeft = getPaddingLeft();
                i5 = paddingTop;
            }
            int i6 = decoratedMeasuredWidth + paddingLeft;
            int i7 = decoratedMeasuredHeight + i5;
            this.c.put(i3, new Rect(paddingLeft, i5, i6, i7));
            if (i7 >= paddingTop) {
                paddingTop = i7;
            }
            i3++;
            paddingLeft = i6;
        }
        this.f = paddingTop - getPaddingTop();
        int paddingTop2 = this.f + getPaddingTop() + getPaddingBottom();
        if (mode == Integer.MIN_VALUE ? paddingTop2 > size2 : mode == 1073741824) {
            paddingTop2 = size2;
        }
        this.e = (paddingTop2 - getPaddingTop()) - getPaddingBottom();
        setMeasuredDimension(size, paddingTop2);
        AppMethodBeat.o(11303);
    }
}
