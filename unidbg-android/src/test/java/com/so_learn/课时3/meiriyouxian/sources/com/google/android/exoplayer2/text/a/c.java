package com.google.android.exoplayer2.text.a;

import android.net.wifi.WifiNetworkScoreCache;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.style.BackgroundColorSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.text.style.UnderlineSpan;
import com.google.android.exoplayer2.text.SubtitleDecoderException;
import com.google.android.exoplayer2.text.d;
import com.google.android.exoplayer2.text.g;
import com.google.android.exoplayer2.text.h;
import com.google.android.exoplayer2.util.i;
import com.google.android.exoplayer2.util.n;
import com.google.android.exoplayer2.util.o;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* compiled from: Cea708Decoder */
public final class c extends e {
    private final o a = new o();
    private final n b = new n();
    private final int c;
    private final a[] d;
    private a e;
    private List<com.google.android.exoplayer2.text.a> f;
    private List<com.google.android.exoplayer2.text.a> g;
    private b h;
    private int i;

    @Override // com.google.android.exoplayer2.text.a.e, com.google.android.exoplayer2.text.e
    public /* bridge */ /* synthetic */ void a(long j) {
        super.a(j);
    }

    @Override // com.google.android.exoplayer2.text.a.e
    public /* bridge */ /* synthetic */ void b(g gVar) throws SubtitleDecoderException {
        super.a(gVar);
    }

    @Override // com.google.android.exoplayer2.text.a.e, com.google.android.exoplayer2.b.c
    public /* bridge */ /* synthetic */ void d() {
        super.d();
    }

    @Override // com.google.android.exoplayer2.text.a.e
    public /* bridge */ /* synthetic */ h g() throws SubtitleDecoderException {
        return super.b();
    }

    @Override // com.google.android.exoplayer2.text.a.e
    public /* bridge */ /* synthetic */ g h() throws SubtitleDecoderException {
        return super.a();
    }

    public c(int i, List<byte[]> list) {
        this.c = i == -1 ? 1 : i;
        this.d = new a[8];
        for (int i2 = 0; i2 < 8; i2++) {
            this.d[i2] = new a();
        }
        this.e = this.d[0];
        p();
    }

    @Override // com.google.android.exoplayer2.text.a.e, com.google.android.exoplayer2.b.c
    public void c() {
        super.c();
        this.f = null;
        this.g = null;
        this.i = 0;
        this.e = this.d[this.i];
        p();
        this.h = null;
    }

    /* access modifiers changed from: protected */
    @Override // com.google.android.exoplayer2.text.a.e
    public boolean e() {
        return this.f != this.g;
    }

    /* access modifiers changed from: protected */
    @Override // com.google.android.exoplayer2.text.a.e
    public d f() {
        List<com.google.android.exoplayer2.text.a> list = this.f;
        this.g = list;
        return new f(list);
    }

    /* access modifiers changed from: protected */
    @Override // com.google.android.exoplayer2.text.a.e
    public void a(g gVar) {
        this.a.a(gVar.b.array(), gVar.b.limit());
        while (this.a.b() >= 3) {
            int h = this.a.h() & 7;
            int i = h & 3;
            boolean z = false;
            boolean z2 = (h & 4) == 4;
            byte h2 = (byte) this.a.h();
            byte h3 = (byte) this.a.h();
            if (i == 2 || i == 3) {
                if (z2) {
                    if (i == 3) {
                        i();
                        int i2 = (h2 & 192) >> 6;
                        int i3 = h2 & 63;
                        if (i3 == 0) {
                            i3 = 64;
                        }
                        this.h = new b(i2, i3);
                        byte[] bArr = this.h.c;
                        b bVar = this.h;
                        int i4 = bVar.d;
                        bVar.d = i4 + 1;
                        bArr[i4] = h3;
                    } else {
                        if (i == 2) {
                            z = true;
                        }
                        com.google.android.exoplayer2.util.a.a(z);
                        b bVar2 = this.h;
                        if (bVar2 == null) {
                            i.d("Cea708Decoder", "Encountered DTVCC_PACKET_DATA before DTVCC_PACKET_START");
                        } else {
                            byte[] bArr2 = bVar2.c;
                            b bVar3 = this.h;
                            int i5 = bVar3.d;
                            bVar3.d = i5 + 1;
                            bArr2[i5] = h2;
                            byte[] bArr3 = this.h.c;
                            b bVar4 = this.h;
                            int i6 = bVar4.d;
                            bVar4.d = i6 + 1;
                            bArr3[i6] = h3;
                        }
                    }
                    if (this.h.d == (this.h.b * 2) - 1) {
                        i();
                    }
                }
            }
        }
    }

    private void i() {
        if (this.h != null) {
            j();
            this.h = null;
        }
    }

    private void j() {
        if (this.h.d != (this.h.b * 2) - 1) {
            i.c("Cea708Decoder", "DtvCcPacket ended prematurely; size is " + ((this.h.b * 2) - 1) + ", but current index is " + this.h.d + " (sequence number " + this.h.a + "); ignoring packet");
            return;
        }
        this.b.a(this.h.c, this.h.d);
        int c = this.b.c(3);
        int c2 = this.b.c(5);
        if (c == 7) {
            this.b.b(2);
            c = this.b.c(6);
            if (c < 7) {
                i.c("Cea708Decoder", "Invalid extended service number: " + c);
            }
        }
        if (c2 == 0) {
            if (c != 0) {
                i.c("Cea708Decoder", "serviceNumber is non-zero (" + c + ") when blockSize is 0");
            }
        } else if (c == this.c) {
            boolean z = false;
            while (this.b.a() > 0) {
                int c3 = this.b.c(8);
                if (c3 == 16) {
                    int c4 = this.b.c(8);
                    if (c4 <= 31) {
                        c(c4);
                    } else if (c4 <= 127) {
                        g(c4);
                    } else if (c4 <= 159) {
                        d(c4);
                    } else if (c4 <= 255) {
                        h(c4);
                    } else {
                        i.c("Cea708Decoder", "Invalid extended command: " + c4);
                    }
                } else if (c3 <= 31) {
                    a(c3);
                } else if (c3 <= 127) {
                    e(c3);
                } else if (c3 <= 159) {
                    b(c3);
                } else if (c3 <= 255) {
                    f(c3);
                } else {
                    i.c("Cea708Decoder", "Invalid base command: " + c3);
                }
                z = true;
            }
            if (z) {
                this.f = o();
            }
        }
    }

    private void a(int i) {
        if (i == 0) {
            return;
        }
        if (i == 3) {
            this.f = o();
        } else if (i != 8) {
            switch (i) {
                case 12:
                    p();
                    return;
                case 13:
                    this.e.a('\n');
                    return;
                case 14:
                    return;
                default:
                    if (i >= 17 && i <= 23) {
                        i.c("Cea708Decoder", "Currently unsupported COMMAND_EXT1 Command: " + i);
                        this.b.b(8);
                        return;
                    } else if (i < 24 || i > 31) {
                        i.c("Cea708Decoder", "Invalid C0 command: " + i);
                        return;
                    } else {
                        i.c("Cea708Decoder", "Currently unsupported COMMAND_P16 Command: " + i);
                        this.b.b(16);
                        return;
                    }
            }
        } else {
            this.e.f();
        }
    }

    private void b(int i) {
        int i2 = 1;
        switch (i) {
            case 128:
            case 129:
            case 130:
            case 131:
            case 132:
            case 133:
            case 134:
            case 135:
                int i3 = i + WifiNetworkScoreCache.INVALID_NETWORK_SCORE;
                if (this.i != i3) {
                    this.i = i3;
                    this.e = this.d[i3];
                    return;
                }
                return;
            case 136:
                while (i2 <= 8) {
                    if (this.b.e()) {
                        this.d[8 - i2].c();
                    }
                    i2++;
                }
                return;
            case 137:
                for (int i4 = 1; i4 <= 8; i4++) {
                    if (this.b.e()) {
                        this.d[8 - i4].a(true);
                    }
                }
                return;
            case 138:
                while (i2 <= 8) {
                    if (this.b.e()) {
                        this.d[8 - i2].a(false);
                    }
                    i2++;
                }
                return;
            case 139:
                for (int i5 = 1; i5 <= 8; i5++) {
                    if (this.b.e()) {
                        a aVar = this.d[8 - i5];
                        aVar.a(!aVar.e());
                    }
                }
                return;
            case 140:
                while (i2 <= 8) {
                    if (this.b.e()) {
                        this.d[8 - i2].b();
                    }
                    i2++;
                }
                return;
            case 141:
                this.b.b(8);
                return;
            case 142:
                return;
            case 143:
                p();
                return;
            case 144:
                if (!this.e.d()) {
                    this.b.b(16);
                    return;
                } else {
                    k();
                    return;
                }
            case 145:
                if (!this.e.d()) {
                    this.b.b(24);
                    return;
                } else {
                    l();
                    return;
                }
            case 146:
                if (!this.e.d()) {
                    this.b.b(16);
                    return;
                } else {
                    m();
                    return;
                }
            case 147:
            case 148:
            case 149:
            case 150:
            default:
                i.c("Cea708Decoder", "Invalid C1 command: " + i);
                return;
            case 151:
                if (!this.e.d()) {
                    this.b.b(32);
                    return;
                } else {
                    n();
                    return;
                }
            case 152:
            case 153:
            case 154:
            case 155:
            case 156:
            case 157:
            case 158:
            case 159:
                int i6 = i - 152;
                i(i6);
                if (this.i != i6) {
                    this.i = i6;
                    this.e = this.d[i6];
                    return;
                }
                return;
        }
    }

    private void c(int i) {
        if (i > 7) {
            if (i <= 15) {
                this.b.b(8);
            } else if (i <= 23) {
                this.b.b(16);
            } else if (i <= 31) {
                this.b.b(24);
            }
        }
    }

    private void d(int i) {
        if (i <= 135) {
            this.b.b(32);
        } else if (i <= 143) {
            this.b.b(40);
        } else if (i <= 159) {
            this.b.b(2);
            this.b.b(this.b.c(6) * 8);
        }
    }

    private void e(int i) {
        if (i == 127) {
            this.e.a('\u266b');
        } else {
            this.e.a((char) (i & 255));
        }
    }

    private void f(int i) {
        this.e.a((char) (i & 255));
    }

    private void g(int i) {
        if (i == 32) {
            this.e.a(' ');
        } else if (i == 33) {
            this.e.a('\u00a0');
        } else if (i == 37) {
            this.e.a('\u2026');
        } else if (i == 42) {
            this.e.a('\u0160');
        } else if (i == 44) {
            this.e.a('\u0152');
        } else if (i == 63) {
            this.e.a('\u0178');
        } else if (i == 57) {
            this.e.a('\u2122');
        } else if (i == 58) {
            this.e.a('\u0161');
        } else if (i == 60) {
            this.e.a('\u0153');
        } else if (i != 61) {
            switch (i) {
                case 48:
                    this.e.a('\u2588');
                    return;
                case 49:
                    this.e.a('\u2018');
                    return;
                case 50:
                    this.e.a('\u2019');
                    return;
                case 51:
                    this.e.a('\u201c');
                    return;
                case 52:
                    this.e.a('\u201d');
                    return;
                case 53:
                    this.e.a('\u2022');
                    return;
                default:
                    switch (i) {
                        case 118:
                            this.e.a('\u215b');
                            return;
                        case 119:
                            this.e.a('\u215c');
                            return;
                        case 120:
                            this.e.a('\u215d');
                            return;
                        case 121:
                            this.e.a('\u215e');
                            return;
                        case 122:
                            this.e.a('\u2502');
                            return;
                        case 123:
                            this.e.a('\u2510');
                            return;
                        case 124:
                            this.e.a('\u2514');
                            return;
                        case 125:
                            this.e.a('\u2500');
                            return;
                        case 126:
                            this.e.a('\u2518');
                            return;
                        case 127:
                            this.e.a('\u250c');
                            return;
                        default:
                            i.c("Cea708Decoder", "Invalid G2 character: " + i);
                            return;
                    }
            }
        } else {
            this.e.a('\u2120');
        }
    }

    private void h(int i) {
        if (i == 160) {
            this.e.a('\u33c4');
            return;
        }
        i.c("Cea708Decoder", "Invalid G3 character: " + i);
        this.e.a('_');
    }

    private void k() {
        this.e.a(this.b.c(4), this.b.c(2), this.b.c(2), this.b.e(), this.b.e(), this.b.c(3), this.b.c(3));
    }

    private void l() {
        int a2 = a.a(this.b.c(2), this.b.c(2), this.b.c(2), this.b.c(2));
        int a3 = a.a(this.b.c(2), this.b.c(2), this.b.c(2), this.b.c(2));
        this.b.b(2);
        this.e.a(a2, a3, a.b(this.b.c(2), this.b.c(2), this.b.c(2)));
    }

    private void m() {
        this.b.b(4);
        int c = this.b.c(4);
        this.b.b(2);
        this.e.a(c, this.b.c(6));
    }

    private void n() {
        int a2 = a.a(this.b.c(2), this.b.c(2), this.b.c(2), this.b.c(2));
        int c = this.b.c(2);
        int b2 = a.b(this.b.c(2), this.b.c(2), this.b.c(2));
        if (this.b.e()) {
            c |= 4;
        }
        boolean e = this.b.e();
        int c2 = this.b.c(2);
        int c3 = this.b.c(2);
        int c4 = this.b.c(2);
        this.b.b(8);
        this.e.a(a2, b2, e, c, c2, c3, c4);
    }

    private void i(int i) {
        a aVar = this.d[i];
        this.b.b(2);
        boolean e = this.b.e();
        boolean e2 = this.b.e();
        boolean e3 = this.b.e();
        int c = this.b.c(3);
        boolean e4 = this.b.e();
        int c2 = this.b.c(7);
        int c3 = this.b.c(8);
        int c4 = this.b.c(4);
        int c5 = this.b.c(4);
        this.b.b(2);
        int c6 = this.b.c(6);
        this.b.b(2);
        aVar.a(e, e2, e3, c, e4, c2, c3, c5, c6, c4, this.b.c(3), this.b.c(3));
    }

    private List<com.google.android.exoplayer2.text.a> o() {
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < 8; i++) {
            if (!this.d[i].a() && this.d[i].e()) {
                arrayList.add(this.d[i].h());
            }
        }
        Collections.sort(arrayList);
        return Collections.unmodifiableList(arrayList);
    }

    private void p() {
        for (int i = 0; i < 8; i++) {
            this.d[i].b();
        }
    }

    /* compiled from: Cea708Decoder */
    /* access modifiers changed from: private */
    public static final class b {
        public final int a;
        public final int b;
        public final byte[] c;
        int d = 0;

        public b(int i, int i2) {
            this.a = i;
            this.b = i2;
            this.c = new byte[((i2 * 2) - 1)];
        }
    }

    /* compiled from: Cea708Decoder */
    /* access modifiers changed from: private */
    public static final class a {
        public static final int a = a(2, 2, 2, 0);
        public static final int b = a(0, 0, 0, 0);
        public static final int c = a(0, 0, 0, 3);
        private static final int[] d = {0, 0, 0, 0, 0, 2, 0};
        private static final int[] e = {0, 0, 0, 0, 0, 0, 2};
        private static final int[] f = {3, 3, 3, 3, 3, 3, 1};
        private static final boolean[] g = {false, false, false, true, true, true, false};
        private static final int[] h;
        private static final int[] i = {0, 1, 2, 3, 4, 3, 4};
        private static final int[] j = {0, 0, 0, 0, 0, 3, 3};
        private static final int[] k;
        private int A;
        private int B;
        private int C;
        private int D;
        private int E;
        private int F;
        private int G;
        private final List<SpannableString> l = new ArrayList();
        private final SpannableStringBuilder m = new SpannableStringBuilder();
        private boolean n;
        private boolean o;
        private int p;
        private boolean q;
        private int r;
        private int s;
        private int t;
        private int u;
        private boolean v;
        private int w;
        private int x;
        private int y;
        private int z;

        static {
            int i2 = b;
            int i3 = c;
            h = new int[]{i2, i3, i2, i2, i3, i2, i2};
            k = new int[]{i2, i2, i2, i2, i2, i3, i3};
        }

        public a() {
            b();
        }

        public boolean a() {
            return !d() || (this.l.isEmpty() && this.m.length() == 0);
        }

        public void b() {
            c();
            this.n = false;
            this.o = false;
            this.p = 4;
            this.q = false;
            this.r = 0;
            this.s = 0;
            this.t = 0;
            this.u = 15;
            this.v = true;
            this.w = 0;
            this.x = 0;
            this.y = 0;
            int i2 = b;
            this.z = i2;
            this.D = a;
            this.F = i2;
        }

        public void c() {
            this.l.clear();
            this.m.clear();
            this.A = -1;
            this.B = -1;
            this.C = -1;
            this.E = -1;
            this.G = 0;
        }

        public boolean d() {
            return this.n;
        }

        public void a(boolean z) {
            this.o = z;
        }

        public boolean e() {
            return this.o;
        }

        public void a(boolean z, boolean z2, boolean z3, int i2, boolean z4, int i3, int i4, int i5, int i6, int i7, int i8, int i9) {
            this.n = true;
            this.o = z;
            this.v = z2;
            this.p = i2;
            this.q = z4;
            this.r = i3;
            this.s = i4;
            this.t = i7;
            int i10 = i5 + 1;
            if (this.u != i10) {
                this.u = i10;
                while (true) {
                    if ((!z2 || this.l.size() < this.u) && this.l.size() < 15) {
                        break;
                    }
                    this.l.remove(0);
                }
            }
            if (!(i8 == 0 || this.x == i8)) {
                this.x = i8;
                int i11 = i8 - 1;
                a(h[i11], c, g[i11], 0, e[i11], f[i11], d[i11]);
            }
            if (i9 != 0 && this.y != i9) {
                this.y = i9;
                int i12 = i9 - 1;
                a(0, 1, 1, false, false, j[i12], i[i12]);
                a(a, k[i12], b);
            }
        }

        public void a(int i2, int i3, boolean z, int i4, int i5, int i6, int i7) {
            this.z = i2;
            this.w = i7;
        }

        public void a(int i2, int i3, int i4, boolean z, boolean z2, int i5, int i6) {
            if (this.A != -1) {
                if (!z) {
                    this.m.setSpan(new StyleSpan(2), this.A, this.m.length(), 33);
                    this.A = -1;
                }
            } else if (z) {
                this.A = this.m.length();
            }
            if (this.B != -1) {
                if (!z2) {
                    this.m.setSpan(new UnderlineSpan(), this.B, this.m.length(), 33);
                    this.B = -1;
                }
            } else if (z2) {
                this.B = this.m.length();
            }
        }

        public void a(int i2, int i3, int i4) {
            int i5;
            int i6;
            if (!(this.C == -1 || (i6 = this.D) == i2)) {
                this.m.setSpan(new ForegroundColorSpan(i6), this.C, this.m.length(), 33);
            }
            if (i2 != a) {
                this.C = this.m.length();
                this.D = i2;
            }
            if (!(this.E == -1 || (i5 = this.F) == i3)) {
                this.m.setSpan(new BackgroundColorSpan(i5), this.E, this.m.length(), 33);
            }
            if (i3 != b) {
                this.E = this.m.length();
                this.F = i3;
            }
        }

        public void a(int i2, int i3) {
            if (this.G != i2) {
                a('\n');
            }
            this.G = i2;
        }

        public void f() {
            int length = this.m.length();
            if (length > 0) {
                this.m.delete(length - 1, length);
            }
        }

        public void a(char c2) {
            if (c2 == '\n') {
                this.l.add(g());
                this.m.clear();
                if (this.A != -1) {
                    this.A = 0;
                }
                if (this.B != -1) {
                    this.B = 0;
                }
                if (this.C != -1) {
                    this.C = 0;
                }
                if (this.E != -1) {
                    this.E = 0;
                }
                while (true) {
                    if ((this.v && this.l.size() >= this.u) || this.l.size() >= 15) {
                        this.l.remove(0);
                    } else {
                        return;
                    }
                }
            } else {
                this.m.append(c2);
            }
        }

        public SpannableString g() {
            SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(this.m);
            int length = spannableStringBuilder.length();
            if (length > 0) {
                if (this.A != -1) {
                    spannableStringBuilder.setSpan(new StyleSpan(2), this.A, length, 33);
                }
                if (this.B != -1) {
                    spannableStringBuilder.setSpan(new UnderlineSpan(), this.B, length, 33);
                }
                if (this.C != -1) {
                    spannableStringBuilder.setSpan(new ForegroundColorSpan(this.D), this.C, length, 33);
                }
                if (this.E != -1) {
                    spannableStringBuilder.setSpan(new BackgroundColorSpan(this.F), this.E, length, 33);
                }
            }
            return new SpannableString(spannableStringBuilder);
        }

        /* JADX WARNING: Removed duplicated region for block: B:19:0x0066  */
        /* JADX WARNING: Removed duplicated region for block: B:20:0x0071  */
        /* JADX WARNING: Removed duplicated region for block: B:23:0x0090  */
        /* JADX WARNING: Removed duplicated region for block: B:24:0x0092  */
        /* JADX WARNING: Removed duplicated region for block: B:30:0x009e  */
        /* JADX WARNING: Removed duplicated region for block: B:31:0x00a0  */
        /* JADX WARNING: Removed duplicated region for block: B:37:0x00ac  */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public com.google.android.exoplayer2.text.a.b h() {
            /*
            // Method dump skipped, instructions count: 195
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.text.a.c.a.h():com.google.android.exoplayer2.text.a.b");
        }

        public static int b(int i2, int i3, int i4) {
            return a(i2, i3, i4, 0);
        }

        /* JADX WARNING: Removed duplicated region for block: B:11:0x0023  */
        /* JADX WARNING: Removed duplicated region for block: B:12:0x0025  */
        /* JADX WARNING: Removed duplicated region for block: B:14:0x0028  */
        /* JADX WARNING: Removed duplicated region for block: B:15:0x002a  */
        /* JADX WARNING: Removed duplicated region for block: B:17:0x002d  */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public static int a(int r4, int r5, int r6, int r7) {
            /*
                r0 = 4
                r1 = 0
                com.google.android.exoplayer2.util.a.a(r4, r1, r0)
                com.google.android.exoplayer2.util.a.a(r5, r1, r0)
                com.google.android.exoplayer2.util.a.a(r6, r1, r0)
                com.google.android.exoplayer2.util.a.a(r7, r1, r0)
                r0 = 1
                r2 = 255(0xff, float:3.57E-43)
                if (r7 == 0) goto L_0x001b
                if (r7 == r0) goto L_0x001b
                r3 = 2
                if (r7 == r3) goto L_0x001f
                r3 = 3
                if (r7 == r3) goto L_0x001d
            L_0x001b:
                r7 = r2
                goto L_0x0021
            L_0x001d:
                r7 = r1
                goto L_0x0021
            L_0x001f:
                r7 = 127(0x7f, float:1.78E-43)
            L_0x0021:
                if (r4 <= r0) goto L_0x0025
                r4 = r2
                goto L_0x0026
            L_0x0025:
                r4 = r1
            L_0x0026:
                if (r5 <= r0) goto L_0x002a
                r5 = r2
                goto L_0x002b
            L_0x002a:
                r5 = r1
            L_0x002b:
                if (r6 <= r0) goto L_0x002e
                r1 = r2
            L_0x002e:
                int r4 = android.graphics.Color.argb(r7, r4, r5, r1)
                return r4
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.text.a.c.a.a(int, int, int, int):int");
        }
    }
}
