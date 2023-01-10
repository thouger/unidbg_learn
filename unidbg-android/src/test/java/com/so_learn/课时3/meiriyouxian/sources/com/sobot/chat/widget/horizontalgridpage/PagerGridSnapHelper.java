package com.sobot.chat.widget.horizontalgridpage;

import android.view.View;
import androidx.recyclerview.widget.LinearSmoothScroller;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;
import com.sobot.chat.utils.m;

public class PagerGridSnapHelper extends SnapHelper {
    private String a = "PagerGridSnapHelper";
    private RecyclerView b;

    @Override // androidx.recyclerview.widget.SnapHelper
    public void attachToRecyclerView(RecyclerView recyclerView) throws IllegalStateException {
        super.attachToRecyclerView(recyclerView);
        this.b = recyclerView;
    }

    @Override // androidx.recyclerview.widget.SnapHelper
    public int[] calculateDistanceToFinalSnap(RecyclerView.LayoutManager layoutManager, View view) {
        int position = layoutManager.getPosition(view);
        m.c("findTargetSnapPosition, pos = " + position);
        return layoutManager instanceof PagerGridLayoutManager ? ((PagerGridLayoutManager) layoutManager).a(position) : new int[2];
    }

    @Override // androidx.recyclerview.widget.SnapHelper
    public View findSnapView(RecyclerView.LayoutManager layoutManager) {
        if (layoutManager instanceof PagerGridLayoutManager) {
            return ((PagerGridLayoutManager) layoutManager).c();
        }
        return null;
    }

    @Override // androidx.recyclerview.widget.SnapHelper
    public int findTargetSnapPosition(RecyclerView.LayoutManager layoutManager, int i, int i2) {
        int i3;
        m.c("findTargetSnapPosition, velocityX = " + i + ", velocityY" + i2);
        if (layoutManager != null && (layoutManager instanceof PagerGridLayoutManager)) {
            PagerGridLayoutManager pagerGridLayoutManager = (PagerGridLayoutManager) layoutManager;
            if (pagerGridLayoutManager.canScrollHorizontally()) {
                if (i > c.a()) {
                    i3 = pagerGridLayoutManager.a();
                } else if (i < (-c.a())) {
                    i3 = pagerGridLayoutManager.b();
                }
                m.c("findTargetSnapPosition, target = " + i3);
                return i3;
            } else if (pagerGridLayoutManager.canScrollVertically()) {
                if (i2 > c.a()) {
                    i3 = pagerGridLayoutManager.a();
                } else if (i2 < (-c.a())) {
                    i3 = pagerGridLayoutManager.b();
                }
                m.c("findTargetSnapPosition, target = " + i3);
                return i3;
            }
        }
        i3 = -1;
        m.c("findTargetSnapPosition, target = " + i3);
        return i3;
    }

    @Override // androidx.recyclerview.widget.SnapHelper, androidx.recyclerview.widget.RecyclerView.OnFlingListener
    public boolean onFling(int i, int i2) {
        RecyclerView.LayoutManager layoutManager = this.b.getLayoutManager();
        if (layoutManager == null || this.b.getAdapter() == null) {
            return false;
        }
        int a = c.a();
        m.c("minFlingVelocity = " + a);
        if ((Math.abs(i2) > a || Math.abs(i) > a) && a(layoutManager, i, i2)) {
            return true;
        }
        return false;
    }

    private boolean a(RecyclerView.LayoutManager layoutManager, int i, int i2) {
        LinearSmoothScroller createSnapScroller;
        int findTargetSnapPosition;
        if (!(layoutManager instanceof RecyclerView.SmoothScroller.ScrollVectorProvider) || (createSnapScroller = createSnapScroller(layoutManager)) == null || (findTargetSnapPosition = findTargetSnapPosition(layoutManager, i, i2)) == -1) {
            return false;
        }
        createSnapScroller.setTargetPosition(findTargetSnapPosition);
        layoutManager.startSmoothScroll(createSnapScroller);
        return true;
    }

    /* access modifiers changed from: protected */
    @Override // androidx.recyclerview.widget.SnapHelper
    public LinearSmoothScroller createSnapScroller(RecyclerView.LayoutManager layoutManager) {
        if (!(layoutManager instanceof RecyclerView.SmoothScroller.ScrollVectorProvider)) {
            return null;
        }
        return new PagerGridSmoothScroller(this.b);
    }
}
