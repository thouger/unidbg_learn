package com.umeng.message.entity;

import org.json.JSONException;
import org.json.JSONObject;

public class ULocation {
    private String a;
    private String b;
    private String c;
    private String d;
    private String e;
    private String f;
    private String g;
    private String h;

    public ULocation(JSONObject jSONObject) {
        try {
            this.a = jSONObject.getString("cenx");
            this.b = jSONObject.getString("ceny");
            JSONObject jSONObject2 = jSONObject.getJSONObject("revergeo");
            this.c = jSONObject2.getString("country");
            this.d = jSONObject2.getString("province");
            this.e = jSONObject2.getString("city");
            this.f = jSONObject2.getString("district");
            this.g = jSONObject2.getString("road");
            this.h = jSONObject2.getString("street");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public String getLongitude() {
        return this.a;
    }

    public String getLatitude() {
        return this.b;
    }

    public String getCountry() {
        return this.c;
    }

    public String getProvince() {
        return this.d;
    }

    public String getCity() {
        return this.e;
    }

    public String getDistrict() {
        return this.f;
    }

    public String getRoad() {
        return this.g;
    }

    public String getStreet() {
        return this.h;
    }
}
