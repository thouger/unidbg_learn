package com.sobot.chat.core.http.a;

import com.huawei.hms.framework.common.ContainerUtils;
import com.sobot.chat.core.http.d.e;
import java.util.LinkedHashMap;
import java.util.Map;

/* compiled from: GetBuilder */
public class a extends b {
    public e a() {
        if (this.d != null) {
            this.a = a(this.a, this.d);
        }
        return new com.sobot.chat.core.http.d.a(this.a, this.b, this.d, this.c).b();
    }

    /* access modifiers changed from: protected */
    public String a(String str, Map<String, String> map) {
        StringBuilder sb = new StringBuilder();
        sb.append(str + "?");
        if (map != null && !map.isEmpty()) {
            for (String str2 : map.keySet()) {
                sb.append(str2);
                sb.append(ContainerUtils.KEY_VALUE_DELIMITER);
                sb.append(map.get(str2));
                sb.append("&");
            }
        }
        return sb.deleteCharAt(sb.length() - 1).toString();
    }

    public a a(String str) {
        this.a = str;
        return this;
    }

    public a a(Map<String, String> map) {
        this.d = map;
        return this;
    }

    public a a(String str, String str2) {
        if (this.d == null) {
            this.d = new LinkedHashMap();
        }
        this.d.put(str, str2);
        return this;
    }
}
