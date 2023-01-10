package cn.missfresh.picture.business;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;
import com.umeng.message.proguard.l;
import java.security.MessageDigest;

public class BlurTransformation extends BitmapTransformation {
    private static final int DEFAULT_DOWN_SAMPLING = 1;
    private static final String ID = "BlurTransformation.1";
    private static final int MAX_RADIUS = 25;
    private static final int VERSION = 1;
    private int radius;
    private int sampling;

    public BlurTransformation() {
        this(25, 1);
    }

    public BlurTransformation(int i) {
        this(i, 1);
    }

    public BlurTransformation(int i, int i2) {
        this.radius = i;
        this.sampling = i2;
    }

    /* access modifiers changed from: protected */
    @Override // com.bumptech.glide.load.resource.bitmap.BitmapTransformation
    public Bitmap transform(BitmapPool bitmapPool, Bitmap bitmap, int i, int i2) {
        AppMethodBeat.i(19317, false);
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        int i3 = this.sampling;
        Bitmap bitmap2 = bitmapPool.get(width / i3, height / i3, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap2);
        int i4 = this.sampling;
        canvas.scale(1.0f / ((float) i4), 1.0f / ((float) i4));
        Paint paint = new Paint();
        paint.setFlags(2);
        canvas.drawBitmap(bitmap, 0.0f, 0.0f, paint);
        Bitmap blur = FastBlur.blur(bitmap2, this.radius, true);
        AppMethodBeat.o(19317);
        return blur;
    }

    public String toString() {
        AppMethodBeat.i(19319, false);
        String str = "BlurTransformation(radius=" + this.radius + ", sampling=" + this.sampling + l.t;
        AppMethodBeat.o(19319);
        return str;
    }

    @Override // com.bumptech.glide.load.Key
    public boolean equals(Object obj) {
        if (obj instanceof BlurTransformation) {
            BlurTransformation blurTransformation = (BlurTransformation) obj;
            if (blurTransformation.radius == this.radius && blurTransformation.sampling == this.sampling) {
                return true;
            }
        }
        return false;
    }

    @Override // com.bumptech.glide.load.Key
    public int hashCode() {
        AppMethodBeat.i(19323, false);
        int hashCode = ID.hashCode() + (this.radius * 1000) + (this.sampling * 10);
        AppMethodBeat.o(19323);
        return hashCode;
    }

    @Override // com.bumptech.glide.load.Key
    public void updateDiskCacheKey(MessageDigest messageDigest) {
        AppMethodBeat.i(19325, false);
        messageDigest.update((ID + this.radius + this.sampling).getBytes(CHARSET));
        AppMethodBeat.o(19325);
    }
}
