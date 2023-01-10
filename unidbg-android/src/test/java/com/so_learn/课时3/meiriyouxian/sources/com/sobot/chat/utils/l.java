package com.sobot.chat.utils;

import android.app.backup.FullBackup;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.ParcelFileDescriptor;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import com.huawei.hms.framework.common.ContainerUtils;
import java.io.File;
import java.io.IOException;

/* compiled from: ImageUtils */
public class l {
    public static int a(String str) {
        int i;
        try {
            int attributeInt = new ExifInterface(str).getAttributeInt("Orientation", 1);
            if (attributeInt == 3) {
                i = 180;
            } else if (attributeInt == 6) {
                i = 90;
            } else if (attributeInt != 8) {
                return 0;
            } else {
                i = 270;
            }
            return i;
        } catch (IOException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public static Bitmap a(Bitmap bitmap, int i) {
        if (bitmap == null) {
            return null;
        }
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        Matrix matrix = new Matrix();
        if (i != 0) {
            matrix.postRotate((float) i, ((float) bitmap.getWidth()) / 2.0f, ((float) bitmap.getHeight()) / 2.0f);
        }
        return Bitmap.createBitmap(bitmap, 0, 0, width, height, matrix, true);
    }

    /* JADX WARNING: Removed duplicated region for block: B:104:0x01ac A[SYNTHETIC, Splitter:B:104:0x01ac] */
    /* JADX WARNING: Removed duplicated region for block: B:109:0x01b6 A[SYNTHETIC, Splitter:B:109:0x01b6] */
    /* JADX WARNING: Removed duplicated region for block: B:116:0x01c3 A[SYNTHETIC, Splitter:B:116:0x01c3] */
    /* JADX WARNING: Removed duplicated region for block: B:121:0x01cd A[SYNTHETIC, Splitter:B:121:0x01cd] */
    /* JADX WARNING: Removed duplicated region for block: B:126:0x01d7 A[SYNTHETIC, Splitter:B:126:0x01d7] */
    /* JADX WARNING: Removed duplicated region for block: B:133:0x01e6 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:134:0x01e7  */
    /* JADX WARNING: Removed duplicated region for block: B:99:0x01a2 A[SYNTHETIC, Splitter:B:99:0x01a2] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String a(android.content.Context r9, android.net.Uri r10) {
        /*
        // Method dump skipped, instructions count: 572
        */
        throw new UnsupportedOperationException("Method not decompiled: com.sobot.chat.utils.l.a(android.content.Context, android.net.Uri):java.lang.String");
    }

    /* JADX WARNING: Removed duplicated region for block: B:18:0x0036  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String a(android.content.Context r8, android.net.Uri r9, java.lang.String r10, java.lang.String[] r11) {
        /*
            java.lang.String r0 = "_data"
            java.lang.String[] r3 = new java.lang.String[]{r0}
            r7 = 0
            android.content.ContentResolver r1 = r8.getContentResolver()     // Catch:{ all -> 0x0032 }
            r6 = 0
            r2 = r9
            r4 = r10
            r5 = r11
            android.database.Cursor r8 = r1.query(r2, r3, r4, r5, r6)     // Catch:{ all -> 0x0032 }
            if (r8 == 0) goto L_0x002c
            boolean r9 = r8.moveToFirst()     // Catch:{ all -> 0x002a }
            if (r9 == 0) goto L_0x002c
            int r9 = r8.getColumnIndexOrThrow(r0)     // Catch:{ all -> 0x002a }
            java.lang.String r9 = r8.getString(r9)     // Catch:{ all -> 0x002a }
            if (r8 == 0) goto L_0x0029
            r8.close()
        L_0x0029:
            return r9
        L_0x002a:
            r9 = move-exception
            goto L_0x0034
        L_0x002c:
            if (r8 == 0) goto L_0x0031
            r8.close()
        L_0x0031:
            return r7
        L_0x0032:
            r9 = move-exception
            r8 = r7
        L_0x0034:
            if (r8 == 0) goto L_0x0039
            r8.close()
        L_0x0039:
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.sobot.chat.utils.l.a(android.content.Context, android.net.Uri, java.lang.String, java.lang.String[]):java.lang.String");
    }

    public static boolean a(Uri uri) {
        return DocumentsContract.EXTERNAL_STORAGE_PROVIDER_AUTHORITY.equals(uri.getAuthority());
    }

    public static boolean b(Uri uri) {
        return "com.android.providers.downloads.documents".equals(uri.getAuthority());
    }

    public static boolean c(Uri uri) {
        return "com.android.providers.media.documents".equals(uri.getAuthority());
    }

    public static boolean d(Uri uri) {
        return "com.google.android.apps.photos.content".equals(uri.getAuthority());
    }

    public static boolean e(Uri uri) {
        return "com.google.android.apps.photos.contentprovider".equals(uri.getAuthority());
    }

    /* JADX WARNING: Removed duplicated region for block: B:22:0x0034 A[SYNTHETIC, Splitter:B:22:0x0034] */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x0040 A[SYNTHETIC, Splitter:B:28:0x0040] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static android.net.Uri b(android.content.Context r2, android.net.Uri r3) {
        /*
            java.lang.String r0 = r3.getAuthority()
            r1 = 0
            if (r0 == 0) goto L_0x0049
            android.content.ContentResolver r0 = r2.getContentResolver()
            if (r0 == 0) goto L_0x0049
            android.content.ContentResolver r0 = r2.getContentResolver()     // Catch:{ FileNotFoundException -> 0x002d, all -> 0x002a }
            java.io.InputStream r3 = r0.openInputStream(r3)     // Catch:{ FileNotFoundException -> 0x002d, all -> 0x002a }
            android.graphics.Bitmap r0 = android.graphics.BitmapFactory.decodeStream(r3)     // Catch:{ FileNotFoundException -> 0x0028 }
            android.net.Uri r2 = a(r2, r0)     // Catch:{ FileNotFoundException -> 0x0028 }
            if (r3 == 0) goto L_0x0027
            r3.close()     // Catch:{ IOException -> 0x0023 }
            goto L_0x0027
        L_0x0023:
            r3 = move-exception
            r3.printStackTrace()
        L_0x0027:
            return r2
        L_0x0028:
            r2 = move-exception
            goto L_0x002f
        L_0x002a:
            r2 = move-exception
            r3 = r1
            goto L_0x003e
        L_0x002d:
            r2 = move-exception
            r3 = r1
        L_0x002f:
            r2.printStackTrace()     // Catch:{ all -> 0x003d }
            if (r3 == 0) goto L_0x0049
            r3.close()     // Catch:{ IOException -> 0x0038 }
            goto L_0x0049
        L_0x0038:
            r2 = move-exception
            r2.printStackTrace()
            goto L_0x0049
        L_0x003d:
            r2 = move-exception
        L_0x003e:
            if (r3 == 0) goto L_0x0048
            r3.close()     // Catch:{ IOException -> 0x0044 }
            goto L_0x0048
        L_0x0044:
            r3 = move-exception
            r3.printStackTrace()
        L_0x0048:
            throw r2
        L_0x0049:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.sobot.chat.utils.l.b(android.content.Context, android.net.Uri):android.net.Uri");
    }

    /* JADX WARNING: Removed duplicated region for block: B:24:0x0056  */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x0062 A[SYNTHETIC, Splitter:B:29:0x0062] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static android.net.Uri a(android.content.Context r4, android.graphics.Bitmap r5) {
        /*
            android.content.ContentResolver r0 = r4.getContentResolver()
            r1 = 0
            if (r0 == 0) goto L_0x006b
            if (r5 == 0) goto L_0x006b
            java.io.ByteArrayOutputStream r0 = new java.io.ByteArrayOutputStream     // Catch:{ Exception -> 0x004f, all -> 0x004c }
            r0.<init>()     // Catch:{ Exception -> 0x004f, all -> 0x004c }
            android.graphics.Bitmap$CompressFormat r2 = android.graphics.Bitmap.CompressFormat.JPEG     // Catch:{ Exception -> 0x004a }
            r3 = 100
            r5.compress(r2, r3, r0)     // Catch:{ Exception -> 0x004a }
            android.content.ContentResolver r4 = r4.getContentResolver()     // Catch:{ Exception -> 0x004a }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x004a }
            r2.<init>()     // Catch:{ Exception -> 0x004a }
            java.lang.String r3 = "IMG"
            r2.append(r3)     // Catch:{ Exception -> 0x004a }
            java.util.Calendar r3 = java.util.Calendar.getInstance()     // Catch:{ Exception -> 0x004a }
            java.util.Date r3 = r3.getTime()     // Catch:{ Exception -> 0x004a }
            r2.append(r3)     // Catch:{ Exception -> 0x004a }
            java.lang.String r2 = r2.toString()     // Catch:{ Exception -> 0x004a }
            java.lang.String r4 = android.provider.MediaStore.Images.Media.insertImage(r4, r5, r2, r1)     // Catch:{ Exception -> 0x004a }
            if (r4 == 0) goto L_0x0046
            android.net.Uri r4 = android.net.Uri.parse(r4)     // Catch:{ Exception -> 0x004a }
            r0.close()     // Catch:{ IOException -> 0x0041 }
            goto L_0x0045
        L_0x0041:
            r5 = move-exception
            r5.printStackTrace()
        L_0x0045:
            return r4
        L_0x0046:
            r0.close()     // Catch:{ IOException -> 0x005a }
            goto L_0x006b
        L_0x004a:
            r4 = move-exception
            goto L_0x0051
        L_0x004c:
            r4 = move-exception
            r0 = r1
            goto L_0x0060
        L_0x004f:
            r4 = move-exception
            r0 = r1
        L_0x0051:
            r4.printStackTrace()     // Catch:{ all -> 0x005f }
            if (r0 == 0) goto L_0x006b
            r0.close()
            goto L_0x006b
        L_0x005a:
            r4 = move-exception
            r4.printStackTrace()
            goto L_0x006b
        L_0x005f:
            r4 = move-exception
        L_0x0060:
            if (r0 == 0) goto L_0x006a
            r0.close()     // Catch:{ IOException -> 0x0066 }
            goto L_0x006a
        L_0x0066:
            r5 = move-exception
            r5.printStackTrace()
        L_0x006a:
            throw r4
        L_0x006b:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.sobot.chat.utils.l.a(android.content.Context, android.graphics.Bitmap):android.net.Uri");
    }

    public static Bitmap c(Context context, Uri uri) {
        try {
            ParcelFileDescriptor openFileDescriptor = context.getContentResolver().openFileDescriptor(uri, FullBackup.ROOT_TREE_TOKEN);
            Bitmap decodeFileDescriptor = BitmapFactory.decodeFileDescriptor(openFileDescriptor.getFileDescriptor());
            openFileDescriptor.close();
            return decodeFileDescriptor;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Uri a(Context context, String str) {
        Cursor query = context.getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, new String[]{"_id"}, "_data=? ", new String[]{str}, null);
        if (query != null && query.moveToFirst()) {
            int i = query.getInt(query.getColumnIndex("_id"));
            Uri parse = Uri.parse("content://media/external/images/media");
            return Uri.withAppendedPath(parse, "" + i);
        } else if (!new File(str).exists()) {
            return null;
        } else {
            ContentValues contentValues = new ContentValues();
            contentValues.put("_data", str);
            return context.getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues);
        }
    }

    public static Uri a(Intent intent, Context context) {
        String encodedPath;
        Uri data = intent.getData();
        String type = intent.getType();
        if (data.getScheme().equals(ContentResolver.SCHEME_FILE) && type.contains("image/*") && (encodedPath = data.getEncodedPath()) != null) {
            String decode = Uri.decode(encodedPath);
            ContentResolver contentResolver = context.getContentResolver();
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append(com.umeng.message.proguard.l.s);
            stringBuffer.append("_data");
            stringBuffer.append(ContainerUtils.KEY_VALUE_DELIMITER);
            stringBuffer.append("'" + decode + "'");
            stringBuffer.append(com.umeng.message.proguard.l.t);
            Cursor query = contentResolver.query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, new String[]{"_id"}, stringBuffer.toString(), null, null);
            int i = 0;
            query.moveToFirst();
            while (!query.isAfterLast()) {
                i = query.getInt(query.getColumnIndex("_id"));
                query.moveToNext();
            }
            if (i != 0) {
                Uri parse = Uri.parse("content://media/external/images/media/" + i);
                if (parse != null) {
                    return parse;
                }
            }
        }
        return data;
    }

    /* JADX WARNING: Removed duplicated region for block: B:35:0x00a4 A[SYNTHETIC, Splitter:B:35:0x00a4] */
    /* JADX WARNING: Removed duplicated region for block: B:40:0x00ae A[SYNTHETIC, Splitter:B:40:0x00ae] */
    /* JADX WARNING: Removed duplicated region for block: B:47:0x00bb A[SYNTHETIC, Splitter:B:47:0x00bb] */
    /* JADX WARNING: Removed duplicated region for block: B:52:0x00c5 A[SYNTHETIC, Splitter:B:52:0x00c5] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static java.lang.String d(android.content.Context r8, android.net.Uri r9) {
        /*
            java.lang.String r0 = r9.getScheme()
            java.lang.String r1 = "file"
            boolean r0 = r0.equals(r1)
            r1 = 0
            if (r0 == 0) goto L_0x0019
            java.io.File r1 = new java.io.File
            java.lang.String r8 = r9.getPath()
            r1.<init>(r8)
            goto L_0x00ce
        L_0x0019:
            java.lang.String r0 = r9.getScheme()
            java.lang.String r2 = "content"
            boolean r0 = r0.equals(r2)
            if (r0 == 0) goto L_0x00ce
            android.content.ContentResolver r0 = r8.getContentResolver()
            r4 = 0
            r5 = 0
            r6 = 0
            r7 = 0
            r2 = r0
            r3 = r9
            android.database.Cursor r2 = r2.query(r3, r4, r5, r6, r7)
            boolean r3 = r2.moveToFirst()
            if (r3 == 0) goto L_0x00ce
            java.lang.String r3 = "_display_name"
            int r3 = r2.getColumnIndex(r3)
            java.lang.String r2 = r2.getString(r3)
            java.io.InputStream r9 = r0.openInputStream(r9)     // Catch:{ IOException -> 0x009c, all -> 0x0099 }
            java.io.File r0 = new java.io.File     // Catch:{ IOException -> 0x0096, all -> 0x0094 }
            java.io.File r8 = r8.getExternalCacheDir()     // Catch:{ IOException -> 0x0096, all -> 0x0094 }
            java.lang.String r8 = r8.getAbsolutePath()     // Catch:{ IOException -> 0x0096, all -> 0x0094 }
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x0096, all -> 0x0094 }
            r3.<init>()     // Catch:{ IOException -> 0x0096, all -> 0x0094 }
            double r4 = java.lang.Math.random()     // Catch:{ IOException -> 0x0096, all -> 0x0094 }
            r6 = 4607182418800017408(0x3ff0000000000000, double:1.0)
            double r4 = r4 + r6
            r6 = 4652007308841189376(0x408f400000000000, double:1000.0)
            double r4 = r4 * r6
            long r4 = java.lang.Math.round(r4)     // Catch:{ IOException -> 0x0096, all -> 0x0094 }
            r3.append(r4)     // Catch:{ IOException -> 0x0096, all -> 0x0094 }
            r3.append(r2)     // Catch:{ IOException -> 0x0096, all -> 0x0094 }
            java.lang.String r2 = r3.toString()     // Catch:{ IOException -> 0x0096, all -> 0x0094 }
            r0.<init>(r8, r2)     // Catch:{ IOException -> 0x0096, all -> 0x0094 }
            java.io.FileOutputStream r8 = new java.io.FileOutputStream     // Catch:{ IOException -> 0x0096, all -> 0x0094 }
            r8.<init>(r0)     // Catch:{ IOException -> 0x0096, all -> 0x0094 }
            com.sobot.chat.utils.k.a(r8, r9)     // Catch:{ IOException -> 0x0092 }
            r8.close()     // Catch:{ IOException -> 0x0082 }
            goto L_0x0086
        L_0x0082:
            r8 = move-exception
            r8.printStackTrace()
        L_0x0086:
            if (r9 == 0) goto L_0x0090
            r9.close()     // Catch:{ IOException -> 0x008c }
            goto L_0x0090
        L_0x008c:
            r8 = move-exception
            r8.printStackTrace()
        L_0x0090:
            r1 = r0
            goto L_0x00ce
        L_0x0092:
            r0 = move-exception
            goto L_0x009f
        L_0x0094:
            r0 = move-exception
            goto L_0x00b9
        L_0x0096:
            r0 = move-exception
            r8 = r1
            goto L_0x009f
        L_0x0099:
            r0 = move-exception
            r9 = r1
            goto L_0x00b9
        L_0x009c:
            r0 = move-exception
            r8 = r1
            r9 = r8
        L_0x009f:
            r0.printStackTrace()     // Catch:{ all -> 0x00b7 }
            if (r8 == 0) goto L_0x00ac
            r8.close()     // Catch:{ IOException -> 0x00a8 }
            goto L_0x00ac
        L_0x00a8:
            r8 = move-exception
            r8.printStackTrace()
        L_0x00ac:
            if (r9 == 0) goto L_0x00ce
            r9.close()     // Catch:{ IOException -> 0x00b2 }
            goto L_0x00ce
        L_0x00b2:
            r8 = move-exception
            r8.printStackTrace()
            goto L_0x00ce
        L_0x00b7:
            r0 = move-exception
            r1 = r8
        L_0x00b9:
            if (r1 == 0) goto L_0x00c3
            r1.close()     // Catch:{ IOException -> 0x00bf }
            goto L_0x00c3
        L_0x00bf:
            r8 = move-exception
            r8.printStackTrace()
        L_0x00c3:
            if (r9 == 0) goto L_0x00cd
            r9.close()     // Catch:{ IOException -> 0x00c9 }
            goto L_0x00cd
        L_0x00c9:
            r8 = move-exception
            r8.printStackTrace()
        L_0x00cd:
            throw r0
        L_0x00ce:
            java.lang.String r8 = r1.getAbsolutePath()
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.sobot.chat.utils.l.d(android.content.Context, android.net.Uri):java.lang.String");
    }
}
