package cn.com.chinatelecom.account.api.c;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;

public final class c {
    private static SharedPreferences a(Context context) {
        AppMethodBeat.i(8154, false);
        SharedPreferences sharedPreferences = context.getSharedPreferences(b(context), 0);
        AppMethodBeat.o(8154);
        return sharedPreferences;
    }

    public static void a(Context context, String str, int i) {
        AppMethodBeat.i(8157, false);
        if (context != null && !TextUtils.isEmpty(str)) {
            try {
                a(context).edit().putInt(str, i).commit();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        AppMethodBeat.o(8157);
    }

    public static void a(Context context, String str, String str2) {
        AppMethodBeat.i(8163, false);
        if (context != null && !TextUtils.isEmpty(str)) {
            try {
                a(context).edit().putString(str, str2).commit();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        AppMethodBeat.o(8163);
    }

    public static boolean a(Context context, String str, long j) {
        AppMethodBeat.i(8160, false);
        if (context != null && !TextUtils.isEmpty(str)) {
            try {
                boolean commit = a(context).edit().putLong(str, j).commit();
                AppMethodBeat.o(8160);
                return commit;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        AppMethodBeat.o(8160);
        return false;
    }

    public static int b(Context context, String str, int i) {
        AppMethodBeat.i(8165, false);
        if (context != null && !TextUtils.isEmpty(str)) {
            try {
                int i2 = a(context).getInt(str, i);
                AppMethodBeat.o(8165);
                return i2;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        AppMethodBeat.o(8165);
        return i;
    }

    public static long b(Context context, String str, long j) {
        AppMethodBeat.i(8170, false);
        if (context != null && !TextUtils.isEmpty(str)) {
            try {
                long j2 = a(context).getLong(str, j);
                AppMethodBeat.o(8170);
                return j2;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        AppMethodBeat.o(8170);
        return j;
    }

    private static String b(Context context) {
        return "ct_account_api_sdk";
    }

    public static String b(Context context, String str, String str2) {
        AppMethodBeat.i(8172, false);
        if (context != null && !TextUtils.isEmpty(str)) {
            try {
                String string = a(context).getString(str, str2);
                AppMethodBeat.o(8172);
                return string;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        AppMethodBeat.o(8172);
        return str2;
    }
}
