package com.umeng.message.common;

import android.content.Context;
import android.text.TextUtils;
import com.umeng.commonsdk.debug.UMLog;
import com.umeng.message.PushAgent;
import com.umeng.message.proguard.k;
import com.umeng.message.proguard.l;

/* compiled from: Res */
public class d {
    private static final String a = d.class.getName();
    private static d b;
    private static Class e = null;
    private static Class f = null;
    private static Class g = null;
    private static Class h = null;
    private static Class i = null;
    private static Class j = null;
    private static Class k = null;
    private static Class l = null;
    private Context c;
    private String d;

    private d(Context context) {
        this.c = context.getApplicationContext();
        String str = a;
        UMLog.mutlInfo(str, 2, "packageName=" + this.c.getPackageName());
        try {
            StringBuilder sb = new StringBuilder();
            sb.append(!TextUtils.isEmpty(PushAgent.getInstance(this.c).getResourcePackageName()) ? PushAgent.getInstance(this.c).getResourcePackageName() : this.c.getPackageName());
            sb.append(".R$drawable");
            f = Class.forName(sb.toString());
        } catch (ClassNotFoundException e2) {
            UMLog.mutlInfo(a, 0, e2.getMessage());
            UMLog.aq(k.c, 0, "\\|");
        }
        try {
            StringBuilder sb2 = new StringBuilder();
            sb2.append(!TextUtils.isEmpty(PushAgent.getInstance(this.c).getResourcePackageName()) ? PushAgent.getInstance(this.c).getResourcePackageName() : this.c.getPackageName());
            sb2.append(".R$layout");
            g = Class.forName(sb2.toString());
        } catch (ClassNotFoundException e3) {
            UMLog.mutlInfo(a, 0, e3.getMessage());
        }
        try {
            StringBuilder sb3 = new StringBuilder();
            sb3.append(!TextUtils.isEmpty(PushAgent.getInstance(this.c).getResourcePackageName()) ? PushAgent.getInstance(this.c).getResourcePackageName() : this.c.getPackageName());
            sb3.append(".R$id");
            e = Class.forName(sb3.toString());
        } catch (ClassNotFoundException e4) {
            UMLog.mutlInfo(a, 0, e4.getMessage());
        }
        try {
            StringBuilder sb4 = new StringBuilder();
            sb4.append(!TextUtils.isEmpty(PushAgent.getInstance(this.c).getResourcePackageName()) ? PushAgent.getInstance(this.c).getResourcePackageName() : this.c.getPackageName());
            sb4.append(".R$anim");
            h = Class.forName(sb4.toString());
        } catch (ClassNotFoundException e5) {
            UMLog.mutlInfo(a, 0, e5.getMessage());
        }
        try {
            StringBuilder sb5 = new StringBuilder();
            sb5.append(!TextUtils.isEmpty(PushAgent.getInstance(this.c).getResourcePackageName()) ? PushAgent.getInstance(this.c).getResourcePackageName() : this.c.getPackageName());
            sb5.append(".R$style");
            i = Class.forName(sb5.toString());
        } catch (ClassNotFoundException e6) {
            UMLog.mutlInfo(a, 0, e6.getMessage());
        }
        try {
            StringBuilder sb6 = new StringBuilder();
            sb6.append(!TextUtils.isEmpty(PushAgent.getInstance(this.c).getResourcePackageName()) ? PushAgent.getInstance(this.c).getResourcePackageName() : this.c.getPackageName());
            sb6.append(".R$string");
            j = Class.forName(sb6.toString());
        } catch (ClassNotFoundException e7) {
            UMLog.mutlInfo(a, 0, e7.getMessage());
        }
        try {
            StringBuilder sb7 = new StringBuilder();
            sb7.append(!TextUtils.isEmpty(PushAgent.getInstance(this.c).getResourcePackageName()) ? PushAgent.getInstance(this.c).getResourcePackageName() : this.c.getPackageName());
            sb7.append(".R$array");
            k = Class.forName(sb7.toString());
        } catch (ClassNotFoundException e8) {
            UMLog.mutlInfo(a, 0, e8.getMessage());
        }
        try {
            StringBuilder sb8 = new StringBuilder();
            sb8.append(!TextUtils.isEmpty(PushAgent.getInstance(this.c).getResourcePackageName()) ? PushAgent.getInstance(this.c).getResourcePackageName() : this.c.getPackageName());
            sb8.append(".R$raw");
            l = Class.forName(sb8.toString());
        } catch (ClassNotFoundException e9) {
            UMLog.mutlInfo(a, 0, e9.getMessage());
        }
    }

    public static d a(Context context) {
        if (b == null) {
            b = new d(context);
        }
        return b;
    }

    public int a(String str) {
        return a(h, str);
    }

    public int b(String str) {
        return a(e, str);
    }

    public int c(String str) throws Exception {
        return b(e, str);
    }

    public int d(String str) {
        return a(f, str);
    }

    public int e(String str) {
        return a(g, str);
    }

    public int f(String str) throws Exception {
        return b(g, str);
    }

    public int g(String str) {
        return a(i, str);
    }

    public int h(String str) {
        return a(j, str);
    }

    public int i(String str) {
        return a(k, str);
    }

    public int j(String str) {
        return a(l, str);
    }

    private int a(Class<?> cls, String str) {
        if (cls != null) {
            try {
                return cls.getField(str).getInt(str);
            } catch (Exception e2) {
                String str2 = a;
                UMLog.mutlInfo(str2, 0, "getRes(" + cls.getName() + ", " + str + l.t);
                UMLog.mutlInfo(a, 0, "\u83b7\u53d6\u8d44\u6e90\u9519\u8bef\uff0c\u786e\u4fdd\u4f60\u5df2\u7ecf\u628ares\u76ee\u5f55\u4e0b\u7684\u6240\u6709\u8d44\u6e90\u4eceSDK\u62f7\u8d1d\u5230\u4e86\u4f60\u7684\u4e3b\u5de5\u7a0b");
                UMLog.mutlInfo(a, 0, e2.getMessage());
                return -1;
            }
        } else {
            String str3 = a;
            UMLog.mutlInfo(str3, 0, "getRes(null," + str + l.t);
            throw new IllegalArgumentException("ResClass\u672a\u521d\u59cb\u5316\uff0c\u8bf7\u786e\u4fdd\u4f60\u5df2\u7ecf\u6dfb\u52a0\u4e86\u5fc5\u8981\u7684\u8d44\u6e90\u3002\u540c\u65f6\u786e\u4fdd\u4f60\u5728\u6df7\u6dc6\u6587\u4ef6\u4e2d\u6dfb\u52a0\u4e86" + this.c.getPackageName() + ".R$* \u3002 field=" + str);
        }
    }

    private int b(Class<?> cls, String str) throws Exception {
        if (cls != null) {
            int i2 = cls.getField(str).getInt(str);
            String str2 = a;
            UMLog.mutlInfo(str2, 0, "getRes(" + cls.getName() + ", " + str + l.t);
            UMLog.mutlInfo(a, 0, "\u83b7\u53d6\u8d44\u6e90\u9519\u8bef\uff0c\u786e\u4fdd\u4f60\u5df2\u7ecf\u628ares\u76ee\u5f55\u4e0b\u7684\u6240\u6709\u8d44\u6e90\u4eceSDK\u62f7\u8d1d\u5230\u4e86\u4f60\u7684\u4e3b\u5de5\u7a0b");
            return i2;
        }
        String str3 = a;
        UMLog.mutlInfo(str3, 0, "getRes(null," + str + l.t);
        throw new IllegalArgumentException("ResClass\u672a\u521d\u59cb\u5316\uff0c\u8bf7\u786e\u4fdd\u4f60\u5df2\u7ecf\u6dfb\u52a0\u4e86\u5fc5\u8981\u7684\u8d44\u6e90\u3002\u540c\u65f6\u786e\u4fdd\u4f60\u5728\u6df7\u6dc6\u6587\u4ef6\u4e2d\u6dfb\u52a0\u4e86" + this.c.getPackageName() + ".R$* \u3002 field=" + str);
    }

    public void k(String str) {
        this.d = str;
    }

    public String a() {
        if (TextUtils.isEmpty(this.d)) {
            return this.c.getPackageName();
        }
        return this.d;
    }
}
