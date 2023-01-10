package com.sobot.chat.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.View;
import com.sobot.chat.utils.r;

public class RoundProgressBar extends View {
    private Paint a;
    private int b;
    private int c;
    private int d;
    private float e;
    private float f;
    private int g;
    private int h;
    private boolean i;
    private int j;

    public RoundProgressBar(Context context) {
        this(context, null);
    }

    public RoundProgressBar(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public RoundProgressBar(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.a = new Paint();
        int b = r.b(context, 14.0f);
        int a = r.a(context, 3.0f);
        this.h = 0;
        this.b = Color.parseColor("#c2bab5");
        this.c = -1;
        this.d = -1;
        this.e = (float) b;
        this.f = (float) a;
        this.i = true;
        this.g = 100;
        this.j = 0;
    }

    /* access modifiers changed from: protected */
    @Override // android.view.View
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int width = getWidth() / 2;
        float f = (float) width;
        int i = (int) (f - (this.f / 2.0f));
        this.a.setColor(this.b);
        this.a.setStyle(Paint.Style.STROKE);
        this.a.setStrokeWidth(this.f);
        this.a.setAntiAlias(true);
        canvas.drawCircle(f, f, (float) i, this.a);
        this.a.setStrokeWidth(0.0f);
        this.a.setColor(this.d);
        this.a.setTextSize(this.e);
        this.a.setStyle(Paint.Style.FILL);
        this.a.setTypeface(Typeface.DEFAULT_BOLD);
        int i2 = (int) ((((float) this.h) / ((float) this.g)) * 100.0f);
        Paint paint = this.a;
        float measureText = paint.measureText(i2 + "%");
        if (this.i && this.j == 0) {
            canvas.drawText(i2 + "%", f - (measureText / 2.0f), f + (this.e / 2.0f), this.a);
        }
        this.a.setStrokeWidth(this.f);
        this.a.setColor(this.c);
        float f2 = (float) (width - i);
        float f3 = (float) (width + i);
        RectF rectF = new RectF(f2, f2, f3, f3);
        int i3 = this.j;
        if (i3 == 0) {
            this.a.setStyle(Paint.Style.STROKE);
            canvas.drawArc(rectF, 0.0f, (float) ((this.h * 360) / this.g), false, this.a);
        } else if (i3 == 1) {
            this.a.setStyle(Paint.Style.FILL_AND_STROKE);
            int i4 = this.h;
            if (i4 != 0) {
                canvas.drawArc(rectF, 0.0f, (float) ((i4 * 360) / this.g), true, this.a);
            }
        }
    }

    public synchronized int getMax() {
        return this.g;
    }

    public synchronized void setMax(int i) {
        if (i >= 0) {
            this.g = i;
        } else {
            throw new IllegalArgumentException("max not less than 0");
        }
    }

    public synchronized int getProgress() {
        return this.h;
    }

    public synchronized void setProgress(int i) {
        if (i >= 0) {
            if (i > this.g) {
                i = this.g;
            }
            if (i <= this.g) {
                this.h = i;
                postInvalidate();
            }
        } else {
            throw new IllegalArgumentException("progress not less than 0");
        }
    }

    public int getCricleColor() {
        return this.b;
    }

    public void setCricleColor(int i) {
        this.b = i;
    }

    public int getCricleProgressColor() {
        return this.c;
    }

    public void setCricleProgressColor(int i) {
        this.c = i;
    }

    public int getTextColor() {
        return this.d;
    }

    public void setTextColor(int i) {
        this.d = i;
    }

    public float getTextSize() {
        return this.e;
    }

    public void setTextSize(float f) {
        this.e = f;
    }

    public float getRoundWidth() {
        return this.f;
    }

    public void setRoundWidth(float f) {
        this.f = f;
    }

    public void setTextDisplayable(boolean z) {
        this.i = z;
    }
}
