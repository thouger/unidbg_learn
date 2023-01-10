package cn.missfresh.module.base.common.helper;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.widget.ImageView;
import cn.missfresh.module.base.utils.aw;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;

/* compiled from: RedpackScrollHelper */
public class b {
    private String a = getClass().getSimpleName();
    private Activity b;
    private ImageView c;
    private AnimatorSet d;
    private ObjectAnimator e;
    private int f = 800;
    private int g = -1;

    public b(Activity activity, ImageView imageView) {
        AppMethodBeat.i(12011, false);
        this.b = activity;
        this.c = imageView;
        a();
        AppMethodBeat.o(12011);
    }

    public void a() {
        AppMethodBeat.i(12013, false);
        this.d = new AnimatorSet();
        int b = aw.b(42);
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
        AppMethodBeat.o(12013);
    }

    /* compiled from: RedpackScrollHelper */
    /* access modifiers changed from: package-private */
    /* renamed from: cn.missfresh.module.base.common.helper.b$1  reason: invalid class name */
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
            AppMethodBeat.i(11993, false);
            b.this.g = 2;
            b.this.c.setAlpha(0.6f);
            AppMethodBeat.o(11993);
        }
    }

    /* compiled from: RedpackScrollHelper */
    /* access modifiers changed from: package-private */
    /* renamed from: cn.missfresh.module.base.common.helper.b$2  reason: invalid class name */
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
            AppMethodBeat.i(11999, false);
            b.this.g = 1;
            b.this.c.setAlpha(1.0f);
            AppMethodBeat.o(11999);
        }
    }

    /* compiled from: RedpackScrollHelper */
    /* access modifiers changed from: package-private */
    /* renamed from: cn.missfresh.module.base.common.helper.b$3  reason: invalid class name */
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
            AppMethodBeat.i(12004, false);
            b.this.g = 0;
            b.this.c.setAlpha(1.0f);
            AppMethodBeat.o(12004);
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            AppMethodBeat.i(12006, false);
            b.this.c.setAlpha(0.6f);
            AppMethodBeat.o(12006);
        }
    }

    public void b() {
        AppMethodBeat.i(12014, false);
        if (!d()) {
            AppMethodBeat.o(12014);
        } else if (this.e.isRunning()) {
            AppMethodBeat.o(12014);
        } else {
            int i = this.g;
            if (i == 0) {
                AppMethodBeat.o(12014);
            } else if (i == 2) {
                this.g = 0;
                this.d.cancel();
                AppMethodBeat.o(12014);
            } else {
                this.d.end();
                this.e.start();
                AppMethodBeat.o(12014);
            }
        }
    }

    public void c() {
        AppMethodBeat.i(12015, false);
        if (!d()) {
            AppMethodBeat.o(12015);
        } else if (this.d.isRunning()) {
            AppMethodBeat.o(12015);
        } else {
            int i = this.g;
            if (i == 1 || i == 2) {
                AppMethodBeat.o(12015);
                return;
            }
            this.e.end();
            this.d.start();
            AppMethodBeat.o(12015);
        }
    }

    public boolean d() {
        boolean z = false;
        AppMethodBeat.i(12017, false);
        if (!this.b.isFinishing() && this.c != null) {
            z = true;
        }
        AppMethodBeat.o(12017);
        return z;
    }
}
