package com.google.android.exoplayer2.text.h;

import android.text.TextUtils;
import com.google.android.exoplayer2.util.d;
import com.google.android.exoplayer2.util.o;
import com.google.android.exoplayer2.util.z;
import com.umeng.message.proguard.l;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* compiled from: CssParser */
/* access modifiers changed from: package-private */
public final class a {
    private static final Pattern a = Pattern.compile("\\[voice=\"([^\"]*)\"\\]");
    private final o b = new o();
    private final StringBuilder c = new StringBuilder();

    public d a(o oVar) {
        this.c.setLength(0);
        int d = oVar.d();
        c(oVar);
        this.b.a(oVar.a, oVar.d());
        this.b.c(d);
        String b = b(this.b, this.c);
        if (b == null || !"{".equals(a(this.b, this.c))) {
            return null;
        }
        d dVar = new d();
        a(dVar, b);
        String str = null;
        boolean z = false;
        while (!z) {
            int d2 = this.b.d();
            str = a(this.b, this.c);
            boolean z2 = str == null || "}".equals(str);
            if (!z2) {
                this.b.c(d2);
                a(this.b, dVar, this.c);
            }
            z = z2;
        }
        if ("}".equals(str)) {
            return dVar;
        }
        return null;
    }

    private static String b(o oVar, StringBuilder sb) {
        b(oVar);
        if (oVar.b() < 5 || !"::cue".equals(oVar.e(5))) {
            return null;
        }
        int d = oVar.d();
        String a2 = a(oVar, sb);
        if (a2 == null) {
            return null;
        }
        if ("{".equals(a2)) {
            oVar.c(d);
            return "";
        }
        String d2 = l.s.equals(a2) ? d(oVar) : null;
        String a3 = a(oVar, sb);
        if (!l.t.equals(a3) || a3 == null) {
            return null;
        }
        return d2;
    }

    private static String d(o oVar) {
        int d = oVar.d();
        int c = oVar.c();
        boolean z = false;
        while (d < c && !z) {
            int i = d + 1;
            z = ((char) oVar.a[d]) == ')';
            d = i;
        }
        return oVar.e((d - 1) - oVar.d()).trim();
    }

    private static void a(o oVar, d dVar, StringBuilder sb) {
        b(oVar);
        String d = d(oVar, sb);
        if (!"".equals(d) && ":".equals(a(oVar, sb))) {
            b(oVar);
            String c = c(oVar, sb);
            if (c != null && !"".equals(c)) {
                int d2 = oVar.d();
                String a2 = a(oVar, sb);
                if (!";".equals(a2)) {
                    if ("}".equals(a2)) {
                        oVar.c(d2);
                    } else {
                        return;
                    }
                }
                if ("color".equals(d)) {
                    dVar.a(d.b(c));
                } else if ("background-color".equals(d)) {
                    dVar.b(d.b(c));
                } else if ("text-decoration".equals(d)) {
                    if ("underline".equals(c)) {
                        dVar.a(true);
                    }
                } else if ("font-family".equals(d)) {
                    dVar.d(c);
                } else if ("font-weight".equals(d)) {
                    if ("bold".equals(c)) {
                        dVar.b(true);
                    }
                } else if ("font-style".equals(d) && "italic".equals(c)) {
                    dVar.c(true);
                }
            }
        }
    }

    static void b(o oVar) {
        while (true) {
            boolean z = true;
            while (oVar.b() > 0 && z) {
                if (!e(oVar) && !f(oVar)) {
                    z = false;
                }
            }
            return;
        }
    }

    static String a(o oVar, StringBuilder sb) {
        b(oVar);
        if (oVar.b() == 0) {
            return null;
        }
        String d = d(oVar, sb);
        if (!"".equals(d)) {
            return d;
        }
        return "" + ((char) oVar.h());
    }

    private static boolean e(o oVar) {
        char a2 = a(oVar, oVar.d());
        if (a2 != '\t' && a2 != '\n' && a2 != '\f' && a2 != '\r' && a2 != ' ') {
            return false;
        }
        oVar.d(1);
        return true;
    }

    static void c(o oVar) {
        do {
        } while (!TextUtils.isEmpty(oVar.A()));
    }

    private static char a(o oVar, int i) {
        return (char) oVar.a[i];
    }

    private static String c(o oVar, StringBuilder sb) {
        StringBuilder sb2 = new StringBuilder();
        boolean z = false;
        while (!z) {
            int d = oVar.d();
            String a2 = a(oVar, sb);
            if (a2 == null) {
                return null;
            }
            if ("}".equals(a2) || ";".equals(a2)) {
                oVar.c(d);
                z = true;
            } else {
                sb2.append(a2);
            }
        }
        return sb2.toString();
    }

    private static boolean f(o oVar) {
        int d = oVar.d();
        int c = oVar.c();
        byte[] bArr = oVar.a;
        if (d + 2 > c) {
            return false;
        }
        int i = d + 1;
        if (bArr[d] != 47) {
            return false;
        }
        int i2 = i + 1;
        if (bArr[i] != 42) {
            return false;
        }
        while (true) {
            int i3 = i2 + 1;
            if (i3 >= c) {
                oVar.d(c - oVar.d());
                return true;
            } else if (((char) bArr[i2]) == '*' && ((char) bArr[i3]) == '/') {
                i2 = i3 + 1;
                c = i2;
            } else {
                i2 = i3;
            }
        }
    }

    private static String d(o oVar, StringBuilder sb) {
        boolean z = false;
        sb.setLength(0);
        int d = oVar.d();
        int c = oVar.c();
        while (d < c && !z) {
            char c2 = (char) oVar.a[d];
            if ((c2 < 'A' || c2 > 'Z') && ((c2 < 'a' || c2 > 'z') && !((c2 >= '0' && c2 <= '9') || c2 == '#' || c2 == '-' || c2 == '.' || c2 == '_'))) {
                z = true;
            } else {
                d++;
                sb.append(c2);
            }
        }
        oVar.d(d - oVar.d());
        return sb.toString();
    }

    private void a(d dVar, String str) {
        if (!"".equals(str)) {
            int indexOf = str.indexOf(91);
            if (indexOf != -1) {
                Matcher matcher = a.matcher(str.substring(indexOf));
                if (matcher.matches()) {
                    dVar.c(matcher.group(1));
                }
                str = str.substring(0, indexOf);
            }
            String[] a2 = z.a(str, "\\.");
            String str2 = a2[0];
            int indexOf2 = str2.indexOf(35);
            if (indexOf2 != -1) {
                dVar.b(str2.substring(0, indexOf2));
                dVar.a(str2.substring(indexOf2 + 1));
            } else {
                dVar.b(str2);
            }
            if (a2.length > 1) {
                dVar.a((String[]) Arrays.copyOfRange(a2, 1, a2.length));
            }
        }
    }
}
