package cn.missfresh.module.base.support.view.snackbar;

import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import androidx.interpolator.view.animation.FastOutLinearInInterpolator;
import androidx.interpolator.view.animation.FastOutSlowInInterpolator;
import androidx.interpolator.view.animation.LinearOutSlowInInterpolator;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;

/* compiled from: AnimationUtils */
class a {
    static final Interpolator a = new LinearInterpolator();
    static final Interpolator b = new FastOutSlowInInterpolator();
    static final Interpolator c = new FastOutLinearInInterpolator();
    static final Interpolator d = new LinearOutSlowInInterpolator();
    static final Interpolator e = new DecelerateInterpolator();

    static {
        AppMethodBeat.i(22830, false);
        AppMethodBeat.o(22830);
    }
}
