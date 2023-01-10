package cn.missfresh.ad.a;

import android.text.format.DateFormat;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import org.json.JSONObject;

/* compiled from: MFADParams */
public class d {
    private JSONObject a;
    private JSONObject b;
    private JSONObject c;
    private String d;

    public JSONObject a() {
        return this.a;
    }

    public JSONObject b() {
        return this.b;
    }

    public JSONObject c() {
        return this.c;
    }

    public String d() {
        return this.d;
    }

    public String toString() {
        AppMethodBeat.i(6118, false);
        String str = "MFADParams{mPostParams=" + this.a + ", mHeaderParams=" + this.b + ", mApiUrl='" + this.d + DateFormat.QUOTE + '}';
        AppMethodBeat.o(6118);
        return str;
    }
}
