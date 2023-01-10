package com.cmic.sso.wy.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Point;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.util.DisplayMetrics;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.bangcle.andjni.JniLib;
import com.cmic.sso.wy.b.d;
import com.cmic.sso.wy.b.e;
import com.cmic.sso.wy.b.f;
import com.cmic.sso.wy.utils.aa;
import com.cmic.sso.wy.utils.g;
import com.cmic.sso.wy.utils.h;
import com.cmic.sso.wy.utils.j;
import com.cmic.sso.wy.utils.o;
import com.cmic.sso.wy.utils.v;
import com.cmic.sso.wy.utils.x;
import java.lang.ref.WeakReference;
import org.json.JSONException;
import org.json.JSONObject;

public class LoginAuthActivity extends Activity implements View.OnClickListener {
    protected static final String a = LoginAuthActivity.class.getSimpleName();
    private Handler b;
    private Context c;
    private RelativeLayout d;
    private com.cmic.sso.wy.widget.a e;
    private com.cmic.sso.wy.widget.a f;
    private com.cmic.sso.wy.widget.a g;
    private Bundle h;
    private e i;
    private String j = "";
    private CheckBox k;
    private LinearLayout l;
    private RelativeLayout m;
    private long n = 0;
    private int o = 0;
    private a p = null;
    private d q;
    private boolean r = true;
    private LinearLayout s;
    private String t;
    private com.cmic.sso.wy.a u;
    private int v;
    private int w;
    private boolean x;
    private Dialog y;

    /* access modifiers changed from: package-private */
    /* renamed from: com.cmic.sso.wy.activity.LoginAuthActivity$6  reason: invalid class name */
    public class AnonymousClass6 implements DialogInterface.OnKeyListener {
        final /* synthetic */ LoginAuthActivity a;

        AnonymousClass6(LoginAuthActivity loginAuthActivity) {
            JniLib.cV(this, loginAuthActivity, 959);
        }

        @Override // android.content.DialogInterface.OnKeyListener
        public boolean onKey(DialogInterface dialogInterface, int i, KeyEvent keyEvent) {
            return i == 4;
        }
    }

    /* access modifiers changed from: protected */
    @Override // android.app.Activity
    public void onCreate(Bundle bundle) {
        try {
            super.onCreate(bundle);
            if (bundle != null) {
                finish();
            }
            this.c = this;
            this.u = com.cmic.sso.wy.b.a.a(this.c).a();
            if (!(this.u == null || this.u.aa() == -1)) {
                setTheme(this.u.aa());
            }
            com.cmic.sso.wy.utils.c.a("authPageIn");
            this.n = System.currentTimeMillis();
            this.i = e.a(this);
            d();
            f();
        } catch (Exception e) {
            com.cmic.sso.wy.d.a.a.add(e);
            g.a(a, e.toString());
            e.printStackTrace();
            a("200025", "\u53d1\u751f\u672a\u77e5\u9519\u8bef", this.h, null);
        }
    }

    /* access modifiers changed from: protected */
    @Override // android.app.Activity
    public void onResume() {
        super.onResume();
        try {
            if (this.h != null) {
                this.h.putString("loginMethod", "loginAuth");
            }
            com.cmic.sso.wy.b.a.a(this).a("200087", (JSONObject) null);
        } catch (Exception e) {
            com.cmic.sso.wy.d.a.a.add(e);
            a("200025", "\u53d1\u751f\u672a\u77e5\u9519\u8bef", this.h, null);
        }
    }

    private void d() {
        String str;
        this.h = getIntent().getExtras();
        if (this.h == null) {
            this.h = new Bundle();
        }
        this.q = j.c(this.h.getString("traceId", ""));
        getWindowManager().getDefaultDisplay().getMetrics(new DisplayMetrics());
        this.b = new Handler(getMainLooper());
        this.p = new a(this);
        this.j = this.h.getString("securityphone");
        String str2 = a;
        g.b(str2, "mSecurityPhone value is " + this.j);
        String string = this.h.getString("operatorType", "");
        String str3 = a;
        g.b(str3, "operator value is " + string);
        if (string.equals("1")) {
            this.t = "\u4e2d\u56fd\u79fb\u52a8\u8ba4\u8bc1\u670d\u52a1\u6761\u6b3e";
            str = "http://wap.cmpassport.com/resources/html/contract.html";
        } else if (string.equals("3")) {
            this.t = "\u4e2d\u56fd\u7535\u4fe1\u5929\u7ffc\u8d26\u53f7\u670d\u52a1\u6761\u6b3e";
            str = "https://e.189.cn/sdk/agreement/detail.do";
        } else {
            this.t = "\u4e2d\u56fd\u8054\u901a\u8ba4\u8bc1\u670d\u52a1\u534f\u8bae";
            str = "https://opencloud.wostore.cn/authz/resource/html/disclaimer.html?fromsdk=true";
        }
        this.e = new com.cmic.sso.wy.widget.a(this.c, 16973840, null, str);
        this.e.setOnKeyListener(new AnonymousClass1(this));
        if (!TextUtils.isEmpty(this.u.G())) {
            this.f = new com.cmic.sso.wy.widget.a(this.c, 16973840, this.u.F(), this.u.G());
            this.f.setOnKeyListener(new AnonymousClass2(this));
        }
        if (!TextUtils.isEmpty(this.u.I())) {
            this.g = new com.cmic.sso.wy.widget.a(this.c, 16973840, this.u.H(), this.u.I());
            this.g.setOnKeyListener(new AnonymousClass3(this));
        }
        h.a().a(new AnonymousClass4(this));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: com.cmic.sso.wy.activity.LoginAuthActivity$1  reason: invalid class name */
    public class AnonymousClass1 implements DialogInterface.OnKeyListener {
        final /* synthetic */ LoginAuthActivity a;

        AnonymousClass1(LoginAuthActivity loginAuthActivity) {
            JniLib.cV(this, loginAuthActivity, 954);
        }

        @Override // android.content.DialogInterface.OnKeyListener
        public boolean onKey(DialogInterface dialogInterface, int i, KeyEvent keyEvent) {
            if (i == 4 && keyEvent.getAction() == 1 && keyEvent.getRepeatCount() == 0) {
                this.a.e.dismiss();
            }
            return true;
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: com.cmic.sso.wy.activity.LoginAuthActivity$2  reason: invalid class name */
    public class AnonymousClass2 implements DialogInterface.OnKeyListener {
        final /* synthetic */ LoginAuthActivity a;

        AnonymousClass2(LoginAuthActivity loginAuthActivity) {
            JniLib.cV(this, loginAuthActivity, 955);
        }

        @Override // android.content.DialogInterface.OnKeyListener
        public boolean onKey(DialogInterface dialogInterface, int i, KeyEvent keyEvent) {
            if (i == 4 && keyEvent.getAction() == 1 && keyEvent.getRepeatCount() == 0) {
                this.a.f.dismiss();
            }
            return true;
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: com.cmic.sso.wy.activity.LoginAuthActivity$3  reason: invalid class name */
    public class AnonymousClass3 implements DialogInterface.OnKeyListener {
        final /* synthetic */ LoginAuthActivity a;

        AnonymousClass3(LoginAuthActivity loginAuthActivity) {
            JniLib.cV(this, loginAuthActivity, 956);
        }

        @Override // android.content.DialogInterface.OnKeyListener
        public boolean onKey(DialogInterface dialogInterface, int i, KeyEvent keyEvent) {
            if (i == 4 && keyEvent.getAction() == 1 && keyEvent.getRepeatCount() == 0) {
                this.a.g.dismiss();
            }
            return true;
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: com.cmic.sso.wy.activity.LoginAuthActivity$4  reason: invalid class name */
    public class AnonymousClass4 implements h.a {
        final /* synthetic */ LoginAuthActivity a;

        AnonymousClass4(LoginAuthActivity loginAuthActivity) {
            JniLib.cV(this, loginAuthActivity, 957);
        }

        @Override // com.cmic.sso.wy.utils.h.a
        public void a() {
            this.a.b.removeCallbacksAndMessages(null);
            if (this.a.e != null && this.a.e.isShowing()) {
                this.a.e.dismiss();
            }
            if (this.a.f != null && this.a.f.isShowing()) {
                this.a.f.dismiss();
            }
            this.a.a(true);
        }
    }

    private void e() {
        int i;
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) this.m.getLayoutParams();
        if (this.u.m() > 0 || this.u.n() < 0) {
            int makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, 0);
            this.m.measure(makeMeasureSpec, makeMeasureSpec);
            String str = a;
            g.b(str, "mPhoneLayout.getMeasuredHeight()=" + this.m.getMeasuredHeight());
            if (this.u.m() <= 0 || (this.v - this.m.getMeasuredHeight()) - x.a(this.c, (float) this.u.m()) <= 0) {
                layoutParams.addRule(12, -1);
            } else {
                g.b(a, "numberField_top");
                layoutParams.addRule(10, -1);
                layoutParams.setMargins(0, x.a(this.c, (float) this.u.m()), 0, 0);
            }
        } else if (this.u.n() <= 0 || (this.v - this.m.getMeasuredHeight()) - x.a(this.c, (float) this.u.n()) <= 0) {
            layoutParams.addRule(10, -1);
        } else {
            g.b(a, "numberField_bottom");
            layoutParams.addRule(12, -1);
            layoutParams.setMargins(0, 0, 0, x.a(this.c, (float) this.u.n()));
        }
        this.m.setLayoutParams(layoutParams);
        RelativeLayout.LayoutParams layoutParams2 = (RelativeLayout.LayoutParams) this.d.getLayoutParams();
        int u = this.u.u() < 0 ? 0 : this.u.u();
        int v = this.u.v() < 0 ? 0 : this.u.v();
        if (this.u.w() > 0 || this.u.x() < 0) {
            if (this.u.w() <= 0 || this.v - x.a(this.c, (float) (this.u.t() + this.u.w())) <= 0) {
                layoutParams2.addRule(12, -1);
                layoutParams2.setMargins(x.a(this.c, (float) u), 0, x.a(this.c, (float) v), 0);
            } else {
                g.b(a, "logBtn_top");
                layoutParams2.addRule(10, -1);
                layoutParams2.setMargins(x.a(this.c, (float) u), x.a(this.c, (float) this.u.w()), x.a(this.c, (float) v), 0);
            }
        } else if (this.u.x() <= 0 || this.v - x.a(this.c, (float) (this.u.t() + this.u.x())) <= 0) {
            layoutParams2.addRule(10, -1);
            layoutParams2.setMargins(x.a(this.c, (float) u), 0, x.a(this.c, (float) v), 0);
        } else {
            g.b(a, "logBtn_bottom");
            layoutParams2.addRule(12, -1);
            layoutParams2.setMargins(x.a(this.c, (float) u), 0, x.a(this.c, (float) v), x.a(this.c, (float) this.u.x()));
        }
        this.d.setLayoutParams(layoutParams2);
        RelativeLayout.LayoutParams layoutParams3 = (RelativeLayout.LayoutParams) this.l.getLayoutParams();
        if (this.u.N() >= 0) {
            i = this.u.B() > 30 ? this.u.N() : this.u.N() - (30 - this.u.B());
        } else {
            i = this.u.B() > 30 ? 0 : -(30 - this.u.B());
        }
        int O = this.u.O() < 0 ? 0 : this.u.O();
        int makeMeasureSpec2 = View.MeasureSpec.makeMeasureSpec(0, 0);
        this.l.measure(makeMeasureSpec2, makeMeasureSpec2);
        if (this.u.P() > 0 || this.u.Q() < 0) {
            if (this.u.P() <= 0 || this.v - x.a(this.c, (float) (this.l.getMeasuredHeight() + this.u.P())) <= 0) {
                String str2 = a;
                g.b(str2, "privacy_bottom=" + i);
                layoutParams3.addRule(12, -1);
                layoutParams3.setMargins(x.a(this.c, (float) i), 0, x.a(this.c, (float) O), 0);
            } else {
                String str3 = a;
                g.b(str3, "privacy_top = " + this.l.getMeasuredHeight());
                layoutParams3.addRule(10, -1);
                layoutParams3.setMargins(x.a(this.c, (float) i), x.a(this.c, (float) this.u.P()), x.a(this.c, (float) O), 0);
            }
        } else if (this.u.Q() <= 0 || this.v - x.a(this.c, (float) (this.l.getMeasuredHeight() + this.u.Q())) <= 0) {
            layoutParams3.addRule(10, -1);
            layoutParams3.setMargins(x.a(this.c, (float) i), 0, x.a(this.c, (float) O), 0);
            g.b(a, "privacy_top");
        } else {
            String str4 = a;
            g.b(str4, "privacy_bottom=" + this.l.getMeasuredHeight());
            layoutParams3.addRule(12, -1);
            layoutParams3.setMargins(x.a(this.c, (float) i), 0, x.a(this.c, (float) O), x.a(this.c, (float) this.u.Q()));
        }
        this.l.setLayoutParams(layoutParams3);
    }

    private void f() {
        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().addFlags(67108864);
            getWindow().addFlags(134217728);
            if (this.u.a() != 0) {
                getWindow().addFlags(Integer.MIN_VALUE);
                getWindow().clearFlags(67108864);
                getWindow().setStatusBarColor(this.u.a());
                getWindow().setNavigationBarColor(this.u.a());
            }
        }
        if (Build.VERSION.SDK_INT >= 23) {
            if (this.u.b()) {
                getWindow().getDecorView().setSystemUiVisibility(8192);
            } else {
                getWindow().getDecorView().setSystemUiVisibility(0);
            }
        }
        RelativeLayout relativeLayout = new RelativeLayout(this);
        relativeLayout.setLayoutParams(new RelativeLayout.LayoutParams(-1, -1));
        View c2 = this.u.c();
        if (c2 != null) {
            ViewParent parent = c2.getParent();
            if (parent != null) {
                ((ViewGroup) parent).removeView(c2);
            }
            relativeLayout.addView(c2);
        } else if (this.u.d() != -1) {
            getLayoutInflater().inflate(this.u.d(), relativeLayout);
        }
        setContentView(relativeLayout);
        int requestedOrientation = getRequestedOrientation();
        this.v = x.b(this.c);
        this.w = x.a(this.c);
        if ((requestedOrientation == 1 && this.w > this.v) || (requestedOrientation == 0 && this.w < this.v)) {
            int i = this.w;
            this.w = this.v;
            this.v = i;
        }
        String str = a;
        g.d(str, "orientation = " + requestedOrientation + "--screenWidth = " + this.w + "--screenHeight = " + this.v);
        WindowManager.LayoutParams attributes = getWindow().getAttributes();
        if (this.u.V() != 0) {
            getWindow().getWindowManager().getDefaultDisplay().getMetrics(new DisplayMetrics());
            getWindowManager().getDefaultDisplay().getSize(new Point());
            attributes.width = x.a(this.c, (float) this.u.V());
            attributes.height = x.a(this.c, (float) this.u.W());
            this.w = attributes.width;
            this.v = attributes.height;
            attributes.x = this.u.X();
            if (this.u.Z() == 1) {
                getWindow().setGravity(80);
            } else {
                attributes.y = this.u.Y();
            }
            getWindow().setAttributes(attributes);
        }
        relativeLayout.setFitsSystemWindows(true);
        relativeLayout.setClipToPadding(true);
        try {
            g();
            relativeLayout.addView(this.m);
            relativeLayout.addView(h());
            relativeLayout.addView(i());
            e();
            this.d.setOnClickListener(this);
            this.s.setOnClickListener(this);
            this.k.setOnCheckedChangeListener(new AnonymousClass5(this));
            k();
            try {
                if (this.u.D()) {
                    this.k.setChecked(true);
                    this.k.setBackgroundResource(o.a(this, this.u.z()));
                    this.d.setEnabled(true);
                    return;
                }
                this.k.setChecked(false);
                this.d.setEnabled(false);
                this.k.setBackgroundResource(o.a(this, this.u.A()));
            } catch (Exception unused) {
                this.k.setChecked(false);
            }
        } catch (Exception e) {
            com.cmic.sso.wy.d.a.a.add(e);
            e.printStackTrace();
            g.a(a, e.toString());
            a("200040", "UI\u8d44\u6e90\u52a0\u8f7d\u5f02\u5e38", this.h, null);
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: com.cmic.sso.wy.activity.LoginAuthActivity$5  reason: invalid class name */
    public class AnonymousClass5 implements CompoundButton.OnCheckedChangeListener {
        final /* synthetic */ LoginAuthActivity a;

        AnonymousClass5(LoginAuthActivity loginAuthActivity) {
            JniLib.cV(this, loginAuthActivity, 958);
        }

        @Override // android.widget.CompoundButton.OnCheckedChangeListener
        public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
            if (z) {
                this.a.d.setEnabled(true);
                try {
                    this.a.k.setBackgroundResource(o.a(this.a, this.a.u.z()));
                } catch (Exception unused) {
                    this.a.k.setBackgroundResource(o.a(this.a, "umcsdk_check_image"));
                }
            } else {
                this.a.d.setEnabled(false);
                try {
                    this.a.k.setBackgroundResource(o.a(this.a, this.a.u.A()));
                } catch (Exception unused2) {
                    this.a.k.setBackgroundResource(o.a(this.a, "umcsdk_uncheck_image"));
                }
            }
        }
    }

    private void g() {
        this.m = new RelativeLayout(this);
        this.m.setId(13107);
        this.m.setLayoutParams(new RelativeLayout.LayoutParams(-1, -2));
        TextView textView = new TextView(this);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -2);
        textView.setGravity(15);
        int l = this.u.l();
        if (l == 0) {
            layoutParams.addRule(13);
        } else if (l > 0) {
            float f = (float) l;
            if ((this.w - textView.getWidth()) - x.a(this.c, f) > 0) {
                layoutParams.setMargins(x.a(this.c, f), 0, 0, 0);
            } else {
                g.b(a, "RelativeLayout.ALIGN_PARENT_RIGHT");
                layoutParams.addRule(11);
            }
        }
        try {
            textView.setTextSize(2, (float) this.u.j());
        } catch (Exception unused) {
            textView.setTextSize(2, 18.0f);
        }
        textView.setText(this.j);
        textView.setId(30583);
        this.m.addView(textView, layoutParams);
        try {
            textView.setTextColor(this.u.k());
        } catch (Exception unused2) {
            textView.setTextColor(-13421773);
        }
        int makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, 0);
        this.m.measure(makeMeasureSpec, makeMeasureSpec);
        String str = a;
        g.b(str, "mPhoneLayout.getMeasuredHeight()=" + this.m.getMeasuredHeight());
    }

    private RelativeLayout h() {
        this.d = new RelativeLayout(this);
        this.d.setId(17476);
        this.d.setLayoutParams(new RelativeLayout.LayoutParams(x.a(this.c, (float) this.u.s()), x.a(this.c, (float) this.u.t())));
        TextView textView = new TextView(this);
        textView.setTextSize(2, (float) this.u.p());
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams.addRule(13);
        textView.setLayoutParams(layoutParams);
        this.d.addView(textView);
        textView.setText(this.u.o());
        try {
            textView.setTextColor(this.u.q());
        } catch (Exception unused) {
            textView.setTextColor(-1);
        }
        try {
            this.d.setBackgroundResource(o.a(this.c, this.u.r()));
        } catch (Exception e) {
            e.printStackTrace();
            this.d.setBackgroundResource(o.a(this.c, "umcsdk_login_btn_bg"));
        }
        return this.d;
    }

    private LinearLayout i() {
        this.l = new LinearLayout(this);
        this.l.setOrientation(0);
        this.l.setHorizontalGravity(1);
        this.l.setLayoutParams(new RelativeLayout.LayoutParams(-1, -2));
        int B = this.u.B();
        int C = this.u.C();
        float f = 30.0f;
        int a2 = x.a(this.c, B > 30 ? (float) B : 30.0f);
        Context context = this.c;
        if (C > 30) {
            f = (float) C;
        }
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(a2, x.a(context, f));
        this.s = new LinearLayout(this);
        this.s.setOrientation(0);
        this.s.setId(34952);
        this.s.setLayoutParams(layoutParams);
        this.k = new CheckBox(this);
        this.k.setChecked(false);
        LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(x.a(this.c, (float) this.u.B()), x.a(this.c, (float) this.u.C()));
        layoutParams2.setMargins(x.a(this.c, B > 30 ? 0.0f : (float) (30 - B)), 0, 0, 0);
        this.k.setLayoutParams(layoutParams2);
        this.s.addView(this.k);
        this.l.addView(this.s);
        TextView textView = new TextView(this);
        textView.setTextSize(2, (float) this.u.J());
        LinearLayout.LayoutParams layoutParams3 = new LinearLayout.LayoutParams(-2, -2);
        layoutParams3.setMargins(x.a(this.c, 5.0f), 0, 0, x.a(this.c, 5.0f));
        textView.setLayoutParams(layoutParams3);
        this.l.addView(textView);
        textView.setTextColor(this.u.K());
        textView.setText(x.a(this, j(), this.t, this.e, this.f, this.g));
        textView.setLineSpacing(8.0f, 1.0f);
        textView.setIncludeFontPadding(false);
        if (this.u.M()) {
            textView.setGravity(17);
        }
        textView.setHighlightColor(getResources().getColor(17170445));
        textView.setMovementMethod(LinkMovementMethod.getInstance());
        this.k.setButtonDrawable(new ColorDrawable());
        try {
            this.k.setBackgroundResource(o.a(this, this.u.A()));
        } catch (Exception unused) {
            this.k.setBackgroundResource(o.a(this, "umcsdk_uncheck_image"));
        }
        return this.l;
    }

    private String j() {
        if (!this.u.E().contains("$$\u300a\u8fd0\u8425\u5546\u6761\u6b3e\u300b$$")) {
            return this.u.E().replace("$$\u8fd0\u8425\u5546\u6761\u6b3e$$", this.t);
        }
        this.t = "\u300a" + this.t + "\u300b";
        return this.u.E().replace("$$\u300a\u8fd0\u8425\u5546\u6761\u6b3e\u300b$$", this.t);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void k() {
        this.d.setClickable(true);
        this.k.setClickable(true);
    }

    private void l() {
        this.d.setClickable(false);
        this.k.setClickable(false);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void a(boolean z) {
        try {
            com.cmic.sso.wy.utils.c.a("authPageOut");
            a("200020", "\u767b\u5f55\u9875\u9762\u5173\u95ed", this.h, null);
        } catch (Exception e) {
            com.cmic.sso.wy.d.a.a.add(e);
            e.printStackTrace();
        }
    }

    @Override // android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyUp(int i, KeyEvent keyEvent) {
        if (i != 4 || keyEvent.getRepeatCount() != 0) {
            return true;
        }
        a(false);
        return true;
    }

    /* access modifiers changed from: protected */
    @Override // android.app.Activity
    public void onDestroy() {
        try {
            this.b.removeCallbacksAndMessages(null);
            com.cmic.sso.wy.utils.c.a("timeOnAuthPage", (System.currentTimeMillis() - this.n) + "");
            if (this.k.isChecked()) {
                com.cmic.sso.wy.utils.c.a("authPrivacyState", "1");
            } else {
                com.cmic.sso.wy.utils.c.a("authPrivacyState", "0");
            }
            if (!this.h.getBoolean("isLoginSwitch", false)) {
                com.cmic.sso.wy.utils.c.a("timeOnAuthPage", (System.currentTimeMillis() - this.n) + "");
                com.cmic.sso.wy.utils.c.a(this.c, this.h);
                com.cmic.sso.wy.utils.c.a();
            }
            this.y = null;
            h.a().c();
            this.p.removeCallbacksAndMessages(null);
        } catch (Exception e) {
            g.a(a, "LoginAuthActivity clear failed");
            com.cmic.sso.wy.d.a.a.add(e);
            e.printStackTrace();
        }
        super.onDestroy();
    }

    /* access modifiers changed from: private */
    public class c implements Runnable {
        final /* synthetic */ LoginAuthActivity a;
        private Bundle b;
        private boolean c;

        c(LoginAuthActivity loginAuthActivity, Bundle bundle) {
            JniLib.cV(this, loginAuthActivity, bundle, 963);
        }

        @Override // java.lang.Runnable
        public void run() {
            if (a(true)) {
                JSONObject jSONObject = new JSONObject();
                try {
                    jSONObject.put("resultCode", "102507");
                    jSONObject.put("resultString", "\u8bf7\u6c42\u8d85\u65f6");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                this.a.r = false;
                com.cmic.sso.wy.utils.c.a("authClickFailed");
                this.a.p.sendEmptyMessage(13);
                this.a.a("102507", "\u8bf7\u6c42\u8d85\u65f6", this.b, jSONObject);
            }
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private synchronized boolean a(boolean z) {
            boolean z2;
            z2 = this.c;
            this.c = z;
            return !z2;
        }
    }

    private void m() {
        try {
            if (this.o >= 5) {
                Toast.makeText(this.c, "\u7f51\u7edc\u4e0d\u7a33\u5b9a,\u8bf7\u8fd4\u56de\u91cd\u8bd5\u5176\u4ed6\u767b\u5f55\u65b9\u5f0f", 1).show();
                this.d.setClickable(true);
                return;
            }
            StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
            StringBuilder sb = new StringBuilder();
            for (StackTraceElement stackTraceElement : stackTrace) {
                g.a("stack", stackTraceElement.getClassName());
                String className = stackTraceElement.getClassName();
                if (!TextUtils.isEmpty(className) && className.contains("com.cmic.sso.wy.activity") && !sb.toString().contains(className)) {
                    sb.append(className);
                    sb.append(";");
                }
            }
            this.h.putString("caller", sb.toString());
            this.h.putLong("loginTime", System.currentTimeMillis());
            String string = this.h.getString("traceId", "");
            if (!TextUtils.isEmpty(string) && j.a(string)) {
                String b2 = aa.b();
                this.h.putString("traceId", b2);
                j.a(b2, this.q);
            }
            b();
            l();
            c cVar = new c(this, this.h);
            this.b.postDelayed(cVar, com.cmic.sso.wy.b.a.a(this).b());
            v.a(new b(this, cVar));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void a(String str, String str2, Bundle bundle, JSONObject jSONObject) {
        try {
            this.b.removeCallbacksAndMessages(null);
            if ("103000".equals(str)) {
                if (com.cmic.sso.wy.b.a.a(this) != null && j.c(bundle.getString("traceId")) != null) {
                    com.cmic.sso.wy.b.a.a(this).a(str, str2, bundle, jSONObject, (Throwable) null, true);
                }
            } else if (!"200020".equals(str)) {
                com.cmic.sso.wy.b.a.a(this).a(str, str2, bundle, jSONObject, (Throwable) null, true);
            } else if (com.cmic.sso.wy.b.a.a(this) == null) {
            } else {
                if (j.c(bundle.getString("traceId")) != null) {
                    com.cmic.sso.wy.b.a.a(this).a(str, str2, bundle, jSONObject, null);
                    a();
                    return;
                }
                a();
            }
        } catch (Exception e) {
            g.a(a, "CallbackResult:\u672a\u77e5\u9519\u8bef");
            e.printStackTrace();
        }
    }

    public void a() {
        this.b.removeCallbacksAndMessages(null);
        com.cmic.sso.wy.widget.a aVar = this.e;
        if (aVar != null && aVar.isShowing()) {
            this.e.dismiss();
        }
        com.cmic.sso.wy.widget.a aVar2 = this.f;
        if (aVar2 != null && aVar2.isShowing()) {
            this.f.dismiss();
        }
        c();
        this.y = null;
        finish();
        if (this.u.T() != null && this.u.U() != null) {
            overridePendingTransition(o.b(this, this.u.T()), o.b(this, this.u.U()));
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        try {
            int id = view.getId();
            if (id == 17476) {
                this.o++;
                m();
            } else if (id == 26214) {
                a(false);
            } else if (id == 34952) {
                if (this.k.isChecked()) {
                    this.k.setChecked(false);
                } else {
                    this.k.setChecked(true);
                }
            }
        } catch (Exception e) {
            com.cmic.sso.wy.d.a.a.add(e);
            e.printStackTrace();
        }
    }

    /* access modifiers changed from: private */
    public static class a extends Handler {
        WeakReference<LoginAuthActivity> a;

        a(LoginAuthActivity loginAuthActivity) {
            this.a = new WeakReference<>(loginAuthActivity);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            try {
                a(message);
            } catch (Exception e) {
                com.cmic.sso.wy.d.a.a.add(e);
                e.printStackTrace();
            }
        }

        private void a(Message message) {
            LoginAuthActivity loginAuthActivity = this.a.get();
            if (loginAuthActivity != null && message.what == 13) {
                loginAuthActivity.c();
                loginAuthActivity.k();
            }
        }
    }

    public void b() {
        g.a(a, "loginClickStart");
        try {
            this.x = true;
            if (this.u.y() != null) {
                this.u.y().a(this.c, null);
            } else if (this.y != null) {
                this.y.show();
                return;
            } else {
                this.y = new AlertDialog.Builder(this).create();
                this.y.setCancelable(false);
                this.y.setCanceledOnTouchOutside(false);
                this.y.setOnKeyListener(new AnonymousClass6(this));
                RelativeLayout relativeLayout = new RelativeLayout(this.y.getContext());
                relativeLayout.setLayoutParams(new RelativeLayout.LayoutParams(-1, -2));
                ImageView imageView = new ImageView(this.y.getContext());
                imageView.setImageResource(o.a(this.c, "dialog_loading"));
                RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(80, 80);
                layoutParams.addRule(13, -1);
                relativeLayout.addView(imageView, layoutParams);
                if (this.y.getWindow() != null) {
                    this.y.getWindow().setDimAmount(0.0f);
                }
                this.y.show();
                this.y.setContentView(relativeLayout);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        g.a(a, "loginClickStart");
    }

    public void c() {
        try {
            g.a(a, "loginClickComplete");
            if (this.u.y() != null && this.x) {
                this.x = false;
                this.u.y().b(this.c, null);
            } else if (this.y != null && this.y.isShowing()) {
                this.y.dismiss();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* access modifiers changed from: private */
    public static class b extends v.a {
        WeakReference<LoginAuthActivity> a;
        WeakReference<c> b;

        protected b(LoginAuthActivity loginAuthActivity, c cVar) {
            this.a = new WeakReference<>(loginAuthActivity);
            this.b = new WeakReference<>(cVar);
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private boolean b() {
            c cVar = this.b.get();
            if (this.a.get() == null || cVar == null) {
                return false;
            }
            return cVar.a(false);
        }

        /* access modifiers changed from: protected */
        @Override // com.cmic.sso.wy.utils.v.a
        public void a() {
            LoginAuthActivity loginAuthActivity = this.a.get();
            if (!loginAuthActivity.r) {
                loginAuthActivity.i.a(loginAuthActivity.h, String.valueOf(3), new AnonymousClass1(this, loginAuthActivity));
            } else {
                loginAuthActivity.i.a(loginAuthActivity.h, new AnonymousClass2(this, loginAuthActivity));
            }
        }

        /* renamed from: com.cmic.sso.wy.activity.LoginAuthActivity$b$1  reason: invalid class name */
        class AnonymousClass1 implements f {
            final /* synthetic */ LoginAuthActivity a;
            final /* synthetic */ b b;

            AnonymousClass1(b bVar, LoginAuthActivity loginAuthActivity) {
                JniLib.cV(this, bVar, loginAuthActivity, 961);
            }

            @Override // com.cmic.sso.wy.b.f
            public void a(String str, String str2, Bundle bundle, JSONObject jSONObject) {
                if (this.b.b()) {
                    if ("103000".equals(str)) {
                        this.a.i.a(this.a.h, new AnonymousClass1(this));
                        return;
                    }
                    this.a.r = false;
                    this.a.a(str, str2, bundle, jSONObject);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    this.a.p.sendEmptyMessage(13);
                }
            }

            /* renamed from: com.cmic.sso.wy.activity.LoginAuthActivity$b$1$1  reason: invalid class name */
            class AnonymousClass1 implements f {
                final /* synthetic */ AnonymousClass1 a;

                AnonymousClass1(AnonymousClass1 r5) {
                    JniLib.cV(this, r5, 960);
                }

                @Override // com.cmic.sso.wy.b.f
                public void a(String str, String str2, Bundle bundle, JSONObject jSONObject) {
                    if (this.a.b.b()) {
                        long j = bundle.getLong("loginTime");
                        if (j != 0) {
                            bundle.putLong("loginTime", System.currentTimeMillis() - j);
                        }
                        if ("103000".equals(str)) {
                            com.cmic.sso.wy.utils.c.a("authClickSuccess");
                            this.a.a.r = true;
                        } else {
                            this.a.a.r = false;
                            com.cmic.sso.wy.utils.c.a("authClickFailed");
                        }
                        this.a.a.a(str, str2, bundle, jSONObject);
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        this.a.a.p.sendEmptyMessage(13);
                    }
                }
            }
        }

        /* renamed from: com.cmic.sso.wy.activity.LoginAuthActivity$b$2  reason: invalid class name */
        class AnonymousClass2 implements f {
            final /* synthetic */ LoginAuthActivity a;
            final /* synthetic */ b b;

            AnonymousClass2(b bVar, LoginAuthActivity loginAuthActivity) {
                JniLib.cV(this, bVar, loginAuthActivity, 962);
            }

            @Override // com.cmic.sso.wy.b.f
            public void a(String str, String str2, Bundle bundle, JSONObject jSONObject) {
                if (this.b.b()) {
                    long j = bundle.getLong("loginTime");
                    if (j != 0) {
                        bundle.putLong("loginTime", System.currentTimeMillis() - j);
                    }
                    if ("103000".equals(str)) {
                        com.cmic.sso.wy.utils.c.a("authClickSuccess");
                        this.a.r = true;
                    } else {
                        this.a.r = false;
                        com.cmic.sso.wy.utils.c.a("authClickFailed");
                    }
                    this.a.a(str, str2, bundle, jSONObject);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    this.a.p.sendEmptyMessage(13);
                }
            }
        }
    }
}
