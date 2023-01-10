package cn.missfresh.module.base.helper;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.ScaleAnimation;
import android.widget.FrameLayout;
import android.widget.ImageView;
import cn.missfresh.module.base.R;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import cn.missfresh.ui.roundiv.MFRoundedImageView;

public class AddGoodsAnimHelper {
    private View a;
    private View b;
    private ViewGroup c;
    private int[] d;
    private int[] e;
    private int f = 900;
    private int g = 200;
    private int h;
    private int i;
    private int j;
    private int k;
    private int l;
    private boolean m;
    private FrameLayout.LayoutParams n;
    private View o;
    private Animation p;

    public AddGoodsAnimHelper(View view, View view2, View view3) {
        AppMethodBeat.i(12926, false);
        if (!(view == null || view2 == null)) {
            this.a = view;
            this.b = view2;
            this.o = view3;
            this.l = (int) this.b.getContext().getResources().getDimension(R.dimen.add_to_shopping_cart_view_size);
            this.p = new ScaleAnimation(1.0f, 1.2f, 1.0f, 1.2f, 1, 0.5f, 1, 0.5f);
            this.p.setDuration(300);
            this.m = true;
        }
        AppMethodBeat.o(12926);
    }

    public void a(Activity activity, ImageView imageView) {
        AppMethodBeat.i(12929, false);
        if (imageView != null || !this.m) {
            a();
            if (imageView != null) {
                MFRoundedImageView a = a(activity, imageView.getDrawable());
                a(activity);
                b(a);
                a(a);
            }
            AppMethodBeat.o(12929);
            return;
        }
        AppMethodBeat.o(12929);
    }

    private void a() {
        AppMethodBeat.i(12932, false);
        if (this.d == null) {
            this.d = new int[2];
            this.a.getLocationInWindow(this.d);
            this.j = this.a.getWidth() / 2;
            this.k = this.a.getHeight() / 2;
            this.h = this.d[0] + this.j;
        }
        if (this.e == null) {
            this.e = new int[2];
            this.b.getLocationInWindow(this.e);
            this.i = this.e[0];
        }
        AppMethodBeat.o(12932);
    }

    private MFRoundedImageView a(Activity activity, Drawable drawable) {
        AppMethodBeat.i(12934, false);
        MFRoundedImageView mFRoundedImageView = new MFRoundedImageView(activity);
        mFRoundedImageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        mFRoundedImageView.setImageDrawable(drawable);
        mFRoundedImageView.setOval(true);
        AppMethodBeat.o(12934);
        return mFRoundedImageView;
    }

    public void a(View view) {
        AppMethodBeat.i(12935, false);
        ScaleAnimation scaleAnimation = new ScaleAnimation(1.0f, 0.05f, 1.0f, 0.05f, 1, 0.5f, 1, 0.5f);
        scaleAnimation.setDuration((long) this.f);
        scaleAnimation.setFillAfter(false);
        a aVar = new a(view, (float) ((this.i - this.h) / 2));
        aVar.setDuration((long) this.f);
        aVar.setFillAfter(false);
        AnimationSet animationSet = new AnimationSet(false);
        animationSet.setFillAfter(false);
        animationSet.addAnimation(scaleAnimation);
        animationSet.addAnimation(aVar);
        animationSet.setDuration((long) this.f);
        animationSet.setAnimationListener(new AnonymousClass1(view));
        view.startAnimation(animationSet);
        AppMethodBeat.o(12935);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: cn.missfresh.module.base.helper.AddGoodsAnimHelper$1  reason: invalid class name */
    public class AnonymousClass1 implements Animation.AnimationListener {
        final /* synthetic */ View a;

        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationRepeat(Animation animation) {
        }

        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationStart(Animation animation) {
        }

        AnonymousClass1(View view) {
            this.a = view;
        }

        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationEnd(Animation animation) {
            AppMethodBeat.i(12920, false);
            if (AddGoodsAnimHelper.this.o != null) {
                AddGoodsAnimHelper.this.o.clearAnimation();
                AddGoodsAnimHelper.this.o.startAnimation(AddGoodsAnimHelper.this.p);
            }
            this.a.setVisibility(8);
            AddGoodsAnimHelper.this.c.postDelayed(new AnonymousClass1(), (long) AddGoodsAnimHelper.this.g);
            AppMethodBeat.o(12920);
        }

        /* renamed from: cn.missfresh.module.base.helper.AddGoodsAnimHelper$1$1  reason: invalid class name */
        class AnonymousClass1 implements Runnable {
            AnonymousClass1() {
            }

            @Override // java.lang.Runnable
            public void run() {
                AppMethodBeat.i(12914, false);
                if (AddGoodsAnimHelper.this.c != null) {
                    AddGoodsAnimHelper.this.c.removeView(AnonymousClass1.this.a);
                }
                AppMethodBeat.o(12914);
            }
        }
    }

    private ViewGroup a(Activity activity) {
        AppMethodBeat.i(12936, false);
        if (this.c == null) {
            this.c = (ViewGroup) activity.getWindow().getDecorView();
        }
        ViewGroup viewGroup = this.c;
        AppMethodBeat.o(12936);
        return viewGroup;
    }

    private void b(View view) {
        AppMethodBeat.i(12938, false);
        if (this.n == null) {
            int i = this.l;
            this.n = new FrameLayout.LayoutParams(i, i);
            FrameLayout.LayoutParams layoutParams = this.n;
            layoutParams.leftMargin = this.h;
            layoutParams.bottomMargin = this.k;
            layoutParams.gravity = 80;
        }
        view.setLayoutParams(this.n);
        this.c.addView(view);
        AppMethodBeat.o(12938);
    }
}
