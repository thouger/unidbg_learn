package com.vivo.push.util;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.text.TextUtils;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import com.android.internal.logging.nano.MetricsProto;
import com.umeng.message.proguard.l;
import com.vivo.push.cache.d;
import com.vivo.push.model.b;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

/* compiled from: PushPackageUtils */
public final class r {
    public static Boolean a;

    public static b a(Context context) {
        b c;
        AppMethodBeat.i(1664, false);
        Context applicationContext = ContextDelegate.getContext(context).getApplicationContext();
        b c2 = c(applicationContext);
        if (c2 != null) {
            n.d("PushPackageUtils", "get system push info :" + c2);
            AppMethodBeat.o(1664);
            return c2;
        }
        List<String> d = d(applicationContext);
        b c3 = c(applicationContext, applicationContext.getPackageName());
        if (d.size() <= 0) {
            if (c3 != null && c3.f) {
                c2 = c3;
            }
            n.a("PushPackageUtils", "findAllPushPackages error: find no package!");
        } else {
            b bVar = null;
            String a2 = x.b(applicationContext).a("com.vivo.push.cur_pkg", null);
            if (TextUtils.isEmpty(a2) || !a(applicationContext, a2, "com.vivo.pushservice.action.METHOD") || (c2 = c(applicationContext, a2)) == null || !c2.f) {
                c2 = null;
            }
            if (c3 == null || !c3.f) {
                c3 = null;
            }
            if (c2 == null) {
                c2 = null;
            }
            if (c3 != null && (c2 == null || (!c3.e ? c2.e || c3.b > c2.b : c2.e && c3.b > c2.b))) {
                c2 = c3;
            }
            HashMap hashMap = new HashMap();
            if (c2 == null) {
                c2 = null;
            } else if (c2.e) {
                bVar = c2;
                c2 = null;
            }
            int size = d.size();
            for (int i = 0; i < size; i++) {
                String str = d.get(i);
                if (!TextUtils.isEmpty(str) && (c = c(applicationContext, str)) != null) {
                    hashMap.put(str, c);
                    if (c.f) {
                        if (c.e) {
                            if (bVar == null || c.b > bVar.b) {
                                bVar = c;
                            }
                        } else if (c2 == null || c.b > c2.b) {
                            c2 = c;
                        }
                    }
                }
            }
            if (c2 == null) {
                n.d("PushPackageUtils", "findSuitablePushPackage, all push app in balck list.");
                c2 = bVar;
            }
        }
        if (c2 == null) {
            n.b(applicationContext, "\u67e5\u627e\u6700\u4f18\u5305\u4e3a\u7a7a!");
            n.d("PushPackageUtils", "finSuitablePushPackage is null");
        } else if (c2.e) {
            n.a(applicationContext, "\u67e5\u627e\u6700\u4f18\u5305\u4e3a:" + c2.a + l.s + c2.b + ", Black)");
            n.d("PushPackageUtils", "finSuitablePushPackage" + c2.a + l.s + c2.b + ", Black)");
        } else {
            n.a(applicationContext, "\u67e5\u627e\u6700\u4f18\u5305\u4e3a:" + c2.a + l.s + c2.b + l.t);
            n.d("PushPackageUtils", "finSuitablePushPackage" + c2.a + l.s + c2.b + l.t);
        }
        AppMethodBeat.o(1664);
        return c2;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:49:0x00aa, code lost:
        r0 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:50:0x00ac, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:65:?, code lost:
        r12.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:66:0x00c9, code lost:
        r12 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:67:0x00ca, code lost:
        com.vivo.push.util.n.a("PushPackageUtils", "close", r12);
     */
    /* JADX WARNING: Exception block dominator not found, dom blocks: [B:5:0x0023, B:17:0x003a] */
    /* JADX WARNING: Removed duplicated region for block: B:49:0x00aa A[ExcHandler: all (th java.lang.Throwable), Splitter:B:5:0x0023] */
    /* JADX WARNING: Removed duplicated region for block: B:60:0x00bc  */
    /* JADX WARNING: Removed duplicated region for block: B:64:0x00c5 A[SYNTHETIC, Splitter:B:64:0x00c5] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String b(android.content.Context r12) {
        /*
            java.lang.String r0 = "name"
            java.lang.String r1 = "close"
            java.lang.String r2 = "PushPackageUtils"
            r3 = 0
            r4 = 1669(0x685, float:2.339E-42)
            cn.missfresh.sherlock.trace.core.AppMethodBeat.i(r4, r3)
            r5 = 0
            android.content.ContentResolver r6 = r12.getContentResolver()     // Catch:{ Exception -> 0x00b2 }
            android.net.Uri r7 = com.vivo.push.h.a     // Catch:{ Exception -> 0x00b2 }
            r8 = 0
            r9 = 0
            r10 = 0
            r11 = 0
            android.database.Cursor r12 = r6.query(r7, r8, r9, r10, r11)     // Catch:{ Exception -> 0x00b2 }
            if (r12 != 0) goto L_0x0038
            java.lang.String r0 = "cursor is null"
            com.vivo.push.util.n.a(r2, r0)     // Catch:{ Exception -> 0x0034, all -> 0x00aa }
            if (r12 == 0) goto L_0x0030
            r12.close()     // Catch:{ Exception -> 0x002c }
            goto L_0x0030
        L_0x002c:
            r12 = move-exception
            com.vivo.push.util.n.a(r2, r1, r12)
        L_0x0030:
            cn.missfresh.sherlock.trace.core.AppMethodBeat.o(r4)
            return r5
        L_0x0034:
            r0 = move-exception
            r3 = r5
            goto L_0x00ad
        L_0x0038:
            r6 = r3
            r3 = r5
        L_0x003a:
            boolean r7 = r12.moveToNext()     // Catch:{ Exception -> 0x00ac, all -> 0x00aa }
            if (r7 == 0) goto L_0x007b
            java.lang.String r7 = "pushPkgName"
            int r8 = r12.getColumnIndex(r0)     // Catch:{ Exception -> 0x00ac, all -> 0x00aa }
            java.lang.String r8 = r12.getString(r8)     // Catch:{ Exception -> 0x00ac, all -> 0x00aa }
            boolean r7 = r7.equals(r8)     // Catch:{ Exception -> 0x00ac, all -> 0x00aa }
            java.lang.String r8 = "value"
            if (r7 == 0) goto L_0x005d
            int r7 = r12.getColumnIndex(r8)
            java.lang.String r3 = r12.getString(r7)
            goto L_0x003a
        L_0x005d:
            java.lang.String r7 = "pushEnable"
            int r9 = r12.getColumnIndex(r0)
            java.lang.String r9 = r12.getString(r9)
            boolean r7 = r7.equals(r9)
            if (r7 == 0) goto L_0x003a
            int r6 = r12.getColumnIndex(r8)
            java.lang.String r6 = r12.getString(r6)
            boolean r6 = java.lang.Boolean.parseBoolean(r6)
            goto L_0x003a
        L_0x007b:
            boolean r0 = android.text.TextUtils.isEmpty(r3)
            if (r0 == 0) goto L_0x008f
            if (r12 == 0) goto L_0x008b
            r12.close()     // Catch:{ Exception -> 0x0087 }
            goto L_0x008b
        L_0x0087:
            r12 = move-exception
            com.vivo.push.util.n.a(r2, r1, r12)
        L_0x008b:
            cn.missfresh.sherlock.trace.core.AppMethodBeat.o(r4)
            return r5
        L_0x008f:
            if (r6 != 0) goto L_0x009f
            if (r12 == 0) goto L_0x009b
            r12.close()     // Catch:{ Exception -> 0x0097 }
            goto L_0x009b
        L_0x0097:
            r12 = move-exception
            com.vivo.push.util.n.a(r2, r1, r12)
        L_0x009b:
            cn.missfresh.sherlock.trace.core.AppMethodBeat.o(r4)
            return r5
        L_0x009f:
            if (r12 == 0) goto L_0x00bf
            r12.close()     // Catch:{ Exception -> 0x00a5 }
            goto L_0x00bf
        L_0x00a5:
            r12 = move-exception
            com.vivo.push.util.n.a(r2, r1, r12)
            goto L_0x00bf
        L_0x00aa:
            r0 = move-exception
            goto L_0x00c3
        L_0x00ac:
            r0 = move-exception
        L_0x00ad:
            r5 = r12
            goto L_0x00b4
        L_0x00af:
            r0 = move-exception
            r12 = r5
            goto L_0x00c3
        L_0x00b2:
            r0 = move-exception
            r3 = r5
        L_0x00b4:
            java.lang.String r12 = "getSystemPush"
            com.vivo.push.util.n.a(r2, r12, r0)     // Catch:{ all -> 0x00af }
            if (r5 == 0) goto L_0x00bf
            r5.close()
        L_0x00bf:
            cn.missfresh.sherlock.trace.core.AppMethodBeat.o(r4)
            return r3
        L_0x00c3:
            if (r12 == 0) goto L_0x00cd
            r12.close()     // Catch:{ Exception -> 0x00c9 }
            goto L_0x00cd
        L_0x00c9:
            r12 = move-exception
            com.vivo.push.util.n.a(r2, r1, r12)
        L_0x00cd:
            cn.missfresh.sherlock.trace.core.AppMethodBeat.o(r4)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.vivo.push.util.r.b(android.content.Context):java.lang.String");
    }

    private static b c(Context context) {
        AppMethodBeat.i(1675, false);
        String b = b(context);
        ApplicationInfo applicationInfo = null;
        if (TextUtils.isEmpty(b)) {
            AppMethodBeat.o(1675);
            return null;
        }
        b bVar = new b(b);
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(b, 128);
            if (packageInfo != null) {
                bVar.c = packageInfo.versionCode;
                bVar.d = packageInfo.versionName;
                applicationInfo = packageInfo.applicationInfo;
            }
            if (applicationInfo != null) {
                bVar.b = y.a(context, b);
            }
            bVar.e = a(context, bVar.b);
            bVar.f = a(context, b);
            AppMethodBeat.o(1675);
            return bVar;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            n.d("PushPackageUtils", "PackageManager NameNotFoundException is null");
            AppMethodBeat.o(1675);
            return null;
        }
    }

    private static b c(Context context, String str) {
        boolean z = false;
        AppMethodBeat.i(1679, false);
        ApplicationInfo applicationInfo = null;
        if (!TextUtils.isEmpty(str)) {
            if (a(context, str, "com.vivo.pushservice.action.METHOD") || a(context, str, "com.vivo.pushservice.action.RECEIVE")) {
                z = true;
            }
            if (z) {
                b bVar = new b(str);
                try {
                    PackageInfo packageInfo = context.getPackageManager().getPackageInfo(str, 128);
                    if (packageInfo != null) {
                        bVar.c = packageInfo.versionCode;
                        bVar.d = packageInfo.versionName;
                        applicationInfo = packageInfo.applicationInfo;
                    }
                    if (applicationInfo != null) {
                        bVar.b = y.a(context, str);
                    }
                } catch (PackageManager.NameNotFoundException e) {
                    n.a("PushPackageUtils", "getPushPackageInfo exception: ", e);
                }
                bVar.f = a(context, str);
                bVar.e = a(context, bVar.b);
                AppMethodBeat.o(1679);
                return bVar;
            }
        }
        AppMethodBeat.o(1679);
        return null;
    }

    public static boolean a(Context context, String str) {
        AppMethodBeat.i(1684, false);
        if (TextUtils.isEmpty(str) || context == null) {
            AppMethodBeat.o(1684);
            return false;
        }
        Intent intent = new Intent("com.vivo.pushservice.action.PUSH_SERVICE");
        intent.setPackage(str);
        PackageManager packageManager = context.getPackageManager();
        List<ResolveInfo> queryIntentServices = packageManager.queryIntentServices(intent, MetricsProto.MetricsEvent.DIALOG_WIFI_P2P_CANCEL_CONNECT);
        if (queryIntentServices == null || queryIntentServices.size() <= 0) {
            n.a("PushPackageUtils", "isEnablePush error: can not find push service.");
            AppMethodBeat.o(1684);
            return false;
        }
        int size = queryIntentServices.size();
        boolean z = false;
        for (int i = 0; i < size; i++) {
            ResolveInfo resolveInfo = queryIntentServices.get(i);
            if (!(resolveInfo == null || resolveInfo.serviceInfo == null)) {
                String str2 = resolveInfo.serviceInfo.name;
                boolean z2 = resolveInfo.serviceInfo.exported;
                if ("com.vivo.push.sdk.service.PushService".equals(str2) && z2) {
                    boolean z3 = resolveInfo.serviceInfo.enabled;
                    int componentEnabledSetting = packageManager.getComponentEnabledSetting(new ComponentName(str, "com.vivo.push.sdk.service.PushService"));
                    boolean z4 = true;
                    if (componentEnabledSetting != 1 && (componentEnabledSetting != 0 || !z3)) {
                        z4 = false;
                    }
                    z = z4;
                }
            }
        }
        AppMethodBeat.o(1684);
        return z;
    }

    private static boolean a(Context context, long j) {
        AppMethodBeat.i(1687, false);
        d a2 = com.vivo.push.cache.b.a().a(context);
        if (a2 != null) {
            boolean isInBlackList = a2.isInBlackList(j);
            AppMethodBeat.o(1687);
            return isInBlackList;
        }
        AppMethodBeat.o(1687);
        return false;
    }

    public static boolean a(Context context, String str, String str2) {
        List<ResolveInfo> list;
        boolean z = false;
        AppMethodBeat.i(1690, false);
        Intent intent = new Intent(str2);
        intent.setPackage(str);
        try {
            list = context.getPackageManager().queryBroadcastReceivers(intent, MetricsProto.MetricsEvent.DIALOG_WIFI_P2P_CANCEL_CONNECT);
        } catch (Exception unused) {
            list = null;
        }
        if (list != null && list.size() > 0) {
            z = true;
        }
        AppMethodBeat.o(1690);
        return z;
    }

    private static List<String> d(Context context) {
        List<ResolveInfo> list;
        AppMethodBeat.i(1693, false);
        f.a("findAllCoreClientPush");
        ArrayList arrayList = new ArrayList();
        try {
            list = context.getPackageManager().queryIntentServices(new Intent("com.vivo.pushservice.action.PUSH_SERVICE"), MetricsProto.MetricsEvent.DIALOG_WIFI_P2P_CANCEL_CONNECT);
        } catch (Exception unused) {
            list = null;
        }
        if (list != null && list.size() > 0) {
            int size = list.size();
            for (int i = 0; i < size; i++) {
                ResolveInfo resolveInfo = list.get(i);
                if (resolveInfo != null) {
                    String str = resolveInfo.serviceInfo.packageName;
                    if (!TextUtils.isEmpty(str)) {
                        arrayList.add(str);
                    }
                }
            }
        }
        if (arrayList.size() <= 0) {
            n.d("PushPackageUtils", "get all push packages is null");
        }
        AppMethodBeat.o(1693);
        return arrayList;
    }

    public static String b(Context context, String str) {
        AppMethodBeat.i(1696, false);
        if (TextUtils.isEmpty(str) || context == null) {
            AppMethodBeat.o(1696);
            return null;
        }
        try {
            byte[] digest = MessageDigest.getInstance("SHA256").digest(context.getPackageManager().getPackageInfo(str, 64).signatures[0].toByteArray());
            StringBuffer stringBuffer = new StringBuffer();
            for (byte b : digest) {
                String upperCase = Integer.toHexString(b & 255).toUpperCase(Locale.US);
                if (upperCase.length() == 1) {
                    stringBuffer.append("0");
                }
                stringBuffer.append(upperCase);
            }
            String stringBuffer2 = stringBuffer.toString();
            AppMethodBeat.o(1696);
            return stringBuffer2;
        } catch (Exception e) {
            n.a("PushPackageUtils", e);
            AppMethodBeat.o(1696);
            return null;
        }
    }
}
