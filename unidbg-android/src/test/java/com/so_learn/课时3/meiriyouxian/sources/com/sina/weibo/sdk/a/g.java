package com.sina.weibo.sdk.a;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.text.TextUtils;
import android.util.StateSet;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Locale;
import org.apache.http.util.EncodingUtils;

/* compiled from: ResourceManager */
public class g {
    private static final String a = g.class.getName();
    private static final String[] b = {"drawable-xxhdpi", "drawable-xhdpi", "drawable-hdpi", "drawable-mdpi", "drawable-ldpi", "drawable"};

    public static String a(Context context, String str, String str2, String str3) {
        Locale a2 = a();
        if (Locale.SIMPLIFIED_CHINESE.equals(a2)) {
            return str2;
        }
        return Locale.TRADITIONAL_CHINESE.equals(a2) ? str3 : str;
    }

    public static Drawable a(Context context, String str) {
        return a(context, d(context, str), false);
    }

    public static Drawable b(Context context, String str) {
        return a(context, d(context, str), true);
    }

    public static Locale a() {
        Locale locale = Locale.getDefault();
        return (Locale.SIMPLIFIED_CHINESE.equals(locale) || Locale.TRADITIONAL_CHINESE.equals(locale)) ? locale : Locale.ENGLISH;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0069, code lost:
        if (java.lang.Math.abs(r4 - r2) <= java.lang.Math.abs(r4 - r5)) goto L_0x0076;
     */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x007f  */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x0085  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static java.lang.String d(android.content.Context r9, java.lang.String r10) {
        /*
            boolean r0 = android.text.TextUtils.isEmpty(r10)
            r1 = 0
            if (r0 == 0) goto L_0x0010
            java.lang.String r9 = com.sina.weibo.sdk.a.g.a
            java.lang.String r10 = "id is NOT correct!"
            com.sina.weibo.sdk.a.d.c(r9, r10)
            return r1
        L_0x0010:
            java.lang.String r0 = a(r9)
            java.lang.String r2 = com.sina.weibo.sdk.a.g.a
            java.lang.String r3 = "find Appropriate path..."
            com.sina.weibo.sdk.a.d.a(r2, r3)
            r2 = 0
            r3 = -1
            r4 = r3
            r5 = r4
        L_0x0020:
            java.lang.String[] r6 = com.sina.weibo.sdk.a.g.b
            int r7 = r6.length
            java.lang.String r8 = "/"
            if (r2 < r7) goto L_0x002a
            r2 = r3
            goto L_0x0057
        L_0x002a:
            r6 = r6[r2]
            boolean r6 = r6.equals(r0)
            if (r6 == 0) goto L_0x0033
            r4 = r2
        L_0x0033:
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            java.lang.String[] r7 = com.sina.weibo.sdk.a.g.b
            r7 = r7[r2]
            java.lang.String r7 = java.lang.String.valueOf(r7)
            r6.<init>(r7)
            r6.append(r8)
            r6.append(r10)
            java.lang.String r6 = r6.toString()
            boolean r7 = e(r9, r6)
            if (r7 == 0) goto L_0x009d
            if (r4 != r2) goto L_0x0053
            return r6
        L_0x0053:
            if (r4 >= 0) goto L_0x0057
            r5 = r2
            goto L_0x009d
        L_0x0057:
            java.lang.String r9 = "Not find the appropriate path for drawable"
            if (r5 <= 0) goto L_0x006c
            if (r2 <= 0) goto L_0x006c
            int r0 = r4 - r2
            int r0 = java.lang.Math.abs(r0)
            int r4 = r4 - r5
            int r3 = java.lang.Math.abs(r4)
            if (r0 > r3) goto L_0x0070
            goto L_0x0076
        L_0x006c:
            if (r5 <= 0) goto L_0x0072
            if (r2 >= 0) goto L_0x0072
        L_0x0070:
            r3 = r5
            goto L_0x007d
        L_0x0072:
            if (r5 >= 0) goto L_0x0078
            if (r2 <= 0) goto L_0x0078
        L_0x0076:
            r3 = r2
            goto L_0x007d
        L_0x0078:
            java.lang.String r0 = com.sina.weibo.sdk.a.g.a
            com.sina.weibo.sdk.a.d.c(r0, r9)
        L_0x007d:
            if (r3 >= 0) goto L_0x0085
            java.lang.String r10 = com.sina.weibo.sdk.a.g.a
            com.sina.weibo.sdk.a.d.c(r10, r9)
            return r1
        L_0x0085:
            java.lang.StringBuilder r9 = new java.lang.StringBuilder
            java.lang.String[] r0 = com.sina.weibo.sdk.a.g.b
            r0 = r0[r3]
            java.lang.String r0 = java.lang.String.valueOf(r0)
            r9.<init>(r0)
            r9.append(r8)
            r9.append(r10)
            java.lang.String r9 = r9.toString()
            return r9
        L_0x009d:
            int r2 = r2 + 1
            goto L_0x0020
        */
        throw new UnsupportedOperationException("Method not decompiled: com.sina.weibo.sdk.a.g.d(android.content.Context, java.lang.String):java.lang.String");
    }

    /* JADX WARNING: Removed duplicated region for block: B:23:0x0064  */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x006b A[SYNTHETIC, Splitter:B:27:0x006b] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static android.graphics.drawable.Drawable a(android.content.Context r8, java.lang.String r9, boolean r10) {
        /*
            android.content.res.AssetManager r0 = r8.getAssets()
            r1 = 0
            java.io.InputStream r9 = r0.open(r9)     // Catch:{ IOException -> 0x005d, all -> 0x005a }
            if (r9 == 0) goto L_0x004f
            android.graphics.Bitmap r4 = android.graphics.BitmapFactory.decodeStream(r9)     // Catch:{ IOException -> 0x004d }
            android.content.res.Resources r0 = r8.getResources()     // Catch:{ IOException -> 0x004d }
            android.util.DisplayMetrics r0 = r0.getDisplayMetrics()     // Catch:{ IOException -> 0x004d }
            if (r10 == 0) goto L_0x003d
            android.content.res.Resources r10 = r8.getResources()     // Catch:{ IOException -> 0x004d }
            android.content.res.Configuration r10 = r10.getConfiguration()     // Catch:{ IOException -> 0x004d }
            android.content.res.Resources r3 = new android.content.res.Resources     // Catch:{ IOException -> 0x004d }
            android.content.res.AssetManager r8 = r8.getAssets()     // Catch:{ IOException -> 0x004d }
            r3.<init>(r8, r0, r10)     // Catch:{ IOException -> 0x004d }
            android.graphics.drawable.NinePatchDrawable r8 = new android.graphics.drawable.NinePatchDrawable     // Catch:{ IOException -> 0x004d }
            byte[] r5 = r4.getNinePatchChunk()     // Catch:{ IOException -> 0x004d }
            android.graphics.Rect r6 = new android.graphics.Rect     // Catch:{ IOException -> 0x004d }
            r10 = 0
            r6.<init>(r10, r10, r10, r10)     // Catch:{ IOException -> 0x004d }
            r7 = 0
            r2 = r8
            r2.<init>(r3, r4, r5, r6, r7)     // Catch:{ IOException -> 0x004d }
            r1 = r8
            goto L_0x004f
        L_0x003d:
            int r10 = r0.densityDpi     // Catch:{ IOException -> 0x004d }
            r4.setDensity(r10)     // Catch:{ IOException -> 0x004d }
            android.graphics.drawable.BitmapDrawable r10 = new android.graphics.drawable.BitmapDrawable     // Catch:{ IOException -> 0x004d }
            android.content.res.Resources r8 = r8.getResources()     // Catch:{ IOException -> 0x004d }
            r10.<init>(r8, r4)     // Catch:{ IOException -> 0x004d }
            r1 = r10
            goto L_0x004f
        L_0x004d:
            r8 = move-exception
            goto L_0x005f
        L_0x004f:
            if (r9 == 0) goto L_0x0067
            r9.close()     // Catch:{ IOException -> 0x0055 }
            goto L_0x0067
        L_0x0055:
            r8 = move-exception
            r8.printStackTrace()
            goto L_0x0067
        L_0x005a:
            r8 = move-exception
            r9 = r1
            goto L_0x0069
        L_0x005d:
            r8 = move-exception
            r9 = r1
        L_0x005f:
            r8.printStackTrace()     // Catch:{ all -> 0x0068 }
            if (r9 == 0) goto L_0x0067
            r9.close()
        L_0x0067:
            return r1
        L_0x0068:
            r8 = move-exception
        L_0x0069:
            if (r9 == 0) goto L_0x0073
            r9.close()     // Catch:{ IOException -> 0x006f }
            goto L_0x0073
        L_0x006f:
            r9 = move-exception
            r9.printStackTrace()
        L_0x0073:
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.sina.weibo.sdk.a.g.a(android.content.Context, java.lang.String, boolean):android.graphics.drawable.Drawable");
    }

    private static boolean e(Context context, String str) {
        if (context == null || TextUtils.isEmpty(str)) {
            return false;
        }
        InputStream inputStream = null;
        try {
            inputStream = context.getAssets().open(str);
            String str2 = a;
            d.a(str2, "file [" + str + "] existed");
            if (inputStream == null) {
                return true;
            }
            try {
                inputStream.close();
                return true;
            } catch (IOException e) {
                e.printStackTrace();
                return true;
            }
        } catch (IOException unused) {
            String str3 = a;
            d.a(str3, "file [" + str + "] NOT existed");
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e2) {
                    e2.printStackTrace();
                }
            }
            return false;
        } catch (Throwable th) {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e3) {
                    e3.printStackTrace();
                }
            }
            throw th;
        }
    }

    private static String a(Context context) {
        int i = context.getResources().getDisplayMetrics().densityDpi;
        if (i <= 120) {
            return "drawable-ldpi";
        }
        if (i > 120 && i <= 160) {
            return "drawable-mdpi";
        }
        if (i <= 160 || i > 240) {
            return (i <= 240 || i > 320) ? "drawable-xxhdpi" : "drawable-xhdpi";
        }
        return "drawable-hdpi";
    }

    public static int a(Context context, int i) {
        return (int) (((double) (((float) i) * context.getResources().getDisplayMetrics().density)) + 0.5d);
    }

    public static ColorStateList a(int i, int i2) {
        return new ColorStateList(new int[][]{new int[]{16842919}, new int[]{16842913}, new int[]{16842908}, StateSet.WILD_CARD}, new int[]{i2, i2, i2, i});
    }

    public static StateListDrawable a(Context context, String str, String str2) {
        Drawable drawable;
        Drawable drawable2;
        if (str.indexOf(".9") > -1) {
            drawable = b(context, str);
        } else {
            drawable = a(context, str);
        }
        if (str2.indexOf(".9") > -1) {
            drawable2 = b(context, str2);
        } else {
            drawable2 = a(context, str2);
        }
        StateListDrawable stateListDrawable = new StateListDrawable();
        stateListDrawable.addState(new int[]{16842919}, drawable2);
        stateListDrawable.addState(new int[]{16842913}, drawable2);
        stateListDrawable.addState(new int[]{16842908}, drawable2);
        stateListDrawable.addState(StateSet.WILD_CARD, drawable);
        return stateListDrawable;
    }

    public static StateListDrawable b(Context context, String str, String str2, String str3) {
        Drawable drawable;
        Drawable drawable2;
        Drawable drawable3;
        if (str.indexOf(".9") > -1) {
            drawable = b(context, str);
        } else {
            drawable = a(context, str);
        }
        if (str3.indexOf(".9") > -1) {
            drawable2 = b(context, str3);
        } else {
            drawable2 = a(context, str3);
        }
        if (str2.indexOf(".9") > -1) {
            drawable3 = b(context, str2);
        } else {
            drawable3 = a(context, str2);
        }
        StateListDrawable stateListDrawable = new StateListDrawable();
        stateListDrawable.addState(new int[]{16842919}, drawable3);
        stateListDrawable.addState(new int[]{16842913}, drawable3);
        stateListDrawable.addState(new int[]{16842908}, drawable3);
        stateListDrawable.addState(new int[]{16842766}, drawable2);
        stateListDrawable.addState(StateSet.WILD_CARD, drawable);
        return stateListDrawable;
    }

    public static String c(Context context, String str) {
        try {
            InputStream open = context.getAssets().open(str);
            if (open == null) {
                return "";
            }
            DataInputStream dataInputStream = new DataInputStream(open);
            byte[] bArr = new byte[dataInputStream.available()];
            dataInputStream.read(bArr);
            String string = EncodingUtils.getString(bArr, "UTF-8");
            open.close();
            return string;
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }
}
