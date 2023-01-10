package com.cmic.sso.wy.a;

import android.content.Context;
import android.os.Build;
import android.telephony.SubscriptionInfo;
import android.telephony.SubscriptionManager;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.TimedRemoteCaller;
import com.cmic.sso.wy.utils.g;
import com.cmic.sso.wy.utils.l;
import com.cmic.sso.wy.utils.u;
import java.lang.reflect.Method;
import java.util.List;

/* compiled from: UMCTelephonyManagement */
public class b {
    private static b a;
    private static long b;
    private C0073b c = null;

    /* compiled from: UMCTelephonyManagement */
    public static class a extends Exception {
        public a(String str) {
            super(str);
        }
    }

    /* compiled from: UMCTelephonyManagement */
    /* renamed from: com.cmic.sso.wy.a.b$b  reason: collision with other inner class name */
    public static class C0073b {
        private String a = "";
        private String b = "";
        private String c = "";
        private String d = "";
        private String e = "";
        private String f = "";
        private boolean g = false;
        private boolean h = false;
        private int i = -1;
        private int j = -1;
        private int k = -1;
        private int l = -1;
        private String m = "";
        private String n = "";
        private int o = -1;
        private int p = -1;
        private int q = -1;

        /* access modifiers changed from: protected */
        public void a(String str) {
            if (str != null) {
                this.c = str;
            }
        }

        public String a() {
            return this.d;
        }

        /* access modifiers changed from: protected */
        public void b(String str) {
            if (str != null) {
                this.d = str;
            }
        }

        public String b() {
            return this.e;
        }

        /* access modifiers changed from: protected */
        public void c(String str) {
            if (str != null) {
                this.e = str;
            }
        }

        public String a(int i) {
            if (this.i == i) {
                return this.a;
            }
            return this.j == i ? this.b : "";
        }

        public String c() {
            return this.f;
        }

        /* access modifiers changed from: protected */
        public void d(String str) {
            if (str != null) {
                this.f = str;
            }
        }

        /* access modifiers changed from: protected */
        public void e(String str) {
            if (str != null) {
                this.m = str;
            }
        }

        public String d() {
            return this.n;
        }

        /* access modifiers changed from: protected */
        public void f(String str) {
            this.n = str;
        }

        /* access modifiers changed from: protected */
        public void a(boolean z) {
            this.g = z;
        }

        public boolean e() {
            return this.h;
        }

        /* access modifiers changed from: protected */
        public void b(boolean z) {
            this.h = z;
        }

        public int f() {
            return this.o;
        }

        /* access modifiers changed from: protected */
        public void b(int i) {
            this.o = i;
        }

        public int g() {
            return this.i;
        }

        /* access modifiers changed from: protected */
        public void c(int i) {
            this.i = i;
        }

        public int h() {
            return this.j;
        }

        /* access modifiers changed from: protected */
        public void d(int i) {
            this.j = i;
        }

        /* access modifiers changed from: protected */
        public void e(int i) {
            this.k = i;
        }

        /* access modifiers changed from: protected */
        public void f(int i) {
            this.l = i;
        }

        public int i() {
            if (!TextUtils.isEmpty(this.f) && !TextUtils.isEmpty(this.e)) {
                return 2;
            }
            if (TextUtils.isEmpty(this.m) || TextUtils.isEmpty(this.n)) {
                return (!TextUtils.isEmpty(this.e) || !TextUtils.isEmpty(this.f) || !TextUtils.isEmpty(this.m) || !TextUtils.isEmpty(this.n)) ? 1 : 0;
            }
            return 2;
        }

        public String g(int i) {
            if (this.i == i) {
                return this.e;
            }
            return this.j == i ? this.f : "";
        }

        public String h(int i) {
            if (this.i == i) {
                return this.c;
            }
            return this.j == i ? this.d : "";
        }

        public String i(int i) {
            if (this.i == i) {
                return this.m;
            }
            return this.j == i ? this.n : "";
        }
    }

    private b() {
    }

    public static b a() {
        if (a == null) {
            a = new b();
        }
        return a;
    }

    public C0073b b() {
        C0073b bVar = this.c;
        return bVar == null ? new C0073b() : bVar;
    }

    public void a(Context context, boolean z) {
        if (System.currentTimeMillis() - b >= TimedRemoteCaller.DEFAULT_CALL_TIMEOUT_MILLIS) {
            this.c = new C0073b();
            if (u.b(context)) {
                b(context, z);
                if (u.d()) {
                    g.b("UMCTelephonyManagement", "\u534e\u4e3a\u624b\u673a\u517c\u5bb9\u6027\u5904\u7406");
                    if (this.c.p == 0 || this.c.p == 1) {
                        C0073b bVar = this.c;
                        bVar.o = bVar.p;
                    }
                }
                if (z) {
                    try {
                        if (Build.VERSION.SDK_INT >= 22) {
                            c(context);
                        } else {
                            d(context);
                        }
                    } catch (Exception unused) {
                        g.a("UMCTelephonyManagement", "read sim info error");
                    }
                }
                if (Build.VERSION.SDK_INT >= 21) {
                    b(context);
                }
                b = System.currentTimeMillis();
            }
        }
    }

    private void b(Context context, boolean z) {
        if (Build.VERSION.SDK_INT >= 22) {
            SubscriptionManager from = SubscriptionManager.from(context.getApplicationContext());
            if (from != null) {
                if (z) {
                    try {
                        SubscriptionInfo a2 = a(from, "getDefaultDataSubscriptionInfo", (Object[]) null);
                        if (a2 != null) {
                            this.c.o = a2.getSimSlotIndex();
                            this.c.p = a2.getSubscriptionId();
                            g.b("UMCTelephonyManagement", "getDefaultDataSubscriptionInfo\u9002\u914d\u6210\u529f: dataSlotId\u5373sim_id = " + this.c.o);
                            g.b("UMCTelephonyManagement", "getDefaultDataSubscriptionInfo\u9002\u914d\u6210\u529f: dataSubId = " + this.c.p);
                            return;
                        }
                    } catch (Exception unused) {
                        g.a("UMCTelephonyManagement", "readDefaultDataSubId-->getDefaultDataSubscriptionInfo \u53cd\u5c04\u51fa\u9519");
                    }
                }
                try {
                    if (this.c.o == -1 && Build.VERSION.SDK_INT >= 24) {
                        this.c.p = SubscriptionManager.getDefaultDataSubscriptionId();
                        g.b("UMCTelephonyManagement", "android 7.0\u53ca\u4ee5\u4e0a\u624b\u673agetDefaultDataSubscriptionId\u9002\u914d\u6210\u529f: dataSubId = " + this.c.p);
                        return;
                    }
                } catch (Exception unused2) {
                    g.a("UMCTelephonyManagement", "android 7.0\u53ca\u4ee5\u4e0a\u624b\u673agetDefaultDataSubscriptionId\u9002\u914d\u5931\u8d25");
                }
                try {
                    Method method = from.getClass().getMethod("getDefaultDataSubId", new Class[0]);
                    if (method != null) {
                        this.c.p = ((Integer) method.invoke(from, new Object[0])).intValue();
                        g.b("UMCTelephonyManagement", "android 7.0\u4ee5\u4e0b\u624b\u673agetDefaultDataSubId\u9002\u914d\u6210\u529f: dataSubId = " + this.c.p);
                        return;
                    }
                } catch (Exception unused3) {
                    g.a("UMCTelephonyManagement", "readDefaultDataSubId-->getDefaultDataSubId \u53cd\u5c04\u51fa\u9519");
                }
                try {
                    Method method2 = from.getClass().getMethod("getDefaultDataSubscriptionId", new Class[0]);
                    if (method2 != null) {
                        this.c.p = ((Integer) method2.invoke(from, new Object[0])).intValue();
                        g.b("UMCTelephonyManagement", "\u53cd\u5c04getDefaultDataSubscriptionId\u9002\u914d\u6210\u529f: dataSubId = " + this.c.p);
                    }
                } catch (Exception unused4) {
                    g.a("UMCTelephonyManagement", "getDefaultDataSubscriptionId-->getDefaultDataSubscriptionId \u53cd\u5c04\u51fa\u9519");
                }
            }
        } else {
            this.c.o = -1;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:72:0x027f, code lost:
        if (r12 != null) goto L_0x028c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:78:0x028a, code lost:
        if (0 == 0) goto L_0x028f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:79:0x028c, code lost:
        r12.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:80:0x028f, code lost:
        com.cmic.sso.wy.utils.g.b("UMCTelephonyManagement", "readSimInfoDbEnd");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:81:0x0295, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void b(android.content.Context r12) {
        /*
        // Method dump skipped, instructions count: 668
        */
        throw new UnsupportedOperationException("Method not decompiled: com.cmic.sso.wy.a.b.b(android.content.Context):void");
    }

    private String a(String str) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        g.b("UMCTelephonyManagement", "operatorChina = " + str);
        if (str.contains("\u4e2d\u56fd\u79fb\u52a8")) {
            return "46000";
        }
        if (str.contains("\u4e2d\u56fd\u8054\u901a")) {
            return "46001";
        }
        if (str.contains("\u4e2d\u56fd\u7535\u4fe1")) {
            return "46003";
        }
        return "";
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARNING: Code restructure failed: missing block: B:36:0x00a4, code lost:
        if (r7.equals("898609") != false) goto L_0x00a8;
     */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x00ab A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:40:0x00ac A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x00b0 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x00b4 A[RETURN] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private java.lang.String b(java.lang.String r7) {
        /*
            r6 = this;
            boolean r0 = android.text.TextUtils.isEmpty(r7)
            java.lang.String r1 = ""
            r2 = 6
            if (r0 == 0) goto L_0x0011
            int r0 = r7.length()
            if (r0 >= r2) goto L_0x0011
            return r1
        L_0x0011:
            r0 = 0
            java.lang.String r7 = r7.substring(r0, r2)
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r4 = "operatorFlag = "
            r3.append(r4)
            r3.append(r7)
            java.lang.String r3 = r3.toString()
            java.lang.String r4 = "UMCTelephonyManagement"
            com.cmic.sso.wy.utils.g.b(r4, r3)
            r3 = -1
            int r4 = r7.hashCode()
            r5 = 1657594888(0x62cce408, float:1.8897836E21)
            if (r4 == r5) goto L_0x009d
            r2 = 1657594911(0x62cce41f, float:1.8897868E21)
            if (r4 == r2) goto L_0x0091
            switch(r4) {
                case 1657594879: goto L_0x0086;
                case 1657594880: goto L_0x007b;
                case 1657594881: goto L_0x0070;
                case 1657594882: goto L_0x0065;
                case 1657594883: goto L_0x005a;
                default: goto L_0x0040;
            }
        L_0x0040:
            switch(r4) {
                case 1657594885: goto L_0x004f;
                case 1657594886: goto L_0x0044;
                default: goto L_0x0043;
            }
        L_0x0043:
            goto L_0x00a7
        L_0x0044:
            java.lang.String r0 = "898607"
            boolean r7 = r7.equals(r0)
            if (r7 == 0) goto L_0x00a7
            r2 = 3
            goto L_0x00a8
        L_0x004f:
            java.lang.String r0 = "898606"
            boolean r7 = r7.equals(r0)
            if (r7 == 0) goto L_0x00a7
            r2 = 5
            goto L_0x00a8
        L_0x005a:
            java.lang.String r0 = "898604"
            boolean r7 = r7.equals(r0)
            if (r7 == 0) goto L_0x00a7
            r2 = 2
            goto L_0x00a8
        L_0x0065:
            java.lang.String r0 = "898603"
            boolean r7 = r7.equals(r0)
            if (r7 == 0) goto L_0x00a7
            r2 = 7
            goto L_0x00a8
        L_0x0070:
            java.lang.String r0 = "898602"
            boolean r7 = r7.equals(r0)
            if (r7 == 0) goto L_0x00a7
            r2 = 1
            goto L_0x00a8
        L_0x007b:
            java.lang.String r0 = "898601"
            boolean r7 = r7.equals(r0)
            if (r7 == 0) goto L_0x00a7
            r2 = 4
            goto L_0x00a8
        L_0x0086:
            java.lang.String r2 = "898600"
            boolean r7 = r7.equals(r2)
            if (r7 == 0) goto L_0x00a7
            r2 = r0
            goto L_0x00a8
        L_0x0091:
            java.lang.String r0 = "898611"
            boolean r7 = r7.equals(r0)
            if (r7 == 0) goto L_0x00a7
            r2 = 8
            goto L_0x00a8
        L_0x009d:
            java.lang.String r0 = "898609"
            boolean r7 = r7.equals(r0)
            if (r7 == 0) goto L_0x00a7
            goto L_0x00a8
        L_0x00a7:
            r2 = r3
        L_0x00a8:
            switch(r2) {
                case 0: goto L_0x00b4;
                case 1: goto L_0x00b4;
                case 2: goto L_0x00b4;
                case 3: goto L_0x00b4;
                case 4: goto L_0x00b0;
                case 5: goto L_0x00b0;
                case 6: goto L_0x00b0;
                case 7: goto L_0x00ac;
                case 8: goto L_0x00ac;
                default: goto L_0x00ab;
            }
        L_0x00ab:
            return r1
        L_0x00ac:
            java.lang.String r7 = "46003"
            return r7
        L_0x00b0:
            java.lang.String r7 = "46001"
            return r7
        L_0x00b4:
            java.lang.String r7 = "46000"
            return r7
            switch-data {1657594879->0x0086, 1657594880->0x007b, 1657594881->0x0070, 1657594882->0x0065, 1657594883->0x005a, }
            switch-data {1657594885->0x004f, 1657594886->0x0044, }
            switch-data {0->0x00b4, 1->0x00b4, 2->0x00b4, 3->0x00b4, 4->0x00b0, 5->0x00b0, 6->0x00b0, 7->0x00ac, 8->0x00ac, }
        */
        throw new UnsupportedOperationException("Method not decompiled: com.cmic.sso.wy.a.b.b(java.lang.String):java.lang.String");
    }

    private void c(Context context) {
        List<SubscriptionInfo> e;
        TelephonyManager telephonyManager = (TelephonyManager) context.getApplicationContext().getSystemService("phone");
        if (telephonyManager != null && (e = e(context)) != null && e.size() > 0) {
            a(e, telephonyManager);
            b(e, telephonyManager);
        }
    }

    private void d(Context context) {
        TelephonyManager telephonyManager = (TelephonyManager) context.getApplicationContext().getSystemService("phone");
        this.c.c(0);
        this.c.d(1);
        this.c.b(-1);
        try {
            this.c.c(a(telephonyManager, "getSubscriberId", 0));
            this.c.d(a(telephonyManager, "getSubscriberId", 1));
        } catch (a unused) {
            try {
                this.c.c(a(telephonyManager, "getSubscriberIdGemini", 0));
                this.c.d(a(telephonyManager, "getSubscriberIdGemini", 1));
            } catch (a unused2) {
                try {
                    this.c.c(telephonyManager.getSubscriberId());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        try {
            this.c.a(b(telephonyManager, "getSimState", 0));
            this.c.b(b(telephonyManager, "getSimState", 1));
        } catch (a unused3) {
            try {
                this.c.a(b(telephonyManager, "getSimStateGemini", 0));
                this.c.b(b(telephonyManager, "getSimStateGemini", 1));
            } catch (a unused4) {
                this.c.a(telephonyManager.getSimState() == 5);
            }
        }
        try {
            this.c.e(a(telephonyManager, "getSimOperator", 0));
            this.c.f(a(telephonyManager, "getSimOperator", 1));
        } catch (a unused5) {
            try {
                this.c.e(a(telephonyManager, "getSimOperatorGemini", 0));
                this.c.f(a(telephonyManager, "getSimOperatorGemini", 1));
            } catch (a unused6) {
                this.c.e(telephonyManager.getSimOperator());
            }
        }
        if (TextUtils.isEmpty(this.c.b()) && !TextUtils.isEmpty(this.c.c())) {
            C0073b bVar = this.c;
            bVar.a(bVar.a());
            this.c.b("");
            C0073b bVar2 = this.c;
            bVar2.c(bVar2.c());
            this.c.d("");
            C0073b bVar3 = this.c;
            bVar3.c(bVar3.h());
            this.c.d(-1);
            C0073b bVar4 = this.c;
            bVar4.a(bVar4.e());
            this.c.b(false);
            C0073b bVar5 = this.c;
            bVar5.e(bVar5.d());
            this.c.f("");
            C0073b bVar6 = this.c;
            bVar6.b(bVar6.g());
        } else if (!TextUtils.isEmpty(this.c.b()) && TextUtils.isEmpty(this.c.c())) {
            this.c.b("");
            this.c.b(false);
            this.c.d(-1);
            C0073b bVar7 = this.c;
            bVar7.b(bVar7.g());
        } else if (TextUtils.isEmpty(this.c.b()) && TextUtils.isEmpty(this.c.c())) {
            this.c.a("");
            this.c.b("");
            this.c.c(-1);
            this.c.d(-1);
            this.c.a(false);
            this.c.b(false);
            this.c.b(-1);
        }
    }

    private void a(List<SubscriptionInfo> list, TelephonyManager telephonyManager) {
        SubscriptionInfo subscriptionInfo;
        int size = list != null ? list.size() : 0;
        if (size == 1) {
            subscriptionInfo = list.get(0);
        } else if (size > 1) {
            subscriptionInfo = a(list, 0);
        } else {
            return;
        }
        this.c.a = subscriptionInfo.getIccId();
        this.c.c(subscriptionInfo.getSimSlotIndex());
        this.c.e(subscriptionInfo.getSubscriptionId());
        C0073b bVar = this.c;
        bVar.m = b(bVar.a);
        g.b("UMCTelephonyManagement", "readSim1Info1 iccid1 = " + this.c.a);
        if (this.c.o == -1 && this.c.p == subscriptionInfo.getSubscriptionId()) {
            this.c.o = subscriptionInfo.getSimSlotIndex();
            g.b("UMCTelephonyManagement", "readSim1Info1 dataSlotId = " + this.c.o);
        }
        if (u.e()) {
            try {
                this.c.a(a(telephonyManager, "getDeviceId", subscriptionInfo.getSimSlotIndex()));
            } catch (a unused) {
                try {
                    this.c.a(a(telephonyManager, "getDeviceIdGemini", subscriptionInfo.getSimSlotIndex()));
                } catch (a unused2) {
                    try {
                        this.c.a(telephonyManager.getDeviceId());
                    } catch (Exception unused3) {
                        g.a("UMCTelephonyManagement", "readSim1Info imei1 failed");
                    }
                }
            }
            int simSlotIndex = a.a() == 0 ? subscriptionInfo.getSimSlotIndex() : subscriptionInfo.getSubscriptionId();
            try {
                this.c.c(a(telephonyManager, "getSubscriberId", subscriptionInfo.getSubscriptionId()));
            } catch (a unused4) {
                try {
                    this.c.c(a(telephonyManager, "getSubscriberIdGemini", simSlotIndex));
                } catch (a unused5) {
                    try {
                        this.c.c(telephonyManager.getSubscriberId());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
            try {
                this.c.e(a(telephonyManager, "getSimOperator", subscriptionInfo.getSubscriptionId()));
            } catch (a unused6) {
                try {
                    this.c.e(a(telephonyManager, "getSimOperatorGemini", simSlotIndex));
                } catch (a unused7) {
                    this.c.e(telephonyManager.getSimOperator());
                }
            }
        }
    }

    private void b(List<SubscriptionInfo> list, TelephonyManager telephonyManager) {
        int i = 1;
        if ((list != null ? list.size() : 0) > 1) {
            SubscriptionInfo a2 = a(list, 1);
            this.c.d(a2.getSimSlotIndex());
            this.c.f(a2.getSubscriptionId());
            this.c.b = a2.getIccId();
            C0073b bVar = this.c;
            bVar.n = b(bVar.b);
            g.b("UMCTelephonyManagement", "readSim1Info2 iccid2 = " + this.c.b);
            if (this.c.o == -1 && this.c.p == a2.getSubscriptionId()) {
                this.c.o = a2.getSimSlotIndex();
                g.b("UMCTelephonyManagement", "readSim1Info2 dataSlotId = " + this.c.o);
            }
            if (u.e()) {
                try {
                    this.c.b(a(telephonyManager, "getDeviceId", 1));
                } catch (a unused) {
                    try {
                        this.c.b(a(telephonyManager, "getDeviceIdGemini", 1));
                    } catch (Exception unused2) {
                        g.a("UMCTelephonyManagement", "readSim1Info imei2 failed");
                    }
                }
                if (a.a() != 0) {
                    i = a2.getSubscriptionId();
                }
                try {
                    this.c.d(a(telephonyManager, "getSubscriberId", a2.getSubscriptionId()));
                } catch (a unused3) {
                    try {
                        this.c.d(a(telephonyManager, "getSubscriberIdGemini", i));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                try {
                    this.c.f(a(telephonyManager, "getSimOperator", a2.getSubscriptionId()));
                } catch (a unused4) {
                    try {
                        this.c.f(a(telephonyManager, "getSimOperatorGemini", i));
                    } catch (Exception e2) {
                        e2.printStackTrace();
                    }
                }
            }
        }
    }

    private SubscriptionInfo a(List<SubscriptionInfo> list, int i) {
        SubscriptionInfo subscriptionInfo = list.get(0);
        for (SubscriptionInfo subscriptionInfo2 : list) {
            if (subscriptionInfo2.getSimSlotIndex() == i) {
                subscriptionInfo = subscriptionInfo2;
            }
        }
        return subscriptionInfo;
    }

    private List<SubscriptionInfo> e(Context context) {
        SubscriptionManager from = SubscriptionManager.from(context.getApplicationContext());
        if (from != null) {
            return from.getActiveSubscriptionInfoList();
        }
        return null;
    }

    private String a(TelephonyManager telephonyManager, String str, int i) throws a {
        Object a2 = a(telephonyManager, str, new Object[]{Integer.valueOf(i)}, new Class[]{Integer.TYPE});
        if (a2 != null) {
            return a2.toString();
        }
        return null;
    }

    private boolean b(TelephonyManager telephonyManager, String str, int i) throws a {
        Object a2 = a(telephonyManager, str, new Object[]{Integer.valueOf(i)}, new Class[]{Integer.TYPE});
        return a2 != null && Integer.parseInt(a2.toString()) == 5;
    }

    private SubscriptionInfo a(Object obj, String str, Object[] objArr) throws a {
        return (SubscriptionInfo) a(obj, str, objArr, null);
    }

    private Object a(Object obj, String str, Object[] objArr, Class[] clsArr) throws a {
        try {
            Class<?> cls = Class.forName(obj.getClass().getName());
            if (objArr == null || clsArr == null) {
                return cls.getMethod(str, new Class[0]).invoke(obj, new Object[0]);
            }
            return cls.getMethod(str, clsArr).invoke(obj, objArr);
        } catch (Exception unused) {
            g.a("UMCTelephonyManagement", str + " \u53cd\u5c04\u51fa\u9519");
            throw new a(str);
        }
    }

    private int f(Context context) {
        TelephonyManager telephonyManager;
        if (!l.a(context, "android.permission.READ_PHONE_STATE") || (telephonyManager = (TelephonyManager) context.getApplicationContext().getSystemService("phone")) == null) {
            return -1;
        }
        if (!u.e()) {
            return telephonyManager.getDataNetworkType();
        }
        try {
            Method method = telephonyManager.getClass().getMethod("getDataNetworkType", Integer.TYPE);
            g.b("UMCTelephonyManagement", "data dataNetworkType defaultDataSubId = " + this.c.p);
            int intValue = ((Integer) method.invoke(telephonyManager, Integer.valueOf(this.c.p))).intValue();
            g.b("UMCTelephonyManagement", "data dataNetworkType ---------" + intValue);
            if (intValue != 0 || Build.VERSION.SDK_INT < 24) {
                return intValue;
            }
            g.b("UMCTelephonyManagement", "data dataNetworkType ---->=N " + intValue);
            return telephonyManager.getDataNetworkType();
        } catch (Exception e) {
            g.a("UMCTelephonyManagement", "data dataNetworkType ----\u53cd\u5c04\u51fa\u9519-----");
            e.printStackTrace();
            return -1;
        }
    }

    public String a(Context context) {
        switch (f(context)) {
            case 1:
            case 2:
            case 4:
            case 7:
            case 11:
            case 16:
                return "1";
            case 3:
            case 5:
            case 6:
            case 8:
            case 9:
            case 10:
            case 12:
            case 14:
            case 15:
            case 17:
                return "2";
            case 13:
            case 18:
            case 19:
                return "3";
            case 20:
                return "4";
            default:
                return "0";
        }
    }
}
