package com.google.android.exoplayer2.text.h;

import android.text.TextUtils;
import com.google.android.exoplayer2.ParserException;
import com.google.android.exoplayer2.text.SubtitleDecoderException;
import com.google.android.exoplayer2.text.b;
import com.google.android.exoplayer2.text.h.e;
import com.google.android.exoplayer2.util.o;
import java.util.ArrayList;
import java.util.List;

/* compiled from: WebvttDecoder */
public final class g extends b {
    private final f a = new f();
    private final o b = new o();
    private final e.a c = new e.a();
    private final a d = new a();
    private final List<d> e = new ArrayList();

    public g() {
        super("WebvttDecoder");
    }

    /* access modifiers changed from: protected */
    /* renamed from: b */
    public i a(byte[] bArr, int i, boolean z) throws SubtitleDecoderException {
        this.b.a(bArr, i);
        this.c.a();
        this.e.clear();
        try {
            h.a(this.b);
            do {
            } while (!TextUtils.isEmpty(this.b.A()));
            ArrayList arrayList = new ArrayList();
            while (true) {
                int a = a(this.b);
                if (a == 0) {
                    return new i(arrayList);
                }
                if (a == 1) {
                    b(this.b);
                } else if (a == 2) {
                    if (arrayList.isEmpty()) {
                        this.b.A();
                        d a2 = this.d.a(this.b);
                        if (a2 != null) {
                            this.e.add(a2);
                        }
                    } else {
                        throw new SubtitleDecoderException("A style block was found after the first cue.");
                    }
                } else if (a == 3 && this.a.a(this.b, this.c, this.e)) {
                    arrayList.add(this.c.b());
                    this.c.a();
                }
            }
        } catch (ParserException e) {
            throw new SubtitleDecoderException(e);
        }
    }

    private static int a(o oVar) {
        int i = 0;
        int i2 = -1;
        while (i2 == -1) {
            i = oVar.d();
            String A = oVar.A();
            if (A == null) {
                i2 = 0;
            } else if ("STYLE".equals(A)) {
                i2 = 2;
            } else {
                i2 = A.startsWith("NOTE") ? 1 : 3;
            }
        }
        oVar.c(i);
        return i2;
    }

    private static void b(o oVar) {
        do {
        } while (!TextUtils.isEmpty(oVar.A()));
    }
}
