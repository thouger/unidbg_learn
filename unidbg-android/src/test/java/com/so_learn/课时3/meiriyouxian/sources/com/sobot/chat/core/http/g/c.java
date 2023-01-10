package com.sobot.chat.core.http.g;

import android.text.TextUtils;
import android.util.Log;
import com.sobot.chat.core.http.d.e;
import com.sobot.chat.core.http.f.a;
import com.sobot.chat.core.http.h.b;
import com.sobot.chat.core.http.model.SobotProgress;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ThreadPoolExecutor;
import okhttp3.Response;
import okhttp3.ResponseBody;

/* compiled from: SobotDownloadTask */
public class c implements Runnable {
    public SobotProgress a;
    public Map<Object, b> b;
    private ThreadPoolExecutor c;
    private com.sobot.chat.core.http.e.c d;

    public c(String str, e eVar) {
        this.a = new SobotProgress();
        SobotProgress sobotProgress = this.a;
        sobotProgress.tag = str;
        sobotProgress.isUpload = false;
        sobotProgress.folder = a.a().b();
        this.a.url = eVar.b().d();
        SobotProgress sobotProgress2 = this.a;
        sobotProgress2.status = 0;
        sobotProgress2.totalSize = -1;
        sobotProgress2.request = eVar;
        this.c = a.a().c().a();
        this.b = new HashMap();
    }

    public c(SobotProgress sobotProgress) {
        this.a = sobotProgress;
        this.c = a.a().c().a();
        this.b = new HashMap();
    }

    public c a(String str) {
        if (str != null && !TextUtils.isEmpty(str.trim())) {
            this.a.fileName = str;
        }
        return this;
    }

    public c a(int i) {
        this.a.priority = i;
        return this;
    }

    public c a() {
        if (!TextUtils.isEmpty(this.a.folder) && !TextUtils.isEmpty(this.a.fileName)) {
            SobotProgress sobotProgress = this.a;
            sobotProgress.filePath = new File(sobotProgress.folder, this.a.fileName).getAbsolutePath();
        }
        a.a().b((a) this.a);
        return this;
    }

    public c a(b bVar) {
        if (bVar != null) {
            this.b.put(bVar.b, bVar);
        }
        return this;
    }

    public void b(String str) {
        this.b.remove(str);
    }

    public void b() {
        if (a.a().b(this.a.tag) == null || a.a().a(this.a.tag) == null) {
            Log.i("SobotDownloadTask", "you must call SobotDownloadTask#save() before SobotDownloadTask#start()\uff01");
        } else if (this.a.status == 0 || this.a.status == 3 || this.a.status == 4) {
            a(this.a);
            b(this.a);
            this.d = new com.sobot.chat.core.http.e.c(this.a.priority, this);
            this.c.execute(this.d);
        } else if (this.a.status != 5) {
        } else {
            if (this.a.filePath == null) {
                SobotProgress sobotProgress = this.a;
                a(sobotProgress, new com.sobot.chat.core.http.b.c("the file of the task with tag:" + this.a.tag + " may be invalid or damaged, please call the method restart() to download again\uff01"));
                return;
            }
            File file = new File(this.a.filePath);
            if (!file.exists() || file.length() != this.a.totalSize) {
                SobotProgress sobotProgress2 = this.a;
                a(sobotProgress2, new com.sobot.chat.core.http.b.c("the file " + this.a.filePath + " may be invalid or damaged, please call the method restart() to download again\uff01"));
                return;
            }
            SobotProgress sobotProgress3 = this.a;
            a(sobotProgress3, new File(sobotProgress3.filePath));
        }
    }

    public void c() {
        this.c.remove(this.d);
        if (this.a.status == 1) {
            c(this.a);
        } else if (this.a.status == 2) {
            SobotProgress sobotProgress = this.a;
            sobotProgress.speed = 0;
            sobotProgress.status = 3;
        }
    }

    public void d() {
        a(false);
    }

    public c a(boolean z) {
        c();
        if (z) {
            b.b(this.a.filePath);
        }
        a.a().b(this.a.tag);
        c c = a.a().c(this.a.tag);
        e(this.a);
        return c;
    }

    @Override // java.lang.Runnable
    public void run() {
        File file;
        long j = this.a.currentSize;
        int i = (j > 0 ? 1 : (j == 0 ? 0 : -1));
        if (i < 0) {
            a(this.a, com.sobot.chat.core.http.b.a.c());
            return;
        }
        if (i > 0 && !TextUtils.isEmpty(this.a.filePath) && !new File(this.a.filePath).exists()) {
            this.a.currentSize = 0;
            j = 0;
        }
        try {
            e eVar = this.a.request;
            eVar.b().a("Range", "bytes=" + j + "-");
            Response c = eVar.c();
            int code = c.code();
            if (code == 404 || code >= 500) {
                a(this.a, com.sobot.chat.core.http.b.b.c());
                return;
            }
            ResponseBody body = c.body();
            if (body == null) {
                a(this.a, new com.sobot.chat.core.http.b.b("response body is null"));
                return;
            }
            if (this.a.totalSize == -1) {
                this.a.totalSize = body.contentLength();
            }
            String str = this.a.fileName;
            if (TextUtils.isEmpty(str)) {
                str = b.a(c, this.a.url);
                this.a.fileName = str;
            }
            if (!b.a(this.a.folder)) {
                a(this.a, com.sobot.chat.core.http.b.c.a());
                return;
            }
            if (TextUtils.isEmpty(this.a.filePath)) {
                file = new File(this.a.folder, str);
                this.a.filePath = file.getAbsolutePath();
            } else {
                file = new File(this.a.filePath);
            }
            int i2 = (j > 0 ? 1 : (j == 0 ? 0 : -1));
            if (i2 > 0 && !file.exists()) {
                d();
                a(this.a, com.sobot.chat.core.http.b.a.c());
            } else if (j > this.a.totalSize) {
                a(true);
                a(this.a, com.sobot.chat.core.http.b.a.c());
            } else {
                if (i2 == 0 && file.exists()) {
                    b.b(file);
                }
                if (j != this.a.totalSize || i2 <= 0) {
                    try {
                        RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rw");
                        randomAccessFile.seek(j);
                        this.a.currentSize = j;
                        try {
                            a.a().b((a) this.a);
                            a(body.byteStream(), randomAccessFile, this.a);
                            if (this.a.status == 3) {
                                c(this.a);
                            } else if (this.a.status != 2) {
                                a(this.a, com.sobot.chat.core.http.b.a.a());
                            } else if (file.length() == this.a.totalSize) {
                                a(this.a, file);
                            } else {
                                a(this.a, com.sobot.chat.core.http.b.a.c());
                            }
                        } catch (Exception e) {
                            a(this.a, e);
                        }
                    } catch (Exception e2) {
                        a(this.a, e2);
                    }
                } else if (!file.exists() || j != file.length()) {
                    a(true);
                    a(this.a, com.sobot.chat.core.http.b.a.c());
                } else {
                    a(this.a, file);
                }
            }
        } catch (Exception e3) {
            a(this.a, e3);
        }
    }

    private void a(InputStream inputStream, RandomAccessFile randomAccessFile, SobotProgress sobotProgress) throws IOException {
        Throwable th;
        int read;
        if (inputStream != null && randomAccessFile != null) {
            sobotProgress.status = 2;
            byte[] bArr = new byte[8192];
            BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream, 8192);
            while (true) {
                try {
                    read = bufferedInputStream.read(bArr, 0, 8192);
                } catch (Throwable th2) {
                    th = th2;
                    b.a(randomAccessFile);
                    b.a(bufferedInputStream);
                    b.a(inputStream);
                    throw th;
                }
                if (read == -1 || sobotProgress.status != 2) {
                    break;
                }
                randomAccessFile.write(bArr, 0, read);
                try {
                    SobotProgress.changeProgress(sobotProgress, (long) read, sobotProgress.totalSize, new AnonymousClass1());
                } catch (Throwable th3) {
                    th = th3;
                }
            }
            b.a(randomAccessFile);
            b.a(bufferedInputStream);
            b.a(inputStream);
        }
    }

    /* compiled from: SobotDownloadTask */
    /* access modifiers changed from: package-private */
    /* renamed from: com.sobot.chat.core.http.g.c$1  reason: invalid class name */
    public class AnonymousClass1 implements SobotProgress.a {
        AnonymousClass1() {
        }

        @Override // com.sobot.chat.core.http.model.SobotProgress.a
        public void a(SobotProgress sobotProgress) {
            c.this.d(sobotProgress);
        }
    }

    private void a(SobotProgress sobotProgress) {
        sobotProgress.speed = 0;
        sobotProgress.status = 0;
        f(sobotProgress);
        com.sobot.chat.core.http.a.a((Runnable) new AnonymousClass2(sobotProgress));
    }

    /* compiled from: SobotDownloadTask */
    /* access modifiers changed from: package-private */
    /* renamed from: com.sobot.chat.core.http.g.c$2  reason: invalid class name */
    public class AnonymousClass2 implements Runnable {
        final /* synthetic */ SobotProgress a;

        AnonymousClass2(SobotProgress sobotProgress) {
            this.a = sobotProgress;
        }

        @Override // java.lang.Runnable
        public void run() {
            for (b bVar : c.this.b.values()) {
                bVar.a(this.a);
            }
        }
    }

    private void b(SobotProgress sobotProgress) {
        sobotProgress.speed = 0;
        sobotProgress.status = 1;
        f(sobotProgress);
        com.sobot.chat.core.http.a.a((Runnable) new AnonymousClass3(sobotProgress));
    }

    /* compiled from: SobotDownloadTask */
    /* access modifiers changed from: package-private */
    /* renamed from: com.sobot.chat.core.http.g.c$3  reason: invalid class name */
    public class AnonymousClass3 implements Runnable {
        final /* synthetic */ SobotProgress a;

        AnonymousClass3(SobotProgress sobotProgress) {
            this.a = sobotProgress;
        }

        @Override // java.lang.Runnable
        public void run() {
            for (b bVar : c.this.b.values()) {
                bVar.b(this.a);
            }
        }
    }

    private void c(SobotProgress sobotProgress) {
        sobotProgress.speed = 0;
        sobotProgress.status = 3;
        f(sobotProgress);
        com.sobot.chat.core.http.a.a((Runnable) new AnonymousClass4(sobotProgress));
    }

    /* compiled from: SobotDownloadTask */
    /* access modifiers changed from: package-private */
    /* renamed from: com.sobot.chat.core.http.g.c$4  reason: invalid class name */
    public class AnonymousClass4 implements Runnable {
        final /* synthetic */ SobotProgress a;

        AnonymousClass4(SobotProgress sobotProgress) {
            this.a = sobotProgress;
        }

        @Override // java.lang.Runnable
        public void run() {
            for (b bVar : c.this.b.values()) {
                bVar.b(this.a);
            }
        }
    }

    /* compiled from: SobotDownloadTask */
    /* access modifiers changed from: package-private */
    /* renamed from: com.sobot.chat.core.http.g.c$5  reason: invalid class name */
    public class AnonymousClass5 implements Runnable {
        final /* synthetic */ SobotProgress a;

        AnonymousClass5(SobotProgress sobotProgress) {
            this.a = sobotProgress;
        }

        @Override // java.lang.Runnable
        public void run() {
            for (b bVar : c.this.b.values()) {
                bVar.b(this.a);
            }
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void d(SobotProgress sobotProgress) {
        f(sobotProgress);
        com.sobot.chat.core.http.a.a((Runnable) new AnonymousClass5(sobotProgress));
    }

    private void a(SobotProgress sobotProgress, Throwable th) {
        sobotProgress.speed = 0;
        sobotProgress.status = 4;
        sobotProgress.exception = th;
        f(sobotProgress);
        com.sobot.chat.core.http.a.a((Runnable) new AnonymousClass6(sobotProgress));
    }

    /* compiled from: SobotDownloadTask */
    /* access modifiers changed from: package-private */
    /* renamed from: com.sobot.chat.core.http.g.c$6  reason: invalid class name */
    public class AnonymousClass6 implements Runnable {
        final /* synthetic */ SobotProgress a;

        AnonymousClass6(SobotProgress sobotProgress) {
            this.a = sobotProgress;
        }

        @Override // java.lang.Runnable
        public void run() {
            for (b bVar : c.this.b.values()) {
                bVar.b(this.a);
                bVar.c(this.a);
            }
        }
    }

    private void a(SobotProgress sobotProgress, File file) {
        sobotProgress.speed = 0;
        sobotProgress.fraction = 1.0f;
        sobotProgress.status = 5;
        f(sobotProgress);
        com.sobot.chat.core.http.a.a((Runnable) new AnonymousClass7(sobotProgress, file));
    }

    /* compiled from: SobotDownloadTask */
    /* access modifiers changed from: package-private */
    /* renamed from: com.sobot.chat.core.http.g.c$7  reason: invalid class name */
    public class AnonymousClass7 implements Runnable {
        final /* synthetic */ SobotProgress a;
        final /* synthetic */ File b;

        AnonymousClass7(SobotProgress sobotProgress, File file) {
            this.a = sobotProgress;
            this.b = file;
        }

        @Override // java.lang.Runnable
        public void run() {
            for (b bVar : c.this.b.values()) {
                bVar.b(this.a);
                bVar.a(this.b, this.a);
            }
            a.a().c(this.a.tag);
        }
    }

    /* compiled from: SobotDownloadTask */
    /* access modifiers changed from: package-private */
    /* renamed from: com.sobot.chat.core.http.g.c$8  reason: invalid class name */
    public class AnonymousClass8 implements Runnable {
        final /* synthetic */ SobotProgress a;

        AnonymousClass8(SobotProgress sobotProgress) {
            this.a = sobotProgress;
        }

        @Override // java.lang.Runnable
        public void run() {
            for (b bVar : c.this.b.values()) {
                bVar.d(this.a);
            }
            c.this.b.clear();
        }
    }

    private void e(SobotProgress sobotProgress) {
        f(sobotProgress);
        com.sobot.chat.core.http.a.a((Runnable) new AnonymousClass8(sobotProgress));
    }

    private void f(SobotProgress sobotProgress) {
        a.a().a(SobotProgress.buildUpdateContentValues(sobotProgress), sobotProgress.tag);
    }
}
