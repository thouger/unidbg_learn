package cn.missfresh.module.base.support.view.snackbar;

import android.content.Context;
import android.content.res.TypedArray;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import com.google.android.material.R;

/* compiled from: ThemeUtils */
class c {
    private static final int[] a = {R.attr.colorPrimary};

    static void a(Context context) {
        AppMethodBeat.i(22931, false);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(a);
        boolean z = !obtainStyledAttributes.hasValue(0);
        if (obtainStyledAttributes != null) {
            obtainStyledAttributes.recycle();
        }
        if (!z) {
            AppMethodBeat.o(22931);
            return;
        }
        IllegalArgumentException illegalArgumentException = new IllegalArgumentException("You need to use a Theme.AppCompat theme (or descendant) with the design library.");
        AppMethodBeat.o(22931);
        throw illegalArgumentException;
    }
}
