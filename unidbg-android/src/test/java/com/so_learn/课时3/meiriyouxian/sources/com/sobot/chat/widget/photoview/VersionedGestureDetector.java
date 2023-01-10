package com.sobot.chat.widget.photoview;

import android.content.Context;
import android.os.Build;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.VelocityTracker;
import android.view.ViewConfiguration;

public abstract class VersionedGestureDetector {
    a a;

    public interface a {
        void a(float f, float f2);

        void a(float f, float f2, float f3);

        void a(float f, float f2, float f3, float f4);
    }

    public abstract boolean a();

    public abstract boolean a(MotionEvent motionEvent);

    public static VersionedGestureDetector a(Context context, a aVar) {
        VersionedGestureDetector versionedGestureDetector;
        int i = Build.VERSION.SDK_INT;
        if (i < 5) {
            versionedGestureDetector = new CupcakeDetector(context);
        } else if (i < 8) {
            versionedGestureDetector = new EclairDetector(context);
        } else {
            versionedGestureDetector = new FroyoDetector(context);
        }
        versionedGestureDetector.a = aVar;
        return versionedGestureDetector;
    }

    /* access modifiers changed from: private */
    public static class CupcakeDetector extends VersionedGestureDetector {
        float b;
        float c;
        final float d;
        final float e;
        private VelocityTracker f;
        private boolean g;

        @Override // com.sobot.chat.widget.photoview.VersionedGestureDetector
        public boolean a() {
            return false;
        }

        public CupcakeDetector(Context context) {
            ViewConfiguration viewConfiguration = ViewConfiguration.get(context);
            this.e = (float) viewConfiguration.getScaledMinimumFlingVelocity();
            this.d = (float) viewConfiguration.getScaledTouchSlop();
        }

        /* access modifiers changed from: package-private */
        public float b(MotionEvent motionEvent) {
            return motionEvent.getX();
        }

        /* access modifiers changed from: package-private */
        public float c(MotionEvent motionEvent) {
            return motionEvent.getY();
        }

        @Override // com.sobot.chat.widget.photoview.VersionedGestureDetector
        public boolean a(MotionEvent motionEvent) {
            VelocityTracker velocityTracker;
            int action = motionEvent.getAction();
            boolean z = false;
            if (action == 0) {
                this.f = VelocityTracker.obtain();
                this.f.addMovement(motionEvent);
                this.b = b(motionEvent);
                this.c = c(motionEvent);
                this.g = false;
            } else if (action == 1) {
                if (this.g && this.f != null) {
                    this.b = b(motionEvent);
                    this.c = c(motionEvent);
                    this.f.addMovement(motionEvent);
                    this.f.computeCurrentVelocity(1000);
                    float xVelocity = this.f.getXVelocity();
                    float yVelocity = this.f.getYVelocity();
                    if (Math.max(Math.abs(xVelocity), Math.abs(yVelocity)) >= this.e) {
                        this.a.a(this.b, this.c, -xVelocity, -yVelocity);
                    }
                }
                VelocityTracker velocityTracker2 = this.f;
                if (velocityTracker2 != null) {
                    velocityTracker2.recycle();
                    this.f = null;
                }
            } else if (action == 2) {
                float b = b(motionEvent);
                float c = c(motionEvent);
                float f = b - this.b;
                float f2 = c - this.c;
                if (!this.g) {
                    if (Math.sqrt((double) ((f * f) + (f2 * f2))) >= ((double) this.d)) {
                        z = true;
                    }
                    this.g = z;
                }
                if (this.g) {
                    this.a.a(f, f2);
                    this.b = b;
                    this.c = c;
                    VelocityTracker velocityTracker3 = this.f;
                    if (velocityTracker3 != null) {
                        velocityTracker3.addMovement(motionEvent);
                    }
                }
            } else if (action == 3 && (velocityTracker = this.f) != null) {
                velocityTracker.recycle();
                this.f = null;
            }
            return true;
        }
    }

    /* access modifiers changed from: private */
    public static class EclairDetector extends CupcakeDetector {
        private int f = -1;
        private int g = 0;

        public EclairDetector(Context context) {
            super(context);
        }

        /* access modifiers changed from: package-private */
        @Override // com.sobot.chat.widget.photoview.VersionedGestureDetector.CupcakeDetector
        public float b(MotionEvent motionEvent) {
            try {
                return motionEvent.getX(this.g);
            } catch (Exception unused) {
                return motionEvent.getX();
            }
        }

        /* access modifiers changed from: package-private */
        @Override // com.sobot.chat.widget.photoview.VersionedGestureDetector.CupcakeDetector
        public float c(MotionEvent motionEvent) {
            try {
                return motionEvent.getY(this.g);
            } catch (Exception unused) {
                return motionEvent.getY();
            }
        }

        @Override // com.sobot.chat.widget.photoview.VersionedGestureDetector.CupcakeDetector, com.sobot.chat.widget.photoview.VersionedGestureDetector
        public boolean a(MotionEvent motionEvent) {
            int action = motionEvent.getAction() & 255;
            if (action != 0) {
                int i = 1;
                if (action == 1 || action == 3) {
                    this.f = -1;
                } else if (action == 6) {
                    int action2 = (motionEvent.getAction() & 65280) >> 8;
                    if (motionEvent.getPointerId(action2) == this.f) {
                        if (action2 != 0) {
                            i = 0;
                        }
                        this.f = motionEvent.getPointerId(i);
                        this.b = motionEvent.getX(i);
                        this.c = motionEvent.getY(i);
                    }
                }
            } else {
                this.f = motionEvent.getPointerId(0);
            }
            int i2 = this.f;
            if (i2 == -1) {
                i2 = 0;
            }
            this.g = motionEvent.findPointerIndex(i2);
            return super.a(motionEvent);
        }
    }

    /* access modifiers changed from: private */
    public static class FroyoDetector extends EclairDetector {
        private final ScaleGestureDetector f;
        private final ScaleGestureDetector.OnScaleGestureListener g = new AnonymousClass1();

        /* renamed from: com.sobot.chat.widget.photoview.VersionedGestureDetector$FroyoDetector$1  reason: invalid class name */
        class AnonymousClass1 implements ScaleGestureDetector.OnScaleGestureListener {
            @Override // android.view.ScaleGestureDetector.OnScaleGestureListener
            public boolean onScaleBegin(ScaleGestureDetector scaleGestureDetector) {
                return true;
            }

            @Override // android.view.ScaleGestureDetector.OnScaleGestureListener
            public void onScaleEnd(ScaleGestureDetector scaleGestureDetector) {
            }

            AnonymousClass1() {
            }

            @Override // android.view.ScaleGestureDetector.OnScaleGestureListener
            public boolean onScale(ScaleGestureDetector scaleGestureDetector) {
                FroyoDetector.this.a.a(scaleGestureDetector.getScaleFactor(), scaleGestureDetector.getFocusX(), scaleGestureDetector.getFocusY());
                return true;
            }
        }

        public FroyoDetector(Context context) {
            super(context);
            this.f = new ScaleGestureDetector(context, this.g);
        }

        @Override // com.sobot.chat.widget.photoview.VersionedGestureDetector.CupcakeDetector, com.sobot.chat.widget.photoview.VersionedGestureDetector
        public boolean a() {
            return this.f.isInProgress();
        }

        @Override // com.sobot.chat.widget.photoview.VersionedGestureDetector.EclairDetector, com.sobot.chat.widget.photoview.VersionedGestureDetector.CupcakeDetector, com.sobot.chat.widget.photoview.VersionedGestureDetector
        public boolean a(MotionEvent motionEvent) {
            this.f.onTouchEvent(motionEvent);
            return super.a(motionEvent);
        }
    }
}
