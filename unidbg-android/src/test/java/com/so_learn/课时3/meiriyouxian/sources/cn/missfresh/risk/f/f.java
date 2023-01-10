package cn.missfresh.risk.f;

import android.content.Context;
import android.content.SharedPreferences;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;

/* compiled from: MissFreshSharedPreferences */
public class f {
    private SharedPreferences a;

    public f(Context context, String str) {
        AppMethodBeat.i(2888, false);
        this.a = context.getSharedPreferences(str, 0);
        AppMethodBeat.o(2888);
    }

    public String a(String str, String str2) {
        AppMethodBeat.i(2909, false);
        String string = this.a.getString(str, str2);
        AppMethodBeat.o(2909);
        return string;
    }

    public boolean b(String str, String str2) {
        AppMethodBeat.i(2914, false);
        if (str2 == null) {
            AppMethodBeat.o(2914);
            return false;
        }
        boolean commit = this.a.edit().putString(str, str2).commit();
        AppMethodBeat.o(2914);
        return commit;
    }
}
