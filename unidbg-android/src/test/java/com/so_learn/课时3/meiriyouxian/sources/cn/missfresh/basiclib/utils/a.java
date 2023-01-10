package cn.missfresh.basiclib.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.text.TextUtils;
import android.webkit.URLUtil;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import cn.missfresh.utils.b;

/* compiled from: MFBitmapUtils */
public class a {
    public static Bitmap a(Context context, String str, int i, int i2) throws Exception {
        AppMethodBeat.i(4686, false);
        if (context == null) {
            NullPointerException nullPointerException = new NullPointerException("context is null");
            AppMethodBeat.o(4686);
            throw nullPointerException;
        } else if (TextUtils.isEmpty(str)) {
            IllegalArgumentException illegalArgumentException = new IllegalArgumentException("imagePath is empty");
            AppMethodBeat.o(4686);
            throw illegalArgumentException;
        } else if (URLUtil.isValidUrl(str)) {
            Bitmap a = a(context, Uri.parse(str), i, i2);
            AppMethodBeat.o(4686);
            return a;
        } else {
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeFile(str, options);
            options.inSampleSize = a(options, Math.max(1, i), Math.max(1, i2));
            options.inJustDecodeBounds = false;
            Bitmap decodeFile = BitmapFactory.decodeFile(str, options);
            if (decodeFile != null) {
                AppMethodBeat.o(4686);
                return decodeFile;
            }
            NullPointerException nullPointerException2 = new NullPointerException("bitmap compress failed");
            AppMethodBeat.o(4686);
            throw nullPointerException2;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:29:0x0079  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static android.graphics.Bitmap a(android.content.Context r6, android.net.Uri r7, int r8, int r9) throws java.lang.Exception {
        /*
            r0 = 0
            r1 = 4690(0x1252, float:6.572E-42)
            cn.missfresh.sherlock.trace.core.AppMethodBeat.i(r1, r0)
            if (r6 == 0) goto L_0x008c
            if (r7 == 0) goto L_0x0080
            r2 = 0
            android.content.ContentResolver r3 = r6.getContentResolver()     // Catch:{ all -> 0x0075 }
            java.io.InputStream r3 = r3.openInputStream(r7)     // Catch:{ all -> 0x0075 }
            if (r3 == 0) goto L_0x0069
            android.graphics.BitmapFactory$Options r4 = new android.graphics.BitmapFactory$Options     // Catch:{ all -> 0x0067 }
            r4.<init>()     // Catch:{ all -> 0x0067 }
            r5 = 1
            r4.inJustDecodeBounds = r5     // Catch:{ all -> 0x0067 }
            android.graphics.BitmapFactory.decodeStream(r3, r2, r4)     // Catch:{ all -> 0x0067 }
            int r8 = java.lang.Math.max(r5, r8)     // Catch:{ all -> 0x0067 }
            int r9 = java.lang.Math.max(r5, r9)     // Catch:{ all -> 0x0067 }
            int r8 = a(r4, r8, r9)     // Catch:{ all -> 0x0067 }
            r4.inSampleSize = r8     // Catch:{ all -> 0x0067 }
            r4.inJustDecodeBounds = r0     // Catch:{ all -> 0x0067 }
            r3.close()     // Catch:{ all -> 0x0067 }
            android.content.ContentResolver r6 = r6.getContentResolver()     // Catch:{ all -> 0x0067 }
            java.io.InputStream r6 = r6.openInputStream(r7)     // Catch:{ all -> 0x0067 }
            if (r6 == 0) goto L_0x005b
            android.graphics.Bitmap r7 = android.graphics.BitmapFactory.decodeStream(r6, r2, r4)     // Catch:{ all -> 0x0058 }
            if (r7 == 0) goto L_0x004c
            if (r6 == 0) goto L_0x0048
            r6.close()
        L_0x0048:
            cn.missfresh.sherlock.trace.core.AppMethodBeat.o(r1)
            return r7
        L_0x004c:
            java.lang.NullPointerException r7 = new java.lang.NullPointerException
            java.lang.String r8 = "compress bitmap failed"
            r7.<init>(r8)
            cn.missfresh.sherlock.trace.core.AppMethodBeat.o(r1)
            throw r7
        L_0x0058:
            r7 = move-exception
            r3 = r6
            goto L_0x0077
        L_0x005b:
            java.lang.NullPointerException r7 = new java.lang.NullPointerException
            java.lang.String r8 = "open bitmap stream failed"
            r7.<init>(r8)
            cn.missfresh.sherlock.trace.core.AppMethodBeat.o(r1)
            throw r7
        L_0x0067:
            r7 = move-exception
            goto L_0x0077
        L_0x0069:
            java.lang.NullPointerException r6 = new java.lang.NullPointerException
            java.lang.String r7 = "open bitmap stream for decode bounds failed"
            r6.<init>(r7)
            cn.missfresh.sherlock.trace.core.AppMethodBeat.o(r1)
            throw r6
        L_0x0075:
            r7 = move-exception
            r3 = r2
        L_0x0077:
            if (r3 == 0) goto L_0x007c
            r3.close()
        L_0x007c:
            cn.missfresh.sherlock.trace.core.AppMethodBeat.o(r1)
            throw r7
        L_0x0080:
            java.lang.NullPointerException r6 = new java.lang.NullPointerException
            java.lang.String r7 = "uri is null"
            r6.<init>(r7)
            cn.missfresh.sherlock.trace.core.AppMethodBeat.o(r1)
            throw r6
        L_0x008c:
            java.lang.NullPointerException r6 = new java.lang.NullPointerException
            java.lang.String r7 = "context is null"
            r6.<init>(r7)
            cn.missfresh.sherlock.trace.core.AppMethodBeat.o(r1)
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.missfresh.basiclib.utils.a.a(android.content.Context, android.net.Uri, int, int):android.graphics.Bitmap");
    }

    public static Uri a(Context context, Bitmap bitmap, String str) throws Exception {
        AppMethodBeat.i(4692, false);
        if (TextUtils.isEmpty(str)) {
            str = System.currentTimeMillis() + ".jpg";
        }
        Uri a = a(context, bitmap, Bitmap.CompressFormat.JPEG, 90, "image/jpeg", str);
        AppMethodBeat.o(4692);
        return a;
    }

    /* JADX WARNING: Removed duplicated region for block: B:55:0x00d0  */
    /* JADX WARNING: Removed duplicated region for block: B:57:0x00d5  */
    /* JADX WARNING: Removed duplicated region for block: B:59:0x00da  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static android.net.Uri a(android.content.Context r8, android.graphics.Bitmap r9, android.graphics.Bitmap.CompressFormat r10, int r11, java.lang.String r12, java.lang.String r13) throws java.lang.Exception {
        /*
        // Method dump skipped, instructions count: 297
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.missfresh.basiclib.utils.a.a(android.content.Context, android.graphics.Bitmap, android.graphics.Bitmap$CompressFormat, int, java.lang.String, java.lang.String):android.net.Uri");
    }

    private static int a(BitmapFactory.Options options, int i, int i2) {
        int i3;
        AppMethodBeat.i(4703, false);
        int i4 = options.outHeight;
        int i5 = options.outWidth;
        if (i4 > i2 || i5 > i) {
            int round = Math.round(((float) i4) / ((float) i2));
            i3 = Math.round(((float) i5) / ((float) i));
            if (round < i3) {
                i3 = round;
            }
        } else {
            i3 = 1;
        }
        AppMethodBeat.o(4703);
        return i3;
    }

    public static int[] a(String str) {
        AppMethodBeat.i(4707, false);
        if (b.a(str)) {
            AppMethodBeat.o(4707);
            return null;
        }
        try {
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;
            options.inSampleSize = 1;
            BitmapFactory.decodeFile(str, options);
            int[] iArr = {options.outWidth, options.outHeight};
            AppMethodBeat.o(4707);
            return iArr;
        } catch (Exception e) {
            e.printStackTrace();
            AppMethodBeat.o(4707);
            return null;
        }
    }
}
