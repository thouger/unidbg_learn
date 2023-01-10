package com.google.android.exoplayer2.text.e;

import android.text.Html;
import android.text.Spanned;
import android.text.TextUtils;
import com.google.android.exoplayer2.text.b;
import com.google.android.exoplayer2.util.i;
import com.google.android.exoplayer2.util.j;
import com.google.android.exoplayer2.util.o;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* compiled from: SubripDecoder */
public final class a extends b {
    private static final Pattern a = Pattern.compile("\\s*((?:(\\d+):)?(\\d+):(\\d+),(\\d+))\\s*-->\\s*((?:(\\d+):)?(\\d+):(\\d+),(\\d+))?\\s*");
    private static final Pattern b = Pattern.compile("\\{\\\\.*?\\}");
    private final StringBuilder c = new StringBuilder();
    private final ArrayList<String> d = new ArrayList<>();

    static float b(int i) {
        if (i != 0) {
            return i != 1 ? 0.92f : 0.5f;
        }
        return 0.08f;
    }

    public a() {
        super("SubripDecoder");
    }

    /* access modifiers changed from: protected */
    /* renamed from: b */
    public b a(byte[] bArr, int i, boolean z) {
        String str;
        ArrayList arrayList = new ArrayList();
        j jVar = new j();
        o oVar = new o(bArr, i);
        while (true) {
            String A = oVar.A();
            if (A == null) {
                break;
            } else if (A.length() != 0) {
                try {
                    Integer.parseInt(A);
                    String A2 = oVar.A();
                    if (A2 == null) {
                        i.c("SubripDecoder", "Unexpected end");
                        break;
                    }
                    Matcher matcher = a.matcher(A2);
                    if (matcher.matches()) {
                        boolean z2 = true;
                        jVar.a(a(matcher, 1));
                        int i2 = 0;
                        if (!TextUtils.isEmpty(matcher.group(6))) {
                            jVar.a(a(matcher, 6));
                        } else {
                            z2 = false;
                        }
                        this.c.setLength(0);
                        this.d.clear();
                        while (true) {
                            String A3 = oVar.A();
                            if (TextUtils.isEmpty(A3)) {
                                break;
                            }
                            if (this.c.length() > 0) {
                                this.c.append("<br>");
                            }
                            this.c.append(a(A3, this.d));
                        }
                        Spanned fromHtml = Html.fromHtml(this.c.toString());
                        while (true) {
                            if (i2 >= this.d.size()) {
                                str = null;
                                break;
                            }
                            str = this.d.get(i2);
                            if (str.matches("\\{\\\\an[1-9]\\}")) {
                                break;
                            }
                            i2++;
                        }
                        arrayList.add(a(fromHtml, str));
                        if (z2) {
                            arrayList.add(null);
                        }
                    } else {
                        i.c("SubripDecoder", "Skipping invalid timing: " + A2);
                    }
                } catch (NumberFormatException unused) {
                    i.c("SubripDecoder", "Skipping invalid index: " + A);
                }
            }
        }
        com.google.android.exoplayer2.text.a[] aVarArr = new com.google.android.exoplayer2.text.a[arrayList.size()];
        arrayList.toArray(aVarArr);
        return new b(aVarArr, jVar.b());
    }

    private String a(String str, ArrayList<String> arrayList) {
        String trim = str.trim();
        StringBuilder sb = new StringBuilder(trim);
        Matcher matcher = b.matcher(trim);
        int i = 0;
        while (matcher.find()) {
            String group = matcher.group();
            arrayList.add(group);
            int start = matcher.start() - i;
            int length = group.length();
            sb.replace(start, start + length, "");
            i += length;
        }
        return sb.toString();
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    private com.google.android.exoplayer2.text.a a(Spanned spanned, String str) {
        char c;
        char c2;
        if (str == null) {
            return new com.google.android.exoplayer2.text.a(spanned);
        }
        switch (str.hashCode()) {
            case -685620710:
                if (str.equals("{\\an1}")) {
                    c = 0;
                    break;
                }
                c = '\uffff';
                break;
            case -685620679:
                if (str.equals("{\\an2}")) {
                    c = 6;
                    break;
                }
                c = '\uffff';
                break;
            case -685620648:
                if (str.equals("{\\an3}")) {
                    c = 3;
                    break;
                }
                c = '\uffff';
                break;
            case -685620617:
                if (str.equals("{\\an4}")) {
                    c = 1;
                    break;
                }
                c = '\uffff';
                break;
            case -685620586:
                if (str.equals("{\\an5}")) {
                    c = 7;
                    break;
                }
                c = '\uffff';
                break;
            case -685620555:
                if (str.equals("{\\an6}")) {
                    c = 4;
                    break;
                }
                c = '\uffff';
                break;
            case -685620524:
                if (str.equals("{\\an7}")) {
                    c = 2;
                    break;
                }
                c = '\uffff';
                break;
            case -685620493:
                if (str.equals("{\\an8}")) {
                    c = '\b';
                    break;
                }
                c = '\uffff';
                break;
            case -685620462:
                if (str.equals("{\\an9}")) {
                    c = 5;
                    break;
                }
                c = '\uffff';
                break;
            default:
                c = '\uffff';
                break;
        }
        int i = (c == 0 || c == 1 || c == 2) ? 0 : (c == 3 || c == 4 || c == 5) ? 2 : 1;
        switch (str.hashCode()) {
            case -685620710:
                if (str.equals("{\\an1}")) {
                    c2 = 0;
                    break;
                }
                c2 = '\uffff';
                break;
            case -685620679:
                if (str.equals("{\\an2}")) {
                    c2 = 1;
                    break;
                }
                c2 = '\uffff';
                break;
            case -685620648:
                if (str.equals("{\\an3}")) {
                    c2 = 2;
                    break;
                }
                c2 = '\uffff';
                break;
            case -685620617:
                if (str.equals("{\\an4}")) {
                    c2 = 6;
                    break;
                }
                c2 = '\uffff';
                break;
            case -685620586:
                if (str.equals("{\\an5}")) {
                    c2 = 7;
                    break;
                }
                c2 = '\uffff';
                break;
            case -685620555:
                if (str.equals("{\\an6}")) {
                    c2 = '\b';
                    break;
                }
                c2 = '\uffff';
                break;
            case -685620524:
                if (str.equals("{\\an7}")) {
                    c2 = 3;
                    break;
                }
                c2 = '\uffff';
                break;
            case -685620493:
                if (str.equals("{\\an8}")) {
                    c2 = 4;
                    break;
                }
                c2 = '\uffff';
                break;
            case -685620462:
                if (str.equals("{\\an9}")) {
                    c2 = 5;
                    break;
                }
                c2 = '\uffff';
                break;
            default:
                c2 = '\uffff';
                break;
        }
        int i2 = (c2 == 0 || c2 == 1 || c2 == 2) ? 2 : (c2 == 3 || c2 == 4 || c2 == 5) ? 0 : 1;
        return new com.google.android.exoplayer2.text.a(spanned, null, b(i2), 0, i2, b(i), i, Float.MIN_VALUE);
    }

    private static long a(Matcher matcher, int i) {
        return ((Long.parseLong(matcher.group(i + 1)) * 60 * 60 * 1000) + (Long.parseLong(matcher.group(i + 2)) * 60 * 1000) + (Long.parseLong(matcher.group(i + 3)) * 1000) + Long.parseLong(matcher.group(i + 4))) * 1000;
    }
}
