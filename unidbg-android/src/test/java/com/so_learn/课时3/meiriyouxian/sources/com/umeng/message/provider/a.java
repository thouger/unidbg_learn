package com.umeng.message.provider;

import android.content.Context;
import android.net.Uri;
import android.provider.BaseColumns;
import com.umeng.message.common.UmengMessageDeviceConfig;

/* compiled from: MessageConstants */
public class a {
    private static a l;
    public String a = null;
    public Uri b = null;
    public Uri c = null;
    public Uri d = null;
    public Uri e = null;
    public Uri f = null;
    public Uri g = null;
    public Uri h = null;
    public Uri i = null;
    public Uri j = null;
    public Uri k = null;

    private a() {
    }

    public static a a(Context context) {
        if (l == null) {
            l = new a();
            String packageName = UmengMessageDeviceConfig.getPackageName(context);
            a aVar = l;
            aVar.a = packageName + ".umeng.message";
            a aVar2 = l;
            aVar2.b = Uri.parse("content://" + l.a + C0190a.a);
            a aVar3 = l;
            aVar3.c = Uri.parse("content://" + l.a + C0190a.b);
            a aVar4 = l;
            aVar4.d = Uri.parse("content://" + l.a + C0190a.c);
            a aVar5 = l;
            aVar5.e = Uri.parse("content://" + l.a + C0190a.d);
            a aVar6 = l;
            aVar6.f = Uri.parse("content://" + l.a + C0190a.e);
            a aVar7 = l;
            aVar7.g = Uri.parse("content://" + l.a + C0190a.f);
            a aVar8 = l;
            aVar8.h = Uri.parse("content://" + l.a + C0190a.g);
            a aVar9 = l;
            aVar9.i = Uri.parse("content://" + l.a + C0190a.h);
            a aVar10 = l;
            aVar10.j = Uri.parse("content://" + l.a + C0190a.i);
            a aVar11 = l;
            aVar11.k = Uri.parse("content://" + l.a + C0190a.j);
        }
        return l;
    }

    /* compiled from: MessageConstants */
    /* renamed from: com.umeng.message.provider.a$a  reason: collision with other inner class name */
    public static class C0190a implements BaseColumns {
        public static final String a = "/MessageStores/";
        public static final String b = "/MsgTemps/";
        public static final String c = "/MsgAlias/";
        public static final String d = "/MsgAliasDeleteAll/";
        public static final String e = "/MsgLogStores/";
        public static final String f = "/MsgLogIdTypeStores/";
        public static final String g = "/MsgLogStoreForAgoos/";
        public static final String h = "/MsgLogIdTypeStoreForAgoos/";
        public static final String i = "/MsgConfigInfos/";
        public static final String j = "/InAppLogStores/";
        public static final String k = "vnd.android.cursor.dir/vnd.umeng.message";
        public static final String l = "vnd.android.cursor.item/vnd.umeng.message";
        private static final String m = "content://";

        private C0190a() {
        }
    }
}
