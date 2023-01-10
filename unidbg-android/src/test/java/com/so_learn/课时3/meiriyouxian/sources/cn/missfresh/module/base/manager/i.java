package cn.missfresh.module.base.manager;

import android.content.Context;
import android.content.SharedPreferences;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;

/* compiled from: MissFreshSharedPreferences */
public class i {
    private SharedPreferences a;

    public i(Context context, String str) {
        AppMethodBeat.i(14488, false);
        this.a = context.getSharedPreferences(str, 0);
        AppMethodBeat.o(14488);
    }

    public void a() {
        AppMethodBeat.i(14489, false);
        this.a.edit().clear().apply();
        AppMethodBeat.o(14489);
    }

    public boolean a(String str, boolean z) {
        AppMethodBeat.i(14490, false);
        boolean z2 = this.a.getBoolean(str, z);
        AppMethodBeat.o(14490);
        return z2;
    }

    public boolean b(String str, boolean z) {
        AppMethodBeat.i(14491, false);
        boolean commit = this.a.edit().putBoolean(str, z).commit();
        AppMethodBeat.o(14491);
        return commit;
    }

    public void c(String str, boolean z) {
        AppMethodBeat.i(14492, false);
        this.a.edit().putBoolean(str, z).apply();
        AppMethodBeat.o(14492);
    }

    public int a(String str, int i) {
        AppMethodBeat.i(14493, false);
        int i2 = this.a.getInt(str, i);
        AppMethodBeat.o(14493);
        return i2;
    }

    public boolean b(String str, int i) {
        AppMethodBeat.i(14494, false);
        boolean commit = this.a.edit().putInt(str, i).commit();
        AppMethodBeat.o(14494);
        return commit;
    }

    public String a(String str, String str2) {
        AppMethodBeat.i(14495, false);
        String string = this.a.getString(str, str2);
        AppMethodBeat.o(14495);
        return string;
    }

    public boolean b(String str, String str2) {
        AppMethodBeat.i(14496, false);
        if (str2 == null) {
            AppMethodBeat.o(14496);
            return false;
        }
        boolean commit = this.a.edit().putString(str, str2).commit();
        AppMethodBeat.o(14496);
        return commit;
    }

    public boolean a(String str, long j) {
        AppMethodBeat.i(14497, false);
        boolean commit = this.a.edit().putLong(str, j).commit();
        AppMethodBeat.o(14497);
        return commit;
    }

    public long b(String str, long j) {
        AppMethodBeat.i(14498, false);
        long j2 = this.a.getLong(str, j);
        AppMethodBeat.o(14498);
        return j2;
    }

    public boolean a(String str) {
        AppMethodBeat.i(14499, false);
        boolean contains = this.a.contains(str);
        AppMethodBeat.o(14499);
        return contains;
    }
}
