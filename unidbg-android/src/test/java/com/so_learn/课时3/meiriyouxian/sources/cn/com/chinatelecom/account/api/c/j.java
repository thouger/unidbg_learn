package cn.com.chinatelecom.account.api.c;

import android.text.TextUtils;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import com.tencent.imsdk.BaseConstants;
import com.tencent.qcloud.tim.uikit.component.video.JCameraView;
import org.json.JSONException;
import org.json.JSONObject;

public class j {
    public static String a() {
        AppMethodBeat.i(8334, false);
        String a = a(80003, "\u65e0\u7f51\u7edc\u8fde\u63a5");
        AppMethodBeat.o(8334);
        return a;
    }

    public static String a(int i, String str) {
        AppMethodBeat.i(8349, false);
        String a = a(i, str, null);
        AppMethodBeat.o(8349);
        return a;
    }

    public static String a(int i, String str, String str2) {
        AppMethodBeat.i(8351, false);
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("result", i);
            jSONObject.put("msg", str);
            if (!TextUtils.isEmpty(str2)) {
                jSONObject.put("reqId", str2);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String jSONObject2 = jSONObject.toString();
        AppMethodBeat.o(8351);
        return jSONObject2;
    }

    public static String b() {
        AppMethodBeat.i(8336, false);
        String a = a(BaseConstants.ERR_SVR_COMM_SENSITIVE_TEXT, "\u8bf7\u6c42\u7f51\u7edc\u5f02\u5e38");
        AppMethodBeat.o(8336);
        return a;
    }

    public static String c() {
        AppMethodBeat.i(8338, false);
        String a = a(JCameraView.MEDIA_QUALITY_SORRY, "\u8bf7\u6c42\u8d85\u65f6");
        AppMethodBeat.o(8338);
        return a;
    }

    public static String d() {
        AppMethodBeat.i(8340, false);
        String a = a(80004, "\u79fb\u52a8\u7f51\u7edc\u672a\u5f00\u542f");
        AppMethodBeat.o(8340);
        return a;
    }

    public static String e() {
        AppMethodBeat.i(8341, false);
        String a = a(80103, "\u8bf7\u5148\u521d\u59cb\u5316SDK");
        AppMethodBeat.o(8341);
        return a;
    }

    public static String f() {
        AppMethodBeat.i(8343, false);
        String a = a(80800, "WIFI\u5207\u6362\u8d85\u65f6");
        AppMethodBeat.o(8343);
        return a;
    }

    public static String g() {
        AppMethodBeat.i(8347, false);
        String a = a(80102, "\u9884\u767b\u5f55\u5f02\u5e38");
        AppMethodBeat.o(8347);
        return a;
    }
}
