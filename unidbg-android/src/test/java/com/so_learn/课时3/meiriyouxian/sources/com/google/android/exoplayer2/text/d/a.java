package com.google.android.exoplayer2.text.d;

import android.app.job.JobInfo;
import android.util.TimeUtils;
import com.google.android.exoplayer2.text.b;
import com.google.android.exoplayer2.util.i;
import com.google.android.exoplayer2.util.j;
import com.google.android.exoplayer2.util.o;
import com.google.android.exoplayer2.util.z;
import com.xiaomi.mipush.sdk.Constants;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* compiled from: SsaDecoder */
public final class a extends b {
    private static final Pattern a = Pattern.compile("(?:(\\d+):)?(\\d+):(\\d+)(?::|\\.)(\\d+)");
    private final boolean b;
    private int c;
    private int d;
    private int e;
    private int f;

    public a() {
        this(null);
    }

    public a(List<byte[]> list) {
        super("SsaDecoder");
        if (list == null || list.isEmpty()) {
            this.b = false;
            return;
        }
        this.b = true;
        String a2 = z.a(list.get(0));
        com.google.android.exoplayer2.util.a.a(a2.startsWith("Format: "));
        b(a2);
        a(new o(list.get(1)));
    }

    /* access modifiers changed from: protected */
    /* renamed from: b */
    public b a(byte[] bArr, int i, boolean z) {
        ArrayList arrayList = new ArrayList();
        j jVar = new j();
        o oVar = new o(bArr, i);
        if (!this.b) {
            a(oVar);
        }
        a(oVar, arrayList, jVar);
        com.google.android.exoplayer2.text.a[] aVarArr = new com.google.android.exoplayer2.text.a[arrayList.size()];
        arrayList.toArray(aVarArr);
        return new b(aVarArr, jVar.b());
    }

    private void a(o oVar) {
        String A;
        do {
            A = oVar.A();
            if (A == null) {
                return;
            }
        } while (!A.startsWith("[Events]"));
    }

    private void a(o oVar, List<com.google.android.exoplayer2.text.a> list, j jVar) {
        while (true) {
            String A = oVar.A();
            if (A == null) {
                return;
            }
            if (!this.b && A.startsWith("Format: ")) {
                b(A);
            } else if (A.startsWith("Dialogue: ")) {
                a(A, list, jVar);
            }
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:20:0x0061  */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x006c  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void b(java.lang.String r9) {
        /*
            r8 = this;
            r0 = 8
            java.lang.String r9 = r9.substring(r0)
            java.lang.String r0 = ","
            java.lang.String[] r9 = android.text.TextUtils.split(r9, r0)
            int r0 = r9.length
            r8.c = r0
            r0 = -1
            r8.d = r0
            r8.e = r0
            r8.f = r0
            r1 = 0
            r2 = r1
        L_0x0019:
            int r3 = r8.c
            if (r2 >= r3) goto L_0x0071
            r3 = r9[r2]
            java.lang.String r3 = r3.trim()
            java.lang.String r3 = com.google.android.exoplayer2.util.z.d(r3)
            int r4 = r3.hashCode()
            r5 = 100571(0x188db, float:1.4093E-40)
            r6 = 2
            r7 = 1
            if (r4 == r5) goto L_0x0053
            r5 = 3556653(0x36452d, float:4.983932E-39)
            if (r4 == r5) goto L_0x0048
            r5 = 109757538(0x68ac462, float:5.219839E-35)
            if (r4 == r5) goto L_0x003d
            goto L_0x005e
        L_0x003d:
            java.lang.String r4 = "start"
            boolean r3 = r3.equals(r4)
            if (r3 == 0) goto L_0x005e
            r3 = r1
            goto L_0x005f
        L_0x0048:
            java.lang.String r4 = "text"
            boolean r3 = r3.equals(r4)
            if (r3 == 0) goto L_0x005e
            r3 = r6
            goto L_0x005f
        L_0x0053:
            java.lang.String r4 = "end"
            boolean r3 = r3.equals(r4)
            if (r3 == 0) goto L_0x005e
            r3 = r7
            goto L_0x005f
        L_0x005e:
            r3 = r0
        L_0x005f:
            if (r3 == 0) goto L_0x006c
            if (r3 == r7) goto L_0x0069
            if (r3 == r6) goto L_0x0066
            goto L_0x006e
        L_0x0066:
            r8.f = r2
            goto L_0x006e
        L_0x0069:
            r8.e = r2
            goto L_0x006e
        L_0x006c:
            r8.d = r2
        L_0x006e:
            int r2 = r2 + 1
            goto L_0x0019
        L_0x0071:
            int r9 = r8.d
            if (r9 == r0) goto L_0x007d
            int r9 = r8.e
            if (r9 == r0) goto L_0x007d
            int r9 = r8.f
            if (r9 != r0) goto L_0x007f
        L_0x007d:
            r8.c = r1
        L_0x007f:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.text.d.a.b(java.lang.String):void");
    }

    private void a(String str, List<com.google.android.exoplayer2.text.a> list, j jVar) {
        long j;
        if (this.c == 0) {
            i.c("SsaDecoder", "Skipping dialogue line before complete format: " + str);
            return;
        }
        String[] split = str.substring(10).split(Constants.ACCEPT_TIME_SEPARATOR_SP, this.c);
        if (split.length != this.c) {
            i.c("SsaDecoder", "Skipping dialogue line with fewer columns than format: " + str);
            return;
        }
        long a2 = a(split[this.d]);
        if (a2 == -9223372036854775807L) {
            i.c("SsaDecoder", "Skipping invalid timing: " + str);
            return;
        }
        String str2 = split[this.e];
        if (!str2.trim().isEmpty()) {
            j = a(str2);
            if (j == -9223372036854775807L) {
                i.c("SsaDecoder", "Skipping invalid timing: " + str);
                return;
            }
        } else {
            j = -9223372036854775807L;
        }
        list.add(new com.google.android.exoplayer2.text.a(split[this.f].replaceAll("\\{.*?\\}", "").replaceAll("\\\\N", "\n").replaceAll("\\\\n", "\n")));
        jVar.a(a2);
        if (j != -9223372036854775807L) {
            list.add(null);
            jVar.a(j);
        }
    }

    public static long a(String str) {
        Matcher matcher = a.matcher(str);
        if (!matcher.matches()) {
            return -9223372036854775807L;
        }
        return (Long.parseLong(matcher.group(1)) * 60 * 60 * TimeUtils.NANOS_PER_MS) + (Long.parseLong(matcher.group(2)) * 60 * TimeUtils.NANOS_PER_MS) + (Long.parseLong(matcher.group(3)) * TimeUtils.NANOS_PER_MS) + (Long.parseLong(matcher.group(4)) * JobInfo.MIN_BACKOFF_MILLIS);
    }
}
