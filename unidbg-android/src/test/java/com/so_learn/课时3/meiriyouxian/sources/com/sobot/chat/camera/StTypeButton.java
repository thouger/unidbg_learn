package com.sobot.chat.camera;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.view.View;

public class StTypeButton extends View {
    private int a;
    private int b;
    private float c;
    private float d;
    private float e;
    private Paint f = new Paint();
    private Path g = new Path();
    private float h;
    private float i;
    private RectF j;

    public StTypeButton(Context context, int i, int i2) {
        super(context);
        this.a = i;
        this.b = i2;
        float f = (float) i2;
        float f2 = f / 2.0f;
        this.e = f2;
        this.c = f2;
        this.d = f2;
        this.h = f / 50.0f;
        this.i = ((float) this.b) / 12.0f;
        float f3 = this.c;
        float f4 = this.d;
        float f5 = this.i;
        this.j = new RectF(f3, f4 - f5, (2.0f * f5) + f3, f4 + f5);
    }

    /* access modifiers changed from: protected */
    @Override // android.view.View
    public void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        int i3 = this.b;
        setMeasuredDimension(i3, i3);
    }

    /* access modifiers changed from: protected */
    @Override // android.view.View
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (this.a == 1) {
            this.f.setAntiAlias(true);
            this.f.setColor(-287515428);
            this.f.setStyle(Paint.Style.FILL);
            canvas.drawCircle(this.c, this.d, this.e, this.f);
            this.f.setColor(-16777216);
            this.f.setStyle(Paint.Style.STROKE);
            this.f.setStrokeWidth(this.h);
            Path path = this.g;
            float f = this.c;
            float f2 = this.i;
            path.moveTo(f - (f2 / 7.0f), this.d + f2);
            Path path2 = this.g;
            float f3 = this.c;
            float f4 = this.i;
            path2.lineTo(f3 + f4, this.d + f4);
            this.g.arcTo(this.j, 90.0f, -180.0f);
            Path path3 = this.g;
            float f5 = this.c;
            float f6 = this.i;
            path3.lineTo(f5 - f6, this.d - f6);
            canvas.drawPath(this.g, this.f);
            this.f.setStyle(Paint.Style.FILL);
            this.g.reset();
            Path path4 = this.g;
            float f7 = this.c;
            float f8 = this.i;
            path4.moveTo(f7 - f8, (float) (((double) this.d) - (((double) f8) * 1.5d)));
            Path path5 = this.g;
            float f9 = this.c;
            float f10 = this.i;
            path5.lineTo(f9 - f10, (float) (((double) this.d) - (((double) f10) / 2.3d)));
            Path path6 = this.g;
            float f11 = this.i;
            path6.lineTo((float) (((double) this.c) - (((double) f11) * 1.6d)), this.d - f11);
            this.g.close();
            canvas.drawPath(this.g, this.f);
        }
        if (this.a == 2) {
            this.f.setAntiAlias(true);
            this.f.setColor(-1);
            this.f.setStyle(Paint.Style.FILL);
            canvas.drawCircle(this.c, this.d, this.e, this.f);
            this.f.setAntiAlias(true);
            this.f.setStyle(Paint.Style.STROKE);
            this.f.setColor(-16724992);
            this.f.setStrokeWidth(this.h);
            this.g.moveTo(this.c - (((float) this.b) / 6.0f), this.d);
            Path path7 = this.g;
            float f12 = this.c;
            int i = this.b;
            path7.lineTo(f12 - (((float) i) / 21.2f), this.d + (((float) i) / 7.7f));
            Path path8 = this.g;
            float f13 = this.c;
            int i2 = this.b;
            path8.lineTo(f13 + (((float) i2) / 4.0f), this.d - (((float) i2) / 8.5f));
            Path path9 = this.g;
            float f14 = this.c;
            int i3 = this.b;
            path9.lineTo(f14 - (((float) i3) / 21.2f), this.d + (((float) i3) / 9.4f));
            this.g.close();
            canvas.drawPath(this.g, this.f);
        }
    }
}
