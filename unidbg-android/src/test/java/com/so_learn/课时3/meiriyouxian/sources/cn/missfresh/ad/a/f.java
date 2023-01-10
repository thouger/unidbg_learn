package cn.missfresh.ad.a;

import android.media.AudioFormat;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import com.tencent.imsdk.BaseConstants;
import java.io.IOException;
import okhttp3.MediaType;
import okhttp3.ResponseBody;
import okio.Buffer;
import okio.BufferedSource;
import okio.ForwardingSource;
import okio.Okio;
import okio.Source;

/* compiled from: ProgressResponseBody */
public class f extends ResponseBody {
    private final ResponseBody a;
    private final g b;
    private BufferedSource c;

    public f(ResponseBody responseBody, g gVar) {
        this.a = responseBody;
        this.b = gVar;
    }

    public long contentLength() {
        AppMethodBeat.i(AudioFormat.CHANNEL_OUT_QUAD_SIDE, false);
        long contentLength = this.a.contentLength();
        AppMethodBeat.o(AudioFormat.CHANNEL_OUT_QUAD_SIDE);
        return contentLength;
    }

    public MediaType contentType() {
        AppMethodBeat.i(6165, false);
        MediaType contentType = this.a.contentType();
        AppMethodBeat.o(6165);
        return contentType;
    }

    public BufferedSource source() {
        AppMethodBeat.i(6169, false);
        if (this.c == null) {
            this.c = Okio.buffer(a((Source) this.a.source()));
        }
        BufferedSource bufferedSource = this.c;
        AppMethodBeat.o(6169);
        return bufferedSource;
    }

    /* compiled from: ProgressResponseBody */
    /* access modifiers changed from: package-private */
    /* renamed from: cn.missfresh.ad.a.f$1  reason: invalid class name */
    public class AnonymousClass1 extends ForwardingSource {
        long a = 0;

        AnonymousClass1(Source source) {
            super(source);
        }

        public long read(Buffer buffer, long j) throws IOException {
            boolean z = false;
            AppMethodBeat.i(BaseConstants.ERR_FRIENDSHIP_PROXY_NOT_SYNCED, false);
            long read = f.super.read(buffer, j);
            int i = (read > -1 ? 1 : (read == -1 ? 0 : -1));
            this.a += i != 0 ? read : 0;
            g gVar = f.this.b;
            long j2 = this.a;
            long contentLength = f.this.a.contentLength();
            if (i == 0) {
                z = true;
            }
            gVar.a(j2, contentLength, z);
            AppMethodBeat.o(BaseConstants.ERR_FRIENDSHIP_PROXY_NOT_SYNCED);
            return read;
        }
    }

    private Source a(Source source) {
        AppMethodBeat.i(6172, false);
        AnonymousClass1 r1 = new AnonymousClass1(source);
        AppMethodBeat.o(6172);
        return r1;
    }
}
