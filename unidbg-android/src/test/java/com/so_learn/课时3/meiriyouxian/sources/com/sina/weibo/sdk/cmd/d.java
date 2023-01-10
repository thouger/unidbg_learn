package com.sina.weibo.sdk.cmd;

import android.provider.Telephony;
import android.provider.UserDictionary;
import com.sina.weibo.sdk.exception.WeiboException;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: CmdInfo */
class d {
    private static final String a = c.class.getName();
    private List<a> b;
    private List<b> c;
    private int d;

    public d(String str) throws WeiboException {
        a(str);
    }

    private void a(String str) throws WeiboException {
        if (str != null) {
            try {
                JSONObject jSONObject = new JSONObject(str);
                if (jSONObject.has("error") || jSONObject.has(Telephony.TextBasedSmsColumns.ERROR_CODE)) {
                    com.sina.weibo.sdk.a.d.d(a, "load cmd api has error !!!");
                    throw new WeiboException("load cmd api has error !!!");
                }
                JSONObject optJSONObject = jSONObject.optJSONObject("cmd");
                if (optJSONObject != null) {
                    this.d = optJSONObject.optInt(UserDictionary.Words.FREQUENCY);
                    JSONArray optJSONArray = optJSONObject.optJSONArray("app_install");
                    if (optJSONArray != null) {
                        this.b = new ArrayList();
                        for (int i = 0; i < optJSONArray.length(); i++) {
                            this.b.add(new a(optJSONArray.getJSONObject(i)));
                        }
                    }
                    JSONArray optJSONArray2 = optJSONObject.optJSONArray("app_invoke");
                    if (optJSONArray2 != null) {
                        this.c = new ArrayList();
                        for (int i2 = 0; i2 < optJSONArray2.length(); i2++) {
                            this.c.add(new b(optJSONArray2.getJSONObject(i2)));
                        }
                    }
                }
            } catch (JSONException e) {
                com.sina.weibo.sdk.a.d.a(a, "parse NotificationInfo error: " + e.getMessage());
            }
        }
    }

    public List<a> a() {
        return this.b;
    }

    public List<b> b() {
        return this.c;
    }

    public int c() {
        return this.d;
    }
}
