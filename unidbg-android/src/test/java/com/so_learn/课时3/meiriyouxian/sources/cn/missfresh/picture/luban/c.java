package cn.missfresh.picture.luban;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/* compiled from: Engine */
/* access modifiers changed from: package-private */
public class c {
    private e a;
    private File b;
    private int c;
    private int d;
    private boolean e;

    c(e eVar, File file, boolean z) throws IOException {
        AppMethodBeat.i(18639, false);
        this.b = file;
        this.a = eVar;
        this.e = z;
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        options.inSampleSize = 1;
        BitmapFactory.decodeStream(eVar.a(), null, options);
        this.c = options.outWidth;
        this.d = options.outHeight;
        AppMethodBeat.o(18639);
    }

    private int b() {
        AppMethodBeat.i(18642, false);
        int i = this.c;
        if (i % 2 == 1) {
            i++;
        }
        this.c = i;
        int i2 = this.d;
        if (i2 % 2 == 1) {
            i2++;
        }
        this.d = i2;
        int max = Math.max(this.c, this.d);
        float min = ((float) Math.min(this.c, this.d)) / ((float) max);
        if (min > 1.0f || ((double) min) <= 0.5625d) {
            double d = (double) min;
            if (d > 0.5625d || d <= 0.5d) {
                int ceil = (int) Math.ceil(((double) max) / (1280.0d / d));
                AppMethodBeat.o(18642);
                return ceil;
            }
            int i3 = max / 1280;
            if (i3 == 0) {
                i3 = 1;
            }
            AppMethodBeat.o(18642);
            return i3;
        } else if (max < 1664) {
            AppMethodBeat.o(18642);
            return 1;
        } else if (max < 4990) {
            AppMethodBeat.o(18642);
            return 2;
        } else if (max <= 4990 || max >= 10240) {
            int i4 = max / 1280;
            if (i4 == 0) {
                i4 = 1;
            }
            AppMethodBeat.o(18642);
            return i4;
        } else {
            AppMethodBeat.o(18642);
            return 4;
        }
    }

    private Bitmap a(Bitmap bitmap, int i) {
        AppMethodBeat.i(18646, false);
        Matrix matrix = new Matrix();
        matrix.postRotate((float) i);
        Bitmap createBitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
        AppMethodBeat.o(18646);
        return createBitmap;
    }

    /* access modifiers changed from: package-private */
    public File a() throws IOException {
        AppMethodBeat.i(18648, false);
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize = b();
        Bitmap decodeStream = BitmapFactory.decodeStream(this.a.a(), null, options);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        if (Checker.SINGLE.isJPG(this.a.a())) {
            decodeStream = a(decodeStream, Checker.SINGLE.getOrientation(this.a.a()));
        }
        decodeStream.compress(this.e ? Bitmap.CompressFormat.PNG : Bitmap.CompressFormat.JPEG, 60, byteArrayOutputStream);
        decodeStream.recycle();
        FileOutputStream fileOutputStream = new FileOutputStream(this.b);
        fileOutputStream.write(byteArrayOutputStream.toByteArray());
        fileOutputStream.flush();
        fileOutputStream.close();
        byteArrayOutputStream.close();
        File file = this.b;
        AppMethodBeat.o(18648);
        return file;
    }
}
