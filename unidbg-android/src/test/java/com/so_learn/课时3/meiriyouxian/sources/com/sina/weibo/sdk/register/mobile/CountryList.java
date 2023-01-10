package com.sina.weibo.sdk.register.mobile;

import com.sina.weibo.sdk.exception.WeiboException;
import com.taobao.accs.common.Constants;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class CountryList implements Serializable {
    private static final long serialVersionUID = 1;
    public List<Country> countries;

    public CountryList(String str) {
        initFromJsonStr(str);
    }

    private void initFromJsonStr(String str) {
        try {
            initFromJsonObject(new JSONObject(str));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void initFromJsonObject(JSONObject jSONObject) throws WeiboException {
        try {
            this.countries = new ArrayList();
            Iterator<String> keys = jSONObject.keys();
            while (keys.hasNext()) {
                String next = keys.next();
                JSONObject optJSONObject = jSONObject.optJSONObject(next);
                String string = optJSONObject.getString(Constants.KEY_HTTP_CODE);
                JSONArray optJSONArray = optJSONObject.optJSONObject("rule").optJSONArray("mcc");
                String[] strArr = new String[optJSONArray.length()];
                for (int i = 0; i < optJSONArray.length(); i++) {
                    strArr[i] = optJSONArray.getString(i);
                }
                Country country = new Country(next, string);
                country.setMccs(strArr);
                this.countries.add(country);
            }
        } catch (JSONException e) {
            e.printStackTrace();
            throw new WeiboException(e);
        }
    }
}
