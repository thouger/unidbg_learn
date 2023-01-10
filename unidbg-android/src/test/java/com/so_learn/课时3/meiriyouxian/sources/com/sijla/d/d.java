package com.sijla.d;

import android.content.Context;
import android.telecom.Logging.Session;
import android.text.TextUtils;
import com.sijla.g.a.a;
import com.sijla.g.a.b;
import com.sijla.g.a.c;
import com.sijla.g.g;
import com.sijla.g.i;
import com.sijla.g.j;
import java.io.File;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class d extends c {
    private final String b;
    private String c = (this.b + "qfs.js-" + com.sijla.g.d.c());
    private Context d;

    public d(Context context) {
        this.d = context;
        this.b = b.a(context) + "qfs/";
    }

    public void a(JSONObject jSONObject) {
        JSONObject b = b(jSONObject);
        JSONArray jSONArray = new JSONArray();
        File file = new File(this.c);
        if (file.exists()) {
            try {
                jSONArray = new JSONArray(com.sijla.g.b.b(com.sijla.g.b.a(file)));
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
        if (b != null) {
            jSONArray.put(b);
            c.a(this.c, jSONArray.toString());
        }
    }

    public void a(String str) {
        try {
            if (a.h(this.d) && com.sijla.g.b.a(this.d, "qfs_time_dur", com.sijla.b.c.a.optLong("timepost", 60))) {
                b();
                c();
                a(d());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void b() {
        String c = com.sijla.g.d.c();
        File[] c2 = c.c(this.b);
        if (c2 != null && c2.length > 0) {
            for (File file : c2) {
                if (!file.getName().contains(c)) {
                    c.a(file);
                }
            }
        }
    }

    private void a(List<File> list) {
        if (list != null && list.size() > 0) {
            HashMap hashMap = new HashMap();
            for (File file : list) {
                if (file.exists()) {
                    hashMap.put(file.getName(), file);
                }
            }
            JSONArray optJSONArray = com.sijla.b.c.a.optJSONArray("fdurls");
            if (optJSONArray != null) {
                boolean z = false;
                if (1 == com.sijla.b.c.a.optInt("repeatReportst", 0)) {
                    z = true;
                }
                i.a("QFS", optJSONArray, z, new JSONObject(), hashMap);
            }
            b(list);
        }
    }

    private void c() {
        File file = new File(this.c);
        if (file.exists()) {
            try {
                JSONArray jSONArray = new JSONArray(com.sijla.g.b.b(com.sijla.g.b.a(file)));
                int length = jSONArray.length();
                if (length > 0) {
                    StringBuilder sb = new StringBuilder();
                    for (int i = 0; i < length; i++) {
                        sb.append(jSONArray.optJSONObject(i).toString());
                        sb.append("\n");
                    }
                    String str = this.b + "qfs_" + j.b(this.d) + Session.SESSION_SEPARATION_CHAR_CHILD + com.sijla.g.d.d() + Session.SESSION_SEPARATION_CHAR_CHILD + (com.sijla.g.b.c()[0] + "");
                    c.a(sb.toString(), str, true);
                    g.a(str, true);
                }
            } catch (Throwable th) {
                file.delete();
                throw th;
            }
            file.delete();
        }
    }

    private JSONObject b(JSONObject jSONObject) {
        if (jSONObject == null || jSONObject.length() <= 0) {
            return null;
        }
        String optString = jSONObject.optString("s1", "");
        if (TextUtils.isEmpty(optString)) {
            return null;
        }
        try {
            String substring = com.sijla.g.a.d.a(optString).substring(0, 8);
            JSONObject jSONObject2 = new JSONObject();
            Iterator<String> keys = jSONObject.keys();
            while (keys.hasNext()) {
                String next = keys.next();
                if (next != null && !"s1".equals(next)) {
                    if (!"e".equals(next)) {
                        try {
                            jSONObject2.put(next, com.sijla.c.b.b(substring, jSONObject.optString(next)));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
            return jSONObject2;
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    private List<File> d() {
        File[] a = c.a(this.b, new 1(this));
        if (a == null || a.length <= 0) {
            return null;
        }
        return Arrays.asList(a);
    }

    private void b(List<File> list) {
        c.a(list);
    }

    @Override // com.sijla.d.c
    public void m() {
        a("onScreenOff");
    }

    @Override // com.sijla.d.c
    public void l() {
        a("onKeyGuardGone");
    }

    @Override // com.sijla.d.c
    public void a() {
        super.a();
        a("onCurrentAppForeground");
    }

    @Override // com.sijla.d.c
    public void a(long j) {
        super.a(j);
        a("onCurrentAppBackGround dur = " + j);
    }
}
