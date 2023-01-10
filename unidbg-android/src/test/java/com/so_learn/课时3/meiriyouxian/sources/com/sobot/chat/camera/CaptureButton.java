package com.sobot.chat.camera;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.CountDownTimer;
import android.view.MotionEvent;
import android.view.View;
import com.sobot.chat.camera.c.d;
import com.sobot.chat.camera.c.h;

public class CaptureButton extends View {
    private int a;
    private int b;
    private int c = -300503530;
    private int d = -287515428;
    private int e = -1;
    private float f;
    private Paint g;
    private float h;
    private int i;
    private int j;
    private float k;
    private float l;
    private float m;
    private float n;
    private float o;
    private int p;
    private float q;
    private int r;
    private int s;
    private int t;
    private RectF u;
    private a v;
    private com.sobot.chat.camera.a.b w;
    private b x;

    public CaptureButton(Context context, int i) {
        super(context);
        this.p = i;
        this.m = ((float) i) / 2.0f;
        float f = this.m;
        this.n = f;
        this.o = f * 0.75f;
        this.h = (float) (i / 15);
        this.i = i / 5;
        this.j = i / 8;
        this.g = new Paint();
        this.g.setAntiAlias(true);
        this.q = 0.0f;
        this.v = new a(this, null);
        this.a = 1;
        this.b = 259;
        h.a("CaptureButtom start");
        this.r = 15000;
        h.a("CaptureButtom end");
        this.s = 1000;
        int i2 = this.p;
        int i3 = this.i;
        this.k = (float) (((i3 * 2) + i2) / 2);
        this.l = (float) ((i2 + (i3 * 2)) / 2);
        float f2 = this.k;
        float f3 = this.m;
        float f4 = this.h;
        float f5 = this.l;
        this.u = new RectF(f2 - ((((float) i3) + f3) - (f4 / 2.0f)), f5 - ((((float) i3) + f3) - (f4 / 2.0f)), f2 + ((((float) i3) + f3) - (f4 / 2.0f)), f5 + ((f3 + ((float) i3)) - (f4 / 2.0f)));
        int i4 = this.r;
        this.x = new b((long) i4, (long) (i4 / 360));
    }

    /* access modifiers changed from: protected */
    @Override // android.view.View
    public void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        int i3 = this.p;
        int i4 = this.i;
        setMeasuredDimension((i4 * 2) + i3, i3 + (i4 * 2));
    }

    /* access modifiers changed from: protected */
    @Override // android.view.View
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        this.g.setStyle(Paint.Style.FILL);
        this.g.setColor(this.d);
        canvas.drawCircle(this.k, this.l, this.n, this.g);
        this.g.setColor(this.e);
        canvas.drawCircle(this.k, this.l, this.o, this.g);
        if (this.a == 4) {
            this.g.setColor(this.c);
            this.g.setStyle(Paint.Style.STROKE);
            this.g.setStrokeWidth(this.h);
            canvas.drawArc(this.u, -90.0f, this.q, false, this.g);
        }
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        int i;
        int action = motionEvent.getAction();
        if (action == 0) {
            h.a("state = " + this.a);
            if (motionEvent.getPointerCount() <= 1 && this.a == 1) {
                this.f = motionEvent.getY();
                this.a = 2;
                int i2 = this.b;
                if (i2 == 258 || i2 == 259) {
                    postDelayed(this.v, 500);
                }
            }
        } else if (action == 1) {
            c();
        } else if (action == 2 && this.w != null && this.a == 4 && ((i = this.b) == 258 || i == 259)) {
            this.w.a(this.f - motionEvent.getY());
        }
        return true;
    }

    private void c() {
        int i;
        removeCallbacks(this.v);
        int i2 = this.a;
        if (i2 != 2) {
            if (i2 == 4) {
                this.x.cancel();
                d();
            }
        } else if (this.w == null || !((i = this.b) == 257 || i == 259)) {
            this.a = 1;
        } else {
            a(this.o);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void d() {
        com.sobot.chat.camera.a.b bVar = this.w;
        if (bVar != null) {
            int i = this.t;
            if (i < this.s) {
                bVar.a((long) i);
            } else {
                bVar.b((long) i);
            }
        }
        e();
    }

    private void e() {
        this.a = 5;
        this.q = 0.0f;
        invalidate();
        float f = this.n;
        float f2 = this.m;
        a(f, f2, this.o, 0.75f * f2);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: com.sobot.chat.camera.CaptureButton$1  reason: invalid class name */
    public class AnonymousClass1 implements ValueAnimator.AnimatorUpdateListener {
        AnonymousClass1() {
        }

        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            CaptureButton.this.o = ((Float) valueAnimator.getAnimatedValue()).floatValue();
            CaptureButton.this.invalidate();
        }
    }

    private void a(float f) {
        ValueAnimator ofFloat = ValueAnimator.ofFloat(f, 0.75f * f, f);
        ofFloat.addUpdateListener(new AnonymousClass1());
        ofFloat.addListener(new AnonymousClass2());
        ofFloat.setDuration(100L);
        ofFloat.start();
    }

    /* access modifiers changed from: package-private */
    /* renamed from: com.sobot.chat.camera.CaptureButton$2  reason: invalid class name */
    public class AnonymousClass2 extends AnimatorListenerAdapter {
        AnonymousClass2() {
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            super.onAnimationEnd(animator);
            CaptureButton.this.w.a();
            CaptureButton.this.a = 5;
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void a(float f, float f2, float f3, float f4) {
        ValueAnimator ofFloat = ValueAnimator.ofFloat(f, f2);
        ValueAnimator ofFloat2 = ValueAnimator.ofFloat(f3, f4);
        ofFloat.addUpdateListener(new AnonymousClass3());
        ofFloat2.addUpdateListener(new AnonymousClass4());
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.addListener(new AnonymousClass5());
        animatorSet.playTogether(ofFloat, ofFloat2);
        animatorSet.setDuration(100L);
        animatorSet.start();
    }

    /* access modifiers changed from: package-private */
    /* renamed from: com.sobot.chat.camera.CaptureButton$3  reason: invalid class name */
    public class AnonymousClass3 implements ValueAnimator.AnimatorUpdateListener {
        AnonymousClass3() {
        }

        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            CaptureButton.this.n = ((Float) valueAnimator.getAnimatedValue()).floatValue();
            CaptureButton.this.invalidate();
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: com.sobot.chat.camera.CaptureButton$4  reason: invalid class name */
    public class AnonymousClass4 implements ValueAnimator.AnimatorUpdateListener {
        AnonymousClass4() {
        }

        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            CaptureButton.this.o = ((Float) valueAnimator.getAnimatedValue()).floatValue();
            CaptureButton.this.invalidate();
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: com.sobot.chat.camera.CaptureButton$5  reason: invalid class name */
    public class AnonymousClass5 extends AnimatorListenerAdapter {
        AnonymousClass5() {
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            super.onAnimationEnd(animator);
            if (CaptureButton.this.a == 3) {
                if (CaptureButton.this.w != null) {
                    CaptureButton.this.w.b();
                }
                CaptureButton.this.a = 4;
                CaptureButton.this.x.start();
            }
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void a(long j) {
        int i = this.r;
        this.t = (int) (((long) i) - j);
        this.q = 360.0f - ((((float) j) / ((float) i)) * 360.0f);
        invalidate();
    }

    /* access modifiers changed from: private */
    public class b extends CountDownTimer {
        b(long j, long j2) {
            super(j, j2);
        }

        @Override // android.os.CountDownTimer
        public void onTick(long j) {
            CaptureButton.this.a(j);
        }

        @Override // android.os.CountDownTimer
        public void onFinish() {
            CaptureButton.this.a(0L);
            CaptureButton.this.d();
        }
    }

    /* access modifiers changed from: private */
    public class a implements Runnable {
        private a() {
        }

        /* synthetic */ a(CaptureButton captureButton, AnonymousClass1 r2) {
            this();
        }

        @Override // java.lang.Runnable
        public void run() {
            CaptureButton.this.a = 3;
            if (d.a() != 1) {
                CaptureButton.this.a = 1;
                if (CaptureButton.this.w != null) {
                    CaptureButton.this.w.c();
                    return;
                }
            }
            CaptureButton captureButton = CaptureButton.this;
            captureButton.a(captureButton.n, CaptureButton.this.n + ((float) CaptureButton.this.i), CaptureButton.this.o, CaptureButton.this.o - ((float) CaptureButton.this.j));
        }
    }

    /* access modifiers changed from: protected */
    @Override // android.view.View
    public void onDetachedFromWindow() {
        b bVar = this.x;
        if (bVar != null) {
            bVar.cancel();
        }
        super.onDetachedFromWindow();
    }

    public void setDuration(int i) {
        this.r = i;
        this.x = new b((long) i, (long) (i / 360));
    }

    public void setMinDuration(int i) {
        this.s = i;
    }

    public void setCaptureLisenter(com.sobot.chat.camera.a.b bVar) {
        this.w = bVar;
    }

    public void setButtonFeatures(int i) {
        this.b = i;
    }

    public boolean a() {
        return this.a == 1;
    }

    public void b() {
        this.a = 1;
    }
}
