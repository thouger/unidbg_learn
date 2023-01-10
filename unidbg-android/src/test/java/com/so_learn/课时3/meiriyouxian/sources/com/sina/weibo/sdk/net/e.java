package com.sina.weibo.sdk.net;

import android.text.TextUtils;
import com.huawei.hms.framework.common.ContainerUtils;
import com.sina.weibo.sdk.a.d;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.LinkedHashMap;
import java.util.Set;

/* compiled from: WeiboParameters */
public class e {
    private LinkedHashMap<String, Object> a = new LinkedHashMap<>();
    private String b;

    public e(String str) {
        this.b = str;
    }

    public String a() {
        return this.b;
    }

    public void a(String str, String str2) {
        this.a.put(str, str2);
    }

    public Object a(String str) {
        return this.a.get(str);
    }

    public void b(String str) {
        if (this.a.containsKey(str)) {
            this.a.remove(str);
            LinkedHashMap<String, Object> linkedHashMap = this.a;
            linkedHashMap.remove(linkedHashMap.get(str));
        }
    }

    public Set<String> b() {
        return this.a.keySet();
    }

    public String c() {
        StringBuilder sb = new StringBuilder();
        boolean z = true;
        for (String str : this.a.keySet()) {
            if (z) {
                z = false;
            } else {
                sb.append("&");
            }
            Object obj = this.a.get(str);
            if (obj instanceof String) {
                String str2 = (String) obj;
                if (!TextUtils.isEmpty(str2)) {
                    try {
                        sb.append(String.valueOf(URLEncoder.encode(str, "UTF-8")) + ContainerUtils.KEY_VALUE_DELIMITER + URLEncoder.encode(str2, "UTF-8"));
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                }
                d.b("encodeUrl", sb.toString());
            }
        }
        return sb.toString();
    }

    /* JADX WARNING: Removed duplicated region for block: B:4:0x0012  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean d() {
        /*
            r3 = this;
            java.util.LinkedHashMap<java.lang.String, java.lang.Object> r0 = r3.a
            java.util.Set r0 = r0.keySet()
            java.util.Iterator r0 = r0.iterator()
        L_0x000a:
            boolean r1 = r0.hasNext()
            if (r1 != 0) goto L_0x0012
            r0 = 0
            return r0
        L_0x0012:
            java.lang.Object r1 = r0.next()
            java.lang.String r1 = (java.lang.String) r1
            java.util.LinkedHashMap<java.lang.String, java.lang.Object> r2 = r3.a
            java.lang.Object r1 = r2.get(r1)
            boolean r2 = r1 instanceof java.io.ByteArrayOutputStream
            if (r2 != 0) goto L_0x0026
            boolean r1 = r1 instanceof android.graphics.Bitmap
            if (r1 == 0) goto L_0x000a
        L_0x0026:
            r0 = 1
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.sina.weibo.sdk.net.e.d():boolean");
    }
}
