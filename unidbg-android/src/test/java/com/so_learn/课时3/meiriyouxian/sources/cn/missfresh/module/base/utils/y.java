package cn.missfresh.module.base.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import cn.missfresh.lib.image.c;
import cn.missfresh.lib.image.d.b;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import com.bumptech.glide.load.Key;
import java.security.MessageDigest;

/* compiled from: GlideRoundTransform */
public class y extends b {
    private boolean a;
    private boolean b;
    private int c = 0;

    public y(int i) {
        this.c = i;
    }

    public y(int i, boolean z, boolean z2) {
        this.c = i;
        this.a = z;
        this.b = z2;
    }

    public Bitmap a(Bitmap bitmap, int i, int i2) {
        AppMethodBeat.i(23323, false);
        if (bitmap == null) {
            AppMethodBeat.o(23323);
            return null;
        }
        Bitmap a = c.a(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        if (a == null) {
            a = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        }
        Canvas canvas = new Canvas(a);
        Paint paint = new Paint();
        paint.setShader(new BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP));
        paint.setAntiAlias(true);
        RectF rectF = new RectF(0.0f, 0.0f, (float) bitmap.getWidth(), (float) bitmap.getHeight());
        int i3 = this.c;
        canvas.drawRoundRect(rectF, (float) i3, (float) i3, paint);
        if (this.a) {
            canvas.drawRect(new RectF(0.0f, (float) this.c, (float) bitmap.getWidth(), (float) bitmap.getHeight()), paint);
        } else if (this.b) {
            canvas.drawRect(new RectF(0.0f, 0.0f, (float) bitmap.getWidth(), (float) (bitmap.getHeight() - this.c)), paint);
        }
        AppMethodBeat.o(23323);
        return a;
    }

    public int hashCode() {
        AppMethodBeat.i(23324, false);
        int hashCode = "cn.missfresh.module.base.utils.GlideRoundTransform".hashCode() + (this.c * 1000);
        AppMethodBeat.o(23324);
        return hashCode;
    }

    @Override // cn.missfresh.lib.image.d.b
    public boolean equals(Object obj) {
        return (obj instanceof y) && ((y) obj).c == this.c;
    }

    public void a(MessageDigest messageDigest) {
        AppMethodBeat.i(23325, false);
        messageDigest.update(("cn.missfresh.module.base.utils.GlideRoundTransform" + this.c).getBytes(Key.CHARSET));
        AppMethodBeat.o(23325);
    }
}
