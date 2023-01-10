package cn.missfresh.module.base.widget.recycleview;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;

public abstract class OnRecyclerViewScrollListener extends RecyclerView.OnScrollListener {
    protected LayoutManagerType a;
    private String b = getClass().getSimpleName();
    private int[] c;
    private int d;
    private int e = 0;
    private boolean f = true;
    private int g = 0;
    private int h = 0;

    public abstract void a();

    public abstract void a(int i, int i2);

    public abstract void b();

    public abstract void c();

    /* access modifiers changed from: private */
    public enum LayoutManagerType {
        LINEAR,
        GRID,
        STAGGERED_GRID;

        public static LayoutManagerType valueOf(String str) {
            AppMethodBeat.i(24262, false);
            LayoutManagerType layoutManagerType = (LayoutManagerType) Enum.valueOf(LayoutManagerType.class, str);
            AppMethodBeat.o(24262);
            return layoutManagerType;
        }

        static {
            AppMethodBeat.i(24263, false);
            AppMethodBeat.o(24263);
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
    public void onScrolled(RecyclerView recyclerView, int i, int i2) {
        super.onScrolled(recyclerView, i, i2);
        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        a(layoutManager);
        b(a(layoutManager, 0), i2);
        this.h += i;
        this.g += i2;
        int i3 = this.h;
        if (i3 < 0) {
            i3 = 0;
        }
        this.h = i3;
        int i4 = this.g;
        if (i4 < 0) {
            i4 = 0;
        }
        this.g = i4;
        a(this.h, this.g);
    }

    private void a(RecyclerView.LayoutManager layoutManager) {
        if (layoutManager instanceof LinearLayoutManager) {
            this.a = LayoutManagerType.LINEAR;
        } else if (layoutManager instanceof GridLayoutManager) {
            this.a = LayoutManagerType.GRID;
        } else if (layoutManager instanceof StaggeredGridLayoutManager) {
            this.a = LayoutManagerType.STAGGERED_GRID;
        } else {
            throw new RuntimeException("Unsupported LayoutManager used. Valid ones are LinearLayoutManager, GridLayoutManager and StaggeredGridLayoutManager");
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: cn.missfresh.module.base.widget.recycleview.OnRecyclerViewScrollListener$1  reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] a = new int[LayoutManagerType.values().length];

        static {
            AppMethodBeat.i(24260, false);
            try {
                a[LayoutManagerType.LINEAR.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                a[LayoutManagerType.GRID.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                a[LayoutManagerType.STAGGERED_GRID.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            AppMethodBeat.o(24260);
        }
    }

    private int a(RecyclerView.LayoutManager layoutManager, int i) {
        int i2 = AnonymousClass1.a[this.a.ordinal()];
        if (i2 == 1) {
            LinearLayoutManager linearLayoutManager = (LinearLayoutManager) layoutManager;
            this.d = linearLayoutManager.findLastVisibleItemPosition();
            return linearLayoutManager.findFirstVisibleItemPosition();
        } else if (i2 == 2) {
            GridLayoutManager gridLayoutManager = (GridLayoutManager) layoutManager;
            this.d = gridLayoutManager.findLastVisibleItemPosition();
            return gridLayoutManager.findFirstVisibleItemPosition();
        } else if (i2 != 3) {
            return i;
        } else {
            StaggeredGridLayoutManager staggeredGridLayoutManager = (StaggeredGridLayoutManager) layoutManager;
            if (this.c == null) {
                this.c = new int[staggeredGridLayoutManager.getSpanCount()];
            }
            this.c = staggeredGridLayoutManager.findLastVisibleItemPositions(this.c);
            this.d = a(this.c);
            staggeredGridLayoutManager.findFirstCompletelyVisibleItemPositions(this.c);
            return a(this.c);
        }
    }

    private void b(int i, int i2) {
        if (i == 0) {
            if (!this.f) {
                b();
                this.f = true;
            }
        } else if (this.e > 20 && this.f) {
            a();
            this.f = false;
            this.e = 0;
        } else if (this.e < -20 && !this.f) {
            b();
            this.f = true;
            this.e = 0;
        }
        if ((this.f && i2 > 0) || (!this.f && i2 < 0)) {
            this.e += i2;
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
    public void onScrollStateChanged(RecyclerView recyclerView, int i) {
        super.onScrollStateChanged(recyclerView, i);
        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        int childCount = layoutManager.getChildCount();
        int itemCount = layoutManager.getItemCount();
        if (childCount > 0 && i == 0 && this.d >= itemCount - 1) {
            c();
        }
    }

    private int a(int[] iArr) {
        int i = iArr[0];
        for (int i2 : iArr) {
            if (i2 > i) {
                i = i2;
            }
        }
        return i;
    }
}
