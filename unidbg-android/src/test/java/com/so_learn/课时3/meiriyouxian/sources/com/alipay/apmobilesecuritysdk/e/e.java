package com.alipay.apmobilesecuritysdk.e;

import android.content.Context;
import com.alipay.apmobilesecuritysdk.f.a;
import com.taobao.accs.common.Constants;
import com.unionpay.tsmservice.mi.data.Constant;
import org.json.JSONObject;

public final class e {
    public static f a(Context context) {
        if (context == null) {
            return null;
        }
        String a = a.a(context, "device_feature_prefs_name", "device_feature_prefs_key");
        if (com.alipay.b.a.a.a.a.a(a)) {
            a = a.a("device_feature_file_name", "device_feature_file_key");
        }
        if (com.alipay.b.a.a.a.a.a(a)) {
            return null;
        }
        try {
            JSONObject jSONObject = new JSONObject(a);
            f fVar = new f();
            fVar.a(jSONObject.getString(Constants.KEY_IMEI));
            fVar.b(jSONObject.getString(Constants.KEY_IMSI));
            fVar.c(jSONObject.getString(Constant.KEY_MAC));
            fVar.d(jSONObject.getString("bluetoothmac"));
            fVar.e(jSONObject.getString("gsi"));
            return fVar;
        } catch (Exception e) {
            com.alipay.apmobilesecuritysdk.c.a.a(e);
            return null;
        }
    }
}
