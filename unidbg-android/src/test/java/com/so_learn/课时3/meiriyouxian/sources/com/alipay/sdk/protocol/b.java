package com.alipay.sdk.protocol;

import android.text.TextUtils;
import com.alipay.sdk.h.a;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONObject;

public class b {
    private a a;
    private String b;
    private String[] c;

    public b(String str, a aVar) {
        this.b = str;
        this.a = aVar;
    }

    public static void a(b bVar) {
        String[] b = bVar.b();
        if (b.length == 3 && TextUtils.equals("tid", b[0])) {
            a a = a.a(com.alipay.sdk.g.b.a().b());
            if (!TextUtils.isEmpty(b[1]) && !TextUtils.isEmpty(b[2])) {
                a.a(b[1], b[2]);
            }
        }
    }

    public static List<b> a(JSONObject jSONObject) {
        ArrayList arrayList = new ArrayList();
        if (jSONObject == null) {
            return arrayList;
        }
        String[] b = b(jSONObject.optString("name", ""));
        for (int i = 0; i < b.length; i++) {
            a a = a.a(b[i]);
            if (a != a.None) {
                b bVar = new b(b[i], a);
                bVar.c = a(b[i]);
                arrayList.add(bVar);
            }
        }
        return arrayList;
    }

    private static String[] a(String str) {
        ArrayList arrayList = new ArrayList();
        int indexOf = str.indexOf(40);
        int lastIndexOf = str.lastIndexOf(41);
        if (indexOf == -1 || lastIndexOf == -1 || lastIndexOf <= indexOf) {
            return null;
        }
        for (String str2 : str.substring(indexOf + 1, lastIndexOf).split("' *, *'", -1)) {
            arrayList.add(str2.trim().replaceAll("'", "").replaceAll("\"", ""));
        }
        return (String[]) arrayList.toArray(new String[0]);
    }

    private static String[] b(String str) {
        if (!TextUtils.isEmpty(str)) {
            return str.split(";");
        }
        return null;
    }

    public a a() {
        return this.a;
    }

    public String[] b() {
        return this.c;
    }
}
