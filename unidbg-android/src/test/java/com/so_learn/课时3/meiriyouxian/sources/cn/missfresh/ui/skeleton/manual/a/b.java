package cn.missfresh.ui.skeleton.manual.a;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import androidx.core.content.ContextCompat;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import cn.missfresh.ui.R;
import cn.missfresh.ui.skeleton.ShimmerLayout;

/* compiled from: ViewSkeletonScreen */
public class b implements cn.missfresh.ui.skeleton.manual.b {
    private static final String a = b.class.getName();
    private final a b;
    private final View c;
    private final int d;
    private final int e;
    private final boolean f;
    private final int g;
    private final int h;

    /* synthetic */ b(a aVar, AnonymousClass1 r2) {
        this(aVar);
    }

    static {
        AppMethodBeat.i(2246, false);
        AppMethodBeat.o(2246);
    }

    private b(a aVar) {
        AppMethodBeat.i(2226, false);
        this.c = aVar.a;
        this.d = aVar.b;
        this.f = aVar.c;
        this.g = aVar.e;
        this.h = aVar.f;
        this.e = aVar.d;
        this.b = new a(aVar.a);
        AppMethodBeat.o(2226);
    }

    private ShimmerLayout a(ViewGroup viewGroup) {
        AppMethodBeat.i(2230, false);
        ShimmerLayout shimmerLayout = (ShimmerLayout) LayoutInflater.from(this.c.getContext()).inflate(R.layout.layout_shimmer, viewGroup, false);
        shimmerLayout.setShimmerColor(this.e);
        shimmerLayout.setShimmerAngle(this.h);
        shimmerLayout.setShimmerAnimationDuration(this.g);
        View inflate = LayoutInflater.from(this.c.getContext()).inflate(this.d, (ViewGroup) shimmerLayout, false);
        ViewGroup.LayoutParams layoutParams = inflate.getLayoutParams();
        if (layoutParams != null) {
            shimmerLayout.setLayoutParams(layoutParams);
        }
        shimmerLayout.addView(inflate);
        shimmerLayout.addOnAttachStateChangeListener(new AnonymousClass1(shimmerLayout));
        shimmerLayout.a();
        AppMethodBeat.o(2230);
        return shimmerLayout;
    }

    /* compiled from: ViewSkeletonScreen */
    /* access modifiers changed from: package-private */
    /* renamed from: cn.missfresh.ui.skeleton.manual.a.b$1  reason: invalid class name */
    public class AnonymousClass1 implements View.OnAttachStateChangeListener {
        final /* synthetic */ ShimmerLayout a;

        AnonymousClass1(ShimmerLayout shimmerLayout) {
            this.a = shimmerLayout;
        }

        @Override // android.view.View.OnAttachStateChangeListener
        public void onViewAttachedToWindow(View view) {
            AppMethodBeat.i(2185, false);
            this.a.a();
            AppMethodBeat.o(2185);
        }

        @Override // android.view.View.OnAttachStateChangeListener
        public void onViewDetachedFromWindow(View view) {
            AppMethodBeat.i(2189, false);
            this.a.b();
            AppMethodBeat.o(2189);
        }
    }

    private View c() {
        AppMethodBeat.i(2235, false);
        ViewParent parent = this.c.getParent();
        if (parent == null) {
            Log.e(a, "the source view have not attach to any view");
            AppMethodBeat.o(2235);
            return null;
        }
        ViewGroup viewGroup = (ViewGroup) parent;
        if (this.f) {
            ShimmerLayout a2 = a(viewGroup);
            AppMethodBeat.o(2235);
            return a2;
        }
        View inflate = LayoutInflater.from(this.c.getContext()).inflate(this.d, viewGroup, false);
        AppMethodBeat.o(2235);
        return inflate;
    }

    public void b() {
        AppMethodBeat.i(2237, false);
        View c = c();
        if (c != null) {
            this.b.a(c);
        }
        AppMethodBeat.o(2237);
    }

    @Override // cn.missfresh.ui.skeleton.manual.b
    public void a() {
        AppMethodBeat.i(2239, false);
        if (this.b.b() instanceof ShimmerLayout) {
            ((ShimmerLayout) this.b.b()).b();
        }
        this.b.a();
        AppMethodBeat.o(2239);
    }

    /* compiled from: ViewSkeletonScreen */
    public static class a {
        private final View a;
        private int b;
        private boolean c = true;
        private int d;
        private int e = 1000;
        private int f = 20;

        public a(View view) {
            AppMethodBeat.i(2197, false);
            this.a = view;
            this.d = ContextCompat.getColor(this.a.getContext(), R.color.shimmer_color);
            AppMethodBeat.o(2197);
        }

        public a a(int i) {
            this.b = i;
            return this;
        }

        public a b(int i) {
            AppMethodBeat.i(2199, false);
            this.d = ContextCompat.getColor(this.a.getContext(), i);
            AppMethodBeat.o(2199);
            return this;
        }

        public a a(boolean z) {
            this.c = z;
            return this;
        }

        public a c(int i) {
            this.e = i;
            return this;
        }

        public b a() {
            AppMethodBeat.i(2211, false);
            b bVar = new b(this, null);
            bVar.b();
            AppMethodBeat.o(2211);
            return bVar;
        }
    }
}
