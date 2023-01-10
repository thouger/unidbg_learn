package cn.missfresh.module.base.support.share;

import android.content.Context;
import android.content.SharedPreferences;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import com.sina.weibo.sdk.auth.b;
import com.tencent.connect.common.Constants;

/* compiled from: AccessTokenKeeper */
public class a {
    public static void a(Context context, b bVar) {
        AppMethodBeat.i(22425, false);
        if (context == null || bVar == null) {
            AppMethodBeat.o(22425);
            return;
        }
        SharedPreferences.Editor edit = context.getSharedPreferences("com_weibo_sdk_android", 32768).edit();
        edit.putString("uid", bVar.b());
        edit.putString(Constants.PARAM_ACCESS_TOKEN, bVar.c());
        edit.putString("refresh_token", bVar.d());
        edit.putLong(Constants.PARAM_EXPIRES_IN, bVar.e());
        edit.commit();
        AppMethodBeat.o(22425);
    }

    public static b a(Context context) {
        AppMethodBeat.i(22426, false);
        if (context == null) {
            AppMethodBeat.o(22426);
            return null;
        }
        b bVar = new b();
        SharedPreferences sharedPreferences = context.getSharedPreferences("com_weibo_sdk_android", 32768);
        bVar.a(sharedPreferences.getString("uid", ""));
        bVar.b(sharedPreferences.getString(Constants.PARAM_ACCESS_TOKEN, ""));
        bVar.c(sharedPreferences.getString("refresh_token", ""));
        bVar.a(sharedPreferences.getLong(Constants.PARAM_EXPIRES_IN, 0));
        AppMethodBeat.o(22426);
        return bVar;
    }

    public static void b(Context context) {
        AppMethodBeat.i(22427, false);
        if (context == null) {
            AppMethodBeat.o(22427);
            return;
        }
        SharedPreferences.Editor edit = context.getSharedPreferences("com_weibo_sdk_android", 32768).edit();
        edit.clear();
        edit.commit();
        AppMethodBeat.o(22427);
    }
}
