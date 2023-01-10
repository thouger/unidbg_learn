package cn.missfresh.module.base.common.helper;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.LinearInterpolator;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.FrameLayout;
import android.widget.ImageView;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import cn.missfresh.ui.roundiv.MFRoundedImageView;

public class GoodsAnimHelper {
    private final String a;
    private int[] b;
    private int c;
    private int d;
    private int e;
    private View f;
    private View g;
    private ViewGroup h;
    private Animation i;
    private b j;

    public interface b {
        void a();
    }

    public GoodsAnimHelper(View view, View view2) {
        AppMethodBeat.i(11967, false);
        this.a = getClass().getSimpleName();
        this.d = 900;
        this.e = 200;
        if (!(view == null || view2 == null)) {
            this.f = view;
            this.g = view2;
            this.b = new int[2];
            this.f.getLocationOnScreen(this.b);
            this.c = this.f.getWidth() / 2;
            this.i = new ScaleAnimation(1.0f, 1.2f, 1.0f, 1.2f, 1, 0.5f, 1, 0.5f);
            this.i.setDuration(300);
        }
        AppMethodBeat.o(11967);
    }

    public GoodsAnimHelper(int i, int i2) {
        AppMethodBeat.i(11968, false);
        this.a = getClass().getSimpleName();
        this.d = 900;
        this.e = 200;
        this.b = new int[2];
        int[] iArr = this.b;
        iArr[0] = i;
        iArr[1] = i2;
        this.g = null;
        this.i = null;
        AppMethodBeat.o(11968);
    }

    public void a(int[] iArr) {
        if (iArr != null && iArr.length == 2) {
            int[] iArr2 = this.b;
            iArr2[0] = iArr[0];
            iArr2[1] = iArr[1];
        }
    }

    public void a(Activity activity, ImageView imageView) {
        AppMethodBeat.i(11973, false);
        if (imageView == null || this.b == null) {
            AppMethodBeat.o(11973);
            return;
        }
        int width = imageView.getWidth();
        int height = imageView.getHeight();
        int[] iArr = new int[2];
        imageView.getLocationInWindow(iArr);
        MFRoundedImageView a2 = a(activity, imageView.getDrawable());
        a(a(activity), a2, iArr, width, height);
        int[] iArr2 = this.b;
        a2.startAnimation(a(a2, ((iArr2[0] - iArr[0]) - (width / 2)) + this.c, (iArr2[1] - iArr[1]) - (height / 2)));
        AppMethodBeat.o(11973);
    }

    private MFRoundedImageView a(Activity activity, Drawable drawable) {
        AppMethodBeat.i(11975, false);
        MFRoundedImageView mFRoundedImageView = new MFRoundedImageView(activity);
        mFRoundedImageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        mFRoundedImageView.setImageDrawable(drawable);
        mFRoundedImageView.setOval(true);
        AppMethodBeat.o(11975);
        return mFRoundedImageView;
    }

    private AnimationSet a(View view, int i, int i2) {
        AppMethodBeat.i(11979, false);
        ScaleAnimation scaleAnimation = new ScaleAnimation(1.0f, 0.05f, 1.0f, 0.05f, 1, 0.5f, 1, 0.5f);
        scaleAnimation.setDuration((long) this.d);
        scaleAnimation.setFillAfter(true);
        TranslateAnimation translateAnimation = new TranslateAnimation(0.0f, (float) i, 0.0f, 0.0f);
        translateAnimation.setInterpolator(new LinearInterpolator());
        translateAnimation.setRepeatCount(0);
        translateAnimation.setFillAfter(true);
        TranslateAnimation translateAnimation2 = new TranslateAnimation(0.0f, 0.0f, 0.0f, (float) i2);
        translateAnimation2.setInterpolator(new AccelerateInterpolator());
        translateAnimation2.setRepeatCount(0);
        translateAnimation2.setFillAfter(true);
        AnimationSet animationSet = new AnimationSet(false);
        animationSet.setFillAfter(false);
        animationSet.addAnimation(scaleAnimation);
        animationSet.addAnimation(translateAnimation);
        animationSet.addAnimation(translateAnimation2);
        animationSet.setDuration((long) this.d);
        animationSet.setAnimationListener(new a(view));
        AppMethodBeat.o(11979);
        return animationSet;
    }

    private ViewGroup a(Activity activity) {
        AppMethodBeat.i(11981, false);
        if (this.h == null) {
            this.h = (ViewGroup) activity.getWindow().getDecorView();
        }
        ViewGroup viewGroup = this.h;
        AppMethodBeat.o(11981);
        return viewGroup;
    }

    private void a(ViewGroup viewGroup, View view, int[] iArr, int i, int i2) {
        AppMethodBeat.i(11983, false);
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(i, i2);
        layoutParams.leftMargin = iArr[0];
        layoutParams.topMargin = iArr[1];
        view.setLayoutParams(layoutParams);
        viewGroup.addView(view);
        AppMethodBeat.o(11983);
    }

    /* access modifiers changed from: private */
    public class a implements Animation.AnimationListener {
        public View a;

        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationRepeat(Animation animation) {
        }

        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationStart(Animation animation) {
        }

        public a(View view) {
            this.a = view;
        }

        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationEnd(Animation animation) {
            AppMethodBeat.i(11812, false);
            this.a.setVisibility(8);
            GoodsAnimHelper.this.h.postDelayed(new AnonymousClass1(), (long) GoodsAnimHelper.this.e);
            if (GoodsAnimHelper.this.g != null) {
                GoodsAnimHelper.this.g.clearAnimation();
                GoodsAnimHelper.this.g.startAnimation(GoodsAnimHelper.this.i);
            }
            if (GoodsAnimHelper.this.j != null) {
                GoodsAnimHelper.this.j.a();
            }
            AppMethodBeat.o(11812);
        }

        /* renamed from: cn.missfresh.module.base.common.helper.GoodsAnimHelper$a$1  reason: invalid class name */
        class AnonymousClass1 implements Runnable {
            AnonymousClass1() {
            }

            @Override // java.lang.Runnable
            public void run() {
                AppMethodBeat.i(11807, false);
                if (GoodsAnimHelper.this.h != null) {
                    GoodsAnimHelper.this.h.removeView(a.this.a);
                }
                AppMethodBeat.o(11807);
            }
        }
    }
}
