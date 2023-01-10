package cn.missfresh.module.base.h5.a;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import cn.missfresh.module.base.bean.ShareInfo;
import cn.missfresh.module.base.utils.q;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import cn.missfresh.utils.a.d;
import com.alibaba.android.arouter.facade.Postcard;
import java.util.ArrayList;
import java.util.Map;

/* compiled from: H5SkipHelper */
@Deprecated
public class a {
    public static void a(Context context, String str, String str2) {
        AppMethodBeat.i(12889, false);
        a(context, str, str2, null);
        AppMethodBeat.o(12889);
    }

    public static void a(Context context, String str, String str2, ShareInfo shareInfo) {
        AppMethodBeat.i(12892, false);
        a(context, "", str, str2, "", shareInfo, "", 0, null, "", "");
        AppMethodBeat.o(12892);
    }

    public static void a(Context context, String str, String str2, String str3, String str4, ShareInfo shareInfo, String str5, Map<String, String> map) {
        AppMethodBeat.i(12894, false);
        a(context, str, str2, str3, str4, shareInfo, str5, 0, map, "", "");
        AppMethodBeat.o(12894);
    }

    public static void a(Context context, String str, String str2, String str3, String str4, ShareInfo shareInfo, String str5, int i, Map<String, String> map) {
        AppMethodBeat.i(12899, false);
        a(context, str, str2, str3, str4, shareInfo, str5, i, map, "", "");
        AppMethodBeat.o(12899);
    }

    public static void a(Context context, String str, String str2, String str3, String str4, ShareInfo shareInfo, String str5, int i, Map<String, String> map, String str6, String str7) {
        AppMethodBeat.i(12902, false);
        if (TextUtils.isEmpty(str3)) {
            AppMethodBeat.o(12902);
            return;
        }
        ArrayList<String> arrayList = null;
        if (map != null) {
            arrayList = q.a(map);
        }
        Postcard withString = com.alibaba.android.arouter.b.a.a().a("/promotion/new_h5event").withString("H5_channel", str).withString("h5_title", str2).withString("h5_url", str3).withString("H5_promotion_id", str4).withSerializable("share_info", shareInfo).withString("STATIC_LABEL", str5).withStringArrayList("STATISTIC_PARAMS", arrayList).withString("STATISTIC_POSITION", str6).withString("FROM_SOURCE", str7);
        if (!(context instanceof Activity) || i <= 0) {
            d.c("===>", "not Activity");
            withString.navigation();
        } else {
            d.c("===>", "is Activity");
            withString.navigation((Activity) context, i);
        }
        AppMethodBeat.o(12902);
    }
}
