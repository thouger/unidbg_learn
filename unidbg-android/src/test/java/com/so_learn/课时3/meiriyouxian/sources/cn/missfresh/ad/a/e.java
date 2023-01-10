package cn.missfresh.ad.a;

import android.text.TextUtils;
import cn.missfresh.ad.b.a;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import java.io.IOException;
import java.util.Iterator;
import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: ParamsManger */
public class e {
    public static RequestBody a(JSONObject jSONObject) {
        AppMethodBeat.i(6136, false);
        if (jSONObject == null || jSONObject.length() == 0) {
            AppMethodBeat.o(6136);
            return null;
        }
        try {
            RequestBody b = b(jSONObject);
            AppMethodBeat.o(6136);
            return b;
        } catch (IOException e) {
            a.a("MFADSDK", e.getMessage());
            AppMethodBeat.o(6136);
            return null;
        } catch (JSONException e2) {
            a.a("MFADSDK", e2.getMessage());
            AppMethodBeat.o(6136);
            return null;
        }
    }

    public static void a(Request.Builder builder, JSONObject jSONObject) {
        AppMethodBeat.i(6138, false);
        if (builder == null || jSONObject == null || jSONObject.length() == 0) {
            AppMethodBeat.o(6138);
            return;
        }
        try {
            Iterator<String> keys = jSONObject.keys();
            while (keys.hasNext()) {
                String next = keys.next();
                builder.addHeader(next, jSONObject.getString(next));
            }
        } catch (JSONException e) {
            a.a("MFADSDK", e.getMessage());
        }
        AppMethodBeat.o(6138);
    }

    private static RequestBody b(JSONObject jSONObject) throws IOException, JSONException {
        AppMethodBeat.i(6141, false);
        JSONObject jSONObject2 = new JSONObject();
        Iterator<String> keys = jSONObject.keys();
        while (keys.hasNext()) {
            String next = keys.next();
            jSONObject2.put(next, jSONObject.getString(next));
        }
        RequestBody create = RequestBody.create(MediaType.parse("application/json; charset=UTF-8"), jSONObject2.toString());
        AppMethodBeat.o(6141);
        return create;
    }

    public static HttpUrl a(String str, JSONObject jSONObject) {
        AppMethodBeat.i(6143, false);
        if (TextUtils.isEmpty(str) || jSONObject == null || jSONObject.length() == 0) {
            HttpUrl parse = HttpUrl.parse(str);
            AppMethodBeat.o(6143);
            return parse;
        }
        try {
            HttpUrl.Builder newBuilder = HttpUrl.parse(str).newBuilder();
            Iterator<String> keys = jSONObject.keys();
            while (keys.hasNext()) {
                String next = keys.next();
                newBuilder.addQueryParameter(next, jSONObject.getString(next));
            }
            HttpUrl build = newBuilder.build();
            AppMethodBeat.o(6143);
            return build;
        } catch (JSONException e) {
            a.a("MFADSDK", e.getMessage());
            HttpUrl parse2 = HttpUrl.parse(str);
            AppMethodBeat.o(6143);
            return parse2;
        }
    }
}
