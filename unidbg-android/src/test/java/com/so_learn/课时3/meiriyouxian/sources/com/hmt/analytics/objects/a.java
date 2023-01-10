package com.hmt.analytics.objects;

import android.content.Context;
import android.text.TextUtils;
import com.hmt.analytics.android.g;
import com.hmt.analytics.android.h;
import com.tencent.open.SocialConstants;
import org.json.JSONObject;

/* compiled from: ActionController */
public class a {
    private static final String a = a.class.getSimpleName();

    public static boolean a(Context context, f fVar, com.hmt.analytics.util.a aVar, com.hmt.analytics.a.a aVar2, c cVar, JSONObject jSONObject) {
        String str;
        try {
            if (!fVar.a()) {
                com.hmt.analytics.android.a.a(a, "Illegal value of acc in act_list");
                return false;
            }
            h.c("act_list-start");
            JSONObject a2 = h.a(e.a(fVar, context), aVar);
            if (cVar != null) {
                a2.put("start_ts", TextUtils.isEmpty(cVar.a) ? "" : cVar.a);
                if (TextUtils.isEmpty(cVar.b)) {
                    str = "";
                } else {
                    str = cVar.b;
                }
                a2.put("end_ts", str);
                if (TextUtils.isEmpty(cVar.a)) {
                    a2.put("duration", "");
                } else {
                    a2.put("duration", String.valueOf(Long.valueOf(cVar.b).longValue() - Long.valueOf(cVar.a).longValue()));
                }
            }
            h.a(a2, jSONObject);
            h.a(context, a2, "act_list", g.o, SocialConstants.PARAM_ACT, aVar2);
            return true;
        } catch (Exception e) {
            String str2 = a;
            com.hmt.analytics.android.a.a(str2, "Collected:" + e.getMessage());
            return false;
        }
    }
}
