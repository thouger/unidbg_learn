package com.cmic.sso.wy.utils;

import android.content.Context;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import com.cmic.sso.wy.a.b;

/* compiled from: SIMUtils */
public class p {
    private static final String a = p.class.getSimpleName();
    private static p c;
    private Context b;

    private p(Context context) {
        this.b = context.getApplicationContext();
    }

    public static p a(Context context) {
        if (c == null) {
            c = new p(context);
        }
        return c;
    }

    public String a() {
        try {
            b.C0073b b = b.a().b();
            return b.g(b.f());
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public String b() {
        try {
            b.C0073b b = b.a().b();
            String g = b.g((b.f() + 1) % 2);
            if (g == null) {
                return "";
            }
            return g;
        } catch (Exception unused) {
            return "";
        }
    }

    public String c() {
        try {
            b.C0073b b = b.a().b();
            String h = b.h(b.f());
            if (TextUtils.isEmpty(h)) {
                h = ((TelephonyManager) this.b.getSystemService("phone")).getDeviceId();
            }
            g.b("UMC_SDK", "imei is " + h);
            if (TextUtils.isEmpty(h)) {
                return "none";
            }
            return h;
        } catch (Exception unused) {
            return "none";
        }
    }

    public String d() {
        try {
            b.C0073b b = b.a().b();
            String h = b.h((b.f() + 1) % 2);
            if (h == null) {
                return "";
            }
            return h;
        } catch (Exception unused) {
            return "";
        }
    }

    public String e() {
        b.C0073b b = b.a().b();
        return b.a(b.f());
    }

    public String a(boolean z) {
        TelephonyManager telephonyManager;
        b.C0073b b = b.a().b();
        String i = b.i(b.f());
        if (TextUtils.isEmpty(i) && u.b(this.b) && (telephonyManager = (TelephonyManager) this.b.getSystemService("phone")) != null) {
            i = telephonyManager.getSimOperator();
            if (TextUtils.isEmpty(i) && l.a(this.b, "android.permission.READ_PHONE_STATE") && u.e()) {
                String str = null;
                try {
                    str = telephonyManager.getSubscriberId();
                } catch (Exception unused) {
                    g.a(a, "getOperator\u5931\u8d25");
                }
                if (!TextUtils.isEmpty(str) && str.length() >= 5) {
                    i = str.substring(0, 5);
                }
            }
        }
        String str2 = a;
        g.b(str2, "operator: " + i);
        if (TextUtils.isEmpty(i)) {
            return z ? "0" : "";
        }
        return a(i);
    }

    public String f() {
        TelephonyManager telephonyManager = (TelephonyManager) this.b.getSystemService("phone");
        if (telephonyManager == null) {
            return "0";
        }
        String simOperator = telephonyManager.getSimOperator();
        String str = a;
        g.b(str, "SysOperType = " + simOperator);
        return a(simOperator);
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x0087 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x008b  */
    /* JADX WARNING: Removed duplicated region for block: B:40:0x0097  */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x00a3  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private java.lang.String a(java.lang.String r3) {
        /*
            r2 = this;
            int r0 = r3.hashCode()
            r1 = 49679479(0x2f60c77, float:3.6153606E-37)
            if (r0 == r1) goto L_0x0078
            r1 = 49679502(0x2f60c8e, float:3.6153657E-37)
            if (r0 == r1) goto L_0x006c
            switch(r0) {
                case 49679470: goto L_0x0061;
                case 49679471: goto L_0x0056;
                case 49679472: goto L_0x004b;
                case 49679473: goto L_0x0040;
                case 49679474: goto L_0x0035;
                case 49679475: goto L_0x0029;
                case 49679476: goto L_0x001e;
                case 49679477: goto L_0x0013;
                default: goto L_0x0011;
            }
        L_0x0011:
            goto L_0x0083
        L_0x0013:
            java.lang.String r0 = "46007"
            boolean r3 = r3.equals(r0)
            if (r3 == 0) goto L_0x0083
            r3 = 2
            goto L_0x0084
        L_0x001e:
            java.lang.String r0 = "46006"
            boolean r3 = r3.equals(r0)
            if (r3 == 0) goto L_0x0083
            r3 = 5
            goto L_0x0084
        L_0x0029:
            java.lang.String r0 = "46005"
            boolean r3 = r3.equals(r0)
            if (r3 == 0) goto L_0x0083
            r3 = 8
            goto L_0x0084
        L_0x0035:
            java.lang.String r0 = "46004"
            boolean r3 = r3.equals(r0)
            if (r3 == 0) goto L_0x0083
            r3 = 3
            goto L_0x0084
        L_0x0040:
            java.lang.String r0 = "46003"
            boolean r3 = r3.equals(r0)
            if (r3 == 0) goto L_0x0083
            r3 = 7
            goto L_0x0084
        L_0x004b:
            java.lang.String r0 = "46002"
            boolean r3 = r3.equals(r0)
            if (r3 == 0) goto L_0x0083
            r3 = 1
            goto L_0x0084
        L_0x0056:
            java.lang.String r0 = "46001"
            boolean r3 = r3.equals(r0)
            if (r3 == 0) goto L_0x0083
            r3 = 4
            goto L_0x0084
        L_0x0061:
            java.lang.String r0 = "46000"
            boolean r3 = r3.equals(r0)
            if (r3 == 0) goto L_0x0083
            r3 = 0
            goto L_0x0084
        L_0x006c:
            java.lang.String r0 = "46011"
            boolean r3 = r3.equals(r0)
            if (r3 == 0) goto L_0x0083
            r3 = 9
            goto L_0x0084
        L_0x0078:
            java.lang.String r0 = "46009"
            boolean r3 = r3.equals(r0)
            if (r3 == 0) goto L_0x0083
            r3 = 6
            goto L_0x0084
        L_0x0083:
            r3 = -1
        L_0x0084:
            switch(r3) {
                case 0: goto L_0x00a3;
                case 1: goto L_0x00a3;
                case 2: goto L_0x00a3;
                case 3: goto L_0x00a3;
                case 4: goto L_0x0097;
                case 5: goto L_0x0097;
                case 6: goto L_0x0097;
                case 7: goto L_0x008b;
                case 8: goto L_0x008b;
                case 9: goto L_0x008b;
                default: goto L_0x0087;
            }
        L_0x0087:
            java.lang.String r3 = "0"
            return r3
        L_0x008b:
            java.lang.String r3 = com.cmic.sso.wy.utils.p.a
            java.lang.String r0 = "\u4e2d\u56fd\u7535\u4fe1"
            com.cmic.sso.wy.utils.g.a(r3, r0)
            java.lang.String r3 = "3"
            return r3
        L_0x0097:
            java.lang.String r3 = com.cmic.sso.wy.utils.p.a
            java.lang.String r0 = "\u4e2d\u56fd\u8054\u901a"
            com.cmic.sso.wy.utils.g.a(r3, r0)
            java.lang.String r3 = "2"
            return r3
        L_0x00a3:
            java.lang.String r3 = com.cmic.sso.wy.utils.p.a
            java.lang.String r0 = "\u4e2d\u56fd\u79fb\u52a8"
            com.cmic.sso.wy.utils.g.a(r3, r0)
            java.lang.String r3 = "1"
            return r3
            switch-data {49679470->0x0061, 49679471->0x0056, 49679472->0x004b, 49679473->0x0040, 49679474->0x0035, 49679475->0x0029, 49679476->0x001e, 49679477->0x0013, }
            switch-data {0->0x00a3, 1->0x00a3, 2->0x00a3, 3->0x00a3, 4->0x0097, 5->0x0097, 6->0x0097, 7->0x008b, 8->0x008b, 9->0x008b, }
        */
        throw new UnsupportedOperationException("Method not decompiled: com.cmic.sso.wy.utils.p.a(java.lang.String):java.lang.String");
    }
}
