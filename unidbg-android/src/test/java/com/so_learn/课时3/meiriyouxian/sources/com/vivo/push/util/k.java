package com.vivo.push.util;

import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: JsonParserUtils */
public final class k {
    public static Map<String, String> a(JSONObject jSONObject) throws JSONException {
        AppMethodBeat.i(997, false);
        HashMap hashMap = new HashMap();
        Iterator<String> keys = jSONObject.keys();
        while (keys.hasNext()) {
            String next = keys.next();
            hashMap.put(next, jSONObject.getString(next));
        }
        AppMethodBeat.o(997);
        return hashMap;
    }
}
