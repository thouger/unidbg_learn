package com.sobot.chat.widget.photoview;

import android.content.Context;
import android.graphics.Matrix;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import com.sobot.chat.widget.photoview.VersionedGestureDetector;
import java.lang.ref.WeakReference;

/* compiled from: PhotoViewAttacher */
public class b implements GestureDetector.OnDoubleTapListener, View.OnTouchListener, ViewTreeObserver.OnGlobalLayoutListener, VersionedGestureDetector.a {
    static final boolean a = Log.isLoggable("PhotoViewAttacher", 3);
    private float b = 1.0f;
    private float c = 1.75f;
    private float d = 3.0f;
    private boolean e = true;
    private WeakReference<ImageView> f;
    private ViewTreeObserver g;
    private GestureDetector h;
    private VersionedGestureDetector i;
    private final Matrix j = new Matrix();
    private final Matrix k = new Matrix();
    private final Matrix l = new Matrix();
    private final RectF m = new RectF();
    private final float[] n = new float[9];
    private c o;
    private d p;
    private e q;
    private View.OnLongClickListener r;
    private int s;
    private int t;
    private int u;
    private int v;
    private RunnableC0148b w;
    private int x = 2;
    private boolean y;
    private ImageView.ScaleType z = ImageView.ScaleType.FIT_CENTER;

    /* compiled from: PhotoViewAttacher */
    public interface c {
        void a(RectF rectF);
    }

    /* compiled from: PhotoViewAttacher */
    public interface d {
        void a(View view, float f, float f2);
    }

    /* compiled from: PhotoViewAttacher */
    public interface e {
        void a(View view, float f, float f2);
    }

    @Override // android.view.GestureDetector.OnDoubleTapListener
    public final boolean onDoubleTapEvent(MotionEvent motionEvent) {
        return false;
    }

    private static void c(float f, float f2, float f3) {
        if (f >= f2) {
            throw new IllegalArgumentException("MinZoom should be less than MidZoom");
        } else if (f2 >= f3) {
            throw new IllegalArgumentException("MidZoom should be less than MaxZoom");
        }
    }

    private static boolean a(ImageView imageView) {
        return (imageView == null || imageView.getDrawable() == null) ? false : true;
    }

    /* compiled from: PhotoViewAttacher */
    /* access modifiers changed from: package-private */
    /* renamed from: com.sobot.chat.widget.photoview.b$2  reason: invalid class name */
    public static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] a = new int[ImageView.ScaleType.values().length];

        static {
            try {
                a[ImageView.ScaleType.MATRIX.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                a[ImageView.ScaleType.FIT_START.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                a[ImageView.ScaleType.FIT_END.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                a[ImageView.ScaleType.FIT_CENTER.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                a[ImageView.ScaleType.FIT_XY.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
        }
    }

    private static boolean b(ImageView.ScaleType scaleType) {
        if (scaleType == null) {
            return false;
        }
        if (AnonymousClass2.a[scaleType.ordinal()] != 1) {
            return true;
        }
        throw new IllegalArgumentException(scaleType.name() + " is not supported in PhotoView");
    }

    private static void b(ImageView imageView) {
        if (imageView != null && !(imageView instanceof PhotoView)) {
            imageView.setScaleType(ImageView.ScaleType.MATRIX);
        }
    }

    public b(ImageView imageView) {
        this.f = new WeakReference<>(imageView);
        imageView.setOnTouchListener(this);
        this.g = imageView.getViewTreeObserver();
        this.g.addOnGlobalLayoutListener(this);
        b(imageView);
        if (!imageView.isInEditMode()) {
            this.i = VersionedGestureDetector.a(imageView.getContext(), this);
            this.h = new GestureDetector(imageView.getContext(), new AnonymousClass1());
            this.h.setOnDoubleTapListener(this);
            b(true);
        }
    }

    /* compiled from: PhotoViewAttacher */
    /* renamed from: com.sobot.chat.widget.photoview.b$1  reason: invalid class name */
    class AnonymousClass1 extends GestureDetector.SimpleOnGestureListener {
        AnonymousClass1() {
        }

        @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
        public void onLongPress(MotionEvent motionEvent) {
            if (b.this.r != null) {
                b.this.r.onLongClick((View) b.this.f.get());
            }
        }
    }

    public final void a() {
        if (Build.VERSION.SDK_INT >= 16) {
            WeakReference<ImageView> weakReference = this.f;
            if (weakReference != null) {
                weakReference.get().getViewTreeObserver().removeOnGlobalLayoutListener(this);
            }
            ViewTreeObserver viewTreeObserver = this.g;
            if (viewTreeObserver != null && viewTreeObserver.isAlive()) {
                this.g.removeOnGlobalLayoutListener(this);
                this.g = null;
                this.o = null;
                this.p = null;
                this.q = null;
                this.f = null;
                return;
            }
            return;
        }
        WeakReference<ImageView> weakReference2 = this.f;
        if (weakReference2 != null) {
            weakReference2.get().getViewTreeObserver().removeGlobalOnLayoutListener(this);
        }
        ViewTreeObserver viewTreeObserver2 = this.g;
        if (viewTreeObserver2 != null && viewTreeObserver2.isAlive()) {
            this.g.removeGlobalOnLayoutListener(this);
            this.g = null;
            this.o = null;
            this.p = null;
            this.q = null;
            this.f = null;
        }
    }

    public final RectF b() {
        n();
        return a(j());
    }

    public final ImageView c() {
        WeakReference<ImageView> weakReference = this.f;
        ImageView imageView = weakReference != null ? weakReference.get() : null;
        if (imageView != null) {
            return imageView;
        }
        a();
        throw new IllegalStateException("ImageView no longer exists. You should not use this PhotoViewAttacher any more.");
    }

    public float d() {
        return this.b;
    }

    public float e() {
        return this.c;
    }

    public float f() {
        return this.d;
    }

    public final float g() {
        return a(this.l, 0);
    }

    public final ImageView.ScaleType h() {
        return this.z;
    }

    @Override // android.view.GestureDetector.OnDoubleTapListener
    public final boolean onDoubleTap(MotionEvent motionEvent) {
        try {
            float g = g();
            float x = motionEvent.getX();
            float y = motionEvent.getY();
            if (g < this.d) {
                b(this.d, x, y);
                return true;
            }
            b(this.b, x, y);
            return true;
        } catch (ArrayIndexOutOfBoundsException unused) {
            return true;
        }
    }

    @Override // com.sobot.chat.widget.photoview.VersionedGestureDetector.a
    public final void a(float f, float f2) {
        if (a) {
            Log.d("PhotoViewAttacher", String.format("onDrag: dx: %.2f. dy: %.2f", Float.valueOf(f), Float.valueOf(f2)));
        }
        ImageView c2 = c();
        if (c2 != null && a(c2)) {
            this.l.postTranslate(f, f2);
            l();
            if (this.e && !this.i.a()) {
                int i = this.x;
                if (i == 2 || ((i == 0 && f >= 1.0f) || (this.x == 1 && f <= -1.0f))) {
                    c2.getParent().requestDisallowInterceptTouchEvent(false);
                }
            }
        }
    }

    @Override // com.sobot.chat.widget.photoview.VersionedGestureDetector.a
    public final void a(float f, float f2, float f3, float f4) {
        if (a) {
            Log.d("PhotoViewAttacher", "onFling. sX: " + f + " sY: " + f2 + " Vx: " + f3 + " Vy: " + f4);
        }
        ImageView c2 = c();
        if (a(c2)) {
            this.w = new RunnableC0148b(c2.getContext());
            this.w.a(c2.getWidth(), c2.getHeight(), (int) f3, (int) f4);
            c2.post(this.w);
        }
    }

    @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
    public final void onGlobalLayout() {
        ImageView c2 = c();
        if (c2 != null && this.y) {
            int top = c2.getTop();
            int right = c2.getRight();
            int bottom = c2.getBottom();
            int left = c2.getLeft();
            if (top != this.s || bottom != this.u || left != this.v || right != this.t) {
                a(c2.getDrawable());
                this.s = top;
                this.t = right;
                this.u = bottom;
                this.v = left;
            }
        }
    }

    @Override // com.sobot.chat.widget.photoview.VersionedGestureDetector.a
    public final void a(float f, float f2, float f3) {
        if (a) {
            Log.d("PhotoViewAttacher", String.format("onScale: scale: %.2f. fX: %.2f. fY: %.2f", Float.valueOf(f), Float.valueOf(f2), Float.valueOf(f3)));
        }
        if (!a(c())) {
            return;
        }
        if (g() < this.d || f < 1.0f) {
            this.l.postScale(f, f, f2, f3);
            l();
        }
    }

    @Override // android.view.GestureDetector.OnDoubleTapListener
    public final boolean onSingleTapConfirmed(MotionEvent motionEvent) {
        RectF b;
        ImageView c2 = c();
        if (c2 == null) {
            return false;
        }
        if (!(this.p == null || (b = b()) == null)) {
            float x = motionEvent.getX();
            float y = motionEvent.getY();
            if (b.contains(x, y)) {
                this.p.a(c2, (x - b.left) / b.width(), (y - b.top) / b.height());
                return true;
            }
        }
        e eVar = this.q;
        if (eVar == null) {
            return false;
        }
        eVar.a(c2, motionEvent.getX(), motionEvent.getY());
        return false;
    }

    @Override // android.view.View.OnTouchListener
    public final boolean onTouch(View view, MotionEvent motionEvent) {
        RectF b;
        boolean z = false;
        if (!this.y) {
            return false;
        }
        int action = motionEvent.getAction();
        if (action == 0) {
            view.getParent().requestDisallowInterceptTouchEvent(true);
            k();
        } else if ((action == 1 || action == 3) && g() < this.b && (b = b()) != null) {
            view.post(new a(g(), this.b, b.centerX(), b.centerY()));
            z = true;
        }
        GestureDetector gestureDetector = this.h;
        if (gestureDetector != null && gestureDetector.onTouchEvent(motionEvent)) {
            z = true;
        }
        VersionedGestureDetector versionedGestureDetector = this.i;
        if (versionedGestureDetector == null || !versionedGestureDetector.a(motionEvent)) {
            return z;
        }
        return true;
    }

    public void a(boolean z) {
        this.e = z;
    }

    public void a(float f) {
        c(f, this.c, this.d);
        this.b = f;
    }

    public void b(float f) {
        c(this.b, f, this.d);
        this.c = f;
    }

    public void c(float f) {
        c(this.b, this.c, f);
        this.d = f;
    }

    public final void a(View.OnLongClickListener onLongClickListener) {
        this.r = onLongClickListener;
    }

    public final void a(c cVar) {
        this.o = cVar;
    }

    public final void a(d dVar) {
        this.p = dVar;
    }

    public final void a(e eVar) {
        this.q = eVar;
    }

    public final void a(ImageView.ScaleType scaleType) {
        if (b(scaleType) && scaleType != this.z) {
            this.z = scaleType;
            i();
        }
    }

    public final void b(boolean z) {
        this.y = z;
        i();
    }

    public final void i() {
        ImageView c2 = c();
        if (c2 == null) {
            return;
        }
        if (this.y) {
            b(c2);
            a(c2.getDrawable());
            return;
        }
        o();
    }

    public final void b(float f, float f2, float f3) {
        ImageView c2 = c();
        if (c2 != null) {
            c2.post(new a(g(), f, f2, f3));
        }
    }

    /* access modifiers changed from: protected */
    public Matrix j() {
        this.k.set(this.j);
        this.k.postConcat(this.l);
        return this.k;
    }

    private void k() {
        RunnableC0148b bVar = this.w;
        if (bVar != null) {
            bVar.a();
            this.w = null;
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void l() {
        n();
        b(j());
    }

    private void m() {
        ImageView c2 = c();
        if (c2 != null && !(c2 instanceof PhotoView) && c2.getScaleType() != ImageView.ScaleType.MATRIX) {
            throw new IllegalStateException("The ImageView's ScaleType has been changed since attaching a PhotoViewAttacher");
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:25:0x0061  */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x0081  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void n() {
        /*
            r11 = this;
            android.widget.ImageView r0 = r11.c()
            if (r0 != 0) goto L_0x0007
            return
        L_0x0007:
            android.graphics.Matrix r1 = r11.j()
            android.graphics.RectF r1 = r11.a(r1)
            if (r1 != 0) goto L_0x0012
            return
        L_0x0012:
            float r2 = r1.height()
            float r3 = r1.width()
            int r4 = r0.getHeight()
            float r4 = (float) r4
            int r5 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            r6 = 1073741824(0x40000000, float:2.0)
            r7 = 3
            r8 = 2
            r9 = 0
            if (r5 > 0) goto L_0x0044
            int[] r5 = com.sobot.chat.widget.photoview.b.AnonymousClass2.a
            android.widget.ImageView$ScaleType r10 = r11.z
            int r10 = r10.ordinal()
            r5 = r5[r10]
            if (r5 == r8) goto L_0x0041
            if (r5 == r7) goto L_0x003d
            float r4 = r4 - r2
            float r4 = r4 / r6
            float r2 = r1.top
        L_0x003a:
            float r2 = r4 - r2
            goto L_0x0058
        L_0x003d:
            float r4 = r4 - r2
            float r2 = r1.top
            goto L_0x003a
        L_0x0041:
            float r2 = r1.top
            goto L_0x004c
        L_0x0044:
            float r2 = r1.top
            int r2 = (r2 > r9 ? 1 : (r2 == r9 ? 0 : -1))
            if (r2 <= 0) goto L_0x004e
            float r2 = r1.top
        L_0x004c:
            float r2 = -r2
            goto L_0x0058
        L_0x004e:
            float r2 = r1.bottom
            int r2 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r2 >= 0) goto L_0x0057
            float r2 = r1.bottom
            goto L_0x003a
        L_0x0057:
            r2 = r9
        L_0x0058:
            int r0 = r0.getWidth()
            float r0 = (float) r0
            int r4 = (r3 > r0 ? 1 : (r3 == r0 ? 0 : -1))
            if (r4 > 0) goto L_0x0081
            int[] r4 = com.sobot.chat.widget.photoview.b.AnonymousClass2.a
            android.widget.ImageView$ScaleType r5 = r11.z
            int r5 = r5.ordinal()
            r4 = r4[r5]
            if (r4 == r8) goto L_0x007a
            if (r4 == r7) goto L_0x0076
            float r0 = r0 - r3
            float r0 = r0 / r6
            float r1 = r1.left
        L_0x0073:
            float r0 = r0 - r1
        L_0x0074:
            r9 = r0
            goto L_0x007e
        L_0x0076:
            float r0 = r0 - r3
            float r1 = r1.left
            goto L_0x0073
        L_0x007a:
            float r0 = r1.left
            float r0 = -r0
            goto L_0x0074
        L_0x007e:
            r11.x = r8
            goto L_0x009f
        L_0x0081:
            float r3 = r1.left
            int r3 = (r3 > r9 ? 1 : (r3 == r9 ? 0 : -1))
            if (r3 <= 0) goto L_0x008e
            r0 = 0
            r11.x = r0
            float r0 = r1.left
            float r9 = -r0
            goto L_0x009f
        L_0x008e:
            float r3 = r1.right
            int r3 = (r3 > r0 ? 1 : (r3 == r0 ? 0 : -1))
            if (r3 >= 0) goto L_0x009c
            float r1 = r1.right
            float r9 = r0 - r1
            r0 = 1
            r11.x = r0
            goto L_0x009f
        L_0x009c:
            r0 = -1
            r11.x = r0
        L_0x009f:
            android.graphics.Matrix r0 = r11.l
            r0.postTranslate(r9, r2)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.sobot.chat.widget.photoview.b.n():void");
    }

    private RectF a(Matrix matrix) {
        Drawable drawable;
        ImageView c2 = c();
        if (c2 == null || (drawable = c2.getDrawable()) == null) {
            return null;
        }
        this.m.set(0.0f, 0.0f, (float) drawable.getIntrinsicWidth(), (float) drawable.getIntrinsicHeight());
        matrix.mapRect(this.m);
        return this.m;
    }

    private float a(Matrix matrix, int i) {
        matrix.getValues(this.n);
        return this.n[i];
    }

    private void o() {
        this.l.reset();
        b(j());
        n();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void b(Matrix matrix) {
        RectF a2;
        ImageView c2 = c();
        if (c2 != null) {
            m();
            c2.setImageMatrix(matrix);
            if (this.o != null && (a2 = a(matrix)) != null) {
                this.o.a(a2);
            }
        }
    }

    private void a(Drawable drawable) {
        ImageView c2 = c();
        if (c2 != null && drawable != null) {
            float width = (float) c2.getWidth();
            float height = (float) c2.getHeight();
            int intrinsicWidth = drawable.getIntrinsicWidth();
            int intrinsicHeight = drawable.getIntrinsicHeight();
            this.j.reset();
            float f = (float) intrinsicWidth;
            float f2 = width / f;
            float f3 = (float) intrinsicHeight;
            float f4 = height / f3;
            if (this.z == ImageView.ScaleType.CENTER) {
                this.j.postTranslate((width - f) / 2.0f, (height - f3) / 2.0f);
            } else if (this.z == ImageView.ScaleType.CENTER_CROP) {
                float max = Math.max(f2, f4);
                this.j.postScale(max, max);
                this.j.postTranslate((width - (f * max)) / 2.0f, (height - (f3 * max)) / 2.0f);
            } else if (this.z == ImageView.ScaleType.CENTER_INSIDE) {
                float min = Math.min(1.0f, Math.min(f2, f4));
                this.j.postScale(min, min);
                this.j.postTranslate((width - (f * min)) / 2.0f, (height - (f3 * min)) / 2.0f);
            } else {
                RectF rectF = new RectF(0.0f, 0.0f, f, f3);
                RectF rectF2 = new RectF(0.0f, 0.0f, width, height);
                int i = AnonymousClass2.a[this.z.ordinal()];
                if (i == 2) {
                    this.j.setRectToRect(rectF, rectF2, Matrix.ScaleToFit.START);
                } else if (i == 3) {
                    this.j.setRectToRect(rectF, rectF2, Matrix.ScaleToFit.END);
                } else if (i == 4) {
                    this.j.setRectToRect(rectF, rectF2, Matrix.ScaleToFit.CENTER);
                } else if (i == 5) {
                    this.j.setRectToRect(rectF, rectF2, Matrix.ScaleToFit.FILL);
                }
            }
            o();
        }
    }

    /* compiled from: PhotoViewAttacher */
    /* access modifiers changed from: private */
    public class a implements Runnable {
        private final float b;
        private final float c;
        private final float d;
        private final float e;

        public a(float f, float f2, float f3, float f4) {
            this.d = f2;
            this.b = f3;
            this.c = f4;
            if (f < f2) {
                this.e = 1.07f;
            } else {
                this.e = 0.93f;
            }
        }

        @Override // java.lang.Runnable
        public void run() {
            ImageView c = b.this.c();
            if (c != null) {
                Matrix matrix = b.this.l;
                float f = this.e;
                matrix.postScale(f, f, this.b, this.c);
                b.this.l();
                float g = b.this.g();
                if ((this.e <= 1.0f || g >= this.d) && (this.e >= 1.0f || this.d >= g)) {
                    float f2 = this.d / g;
                    b.this.l.postScale(f2, f2, this.b, this.c);
                    b.this.l();
                    return;
                }
                a.a(c, this);
            }
        }
    }

    /* compiled from: PhotoViewAttacher */
    /* access modifiers changed from: private */
    /* renamed from: com.sobot.chat.widget.photoview.b$b  reason: collision with other inner class name */
    public class RunnableC0148b implements Runnable {
        private final ScrollerProxy b;
        private int c;
        private int d;

        public RunnableC0148b(Context context) {
            this.b = ScrollerProxy.a(context);
        }

        public void a() {
            if (b.a) {
                Log.d("PhotoViewAttacher", "Cancel Fling");
            }
            this.b.a(true);
        }

        public void a(int i, int i2, int i3, int i4) {
            int i5;
            int i6;
            int i7;
            int i8;
            RectF b = b.this.b();
            if (b != null) {
                int round = Math.round(-b.left);
                float f = (float) i;
                if (f < b.width()) {
                    i5 = Math.round(b.width() - f);
                    i6 = 0;
                } else {
                    i6 = round;
                    i5 = i6;
                }
                int round2 = Math.round(-b.top);
                float f2 = (float) i2;
                if (f2 < b.height()) {
                    i7 = Math.round(b.height() - f2);
                    i8 = 0;
                } else {
                    i8 = round2;
                    i7 = i8;
                }
                this.c = round;
                this.d = round2;
                if (b.a) {
                    Log.d("PhotoViewAttacher", "fling. StartX:" + round + " StartY:" + round2 + " MaxX:" + i5 + " MaxY:" + i7);
                }
                if (round != i5 || round2 != i7) {
                    this.b.a(round, round2, i3, i4, i6, i5, i8, i7, 0, 0);
                }
            }
        }

        @Override // java.lang.Runnable
        public void run() {
            ImageView c = b.this.c();
            if (c != null && this.b.a()) {
                int b = this.b.b();
                int c2 = this.b.c();
                if (b.a) {
                    Log.d("PhotoViewAttacher", "fling run(). CurrentX:" + this.c + " CurrentY:" + this.d + " NewX:" + b + " NewY:" + c2);
                }
                b.this.l.postTranslate((float) (this.c - b), (float) (this.d - c2));
                b bVar = b.this;
                bVar.b(bVar.j());
                this.c = b;
                this.d = c2;
                a.a(c, this);
            }
        }
    }
}
