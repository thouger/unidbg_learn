package cn.missfresh.ui.multistatelayout;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import cn.missfresh.ui.R;
import com.android.internal.logging.nano.MetricsProto;

public class MultiStateLayout extends FrameLayout {
    private int A;
    private String B;
    private String C;
    private LayoutInflater a;
    private View b;
    private View c;
    private View d;
    private View e;
    private View f;
    private int g;
    private ImageView h;
    private ImageView i;
    private ImageView j;
    private TextView k;
    private TextView l;
    private TextView m;
    private TextView n;
    private TextView o;
    private d p;
    private TextView q;
    private c r;
    private b s;
    private a t;
    private int u;
    private int v;
    private int w;
    private String x;
    private int y;
    private int z;

    public interface a {
        void a();
    }

    public interface b {
        void a(View view);
    }

    public interface c {
        void a();
    }

    public interface d {
        void O_();
    }

    public MultiStateLayout(Context context) {
        this(context, null);
    }

    public MultiStateLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        AppMethodBeat.i(MetricsProto.MetricsEvent.ACTION_PERMISSION_REQUEST_ADD_VOICEMAIL, false);
        this.g = 0;
        this.u = -1;
        this.v = -1;
        this.w = -1;
        this.y = -1;
        this.z = -1;
        this.A = R.drawable.mf_network_error;
        a(attributeSet);
        AppMethodBeat.o(MetricsProto.MetricsEvent.ACTION_PERMISSION_REQUEST_ADD_VOICEMAIL);
    }

    public MultiStateLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        AppMethodBeat.i(MetricsProto.MetricsEvent.ACTION_PERMISSION_DENIED_ADD_VOICEMAIL, false);
        this.g = 0;
        this.u = -1;
        this.v = -1;
        this.w = -1;
        this.y = -1;
        this.z = -1;
        this.A = R.drawable.mf_network_error;
        a(attributeSet);
        AppMethodBeat.o(MetricsProto.MetricsEvent.ACTION_PERMISSION_DENIED_ADD_VOICEMAIL);
    }

    public void setOnRefreshListener(d dVar) {
        this.p = dVar;
    }

    public void setOnLoginListener(c cVar) {
        this.r = cVar;
    }

    public void setOnAddListener(a aVar) {
        this.t = aVar;
    }

    private void a(AttributeSet attributeSet) {
        AppMethodBeat.i(MetricsProto.MetricsEvent.ACTION_PERMISSION_GRANT_RECEIVE_WAP_PUSH, false);
        this.a = LayoutInflater.from(getContext());
        TypedArray obtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, R.styleable.MultiStateLayout);
        this.u = obtainStyledAttributes.getResourceId(R.styleable.MultiStateLayout_msv_loadingView, -1);
        this.v = obtainStyledAttributes.getResourceId(R.styleable.MultiStateLayout_msv_emptyView, -1);
        this.x = obtainStyledAttributes.getNonResourceString(R.styleable.MultiStateLayout_msv_empty_text);
        this.y = obtainStyledAttributes.getResourceId(R.styleable.MultiStateLayout_msv_empty_icon, -1);
        this.z = obtainStyledAttributes.getResourceId(R.styleable.MultiStateLayout_msv_errorView, -1);
        this.g = obtainStyledAttributes.getInt(R.styleable.MultiStateLayout_msv_viewState, 0);
        setViewState(this.g);
        obtainStyledAttributes.recycle();
        AppMethodBeat.o(MetricsProto.MetricsEvent.ACTION_PERMISSION_GRANT_RECEIVE_WAP_PUSH);
    }

    public void setErrorViewResId(int i) {
        this.z = i;
    }

    /* access modifiers changed from: protected */
    @Override // android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        AppMethodBeat.i(MetricsProto.MetricsEvent.ACTION_PERMISSION_REQUEST_RECEIVE_MMS, false);
        super.onAttachedToWindow();
        d();
        AppMethodBeat.o(MetricsProto.MetricsEvent.ACTION_PERMISSION_REQUEST_RECEIVE_MMS);
    }

    @Override // android.view.ViewGroup, android.view.ViewManager
    public void addView(View view, ViewGroup.LayoutParams layoutParams) {
        AppMethodBeat.i(MetricsProto.MetricsEvent.ACTION_PERMISSION_GRANT_READ_EXTERNAL_STORAGE, false);
        super.addView(view, -1, layoutParams);
        AppMethodBeat.o(MetricsProto.MetricsEvent.ACTION_PERMISSION_GRANT_READ_EXTERNAL_STORAGE);
    }

    public void setViewState(int i) {
        AppMethodBeat.i(MetricsProto.MetricsEvent.ACTION_PERMISSION_REVOKE_READ_EXTERNAL_STORAGE, false);
        if (i != this.g) {
            this.g = i;
            d();
        }
        AppMethodBeat.o(MetricsProto.MetricsEvent.ACTION_PERMISSION_REVOKE_READ_EXTERNAL_STORAGE);
    }

    public void setViewStateForRefresh(int i) {
        this.g = i;
    }

    private void d() {
        TextView textView;
        AppMethodBeat.i(MetricsProto.MetricsEvent.PROVISIONING_SESSION_STARTED, false);
        e();
        int i = this.g;
        if (i == 1) {
            if (this.c == null) {
                int i2 = this.z;
                if (i2 > -1) {
                    this.c = this.a.inflate(i2, (ViewGroup) this, false);
                    this.i = (ImageView) this.c.findViewById(R.id.mf_multistate_error_icon);
                    this.l = (TextView) this.c.findViewById(R.id.mf_multistate_error_tip);
                    this.o = (TextView) this.c.findViewById(R.id.mf_multistate_error_refresh);
                    TextView textView2 = this.o;
                    if (textView2 != null) {
                        textView2.setOnClickListener(new AnonymousClass2());
                    }
                } else {
                    this.c = this.a.inflate(R.layout.ui_mf_multistate_error_layout, (ViewGroup) this, false);
                    this.o = (TextView) this.c.findViewById(R.id.mf_multistate_error_refresh);
                    this.i = (ImageView) this.c.findViewById(R.id.mf_multistate_error_icon);
                    this.l = (TextView) this.c.findViewById(R.id.mf_multistate_error_tip);
                    this.o.setOnClickListener(new AnonymousClass3());
                }
                View view = this.c;
                addView(view, view.getLayoutParams());
            }
            if (this.A != 0) {
                ImageView imageView = this.i;
                if (imageView != null) {
                    imageView.setVisibility(0);
                    this.i.setImageResource(this.A);
                }
            } else {
                ImageView imageView2 = this.i;
                if (imageView2 != null) {
                    imageView2.setVisibility(4);
                }
            }
            if (!TextUtils.isEmpty(this.B) && !TextUtils.isEmpty(this.C) && (textView = this.l) != null && this.o != null) {
                textView.setText(this.B);
                this.o.setText(this.C);
            }
            this.c.setVisibility(0);
        } else if (i == 2) {
            if (this.d == null) {
                int i3 = this.v;
                if (i3 > -1) {
                    this.d = this.a.inflate(i3, (ViewGroup) this, false);
                } else {
                    this.d = this.a.inflate(R.layout.ui_mf_multistate_empty_layout, (ViewGroup) this, false);
                    if (this.y > -1) {
                        this.h = (ImageView) this.d.findViewById(R.id.mf_multistate_empty_icon);
                        this.h.setBackgroundResource(this.y);
                    }
                    this.k = (TextView) this.d.findViewById(R.id.mf_multistate_empty_notice);
                    this.k.setText(this.x);
                }
                View view2 = this.d;
                addView(view2, view2.getLayoutParams());
            }
            this.d.setVisibility(0);
        } else if (i == 3) {
            if (this.b == null) {
                int i4 = this.u;
                if (i4 > -1) {
                    this.b = this.a.inflate(i4, (ViewGroup) this, false);
                } else {
                    this.b = this.a.inflate(R.layout.ui_mf_multistate_loading_layout, (ViewGroup) this, false);
                }
                View view3 = this.b;
                addView(view3, view3.getLayoutParams());
            }
            this.b.setVisibility(0);
        } else if (i == 4) {
            if (this.f == null) {
                this.f = this.a.inflate(R.layout.ui_mf_multistate_login_error_layout, (ViewGroup) this, false);
                this.q = (TextView) this.f.findViewById(R.id.mf_multistate_login_tv);
                this.q.setOnClickListener(new AnonymousClass4());
                View view4 = this.f;
                addView(view4, view4.getLayoutParams());
            }
            this.f.setVisibility(0);
        } else if (i == 5) {
            if (this.e == null) {
                int i5 = this.w;
                if (i5 > -1) {
                    this.e = this.a.inflate(i5, (ViewGroup) this, false);
                }
                View view5 = this.e;
                if (view5 != null) {
                    addView(view5, view5.getLayoutParams());
                }
            }
            b bVar = this.s;
            if (bVar != null) {
                bVar.a(this.e);
            }
            this.n = (TextView) this.e.findViewById(R.id.mf_multistate_custom_button);
            TextView textView3 = this.n;
            if (textView3 != null) {
                textView3.setOnClickListener(new AnonymousClass1());
            }
            this.m = (TextView) this.e.findViewById(R.id.mf_multistate_custom_text);
            this.j = (ImageView) this.e.findViewById(R.id.mf_multistate_custom_icon);
        }
        AppMethodBeat.o(MetricsProto.MetricsEvent.PROVISIONING_SESSION_STARTED);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: cn.missfresh.ui.multistatelayout.MultiStateLayout$1  reason: invalid class name */
    public class AnonymousClass1 implements View.OnClickListener {
        AnonymousClass1() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            AppMethodBeat.i(MetricsProto.MetricsEvent.ACTION_PERMISSION_REVOKE_GET_ACCOUNTS, false);
            if (MultiStateLayout.this.t != null) {
                MultiStateLayout.this.t.a();
            }
            AppMethodBeat.onClick(this, view);
            AppMethodBeat.o(MetricsProto.MetricsEvent.ACTION_PERMISSION_REVOKE_GET_ACCOUNTS);
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: cn.missfresh.ui.multistatelayout.MultiStateLayout$2  reason: invalid class name */
    public class AnonymousClass2 implements View.OnClickListener {
        AnonymousClass2() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            AppMethodBeat.i(MetricsProto.MetricsEvent.ACTION_PERMISSION_GRANT_ACCESS_COARSE_LOCATION, false);
            if (MultiStateLayout.this.p != null) {
                MultiStateLayout.this.p.O_();
            }
            AppMethodBeat.onClick(this, view);
            AppMethodBeat.o(MetricsProto.MetricsEvent.ACTION_PERMISSION_GRANT_ACCESS_COARSE_LOCATION);
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: cn.missfresh.ui.multistatelayout.MultiStateLayout$3  reason: invalid class name */
    public class AnonymousClass3 implements View.OnClickListener {
        AnonymousClass3() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            AppMethodBeat.i(MetricsProto.MetricsEvent.ACTION_PERMISSION_REQUEST_READ_PHONE_STATE, false);
            if (MultiStateLayout.this.p != null) {
                MultiStateLayout.this.p.O_();
            }
            AppMethodBeat.onClick(this, view);
            AppMethodBeat.o(MetricsProto.MetricsEvent.ACTION_PERMISSION_REQUEST_READ_PHONE_STATE);
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: cn.missfresh.ui.multistatelayout.MultiStateLayout$4  reason: invalid class name */
    public class AnonymousClass4 implements View.OnClickListener {
        AnonymousClass4() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            AppMethodBeat.i(MetricsProto.MetricsEvent.ACTION_PERMISSION_REVOKE_READ_PHONE_STATE, false);
            if (MultiStateLayout.this.r != null) {
                MultiStateLayout.this.r.a();
            }
            AppMethodBeat.onClick(this, view);
            AppMethodBeat.o(MetricsProto.MetricsEvent.ACTION_PERMISSION_REVOKE_READ_PHONE_STATE);
        }
    }

    private void e() {
        AppMethodBeat.i(MetricsProto.MetricsEvent.ACTION_PERMISSION_DENIED_READ_PHONE_NUMBERS, false);
        View view = this.b;
        if (view != null) {
            view.setVisibility(8);
        }
        View view2 = this.c;
        if (view2 != null) {
            view2.setVisibility(8);
        }
        View view3 = this.d;
        if (view3 != null) {
            view3.setVisibility(8);
        }
        View view4 = this.f;
        if (view4 != null) {
            view4.setVisibility(8);
        }
        View view5 = this.e;
        if (view5 != null) {
            view5.setVisibility(8);
        }
        AppMethodBeat.o(MetricsProto.MetricsEvent.ACTION_PERMISSION_DENIED_READ_PHONE_NUMBERS);
    }

    public void setCustomIcon(Drawable drawable) {
        AppMethodBeat.i(742, false);
        ImageView imageView = this.j;
        if (!(imageView == null || drawable == null)) {
            imageView.setImageDrawable(drawable);
        }
        AppMethodBeat.o(742);
    }

    public void setCustomText(String str) {
        AppMethodBeat.i(MetricsProto.MetricsEvent.SETTINGS_SYSTEM_CATEGORY, false);
        if (!(this.m == null || str == null || str.length() == 0 || str.equalsIgnoreCase("null"))) {
            this.m.setText(str);
        }
        AppMethodBeat.o(MetricsProto.MetricsEvent.SETTINGS_SYSTEM_CATEGORY);
    }

    public void setCustomButtonText(String str) {
        AppMethodBeat.i(MetricsProto.MetricsEvent.SETTINGS_NETWORK_CATEGORY, false);
        if (!(this.n == null || str == null || str.length() == 0 || str.equalsIgnoreCase("null"))) {
            this.n.setText(str);
        }
        AppMethodBeat.o(MetricsProto.MetricsEvent.SETTINGS_NETWORK_CATEGORY);
    }

    public void a() {
        AppMethodBeat.i(752, false);
        setViewState(2);
        AppMethodBeat.o(752);
    }

    public void b() {
        AppMethodBeat.i(MetricsProto.MetricsEvent.RESERVED_FOR_LOGBUILDER_CATEGORY, false);
        setViewState(1);
        AppMethodBeat.o(MetricsProto.MetricsEvent.RESERVED_FOR_LOGBUILDER_CATEGORY);
    }

    public void c() {
        AppMethodBeat.i(MetricsProto.MetricsEvent.RESERVED_FOR_LOGBUILDER_SUBTYPE, false);
        setViewState(0);
        AppMethodBeat.o(MetricsProto.MetricsEvent.RESERVED_FOR_LOGBUILDER_SUBTYPE);
    }

    public void setEmptyViewResId(int i) {
        this.v = i;
    }

    public View getEmptyView() {
        return this.d;
    }

    public void setEmptyText(String str) {
        this.x = str;
    }

    public void setIconEmptyResId(int i) {
        this.y = i;
    }

    public void setOnCustomViewShowListener(b bVar) {
        this.s = bVar;
    }

    public void setCustomViewResId(int i) {
        this.w = i;
    }

    public int getViewState() {
        return this.g;
    }
}
