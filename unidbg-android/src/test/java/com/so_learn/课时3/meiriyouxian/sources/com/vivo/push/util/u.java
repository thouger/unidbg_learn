package com.vivo.push.util;

import android.content.ContentResolver;
import android.content.Context;
import android.provider.Settings;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;

/* compiled from: SettingsCache */
/* access modifiers changed from: package-private */
public final class u implements c {
    private ContentResolver a;

    u() {
    }

    @Override // com.vivo.push.util.c
    public final boolean a(Context context) {
        AppMethodBeat.i(1734, false);
        if (i.b()) {
            this.a = context.getContentResolver();
            AppMethodBeat.o(1734);
            return true;
        }
        AppMethodBeat.o(1734);
        return false;
    }

    @Override // com.vivo.push.util.c
    public final String a(String str, String str2) {
        AppMethodBeat.i(1737, false);
        try {
            str2 = Settings.System.getString(this.a, str);
        } catch (Exception e) {
            e.printStackTrace();
            n.b("SettingsCache", "getString error by " + str);
        }
        AppMethodBeat.o(1737);
        return str2;
    }

    @Override // com.vivo.push.util.c
    public final void b(String str, String str2) {
        AppMethodBeat.i(1739, false);
        try {
            Settings.System.putString(this.a, str, str2);
            AppMethodBeat.o(1739);
        } catch (Exception e) {
            e.printStackTrace();
            n.b("SettingsCache", "putString error by " + str);
            AppMethodBeat.o(1739);
        }
    }
}
