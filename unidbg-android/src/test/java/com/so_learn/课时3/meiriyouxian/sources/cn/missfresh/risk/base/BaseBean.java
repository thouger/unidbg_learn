package cn.missfresh.risk.base;

import android.text.TextUtils;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import java.io.Serializable;
import org.json.JSONObject;

public class BaseBean implements Serializable {
    protected JSONObject jsonObject = new JSONObject();

    /* access modifiers changed from: protected */
    public JSONObject toJSONObject() {
        return this.jsonObject;
    }

    protected BaseBean() {
        AppMethodBeat.i(1264, false);
        AppMethodBeat.o(1264);
    }

    /* access modifiers changed from: protected */
    public String isEmpty(String str) {
        AppMethodBeat.i(1270, false);
        if (TextUtils.isEmpty(str)) {
            AppMethodBeat.o(1270);
            return "unknow";
        }
        AppMethodBeat.o(1270);
        return str;
    }
}
