package cn.missfresh.module.base.widget.imageWatcher;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.Transformation;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import java.util.ArrayList;

public class ProgressView extends View {
    private AnimationDrawable a;
    private float b;

    public ProgressView(Context context) {
        this(context, null);
    }

    public ProgressView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        AppMethodBeat.i(24123, false);
        this.b = 1.0f;
        a(new a(getContext(), this));
        AppMethodBeat.o(24123);
    }

    private void a(AnimationDrawable animationDrawable) {
        AppMethodBeat.i(24124, false);
        this.a = animationDrawable;
        this.a.setAlpha(255);
        this.a.setCallback(this);
        AppMethodBeat.o(24124);
    }

    @Override // android.view.View, android.graphics.drawable.Drawable.Callback
    public void invalidateDrawable(Drawable drawable) {
        AppMethodBeat.i(24125, false);
        if (drawable == this.a) {
            invalidate();
        } else {
            super.invalidateDrawable(drawable);
        }
        AppMethodBeat.o(24125);
    }

    @Override // android.view.View, android.graphics.drawable.Drawable.Callback
    public void scheduleDrawable(Drawable drawable, Runnable runnable, long j) {
        AppMethodBeat.i(24126, false);
        super.scheduleDrawable(drawable, runnable, j);
        AppMethodBeat.o(24126);
    }

    public void a() {
        AppMethodBeat.i(24127, false);
        this.a.start();
        AppMethodBeat.o(24127);
    }

    public void b() {
        AppMethodBeat.i(24128, false);
        this.a.stop();
        AppMethodBeat.o(24128);
    }

    /* access modifiers changed from: protected */
    @Override // android.view.View
    public void onMeasure(int i, int i2) {
        AppMethodBeat.i(24129, false);
        super.onMeasure(i, View.MeasureSpec.makeMeasureSpec(this.a.getIntrinsicHeight() + getPaddingTop() + getPaddingBottom(), 1073741824));
        AppMethodBeat.o(24129);
    }

    /* access modifiers changed from: protected */
    @Override // android.view.View
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        AppMethodBeat.i(24130, false);
        int intrinsicHeight = this.a.getIntrinsicHeight();
        this.a.setBounds(0, 0, intrinsicHeight, intrinsicHeight);
        AppMethodBeat.o(24130);
    }

    /* access modifiers changed from: protected */
    @Override // android.view.View
    public void onDraw(Canvas canvas) {
        AppMethodBeat.i(24131, false);
        int save = canvas.save();
        Rect bounds = this.a.getBounds();
        canvas.translate((float) (getPaddingLeft() + ((getMeasuredWidth() - this.a.getIntrinsicWidth()) / 2)), (float) getPaddingTop());
        float f = this.b;
        canvas.scale(f, f, bounds.exactCenterX(), bounds.exactCenterY());
        this.a.draw(canvas);
        canvas.restoreToCount(save);
        AppMethodBeat.o(24131);
    }

    static class a extends AnimationDrawable {
        private static final Interpolator a = new LinearInterpolator();
        private static final Interpolator b = new C0032a();
        private static final Interpolator c = new c();
        private static final Interpolator d = new AccelerateDecelerateInterpolator();
        private final int[] e;
        private final ArrayList<Animation> f;
        private final b g;
        private final Drawable.Callback h;
        private float i;
        private Resources j;
        private View k;
        private Animation l;
        private float m;
        private double n;
        private double o;
        private Animation p;

        @Override // android.graphics.drawable.DrawableContainer, android.graphics.drawable.Drawable
        public int getOpacity() {
            return -3;
        }

        static {
            AppMethodBeat.i(24122, false);
            AppMethodBeat.o(24122);
        }

        /* renamed from: cn.missfresh.module.base.widget.imageWatcher.ProgressView$a$1  reason: invalid class name */
        class AnonymousClass1 implements Drawable.Callback {
            AnonymousClass1() {
            }

            @Override // android.graphics.drawable.Drawable.Callback
            public void invalidateDrawable(Drawable drawable) {
                AppMethodBeat.i(24088, false);
                a.this.invalidateSelf();
                AppMethodBeat.o(24088);
            }

            @Override // android.graphics.drawable.Drawable.Callback
            public void scheduleDrawable(Drawable drawable, Runnable runnable, long j) {
                AppMethodBeat.i(24089, false);
                a.this.scheduleSelf(runnable, j);
                AppMethodBeat.o(24089);
            }

            @Override // android.graphics.drawable.Drawable.Callback
            public void unscheduleDrawable(Drawable drawable, Runnable runnable) {
                AppMethodBeat.i(24090, false);
                a.this.unscheduleSelf(runnable);
                AppMethodBeat.o(24090);
            }
        }

        private a(Context context, View view) {
            AppMethodBeat.i(24111, false);
            this.e = new int[]{-1, -1, -1, -1};
            this.f = new ArrayList<>();
            this.h = new AnonymousClass1();
            this.k = view;
            this.j = context.getResources();
            this.g = new b(this.h);
            this.g.a(this.e);
            a(30.0d, 30.0d, 8.0d, 2.0d);
            c();
            AppMethodBeat.o(24111);
        }

        private void a(double d2, double d3, double d4, double d5) {
            AppMethodBeat.i(24112, false);
            b bVar = this.g;
            float f = this.j.getDisplayMetrics().density;
            double d6 = (double) f;
            this.n = d2 * d6;
            this.o = d3 * d6;
            bVar.a(((float) d5) * f);
            bVar.a(d4 * d6);
            bVar.a(0);
            bVar.a((int) this.n, (int) this.o);
            AppMethodBeat.o(24112);
        }

        @Override // android.graphics.drawable.DrawableContainer, android.graphics.drawable.Drawable
        public int getIntrinsicHeight() {
            return (int) this.o;
        }

        @Override // android.graphics.drawable.DrawableContainer, android.graphics.drawable.Drawable
        public int getIntrinsicWidth() {
            return (int) this.n;
        }

        @Override // android.graphics.drawable.DrawableContainer, android.graphics.drawable.Drawable
        public void draw(Canvas canvas) {
            AppMethodBeat.i(24113, false);
            Rect bounds = getBounds();
            int save = canvas.save();
            canvas.rotate(this.i, bounds.exactCenterX(), bounds.exactCenterY());
            this.g.a(canvas, bounds);
            canvas.restoreToCount(save);
            AppMethodBeat.o(24113);
        }

        @Override // android.graphics.drawable.DrawableContainer, android.graphics.drawable.Drawable
        public int getAlpha() {
            AppMethodBeat.i(24114, false);
            int b2 = this.g.b();
            AppMethodBeat.o(24114);
            return b2;
        }

        @Override // android.graphics.drawable.DrawableContainer, android.graphics.drawable.Drawable
        public void setAlpha(int i) {
            AppMethodBeat.i(24115, false);
            this.g.b(i);
            AppMethodBeat.o(24115);
        }

        @Override // android.graphics.drawable.DrawableContainer, android.graphics.drawable.Drawable
        public void setColorFilter(ColorFilter colorFilter) {
            AppMethodBeat.i(24116, false);
            this.g.a(colorFilter);
            AppMethodBeat.o(24116);
        }

        /* access modifiers changed from: package-private */
        public void a(float f) {
            AppMethodBeat.i(24117, false);
            this.i = f;
            invalidateSelf();
            AppMethodBeat.o(24117);
        }

        @Override // android.graphics.drawable.AnimationDrawable, android.graphics.drawable.Animatable
        public boolean isRunning() {
            AppMethodBeat.i(24118, false);
            ArrayList<Animation> arrayList = this.f;
            int size = arrayList.size();
            for (int i = 0; i < size; i++) {
                Animation animation = arrayList.get(i);
                if (animation.hasStarted() && !animation.hasEnded()) {
                    AppMethodBeat.o(24118);
                    return true;
                }
            }
            AppMethodBeat.o(24118);
            return false;
        }

        @Override // android.graphics.drawable.AnimationDrawable, android.graphics.drawable.Animatable
        public void start() {
            AppMethodBeat.i(24119, false);
            this.l.reset();
            this.g.j();
            if (this.g.g() != this.g.d()) {
                this.k.startAnimation(this.p);
            } else {
                this.g.a(0);
                this.g.k();
                this.k.startAnimation(this.l);
            }
            AppMethodBeat.o(24119);
        }

        @Override // android.graphics.drawable.AnimationDrawable, android.graphics.drawable.Animatable
        public void stop() {
            AppMethodBeat.i(24120, false);
            this.k.clearAnimation();
            a(0.0f);
            this.g.a(false);
            this.g.a(0);
            this.g.k();
            AppMethodBeat.o(24120);
        }

        /* access modifiers changed from: package-private */
        /* renamed from: cn.missfresh.module.base.widget.imageWatcher.ProgressView$a$2  reason: invalid class name */
        public class AnonymousClass2 extends Animation {
            final /* synthetic */ b a;

            AnonymousClass2(b bVar) {
                this.a = bVar;
            }

            @Override // android.view.animation.Animation
            public void applyTransformation(float f, Transformation transformation) {
                AppMethodBeat.i(24091, false);
                this.a.b(this.a.e() + ((this.a.f() - this.a.e()) * f));
                this.a.d(this.a.i() + ((((float) (Math.floor((double) (this.a.i() / 0.8f)) + 1.0d)) - this.a.i()) * f));
                this.a.e(1.0f - f);
                AppMethodBeat.o(24091);
            }
        }

        private void c() {
            AppMethodBeat.i(24121, false);
            b bVar = this.g;
            AnonymousClass2 r2 = new AnonymousClass2(bVar);
            r2.setInterpolator(d);
            r2.setDuration(666);
            r2.setAnimationListener(new AnonymousClass3(bVar));
            AnonymousClass4 r3 = new AnonymousClass4(bVar);
            r3.setRepeatCount(-1);
            r3.setRepeatMode(1);
            r3.setInterpolator(a);
            r3.setDuration(1333);
            r3.setAnimationListener(new AnonymousClass5(bVar));
            this.p = r2;
            this.l = r3;
            AppMethodBeat.o(24121);
        }

        /* access modifiers changed from: package-private */
        /* renamed from: cn.missfresh.module.base.widget.imageWatcher.ProgressView$a$3  reason: invalid class name */
        public class AnonymousClass3 implements Animation.AnimationListener {
            final /* synthetic */ b a;

            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationRepeat(Animation animation) {
            }

            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationStart(Animation animation) {
            }

            AnonymousClass3(b bVar) {
                this.a = bVar;
            }

            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationEnd(Animation animation) {
                AppMethodBeat.i(24092, false);
                this.a.a();
                this.a.j();
                this.a.a(false);
                a.this.k.startAnimation(a.this.l);
                AppMethodBeat.o(24092);
            }
        }

        /* access modifiers changed from: package-private */
        /* renamed from: cn.missfresh.module.base.widget.imageWatcher.ProgressView$a$4  reason: invalid class name */
        public class AnonymousClass4 extends Animation {
            final /* synthetic */ b a;

            AnonymousClass4(b bVar) {
                this.a = bVar;
            }

            @Override // android.view.animation.Animation
            public void applyTransformation(float f, Transformation transformation) {
                AppMethodBeat.i(24093, false);
                float f2 = this.a.f();
                float e = this.a.e();
                float i = this.a.i();
                this.a.c(f2 + ((0.8f - ((float) Math.toRadians(((double) this.a.c()) / (this.a.h() * 6.283185307179586d)))) * a.c.getInterpolation(f)));
                this.a.b(e + (a.b.getInterpolation(f) * 0.8f));
                this.a.d(i + (0.25f * f));
                a.this.a((f * 144.0f) + ((a.this.m / 5.0f) * 720.0f));
                AppMethodBeat.o(24093);
            }
        }

        /* access modifiers changed from: package-private */
        /* renamed from: cn.missfresh.module.base.widget.imageWatcher.ProgressView$a$5  reason: invalid class name */
        public class AnonymousClass5 implements Animation.AnimationListener {
            final /* synthetic */ b a;

            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationEnd(Animation animation) {
            }

            AnonymousClass5(b bVar) {
                this.a = bVar;
            }

            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationStart(Animation animation) {
                AppMethodBeat.i(24094, false);
                a.this.m = 0.0f;
                AppMethodBeat.o(24094);
            }

            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationRepeat(Animation animation) {
                AppMethodBeat.i(24095, false);
                this.a.j();
                this.a.a();
                b bVar = this.a;
                bVar.b(bVar.g());
                a aVar = a.this;
                aVar.m = (aVar.m + 1.0f) % 5.0f;
                AppMethodBeat.o(24095);
            }
        }

        /* access modifiers changed from: private */
        public static class b {
            private final RectF a = new RectF();
            private final Paint b = new Paint();
            private final Paint c = new Paint();
            private final Drawable.Callback d;
            private final Paint e = new Paint();
            private float f = 0.0f;
            private float g = 0.0f;
            private float h = 0.0f;
            private float i = 5.0f;
            private float j = 2.5f;
            private int[] k;
            private int l;
            private float m;
            private float n;
            private float o;
            private boolean p;
            private float q;
            private double r;
            private int s;

            public b(Drawable.Callback callback) {
                AppMethodBeat.i(24097, false);
                this.d = callback;
                this.b.setStrokeCap(Paint.Cap.SQUARE);
                this.b.setAntiAlias(true);
                this.b.setStyle(Paint.Style.STROKE);
                this.c.setStyle(Paint.Style.FILL);
                this.c.setAntiAlias(true);
                this.e.setAntiAlias(true);
                AppMethodBeat.o(24097);
            }

            public void a(Canvas canvas, Rect rect) {
                AppMethodBeat.i(24098, false);
                RectF rectF = this.a;
                rectF.set(rect);
                float f = this.j;
                rectF.inset(f, f);
                float f2 = this.f;
                float f3 = this.h;
                float f4 = (f2 + f3) * 360.0f;
                float f5 = ((this.g + f3) * 360.0f) - f4;
                this.b.setColor(this.k[this.l]);
                this.b.setAlpha(this.s);
                canvas.drawArc(rectF, f4, f5, false, this.b);
                AppMethodBeat.o(24098);
            }

            public void a(int[] iArr) {
                AppMethodBeat.i(24099, false);
                this.k = iArr;
                a(0);
                AppMethodBeat.o(24099);
            }

            public void a(int i) {
                this.l = i;
            }

            public void a() {
                this.l = (this.l + 1) % this.k.length;
            }

            public void a(ColorFilter colorFilter) {
                AppMethodBeat.i(24100, false);
                this.b.setColorFilter(colorFilter);
                l();
                AppMethodBeat.o(24100);
            }

            public int b() {
                return this.s;
            }

            public void b(int i) {
                this.s = i;
            }

            public float c() {
                return this.i;
            }

            public void a(float f) {
                AppMethodBeat.i(24101, false);
                this.i = f;
                this.b.setStrokeWidth(f);
                l();
                AppMethodBeat.o(24101);
            }

            public float d() {
                return this.f;
            }

            public void b(float f) {
                AppMethodBeat.i(24102, false);
                this.f = f;
                l();
                AppMethodBeat.o(24102);
            }

            public float e() {
                return this.m;
            }

            public float f() {
                return this.n;
            }

            public float g() {
                return this.g;
            }

            public void c(float f) {
                AppMethodBeat.i(24103, false);
                this.g = f;
                l();
                AppMethodBeat.o(24103);
            }

            public void d(float f) {
                AppMethodBeat.i(24104, false);
                this.h = f;
                l();
                AppMethodBeat.o(24104);
            }

            public void a(int i, int i2) {
                double d;
                AppMethodBeat.i(24105, false);
                float min = (float) Math.min(i, i2);
                double d2 = this.r;
                if (d2 <= 0.0d || min < 0.0f) {
                    d = Math.ceil((double) (this.i / 2.0f));
                } else {
                    d = ((double) (min / 2.0f)) - d2;
                }
                this.j = (float) d;
                AppMethodBeat.o(24105);
            }

            public double h() {
                return this.r;
            }

            public void a(double d) {
                this.r = d;
            }

            public void a(boolean z) {
                AppMethodBeat.i(24106, false);
                if (this.p != z) {
                    this.p = z;
                    l();
                }
                AppMethodBeat.o(24106);
            }

            public void e(float f) {
                AppMethodBeat.i(24107, false);
                if (f != this.q) {
                    this.q = f;
                    l();
                }
                AppMethodBeat.o(24107);
            }

            public float i() {
                return this.o;
            }

            public void j() {
                this.m = this.f;
                this.n = this.g;
                this.o = this.h;
            }

            public void k() {
                AppMethodBeat.i(24108, false);
                this.m = 0.0f;
                this.n = 0.0f;
                this.o = 0.0f;
                b(0.0f);
                c(0.0f);
                d(0.0f);
                AppMethodBeat.o(24108);
            }

            private void l() {
                AppMethodBeat.i(24109, false);
                this.d.invalidateDrawable(null);
                AppMethodBeat.o(24109);
            }
        }

        /* renamed from: cn.missfresh.module.base.widget.imageWatcher.ProgressView$a$a  reason: collision with other inner class name */
        private static class C0032a extends AccelerateDecelerateInterpolator {
            private C0032a() {
            }

            @Override // android.view.animation.AccelerateDecelerateInterpolator, android.animation.TimeInterpolator
            public float getInterpolation(float f) {
                AppMethodBeat.i(24096, false);
                float interpolation = super.getInterpolation(Math.max(0.0f, (f - 0.5f) * 2.0f));
                AppMethodBeat.o(24096);
                return interpolation;
            }
        }

        private static class c extends AccelerateDecelerateInterpolator {
            private c() {
            }

            @Override // android.view.animation.AccelerateDecelerateInterpolator, android.animation.TimeInterpolator
            public float getInterpolation(float f) {
                AppMethodBeat.i(24110, false);
                float interpolation = super.getInterpolation(Math.min(1.0f, f * 2.0f));
                AppMethodBeat.o(24110);
                return interpolation;
            }
        }
    }
}
