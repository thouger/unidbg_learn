package com.sobot.chat.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.graphics.Xfermode;
import android.util.AttributeSet;
import android.widget.ImageView;
import com.sobot.chat.utils.q;

public class SobotSectorProgressView extends ImageView {
    private int a;
    private Paint b;
    private RectF c;
    private Paint d = new Paint(3);
    private RectF e;
    private Xfermode f = new PorterDuffXfermode(PorterDuff.Mode.SRC_ATOP);
    private float g = 1.0f;
    private float h;
    private float i = 100.0f;
    private float j;

    public SobotSectorProgressView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a();
    }

    private void a() {
        this.j = 270.0f;
        this.b = new Paint();
        this.a = getContext().getResources().getColor(q.a(getContext(), "color", "sobot_sectorProgressView_fgColor"));
        this.b.setColor(this.a);
    }

    /* access modifiers changed from: protected */
    @Override // android.view.View
    public void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        float paddingLeft = (float) (getPaddingLeft() + getPaddingRight());
        float paddingBottom = (float) (getPaddingBottom() + getPaddingTop());
        float f = (float) i;
        float f2 = (float) i2;
        int i5 = (int) ((f + paddingLeft) / 2.0f);
        int i6 = (int) ((f2 + paddingBottom) / 2.0f);
        this.c = new RectF((float) (i5 - i), (float) (i6 - i2), (float) (i5 + i), (float) (i6 + i2));
        this.e = new RectF((float) getPaddingLeft(), (float) getPaddingTop(), ((float) getPaddingLeft()) + (f - paddingLeft), ((float) getPaddingTop()) + (f2 - paddingBottom));
    }

    /* access modifiers changed from: protected */
    @Override // android.widget.ImageView, android.view.View
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int saveLayer = canvas.saveLayer(this.e, this.d, 31);
        this.d.setXfermode(this.f);
        canvas.drawArc(this.c, this.j, (-this.h) * 3.6f, true, this.b);
        this.d.setXfermode(null);
        canvas.restoreToCount(saveLayer);
    }

    public float getStartAngle() {
        return this.j;
    }

    public void setStartAngle(float f) {
        this.j = f + 270.0f;
        postInvalidate();
    }

    public float getProgress() {
        return this.h;
    }

    public void setProgress(float f) {
        if (f < 0.0f) {
            f = 0.0f;
        }
        float f2 = this.i;
        if (f > f2) {
            f = f2;
        }
        this.h = (this.i - f) * this.g;
        postInvalidate();
    }

    public void setMax(int i) {
        if (i >= 0) {
            float f = (float) i;
            this.g = 100.0f / f;
            this.i = f;
        }
    }
}
