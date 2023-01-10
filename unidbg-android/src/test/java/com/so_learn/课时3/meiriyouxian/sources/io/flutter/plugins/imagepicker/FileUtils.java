package io.flutter.plugins.imagepicker;

import android.net.Uri;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

class FileUtils {
    FileUtils() {
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0036, code lost:
        r2 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x0038, code lost:
        r5 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:?, code lost:
        r6.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:?, code lost:
        r1.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:43:?, code lost:
        r6.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:46:0x0054, code lost:
        r2.close();
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x0038 A[ExcHandler: all (th java.lang.Throwable), Splitter:B:4:0x0011] */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x0041 A[SYNTHETIC, Splitter:B:30:0x0041] */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x0046 A[SYNTHETIC, Splitter:B:34:0x0046] */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x004f A[SYNTHETIC, Splitter:B:42:0x004f] */
    /* JADX WARNING: Removed duplicated region for block: B:46:0x0054  */
    /* JADX WARNING: Removed duplicated region for block: B:49:0x0059  */
    /* JADX WARNING: Removed duplicated region for block: B:51:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.String getPathFromUri(android.content.Context r5, android.net.Uri r6) {
        /*
            r4 = this;
            r0 = 0
            r1 = 0
            java.lang.String r2 = getImageExtension(r6)     // Catch:{ IOException -> 0x004a, all -> 0x003d }
            android.content.ContentResolver r3 = r5.getContentResolver()     // Catch:{ IOException -> 0x004a, all -> 0x003d }
            java.io.InputStream r6 = r3.openInputStream(r6)     // Catch:{ IOException -> 0x004a, all -> 0x003d }
            java.lang.String r3 = "image_picker"
            java.io.File r5 = r5.getCacheDir()     // Catch:{ IOException -> 0x003a, all -> 0x0038 }
            java.io.File r5 = java.io.File.createTempFile(r3, r2, r5)     // Catch:{ IOException -> 0x003a, all -> 0x0038 }
            r5.deleteOnExit()     // Catch:{ IOException -> 0x0036, all -> 0x0038 }
            java.io.FileOutputStream r2 = new java.io.FileOutputStream     // Catch:{ IOException -> 0x0036, all -> 0x0038 }
            r2.<init>(r5)     // Catch:{ IOException -> 0x0036, all -> 0x0038 }
            if (r6 == 0) goto L_0x002b
            copy(r6, r2)     // Catch:{ IOException -> 0x004d, all -> 0x0028 }
            r3 = 1
            goto L_0x002c
        L_0x0028:
            r5 = move-exception
            r1 = r2
            goto L_0x003f
        L_0x002b:
            r3 = r0
        L_0x002c:
            if (r6 == 0) goto L_0x0031
            r6.close()     // Catch:{ IOException -> 0x0031 }
        L_0x0031:
            r2.close()     // Catch:{ IOException -> 0x0057 }
            r0 = r3
            goto L_0x0057
        L_0x0036:
            r2 = r1
            goto L_0x004d
        L_0x0038:
            r5 = move-exception
            goto L_0x003f
        L_0x003a:
            r5 = r1
            r2 = r5
            goto L_0x004d
        L_0x003d:
            r5 = move-exception
            r6 = r1
        L_0x003f:
            if (r6 == 0) goto L_0x0044
            r6.close()     // Catch:{ IOException -> 0x0044 }
        L_0x0044:
            if (r1 == 0) goto L_0x0049
            r1.close()     // Catch:{ IOException -> 0x0049 }
        L_0x0049:
            throw r5
        L_0x004a:
            r5 = r1
            r6 = r5
            r2 = r6
        L_0x004d:
            if (r6 == 0) goto L_0x0052
            r6.close()     // Catch:{ IOException -> 0x0052 }
        L_0x0052:
            if (r2 == 0) goto L_0x0057
            r2.close()
        L_0x0057:
            if (r0 == 0) goto L_0x005d
            java.lang.String r1 = r5.getPath()
        L_0x005d:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: io.flutter.plugins.imagepicker.FileUtils.getPathFromUri(android.content.Context, android.net.Uri):java.lang.String");
    }

    private static String getImageExtension(Uri uri) {
        String str = null;
        try {
            String path = uri.getPath();
            if (!(path == null || path.lastIndexOf(".") == -1)) {
                str = path.substring(path.lastIndexOf(".") + 1);
            }
        } catch (Exception unused) {
        }
        if (str == null || str.isEmpty()) {
            str = "jpg";
        }
        return "." + str;
    }

    private static void copy(InputStream inputStream, OutputStream outputStream) throws IOException {
        byte[] bArr = new byte[4096];
        while (true) {
            int read = inputStream.read(bArr);
            if (read != -1) {
                outputStream.write(bArr, 0, read);
            } else {
                outputStream.flush();
                return;
            }
        }
    }
}
