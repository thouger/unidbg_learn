package com.alipay.sdk.app;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.SystemClock;
import android.text.TextUtils;
import cn.missfresh.buttomline.abtest.uitl.FileOpt;
import cn.missfresh.buttomline.logtrace.bean.ConstantKey;
import com.alipay.sdk.app.PayResultActivity;
import com.alipay.sdk.b.a;
import com.alipay.sdk.g.a;
import com.alipay.sdk.g.b;
import com.alipay.sdk.util.e;
import com.alipay.sdk.util.f;
import com.alipay.sdk.util.g;
import com.alipay.sdk.util.j;
import com.alipay.sdk.util.l;
import com.huawei.hms.support.api.entity.core.JosStatusCodes;
import com.huawei.hms.support.hianalytics.HiAnalyticsConstant;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Pattern;
import org.json.JSONObject;

public class PayTask {
    static final Object a = f.class;
    private static long i = 0;
    private static long j = -1;
    private Activity b;
    private com.alipay.sdk.widget.a c;
    private final String d = "wappaygw.alipay.com/service/rest.htm";
    private final String e = "mclient.alipay.com/service/rest.htm";
    private final String f = "mclient.alipay.com/home/exterfaceAssign.htm";
    private final String g = "mclient.alipay.com/cashier/mobilepay.htm";
    private Map<String, a> h = new HashMap();

    public String getVersion() {
        return "15.7.9";
    }

    public PayTask(Activity activity) {
        this.b = activity;
        b.a().a(this.b);
        this.c = new com.alipay.sdk.widget.a(activity, "\u53bb\u652f\u4ed8\u5b9d\u4ed8\u6b3e");
    }

    public synchronized String pay(String str, boolean z) {
        return a(new com.alipay.sdk.g.a(this.b, str, "pay"), str, z);
    }

    private synchronized String a(com.alipay.sdk.g.a aVar, String str, boolean z) {
        Context applicationContext;
        String str2;
        if (b()) {
            com.alipay.sdk.app.a.a.a(aVar, "biz", "RepPay", "");
            return b.d();
        }
        if (z) {
            showLoading();
        }
        if (str.contains("payment_inst=")) {
            String substring = str.substring(str.indexOf("payment_inst=") + 13);
            int indexOf = substring.indexOf(38);
            if (indexOf > 0) {
                substring = substring.substring(0, indexOf);
            }
            a.a(substring.replaceAll("\"", "").toLowerCase(Locale.getDefault()).replaceAll("alipay", ""));
        } else {
            a.a("");
        }
        if (str.contains("service=alipay.acquire.mr.ord.createandpay")) {
            com.alipay.sdk.a.a.c = true;
        }
        if (com.alipay.sdk.a.a.c) {
            if (str.startsWith("https://wappaygw.alipay.com/home/exterfaceAssign.htm?")) {
                str = str.substring(str.indexOf("https://wappaygw.alipay.com/home/exterfaceAssign.htm?") + 53);
            } else if (str.startsWith("https://mclient.alipay.com/home/exterfaceAssign.htm?")) {
                str = str.substring(str.indexOf("https://mclient.alipay.com/home/exterfaceAssign.htm?") + 52);
            }
        }
        String str3 = "";
        try {
            e.b("mspl", "pay prepared: " + str);
            str3 = a(str, aVar);
            e.b("mspl", "pay raw result: " + str3);
            g.a(aVar, this.b.getApplicationContext(), str3);
            com.alipay.sdk.app.a.a.b(aVar, "biz", "PgReturn", "" + SystemClock.elapsedRealtime());
            com.alipay.sdk.app.a.a.b(aVar, "biz", "PgReturnV", j.a(str3, "resultStatus") + HiAnalyticsConstant.REPORT_VAL_SEPARATOR + j.a(str3, "memo"));
            if (!com.alipay.sdk.b.a.p().n()) {
                com.alipay.sdk.b.a.p().a(aVar, this.b.getApplicationContext());
            }
            dismissLoading();
            applicationContext = this.b.getApplicationContext();
            str2 = aVar.a;
        } catch (Throwable th) {
            com.alipay.sdk.app.a.a.b(aVar, "biz", "PgReturn", "" + SystemClock.elapsedRealtime());
            com.alipay.sdk.app.a.a.b(aVar, "biz", "PgReturnV", j.a(str3, "resultStatus") + HiAnalyticsConstant.REPORT_VAL_SEPARATOR + j.a(str3, "memo"));
            if (!com.alipay.sdk.b.a.p().n()) {
                com.alipay.sdk.b.a.p().a(aVar, this.b.getApplicationContext());
            }
            dismissLoading();
            com.alipay.sdk.app.a.a.b(this.b.getApplicationContext(), aVar, str, aVar.a);
            throw th;
        }
        com.alipay.sdk.app.a.a.b(applicationContext, aVar, str, str2);
        e.b("mspl", "pay returning: " + str3);
        return str3;
    }

    public synchronized Map<String, String> payV2(String str, boolean z) {
        com.alipay.sdk.g.a aVar;
        aVar = new com.alipay.sdk.g.a(this.b, str, "payV2");
        return j.a(aVar, a(aVar, str, z));
    }

    public synchronized String fetchTradeToken() {
        return g.a(new com.alipay.sdk.g.a(this.b, "", "fetchTradeToken"), this.b.getApplicationContext());
    }

    public synchronized boolean payInterceptorWithUrl(String str, boolean z, H5PayCallback h5PayCallback) {
        String fetchOrderInfoFromH5PayUrl;
        fetchOrderInfoFromH5PayUrl = fetchOrderInfoFromH5PayUrl(str);
        if (!TextUtils.isEmpty(fetchOrderInfoFromH5PayUrl)) {
            e.b("mspl", "intercepted: " + fetchOrderInfoFromH5PayUrl);
            new Thread(new AnonymousClass1(fetchOrderInfoFromH5PayUrl, z, h5PayCallback)).start();
        }
        return !TextUtils.isEmpty(fetchOrderInfoFromH5PayUrl);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: com.alipay.sdk.app.PayTask$1  reason: invalid class name */
    public class AnonymousClass1 implements Runnable {
        final /* synthetic */ String a;
        final /* synthetic */ boolean b;
        final /* synthetic */ H5PayCallback c;

        AnonymousClass1(String str, boolean z, H5PayCallback h5PayCallback) {
            this.a = str;
            this.b = z;
            this.c = h5PayCallback;
        }

        @Override // java.lang.Runnable
        public void run() {
            com.alipay.sdk.util.a h5Pay = PayTask.this.h5Pay(new com.alipay.sdk.g.a(PayTask.this.b, this.a, "payInterceptorWithUrl"), this.a, this.b);
            e.b("mspl", "inc finished: " + h5Pay.b());
            this.c.onPayResult(h5Pay);
        }
    }

    /* JADX DEBUG: Can't convert new array creation: APUT found in different block: 0x0399: APUT  (r13v0 java.lang.String[]), (1 ??[boolean, int, float, short, byte, char]), (r14v5 java.lang.String) */
    public synchronized String fetchOrderInfoFromH5PayUrl(String str) {
        try {
            if (!TextUtils.isEmpty(str)) {
                String trim = str.trim();
                if (trim.startsWith("https://wappaygw.alipay.com/service/rest.htm") || trim.startsWith("http://wappaygw.alipay.com/service/rest.htm")) {
                    String trim2 = trim.replaceFirst("(http|https)://wappaygw.alipay.com/service/rest.htm\\?", "").trim();
                    if (!TextUtils.isEmpty(trim2)) {
                        String a2 = l.a("<request_token>", "</request_token>", l.b(trim2).get("req_data"));
                        com.alipay.sdk.g.a aVar = new com.alipay.sdk.g.a(this.b, "", "");
                        return "_input_charset=\"utf-8\"&ordertoken=\"" + a2 + "\"&pay_channel_id=\"alipay_sdk\"&bizcontext=\"" + aVar.a("sc", "h5tonative") + "\"";
                    }
                }
                if (trim.startsWith("https://mclient.alipay.com/service/rest.htm") || trim.startsWith("http://mclient.alipay.com/service/rest.htm")) {
                    String trim3 = trim.replaceFirst("(http|https)://mclient.alipay.com/service/rest.htm\\?", "").trim();
                    if (!TextUtils.isEmpty(trim3)) {
                        String a3 = l.a("<request_token>", "</request_token>", l.b(trim3).get("req_data"));
                        com.alipay.sdk.g.a aVar2 = new com.alipay.sdk.g.a(this.b, "", "");
                        return "_input_charset=\"utf-8\"&ordertoken=\"" + a3 + "\"&pay_channel_id=\"alipay_sdk\"&bizcontext=\"" + aVar2.a("sc", "h5tonative") + "\"";
                    }
                }
                if ((trim.startsWith("https://mclient.alipay.com/home/exterfaceAssign.htm") || trim.startsWith("http://mclient.alipay.com/home/exterfaceAssign.htm")) && ((trim.contains("alipay.wap.create.direct.pay.by.user") || trim.contains("create_forex_trade_wap")) && !TextUtils.isEmpty(trim.replaceFirst("(http|https)://mclient.alipay.com/home/exterfaceAssign.htm\\?", "").trim()))) {
                    com.alipay.sdk.g.a aVar3 = new com.alipay.sdk.g.a(this.b, "", "");
                    JSONObject jSONObject = new JSONObject();
                    jSONObject.put("url", str);
                    jSONObject.put("bizcontext", aVar3.a("sc", "h5tonative"));
                    return "new_external_info==" + jSONObject.toString();
                }
                if (Pattern.compile("^(http|https)://(maliprod\\.alipay\\.com/w/trade_pay\\.do.?|mali\\.alipay\\.com/w/trade_pay\\.do.?|mclient\\.alipay\\.com/w/trade_pay\\.do.?)").matcher(str).find()) {
                    String a4 = l.a("?", "", str);
                    if (!TextUtils.isEmpty(a4)) {
                        Map<String, String> b = l.b(a4);
                        StringBuilder sb = new StringBuilder();
                        if (a(false, true, "trade_no", sb, b, "trade_no", "alipay_trade_no")) {
                            a(true, false, "pay_phase_id", sb, b, "payPhaseId", "pay_phase_id", "out_relation_id");
                            sb.append("&biz_sub_type=\"TRADE\"");
                            sb.append("&biz_type=\"trade\"");
                            String str2 = b.get("app_name");
                            if (TextUtils.isEmpty(str2) && !TextUtils.isEmpty(b.get("cid"))) {
                                str2 = "ali1688";
                            } else if (TextUtils.isEmpty(str2) && (!TextUtils.isEmpty(b.get("sid")) || !TextUtils.isEmpty(b.get("s_id")))) {
                                str2 = "tb";
                            }
                            sb.append("&app_name=\"" + str2 + "\"");
                            if (!a(true, true, "extern_token", sb, b, "extern_token", "cid", "sid", "s_id")) {
                                return "";
                            }
                            a(true, false, "appenv", sb, b, "appenv");
                            sb.append("&pay_channel_id=\"alipay_sdk\"");
                            a aVar4 = new a(this, null);
                            aVar4.a(b.get("return_url"));
                            aVar4.c(b.get("show_url"));
                            aVar4.b(b.get("pay_order_id"));
                            com.alipay.sdk.g.a aVar5 = new com.alipay.sdk.g.a(this.b, "", "");
                            String str3 = sb.toString() + "&bizcontext=\"" + aVar5.a("sc", "h5tonative") + "\"";
                            this.h.put(str3, aVar4);
                            return str3;
                        }
                    }
                }
                if (trim.startsWith("https://mclient.alipay.com/cashier/mobilepay.htm") || trim.startsWith("http://mclient.alipay.com/cashier/mobilepay.htm") || (EnvUtils.a() && trim.contains("mobileclientgw.alipaydev.com/cashier/mobilepay.htm"))) {
                    String a5 = new com.alipay.sdk.g.a(this.b, "", "").a("sc", "h5tonative");
                    JSONObject jSONObject2 = new JSONObject();
                    jSONObject2.put("url", trim);
                    jSONObject2.put("bizcontext", a5);
                    return String.format("new_external_info==%s", jSONObject2.toString());
                } else if (com.alipay.sdk.b.a.p().d() && Pattern.compile("^https?://(maliprod\\.alipay\\.com|mali\\.alipay\\.com)/batch_payment\\.do\\?").matcher(trim).find()) {
                    Uri parse = Uri.parse(trim);
                    String queryParameter = parse.getQueryParameter("return_url");
                    String queryParameter2 = parse.getQueryParameter("show_url");
                    String queryParameter3 = parse.getQueryParameter("pay_order_id");
                    String a6 = a(parse.getQueryParameter("trade_nos"), parse.getQueryParameter("alipay_trade_no"));
                    String a7 = a(parse.getQueryParameter("payPhaseId"), parse.getQueryParameter("pay_phase_id"), parse.getQueryParameter("out_relation_id"));
                    String[] strArr = new String[4];
                    strArr[0] = parse.getQueryParameter("app_name");
                    strArr[1] = !TextUtils.isEmpty(parse.getQueryParameter("cid")) ? "ali1688" : "";
                    strArr[2] = !TextUtils.isEmpty(parse.getQueryParameter("sid")) ? "tb" : "";
                    strArr[3] = !TextUtils.isEmpty(parse.getQueryParameter("s_id")) ? "tb" : "";
                    String a8 = a(strArr);
                    String a9 = a(parse.getQueryParameter("extern_token"), parse.getQueryParameter("cid"), parse.getQueryParameter("sid"), parse.getQueryParameter("s_id"));
                    String a10 = a(parse.getQueryParameter("appenv"));
                    if (!TextUtils.isEmpty(a6) && !TextUtils.isEmpty(a8) && !TextUtils.isEmpty(a9)) {
                        String format = String.format("trade_no=\"%s\"&pay_phase_id=\"%s\"&biz_type=\"trade\"&biz_sub_type=\"TRADE\"&app_name=\"%s\"&extern_token=\"%s\"&appenv=\"%s\"&pay_channel_id=\"alipay_sdk\"&bizcontext=\"%s\"", a6, a7, a8, a9, a10, new com.alipay.sdk.g.a(this.b, "", "").a("sc", "h5tonative"));
                        a aVar6 = new a(this, null);
                        aVar6.a(queryParameter);
                        aVar6.c(queryParameter2);
                        aVar6.b(queryParameter3);
                        aVar6.d(a6);
                        this.h.put(format, aVar6);
                        return format;
                    }
                }
            }
        } catch (Throwable th) {
            e.a(th);
        }
        return "";
    }

    private static final String a(String... strArr) {
        if (strArr == null) {
            return "";
        }
        for (String str : strArr) {
            if (!TextUtils.isEmpty(str)) {
                return str;
            }
        }
        return "";
    }

    public static synchronized boolean fetchSdkConfig(Context context) {
        synchronized (PayTask.class) {
            try {
                b.a().a(context);
                long elapsedRealtime = SystemClock.elapsedRealtime() / 1000;
                if (elapsedRealtime - i < ((long) com.alipay.sdk.b.a.p().f())) {
                    return false;
                }
                i = elapsedRealtime;
                com.alipay.sdk.b.a.p().a(com.alipay.sdk.g.a.a(), context.getApplicationContext());
                return true;
            } catch (Exception e) {
                e.a(e);
                return false;
            }
        }
    }

    /* access modifiers changed from: private */
    public class a {
        private String b;
        private String c;
        private String d;
        private String e;

        private a() {
            this.b = "";
            this.c = "";
            this.d = "";
            this.e = "";
        }

        /* synthetic */ a(PayTask payTask, AnonymousClass1 r2) {
            this();
        }

        public String a() {
            return this.b;
        }

        public void a(String str) {
            this.b = str;
        }

        public String b() {
            return this.d;
        }

        public void b(String str) {
            this.d = str;
        }

        public String c() {
            return this.c;
        }

        public void c(String str) {
            this.c = str;
        }

        public String d() {
            return this.e;
        }

        public void d(String str) {
            this.e = str;
        }
    }

    private boolean a(boolean z, boolean z2, String str, StringBuilder sb, Map<String, String> map, String... strArr) {
        String str2;
        int length = strArr.length;
        int i2 = 0;
        while (true) {
            if (i2 >= length) {
                str2 = "";
                break;
            }
            String str3 = strArr[i2];
            if (!TextUtils.isEmpty(map.get(str3))) {
                str2 = map.get(str3);
                break;
            }
            i2++;
        }
        if (TextUtils.isEmpty(str2)) {
            if (z2) {
                return false;
            }
            return true;
        } else if (z) {
            sb.append("&");
            sb.append(str);
            sb.append("=\"");
            sb.append(str2);
            sb.append("\"");
            return true;
        } else {
            sb.append(str);
            sb.append("=\"");
            sb.append(str2);
            sb.append("\"");
            return true;
        }
    }

    public synchronized com.alipay.sdk.util.a h5Pay(com.alipay.sdk.g.a aVar, String str, boolean z) {
        com.alipay.sdk.util.a aVar2;
        aVar2 = new com.alipay.sdk.util.a();
        try {
            String[] split = a(aVar, str, z).split(";");
            HashMap hashMap = new HashMap();
            for (String str2 : split) {
                int indexOf = str2.indexOf("={");
                if (indexOf >= 0) {
                    String substring = str2.substring(0, indexOf);
                    hashMap.put(substring, a(str2, substring));
                }
            }
            if (hashMap.containsKey("resultStatus")) {
                aVar2.b(hashMap.get("resultStatus"));
            }
            aVar2.a(a(str, hashMap));
            if (TextUtils.isEmpty(aVar2.a())) {
                com.alipay.sdk.app.a.a.a(aVar, "biz", "H5CbUrlEmpty", "");
            }
        } catch (Throwable th) {
            com.alipay.sdk.app.a.a.a(aVar, "biz", "H5CbEx", th);
            e.a(th);
        }
        return aVar2;
    }

    /* JADX DEBUG: Can't convert new array creation: APUT found in different block: 0x002e: APUT  (r3v0 java.lang.String[]), (0 ??[int, short, byte, char]), (r5v0 java.lang.String) */
    private String a(String str, Map<String, String> map) throws UnsupportedEncodingException {
        String str2;
        String str3;
        boolean equals = "9000".equals(map.get("resultStatus"));
        String str4 = map.get("result");
        a remove = this.h.remove(str);
        String[] strArr = new String[2];
        if (remove != null) {
            str2 = remove.b();
        } else {
            str2 = "";
        }
        strArr[0] = str2;
        if (remove != null) {
            str3 = remove.d();
        } else {
            str3 = "";
        }
        strArr[1] = str3;
        a(strArr);
        if (map.containsKey("callBackUrl")) {
            return map.get("callBackUrl");
        }
        if (str4.length() > 15) {
            String a2 = a(l.a("&callBackUrl=\"", "\"", str4), l.a("&call_back_url=\"", "\"", str4), l.a("&return_url=\"", "\"", str4), URLDecoder.decode(l.a("&return_url=", "&", str4), FileOpt.ENCODE_STR), URLDecoder.decode(l.a("&callBackUrl=", "&", str4), FileOpt.ENCODE_STR), l.a("call_back_url=\"", "\"", str4));
            if (!TextUtils.isEmpty(a2)) {
                return a2;
            }
        }
        if (remove != null) {
            String a3 = equals ? remove.a() : remove.c();
            if (!TextUtils.isEmpty(a3)) {
                return a3;
            }
        }
        if (remove != null) {
            return com.alipay.sdk.b.a.p().e();
        }
        return "";
    }

    private String a(String str, String str2) {
        String str3 = str2 + "={";
        return str.substring(str.indexOf(str3) + str3.length(), str.lastIndexOf("}"));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: com.alipay.sdk.app.PayTask$2  reason: invalid class name */
    public class AnonymousClass2 implements f.c {
        @Override // com.alipay.sdk.util.f.c
        public void a() {
        }

        AnonymousClass2() {
        }

        @Override // com.alipay.sdk.util.f.c
        public void b() {
            PayTask.this.dismissLoading();
        }
    }

    private f.c a() {
        return new AnonymousClass2();
    }

    public void showLoading() {
        com.alipay.sdk.widget.a aVar = this.c;
        if (aVar != null) {
            aVar.a();
        }
    }

    public void dismissLoading() {
        com.alipay.sdk.widget.a aVar = this.c;
        if (aVar != null) {
            aVar.b();
            this.c = null;
        }
    }

    private String a(String str, com.alipay.sdk.g.a aVar) {
        String a2 = aVar.a(str);
        if (a2.contains("paymethod=\"expressGateway\"")) {
            return a(aVar, a2);
        }
        List<a.C0063a> o = com.alipay.sdk.b.a.p().o();
        if (!com.alipay.sdk.b.a.p().a || o == null) {
            o = a.a;
        }
        if (l.b(aVar, this.b, o)) {
            f fVar = new f(this.b, aVar, a());
            e.b("mspl", "pay inner started: " + a2);
            String a3 = fVar.a(a2);
            e.b("mspl", "pay inner raw result: " + a3);
            fVar.a();
            if (TextUtils.equals(a3, "failed") || TextUtils.equals(a3, "scheme_failed")) {
                com.alipay.sdk.app.a.a.a(aVar, "biz", "LogBindCalledH5");
                return a(aVar, a2);
            } else if (TextUtils.isEmpty(a3)) {
                return b.c();
            } else {
                if (!a3.contains("{\"isLogin\":\"false\"}")) {
                    return a3;
                }
                com.alipay.sdk.app.a.a.a(aVar, "biz", "LogHkLoginByIntent");
                return a(aVar, a2, o, a3, this.b);
            }
        } else {
            com.alipay.sdk.app.a.a.a(aVar, "biz", "LogCalledH5");
            return a(aVar, a2);
        }
    }

    private static String a(com.alipay.sdk.g.a aVar, String str, List<a.C0063a> list, String str2, Activity activity) {
        l.a a2 = l.a(aVar, activity, list);
        if (a2 == null || a2.a(aVar) || a2.a() || !TextUtils.equals(a2.a.packageName, "hk.alipay.wallet")) {
            return str2;
        }
        e.a("mspl", "PayTask not_login");
        String valueOf = String.valueOf(str.hashCode());
        PayResultActivity.a.put(valueOf, new Object());
        Intent intent = new Intent(activity, PayResultActivity.class);
        intent.putExtra("orderSuffix", str);
        intent.putExtra("externalPkgName", activity.getPackageName());
        intent.putExtra("phonecashier.pay.hash", valueOf);
        a.C0066a.a(aVar, intent);
        activity.startActivity(intent);
        synchronized (PayResultActivity.a.get(valueOf)) {
            try {
                e.a("mspl", "PayTask wait");
                PayResultActivity.a.get(valueOf).wait();
            } catch (InterruptedException unused) {
                e.a("mspl", "PayTask interrupted");
                return b.c();
            }
        }
        String str3 = PayResultActivity.a.b;
        e.a("mspl", "PayTask ret: " + str3);
        return str3;
    }

    private String a(com.alipay.sdk.g.a aVar, String str) {
        String a2;
        showLoading();
        c cVar = null;
        try {
            JSONObject c = new com.alipay.sdk.f.a.e().a(aVar, this.b.getApplicationContext(), str).c();
            String optString = c.optString("end_code", null);
            List<com.alipay.sdk.protocol.b> a3 = com.alipay.sdk.protocol.b.a(c.optJSONObject("form").optJSONObject("onload"));
            for (int i2 = 0; i2 < a3.size(); i2++) {
                if (a3.get(i2).a() == com.alipay.sdk.protocol.a.Update) {
                    com.alipay.sdk.protocol.b.a(a3.get(i2));
                }
            }
            a(aVar, c);
            dismissLoading();
            com.alipay.sdk.app.a.a.a(this.b, aVar, str, aVar.a);
            for (int i3 = 0; i3 < a3.size(); i3++) {
                com.alipay.sdk.protocol.b bVar = a3.get(i3);
                if (bVar.a() == com.alipay.sdk.protocol.a.WapPay) {
                    a2 = a(aVar, bVar);
                } else if (bVar.a() == com.alipay.sdk.protocol.a.OpenWeb) {
                    a2 = a(aVar, bVar, optString);
                }
                dismissLoading();
                com.alipay.sdk.app.a.a.a(this.b, aVar, str, aVar.a);
                return a2;
            }
        } catch (IOException e) {
            c b = c.b(c.NETWORK_ERROR.a());
            com.alipay.sdk.app.a.a.a(aVar, ConstantKey.NET, e);
            dismissLoading();
            com.alipay.sdk.app.a.a.a(this.b, aVar, str, aVar.a);
            cVar = b;
        } catch (Throwable th) {
            dismissLoading();
            com.alipay.sdk.app.a.a.a(this.b, aVar, str, aVar.a);
            throw th;
        }
        dismissLoading();
        com.alipay.sdk.app.a.a.a(this.b, aVar, str, aVar.a);
        if (cVar == null) {
            cVar = c.b(c.FAILED.a());
        }
        return b.a(cVar.a(), cVar.b(), "");
    }

    private void a(com.alipay.sdk.g.a aVar, JSONObject jSONObject) {
        try {
            String optString = jSONObject.optString("tid");
            String optString2 = jSONObject.optString("client_key");
            if (!TextUtils.isEmpty(optString) && !TextUtils.isEmpty(optString2)) {
                com.alipay.sdk.h.a.a(b.a().b()).a(optString, optString2);
            }
        } catch (Throwable th) {
            com.alipay.sdk.app.a.a.a(aVar, "biz", "ParserTidClientKeyEx", th);
        }
    }

    private String a(com.alipay.sdk.g.a aVar, com.alipay.sdk.protocol.b bVar, String str) {
        boolean b;
        String a2;
        String[] b2 = bVar.b();
        Intent intent = new Intent(this.b, H5PayActivity.class);
        try {
            JSONObject c = l.c(new String(com.alipay.sdk.c.a.a(b2[2])));
            intent.putExtra("url", b2[0]);
            intent.putExtra("title", b2[1]);
            intent.putExtra("version", "v2");
            intent.putExtra("method", c.optString("method", "POST"));
            b.a(false);
            b.a((String) null);
            a.C0066a.a(aVar, intent);
            this.b.startActivity(intent);
            synchronized (a) {
                try {
                    a.wait();
                    b = b.b();
                    a2 = b.a();
                    b.a(false);
                    b.a((String) null);
                } catch (InterruptedException e) {
                    e.a(e);
                    return b.c();
                }
            }
            String str2 = "";
            if (b) {
                try {
                    List<com.alipay.sdk.protocol.b> a3 = com.alipay.sdk.protocol.b.a(l.c(new String(com.alipay.sdk.c.a.a(a2))));
                    int i2 = 0;
                    while (true) {
                        if (i2 >= a3.size()) {
                            break;
                        }
                        com.alipay.sdk.protocol.b bVar2 = a3.get(i2);
                        if (bVar2.a() == com.alipay.sdk.protocol.a.SetResult) {
                            String[] b3 = bVar2.b();
                            str2 = b.a(Integer.valueOf(b3[1]).intValue(), b3[0], l.b(aVar, b3[2]));
                            break;
                        }
                        i2++;
                    }
                } catch (Throwable th) {
                    e.a(th);
                    com.alipay.sdk.app.a.a.a(aVar, "biz", "H5PayDataAnalysisError", th, a2);
                }
            }
            if (!TextUtils.isEmpty(str2)) {
                return str2;
            }
            try {
                return b.a(Integer.valueOf(str).intValue(), "", "");
            } catch (Throwable th2) {
                com.alipay.sdk.app.a.a.a(aVar, "biz", "H5PayDataAnalysisError", th2, "endCode: " + str);
                return b.a(JosStatusCodes.RTN_CODE_COMMON_ERROR, "", "");
            }
        } catch (Throwable th3) {
            e.a(th3);
            com.alipay.sdk.app.a.a.a(aVar, "biz", "H5PayDataAnalysisError", th3, Arrays.toString(b2));
            return b.c();
        }
    }

    private String a(com.alipay.sdk.g.a aVar, com.alipay.sdk.protocol.b bVar) {
        String[] b = bVar.b();
        Intent intent = new Intent(this.b, H5PayActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("url", b[0]);
        if (b.length == 2) {
            bundle.putString("cookie", b[1]);
        }
        intent.putExtras(bundle);
        a.C0066a.a(aVar, intent);
        this.b.startActivity(intent);
        synchronized (a) {
            try {
                a.wait();
            } catch (InterruptedException e) {
                e.a(e);
                return b.c();
            }
        }
        String a2 = b.a();
        if (TextUtils.isEmpty(a2)) {
            return b.c();
        }
        return a2;
    }

    private static boolean b() {
        long elapsedRealtime = SystemClock.elapsedRealtime();
        if (elapsedRealtime - j < 3000) {
            return true;
        }
        j = elapsedRealtime;
        return false;
    }
}
