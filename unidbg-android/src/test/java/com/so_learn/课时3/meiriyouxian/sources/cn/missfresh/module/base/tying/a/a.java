package cn.missfresh.module.base.tying.a;

import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import cn.missfresh.module.base.R;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;

/* compiled from: TyingAnimationHelper */
public class a {
    public static void a(View view) {
        AppMethodBeat.i(22996, false);
        if (view == null) {
            AppMethodBeat.o(22996);
            return;
        }
        view.setPivotY(0.0f);
        view.setPivotX(0.0f);
        Animation loadAnimation = AnimationUtils.loadAnimation(view.getContext(), R.anim.anim_tying_in);
        loadAnimation.setInterpolator(new AccelerateDecelerateInterpolator());
        loadAnimation.setDuration(400);
        view.postDelayed(new AnonymousClass1(view, loadAnimation), 50);
        AppMethodBeat.o(22996);
    }

    /* compiled from: TyingAnimationHelper */
    /* renamed from: cn.missfresh.module.base.tying.a.a$1  reason: invalid class name */
    static class AnonymousClass1 implements Runnable {
        final /* synthetic */ View a;
        final /* synthetic */ Animation b;

        AnonymousClass1(View view, Animation animation) {
            this.a = view;
            this.b = animation;
        }

        @Override // java.lang.Runnable
        public void run() {
            AppMethodBeat.i(22995, false);
            this.a.startAnimation(this.b);
            AppMethodBeat.o(22995);
        }
    }
}
