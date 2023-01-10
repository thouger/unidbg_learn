package com.alipay.b.a.a.d;

import com.xiaomi.mipush.sdk.Constants;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public final class a {
    private String a;
    private String b;
    private String c;
    private String d;
    private String e;
    private String f;
    private String g;

    public a(String str, String str2, String str3, String str4, String str5, String str6, String str7) {
        this.a = str;
        this.b = str2;
        this.c = str3;
        this.d = str4;
        this.e = str5;
        this.f = str6;
        this.g = str7;
    }

    public final String toString() {
        String str;
        StringBuilder sb;
        String str2;
        StringBuilder sb2;
        String str3;
        StringBuilder sb3;
        StringBuffer stringBuffer = new StringBuffer(new SimpleDateFormat("yyyyMMddHHmmssSSS").format(Calendar.getInstance().getTime()));
        stringBuffer.append(Constants.ACCEPT_TIME_SEPARATOR_SP + this.a);
        stringBuffer.append(Constants.ACCEPT_TIME_SEPARATOR_SP + this.b);
        stringBuffer.append(Constants.ACCEPT_TIME_SEPARATOR_SP + this.c);
        stringBuffer.append(Constants.ACCEPT_TIME_SEPARATOR_SP + this.d);
        if (com.alipay.b.a.a.a.a.a(this.e) || this.e.length() < 20) {
            sb = new StringBuilder(Constants.ACCEPT_TIME_SEPARATOR_SP);
            str = this.e;
        } else {
            sb = new StringBuilder(Constants.ACCEPT_TIME_SEPARATOR_SP);
            str = this.e.substring(0, 20);
        }
        sb.append(str);
        stringBuffer.append(sb.toString());
        if (com.alipay.b.a.a.a.a.a(this.f) || this.f.length() < 20) {
            sb2 = new StringBuilder(Constants.ACCEPT_TIME_SEPARATOR_SP);
            str2 = this.f;
        } else {
            sb2 = new StringBuilder(Constants.ACCEPT_TIME_SEPARATOR_SP);
            str2 = this.f.substring(0, 20);
        }
        sb2.append(str2);
        stringBuffer.append(sb2.toString());
        if (com.alipay.b.a.a.a.a.a(this.g) || this.g.length() < 20) {
            sb3 = new StringBuilder(Constants.ACCEPT_TIME_SEPARATOR_SP);
            str3 = this.g;
        } else {
            sb3 = new StringBuilder(Constants.ACCEPT_TIME_SEPARATOR_SP);
            str3 = this.g.substring(0, 20);
        }
        sb3.append(str3);
        stringBuffer.append(sb3.toString());
        return stringBuffer.toString();
    }
}
