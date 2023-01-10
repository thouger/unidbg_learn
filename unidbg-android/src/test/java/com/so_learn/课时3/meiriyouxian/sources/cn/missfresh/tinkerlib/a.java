package cn.missfresh.tinkerlib;

import android.text.format.DateFormat;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;

/* compiled from: HotFixPatchInfo */
public class a {
    public String a;
    public String b;
    public boolean c;
    public boolean d;

    public String toString() {
        AppMethodBeat.i(12790, false);
        String str = "HotFixPatchInfo{patch_url='" + this.a + DateFormat.QUOTE + ", patch_version='" + this.b + DateFormat.QUOTE + ", restart_now=" + this.c + ", rollback=" + this.d + '}';
        AppMethodBeat.o(12790);
        return str;
    }
}
