package com.vivo.push;

import android.content.Intent;
import android.text.TextUtils;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import com.taobao.accs.common.Constants;
import com.vivo.push.b.i;
import com.vivo.push.b.j;
import com.vivo.push.b.k;
import com.vivo.push.b.l;
import com.vivo.push.b.m;
import com.vivo.push.b.n;
import com.vivo.push.b.o;
import com.vivo.push.b.p;
import com.vivo.push.b.q;
import com.vivo.push.b.r;
import com.vivo.push.b.t;
import com.vivo.push.b.u;
import com.vivo.push.c.d;
import com.vivo.push.c.f;
import com.vivo.push.c.g;
import com.vivo.push.c.h;
import com.vivo.push.c.s;

/* compiled from: PushClientFactory */
public final class c implements IPushClientFactory {
    private s a = new s();

    public c() {
        AppMethodBeat.i(2062, false);
        AppMethodBeat.o(2062);
    }

    @Override // com.vivo.push.IPushClientFactory
    public final g createReceiverCommand(Intent intent) {
        g gVar;
        AppMethodBeat.i(2063, false);
        int intExtra = intent.getIntExtra(Constants.KEY_COMMAND, -1);
        if (intExtra < 0) {
            intExtra = intent.getIntExtra("method", -1);
        }
        if (intExtra == 20) {
            gVar = new u();
        } else if (intExtra != 2016) {
            switch (intExtra) {
                case 1:
                case 2:
                    gVar = new t(intExtra);
                    break;
                case 3:
                    gVar = new o();
                    break;
                case 4:
                    gVar = new q();
                    break;
                case 5:
                    gVar = new p();
                    break;
                case 6:
                    gVar = new r();
                    break;
                case 7:
                    gVar = new n();
                    break;
                case 8:
                    gVar = new m();
                    break;
                case 9:
                    gVar = new k();
                    break;
                case 10:
                case 11:
                    gVar = new i(intExtra);
                    break;
                case 12:
                    gVar = new j();
                    break;
                default:
                    gVar = null;
                    break;
            }
        } else {
            gVar = new l();
        }
        if (gVar != null) {
            a a = a.a(intent);
            if (a == null) {
                com.vivo.push.util.n.b("PushCommand", "bundleWapper is null");
            } else {
                String str = a.b;
                if (!TextUtils.isEmpty(str)) {
                    gVar.b = str;
                } else {
                    gVar.b = a.a("client_pkgname");
                }
                gVar.c(a);
            }
        }
        AppMethodBeat.o(2063);
        return gVar;
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x0074  */
    @Override // com.vivo.push.IPushClientFactory
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.vivo.push.e createTask(com.vivo.push.g r4) {
        /*
            r3 = this;
            r0 = 2064(0x810, float:2.892E-42)
            r1 = 0
            cn.missfresh.sherlock.trace.core.AppMethodBeat.i(r0, r1)
            int r1 = r4.a
            r2 = 20
            if (r1 == r2) goto L_0x0086
            r2 = 100
            if (r1 == r2) goto L_0x0080
            r2 = 101(0x65, float:1.42E-43)
            if (r1 == r2) goto L_0x007a
            switch(r1) {
                case 0: goto L_0x0074;
                case 1: goto L_0x006e;
                case 2: goto L_0x0068;
                case 3: goto L_0x0062;
                case 4: goto L_0x005c;
                case 5: goto L_0x0056;
                case 6: goto L_0x0050;
                case 7: goto L_0x004a;
                case 8: goto L_0x0044;
                case 9: goto L_0x003e;
                case 10: goto L_0x0038;
                case 11: goto L_0x0032;
                case 12: goto L_0x002c;
                default: goto L_0x0017;
            }
        L_0x0017:
            switch(r1) {
                case 2000: goto L_0x0074;
                case 2001: goto L_0x0074;
                case 2002: goto L_0x0074;
                case 2003: goto L_0x0074;
                case 2004: goto L_0x0074;
                case 2005: goto L_0x0074;
                case 2006: goto L_0x0025;
                case 2007: goto L_0x001e;
                case 2008: goto L_0x0074;
                case 2009: goto L_0x0074;
                case 2010: goto L_0x0074;
                case 2011: goto L_0x0074;
                case 2012: goto L_0x0074;
                case 2013: goto L_0x0074;
                case 2014: goto L_0x0074;
                case 2015: goto L_0x0074;
                default: goto L_0x001a;
            }
        L_0x001a:
            r4 = 0
            r1 = r4
            goto L_0x008b
        L_0x001e:
            com.vivo.push.c.u r1 = new com.vivo.push.c.u
            r1.<init>(r4)
            goto L_0x008b
        L_0x0025:
            com.vivo.push.c.a r1 = new com.vivo.push.c.a
            r1.<init>(r4)
            goto L_0x008b
        L_0x002c:
            com.vivo.push.c.e r1 = new com.vivo.push.c.e
            r1.<init>(r4)
            goto L_0x008b
        L_0x0032:
            com.vivo.push.c.q r1 = new com.vivo.push.c.q
            r1.<init>(r4)
            goto L_0x008b
        L_0x0038:
            com.vivo.push.c.d r1 = new com.vivo.push.c.d
            r1.<init>(r4)
            goto L_0x008b
        L_0x003e:
            com.vivo.push.c.f r1 = new com.vivo.push.c.f
            r1.<init>(r4)
            goto L_0x008b
        L_0x0044:
            com.vivo.push.c.i r1 = new com.vivo.push.c.i
            r1.<init>(r4)
            goto L_0x008b
        L_0x004a:
            com.vivo.push.c.j r1 = new com.vivo.push.c.j
            r1.<init>(r4)
            goto L_0x008b
        L_0x0050:
            com.vivo.push.c.n r1 = new com.vivo.push.c.n
            r1.<init>(r4)
            goto L_0x008b
        L_0x0056:
            com.vivo.push.c.m r1 = new com.vivo.push.c.m
            r1.<init>(r4)
            goto L_0x008b
        L_0x005c:
            com.vivo.push.c.l r1 = new com.vivo.push.c.l
            r1.<init>(r4)
            goto L_0x008b
        L_0x0062:
            com.vivo.push.c.k r1 = new com.vivo.push.c.k
            r1.<init>(r4)
            goto L_0x008b
        L_0x0068:
            com.vivo.push.c.g r1 = new com.vivo.push.c.g
            r1.<init>(r4)
            goto L_0x008b
        L_0x006e:
            com.vivo.push.c.p r1 = new com.vivo.push.c.p
            r1.<init>(r4)
            goto L_0x008b
        L_0x0074:
            com.vivo.push.c.t r1 = new com.vivo.push.c.t
            r1.<init>(r4)
            goto L_0x008b
        L_0x007a:
            com.vivo.push.c.c r1 = new com.vivo.push.c.c
            r1.<init>(r4)
            goto L_0x008b
        L_0x0080:
            com.vivo.push.c.b r1 = new com.vivo.push.c.b
            r1.<init>(r4)
            goto L_0x008b
        L_0x0086:
            com.vivo.push.c.r r1 = new com.vivo.push.c.r
            r1.<init>(r4)
        L_0x008b:
            cn.missfresh.sherlock.trace.core.AppMethodBeat.o(r0)
            return r1
            switch-data {0->0x0074, 1->0x006e, 2->0x0068, 3->0x0062, 4->0x005c, 5->0x0056, 6->0x0050, 7->0x004a, 8->0x0044, 9->0x003e, 10->0x0038, 11->0x0032, 12->0x002c, }
            switch-data {2000->0x0074, 2001->0x0074, 2002->0x0074, 2003->0x0074, 2004->0x0074, 2005->0x0074, 2006->0x0025, 2007->0x001e, 2008->0x0074, 2009->0x0074, 2010->0x0074, 2011->0x0074, 2012->0x0074, 2013->0x0074, 2014->0x0074, 2015->0x0074, }
        */
        throw new UnsupportedOperationException("Method not decompiled: com.vivo.push.c.createTask(com.vivo.push.g):com.vivo.push.e");
    }

    @Override // com.vivo.push.IPushClientFactory
    public final com.vivo.push.c.o createReceiveTask(g gVar) {
        com.vivo.push.c.o oVar;
        AppMethodBeat.i(2066, false);
        int i = gVar.a;
        if (i == 20) {
            oVar = new com.vivo.push.c.r(gVar);
        } else if (i != 2016) {
            switch (i) {
                case 1:
                    oVar = new com.vivo.push.c.p(gVar);
                    break;
                case 2:
                    oVar = new g(gVar);
                    break;
                case 3:
                    oVar = new com.vivo.push.c.k(gVar);
                    break;
                case 4:
                    oVar = new com.vivo.push.c.l(gVar);
                    break;
                case 5:
                    oVar = new com.vivo.push.c.m(gVar);
                    break;
                case 6:
                    oVar = new com.vivo.push.c.n(gVar);
                    break;
                case 7:
                    oVar = new com.vivo.push.c.j(gVar);
                    break;
                case 8:
                    oVar = new com.vivo.push.c.i(gVar);
                    break;
                case 9:
                    oVar = new f(gVar);
                    break;
                case 10:
                    oVar = new d(gVar);
                    break;
                case 11:
                    oVar = new com.vivo.push.c.q(gVar);
                    break;
                default:
                    oVar = null;
                    break;
            }
        } else {
            oVar = new h(gVar);
        }
        AppMethodBeat.o(2066);
        return oVar;
    }
}
