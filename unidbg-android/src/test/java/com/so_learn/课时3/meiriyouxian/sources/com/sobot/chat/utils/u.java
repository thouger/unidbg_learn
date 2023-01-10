package com.sobot.chat.utils;

import android.content.Context;
import android.os.Process;
import android.telecom.Logging.Session;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

/* compiled from: SobotCache */
public class u {
    private static Map<String, u> a = new HashMap();
    private a b;

    public static u a(Context context) {
        return a(context, context.getPackageName() + "_sobotCache");
    }

    public static u a(Context context, String str) {
        return a(new File(context.getCacheDir(), str), 50000000, Integer.MAX_VALUE);
    }

    public static u a(File file, long j, int i) {
        Map<String, u> map = a;
        u uVar = map.get(file.getAbsoluteFile() + a());
        if (uVar != null) {
            return uVar;
        }
        u uVar2 = new u(file, j, i);
        Map<String, u> map2 = a;
        map2.put(file.getAbsolutePath() + a(), uVar2);
        return uVar2;
    }

    private static String a() {
        return Session.SESSION_SEPARATION_CHAR_CHILD + Process.myPid();
    }

    private u(File file, long j, int i) {
        if (!file.exists()) {
            file.mkdirs();
        }
        this.b = new a(file, j, i);
    }

    /* JADX WARNING: Removed duplicated region for block: B:17:0x0026 A[SYNTHETIC, Splitter:B:17:0x0026] */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x0039 A[SYNTHETIC, Splitter:B:24:0x0039] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void a(java.lang.String r3, byte[] r4) {
        /*
            r2 = this;
            com.sobot.chat.utils.u$a r0 = r2.b
            java.io.File r3 = com.sobot.chat.utils.u.a.a(r0, r3)
            r0 = 0
            java.io.FileOutputStream r1 = new java.io.FileOutputStream     // Catch:{ Exception -> 0x0020 }
            r1.<init>(r3)     // Catch:{ Exception -> 0x0020 }
            r1.write(r4)     // Catch:{ Exception -> 0x001b, all -> 0x0018 }
            r1.flush()     // Catch:{ IOException -> 0x0016 }
            r1.close()     // Catch:{ IOException -> 0x0016 }
            goto L_0x0031
        L_0x0016:
            r4 = move-exception
            goto L_0x002e
        L_0x0018:
            r4 = move-exception
            r0 = r1
            goto L_0x0037
        L_0x001b:
            r4 = move-exception
            r0 = r1
            goto L_0x0021
        L_0x001e:
            r4 = move-exception
            goto L_0x0037
        L_0x0020:
            r4 = move-exception
        L_0x0021:
            r4.printStackTrace()     // Catch:{ all -> 0x001e }
            if (r0 == 0) goto L_0x0031
            r0.flush()     // Catch:{ IOException -> 0x002d }
            r0.close()     // Catch:{ IOException -> 0x002d }
            goto L_0x0031
        L_0x002d:
            r4 = move-exception
        L_0x002e:
            r4.printStackTrace()
        L_0x0031:
            com.sobot.chat.utils.u$a r4 = r2.b
            com.sobot.chat.utils.u.a.a(r4, r3)
            return
        L_0x0037:
            if (r0 == 0) goto L_0x0044
            r0.flush()     // Catch:{ IOException -> 0x0040 }
            r0.close()     // Catch:{ IOException -> 0x0040 }
            goto L_0x0044
        L_0x0040:
            r0 = move-exception
            r0.printStackTrace()
        L_0x0044:
            com.sobot.chat.utils.u$a r0 = r2.b
            com.sobot.chat.utils.u.a.a(r0, r3)
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.sobot.chat.utils.u.a(java.lang.String, byte[]):void");
    }

    public void a(String str, byte[] bArr, int i) {
        a(str, b.b(i, bArr));
    }

    /* JADX WARNING: Removed duplicated region for block: B:28:0x004a A[SYNTHETIC, Splitter:B:28:0x004a] */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x0057 A[SYNTHETIC, Splitter:B:36:0x0057] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public byte[] a(java.lang.String r6) {
        /*
            r5 = this;
            r0 = 0
            com.sobot.chat.utils.u$a r1 = r5.b     // Catch:{ Exception -> 0x0043, all -> 0x0041 }
            java.io.File r1 = com.sobot.chat.utils.u.a.b(r1, r6)     // Catch:{ Exception -> 0x0043, all -> 0x0041 }
            boolean r2 = r1.exists()     // Catch:{ Exception -> 0x0043, all -> 0x0041 }
            if (r2 != 0) goto L_0x000e
            return r0
        L_0x000e:
            java.io.RandomAccessFile r2 = new java.io.RandomAccessFile     // Catch:{ Exception -> 0x0043, all -> 0x0041 }
            java.lang.String r3 = "r"
            r2.<init>(r1, r3)     // Catch:{ Exception -> 0x0043, all -> 0x0041 }
            long r3 = r2.length()     // Catch:{ Exception -> 0x003f }
            int r1 = (int) r3     // Catch:{ Exception -> 0x003f }
            byte[] r1 = new byte[r1]     // Catch:{ Exception -> 0x003f }
            r2.read(r1)     // Catch:{ Exception -> 0x003f }
            boolean r3 = com.sobot.chat.utils.u.b.a(r1)     // Catch:{ Exception -> 0x003f }
            if (r3 != 0) goto L_0x0033
            byte[] r6 = com.sobot.chat.utils.u.b.b(r1)     // Catch:{ Exception -> 0x003f }
            r2.close()     // Catch:{ IOException -> 0x002e }
            goto L_0x0032
        L_0x002e:
            r0 = move-exception
            r0.printStackTrace()
        L_0x0032:
            return r6
        L_0x0033:
            r2.close()     // Catch:{ IOException -> 0x0037 }
            goto L_0x003b
        L_0x0037:
            r1 = move-exception
            r1.printStackTrace()
        L_0x003b:
            r5.c(r6)
            return r0
        L_0x003f:
            r6 = move-exception
            goto L_0x0045
        L_0x0041:
            r6 = move-exception
            goto L_0x0055
        L_0x0043:
            r6 = move-exception
            r2 = r0
        L_0x0045:
            r6.printStackTrace()     // Catch:{ all -> 0x0053 }
            if (r2 == 0) goto L_0x0052
            r2.close()     // Catch:{ IOException -> 0x004e }
            goto L_0x0052
        L_0x004e:
            r6 = move-exception
            r6.printStackTrace()
        L_0x0052:
            return r0
        L_0x0053:
            r6 = move-exception
            r0 = r2
        L_0x0055:
            if (r0 == 0) goto L_0x005f
            r0.close()     // Catch:{ IOException -> 0x005b }
            goto L_0x005f
        L_0x005b:
            r0 = move-exception
            r0.printStackTrace()
        L_0x005f:
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.sobot.chat.utils.u.a(java.lang.String):byte[]");
    }

    public void a(String str, Serializable serializable) {
        a(str, serializable, -1);
    }

    public void a(String str, Serializable serializable, int i) {
        Throwable th;
        Exception e;
        ObjectOutputStream objectOutputStream = null;
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ObjectOutputStream objectOutputStream2 = new ObjectOutputStream(byteArrayOutputStream);
            try {
                objectOutputStream2.writeObject(serializable);
                byte[] byteArray = byteArrayOutputStream.toByteArray();
                if (i != -1) {
                    a(str, byteArray, i);
                } else {
                    a(str, byteArray);
                }
                try {
                    objectOutputStream2.close();
                } catch (IOException unused) {
                }
            } catch (Exception e2) {
                e = e2;
                objectOutputStream = objectOutputStream2;
                try {
                    e.printStackTrace();
                    objectOutputStream.close();
                } catch (Throwable th2) {
                    th = th2;
                    try {
                        objectOutputStream.close();
                    } catch (IOException unused2) {
                    }
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                objectOutputStream = objectOutputStream2;
                objectOutputStream.close();
                throw th;
            }
        } catch (Exception e3) {
            e = e3;
            e.printStackTrace();
            objectOutputStream.close();
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:28:0x0038 A[SYNTHETIC, Splitter:B:28:0x0038] */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x0042 A[SYNTHETIC, Splitter:B:33:0x0042] */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x0051 A[SYNTHETIC, Splitter:B:41:0x0051] */
    /* JADX WARNING: Removed duplicated region for block: B:46:0x005b A[SYNTHETIC, Splitter:B:46:0x005b] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.Object b(java.lang.String r5) {
        /*
            r4 = this;
            byte[] r5 = r4.a(r5)
            r0 = 0
            if (r5 == 0) goto L_0x0064
            java.io.ByteArrayInputStream r1 = new java.io.ByteArrayInputStream     // Catch:{ Exception -> 0x0030, all -> 0x002d }
            r1.<init>(r5)     // Catch:{ Exception -> 0x0030, all -> 0x002d }
            java.io.ObjectInputStream r5 = new java.io.ObjectInputStream     // Catch:{ Exception -> 0x002a, all -> 0x0028 }
            r5.<init>(r1)     // Catch:{ Exception -> 0x002a, all -> 0x0028 }
            java.lang.Object r0 = r5.readObject()     // Catch:{ Exception -> 0x0026 }
            r1.close()     // Catch:{ IOException -> 0x0019 }
            goto L_0x001d
        L_0x0019:
            r1 = move-exception
            r1.printStackTrace()
        L_0x001d:
            r5.close()     // Catch:{ IOException -> 0x0021 }
            goto L_0x0025
        L_0x0021:
            r5 = move-exception
            r5.printStackTrace()
        L_0x0025:
            return r0
        L_0x0026:
            r2 = move-exception
            goto L_0x0033
        L_0x0028:
            r5 = move-exception
            goto L_0x004f
        L_0x002a:
            r2 = move-exception
            r5 = r0
            goto L_0x0033
        L_0x002d:
            r5 = move-exception
            r1 = r0
            goto L_0x004f
        L_0x0030:
            r2 = move-exception
            r5 = r0
            r1 = r5
        L_0x0033:
            r2.printStackTrace()     // Catch:{ all -> 0x004b }
            if (r1 == 0) goto L_0x0040
            r1.close()     // Catch:{ IOException -> 0x003c }
            goto L_0x0040
        L_0x003c:
            r1 = move-exception
            r1.printStackTrace()
        L_0x0040:
            if (r5 == 0) goto L_0x004a
            r5.close()     // Catch:{ IOException -> 0x0046 }
            goto L_0x004a
        L_0x0046:
            r5 = move-exception
            r5.printStackTrace()
        L_0x004a:
            return r0
        L_0x004b:
            r0 = move-exception
            r3 = r0
            r0 = r5
            r5 = r3
        L_0x004f:
            if (r1 == 0) goto L_0x0059
            r1.close()     // Catch:{ IOException -> 0x0055 }
            goto L_0x0059
        L_0x0055:
            r1 = move-exception
            r1.printStackTrace()
        L_0x0059:
            if (r0 == 0) goto L_0x0063
            r0.close()     // Catch:{ IOException -> 0x005f }
            goto L_0x0063
        L_0x005f:
            r0 = move-exception
            r0.printStackTrace()
        L_0x0063:
            throw r5
        L_0x0064:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.sobot.chat.utils.u.b(java.lang.String):java.lang.Object");
    }

    public boolean c(String str) {
        return this.b.c(str);
    }

    /* compiled from: SobotCache */
    public class a {
        protected File a;
        private final AtomicLong c;
        private final AtomicInteger d;
        private final long e;
        private final int f;
        private final Map<File, Long> g;

        private a(File file, long j, int i) {
            this.g = Collections.synchronizedMap(new HashMap());
            this.a = file;
            this.e = j;
            this.f = i;
            this.c = new AtomicLong();
            this.d = new AtomicInteger();
            a();
        }

        /* compiled from: SobotCache */
        /* access modifiers changed from: package-private */
        /* renamed from: com.sobot.chat.utils.u$a$1  reason: invalid class name */
        public class AnonymousClass1 implements Runnable {
            AnonymousClass1() {
            }

            @Override // java.lang.Runnable
            public void run() {
                File[] listFiles = a.this.a.listFiles();
                if (listFiles != null) {
                    int i = 0;
                    int i2 = 0;
                    for (File file : listFiles) {
                        i = (int) (((long) i) + a.this.b(file));
                        i2++;
                        a.this.g.put(file, Long.valueOf(file.lastModified()));
                    }
                    a.this.c.set((long) i);
                    a.this.d.set(i2);
                }
            }
        }

        private void a() {
            new Thread(new AnonymousClass1()).start();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void a(File file) {
            int i = this.d.get();
            while (i + 1 > this.f) {
                this.c.addAndGet(-b());
                i = this.d.addAndGet(-1);
            }
            this.d.addAndGet(1);
            long b = b(file);
            long j = this.c.get();
            while (j + b > this.e) {
                j = this.c.addAndGet(-b());
            }
            this.c.addAndGet(b);
            Long valueOf = Long.valueOf(System.currentTimeMillis());
            file.setLastModified(valueOf.longValue());
            this.g.put(file, valueOf);
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private File a(String str) {
            File b = b(str);
            Long valueOf = Long.valueOf(System.currentTimeMillis());
            b.setLastModified(valueOf.longValue());
            this.g.put(b, valueOf);
            return b;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private File b(String str) {
            if (!this.a.exists()) {
                this.a.mkdirs();
            }
            File file = this.a;
            return new File(file, str.hashCode() + "");
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private boolean c(String str) {
            return a(str).delete();
        }

        private long b() {
            File file;
            if (this.g.isEmpty()) {
                return 0;
            }
            Set<Map.Entry<File, Long>> entrySet = this.g.entrySet();
            synchronized (this.g) {
                file = null;
                Long l = null;
                for (Map.Entry<File, Long> entry : entrySet) {
                    if (file == null) {
                        file = entry.getKey();
                        l = entry.getValue();
                    } else {
                        Long value = entry.getValue();
                        if (value.longValue() < l.longValue()) {
                            file = entry.getKey();
                            l = value;
                        }
                    }
                }
            }
            long b = b(file);
            if (file.delete()) {
                this.g.remove(file);
            }
            return b;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private long b(File file) {
            return file.length();
        }
    }

    /* compiled from: SobotCache */
    /* access modifiers changed from: private */
    public static class b {
        /* access modifiers changed from: private */
        public static boolean c(byte[] bArr) {
            String[] f = f(bArr);
            if (f != null && f.length == 2) {
                String str = f[0];
                while (str.startsWith("0")) {
                    str = str.substring(1, str.length());
                }
                if (System.currentTimeMillis() > Long.valueOf(str).longValue() + (Long.valueOf(f[1]).longValue() * 1000)) {
                    return true;
                }
            }
            return false;
        }

        /* access modifiers changed from: private */
        public static byte[] b(int i, byte[] bArr) {
            byte[] bytes = a(i).getBytes();
            byte[] bArr2 = new byte[(bytes.length + bArr.length)];
            System.arraycopy(bytes, 0, bArr2, 0, bytes.length);
            System.arraycopy(bArr, 0, bArr2, bytes.length, bArr.length);
            return bArr2;
        }

        /* access modifiers changed from: private */
        public static byte[] d(byte[] bArr) {
            return e(bArr) ? a(bArr, a(bArr, ' ') + 1, bArr.length) : bArr;
        }

        private static boolean e(byte[] bArr) {
            return bArr != null && bArr.length > 15 && bArr[13] == 45 && a(bArr, ' ') > 14;
        }

        private static String[] f(byte[] bArr) {
            if (e(bArr)) {
                return new String[]{new String(a(bArr, 0, 13)), new String(a(bArr, 14, a(bArr, ' ')))};
            }
            return null;
        }

        private static int a(byte[] bArr, char c) {
            for (int i = 0; i < bArr.length; i++) {
                if (bArr[i] == c) {
                    return i;
                }
            }
            return -1;
        }

        private static byte[] a(byte[] bArr, int i, int i2) {
            int i3 = i2 - i;
            if (i3 >= 0) {
                byte[] bArr2 = new byte[i3];
                System.arraycopy(bArr, i, bArr2, 0, Math.min(bArr.length - i, i3));
                return bArr2;
            }
            throw new IllegalArgumentException(i + " > " + i2);
        }

        private static String a(int i) {
            String str = System.currentTimeMillis() + "";
            while (str.length() < 13) {
                str = "0" + str;
            }
            return str + "-" + i + ' ';
        }
    }
}
