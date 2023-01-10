package com.cmic.sso.wy.b;

import android.os.Bundle;
import android.provider.Telephony;
import android.util.Log;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: AuthnResult */
/* access modifiers changed from: package-private */
public class g {
    public static JSONObject a(String str, String str2) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("resultCode", str);
            jSONObject.put("resultDesc", str2);
        } catch (JSONException e) {
            Log.e("AuthnResult", "JSONException", e);
        }
        return jSONObject;
    }

    static JSONObject a(String str, String str2, Bundle bundle, JSONObject jSONObject) {
        String str3;
        String str4 = "0";
        JSONObject jSONObject2 = new JSONObject();
        try {
            int intValue = Integer.valueOf(bundle.getString(Telephony.Carriers.AUTH_TYPE, str4)).intValue();
            int i = bundle.getInt("networkType");
            if (intValue == 2) {
                str4 = "7";
                str3 = "\u77ed\u4fe1\u9a8c\u8bc1\u7801";
            } else if (intValue != 3) {
                if (intValue != 4) {
                    str3 = "\u5176\u4ed6";
                } else {
                    str4 = "3";
                    str3 = "\u77ed\u4fe1\u4e0a\u884c";
                }
            } else if (i == 3) {
                str3 = "WIFI\u4e0b\u7f51\u5173\u9274\u6743";
                str4 = "1";
            } else {
                str3 = "\u7f51\u5173\u9274\u6743";
                str4 = "2";
            }
            jSONObject2.put("resultCode", str);
            jSONObject2.put("authType", str4 + "");
            jSONObject2.put("authTypeDes", str3);
            if ("103000".equals(str)) {
                if (1 == bundle.getInt("logintype", 0)) {
                    jSONObject2.put("openId", bundle.getString("openId"));
                }
                jSONObject2.put("token", jSONObject.optString("token"));
            } else {
                jSONObject2.put("resultDesc", str2);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        com.cmic.sso.wy.utils.g.b("AuthnResult", "\u8fd4\u56de\u53c2\u6570:" + jSONObject2.toString());
        return jSONObject2;
    }
}
