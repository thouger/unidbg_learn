package com.google.android.flexbox;

import android.content.Context;
import android.graphics.PointF;
import android.graphics.Rect;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.View;
import androidx.recyclerview.widget.LinearSmoothScroller;
import androidx.recyclerview.widget.OrientationHelper;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.flexbox.c;
import java.util.ArrayList;
import java.util.List;

public class FlexboxLayoutManager extends RecyclerView.LayoutManager implements RecyclerView.SmoothScroller.ScrollVectorProvider, a {
    static final /* synthetic */ boolean a = (!FlexboxLayoutManager.class.desiredAssertionStatus());
    private static final Rect b = new Rect();
    private int A;
    private c.a B;
    private int c;
    private int d;
    private int e;
    private int f;
    private int g;
    private boolean h;
    private boolean i;
    private List<b> j;
    private final c k;
    private RecyclerView.Recycler l;
    private RecyclerView.State m;
    private b n;
    private a o;
    private OrientationHelper p;
    private OrientationHelper q;
    private SavedState r;
    private int s;
    private int t;
    private int u;
    private int v;
    private boolean w;
    private SparseArray<View> x;
    private final Context y;
    private View z;

    @Override // com.google.android.flexbox.a
    public void a(b bVar) {
    }

    @Override // com.google.android.flexbox.a
    public int getAlignContent() {
        return 5;
    }

    public FlexboxLayoutManager(Context context) {
        this(context, 0, 1);
    }

    public FlexboxLayoutManager(Context context, int i, int i2) {
        this.g = -1;
        this.j = new ArrayList();
        this.k = new c(this);
        this.o = new a();
        this.s = -1;
        this.t = Integer.MIN_VALUE;
        this.u = Integer.MIN_VALUE;
        this.v = Integer.MIN_VALUE;
        this.x = new SparseArray<>();
        this.A = -1;
        this.B = new c.a();
        c(i);
        d(i2);
        f(4);
        setAutoMeasureEnabled(true);
        this.y = context;
    }

    public FlexboxLayoutManager(Context context, AttributeSet attributeSet, int i, int i2) {
        this.g = -1;
        this.j = new ArrayList();
        this.k = new c(this);
        this.o = new a();
        this.s = -1;
        this.t = Integer.MIN_VALUE;
        this.u = Integer.MIN_VALUE;
        this.v = Integer.MIN_VALUE;
        this.x = new SparseArray<>();
        this.A = -1;
        this.B = new c.a();
        RecyclerView.LayoutManager.Properties properties = getProperties(context, attributeSet, i, i2);
        int i3 = properties.orientation;
        if (i3 != 0) {
            if (i3 == 1) {
                if (properties.reverseLayout) {
                    c(3);
                } else {
                    c(2);
                }
            }
        } else if (properties.reverseLayout) {
            c(1);
        } else {
            c(0);
        }
        d(1);
        f(4);
        setAutoMeasureEnabled(true);
        this.y = context;
    }

    @Override // com.google.android.flexbox.a
    public int getFlexDirection() {
        return this.c;
    }

    public void c(int i) {
        if (this.c != i) {
            removeAllViews();
            this.c = i;
            this.p = null;
            this.q = null;
            k();
            requestLayout();
        }
    }

    @Override // com.google.android.flexbox.a
    public int getFlexWrap() {
        return this.d;
    }

    public void d(int i) {
        if (i != 2) {
            int i2 = this.d;
            if (i2 != i) {
                if (i2 == 0 || i == 0) {
                    removeAllViews();
                    k();
                }
                this.d = i;
                this.p = null;
                this.q = null;
                requestLayout();
                return;
            }
            return;
        }
        throw new UnsupportedOperationException("wrap_reverse is not supported in FlexboxLayoutManager");
    }

    public void e(int i) {
        if (this.e != i) {
            this.e = i;
            requestLayout();
        }
    }

    @Override // com.google.android.flexbox.a
    public int getAlignItems() {
        return this.f;
    }

    public void f(int i) {
        int i2 = this.f;
        if (i2 != i) {
            if (i2 == 4 || i == 4) {
                removeAllViews();
                k();
            }
            this.f = i;
            requestLayout();
        }
    }

    @Override // com.google.android.flexbox.a
    public int getMaxLine() {
        return this.g;
    }

    public List<b> b() {
        ArrayList arrayList = new ArrayList(this.j.size());
        int size = this.j.size();
        for (int i = 0; i < size; i++) {
            b bVar = this.j.get(i);
            if (bVar.b() != 0) {
                arrayList.add(bVar);
            }
        }
        return arrayList;
    }

    @Override // com.google.android.flexbox.a
    public int a(View view, int i, int i2) {
        int topDecorationHeight;
        int bottomDecorationHeight;
        if (a()) {
            topDecorationHeight = getLeftDecorationWidth(view);
            bottomDecorationHeight = getRightDecorationWidth(view);
        } else {
            topDecorationHeight = getTopDecorationHeight(view);
            bottomDecorationHeight = getBottomDecorationHeight(view);
        }
        return topDecorationHeight + bottomDecorationHeight;
    }

    @Override // com.google.android.flexbox.a
    public int a(View view) {
        int leftDecorationWidth;
        int rightDecorationWidth;
        if (a()) {
            leftDecorationWidth = getTopDecorationHeight(view);
            rightDecorationWidth = getBottomDecorationHeight(view);
        } else {
            leftDecorationWidth = getLeftDecorationWidth(view);
            rightDecorationWidth = getRightDecorationWidth(view);
        }
        return leftDecorationWidth + rightDecorationWidth;
    }

    @Override // com.google.android.flexbox.a
    public void a(View view, int i, int i2, b bVar) {
        calculateItemDecorationsForChild(view, b);
        if (a()) {
            int leftDecorationWidth = getLeftDecorationWidth(view) + getRightDecorationWidth(view);
            bVar.e += leftDecorationWidth;
            bVar.f += leftDecorationWidth;
            return;
        }
        int topDecorationHeight = getTopDecorationHeight(view) + getBottomDecorationHeight(view);
        bVar.e += topDecorationHeight;
        bVar.f += topDecorationHeight;
    }

    @Override // com.google.android.flexbox.a
    public int getFlexItemCount() {
        return this.m.getItemCount();
    }

    @Override // com.google.android.flexbox.a
    public View a(int i) {
        View view = (View) this.x.get(i);
        if (view != null) {
            return view;
        }
        return this.l.getViewForPosition(i);
    }

    @Override // com.google.android.flexbox.a
    public View b(int i) {
        return a(i);
    }

    @Override // com.google.android.flexbox.a
    public int a(int i, int i2, int i3) {
        return getChildMeasureSpec(getWidth(), getWidthMode(), i2, i3, canScrollHorizontally());
    }

    @Override // com.google.android.flexbox.a
    public int b(int i, int i2, int i3) {
        return getChildMeasureSpec(getHeight(), getHeightMode(), i2, i3, canScrollVertically());
    }

    @Override // com.google.android.flexbox.a
    public int getLargestMainSize() {
        if (this.j.size() == 0) {
            return 0;
        }
        int i = Integer.MIN_VALUE;
        int size = this.j.size();
        for (int i2 = 0; i2 < size; i2++) {
            i = Math.max(i, this.j.get(i2).e);
        }
        return i;
    }

    @Override // com.google.android.flexbox.a
    public int getSumOfCrossSize() {
        int size = this.j.size();
        int i = 0;
        for (int i2 = 0; i2 < size; i2++) {
            i += this.j.get(i2).g;
        }
        return i;
    }

    @Override // com.google.android.flexbox.a
    public void setFlexLines(List<b> list) {
        this.j = list;
    }

    @Override // com.google.android.flexbox.a
    public List<b> getFlexLinesInternal() {
        return this.j;
    }

    @Override // com.google.android.flexbox.a
    public void a(int i, View view) {
        this.x.put(i, view);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.SmoothScroller.ScrollVectorProvider
    public PointF computeScrollVectorForPosition(int i) {
        if (getChildCount() == 0) {
            return null;
        }
        int i2 = i < getPosition(getChildAt(0)) ? -1 : 1;
        if (a()) {
            return new PointF(0.0f, (float) i2);
        }
        return new PointF((float) i2, 0.0f);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public RecyclerView.LayoutParams generateDefaultLayoutParams() {
        return new LayoutParams(-2, -2);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public RecyclerView.LayoutParams generateLayoutParams(Context context, AttributeSet attributeSet) {
        return new LayoutParams(context, attributeSet);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public boolean checkLayoutParams(RecyclerView.LayoutParams layoutParams) {
        return layoutParams instanceof LayoutParams;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void onAdapterChanged(RecyclerView.Adapter adapter, RecyclerView.Adapter adapter2) {
        removeAllViews();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public Parcelable onSaveInstanceState() {
        SavedState savedState = this.r;
        if (savedState != null) {
            return new SavedState(savedState);
        }
        SavedState savedState2 = new SavedState();
        if (getChildCount() > 0) {
            View g = g();
            savedState2.a = getPosition(g);
            savedState2.b = this.p.getDecoratedStart(g) - this.p.getStartAfterPadding();
        } else {
            savedState2.a();
        }
        return savedState2;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void onRestoreInstanceState(Parcelable parcelable) {
        if (parcelable instanceof SavedState) {
            this.r = (SavedState) parcelable;
            requestLayout();
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void onItemsAdded(RecyclerView recyclerView, int i, int i2) {
        super.onItemsAdded(recyclerView, i, i2);
        h(i);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void onItemsUpdated(RecyclerView recyclerView, int i, int i2, Object obj) {
        super.onItemsUpdated(recyclerView, i, i2, obj);
        h(i);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void onItemsUpdated(RecyclerView recyclerView, int i, int i2) {
        super.onItemsUpdated(recyclerView, i, i2);
        h(i);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void onItemsRemoved(RecyclerView recyclerView, int i, int i2) {
        super.onItemsRemoved(recyclerView, i, i2);
        h(i);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void onItemsMoved(RecyclerView recyclerView, int i, int i2, int i3) {
        super.onItemsMoved(recyclerView, i, i2, i3);
        h(Math.min(i, i2));
    }

    private void h(int i) {
        if (i < e()) {
            int childCount = getChildCount();
            this.k.c(childCount);
            this.k.b(childCount);
            this.k.d(childCount);
            if (!a && this.k.a == null) {
                throw new AssertionError();
            } else if (i < this.k.a.length) {
                this.A = i;
                View g = g();
                if (g != null) {
                    this.s = getPosition(g);
                    if (a() || !this.h) {
                        this.t = this.p.getDecoratedStart(g) - this.p.getStartAfterPadding();
                    } else {
                        this.t = this.p.getDecoratedEnd(g) + this.p.getEndPadding();
                    }
                }
            }
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
        int i;
        int i2;
        this.l = recycler;
        this.m = state;
        int itemCount = state.getItemCount();
        if (itemCount != 0 || !state.isPreLayout()) {
            f();
            i();
            j();
            this.k.c(itemCount);
            this.k.b(itemCount);
            this.k.d(itemCount);
            this.n.j = false;
            SavedState savedState = this.r;
            if (savedState != null && savedState.a(itemCount)) {
                this.s = this.r.a;
            }
            if (!(this.o.h && this.s == -1 && this.r == null)) {
                this.o.a();
                a(state, this.o);
                this.o.h = true;
            }
            detachAndScrapAttachedViews(recycler);
            if (this.o.g) {
                b(this.o, false, true);
            } else {
                a(this.o, false, true);
            }
            i(itemCount);
            if (this.o.g) {
                a(recycler, state, this.n);
                i2 = this.n.e;
                a(this.o, true, false);
                a(recycler, state, this.n);
                i = this.n.e;
            } else {
                a(recycler, state, this.n);
                i = this.n.e;
                b(this.o, true, false);
                a(recycler, state, this.n);
                i2 = this.n.e;
            }
            if (getChildCount() <= 0) {
                return;
            }
            if (this.o.g) {
                a(i2 + b(i, recycler, state, true), recycler, state, false);
            } else {
                b(i + a(i2, recycler, state, true), recycler, state, false);
            }
        }
    }

    private int a(int i, RecyclerView.Recycler recycler, RecyclerView.State state, boolean z) {
        int i2;
        int startAfterPadding;
        if (a() || !this.h) {
            int startAfterPadding2 = i - this.p.getStartAfterPadding();
            if (startAfterPadding2 <= 0) {
                return 0;
            }
            i2 = -a(startAfterPadding2, recycler, state);
        } else {
            int endAfterPadding = this.p.getEndAfterPadding() - i;
            if (endAfterPadding <= 0) {
                return 0;
            }
            i2 = a(-endAfterPadding, recycler, state);
        }
        int i3 = i + i2;
        if (!z || (startAfterPadding = i3 - this.p.getStartAfterPadding()) <= 0) {
            return i2;
        }
        this.p.offsetChildren(-startAfterPadding);
        return i2 - startAfterPadding;
    }

    private int b(int i, RecyclerView.Recycler recycler, RecyclerView.State state, boolean z) {
        int i2;
        int endAfterPadding;
        if (!a() && this.h) {
            int startAfterPadding = i - this.p.getStartAfterPadding();
            if (startAfterPadding <= 0) {
                return 0;
            }
            i2 = a(startAfterPadding, recycler, state);
        } else {
            int endAfterPadding2 = this.p.getEndAfterPadding() - i;
            if (endAfterPadding2 <= 0) {
                return 0;
            }
            i2 = -a(-endAfterPadding2, recycler, state);
        }
        int i3 = i + i2;
        if (!z || (endAfterPadding = this.p.getEndAfterPadding() - i3) <= 0) {
            return i2;
        }
        this.p.offsetChildren(endAfterPadding);
        return endAfterPadding + i2;
    }

    private void i(int i) {
        int i2;
        int makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(getWidth(), getWidthMode());
        int makeMeasureSpec2 = View.MeasureSpec.makeMeasureSpec(getHeight(), getHeightMode());
        int width = getWidth();
        int height = getHeight();
        boolean z = true;
        if (a()) {
            int i3 = this.u;
            if (i3 == Integer.MIN_VALUE || i3 == width) {
                z = false;
            }
            if (this.n.b) {
                i2 = this.y.getResources().getDisplayMetrics().heightPixels;
            } else {
                i2 = this.n.a;
            }
        } else {
            int i4 = this.v;
            if (i4 == Integer.MIN_VALUE || i4 == height) {
                z = false;
            }
            if (this.n.b) {
                i2 = this.y.getResources().getDisplayMetrics().widthPixels;
            } else {
                i2 = this.n.a;
            }
        }
        this.u = width;
        this.v = height;
        if (this.A != -1 || (this.s == -1 && !z)) {
            int i5 = this.A;
            int min = i5 != -1 ? Math.min(i5, this.o.c) : this.o.c;
            this.B.a();
            if (a()) {
                if (this.j.size() > 0) {
                    this.k.a(this.j, min);
                    this.k.a(this.B, makeMeasureSpec, makeMeasureSpec2, i2, min, this.o.c, this.j);
                } else {
                    this.k.d(i);
                    this.k.a(this.B, makeMeasureSpec, makeMeasureSpec2, i2, 0, this.j);
                }
            } else if (this.j.size() > 0) {
                this.k.a(this.j, min);
                this.k.a(this.B, makeMeasureSpec2, makeMeasureSpec, i2, min, this.o.c, this.j);
            } else {
                this.k.d(i);
                this.k.c(this.B, makeMeasureSpec, makeMeasureSpec2, i2, 0, this.j);
            }
            this.j = this.B.a;
            this.k.a(makeMeasureSpec, makeMeasureSpec2, min);
            this.k.a(min);
        } else if (!this.o.g) {
            this.j.clear();
            if (a || this.k.a != null) {
                this.B.a();
                if (a()) {
                    this.k.b(this.B, makeMeasureSpec, makeMeasureSpec2, i2, this.o.c, this.j);
                } else {
                    this.k.d(this.B, makeMeasureSpec, makeMeasureSpec2, i2, this.o.c, this.j);
                }
                this.j = this.B.a;
                this.k.a(makeMeasureSpec, makeMeasureSpec2);
                this.k.a();
                this.o.d = this.k.a[this.o.c];
                this.n.c = this.o.d;
                return;
            }
            throw new AssertionError();
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void onLayoutCompleted(RecyclerView.State state) {
        super.onLayoutCompleted(state);
        this.r = null;
        this.s = -1;
        this.t = Integer.MIN_VALUE;
        this.A = -1;
        this.o.a();
        this.x.clear();
    }

    /* access modifiers changed from: package-private */
    public boolean c() {
        return this.h;
    }

    private void f() {
        int layoutDirection = getLayoutDirection();
        int i = this.c;
        boolean z = false;
        if (i == 0) {
            this.h = layoutDirection == 1;
            if (this.d == 2) {
                z = true;
            }
            this.i = z;
        } else if (i == 1) {
            this.h = layoutDirection != 1;
            if (this.d == 2) {
                z = true;
            }
            this.i = z;
        } else if (i == 2) {
            this.h = layoutDirection == 1;
            if (this.d == 2) {
                this.h = !this.h;
            }
            this.i = false;
        } else if (i != 3) {
            this.h = false;
            this.i = false;
        } else {
            if (layoutDirection == 1) {
                z = true;
            }
            this.h = z;
            if (this.d == 2) {
                this.h = !this.h;
            }
            this.i = true;
        }
    }

    private void a(RecyclerView.State state, a aVar) {
        if (!a(state, aVar, this.r) && !b(state, aVar)) {
            aVar.b();
            aVar.c = 0;
            aVar.d = 0;
        }
    }

    private boolean a(RecyclerView.State state, a aVar, SavedState savedState) {
        int i;
        int i2;
        if (a || this.k.a != null) {
            boolean z = false;
            if (!state.isPreLayout() && (i = this.s) != -1) {
                if (i < 0 || i >= state.getItemCount()) {
                    this.s = -1;
                    this.t = Integer.MIN_VALUE;
                } else {
                    aVar.c = this.s;
                    aVar.d = this.k.a[aVar.c];
                    SavedState savedState2 = this.r;
                    if (savedState2 != null && savedState2.a(state.getItemCount())) {
                        aVar.e = this.p.getStartAfterPadding() + savedState.b;
                        aVar.i = true;
                        aVar.d = -1;
                        return true;
                    } else if (this.t == Integer.MIN_VALUE) {
                        View findViewByPosition = findViewByPosition(this.s);
                        if (findViewByPosition == null) {
                            if (getChildCount() > 0) {
                                if (this.s < getPosition(getChildAt(0))) {
                                    z = true;
                                }
                                aVar.g = z;
                            }
                            aVar.b();
                        } else if (this.p.getDecoratedMeasurement(findViewByPosition) > this.p.getTotalSpace()) {
                            aVar.b();
                            return true;
                        } else if (this.p.getDecoratedStart(findViewByPosition) - this.p.getStartAfterPadding() < 0) {
                            aVar.e = this.p.getStartAfterPadding();
                            aVar.g = false;
                            return true;
                        } else if (this.p.getEndAfterPadding() - this.p.getDecoratedEnd(findViewByPosition) < 0) {
                            aVar.e = this.p.getEndAfterPadding();
                            aVar.g = true;
                            return true;
                        } else {
                            if (aVar.g) {
                                i2 = this.p.getDecoratedEnd(findViewByPosition) + this.p.getTotalSpaceChange();
                            } else {
                                i2 = this.p.getDecoratedStart(findViewByPosition);
                            }
                            aVar.e = i2;
                        }
                        return true;
                    } else {
                        if (a() || !this.h) {
                            aVar.e = this.p.getStartAfterPadding() + this.t;
                        } else {
                            aVar.e = this.t - this.p.getEndPadding();
                        }
                        return true;
                    }
                }
            }
            return false;
        }
        throw new AssertionError();
    }

    private boolean b(RecyclerView.State state, a aVar) {
        View view;
        int i;
        boolean z = false;
        if (getChildCount() == 0) {
            return false;
        }
        if (aVar.g) {
            view = k(state.getItemCount());
        } else {
            view = j(state.getItemCount());
        }
        if (view == null) {
            return false;
        }
        aVar.a(view);
        if (!state.isPreLayout() && supportsPredictiveItemAnimations()) {
            if (this.p.getDecoratedStart(view) >= this.p.getEndAfterPadding() || this.p.getDecoratedEnd(view) < this.p.getStartAfterPadding()) {
                z = true;
            }
            if (z) {
                if (aVar.g) {
                    i = this.p.getEndAfterPadding();
                } else {
                    i = this.p.getStartAfterPadding();
                }
                aVar.e = i;
            }
        }
        return true;
    }

    private View j(int i) {
        int i2;
        if (a || this.k.a != null) {
            View c = c(0, getChildCount(), i);
            if (c == null || (i2 = this.k.a[getPosition(c)]) == -1) {
                return null;
            }
            return a(c, this.j.get(i2));
        }
        throw new AssertionError();
    }

    private View k(int i) {
        if (a || this.k.a != null) {
            View c = c(getChildCount() - 1, -1, i);
            if (c == null) {
                return null;
            }
            return b(c, this.j.get(this.k.a[getPosition(c)]));
        }
        throw new AssertionError();
    }

    private View c(int i, int i2, int i3) {
        i();
        j();
        int startAfterPadding = this.p.getStartAfterPadding();
        int endAfterPadding = this.p.getEndAfterPadding();
        int i4 = i2 > i ? 1 : -1;
        View view = null;
        View view2 = null;
        while (i != i2) {
            View childAt = getChildAt(i);
            int position = getPosition(childAt);
            if (position >= 0 && position < i3) {
                if (((RecyclerView.LayoutParams) childAt.getLayoutParams()).isItemRemoved()) {
                    if (view2 == null) {
                        view2 = childAt;
                    }
                } else if (this.p.getDecoratedStart(childAt) >= startAfterPadding && this.p.getDecoratedEnd(childAt) <= endAfterPadding) {
                    return childAt;
                } else {
                    if (view == null) {
                        view = childAt;
                    }
                }
            }
            i += i4;
        }
        return view != null ? view : view2;
    }

    private View g() {
        return getChildAt(0);
    }

    private int a(RecyclerView.Recycler recycler, RecyclerView.State state, b bVar) {
        if (bVar.f != Integer.MIN_VALUE) {
            if (bVar.a < 0) {
                bVar.f += bVar.a;
            }
            a(recycler, bVar);
        }
        int i = bVar.a;
        int i2 = bVar.a;
        int i3 = 0;
        boolean a2 = a();
        while (true) {
            if ((i2 > 0 || this.n.b) && bVar.a(state, this.j)) {
                b bVar2 = this.j.get(bVar.c);
                bVar.d = bVar2.o;
                i3 += a(bVar2, bVar);
                if (a2 || !this.h) {
                    bVar.e += bVar2.a() * bVar.i;
                } else {
                    bVar.e -= bVar2.a() * bVar.i;
                }
                i2 -= bVar2.a();
            }
        }
        bVar.a -= i3;
        if (bVar.f != Integer.MIN_VALUE) {
            bVar.f += i3;
            if (bVar.a < 0) {
                bVar.f += bVar.a;
            }
            a(recycler, bVar);
        }
        return i - bVar.a;
    }

    private void a(RecyclerView.Recycler recycler, b bVar) {
        if (bVar.j) {
            if (bVar.i == -1) {
                c(recycler, bVar);
            } else {
                b(recycler, bVar);
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:25:0x0071, code lost:
        r2 = r5;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void b(androidx.recyclerview.widget.RecyclerView.Recycler r9, com.google.android.flexbox.FlexboxLayoutManager.b r10) {
        /*
            r8 = this;
            int r0 = com.google.android.flexbox.FlexboxLayoutManager.b.d(r10)
            if (r0 >= 0) goto L_0x0007
            return
        L_0x0007:
            boolean r0 = com.google.android.flexbox.FlexboxLayoutManager.a
            if (r0 != 0) goto L_0x0018
            com.google.android.flexbox.c r0 = r8.k
            int[] r0 = r0.a
            if (r0 == 0) goto L_0x0012
            goto L_0x0018
        L_0x0012:
            java.lang.AssertionError r9 = new java.lang.AssertionError
            r9.<init>()
            throw r9
        L_0x0018:
            int r0 = r8.getChildCount()
            if (r0 != 0) goto L_0x001f
            return
        L_0x001f:
            r1 = 0
            android.view.View r2 = r8.getChildAt(r1)
            com.google.android.flexbox.c r3 = r8.k
            int[] r3 = r3.a
            int r2 = r8.getPosition(r2)
            r2 = r3[r2]
            r3 = -1
            if (r2 != r3) goto L_0x0032
            return
        L_0x0032:
            java.util.List<com.google.android.flexbox.b> r4 = r8.j
            java.lang.Object r4 = r4.get(r2)
            com.google.android.flexbox.b r4 = (com.google.android.flexbox.b) r4
            r5 = r3
            r3 = r2
            r2 = r1
        L_0x003d:
            if (r2 >= r0) goto L_0x0071
            android.view.View r6 = r8.getChildAt(r2)
            int r7 = com.google.android.flexbox.FlexboxLayoutManager.b.d(r10)
            boolean r7 = r8.a(r6, r7)
            if (r7 == 0) goto L_0x0071
            int r7 = r4.p
            int r6 = r8.getPosition(r6)
            if (r7 != r6) goto L_0x006e
            java.util.List<com.google.android.flexbox.b> r4 = r8.j
            int r4 = r4.size()
            int r4 = r4 + -1
            if (r3 < r4) goto L_0x0060
            goto L_0x0072
        L_0x0060:
            int r4 = com.google.android.flexbox.FlexboxLayoutManager.b.f(r10)
            int r3 = r3 + r4
            java.util.List<com.google.android.flexbox.b> r4 = r8.j
            java.lang.Object r4 = r4.get(r3)
            com.google.android.flexbox.b r4 = (com.google.android.flexbox.b) r4
            r5 = r2
        L_0x006e:
            int r2 = r2 + 1
            goto L_0x003d
        L_0x0071:
            r2 = r5
        L_0x0072:
            r8.a(r9, r1, r2)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.flexbox.FlexboxLayoutManager.b(androidx.recyclerview.widget.RecyclerView$Recycler, com.google.android.flexbox.FlexboxLayoutManager$b):void");
    }

    private boolean a(View view, int i) {
        return (a() || !this.h) ? this.p.getDecoratedEnd(view) <= i : this.p.getEnd() - this.p.getDecoratedStart(view) <= i;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:24:0x0071, code lost:
        r0 = r4;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void c(androidx.recyclerview.widget.RecyclerView.Recycler r8, com.google.android.flexbox.FlexboxLayoutManager.b r9) {
        /*
            r7 = this;
            int r0 = com.google.android.flexbox.FlexboxLayoutManager.b.d(r9)
            if (r0 >= 0) goto L_0x0007
            return
        L_0x0007:
            boolean r0 = com.google.android.flexbox.FlexboxLayoutManager.a
            if (r0 != 0) goto L_0x0018
            com.google.android.flexbox.c r0 = r7.k
            int[] r0 = r0.a
            if (r0 == 0) goto L_0x0012
            goto L_0x0018
        L_0x0012:
            java.lang.AssertionError r8 = new java.lang.AssertionError
            r8.<init>()
            throw r8
        L_0x0018:
            androidx.recyclerview.widget.OrientationHelper r0 = r7.p
            r0.getEnd()
            com.google.android.flexbox.FlexboxLayoutManager.b.d(r9)
            int r0 = r7.getChildCount()
            if (r0 != 0) goto L_0x0027
            return
        L_0x0027:
            int r1 = r0 + -1
            android.view.View r2 = r7.getChildAt(r1)
            com.google.android.flexbox.c r3 = r7.k
            int[] r3 = r3.a
            int r2 = r7.getPosition(r2)
            r2 = r3[r2]
            r3 = -1
            if (r2 != r3) goto L_0x003b
            return
        L_0x003b:
            java.util.List<com.google.android.flexbox.b> r3 = r7.j
            java.lang.Object r3 = r3.get(r2)
            com.google.android.flexbox.b r3 = (com.google.android.flexbox.b) r3
            r4 = r0
            r0 = r1
        L_0x0045:
            if (r0 < 0) goto L_0x0071
            android.view.View r5 = r7.getChildAt(r0)
            int r6 = com.google.android.flexbox.FlexboxLayoutManager.b.d(r9)
            boolean r6 = r7.b(r5, r6)
            if (r6 == 0) goto L_0x0071
            int r6 = r3.o
            int r5 = r7.getPosition(r5)
            if (r6 != r5) goto L_0x006e
            if (r2 > 0) goto L_0x0060
            goto L_0x0072
        L_0x0060:
            int r3 = com.google.android.flexbox.FlexboxLayoutManager.b.f(r9)
            int r2 = r2 + r3
            java.util.List<com.google.android.flexbox.b> r3 = r7.j
            java.lang.Object r3 = r3.get(r2)
            com.google.android.flexbox.b r3 = (com.google.android.flexbox.b) r3
            r4 = r0
        L_0x006e:
            int r0 = r0 + -1
            goto L_0x0045
        L_0x0071:
            r0 = r4
        L_0x0072:
            r7.a(r8, r0, r1)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.flexbox.FlexboxLayoutManager.c(androidx.recyclerview.widget.RecyclerView$Recycler, com.google.android.flexbox.FlexboxLayoutManager$b):void");
    }

    private boolean b(View view, int i) {
        return (a() || !this.h) ? this.p.getDecoratedStart(view) >= this.p.getEnd() - i : this.p.getDecoratedEnd(view) <= i;
    }

    private void a(RecyclerView.Recycler recycler, int i, int i2) {
        while (i2 >= i) {
            removeAndRecycleViewAt(i2, recycler);
            i2--;
        }
    }

    private int a(b bVar, b bVar2) {
        if (a()) {
            return b(bVar, bVar2);
        }
        return c(bVar, bVar2);
    }

    /* JADX WARNING: Removed duplicated region for block: B:44:0x00ec  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private int b(com.google.android.flexbox.b r22, com.google.android.flexbox.FlexboxLayoutManager.b r23) {
        /*
        // Method dump skipped, instructions count: 460
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.flexbox.FlexboxLayoutManager.b(com.google.android.flexbox.b, com.google.android.flexbox.FlexboxLayoutManager$b):int");
    }

    /* JADX WARNING: Removed duplicated region for block: B:44:0x00f4  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private int c(com.google.android.flexbox.b r26, com.google.android.flexbox.FlexboxLayoutManager.b r27) {
        /*
        // Method dump skipped, instructions count: 574
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.flexbox.FlexboxLayoutManager.c(com.google.android.flexbox.b, com.google.android.flexbox.FlexboxLayoutManager$b):int");
    }

    @Override // com.google.android.flexbox.a
    public boolean a() {
        int i = this.c;
        return i == 0 || i == 1;
    }

    private void a(a aVar, boolean z, boolean z2) {
        if (z2) {
            h();
        } else {
            this.n.b = false;
        }
        if (a() || !this.h) {
            this.n.a = this.p.getEndAfterPadding() - aVar.e;
        } else {
            this.n.a = aVar.e - getPaddingRight();
        }
        this.n.d = aVar.c;
        this.n.h = 1;
        this.n.i = 1;
        this.n.e = aVar.e;
        this.n.f = Integer.MIN_VALUE;
        this.n.c = aVar.d;
        if (z && this.j.size() > 1 && aVar.d >= 0 && aVar.d < this.j.size() - 1) {
            b.i(this.n);
            this.n.d += this.j.get(aVar.d).b();
        }
    }

    private void b(a aVar, boolean z, boolean z2) {
        if (z2) {
            h();
        } else {
            this.n.b = false;
        }
        if (a() || !this.h) {
            this.n.a = aVar.e - this.p.getStartAfterPadding();
        } else {
            this.n.a = (this.z.getWidth() - aVar.e) - this.p.getStartAfterPadding();
        }
        this.n.d = aVar.c;
        this.n.h = 1;
        this.n.i = -1;
        this.n.e = aVar.e;
        this.n.f = Integer.MIN_VALUE;
        this.n.c = aVar.d;
        if (z && aVar.d > 0 && this.j.size() > aVar.d) {
            b.j(this.n);
            this.n.d -= this.j.get(aVar.d).b();
        }
    }

    private void h() {
        int i;
        if (a()) {
            i = getHeightMode();
        } else {
            i = getWidthMode();
        }
        this.n.b = i == 0 || i == Integer.MIN_VALUE;
    }

    private void i() {
        if (this.p == null) {
            if (a()) {
                if (this.d == 0) {
                    this.p = OrientationHelper.createHorizontalHelper(this);
                    this.q = OrientationHelper.createVerticalHelper(this);
                    return;
                }
                this.p = OrientationHelper.createVerticalHelper(this);
                this.q = OrientationHelper.createHorizontalHelper(this);
            } else if (this.d == 0) {
                this.p = OrientationHelper.createVerticalHelper(this);
                this.q = OrientationHelper.createHorizontalHelper(this);
            } else {
                this.p = OrientationHelper.createHorizontalHelper(this);
                this.q = OrientationHelper.createVerticalHelper(this);
            }
        }
    }

    private void j() {
        if (this.n == null) {
            this.n = new b();
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void scrollToPosition(int i) {
        this.s = i;
        this.t = Integer.MIN_VALUE;
        SavedState savedState = this.r;
        if (savedState != null) {
            savedState.a();
        }
        requestLayout();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void smoothScrollToPosition(RecyclerView recyclerView, RecyclerView.State state, int i) {
        LinearSmoothScroller linearSmoothScroller = new LinearSmoothScroller(recyclerView.getContext());
        linearSmoothScroller.setTargetPosition(i);
        startSmoothScroll(linearSmoothScroller);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void onAttachedToWindow(RecyclerView recyclerView) {
        super.onAttachedToWindow(recyclerView);
        this.z = (View) recyclerView.getParent();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void onDetachedFromWindow(RecyclerView recyclerView, RecyclerView.Recycler recycler) {
        super.onDetachedFromWindow(recyclerView, recycler);
        if (this.w) {
            removeAndRecycleAllViews(recycler);
            recycler.clear();
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public boolean canScrollHorizontally() {
        if (this.d == 0) {
            return a();
        }
        if (a()) {
            int width = getWidth();
            View view = this.z;
            if (width <= (view != null ? view.getWidth() : 0)) {
                return false;
            }
        }
        return true;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public boolean canScrollVertically() {
        if (this.d == 0) {
            return !a();
        }
        if (a()) {
            return true;
        }
        int height = getHeight();
        View view = this.z;
        if (height > (view != null ? view.getHeight() : 0)) {
            return true;
        }
        return false;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public int scrollHorizontallyBy(int i, RecyclerView.Recycler recycler, RecyclerView.State state) {
        if (!a() || (this.d == 0 && a())) {
            int a2 = a(i, recycler, state);
            this.x.clear();
            return a2;
        }
        int l = l(i);
        this.o.f += l;
        this.q.offsetChildren(-l);
        return l;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public int scrollVerticallyBy(int i, RecyclerView.Recycler recycler, RecyclerView.State state) {
        if (a() || (this.d == 0 && !a())) {
            int a2 = a(i, recycler, state);
            this.x.clear();
            return a2;
        }
        int l = l(i);
        this.o.f += l;
        this.q.offsetChildren(-l);
        return l;
    }

    private int a(int i, RecyclerView.Recycler recycler, RecyclerView.State state) {
        if (getChildCount() == 0 || i == 0) {
            return 0;
        }
        i();
        int i2 = 1;
        this.n.j = true;
        boolean z = !a() && this.h;
        if (!z ? i <= 0 : i >= 0) {
            i2 = -1;
        }
        int abs = Math.abs(i);
        a(i2, abs);
        int a2 = this.n.f + a(recycler, state, this.n);
        if (a2 < 0) {
            return 0;
        }
        if (z) {
            if (abs > a2) {
                i = (-i2) * a2;
            }
        } else if (abs > a2) {
            i = i2 * a2;
        }
        this.p.offsetChildren(-i);
        this.n.g = i;
        return i;
    }

    private int l(int i) {
        int i2;
        boolean z = false;
        if (getChildCount() == 0 || i == 0) {
            return 0;
        }
        i();
        boolean a2 = a();
        View view = this.z;
        int width = a2 ? view.getWidth() : view.getHeight();
        int width2 = a2 ? getWidth() : getHeight();
        if (getLayoutDirection() == 1) {
            z = true;
        }
        if (z) {
            int abs = Math.abs(i);
            if (i < 0) {
                i2 = Math.min((width2 + this.o.f) - width, abs);
            } else if (this.o.f + i <= 0) {
                return i;
            } else {
                i2 = this.o.f;
            }
        } else if (i > 0) {
            return Math.min((width2 - this.o.f) - width, i);
        } else {
            if (this.o.f + i >= 0) {
                return i;
            }
            i2 = this.o.f;
        }
        return -i2;
    }

    private void a(int i, int i2) {
        if (a || this.k.a != null) {
            this.n.i = i;
            boolean a2 = a();
            int makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(getWidth(), getWidthMode());
            int makeMeasureSpec2 = View.MeasureSpec.makeMeasureSpec(getHeight(), getHeightMode());
            int i3 = 0;
            boolean z = !a2 && this.h;
            if (i == 1) {
                View childAt = getChildAt(getChildCount() - 1);
                this.n.e = this.p.getDecoratedEnd(childAt);
                int position = getPosition(childAt);
                View b2 = b(childAt, this.j.get(this.k.a[position]));
                this.n.h = 1;
                b bVar = this.n;
                bVar.d = position + bVar.h;
                if (this.k.a.length <= this.n.d) {
                    this.n.c = -1;
                } else {
                    this.n.c = this.k.a[this.n.d];
                }
                if (z) {
                    this.n.e = this.p.getDecoratedStart(b2);
                    this.n.f = (-this.p.getDecoratedStart(b2)) + this.p.getStartAfterPadding();
                    b bVar2 = this.n;
                    if (bVar2.f >= 0) {
                        i3 = this.n.f;
                    }
                    bVar2.f = i3;
                } else {
                    this.n.e = this.p.getDecoratedEnd(b2);
                    this.n.f = this.p.getDecoratedEnd(b2) - this.p.getEndAfterPadding();
                }
                if ((this.n.c == -1 || this.n.c > this.j.size() - 1) && this.n.d <= getFlexItemCount()) {
                    int i4 = i2 - this.n.f;
                    this.B.a();
                    if (i4 > 0) {
                        if (a2) {
                            this.k.a(this.B, makeMeasureSpec, makeMeasureSpec2, i4, this.n.d, this.j);
                        } else {
                            this.k.c(this.B, makeMeasureSpec, makeMeasureSpec2, i4, this.n.d, this.j);
                        }
                        this.k.a(makeMeasureSpec, makeMeasureSpec2, this.n.d);
                        this.k.a(this.n.d);
                    }
                }
            } else {
                View childAt2 = getChildAt(0);
                this.n.e = this.p.getDecoratedStart(childAt2);
                int position2 = getPosition(childAt2);
                View a3 = a(childAt2, this.j.get(this.k.a[position2]));
                this.n.h = 1;
                int i5 = this.k.a[position2];
                if (i5 == -1) {
                    i5 = 0;
                }
                if (i5 > 0) {
                    this.n.d = position2 - this.j.get(i5 - 1).b();
                } else {
                    this.n.d = -1;
                }
                this.n.c = i5 > 0 ? i5 - 1 : 0;
                if (z) {
                    this.n.e = this.p.getDecoratedEnd(a3);
                    this.n.f = this.p.getDecoratedEnd(a3) - this.p.getEndAfterPadding();
                    b bVar3 = this.n;
                    if (bVar3.f >= 0) {
                        i3 = this.n.f;
                    }
                    bVar3.f = i3;
                } else {
                    this.n.e = this.p.getDecoratedStart(a3);
                    this.n.f = (-this.p.getDecoratedStart(a3)) + this.p.getStartAfterPadding();
                }
            }
            b bVar4 = this.n;
            bVar4.a = i2 - bVar4.f;
            return;
        }
        throw new AssertionError();
    }

    private View a(View view, b bVar) {
        boolean a2 = a();
        int i = bVar.h;
        for (int i2 = 1; i2 < i; i2++) {
            View childAt = getChildAt(i2);
            if (!(childAt == null || childAt.getVisibility() == 8)) {
                if (!this.h || a2) {
                    if (this.p.getDecoratedStart(view) <= this.p.getDecoratedStart(childAt)) {
                    }
                } else if (this.p.getDecoratedEnd(view) >= this.p.getDecoratedEnd(childAt)) {
                }
                view = childAt;
            }
        }
        return view;
    }

    private View b(View view, b bVar) {
        boolean a2 = a();
        int childCount = (getChildCount() - bVar.h) - 1;
        for (int childCount2 = getChildCount() - 2; childCount2 > childCount; childCount2--) {
            View childAt = getChildAt(childCount2);
            if (!(childAt == null || childAt.getVisibility() == 8)) {
                if (!this.h || a2) {
                    if (this.p.getDecoratedEnd(view) >= this.p.getDecoratedEnd(childAt)) {
                    }
                } else if (this.p.getDecoratedStart(view) <= this.p.getDecoratedStart(childAt)) {
                }
                view = childAt;
            }
        }
        return view;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public int computeHorizontalScrollExtent(RecyclerView.State state) {
        return a(state);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public int computeVerticalScrollExtent(RecyclerView.State state) {
        return a(state);
    }

    private int a(RecyclerView.State state) {
        if (getChildCount() == 0) {
            return 0;
        }
        int itemCount = state.getItemCount();
        i();
        View j = j(itemCount);
        View k = k(itemCount);
        if (state.getItemCount() == 0 || j == null || k == null) {
            return 0;
        }
        return Math.min(this.p.getTotalSpace(), this.p.getDecoratedEnd(k) - this.p.getDecoratedStart(j));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public int computeHorizontalScrollOffset(RecyclerView.State state) {
        return b(state);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public int computeVerticalScrollOffset(RecyclerView.State state) {
        return b(state);
    }

    private int b(RecyclerView.State state) {
        if (getChildCount() == 0) {
            return 0;
        }
        int itemCount = state.getItemCount();
        View j = j(itemCount);
        View k = k(itemCount);
        if (!(state.getItemCount() == 0 || j == null || k == null)) {
            if (a || this.k.a != null) {
                int position = getPosition(j);
                int position2 = getPosition(k);
                int abs = Math.abs(this.p.getDecoratedEnd(k) - this.p.getDecoratedStart(j));
                int i = this.k.a[position];
                if (!(i == 0 || i == -1)) {
                    return Math.round((((float) i) * (((float) abs) / ((float) ((this.k.a[position2] - i) + 1)))) + ((float) (this.p.getStartAfterPadding() - this.p.getDecoratedStart(j))));
                }
            } else {
                throw new AssertionError();
            }
        }
        return 0;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public int computeHorizontalScrollRange(RecyclerView.State state) {
        return c(state);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public int computeVerticalScrollRange(RecyclerView.State state) {
        return c(state);
    }

    private int c(RecyclerView.State state) {
        if (getChildCount() == 0) {
            return 0;
        }
        int itemCount = state.getItemCount();
        View j = j(itemCount);
        View k = k(itemCount);
        if (state.getItemCount() == 0 || j == null || k == null) {
            return 0;
        }
        if (a || this.k.a != null) {
            int d = d();
            return (int) ((((float) Math.abs(this.p.getDecoratedEnd(k) - this.p.getDecoratedStart(j))) / ((float) ((e() - d) + 1))) * ((float) state.getItemCount()));
        }
        throw new AssertionError();
    }

    private boolean a(View view, int i, int i2, RecyclerView.LayoutParams layoutParams) {
        return view.isLayoutRequested() || !isMeasurementCacheEnabled() || !d(view.getWidth(), i, layoutParams.width) || !d(view.getHeight(), i2, layoutParams.height);
    }

    private static boolean d(int i, int i2, int i3) {
        int mode = View.MeasureSpec.getMode(i2);
        int size = View.MeasureSpec.getSize(i2);
        if (i3 > 0 && i != i3) {
            return false;
        }
        if (mode == Integer.MIN_VALUE) {
            return size >= i;
        }
        if (mode != 0) {
            return mode == 1073741824 && size == i;
        }
        return true;
    }

    private void k() {
        this.j.clear();
        this.o.a();
        this.o.f = 0;
    }

    private int b(View view) {
        return getDecoratedLeft(view) - ((RecyclerView.LayoutParams) view.getLayoutParams()).leftMargin;
    }

    private int c(View view) {
        return getDecoratedRight(view) + ((RecyclerView.LayoutParams) view.getLayoutParams()).rightMargin;
    }

    private int d(View view) {
        return getDecoratedTop(view) - ((RecyclerView.LayoutParams) view.getLayoutParams()).topMargin;
    }

    private int e(View view) {
        return getDecoratedBottom(view) + ((RecyclerView.LayoutParams) view.getLayoutParams()).bottomMargin;
    }

    private boolean a(View view, boolean z) {
        int paddingLeft = getPaddingLeft();
        int paddingTop = getPaddingTop();
        int width = getWidth() - getPaddingRight();
        int height = getHeight() - getPaddingBottom();
        int b2 = b(view);
        int d = d(view);
        int c = c(view);
        int e = e(view);
        return z ? (paddingLeft <= b2 && width >= c) && (paddingTop <= d && height >= e) : (b2 >= width || c >= paddingLeft) && (d >= height || e >= paddingTop);
    }

    public int d() {
        View a2 = a(0, getChildCount(), false);
        if (a2 == null) {
            return -1;
        }
        return getPosition(a2);
    }

    public int e() {
        View a2 = a(getChildCount() - 1, -1, false);
        if (a2 == null) {
            return -1;
        }
        return getPosition(a2);
    }

    private View a(int i, int i2, boolean z) {
        int i3 = i2 > i ? 1 : -1;
        while (i != i2) {
            View childAt = getChildAt(i);
            if (a(childAt, z)) {
                return childAt;
            }
            i += i3;
        }
        return null;
    }

    /* access modifiers changed from: package-private */
    public int g(int i) {
        if (a || this.k.a != null) {
            return this.k.a[i];
        }
        throw new AssertionError();
    }

    public static class LayoutParams extends RecyclerView.LayoutParams implements FlexItem {
        public static final Parcelable.Creator<LayoutParams> CREATOR = new AnonymousClass1();
        private float a = 0.0f;
        private float b = 1.0f;
        private int c = -1;
        private float d = -1.0f;
        private int e;
        private int f;
        private int g = 16777215;
        private int h = 16777215;
        private boolean i;

        @Override // com.google.android.flexbox.FlexItem
        public int c() {
            return 1;
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        @Override // com.google.android.flexbox.FlexItem
        public int a() {
            return this.width;
        }

        @Override // com.google.android.flexbox.FlexItem
        public int b() {
            return this.height;
        }

        @Override // com.google.android.flexbox.FlexItem
        public float d() {
            return this.a;
        }

        @Override // com.google.android.flexbox.FlexItem
        public float e() {
            return this.b;
        }

        @Override // com.google.android.flexbox.FlexItem
        public int f() {
            return this.c;
        }

        @Override // com.google.android.flexbox.FlexItem
        public int g() {
            return this.e;
        }

        @Override // com.google.android.flexbox.FlexItem
        public void a(int i) {
            this.e = i;
        }

        @Override // com.google.android.flexbox.FlexItem
        public int h() {
            return this.f;
        }

        @Override // com.google.android.flexbox.FlexItem
        public void b(int i) {
            this.f = i;
        }

        @Override // com.google.android.flexbox.FlexItem
        public int i() {
            return this.g;
        }

        @Override // com.google.android.flexbox.FlexItem
        public int j() {
            return this.h;
        }

        @Override // com.google.android.flexbox.FlexItem
        public boolean k() {
            return this.i;
        }

        @Override // com.google.android.flexbox.FlexItem
        public float l() {
            return this.d;
        }

        @Override // com.google.android.flexbox.FlexItem
        public int m() {
            return this.leftMargin;
        }

        @Override // com.google.android.flexbox.FlexItem
        public int n() {
            return this.topMargin;
        }

        @Override // com.google.android.flexbox.FlexItem
        public int o() {
            return this.rightMargin;
        }

        @Override // com.google.android.flexbox.FlexItem
        public int p() {
            return this.bottomMargin;
        }

        public LayoutParams(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
        }

        public LayoutParams(int i, int i2) {
            super(i, i2);
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeFloat(this.a);
            parcel.writeFloat(this.b);
            parcel.writeInt(this.c);
            parcel.writeFloat(this.d);
            parcel.writeInt(this.e);
            parcel.writeInt(this.f);
            parcel.writeInt(this.g);
            parcel.writeInt(this.h);
            parcel.writeByte(this.i ? (byte) 1 : 0);
            parcel.writeInt(this.bottomMargin);
            parcel.writeInt(this.leftMargin);
            parcel.writeInt(this.rightMargin);
            parcel.writeInt(this.topMargin);
            parcel.writeInt(this.height);
            parcel.writeInt(this.width);
        }

        protected LayoutParams(Parcel parcel) {
            super(-2, -2);
            this.a = parcel.readFloat();
            this.b = parcel.readFloat();
            this.c = parcel.readInt();
            this.d = parcel.readFloat();
            this.e = parcel.readInt();
            this.f = parcel.readInt();
            this.g = parcel.readInt();
            this.h = parcel.readInt();
            this.i = parcel.readByte() != 0;
            this.bottomMargin = parcel.readInt();
            this.leftMargin = parcel.readInt();
            this.rightMargin = parcel.readInt();
            this.topMargin = parcel.readInt();
            this.height = parcel.readInt();
            this.width = parcel.readInt();
        }

        /* renamed from: com.google.android.flexbox.FlexboxLayoutManager$LayoutParams$1  reason: invalid class name */
        static class AnonymousClass1 implements Parcelable.Creator<LayoutParams> {
            AnonymousClass1() {
            }

            /* renamed from: a */
            public LayoutParams createFromParcel(Parcel parcel) {
                return new LayoutParams(parcel);
            }

            /* renamed from: a */
            public LayoutParams[] newArray(int i) {
                return new LayoutParams[i];
            }
        }
    }

    /* access modifiers changed from: private */
    public class a {
        static final /* synthetic */ boolean a = (!FlexboxLayoutManager.class.desiredAssertionStatus());
        private int c;
        private int d;
        private int e;
        private int f;
        private boolean g;
        private boolean h;
        private boolean i;

        private a() {
            this.f = 0;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void a() {
            this.c = -1;
            this.d = -1;
            this.e = Integer.MIN_VALUE;
            boolean z = false;
            this.h = false;
            this.i = false;
            if (FlexboxLayoutManager.this.a()) {
                if (FlexboxLayoutManager.this.d == 0) {
                    if (FlexboxLayoutManager.this.c == 1) {
                        z = true;
                    }
                    this.g = z;
                    return;
                }
                if (FlexboxLayoutManager.this.d == 2) {
                    z = true;
                }
                this.g = z;
            } else if (FlexboxLayoutManager.this.d == 0) {
                if (FlexboxLayoutManager.this.c == 3) {
                    z = true;
                }
                this.g = z;
            } else {
                if (FlexboxLayoutManager.this.d == 2) {
                    z = true;
                }
                this.g = z;
            }
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void b() {
            int i;
            int i2;
            if (FlexboxLayoutManager.this.a() || !FlexboxLayoutManager.this.h) {
                if (this.g) {
                    i = FlexboxLayoutManager.this.p.getEndAfterPadding();
                } else {
                    i = FlexboxLayoutManager.this.p.getStartAfterPadding();
                }
                this.e = i;
                return;
            }
            if (this.g) {
                i2 = FlexboxLayoutManager.this.p.getEndAfterPadding();
            } else {
                i2 = FlexboxLayoutManager.this.getWidth() - FlexboxLayoutManager.this.p.getStartAfterPadding();
            }
            this.e = i2;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void a(View view) {
            OrientationHelper orientationHelper;
            if (FlexboxLayoutManager.this.d == 0) {
                orientationHelper = FlexboxLayoutManager.this.q;
            } else {
                orientationHelper = FlexboxLayoutManager.this.p;
            }
            if (FlexboxLayoutManager.this.a() || !FlexboxLayoutManager.this.h) {
                if (this.g) {
                    this.e = orientationHelper.getDecoratedEnd(view) + orientationHelper.getTotalSpaceChange();
                } else {
                    this.e = orientationHelper.getDecoratedStart(view);
                }
            } else if (this.g) {
                this.e = orientationHelper.getDecoratedStart(view) + orientationHelper.getTotalSpaceChange();
            } else {
                this.e = orientationHelper.getDecoratedEnd(view);
            }
            this.c = FlexboxLayoutManager.this.getPosition(view);
            int i = 0;
            this.i = false;
            if (a || FlexboxLayoutManager.this.k.a != null) {
                int[] iArr = FlexboxLayoutManager.this.k.a;
                int i2 = this.c;
                if (i2 == -1) {
                    i2 = 0;
                }
                int i3 = iArr[i2];
                if (i3 != -1) {
                    i = i3;
                }
                this.d = i;
                if (FlexboxLayoutManager.this.j.size() > this.d) {
                    this.c = ((b) FlexboxLayoutManager.this.j.get(this.d)).o;
                    return;
                }
                return;
            }
            throw new AssertionError();
        }

        public String toString() {
            return "AnchorInfo{mPosition=" + this.c + ", mFlexLinePosition=" + this.d + ", mCoordinate=" + this.e + ", mPerpendicularCoordinate=" + this.f + ", mLayoutFromEnd=" + this.g + ", mValid=" + this.h + ", mAssignedFromSavedState=" + this.i + '}';
        }
    }

    /* access modifiers changed from: private */
    public static class b {
        private int a;
        private boolean b;
        private int c;
        private int d;
        private int e;
        private int f;
        private int g;
        private int h;
        private int i;
        private boolean j;

        private b() {
            this.h = 1;
            this.i = 1;
        }

        static /* synthetic */ int i(b bVar) {
            int i = bVar.c;
            bVar.c = i + 1;
            return i;
        }

        static /* synthetic */ int j(b bVar) {
            int i = bVar.c;
            bVar.c = i - 1;
            return i;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private boolean a(RecyclerView.State state, List<b> list) {
            int i;
            int i2 = this.d;
            return i2 >= 0 && i2 < state.getItemCount() && (i = this.c) >= 0 && i < list.size();
        }

        public String toString() {
            return "LayoutState{mAvailable=" + this.a + ", mFlexLinePosition=" + this.c + ", mPosition=" + this.d + ", mOffset=" + this.e + ", mScrollingOffset=" + this.f + ", mLastScrollDelta=" + this.g + ", mItemDirection=" + this.h + ", mLayoutDirection=" + this.i + '}';
        }
    }

    /* access modifiers changed from: private */
    public static class SavedState implements Parcelable {
        public static final Parcelable.Creator<SavedState> CREATOR = new AnonymousClass1();
        private int a;
        private int b;

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeInt(this.a);
            parcel.writeInt(this.b);
        }

        SavedState() {
        }

        private SavedState(Parcel parcel) {
            this.a = parcel.readInt();
            this.b = parcel.readInt();
        }

        private SavedState(SavedState savedState) {
            this.a = savedState.a;
            this.b = savedState.b;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void a() {
            this.a = -1;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private boolean a(int i) {
            int i2 = this.a;
            return i2 >= 0 && i2 < i;
        }

        /* renamed from: com.google.android.flexbox.FlexboxLayoutManager$SavedState$1  reason: invalid class name */
        static class AnonymousClass1 implements Parcelable.Creator<SavedState> {
            AnonymousClass1() {
            }

            /* renamed from: a */
            public SavedState createFromParcel(Parcel parcel) {
                return new SavedState(parcel);
            }

            /* renamed from: a */
            public SavedState[] newArray(int i) {
                return new SavedState[i];
            }
        }

        public String toString() {
            return "SavedState{mAnchorPosition=" + this.a + ", mAnchorOffset=" + this.b + '}';
        }
    }
}
