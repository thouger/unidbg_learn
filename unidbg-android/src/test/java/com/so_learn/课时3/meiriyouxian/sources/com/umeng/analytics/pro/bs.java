package com.umeng.analytics.pro;

import com.umeng.analytics.pro.bj;

/* compiled from: TProtocolUtil */
public class bs {
    private static int a = Integer.MAX_VALUE;

    public static void a(int i) {
        a = i;
    }

    public static void a(bp bpVar, byte b) throws aw {
        a(bpVar, b, a);
    }

    public static void a(bp bpVar, byte b, int i) throws aw {
        if (i > 0) {
            int i2 = 0;
            switch (b) {
                case 2:
                    bpVar.t();
                    return;
                case 3:
                    bpVar.u();
                    return;
                case 4:
                    bpVar.y();
                    return;
                case 5:
                case 7:
                case 9:
                default:
                    return;
                case 6:
                    bpVar.v();
                    return;
                case 8:
                    bpVar.w();
                    return;
                case 10:
                    bpVar.x();
                    return;
                case 11:
                    bpVar.A();
                    return;
                case 12:
                    bpVar.j();
                    while (true) {
                        bk l = bpVar.l();
                        if (l.b == 0) {
                            bpVar.k();
                            return;
                        } else {
                            a(bpVar, l.b, i - 1);
                            bpVar.m();
                        }
                    }
                case 13:
                    bm n = bpVar.n();
                    while (i2 < n.c) {
                        int i3 = i - 1;
                        a(bpVar, n.a, i3);
                        a(bpVar, n.b, i3);
                        i2++;
                    }
                    bpVar.o();
                    return;
                case 14:
                    bt r = bpVar.r();
                    while (i2 < r.b) {
                        a(bpVar, r.a, i - 1);
                        i2++;
                    }
                    bpVar.s();
                    return;
                case 15:
                    bl p = bpVar.p();
                    while (i2 < p.b) {
                        a(bpVar, p.a, i - 1);
                        i2++;
                    }
                    bpVar.q();
                    return;
            }
        } else {
            throw new aw("Maximum skip depth exceeded");
        }
    }

    public static br a(byte[] bArr, br brVar) {
        if (bArr[0] > 16) {
            return new bj.a();
        }
        return (bArr.length <= 1 || (bArr[1] & 128) == 0) ? brVar : new bj.a();
    }
}
