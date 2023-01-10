package cn.missfresh.module.base.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.CountDownTimer;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import cn.missfresh.module.base.R;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;

public class MultiStateLayout extends FrameLayout {
    private int A;
    private String B;
    private int C;
    private String D;
    private String E;
    private boolean F;
    private CountDownTimer G;
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
    private TextView p;
    private d q;
    private TextView r;
    private c s;
    private b t;
    private a u;
    private int v;
    private int w;
    private int x;
    private String y;
    private int z;

    public interface a {
        void b();
    }

    public interface b {
        void a(View view);
    }

    public interface c {
        void b();
    }

    public interface d {
        void f_();
    }

    public MultiStateLayout(Context context) {
        this(context, null);
    }

    public MultiStateLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        AppMethodBeat.i(23727, false);
        this.g = 0;
        this.v = -1;
        this.w = -1;
        this.x = -1;
        this.z = -1;
        this.A = -1;
        this.C = R.drawable.img_error_network;
        a(attributeSet);
        AppMethodBeat.o(23727);
    }

    public MultiStateLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        AppMethodBeat.i(23728, false);
        this.g = 0;
        this.v = -1;
        this.w = -1;
        this.x = -1;
        this.z = -1;
        this.A = -1;
        this.C = R.drawable.img_error_network;
        a(attributeSet);
        AppMethodBeat.o(23728);
    }

    public void setOnRefreshListener(d dVar) {
        this.q = dVar;
    }

    public void setOnLoginListener(c cVar) {
        this.s = cVar;
    }

    public void setOnAddListener(a aVar) {
        this.u = aVar;
    }

    private void a(AttributeSet attributeSet) {
        AppMethodBeat.i(23729, false);
        this.a = LayoutInflater.from(getContext());
        TypedArray obtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, R.styleable.MultiStateLayout);
        this.v = obtainStyledAttributes.getResourceId(R.styleable.MultiStateLayout_msv_loadingView, -1);
        this.w = obtainStyledAttributes.getResourceId(R.styleable.MultiStateLayout_msv_emptyView, -1);
        this.y = obtainStyledAttributes.getNonResourceString(R.styleable.MultiStateLayout_msv_empty_text);
        this.z = obtainStyledAttributes.getResourceId(R.styleable.MultiStateLayout_msv_empty_icon, -1);
        this.F = obtainStyledAttributes.getBoolean(R.styleable.MultiStateLayout_msv_important, false);
        this.A = obtainStyledAttributes.getResourceId(R.styleable.MultiStateLayout_msv_errorView, -1);
        this.B = obtainStyledAttributes.getString(R.styleable.MultiStateLayout_msv_empty_btn_text);
        this.g = obtainStyledAttributes.getInt(R.styleable.MultiStateLayout_msv_viewState, 0);
        setViewState(this.g);
        obtainStyledAttributes.recycle();
        AppMethodBeat.o(23729);
    }

    public void setErrorViewResId(int i) {
        this.A = i;
    }

    /* access modifiers changed from: protected */
    @Override // android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        AppMethodBeat.i(23730, false);
        super.onAttachedToWindow();
        f();
        AppMethodBeat.o(23730);
    }

    @Override // android.view.ViewGroup, android.view.ViewManager
    public void addView(View view, ViewGroup.LayoutParams layoutParams) {
        AppMethodBeat.i(23731, false);
        super.addView(view, -1, layoutParams);
        AppMethodBeat.o(23731);
    }

    public void setViewState(int i) {
        AppMethodBeat.i(23732, false);
        if (i != this.g) {
            this.g = i;
            f();
        }
        AppMethodBeat.o(23732);
    }

    public void setViewStateForRefresh(int i) {
        this.g = i;
    }

    private void f() {
        TextView textView;
        int i;
        int i2;
        AppMethodBeat.i(23733, false);
        g();
        setLoadingTimerState(this.g == 3);
        int i3 = this.g;
        if (i3 == 1) {
            if (this.c == null) {
                int i4 = this.A;
                if (i4 > -1) {
                    this.c = this.a.inflate(i4, (ViewGroup) this, false);
                    this.i = (ImageView) this.c.findViewById(R.id.net_icon);
                    this.l = (TextView) this.c.findViewById(R.id.net_tip);
                    this.p = (TextView) this.c.findViewById(R.id.tv_refresh);
                    TextView textView2 = this.p;
                    if (textView2 != null) {
                        textView2.setOnClickListener(new AnonymousClass5());
                    }
                } else {
                    this.c = this.a.inflate(R.layout.layout_loading_page_error, (ViewGroup) this, false);
                    this.p = (TextView) this.c.findViewById(R.id.tv_refresh);
                    this.i = (ImageView) this.c.findViewById(R.id.net_icon);
                    this.l = (TextView) this.c.findViewById(R.id.net_tip);
                    this.p.setOnClickListener(new AnonymousClass6());
                }
            }
            if (this.C != 0) {
                ImageView imageView = this.i;
                if (imageView != null) {
                    imageView.setVisibility(0);
                    this.i.setImageResource(this.C);
                }
            } else {
                ImageView imageView2 = this.i;
                if (imageView2 != null) {
                    imageView2.setVisibility(4);
                }
            }
            if (!TextUtils.isEmpty(this.D) && !TextUtils.isEmpty(this.E) && (textView = this.l) != null && this.p != null) {
                textView.setText(this.D);
                this.p.setText(this.E);
            }
            View view = this.c;
            addView(view, view.getLayoutParams());
            this.c.setVisibility(0);
        } else if (i3 == 2) {
            if (this.d == null) {
                int i5 = this.w;
                if (i5 > -1) {
                    this.d = this.a.inflate(i5, (ViewGroup) this, false);
                    View findViewById = this.d.findViewById(R.id.tv_add);
                    if (findViewById != null) {
                        findViewById.setOnClickListener(new AnonymousClass2());
                    }
                } else {
                    this.d = this.a.inflate(R.layout.layout_empty_page, (ViewGroup) this, false);
                    if (this.z > -1) {
                        this.h = (ImageView) this.d.findViewById(R.id.iv_image);
                        this.h.setImageResource(this.z);
                    }
                    this.k = (TextView) this.d.findViewById(R.id.tv_notice);
                    this.k.setText(this.y);
                    this.o = (TextView) this.d.findViewById(R.id.tv_btn_text);
                    TextView textView3 = this.o;
                    if (textView3 != null) {
                        textView3.setText(this.B);
                        this.o.setVisibility(TextUtils.isEmpty(this.B) ? 8 : 0);
                        this.o.setOnClickListener(new AnonymousClass3());
                    }
                }
            }
            View view2 = this.d;
            addView(view2, view2.getLayoutParams());
            this.d.setVisibility(0);
        } else if (i3 == 3) {
            if (this.b == null) {
                int i6 = this.v;
                if (i6 > -1) {
                    this.b = this.a.inflate(i6, (ViewGroup) this, false);
                } else {
                    this.b = this.a.inflate(R.layout.layout_loading_page_loading, (ViewGroup) this, false);
                }
            }
            this.b.setOnClickListener(new AnonymousClass1());
            View view3 = this.b;
            addView(view3, view3.getLayoutParams());
            this.b.setVisibility(0);
        } else if (i3 == 4) {
            if (this.f == null) {
                this.f = this.a.inflate(R.layout.layout_not_login_page_error, (ViewGroup) this, false);
                this.r = (TextView) this.f.findViewById(R.id.tv_login);
                this.r.setOnClickListener(new AnonymousClass7());
            }
            View view4 = this.f;
            addView(view4, view4.getLayoutParams());
            this.f.setVisibility(0);
        } else if (i3 == 5) {
            if (this.e == null && (i2 = this.x) > -1) {
                this.e = this.a.inflate(i2, (ViewGroup) this, false);
            }
            View view5 = this.e;
            if (view5 != null) {
                addView(view5, view5.getLayoutParams());
                this.n = (TextView) this.e.findViewById(R.id.btn_custom_button);
                this.m = (TextView) this.e.findViewById(R.id.tv_custom_text);
                this.j = (ImageView) this.e.findViewById(R.id.iv_custom_icon);
                TextView textView4 = this.n;
                if (textView4 != null) {
                    textView4.setOnClickListener(new AnonymousClass4());
                }
                if (this.m != null && !cn.missfresh.utils.b.a(this.y)) {
                    this.m.setText(this.y);
                }
                ImageView imageView3 = this.j;
                if (imageView3 != null && (i = this.z) > -1) {
                    imageView3.setImageResource(i);
                }
            }
            b bVar = this.t;
            if (bVar != null) {
                bVar.a(this.e);
            }
        }
        AppMethodBeat.o(23733);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: cn.missfresh.module.base.widget.MultiStateLayout$1  reason: invalid class name */
    public class AnonymousClass1 implements View.OnClickListener {
        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
        }

        AnonymousClass1() {
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: cn.missfresh.module.base.widget.MultiStateLayout$2  reason: invalid class name */
    public class AnonymousClass2 implements View.OnClickListener {
        AnonymousClass2() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            AppMethodBeat.i(23720, false);
            if (MultiStateLayout.this.u != null) {
                MultiStateLayout.this.u.b();
            }
            AppMethodBeat.onClick(this, view);
            AppMethodBeat.o(23720);
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: cn.missfresh.module.base.widget.MultiStateLayout$3  reason: invalid class name */
    public class AnonymousClass3 implements View.OnClickListener {
        AnonymousClass3() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            AppMethodBeat.i(23721, false);
            if (MultiStateLayout.this.u != null) {
                MultiStateLayout.this.u.b();
            }
            AppMethodBeat.onClick(this, view);
            AppMethodBeat.o(23721);
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: cn.missfresh.module.base.widget.MultiStateLayout$4  reason: invalid class name */
    public class AnonymousClass4 implements View.OnClickListener {
        AnonymousClass4() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            AppMethodBeat.i(23722, false);
            if (MultiStateLayout.this.u != null) {
                MultiStateLayout.this.u.b();
            }
            AppMethodBeat.onClick(this, view);
            AppMethodBeat.o(23722);
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: cn.missfresh.module.base.widget.MultiStateLayout$5  reason: invalid class name */
    public class AnonymousClass5 implements View.OnClickListener {
        AnonymousClass5() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            AppMethodBeat.i(23723, false);
            if (MultiStateLayout.this.q != null) {
                MultiStateLayout.this.q.f_();
            }
            AppMethodBeat.onClick(this, view);
            AppMethodBeat.o(23723);
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: cn.missfresh.module.base.widget.MultiStateLayout$6  reason: invalid class name */
    public class AnonymousClass6 implements View.OnClickListener {
        AnonymousClass6() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            AppMethodBeat.i(23724, false);
            if (MultiStateLayout.this.q != null) {
                MultiStateLayout.this.q.f_();
            }
            AppMethodBeat.onClick(this, view);
            AppMethodBeat.o(23724);
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: cn.missfresh.module.base.widget.MultiStateLayout$7  reason: invalid class name */
    public class AnonymousClass7 implements View.OnClickListener {
        AnonymousClass7() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            AppMethodBeat.i(23725, false);
            if (MultiStateLayout.this.s != null) {
                MultiStateLayout.this.s.b();
            }
            AppMethodBeat.onClick(this, view);
            AppMethodBeat.o(23725);
        }
    }

    private void setLoadingTimerState(boolean z) {
        AppMethodBeat.i(23734, false);
        CountDownTimer countDownTimer = this.G;
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
        if (!this.F) {
            AppMethodBeat.o(23734);
            return;
        }
        if (z) {
            this.G = new AnonymousClass8(8000, 8000);
            this.G.start();
        }
        AppMethodBeat.o(23734);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: cn.missfresh.module.base.widget.MultiStateLayout$8  reason: invalid class name */
    public class AnonymousClass8 extends CountDownTimer {
        @Override // android.os.CountDownTimer
        public void onTick(long j) {
        }

        AnonymousClass8(long j, long j2) {
            super(j, j2);
        }

        @Override // android.os.CountDownTimer
        public void onFinish() {
            AppMethodBeat.i(23726, false);
            if (MultiStateLayout.this.g == 3) {
                MultiStateLayout.this.setViewState(1);
            }
            AppMethodBeat.o(23726);
        }
    }

    /* access modifiers changed from: protected */
    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        AppMethodBeat.i(23735, false);
        super.onDetachedFromWindow();
        CountDownTimer countDownTimer = this.G;
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
        AppMethodBeat.o(23735);
    }

    private void g() {
        AppMethodBeat.i(23736, false);
        View view = this.b;
        if (view != null) {
            removeView(view);
        }
        View view2 = this.c;
        if (view2 != null) {
            removeView(view2);
        }
        View view3 = this.d;
        if (view3 != null) {
            removeView(view3);
        }
        View view4 = this.f;
        if (view4 != null) {
            removeView(view4);
        }
        View view5 = this.e;
        if (view5 != null) {
            removeView(view5);
        }
        AppMethodBeat.o(23736);
    }

    public void setCustomIcon(String str) {
        AppMethodBeat.i(23737, false);
        if (this.j != null) {
            cn.missfresh.module.base.network.d.a(getContext(), str, -1, -1, -1, -1, this.j);
        }
        AppMethodBeat.o(23737);
    }

    public void setCustomText(String str) {
        AppMethodBeat.i(23738, false);
        TextView textView = this.m;
        if (textView != null) {
            textView.setText(str);
        }
        AppMethodBeat.o(23738);
    }

    public void setCustomButtonText(String str) {
        AppMethodBeat.i(23739, false);
        TextView textView = this.n;
        if (textView != null) {
            textView.setText(str);
        }
        AppMethodBeat.o(23739);
    }

    public TextView getRefresh() {
        return this.p;
    }

    public void a() {
        AppMethodBeat.i(23740, false);
        setViewState(2);
        AppMethodBeat.o(23740);
    }

    public void b() {
        AppMethodBeat.i(23742, false);
        setViewState(1);
        AppMethodBeat.o(23742);
    }

    public void c() {
        AppMethodBeat.i(23743, false);
        setViewState(0);
        AppMethodBeat.o(23743);
    }

    public void d() {
        AppMethodBeat.i(23744, false);
        setViewState(3);
        AppMethodBeat.o(23744);
    }

    public void e() {
        AppMethodBeat.i(23745, false);
        setViewState(4);
        AppMethodBeat.o(23745);
    }

    public void setEmptyViewResId(int i) {
        this.w = i;
    }

    public void setLoadingViewResId(int i) {
        this.v = i;
    }

    public View getEmptyView() {
        return this.d;
    }

    public void setEmptyText(String str) {
        this.y = str;
    }

    public void setIconEmptyResId(int i) {
        this.z = i;
    }

    public void setOnCustomViewShowListener(b bVar) {
        this.t = bVar;
    }

    public void setCustomViewResId(int i) {
        this.x = i;
    }

    public int getViewState() {
        return this.g;
    }

    public View getCustomView() {
        return this.e;
    }
}
