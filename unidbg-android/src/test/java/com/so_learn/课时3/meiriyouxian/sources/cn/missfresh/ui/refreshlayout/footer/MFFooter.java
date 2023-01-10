package cn.missfresh.ui.refreshlayout.footer;

import android.content.Context;
import android.content.res.TypedArray;
import android.telephony.PreciseDisconnectCause;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import cn.missfresh.ui.R;
import com.android.internal.logging.nano.MetricsProto;
import com.scwang.smartrefresh.layout.api.RefreshFooter;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.RefreshState;
import com.scwang.smartrefresh.layout.constant.b;
import com.scwang.smartrefresh.layout.internal.InternalClassics;
import com.scwang.smartrefresh.layout.internal.a;
import com.scwang.smartrefresh.layout.internal.c;

public class MFFooter extends InternalClassics<Object> implements RefreshFooter {
    public static String a;
    public static String b;
    public static String c;
    public static String d;
    public static String e;
    public static String f;
    public static String g;
    protected boolean h;

    @Override // com.scwang.smartrefresh.layout.internal.InternalClassics, com.scwang.smartrefresh.layout.internal.InternalAbstract, com.scwang.smartrefresh.layout.api.RefreshInternal
    public int onFinish(RefreshLayout refreshLayout, boolean z) {
        return 0;
    }

    public MFFooter(Context context) {
        this(context, null);
    }

    public MFFooter(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public MFFooter(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        AppMethodBeat.i(1230, false);
        this.h = false;
        View.inflate(context, R.layout.ui_mf_rf_footer, this);
        if (a == null) {
            a = context.getString(R.string.mf_footer_pulling);
        }
        if (b == null) {
            b = context.getString(R.string.mf_footer_release);
        }
        if (c == null) {
            c = context.getString(R.string.mf_footer_loading);
        }
        if (d == null) {
            d = context.getString(R.string.mf_footer_refreshing);
        }
        if (e == null) {
            e = context.getString(R.string.mf_footer_finish);
        }
        if (f == null) {
            f = context.getString(R.string.mf_footer_failed);
        }
        if (g == null) {
            g = context.getString(R.string.missfresh_footer_nothing);
        }
        ImageView imageView = (ImageView) findViewById(R.id.srl_classics_arrow);
        this.m = imageView;
        ImageView imageView2 = (ImageView) findViewById(R.id.srl_classics_progress);
        this.n = imageView2;
        this.l = (TextView) findViewById(R.id.srl_classics_title);
        this.l.setTextColor(-3750202);
        this.l.setText(isInEditMode() ? c : a);
        this.l.setVisibility(8);
        this.m.setVisibility(8);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.ClassicsFooter);
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) imageView.getLayoutParams();
        RelativeLayout.LayoutParams layoutParams2 = (RelativeLayout.LayoutParams) imageView2.getLayoutParams();
        layoutParams.rightMargin = 0;
        layoutParams.width = obtainStyledAttributes.getLayoutDimension(R.styleable.ClassicsFooter_srlDrawableArrowSize, layoutParams.width);
        layoutParams.height = obtainStyledAttributes.getLayoutDimension(R.styleable.ClassicsFooter_srlDrawableArrowSize, layoutParams.height);
        layoutParams2.width = obtainStyledAttributes.getLayoutDimension(R.styleable.ClassicsFooter_srlDrawableProgressSize, layoutParams2.width);
        layoutParams2.height = obtainStyledAttributes.getLayoutDimension(R.styleable.ClassicsFooter_srlDrawableProgressSize, layoutParams2.height);
        layoutParams.width = obtainStyledAttributes.getLayoutDimension(R.styleable.ClassicsFooter_srlDrawableSize, layoutParams.width);
        layoutParams.height = obtainStyledAttributes.getLayoutDimension(R.styleable.ClassicsFooter_srlDrawableSize, layoutParams.height);
        layoutParams2.width = obtainStyledAttributes.getLayoutDimension(R.styleable.ClassicsFooter_srlDrawableSize, layoutParams2.width);
        layoutParams2.height = obtainStyledAttributes.getLayoutDimension(R.styleable.ClassicsFooter_srlDrawableSize, layoutParams2.height);
        this.u = obtainStyledAttributes.getInt(R.styleable.ClassicsFooter_srlFinishDuration, this.u);
        this.z = b.f[obtainStyledAttributes.getInt(R.styleable.ClassicsFooter_srlClassicsSpinnerStyle, this.z.g)];
        if (obtainStyledAttributes.hasValue(R.styleable.ClassicsFooter_srlDrawableArrow)) {
            this.m.setImageDrawable(obtainStyledAttributes.getDrawable(R.styleable.ClassicsFooter_srlDrawableArrow));
        } else {
            this.p = new a();
            this.p.a(-3750202);
            this.m.setImageDrawable(this.p);
        }
        if (obtainStyledAttributes.hasValue(R.styleable.ClassicsFooter_srlDrawableProgress)) {
            this.n.setImageDrawable(obtainStyledAttributes.getDrawable(R.styleable.ClassicsFooter_srlDrawableProgress));
        } else {
            this.q = new c();
            this.q.a(-3750202);
            this.n.setImageDrawable(this.q);
        }
        if (obtainStyledAttributes.hasValue(R.styleable.ClassicsFooter_srlTextSizeTitle)) {
            this.l.setTextSize(0, (float) obtainStyledAttributes.getDimensionPixelSize(R.styleable.ClassicsFooter_srlTextSizeTitle, com.scwang.smartrefresh.layout.b.b.a(16.0f)));
        } else {
            this.l.setTextSize(12.0f);
        }
        if (obtainStyledAttributes.hasValue(R.styleable.ClassicsFooter_srlPrimaryColor)) {
            a(obtainStyledAttributes.getColor(R.styleable.ClassicsFooter_srlPrimaryColor, 0));
        }
        if (obtainStyledAttributes.hasValue(R.styleable.ClassicsFooter_srlAccentColor)) {
            b(obtainStyledAttributes.getColor(R.styleable.ClassicsFooter_srlAccentColor, 0));
        }
        obtainStyledAttributes.recycle();
        AppMethodBeat.o(1230);
    }

    @Override // com.scwang.smartrefresh.layout.internal.InternalClassics, com.scwang.smartrefresh.layout.internal.InternalAbstract, com.scwang.smartrefresh.layout.api.RefreshInternal
    public void onStartAnimator(RefreshLayout refreshLayout, int i, int i2) {
        AppMethodBeat.i(MetricsProto.MetricsEvent.FIELD_ANOMALY_ACTION_TYPE, false);
        if (!this.h) {
            super.onStartAnimator(refreshLayout, i, i2);
        }
        AppMethodBeat.o(MetricsProto.MetricsEvent.FIELD_ANOMALY_ACTION_TYPE);
    }

    @Override // com.scwang.smartrefresh.layout.internal.InternalClassics, com.scwang.smartrefresh.layout.internal.InternalAbstract, com.scwang.smartrefresh.layout.api.RefreshInternal
    @Deprecated
    public void setPrimaryColors(int... iArr) {
        AppMethodBeat.i(1240, false);
        if (this.z == b.c) {
            super.setPrimaryColors(iArr);
        }
        AppMethodBeat.o(1240);
    }

    @Override // com.scwang.smartrefresh.layout.internal.InternalAbstract, com.scwang.smartrefresh.layout.api.RefreshFooter
    public boolean setNoMoreData(boolean z) {
        AppMethodBeat.i(1243, false);
        if (this.h != z) {
            this.h = z;
            ImageView imageView = this.m;
            if (z) {
                this.l.setText(g);
                this.l.setVisibility(0);
                imageView.setVisibility(8);
                this.n.setVisibility(8);
            } else {
                this.l.setText(a);
                imageView.setVisibility(0);
                this.n.setVisibility(0);
                this.l.setVisibility(8);
            }
        }
        AppMethodBeat.o(1243);
        return true;
    }

    @Override // com.scwang.smartrefresh.layout.internal.InternalAbstract
    public void a(RefreshLayout refreshLayout, RefreshState refreshState, RefreshState refreshState2) {
        AppMethodBeat.i(1247, false);
        ImageView imageView = this.m;
        if (!this.h) {
            switch (AnonymousClass1.a[refreshState2.ordinal()]) {
                case 1:
                    imageView.setVisibility(0);
                case 2:
                    this.l.setText(a);
                    imageView.animate().rotation(180.0f);
                    break;
                case 3:
                case 4:
                    imageView.setVisibility(8);
                    this.l.setText(c);
                    break;
                case 5:
                    this.l.setText(b);
                    imageView.animate().rotation(0.0f);
                    break;
                case 6:
                    this.l.setText(d);
                    imageView.setVisibility(8);
                    break;
            }
        }
        AppMethodBeat.o(1247);
    }

    /* renamed from: cn.missfresh.ui.refreshlayout.footer.MFFooter$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] a = new int[RefreshState.values().length];

        static {
            AppMethodBeat.i(PreciseDisconnectCause.LOCAL_CALL_VOLTE_RETRY_REQUIRED, false);
            try {
                a[RefreshState.None.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                a[RefreshState.PullUpToLoad.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                a[RefreshState.Loading.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                a[RefreshState.LoadReleased.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                a[RefreshState.ReleaseToLoad.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                a[RefreshState.Refreshing.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            AppMethodBeat.o(PreciseDisconnectCause.LOCAL_CALL_VOLTE_RETRY_REQUIRED);
        }
    }
}
