package cn.missfresh.module.base.utils;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;

/* compiled from: AnimUtil */
public class h {
    public static void a(View view) {
        AppMethodBeat.i(23041, false);
        AnimatorSet animatorSet = new AnimatorSet();
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(view, "scaleX", 1.0f, 0.9f, 1.0f, 1.1f, 1.0f);
        ofFloat.setRepeatCount(1);
        ofFloat.setDuration(400L);
        ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(view, "scaleY", 1.0f, 0.9f, 1.0f, 1.1f, 1.0f);
        ofFloat2.setRepeatCount(1);
        ofFloat2.setDuration(400L);
        animatorSet.playTogether(ofFloat, ofFloat2);
        animatorSet.setInterpolator(new AccelerateDecelerateInterpolator());
        animatorSet.start();
        AppMethodBeat.o(23041);
    }
}
