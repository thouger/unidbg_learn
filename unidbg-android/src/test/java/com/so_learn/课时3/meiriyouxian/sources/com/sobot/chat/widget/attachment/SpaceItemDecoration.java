package com.sobot.chat.widget.attachment;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.view.View;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

public class SpaceItemDecoration extends RecyclerView.ItemDecoration {
    private int a;
    private int b;
    private int c;
    private int d;
    private boolean e;
    private int f;
    private int g;

    public SpaceItemDecoration(int i, int i2, int i3, int i4) {
        this.a = i;
        this.b = i2;
        this.c = i3;
        this.g = i4;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.ItemDecoration
    public void onDraw(Canvas canvas, RecyclerView recyclerView, RecyclerView.State state) {
        super.onDraw(canvas, recyclerView, state);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.ItemDecoration
    public void getItemOffsets(Rect rect, View view, RecyclerView recyclerView, RecyclerView.State state) {
        int i = this.g;
        if (i == 0) {
            a(rect, view, recyclerView, state);
        } else if (i == 1) {
            this.f = ((GridLayoutManager) recyclerView.getLayoutManager()).getSpanCount();
            c(rect, view, recyclerView, state);
        } else if (i == 2) {
            this.f = ((StaggeredGridLayoutManager) recyclerView.getLayoutManager()).getSpanCount();
            b(rect, view, recyclerView, state);
        }
    }

    private void a(Rect rect, View view, RecyclerView recyclerView, RecyclerView.State state) {
        int i = this.d;
        rect.left = i;
        rect.right = i;
        rect.bottom = i;
        if (recyclerView.getChildLayoutPosition(view) == 0) {
            rect.top = this.d;
        } else {
            rect.top = 0;
        }
    }

    private void b(Rect rect, View view, RecyclerView recyclerView, RecyclerView.State state) {
        int childAdapterPosition = recyclerView.getChildAdapterPosition(view);
        int i = this.c;
        int i2 = childAdapterPosition - i;
        if (i == 0 || i2 != (-i)) {
            int i3 = this.f;
            int i4 = i2 % i3;
            if (this.e) {
                int i5 = this.d;
                rect.left = i5 - ((i4 * i5) / i3);
                rect.right = ((i4 + 1) * i5) / i3;
                if (i2 < i3) {
                    rect.top = i5;
                }
                rect.bottom = this.d;
                return;
            }
            int i6 = this.d;
            rect.left = (i4 * i6) / i3;
            rect.right = i6 - (((i4 + 1) * i6) / i3);
            if (i2 >= i3) {
                rect.top = i6;
            }
        }
    }

    private void c(Rect rect, View view, RecyclerView recyclerView, RecyclerView.State state) {
        GridLayoutManager gridLayoutManager = (GridLayoutManager) recyclerView.getLayoutManager();
        int itemCount = gridLayoutManager.getItemCount();
        int spanCount = itemCount % gridLayoutManager.getSpanCount();
        int childAdapterPosition = recyclerView.getChildAdapterPosition(view);
        if (childAdapterPosition < this.f) {
            rect.top = 0;
        } else {
            rect.top = this.b / 2;
        }
        if (childAdapterPosition % this.f == 0) {
            rect.left = 0;
        } else {
            rect.left = this.a / 2;
        }
        if ((childAdapterPosition + 1) % this.f == 0) {
            rect.right = 0;
        } else {
            rect.right = this.a / 2;
        }
        if (childAdapterPosition >= itemCount - spanCount) {
            rect.bottom = 0;
        } else {
            rect.bottom = this.b;
        }
    }
}
