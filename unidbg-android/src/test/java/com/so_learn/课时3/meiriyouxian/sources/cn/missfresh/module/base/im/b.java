package cn.missfresh.module.base.im;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import cn.missfresh.module.base.R;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;

/* compiled from: TitleTextWindow */
public class b implements View.OnTouchListener {
    private Activity a;
    private LinearLayout b;
    private int c;
    private String d;
    private String e;
    private boolean f = false;
    private Handler g = new AnonymousClass1(Looper.myLooper());

    static /* synthetic */ void a(b bVar) {
        AppMethodBeat.i(13490, false);
        bVar.d();
        AppMethodBeat.o(13490);
    }

    public b(Activity activity, String str, String str2) {
        AppMethodBeat.i(13473, false);
        this.a = activity;
        this.d = str;
        this.e = str2;
        AppMethodBeat.o(13473);
    }

    /* compiled from: TitleTextWindow */
    /* renamed from: cn.missfresh.module.base.im.b$1  reason: invalid class name */
    class AnonymousClass1 extends Handler {
        AnonymousClass1(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            AppMethodBeat.i(13448, false);
            super.handleMessage(message);
            b.a(b.this);
            AppMethodBeat.o(13448);
        }
    }

    private void c() {
        AppMethodBeat.i(13476, false);
        LinearLayout linearLayout = this.b;
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(linearLayout, "translationY", (float) (-linearLayout.getMeasuredHeight()), 0.0f);
        ofFloat.setDuration(600L);
        ofFloat.start();
        AppMethodBeat.o(13476);
    }

    private void d() {
        AppMethodBeat.i(13479, false);
        LinearLayout linearLayout = this.b;
        if (linearLayout == null || linearLayout.getParent() == null) {
            AppMethodBeat.o(13479);
            return;
        }
        Activity activity = this.a;
        if (activity == null || activity.isFinishing()) {
            AppMethodBeat.o(13479);
            return;
        }
        LinearLayout linearLayout2 = this.b;
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(linearLayout2, "translationY", linearLayout2.getTranslationY(), (float) (-this.b.getMeasuredHeight()));
        ofFloat.setDuration(600L);
        ofFloat.start();
        ofFloat.addListener(new AnonymousClass2());
        AppMethodBeat.o(13479);
    }

    /* compiled from: TitleTextWindow */
    /* access modifiers changed from: package-private */
    /* renamed from: cn.missfresh.module.base.im.b$2  reason: invalid class name */
    public class AnonymousClass2 extends AnimatorListenerAdapter {
        AnonymousClass2() {
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationCancel(Animator animator) {
            AppMethodBeat.i(13454, false);
            super.onAnimationCancel(animator);
            b.this.b();
            AppMethodBeat.o(13454);
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            AppMethodBeat.i(13457, false);
            super.onAnimationEnd(animator);
            b.this.b();
            AppMethodBeat.o(13457);
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationRepeat(Animator animator) {
            AppMethodBeat.i(13458, false);
            super.onAnimationRepeat(animator);
            AppMethodBeat.o(13458);
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animator) {
            AppMethodBeat.i(13460, false);
            super.onAnimationStart(animator);
            AppMethodBeat.o(13460);
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorPauseListener
        public void onAnimationPause(Animator animator) {
            AppMethodBeat.i(13463, false);
            super.onAnimationPause(animator);
            AppMethodBeat.o(13463);
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorPauseListener
        public void onAnimationResume(Animator animator) {
            AppMethodBeat.i(13465, false);
            super.onAnimationResume(animator);
            AppMethodBeat.o(13465);
        }
    }

    public void a() {
        AppMethodBeat.i(13482, false);
        Activity activity = this.a;
        if (activity == null || activity.isFinishing()) {
            AppMethodBeat.o(13482);
            return;
        }
        e();
        ((ViewGroup) this.a.getWindow().getDecorView()).addView(this.b);
        c();
        this.g.sendEmptyMessageDelayed(20, 3000);
        AppMethodBeat.o(13482);
    }

    public void b() {
        AppMethodBeat.i(13484, false);
        Activity activity = this.a;
        if (activity != null && !activity.isFinishing()) {
            ViewGroup viewGroup = (ViewGroup) this.a.getWindow().getDecorView();
            if (viewGroup.indexOfChild(this.b) != -1) {
                viewGroup.removeView(this.b);
            }
        }
        AppMethodBeat.o(13484);
    }

    private void e() {
        AppMethodBeat.i(13486, false);
        this.b = new LinearLayout(this.a);
        this.b.setLayoutParams(new LinearLayout.LayoutParams(-1, -2));
        View inflate = View.inflate(this.a, R.layout.im_new_msg_view, null);
        ((TextView) inflate.findViewById(R.id.action_title_tv)).setText("\u60a8\u7684\u8ba2\u5355\u3010" + this.e + "\u3011\u7684\u9a91\u624b\u53d1\u6765\u6d88\u606f");
        this.b.setOnTouchListener(this);
        this.b.addView(inflate);
        AppMethodBeat.o(13486);
    }

    @Override // android.view.View.OnTouchListener
    public boolean onTouch(View view, MotionEvent motionEvent) {
        AppMethodBeat.i(13488, false);
        int action = motionEvent.getAction();
        if (action == 0) {
            this.c = (int) motionEvent.getRawY();
        } else if (action == 1) {
            if (((double) Math.abs(this.b.getTranslationY())) > ((double) this.b.getMeasuredHeight()) / 1.5d) {
                d();
            } else {
                this.b.setTranslationY(0.0f);
            }
            if (!this.f) {
                IMManager.a().a(this.d);
            }
        } else if (action == 2) {
            this.f = true;
            int rawY = (int) motionEvent.getRawY();
            int i = this.c;
            if (rawY - i < 0) {
                this.b.setTranslationY((float) (rawY - i));
                this.f = true;
            } else {
                this.f = false;
            }
        }
        AppMethodBeat.o(13488);
        return true;
    }
}
