package it.sephiroth.android.library.imagezoom;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.ImageView;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;

public abstract class ImageViewTouchBase extends ImageView {
    private float a;
    private float b;
    private boolean c;
    private boolean d;
    private int e;
    private int f;
    private PointF g;
    private boolean h;
    private boolean i;
    private a j;
    protected it.sephiroth.android.library.easing.b k;
    protected Matrix l;
    protected Matrix m;
    protected Matrix n;
    protected Handler o;
    protected Runnable p;
    protected boolean q;
    protected final Matrix r;
    protected final float[] s;
    protected DisplayType t;
    protected final int u;
    protected RectF v;
    protected RectF w;
    protected RectF x;
    private b y;

    public interface a {
        void a(Drawable drawable);
    }

    public interface b {
        void a(boolean z, int i, int i2, int i3, int i4);
    }

    /* access modifiers changed from: protected */
    public void a(float f) {
    }

    /* access modifiers changed from: protected */
    public void c(float f) {
    }

    /* access modifiers changed from: protected */
    public void d() {
    }

    @Override // android.view.View
    public float getRotation() {
        return 0.0f;
    }

    public enum DisplayType {
        NONE,
        FIT_TO_SCREEN,
        FIT_IF_BIGGER;

        public static DisplayType valueOf(String str) {
            AppMethodBeat.i(4549, false);
            DisplayType displayType = (DisplayType) Enum.valueOf(DisplayType.class, str);
            AppMethodBeat.o(4549);
            return displayType;
        }

        static {
            AppMethodBeat.i(4550, false);
            AppMethodBeat.o(4550);
        }
    }

    public ImageViewTouchBase(Context context) {
        this(context, null);
    }

    public ImageViewTouchBase(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public ImageViewTouchBase(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.k = new it.sephiroth.android.library.easing.a();
        this.l = new Matrix();
        this.m = new Matrix();
        this.o = new Handler();
        this.p = null;
        this.q = false;
        this.a = -1.0f;
        this.b = -1.0f;
        this.r = new Matrix();
        this.s = new float[9];
        this.e = -1;
        this.f = -1;
        this.g = new PointF();
        this.t = DisplayType.NONE;
        this.u = 200;
        this.v = new RectF();
        this.w = new RectF();
        this.x = new RectF();
        a(context, attributeSet, i);
    }

    public void setOnDrawableChangedListener(a aVar) {
        this.j = aVar;
    }

    public void setOnLayoutChangeListener(b bVar) {
        this.y = bVar;
    }

    /* access modifiers changed from: protected */
    public void a(Context context, AttributeSet attributeSet, int i) {
        setScaleType(ImageView.ScaleType.MATRIX);
    }

    @Override // android.widget.ImageView
    public void setScaleType(ImageView.ScaleType scaleType) {
        if (scaleType == ImageView.ScaleType.MATRIX) {
            super.setScaleType(scaleType);
        } else {
            Log.w("ImageViewTouchBase", "Unsupported scaletype. Only MATRIX can be used");
        }
    }

    public void setDisplayType(DisplayType displayType) {
        if (displayType != this.t) {
            this.q = false;
            this.t = displayType;
            this.h = true;
            requestLayout();
        }
    }

    public DisplayType getDisplayType() {
        return this.t;
    }

    /* access modifiers changed from: protected */
    public void setMinScale(float f) {
        this.b = f;
    }

    /* access modifiers changed from: protected */
    public void setMaxScale(float f) {
        this.a = f;
    }

    /* access modifiers changed from: protected */
    @Override // android.view.View
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        int i5;
        int i6;
        float f;
        super.onLayout(z, i, i2, i3, i4);
        if (z) {
            int i7 = this.e;
            int i8 = this.f;
            this.e = i3 - i;
            this.f = i4 - i2;
            int i9 = this.e;
            i6 = i9 - i7;
            int i10 = this.f;
            i5 = i10 - i8;
            PointF pointF = this.g;
            pointF.x = ((float) i9) / 2.0f;
            pointF.y = ((float) i10) / 2.0f;
        } else {
            i6 = 0;
            i5 = 0;
        }
        Runnable runnable = this.p;
        if (runnable != null) {
            this.p = null;
            runnable.run();
        }
        Drawable drawable = getDrawable();
        if (drawable == null) {
            if (this.i) {
                a(drawable);
            }
            if (z || this.i || this.h) {
                b(i, i2, i3, i4);
            }
            if (this.i) {
                this.i = false;
            }
            if (this.h) {
                this.h = false;
            }
        } else if (z || this.h || this.i) {
            a(this.t);
            float c = c(this.l);
            float scale = getScale();
            float f2 = 1.0f;
            float min = Math.min(1.0f, 1.0f / c);
            a(drawable, this.l);
            float c2 = c(this.l);
            if (this.i || this.h) {
                Matrix matrix = this.n;
                if (matrix != null) {
                    this.m.set(matrix);
                    this.n = null;
                    f = getScale();
                } else {
                    this.m.reset();
                    f = a(this.t);
                }
                f2 = f;
                setImageMatrix(getImageViewMatrix());
                if (f2 != getScale()) {
                    b(f2);
                }
            } else if (z) {
                if (!this.d) {
                    this.b = -1.0f;
                }
                if (!this.c) {
                    this.a = -1.0f;
                }
                setImageMatrix(getImageViewMatrix());
                b((float) (-i6), (float) (-i5));
                if (!this.q) {
                    f2 = a(this.t);
                    b(f2);
                } else {
                    if (((double) Math.abs(scale - min)) > 0.001d) {
                        f2 = (c / c2) * scale;
                    }
                    b(f2);
                }
            }
            this.q = false;
            if (f2 > getMaxScale() || f2 < getMinScale()) {
                b(f2);
            }
            a(true, true);
            if (this.i) {
                a(drawable);
            }
            if (z || this.i || this.h) {
                b(i, i2, i3, i4);
            }
            if (this.h) {
                this.h = false;
            }
            if (this.i) {
                this.i = false;
            }
        }
    }

    public void a() {
        this.m = new Matrix();
        float a2 = a(this.t);
        setImageMatrix(getImageViewMatrix());
        if (a2 != getScale()) {
            b(a2);
        }
        postInvalidate();
    }

    /* access modifiers changed from: protected */
    public float a(DisplayType displayType) {
        if (displayType == DisplayType.FIT_TO_SCREEN) {
            return 1.0f;
        }
        if (displayType == DisplayType.FIT_IF_BIGGER) {
            return Math.min(1.0f, 1.0f / c(this.l));
        }
        return 1.0f / c(this.l);
    }

    @Override // android.widget.ImageView
    public void setImageResource(int i) {
        setImageDrawable(getContext().getResources().getDrawable(i));
    }

    @Override // android.widget.ImageView
    public void setImageBitmap(Bitmap bitmap) {
        a(bitmap, (Matrix) null, -1.0f, -1.0f);
    }

    public void a(Bitmap bitmap, Matrix matrix, float f, float f2) {
        if (bitmap != null) {
            b(new it.sephiroth.android.library.imagezoom.a.a(bitmap), matrix, f, f2);
        } else {
            b((Drawable) null, matrix, f, f2);
        }
    }

    @Override // android.widget.ImageView
    public void setImageDrawable(Drawable drawable) {
        b(drawable, (Matrix) null, -1.0f, -1.0f);
    }

    public void b(Drawable drawable, Matrix matrix, float f, float f2) {
        if (getWidth() <= 0) {
            this.p = new AnonymousClass1(drawable, matrix, f, f2);
        } else {
            a(drawable, matrix, f, f2);
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: it.sephiroth.android.library.imagezoom.ImageViewTouchBase$1  reason: invalid class name */
    public class AnonymousClass1 implements Runnable {
        final /* synthetic */ Drawable a;
        final /* synthetic */ Matrix b;
        final /* synthetic */ float c;
        final /* synthetic */ float d;

        AnonymousClass1(Drawable drawable, Matrix matrix, float f, float f2) {
            this.a = drawable;
            this.b = matrix;
            this.c = f;
            this.d = f2;
        }

        @Override // java.lang.Runnable
        public void run() {
            AppMethodBeat.i(4528, false);
            ImageViewTouchBase.this.b(this.a, this.b, this.c, this.d);
            AppMethodBeat.o(4528);
        }
    }

    /* access modifiers changed from: protected */
    public void a(Drawable drawable, Matrix matrix, float f, float f2) {
        if (drawable != null) {
            super.setImageDrawable(drawable);
        } else {
            this.l.reset();
            super.setImageDrawable(null);
        }
        if (f == -1.0f || f2 == -1.0f) {
            this.b = -1.0f;
            this.a = -1.0f;
            this.d = false;
            this.c = false;
        } else {
            float min = Math.min(f, f2);
            float max = Math.max(min, f2);
            this.b = min;
            this.a = max;
            this.d = true;
            this.c = true;
            if (this.t == DisplayType.FIT_TO_SCREEN || this.t == DisplayType.FIT_IF_BIGGER) {
                if (this.b >= 1.0f) {
                    this.d = false;
                    this.b = -1.0f;
                }
                if (this.a <= 1.0f) {
                    this.c = true;
                    this.a = -1.0f;
                }
            }
        }
        if (matrix != null) {
            this.n = new Matrix(matrix);
        }
        this.i = true;
        requestLayout();
    }

    /* access modifiers changed from: protected */
    public void a(Drawable drawable) {
        b(drawable);
    }

    /* access modifiers changed from: protected */
    public void a(int i, int i2, int i3, int i4) {
        b bVar = this.y;
        if (bVar != null) {
            bVar.a(true, i, i2, i3, i4);
        }
    }

    /* access modifiers changed from: protected */
    public void b(Drawable drawable) {
        a aVar = this.j;
        if (aVar != null) {
            aVar.a(drawable);
        }
    }

    /* access modifiers changed from: protected */
    public void b(int i, int i2, int i3, int i4) {
        a(i, i2, i3, i4);
    }

    /* access modifiers changed from: protected */
    public float b() {
        Drawable drawable = getDrawable();
        if (drawable == null) {
            return 1.0f;
        }
        return Math.max(((float) drawable.getIntrinsicWidth()) / ((float) this.e), ((float) drawable.getIntrinsicHeight()) / ((float) this.f)) * 8.0f;
    }

    /* access modifiers changed from: protected */
    public float c() {
        if (getDrawable() == null) {
            return 1.0f;
        }
        return Math.min(1.0f, 1.0f / c(this.l));
    }

    public float getMaxScale() {
        if (this.a == -1.0f) {
            this.a = b();
        }
        return this.a;
    }

    public float getMinScale() {
        if (this.b == -1.0f) {
            this.b = c();
        }
        return this.b;
    }

    public Matrix getImageViewMatrix() {
        return a(this.m);
    }

    public Matrix a(Matrix matrix) {
        this.r.set(this.l);
        this.r.postConcat(matrix);
        return this.r;
    }

    @Override // android.widget.ImageView
    public void setImageMatrix(Matrix matrix) {
        Matrix imageMatrix = getImageMatrix();
        boolean z = (matrix == null && !imageMatrix.isIdentity()) || (matrix != null && !imageMatrix.equals(matrix));
        super.setImageMatrix(matrix);
        if (z) {
            d();
        }
    }

    public Matrix getDisplayMatrix() {
        return new Matrix(this.m);
    }

    /* access modifiers changed from: protected */
    public void a(Drawable drawable, Matrix matrix) {
        float f = (float) this.e;
        float f2 = (float) this.f;
        float intrinsicWidth = (float) drawable.getIntrinsicWidth();
        float intrinsicHeight = (float) drawable.getIntrinsicHeight();
        matrix.reset();
        if (intrinsicWidth > f || intrinsicHeight > f2) {
            float min = Math.min(f / intrinsicWidth, f2 / intrinsicHeight);
            matrix.postScale(min, min);
            matrix.postTranslate((f - (intrinsicWidth * min)) / 2.0f, (f2 - (intrinsicHeight * min)) / 2.0f);
            return;
        }
        float min2 = Math.min(f / intrinsicWidth, f2 / intrinsicHeight);
        matrix.postScale(min2, min2);
        matrix.postTranslate((f - (intrinsicWidth * min2)) / 2.0f, (f2 - (intrinsicHeight * min2)) / 2.0f);
    }

    /* access modifiers changed from: protected */
    public float a(Matrix matrix, int i) {
        matrix.getValues(this.s);
        return this.s[i];
    }

    public RectF getBitmapRect() {
        return b(this.m);
    }

    /* access modifiers changed from: protected */
    public RectF b(Matrix matrix) {
        Drawable drawable = getDrawable();
        if (drawable == null) {
            return null;
        }
        Matrix a2 = a(matrix);
        this.v.set(0.0f, 0.0f, (float) drawable.getIntrinsicWidth(), (float) drawable.getIntrinsicHeight());
        a2.mapRect(this.v);
        return this.v;
    }

    /* access modifiers changed from: protected */
    public float c(Matrix matrix) {
        return a(matrix, 0);
    }

    public float getScale() {
        return c(this.m);
    }

    public float getBaseScale() {
        return c(this.l);
    }

    /* access modifiers changed from: protected */
    public void a(boolean z, boolean z2) {
        if (getDrawable() != null) {
            RectF a2 = a(this.m, z, z2);
            if (a2.left != 0.0f || a2.top != 0.0f) {
                b(a2.left, a2.top);
            }
        }
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x0048  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public android.graphics.RectF a(android.graphics.Matrix r6, boolean r7, boolean r8) {
        /*
            r5 = this;
            android.graphics.drawable.Drawable r0 = r5.getDrawable()
            r1 = 0
            if (r0 != 0) goto L_0x000d
            android.graphics.RectF r6 = new android.graphics.RectF
            r6.<init>(r1, r1, r1, r1)
            return r6
        L_0x000d:
            android.graphics.RectF r0 = r5.w
            r0.set(r1, r1, r1, r1)
            android.graphics.RectF r6 = r5.b(r6)
            float r0 = r6.height()
            float r2 = r6.width()
            r3 = 1073741824(0x40000000, float:2.0)
            if (r8 == 0) goto L_0x0045
            int r8 = r5.f
            float r8 = (float) r8
            int r4 = (r0 > r8 ? 1 : (r0 == r8 ? 0 : -1))
            if (r4 >= 0) goto L_0x002f
            float r8 = r8 - r0
            float r8 = r8 / r3
            float r0 = r6.top
        L_0x002d:
            float r8 = r8 - r0
            goto L_0x0046
        L_0x002f:
            float r0 = r6.top
            int r0 = (r0 > r1 ? 1 : (r0 == r1 ? 0 : -1))
            if (r0 <= 0) goto L_0x0039
            float r8 = r6.top
            float r8 = -r8
            goto L_0x0046
        L_0x0039:
            float r0 = r6.bottom
            int r8 = (r0 > r8 ? 1 : (r0 == r8 ? 0 : -1))
            if (r8 >= 0) goto L_0x0045
            int r8 = r5.f
            float r8 = (float) r8
            float r0 = r6.bottom
            goto L_0x002d
        L_0x0045:
            r8 = r1
        L_0x0046:
            if (r7 == 0) goto L_0x0069
            int r7 = r5.e
            float r7 = (float) r7
            int r0 = (r2 > r7 ? 1 : (r2 == r7 ? 0 : -1))
            if (r0 >= 0) goto L_0x0056
            float r7 = r7 - r2
            float r7 = r7 / r3
            float r6 = r6.left
        L_0x0053:
            float r6 = r7 - r6
            goto L_0x006a
        L_0x0056:
            float r0 = r6.left
            int r0 = (r0 > r1 ? 1 : (r0 == r1 ? 0 : -1))
            if (r0 <= 0) goto L_0x0060
            float r6 = r6.left
            float r6 = -r6
            goto L_0x006a
        L_0x0060:
            float r0 = r6.right
            int r0 = (r0 > r7 ? 1 : (r0 == r7 ? 0 : -1))
            if (r0 >= 0) goto L_0x0069
            float r6 = r6.right
            goto L_0x0053
        L_0x0069:
            r6 = r1
        L_0x006a:
            android.graphics.RectF r7 = r5.w
            r7.set(r6, r8, r1, r1)
            android.graphics.RectF r6 = r5.w
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: it.sephiroth.android.library.imagezoom.ImageViewTouchBase.a(android.graphics.Matrix, boolean, boolean):android.graphics.RectF");
    }

    /* access modifiers changed from: protected */
    public void b(float f, float f2) {
        if (f != 0.0f || f2 != 0.0f) {
            this.m.postTranslate(f, f2);
            setImageMatrix(getImageViewMatrix());
        }
    }

    /* access modifiers changed from: protected */
    public void a(float f, float f2, float f3) {
        this.m.postScale(f, f, f2, f3);
        setImageMatrix(getImageViewMatrix());
    }

    /* access modifiers changed from: protected */
    public PointF getCenter() {
        return this.g;
    }

    /* access modifiers changed from: protected */
    public void b(float f) {
        if (f > getMaxScale()) {
            f = getMaxScale();
        }
        if (f < getMinScale()) {
            f = getMinScale();
        }
        PointF center = getCenter();
        b(f, center.x, center.y);
    }

    public void c(float f, float f2) {
        PointF center = getCenter();
        a(f, center.x, center.y, f2);
    }

    /* access modifiers changed from: protected */
    public void b(float f, float f2, float f3) {
        if (f > getMaxScale()) {
            f = getMaxScale();
        }
        a(f / getScale(), f2, f3);
        c(getScale());
        a(true, true);
    }

    public void d(float f, float f2) {
        a((double) f, (double) f2);
    }

    /* access modifiers changed from: protected */
    public void a(double d, double d2) {
        RectF bitmapRect = getBitmapRect();
        this.x.set((float) d, (float) d2, 0.0f, 0.0f);
        a(bitmapRect, this.x);
        b(this.x.left, this.x.top);
        a(true, true);
    }

    /* access modifiers changed from: protected */
    public void a(RectF rectF, RectF rectF2) {
        if (rectF != null) {
            if (rectF.top >= 0.0f && rectF.bottom <= ((float) this.f)) {
                rectF2.top = 0.0f;
            }
            if (rectF.left >= 0.0f && rectF.right <= ((float) this.e)) {
                rectF2.left = 0.0f;
            }
            if (rectF.top + rectF2.top >= 0.0f && rectF.bottom > ((float) this.f)) {
                rectF2.top = (float) ((int) (0.0f - rectF.top));
            }
            if (rectF.bottom + rectF2.top <= ((float) (this.f + 0)) && rectF.top < 0.0f) {
                rectF2.top = (float) ((int) (((float) (this.f + 0)) - rectF.bottom));
            }
            if (rectF.left + rectF2.left >= 0.0f) {
                rectF2.left = (float) ((int) (0.0f - rectF.left));
            }
            float f = rectF.right + rectF2.left;
            int i = this.e;
            if (f <= ((float) (i + 0))) {
                rectF2.left = (float) ((int) (((float) (i + 0)) - rectF.right));
            }
        }
    }

    /* renamed from: it.sephiroth.android.library.imagezoom.ImageViewTouchBase$2  reason: invalid class name */
    class AnonymousClass2 implements Runnable {
        double a = 0.0d;
        double b = 0.0d;
        final /* synthetic */ double c;
        final /* synthetic */ long d;
        final /* synthetic */ double e;
        final /* synthetic */ double f;

        AnonymousClass2(double d, long j, double d2, double d3) {
            this.c = d;
            this.d = j;
            this.e = d2;
            this.f = d3;
        }

        @Override // java.lang.Runnable
        public void run() {
            AppMethodBeat.i(4538, false);
            double min = Math.min(this.c, (double) (System.currentTimeMillis() - this.d));
            double a = ImageViewTouchBase.this.k.a(min, 0.0d, this.e, this.c);
            double a2 = ImageViewTouchBase.this.k.a(min, 0.0d, this.f, this.c);
            ImageViewTouchBase.this.a(a - this.a, a2 - this.b);
            this.a = a;
            this.b = a2;
            if (min < this.c) {
                ImageViewTouchBase.this.o.post(this);
            } else {
                ImageViewTouchBase imageViewTouchBase = ImageViewTouchBase.this;
                RectF a3 = imageViewTouchBase.a(imageViewTouchBase.m, true, true);
                if (!(a3.left == 0.0f && a3.top == 0.0f)) {
                    ImageViewTouchBase.this.d(a3.left, a3.top);
                }
            }
            AppMethodBeat.o(4538);
        }
    }

    /* access modifiers changed from: protected */
    public void a(float f, float f2, double d) {
        this.o.post(new AnonymousClass2(d, System.currentTimeMillis(), (double) f, (double) f2));
    }

    /* access modifiers changed from: protected */
    public void a(float f, float f2, float f3, float f4) {
        if (f > getMaxScale()) {
            f = getMaxScale();
        }
        long currentTimeMillis = System.currentTimeMillis();
        float scale = getScale();
        Matrix matrix = new Matrix(this.m);
        matrix.postScale(f, f, f2, f3);
        RectF a2 = a(matrix, true, true);
        this.o.post(new AnonymousClass3(f4, currentTimeMillis, f - scale, scale, f2 + (a2.left * f), f3 + (a2.top * f)));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: it.sephiroth.android.library.imagezoom.ImageViewTouchBase$3  reason: invalid class name */
    public class AnonymousClass3 implements Runnable {
        final /* synthetic */ float a;
        final /* synthetic */ long b;
        final /* synthetic */ float c;
        final /* synthetic */ float d;
        final /* synthetic */ float e;
        final /* synthetic */ float f;

        AnonymousClass3(float f, long j, float f2, float f3, float f4, float f5) {
            this.a = f;
            this.b = j;
            this.c = f2;
            this.d = f3;
            this.e = f4;
            this.f = f5;
        }

        @Override // java.lang.Runnable
        public void run() {
            AppMethodBeat.i(4545, false);
            float min = Math.min(this.a, (float) (System.currentTimeMillis() - this.b));
            ImageViewTouchBase.this.b(this.d + ((float) ImageViewTouchBase.this.k.b((double) min, 0.0d, (double) this.c, (double) this.a)), this.e, this.f);
            if (min < this.a) {
                ImageViewTouchBase.this.o.post(this);
            } else {
                ImageViewTouchBase imageViewTouchBase = ImageViewTouchBase.this;
                imageViewTouchBase.a(imageViewTouchBase.getScale());
                ImageViewTouchBase.this.a(true, true);
            }
            AppMethodBeat.o(4545);
        }
    }
}
