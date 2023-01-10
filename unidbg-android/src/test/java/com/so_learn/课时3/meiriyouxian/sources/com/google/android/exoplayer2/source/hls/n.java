package com.google.android.exoplayer2.source.hls;

import android.text.TextUtils;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.ParserException;
import com.google.android.exoplayer2.drm.DrmInitData;
import com.google.android.exoplayer2.extractor.g;
import com.google.android.exoplayer2.extractor.h;
import com.google.android.exoplayer2.extractor.i;
import com.google.android.exoplayer2.extractor.o;
import com.google.android.exoplayer2.extractor.q;
import com.google.android.exoplayer2.util.o;
import com.google.android.exoplayer2.util.w;
import java.io.IOException;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* compiled from: WebvttExtractor */
public final class n implements g {
    private static final Pattern a = Pattern.compile("LOCAL:([^,]+)");
    private static final Pattern b = Pattern.compile("MPEGTS:(\\d+)");
    private final String c;
    private final w d;
    private final o e = new o();
    private i f;
    private byte[] g = new byte[1024];
    private int h;

    @Override // com.google.android.exoplayer2.extractor.g
    public void c() {
    }

    public n(String str, w wVar) {
        this.c = str;
        this.d = wVar;
    }

    @Override // com.google.android.exoplayer2.extractor.g
    public boolean a(h hVar) throws IOException, InterruptedException {
        hVar.b(this.g, 0, 6, false);
        this.e.a(this.g, 6);
        if (com.google.android.exoplayer2.text.h.h.b(this.e)) {
            return true;
        }
        hVar.b(this.g, 6, 3, false);
        this.e.a(this.g, 9);
        return com.google.android.exoplayer2.text.h.h.b(this.e);
    }

    @Override // com.google.android.exoplayer2.extractor.g
    public void a(i iVar) {
        this.f = iVar;
        iVar.a(new o.b(-9223372036854775807L));
    }

    @Override // com.google.android.exoplayer2.extractor.g
    public void a(long j, long j2) {
        throw new IllegalStateException();
    }

    @Override // com.google.android.exoplayer2.extractor.g
    public int a(h hVar, com.google.android.exoplayer2.extractor.n nVar) throws IOException, InterruptedException {
        int i;
        int d = (int) hVar.d();
        int i2 = this.h;
        byte[] bArr = this.g;
        if (i2 == bArr.length) {
            if (d != -1) {
                i = d;
            } else {
                i = bArr.length;
            }
            this.g = Arrays.copyOf(bArr, (i * 3) / 2);
        }
        byte[] bArr2 = this.g;
        int i3 = this.h;
        int a2 = hVar.a(bArr2, i3, bArr2.length - i3);
        if (a2 != -1) {
            this.h += a2;
            if (d == -1 || this.h != d) {
                return 0;
            }
        }
        a();
        return -1;
    }

    private void a() throws ParserException {
        com.google.android.exoplayer2.util.o oVar = new com.google.android.exoplayer2.util.o(this.g);
        com.google.android.exoplayer2.text.h.h.a(oVar);
        long j = 0;
        long j2 = 0;
        while (true) {
            String A = oVar.A();
            if (TextUtils.isEmpty(A)) {
                Matcher c = com.google.android.exoplayer2.text.h.h.c(oVar);
                if (c == null) {
                    a(0);
                    return;
                }
                long a2 = com.google.android.exoplayer2.text.h.h.a(c.group(1));
                long b2 = this.d.b(w.e((j + a2) - j2));
                q a3 = a(b2 - a2);
                this.e.a(this.g, this.h);
                a3.a(this.e, this.h);
                a3.a(b2, 1, this.h, 0, null);
                return;
            } else if (A.startsWith("X-TIMESTAMP-MAP")) {
                Matcher matcher = a.matcher(A);
                if (matcher.find()) {
                    Matcher matcher2 = b.matcher(A);
                    if (matcher2.find()) {
                        j2 = com.google.android.exoplayer2.text.h.h.a(matcher.group(1));
                        j = w.d(Long.parseLong(matcher2.group(1)));
                    } else {
                        throw new ParserException("X-TIMESTAMP-MAP doesn't contain media timestamp: " + A);
                    }
                } else {
                    throw new ParserException("X-TIMESTAMP-MAP doesn't contain local timestamp: " + A);
                }
            }
        }
    }

    private q a(long j) {
        q a2 = this.f.a(0, 3);
        a2.a(Format.a((String) null, "text/vtt", (String) null, -1, 0, this.c, (DrmInitData) null, j));
        this.f.a();
        return a2;
    }
}
