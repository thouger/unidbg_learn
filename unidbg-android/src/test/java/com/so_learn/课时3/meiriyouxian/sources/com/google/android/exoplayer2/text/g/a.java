package com.google.android.exoplayer2.text.g;

import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.text.style.TypefaceSpan;
import android.text.style.UnderlineSpan;
import com.google.android.exoplayer2.text.SubtitleDecoderException;
import com.google.android.exoplayer2.text.b;
import com.google.android.exoplayer2.text.d;
import com.google.android.exoplayer2.util.o;
import com.google.android.exoplayer2.util.z;
import com.umeng.message.proguard.f;
import java.nio.charset.Charset;
import java.util.List;

/* compiled from: Tx3gDecoder */
public final class a extends b {
    private static final int a = z.h("styl");
    private static final int b = z.h("tbox");
    private final o c = new o();
    private boolean d;
    private int e;
    private int f;
    private String g;
    private float h;
    private int i;

    public a(List<byte[]> list) {
        super("Tx3gDecoder");
        a(list);
    }

    private void a(List<byte[]> list) {
        String str = "sans-serif";
        boolean z = false;
        if (list != null && list.size() == 1 && (list.get(0).length == 48 || list.get(0).length == 53)) {
            byte[] bArr = list.get(0);
            this.e = bArr[24];
            this.f = ((bArr[26] & 255) << 24) | ((bArr[27] & 255) << 16) | ((bArr[28] & 255) << 8) | (bArr[29] & 255);
            if ("Serif".equals(z.a(bArr, 43, bArr.length - 43))) {
                str = "serif";
            }
            this.g = str;
            this.i = bArr[25] * 20;
            if ((bArr[0] & 32) != 0) {
                z = true;
            }
            this.d = z;
            if (this.d) {
                this.h = ((float) ((bArr[11] & 255) | ((bArr[10] & 255) << 8))) / ((float) this.i);
                this.h = z.a(this.h, 0.0f, 0.95f);
                return;
            }
            this.h = 0.85f;
            return;
        }
        this.e = 0;
        this.f = -1;
        this.g = str;
        this.d = false;
        this.h = 0.85f;
    }

    /* access modifiers changed from: protected */
    @Override // com.google.android.exoplayer2.text.b
    public d a(byte[] bArr, int i, boolean z) throws SubtitleDecoderException {
        this.c.a(bArr, i);
        String a2 = a(this.c);
        if (a2.isEmpty()) {
            return b.a;
        }
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(a2);
        a(spannableStringBuilder, this.e, 0, 0, spannableStringBuilder.length(), (int) Spanned.SPAN_PRIORITY);
        b(spannableStringBuilder, this.f, -1, 0, spannableStringBuilder.length(), Spanned.SPAN_PRIORITY);
        a(spannableStringBuilder, this.g, "sans-serif", 0, spannableStringBuilder.length(), (int) Spanned.SPAN_PRIORITY);
        float f = this.h;
        while (this.c.b() >= 8) {
            int d = this.c.d();
            int p = this.c.p();
            int p2 = this.c.p();
            boolean z2 = true;
            if (p2 == a) {
                if (this.c.b() < 2) {
                    z2 = false;
                }
                a(z2);
                int i2 = this.c.i();
                for (int i3 = 0; i3 < i2; i3++) {
                    a(this.c, spannableStringBuilder);
                }
            } else if (p2 == b && this.d) {
                if (this.c.b() < 2) {
                    z2 = false;
                }
                a(z2);
                f = z.a(((float) this.c.i()) / ((float) this.i), 0.0f, 0.95f);
            }
            this.c.c(d + p);
        }
        return new b(new com.google.android.exoplayer2.text.a(spannableStringBuilder, null, f, 0, 0, Float.MIN_VALUE, Integer.MIN_VALUE, Float.MIN_VALUE));
    }

    private static String a(o oVar) throws SubtitleDecoderException {
        char g;
        a(oVar.b() >= 2);
        int i = oVar.i();
        if (i == 0) {
            return "";
        }
        if (oVar.b() < 2 || ((g = oVar.g()) != '\ufeff' && g != '\ufffe')) {
            return oVar.a(i, Charset.forName("UTF-8"));
        }
        return oVar.a(i, Charset.forName(f.c));
    }

    private void a(o oVar, SpannableStringBuilder spannableStringBuilder) throws SubtitleDecoderException {
        a(oVar.b() >= 12);
        int i = oVar.i();
        int i2 = oVar.i();
        oVar.d(2);
        int h = oVar.h();
        oVar.d(1);
        int p = oVar.p();
        a(spannableStringBuilder, h, this.e, i, i2, 0);
        b(spannableStringBuilder, p, this.f, i, i2, 0);
    }

    private static void a(SpannableStringBuilder spannableStringBuilder, int i, int i2, int i3, int i4, int i5) {
        if (i != i2) {
            int i6 = i5 | 33;
            boolean z = true;
            boolean z2 = (i & 1) != 0;
            boolean z3 = (i & 2) != 0;
            if (z2) {
                if (z3) {
                    spannableStringBuilder.setSpan(new StyleSpan(3), i3, i4, i6);
                } else {
                    spannableStringBuilder.setSpan(new StyleSpan(1), i3, i4, i6);
                }
            } else if (z3) {
                spannableStringBuilder.setSpan(new StyleSpan(2), i3, i4, i6);
            }
            if ((i & 4) == 0) {
                z = false;
            }
            if (z) {
                spannableStringBuilder.setSpan(new UnderlineSpan(), i3, i4, i6);
            }
            if (!z && !z2 && !z3) {
                spannableStringBuilder.setSpan(new StyleSpan(0), i3, i4, i6);
            }
        }
    }

    private static void b(SpannableStringBuilder spannableStringBuilder, int i, int i2, int i3, int i4, int i5) {
        if (i != i2) {
            spannableStringBuilder.setSpan(new ForegroundColorSpan((i >>> 8) | ((i & 255) << 24)), i3, i4, i5 | 33);
        }
    }

    private static void a(SpannableStringBuilder spannableStringBuilder, String str, String str2, int i, int i2, int i3) {
        if (str != str2) {
            spannableStringBuilder.setSpan(new TypefaceSpan(str), i, i2, i3 | 33);
        }
    }

    private static void a(boolean z) throws SubtitleDecoderException {
        if (!z) {
            throw new SubtitleDecoderException("Unexpected subtitle format.");
        }
    }
}
