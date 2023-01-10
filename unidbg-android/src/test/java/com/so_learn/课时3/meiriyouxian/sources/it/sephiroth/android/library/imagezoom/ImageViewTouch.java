package it.sephiroth.android.library.imagezoom;

import android.content.Context;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.ViewConfiguration;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;

public class ImageViewTouch extends ImageViewTouchBase {
    protected ScaleGestureDetector a;
    protected GestureDetector b;
    protected int c;
    protected float d;
    protected int e;
    protected GestureDetector.OnGestureListener f;
    protected ScaleGestureDetector.OnScaleGestureListener g;
    protected boolean h;
    protected boolean i;
    protected boolean j;
    private b y;
    private c z;

    public interface b {
        void a();
    }

    public interface c {
        void a();
    }

    public boolean a(MotionEvent motionEvent) {
        return true;
    }

    public boolean b(MotionEvent motionEvent) {
        return true;
    }

    public boolean d(MotionEvent motionEvent) {
        return true;
    }

    public ImageViewTouch(Context context) {
        super(context);
        this.h = true;
        this.i = true;
        this.j = true;
    }

    public ImageViewTouch(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public ImageViewTouch(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.h = true;
        this.i = true;
        this.j = true;
    }

    /* access modifiers changed from: protected */
    @Override // it.sephiroth.android.library.imagezoom.ImageViewTouchBase
    public void a(Context context, AttributeSet attributeSet, int i) {
        AppMethodBeat.i(4484, false);
        super.a(context, attributeSet, i);
        this.c = ViewConfiguration.get(getContext()).getScaledTouchSlop();
        this.f = getGestureListener();
        this.g = getScaleListener();
        this.a = new ScaleGestureDetector(getContext(), this.g);
        this.b = new GestureDetector(getContext(), this.f, null, true);
        this.e = 1;
        AppMethodBeat.o(4484);
    }

    public void setDoubleTapListener(b bVar) {
        this.y = bVar;
    }

    public void setSingleTapListener(c cVar) {
        this.z = cVar;
    }

    public void setDoubleTapEnabled(boolean z) {
        this.h = z;
    }

    public void setScaleEnabled(boolean z) {
        this.i = z;
    }

    public void setScrollEnabled(boolean z) {
        this.j = z;
    }

    public boolean getDoubleTapEnabled() {
        return this.h;
    }

    /* access modifiers changed from: protected */
    public GestureDetector.OnGestureListener getGestureListener() {
        AppMethodBeat.i(4493, false);
        a aVar = new a();
        AppMethodBeat.o(4493);
        return aVar;
    }

    /* access modifiers changed from: protected */
    public ScaleGestureDetector.OnScaleGestureListener getScaleListener() {
        AppMethodBeat.i(4495, false);
        d dVar = new d();
        AppMethodBeat.o(4495);
        return dVar;
    }

    /* access modifiers changed from: protected */
    @Override // it.sephiroth.android.library.imagezoom.ImageViewTouchBase
    public void a(Drawable drawable, Matrix matrix, float f, float f2) {
        AppMethodBeat.i(4497, false);
        super.a(drawable, matrix, f, f2);
        this.d = getMaxScale() / 3.0f;
        AppMethodBeat.o(4497);
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        AppMethodBeat.i(4501, false);
        this.a.onTouchEvent(motionEvent);
        if (!this.a.isInProgress()) {
            this.b.onTouchEvent(motionEvent);
        }
        if ((motionEvent.getAction() & 255) != 1) {
            AppMethodBeat.o(4501);
            return true;
        }
        boolean c2 = c(motionEvent);
        AppMethodBeat.o(4501);
        return c2;
    }

    /* access modifiers changed from: protected */
    @Override // it.sephiroth.android.library.imagezoom.ImageViewTouchBase
    public void a(float f) {
        AppMethodBeat.i(4504, false);
        if (f < getMinScale()) {
            c(getMinScale(), 50.0f);
        }
        AppMethodBeat.o(4504);
    }

    /* access modifiers changed from: protected */
    public float a(float f, float f2) {
        if (this.e == 1) {
            float f3 = this.d;
            if ((2.0f * f3) + f <= f2) {
                return f + f3;
            }
            this.e = -1;
            return f2;
        }
        this.e = 1;
        return 1.0f;
    }

    public boolean a(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
        AppMethodBeat.i(4506, false);
        if (getScale() == 1.0f) {
            AppMethodBeat.o(4506);
            return false;
        }
        this.q = true;
        d(-f, -f2);
        invalidate();
        AppMethodBeat.o(4506);
        return true;
    }

    public boolean b(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
        AppMethodBeat.i(4510, false);
        float x = motionEvent2.getX() - motionEvent.getX();
        float y = motionEvent2.getY() - motionEvent.getY();
        if (Math.abs(f) > 800.0f || Math.abs(f2) > 800.0f) {
            this.q = true;
            a(x / 2.0f, y / 2.0f, 300.0d);
            invalidate();
            AppMethodBeat.o(4510);
            return true;
        }
        AppMethodBeat.o(4510);
        return false;
    }

    public boolean c(MotionEvent motionEvent) {
        AppMethodBeat.i(4513, false);
        if (getScale() < getMinScale()) {
            c(getMinScale(), 50.0f);
        }
        AppMethodBeat.o(4513);
        return true;
    }

    public boolean a(int i) {
        boolean z = false;
        AppMethodBeat.i(4520, false);
        RectF bitmapRect = getBitmapRect();
        a(bitmapRect, this.x);
        Rect rect = new Rect();
        getGlobalVisibleRect(rect);
        if (bitmapRect == null) {
            AppMethodBeat.o(4520);
            return false;
        } else if (bitmapRect.right < ((float) rect.right) || i >= 0) {
            if (((double) Math.abs(bitmapRect.left - this.x.left)) > 1.0d) {
                z = true;
            }
            AppMethodBeat.o(4520);
            return z;
        } else {
            if (Math.abs(bitmapRect.right - ((float) rect.right)) > 1.0f) {
                z = true;
            }
            AppMethodBeat.o(4520);
            return z;
        }
    }

    public class a extends GestureDetector.SimpleOnGestureListener {
        public a() {
        }

        @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnDoubleTapListener
        public boolean onSingleTapConfirmed(MotionEvent motionEvent) {
            AppMethodBeat.i(4432, false);
            if (ImageViewTouch.this.z != null) {
                ImageViewTouch.this.z.a();
            }
            boolean a = ImageViewTouch.this.a(motionEvent);
            AppMethodBeat.o(4432);
            return a;
        }

        @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnDoubleTapListener
        public boolean onDoubleTap(MotionEvent motionEvent) {
            AppMethodBeat.i(4436, false);
            Log.i("ImageViewTouchBase", "onDoubleTap. double tap enabled? " + ImageViewTouch.this.h);
            if (ImageViewTouch.this.h) {
                ImageViewTouch imageViewTouch = ImageViewTouch.this;
                imageViewTouch.q = true;
                float scale = imageViewTouch.getScale();
                ImageViewTouch imageViewTouch2 = ImageViewTouch.this;
                ImageViewTouch.this.a(Math.min(ImageViewTouch.this.getMaxScale(), Math.max(imageViewTouch2.a(scale, imageViewTouch2.getMaxScale()), ImageViewTouch.this.getMinScale())), motionEvent.getX(), motionEvent.getY(), 200.0f);
                ImageViewTouch.this.invalidate();
            }
            if (ImageViewTouch.this.y != null) {
                ImageViewTouch.this.y.a();
            }
            boolean onDoubleTap = super.onDoubleTap(motionEvent);
            AppMethodBeat.o(4436);
            return onDoubleTap;
        }

        @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
        public void onLongPress(MotionEvent motionEvent) {
            AppMethodBeat.i(4440, false);
            if (ImageViewTouch.this.isLongClickable() && !ImageViewTouch.this.a.isInProgress()) {
                ImageViewTouch.this.setPressed(true);
                ImageViewTouch.this.performLongClick();
            }
            AppMethodBeat.o(4440);
        }

        @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
        public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
            AppMethodBeat.i(4445, false);
            if (!ImageViewTouch.this.j) {
                AppMethodBeat.o(4445);
                return false;
            } else if (motionEvent == null || motionEvent2 == null) {
                AppMethodBeat.o(4445);
                return false;
            } else if (motionEvent.getPointerCount() > 1 || motionEvent2.getPointerCount() > 1) {
                AppMethodBeat.o(4445);
                return false;
            } else if (ImageViewTouch.this.a.isInProgress()) {
                AppMethodBeat.o(4445);
                return false;
            } else {
                boolean a = ImageViewTouch.this.a(motionEvent, motionEvent2, f, f2);
                AppMethodBeat.o(4445);
                return a;
            }
        }

        @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
        public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
            AppMethodBeat.i(4449, false);
            if (!ImageViewTouch.this.j) {
                AppMethodBeat.o(4449);
                return false;
            } else if (motionEvent.getPointerCount() > 1 || motionEvent2.getPointerCount() > 1) {
                AppMethodBeat.o(4449);
                return false;
            } else if (ImageViewTouch.this.a.isInProgress()) {
                AppMethodBeat.o(4449);
                return false;
            } else if (ImageViewTouch.this.getScale() == 1.0f) {
                AppMethodBeat.o(4449);
                return false;
            } else {
                boolean b = ImageViewTouch.this.b(motionEvent, motionEvent2, f, f2);
                AppMethodBeat.o(4449);
                return b;
            }
        }

        @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
        public boolean onSingleTapUp(MotionEvent motionEvent) {
            AppMethodBeat.i(4453, false);
            boolean d = ImageViewTouch.this.d(motionEvent);
            AppMethodBeat.o(4453);
            return d;
        }

        @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
        public boolean onDown(MotionEvent motionEvent) {
            AppMethodBeat.i(4457, false);
            boolean b = ImageViewTouch.this.b(motionEvent);
            AppMethodBeat.o(4457);
            return b;
        }
    }

    public class d extends ScaleGestureDetector.SimpleOnScaleGestureListener {
        protected boolean a = false;

        public d() {
        }

        @Override // android.view.ScaleGestureDetector.SimpleOnScaleGestureListener, android.view.ScaleGestureDetector.OnScaleGestureListener
        public boolean onScale(ScaleGestureDetector scaleGestureDetector) {
            AppMethodBeat.i(4470, false);
            float currentSpan = scaleGestureDetector.getCurrentSpan() - scaleGestureDetector.getPreviousSpan();
            float scale = ImageViewTouch.this.getScale() * scaleGestureDetector.getScaleFactor();
            if (ImageViewTouch.this.i) {
                if (this.a && currentSpan != 0.0f) {
                    ImageViewTouch imageViewTouch = ImageViewTouch.this;
                    imageViewTouch.q = true;
                    ImageViewTouch.this.b(Math.min(imageViewTouch.getMaxScale(), Math.max(scale, ImageViewTouch.this.getMinScale() - 0.1f)), scaleGestureDetector.getFocusX(), scaleGestureDetector.getFocusY());
                    ImageViewTouch imageViewTouch2 = ImageViewTouch.this;
                    imageViewTouch2.e = 1;
                    imageViewTouch2.invalidate();
                    AppMethodBeat.o(4470);
                    return true;
                } else if (!this.a) {
                    this.a = true;
                }
            }
            AppMethodBeat.o(4470);
            return true;
        }
    }
}
