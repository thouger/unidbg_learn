package cn.missfresh.ui.skeleton;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ComposeShader;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.Shader;
import android.location.GnssNavigationMessage;
import android.os.Build;
import android.util.AttributeSet;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import cn.missfresh.ui.R;

public class ShimmerLayout extends FrameLayout {
    private int a;
    private Rect b;
    private Paint c;
    private ValueAnimator d;
    private Bitmap e;
    private Bitmap f;
    private Canvas g;
    private boolean h;
    private boolean i;
    private boolean j;
    private int k;
    private int l;
    private int m;
    private float n;
    private float o;
    private ViewTreeObserver.OnPreDrawListener p;

    public ShimmerLayout(Context context) {
        this(context, null);
    }

    public ShimmerLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public ShimmerLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        AppMethodBeat.i(1522, false);
        setWillNotDraw(false);
        TypedArray obtainStyledAttributes = context.getTheme().obtainStyledAttributes(attributeSet, R.styleable.ShimmerLayout, 0, 0);
        try {
            this.m = obtainStyledAttributes.getInteger(R.styleable.ShimmerLayout_shimmer_angle, 20);
            this.k = obtainStyledAttributes.getInteger(R.styleable.ShimmerLayout_shimmer_animation_duration, 1500);
            this.l = obtainStyledAttributes.getColor(R.styleable.ShimmerLayout_shimmer_color, a(R.color.shimmer_color));
            this.j = obtainStyledAttributes.getBoolean(R.styleable.ShimmerLayout_shimmer_auto_start, false);
            this.n = obtainStyledAttributes.getFloat(R.styleable.ShimmerLayout_shimmer_mask_width, 0.5f);
            this.o = obtainStyledAttributes.getFloat(R.styleable.ShimmerLayout_shimmer_gradient_center_color_width, 0.1f);
            this.h = obtainStyledAttributes.getBoolean(R.styleable.ShimmerLayout_shimmer_reverse_animation, false);
            obtainStyledAttributes.recycle();
            setMaskWidth(this.n);
            setGradientCenterColorWidth(this.o);
            setShimmerAngle(this.m);
            if (this.j && getVisibility() == 0) {
                a();
            }
            AppMethodBeat.o(1522);
        } catch (Throwable th) {
            obtainStyledAttributes.recycle();
            AppMethodBeat.o(1522);
            throw th;
        }
    }

    /* access modifiers changed from: protected */
    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        AppMethodBeat.i(1526, false);
        d();
        super.onDetachedFromWindow();
        AppMethodBeat.o(1526);
    }

    /* access modifiers changed from: protected */
    @Override // android.view.ViewGroup, android.view.View
    public void dispatchDraw(Canvas canvas) {
        AppMethodBeat.i(1529, false);
        try {
            if (this.i && getWidth() > 0) {
                if (getHeight() > 0) {
                    a(canvas);
                    AppMethodBeat.o(1529);
                }
            }
            super.dispatchDraw(canvas);
        } catch (Exception e) {
            e.printStackTrace();
        }
        AppMethodBeat.o(1529);
    }

    @Override // android.view.View
    public void setVisibility(int i) {
        AppMethodBeat.i(1533, false);
        super.setVisibility(i);
        if (i != 0) {
            b();
        } else if (this.j) {
            a();
        }
        AppMethodBeat.o(1533);
    }

    public void a() {
        AppMethodBeat.i(GnssNavigationMessage.TYPE_GAL_I, false);
        if (this.i) {
            AppMethodBeat.o(GnssNavigationMessage.TYPE_GAL_I);
        } else if (getWidth() == 0) {
            this.p = new AnonymousClass1();
            getViewTreeObserver().addOnPreDrawListener(this.p);
            AppMethodBeat.o(GnssNavigationMessage.TYPE_GAL_I);
        } else {
            getShimmerAnimation().start();
            this.i = true;
            AppMethodBeat.o(GnssNavigationMessage.TYPE_GAL_I);
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: cn.missfresh.ui.skeleton.ShimmerLayout$1  reason: invalid class name */
    public class AnonymousClass1 implements ViewTreeObserver.OnPreDrawListener {
        AnonymousClass1() {
        }

        @Override // android.view.ViewTreeObserver.OnPreDrawListener
        public boolean onPreDraw() {
            AppMethodBeat.i(1495, false);
            ShimmerLayout.this.getViewTreeObserver().removeOnPreDrawListener(this);
            ShimmerLayout.this.a();
            AppMethodBeat.o(1495);
            return true;
        }
    }

    public void b() {
        AppMethodBeat.i(1546, false);
        if (this.p != null) {
            getViewTreeObserver().removeOnPreDrawListener(this.p);
        }
        d();
        AppMethodBeat.o(1546);
    }

    public void setShimmerColor(int i) {
        AppMethodBeat.i(1548, false);
        this.l = i;
        c();
        AppMethodBeat.o(1548);
    }

    public void setShimmerAnimationDuration(int i) {
        AppMethodBeat.i(1758, false);
        this.k = i;
        c();
        AppMethodBeat.o(1758);
    }

    public void setAnimationReversed(boolean z) {
        AppMethodBeat.i(1759, false);
        this.h = z;
        c();
        AppMethodBeat.o(1759);
    }

    public void setShimmerAngle(int i) {
        AppMethodBeat.i(1760, false);
        if (i < -45 || 45 < i) {
            IllegalArgumentException illegalArgumentException = new IllegalArgumentException(String.format("shimmerAngle value must be between %d and %d", (byte) -45, (byte) 45));
            AppMethodBeat.o(1760);
            throw illegalArgumentException;
        }
        this.m = i;
        c();
        AppMethodBeat.o(1760);
    }

    public void setMaskWidth(float f) {
        AppMethodBeat.i(1761, false);
        if (f <= 0.0f || 1.0f < f) {
            IllegalArgumentException illegalArgumentException = new IllegalArgumentException(String.format("maskWidth value must be higher than %d and less or equal to %d", (byte) 0, (byte) 1));
            AppMethodBeat.o(1761);
            throw illegalArgumentException;
        }
        this.n = f;
        c();
        AppMethodBeat.o(1761);
    }

    public void setGradientCenterColorWidth(float f) {
        AppMethodBeat.i(1762, false);
        if (f <= 0.0f || 1.0f <= f) {
            IllegalArgumentException illegalArgumentException = new IllegalArgumentException(String.format("gradientCenterColorWidth value must be higher than %d and less than %d", (byte) 0, (byte) 1));
            AppMethodBeat.o(1762);
            throw illegalArgumentException;
        }
        this.o = f;
        c();
        AppMethodBeat.o(1762);
    }

    private void c() {
        AppMethodBeat.i(1763, false);
        if (this.i) {
            d();
            a();
        }
        AppMethodBeat.o(1763);
    }

    private void a(Canvas canvas) {
        AppMethodBeat.i(1764, false);
        super.dispatchDraw(canvas);
        this.e = getMaskBitmap();
        Bitmap bitmap = this.e;
        if (bitmap == null) {
            AppMethodBeat.o(1764);
            return;
        }
        if (this.g == null) {
            this.g = new Canvas(bitmap);
        }
        this.g.drawColor(0, PorterDuff.Mode.CLEAR);
        this.g.save();
        this.g.translate((float) (-this.a), 0.0f);
        super.dispatchDraw(this.g);
        this.g.restore();
        b(canvas);
        this.e = null;
        AppMethodBeat.o(1764);
    }

    private void b(Canvas canvas) {
        AppMethodBeat.i(1765, false);
        f();
        canvas.save();
        canvas.translate((float) this.a, 0.0f);
        canvas.drawRect((float) this.b.left, 0.0f, (float) this.b.width(), (float) this.b.height(), this.c);
        canvas.restore();
        AppMethodBeat.o(1765);
    }

    private void d() {
        AppMethodBeat.i(1766, false);
        ValueAnimator valueAnimator = this.d;
        if (valueAnimator != null) {
            valueAnimator.end();
            this.d.removeAllUpdateListeners();
        }
        this.d = null;
        this.c = null;
        this.i = false;
        e();
        AppMethodBeat.o(1766);
    }

    private void e() {
        AppMethodBeat.i(1767, false);
        this.g = null;
        Bitmap bitmap = this.f;
        if (bitmap != null) {
            bitmap.recycle();
            this.f = null;
        }
        AppMethodBeat.o(1767);
    }

    private Bitmap getMaskBitmap() {
        AppMethodBeat.i(1768, false);
        if (this.f == null) {
            this.f = a(this.b.width(), getHeight());
        }
        Bitmap bitmap = this.f;
        AppMethodBeat.o(1768);
        return bitmap;
    }

    private void f() {
        AppMethodBeat.i(1769, false);
        if (this.c != null) {
            AppMethodBeat.o(1769);
            return;
        }
        int b = b(this.l);
        float width = ((float) (getWidth() / 2)) * this.n;
        float height = this.m >= 0 ? (float) getHeight() : 0.0f;
        int i = this.l;
        ComposeShader composeShader = new ComposeShader(new LinearGradient(0.0f, height, ((float) Math.cos(Math.toRadians((double) this.m))) * width, height + (((float) Math.sin(Math.toRadians((double) this.m))) * width), new int[]{b, i, i, b}, getGradientColorDistribution(), Shader.TileMode.CLAMP), new BitmapShader(this.e, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP), PorterDuff.Mode.DST_IN);
        this.c = new Paint();
        this.c.setAntiAlias(true);
        this.c.setDither(true);
        this.c.setFilterBitmap(true);
        this.c.setShader(composeShader);
        AppMethodBeat.o(1769);
    }

    private Animator getShimmerAnimation() {
        int i;
        ValueAnimator valueAnimator;
        AppMethodBeat.i(1770, false);
        ValueAnimator valueAnimator2 = this.d;
        if (valueAnimator2 != null) {
            AppMethodBeat.o(1770);
            return valueAnimator2;
        }
        if (this.b == null) {
            this.b = g();
        }
        int width = getWidth();
        if (getWidth() > this.b.width()) {
            i = -width;
        } else {
            i = -this.b.width();
        }
        int width2 = this.b.width();
        int i2 = width - i;
        if (this.h) {
            valueAnimator = ValueAnimator.ofInt(i2, 0);
        } else {
            valueAnimator = ValueAnimator.ofInt(0, i2);
        }
        this.d = valueAnimator;
        this.d.setDuration((long) this.k);
        this.d.setRepeatCount(-1);
        this.d.addUpdateListener(new AnonymousClass2(i, width2));
        ValueAnimator valueAnimator3 = this.d;
        AppMethodBeat.o(1770);
        return valueAnimator3;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: cn.missfresh.ui.skeleton.ShimmerLayout$2  reason: invalid class name */
    public class AnonymousClass2 implements ValueAnimator.AnimatorUpdateListener {
        final /* synthetic */ int a;
        final /* synthetic */ int b;

        AnonymousClass2(int i, int i2) {
            this.a = i;
            this.b = i2;
        }

        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            AppMethodBeat.i(1505, false);
            ShimmerLayout.this.a = this.a + ((Integer) valueAnimator.getAnimatedValue()).intValue();
            if (ShimmerLayout.this.a + this.b >= 0) {
                ShimmerLayout.this.invalidate();
            }
            AppMethodBeat.o(1505);
        }
    }

    private Bitmap a(int i, int i2) {
        AppMethodBeat.i(1771, false);
        try {
            Bitmap createBitmap = Bitmap.createBitmap(i, i2, Bitmap.Config.ALPHA_8);
            AppMethodBeat.o(1771);
            return createBitmap;
        } catch (OutOfMemoryError unused) {
            System.gc();
            AppMethodBeat.o(1771);
            return null;
        }
    }

    private int a(int i) {
        AppMethodBeat.i(1772, false);
        if (Build.VERSION.SDK_INT >= 23) {
            int color = getContext().getColor(i);
            AppMethodBeat.o(1772);
            return color;
        }
        int color2 = getResources().getColor(i);
        AppMethodBeat.o(1772);
        return color2;
    }

    private int b(int i) {
        AppMethodBeat.i(1773, false);
        int argb = Color.argb(0, Color.red(i), Color.green(i), Color.blue(i));
        AppMethodBeat.o(1773);
        return argb;
    }

    private Rect g() {
        AppMethodBeat.i(1774, false);
        Rect rect = new Rect(0, 0, h(), getHeight());
        AppMethodBeat.o(1774);
        return rect;
    }

    private int h() {
        AppMethodBeat.i(1775, false);
        int width = (int) ((((double) (((float) (getWidth() / 2)) * this.n)) / Math.cos(Math.toRadians((double) Math.abs(this.m)))) + (((double) getHeight()) * Math.tan(Math.toRadians((double) Math.abs(this.m)))));
        AppMethodBeat.o(1775);
        return width;
    }

    private float[] getGradientColorDistribution() {
        float[] fArr = new float[4];
        fArr[0] = 0.0f;
        fArr[3] = 1.0f;
        float f = this.o;
        fArr[1] = 0.5f - (f / 2.0f);
        fArr[2] = (f / 2.0f) + 0.5f;
        return fArr;
    }
}
