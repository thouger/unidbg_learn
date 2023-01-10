package cn.missfresh.module.base.h5.b;

import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import java.util.HashMap;

/* compiled from: H5Data */
public class a {
    public static HashMap<String, String> a(String str, String str2) {
        AppMethodBeat.i(12909, false);
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("channel", str);
        hashMap.put("last_page", str2);
        AppMethodBeat.o(12909);
        return hashMap;
    }
}
