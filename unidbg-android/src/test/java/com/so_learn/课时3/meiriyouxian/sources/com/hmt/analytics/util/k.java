package com.hmt.analytics.util;

import android.content.Context;
import com.hmt.analytics.android.a;
import com.hmt.analytics.android.i;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: UploadService */
public class k {
    private static final String d = k.class.getSimpleName();
    private JSONObject a = new JSONObject();
    private String b = "";
    private String c = "";
    private Context e;

    public k(Context context, List<e> list, String str, String str2) {
        this.b = str;
        this.c = str2;
        this.e = context.getApplicationContext();
        int size = list.size();
        for (int i = 0; i < size; i++) {
            e eVar = list.get(i);
            try {
                if (this.a.isNull(eVar.b())) {
                    this.a.put(eVar.b(), new JSONArray());
                }
                this.a.getJSONArray(eVar.b()).put(new JSONObject(eVar.c()));
            } catch (JSONException e) {
                String str3 = d;
                a.a(str3, "Collected:" + e.getMessage());
            }
        }
    }

    public boolean a() {
        return b();
    }

    public boolean b() {
        return i.a(this.e, this.b, this.a.toString(), "all_data", this.c);
    }
}
