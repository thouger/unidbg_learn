package cn.missfresh.module.base.span;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.text.style.ImageSpan;
import cn.missfresh.module.base.utils.aw;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;

/* compiled from: CenterImageSpan */
public class a extends ImageSpan {
    private Drawable a = null;
    private Bitmap b;

    public a(Context context, Bitmap bitmap) {
        super(context, bitmap, 1);
        this.b = bitmap;
    }

    @Override // android.text.style.DynamicDrawableSpan, android.text.style.ReplacementSpan
    public int getSize(Paint paint, CharSequence charSequence, int i, int i2, Paint.FontMetricsInt fontMetricsInt) {
        AppMethodBeat.i(19675, false);
        a(paint);
        int intrinsicWidth = getDrawable().getIntrinsicWidth();
        AppMethodBeat.o(19675);
        return intrinsicWidth;
    }

    @Override // android.text.style.DynamicDrawableSpan, android.text.style.ReplacementSpan
    public void draw(Canvas canvas, CharSequence charSequence, int i, int i2, float f, int i3, int i4, int i5, Paint paint) {
        AppMethodBeat.i(19677, false);
        Drawable drawable = this.a;
        if (drawable == null) {
            drawable = getDrawable();
        }
        Paint.FontMetricsInt fontMetricsInt = paint.getFontMetricsInt();
        canvas.save();
        canvas.translate(f, (float) (((((fontMetricsInt.descent + i4) + i4) + fontMetricsInt.ascent) / 2) - (drawable.getBounds().bottom / 2)));
        drawable.draw(canvas);
        canvas.restore();
        AppMethodBeat.o(19677);
    }

    private void a(Paint paint) {
        AppMethodBeat.i(19678, false);
        Paint.FontMetrics fontMetrics = paint.getFontMetrics();
        int ceil = (int) Math.ceil((double) (fontMetrics.descent - fontMetrics.ascent));
        if (ceil != getDrawable().getIntrinsicHeight()) {
            int intrinsicWidth = (getDrawable().getIntrinsicWidth() * ceil) / getDrawable().getIntrinsicHeight();
            this.a = new BitmapDrawable(aw.c(), this.b);
            this.a.setBounds(0, 0, intrinsicWidth, ceil);
        }
        AppMethodBeat.o(19678);
    }
}
