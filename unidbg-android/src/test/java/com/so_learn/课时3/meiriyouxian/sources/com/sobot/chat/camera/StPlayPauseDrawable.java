package com.sobot.chat.camera;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.Property;
import android.util.TypedValue;
import android.view.animation.DecelerateInterpolator;

public class StPlayPauseDrawable extends Drawable {
    private static final Property<StPlayPauseDrawable, Float> a = new AnonymousClass1(Float.class, "progress");
    private final Path b = new Path();
    private final Path c = new Path();
    private final Paint d = new Paint();
    private final float e;
    private final float f;
    private final float g;
    private float h;
    private float i;
    private float j;
    private boolean k;
    private boolean l;
    private Animator m;

    private static float a(float f, float f2, float f3) {
        return f + ((f2 - f) * f3);
    }

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        return -3;
    }

    /* renamed from: com.sobot.chat.camera.StPlayPauseDrawable$1  reason: invalid class name */
    static class AnonymousClass1 extends Property<StPlayPauseDrawable, Float> {
        AnonymousClass1(Class cls, String str) {
            super(cls, str);
        }

        /* renamed from: a */
        public Float get(StPlayPauseDrawable stPlayPauseDrawable) {
            return Float.valueOf(stPlayPauseDrawable.c());
        }

        /* renamed from: a */
        public void set(StPlayPauseDrawable stPlayPauseDrawable, Float f) {
            stPlayPauseDrawable.a(f.floatValue());
        }
    }

    public StPlayPauseDrawable(Context context) {
        Resources resources = context.getResources();
        this.d.setAntiAlias(true);
        this.d.setStyle(Paint.Style.FILL);
        this.d.setColor(-1);
        this.e = TypedValue.applyDimension(1, 3.0f, resources.getDisplayMetrics());
        this.f = TypedValue.applyDimension(1, 14.0f, resources.getDisplayMetrics());
        this.g = TypedValue.applyDimension(1, 4.0f, resources.getDisplayMetrics());
    }

    /* access modifiers changed from: protected */
    @Override // android.graphics.drawable.Drawable
    public void onBoundsChange(Rect rect) {
        super.onBoundsChange(rect);
        if (rect.width() > 0 && rect.height() > 0) {
            this.h = (float) rect.width();
            this.i = (float) rect.height();
        }
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        this.b.rewind();
        this.c.rewind();
        float f = 0.0f;
        float a2 = a(this.g, 0.0f, this.j);
        float a3 = a(this.e, this.f / 1.75f, this.j);
        if (this.j == 1.0f) {
            a3 = (float) Math.round(a3);
        }
        float a4 = a(0.0f, a3, this.j);
        float f2 = (a3 * 2.0f) + a2;
        float f3 = a2 + a3;
        float a5 = a(f2, f3, this.j);
        this.b.moveTo(0.0f, 0.0f);
        this.b.lineTo(a4, -this.f);
        this.b.lineTo(a3, -this.f);
        if (this.k) {
            this.b.lineTo(a3 + 2.0f, 0.0f);
        } else {
            this.b.lineTo(a3, 0.0f);
        }
        this.b.close();
        if (this.k) {
            this.c.moveTo(f3 - 1.0f, 0.0f);
        } else {
            this.c.moveTo(f3, 0.0f);
        }
        this.c.lineTo(f3, -this.f);
        this.c.lineTo(a5, -this.f);
        this.c.lineTo(f2, 0.0f);
        this.c.close();
        int save = canvas.save();
        canvas.translate(a(0.0f, this.f / 8.0f, this.j), 0.0f);
        float f4 = this.k ? 1.0f - this.j : this.j;
        if (this.k) {
            f = 90.0f;
        }
        canvas.rotate(a(f, 90.0f + f, f4), this.h / 2.0f, this.i / 2.0f);
        canvas.translate((float) Math.round((this.h / 2.0f) - (f2 / 2.0f)), (float) Math.round((this.i / 2.0f) + (this.f / 2.0f)));
        canvas.drawPath(this.b, this.d);
        canvas.drawPath(this.c, this.d);
        canvas.restoreToCount(save);
    }

    /* JADX DEBUG: Can't convert new array creation: APUT found in different block: 0x0016: APUT  (r2v1 float[]), (0 ??[int, short, byte, char]), (r4v1 float) */
    private Animator b() {
        this.l = !this.l;
        Property<StPlayPauseDrawable, Float> property = a;
        float[] fArr = new float[2];
        float f = 1.0f;
        fArr[0] = this.k ? 1.0f : 0.0f;
        if (this.k) {
            f = 0.0f;
        }
        fArr[1] = f;
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this, property, fArr);
        ofFloat.addListener(new AnonymousClass2());
        return ofFloat;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: com.sobot.chat.camera.StPlayPauseDrawable$2  reason: invalid class name */
    public class AnonymousClass2 extends AnimatorListenerAdapter {
        AnonymousClass2() {
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            StPlayPauseDrawable stPlayPauseDrawable = StPlayPauseDrawable.this;
            stPlayPauseDrawable.k = !stPlayPauseDrawable.k;
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void a(float f) {
        this.j = f;
        invalidateSelf();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private float c() {
        return this.j;
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int i) {
        this.d.setAlpha(i);
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter colorFilter) {
        this.d.setColorFilter(colorFilter);
        invalidateSelf();
    }

    public void a(boolean z) {
        if (!z) {
            this.l = true;
            this.k = true;
            a(1.0f);
        } else if (!this.l) {
            a();
        }
    }

    public void b(boolean z) {
        if (!z) {
            this.l = false;
            this.k = false;
            a(0.0f);
        } else if (this.l) {
            a();
        }
    }

    public void a() {
        Animator animator = this.m;
        if (animator != null) {
            animator.cancel();
        }
        this.m = b();
        this.m.setInterpolator(new DecelerateInterpolator());
        this.m.setDuration(250);
        this.m.start();
    }
}
