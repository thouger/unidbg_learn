package com.umeng.message.proguard;

import com.huawei.hms.framework.common.ContainerUtils;
import java.net.URLEncoder;
import java.util.Map;
import java.util.Set;

/* compiled from: NetUtil */
public class i {
    public static String a(Map<String, Object> map, String str) {
        String str2;
        if (map == null || map.isEmpty()) {
            return str;
        }
        StringBuilder sb = new StringBuilder(str);
        Set<String> keySet = map.keySet();
        if (!str.endsWith("?")) {
            sb.append("?");
        }
        for (String str3 : keySet) {
            StringBuilder sb2 = new StringBuilder();
            sb2.append(URLEncoder.encode(str3));
            sb2.append(ContainerUtils.KEY_VALUE_DELIMITER);
            if (map.get(str3) == null) {
                str2 = "";
            } else {
                str2 = map.get(str3).toString();
            }
            sb2.append(URLEncoder.encode(str2));
            sb2.append("&");
            sb.append(sb2.toString());
        }
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }
}
