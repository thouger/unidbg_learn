package com.sobot.chat.widget.horizontalgridpage;

import android.util.DisplayMetrics;
import android.view.View;
import androidx.recyclerview.widget.LinearSmoothScroller;
import androidx.recyclerview.widget.RecyclerView;
import com.sobot.chat.utils.m;

public class PagerGridSmoothScroller extends LinearSmoothScroller {
    private String a = "PagerGridSmoothScroller";
    private RecyclerView b;

    public PagerGridSmoothScroller(RecyclerView recyclerView) {
        super(recyclerView.getContext());
        this.b = recyclerView;
    }

    /* access modifiers changed from: protected */
    @Override // androidx.recyclerview.widget.LinearSmoothScroller, androidx.recyclerview.widget.RecyclerView.SmoothScroller
    public void onTargetFound(View view, RecyclerView.State state, RecyclerView.SmoothScroller.Action action) {
        RecyclerView.LayoutManager layoutManager = this.b.getLayoutManager();
        if (layoutManager != null && (layoutManager instanceof PagerGridLayoutManager)) {
            int[] a = ((PagerGridLayoutManager) layoutManager).a(this.b.getChildAdapterPosition(view));
            int i = a[0];
            int i2 = a[1];
            m.d("dx = " + i);
            m.d("dy = " + i2);
            int calculateTimeForScrolling = calculateTimeForScrolling(Math.max(Math.abs(i), Math.abs(i2)));
            if (calculateTimeForScrolling > 0) {
                action.update(i, i2, calculateTimeForScrolling, this.mDecelerateInterpolator);
            }
        }
    }

    /* access modifiers changed from: protected */
    @Override // androidx.recyclerview.widget.LinearSmoothScroller
    public float calculateSpeedPerPixel(DisplayMetrics displayMetrics) {
        return c.b() / ((float) displayMetrics.densityDpi);
    }
}
