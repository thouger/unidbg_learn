package cn.missfresh.module.base.support.superindicatorlibray;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.content.Context;
import android.content.res.TypedArray;
import android.database.DataSetObserver;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Interpolator;
import android.widget.LinearLayout;
import androidx.viewpager.widget.ViewPager;
import cn.missfresh.module.base.R;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;

public class CircleIndicator extends LinearLayout {
    private ViewPager a;
    private int b = -1;
    private int c = -1;
    private int d = -1;
    private int e = R.animator.scale_with_alpha;
    private int f = 0;
    private int g = R.drawable.white_radius;
    private int h = R.drawable.white_radius;
    private Animator i;
    private Animator j;
    private Animator k;
    private Animator l;
    private int m = -1;
    private final ViewPager.OnPageChangeListener n = new AnonymousClass1();
    private DataSetObserver o = new AnonymousClass2();

    static /* synthetic */ void g(CircleIndicator circleIndicator) {
        AppMethodBeat.i(22586, false);
        circleIndicator.a();
        AppMethodBeat.o(22586);
    }

    public CircleIndicator(Context context) {
        super(context);
        AppMethodBeat.i(22567, false);
        a(context, (AttributeSet) null);
        AppMethodBeat.o(22567);
    }

    public CircleIndicator(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        AppMethodBeat.i(22568, false);
        a(context, attributeSet);
        AppMethodBeat.o(22568);
    }

    public CircleIndicator(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        AppMethodBeat.i(22569, false);
        a(context, attributeSet);
        AppMethodBeat.o(22569);
    }

    public CircleIndicator(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        AppMethodBeat.i(22570, false);
        a(context, attributeSet);
        AppMethodBeat.o(22570);
    }

    private void a(Context context, AttributeSet attributeSet) {
        AppMethodBeat.i(22571, false);
        b(context, attributeSet);
        a(context);
        AppMethodBeat.o(22571);
    }

    private void b(Context context, AttributeSet attributeSet) {
        int i = 0;
        AppMethodBeat.i(22572, false);
        if (attributeSet == null) {
            AppMethodBeat.o(22572);
            return;
        }
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.CircleIndicator);
        this.c = obtainStyledAttributes.getDimensionPixelSize(R.styleable.CircleIndicator_ci_width, -1);
        this.d = obtainStyledAttributes.getDimensionPixelSize(R.styleable.CircleIndicator_ci_height, -1);
        this.b = obtainStyledAttributes.getDimensionPixelSize(R.styleable.CircleIndicator_ci_margin, -1);
        this.e = obtainStyledAttributes.getResourceId(R.styleable.CircleIndicator_ci_animator, R.animator.scale_with_alpha);
        this.f = obtainStyledAttributes.getResourceId(R.styleable.CircleIndicator_ci_animator_reverse, 0);
        this.g = obtainStyledAttributes.getResourceId(R.styleable.CircleIndicator_ci_drawable, R.drawable.white_radius);
        this.h = obtainStyledAttributes.getResourceId(R.styleable.CircleIndicator_ci_drawable_unselected, this.g);
        if (obtainStyledAttributes.getInt(R.styleable.CircleIndicator_ci_orientation, -1) == 1) {
            i = 1;
        }
        setOrientation(i);
        int i2 = obtainStyledAttributes.getInt(R.styleable.CircleIndicator_ci_gravity, -1);
        if (i2 < 0) {
            i2 = 17;
        }
        setGravity(i2);
        obtainStyledAttributes.recycle();
        AppMethodBeat.o(22572);
    }

    private void a(Context context) {
        AppMethodBeat.i(22575, false);
        int i = this.c;
        if (i < 0) {
            i = a(5.0f);
        }
        this.c = i;
        int i2 = this.d;
        if (i2 < 0) {
            i2 = a(5.0f);
        }
        this.d = i2;
        int i3 = this.b;
        if (i3 < 0) {
            i3 = a(5.0f);
        }
        this.b = i3;
        int i4 = this.e;
        if (i4 == 0) {
            i4 = R.animator.scale_with_alpha;
        }
        this.e = i4;
        this.i = b(context);
        this.k = b(context);
        this.k.setDuration(0);
        this.j = c(context);
        this.l = c(context);
        this.l.setDuration(0);
        int i5 = this.g;
        if (i5 == 0) {
            i5 = R.drawable.white_radius;
        }
        this.g = i5;
        int i6 = this.h;
        if (i6 == 0) {
            i6 = this.g;
        }
        this.h = i6;
        AppMethodBeat.o(22575);
    }

    private Animator b(Context context) {
        AppMethodBeat.i(22576, false);
        Animator loadAnimator = AnimatorInflater.loadAnimator(context, this.e);
        AppMethodBeat.o(22576);
        return loadAnimator;
    }

    private Animator c(Context context) {
        Animator animator;
        AppMethodBeat.i(22577, false);
        int i = this.f;
        if (i == 0) {
            animator = AnimatorInflater.loadAnimator(context, this.e);
            animator.setInterpolator(new a(this, null));
        } else {
            animator = AnimatorInflater.loadAnimator(context, i);
        }
        AppMethodBeat.o(22577);
        return animator;
    }

    public void setViewPager(ViewPager viewPager) {
        AppMethodBeat.i(22578, false);
        this.a = viewPager;
        ViewPager viewPager2 = this.a;
        if (!(viewPager2 == null || viewPager2.getAdapter() == null)) {
            this.m = -1;
            a();
            this.a.removeOnPageChangeListener(this.n);
            this.a.addOnPageChangeListener(this.n);
            this.n.onPageSelected(this.a.getCurrentItem());
        }
        AppMethodBeat.o(22578);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: cn.missfresh.module.base.support.superindicatorlibray.CircleIndicator$1  reason: invalid class name */
    public class AnonymousClass1 implements ViewPager.OnPageChangeListener {
        @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
        public void onPageScrollStateChanged(int i) {
        }

        @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
        public void onPageScrolled(int i, float f, int i2) {
        }

        AnonymousClass1() {
        }

        @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
        public void onPageSelected(int i) {
            CircleIndicator circleIndicator;
            View childAt;
            AppMethodBeat.i(22564, false);
            if (CircleIndicator.this.a.getAdapter() == null || CircleIndicator.this.a.getAdapter().getCount() <= 0) {
                AppMethodBeat.o(22564);
                return;
            }
            if (CircleIndicator.this.j.isRunning()) {
                CircleIndicator.this.j.end();
                CircleIndicator.this.j.cancel();
            }
            if (CircleIndicator.this.i.isRunning()) {
                CircleIndicator.this.i.end();
                CircleIndicator.this.i.cancel();
            }
            if (CircleIndicator.this.m >= 0 && (childAt = (circleIndicator = CircleIndicator.this).getChildAt(circleIndicator.m)) != null) {
                childAt.setBackgroundResource(CircleIndicator.this.h);
                CircleIndicator.this.j.setTarget(childAt);
                CircleIndicator.this.j.start();
            }
            View childAt2 = CircleIndicator.this.getChildAt(i);
            if (childAt2 != null) {
                childAt2.setBackgroundResource(CircleIndicator.this.g);
                CircleIndicator.this.i.setTarget(childAt2);
                CircleIndicator.this.i.start();
            }
            CircleIndicator.this.m = i;
            AppMethodBeat.o(22564);
        }
    }

    public DataSetObserver getDataSetObserver() {
        return this.o;
    }

    /* renamed from: cn.missfresh.module.base.support.superindicatorlibray.CircleIndicator$2  reason: invalid class name */
    class AnonymousClass2 extends DataSetObserver {
        AnonymousClass2() {
        }

        @Override // android.database.DataSetObserver
        public void onChanged() {
            AppMethodBeat.i(22565, false);
            super.onChanged();
            if (CircleIndicator.this.a == null) {
                AppMethodBeat.o(22565);
                return;
            }
            int count = CircleIndicator.this.a.getAdapter().getCount();
            if (count == CircleIndicator.this.getChildCount()) {
                AppMethodBeat.o(22565);
                return;
            }
            if (CircleIndicator.this.m < count) {
                CircleIndicator circleIndicator = CircleIndicator.this;
                circleIndicator.m = circleIndicator.a.getCurrentItem();
            } else {
                CircleIndicator.this.m = -1;
            }
            CircleIndicator.g(CircleIndicator.this);
            AppMethodBeat.o(22565);
        }
    }

    @Deprecated
    public void setOnPageChangeListener(ViewPager.OnPageChangeListener onPageChangeListener) {
        AppMethodBeat.i(22579, false);
        ViewPager viewPager = this.a;
        if (viewPager != null) {
            viewPager.removeOnPageChangeListener(onPageChangeListener);
            this.a.addOnPageChangeListener(onPageChangeListener);
            AppMethodBeat.o(22579);
            return;
        }
        NullPointerException nullPointerException = new NullPointerException("can not find Viewpager , setViewPager first");
        AppMethodBeat.o(22579);
        throw nullPointerException;
    }

    private void a() {
        AppMethodBeat.i(22581, false);
        removeAllViews();
        int count = this.a.getAdapter().getCount();
        if (count <= 0) {
            AppMethodBeat.o(22581);
            return;
        }
        int currentItem = this.a.getCurrentItem();
        for (int i = 0; i < count; i++) {
            if (currentItem == i) {
                a(this.g, this.k);
            } else {
                a(this.h, this.l);
            }
        }
        AppMethodBeat.o(22581);
    }

    private void a(int i, Animator animator) {
        AppMethodBeat.i(22582, false);
        if (animator.isRunning()) {
            animator.end();
            animator.cancel();
        }
        View view = new View(getContext());
        view.setBackgroundResource(i);
        addView(view, this.c, this.d);
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) view.getLayoutParams();
        int i2 = this.b;
        layoutParams.leftMargin = i2;
        layoutParams.rightMargin = i2;
        view.setLayoutParams(layoutParams);
        animator.setTarget(view);
        animator.start();
        AppMethodBeat.o(22582);
    }

    /* access modifiers changed from: private */
    public class a implements Interpolator {
        private a() {
        }

        /* synthetic */ a(CircleIndicator circleIndicator, AnonymousClass1 r2) {
            this();
        }

        @Override // android.animation.TimeInterpolator
        public float getInterpolation(float f) {
            AppMethodBeat.i(22566, false);
            float abs = Math.abs(1.0f - f);
            AppMethodBeat.o(22566);
            return abs;
        }
    }

    public int a(float f) {
        AppMethodBeat.i(22583, false);
        int i = (int) ((f * getResources().getDisplayMetrics().density) + 0.5f);
        AppMethodBeat.o(22583);
        return i;
    }
}
