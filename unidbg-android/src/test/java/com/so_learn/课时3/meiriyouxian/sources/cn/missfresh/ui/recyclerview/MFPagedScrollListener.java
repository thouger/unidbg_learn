package cn.missfresh.ui.recyclerview;

import android.graphics.Rect;
import android.os.Handler;
import android.view.View;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import com.android.internal.logging.nano.MetricsProto;
import java.lang.ref.WeakReference;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public abstract class MFPagedScrollListener extends RecyclerView.OnScrollListener implements RecyclerView.OnChildAttachStateChangeListener {
    private WeakReference<RecyclerView> a;
    private int b = 0;
    private int[] c;
    private Rect d = new Rect();
    private List<Integer> e = new LinkedList();
    private List<Integer> f = new LinkedList();
    private Handler g;
    private Runnable h = new AnonymousClass1();

    public abstract void a();

    public abstract void a(List<Integer> list);

    public abstract int b();

    public float c() {
        return 0.0f;
    }

    public boolean d() {
        return false;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.OnChildAttachStateChangeListener
    public void onChildViewDetachedFromWindow(View view) {
    }

    /* renamed from: cn.missfresh.ui.recyclerview.MFPagedScrollListener$1  reason: invalid class name */
    class AnonymousClass1 implements Runnable {
        AnonymousClass1() {
        }

        @Override // java.lang.Runnable
        public void run() {
            AppMethodBeat.i(MetricsProto.MetricsEvent.APP_TRANSITION_BIND_APPLICATION_DELAY_MS, false);
            List<Integer> f = MFPagedScrollListener.this.f();
            if (MFPagedScrollListener.this.d()) {
                if (!f.isEmpty()) {
                    MFPagedScrollListener.this.f.clear();
                    MFPagedScrollListener.this.f.addAll(MFPagedScrollListener.this.e);
                    MFPagedScrollListener.this.e.clear();
                    MFPagedScrollListener.this.e.addAll(f);
                    f.removeAll(MFPagedScrollListener.this.f);
                    if (!f.isEmpty()) {
                        MFPagedScrollListener.this.a(f);
                    }
                } else {
                    MFPagedScrollListener.this.e.clear();
                }
            } else if (!f.isEmpty()) {
                MFPagedScrollListener.this.a(f);
            }
            AppMethodBeat.o(MetricsProto.MetricsEvent.APP_TRANSITION_BIND_APPLICATION_DELAY_MS);
        }
    }

    public void e() {
        RecyclerView g = g();
        if (g != null && g.getScrollState() == 0) {
            if (this.g == null) {
                this.g = new Handler();
            }
            this.g.removeCallbacks(this.h);
            this.g.postDelayed(this.h, 400);
        }
    }

    public List<Integer> f() {
        int childAdapterPosition;
        LinkedList linkedList = new LinkedList();
        RecyclerView g = g();
        if (g == null) {
            return linkedList;
        }
        int h = (h() + 0) - 1;
        for (int i = 0; i < g.getChildCount(); i++) {
            View childAt = g.getChildAt(i);
            if (childAt != null && childAt.getGlobalVisibleRect(this.d)) {
                if (a(this.d, childAt) >= Math.min(c(), 1.0f) && (childAdapterPosition = g.getChildAdapterPosition(childAt)) >= 0 && childAdapterPosition <= h) {
                    linkedList.add(Integer.valueOf(childAdapterPosition + 0));
                }
            }
        }
        return linkedList;
    }

    private float a(Rect rect, View view) {
        float f = (float) ((rect.right - rect.left) * (rect.bottom - rect.top));
        float width = (float) (view.getWidth() * view.getHeight());
        if (width > 0.0f) {
            return f / width;
        }
        return 1.0f;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
    public void onScrollStateChanged(RecyclerView recyclerView, int i) {
        StaggeredGridLayoutManager staggeredGridLayoutManager;
        super.onScrollStateChanged(recyclerView, i);
        if (i == 0) {
            int i2 = this.b;
            if (i2 > 0) {
                List<Integer> f = f();
                int h = (h() - 1) - b();
                if (h < 0) {
                    h = 0;
                }
                if (!f.isEmpty() && f.contains(Integer.valueOf(h))) {
                    a();
                }
            } else if (i2 < 0 && (recyclerView.getLayoutManager() instanceof StaggeredGridLayoutManager) && (staggeredGridLayoutManager = (StaggeredGridLayoutManager) recyclerView.getLayoutManager()) != null) {
                if (this.c == null) {
                    this.c = new int[staggeredGridLayoutManager.getSpanCount()];
                }
                Arrays.fill(this.c, -1);
                staggeredGridLayoutManager.findFirstCompletelyVisibleItemPositions(this.c);
                if (a(this.c) == 0) {
                    staggeredGridLayoutManager.invalidateSpanAssignments();
                }
            }
            e();
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
    public void onScrolled(RecyclerView recyclerView, int i, int i2) {
        super.onScrolled(recyclerView, i, i2);
        this.b = i2;
        if (recyclerView != g()) {
            a(recyclerView);
            e();
            recyclerView.addOnChildAttachStateChangeListener(this);
        }
    }

    private RecyclerView g() {
        WeakReference<RecyclerView> weakReference = this.a;
        if (weakReference != null) {
            return weakReference.get();
        }
        return null;
    }

    private void a(RecyclerView recyclerView) {
        WeakReference<RecyclerView> weakReference = this.a;
        if (weakReference == null || recyclerView != weakReference.get()) {
            this.a = new WeakReference<>(recyclerView);
        }
    }

    private int h() {
        RecyclerView g = g();
        if (g == null || g.getAdapter() == null) {
            return 0;
        }
        return g.getAdapter().getItemCount();
    }

    private int a(int[] iArr) {
        int i = Integer.MAX_VALUE;
        for (int i2 : iArr) {
            if (i2 >= 0 && i2 < i) {
                i = i2;
            }
        }
        return i;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.OnChildAttachStateChangeListener
    public void onChildViewAttachedToWindow(View view) {
        e();
    }
}
