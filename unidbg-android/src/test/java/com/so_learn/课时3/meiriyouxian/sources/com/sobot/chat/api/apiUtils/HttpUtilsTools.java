package com.sobot.chat.api.apiUtils;

import com.huawei.hms.framework.common.ContainerUtils;
import com.sobot.chat.core.a;
import com.sobot.chat.utils.m;
import java.io.IOException;
import java.util.Map;
import okhttp3.Response;

public class HttpUtilsTools {

    public interface SobotRequestCallBack {
        void onFailure(String str);

        void onSuccess(String str);
    }

    public static void doPost(String str, Map<String, String> map, a.b bVar) {
        a.a().a((Object) null, str, map, bVar);
    }

    public static void doPost(Object obj, String str, Map<String, String> map, a.b bVar) {
        a.a().a(obj, str, map, bVar);
    }

    public static Response doPostSync(Object obj, String str, Map<String, String> map) throws IOException {
        return a.a().a(obj, str, map);
    }

    public static void uploadFile(Object obj, String str, Map<String, String> map, String str2, a.b bVar) {
        if (map != null) {
            StringBuilder sb = new StringBuilder();
            sb.append("sobot---\u8bf7\u6c42\u53c2\u6570\uff1a url = " + str + ", filePath=" + str2 + "  ");
            for (String str3 : map.keySet()) {
                sb.append(str3 + ContainerUtils.KEY_VALUE_DELIMITER + map.get(str3) + ", ");
            }
            m.d(sb.toString().substring(0, sb.toString().length() - 2));
        }
        a.a().a(obj, str, map, str2, bVar);
    }
}
