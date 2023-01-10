package cn.missfresh.module.base.widget.search;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import androidx.vectordrawable.graphics.drawable.PathInterpolatorCompat;
import cn.missfresh.module.base.R;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;

class BannerView extends FrameLayout {
    private int a;
    private int b;
    private int c;
    private View d;
    private View e;
    private Animation f;
    private Animation g;
    private a h;
    private b i;
    private Runnable j;

    /* access modifiers changed from: private */
    public interface b {
    }

    static /* synthetic */ void a(BannerView bannerView) {
        AppMethodBeat.i(24284, false);
        bannerView.g();
        AppMethodBeat.o(24284);
    }

    /* renamed from: cn.missfresh.module.base.widget.search.BannerView$1  reason: invalid class name */
    class AnonymousClass1 implements Runnable {
        AnonymousClass1() {
        }

        @Override // java.lang.Runnable
        public void run() {
            AppMethodBeat.i(24269, false);
            BannerView.a(BannerView.this);
            BannerView.this.i.a(this, (long) BannerView.this.b);
            AppMethodBeat.o(24269);
        }
    }

    public BannerView(Context context) {
        this(context, null);
    }

    public BannerView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public BannerView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        AppMethodBeat.i(24272, false);
        this.a = 0;
        this.b = PathInterpolatorCompat.MAX_NUM_POINTS;
        this.c = 800;
        this.i = new b();
        this.j = new AnonymousClass1();
        this.f = AnimationUtils.loadAnimation(getContext(), R.anim.anim_search_box_in);
        this.g = AnimationUtils.loadAnimation(getContext(), R.anim.anim_search_box_out);
        this.f.setDuration((long) this.c);
        this.g.setDuration((long) this.c);
        AppMethodBeat.o(24272);
    }

    public void a(a aVar) {
        AppMethodBeat.i(24273, false);
        if (aVar == null) {
            AppMethodBeat.o(24273);
            return;
        }
        this.h = aVar;
        this.h.a(new AnonymousClass2());
        d();
        AppMethodBeat.o(24273);
    }

    /* renamed from: cn.missfresh.module.base.widget.search.BannerView$2  reason: invalid class name */
    class AnonymousClass2 implements b {
        AnonymousClass2() {
        }
    }

    private void d() {
        AppMethodBeat.i(24274, false);
        e();
        if (this.h.a() == 0) {
            AppMethodBeat.o(24274);
            return;
        }
        f();
        a(this.d, this.a);
        if (this.h.a() < 2) {
            AppMethodBeat.o(24274);
            return;
        }
        a();
        AppMethodBeat.o(24274);
    }

    private void e() {
        AppMethodBeat.i(24275, false);
        View view = this.e;
        if (view != null) {
            view.clearAnimation();
        }
        View view2 = this.d;
        if (view2 != null) {
            view2.clearAnimation();
        }
        clearAnimation();
        removeAllViews();
        b();
        this.a = 0;
        AppMethodBeat.o(24275);
    }

    private void f() {
        AppMethodBeat.i(24276, false);
        this.d = this.h.a(this);
        this.e = this.h.a(this);
        addView(this.e);
        addView(this.d);
        AppMethodBeat.o(24276);
    }

    private void a(View view, int i) {
        AppMethodBeat.i(24277, false);
        this.h.a(view, i);
        AppMethodBeat.o(24277);
    }

    public void a() {
        AppMethodBeat.i(24278, false);
        this.i.a(this.j);
        this.i.a(this.j, (long) this.b);
        AppMethodBeat.o(24278);
    }

    public void b() {
        AppMethodBeat.i(24279, false);
        this.i.a(this.j);
        AppMethodBeat.o(24279);
    }

    private void g() {
        AppMethodBeat.i(24280, false);
        a aVar = this.h;
        if (aVar == null || aVar.a() <= 1) {
            AppMethodBeat.o(24280);
            return;
        }
        this.a++;
        int i = this.a;
        if (i % 2 == 0) {
            a(this.d, i % this.h.a());
            a(this.d, this.e);
            bringChildToFront(this.e);
        } else {
            a(this.e, i % this.h.a());
            a(this.e, this.d);
            bringChildToFront(this.d);
        }
        AppMethodBeat.o(24280);
    }

    public int c() {
        AppMethodBeat.i(24281, false);
        int a2 = this.a % this.h.a();
        AppMethodBeat.o(24281);
        return a2;
    }

    private void a(View view, View view2) {
        AppMethodBeat.i(24283, false);
        view.startAnimation(this.f);
        view.setVisibility(0);
        view2.startAnimation(this.g);
        view2.setVisibility(0);
        this.f.setAnimationListener(new AnonymousClass3(view2));
        AppMethodBeat.o(24283);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: cn.missfresh.module.base.widget.search.BannerView$3  reason: invalid class name */
    public class AnonymousClass3 implements Animation.AnimationListener {
        final /* synthetic */ View a;

        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationRepeat(Animation animation) {
        }

        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationStart(Animation animation) {
        }

        AnonymousClass3(View view) {
            this.a = view;
        }

        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationEnd(Animation animation) {
            AppMethodBeat.i(24271, false);
            this.a.setVisibility(8);
            AppMethodBeat.o(24271);
        }
    }

    public static abstract class a {
        private b a;

        public abstract int a();

        public abstract View a(ViewGroup viewGroup);

        public abstract void a(View view, int i);

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void a(b bVar) {
            this.a = bVar;
        }
    }
}
