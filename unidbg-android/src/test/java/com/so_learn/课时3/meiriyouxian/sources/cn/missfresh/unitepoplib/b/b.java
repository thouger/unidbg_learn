package cn.missfresh.unitepoplib.b;

import android.content.Context;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;

/* compiled from: UIUtil */
public class b {
    public static int a(int i, Context context) {
        AppMethodBeat.i(15573, false);
        int i2 = (int) ((((float) i) * context.getResources().getDisplayMetrics().density) + 0.5f);
        AppMethodBeat.o(15573);
        return i2;
    }
}
