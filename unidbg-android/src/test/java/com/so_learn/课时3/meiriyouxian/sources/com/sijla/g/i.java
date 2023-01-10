package com.sijla.g;

import android.net.wifi.WifiEnterpriseConfig;
import com.sijla.common.d;
import com.sijla.g.a.c;
import java.io.File;
import java.util.Locale;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

public class i {

    public interface a {
        void a(String str);

        void a(String str, JSONObject jSONObject);
    }

    public static void a(String str, JSONObject jSONObject, a aVar, boolean z) {
        JSONObject jSONObject2;
        com.sijla.g.b.a.a a2 = com.sijla.g.b.a.a().a(str, jSONObject);
        if (aVar != null) {
            int a3 = a2.a();
            if (200 != a3 && 204 != a3) {
                aVar.a(String.format(Locale.getDefault(), "[%d] %s %s", Integer.valueOf(a3), str, (String) a2.b()));
            } else if (z) {
                String str2 = (String) a2.b();
                try {
                    jSONObject2 = new JSONObject(str2);
                } catch (Throwable unused) {
                    jSONObject2 = b.d(str2);
                }
                aVar.a(str, jSONObject2);
            } else {
                aVar.a(str, jSONObject);
            }
        }
    }

    public static boolean a(String str, JSONObject jSONObject) {
        d dVar = new d();
        com.sijla.g.b.a.a a2 = com.sijla.g.b.a.a().a(str, jSONObject);
        int a3 = a2.a();
        if (200 == a3 || 204 == a3) {
            dVar.a(true);
        } else {
            dVar.a(false);
            dVar.a(a2.b());
        }
        return dVar.b();
    }

    public static d a(String str, JSONObject jSONObject, Map<String, File> map) {
        d dVar = new d();
        com.sijla.g.b.a.a a2 = com.sijla.g.b.a.a().a(str, jSONObject, map);
        int a3 = a2.a();
        if (200 == a3 || 204 == a3) {
            dVar.a(true);
        } else {
            dVar.a(false);
            dVar.a(a2.b());
        }
        return dVar;
    }

    public static File a(String str, JSONObject jSONObject, String str2, boolean z) {
        d dVar = new d();
        dVar.a(false);
        File file = new File(str2);
        File parentFile = file.getParentFile();
        if (parentFile != null && !parentFile.exists()) {
            parentFile.mkdirs();
        }
        int a2 = com.sijla.g.b.a.a().b(str, jSONObject, new com.sijla.g.b.b.a.a(file)).a();
        if (a2 == 200 || a2 == 204) {
            dVar.a(true);
        }
        if (204 == a2 && z && z) {
            a(str2);
        }
        if (dVar.b()) {
            return file;
        }
        return null;
    }

    public static File a(String str, String str2) {
        if (b.a(str) || b.a(str2)) {
            return null;
        }
        d dVar = new d();
        dVar.a(false);
        File file = new File(str2);
        File parentFile = file.getParentFile();
        if (parentFile != null && !parentFile.exists()) {
            parentFile.mkdirs();
        }
        int a2 = com.sijla.g.b.a.a().a(str, (JSONObject) null, new com.sijla.g.b.b.a.a(file)).a();
        if (204 == a2) {
            dVar.a(true);
            a(str2);
        }
        if (200 == a2) {
            dVar.a(true);
        }
        if (dVar.b()) {
            return file;
        }
        return null;
    }

    private static void a(String str) {
        File file = new File(str.replace(".gz", ""));
        if (file.exists() && file.isFile()) {
            c.a(WifiEnterpriseConfig.CA_CERT_ALIAS_DELIMITER, file.getAbsolutePath(), true);
            h.a(str + " lastModified:" + d.b(file.lastModified()));
        }
    }

    public static boolean a(String str, JSONArray jSONArray, boolean z, JSONObject jSONObject, Map<String, File> map) {
        if (jSONArray == null || jSONArray.length() == 0 || map == null) {
            return false;
        }
        boolean z2 = false;
        for (int i = 0; i < jSONArray.length(); i++) {
            try {
                String optString = jSONArray.optString(i, "");
                if ((z || !z2) && !b.a(optString) && a(optString, jSONObject, map).b() && !z2) {
                    z2 = true;
                }
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
        return z2;
    }
}
