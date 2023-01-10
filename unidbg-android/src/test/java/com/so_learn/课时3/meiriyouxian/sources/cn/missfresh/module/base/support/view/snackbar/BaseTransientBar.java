package cn.missfresh.module.base.support.view.snackbar;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.accessibility.AccessibilityManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.view.OnApplyWindowInsetsListener;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import cn.missfresh.module.base.R;
import cn.missfresh.module.base.support.view.snackbar.BaseTransientBar;
import cn.missfresh.module.base.support.view.snackbar.SwipeDismissBehavior;
import cn.missfresh.module.base.support.view.snackbar.b;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import java.util.ArrayList;
import java.util.List;

public abstract class BaseTransientBar<B extends BaseTransientBar<B>> {
    static final Handler a = new Handler(Looper.getMainLooper(), new AnonymousClass1());
    private static final boolean e = (Build.VERSION.SDK_INT >= 16 && Build.VERSION.SDK_INT <= 19);
    final SnackbarBaseLayout b;
    final b.a c = new AnonymousClass12();
    private final boolean d;
    private final ViewGroup f;
    private final Context g;
    private final c h;
    private int i;
    private List<a<B>> j;
    private final AccessibilityManager k;

    public static abstract class a<B> {
        public void a(B b) {
        }

        public void a(B b, int i) {
        }
    }

    public interface c {
        void a(int i, int i2);

        void b(int i, int i2);
    }

    /* renamed from: cn.missfresh.module.base.support.view.snackbar.BaseTransientBar$1  reason: invalid class name */
    static class AnonymousClass1 implements Handler.Callback {
        AnonymousClass1() {
        }

        @Override // android.os.Handler.Callback
        public boolean handleMessage(Message message) {
            AppMethodBeat.i(22831, false);
            int i = message.what;
            if (i == 0) {
                ((BaseTransientBar) message.obj).c();
                AppMethodBeat.o(22831);
                return true;
            } else if (i != 1) {
                AppMethodBeat.o(22831);
                return false;
            } else {
                ((BaseTransientBar) message.obj).c(message.arg1);
                AppMethodBeat.o(22831);
                return true;
            }
        }
    }

    protected BaseTransientBar(ViewGroup viewGroup, View view, c cVar, boolean z) {
        if (viewGroup == null) {
            throw new IllegalArgumentException("Transient bottom bar must have non-null parent");
        } else if (view == null) {
            throw new IllegalArgumentException("Transient bottom bar must have non-null content");
        } else if (cVar != null) {
            this.f = viewGroup;
            this.h = cVar;
            this.g = viewGroup.getContext();
            this.d = z;
            c.a(this.g);
            LayoutInflater from = LayoutInflater.from(this.g);
            if (this.d) {
                this.b = (SnackbarBaseLayout) from.inflate(R.layout.design_layout_top_snackbar, this.f, false);
            } else {
                this.b = (SnackbarBaseLayout) from.inflate(R.layout.design_layout_bottom_snackbar, this.f, false);
            }
            this.b.addView(view);
            ViewCompat.setAccessibilityLiveRegion(this.b, 1);
            ViewCompat.setImportantForAccessibility(this.b, 1);
            ViewCompat.setFitsSystemWindows(this.b, true);
            ViewCompat.setOnApplyWindowInsetsListener(this.b, new AnonymousClass11());
            this.k = (AccessibilityManager) this.g.getSystemService(Context.ACCESSIBILITY_SERVICE);
        } else {
            throw new IllegalArgumentException("Transient bottom bar must have non-null callback");
        }
    }

    /* renamed from: cn.missfresh.module.base.support.view.snackbar.BaseTransientBar$11  reason: invalid class name */
    class AnonymousClass11 implements OnApplyWindowInsetsListener {
        AnonymousClass11() {
        }

        @Override // androidx.core.view.OnApplyWindowInsetsListener
        public WindowInsetsCompat onApplyWindowInsets(View view, WindowInsetsCompat windowInsetsCompat) {
            AppMethodBeat.i(22844, false);
            view.setPadding(view.getPaddingLeft(), view.getPaddingTop(), view.getPaddingRight(), windowInsetsCompat.getSystemWindowInsetBottom());
            AppMethodBeat.o(22844);
            return windowInsetsCompat;
        }
    }

    public B a(int i) {
        this.i = i;
        return this;
    }

    public void a() {
        b.a().a(this.i, this.c);
    }

    /* access modifiers changed from: package-private */
    public void b(int i) {
        b.a().a(this.c, i);
    }

    public B a(a<B> aVar) {
        if (aVar == null) {
            return this;
        }
        if (this.j == null) {
            this.j = new ArrayList();
        }
        this.j.add(aVar);
        return this;
    }

    public boolean b() {
        return b.a().e(this.c);
    }

    /* renamed from: cn.missfresh.module.base.support.view.snackbar.BaseTransientBar$12  reason: invalid class name */
    class AnonymousClass12 implements b.a {
        AnonymousClass12() {
        }

        @Override // cn.missfresh.module.base.support.view.snackbar.b.a
        public void a() {
            AppMethodBeat.i(22845, false);
            BaseTransientBar.a.sendMessage(BaseTransientBar.a.obtainMessage(0, BaseTransientBar.this));
            AppMethodBeat.o(22845);
        }

        @Override // cn.missfresh.module.base.support.view.snackbar.b.a
        public void a(int i) {
            AppMethodBeat.i(22846, false);
            BaseTransientBar.a.sendMessage(BaseTransientBar.a.obtainMessage(1, i, 0, BaseTransientBar.this));
            AppMethodBeat.o(22846);
        }
    }

    /* access modifiers changed from: package-private */
    public final void c() {
        if (this.b.getParent() == null) {
            ViewGroup.LayoutParams layoutParams = this.b.getLayoutParams();
            if (layoutParams instanceof CoordinatorLayout.LayoutParams) {
                CoordinatorLayout.LayoutParams layoutParams2 = (CoordinatorLayout.LayoutParams) layoutParams;
                b bVar = new b();
                bVar.a(0.1f);
                bVar.b(0.6f);
                bVar.a(0);
                bVar.a(new AnonymousClass13());
                layoutParams2.setBehavior(bVar);
                layoutParams2.insetEdge = 80;
            }
            this.f.addView(this.b);
        }
        this.b.setOnAttachStateChangeListener(new AnonymousClass14());
        if (!ViewCompat.isLaidOut(this.b)) {
            this.b.setOnLayoutChangeListener(new AnonymousClass15());
        } else if (!g()) {
            f();
        } else if (this.d) {
            d();
        } else {
            e();
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: cn.missfresh.module.base.support.view.snackbar.BaseTransientBar$13  reason: invalid class name */
    public class AnonymousClass13 implements SwipeDismissBehavior.a {
        AnonymousClass13() {
        }

        @Override // cn.missfresh.module.base.support.view.snackbar.SwipeDismissBehavior.a
        public void a(View view) {
            AppMethodBeat.i(22847, false);
            view.setVisibility(8);
            BaseTransientBar.this.b(0);
            AppMethodBeat.o(22847);
        }

        @Override // cn.missfresh.module.base.support.view.snackbar.SwipeDismissBehavior.a
        public void a(int i) {
            AppMethodBeat.i(22848, false);
            if (i == 0) {
                b.a().d(BaseTransientBar.this.c);
            } else if (i == 1 || i == 2) {
                b.a().c(BaseTransientBar.this.c);
            }
            AppMethodBeat.o(22848);
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: cn.missfresh.module.base.support.view.snackbar.BaseTransientBar$14  reason: invalid class name */
    public class AnonymousClass14 implements View.OnAttachStateChangeListener {
        @Override // android.view.View.OnAttachStateChangeListener
        public void onViewAttachedToWindow(View view) {
        }

        AnonymousClass14() {
        }

        @Override // android.view.View.OnAttachStateChangeListener
        public void onViewDetachedFromWindow(View view) {
            AppMethodBeat.i(22850, false);
            if (BaseTransientBar.this.b()) {
                BaseTransientBar.a.post(new AnonymousClass1());
            }
            AppMethodBeat.o(22850);
        }

        /* renamed from: cn.missfresh.module.base.support.view.snackbar.BaseTransientBar$14$1  reason: invalid class name */
        class AnonymousClass1 implements Runnable {
            AnonymousClass1() {
            }

            @Override // java.lang.Runnable
            public void run() {
                AppMethodBeat.i(22849, false);
                BaseTransientBar.this.d(3);
                AppMethodBeat.o(22849);
            }
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: cn.missfresh.module.base.support.view.snackbar.BaseTransientBar$15  reason: invalid class name */
    public class AnonymousClass15 implements View.OnLayoutChangeListener {
        AnonymousClass15() {
        }

        @Override // android.view.View.OnLayoutChangeListener
        public void onLayoutChange(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
            AppMethodBeat.i(22851, false);
            BaseTransientBar.this.b.setOnLayoutChangeListener(null);
            if (!BaseTransientBar.this.g()) {
                BaseTransientBar.this.f();
            } else if (BaseTransientBar.this.d) {
                BaseTransientBar.this.d();
            } else {
                BaseTransientBar.this.e();
            }
            AppMethodBeat.o(22851);
        }
    }

    /* access modifiers changed from: package-private */
    public void d() {
        if (Build.VERSION.SDK_INT >= 12) {
            int i = -this.b.getHeight();
            if (e) {
                ViewCompat.offsetTopAndBottom(this.b, i);
            } else {
                this.b.setTranslationY((float) i);
            }
            ValueAnimator valueAnimator = new ValueAnimator();
            valueAnimator.setIntValues(i, 0);
            valueAnimator.setInterpolator(a.b);
            valueAnimator.setDuration(250L);
            valueAnimator.addListener(new AnonymousClass16());
            valueAnimator.addUpdateListener(new AnonymousClass17(i));
            valueAnimator.start();
            return;
        }
        Animation loadAnimation = AnimationUtils.loadAnimation(this.b.getContext(), R.anim.design_snackbar_in);
        loadAnimation.setInterpolator(a.b);
        loadAnimation.setDuration(250);
        loadAnimation.setAnimationListener(new AnonymousClass18());
        this.b.startAnimation(loadAnimation);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: cn.missfresh.module.base.support.view.snackbar.BaseTransientBar$16  reason: invalid class name */
    public class AnonymousClass16 extends AnimatorListenerAdapter {
        AnonymousClass16() {
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animator) {
            AppMethodBeat.i(22852, false);
            BaseTransientBar.this.h.a(70, 180);
            AppMethodBeat.o(22852);
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            AppMethodBeat.i(22853, false);
            BaseTransientBar.this.f();
            AppMethodBeat.o(22853);
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: cn.missfresh.module.base.support.view.snackbar.BaseTransientBar$17  reason: invalid class name */
    public class AnonymousClass17 implements ValueAnimator.AnimatorUpdateListener {
        final /* synthetic */ int a;
        private int c = this.a;

        AnonymousClass17(int i) {
            this.a = i;
        }

        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            AppMethodBeat.i(22854, false);
            int intValue = ((Integer) valueAnimator.getAnimatedValue()).intValue();
            if (BaseTransientBar.e) {
                ViewCompat.offsetTopAndBottom(BaseTransientBar.this.b, intValue - this.c);
            } else {
                BaseTransientBar.this.b.setTranslationY((float) intValue);
            }
            this.c = intValue;
            AppMethodBeat.o(22854);
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: cn.missfresh.module.base.support.view.snackbar.BaseTransientBar$18  reason: invalid class name */
    public class AnonymousClass18 implements Animation.AnimationListener {
        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationRepeat(Animation animation) {
        }

        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationStart(Animation animation) {
        }

        AnonymousClass18() {
        }

        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationEnd(Animation animation) {
            AppMethodBeat.i(22855, false);
            BaseTransientBar.this.f();
            AppMethodBeat.o(22855);
        }
    }

    private void e(int i) {
        if (Build.VERSION.SDK_INT >= 12) {
            this.b.setTranslationY(0.0f);
            ValueAnimator valueAnimator = new ValueAnimator();
            valueAnimator.setIntValues(0, -this.b.getHeight());
            valueAnimator.setInterpolator(a.b);
            valueAnimator.setDuration(250L);
            valueAnimator.addListener(new AnonymousClass2(i));
            valueAnimator.addUpdateListener(new AnonymousClass3());
            valueAnimator.start();
            return;
        }
        Animation loadAnimation = AnimationUtils.loadAnimation(this.b.getContext(), com.google.android.material.R.anim.design_snackbar_out);
        loadAnimation.setInterpolator(a.b);
        loadAnimation.setDuration(250);
        loadAnimation.setAnimationListener(new AnonymousClass4(i));
        this.b.startAnimation(loadAnimation);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: cn.missfresh.module.base.support.view.snackbar.BaseTransientBar$2  reason: invalid class name */
    public class AnonymousClass2 extends AnimatorListenerAdapter {
        final /* synthetic */ int a;

        AnonymousClass2(int i) {
            this.a = i;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animator) {
            AppMethodBeat.i(22832, false);
            BaseTransientBar.this.h.b(0, 180);
            AppMethodBeat.o(22832);
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            AppMethodBeat.i(22833, false);
            BaseTransientBar.this.d(this.a);
            AppMethodBeat.o(22833);
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: cn.missfresh.module.base.support.view.snackbar.BaseTransientBar$3  reason: invalid class name */
    public class AnonymousClass3 implements ValueAnimator.AnimatorUpdateListener {
        private int b = 0;

        AnonymousClass3() {
        }

        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            AppMethodBeat.i(22834, false);
            int intValue = ((Integer) valueAnimator.getAnimatedValue()).intValue();
            if (BaseTransientBar.e) {
                ViewCompat.offsetTopAndBottom(BaseTransientBar.this.b, intValue - this.b);
            } else {
                BaseTransientBar.this.b.setTranslationY((float) intValue);
            }
            this.b = intValue;
            AppMethodBeat.o(22834);
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: cn.missfresh.module.base.support.view.snackbar.BaseTransientBar$4  reason: invalid class name */
    public class AnonymousClass4 implements Animation.AnimationListener {
        final /* synthetic */ int a;

        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationRepeat(Animation animation) {
        }

        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationStart(Animation animation) {
        }

        AnonymousClass4(int i) {
            this.a = i;
        }

        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationEnd(Animation animation) {
            AppMethodBeat.i(22835, false);
            BaseTransientBar.this.d(this.a);
            AppMethodBeat.o(22835);
        }
    }

    /* access modifiers changed from: package-private */
    public void e() {
        if (Build.VERSION.SDK_INT >= 12) {
            int height = this.b.getHeight();
            if (e) {
                ViewCompat.offsetTopAndBottom(this.b, height);
            } else {
                this.b.setTranslationY((float) height);
            }
            ValueAnimator valueAnimator = new ValueAnimator();
            valueAnimator.setIntValues(height, 0);
            valueAnimator.setInterpolator(a.b);
            valueAnimator.setDuration(250L);
            valueAnimator.addListener(new AnonymousClass5());
            valueAnimator.addUpdateListener(new AnonymousClass6(height));
            valueAnimator.start();
            return;
        }
        Animation loadAnimation = AnimationUtils.loadAnimation(this.b.getContext(), com.google.android.material.R.anim.design_snackbar_in);
        loadAnimation.setInterpolator(a.b);
        loadAnimation.setDuration(250);
        loadAnimation.setAnimationListener(new AnonymousClass7());
        this.b.startAnimation(loadAnimation);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: cn.missfresh.module.base.support.view.snackbar.BaseTransientBar$5  reason: invalid class name */
    public class AnonymousClass5 extends AnimatorListenerAdapter {
        AnonymousClass5() {
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animator) {
            AppMethodBeat.i(22836, false);
            BaseTransientBar.this.h.a(70, 180);
            AppMethodBeat.o(22836);
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            AppMethodBeat.i(22837, false);
            BaseTransientBar.this.f();
            AppMethodBeat.o(22837);
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: cn.missfresh.module.base.support.view.snackbar.BaseTransientBar$6  reason: invalid class name */
    public class AnonymousClass6 implements ValueAnimator.AnimatorUpdateListener {
        final /* synthetic */ int a;
        private int c = this.a;

        AnonymousClass6(int i) {
            this.a = i;
        }

        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            AppMethodBeat.i(22838, false);
            int intValue = ((Integer) valueAnimator.getAnimatedValue()).intValue();
            if (BaseTransientBar.e) {
                ViewCompat.offsetTopAndBottom(BaseTransientBar.this.b, intValue - this.c);
            } else {
                BaseTransientBar.this.b.setTranslationY((float) intValue);
            }
            this.c = intValue;
            AppMethodBeat.o(22838);
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: cn.missfresh.module.base.support.view.snackbar.BaseTransientBar$7  reason: invalid class name */
    public class AnonymousClass7 implements Animation.AnimationListener {
        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationRepeat(Animation animation) {
        }

        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationStart(Animation animation) {
        }

        AnonymousClass7() {
        }

        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationEnd(Animation animation) {
            AppMethodBeat.i(22839, false);
            BaseTransientBar.this.f();
            AppMethodBeat.o(22839);
        }
    }

    private void f(int i) {
        if (Build.VERSION.SDK_INT >= 12) {
            ValueAnimator valueAnimator = new ValueAnimator();
            valueAnimator.setIntValues(0, this.b.getHeight());
            valueAnimator.setInterpolator(a.b);
            valueAnimator.setDuration(250L);
            valueAnimator.addListener(new AnonymousClass8(i));
            valueAnimator.addUpdateListener(new AnonymousClass9());
            valueAnimator.start();
            return;
        }
        Animation loadAnimation = AnimationUtils.loadAnimation(this.b.getContext(), com.google.android.material.R.anim.design_snackbar_out);
        loadAnimation.setInterpolator(a.b);
        loadAnimation.setDuration(250);
        loadAnimation.setAnimationListener(new AnonymousClass10(i));
        this.b.startAnimation(loadAnimation);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: cn.missfresh.module.base.support.view.snackbar.BaseTransientBar$8  reason: invalid class name */
    public class AnonymousClass8 extends AnimatorListenerAdapter {
        final /* synthetic */ int a;

        AnonymousClass8(int i) {
            this.a = i;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animator) {
            AppMethodBeat.i(22840, false);
            BaseTransientBar.this.h.b(0, 180);
            AppMethodBeat.o(22840);
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            AppMethodBeat.i(22841, false);
            BaseTransientBar.this.d(this.a);
            AppMethodBeat.o(22841);
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: cn.missfresh.module.base.support.view.snackbar.BaseTransientBar$9  reason: invalid class name */
    public class AnonymousClass9 implements ValueAnimator.AnimatorUpdateListener {
        private int b = 0;

        AnonymousClass9() {
        }

        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            AppMethodBeat.i(22842, false);
            int intValue = ((Integer) valueAnimator.getAnimatedValue()).intValue();
            if (BaseTransientBar.e) {
                ViewCompat.offsetTopAndBottom(BaseTransientBar.this.b, intValue - this.b);
            } else {
                BaseTransientBar.this.b.setTranslationY((float) intValue);
            }
            this.b = intValue;
            AppMethodBeat.o(22842);
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: cn.missfresh.module.base.support.view.snackbar.BaseTransientBar$10  reason: invalid class name */
    public class AnonymousClass10 implements Animation.AnimationListener {
        final /* synthetic */ int a;

        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationRepeat(Animation animation) {
        }

        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationStart(Animation animation) {
        }

        AnonymousClass10(int i) {
            this.a = i;
        }

        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationEnd(Animation animation) {
            AppMethodBeat.i(22843, false);
            BaseTransientBar.this.d(this.a);
            AppMethodBeat.o(22843);
        }
    }

    /* access modifiers changed from: package-private */
    public final void c(int i) {
        if (!g() || this.b.getVisibility() != 0) {
            d(i);
        } else if (this.d) {
            e(i);
        } else {
            f(i);
        }
    }

    /* access modifiers changed from: package-private */
    public void f() {
        b.a().b(this.c);
        List<a<B>> list = this.j;
        if (list != null) {
            for (int size = list.size() - 1; size >= 0; size--) {
                this.j.get(size).a(this);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void d(int i) {
        b.a().a(this.c);
        List<a<B>> list = this.j;
        if (list != null) {
            for (int size = list.size() - 1; size >= 0; size--) {
                this.j.get(size).a(this, i);
            }
        }
        if (Build.VERSION.SDK_INT < 11) {
            this.b.setVisibility(8);
        }
        ViewParent parent = this.b.getParent();
        if (parent instanceof ViewGroup) {
            ((ViewGroup) parent).removeView(this.b);
        }
    }

    /* access modifiers changed from: package-private */
    public boolean g() {
        return !this.k.isEnabled();
    }

    /* access modifiers changed from: package-private */
    public static class SnackbarBaseLayout extends FrameLayout {
        private View.OnLayoutChangeListener a;
        private View.OnAttachStateChangeListener b;

        SnackbarBaseLayout(Context context) {
            this(context, null);
        }

        SnackbarBaseLayout(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            AppMethodBeat.i(22858, false);
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.SnackbarLayout);
            if (obtainStyledAttributes.hasValue(R.styleable.SnackbarLayout_elevation)) {
                ViewCompat.setElevation(this, (float) obtainStyledAttributes.getDimensionPixelSize(R.styleable.SnackbarLayout_elevation, 0));
            }
            obtainStyledAttributes.recycle();
            setClickable(true);
            AppMethodBeat.o(22858);
        }

        /* access modifiers changed from: protected */
        @Override // android.widget.FrameLayout, android.view.ViewGroup, android.view.View
        public void onLayout(boolean z, int i, int i2, int i3, int i4) {
            AppMethodBeat.i(22859, false);
            super.onLayout(z, i, i2, i3, i4);
            View.OnLayoutChangeListener onLayoutChangeListener = this.a;
            if (onLayoutChangeListener != null) {
                onLayoutChangeListener.onLayoutChange(this, i, i2, i3, i4, i, i2, i3, i4);
            }
            AppMethodBeat.o(22859);
        }

        /* access modifiers changed from: protected */
        @Override // android.view.ViewGroup, android.view.View
        public void onAttachedToWindow() {
            AppMethodBeat.i(22860, false);
            super.onAttachedToWindow();
            View.OnAttachStateChangeListener onAttachStateChangeListener = this.b;
            if (onAttachStateChangeListener != null) {
                onAttachStateChangeListener.onViewAttachedToWindow(this);
            }
            ViewCompat.requestApplyInsets(this);
            AppMethodBeat.o(22860);
        }

        /* access modifiers changed from: protected */
        @Override // android.view.ViewGroup, android.view.View
        public void onDetachedFromWindow() {
            AppMethodBeat.i(22861, false);
            super.onDetachedFromWindow();
            View.OnAttachStateChangeListener onAttachStateChangeListener = this.b;
            if (onAttachStateChangeListener != null) {
                onAttachStateChangeListener.onViewDetachedFromWindow(this);
            }
            AppMethodBeat.o(22861);
        }

        /* access modifiers changed from: package-private */
        public void setOnLayoutChangeListener(View.OnLayoutChangeListener onLayoutChangeListener) {
            this.a = onLayoutChangeListener;
        }

        /* access modifiers changed from: package-private */
        public void setOnAttachStateChangeListener(View.OnAttachStateChangeListener onAttachStateChangeListener) {
            this.b = onAttachStateChangeListener;
        }
    }

    /* access modifiers changed from: package-private */
    public final class b extends SwipeDismissBehavior<SnackbarBaseLayout> {
        b() {
        }

        @Override // cn.missfresh.module.base.support.view.snackbar.SwipeDismissBehavior, androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
        public /* synthetic */ boolean onInterceptTouchEvent(CoordinatorLayout coordinatorLayout, View view, MotionEvent motionEvent) {
            AppMethodBeat.i(22857, false);
            boolean a = a(coordinatorLayout, (SnackbarBaseLayout) view, motionEvent);
            AppMethodBeat.o(22857);
            return a;
        }

        @Override // cn.missfresh.module.base.support.view.snackbar.SwipeDismissBehavior
        public boolean a(View view) {
            return view instanceof SnackbarBaseLayout;
        }

        public boolean a(CoordinatorLayout coordinatorLayout, SnackbarBaseLayout snackbarBaseLayout, MotionEvent motionEvent) {
            AppMethodBeat.i(22856, false);
            int actionMasked = motionEvent.getActionMasked();
            if (actionMasked != 0) {
                if (actionMasked == 1 || actionMasked == 3) {
                    b.a().d(BaseTransientBar.this.c);
                }
            } else if (coordinatorLayout.isPointInChildBounds(snackbarBaseLayout, (int) motionEvent.getX(), (int) motionEvent.getY())) {
                b.a().c(BaseTransientBar.this.c);
            }
            boolean onInterceptTouchEvent = super.onInterceptTouchEvent(coordinatorLayout, snackbarBaseLayout, motionEvent);
            AppMethodBeat.o(22856);
            return onInterceptTouchEvent;
        }
    }
}
