package com.hmt.analytics.util;

import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: HParams */
public class a {
    private static final String b = a.class.getSimpleName();
    private JSONObject a = new JSONObject();

    public void a(String str, String str2) {
        try {
            this.a.put(str, str2);
        } catch (JSONException e) {
            com.hmt.analytics.android.a.a(b, e.getMessage());
        }
    }

    public JSONObject a() {
        return this.a;
    }
}
