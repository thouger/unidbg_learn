package it.sephiroth.android.library.imagezoom.a;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;

/* compiled from: FastBitmapDrawable */
public class a extends Drawable {
    protected Bitmap a;
    protected Paint b;
    protected int c;
    protected int d;

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        return -3;
    }

    public a(Bitmap bitmap) {
        AppMethodBeat.i(4581, false);
        this.a = bitmap;
        Bitmap bitmap2 = this.a;
        if (bitmap2 != null) {
            this.c = bitmap2.getWidth();
            this.d = this.a.getHeight();
        } else {
            this.c = 0;
            this.d = 0;
        }
        this.b = new Paint();
        this.b.setDither(true);
        this.b.setFilterBitmap(true);
        AppMethodBeat.o(4581);
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        AppMethodBeat.i(4588, false);
        Bitmap bitmap = this.a;
        if (bitmap != null && !bitmap.isRecycled()) {
            canvas.drawBitmap(this.a, 0.0f, 0.0f, this.b);
        }
        AppMethodBeat.o(4588);
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int i) {
        AppMethodBeat.i(4591, false);
        this.b.setAlpha(i);
        AppMethodBeat.o(4591);
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter colorFilter) {
        AppMethodBeat.i(4593, false);
        this.b.setColorFilter(colorFilter);
        AppMethodBeat.o(4593);
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicWidth() {
        return this.c;
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicHeight() {
        return this.d;
    }

    @Override // android.graphics.drawable.Drawable
    public int getMinimumWidth() {
        return this.c;
    }

    @Override // android.graphics.drawable.Drawable
    public int getMinimumHeight() {
        return this.d;
    }
}
