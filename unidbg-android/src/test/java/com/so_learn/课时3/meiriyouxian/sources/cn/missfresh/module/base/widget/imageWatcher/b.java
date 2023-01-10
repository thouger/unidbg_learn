package cn.missfresh.module.base.widget.imageWatcher;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.view.View;
import cn.missfresh.module.base.R;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;

/* compiled from: ViewState */
public class b {
    static final int a = R.id.state_origin;
    static final int b = R.id.state_thumb;
    static final int c = R.id.state_default;
    static final int d = R.id.state_current;
    static final int e = R.id.state_temp;
    static final int f = R.id.state_touch_drag;
    static final int g = R.id.state_exit;
    static final int h = R.id.state_touch_scale;
    int i;
    int j;
    int k;
    float l;
    float m;
    float n;
    float o;
    float p;

    private b(int i) {
        this.i = i;
    }

    static b a(View view, int i) {
        AppMethodBeat.i(24134, false);
        if (view == null) {
            AppMethodBeat.o(24134);
            return null;
        }
        b b2 = b(view, i);
        if (b2 == null) {
            b2 = new b(i);
            view.setTag(i, b2);
        }
        b2.j = view.getWidth();
        b2.k = view.getHeight();
        b2.l = view.getTranslationX();
        b2.m = view.getTranslationY();
        b2.n = view.getScaleX();
        b2.o = view.getScaleY();
        b2.p = view.getAlpha();
        AppMethodBeat.o(24134);
        return b2;
    }

    static b b(View view, int i) {
        AppMethodBeat.i(24135, false);
        b bVar = null;
        if (view == null) {
            AppMethodBeat.o(24135);
            return null;
        }
        if (view.getTag(i) != null) {
            bVar = (b) view.getTag(i);
        }
        AppMethodBeat.o(24135);
        return bVar;
    }

    static void c(View view, int i) {
        AppMethodBeat.i(24136, false);
        if (view == null) {
            AppMethodBeat.o(24136);
            return;
        }
        view.setTag(i, null);
        AppMethodBeat.o(24136);
    }

    static b a(b bVar, int i) {
        AppMethodBeat.i(24137, false);
        b bVar2 = new b(i);
        bVar2.j = bVar.j;
        bVar2.k = bVar.k;
        bVar2.l = bVar.l;
        bVar2.m = bVar.m;
        bVar2.n = bVar.n;
        bVar2.o = bVar.o;
        bVar2.p = bVar.p;
        AppMethodBeat.o(24137);
        return bVar2;
    }

    static void d(View view, int i) {
        AppMethodBeat.i(24138, false);
        b b2 = b(view, i);
        if (b2 != null) {
            view.setTranslationX(b2.l);
            view.setTranslationY(b2.m);
            view.setScaleX(b2.n);
            view.setScaleY(b2.o);
            view.setAlpha(b2.p);
            if (!(view.getLayoutParams().width == b2.j && view.getLayoutParams().height == b2.k)) {
                view.getLayoutParams().width = b2.j;
                view.getLayoutParams().height = b2.k;
                view.requestLayout();
            }
        }
        AppMethodBeat.o(24138);
    }

    static a e(View view, int i) {
        ValueAnimator valueAnimator;
        b b2;
        AppMethodBeat.i(24139, false);
        if (view != null) {
            b a2 = a(view, d);
            if (a2.j == 0 && a2.k == 0 && (b2 = b(view, a)) != null) {
                a2.a(b2.j).b(b2.k);
            }
            b b3 = b(view, i);
            if (b3 != null) {
                valueAnimator = ValueAnimator.ofFloat(0.0f, 1.0f).setDuration(200L);
                valueAnimator.addUpdateListener(new AnonymousClass1(view, a2, b3));
                a aVar = new a(valueAnimator);
                AppMethodBeat.o(24139);
                return aVar;
            }
        }
        valueAnimator = null;
        a aVar = new a(valueAnimator);
        AppMethodBeat.o(24139);
        return aVar;
    }

    /* compiled from: ViewState */
    /* access modifiers changed from: package-private */
    /* renamed from: cn.missfresh.module.base.widget.imageWatcher.b$1  reason: invalid class name */
    public static class AnonymousClass1 implements ValueAnimator.AnimatorUpdateListener {
        final /* synthetic */ View a;
        final /* synthetic */ b b;
        final /* synthetic */ b c;

        AnonymousClass1(View view, b bVar, b bVar2) {
            this.a = view;
            this.b = bVar;
            this.c = bVar2;
        }

        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            AppMethodBeat.i(24132, false);
            float floatValue = ((Float) valueAnimator.getAnimatedValue()).floatValue();
            this.a.setTranslationX(this.b.l + ((this.c.l - this.b.l) * floatValue));
            this.a.setTranslationY(this.b.m + ((this.c.m - this.b.m) * floatValue));
            this.a.setScaleX(this.b.n + ((this.c.n - this.b.n) * floatValue));
            this.a.setScaleY(this.b.o + ((this.c.o - this.b.o) * floatValue));
            this.a.setAlpha(this.b.p + ((this.c.p - this.b.p) * floatValue));
            if (!(this.b.j == this.c.j || this.b.k == this.c.k || this.c.j == 0 || this.c.k == 0)) {
                this.a.getLayoutParams().width = (int) (((float) this.b.j) + (((float) (this.c.j - this.b.j)) * floatValue));
                this.a.getLayoutParams().height = (int) (((float) this.b.k) + (((float) (this.c.k - this.b.k)) * floatValue));
                this.a.requestLayout();
            }
            AppMethodBeat.o(24132);
        }
    }

    /* compiled from: ViewState */
    /* access modifiers changed from: package-private */
    public static class a {
        ValueAnimator a;

        a(ValueAnimator valueAnimator) {
            this.a = valueAnimator;
        }

        /* access modifiers changed from: package-private */
        public a a(Animator.AnimatorListener animatorListener) {
            AppMethodBeat.i(24133, false);
            ValueAnimator valueAnimator = this.a;
            if (valueAnimator != null) {
                valueAnimator.addListener(animatorListener);
            }
            AppMethodBeat.o(24133);
            return this;
        }

        /* access modifiers changed from: package-private */
        public ValueAnimator a() {
            return this.a;
        }
    }

    /* access modifiers changed from: package-private */
    public b a(float f2) {
        this.n = f2;
        return this;
    }

    /* access modifiers changed from: package-private */
    public b b(float f2) {
        this.n *= f2;
        return this;
    }

    /* access modifiers changed from: package-private */
    public b c(float f2) {
        this.o = f2;
        return this;
    }

    /* access modifiers changed from: package-private */
    public b a(int i) {
        this.j = i;
        return this;
    }

    /* access modifiers changed from: package-private */
    public b b(int i) {
        this.k = i;
        return this;
    }

    /* access modifiers changed from: package-private */
    public b d(float f2) {
        this.l = f2;
        return this;
    }

    /* access modifiers changed from: package-private */
    public b e(float f2) {
        this.m = f2;
        return this;
    }

    /* access modifiers changed from: package-private */
    public b f(float f2) {
        this.p = f2;
        return this;
    }
}
