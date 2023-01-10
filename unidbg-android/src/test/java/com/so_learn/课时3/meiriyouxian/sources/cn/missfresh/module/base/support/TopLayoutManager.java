package cn.missfresh.module.base.support;

import android.content.Context;
import android.util.AttributeSet;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSmoothScroller;
import androidx.recyclerview.widget.RecyclerView;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import cn.missfresh.utils.a.d;

public class TopLayoutManager extends LinearLayoutManager {
    private final String a = "TopLayoutManager";
    private int b;

    @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.LayoutManager
    public boolean supportsPredictiveItemAnimations() {
        return false;
    }

    public TopLayoutManager(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void smoothScrollToPosition(RecyclerView recyclerView, RecyclerView.State state, int i) {
        AppMethodBeat.i(20312, false);
        a aVar = new a(recyclerView.getContext(), this.b);
        aVar.setTargetPosition(i);
        aVar.a(((LinearLayoutManager) recyclerView.getLayoutManager()).findFirstVisibleItemPosition());
        startSmoothScroll(aVar);
        AppMethodBeat.o(20312);
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
        AppMethodBeat.i(20316, false);
        try {
            super.onLayoutChildren(recycler, state);
        } catch (IndexOutOfBoundsException unused) {
            d.b("TopLayoutManager", "meet a IOOBE in RecyclerView");
        }
        AppMethodBeat.o(20316);
    }

    private static class a extends LinearSmoothScroller {
        private int a;
        private int b;

        a(Context context, int i) {
            super(context);
            this.a = i;
        }

        public void a(int i) {
            this.b = i;
        }

        @Override // androidx.recyclerview.widget.LinearSmoothScroller
        public int calculateDtToFit(int i, int i2, int i3, int i4, int i5) {
            return (i3 - i) + this.a;
        }
    }
}
