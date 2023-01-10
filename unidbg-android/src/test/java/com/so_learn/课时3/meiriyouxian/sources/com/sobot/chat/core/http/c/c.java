package com.sobot.chat.core.http.c;

import java.io.File;
import okhttp3.Response;

/* compiled from: FileCallBack */
public abstract class c extends b<File> {
    private String a;
    private String b;

    public abstract void a(float f, long j);

    public c(String str) {
        File file = new File(str);
        this.a = file.getParent();
        this.b = file.getName();
    }

    /* renamed from: b */
    public File a(Response response) throws Exception {
        return c(response);
    }

    /* JADX WARNING: Removed duplicated region for block: B:27:0x0089 A[SYNTHETIC, Splitter:B:27:0x0089] */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x008e A[SYNTHETIC, Splitter:B:31:0x008e] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.io.File c(okhttp3.Response r18) throws java.io.IOException {
        /*
            r17 = this;
            r7 = r17
            r0 = 2048(0x800, float:2.87E-42)
            byte[] r0 = new byte[r0]
            r1 = 0
            okhttp3.ResponseBody r2 = r18.body()     // Catch:{ all -> 0x0085 }
            java.io.InputStream r8 = r2.byteStream()     // Catch:{ all -> 0x0085 }
            okhttp3.ResponseBody r2 = r18.body()     // Catch:{ all -> 0x0083 }
            long r9 = r2.contentLength()     // Catch:{ all -> 0x0083 }
            r2 = 0
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ all -> 0x0083 }
            r4.<init>()     // Catch:{ all -> 0x0083 }
            r4.append(r9)     // Catch:{ all -> 0x0083 }
            java.lang.String r5 = ""
            r4.append(r5)     // Catch:{ all -> 0x0083 }
            java.lang.String r4 = r4.toString()     // Catch:{ all -> 0x0083 }
            com.sobot.chat.core.http.h.c.a(r4)     // Catch:{ all -> 0x0083 }
            java.io.File r4 = new java.io.File     // Catch:{ all -> 0x0083 }
            java.lang.String r5 = r7.a     // Catch:{ all -> 0x0083 }
            r4.<init>(r5)     // Catch:{ all -> 0x0083 }
            boolean r5 = r4.exists()     // Catch:{ all -> 0x0083 }
            if (r5 != 0) goto L_0x003e
            r4.mkdirs()     // Catch:{ all -> 0x0083 }
        L_0x003e:
            java.io.File r11 = new java.io.File     // Catch:{ all -> 0x0083 }
            java.lang.String r5 = r7.b     // Catch:{ all -> 0x0083 }
            r11.<init>(r4, r5)     // Catch:{ all -> 0x0083 }
            java.io.FileOutputStream r12 = new java.io.FileOutputStream     // Catch:{ all -> 0x0083 }
            r12.<init>(r11)     // Catch:{ all -> 0x0083 }
        L_0x004a:
            int r1 = r8.read(r0)     // Catch:{ all -> 0x0080 }
            r4 = -1
            if (r1 == r4) goto L_0x0074
            long r4 = (long) r1     // Catch:{ all -> 0x0080 }
            long r13 = r2 + r4
            r2 = 0
            r12.write(r0, r2, r1)     // Catch:{ all -> 0x0080 }
            com.sobot.chat.core.http.a r1 = com.sobot.chat.core.http.a.a()     // Catch:{ all -> 0x0080 }
            android.os.Handler r15 = r1.b()     // Catch:{ all -> 0x0080 }
            com.sobot.chat.core.http.c.c$1 r5 = new com.sobot.chat.core.http.c.c$1     // Catch:{ all -> 0x0080 }
            r1 = r5
            r2 = r17
            r3 = r13
            r16 = r0
            r0 = r5
            r5 = r9
            r1.<init>(r3, r5)     // Catch:{ all -> 0x0080 }
            r15.post(r0)     // Catch:{ all -> 0x0080 }
            r2 = r13
            r0 = r16
            goto L_0x004a
        L_0x0074:
            r12.flush()     // Catch:{ all -> 0x0080 }
            if (r8 == 0) goto L_0x007c
            r8.close()     // Catch:{ IOException -> 0x007c }
        L_0x007c:
            r12.close()     // Catch:{ IOException -> 0x007f }
        L_0x007f:
            return r11
        L_0x0080:
            r0 = move-exception
            r1 = r12
            goto L_0x0087
        L_0x0083:
            r0 = move-exception
            goto L_0x0087
        L_0x0085:
            r0 = move-exception
            r8 = r1
        L_0x0087:
            if (r8 == 0) goto L_0x008c
            r8.close()     // Catch:{ IOException -> 0x008c }
        L_0x008c:
            if (r1 == 0) goto L_0x0091
            r1.close()     // Catch:{ IOException -> 0x0091 }
        L_0x0091:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.sobot.chat.core.http.c.c.c(okhttp3.Response):java.io.File");
    }

    /* compiled from: FileCallBack */
    /* access modifiers changed from: package-private */
    /* renamed from: com.sobot.chat.core.http.c.c$1  reason: invalid class name */
    public class AnonymousClass1 implements Runnable {
        final /* synthetic */ long a;
        final /* synthetic */ long b;

        AnonymousClass1(long j, long j2) {
            this.a = j;
            this.b = j2;
        }

        @Override // java.lang.Runnable
        public void run() {
            c cVar = c.this;
            long j = this.b;
            cVar.a((((float) this.a) * 1.0f) / ((float) j), j);
        }
    }
}
