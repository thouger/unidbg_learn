package com.sijla.b;

import android.content.Context;
import com.sijla.g.a.c;
import com.sijla.g.b;
import com.sijla.g.i;
import org.json.JSONArray;
import org.json.JSONObject;

class a$1 implements i.a {
    final /* synthetic */ Context a;

    @Override // com.sijla.g.i.a
    public void a(String str) {
    }

    a$1(Context context) {
        this.a = context;
    }

    @Override // com.sijla.g.i.a
    public void a(String str, JSONObject jSONObject) {
        JSONArray optJSONArray;
        if (jSONObject != null && (optJSONArray = jSONObject.optJSONArray("data")) != null && optJSONArray.length() > 0) {
            String a = b.a(optJSONArray);
            c.a(a, com.sijla.g.a.b.f(this.a) + "f7", false);
        }
    }
}
