package cn.missfresh.lib.image.c;

import android.graphics.drawable.Drawable;
import cn.missfresh.lib.image.config.ImageConfig;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import com.android.internal.telephony.gsm.SmsCbConstants;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;

/* compiled from: LoadTarget */
public abstract class b<R> implements c<R> {
    public void a(Drawable drawable) {
    }

    public void b(Drawable drawable) {
    }

    public void c(Drawable drawable) {
    }

    /* compiled from: LoadTarget */
    /* renamed from: cn.missfresh.lib.image.c.b$1  reason: invalid class name */
    class AnonymousClass1 extends SimpleTarget<R> {
        final /* synthetic */ ImageConfig a;

        AnonymousClass1(ImageConfig imageConfig) {
            this.a = imageConfig;
        }

        @Override // com.bumptech.glide.request.target.BaseTarget, com.bumptech.glide.request.target.Target
        public void onLoadStarted(Drawable drawable) {
            AppMethodBeat.i(SmsCbConstants.MESSAGE_ID_CMAS_ALERT_EXERCISE, false);
            super.onLoadStarted(drawable);
            b.this.a(drawable);
            AppMethodBeat.o(SmsCbConstants.MESSAGE_ID_CMAS_ALERT_EXERCISE);
        }

        @Override // com.bumptech.glide.request.target.Target
        public void onResourceReady(R r, Transition<? super R> transition) {
            AppMethodBeat.i(SmsCbConstants.MESSAGE_ID_CMAS_ALERT_EXTREME_IMMEDIATE_OBSERVED_LANGUAGE, false);
            b.this.a((Object) r);
            AppMethodBeat.o(SmsCbConstants.MESSAGE_ID_CMAS_ALERT_EXTREME_IMMEDIATE_OBSERVED_LANGUAGE);
        }

        @Override // com.bumptech.glide.request.target.BaseTarget, com.bumptech.glide.request.target.Target
        public void onLoadFailed(Drawable drawable) {
            AppMethodBeat.i(SmsCbConstants.MESSAGE_ID_CMAS_ALERT_EXTREME_EXPECTED_LIKELY_LANGUAGE, false);
            super.onLoadFailed(drawable);
            b.this.b(drawable);
            b.this.a(this.a, drawable);
            AppMethodBeat.o(SmsCbConstants.MESSAGE_ID_CMAS_ALERT_EXTREME_EXPECTED_LIKELY_LANGUAGE);
        }

        @Override // com.bumptech.glide.request.target.BaseTarget, com.bumptech.glide.request.target.Target
        public void onLoadCleared(Drawable drawable) {
            AppMethodBeat.i(SmsCbConstants.MESSAGE_ID_CMAS_ALERT_SEVERE_EXPECTED_OBSERVED_LANGUAGE, false);
            b.this.a(this.a, drawable);
            b.this.c(drawable);
            AppMethodBeat.o(SmsCbConstants.MESSAGE_ID_CMAS_ALERT_SEVERE_EXPECTED_OBSERVED_LANGUAGE);
        }
    }

    public final SimpleTarget<R> a(ImageConfig imageConfig) {
        return new AnonymousClass1(imageConfig);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void a(ImageConfig imageConfig, Drawable drawable) {
        if (imageConfig.H() != null) {
            imageConfig.H().setImageBitmap(null);
            imageConfig.H().setImageDrawable(drawable);
        }
    }
}
