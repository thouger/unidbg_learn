package cn.missfresh.module.base.support.divider;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.View;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;

public class GridDividerItemDecoration extends RecyclerView.ItemDecoration {
    private Paint a = new Paint(1);
    private int b;

    public GridDividerItemDecoration(int i, int i2) {
        AppMethodBeat.i(21904, false);
        this.b = i;
        this.a.setColor(i2);
        this.a.setStyle(Paint.Style.FILL);
        AppMethodBeat.o(21904);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.ItemDecoration
    public void getItemOffsets(Rect rect, View view, RecyclerView recyclerView, RecyclerView.State state) {
        AppMethodBeat.i(21906, false);
        super.getItemOffsets(rect, view, recyclerView, state);
        int viewLayoutPosition = ((RecyclerView.LayoutParams) view.getLayoutParams()).getViewLayoutPosition();
        int a = a(recyclerView);
        int itemCount = recyclerView.getAdapter().getItemCount();
        boolean b = b(recyclerView, viewLayoutPosition, a, itemCount);
        a(recyclerView, viewLayoutPosition, a, itemCount);
        int i = this.b;
        int i2 = viewLayoutPosition % a;
        int i3 = (i - (((a - 1) * i) / a)) * i2;
        if (i2 == 0) {
            i3 = 0;
        }
        if (b) {
            i = 0;
        }
        rect.set(i3, 0, 0, i);
        AppMethodBeat.o(21906);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.ItemDecoration
    public void onDraw(Canvas canvas, RecyclerView recyclerView, RecyclerView.State state) {
        AppMethodBeat.i(21907, false);
        super.onDraw(canvas, recyclerView, state);
        a(canvas, recyclerView);
        AppMethodBeat.o(21907);
    }

    private void a(Canvas canvas, RecyclerView recyclerView) {
        AppMethodBeat.i(21910, false);
        int childCount = recyclerView.getChildCount();
        a(recyclerView);
        recyclerView.getAdapter().getItemCount();
        if (childCount <= 2) {
            AppMethodBeat.o(21910);
            return;
        }
        for (int i = 0; i < childCount; i++) {
            View childAt = recyclerView.getChildAt(i);
            RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) childAt.getLayoutParams();
            int left = childAt.getLeft();
            int right = childAt.getRight();
            int bottom = childAt.getBottom() + layoutParams.bottomMargin;
            int i2 = this.b + bottom;
            Paint paint = this.a;
            if (paint != null) {
                canvas.drawRect((float) left, (float) bottom, (float) right, (float) i2, paint);
            }
            int top = childAt.getTop();
            int bottom2 = childAt.getBottom() + this.b;
            int right2 = childAt.getRight() + layoutParams.rightMargin;
            int i3 = this.b + right2;
            Paint paint2 = this.a;
            if (paint2 != null) {
                canvas.drawRect((float) right2, (float) top, (float) i3, (float) bottom2, paint2);
            }
        }
        AppMethodBeat.o(21910);
    }

    private boolean a(RecyclerView recyclerView, int i, int i2, int i3) {
        AppMethodBeat.i(21911, false);
        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager) {
            if ((i + 1) % i2 == 0) {
                AppMethodBeat.o(21911);
                return true;
            }
        } else if (layoutManager instanceof StaggeredGridLayoutManager) {
            if (((StaggeredGridLayoutManager) layoutManager).getOrientation() == 1) {
                if ((i + 1) % i2 == 0) {
                    AppMethodBeat.o(21911);
                    return true;
                }
            } else if (i >= i3 - (i3 % i2)) {
                AppMethodBeat.o(21911);
                return true;
            }
        }
        AppMethodBeat.o(21911);
        return false;
    }

    private boolean b(RecyclerView recyclerView, int i, int i2, int i3) {
        boolean z = false;
        AppMethodBeat.i(21914, false);
        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager) {
            int i4 = i3 % i2;
            int i5 = i3 / i2;
            if (i4 != 0) {
                i5++;
            }
            if (i5 == (i / i2) + 1) {
                z = true;
            }
            AppMethodBeat.o(21914);
            return z;
        }
        if (layoutManager instanceof StaggeredGridLayoutManager) {
            if (((StaggeredGridLayoutManager) layoutManager).getOrientation() == 1) {
                if (i >= i3 - (i3 % i2)) {
                    AppMethodBeat.o(21914);
                    return true;
                }
            } else if ((i + 1) % i2 == 0) {
                AppMethodBeat.o(21914);
                return true;
            }
        }
        AppMethodBeat.o(21914);
        return false;
    }

    private int a(RecyclerView recyclerView) {
        int i;
        AppMethodBeat.i(21915, false);
        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager) {
            i = ((GridLayoutManager) layoutManager).getSpanCount();
        } else {
            i = layoutManager instanceof StaggeredGridLayoutManager ? ((StaggeredGridLayoutManager) layoutManager).getSpanCount() : -1;
        }
        AppMethodBeat.o(21915);
        return i;
    }
}
