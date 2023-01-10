package cn.missfresh.lib.image.d;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.Key;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import java.security.MessageDigest;

/* compiled from: CornerTransform */
public class a implements c {
    private final String a = getClass().getCanonicalName();
    private BitmapPool b;
    private float c;
    private float d;
    private boolean e;
    private boolean f;
    private boolean g;
    private boolean h;

    public a a(boolean z, boolean z2, boolean z3, boolean z4) {
        this.e = z;
        this.f = z2;
        this.g = z3;
        this.h = z4;
        return this;
    }

    public a(Context context, float f) {
        AppMethodBeat.i(4425, false);
        this.b = Glide.get(context).getBitmapPool();
        this.c = f;
        AppMethodBeat.o(4425);
    }

    public int hashCode() {
        AppMethodBeat.i(4428, false);
        int hashCode = this.a.hashCode() + (((int) this.c) * 1000);
        AppMethodBeat.o(4428);
        return hashCode;
    }

    public boolean equals(Object obj) {
        boolean z = false;
        AppMethodBeat.i(4433, false);
        if ((obj instanceof a) && Float.compare(((a) obj).c, this.c) == 0) {
            z = true;
        }
        AppMethodBeat.o(4433);
        return z;
    }

    public Bitmap a(Bitmap bitmap, int i, int i2) {
        int i3;
        int i4;
        AppMethodBeat.i(4439, false);
        if (i > i2) {
            float f = (float) i2;
            float f2 = (float) i;
            i3 = bitmap.getWidth();
            i4 = (int) (((float) bitmap.getWidth()) * (f / f2));
            if (i4 > bitmap.getHeight()) {
                i4 = bitmap.getHeight();
                i3 = (int) (((float) bitmap.getHeight()) * (f2 / f));
            }
        } else if (i < i2) {
            float f3 = (float) i;
            float f4 = (float) i2;
            int height = bitmap.getHeight();
            int height2 = (int) (((float) bitmap.getHeight()) * (f3 / f4));
            if (height2 > bitmap.getWidth()) {
                i3 = bitmap.getWidth();
                i4 = (int) (((float) bitmap.getWidth()) * (f4 / f3));
            } else {
                i3 = height2;
                i4 = height;
            }
        } else {
            i3 = bitmap.getHeight();
            i4 = i3;
        }
        this.d = (this.c * ((float) i4)) / ((float) i2);
        Bitmap bitmap2 = this.b.get(i3, i4, Bitmap.Config.ARGB_8888);
        if (bitmap2 == null) {
            bitmap2 = Bitmap.createBitmap(i3, i4, Bitmap.Config.ARGB_8888);
        }
        Canvas canvas = new Canvas(bitmap2);
        Paint paint = new Paint();
        BitmapShader bitmapShader = new BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
        int width = (bitmap.getWidth() - i3) / 2;
        int height3 = (bitmap.getHeight() - i4) / 2;
        if (!(width == 0 && height3 == 0)) {
            Matrix matrix = new Matrix();
            matrix.setTranslate((float) (-width), (float) (-height3));
            bitmapShader.setLocalMatrix(matrix);
        }
        paint.setShader(bitmapShader);
        paint.setAntiAlias(true);
        RectF rectF = new RectF(0.0f, 0.0f, (float) canvas.getWidth(), (float) canvas.getHeight());
        float f5 = this.d;
        canvas.drawRoundRect(rectF, f5, f5, paint);
        if (this.e) {
            float f6 = this.d;
            canvas.drawRect(0.0f, 0.0f, f6, f6, paint);
        }
        if (this.f) {
            float f7 = this.d;
            canvas.drawRect(((float) canvas.getWidth()) - f7, 0.0f, f7, f7, paint);
        }
        if (this.g) {
            float f8 = this.d;
            canvas.drawRect(0.0f, ((float) canvas.getHeight()) - f8, f8, (float) canvas.getHeight(), paint);
        }
        if (this.h) {
            canvas.drawRect(((float) canvas.getWidth()) - this.d, ((float) canvas.getHeight()) - this.d, (float) canvas.getWidth(), (float) canvas.getHeight(), paint);
        }
        AppMethodBeat.o(4439);
        return bitmap2;
    }

    public void a(MessageDigest messageDigest) {
        AppMethodBeat.i(4443, false);
        messageDigest.update((this.a + this.c).getBytes(Key.CHARSET));
        AppMethodBeat.o(4443);
    }
}
