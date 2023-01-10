package com.umeng.commonsdk.stateless;

import android.content.Context;
import android.security.keystore.KeyProperties;
import android.telecom.Logging.Session;
import android.text.TextUtils;
import android.util.Base64;
import com.umeng.commonsdk.internal.crash.UMCrashManager;
import com.umeng.commonsdk.statistics.common.ULog;
import java.io.File;
import java.io.FilenameFilter;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Locale;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/* compiled from: UMSLUtils */
public class d {
    public static int a;
    private static final byte[] b = {10, 1, 11, 5, 4, 15, 7, 9, 23, 3, 1, 6, 8, 12, 13, 91};
    private static Object c = new Object();

    public static boolean a(long j, long j2) {
        return j > j2;
    }

    public static boolean a(File file) {
        String[] list;
        if (file == null) {
            return false;
        }
        if (file.isDirectory()) {
            for (String str : file.list()) {
                if (!a(new File(file, str))) {
                    return false;
                }
            }
        }
        return file.delete();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0063, code lost:
        if (0 == 0) goto L_0x0077;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:?, code lost:
        r1.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x006d, code lost:
        if (0 == 0) goto L_0x0077;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static int a(android.content.Context r6, java.lang.String r7, java.lang.String r8, byte[] r9) {
        /*
            r0 = 101(0x65, float:1.42E-43)
            if (r6 == 0) goto L_0x0077
            r1 = 0
            java.lang.Object r2 = com.umeng.commonsdk.stateless.d.c     // Catch:{ IOException -> 0x0069, all -> 0x005f }
            monitor-enter(r2)     // Catch:{ IOException -> 0x0069, all -> 0x005f }
            java.io.File r3 = new java.io.File     // Catch:{ all -> 0x005c }
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ all -> 0x005c }
            r4.<init>()     // Catch:{ all -> 0x005c }
            java.io.File r5 = r6.getFilesDir()     // Catch:{ all -> 0x005c }
            r4.append(r5)     // Catch:{ all -> 0x005c }
            java.lang.String r5 = java.io.File.separator     // Catch:{ all -> 0x005c }
            r4.append(r5)     // Catch:{ all -> 0x005c }
            r4.append(r7)     // Catch:{ all -> 0x005c }
            java.lang.String r7 = r4.toString()     // Catch:{ all -> 0x005c }
            r3.<init>(r7)     // Catch:{ all -> 0x005c }
            boolean r7 = r3.isDirectory()     // Catch:{ all -> 0x005c }
            if (r7 != 0) goto L_0x002e
            r3.mkdir()     // Catch:{ all -> 0x005c }
        L_0x002e:
            java.io.File r7 = new java.io.File     // Catch:{ all -> 0x005c }
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ all -> 0x005c }
            r4.<init>()     // Catch:{ all -> 0x005c }
            java.lang.String r3 = r3.getPath()     // Catch:{ all -> 0x005c }
            r4.append(r3)     // Catch:{ all -> 0x005c }
            java.lang.String r3 = java.io.File.separator     // Catch:{ all -> 0x005c }
            r4.append(r3)     // Catch:{ all -> 0x005c }
            r4.append(r8)     // Catch:{ all -> 0x005c }
            java.lang.String r8 = r4.toString()     // Catch:{ all -> 0x005c }
            r7.<init>(r8)     // Catch:{ all -> 0x005c }
            java.io.FileOutputStream r8 = new java.io.FileOutputStream     // Catch:{ all -> 0x005c }
            r8.<init>(r7)     // Catch:{ all -> 0x005c }
            r8.write(r9)     // Catch:{ all -> 0x0059 }
            r8.close()     // Catch:{ all -> 0x0059 }
            r0 = 0
            monitor-exit(r2)
            goto L_0x0077
        L_0x0059:
            r7 = move-exception
            r1 = r8
            goto L_0x005d
        L_0x005c:
            r7 = move-exception
        L_0x005d:
            monitor-exit(r2)
            throw r7
        L_0x005f:
            r7 = move-exception
            com.umeng.commonsdk.internal.crash.UMCrashManager.reportCrash(r6, r7)     // Catch:{ all -> 0x0070 }
            if (r1 == 0) goto L_0x0077
        L_0x0065:
            r1.close()     // Catch:{ all -> 0x0077 }
            goto L_0x0077
        L_0x0069:
            r7 = move-exception
            com.umeng.commonsdk.internal.crash.UMCrashManager.reportCrash(r6, r7)
            if (r1 == 0) goto L_0x0077
            goto L_0x0065
        L_0x0070:
            r6 = move-exception
            if (r1 == 0) goto L_0x0076
            r1.close()     // Catch:{ all -> 0x0076 }
        L_0x0076:
            throw r6
        L_0x0077:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.umeng.commonsdk.stateless.d.a(android.content.Context, java.lang.String, java.lang.String, byte[]):int");
    }

    /* JADX WARNING: Removed duplicated region for block: B:61:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean b(android.content.Context r8, java.lang.String r9, java.lang.String r10, byte[] r11) {
        /*
        // Method dump skipped, instructions count: 283
        */
        throw new UnsupportedOperationException("Method not decompiled: com.umeng.commonsdk.stateless.d.b(android.content.Context, java.lang.String, java.lang.String, byte[]):boolean");
    }

    /* JADX WARNING: Removed duplicated region for block: B:27:0x006b A[SYNTHETIC, Splitter:B:27:0x006b] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static byte[] a(java.lang.String r12) throws java.io.IOException {
        /*
            java.lang.Object r0 = com.umeng.commonsdk.stateless.d.c
            monitor-enter(r0)
            r1 = 0
            r2 = 0
            java.io.RandomAccessFile r3 = new java.io.RandomAccessFile     // Catch:{ IOException -> 0x0042, all -> 0x003f }
            java.lang.String r4 = "r"
            r3.<init>(r12, r4)     // Catch:{ IOException -> 0x0042, all -> 0x003f }
            java.nio.channels.FileChannel r12 = r3.getChannel()     // Catch:{ IOException -> 0x0042, all -> 0x003f }
            java.nio.channels.FileChannel$MapMode r6 = java.nio.channels.FileChannel.MapMode.READ_ONLY     // Catch:{ IOException -> 0x003d }
            r7 = 0
            long r9 = r12.size()     // Catch:{ IOException -> 0x003d }
            r5 = r12
            java.nio.MappedByteBuffer r2 = r5.map(r6, r7, r9)     // Catch:{ IOException -> 0x003d }
            java.nio.MappedByteBuffer r2 = r2.load()     // Catch:{ IOException -> 0x003d }
            long r3 = r12.size()     // Catch:{ IOException -> 0x003d }
            int r3 = (int) r3     // Catch:{ IOException -> 0x003d }
            byte[] r3 = new byte[r3]     // Catch:{ IOException -> 0x003d }
            int r4 = r2.remaining()     // Catch:{ IOException -> 0x003d }
            if (r4 <= 0) goto L_0x0036
            int r4 = r2.remaining()     // Catch:{ IOException -> 0x003d }
            r2.get(r3, r1, r4)     // Catch:{ IOException -> 0x003d }
        L_0x0036:
            if (r12 == 0) goto L_0x003b
            r12.close()     // Catch:{ all -> 0x003b }
        L_0x003b:
            monitor-exit(r0)     // Catch:{ all -> 0x006f }
            return r3
        L_0x003d:
            r2 = move-exception
            goto L_0x0046
        L_0x003f:
            r1 = move-exception
            r12 = r2
            goto L_0x0069
        L_0x0042:
            r12 = move-exception
            r11 = r2
            r2 = r12
            r12 = r11
        L_0x0046:
            java.lang.String r3 = "walle"
            r4 = 1
            java.lang.Object[] r4 = new java.lang.Object[r4]     // Catch:{ all -> 0x0068 }
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ all -> 0x0068 }
            r5.<init>()     // Catch:{ all -> 0x0068 }
            java.lang.String r6 = "[stateless] write envelope, e is "
            r5.append(r6)     // Catch:{ all -> 0x0068 }
            java.lang.String r6 = r2.getMessage()     // Catch:{ all -> 0x0068 }
            r5.append(r6)     // Catch:{ all -> 0x0068 }
            java.lang.String r5 = r5.toString()     // Catch:{ all -> 0x0068 }
            r4[r1] = r5     // Catch:{ all -> 0x0068 }
            com.umeng.commonsdk.statistics.common.ULog.i(r3, r4)     // Catch:{ all -> 0x0068 }
            throw r2     // Catch:{ all -> 0x0068 }
        L_0x0068:
            r1 = move-exception
        L_0x0069:
            if (r12 == 0) goto L_0x006e
            r12.close()     // Catch:{ all -> 0x006e }
        L_0x006e:
            throw r1
        L_0x006f:
            r12 = move-exception
            monitor-exit(r0)
            throw r12
        */
        throw new UnsupportedOperationException("Method not decompiled: com.umeng.commonsdk.stateless.d.a(java.lang.String):byte[]");
    }

    public static File a(Context context) {
        Throwable th;
        File[] listFiles;
        File[] listFiles2;
        File file = null;
        try {
            synchronized (c) {
                try {
                    ULog.i("walle", "get last envelope begin, thread is " + Thread.currentThread());
                    if (!(context == null || context.getApplicationContext() == null)) {
                        String str = context.getApplicationContext().getFilesDir() + File.separator + a.e;
                        if (!TextUtils.isEmpty(str)) {
                            File file2 = new File(str);
                            if (file2.isDirectory() && (listFiles = file2.listFiles()) != null && listFiles.length > 0) {
                                File file3 = null;
                                for (File file4 : listFiles) {
                                    try {
                                        if (file4 != null && file4.isDirectory() && (listFiles2 = file4.listFiles()) != null && listFiles2.length > 0) {
                                            Arrays.sort(listFiles2, new AnonymousClass1());
                                            File file5 = listFiles2[0];
                                            if (file5 != null && (file3 == null || file3.lastModified() > file5.lastModified())) {
                                                file3 = file5;
                                            }
                                        }
                                    } catch (Throwable th2) {
                                        th = th2;
                                        throw th;
                                    }
                                }
                                file = file3;
                            }
                        }
                    }
                    ULog.i("walle", "get last envelope end, thread is " + Thread.currentThread());
                } catch (Throwable th3) {
                    th = th3;
                    throw th;
                }
            }
        } catch (Throwable th4) {
            UMCrashManager.reportCrash(context, th4);
        }
        return file;
    }

    /* compiled from: UMSLUtils */
    /* renamed from: com.umeng.commonsdk.stateless.d$1  reason: invalid class name */
    static class AnonymousClass1 implements Comparator<File> {
        AnonymousClass1() {
        }

        /* renamed from: a */
        public int compare(File file, File file2) {
            int i = ((file.lastModified() - file2.lastModified()) > 0 ? 1 : ((file.lastModified() - file2.lastModified()) == 0 ? 0 : -1));
            if (i > 0) {
                return 1;
            }
            return i == 0 ? 0 : -1;
        }
    }

    public static File b(Context context) {
        if (context == null) {
            return null;
        }
        try {
            synchronized (c) {
                String str = context.getApplicationContext().getFilesDir() + File.separator + a.f;
                if (TextUtils.isEmpty(str)) {
                    return null;
                }
                File file = new File(str);
                synchronized (c) {
                    File[] listFiles = file.listFiles();
                    if (listFiles != null) {
                        if (listFiles.length != 0) {
                            Arrays.sort(listFiles, new AnonymousClass2());
                            return listFiles[0];
                        }
                    }
                    return null;
                }
            }
        } catch (Throwable th) {
            UMCrashManager.reportCrash(context, th);
            return null;
        }
    }

    /* compiled from: UMSLUtils */
    /* renamed from: com.umeng.commonsdk.stateless.d$2  reason: invalid class name */
    static class AnonymousClass2 implements Comparator<File> {
        AnonymousClass2() {
        }

        /* renamed from: a */
        public int compare(File file, File file2) {
            int i = ((file.lastModified() - file2.lastModified()) > 0 ? 1 : ((file.lastModified() - file2.lastModified()) == 0 ? 0 : -1));
            if (i > 0) {
                return 1;
            }
            return i == 0 ? 0 : -1;
        }
    }

    public static String a(Context context, boolean z) {
        String str;
        if (context == null) {
            return null;
        }
        if (z) {
            try {
                str = context.getApplicationContext().getFilesDir() + File.separator + a.e;
            } catch (Throwable unused) {
                return null;
            }
        } else {
            str = context.getApplicationContext().getFilesDir() + File.separator + a.f;
        }
        return str;
    }

    public static File[] c(Context context) {
        if (context == null) {
            return null;
        }
        try {
            synchronized (c) {
                String str = context.getApplicationContext().getFilesDir() + File.separator + a.f;
                if (TextUtils.isEmpty(str)) {
                    return null;
                }
                File file = new File(str);
                synchronized (c) {
                    File[] listFiles = file.listFiles();
                    if (listFiles != null) {
                        if (listFiles.length != 0) {
                            Arrays.sort(listFiles, new AnonymousClass3());
                            return listFiles;
                        }
                    }
                    return null;
                }
            }
        } catch (Throwable th) {
            UMCrashManager.reportCrash(context, th);
            return null;
        }
    }

    /* compiled from: UMSLUtils */
    /* renamed from: com.umeng.commonsdk.stateless.d$3  reason: invalid class name */
    static class AnonymousClass3 implements Comparator<File> {
        AnonymousClass3() {
        }

        /* renamed from: a */
        public int compare(File file, File file2) {
            int i = ((file.lastModified() - file2.lastModified()) > 0 ? 1 : ((file.lastModified() - file2.lastModified()) == 0 ? 0 : -1));
            if (i > 0) {
                return 1;
            }
            return i == 0 ? 0 : -1;
        }
    }

    public static void a(Context context, String str, int i) {
        if (str == null) {
            try {
                ULog.i("AmapLBS", "[lbs-build] fileDir not exist, thread is " + Thread.currentThread());
            } catch (Throwable th) {
                UMCrashManager.reportCrash(context, th);
            }
        } else {
            File file = new File(str);
            if (!file.isDirectory()) {
                ULog.i("AmapLBS", "[lbs-build] fileDir not exist, thread is " + Thread.currentThread());
                return;
            }
            synchronized (c) {
                File[] listFiles = file.listFiles();
                ULog.i("AmapLBS", "[lbs-build] delete file begin " + listFiles.length + ", thread is " + Thread.currentThread());
                if (listFiles == null || listFiles.length < i) {
                    ULog.i("AmapLBS", "[lbs-build] file size < max");
                } else {
                    ULog.i("AmapLBS", "[lbs-build] file size >= max");
                    ArrayList arrayList = new ArrayList();
                    for (File file2 : listFiles) {
                        if (file2 != null) {
                            arrayList.add(file2);
                        }
                    }
                    if (arrayList.size() >= i) {
                        Collections.sort(arrayList, new AnonymousClass4());
                        if (ULog.DEBUG) {
                            for (int i2 = 0; i2 < arrayList.size(); i2++) {
                                ULog.i("AmapLBS", "[lbs-build] overrun native file is " + ((File) arrayList.get(i2)).getPath());
                            }
                        }
                        for (int i3 = 0; i3 <= arrayList.size() - i; i3++) {
                            if (arrayList.get(i3) != null) {
                                ULog.i("AmapLBS", "[lbs-build] overrun remove file is " + ((File) arrayList.get(i3)).getPath());
                                try {
                                    ((File) arrayList.get(i3)).delete();
                                    arrayList.remove(i3);
                                } catch (Exception unused) {
                                }
                            }
                        }
                    }
                }
                ULog.i("AmapLBS", "[lbs-build] delete file end " + listFiles.length + ", thread is " + Thread.currentThread());
            }
        }
    }

    /* compiled from: UMSLUtils */
    /* renamed from: com.umeng.commonsdk.stateless.d$4  reason: invalid class name */
    static class AnonymousClass4 implements Comparator<File> {
        AnonymousClass4() {
        }

        /* renamed from: a */
        public int compare(File file, File file2) {
            if (file == null || file2 == null || file.lastModified() >= file2.lastModified()) {
                return (file == null || file2 == null || file.lastModified() != file2.lastModified()) ? 1 : 0;
            }
            return -1;
        }
    }

    public static void a(Context context, String str, String str2, int i) {
        if (str != null) {
            try {
                File file = new File(str);
                if (file.isDirectory()) {
                    synchronized (c) {
                        File[] listFiles = file.listFiles(new AnonymousClass5(str2));
                        if (listFiles == null || listFiles.length < i) {
                            ULog.i("AmapLBS", "[lbs-build] file size < max");
                        } else {
                            ULog.i("AmapLBS", "[lbs-build] file size >= max");
                            ArrayList arrayList = new ArrayList();
                            for (File file2 : listFiles) {
                                if (file2 != null) {
                                    arrayList.add(file2);
                                }
                            }
                            if (arrayList.size() >= i) {
                                Collections.sort(arrayList, new AnonymousClass6());
                                if (ULog.DEBUG) {
                                    for (int i2 = 0; i2 < arrayList.size(); i2++) {
                                        ULog.i("AmapLBS", "[lbs-build] overrun native file is " + ((File) arrayList.get(i2)).getPath());
                                    }
                                }
                                for (int i3 = 0; i3 <= arrayList.size() - i; i3++) {
                                    if (arrayList.get(i3) != null) {
                                        ULog.i("AmapLBS", "[lbs-build] overrun remove file is " + ((File) arrayList.get(i3)).getPath());
                                        try {
                                            ((File) arrayList.get(i3)).delete();
                                            arrayList.remove(i3);
                                        } catch (Exception unused) {
                                        }
                                    }
                                }
                            }
                        }
                        ULog.i("AmapLBS", "[lbs-build] delete file end " + listFiles.length + ", thread is " + Thread.currentThread());
                    }
                }
            } catch (Throwable th) {
                UMCrashManager.reportCrash(context, th);
            }
        }
    }

    /* compiled from: UMSLUtils */
    /* access modifiers changed from: package-private */
    /* renamed from: com.umeng.commonsdk.stateless.d$5  reason: invalid class name */
    public static class AnonymousClass5 implements FilenameFilter {
        final /* synthetic */ String a;

        AnonymousClass5(String str) {
            this.a = str;
        }

        @Override // java.io.FilenameFilter
        public boolean accept(File file, String str) {
            return str.startsWith(this.a);
        }
    }

    /* compiled from: UMSLUtils */
    /* access modifiers changed from: package-private */
    /* renamed from: com.umeng.commonsdk.stateless.d$6  reason: invalid class name */
    public static class AnonymousClass6 implements Comparator<File> {
        AnonymousClass6() {
        }

        /* renamed from: a */
        public int compare(File file, File file2) {
            if (file == null || file2 == null || file.lastModified() >= file2.lastModified()) {
                return (file == null || file2 == null || file.lastModified() != file2.lastModified()) ? 1 : 0;
            }
            return -1;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:18:0x0042  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static byte[] a(byte[] r5) throws java.io.IOException {
        /*
            r0 = 0
            if (r5 == 0) goto L_0x0046
            int r1 = r5.length
            if (r1 > 0) goto L_0x0007
            goto L_0x0046
        L_0x0007:
            java.util.zip.Deflater r1 = new java.util.zip.Deflater
            r1.<init>()
            r1.setInput(r5)
            r1.finish()
            r5 = 8192(0x2000, float:1.14794E-41)
            byte[] r5 = new byte[r5]
            r2 = 0
            com.umeng.commonsdk.stateless.d.a = r2
            java.io.ByteArrayOutputStream r3 = new java.io.ByteArrayOutputStream     // Catch:{ all -> 0x003e }
            r3.<init>()     // Catch:{ all -> 0x003e }
        L_0x001e:
            boolean r0 = r1.finished()     // Catch:{ all -> 0x003c }
            if (r0 != 0) goto L_0x0031
            int r0 = r1.deflate(r5)     // Catch:{ all -> 0x003c }
            int r4 = com.umeng.commonsdk.stateless.d.a     // Catch:{ all -> 0x003c }
            int r4 = r4 + r0
            com.umeng.commonsdk.stateless.d.a = r4     // Catch:{ all -> 0x003c }
            r3.write(r5, r2, r0)     // Catch:{ all -> 0x003c }
            goto L_0x001e
        L_0x0031:
            r1.end()     // Catch:{ all -> 0x003c }
            r3.close()
            byte[] r5 = r3.toByteArray()
            return r5
        L_0x003c:
            r5 = move-exception
            goto L_0x0040
        L_0x003e:
            r5 = move-exception
            r3 = r0
        L_0x0040:
            if (r3 == 0) goto L_0x0045
            r3.close()
        L_0x0045:
            throw r5
        L_0x0046:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.umeng.commonsdk.stateless.d.a(byte[]):byte[]");
    }

    public static byte[] a(byte[] bArr, byte[] bArr2) throws Exception {
        Cipher instance = Cipher.getInstance("AES/CBC/PKCS7Padding");
        instance.init(1, new SecretKeySpec(bArr2, KeyProperties.KEY_ALGORITHM_AES), new IvParameterSpec(b));
        return instance.doFinal(bArr);
    }

    public static byte[] b(byte[] bArr) {
        try {
            MessageDigest instance = MessageDigest.getInstance(KeyProperties.DIGEST_MD5);
            instance.reset();
            instance.update(bArr);
            return instance.digest();
        } catch (Exception unused) {
            return null;
        }
    }

    public static String c(byte[] bArr) {
        if (bArr == null) {
            return null;
        }
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < bArr.length; i++) {
            stringBuffer.append(String.format("%02X", Byte.valueOf(bArr[i])));
        }
        return stringBuffer.toString().toLowerCase(Locale.US);
    }

    public static String b(String str) {
        try {
            return Base64.encodeToString(str.getBytes(), 0);
        } catch (Throwable unused) {
            return "";
        }
    }

    public static String c(String str) {
        try {
            return new String(Base64.decode(str, 0));
        } catch (Throwable unused) {
            return "";
        }
    }

    public static String d(String str) {
        int lastIndexOf;
        int lastIndexOf2;
        if (!TextUtils.isEmpty(str) && str.indexOf("envelope") < 0 && (lastIndexOf = str.lastIndexOf(Session.SESSION_SEPARATION_CHAR_CHILD)) >= 0 && (lastIndexOf2 = str.lastIndexOf(".")) >= 0) {
            return str.substring(lastIndexOf + 1, lastIndexOf2);
        }
        return "";
    }
}
