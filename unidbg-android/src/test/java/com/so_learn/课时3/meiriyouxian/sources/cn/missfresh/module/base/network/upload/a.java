package cn.missfresh.module.base.network.upload;

import com.bangcle.andjni.JniLib;
import java.io.IOException;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import okio.Buffer;
import okio.BufferedSink;
import okio.ForwardingSink;
import okio.Sink;

/* compiled from: ProgressRequestBody */
public class a extends RequestBody {
    private final RequestBody a;
    private final AbstractC0024a b;
    private BufferedSink c;

    /* compiled from: ProgressRequestBody */
    /* renamed from: cn.missfresh.module.base.network.upload.a$a  reason: collision with other inner class name */
    public interface AbstractC0024a {
        void a(long j, long j2, boolean z);
    }

    private Sink a(Sink sink) {
        return (Sink) JniLib.cL(this, sink, 198);
    }

    public long contentLength() throws IOException {
        return JniLib.cJ(this, 195);
    }

    public MediaType contentType() {
        return (MediaType) JniLib.cL(this, 196);
    }

    public void writeTo(BufferedSink bufferedSink) throws IOException {
        JniLib.cV(this, bufferedSink, 197);
    }

    public a(RequestBody requestBody, AbstractC0024a aVar) {
        this.a = requestBody;
        this.b = aVar;
    }

    /* compiled from: ProgressRequestBody */
    /* renamed from: cn.missfresh.module.base.network.upload.a$1  reason: invalid class name */
    class AnonymousClass1 extends ForwardingSink {
        long a = 0;
        long b = 0;

        public void write(Buffer buffer, long j) throws IOException {
            JniLib.cV(this, buffer, Long.valueOf(j), 194);
        }

        AnonymousClass1(Sink sink) {
            super(sink);
        }
    }
}
