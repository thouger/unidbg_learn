package com.sobot.chat.camera;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.view.View;

public class StReturnButton extends View {
    Path a;
    private int b;
    private int c;
    private int d;
    private float e;
    private Paint f;

    public StReturnButton(Context context, int i) {
        this(context);
        this.b = i;
        int i2 = i / 2;
        this.c = i2;
        this.d = i2;
        this.e = ((float) i) / 15.0f;
        this.f = new Paint();
        this.f.setAntiAlias(true);
        this.f.setColor(-1);
        this.f.setStyle(Paint.Style.STROKE);
        this.f.setStrokeWidth(this.e);
        this.a = new Path();
    }

    public StReturnButton(Context context) {
        super(context);
    }

    /* access modifiers changed from: protected */
    @Override // android.view.View
    public void onMeasure(int i, int i2) {
        int i3 = this.b;
        setMeasuredDimension(i3, i3 / 2);
    }

    /* access modifiers changed from: protected */
    @Override // android.view.View
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Path path = this.a;
        float f = this.e;
        path.moveTo(f, f / 2.0f);
        this.a.lineTo((float) this.c, ((float) this.d) - (this.e / 2.0f));
        Path path2 = this.a;
        float f2 = this.e;
        path2.lineTo(((float) this.b) - f2, f2 / 2.0f);
        canvas.drawPath(this.a, this.f);
    }
}
