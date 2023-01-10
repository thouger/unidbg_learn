package com.google.android.exoplayer2.text.h;

import com.google.android.exoplayer2.ParserException;
import com.google.android.exoplayer2.util.o;
import com.google.android.exoplayer2.util.z;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* compiled from: WebvttParserUtil */
public final class h {
    private static final Pattern a = Pattern.compile("^NOTE(( |\t).*)?$");

    public static void a(o oVar) throws ParserException {
        int d = oVar.d();
        if (!b(oVar)) {
            oVar.c(d);
            throw new ParserException("Expected WEBVTT. Got " + oVar.A());
        }
    }

    public static boolean b(o oVar) {
        String A = oVar.A();
        return A != null && A.startsWith("WEBVTT");
    }

    public static long a(String str) throws NumberFormatException {
        String[] b = z.b(str, "\\.");
        long j = 0;
        for (String str2 : z.a(b[0], ":")) {
            j = (j * 60) + Long.parseLong(str2);
        }
        long j2 = j * 1000;
        if (b.length == 2) {
            j2 += Long.parseLong(b[1]);
        }
        return j2 * 1000;
    }

    public static float b(String str) throws NumberFormatException {
        if (str.endsWith("%")) {
            return Float.parseFloat(str.substring(0, str.length() - 1)) / 100.0f;
        }
        throw new NumberFormatException("Percentages must end with %");
    }

    public static Matcher c(o oVar) {
        String A;
        while (true) {
            String A2 = oVar.A();
            if (A2 == null) {
                return null;
            }
            if (a.matcher(A2).matches()) {
                do {
                    A = oVar.A();
                    if (A == null) {
                        break;
                    }
                } while (!A.isEmpty());
            } else {
                Matcher matcher = f.a.matcher(A2);
                if (matcher.matches()) {
                    return matcher;
                }
            }
        }
    }
}
