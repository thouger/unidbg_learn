package cn.missfresh.module.base.common.dialog.dclog;

import android.app.Activity;
import cn.missfresh.module.base.common.api.IApplicationDelegateService;
import cn.missfresh.module.base.manager.e;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import com.alibaba.fastjson.JSONObject;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/* compiled from: LogParamsPresenter */
public class a {
    public static List<String> a = new ArrayList();
    public static List<String> b = new ArrayList();
    public static List<String> c = new ArrayList();
    private static Activity d;

    static {
        AppMethodBeat.i(11585, false);
        AppMethodBeat.o(11585);
    }

    public static void a(int i, String str, Map<String, String> map, JSONObject jSONObject) {
        AppMethodBeat.i(11580, false);
        if (e.m()) {
            if (((IApplicationDelegateService) com.alibaba.android.arouter.b.a.a().a("/common/application_delegate_service").navigation()).getTopActivity() != d) {
                d = ((IApplicationDelegateService) com.alibaba.android.arouter.b.a.a().a("/common/application_delegate_service").navigation()).getTopActivity();
                a.clear();
                b.clear();
                c.clear();
            }
            b(i, str, map, jSONObject);
        }
        AppMethodBeat.o(11580);
    }

    private static void b(int i, String str, Map<String, String> map, JSONObject jSONObject) {
        AppMethodBeat.i(11583, false);
        if (i == 0) {
            List<String> list = a;
            list.add("==============url==========\n" + str);
            if (map != null) {
                for (String str2 : map.keySet()) {
                    List<String> list2 = a;
                    list2.add(((Object) str2) + " : " + map);
                }
            }
        } else if (i == 1) {
            if (jSONObject != null) {
                for (Object obj : jSONObject.keySet()) {
                    List<String> list3 = b;
                    list3.add(obj + " : " + jSONObject.get(obj));
                }
            }
        }
        AppMethodBeat.o(11583);
    }
}
