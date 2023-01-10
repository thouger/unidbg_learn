package com.vivo.push.util;

import android.content.Context;
import android.content.SharedPreferences;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;

/* compiled from: SpCache */
public final class w implements c {
    public static String a = "SpCache";
    private static String c = "com.vivo.push.cache";
    public SharedPreferences b;

    @Override // com.vivo.push.util.c
    public final boolean a(Context context) {
        AppMethodBeat.i(1753, false);
        if (this.b == null) {
            this.b = context.getSharedPreferences(c, 0);
        }
        AppMethodBeat.o(1753);
        return true;
    }

    @Override // com.vivo.push.util.c
    public final String a(String str, String str2) {
        AppMethodBeat.i(1755, false);
        String string = this.b.getString(str, str2);
        String str3 = a;
        n.d(str3, "getString " + str + " is " + string);
        AppMethodBeat.o(1755);
        return string;
    }

    @Override // com.vivo.push.util.c
    public final void b(String str, String str2) {
        AppMethodBeat.i(1790, false);
        SharedPreferences.Editor edit = this.b.edit();
        if (edit != null) {
            edit.putString(str, str2);
            a.a(edit);
            String str3 = a;
            n.d(str3, "putString by " + str);
            AppMethodBeat.o(1790);
            return;
        }
        String str4 = a;
        n.b(str4, "putString error by " + str);
        AppMethodBeat.o(1790);
    }
}
