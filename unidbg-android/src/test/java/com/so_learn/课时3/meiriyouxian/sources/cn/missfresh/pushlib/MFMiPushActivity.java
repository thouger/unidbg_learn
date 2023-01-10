package cn.missfresh.pushlib;

import android.os.Bundle;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import com.alibaba.fastjson.JSONObject;
import com.umeng.message.UmengNotifyClickActivity;
import java.util.Iterator;
import org.json.JSONException;

public class MFMiPushActivity extends UmengNotifyClickActivity {
    @Override // android.app.Activity, android.view.Window.Callback
    public void onWindowFocusChanged(boolean z) {
        super.onWindowFocusChanged(z);
        AppMethodBeat.at(this, z);
    }

    /* access modifiers changed from: protected */
    @Override // com.taobao.agoo.BaseNotifyClickActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        AppMethodBeat.i(22091, false);
        super.onCreate(bundle);
        AppMethodBeat.o(22091);
    }

    public String a(String str) {
        AppMethodBeat.i(22092, false);
        try {
            Object parse = JSONObject.parse(str);
            if (parse instanceof JSONObject) {
                String string = ((JSONObject) parse).getString("extra");
                AppMethodBeat.o(22092);
                return string;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        AppMethodBeat.o(22092);
        return null;
    }

    public Bundle b(String str) throws JSONException {
        AppMethodBeat.i(22093, false);
        Bundle bundle = new Bundle();
        org.json.JSONObject jSONObject = new org.json.JSONObject(str);
        Iterator<String> keys = jSONObject.keys();
        while (keys.hasNext()) {
            String next = keys.next();
            Object obj = jSONObject.get(next);
            if (obj != null) {
                if (obj instanceof Integer) {
                    bundle.putInt(next, ((Integer) obj).intValue());
                } else if (obj instanceof Float) {
                    bundle.putFloat(next, ((Float) obj).floatValue());
                } else if (obj instanceof Double) {
                    bundle.putDouble(next, ((Double) obj).doubleValue());
                } else if (obj instanceof String) {
                    bundle.putString(next, (String) obj);
                }
            }
        }
        AppMethodBeat.o(22093);
        return bundle;
    }
}
