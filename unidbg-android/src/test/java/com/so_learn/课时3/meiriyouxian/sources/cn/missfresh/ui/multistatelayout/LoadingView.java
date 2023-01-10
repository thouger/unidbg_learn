package cn.missfresh.ui.multistatelayout;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.AnimationDrawable;
import android.util.AttributeSet;
import androidx.appcompat.widget.AppCompatImageView;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import cn.missfresh.ui.R;
import com.android.internal.logging.nano.MetricsProto;

public class LoadingView extends AppCompatImageView {
    private final int a = 50;
    private AnimationDrawable b;

    public LoadingView(Context context) {
        super(context);
        AppMethodBeat.i(MetricsProto.MetricsEvent.ACTION_PERMISSION_GRANT_UNKNOWN, false);
        a(null);
        AppMethodBeat.o(MetricsProto.MetricsEvent.ACTION_PERMISSION_GRANT_UNKNOWN);
    }

    public LoadingView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        AppMethodBeat.i(MetricsProto.MetricsEvent.ACTION_PERMISSION_REQUEST_READ_CALENDAR, false);
        a(attributeSet);
        AppMethodBeat.o(MetricsProto.MetricsEvent.ACTION_PERMISSION_REQUEST_READ_CALENDAR);
    }

    public LoadingView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        AppMethodBeat.i(MetricsProto.MetricsEvent.ACTION_PERMISSION_DENIED_READ_CALENDAR, false);
        a(attributeSet);
        AppMethodBeat.o(MetricsProto.MetricsEvent.ACTION_PERMISSION_DENIED_READ_CALENDAR);
    }

    /* access modifiers changed from: protected */
    @Override // android.view.View
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        AppMethodBeat.i(640, false);
        super.onLayout(z, i, i2, i3, i4);
        setAnimationStatus(AnimStatus.START);
        AppMethodBeat.o(640);
    }

    private void a(AttributeSet attributeSet) {
        AppMethodBeat.i(MetricsProto.MetricsEvent.ACTION_PERMISSION_GRANT_CAMERA, false);
        Resources resources = getResources();
        this.b = new AnimationDrawable();
        this.b = new AnimationDrawable();
        this.b.addFrame(resources.getDrawable(R.drawable.mf_refresh_header_0), 50);
        this.b.addFrame(resources.getDrawable(R.drawable.mf_refresh_header_1), 50);
        this.b.addFrame(resources.getDrawable(R.drawable.mf_refresh_header_2), 50);
        this.b.addFrame(resources.getDrawable(R.drawable.mf_refresh_header_3), 50);
        this.b.addFrame(resources.getDrawable(R.drawable.mf_refresh_header_4), 50);
        this.b.addFrame(resources.getDrawable(R.drawable.mf_refresh_header_5), 50);
        this.b.addFrame(resources.getDrawable(R.drawable.mf_refresh_header_6), 50);
        this.b.addFrame(resources.getDrawable(R.drawable.mf_refresh_header_7), 50);
        this.b.addFrame(resources.getDrawable(R.drawable.mf_refresh_header_8), 50);
        this.b.addFrame(resources.getDrawable(R.drawable.mf_refresh_header_9), 50);
        this.b.setOneShot(false);
        setImageResource(R.drawable.mf_refresh_header_0);
        AppMethodBeat.o(MetricsProto.MetricsEvent.ACTION_PERMISSION_GRANT_CAMERA);
    }

    @Override // android.widget.ImageView, android.view.View
    public void setVisibility(int i) {
        AppMethodBeat.i(MetricsProto.MetricsEvent.ACTION_PERMISSION_REVOKE_CAMERA, false);
        if (getVisibility() != i) {
            super.setVisibility(i);
            if (i == 8 || i == 4) {
                setAnimationStatus(AnimStatus.END);
            } else {
                setAnimationStatus(AnimStatus.START);
            }
        }
        AppMethodBeat.o(MetricsProto.MetricsEvent.ACTION_PERMISSION_REVOKE_CAMERA);
    }

    /* access modifiers changed from: protected */
    @Override // android.widget.ImageView, android.view.View
    public void onDetachedFromWindow() {
        AppMethodBeat.i(MetricsProto.MetricsEvent.ACTION_PERMISSION_GRANT_READ_CONTACTS, false);
        super.onDetachedFromWindow();
        setAnimationStatus(AnimStatus.CANCEL);
        AppMethodBeat.o(MetricsProto.MetricsEvent.ACTION_PERMISSION_GRANT_READ_CONTACTS);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: cn.missfresh.ui.multistatelayout.LoadingView$1  reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] a = new int[AnimStatus.values().length];

        static {
            AppMethodBeat.i(MetricsProto.MetricsEvent.PROVISIONING_ENTRY_POINT_QR_CODE, false);
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
            AppMethodBeat.o(MetricsProto.MetricsEvent.PROVISIONING_ENTRY_POINT_QR_CODE);
        }
    }

    public void setAnimationStatus(AnimStatus animStatus) {
        AppMethodBeat.i(MetricsProto.MetricsEvent.ACTION_PERMISSION_GRANT_WRITE_CONTACTS, false);
        int i = AnonymousClass1.a[animStatus.ordinal()];
        if (i == 1) {
            setImageDrawable(this.b);
            this.b.start();
        } else if (i == 2) {
            clearAnimation();
            this.b.stop();
            clearAnimation();
        } else if (i == 3) {
            clearAnimation();
            this.b.stop();
            clearAnimation();
        }
        AppMethodBeat.o(MetricsProto.MetricsEvent.ACTION_PERMISSION_GRANT_WRITE_CONTACTS);
    }

    public enum AnimStatus {
        START,
        END,
        CANCEL;

        public static AnimStatus valueOf(String str) {
            AppMethodBeat.i(MetricsProto.MetricsEvent.PROVISIONING_CREATE_PROFILE_TASK_MS, false);
            AnimStatus animStatus = (AnimStatus) Enum.valueOf(AnimStatus.class, str);
            AppMethodBeat.o(MetricsProto.MetricsEvent.PROVISIONING_CREATE_PROFILE_TASK_MS);
            return animStatus;
        }

        static {
            AppMethodBeat.i(MetricsProto.MetricsEvent.PROVISIONING_CANCELLED, false);
            AppMethodBeat.o(MetricsProto.MetricsEvent.PROVISIONING_CANCELLED);
        }
    }
}
