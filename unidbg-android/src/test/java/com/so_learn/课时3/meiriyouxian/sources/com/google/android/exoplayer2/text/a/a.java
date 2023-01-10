package com.google.android.exoplayer2.text.a;

import android.graphics.Color;
import android.mtp.MtpConstants;
import android.text.Layout;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.text.style.UnderlineSpan;
import com.android.internal.midi.MidiConstants;
import com.google.android.exoplayer2.text.SubtitleDecoderException;
import com.google.android.exoplayer2.text.d;
import com.google.android.exoplayer2.text.g;
import com.google.android.exoplayer2.text.h;
import com.google.android.exoplayer2.util.o;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* compiled from: Cea608Decoder */
public final class a extends e {
    private static final int[] a = {11, 1, 3, 12, 14, 5, 7, 9};
    private static final int[] b = {0, 4, 8, 12, 16, 20, 24, 28};
    private static final int[] c = {-1, Color.GREEN, Color.BLUE, Color.CYAN, -65536, -256, Color.MAGENTA};
    private static final int[] d = {32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 225, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63, 64, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 91, 233, 93, 237, 243, 250, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 231, 247, 209, 241, 9632};
    private static final int[] e = {174, 176, 189, 191, 8482, 162, 163, 9834, 224, 32, 232, 226, 234, 238, 244, 251};
    private static final int[] f = {193, 201, 211, 218, 220, 252, MtpConstants.RESPONSE_CAPTURE_ALREADY_TERMINATED, 161, 42, 39, 8212, 169, 8480, 8226, MtpConstants.RESPONSE_INVALID_DEVICE_PROP_VALUE, MtpConstants.RESPONSE_INVALID_PARAMETER, 192, 194, 199, 200, 202, 203, 235, 206, 207, 239, 212, 217, 249, 219, 171, 187};
    private static final int[] g = {195, 227, 205, 204, 236, 210, 242, 213, 245, 123, 125, 92, 94, 95, 124, 126, 196, 228, 214, 246, 223, 165, 164, 9474, 197, 229, 216, 248, 9484, 9488, 9492, 9496};
    private final o h = new o();
    private final int i;
    private final int j;
    private final ArrayList<C0095a> k = new ArrayList<>();
    private C0095a l = new C0095a(0, 4);
    private List<com.google.android.exoplayer2.text.a> m;
    private List<com.google.android.exoplayer2.text.a> n;
    private int o;
    private int p;
    private boolean q;
    private byte r;
    private byte s;

    private static boolean c(byte b2, byte b3) {
        return (b2 & MidiConstants.STATUS_END_SYSEX) == 17 && (b3 & 240) == 32;
    }

    private static boolean d(byte b2, byte b3) {
        return (b2 & 240) == 16 && (b3 & 192) == 64;
    }

    private static boolean e(byte b2, byte b3) {
        return (b2 & MidiConstants.STATUS_END_SYSEX) == 23 && b3 >= 33 && b3 <= 35;
    }

    private static boolean f(byte b2, byte b3) {
        return (b2 & MidiConstants.STATUS_END_SYSEX) == 20 && (b3 & 240) == 32;
    }

    private static boolean g(byte b2) {
        return (b2 & 240) == 16;
    }

    @Override // com.google.android.exoplayer2.text.a.e, com.google.android.exoplayer2.b.c
    public void d() {
    }

    @Override // com.google.android.exoplayer2.text.a.e, com.google.android.exoplayer2.text.e
    public /* bridge */ /* synthetic */ void a(long j) {
        super.a(j);
    }

    @Override // com.google.android.exoplayer2.text.a.e
    public /* bridge */ /* synthetic */ void b(g gVar) throws SubtitleDecoderException {
        super.a(gVar);
    }

    @Override // com.google.android.exoplayer2.text.a.e
    public /* bridge */ /* synthetic */ h g() throws SubtitleDecoderException {
        return super.b();
    }

    @Override // com.google.android.exoplayer2.text.a.e
    public /* bridge */ /* synthetic */ g h() throws SubtitleDecoderException {
        return super.a();
    }

    public a(String str, int i) {
        this.i = "application/x-mp4-cea-608".equals(str) ? 2 : 3;
        if (i == 3 || i == 4) {
            this.j = 2;
        } else {
            this.j = 1;
        }
        a(0);
        k();
    }

    @Override // com.google.android.exoplayer2.text.a.e, com.google.android.exoplayer2.b.c
    public void c() {
        super.c();
        this.m = null;
        this.n = null;
        a(0);
        b(4);
        k();
        this.q = false;
        this.r = 0;
        this.s = 0;
    }

    /* access modifiers changed from: protected */
    @Override // com.google.android.exoplayer2.text.a.e
    public boolean e() {
        return this.m != this.n;
    }

    /* access modifiers changed from: protected */
    @Override // com.google.android.exoplayer2.text.a.e
    public d f() {
        List<com.google.android.exoplayer2.text.a> list = this.m;
        this.n = list;
        return new f(list);
    }

    /* access modifiers changed from: protected */
    @Override // com.google.android.exoplayer2.text.a.e
    public void a(g gVar) {
        byte b2;
        this.h.a(gVar.b.array(), gVar.b.limit());
        boolean z = false;
        boolean z2 = false;
        while (true) {
            int b3 = this.h.b();
            int i = this.i;
            if (b3 < i) {
                break;
            }
            if (i == 2) {
                b2 = -4;
            } else {
                b2 = (byte) this.h.h();
            }
            byte h = (byte) (this.h.h() & 127);
            byte h2 = (byte) (this.h.h() & 127);
            if ((b2 & 6) == 4 && (this.j != 1 || (b2 & 1) == 0)) {
                if (this.j != 2 || (b2 & 1) == 1) {
                    if (h != 0 || h2 != 0) {
                        if ((h & MidiConstants.STATUS_END_SYSEX) == 17 && (h2 & 240) == 48) {
                            this.l.a(d(h2));
                        } else if ((h & MidiConstants.STATUS_TUNE_REQUEST) == 18 && (h2 & MidiConstants.STATUS_PITCH_BEND) == 32) {
                            this.l.b();
                            if ((h & 1) == 0) {
                                this.l.a(e(h2));
                            } else {
                                this.l.a(f(h2));
                            }
                        } else if ((h & MidiConstants.STATUS_PITCH_BEND) == 0) {
                            z2 = a(h, h2);
                        } else {
                            this.l.a(c(h));
                            if ((h2 & MidiConstants.STATUS_PITCH_BEND) != 0) {
                                this.l.a(c(h2));
                            }
                        }
                        z = true;
                    }
                }
            }
        }
        if (z) {
            if (!z2) {
                this.q = false;
            }
            int i2 = this.o;
            if (i2 == 1 || i2 == 3) {
                this.m = j();
            }
        }
    }

    private boolean a(byte b2, byte b3) {
        boolean g2 = g(b2);
        if (g2) {
            if (this.q && this.r == b2 && this.s == b3) {
                this.q = false;
                return true;
            }
            this.q = true;
            this.r = b2;
            this.s = b3;
        }
        if (c(b2, b3)) {
            a(b3);
        } else if (d(b2, b3)) {
            b(b2, b3);
        } else if (e(b2, b3)) {
            this.l.e(b3 + MidiConstants.STATUS_PITCH_BEND);
        } else if (f(b2, b3)) {
            b(b3);
        }
        return g2;
    }

    private void a(byte b2) {
        this.l.a(' ');
        this.l.a((b2 >> 1) & 7, (b2 & 1) == 1);
    }

    private void b(byte b2, byte b3) {
        int i = a[b2 & 7];
        boolean z = false;
        if ((b3 & 32) != 0) {
            i++;
        }
        if (i != this.l.c()) {
            if (this.o != 1 && !this.l.a()) {
                this.l = new C0095a(this.o, this.p);
                this.k.add(this.l);
            }
            this.l.c(i);
        }
        boolean z2 = (b3 & 16) == 16;
        if ((b3 & 1) == 1) {
            z = true;
        }
        int i2 = (b3 >> 1) & 7;
        this.l.a(z2 ? 8 : i2, z);
        if (z2) {
            this.l.d(b[i2]);
        }
    }

    private void b(byte b2) {
        if (b2 == 32) {
            a(2);
        } else if (b2 != 41) {
            switch (b2) {
                case 37:
                    a(1);
                    b(2);
                    return;
                case 38:
                    a(1);
                    b(3);
                    return;
                case 39:
                    a(1);
                    b(4);
                    return;
                default:
                    int i = this.o;
                    if (i != 0) {
                        if (b2 == 33) {
                            this.l.b();
                            return;
                        } else if (b2 != 36) {
                            switch (b2) {
                                case 44:
                                    this.m = Collections.emptyList();
                                    int i2 = this.o;
                                    if (i2 == 1 || i2 == 3) {
                                        k();
                                        return;
                                    }
                                    return;
                                case 45:
                                    if (i == 1 && !this.l.a()) {
                                        this.l.d();
                                        return;
                                    }
                                    return;
                                case 46:
                                    k();
                                    return;
                                case 47:
                                    this.m = j();
                                    k();
                                    return;
                                default:
                                    return;
                            }
                        } else {
                            return;
                        }
                    } else {
                        return;
                    }
            }
        } else {
            a(3);
        }
    }

    private List<com.google.android.exoplayer2.text.a> j() {
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < this.k.size(); i++) {
            com.google.android.exoplayer2.text.a f2 = this.k.get(i).f();
            if (f2 != null) {
                arrayList.add(f2);
            }
        }
        return arrayList;
    }

    private void a(int i) {
        int i2 = this.o;
        if (i2 != i) {
            this.o = i;
            k();
            if (i2 == 3 || i == 1 || i == 0) {
                this.m = Collections.emptyList();
            }
        }
    }

    private void b(int i) {
        this.p = i;
        this.l.b(i);
    }

    private void k() {
        this.l.a(this.o);
        this.k.clear();
        this.k.add(this.l);
    }

    private static char c(byte b2) {
        return (char) d[(b2 & Byte.MAX_VALUE) - 32];
    }

    private static char d(byte b2) {
        return (char) e[b2 & 15];
    }

    private static char e(byte b2) {
        return (char) f[b2 & 31];
    }

    private static char f(byte b2) {
        return (char) g[b2 & 31];
    }

    /* compiled from: Cea608Decoder */
    /* access modifiers changed from: private */
    /* renamed from: com.google.android.exoplayer2.text.a.a$a  reason: collision with other inner class name */
    public static class C0095a {
        private final List<C0096a> a = new ArrayList();
        private final List<SpannableString> b = new ArrayList();
        private final StringBuilder c = new StringBuilder();
        private int d;
        private int e;
        private int f;
        private int g;
        private int h;

        public C0095a(int i, int i2) {
            a(i);
            b(i2);
        }

        public void a(int i) {
            this.g = i;
            this.a.clear();
            this.b.clear();
            this.c.setLength(0);
            this.d = 15;
            this.e = 0;
            this.f = 0;
        }

        public void b(int i) {
            this.h = i;
        }

        public boolean a() {
            return this.a.isEmpty() && this.b.isEmpty() && this.c.length() == 0;
        }

        public void b() {
            int length = this.c.length();
            if (length > 0) {
                this.c.delete(length - 1, length);
                for (int size = this.a.size() - 1; size >= 0; size--) {
                    C0096a aVar = this.a.get(size);
                    if (aVar.c == length) {
                        aVar.c--;
                    } else {
                        return;
                    }
                }
            }
        }

        public int c() {
            return this.d;
        }

        public void c(int i) {
            this.d = i;
        }

        public void d() {
            this.b.add(e());
            this.c.setLength(0);
            this.a.clear();
            int min = Math.min(this.h, this.d);
            while (this.b.size() >= min) {
                this.b.remove(0);
            }
        }

        public void d(int i) {
            this.e = i;
        }

        public void e(int i) {
            this.f = i;
        }

        public void a(int i, boolean z) {
            this.a.add(new C0096a(i, z, this.c.length()));
        }

        public void a(char c) {
            this.c.append(c);
        }

        public SpannableString e() {
            SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(this.c);
            int length = spannableStringBuilder.length();
            int i = 0;
            int i2 = 0;
            boolean z = false;
            int i3 = -1;
            int i4 = -1;
            int i5 = -1;
            int i6 = -1;
            while (i < this.a.size()) {
                C0096a aVar = this.a.get(i);
                boolean z2 = aVar.b;
                int i7 = aVar.a;
                if (i7 != 8) {
                    boolean z3 = i7 == 7;
                    if (i7 != 7) {
                        i6 = a.c[i7];
                    }
                    z = z3;
                }
                int i8 = aVar.c;
                i++;
                if (i8 != (i < this.a.size() ? this.a.get(i).c : length)) {
                    if (i3 != -1 && !z2) {
                        a(spannableStringBuilder, i3, i8);
                        i3 = -1;
                    } else if (i3 == -1 && z2) {
                        i3 = i8;
                    }
                    if (i4 != -1 && !z) {
                        b(spannableStringBuilder, i4, i8);
                        i4 = -1;
                    } else if (i4 == -1 && z) {
                        i4 = i8;
                    }
                    if (i6 != i5) {
                        a(spannableStringBuilder, i2, i8, i5);
                        i5 = i6;
                        i2 = i8;
                    }
                }
            }
            if (!(i3 == -1 || i3 == length)) {
                a(spannableStringBuilder, i3, length);
            }
            if (!(i4 == -1 || i4 == length)) {
                b(spannableStringBuilder, i4, length);
            }
            if (i2 != length) {
                a(spannableStringBuilder, i2, length, i5);
            }
            return new SpannableString(spannableStringBuilder);
        }

        public com.google.android.exoplayer2.text.a f() {
            int i;
            float f;
            int i2;
            int i3;
            SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
            for (int i4 = 0; i4 < this.b.size(); i4++) {
                spannableStringBuilder.append((CharSequence) this.b.get(i4));
                spannableStringBuilder.append('\n');
            }
            spannableStringBuilder.append((CharSequence) e());
            if (spannableStringBuilder.length() == 0) {
                return null;
            }
            int i5 = this.e + this.f;
            int length = (32 - i5) - spannableStringBuilder.length();
            int i6 = i5 - length;
            if (this.g == 2 && (Math.abs(i6) < 3 || length < 0)) {
                f = 0.5f;
                i = 1;
            } else if (this.g != 2 || i6 <= 0) {
                i = 0;
                f = ((((float) i5) / 32.0f) * 0.8f) + 0.1f;
            } else {
                f = ((((float) (32 - length)) / 32.0f) * 0.8f) + 0.1f;
                i = 2;
            }
            if (this.g == 1 || (i3 = this.d) > 7) {
                i3 = (this.d - 15) - 2;
                i2 = 2;
            } else {
                i2 = 0;
            }
            return new com.google.android.exoplayer2.text.a(spannableStringBuilder, Layout.Alignment.ALIGN_NORMAL, (float) i3, 1, i2, f, i, Float.MIN_VALUE);
        }

        public String toString() {
            return this.c.toString();
        }

        private static void a(SpannableStringBuilder spannableStringBuilder, int i, int i2) {
            spannableStringBuilder.setSpan(new UnderlineSpan(), i, i2, 33);
        }

        private static void b(SpannableStringBuilder spannableStringBuilder, int i, int i2) {
            spannableStringBuilder.setSpan(new StyleSpan(2), i, i2, 33);
        }

        private static void a(SpannableStringBuilder spannableStringBuilder, int i, int i2, int i3) {
            if (i3 != -1) {
                spannableStringBuilder.setSpan(new ForegroundColorSpan(i3), i, i2, 33);
            }
        }

        /* compiled from: Cea608Decoder */
        /* access modifiers changed from: private */
        /* renamed from: com.google.android.exoplayer2.text.a.a$a$a  reason: collision with other inner class name */
        public static class C0096a {
            public final int a;
            public final boolean b;
            public int c;

            public C0096a(int i, boolean z, int i2) {
                this.a = i;
                this.b = z;
                this.c = i2;
            }
        }
    }
}
