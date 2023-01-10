package com.sobot.chat.camera;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import com.sobot.chat.camera.c.g;

public class StFoucsView extends View {
    private int a;
    private int b;
    private int c;
    private int d;
    private Paint e;

    public StFoucsView(Context context) {
        this(context, null);
    }

    public StFoucsView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public StFoucsView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.a = g.b(context) / 3;
        this.e = new Paint();
        this.e.setAntiAlias(true);
        this.e.setDither(true);
        this.e.setColor(-300503530);
        this.e.setStrokeWidth(4.0f);
        this.e.setStyle(Paint.Style.STROKE);
    }

    /* access modifiers changed from: protected */
    @Override // android.view.View
    public void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        int i3 = this.a;
        this.b = (int) (((double) i3) / 2.0d);
        this.c = (int) (((double) i3) / 2.0d);
        this.d = ((int) (((double) i3) / 2.0d)) - 2;
        setMeasuredDimension(i3, i3);
    }

    /* access modifiers changed from: protected */
    @Override // android.view.View
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int i = this.b;
        int i2 = this.d;
        int i3 = this.c;
        canvas.drawRect((float) (i - i2), (float) (i3 - i2), (float) (i + i2), (float) (i3 + i2), this.e);
        canvas.drawLine(2.0f, (float) (getHeight() / 2), (float) (this.a / 10), (float) (getHeight() / 2), this.e);
        canvas.drawLine((float) (getWidth() - 2), (float) (getHeight() / 2), (float) (getWidth() - (this.a / 10)), (float) (getHeight() / 2), this.e);
        canvas.drawLine((float) (getWidth() / 2), 2.0f, (float) (getWidth() / 2), (float) (this.a / 10), this.e);
        canvas.drawLine((float) (getWidth() / 2), (float) (getHeight() - 2), (float) (getWidth() / 2), (float) (getHeight() - (this.a / 10)), this.e);
    }
}
