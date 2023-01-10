package cn.missfresh.lib.image.c;

import android.graphics.drawable.Drawable;
import android.view.View;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import com.bumptech.glide.request.target.ViewTarget;
import com.bumptech.glide.request.transition.Transition;

/* compiled from: MFViewTarget */
public abstract class d<T extends View, Z> implements c<Z> {
    protected final T a;

    public void a(Drawable drawable) {
    }

    public void b(Drawable drawable) {
    }

    public void c(Drawable drawable) {
    }

    public d(T t) {
        this.a = t;
    }

    /* compiled from: MFViewTarget */
    /* renamed from: cn.missfresh.lib.image.c.d$1  reason: invalid class name */
    class AnonymousClass1 extends ViewTarget<T, Z> {
        AnonymousClass1(View view) {
            super(view);
        }

        @Override // com.bumptech.glide.request.target.Target
        public void onResourceReady(Z z, Transition<? super Z> transition) {
            AppMethodBeat.i(4402, false);
            d.this.a((Object) z);
            AppMethodBeat.o(4402);
        }

        @Override // com.bumptech.glide.request.target.ViewTarget, com.bumptech.glide.request.target.BaseTarget, com.bumptech.glide.request.target.Target
        public void onLoadStarted(Drawable drawable) {
            AppMethodBeat.i(4405, false);
            super.onLoadStarted(drawable);
            d.this.a(drawable);
            AppMethodBeat.o(4405);
        }

        @Override // com.bumptech.glide.request.target.BaseTarget, com.bumptech.glide.request.target.Target
        public void onLoadFailed(Drawable drawable) {
            AppMethodBeat.i(4407, false);
            super.onLoadFailed(drawable);
            d.this.b(drawable);
            AppMethodBeat.o(4407);
        }

        @Override // com.bumptech.glide.request.target.ViewTarget, com.bumptech.glide.request.target.BaseTarget, com.bumptech.glide.request.target.Target
        public void onLoadCleared(Drawable drawable) {
            AppMethodBeat.i(4410, false);
            super.onLoadCleared(drawable);
            d.this.c(drawable);
            AppMethodBeat.o(4410);
        }
    }

    public final ViewTarget<T, Z> a() {
        return new AnonymousClass1(this.a);
    }
}
