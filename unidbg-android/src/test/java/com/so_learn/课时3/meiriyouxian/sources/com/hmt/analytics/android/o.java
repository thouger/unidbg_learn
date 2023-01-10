package com.hmt.analytics.android;

import android.content.Context;
import android.database.sqlite.SQLiteException;
import java.util.Iterator;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: SaveInfoExec */
public class o {
    private static final String a = o.class.getSimpleName();

    public static void a(Context context, JSONObject jSONObject, String str) {
        a.a(a, "saveExec 3");
        Iterator<String> keys = jSONObject.keys();
        while (keys.hasNext()) {
            String next = keys.next();
            try {
                m a2 = m.a(context);
                JSONArray jSONArray = jSONObject.getJSONArray(next);
                int length = jSONArray.length();
                for (int i = 0; i < length; i++) {
                    a2.a(next, jSONArray.get(i).toString(), str);
                }
            } catch (JSONException e) {
                String str2 = a;
                a.a(str2, "Collected:" + e.getMessage());
            } catch (SQLiteException e2) {
                String str3 = a;
                a.a(str3, "Collected:" + e2.getMessage());
            }
        }
    }
}
