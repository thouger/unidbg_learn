package com.sobot.chat.widget.horizontalgridpage;

import android.graphics.PointF;
import android.graphics.Rect;
import android.util.SparseArray;
import android.view.View;
import androidx.recyclerview.widget.RecyclerView;
import com.sobot.chat.utils.m;
import com.umeng.message.proguard.l;

public class PagerGridLayoutManager extends RecyclerView.LayoutManager implements RecyclerView.SmoothScroller.ScrollVectorProvider {
    private static final String a = PagerGridLayoutManager.class.getSimpleName();
    private int b;
    private int c = 0;
    private int d = 0;
    private int e;
    private int f;
    private int g;
    private SparseArray<Rect> h = new SparseArray<>();
    private int i = 0;
    private int j = 0;
    private int k = 0;
    private int l = 0;
    private int m;
    private int n;
    private int o = 0;
    private boolean p = true;
    private RecyclerView q;
    private boolean r = true;
    private int s = -1;
    private int t = -1;
    private a u = null;

    public interface a {
        void a(int i);

        void b(int i);
    }

    public PagerGridLayoutManager(int i, int i2, int i3) {
        this.b = i3;
        this.e = i;
        this.f = i2;
        this.g = this.e * this.f;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void onAttachedToWindow(RecyclerView recyclerView) {
        super.onAttachedToWindow(recyclerView);
        this.q = recyclerView;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
        m.d("Item onLayoutChildren");
        m.d("Item onLayoutChildren isPreLayout = " + state.isPreLayout());
        m.d("Item onLayoutChildren isMeasuring = " + state.isMeasuring());
        m.c("Item onLayoutChildren state = " + state);
        if (!state.isPreLayout() && state.didStructureChange()) {
            int i = 0;
            if (getItemCount() == 0) {
                removeAndRecycleAllViews(recycler);
                g(0);
                a(0, false);
                return;
            }
            g(l());
            a(m(), false);
            int itemCount = getItemCount() / this.g;
            if (getItemCount() % this.g != 0) {
                itemCount++;
            }
            if (canScrollHorizontally()) {
                this.m = (itemCount - 1) * j();
                this.n = 0;
                int i2 = this.c;
                int i3 = this.m;
                if (i2 > i3) {
                    this.c = i3;
                }
            } else {
                this.m = 0;
                this.n = (itemCount - 1) * k();
                int i4 = this.d;
                int i5 = this.n;
                if (i4 > i5) {
                    this.d = i5;
                }
            }
            m.d("count = " + getItemCount());
            if (this.i <= 0) {
                this.i = j() / this.f;
            }
            if (this.j <= 0) {
                this.j = k() / this.e;
            }
            this.k = j() - this.i;
            this.l = k() - this.j;
            for (int i6 = 0; i6 < this.g * 2; i6++) {
                d(i6);
            }
            if (this.c == 0 && this.d == 0) {
                while (i < this.g && i < getItemCount()) {
                    View viewForPosition = recycler.getViewForPosition(i);
                    addView(viewForPosition);
                    measureChildWithMargins(viewForPosition, this.k, this.l);
                    i++;
                }
            }
            a(recycler, state, true);
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void onLayoutCompleted(RecyclerView.State state) {
        super.onLayoutCompleted(state);
        if (!state.isPreLayout()) {
            g(l());
            a(m(), false);
        }
    }

    private void a(RecyclerView.Recycler recycler, RecyclerView.State state, boolean z) {
        if (!state.isPreLayout()) {
            m.d("mOffsetX = " + this.c);
            m.d("mOffsetY = " + this.d);
            Rect rect = new Rect(this.c - this.i, this.d - this.j, j() + this.c + this.i, k() + this.d + this.j);
            rect.intersect(0, 0, this.m + j(), this.n + k());
            m.c("displayRect = " + rect.toString());
            int m = m() * this.g;
            m.d("startPos = " + m);
            int i = m - (this.g * 2);
            if (i < 0) {
                i = 0;
            }
            int i2 = (this.g * 4) + i;
            if (i2 > getItemCount()) {
                i2 = getItemCount();
            }
            m.c("startPos = " + i);
            m.c("stopPos = " + i2);
            detachAndScrapAttachedViews(recycler);
            if (z) {
                while (i < i2) {
                    a(recycler, rect, i);
                    i++;
                }
            } else {
                for (int i3 = i2 - 1; i3 >= i; i3--) {
                    a(recycler, rect, i3);
                }
            }
            m.c("child count = " + getChildCount());
        }
    }

    private void a(RecyclerView.Recycler recycler, Rect rect, int i) {
        View viewForPosition = recycler.getViewForPosition(i);
        Rect d = d(i);
        if (!Rect.intersects(rect, d)) {
            removeAndRecycleView(viewForPosition, recycler);
            return;
        }
        addView(viewForPosition);
        measureChildWithMargins(viewForPosition, this.k, this.l);
        RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) viewForPosition.getLayoutParams();
        layoutDecorated(viewForPosition, (d.left - this.c) + layoutParams.leftMargin + getPaddingLeft(), (d.top - this.d) + layoutParams.topMargin + getPaddingTop(), ((d.right - this.c) - layoutParams.rightMargin) + getPaddingLeft(), ((d.bottom - this.d) - layoutParams.bottomMargin) + getPaddingTop());
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public int scrollHorizontallyBy(int i, RecyclerView.Recycler recycler, RecyclerView.State state) {
        int i2 = this.c;
        int i3 = i2 + i;
        int i4 = this.m;
        if (i3 > i4) {
            i = i4 - i2;
        } else if (i3 < 0) {
            i = 0 - i2;
        }
        this.c += i;
        a(m(), true);
        offsetChildrenHorizontal(-i);
        if (i > 0) {
            a(recycler, state, true);
        } else {
            a(recycler, state, false);
        }
        return i;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public int scrollVerticallyBy(int i, RecyclerView.Recycler recycler, RecyclerView.State state) {
        int i2 = this.d;
        int i3 = i2 + i;
        int i4 = this.n;
        if (i3 > i4) {
            i = i4 - i2;
        } else if (i3 < 0) {
            i = 0 - i2;
        }
        this.d += i;
        a(m(), true);
        offsetChildrenVertical(-i);
        if (i > 0) {
            a(recycler, state, true);
        } else {
            a(recycler, state, false);
        }
        return i;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void onScrollStateChanged(int i) {
        m.d("onScrollStateChanged = " + i);
        this.o = i;
        super.onScrollStateChanged(i);
        if (i == 0) {
            a(m(), false);
        }
    }

    private Rect d(int i) {
        int i2;
        Rect rect = (Rect) this.h.get(i);
        if (rect == null) {
            rect = new Rect();
            int i3 = i / this.g;
            int i4 = 0;
            if (canScrollHorizontally()) {
                i2 = (j() * i3) + 0;
            } else {
                i4 = (k() * i3) + 0;
                i2 = 0;
            }
            int i5 = i % this.g;
            int i6 = this.f;
            int i7 = i5 / i6;
            int i8 = i5 - (i6 * i7);
            int i9 = i2 + (this.i * i8);
            int i10 = i4 + (this.j * i7);
            m.d("pagePos = " + i5);
            m.d("\u884c = " + i7);
            m.d("\u5217 = " + i8);
            m.d("offsetX = " + i9);
            m.d("offsetY = " + i10);
            rect.left = i9;
            rect.top = i10;
            rect.right = i9 + this.i;
            rect.bottom = i10 + this.j;
            this.h.put(i, rect);
        }
        return rect;
    }

    private int j() {
        return (getWidth() - getPaddingLeft()) - getPaddingRight();
    }

    private int k() {
        return (getHeight() - getPaddingTop()) - getPaddingBottom();
    }

    private int l() {
        if (getItemCount() <= 0) {
            return 0;
        }
        int itemCount = getItemCount() / this.g;
        return getItemCount() % this.g != 0 ? itemCount + 1 : itemCount;
    }

    private int e(int i) {
        return i / this.g;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x002a, code lost:
        if ((r2 % r0) > (r0 / 2)) goto L_0x002c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:6:0x0017, code lost:
        if ((r2 % r0) > (r0 / 2)) goto L_0x002c;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private int m() {
        /*
            r3 = this;
            boolean r0 = r3.canScrollVertically()
            r1 = 0
            if (r0 == 0) goto L_0x001a
            int r0 = r3.k()
            int r2 = r3.d
            if (r2 <= 0) goto L_0x002e
            if (r0 > 0) goto L_0x0012
            goto L_0x002e
        L_0x0012:
            int r1 = r2 / r0
            int r2 = r2 % r0
            int r0 = r0 / 2
            if (r2 <= r0) goto L_0x002e
            goto L_0x002c
        L_0x001a:
            int r0 = r3.j()
            int r2 = r3.c
            if (r2 <= 0) goto L_0x002e
            if (r0 > 0) goto L_0x0025
            goto L_0x002e
        L_0x0025:
            int r1 = r2 / r0
            int r2 = r2 % r0
            int r0 = r0 / 2
            if (r2 <= r0) goto L_0x002e
        L_0x002c:
            int r1 = r1 + 1
        L_0x002e:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r2 = "getPageIndexByOffset pageIndex = "
            r0.append(r2)
            r0.append(r1)
            java.lang.String r0 = r0.toString()
            com.sobot.chat.utils.m.d(r0)
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.sobot.chat.widget.horizontalgridpage.PagerGridLayoutManager.m():int");
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public RecyclerView.LayoutParams generateDefaultLayoutParams() {
        return new RecyclerView.LayoutParams(-2, -2);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void onMeasure(RecyclerView.Recycler recycler, RecyclerView.State state, int i, int i2) {
        super.onMeasure(recycler, state, i, i2);
        int size = View.MeasureSpec.getSize(i);
        int mode = View.MeasureSpec.getMode(i);
        int size2 = View.MeasureSpec.getSize(i2);
        int mode2 = View.MeasureSpec.getMode(i2);
        if (mode != 1073741824 && size > 0) {
            mode = 1073741824;
        }
        if (mode2 != 1073741824 && size2 > 0) {
            mode2 = 1073741824;
        }
        setMeasuredDimension(View.MeasureSpec.makeMeasureSpec(size, mode), View.MeasureSpec.makeMeasureSpec(size2, mode2));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public boolean canScrollHorizontally() {
        return this.b == 1;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public boolean canScrollVertically() {
        return this.b == 0;
    }

    /* access modifiers changed from: package-private */
    public int a() {
        int i = this.t + 1;
        if (i >= l()) {
            i = l() - 1;
        }
        m.c("computeScrollVectorForPosition next = " + i);
        return i * this.g;
    }

    /* access modifiers changed from: package-private */
    public int b() {
        int i = this.t - 1;
        m.c("computeScrollVectorForPosition pre = " + i);
        if (i < 0) {
            i = 0;
        }
        m.c("computeScrollVectorForPosition pre = " + i);
        return i * this.g;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.SmoothScroller.ScrollVectorProvider
    public PointF computeScrollVectorForPosition(int i) {
        PointF pointF = new PointF();
        int[] a2 = a(i);
        pointF.x = (float) a2[0];
        pointF.y = (float) a2[1];
        return pointF;
    }

    /* access modifiers changed from: package-private */
    public int[] a(int i) {
        int[] f = f(i);
        return new int[]{f[0] - this.c, f[1] - this.d};
    }

    private int[] f(int i) {
        int[] iArr = new int[2];
        int e = e(i);
        if (canScrollHorizontally()) {
            iArr[0] = e * j();
            iArr[1] = 0;
        } else {
            iArr[0] = 0;
            iArr[1] = e * k();
        }
        return iArr;
    }

    public View c() {
        if (getFocusedChild() != null) {
            return getFocusedChild();
        }
        if (getChildCount() <= 0) {
            return null;
        }
        int m = m() * this.g;
        for (int i = 0; i < getChildCount(); i++) {
            if (getPosition(getChildAt(i)) == m) {
                return getChildAt(i);
            }
        }
        return getChildAt(0);
    }

    private void g(int i) {
        if (i >= 0) {
            a aVar = this.u;
            if (!(aVar == null || i == this.s)) {
                aVar.a(i);
            }
            this.s = i;
        }
    }

    private void a(int i, boolean z) {
        a aVar;
        m.c("setPageIndex = " + i + ":" + z);
        if (i != this.t) {
            if (i()) {
                this.t = i;
            } else if (!z) {
                this.t = i;
            }
            if ((!z || this.r) && i >= 0 && (aVar = this.u) != null) {
                aVar.b(i);
            }
        }
    }

    public int d() {
        return this.b;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void smoothScrollToPosition(RecyclerView recyclerView, RecyclerView.State state, int i) {
        b(e(i));
    }

    public void b(int i) {
        if (i < 0 || i >= this.s) {
            m.c("pageIndex is outOfIndex, must in [0, " + this.s + ").");
        } else if (this.q == null) {
            m.c("RecyclerView Not Found!");
        } else {
            int m = m();
            if (Math.abs(i - m) > 3) {
                if (i > m) {
                    c(i - 3);
                } else if (i < m) {
                    c(i + 3);
                }
            }
            PagerGridSmoothScroller pagerGridSmoothScroller = new PagerGridSmoothScroller(this.q);
            pagerGridSmoothScroller.setTargetPosition(i * this.g);
            startSmoothScroll(pagerGridSmoothScroller);
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void scrollToPosition(int i) {
        c(e(i));
    }

    public void e() {
        c(m() - 1);
    }

    public boolean f() {
        m.d("getPageIndexByOffset = " + m() + "   mLastPageCount=" + this.s + l.t);
        return m() == 0;
    }

    public void g() {
        c(m() + 1);
    }

    public boolean h() {
        m.d("getPageIndexByOffset = " + (m() + 1) + "   mLastPageCount=" + this.s + l.t);
        if (m() + 1 == this.s) {
            return true;
        }
        return false;
    }

    public void c(int i) {
        int i2;
        int i3;
        if (i < 0 || i >= this.s) {
            m.c("pageIndex = " + i + " is out of bounds, mast in [0, " + this.s + l.t);
        } else if (this.q == null) {
            m.c("RecyclerView Not Found!");
        } else {
            if (canScrollVertically()) {
                i2 = (k() * i) - this.d;
                i3 = 0;
            } else {
                i3 = (j() * i) - this.c;
                i2 = 0;
            }
            m.c("mTargetOffsetXBy = " + i3);
            m.c("mTargetOffsetYBy = " + i2);
            this.q.scrollBy(i3, i2);
            a(i, false);
        }
    }

    public boolean i() {
        return this.p;
    }

    public void a(boolean z) {
        this.p = z;
    }

    public void a(a aVar) {
        this.u = aVar;
    }
}
