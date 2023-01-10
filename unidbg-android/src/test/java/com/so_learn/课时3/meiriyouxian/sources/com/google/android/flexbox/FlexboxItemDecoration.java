package com.google.android.flexbox;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.view.View;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class FlexboxItemDecoration extends RecyclerView.ItemDecoration {
    private static final int[] a = {16843284};
    private Drawable b;
    private int c;

    @Override // androidx.recyclerview.widget.RecyclerView.ItemDecoration
    public void onDraw(Canvas canvas, RecyclerView recyclerView, RecyclerView.State state) {
        b(canvas, recyclerView);
        a(canvas, recyclerView);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.ItemDecoration
    public void getItemOffsets(Rect rect, View view, RecyclerView recyclerView, RecyclerView.State state) {
        int childAdapterPosition = recyclerView.getChildAdapterPosition(view);
        if (childAdapterPosition != 0) {
            if (a() || b()) {
                FlexboxLayoutManager flexboxLayoutManager = (FlexboxLayoutManager) recyclerView.getLayoutManager();
                List<b> b = flexboxLayoutManager.b();
                a(rect, childAdapterPosition, flexboxLayoutManager, b, flexboxLayoutManager.getFlexDirection());
                a(rect, childAdapterPosition, flexboxLayoutManager, b);
                return;
            }
            rect.set(0, 0, 0, 0);
        }
    }

    private void a(Rect rect, int i, FlexboxLayoutManager flexboxLayoutManager, List<b> list) {
        if (list.size() != 0 && flexboxLayoutManager.g(i) != 0) {
            if (flexboxLayoutManager.a()) {
                if (!a()) {
                    rect.top = 0;
                    rect.bottom = 0;
                    return;
                }
                rect.top = this.b.getIntrinsicHeight();
                rect.bottom = 0;
            } else if (b()) {
                if (flexboxLayoutManager.c()) {
                    rect.right = this.b.getIntrinsicWidth();
                    rect.left = 0;
                    return;
                }
                rect.left = this.b.getIntrinsicWidth();
                rect.right = 0;
            }
        }
    }

    private void a(Rect rect, int i, FlexboxLayoutManager flexboxLayoutManager, List<b> list, int i2) {
        if (!a(i, list, flexboxLayoutManager)) {
            if (flexboxLayoutManager.a()) {
                if (!b()) {
                    rect.left = 0;
                    rect.right = 0;
                } else if (flexboxLayoutManager.c()) {
                    rect.right = this.b.getIntrinsicWidth();
                    rect.left = 0;
                } else {
                    rect.left = this.b.getIntrinsicWidth();
                    rect.right = 0;
                }
            } else if (!a()) {
                rect.top = 0;
                rect.bottom = 0;
            } else if (i2 == 3) {
                rect.bottom = this.b.getIntrinsicHeight();
                rect.top = 0;
            } else {
                rect.top = this.b.getIntrinsicHeight();
                rect.bottom = 0;
            }
        }
    }

    private void a(Canvas canvas, RecyclerView recyclerView) {
        int i;
        int i2;
        int i3;
        int i4;
        int bottom;
        int i5;
        if (b()) {
            FlexboxLayoutManager flexboxLayoutManager = (FlexboxLayoutManager) recyclerView.getLayoutManager();
            int top = recyclerView.getTop() - recyclerView.getPaddingTop();
            int bottom2 = recyclerView.getBottom() + recyclerView.getPaddingBottom();
            int childCount = recyclerView.getChildCount();
            int flexDirection = flexboxLayoutManager.getFlexDirection();
            for (int i6 = 0; i6 < childCount; i6++) {
                View childAt = recyclerView.getChildAt(i6);
                RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) childAt.getLayoutParams();
                if (flexboxLayoutManager.c()) {
                    i2 = childAt.getRight() + layoutParams.rightMargin;
                    i = this.b.getIntrinsicWidth() + i2;
                } else {
                    i = childAt.getLeft() - layoutParams.leftMargin;
                    i2 = i - this.b.getIntrinsicWidth();
                }
                if (flexboxLayoutManager.a()) {
                    i3 = childAt.getTop() - layoutParams.topMargin;
                    bottom = childAt.getBottom();
                    i5 = layoutParams.bottomMargin;
                } else if (flexDirection == 3) {
                    int min = Math.min(childAt.getBottom() + layoutParams.bottomMargin + this.b.getIntrinsicHeight(), bottom2);
                    i3 = childAt.getTop() - layoutParams.topMargin;
                    i4 = min;
                    this.b.setBounds(i2, i3, i, i4);
                    this.b.draw(canvas);
                } else {
                    i3 = Math.max((childAt.getTop() - layoutParams.topMargin) - this.b.getIntrinsicHeight(), top);
                    bottom = childAt.getBottom();
                    i5 = layoutParams.bottomMargin;
                }
                i4 = bottom + i5;
                this.b.setBounds(i2, i3, i, i4);
                this.b.draw(canvas);
            }
        }
    }

    private void b(Canvas canvas, RecyclerView recyclerView) {
        int i;
        int i2;
        int i3;
        int i4;
        int i5;
        int i6;
        if (a()) {
            FlexboxLayoutManager flexboxLayoutManager = (FlexboxLayoutManager) recyclerView.getLayoutManager();
            int flexDirection = flexboxLayoutManager.getFlexDirection();
            int left = recyclerView.getLeft() - recyclerView.getPaddingLeft();
            int right = recyclerView.getRight() + recyclerView.getPaddingRight();
            int childCount = recyclerView.getChildCount();
            for (int i7 = 0; i7 < childCount; i7++) {
                View childAt = recyclerView.getChildAt(i7);
                RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) childAt.getLayoutParams();
                if (flexDirection == 3) {
                    i2 = childAt.getBottom() + layoutParams.bottomMargin;
                    i = this.b.getIntrinsicHeight() + i2;
                } else {
                    i = childAt.getTop() - layoutParams.topMargin;
                    i2 = i - this.b.getIntrinsicHeight();
                }
                if (!flexboxLayoutManager.a()) {
                    i3 = childAt.getLeft() - layoutParams.leftMargin;
                    i6 = childAt.getRight();
                    i5 = layoutParams.rightMargin;
                } else if (flexboxLayoutManager.c()) {
                    int min = Math.min(childAt.getRight() + layoutParams.rightMargin + this.b.getIntrinsicWidth(), right);
                    i3 = childAt.getLeft() - layoutParams.leftMargin;
                    i4 = min;
                    this.b.setBounds(i3, i2, i4, i);
                    this.b.draw(canvas);
                } else {
                    i3 = Math.max((childAt.getLeft() - layoutParams.leftMargin) - this.b.getIntrinsicWidth(), left);
                    i6 = childAt.getRight();
                    i5 = layoutParams.rightMargin;
                }
                i4 = i6 + i5;
                this.b.setBounds(i3, i2, i4, i);
                this.b.draw(canvas);
            }
        }
    }

    private boolean a() {
        return (this.c & 1) > 0;
    }

    private boolean b() {
        return (this.c & 2) > 0;
    }

    private boolean a(int i, List<b> list, FlexboxLayoutManager flexboxLayoutManager) {
        int g = flexboxLayoutManager.g(i);
        if ((g != -1 && g < flexboxLayoutManager.getFlexLinesInternal().size() && flexboxLayoutManager.getFlexLinesInternal().get(g).o == i) || i == 0) {
            return true;
        }
        if (list.size() != 0 && list.get(list.size() - 1).p == i - 1) {
            return true;
        }
        return false;
    }
}
