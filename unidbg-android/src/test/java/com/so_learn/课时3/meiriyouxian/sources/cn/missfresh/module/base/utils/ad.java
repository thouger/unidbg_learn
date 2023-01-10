package cn.missfresh.module.base.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;

/* compiled from: MFIntentUtil */
public class ad {
    public static Intent a(Context context, Intent intent) {
        AppMethodBeat.i(23346, false);
        if (intent != null && !(context instanceof Activity)) {
            intent.addFlags(268435456);
        }
        AppMethodBeat.o(23346);
        return intent;
    }
}
