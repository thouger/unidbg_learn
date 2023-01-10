package cn.missfresh.module.base.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import cn.missfresh.module.base.R;
import cn.missfresh.module.base.network.d;
import cn.missfresh.module.base.widget.MultiStateLayout;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import cn.missfresh.utils.b;
import cn.missfresh.utils.e;

public class NewMultiStateLayout extends FrameLayout {
    private int A;
    private String B;
    private int C;
    private String D;
    private String E;
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
    private MultiStateLayout.d q;
    private TextView r;
    private MultiStateLayout.c s;
    private MultiStateLayout.b t;
    private MultiStateLayout.a u;
    private int v;
    private int w;
    private int x;
    private String y;
    private int z;

    public NewMultiStateLayout(Context context) {
        this(context, null);
    }

    public NewMultiStateLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        AppMethodBeat.i(23751, false);
        this.g = 0;
        this.p = null;
        this.v = -1;
        this.w = -1;
        this.x = -1;
        this.z = -1;
        this.A = -1;
        this.C = R.drawable.img_error_network;
        a(attributeSet);
        AppMethodBeat.o(23751);
    }

    public NewMultiStateLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        AppMethodBeat.i(23752, false);
        this.g = 0;
        this.p = null;
        this.v = -1;
        this.w = -1;
        this.x = -1;
        this.z = -1;
        this.A = -1;
        this.C = R.drawable.img_error_network;
        a(attributeSet);
        AppMethodBeat.o(23752);
    }

    public void setOnRefreshListener(MultiStateLayout.d dVar) {
        this.q = dVar;
    }

    public void setOnLoginListener(MultiStateLayout.c cVar) {
        this.s = cVar;
    }

    public void setOnAddListener(MultiStateLayout.a aVar) {
        this.u = aVar;
    }

    private void a(AttributeSet attributeSet) {
        AppMethodBeat.i(23753, false);
        this.a = LayoutInflater.from(getContext());
        TypedArray obtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, R.styleable.MultiStateLayout);
        this.v = obtainStyledAttributes.getResourceId(R.styleable.MultiStateLayout_msv_loadingView, -1);
        this.w = obtainStyledAttributes.getResourceId(R.styleable.MultiStateLayout_msv_emptyView, -1);
        this.y = obtainStyledAttributes.getNonResourceString(R.styleable.MultiStateLayout_msv_empty_text);
        this.z = obtainStyledAttributes.getResourceId(R.styleable.MultiStateLayout_msv_empty_icon, -1);
        this.A = obtainStyledAttributes.getResourceId(R.styleable.MultiStateLayout_msv_errorView, -1);
        this.B = obtainStyledAttributes.getString(R.styleable.MultiStateLayout_msv_empty_btn_text);
        this.g = obtainStyledAttributes.getInt(R.styleable.MultiStateLayout_msv_viewState, 0);
        setViewState(this.g);
        obtainStyledAttributes.recycle();
        AppMethodBeat.o(23753);
    }

    /* access modifiers changed from: protected */
    @Override // android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        AppMethodBeat.i(23754, false);
        super.onAttachedToWindow();
        c();
        AppMethodBeat.o(23754);
    }

    @Override // android.view.ViewGroup, android.view.ViewManager
    public void addView(View view, ViewGroup.LayoutParams layoutParams) {
        AppMethodBeat.i(23755, false);
        super.addView(view, -1, layoutParams);
        AppMethodBeat.o(23755);
    }

    public void setViewState(int i) {
        AppMethodBeat.i(23756, false);
        if (i != this.g) {
            this.g = i;
            c();
        }
        AppMethodBeat.o(23756);
    }

    public void setViewStateForRefresh(int i) {
        this.g = i;
    }

    private void c() {
        int i;
        int i2;
        AppMethodBeat.i(23757, false);
        g();
        int i3 = this.g;
        if (i3 == 1) {
            f();
        } else if (i3 == 2) {
            e();
        } else if (i3 == 3) {
            d();
        } else if (i3 == 4) {
            if (this.f == null) {
                this.f = this.a.inflate(R.layout.layout_not_login_page_error, (ViewGroup) this, false);
                this.r = (TextView) this.f.findViewById(R.id.tv_login);
                this.r.setOnClickListener(new AnonymousClass2());
            }
            this.f.setVisibility(0);
            View view = this.f;
            addView(view, view.getLayoutParams());
        } else if (i3 == 5) {
            if (this.e == null && (i2 = this.x) > -1) {
                this.e = this.a.inflate(i2, (ViewGroup) this, false);
            }
            View view2 = this.e;
            if (view2 != null) {
                addView(view2, view2.getLayoutParams());
                this.n = (TextView) this.e.findViewById(R.id.btn_custom_button);
                this.m = (TextView) this.e.findViewById(R.id.tv_custom_text);
                this.j = (ImageView) this.e.findViewById(R.id.iv_custom_icon);
                TextView textView = this.n;
                if (textView != null) {
                    textView.setOnClickListener(new AnonymousClass1());
                }
                if (this.m != null && !b.a(this.y)) {
                    this.m.setText(this.y);
                }
                ImageView imageView = this.j;
                if (imageView != null && (i = this.z) > -1) {
                    imageView.setImageResource(i);
                }
            }
            MultiStateLayout.b bVar = this.t;
            if (bVar != null) {
                bVar.a(this.e);
            }
        }
        AppMethodBeat.o(23757);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: cn.missfresh.module.base.widget.NewMultiStateLayout$1  reason: invalid class name */
    public class AnonymousClass1 implements View.OnClickListener {
        AnonymousClass1() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            AppMethodBeat.i(23746, false);
            if (NewMultiStateLayout.this.u != null) {
                NewMultiStateLayout.this.u.b();
            }
            AppMethodBeat.onClick(this, view);
            AppMethodBeat.o(23746);
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: cn.missfresh.module.base.widget.NewMultiStateLayout$2  reason: invalid class name */
    public class AnonymousClass2 implements View.OnClickListener {
        AnonymousClass2() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            AppMethodBeat.i(23747, false);
            if (NewMultiStateLayout.this.s != null) {
                NewMultiStateLayout.this.s.b();
            }
            AppMethodBeat.onClick(this, view);
            AppMethodBeat.o(23747);
        }
    }

    private void d() {
        AppMethodBeat.i(23758, false);
        if (this.b == null) {
            int i = this.v;
            if (i > -1) {
                this.b = this.a.inflate(i, (ViewGroup) this, false);
            } else {
                this.b = this.a.inflate(R.layout.layout_loading_page_loading_new, (ViewGroup) this, false);
            }
        }
        this.b.setOnClickListener(new AnonymousClass3());
        View view = this.b;
        addView(view, view.getLayoutParams());
        this.b.setVisibility(0);
        AppMethodBeat.o(23758);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: cn.missfresh.module.base.widget.NewMultiStateLayout$3  reason: invalid class name */
    public class AnonymousClass3 implements View.OnClickListener {
        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
        }

        AnonymousClass3() {
        }
    }

    private void e() {
        AppMethodBeat.i(23759, false);
        if (this.d == null) {
            int i = this.w;
            if (i > -1) {
                this.d = this.a.inflate(i, (ViewGroup) this, false);
                View findViewById = this.d.findViewById(R.id.tv_add);
                if (findViewById != null) {
                    findViewById.setOnClickListener(new AnonymousClass4());
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
                if (this.o != null) {
                    if (e.a(this.B)) {
                        this.o.setVisibility(8);
                    } else {
                        this.o.setText(this.B);
                        this.o.setVisibility(0);
                        this.o.setOnClickListener(new AnonymousClass5());
                    }
                }
            }
        }
        View view = this.d;
        addView(view, view.getLayoutParams());
        this.d.setVisibility(0);
        AppMethodBeat.o(23759);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: cn.missfresh.module.base.widget.NewMultiStateLayout$4  reason: invalid class name */
    public class AnonymousClass4 implements View.OnClickListener {
        AnonymousClass4() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            AppMethodBeat.i(23748, false);
            if (NewMultiStateLayout.this.u != null) {
                NewMultiStateLayout.this.u.b();
            }
            AppMethodBeat.onClick(this, view);
            AppMethodBeat.o(23748);
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: cn.missfresh.module.base.widget.NewMultiStateLayout$5  reason: invalid class name */
    public class AnonymousClass5 implements View.OnClickListener {
        AnonymousClass5() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            AppMethodBeat.i(23749, false);
            if (NewMultiStateLayout.this.u != null) {
                NewMultiStateLayout.this.u.b();
            }
            AppMethodBeat.onClick(this, view);
            AppMethodBeat.o(23749);
        }
    }

    private void f() {
        AppMethodBeat.i(23760, false);
        if (this.c == null) {
            int i = this.A;
            if (i > -1) {
                this.c = this.a.inflate(i, (ViewGroup) this, false);
                this.i = (ImageView) this.c.findViewById(R.id.net_icon);
                this.l = (TextView) this.c.findViewById(R.id.net_tip);
                this.p = (TextView) this.c.findViewById(R.id.tv_refresh);
            } else {
                this.c = this.a.inflate(R.layout.layout_loading_page_error, (ViewGroup) this, false);
                this.p = (TextView) this.c.findViewById(R.id.tv_refresh);
                this.i = (ImageView) this.c.findViewById(R.id.net_icon);
                this.l = (TextView) this.c.findViewById(R.id.net_tip);
            }
            TextView textView = this.p;
            if (textView != null) {
                textView.setOnClickListener(new AnonymousClass6());
            }
        }
        ImageView imageView = this.i;
        if (imageView != null) {
            if (this.C != 0) {
                imageView.setVisibility(0);
                this.i.setImageResource(this.C);
            } else {
                imageView.setVisibility(4);
            }
        }
        if (this.l != null && !e.a(this.D)) {
            this.l.setText(this.D);
        }
        if (this.p != null && !e.a(this.E)) {
            this.p.setText(this.E);
        }
        View view = this.c;
        addView(view, view.getLayoutParams());
        this.c.setVisibility(0);
        AppMethodBeat.o(23760);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: cn.missfresh.module.base.widget.NewMultiStateLayout$6  reason: invalid class name */
    public class AnonymousClass6 implements View.OnClickListener {
        AnonymousClass6() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            AppMethodBeat.i(23750, false);
            if (NewMultiStateLayout.this.q != null) {
                NewMultiStateLayout.this.q.f_();
            }
            AppMethodBeat.onClick(this, view);
            AppMethodBeat.o(23750);
        }
    }

    private void g() {
        AppMethodBeat.i(23761, false);
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
        AppMethodBeat.o(23761);
    }

    public void setCustomIcon(String str) {
        AppMethodBeat.i(23763, false);
        if (this.j != null) {
            d.a(getContext(), str, -1, -1, -1, -1, this.j);
        }
        AppMethodBeat.o(23763);
    }

    public void setCustomText(String str) {
        AppMethodBeat.i(23764, false);
        TextView textView = this.m;
        if (textView != null) {
            textView.setText(str);
        }
        AppMethodBeat.o(23764);
    }

    public void setCustomButtonText(String str) {
        AppMethodBeat.i(23765, false);
        TextView textView = this.n;
        if (textView != null) {
            textView.setText(str);
        }
        AppMethodBeat.o(23765);
    }

    public void a() {
        AppMethodBeat.i(23766, false);
        a(this.C);
        AppMethodBeat.o(23766);
    }

    public void a(int i) {
        AppMethodBeat.i(23767, false);
        a(i, "", getResources().getString(R.string.refresh));
        AppMethodBeat.o(23767);
    }

    public void a(int i, String str, String str2) {
        AppMethodBeat.i(23770, false);
        b(i, str, str2);
        setViewState(1);
        AppMethodBeat.o(23770);
    }

    public void setErrorViewResId(int i) {
        this.A = i;
    }

    public void setErrorResource(int i) {
        AppMethodBeat.i(23771, false);
        b(i, "", getResources().getString(R.string.refresh));
        AppMethodBeat.o(23771);
    }

    public void setErrorResource(String str) {
        AppMethodBeat.i(23772, false);
        b(this.C, str, getResources().getString(R.string.refresh));
        AppMethodBeat.o(23772);
    }

    public void b(int i, String str, String str2) {
        this.C = i;
        this.D = str;
        this.E = str2;
    }

    public void b() {
        AppMethodBeat.i(23774, false);
        setViewState(0);
        AppMethodBeat.o(23774);
    }

    public void a(String str, String str2) {
        AppMethodBeat.i(23780, false);
        c(this.z, str, str2);
        AppMethodBeat.o(23780);
    }

    public void c(int i, String str, String str2) {
        AppMethodBeat.i(23781, false);
        d(i, str, str2);
        setViewState(2);
        AppMethodBeat.o(23781);
    }

    public void setEmptyViewResId(int i) {
        this.w = i;
    }

    public View getEmptyView() {
        return this.d;
    }

    public void setEmptyIconResId(int i) {
        AppMethodBeat.i(23782, false);
        d(i, "", "");
        AppMethodBeat.o(23782);
    }

    public void setEmptyResource(String str) {
        AppMethodBeat.i(23783, false);
        b(str, "");
        AppMethodBeat.o(23783);
    }

    public void b(String str, String str2) {
        AppMethodBeat.i(23784, false);
        d(this.z, str, str2);
        AppMethodBeat.o(23784);
    }

    public void d(int i, String str, String str2) {
        this.z = i;
        this.y = str;
        this.B = str2;
    }

    public void setOnCustomViewShowListener(MultiStateLayout.b bVar) {
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
