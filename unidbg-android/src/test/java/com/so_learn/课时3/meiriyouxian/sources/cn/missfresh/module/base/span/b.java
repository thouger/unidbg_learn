package cn.missfresh.module.base.span;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.text.style.ReplacementSpan;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;

/* compiled from: RadiusBackgroundSpan */
public class b extends ReplacementSpan {
    private int a;
    private int b;
    private int c;
    private int d;
    private int e;
    private int f;
    private int g;
    private int h;
    private Paint i;
    private final int j = 1;

    public b(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
        AppMethodBeat.i(19693, false);
        this.a = i;
        this.b = i2;
        this.c = i3;
        this.e = i5;
        this.f = i6;
        this.d = i4;
        this.g = i7;
        this.h = i8;
        this.i = new Paint();
        this.i.setAntiAlias(true);
        this.i.setStrokeWidth(1.0f);
        this.i.setColor(this.d);
        this.i.setStyle(Paint.Style.STROKE);
        AppMethodBeat.o(19693);
    }

    @Override // android.text.style.ReplacementSpan
    public int getSize(Paint paint, CharSequence charSequence, int i, int i2, Paint.FontMetricsInt fontMetricsInt) {
        AppMethodBeat.i(19696, false);
        paint.setTextSize((float) this.e);
        int measureText = ((int) paint.measureText(charSequence, i, i2)) + (this.f * 2) + this.g + this.h;
        AppMethodBeat.o(19696);
        return measureText;
    }

    @Override // android.text.style.ReplacementSpan
    public void draw(Canvas canvas, CharSequence charSequence, int i, int i2, float f, int i3, int i4, int i5, Paint paint) {
        AppMethodBeat.i(19698, false);
        paint.setTextSize((float) this.e);
        paint.setAntiAlias(true);
        RectF rectF = new RectF();
        rectF.left = (float) (((int) f) + this.g);
        Paint.FontMetricsInt fontMetricsInt = paint.getFontMetricsInt();
        int i6 = (((i5 - i3) - fontMetricsInt.descent) + fontMetricsInt.top) / 2;
        rectF.top = (float) (i3 + i6 + 1);
        rectF.bottom = (float) ((i5 - i6) + 2);
        rectF.right = rectF.left + ((float) ((int) paint.measureText(charSequence, i, i2))) + ((float) (this.f * 2));
        paint.setColor(this.a);
        int i7 = this.b;
        canvas.drawRoundRect(rectF, (float) i7, (float) i7, paint);
        int i8 = this.b;
        canvas.drawRoundRect(rectF, (float) i8, (float) i8, this.i);
        paint.setColor(this.c);
        canvas.drawText(charSequence, i, i2, f + ((float) this.f) + ((float) this.g), ((float) i4) + ((float) ((((fontMetricsInt.descent - fontMetricsInt.ascent) / 2) + fontMetricsInt.ascent) / 2)) + 2.0f, paint);
        AppMethodBeat.o(19698);
    }
}
