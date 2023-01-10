package com.alipay.sdk.g;

import android.content.Context;
import com.alipay.sdk.util.e;
import com.ta.utdid2.device.UTDevice;
import java.io.File;

public class b {
    private static b a;
    private Context b;

    private b() {
    }

    public static b a() {
        if (a == null) {
            a = new b();
        }
        return a;
    }

    public Context b() {
        return this.b;
    }

    public void a(Context context) {
        com.alipay.sdk.b.b.a();
        this.b = context.getApplicationContext();
    }

    public com.alipay.sdk.b.b c() {
        return com.alipay.sdk.b.b.a();
    }

    public static boolean d() {
        for (String str : new String[]{"/system/app/Superuser.apk", "/sbin/su", "/system/bin/su", "/system/xbin/su", "/data/local/xbin/su", "/data/local/bin/su", "/system/sd/xbin/su", "/system/bin/failsafe/su", "/data/local/su", "/su/bin/su"}) {
            if (new File(str).exists()) {
                return true;
            }
        }
        return false;
    }

    public String e() {
        try {
            return UTDevice.getUtdid(this.b);
        } catch (Throwable th) {
            e.a(th);
            return "getUtdidEx";
        }
    }
}
