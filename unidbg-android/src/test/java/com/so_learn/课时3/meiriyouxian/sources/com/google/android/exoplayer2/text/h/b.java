package com.google.android.exoplayer2.text.h;

import com.google.android.exoplayer2.text.SubtitleDecoderException;
import com.google.android.exoplayer2.text.a;
import com.google.android.exoplayer2.text.h.e;
import com.google.android.exoplayer2.util.o;
import com.google.android.exoplayer2.util.z;
import java.util.ArrayList;
import java.util.Collections;

/* compiled from: Mp4WebvttDecoder */
public final class b extends com.google.android.exoplayer2.text.b {
    private static final int a = z.h("payl");
    private static final int b = z.h("sttg");
    private static final int c = z.h("vttc");
    private final o d = new o();
    private final e.a e = new e.a();

    public b() {
        super("Mp4WebvttDecoder");
    }

    /* access modifiers changed from: protected */
    /* renamed from: b */
    public c a(byte[] bArr, int i, boolean z) throws SubtitleDecoderException {
        this.d.a(bArr, i);
        ArrayList arrayList = new ArrayList();
        while (this.d.b() > 0) {
            if (this.d.b() >= 8) {
                int p = this.d.p();
                if (this.d.p() == c) {
                    arrayList.add(a(this.d, this.e, p - 8));
                } else {
                    this.d.d(p - 8);
                }
            } else {
                throw new SubtitleDecoderException("Incomplete Mp4Webvtt Top Level box header found.");
            }
        }
        return new c(arrayList);
    }

    private static a a(o oVar, e.a aVar, int i) throws SubtitleDecoderException {
        aVar.a();
        while (i > 0) {
            if (i >= 8) {
                int p = oVar.p();
                int p2 = oVar.p();
                int i2 = p - 8;
                String a2 = z.a(oVar.a, oVar.d(), i2);
                oVar.d(i2);
                i = (i - 8) - i2;
                if (p2 == b) {
                    f.a(a2, aVar);
                } else if (p2 == a) {
                    f.a((String) null, a2.trim(), aVar, Collections.emptyList());
                }
            } else {
                throw new SubtitleDecoderException("Incomplete vtt cue box header found.");
            }
        }
        return aVar.b();
    }
}
