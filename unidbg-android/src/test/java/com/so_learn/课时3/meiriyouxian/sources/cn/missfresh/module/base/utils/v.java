package cn.missfresh.module.base.utils;

import android.app.Application;
import android.content.Context;
import android.os.Build;
import android.os.Environment;
import cn.missfresh.module.base.b.a;
import cn.missfresh.module.base.common.api.IApplicationDelegateService;
import cn.missfresh.module.base.manager.e;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import cn.missfresh.utils.a.d;
import cn.missfresh.utils.c;
import com.alibaba.fastjson.JSONObject;
import com.huawei.hms.support.api.push.PushReceiver;
import com.taobao.accs.common.Constants;
import java.io.File;
import java.io.IOException;

/* compiled from: DeviceTokenUtil */
public class v {
    public static volatile String a = "";

    public static JSONObject a(Context context) {
        AppMethodBeat.i(23279, false);
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("deviceSdcard", b(context));
        jSONObject.put("deviceSharedPreference", b());
        jSONObject.put("deviceSettings", "");
        if (e.p().getUser_id() != -1) {
            jSONObject.put("userId", Integer.valueOf(e.p().getUser_id()));
        }
        jSONObject.put(Constants.KEY_IMEI, r.h());
        jSONObject.put("oaid", r.c);
        jSONObject.put("androidId", r.i());
        jSONObject.put("aaid", r.b);
        jSONObject.put("vaid", r.a);
        jSONObject.put("channel", e.s());
        jSONObject.put("userAgent", a.b());
        jSONObject.put("appVersion", r.d());
        jSONObject.put("originChannel", e.c());
        jSONObject.put("channelTaskId", e.d());
        AppMethodBeat.o(23279);
        return jSONObject;
    }

    public static JSONObject a(Context context, String str) {
        AppMethodBeat.i(23280, false);
        JSONObject a2 = a(context);
        a2.put("deepLinkSource", str);
        AppMethodBeat.o(23280);
        return a2;
    }

    public static String a() {
        AppMethodBeat.i(23281, false);
        if (!cn.missfresh.utils.e.a(a)) {
            String str = a;
            AppMethodBeat.o(23281);
            return str;
        }
        try {
            IApplicationDelegateService iApplicationDelegateService = (IApplicationDelegateService) com.alibaba.android.arouter.b.a.a().a("/common/application_delegate_service").navigation();
            String b = b();
            if (!cn.missfresh.utils.e.a(b)) {
                a = b;
                d.d("DeviceTokenUtil", "deviceToken :" + a);
                String str2 = a;
                AppMethodBeat.o(23281);
                return str2;
            }
            String b2 = b(iApplicationDelegateService.getApplication());
            if (!cn.missfresh.utils.e.a(b2)) {
                a = b2;
                d.d("DeviceTokenUtil", "deviceToken :" + a);
                String str3 = a;
                AppMethodBeat.o(23281);
                return str3;
            }
            d.d("DeviceTokenUtil", "deviceToken :" + a);
            String str4 = a;
            AppMethodBeat.o(23281);
            return str4;
        } catch (Exception e) {
            e.printStackTrace();
            String message = e.getMessage();
            ac.a("deviceInfo", "getDeviceToken", "IOException:" + message);
            AppMethodBeat.o(23281);
            return "";
        }
    }

    public static String b() {
        AppMethodBeat.i(23282, false);
        String aD = e.aD();
        d.d(PushReceiver.BoundKey.DEVICE_TOKEN_KEY, aD);
        if (cn.missfresh.utils.e.a(aD)) {
            aD = "";
        }
        AppMethodBeat.o(23282);
        return aD;
    }

    public static String b(Context context) {
        AppMethodBeat.i(23283, false);
        try {
            String b = c.b(c(context));
            d.d(PushReceiver.BoundKey.DEVICE_TOKEN_KEY, b);
            if (cn.missfresh.utils.e.a(b)) {
                b = "";
            }
            AppMethodBeat.o(23283);
            return b;
        } catch (IOException e) {
            e.printStackTrace();
            String message = e.getMessage();
            ac.a("deviceInfo", "getSdDeviceInfoToken", "IOException:" + message);
            AppMethodBeat.o(23283);
            return "";
        }
    }

    public static void a(long j) {
        AppMethodBeat.i(23284, false);
        if (j == -1) {
            AppMethodBeat.o(23284);
            return;
        }
        a = j + "";
        try {
            IApplicationDelegateService iApplicationDelegateService = (IApplicationDelegateService) com.alibaba.android.arouter.b.a.a().a("/common/application_delegate_service").navigation();
            e.e(j);
            if (c(iApplicationDelegateService.getApplication()) == null) {
                AppMethodBeat.o(23284);
                return;
            }
            Application application = iApplicationDelegateService.getApplication();
            b(application, j + "");
            AppMethodBeat.o(23284);
        } catch (Exception e) {
            e.printStackTrace();
            String message = e.getMessage();
            ac.a("deviceInfo", "saveToken", "IOException:" + message);
        }
    }

    private static File c(Context context) {
        File file;
        AppMethodBeat.i(23285, false);
        if (Build.VERSION.SDK_INT >= 29) {
            file = c.a(context, ".device");
        } else {
            file = new File(Environment.getExternalStorageDirectory(), ".device");
            if (!file.exists() && !file.mkdir()) {
                AppMethodBeat.o(23285);
                return null;
            }
        }
        File file2 = new File(file, ".deviceInfo");
        AppMethodBeat.o(23285);
        return file2;
    }

    /* JADX WARNING: Removed duplicated region for block: B:35:0x0068  */
    /* JADX WARNING: Removed duplicated region for block: B:40:0x0071 A[SYNTHETIC, Splitter:B:40:0x0071] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void b(android.content.Context r5, java.lang.String r6) {
        /*
            r0 = 23288(0x5af8, float:3.2633E-41)
            r1 = 0
            cn.missfresh.sherlock.trace.core.AppMethodBeat.i(r0, r1)
            r1 = 0
            int r2 = android.os.Build.VERSION.SDK_INT     // Catch:{ Exception -> 0x0062 }
            r3 = 29
            java.lang.String r4 = ".device"
            if (r2 < r3) goto L_0x0015
            java.io.File r5 = cn.missfresh.utils.c.a(r5, r4)
            goto L_0x002e
        L_0x0015:
            java.io.File r5 = new java.io.File
            java.io.File r2 = android.os.Environment.getExternalStorageDirectory()
            r5.<init>(r2, r4)
            boolean r2 = r5.exists()
            if (r2 != 0) goto L_0x002e
            boolean r2 = r5.mkdir()
            if (r2 != 0) goto L_0x002e
            cn.missfresh.sherlock.trace.core.AppMethodBeat.o(r0)
            return
        L_0x002e:
            if (r5 != 0) goto L_0x0034
            cn.missfresh.sherlock.trace.core.AppMethodBeat.o(r0)
            return
        L_0x0034:
            java.io.File r2 = new java.io.File
            java.lang.String r3 = ".deviceInfo"
            r2.<init>(r5, r3)
            boolean r5 = r2.exists()
            if (r5 != 0) goto L_0x004c
            boolean r5 = r2.createNewFile()
            if (r5 != 0) goto L_0x004c
            cn.missfresh.sherlock.trace.core.AppMethodBeat.o(r0)
            return
        L_0x004c:
            java.io.FileWriter r5 = new java.io.FileWriter
            r5.<init>(r2)
            r5.write(r6)     // Catch:{ Exception -> 0x005c, all -> 0x0058 }
            r5.close()     // Catch:{ Exception -> 0x006b }
            goto L_0x006b
        L_0x0058:
            r6 = move-exception
            r1 = r5
            r5 = r6
            goto L_0x006f
        L_0x005c:
            r6 = move-exception
            r1 = r5
            r5 = r6
            goto L_0x0063
        L_0x0060:
            r5 = move-exception
            goto L_0x006f
        L_0x0062:
            r5 = move-exception
        L_0x0063:
            r5.printStackTrace()     // Catch:{ all -> 0x0060 }
            if (r1 == 0) goto L_0x006b
            r1.close()
        L_0x006b:
            cn.missfresh.sherlock.trace.core.AppMethodBeat.o(r0)
            return
        L_0x006f:
            if (r1 == 0) goto L_0x0074
            r1.close()     // Catch:{ Exception -> 0x0074 }
        L_0x0074:
            cn.missfresh.sherlock.trace.core.AppMethodBeat.o(r0)
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.missfresh.module.base.utils.v.b(android.content.Context, java.lang.String):void");
    }
}
