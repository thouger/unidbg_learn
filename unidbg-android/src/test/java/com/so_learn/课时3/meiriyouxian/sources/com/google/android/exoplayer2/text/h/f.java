package com.google.android.exoplayer2.text.h;

import android.net.wifi.WifiEnterpriseConfig;
import android.provider.BrowserContract;
import android.provider.Telephony;
import android.text.Layout;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.AlignmentSpan;
import android.text.style.BackgroundColorSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.StrikethroughSpan;
import android.text.style.StyleSpan;
import android.text.style.TypefaceSpan;
import android.text.style.UnderlineSpan;
import com.android.internal.app.DumpHeapActivity;
import com.google.android.exoplayer2.text.h.e;
import com.google.android.exoplayer2.util.i;
import com.google.android.exoplayer2.util.o;
import com.google.android.exoplayer2.util.z;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* compiled from: WebvttCueParser */
public final class f {
    public static final Pattern a = Pattern.compile("^(\\S+)\\s+-->\\s+(\\S+)(.*)?$");
    private static final Pattern b = Pattern.compile("(\\S+?):(\\S+)");
    private final StringBuilder c = new StringBuilder();

    public boolean a(o oVar, e.a aVar, List<d> list) {
        String A = oVar.A();
        if (A == null) {
            return false;
        }
        Matcher matcher = a.matcher(A);
        if (matcher.matches()) {
            return a(null, matcher, oVar, aVar, this.c, list);
        }
        String A2 = oVar.A();
        if (A2 == null) {
            return false;
        }
        Matcher matcher2 = a.matcher(A2);
        if (matcher2.matches()) {
            return a(A.trim(), matcher2, oVar, aVar, this.c, list);
        }
        return false;
    }

    static void a(String str, e.a aVar) {
        Matcher matcher = b.matcher(str);
        while (matcher.find()) {
            String group = matcher.group(1);
            String group2 = matcher.group(2);
            try {
                if ("line".equals(group)) {
                    b(group2, aVar);
                } else if ("align".equals(group)) {
                    aVar.a(b(group2));
                } else if (BrowserContract.Bookmarks.POSITION.equals(group)) {
                    c(group2, aVar);
                } else if (DumpHeapActivity.KEY_SIZE.equals(group)) {
                    aVar.c(h.b(group2));
                } else {
                    i.c("WebvttCueParser", "Unknown cue setting " + group + ":" + group2);
                }
            } catch (NumberFormatException unused) {
                i.c("WebvttCueParser", "Skipping bad cue setting: " + matcher.group());
            }
        }
    }

    static void a(String str, String str2, e.a aVar, List<d> list) {
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
        ArrayDeque arrayDeque = new ArrayDeque();
        ArrayList arrayList = new ArrayList();
        int i = 0;
        while (i < str2.length()) {
            char charAt = str2.charAt(i);
            if (charAt == '&') {
                i++;
                int indexOf = str2.indexOf(59, i);
                int indexOf2 = str2.indexOf(32, i);
                if (indexOf == -1) {
                    indexOf = indexOf2;
                } else if (indexOf2 != -1) {
                    indexOf = Math.min(indexOf, indexOf2);
                }
                if (indexOf != -1) {
                    a(str2.substring(i, indexOf), spannableStringBuilder);
                    if (indexOf == indexOf2) {
                        spannableStringBuilder.append(WifiEnterpriseConfig.CA_CERT_ALIAS_DELIMITER);
                    }
                    i = indexOf + 1;
                } else {
                    spannableStringBuilder.append(charAt);
                }
            } else if (charAt != '<') {
                spannableStringBuilder.append(charAt);
                i++;
            } else {
                int i2 = i + 1;
                if (i2 < str2.length()) {
                    int i3 = 1;
                    boolean z = str2.charAt(i2) == '/';
                    i2 = a(str2, i2);
                    int i4 = i2 - 2;
                    boolean z2 = str2.charAt(i4) == '/';
                    if (z) {
                        i3 = 2;
                    }
                    int i5 = i + i3;
                    if (!z2) {
                        i4 = i2 - 1;
                    }
                    String substring = str2.substring(i5, i4);
                    String d = d(substring);
                    if (d != null && c(d)) {
                        if (z) {
                            while (!arrayDeque.isEmpty()) {
                                a aVar2 = (a) arrayDeque.pop();
                                a(str, aVar2, spannableStringBuilder, list, arrayList);
                                if (aVar2.a.equals(d)) {
                                    break;
                                }
                            }
                        } else if (!z2) {
                            arrayDeque.push(a.a(substring, spannableStringBuilder.length()));
                        }
                    }
                }
                i = i2;
            }
        }
        while (!arrayDeque.isEmpty()) {
            a(str, (a) arrayDeque.pop(), spannableStringBuilder, list, arrayList);
        }
        a(str, a.a(), spannableStringBuilder, list, arrayList);
        aVar.a(spannableStringBuilder);
    }

    private static boolean a(String str, Matcher matcher, o oVar, e.a aVar, StringBuilder sb, List<d> list) {
        try {
            aVar.a(h.a(matcher.group(1))).b(h.a(matcher.group(2)));
            a(matcher.group(3), aVar);
            sb.setLength(0);
            while (true) {
                String A = oVar.A();
                if (!TextUtils.isEmpty(A)) {
                    if (sb.length() > 0) {
                        sb.append("\n");
                    }
                    sb.append(A.trim());
                } else {
                    a(str, sb.toString(), aVar, list);
                    return true;
                }
            }
        } catch (NumberFormatException unused) {
            i.c("WebvttCueParser", "Skipping cue with bad header: " + matcher.group());
            return false;
        }
    }

    private static void b(String str, e.a aVar) throws NumberFormatException {
        int indexOf = str.indexOf(44);
        if (indexOf != -1) {
            aVar.b(a(str.substring(indexOf + 1)));
            str = str.substring(0, indexOf);
        } else {
            aVar.b(Integer.MIN_VALUE);
        }
        if (str.endsWith("%")) {
            aVar.a(h.b(str)).a(0);
            return;
        }
        int parseInt = Integer.parseInt(str);
        if (parseInt < 0) {
            parseInt--;
        }
        aVar.a((float) parseInt).a(1);
    }

    private static void c(String str, e.a aVar) throws NumberFormatException {
        int indexOf = str.indexOf(44);
        if (indexOf != -1) {
            aVar.c(a(str.substring(indexOf + 1)));
            str = str.substring(0, indexOf);
        } else {
            aVar.c(Integer.MIN_VALUE);
        }
        aVar.b(h.b(str));
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    private static int a(String str) {
        char c;
        switch (str.hashCode()) {
            case -1364013995:
                if (str.equals("center")) {
                    c = 1;
                    break;
                }
                c = '\uffff';
                break;
            case -1074341483:
                if (str.equals("middle")) {
                    c = 2;
                    break;
                }
                c = '\uffff';
                break;
            case 100571:
                if (str.equals("end")) {
                    c = 3;
                    break;
                }
                c = '\uffff';
                break;
            case 109757538:
                if (str.equals(Telephony.BaseMmsColumns.START)) {
                    c = 0;
                    break;
                }
                c = '\uffff';
                break;
            default:
                c = '\uffff';
                break;
        }
        if (c == 0) {
            return 0;
        }
        if (c == 1 || c == 2) {
            return 1;
        }
        if (c == 3) {
            return 2;
        }
        i.c("WebvttCueParser", "Invalid anchor value: " + str);
        return Integer.MIN_VALUE;
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    private static Layout.Alignment b(String str) {
        char c;
        switch (str.hashCode()) {
            case -1364013995:
                if (str.equals("center")) {
                    c = 2;
                    break;
                }
                c = '\uffff';
                break;
            case -1074341483:
                if (str.equals("middle")) {
                    c = 3;
                    break;
                }
                c = '\uffff';
                break;
            case 100571:
                if (str.equals("end")) {
                    c = 4;
                    break;
                }
                c = '\uffff';
                break;
            case 3317767:
                if (str.equals("left")) {
                    c = 1;
                    break;
                }
                c = '\uffff';
                break;
            case 108511772:
                if (str.equals("right")) {
                    c = 5;
                    break;
                }
                c = '\uffff';
                break;
            case 109757538:
                if (str.equals(Telephony.BaseMmsColumns.START)) {
                    c = 0;
                    break;
                }
                c = '\uffff';
                break;
            default:
                c = '\uffff';
                break;
        }
        if (c == 0 || c == 1) {
            return Layout.Alignment.ALIGN_NORMAL;
        }
        if (c == 2 || c == 3) {
            return Layout.Alignment.ALIGN_CENTER;
        }
        if (c == 4 || c == 5) {
            return Layout.Alignment.ALIGN_OPPOSITE;
        }
        i.c("WebvttCueParser", "Invalid alignment value: " + str);
        return null;
    }

    private static int a(String str, int i) {
        int indexOf = str.indexOf(62, i);
        return indexOf == -1 ? str.length() : indexOf + 1;
    }

    /* JADX WARNING: Removed duplicated region for block: B:22:0x0049  */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x0080  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static void a(java.lang.String r5, android.text.SpannableStringBuilder r6) {
        /*
            int r0 = r5.hashCode()
            r1 = 3309(0xced, float:4.637E-42)
            r2 = 3
            r3 = 2
            r4 = 1
            if (r0 == r1) goto L_0x003b
            r1 = 3464(0xd88, float:4.854E-42)
            if (r0 == r1) goto L_0x0030
            r1 = 96708(0x179c4, float:1.35517E-40)
            if (r0 == r1) goto L_0x0025
            r1 = 3374865(0x337f11, float:4.729193E-39)
            if (r0 == r1) goto L_0x001a
            goto L_0x0046
        L_0x001a:
            java.lang.String r0 = "nbsp"
            boolean r0 = r5.equals(r0)
            if (r0 == 0) goto L_0x0046
            r0 = r3
            goto L_0x0047
        L_0x0025:
            java.lang.String r0 = "amp"
            boolean r0 = r5.equals(r0)
            if (r0 == 0) goto L_0x0046
            r0 = r2
            goto L_0x0047
        L_0x0030:
            java.lang.String r0 = "lt"
            boolean r0 = r5.equals(r0)
            if (r0 == 0) goto L_0x0046
            r0 = 0
            goto L_0x0047
        L_0x003b:
            java.lang.String r0 = "gt"
            boolean r0 = r5.equals(r0)
            if (r0 == 0) goto L_0x0046
            r0 = r4
            goto L_0x0047
        L_0x0046:
            r0 = -1
        L_0x0047:
            if (r0 == 0) goto L_0x0080
            if (r0 == r4) goto L_0x007a
            if (r0 == r3) goto L_0x0074
            if (r0 == r2) goto L_0x006e
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            r6.<init>()
            java.lang.String r0 = "ignoring unsupported entity: '&"
            r6.append(r0)
            r6.append(r5)
            java.lang.String r5 = ";'"
            r6.append(r5)
            java.lang.String r5 = r6.toString()
            java.lang.String r6 = "WebvttCueParser"
            com.google.android.exoplayer2.util.i.c(r6, r5)
            goto L_0x0085
        L_0x006e:
            r5 = 38
            r6.append(r5)
            goto L_0x0085
        L_0x0074:
            r5 = 32
            r6.append(r5)
            goto L_0x0085
        L_0x007a:
            r5 = 62
            r6.append(r5)
            goto L_0x0085
        L_0x0080:
            r5 = 60
            r6.append(r5)
        L_0x0085:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.text.h.f.a(java.lang.String, android.text.SpannableStringBuilder):void");
    }

    /* JADX WARNING: Removed duplicated region for block: B:32:0x0069 A[ADDED_TO_REGION] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static boolean c(java.lang.String r8) {
        /*
            int r0 = r8.hashCode()
            r1 = 98
            r2 = 0
            r3 = 5
            r4 = 4
            r5 = 3
            r6 = 2
            r7 = 1
            if (r0 == r1) goto L_0x005b
            r1 = 99
            if (r0 == r1) goto L_0x0050
            r1 = 105(0x69, float:1.47E-43)
            if (r0 == r1) goto L_0x0045
            r1 = 3314158(0x3291ee, float:4.644125E-39)
            if (r0 == r1) goto L_0x003a
            r1 = 117(0x75, float:1.64E-43)
            if (r0 == r1) goto L_0x002f
            r1 = 118(0x76, float:1.65E-43)
            if (r0 == r1) goto L_0x0024
            goto L_0x0066
        L_0x0024:
            java.lang.String r0 = "v"
            boolean r8 = r8.equals(r0)
            if (r8 == 0) goto L_0x0066
            r8 = r3
            goto L_0x0067
        L_0x002f:
            java.lang.String r0 = "u"
            boolean r8 = r8.equals(r0)
            if (r8 == 0) goto L_0x0066
            r8 = r4
            goto L_0x0067
        L_0x003a:
            java.lang.String r0 = "lang"
            boolean r8 = r8.equals(r0)
            if (r8 == 0) goto L_0x0066
            r8 = r5
            goto L_0x0067
        L_0x0045:
            java.lang.String r0 = "i"
            boolean r8 = r8.equals(r0)
            if (r8 == 0) goto L_0x0066
            r8 = r6
            goto L_0x0067
        L_0x0050:
            java.lang.String r0 = "c"
            boolean r8 = r8.equals(r0)
            if (r8 == 0) goto L_0x0066
            r8 = r7
            goto L_0x0067
        L_0x005b:
            java.lang.String r0 = "b"
            boolean r8 = r8.equals(r0)
            if (r8 == 0) goto L_0x0066
            r8 = r2
            goto L_0x0067
        L_0x0066:
            r8 = -1
        L_0x0067:
            if (r8 == 0) goto L_0x0074
            if (r8 == r7) goto L_0x0074
            if (r8 == r6) goto L_0x0074
            if (r8 == r5) goto L_0x0074
            if (r8 == r4) goto L_0x0074
            if (r8 == r3) goto L_0x0074
            return r2
        L_0x0074:
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.text.h.f.c(java.lang.String):boolean");
    }

    /* JADX WARNING: Removed duplicated region for block: B:38:0x007e A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x007f  */
    /* JADX WARNING: Removed duplicated region for block: B:40:0x0088  */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x0091  */
    /* JADX WARNING: Removed duplicated region for block: B:44:0x00a5 A[LOOP:0: B:43:0x00a3->B:44:0x00a5, LOOP_END] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static void a(java.lang.String r8, com.google.android.exoplayer2.text.h.f.a r9, android.text.SpannableStringBuilder r10, java.util.List<com.google.android.exoplayer2.text.h.d> r11, java.util.List<com.google.android.exoplayer2.text.h.f.b> r12) {
        /*
            int r0 = r9.b
            int r1 = r10.length()
            java.lang.String r2 = r9.a
            int r3 = r2.hashCode()
            r4 = 0
            r5 = 2
            r6 = 1
            if (r3 == 0) goto L_0x006d
            r7 = 105(0x69, float:1.47E-43)
            if (r3 == r7) goto L_0x0062
            r7 = 3314158(0x3291ee, float:4.644125E-39)
            if (r3 == r7) goto L_0x0057
            r7 = 98
            if (r3 == r7) goto L_0x004c
            r7 = 99
            if (r3 == r7) goto L_0x0041
            r7 = 117(0x75, float:1.64E-43)
            if (r3 == r7) goto L_0x0036
            r7 = 118(0x76, float:1.65E-43)
            if (r3 == r7) goto L_0x002b
            goto L_0x0078
        L_0x002b:
            java.lang.String r3 = "v"
            boolean r2 = r2.equals(r3)
            if (r2 == 0) goto L_0x0078
            r2 = 5
            goto L_0x0079
        L_0x0036:
            java.lang.String r3 = "u"
            boolean r2 = r2.equals(r3)
            if (r2 == 0) goto L_0x0078
            r2 = r5
            goto L_0x0079
        L_0x0041:
            java.lang.String r3 = "c"
            boolean r2 = r2.equals(r3)
            if (r2 == 0) goto L_0x0078
            r2 = 3
            goto L_0x0079
        L_0x004c:
            java.lang.String r3 = "b"
            boolean r2 = r2.equals(r3)
            if (r2 == 0) goto L_0x0078
            r2 = r4
            goto L_0x0079
        L_0x0057:
            java.lang.String r3 = "lang"
            boolean r2 = r2.equals(r3)
            if (r2 == 0) goto L_0x0078
            r2 = 4
            goto L_0x0079
        L_0x0062:
            java.lang.String r3 = "i"
            boolean r2 = r2.equals(r3)
            if (r2 == 0) goto L_0x0078
            r2 = r6
            goto L_0x0079
        L_0x006d:
            java.lang.String r3 = ""
            boolean r2 = r2.equals(r3)
            if (r2 == 0) goto L_0x0078
            r2 = 6
            goto L_0x0079
        L_0x0078:
            r2 = -1
        L_0x0079:
            r3 = 33
            switch(r2) {
                case 0: goto L_0x0091;
                case 1: goto L_0x0088;
                case 2: goto L_0x007f;
                case 3: goto L_0x0099;
                case 4: goto L_0x0099;
                case 5: goto L_0x0099;
                case 6: goto L_0x0099;
                default: goto L_0x007e;
            }
        L_0x007e:
            return
        L_0x007f:
            android.text.style.UnderlineSpan r2 = new android.text.style.UnderlineSpan
            r2.<init>()
            r10.setSpan(r2, r0, r1, r3)
            goto L_0x0099
        L_0x0088:
            android.text.style.StyleSpan r2 = new android.text.style.StyleSpan
            r2.<init>(r5)
            r10.setSpan(r2, r0, r1, r3)
            goto L_0x0099
        L_0x0091:
            android.text.style.StyleSpan r2 = new android.text.style.StyleSpan
            r2.<init>(r6)
            r10.setSpan(r2, r0, r1, r3)
        L_0x0099:
            r12.clear()
            a(r11, r8, r9, r12)
            int r8 = r12.size()
        L_0x00a3:
            if (r4 >= r8) goto L_0x00b3
            java.lang.Object r9 = r12.get(r4)
            com.google.android.exoplayer2.text.h.f$b r9 = (com.google.android.exoplayer2.text.h.f.b) r9
            com.google.android.exoplayer2.text.h.d r9 = r9.b
            a(r10, r9, r0, r1)
            int r4 = r4 + 1
            goto L_0x00a3
        L_0x00b3:
            return
            switch-data {0->0x0091, 1->0x0088, 2->0x007f, 3->0x0099, 4->0x0099, 5->0x0099, 6->0x0099, }
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.text.h.f.a(java.lang.String, com.google.android.exoplayer2.text.h.f$a, android.text.SpannableStringBuilder, java.util.List, java.util.List):void");
    }

    private static void a(SpannableStringBuilder spannableStringBuilder, d dVar, int i, int i2) {
        if (dVar != null) {
            if (dVar.b() != -1) {
                spannableStringBuilder.setSpan(new StyleSpan(dVar.b()), i, i2, 33);
            }
            if (dVar.c()) {
                spannableStringBuilder.setSpan(new StrikethroughSpan(), i, i2, 33);
            }
            if (dVar.d()) {
                spannableStringBuilder.setSpan(new UnderlineSpan(), i, i2, 33);
            }
            if (dVar.g()) {
                spannableStringBuilder.setSpan(new ForegroundColorSpan(dVar.f()), i, i2, 33);
            }
            if (dVar.i()) {
                spannableStringBuilder.setSpan(new BackgroundColorSpan(dVar.h()), i, i2, 33);
            }
            if (dVar.e() != null) {
                spannableStringBuilder.setSpan(new TypefaceSpan(dVar.e()), i, i2, 33);
            }
            if (dVar.j() != null) {
                spannableStringBuilder.setSpan(new AlignmentSpan.Standard(dVar.j()), i, i2, 33);
            }
            int k = dVar.k();
            if (k == 1) {
                spannableStringBuilder.setSpan(new AbsoluteSizeSpan((int) dVar.l(), true), i, i2, 33);
            } else if (k == 2) {
                spannableStringBuilder.setSpan(new RelativeSizeSpan(dVar.l()), i, i2, 33);
            } else if (k == 3) {
                spannableStringBuilder.setSpan(new RelativeSizeSpan(dVar.l() / 100.0f), i, i2, 33);
            }
        }
    }

    private static String d(String str) {
        String trim = str.trim();
        if (trim.isEmpty()) {
            return null;
        }
        return z.b(trim, "[ \\.]")[0];
    }

    private static void a(List<d> list, String str, a aVar, List<b> list2) {
        int size = list.size();
        for (int i = 0; i < size; i++) {
            d dVar = list.get(i);
            int a2 = dVar.a(str, aVar.a, aVar.d, aVar.c);
            if (a2 > 0) {
                list2.add(new b(a2, dVar));
            }
        }
        Collections.sort(list2);
    }

    /* compiled from: WebvttCueParser */
    /* access modifiers changed from: private */
    public static final class b implements Comparable<b> {
        public final int a;
        public final d b;

        public b(int i, d dVar) {
            this.a = i;
            this.b = dVar;
        }

        /* renamed from: a */
        public int compareTo(b bVar) {
            return this.a - bVar.a;
        }
    }

    /* compiled from: WebvttCueParser */
    /* access modifiers changed from: private */
    public static final class a {
        private static final String[] e = new String[0];
        public final String a;
        public final int b;
        public final String c;
        public final String[] d;

        private a(String str, int i, String str2, String[] strArr) {
            this.b = i;
            this.a = str;
            this.c = str2;
            this.d = strArr;
        }

        public static a a(String str, int i) {
            String str2;
            String[] strArr;
            String trim = str.trim();
            if (trim.isEmpty()) {
                return null;
            }
            int indexOf = trim.indexOf(WifiEnterpriseConfig.CA_CERT_ALIAS_DELIMITER);
            if (indexOf == -1) {
                str2 = "";
            } else {
                String trim2 = trim.substring(indexOf).trim();
                trim = trim.substring(0, indexOf);
                str2 = trim2;
            }
            String[] a = z.a(trim, "\\.");
            String str3 = a[0];
            if (a.length > 1) {
                strArr = (String[]) Arrays.copyOfRange(a, 1, a.length);
            } else {
                strArr = e;
            }
            return new a(str3, i, str2, strArr);
        }

        public static a a() {
            return new a("", 0, "", new String[0]);
        }
    }
}
