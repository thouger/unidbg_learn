package cn.missfresh.basiclib.utils.permission;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.text.TextUtils;
import androidx.core.app.AppOpsManagerCompat;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import com.unionpay.tsmservice.mi.data.Constant;

/* compiled from: ManufacturerManager */
public class a {
    public static void a(Context context) {
        Intent intent;
        boolean z = false;
        AppMethodBeat.i(4805, false);
        if (context == null) {
            AppMethodBeat.o(4805);
            return;
        }
        String str = Build.MANUFACTURER;
        if (str.hashCode() != -1675632421 || !str.equals(Constant.DEVICE_XIAOMI)) {
            z = true;
        }
        if (z) {
            intent = c();
        } else {
            intent = a();
        }
        if (intent != null) {
            intent.addFlags(268435456);
            try {
                context.startActivity(intent);
            } catch (Exception unused) {
                Intent c = c();
                c.addFlags(268435456);
                context.startActivity(c);
            }
        }
        AppMethodBeat.o(4805);
    }

    public static Intent a() {
        Intent intent;
        AppMethodBeat.i(4815, false);
        String b = b();
        if (c(b)) {
            intent = new Intent("miui.intent.action.APP_PERM_EDITOR");
            if (b(b)) {
                intent.setClassName("com.miui.securitycenter", "com.miui.permcenter.permissions.AppPermissionsEditorActivity");
            } else {
                intent.setClassName("com.miui.securitycenter", "com.miui.permcenter.permissions.PermissionsEditorActivity");
            }
            intent.putExtra("extra_pkgname", b.a().c());
        } else {
            intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS, Uri.parse("package:" + b.a().c()));
        }
        AppMethodBeat.o(4815);
        return intent;
    }

    private static boolean b(String str) {
        boolean z = false;
        AppMethodBeat.i(4817, false);
        if ("V6".equals(str) || "V7".equals(str)) {
            z = true;
        }
        AppMethodBeat.o(4817);
        return z;
    }

    private static boolean c(String str) {
        AppMethodBeat.i(4820, false);
        boolean z = !TextUtils.equals(str, "V5");
        AppMethodBeat.o(4820);
        return z;
    }

    private static Intent c() {
        AppMethodBeat.i(4840, false);
        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS, Uri.parse("package:" + b.a().c()));
        AppMethodBeat.o(4840);
        return intent;
    }

    /* JADX WARNING: Removed duplicated region for block: B:14:0x0037 A[SYNTHETIC, Splitter:B:14:0x0037] */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x0041 A[SYNTHETIC, Splitter:B:23:0x0041] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String b() {
        /*
            r0 = 4845(0x12ed, float:6.789E-42)
            r1 = 0
            cn.missfresh.sherlock.trace.core.AppMethodBeat.i(r0, r1)
            r1 = 0
            java.lang.Runtime r2 = java.lang.Runtime.getRuntime()     // Catch:{ IOException -> 0x003e, all -> 0x0034 }
            java.lang.String r3 = "getprop ro.miui.ui.version.name"
            java.lang.Process r2 = r2.exec(r3)     // Catch:{ IOException -> 0x003e, all -> 0x0034 }
            java.io.BufferedReader r3 = new java.io.BufferedReader     // Catch:{ IOException -> 0x003e, all -> 0x0034 }
            java.io.InputStreamReader r4 = new java.io.InputStreamReader     // Catch:{ IOException -> 0x003e, all -> 0x0034 }
            java.io.InputStream r2 = r2.getInputStream()     // Catch:{ IOException -> 0x003e, all -> 0x0034 }
            r4.<init>(r2)     // Catch:{ IOException -> 0x003e, all -> 0x0034 }
            r2 = 1024(0x400, float:1.435E-42)
            r3.<init>(r4, r2)     // Catch:{ IOException -> 0x003e, all -> 0x0034 }
            java.lang.String r2 = r3.readLine()     // Catch:{ IOException -> 0x003f, all -> 0x0030 }
            r3.close()     // Catch:{ IOException -> 0x003f, all -> 0x0030 }
            r3.close()     // Catch:{ IOException -> 0x002c }
        L_0x002c:
            cn.missfresh.sherlock.trace.core.AppMethodBeat.o(r0)
            return r2
        L_0x0030:
            r1 = move-exception
            r2 = r1
            r1 = r3
            goto L_0x0035
        L_0x0034:
            r2 = move-exception
        L_0x0035:
            if (r1 == 0) goto L_0x003a
            r1.close()     // Catch:{ IOException -> 0x003a }
        L_0x003a:
            cn.missfresh.sherlock.trace.core.AppMethodBeat.o(r0)
            throw r2
        L_0x003e:
            r3 = r1
        L_0x003f:
            if (r3 == 0) goto L_0x0044
            r3.close()     // Catch:{ IOException -> 0x0044 }
        L_0x0044:
            cn.missfresh.sherlock.trace.core.AppMethodBeat.o(r0)
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.missfresh.basiclib.utils.permission.a.b():java.lang.String");
    }

    public static boolean a(String str) {
        AppMethodBeat.i(4850, false);
        if (TextUtils.equals(Constant.DEVICE_XIAOMI, Build.MANUFACTURER)) {
            if (AppOpsManagerCompat.noteProxyOp(b.a().b(), AppOpsManagerCompat.permissionToOp(str), b.a().c()) != 1 || Build.VERSION.SDK_INT < 23) {
                AppMethodBeat.o(4850);
                return true;
            }
            AppMethodBeat.o(4850);
            return false;
        }
        AppMethodBeat.o(4850);
        return true;
    }
}
