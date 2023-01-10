package cn.missfresh.module.base.utils;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.ExifInterface;
import android.net.Uri;
import android.provider.MediaStore;
import android.text.Spanned;
import android.webkit.URLUtil;
import cn.missfresh.basiclib.utils.a;
import cn.missfresh.module.base.common.config.c;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import cn.missfresh.utils.a.d;
import com.android.internal.logging.nano.MetricsProto;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/* compiled from: BitmapUtil */
public class l {
    private boolean a = false;

    public static String a(Context context, String str, String str2, int i, int i2, int i3) throws Exception {
        int a;
        AppMethodBeat.i(23115, false);
        Bitmap a2 = a.a(context, str, i, i2);
        if (!(a2 == null || (a = a(context, str)) == 0)) {
            a2 = a(a2, a);
        }
        if (a2 != null) {
            File file = new File(c.g);
            if (file.exists() || file.mkdirs()) {
                File file2 = new File(file, str2);
                FileOutputStream fileOutputStream = new FileOutputStream(file2);
                try {
                    a2.compress(a2.hasAlpha() ? Bitmap.CompressFormat.PNG : Bitmap.CompressFormat.JPEG, i3, fileOutputStream);
                    a2.recycle();
                    try {
                        fileOutputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } catch (Exception e2) {
                    e2.printStackTrace();
                    fileOutputStream.close();
                } catch (Throwable th) {
                    try {
                        fileOutputStream.close();
                    } catch (IOException e3) {
                        e3.printStackTrace();
                    }
                    AppMethodBeat.o(23115);
                    throw th;
                }
                String path = file2.getPath();
                AppMethodBeat.o(23115);
                return path;
            }
            IOException iOException = new IOException("make imageDir failed");
            AppMethodBeat.o(23115);
            throw iOException;
        }
        IOException iOException2 = new IOException("bitmap is null");
        AppMethodBeat.o(23115);
        throw iOException2;
    }

    private static int a(Context context, String str) {
        int i;
        int i2 = 0;
        AppMethodBeat.i(23116, false);
        try {
            if (URLUtil.isValidUrl(str)) {
                i = a(context, Uri.parse(str));
            } else {
                int attributeInt = new ExifInterface(str).getAttributeInt("Orientation", 1);
                if (attributeInt == 3) {
                    i = 180;
                } else if (attributeInt != 6) {
                    if (attributeInt == 8) {
                        i = 270;
                    }
                    AppMethodBeat.o(23116);
                    return i2;
                } else {
                    i = 90;
                }
            }
            i2 = i;
        } catch (IOException e) {
            e.printStackTrace();
        }
        AppMethodBeat.o(23116);
        return i2;
    }

    private static int a(Context context, Uri uri) {
        int i = 0;
        int i2 = 23117;
        AppMethodBeat.i(23117, false);
        Cursor cursor = null;
        try {
            cursor = context.getContentResolver().query(uri, new String[]{MediaStore.Images.ImageColumns.ORIENTATION}, null, null, null);
            if (cursor != null && cursor.moveToFirst()) {
                i = cursor.getInt(0);
            }
            return i;
        } finally {
            if (cursor != null) {
                cursor.close();
            }
            AppMethodBeat.o(i2);
        }
    }

    public static Bitmap a(Bitmap bitmap, int i) {
        AppMethodBeat.i(23118, false);
        if (bitmap != null) {
            Matrix matrix = new Matrix();
            matrix.postRotate((float) i);
            Bitmap createBitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
            AppMethodBeat.o(23118);
            return createBitmap;
        }
        AppMethodBeat.o(23118);
        return bitmap;
    }

    public static Bitmap a(Bitmap bitmap, float f, float f2) {
        AppMethodBeat.i(23119, false);
        if (f > 1.0f || f2 > 1.0f) {
            AppMethodBeat.o(23119);
            return null;
        }
        float f3 = 1.0f / f;
        float f4 = 1.0f / f2;
        int i = (int) f3;
        int i2 = (int) f4;
        int i3 = i * i2;
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        int i4 = (int) (((float) width) * f);
        int i5 = (int) (((float) height) * f2);
        int[] iArr = new int[(width * height)];
        bitmap.getPixels(iArr, 0, width, 0, 0, width, height);
        int[] iArr2 = new int[(i4 * i5)];
        for (int i6 = 0; i6 < i5; i6++) {
            int i7 = 0;
            while (i7 < i4) {
                int i8 = 0;
                int i9 = 0;
                int i10 = 0;
                int i11 = 0;
                while (i8 < i2) {
                    int i12 = i11;
                    int i13 = i10;
                    int i14 = i9;
                    int i15 = 0;
                    while (i15 < i) {
                        int i16 = (((int) ((((float) i6) * f4) + ((float) i8))) * width) + ((int) ((((float) i7) * f3) + ((float) i15)));
                        i14 += (iArr[i16] & Spanned.SPAN_PRIORITY) >>> 16;
                        i13 += (iArr[i16] & 65280) >>> 8;
                        i12 += iArr[i16] & 255;
                        i15++;
                        i = i;
                        f4 = f4;
                        f3 = f3;
                    }
                    i8++;
                    i9 = i14;
                    i10 = i13;
                    i11 = i12;
                }
                iArr2[(i6 * i4) + i7] = -16777216 | ((i9 / i3) << 16) | ((i10 / i3) << 8) | (i11 / i3);
                i7++;
                i = i;
                f4 = f4;
                f3 = f3;
            }
        }
        Bitmap createBitmap = Bitmap.createBitmap(iArr2, i4, i5, Bitmap.Config.ARGB_8888);
        AppMethodBeat.o(23119);
        return createBitmap;
    }

    public static Bitmap a(Bitmap bitmap, Bitmap bitmap2, boolean z, int i, int i2) {
        AppMethodBeat.i(23120, false);
        try {
            Bitmap createScaledBitmap = Bitmap.createScaledBitmap(bitmap, MetricsProto.MetricsEvent.SETTINGS_LANGUAGE_CATEGORY, i, false);
            Bitmap createScaledBitmap2 = Bitmap.createScaledBitmap(bitmap2, MetricsProto.MetricsEvent.SETTINGS_LANGUAGE_CATEGORY, i2, false);
            int i3 = i + i2;
            Bitmap createBitmap = Bitmap.createBitmap(MetricsProto.MetricsEvent.SETTINGS_LANGUAGE_CATEGORY, i3, Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(createBitmap);
            Rect rect = new Rect(0, 0, createScaledBitmap.getWidth(), createScaledBitmap.getHeight());
            Rect rect2 = new Rect(0, 0, createScaledBitmap2.getWidth(), createScaledBitmap2.getHeight());
            Rect rect3 = new Rect(0, createScaledBitmap.getHeight(), MetricsProto.MetricsEvent.SETTINGS_LANGUAGE_CATEGORY, i3);
            canvas.drawBitmap(createScaledBitmap, rect, rect, (Paint) null);
            canvas.drawBitmap(createScaledBitmap2, rect2, rect3, (Paint) null);
            bitmap.recycle();
            bitmap2.recycle();
            AppMethodBeat.o(23120);
            return createBitmap;
        } catch (Exception unused) {
            AppMethodBeat.o(23120);
            return bitmap;
        }
    }

    public byte[] b(Bitmap bitmap, int i) {
        AppMethodBeat.i(23121, false);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        int i2 = 98;
        bitmap.compress(Bitmap.CompressFormat.JPEG, 98, byteArrayOutputStream);
        while (byteArrayOutputStream.toByteArray().length > i && !this.a && i2 >= 20) {
            byteArrayOutputStream.reset();
            bitmap.compress(Bitmap.CompressFormat.JPEG, i2, byteArrayOutputStream);
            i2 -= 2;
        }
        if (byteArrayOutputStream.toByteArray().length <= i || this.a) {
            byte[] byteArray = byteArrayOutputStream.toByteArray();
            AppMethodBeat.o(23121);
            return byteArray;
        }
        byte[] a = a(byteArrayOutputStream, i);
        AppMethodBeat.o(23121);
        return a;
    }

    private byte[] a(ByteArrayOutputStream byteArrayOutputStream, int i) {
        AppMethodBeat.i(23122, false);
        Bitmap decodeByteArray = BitmapFactory.decodeByteArray(byteArrayOutputStream.toByteArray(), 0, byteArrayOutputStream.toByteArray().length);
        int width = decodeByteArray.getWidth();
        int height = decodeByteArray.getHeight();
        if (width <= 0 || height <= 0) {
            byte[] byteArray = byteArrayOutputStream.toByteArray();
            AppMethodBeat.o(23122);
            return byteArray;
        }
        float sqrt = (float) Math.sqrt((double) (((float) i) / ((float) ((height * width) * 4))));
        if (sqrt >= 1.0f) {
            byte[] byteArray2 = byteArrayOutputStream.toByteArray();
            AppMethodBeat.o(23122);
            return byteArray2;
        }
        Bitmap createScaledBitmap = Bitmap.createScaledBitmap(decodeByteArray, (int) (((float) width) * sqrt), (int) (((float) height) * sqrt), true);
        ByteArrayOutputStream byteArrayOutputStream2 = new ByteArrayOutputStream();
        createScaledBitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream2);
        byte[] byteArray3 = byteArrayOutputStream2.toByteArray();
        AppMethodBeat.o(23122);
        return byteArray3;
    }

    public byte[] c(Bitmap bitmap, int i) {
        AppMethodBeat.i(23123, false);
        byte[] bArr = null;
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            bitmap.compress(bitmap.hasAlpha() ? Bitmap.CompressFormat.PNG : Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
            int size = byteArrayOutputStream.size();
            d.d("BitmapUtil", "bitmap2Bytes bitmapSize:" + size);
            while (size >= i && !this.a) {
                bitmap = a(bitmap, 0.8f, 0.8f);
                byteArrayOutputStream.reset();
                bitmap.compress(bitmap.hasAlpha() ? Bitmap.CompressFormat.PNG : Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
                size = byteArrayOutputStream.size();
            }
            bArr = byteArrayOutputStream.toByteArray();
            d.d("BitmapUtil", "bitmap2Bytes resize:0.8,rs.size:" + bArr.length);
            byteArrayOutputStream.close();
        } catch (IOException e) {
            d.a("BitmapUtil", (Exception) e);
        }
        AppMethodBeat.o(23123);
        return bArr;
    }

    public double a(int i, int i2, int i3) {
        AppMethodBeat.i(23124, false);
        double sqrt = Math.sqrt((double) (((float) i3) / ((float) ((i * 4) * i2))));
        AppMethodBeat.o(23124);
        return sqrt;
    }

    public Bitmap a(Bitmap bitmap) {
        AppMethodBeat.i(23125, false);
        Bitmap d = d(bitmap, 31744);
        AppMethodBeat.o(23125);
        return d;
    }

    public Bitmap d(Bitmap bitmap, int i) {
        AppMethodBeat.i(23126, false);
        if (bitmap == null) {
            AppMethodBeat.o(23126);
            return null;
        }
        double a = a(bitmap.getWidth(), bitmap.getHeight(), i);
        Bitmap a2 = a(bitmap, (int) (((double) bitmap.getWidth()) * a), (int) (((double) bitmap.getHeight()) * a));
        AppMethodBeat.o(23126);
        return a2;
    }

    public Bitmap a(Bitmap bitmap, int i, int i2) {
        AppMethodBeat.i(23127, false);
        BitmapFactory.Options options = new BitmapFactory.Options();
        int i3 = 1;
        options.inJustDecodeBounds = true;
        int width = bitmap.getWidth() / i;
        int height = bitmap.getHeight() / i2;
        if (width < height) {
            width = height;
        }
        if (width > 1) {
            i3 = width;
        }
        options.inSampleSize = aa.b(i3, 2);
        options.inJustDecodeBounds = false;
        try {
            byte[] b = b(bitmap);
            bitmap = BitmapFactory.decodeByteArray(b, 0, b.length, options);
        } catch (IOException e) {
            e.printStackTrace();
        }
        AppMethodBeat.o(23127);
        return bitmap;
    }

    private byte[] b(Bitmap bitmap) throws IOException {
        AppMethodBeat.i(23128, false);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        byteArrayOutputStream.close();
        AppMethodBeat.o(23128);
        return byteArray;
    }

    public byte[] a(Bitmap bitmap, boolean z) {
        AppMethodBeat.i(23129, false);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
        if (z) {
            bitmap.recycle();
        }
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        try {
            byteArrayOutputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        AppMethodBeat.o(23129);
        return byteArray;
    }

    public static Uri a(Context context, Bitmap bitmap) throws Exception {
        AppMethodBeat.i(23130, false);
        Uri a = a.a(context, bitmap, "cache" + System.currentTimeMillis() + ".jpg");
        AppMethodBeat.o(23130);
        return a;
    }

    public static Bitmap a(Drawable drawable) {
        AppMethodBeat.i(23131, false);
        if (drawable instanceof BitmapDrawable) {
            Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();
            AppMethodBeat.o(23131);
            return bitmap;
        }
        int intrinsicWidth = drawable.getIntrinsicWidth();
        int intrinsicHeight = drawable.getIntrinsicHeight();
        Bitmap createBitmap = Bitmap.createBitmap(intrinsicWidth, intrinsicHeight, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        drawable.setBounds(0, 0, intrinsicWidth, intrinsicHeight);
        drawable.draw(canvas);
        AppMethodBeat.o(23131);
        return createBitmap;
    }
}
