package cn.missfresh.module.base.helper;

import android.content.Intent;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import cn.missfresh.utils.e;

/* compiled from: MFToutiaoBackHelper */
public class d {

    /* compiled from: MFToutiaoBackHelper */
    /* access modifiers changed from: private */
    public static class a {
        private static final d a = new d();

        static {
            AppMethodBeat.i(12973, false);
            AppMethodBeat.o(12973);
        }
    }

    public static d a() {
        AppMethodBeat.i(12977, false);
        d dVar = a.a;
        AppMethodBeat.o(12977);
        return dVar;
    }

    public boolean a(Intent intent) {
        AppMethodBeat.i(12979, false);
        if (intent == null || intent.getData() == null || e.a(intent.getData().getHost()) || !intent.getData().getHost().equals("launch")) {
            AppMethodBeat.o(12979);
            return false;
        }
        AppMethodBeat.o(12979);
        return true;
    }

    public void b(Intent intent) {
        AppMethodBeat.i(12980, false);
        cn.missfresh.utils.a.d.c("=====>", "fromToutiaoHot is called");
        if (intent != null && intent.getData() != null && !e.a(intent.getData().getHost()) && intent.getData().getHost().equals("launch")) {
            com.alibaba.android.arouter.b.a.a().a("/main/mall").withString("backurl", intent.getData().getQueryParameter("backurl")).addFlags(67108864).navigation();
        }
        AppMethodBeat.o(12980);
    }

    public void a(String str) {
        AppMethodBeat.i(12982, false);
        cn.missfresh.utils.a.d.c("=====>", "fromToutiaoCold is called");
        cn.missfresh.utils.a.d.c("=====>", "backurl=" + str);
        if (!e.a(str)) {
            com.alibaba.android.arouter.b.a.a().a("/main/mall").withString("backurl", str).addFlags(67108864).navigation();
        }
        AppMethodBeat.o(12982);
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0077, code lost:
        if (r3.equals("snssdk141") != false) goto L_0x0091;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void c(android.content.Intent r11) {
        /*
            r10 = this;
            r0 = 0
            r1 = 12984(0x32b8, float:1.8194E-41)
            cn.missfresh.sherlock.trace.core.AppMethodBeat.i(r1, r0)
            java.lang.String r2 = "=====>"
            java.lang.String r3 = "backBtn is called"
            cn.missfresh.utils.a.d.c(r2, r3)
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r4 = "backurl="
            r3.append(r4)
            java.lang.String r4 = "backurl"
            java.lang.String r5 = r11.getStringExtra(r4)
            r3.append(r5)
            java.lang.String r3 = r3.toString()
            cn.missfresh.utils.a.d.c(r2, r3)
            if (r11 == 0) goto L_0x00e6
            java.lang.String r3 = r11.getStringExtra(r4)     // Catch:{ Exception -> 0x00d0 }
            boolean r3 = cn.missfresh.utils.e.a(r3)     // Catch:{ Exception -> 0x00d0 }
            if (r3 != 0) goto L_0x00e6
            java.lang.String r11 = r11.getStringExtra(r4)     // Catch:{ Exception -> 0x00d0 }
            java.lang.String r11 = android.net.Uri.decode(r11)     // Catch:{ Exception -> 0x00d0 }
            android.net.Uri r11 = android.net.Uri.parse(r11)     // Catch:{ Exception -> 0x00d0 }
            if (r11 != 0) goto L_0x0049
            cn.missfresh.sherlock.trace.core.AppMethodBeat.o(r1)
            return
        L_0x0049:
            java.lang.String r3 = r11.getScheme()
            r4 = -1
            int r5 = r3.hashCode()
            r6 = 4
            r7 = 3
            r8 = 2
            r9 = 1
            switch(r5) {
                case -1869037821: goto L_0x0085;
                case -1869037784: goto L_0x007a;
                case -891575444: goto L_0x0070;
                case 802523553: goto L_0x0065;
                case 802523556: goto L_0x005a;
                default: goto L_0x0059;
            }
        L_0x0059:
            goto L_0x0090
        L_0x005a:
            java.lang.String r0 = "snssdk35"
            boolean r0 = r3.equals(r0)
            if (r0 == 0) goto L_0x0090
            r0 = r9
            goto L_0x0091
        L_0x0065:
            java.lang.String r0 = "snssdk32"
            boolean r0 = r3.equals(r0)
            if (r0 == 0) goto L_0x0090
            r0 = r8
            goto L_0x0091
        L_0x0070:
            java.lang.String r5 = "snssdk141"
            boolean r3 = r3.equals(r5)
            if (r3 == 0) goto L_0x0090
            goto L_0x0091
        L_0x007a:
            java.lang.String r0 = "snssdk1128"
            boolean r0 = r3.equals(r0)
            if (r0 == 0) goto L_0x0090
            r0 = r7
            goto L_0x0091
        L_0x0085:
            java.lang.String r0 = "snssdk1112"
            boolean r0 = r3.equals(r0)
            if (r0 == 0) goto L_0x0090
            r0 = r6
            goto L_0x0091
        L_0x0090:
            r0 = r4
        L_0x0091:
            if (r0 == 0) goto L_0x00af
            if (r0 == r9) goto L_0x00ab
            if (r0 == r8) goto L_0x00a7
            if (r0 == r7) goto L_0x00a3
            if (r0 == r6) goto L_0x009f
            java.lang.String r0 = "\u8fd4\u56de"
            goto L_0x00b2
        L_0x009f:
            java.lang.String r0 = "\u8fd4\u56de\u706b\u5c71\u5c0f\u89c6\u9891"
            goto L_0x00b2
        L_0x00a3:
            java.lang.String r0 = "\u8fd4\u56de\u6296\u97f3"
            goto L_0x00b2
        L_0x00a7:
            java.lang.String r0 = "\u8fd4\u56de\u897f\u74dc\u89c6\u9891"
            goto L_0x00b2
        L_0x00ab:
            java.lang.String r0 = "\u8fd4\u56de\u5934\u6761Lite"
            goto L_0x00b2
        L_0x00af:
            java.lang.String r0 = "\u8fd4\u56de\u4eca\u65e5\u5934\u6761"
        L_0x00b2:
            cn.missfresh.module.base.helper.h r3 = cn.missfresh.module.base.helper.h.a()
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.String r11 = r11.getScheme()
            r4.append(r11)
            java.lang.String r11 = "://"
            r4.append(r11)
            java.lang.String r11 = r4.toString()
            r3.a(r0, r11)
            goto L_0x00e6
        L_0x00d0:
            r11 = move-exception
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r3 = "Exception="
            r0.append(r3)
            r0.append(r11)
            java.lang.String r11 = r0.toString()
            cn.missfresh.utils.a.d.c(r2, r11)
        L_0x00e6:
            cn.missfresh.sherlock.trace.core.AppMethodBeat.o(r1)
            return
            switch-data {-1869037821->0x0085, -1869037784->0x007a, -891575444->0x0070, 802523553->0x0065, 802523556->0x005a, }
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.missfresh.module.base.helper.d.c(android.content.Intent):void");
    }
}
