package cn.missfresh.module.base.common.helper;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.util.Log;
import android.view.View;
import cn.missfresh.module.base.utils.aw;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;

/* compiled from: CountPondScrollHelper */
public class a {
    private String a = getClass().getSimpleName();
    private Activity b;
    private View c;
    private AnimatorSet d;
    private ObjectAnimator e;
    private int f = 600;
    private int g = -1;
    private int h = 0;

    public a(Activity activity, View view, int i) {
        AppMethodBeat.i(11789, false);
        this.b = activity;
        this.c = view;
        this.h = i;
        a();
        AppMethodBeat.o(11789);
    }

    public void a() {
        AppMethodBeat.i(11792, false);
        this.d = new AnimatorSet();
        int i = this.c.getLayoutParams().width;
        Log.e("width...", i + ".........");
        int b = aw.b(this.h == 0 ? -160 : 160);
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this.c, "waite", 100.0f, 200.0f);
        ofFloat.setDuration((long) this.f);
        ofFloat.addListener(new AnonymousClass1());
        float f = (float) b;
        ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(this.c, "translationX", f, 0.0f);
        ofFloat2.setDuration(200L);
        ofFloat2.addListener(new AnonymousClass2());
        this.d.playSequentially(ofFloat, ofFloat2);
        this.e = ObjectAnimator.ofFloat(this.c, "translationX", 0.0f, f);
        this.e.setDuration(200L);
        this.e.addListener(new AnonymousClass3());
        AppMethodBeat.o(11792);
    }

    /* compiled from: CountPondScrollHelper */
    /* access modifiers changed from: package-private */
    /* renamed from: cn.missfresh.module.base.common.helper.a$1  reason: invalid class name */
    public class AnonymousClass1 implements Animator.AnimatorListener {
        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationCancel(Animator animator) {
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationRepeat(Animator animator) {
        }

        AnonymousClass1() {
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animator) {
            AppMethodBeat.i(11764, false);
            a.this.g = 2;
            a.this.c.setAlpha(0.6f);
            AppMethodBeat.o(11764);
        }
    }

    /* compiled from: CountPondScrollHelper */
    /* access modifiers changed from: package-private */
    /* renamed from: cn.missfresh.module.base.common.helper.a$2  reason: invalid class name */
    public class AnonymousClass2 implements Animator.AnimatorListener {
        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationCancel(Animator animator) {
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationRepeat(Animator animator) {
        }

        AnonymousClass2() {
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animator) {
            AppMethodBeat.i(11770, false);
            a.this.g = 1;
            a.this.c.setAlpha(1.0f);
            AppMethodBeat.o(11770);
        }
    }

    /* compiled from: CountPondScrollHelper */
    /* access modifiers changed from: package-private */
    /* renamed from: cn.missfresh.module.base.common.helper.a$3  reason: invalid class name */
    public class AnonymousClass3 implements Animator.AnimatorListener {
        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationCancel(Animator animator) {
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationRepeat(Animator animator) {
        }

        AnonymousClass3() {
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animator) {
            AppMethodBeat.i(11779, false);
            a.this.g = 0;
            a.this.c.setAlpha(1.0f);
            AppMethodBeat.o(11779);
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            AppMethodBeat.i(11781, false);
            a.this.c.setAlpha(0.6f);
            AppMethodBeat.o(11781);
        }
    }

    public void b() {
        AppMethodBeat.i(11795, false);
        if (!d()) {
            AppMethodBeat.o(11795);
        } else if (this.e.isRunning()) {
            AppMethodBeat.o(11795);
        } else {
            int i = this.g;
            if (i == 0) {
                AppMethodBeat.o(11795);
            } else if (i == 2) {
                this.g = 0;
                this.d.cancel();
                AppMethodBeat.o(11795);
            } else {
                this.d.end();
                this.e.start();
                AppMethodBeat.o(11795);
            }
        }
    }

    public void c() {
        AppMethodBeat.i(11798, false);
        if (!d()) {
            AppMethodBeat.o(11798);
        } else if (this.d.isRunning()) {
            AppMethodBeat.o(11798);
        } else {
            int i = this.g;
            if (i == 1 || i == 2) {
                AppMethodBeat.o(11798);
                return;
            }
            this.e.end();
            this.d.start();
            AppMethodBeat.o(11798);
        }
    }

    public boolean d() {
        boolean z = false;
        AppMethodBeat.i(11800, false);
        if (!this.b.isFinishing() && this.c != null) {
            z = true;
        }
        AppMethodBeat.o(11800);
        return z;
    }
}
