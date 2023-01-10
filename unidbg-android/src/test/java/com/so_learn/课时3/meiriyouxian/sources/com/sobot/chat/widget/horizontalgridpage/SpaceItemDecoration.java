package com.sobot.chat.widget.horizontalgridpage;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.view.View;
import androidx.recyclerview.widget.RecyclerView;

public class SpaceItemDecoration extends RecyclerView.ItemDecoration {
    private int a;
    private int b;

    public SpaceItemDecoration(int i, int i2) {
        this.a = i;
        this.b = i2;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.ItemDecoration
    public void onDraw(Canvas canvas, RecyclerView recyclerView, RecyclerView.State state) {
        super.onDraw(canvas, recyclerView, state);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.ItemDecoration
    public void getItemOffsets(Rect rect, View view, RecyclerView recyclerView, RecyclerView.State state) {
        PagerGridLayoutManager pagerGridLayoutManager = (PagerGridLayoutManager) recyclerView.getLayoutManager();
        if (pagerGridLayoutManager.d() == 0) {
            if (recyclerView.getChildAdapterPosition(view) == pagerGridLayoutManager.getItemCount() - 1) {
                rect.bottom = this.b;
            }
            if (recyclerView.getChildAdapterPosition(view) == 0) {
                rect.top = 0;
            } else {
                rect.top = this.b;
            }
            int i = this.a;
            rect.left = i;
            rect.right = i;
            return;
        }
        if (recyclerView.getChildAdapterPosition(view) == pagerGridLayoutManager.getItemCount() - 1) {
            rect.right = this.a;
        }
        int i2 = this.b;
        rect.top = i2;
        rect.left = this.a;
        rect.bottom = i2;
    }
}
