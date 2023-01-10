package com.cmic.sso.wy.utils;

import android.content.Context;
import android.net.Network;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.os.SystemClock;
import android.text.TextUtils;
import com.cmic.sso.wy.c.a.c;
import com.cmic.sso.wy.c.a.f;
import com.cmic.sso.wy.utils.b;
import com.huawei.hms.framework.common.ContainerUtils;
import java.net.HttpURLConnection;
import javax.net.ssl.HttpsURLConnection;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: HttpUtils */
public class e {
    private Network a;
    private String b;
    private String c = "";
    private int d = 1;
    private String e = null;
    private Bundle f;

    /* compiled from: HttpUtils */
    public interface a {
        void a(String str, String str2);

        void a(String str, String str2, String str3);
    }

    public <T extends f> void a(String str, T t, boolean z, a aVar, String str2, String str3, Bundle bundle) {
        this.f = bundle;
        this.b = str3;
        if (!j.a(str3) || str.contains("logReport") || str.contains("Config")) {
            g.c("HttpUtils", "\u4f7f\u7528wifi\u4e0b\u53d6\u53f7\uff1f\uff1f\uff1f\uff1f\uff1f\uff1f\uff1f" + z);
            if (z) {
                a(str, t, aVar, str2);
                return;
            }
            if (str.contains("getPrePhonescrip")) {
                a((c) t, bundle);
            }
            a(str, t.a().toString(), aVar, null, str2);
        }
    }

    private void a(c cVar, Bundle bundle) {
        String a2 = z.a(true);
        String a3 = z.a(true, "1".equals(bundle.getString("operatorType", "")));
        bundle.putString("ipv4_list", a2);
        bundle.putString("ipv6_list", a3);
        c.a b = cVar.b();
        if (!bundle.getBoolean("isCloseIpv4", false)) {
            b.a(a2);
        }
        if (!bundle.getBoolean("isCloseIpv6", false)) {
            b.b(a3);
        }
        b.v(b.w(bundle.getString("appkey")));
        cVar.a(b);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:105:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:106:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:107:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:108:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:109:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:110:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:64:0x0198, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:65:0x0199, code lost:
        r1 = r0;
        r14 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:66:0x019d, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:72:0x01af, code lost:
        r17.f.putBoolean("isNeedToGetCert", true);
        a(null, java.lang.Integer.valueOf("200072").intValue(), r20, null, r21, r19, r18, r22);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:75:0x01d7, code lost:
        a(null, java.lang.Integer.valueOf("200050").intValue(), r20, null, r21, r19, r18, r22);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:76:0x01f3, code lost:
        if (r16 != null) goto L_0x01f5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:78:?, code lost:
        r16.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:79:0x01f9, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:81:0x01fb, code lost:
        if (r14 != null) goto L_0x01fd;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:82:0x01fd, code lost:
        r14.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:83:0x0201, code lost:
        com.cmic.sso.wy.d.a.a.add(r0);
        r0.printStackTrace();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:84:0x020a, code lost:
        a(null, -1, r20, null, r21, r19, r18, r22);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:85:0x021c, code lost:
        if (r16 != null) goto L_0x021e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:87:?, code lost:
        r16.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:88:0x0222, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:90:0x0224, code lost:
        if (r14 != null) goto L_0x0226;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:91:0x0226, code lost:
        r14.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:92:0x022a, code lost:
        com.cmic.sso.wy.d.a.a.add(r0);
        r0.printStackTrace();
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:101:0x0241 A[Catch:{ IOException -> 0x023d }] */
    /* JADX WARNING: Removed duplicated region for block: B:64:0x0198 A[ExcHandler: all (r0v11 'th' java.lang.Throwable A[CUSTOM_DECLARE]), Splitter:B:8:0x003a] */
    /* JADX WARNING: Removed duplicated region for block: B:72:0x01af A[Catch:{ all -> 0x0233 }] */
    /* JADX WARNING: Removed duplicated region for block: B:75:0x01d7 A[Catch:{ all -> 0x0233 }] */
    /* JADX WARNING: Removed duplicated region for block: B:84:0x020a  */
    /* JADX WARNING: Removed duplicated region for block: B:96:0x0239 A[SYNTHETIC, Splitter:B:96:0x0239] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void a(java.lang.String r18, java.lang.String r19, com.cmic.sso.wy.utils.e.a r20, android.net.Network r21, java.lang.String r22) {
        /*
        // Method dump skipped, instructions count: 590
        */
        throw new UnsupportedOperationException("Method not decompiled: com.cmic.sso.wy.utils.e.a(java.lang.String, java.lang.String, com.cmic.sso.wy.utils.e$a, android.net.Network, java.lang.String):void");
    }

    private static void a(HttpsURLConnection httpsURLConnection, Bundle bundle, String str) {
        if (!bundle.getBoolean("CLOSE_CERT_VERIFY", true) && !str.contains("https://config.cmpassport.com/client/uniConfig")) {
            httpsURLConnection.setSSLSocketFactory(new com.cmic.sso.wy.c.c.a(bundle).a().getSocketFactory());
        }
    }

    private <T extends f> void a(String str, T t, a aVar, String str2) {
        g.b("HttpUtils", "in  wifiNetwork");
        b a2 = b.a((Context) null);
        int i = 0;
        if (Build.VERSION.SDK_INT >= 21) {
            this.a = null;
            a2.a(new AnonymousClass1());
            while (this.a == null) {
                i++;
                SystemClock.sleep(50);
                g.b("HttpUtils", "waiting for newtwork");
                if (i > 60) {
                    aVar.a("200024", "\u6570\u636e\u7f51\u7edc\u5207\u6362\u5931\u8d25", this.c);
                    return;
                }
            }
            this.c = w.a();
            if (str.contains("getPrePhonescrip")) {
                a((c) t, this.f);
            }
            a(str, t.a().toString(), aVar, this.a, str2);
            return;
        }
        b.a.startUsingNetworkFeature(0, "enableHIPRI");
        while (true) {
            if (i >= 30) {
                break;
            }
            try {
                if (b.a.getNetworkInfo(5).getState().compareTo(NetworkInfo.State.CONNECTED) == 0) {
                    break;
                }
                SystemClock.sleep(1000);
                i++;
            } catch (Exception e) {
                com.cmic.sso.wy.d.a.a.add(e);
                g.a("HttpUtils", "check hipri failed");
                aVar.a("200024", "\u6570\u636e\u7f51\u7edc\u5207\u6362\u5931\u8d25", this.c);
                return;
            }
        }
        boolean requestRouteToHost = b.a.requestRouteToHost(5, b.a(b.b(str)));
        g.a("HttpUtils", "\u5207\u6362\u6570\u636e\u7f51\u7edc\u7ed3\u679c >>> " + requestRouteToHost);
        if (requestRouteToHost) {
            g.a("HttpUtils", "\u5207\u6362\u7f51\u7edc\u6210\u529f");
            this.c = w.a();
            if (str.contains("getPrePhonescrip")) {
                a((c) t, this.f);
            }
            a(str, t.a().toString(), aVar, null, str2);
            return;
        }
        g.a("HttpUtils", "\u5207\u6362\u7f51\u7edc\u5931\u8d25or\u65e0\u6570\u636e\u7f51\u7edc");
        aVar.a("200024", "\u6570\u636e\u7f51\u7edc\u5207\u6362\u5931\u8d25", this.c);
    }

    /* compiled from: HttpUtils */
    /* access modifiers changed from: package-private */
    /* renamed from: com.cmic.sso.wy.utils.e$1  reason: invalid class name */
    public class AnonymousClass1 implements b.a {
        AnonymousClass1() {
        }

        @Override // com.cmic.sso.wy.utils.b.a
        public void a(Network network) {
            e.this.a = network;
            g.b("HttpUtils", "onAvailable");
        }
    }

    private void a(String str, int i, a aVar, HttpURLConnection httpURLConnection, Network network, String str2, String str3, String str4) {
        String str5;
        String str6;
        JSONException e;
        String str7;
        String str8 = str;
        if (i == 302 || i == 301) {
            String string = this.f.getString("interfacecode", "");
            this.f.putString("interfacecode", string + i + ";");
            String headerField = httpURLConnection.getHeaderField("Location");
            g.b("HttpUtils", "Location head =" + headerField);
            if (this.e == null) {
                this.e = httpURLConnection.getHeaderField("pplocation");
            }
            if (TextUtils.isEmpty(headerField) || this.e == null) {
                aVar.a("200021", "\u6570\u636e\u89e3\u6790\u5f02\u5e38", this.c);
                return;
            }
            String string2 = this.f.getString("interfacetype", "");
            if ("2".equals(this.f.getString("operatorType", "0"))) {
                this.f.putString("interfacetype", string2 + "getUnicomMobile;");
            } else {
                this.f.putString("interfacetype", string2 + "getTelecomMobile;");
            }
            a(headerField, "", aVar, network, "POST");
        } else if (i != 200) {
            try {
                g.a("HttpUtils", "http response code is not 200 ---" + i);
                this.d = this.d + 1;
                if (this.d <= 3) {
                    if (j.a(this.b)) {
                        if (str3.contains("logReport")) {
                        }
                    }
                    a(str3, str2, aVar, network, str4);
                    return;
                }
                if (i == 0) {
                    aVar.a(i + "", "\u8bf7\u6c42\u51fa\u9519", this.c);
                } else if (i == Integer.valueOf("200050").intValue()) {
                    aVar.a("200050", "EOF\u5f02\u5e38", this.c);
                } else if (i == Integer.valueOf("200072").intValue()) {
                    aVar.a("200072", "ca\u6839\u8bc1\u4e66\u6821\u9a8c\u5931\u8d25", this.c);
                } else if (i == Integer.valueOf("102507").intValue()) {
                    aVar.a(i + "", str8, this.c);
                } else {
                    if (TextUtils.isEmpty(str)) {
                        str7 = "\u7f51\u7edc\u5f02\u5e38";
                    } else {
                        str7 = str8;
                    }
                    aVar.a("200028", str7, this.c);
                }
            } catch (Exception e2) {
                com.cmic.sso.wy.d.a.a.add(e2);
                if (TextUtils.isEmpty(str)) {
                    str8 = "\u7f51\u7edc\u5f02\u5e38";
                }
                aVar.a("200028", str8, this.c);
            }
        } else if (!TextUtils.isEmpty(this.e)) {
            try {
                g.c("HttpUtils", "\u5f02\u7f51\u53d6\u53f7\u7ed3\u679c = " + str8);
                JSONObject jSONObject = new JSONObject(str8);
                String optString = jSONObject.optString("result", "0");
                String string3 = this.f.getString("interfacecode", "");
                this.f.putString("interfacecode", string3 + optString + ";");
                if (TextUtils.isEmpty(jSONObject.getString("result")) || !"0".equals(jSONObject.getString("result"))) {
                    str5 = "\u53d6\u53f7\u63a5\u53e3\u5931\u8d25";
                    str6 = "200039";
                    try {
                        aVar.a(str6, str5, this.c);
                    } catch (JSONException e3) {
                        e = e3;
                    }
                } else {
                    g.c("HttpUtils", "pplocation=" + this.e);
                    String substring = this.e.substring(this.e.indexOf("?") + 1);
                    JSONObject jSONObject2 = new JSONObject();
                    jSONObject2.put(substring.split(ContainerUtils.KEY_VALUE_DELIMITER)[0], substring.split(ContainerUtils.KEY_VALUE_DELIMITER)[1]);
                    jSONObject2.put("data", str8);
                    this.e = this.e.substring(1, this.e.lastIndexOf("?"));
                    String str9 = "http://www.cmpassport.com/unisdk/" + this.e;
                    String string4 = this.f.getString("interfacetype", "");
                    this.f.putString("interfacetype", string4 + "getNewTelecomPhoneNumberNotify;");
                    this.e = null;
                    g.c("HttpUtils", "location" + str9);
                    a(str9, jSONObject2.toString(), aVar, network, "POST");
                }
            } catch (JSONException e4) {
                e = e4;
                str5 = "\u53d6\u53f7\u63a5\u53e3\u5931\u8d25";
                str6 = "200039";
                com.cmic.sso.wy.d.a.a.add(e);
                aVar.a(str6, str5, this.c);
            }
        } else {
            aVar.a(str8, this.c);
        }
    }
}
