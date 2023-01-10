package com.google.android.exoplayer2.text.a;

import com.google.android.exoplayer2.extractor.q;
import com.google.android.exoplayer2.util.i;
import com.google.android.exoplayer2.util.o;
import com.google.android.exoplayer2.util.z;

/* compiled from: CeaUtil */
public final class g {
    public static final int a = z.h("GA94");

    public static void a(long j, o oVar, q[] qVarArr) {
        while (true) {
            boolean z = true;
            if (oVar.b() > 1) {
                int a2 = a(oVar);
                int a3 = a(oVar);
                int d = oVar.d() + a3;
                if (a3 == -1 || a3 > oVar.b()) {
                    i.c("CeaUtil", "Skipping remainder of malformed SEI NAL unit.");
                    d = oVar.c();
                } else if (a2 == 4 && a3 >= 8) {
                    int h = oVar.h();
                    int i = oVar.i();
                    int p = i == 49 ? oVar.p() : 0;
                    int h2 = oVar.h();
                    if (i == 47) {
                        oVar.d(1);
                    }
                    boolean z2 = h == 181 && (i == 49 || i == 47) && h2 == 3;
                    if (i == 49) {
                        if (p != a) {
                            z = false;
                        }
                        z2 &= z;
                    }
                    if (z2) {
                        b(j, oVar, qVarArr);
                    }
                }
                oVar.c(d);
            } else {
                return;
            }
        }
    }

    public static void b(long j, o oVar, q[] qVarArr) {
        int h = oVar.h();
        if ((h & 64) != 0) {
            oVar.d(1);
            int i = (h & 31) * 3;
            int d = oVar.d();
            for (q qVar : qVarArr) {
                oVar.c(d);
                qVar.a(oVar, i);
                qVar.a(j, 1, i, 0, null);
            }
        }
    }

    private static int a(o oVar) {
        int i = 0;
        while (oVar.b() != 0) {
            int h = oVar.h();
            i += h;
            if (h != 255) {
                return i;
            }
        }
        return -1;
    }
}
