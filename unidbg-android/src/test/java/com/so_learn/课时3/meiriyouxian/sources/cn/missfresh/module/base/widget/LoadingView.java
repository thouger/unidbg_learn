package cn.missfresh.module.base.widget;

import android.content.Context;
import android.util.AttributeSet;
import androidx.appcompat.widget.AppCompatImageView;
import cn.missfresh.module.base.R;
import cn.missfresh.module.base.refresh.a;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import cn.missfresh.utils.a.d;

public class LoadingView extends AppCompatImageView {
    private a.b a;

    public LoadingView(Context context) {
        super(context);
        AppMethodBeat.i(23707, false);
        a();
        AppMethodBeat.o(23707);
    }

    public LoadingView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        AppMethodBeat.i(23708, false);
        a();
        AppMethodBeat.o(23708);
    }

    public LoadingView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        AppMethodBeat.i(23709, false);
        a();
        AppMethodBeat.o(23709);
    }

    /* access modifiers changed from: protected */
    @Override // android.view.View
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        AppMethodBeat.i(23710, false);
        super.onLayout(z, i, i2, i3, i4);
        setAnimationStatus(AnimStatus.START);
        AppMethodBeat.o(23710);
    }

    private void a() {
        AppMethodBeat.i(23711, false);
        if (this.a == null) {
            this.a = a.a(getContext(), R.array.loading_anim, 10).a(this);
        }
        AppMethodBeat.o(23711);
    }

    @Override // android.widget.ImageView, android.view.View
    public void setVisibility(int i) {
        AppMethodBeat.i(23712, false);
        if (getVisibility() != i) {
            super.setVisibility(i);
            if (i == 8 || i == 4) {
                setAnimationStatus(AnimStatus.END);
                d.c("anim_loading", " visibility stop anim");
            } else {
                setAnimationStatus(AnimStatus.START);
                d.c("anim_loading", " visibility start anim");
            }
        }
        AppMethodBeat.o(23712);
    }

    /* access modifiers changed from: protected */
    @Override // android.widget.ImageView, android.view.View
    public void onAttachedToWindow() {
        AppMethodBeat.i(23713, false);
        super.onAttachedToWindow();
        if (getVisibility() == 0) {
            setAnimationStatus(AnimStatus.START);
        }
        AppMethodBeat.o(23713);
    }

    /* access modifiers changed from: protected */
    @Override // android.widget.ImageView, android.view.View
    public void onDetachedFromWindow() {
        AppMethodBeat.i(23714, false);
        super.onDetachedFromWindow();
        setAnimationStatus(AnimStatus.CANCEL);
        AppMethodBeat.o(23714);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: cn.missfresh.module.base.widget.LoadingView$1  reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] a = new int[AnimStatus.values().length];

        static {
            AppMethodBeat.i(23703, false);
            try {
                a[AnimStatus.START.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                a[AnimStatus.END.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                a[AnimStatus.CANCEL.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            AppMethodBeat.o(23703);
        }
    }

    public void setAnimationStatus(AnimStatus animStatus) {
        AppMethodBeat.i(23715, false);
        int i = AnonymousClass1.a[animStatus.ordinal()];
        if (i == 1) {
            this.a.a();
        } else if (i == 2 || i == 3) {
            clearAnimation();
            this.a.b();
        }
        AppMethodBeat.o(23715);
    }

    public enum AnimStatus {
        START,
        END,
        CANCEL;

        public static AnimStatus valueOf(String str) {
            AppMethodBeat.i(23705, false);
            AnimStatus animStatus = (AnimStatus) Enum.valueOf(AnimStatus.class, str);
            AppMethodBeat.o(23705);
            return animStatus;
        }

        static {
            AppMethodBeat.i(23706, false);
            AppMethodBeat.o(23706);
        }
    }
}
