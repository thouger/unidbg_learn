package com.cmic.sso.wy.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import com.cmic.sso.wy.c.b.a;
import com.cmic.sso.wy.c.b.b;
import com.huawei.hms.framework.common.ContainerUtils;
import com.xiaomi.mipush.sdk.Constants;
import org.json.JSONObject;

/* compiled from: UmcConfigUtil */
public class y {
    private static volatile boolean a;

    public static void a(Context context, Bundle bundle) {
        if (a) {
            g.a("UmcConfigUtil", "\u6b63\u5728\u83b7\u53d6\u914d\u7f6e\u4e2d...");
            return;
        }
        a = true;
        a.a(context).a(bundle.getBoolean("isNeedToGetCert", true), bundle, new AnonymousClass1());
    }

    /* compiled from: UmcConfigUtil */
    /* access modifiers changed from: package-private */
    /* renamed from: com.cmic.sso.wy.utils.y$1  reason: invalid class name */
    public static class AnonymousClass1 implements b {
        AnonymousClass1() {
        }

        @Override // com.cmic.sso.wy.c.b.b
        public void a(String str, String str2, JSONObject jSONObject) {
            try {
                if ("103000".equals(str)) {
                    y.b(jSONObject);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            boolean unused = y.a = false;
        }
    }

    /* access modifiers changed from: private */
    public static void b(JSONObject jSONObject) {
        String str;
        String str2;
        String str3;
        String str4;
        String str5;
        String str6;
        String str7;
        String str8;
        String str9;
        String str10;
        String str11;
        SharedPreferences.Editor a2 = q.a();
        try {
            a2.putString("getConfigDate", w.b());
            if (jSONObject.has("client_valid")) {
                a2.putLong("client_valid", System.currentTimeMillis() + (((long) Integer.valueOf(jSONObject.getString("client_valid")).intValue()) * 60 * 60 * 1000));
            }
            if (jSONObject.has("Configlist")) {
                JSONObject jSONObject2 = jSONObject.getJSONObject("Configlist");
                if (jSONObject2.has("CHANGE_HOST")) {
                    String string = jSONObject2.getString("CHANGE_HOST");
                    if (string.contains("M005")) {
                        String[] split = string.split("&");
                        int length = split.length;
                        int i = 0;
                        while (true) {
                            if (i >= length) {
                                str10 = null;
                                break;
                            }
                            str10 = split[i];
                            if (str10.contains("M005")) {
                                break;
                            }
                            i++;
                        }
                        if (!TextUtils.isEmpty(str10)) {
                            if (str10.contains(Constants.ACCEPT_TIME_SEPARATOR_SP)) {
                                str11 = str10.substring(str10.indexOf(ContainerUtils.KEY_VALUE_DELIMITER) + 1, str10.indexOf(Constants.ACCEPT_TIME_SEPARATOR_SP));
                                str = str10.substring(str10.lastIndexOf(ContainerUtils.KEY_VALUE_DELIMITER) + 1);
                            } else if (str10.contains("https") || str10.contains("HTTPS")) {
                                str = str10.substring(str10.lastIndexOf(ContainerUtils.KEY_VALUE_DELIMITER) + 1);
                                str11 = null;
                            } else if (str10.contains("http") || str10.contains("HTTP")) {
                                str11 = str10.substring(str10.lastIndexOf(ContainerUtils.KEY_VALUE_DELIMITER) + 1);
                                str = null;
                            }
                            g.a("UmcConfigUtil", "HTTP:" + str11 + "||||||||HTTPS:" + str);
                            a2.putString("httpHost", str11);
                            a2.putString("httpsHost", str);
                        }
                        str11 = null;
                        str = null;
                        g.a("UmcConfigUtil", "HTTP:" + str11 + "||||||||HTTPS:" + str);
                        a2.putString("httpHost", str11);
                        a2.putString("httpsHost", str);
                    } else {
                        str = null;
                    }
                    if (string.contains("M007")) {
                        String[] split2 = string.split("&");
                        int length2 = split2.length;
                        int i2 = 0;
                        while (true) {
                            if (i2 >= length2) {
                                str9 = null;
                                break;
                            }
                            str9 = split2[i2];
                            if (str9.contains("M007")) {
                                break;
                            }
                            i2++;
                        }
                        str2 = !TextUtils.isEmpty(str9) ? str9.substring(str9.lastIndexOf(ContainerUtils.KEY_VALUE_DELIMITER) + 1) : str9;
                        g.a("UmcConfigUtil", "HTTPS:" + str2);
                        a2.putString("logHost", str2);
                    } else {
                        str2 = null;
                    }
                } else {
                    str2 = null;
                    str = null;
                }
                if (jSONObject2.has("HOST_CERT_INFO")) {
                    String string2 = jSONObject2.getString("HOST_CERT_INFO");
                    String[] split3 = string2.split("&");
                    if (TextUtils.isEmpty(str2) || !str2.contains(":")) {
                        str3 = str2;
                    } else {
                        str3 = str2.substring(0, str2.indexOf(":"));
                        g.a("UmcConfigUtil", "logHostWithoutHost:" + str3);
                    }
                    if (TextUtils.isEmpty(str2) || !string2.contains(str3)) {
                        str4 = null;
                    } else {
                        int length3 = split3.length;
                        int i3 = 0;
                        while (true) {
                            if (i3 >= length3) {
                                str8 = null;
                                break;
                            }
                            str8 = split3[i3];
                            if (!TextUtils.isEmpty(str2) && str8.contains(str3)) {
                                break;
                            }
                            i3++;
                        }
                        str4 = !TextUtils.isEmpty(str8) ? str8.substring(str8.indexOf(":") + 1, str8.indexOf(Constants.ACCEPT_TIME_SEPARATOR_SP)) : str8;
                        g.a("UmcConfigUtil", "log CERT:");
                        a2.putString("https://" + str2 + "/log/logReport", str4);
                    }
                    if (string2.contains("log1.cmpassport.com")) {
                        int length4 = split3.length;
                        int i4 = 0;
                        while (true) {
                            if (i4 >= length4) {
                                str7 = str4;
                                break;
                            }
                            str7 = split3[i4];
                            if (str7.contains("log1.cmpassport.com")) {
                                break;
                            }
                            i4++;
                        }
                        str4 = !TextUtils.isEmpty(str7) ? str7.substring(str7.indexOf(":") + 1, str7.indexOf(Constants.ACCEPT_TIME_SEPARATOR_SP)) : str7;
                        g.a("UmcConfigUtil", "log1 CERT:");
                        a2.putString("https://log1.cmpassport.com:9443/log/logReport", str4);
                    }
                    if (TextUtils.isEmpty(str) || !str.contains(":")) {
                        str5 = str;
                    } else {
                        str5 = str.substring(0, str.indexOf(":"));
                        g.a("UmcConfigUtil", "httpsHostWithoutPort:" + str5);
                    }
                    if (!TextUtils.isEmpty(str) && string2.contains(str5)) {
                        int length5 = split3.length;
                        int i5 = 0;
                        while (true) {
                            if (i5 >= length5) {
                                str6 = str4;
                                break;
                            }
                            str6 = split3[i5];
                            if (!TextUtils.isEmpty(str) && str6.contains(str5)) {
                                break;
                            }
                            i5++;
                        }
                        str4 = !TextUtils.isEmpty(str6) ? str6.substring(str6.indexOf(":") + 1, str6.indexOf(Constants.ACCEPT_TIME_SEPARATOR_SP)) : str6;
                        g.a("UmcConfigUtil", "httpsHost CERT:");
                        a2.putString("https://" + str + "/unisdk", str4);
                    }
                    if (string2.contains("onekey1.cmpassport.com")) {
                        int length6 = split3.length;
                        int i6 = 0;
                        while (true) {
                            if (i6 >= length6) {
                                break;
                            }
                            String str12 = split3[i6];
                            if (str12.contains("onekey1.cmpassport.com")) {
                                str4 = str12;
                                break;
                            }
                            i6++;
                        }
                        if (!TextUtils.isEmpty(str4)) {
                            str4 = str4.substring(str4.indexOf(":") + 1, str4.indexOf(Constants.ACCEPT_TIME_SEPARATOR_SP));
                        }
                        g.a("UmcConfigUtil", "omekey1 CERT:");
                        a2.putString("https://onekey1.cmpassport.com:443/unisdk/", str4);
                    }
                }
                a(jSONObject2, "CLOSE_CERT_VERIFY", "0", a2);
                a(jSONObject2, "CLOSE_FRIEND_WAPKS", "0", a2);
                a(jSONObject2, "CLOSE_LOGS_VERSION", "0", a2);
                a(jSONObject2, "CLOSE_IPV4_LIST", "0", a2);
                a(jSONObject2, "CLOSE_IPV6_LIST", "1", a2);
                a(jSONObject2, "CLOSE_M001_SDKVERSION_LIST", "0", a2);
                a(jSONObject2, "CLOSE_M001_APPID_LIST", "0", a2);
                a(jSONObject2, "CLOSE_M005_SDKVERSION_LIST", "0", a2);
                a(jSONObject2, "CLOSE_M005_APPID_LIST", "0", a2);
                if (jSONObject2.has("LOGS_CONTROL")) {
                    String[] split4 = jSONObject2.getString("LOGS_CONTROL").replace("h", "").split("&");
                    if (split4.length > 0 && !TextUtils.isEmpty(split4[0])) {
                        a2.putInt("maxFailedLogTimes", Integer.parseInt(split4[0]));
                    }
                    if (1 < split4.length && !TextUtils.isEmpty(split4[1])) {
                        a2.putInt("pauseTime", Integer.parseInt(split4[1]));
                    }
                } else {
                    a2.putInt("maxFailedLogTimes", 0);
                    a2.putInt("pauseTime", 0);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } catch (Throwable th) {
            a2.commit();
            throw th;
        }
        a2.commit();
    }

    private static void a(JSONObject jSONObject, String str, String str2, SharedPreferences.Editor editor) {
        if (jSONObject.has(str)) {
            editor.putString(str, jSONObject.optString(str, str2));
        }
    }

    public static boolean a() {
        return System.currentTimeMillis() >= q.b("client_valid", 0);
    }

    public static boolean b() {
        return "1".equals(q.b("CLOSE_IPV4_LIST", "0"));
    }

    public static boolean c() {
        return "1".equals(q.b("CLOSE_IPV6_LIST", "0"));
    }

    public static boolean d() {
        return "1".equals(q.b("CLOSE_M001_SDKVERSION_LIST", "0")) || "1".equals(q.b("CLOSE_M001_APPID_LIST", "0")) || "1".equals(q.b("CLOSE_M005_APPID_LIST", "0")) || "1".equals(q.b("CLOSE_M005_SDKVERSION_LIST", "0"));
    }

    public static String e() {
        String b = q.b("httpHost", (String) null);
        if (TextUtils.isEmpty(b)) {
            return "http://www.cmpassport.com/unisdk/";
        }
        return "http://" + b + "/unisdk";
    }

    public static String f() {
        String b = q.b("httpsHost", (String) null);
        if (TextUtils.isEmpty(b)) {
            return "https://onekey1.cmpassport.com:443/unisdk/";
        }
        return "https://" + b + "/unisdk";
    }

    public static String g() {
        String b = q.b("logHost", "");
        if (TextUtils.isEmpty(b)) {
            return "https://log1.cmpassport.com:9443/log/logReport";
        }
        return "https://" + b + "/log/logReport";
    }

    public static boolean h() {
        return "1".equals(q.b("CLOSE_CERT_VERIFY", "1"));
    }

    public static boolean i() {
        return q.b("CLOSE_FRIEND_WAPKS", "").contains("CU");
    }

    public static boolean j() {
        return q.b("CLOSE_FRIEND_WAPKS", "").contains("CT");
    }

    public static boolean k() {
        return "1".equals(q.b("CLOSE_LOGS_VERSION", "0"));
    }

    public static int l() {
        return q.a("maxFailedLogTimes", 0);
    }

    public static int m() {
        return q.a("pauseTime", 0) * 60 * 60 * 1000;
    }

    public static String a(String str) {
        return q.b(str, "");
    }
}
