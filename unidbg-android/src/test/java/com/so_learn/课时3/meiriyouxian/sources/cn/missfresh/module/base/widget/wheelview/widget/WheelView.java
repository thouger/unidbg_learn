package cn.missfresh.module.base.widget.wheelview.widget;

import android.content.Context;
import android.database.DataSetObserver;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Interpolator;
import android.widget.LinearLayout;
import cn.missfresh.module.base.R;
import cn.missfresh.module.base.widget.wheelview.a.c;
import cn.missfresh.module.base.widget.wheelview.widget.f;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import java.util.LinkedList;
import java.util.List;

public class WheelView extends View {
    private static final int[] c = {-285212673, -352321537, 872415231};
    boolean a = false;
    f.a b = new AnonymousClass1();
    private int d = 0;
    private int e = 5;
    private int f = 0;
    private Drawable g;
    private GradientDrawable h;
    private GradientDrawable i;
    private f j;
    private boolean k;
    private int l;
    private LinearLayout m;
    private int n;
    private c o;
    private e p = new e(this);
    private List<b> q = new LinkedList();
    private List<d> r = new LinkedList();
    private List<c> s = new LinkedList();
    private DataSetObserver t = new AnonymousClass2();

    static /* synthetic */ void a(WheelView wheelView, int i) {
        AppMethodBeat.i(24461, false);
        wheelView.b(i);
        AppMethodBeat.o(24461);
    }

    /* renamed from: cn.missfresh.module.base.widget.wheelview.widget.WheelView$1  reason: invalid class name */
    class AnonymousClass1 implements f.a {
        AnonymousClass1() {
        }

        @Override // cn.missfresh.module.base.widget.wheelview.widget.f.a
        public void a() {
            AppMethodBeat.i(24411, false);
            WheelView.this.k = true;
            WheelView.this.a();
            AppMethodBeat.o(24411);
        }

        @Override // cn.missfresh.module.base.widget.wheelview.widget.f.a
        public void a(int i) {
            AppMethodBeat.i(24412, false);
            WheelView.a(WheelView.this, i);
            int height = WheelView.this.getHeight();
            if (WheelView.this.l > height) {
                WheelView.this.l = height;
                WheelView.this.j.a();
            } else {
                int i2 = -height;
                if (WheelView.this.l < i2) {
                    WheelView.this.l = i2;
                    WheelView.this.j.a();
                }
            }
            AppMethodBeat.o(24412);
        }

        @Override // cn.missfresh.module.base.widget.wheelview.widget.f.a
        public void b() {
            AppMethodBeat.i(24413, false);
            if (WheelView.this.k) {
                WheelView.this.b();
                WheelView.this.k = false;
            }
            WheelView.this.l = 0;
            WheelView.this.invalidate();
            AppMethodBeat.o(24413);
        }

        @Override // cn.missfresh.module.base.widget.wheelview.widget.f.a
        public void c() {
            AppMethodBeat.i(24414, false);
            if (Math.abs(WheelView.this.l) > 1) {
                WheelView.this.j.a(WheelView.this.l, 0);
            }
            AppMethodBeat.o(24414);
        }
    }

    /* renamed from: cn.missfresh.module.base.widget.wheelview.widget.WheelView$2  reason: invalid class name */
    class AnonymousClass2 extends DataSetObserver {
        AnonymousClass2() {
        }

        @Override // android.database.DataSetObserver
        public void onChanged() {
            AppMethodBeat.i(24415, false);
            WheelView.this.a(false);
            AppMethodBeat.o(24415);
        }

        @Override // android.database.DataSetObserver
        public void onInvalidated() {
            AppMethodBeat.i(24416, false);
            WheelView.this.a(true);
            AppMethodBeat.o(24416);
        }
    }

    public WheelView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        AppMethodBeat.i(24417, false);
        a(context);
        AppMethodBeat.o(24417);
    }

    public WheelView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        AppMethodBeat.i(24418, false);
        a(context);
        AppMethodBeat.o(24418);
    }

    public WheelView(Context context) {
        super(context);
        AppMethodBeat.i(24419, false);
        a(context);
        AppMethodBeat.o(24419);
    }

    private void a(Context context) {
        AppMethodBeat.i(24420, false);
        this.j = new f(getContext(), this.b);
        AppMethodBeat.o(24420);
    }

    public void setInterpolator(Interpolator interpolator) {
        AppMethodBeat.i(24421, false);
        this.j.a(interpolator);
        AppMethodBeat.o(24421);
    }

    public int getVisibleItems() {
        return this.e;
    }

    public void setVisibleItems(int i) {
        this.e = i;
    }

    public c getViewAdapter() {
        return this.o;
    }

    public void setViewAdapter(c cVar) {
        AppMethodBeat.i(24422, false);
        c cVar2 = this.o;
        if (cVar2 != null) {
            cVar2.b(this.t);
        }
        this.o = cVar;
        c cVar3 = this.o;
        if (cVar3 != null) {
            cVar3.a(this.t);
        }
        a(true);
        AppMethodBeat.o(24422);
    }

    public void a(b bVar) {
        AppMethodBeat.i(24423, false);
        this.q.add(bVar);
        AppMethodBeat.o(24423);
    }

    /* access modifiers changed from: protected */
    public void a(int i, int i2) {
        AppMethodBeat.i(24425, false);
        for (b bVar : this.q) {
            bVar.a(this, i, i2);
        }
        AppMethodBeat.o(24425);
    }

    public void a(d dVar) {
        AppMethodBeat.i(24426, false);
        this.r.add(dVar);
        AppMethodBeat.o(24426);
    }

    /* access modifiers changed from: protected */
    public void a() {
        AppMethodBeat.i(24428, false);
        for (d dVar : this.r) {
            dVar.a(this);
        }
        AppMethodBeat.o(24428);
    }

    /* access modifiers changed from: protected */
    public void b() {
        AppMethodBeat.i(24429, false);
        for (d dVar : this.r) {
            dVar.b(this);
        }
        AppMethodBeat.o(24429);
    }

    /* access modifiers changed from: protected */
    public void a(int i) {
        AppMethodBeat.i(24432, false);
        for (c cVar : this.s) {
            cVar.a(this, i);
        }
        AppMethodBeat.o(24432);
    }

    public int getCurrentItem() {
        return this.d;
    }

    public void setCurrentItem(int i) {
        AppMethodBeat.i(24433, false);
        a(i, false);
        AppMethodBeat.o(24433);
    }

    public void a(int i, boolean z) {
        int min;
        AppMethodBeat.i(24434, false);
        c cVar = this.o;
        if (cVar == null || cVar.b() == 0) {
            AppMethodBeat.o(24434);
            return;
        }
        int b = this.o.b();
        if (i < 0 || i >= b) {
            if (this.a) {
                while (i < 0) {
                    i += b;
                }
                i %= b;
            } else {
                AppMethodBeat.o(24434);
                return;
            }
        }
        int i2 = this.d;
        if (i != i2) {
            if (z) {
                int i3 = i - i2;
                if (this.a && (min = (b + Math.min(i, i2)) - Math.max(i, this.d)) < Math.abs(i3)) {
                    i3 = i3 < 0 ? min : -min;
                }
                b(i3, 0);
            } else {
                this.l = 0;
                this.d = i;
                a(i2, this.d);
                invalidate();
            }
        }
        AppMethodBeat.o(24434);
    }

    public boolean c() {
        return this.a;
    }

    public void setCyclic(boolean z) {
        AppMethodBeat.i(24435, false);
        this.a = z;
        a(false);
        AppMethodBeat.o(24435);
    }

    public void a(boolean z) {
        AppMethodBeat.i(24436, false);
        if (z) {
            this.p.c();
            LinearLayout linearLayout = this.m;
            if (linearLayout != null) {
                linearLayout.removeAllViews();
            }
            this.l = 0;
        } else {
            LinearLayout linearLayout2 = this.m;
            if (linearLayout2 != null) {
                this.p.a(linearLayout2, this.n, new a());
            }
        }
        invalidate();
        AppMethodBeat.o(24436);
    }

    private void d() {
        AppMethodBeat.i(24437, false);
        if (this.g == null) {
            this.g = getContext().getResources().getDrawable(R.drawable.shape_wheel_val);
        }
        if (this.h == null) {
            this.h = new GradientDrawable(GradientDrawable.Orientation.TOP_BOTTOM, c);
        }
        if (this.i == null) {
            this.i = new GradientDrawable(GradientDrawable.Orientation.BOTTOM_TOP, c);
        }
        setBackgroundResource(R.drawable.shape_wheel_bg);
        AppMethodBeat.o(24437);
    }

    private int a(LinearLayout linearLayout) {
        AppMethodBeat.i(24438, false);
        if (!(linearLayout == null || linearLayout.getChildAt(0) == null)) {
            this.f = linearLayout.getChildAt(0).getMeasuredHeight();
        }
        int i = this.f;
        int max = Math.max((this.e * i) - ((i * 10) / 50), getSuggestedMinimumHeight());
        AppMethodBeat.o(24438);
        return max;
    }

    private int getItemHeight() {
        AppMethodBeat.i(24439, false);
        int i = this.f;
        if (i != 0) {
            AppMethodBeat.o(24439);
            return i;
        }
        LinearLayout linearLayout = this.m;
        if (linearLayout == null || linearLayout.getChildAt(0) == null) {
            int height = getHeight() / this.e;
            AppMethodBeat.o(24439);
            return height;
        }
        this.f = this.m.getChildAt(0).getHeight();
        int i2 = this.f;
        AppMethodBeat.o(24439);
        return i2;
    }

    private int c(int i, int i2) {
        AppMethodBeat.i(24440, false);
        d();
        this.m.setLayoutParams(new ViewGroup.LayoutParams(-2, -2));
        this.m.measure(View.MeasureSpec.makeMeasureSpec(i, 0), View.MeasureSpec.makeMeasureSpec(0, 0));
        int measuredWidth = this.m.getMeasuredWidth();
        if (i2 != 1073741824) {
            int max = Math.max(measuredWidth + 20, getSuggestedMinimumWidth());
            if (i2 != Integer.MIN_VALUE || i >= max) {
                i = max;
            }
        }
        this.m.measure(View.MeasureSpec.makeMeasureSpec(i - 20, 1073741824), View.MeasureSpec.makeMeasureSpec(0, 0));
        AppMethodBeat.o(24440);
        return i;
    }

    /* access modifiers changed from: protected */
    @Override // android.view.View
    public void onMeasure(int i, int i2) {
        AppMethodBeat.i(24441, false);
        int mode = View.MeasureSpec.getMode(i);
        int mode2 = View.MeasureSpec.getMode(i2);
        int size = View.MeasureSpec.getSize(i);
        int size2 = View.MeasureSpec.getSize(i2);
        h();
        int c2 = c(size, mode);
        if (mode2 != 1073741824) {
            int a = a(this.m);
            size2 = mode2 == Integer.MIN_VALUE ? Math.min(a, size2) : a;
        }
        setMeasuredDimension(c2, size2);
        AppMethodBeat.o(24441);
    }

    /* access modifiers changed from: protected */
    @Override // android.view.View
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        AppMethodBeat.i(24442, false);
        d(i3 - i, i4 - i2);
        AppMethodBeat.o(24442);
    }

    private void d(int i, int i2) {
        AppMethodBeat.i(24443, false);
        this.m.layout(0, 0, i - 20, i2);
        AppMethodBeat.o(24443);
    }

    /* access modifiers changed from: protected */
    @Override // android.view.View
    public void onDraw(Canvas canvas) {
        AppMethodBeat.i(24444, false);
        super.onDraw(canvas);
        c cVar = this.o;
        if (cVar != null && cVar.b() > 0) {
            f();
            b(canvas);
            c(canvas);
        }
        a(canvas);
        AppMethodBeat.o(24444);
    }

    private void a(Canvas canvas) {
        AppMethodBeat.i(24445, false);
        int itemHeight = (int) (((double) getItemHeight()) * 2.0d);
        this.h.setBounds(0, 0, getWidth(), itemHeight);
        this.h.draw(canvas);
        this.i.setBounds(0, getHeight() - itemHeight, getWidth(), getHeight());
        this.i.draw(canvas);
        AppMethodBeat.o(24445);
    }

    private void b(Canvas canvas) {
        AppMethodBeat.i(24446, false);
        canvas.save();
        canvas.translate(10.0f, (float) ((-(((this.d - this.n) * getItemHeight()) + ((getItemHeight() - getHeight()) / 2))) + this.l));
        this.m.draw(canvas);
        canvas.restore();
        AppMethodBeat.o(24446);
    }

    private void c(Canvas canvas) {
        AppMethodBeat.i(24447, false);
        int height = getHeight() / 2;
        int itemHeight = (int) (((double) (((float) getItemHeight()) / 2.0f)) * 1.2d);
        this.g.setBounds(0, height - itemHeight, getWidth(), height + itemHeight);
        this.g.draw(canvas);
        AppMethodBeat.o(24447);
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        int i;
        AppMethodBeat.i(24448, false);
        if (!isEnabled() || getViewAdapter() == null) {
            AppMethodBeat.o(24448);
            return true;
        }
        int action = motionEvent.getAction();
        if (action != 1) {
            if (action == 2 && getParent() != null) {
                getParent().requestDisallowInterceptTouchEvent(true);
            }
        } else if (!this.k) {
            int y = ((int) motionEvent.getY()) - (getHeight() / 2);
            if (y > 0) {
                i = y + (getItemHeight() / 2);
            } else {
                i = y - (getItemHeight() / 2);
            }
            int itemHeight = i / getItemHeight();
            if (itemHeight != 0 && c(this.d + itemHeight)) {
                a(this.d + itemHeight);
            }
        }
        boolean a = this.j.a(motionEvent);
        AppMethodBeat.o(24448);
        return a;
    }

    private void b(int i) {
        AppMethodBeat.i(24449, false);
        this.l += i;
        int itemHeight = getItemHeight();
        int i2 = this.l / itemHeight;
        int i3 = this.d - i2;
        c cVar = this.o;
        int b = cVar == null ? 0 : cVar.b();
        int i4 = this.l % itemHeight;
        if (Math.abs(i4) <= itemHeight / 2) {
            i4 = 0;
        }
        if (this.a && b > 0) {
            if (i4 > 0) {
                i3--;
                i2++;
            } else if (i4 < 0) {
                i3++;
                i2--;
            }
            while (i3 < 0) {
                i3 += b;
            }
            i3 %= b;
        } else if (i3 < 0) {
            i2 = this.d;
            i3 = 0;
        } else if (i3 >= b) {
            i2 = (this.d - b) + 1;
            i3 = b - 1;
        } else if (i3 > 0 && i4 > 0) {
            i3--;
            i2++;
        } else if (i3 < b - 1 && i4 < 0) {
            i3++;
            i2--;
        }
        int i5 = this.l;
        if (i3 != this.d) {
            a(i3, false);
        } else {
            invalidate();
        }
        this.l = i5 - (i2 * itemHeight);
        if (this.l > getHeight()) {
            this.l = (this.l % getHeight()) + getHeight();
        }
        AppMethodBeat.o(24449);
    }

    public void b(int i, int i2) {
        AppMethodBeat.i(24450, false);
        this.j.a((i * getItemHeight()) - this.l, i2);
        AppMethodBeat.o(24450);
    }

    private a getItemsRange() {
        AppMethodBeat.i(24451, false);
        if (getItemHeight() == 0) {
            AppMethodBeat.o(24451);
            return null;
        }
        int i = this.d;
        int i2 = 1;
        while (getItemHeight() * i2 < getHeight()) {
            i--;
            i2 += 2;
        }
        int i3 = this.l;
        if (i3 != 0) {
            if (i3 > 0) {
                i--;
            }
            int itemHeight = this.l / getItemHeight();
            i -= itemHeight;
            i2 = (int) (((double) (i2 + 1)) + Math.asin((double) itemHeight));
        }
        a aVar = new a(i, i2);
        AppMethodBeat.o(24451);
        return aVar;
    }

    private boolean e() {
        boolean z;
        AppMethodBeat.i(24452, false);
        a itemsRange = getItemsRange();
        if (itemsRange == null) {
            AppMethodBeat.o(24452);
            return false;
        }
        LinearLayout linearLayout = this.m;
        if (linearLayout != null) {
            int a = this.p.a(linearLayout, this.n, itemsRange);
            z = this.n != a;
            this.n = a;
        } else {
            g();
            z = true;
        }
        if (!z) {
            z = (this.n == itemsRange.a() && this.m.getChildCount() == itemsRange.c()) ? false : true;
        }
        if (this.n <= itemsRange.a() || this.n > itemsRange.b()) {
            this.n = itemsRange.a();
        } else {
            int i = this.n - 1;
            while (i >= itemsRange.a() && b(i, true)) {
                this.n = i;
                i--;
            }
        }
        int i2 = this.n;
        for (int childCount = this.m.getChildCount(); childCount < itemsRange.c(); childCount++) {
            if (!b(this.n + childCount, false) && this.m.getChildCount() == 0) {
                i2++;
            }
        }
        this.n = i2;
        AppMethodBeat.o(24452);
        return z;
    }

    private void f() {
        AppMethodBeat.i(24453, false);
        if (e()) {
            c(getWidth(), 1073741824);
            d(getWidth(), getHeight());
        }
        AppMethodBeat.o(24453);
    }

    private void g() {
        AppMethodBeat.i(24454, false);
        if (this.m == null) {
            this.m = new LinearLayout(getContext());
            this.m.setOrientation(1);
        }
        AppMethodBeat.o(24454);
    }

    private void h() {
        AppMethodBeat.i(24455, false);
        LinearLayout linearLayout = this.m;
        if (linearLayout != null) {
            this.p.a(linearLayout, this.n, new a());
        } else {
            g();
        }
        int i = this.e / 2;
        for (int i2 = this.d + i; i2 >= this.d - i; i2--) {
            if (b(i2, true)) {
                this.n = i2;
            }
        }
        AppMethodBeat.o(24455);
    }

    private boolean b(int i, boolean z) {
        AppMethodBeat.i(24456, false);
        View d = d(i);
        if (d != null) {
            if (z) {
                this.m.addView(d, 0);
            } else {
                this.m.addView(d);
            }
            AppMethodBeat.o(24456);
            return true;
        }
        AppMethodBeat.o(24456);
        return false;
    }

    private boolean c(int i) {
        boolean z = false;
        AppMethodBeat.i(24457, false);
        c cVar = this.o;
        if (cVar != null && cVar.b() > 0 && (this.a || (i >= 0 && i < this.o.b()))) {
            z = true;
        }
        AppMethodBeat.o(24457);
        return z;
    }

    private View d(int i) {
        AppMethodBeat.i(24459, false);
        c cVar = this.o;
        if (cVar == null || cVar.b() == 0) {
            AppMethodBeat.o(24459);
            return null;
        }
        int b = this.o.b();
        if (!c(i)) {
            View a = this.o.a(this.p.b(), this.m);
            AppMethodBeat.o(24459);
            return a;
        }
        while (i < 0) {
            i += b;
        }
        View a2 = this.o.a(i % b, this.p.a(), this.m);
        AppMethodBeat.o(24459);
        return a2;
    }
}
