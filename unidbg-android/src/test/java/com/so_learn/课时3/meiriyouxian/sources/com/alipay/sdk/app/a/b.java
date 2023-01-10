package com.alipay.sdk.app.a;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.Signature;
import android.os.Build;
import android.text.TextUtils;
import com.alipay.sdk.app.a.a;
import com.alipay.sdk.util.e;
import com.huawei.hms.framework.common.ContainerUtils;
import com.huawei.hms.support.hianalytics.HiAnalyticsConstant;
import com.umeng.message.proguard.l;
import com.xiaomi.mipush.sdk.Constants;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.UUID;
import org.json.JSONObject;

public class b {
    private String a;
    private String b;
    private String c;
    private String d;
    private String e;
    private String f;
    private String g;
    private String h = "";
    private String i = "";
    private String j;

    public b(Context context, boolean z) {
        long j;
        context = context != null ? context.getApplicationContext() : context;
        this.a = b();
        this.c = a(context);
        if (z) {
            j = 0;
        } else {
            j = a.c.a(context);
        }
        this.d = a(j);
        this.e = d();
        this.f = b(context);
        this.g = "-";
        this.j = "-";
    }

    /* access modifiers changed from: package-private */
    public void a(String str, String str2, Throwable th) {
        c(str, str2, a(th));
    }

    /* access modifiers changed from: package-private */
    public void a(String str, String str2, Throwable th, String str3) {
        String a = a(th);
        c(str, str2, str3 + ": " + a);
    }

    /* JADX DEBUG: Can't convert new array creation: APUT found in different block: 0x0057: APUT  (r3v2 java.lang.Object[]), (2 ??[int, float, short, byte, char]), (r8v3 java.lang.String) */
    private synchronized void c(String str, String str2, String str3) {
        e.d("mspl", String.format("err %s %s %s", str, str2, str3));
        String str4 = "";
        if (!TextUtils.isEmpty(this.i)) {
            str4 = str4 + "^";
        }
        StringBuilder sb = new StringBuilder();
        sb.append(str4);
        Object[] objArr = new Object[4];
        objArr[0] = str;
        objArr[1] = str2;
        objArr[2] = TextUtils.isEmpty(str3) ? "-" : b(str3);
        objArr[3] = b(a());
        sb.append(String.format("%s,%s,%s,%s", objArr));
        this.i += sb.toString();
    }

    /* access modifiers changed from: package-private */
    public void a(String str, String str2, String str3) {
        c(str, str2, str3);
    }

    /* access modifiers changed from: package-private */
    public void b(String str, String str2, String str3) {
        d("", str, str2 + HiAnalyticsConstant.REPORT_VAL_SEPARATOR + str3);
    }

    /* access modifiers changed from: package-private */
    public void a(String str, String str2) {
        d("", str, str2);
    }

    /* JADX DEBUG: Can't convert new array creation: APUT found in different block: 0x0053: APUT  (r3v2 java.lang.Object[]), (0 ??[int, short, byte, char]), (r9v2 java.lang.String) */
    private synchronized void d(String str, String str2, String str3) {
        e.b("mspl", String.format("event %s %s %s", str, str2, str3));
        String str4 = "";
        if (!TextUtils.isEmpty(this.h)) {
            str4 = str4 + "^";
        }
        StringBuilder sb = new StringBuilder();
        sb.append(str4);
        Object[] objArr = new Object[4];
        objArr[0] = TextUtils.isEmpty(str) ? "-" : b(str);
        objArr[1] = b(str2);
        objArr[2] = b(str3);
        objArr[3] = b(a());
        sb.append(String.format("%s,%s,%s,-,-,-,-,-,-,-,-,-,-,%s", objArr));
        this.h += sb.toString();
    }

    private static String a() {
        return new SimpleDateFormat("HH:mm:ss:SSS", Locale.getDefault()).format(new Date());
    }

    private static String b(String str) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        return str.replace("[", "\u3010").replace("]", "\u3011").replace(l.s, "\uff08").replace(l.t, "\uff09").replace(Constants.ACCEPT_TIME_SEPARATOR_SP, "\uff0c").replace("^", Constants.WAVE_SEPARATOR).replace("#", "\uff03");
    }

    private static String c(String str) {
        return TextUtils.isEmpty(str) ? "-" : str;
    }

    private static String a(Throwable th) {
        if (th == null) {
            return "";
        }
        StringBuffer stringBuffer = new StringBuffer();
        try {
            stringBuffer.append(th.getClass().getName());
            stringBuffer.append(":");
            stringBuffer.append(th.getMessage());
            stringBuffer.append(" \u300b ");
            StackTraceElement[] stackTrace = th.getStackTrace();
            if (stackTrace != null) {
                int i = 0;
                for (StackTraceElement stackTraceElement : stackTrace) {
                    stringBuffer.append(stackTraceElement.toString());
                    stringBuffer.append(" \u300b ");
                    i++;
                    if (i > 5) {
                        break;
                    }
                }
            }
        } catch (Throwable unused) {
        }
        return stringBuffer.toString();
    }

    public String a(String str) {
        this.b = d(str);
        return String.format("[(%s),(%s),(%s),(%s),(%s),(%s),(%s),(%s),(%s),(%s)]", this.a, this.b, this.c, this.d, this.e, this.f, this.g, c(this.h), c(this.i), this.j);
    }

    private static String b() {
        return String.format("%s,%s", c(), new SimpleDateFormat("yyyy-MM-dd-HH:mm:ss").format(new Date()));
    }

    private static String c() {
        try {
            return UUID.randomUUID().toString();
        } catch (Throwable unused) {
            return "12345678uuid";
        }
    }

    private static String d(String str) {
        String str2;
        String str3;
        if (str == null) {
            str = "";
        }
        String[] split = str.split("&");
        String str4 = null;
        if (split != null) {
            str3 = null;
            str2 = null;
            String str5 = null;
            for (String str6 : split) {
                String[] split2 = str6.split(ContainerUtils.KEY_VALUE_DELIMITER);
                if (split2 != null && split2.length == 2) {
                    if (split2[0].equalsIgnoreCase("partner")) {
                        str3 = split2[1].replace("\"", "");
                    } else if (split2[0].equalsIgnoreCase("out_trade_no")) {
                        str2 = split2[1].replace("\"", "");
                    } else if (split2[0].equalsIgnoreCase("trade_no")) {
                        str5 = split2[1].replace("\"", "");
                    } else if (split2[0].equalsIgnoreCase("biz_content")) {
                        try {
                            JSONObject jSONObject = new JSONObject(com.alipay.sdk.util.l.b(com.alipay.sdk.g.a.a(), split2[1]));
                            if (TextUtils.isEmpty(str2)) {
                                str2 = jSONObject.getString("out_trade_no");
                            }
                        } catch (Throwable unused) {
                        }
                    } else if (split2[0].equalsIgnoreCase("app_id") && TextUtils.isEmpty(str3)) {
                        str3 = split2[1];
                    }
                }
            }
            str4 = str5;
        } else {
            str3 = null;
            str2 = null;
        }
        return String.format("%s,%s,-,%s,-,-,-", b(str4), b(str2), b(str3));
    }

    private static String a(Context context) {
        String str;
        String str2 = "-";
        if (context != null) {
            try {
                Context applicationContext = context.getApplicationContext();
                str = applicationContext.getPackageName();
                try {
                    PackageInfo packageInfo = applicationContext.getPackageManager().getPackageInfo(str, 64);
                    str2 = packageInfo.versionName + HiAnalyticsConstant.REPORT_VAL_SEPARATOR + a(packageInfo);
                } catch (Throwable unused) {
                }
            } catch (Throwable unused2) {
            }
            return String.format("%s,%s,-,-,-", b(str), b(str2));
        }
        str = str2;
        return String.format("%s,%s,-,-,-", b(str), b(str2));
    }

    private static String a(PackageInfo packageInfo) {
        String str;
        if (packageInfo == null || packageInfo.signatures == null || packageInfo.signatures.length == 0) {
            return "0";
        }
        try {
            StringBuilder sb = new StringBuilder();
            sb.append(packageInfo.signatures.length);
            for (Signature signature : packageInfo.signatures) {
                try {
                    String a = com.alipay.sdk.util.l.a((com.alipay.sdk.g.a) null, signature.toByteArray());
                    if (TextUtils.isEmpty(a)) {
                        str = "?";
                        sb.append("-");
                        sb.append(str);
                    } else {
                        str = com.alipay.sdk.util.l.f(a).substring(0, 8);
                        sb.append("-");
                        sb.append(str);
                    }
                } catch (Throwable unused) {
                }
            }
            return sb.toString();
        } catch (Throwable unused2) {
            return "?";
        }
    }

    private static String a(long j) {
        String b = b("15.7.9");
        String b2 = b("h.a.3.7.9");
        return String.format("android,3,%s,%s,com.alipay.mcpay,5.0,-,%s,-", b, b2, Constants.WAVE_SEPARATOR + j);
    }

    private static String d() {
        return String.format("%s,%s,-,-,-", b(com.alipay.sdk.h.a.a(com.alipay.sdk.g.b.a().b()).a()), b(com.alipay.sdk.g.b.a().e()));
    }

    private static String b(Context context) {
        return String.format("%s,%s,%s,%s,%s,%s,%s,%s,%s,-", b(com.alipay.sdk.util.b.c(context)), "android", b(Build.VERSION.RELEASE), b(Build.MODEL), "-", b(com.alipay.sdk.util.b.a(context).a()), b(com.alipay.sdk.util.b.b(context).b()), "gw", b(com.alipay.sdk.util.b.a(context).b()));
    }
}
