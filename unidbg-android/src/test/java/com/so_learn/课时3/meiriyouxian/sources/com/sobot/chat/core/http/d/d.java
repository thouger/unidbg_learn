package com.sobot.chat.core.http.d;

import com.sobot.chat.core.http.model.SobotProgress;
import java.io.IOException;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import okio.Buffer;
import okio.BufferedSink;
import okio.ForwardingSink;
import okio.Okio;
import okio.Sink;

/* compiled from: ProgressRequestBody */
public class d extends RequestBody {
    private RequestBody a;
    private com.sobot.chat.core.http.c.b b;
    private b c;

    /* compiled from: ProgressRequestBody */
    public interface b {
        void a(SobotProgress sobotProgress);
    }

    d(RequestBody requestBody, com.sobot.chat.core.http.c.b bVar) {
        this.a = requestBody;
        this.b = bVar;
    }

    public MediaType contentType() {
        return this.a.contentType();
    }

    public long contentLength() {
        try {
            return this.a.contentLength();
        } catch (IOException unused) {
            return -1;
        }
    }

    public void writeTo(BufferedSink bufferedSink) throws IOException {
        BufferedSink buffer = Okio.buffer(new a(bufferedSink));
        this.a.writeTo(buffer);
        buffer.flush();
    }

    /* compiled from: ProgressRequestBody */
    private final class a extends ForwardingSink {
        private SobotProgress b = new SobotProgress();

        a(Sink sink) {
            super(sink);
            this.b.totalSize = d.this.contentLength();
        }

        public void write(Buffer buffer, long j) throws IOException {
            d.super.write(buffer, j);
            SobotProgress.changeProgress(this.b, j, new AnonymousClass1());
        }

        /* compiled from: ProgressRequestBody */
        /* renamed from: com.sobot.chat.core.http.d.d$a$1  reason: invalid class name */
        class AnonymousClass1 implements SobotProgress.a {
            AnonymousClass1() {
            }

            @Override // com.sobot.chat.core.http.model.SobotProgress.a
            public void a(SobotProgress sobotProgress) {
                if (d.this.c != null) {
                    d.this.c.a(sobotProgress);
                } else {
                    d.this.a(sobotProgress);
                }
            }
        }
    }

    /* compiled from: ProgressRequestBody */
    /* access modifiers changed from: package-private */
    /* renamed from: com.sobot.chat.core.http.d.d$1  reason: invalid class name */
    public class AnonymousClass1 implements Runnable {
        final /* synthetic */ SobotProgress a;

        AnonymousClass1(SobotProgress sobotProgress) {
            this.a = sobotProgress;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (d.this.b != null) {
                d.this.b.a(this.a.fraction);
            }
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void a(SobotProgress sobotProgress) {
        com.sobot.chat.core.http.a.a((Runnable) new AnonymousClass1(sobotProgress));
    }

    public void a(b bVar) {
        this.c = bVar;
    }
}
