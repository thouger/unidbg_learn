package com.sina.weibo.sdk.register.mobile;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.SpannableStringBuilder;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.format.DateUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.util.DisplayMetrics;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import com.sina.weibo.sdk.a.f;
import com.sina.weibo.sdk.a.g;
import com.sina.weibo.sdk.a.j;
import com.sina.weibo.sdk.component.WeiboSdkBrowser;
import com.sina.weibo.sdk.component.view.ResizeableLayout;
import com.sina.weibo.sdk.component.view.TitleBar;
import com.sina.weibo.sdk.exception.WeiboException;
import com.sina.weibo.sdk.net.e;
import com.taobao.accs.common.Constants;
import com.umeng.message.proguard.l;
import java.util.Locale;
import org.json.JSONObject;

public class MobileRegisterActivity extends Activity implements View.OnClickListener, View.OnFocusChangeListener, ResizeableLayout.a {
    private static final String a = MobileRegisterActivity.class.getName();
    private ProgressDialog b;
    private TitleBar c;
    private ScrollView d;
    private LinearLayout e;
    private TextView f;
    private RelativeLayout g;
    private TextView h;
    private TextView i;
    private EditText j;
    private ImageView k;
    private EditText l;
    private Button m;
    private TextView n;
    private Button o;
    private String p;
    private String q;
    private String r;
    private String s;
    private String t;
    private String u;
    private String v;
    private b w = new b(this, null);
    private int x = 0;
    private CountDownTimer y;

    /* access modifiers changed from: protected */
    @Override // android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Bundle extras = getIntent().getExtras();
        if (extras == null) {
            j.a(getApplicationContext(), "Pass wrong params!!", 0);
            finish();
        }
        this.p = extras.getString(Constants.KEY_APP_KEY);
        this.q = extras.getString("packagename");
        this.r = extras.getString("key_hash");
        if (TextUtils.isEmpty(this.p)) {
            j.a(getApplicationContext(), g.a(this, "your appkey not set", "\u60a8\u7684app_key\u6ca1\u6709\u8bbe\u7f6e", "\u60a8\u7684app_key\u6c92\u6709\u8a2d\u7f6e"), 0);
            finish();
        }
        String string = extras.getString("register_title");
        if (TextUtils.isEmpty(string)) {
            string = g.a(this, "Login", "\u9a8c\u8bc1\u7801\u767b\u5f55", "\u9a57\u8b49\u78bc\u767b\u9304");
        }
        this.s = string;
        this.t = Country.CHINA_CODE;
        this.u = g.a(this, "China", "\u4e2d\u56fd", "\u4e2d\u570b");
        c();
        this.y = new AnonymousClass1(DateUtils.MINUTE_IN_MILLIS, 1000);
    }

    /* renamed from: com.sina.weibo.sdk.register.mobile.MobileRegisterActivity$1  reason: invalid class name */
    class AnonymousClass1 extends CountDownTimer {
        AnonymousClass1(long j, long j2) {
            super(j, j2);
        }

        @Override // android.os.CountDownTimer
        public void onTick(long j) {
            Button button = MobileRegisterActivity.this.m;
            button.setText(String.valueOf(g.a(MobileRegisterActivity.this.getApplicationContext(), "Get code", "\u83b7\u53d6\u9a8c\u8bc1\u7801", "\u7372\u53d6\u9a57\u8b49\u78bc")) + l.s + (j / 1000) + "s)");
        }

        @Override // android.os.CountDownTimer
        public void onFinish() {
            MobileRegisterActivity.this.m.setText(g.a(MobileRegisterActivity.this.getApplicationContext(), "Get code", "\u83b7\u53d6\u9a8c\u8bc1\u7801", "\u7372\u53d6\u9a57\u8b49\u78bc"));
            MobileRegisterActivity.this.h();
        }
    }

    private void c() {
        ResizeableLayout resizeableLayout = new ResizeableLayout(this);
        resizeableLayout.setLayoutParams(new ViewGroup.LayoutParams(-1, -1));
        resizeableLayout.setBackgroundColor(-855310);
        this.c = new TitleBar(this);
        this.c.setId(1);
        this.c.setLeftBtnText(g.a(this, "Cancel", "\u53d6\u6d88", "\u53d6\u6d88"));
        this.c.setTitleBarText(this.s);
        this.c.setTitleBarClickListener(new AnonymousClass2());
        resizeableLayout.addView(this.c);
        View view = new View(this);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, g.a(this, 2));
        view.setBackgroundDrawable(g.b(this, "weibosdk_common_shadow_top.9.png"));
        layoutParams.addRule(3, 1);
        view.setLayoutParams(layoutParams);
        resizeableLayout.addView(view);
        this.d = new ScrollView(this);
        RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(-1, -1);
        layoutParams2.topMargin = g.a(this, 47);
        this.d.setBackgroundColor(-855310);
        this.d.setLayoutParams(layoutParams2);
        this.e = new LinearLayout(this);
        LinearLayout.LayoutParams layoutParams3 = new LinearLayout.LayoutParams(-1, -2);
        this.e.setOrientation(1);
        this.e.setLayoutParams(layoutParams3);
        this.f = new TextView(this);
        this.f.setTextSize(2, 13.0f);
        this.f.setHeight(g.a(this, 44));
        this.f.setGravity(17);
        this.f.setTextColor(-8224126);
        this.f.setText(g.a(this, "Confirm your country/region and enter your mobile number", "\u8bf7\u786e\u8ba4\u56fd\u5bb6\u548c\u5730\u533a\u5e76\u586b\u5199\u624b\u673a\u53f7\u7801", "\u8acb\u78ba\u8a8d\u570b\u5bb6\u548c\u5730\u5340\u5e76\u586b\u5beb\u624b\u6a5f\u865f"));
        this.f.setFocusable(true);
        this.f.setFocusableInTouchMode(true);
        this.e.addView(this.f);
        this.g = new RelativeLayout(this);
        RelativeLayout.LayoutParams layoutParams4 = new RelativeLayout.LayoutParams(-1, g.a(this, 48));
        this.g.setBackgroundDrawable(g.a(this, "login_country_background.9.png", "login_country_background_highlighted.9.png"));
        this.g.setLayoutParams(layoutParams4);
        this.h = new TextView(this);
        this.h.setTextSize(2, 17.0f);
        this.h.setText(Country.CHINA_CODE);
        this.h.setTextColor(-11382190);
        this.h.setGravity(3);
        this.h.setGravity(16);
        RelativeLayout.LayoutParams layoutParams5 = new RelativeLayout.LayoutParams(-2, g.a(this, 48));
        layoutParams5.leftMargin = g.a(this, 15);
        layoutParams5.addRule(9);
        this.h.setLayoutParams(layoutParams5);
        ImageView imageView = new ImageView(this);
        imageView.setId(2);
        imageView.setImageDrawable(g.a(this, "triangle.png"));
        RelativeLayout.LayoutParams layoutParams6 = new RelativeLayout.LayoutParams(g.a(this, 13), g.a(this, 13));
        layoutParams6.rightMargin = g.a(this, 15);
        layoutParams6.addRule(11);
        layoutParams6.addRule(15);
        imageView.setLayoutParams(layoutParams6);
        this.i = new TextView(this);
        this.i.setTextSize(2, 17.0f);
        this.i.setTextColor(-11382190);
        this.i.setText(this.u);
        this.i.setGravity(16);
        RelativeLayout.LayoutParams layoutParams7 = new RelativeLayout.LayoutParams(-2, g.a(this, 48));
        layoutParams7.rightMargin = g.a(this, 118);
        layoutParams7.addRule(0, 2);
        layoutParams7.addRule(15);
        this.i.setLayoutParams(layoutParams7);
        this.g.addView(this.h);
        this.g.addView(this.i);
        this.g.addView(imageView);
        this.e.addView(this.g);
        LinearLayout linearLayout = new LinearLayout(this);
        LinearLayout.LayoutParams layoutParams8 = new LinearLayout.LayoutParams(-1, -2);
        layoutParams8.topMargin = g.a(this, 10);
        linearLayout.setLayoutParams(layoutParams8);
        linearLayout.setOrientation(1);
        RelativeLayout relativeLayout = new RelativeLayout(this);
        LinearLayout.LayoutParams layoutParams9 = new LinearLayout.LayoutParams(-1, g.a(this, 50));
        layoutParams9.gravity = 16;
        relativeLayout.setBackgroundDrawable(g.b(this, "login_top_background.9.png"));
        relativeLayout.setLayoutParams(layoutParams9);
        this.k = new ImageView(this);
        this.k.setId(4);
        this.k.setImageDrawable(g.a(this, "search_clear_btn_normal.png", "search_clear_btn_down.png"));
        RelativeLayout.LayoutParams layoutParams10 = new RelativeLayout.LayoutParams(g.a(this, 22), g.a(this, 22));
        layoutParams10.rightMargin = g.a(this, 15);
        layoutParams10.addRule(11);
        layoutParams10.addRule(15);
        this.k.setVisibility(4);
        this.k.setLayoutParams(layoutParams10);
        relativeLayout.addView(this.k);
        this.j = new EditText(this);
        this.j.setTextSize(2, 16.0f);
        this.j.setTextColor(-16777216);
        this.j.setHint(g.a(this, "Your mobile number", "\u8bf7\u8f93\u5165\u624b\u673a\u53f7\u7801", "\u8acb\u8f38\u5165\u624b\u6a5f\u865f"));
        this.j.setHintTextColor(-4342339);
        this.j.setBackgroundDrawable(null);
        this.j.setSelected(false);
        RelativeLayout.LayoutParams layoutParams11 = new RelativeLayout.LayoutParams(-1, g.a(this, 50));
        layoutParams11.topMargin = g.a(this, 0);
        layoutParams11.bottomMargin = g.a(this, 0);
        layoutParams11.leftMargin = g.a(this, 0);
        layoutParams11.rightMargin = g.a(this, 0);
        layoutParams11.addRule(0, 4);
        this.j.setLayoutParams(layoutParams11);
        relativeLayout.addView(this.j);
        RelativeLayout relativeLayout2 = new RelativeLayout(this);
        RelativeLayout.LayoutParams layoutParams12 = new RelativeLayout.LayoutParams(-1, g.a(this, 50));
        relativeLayout2.setBackgroundDrawable(g.b(this, "login_bottom_background.9.png"));
        relativeLayout2.setLayoutParams(layoutParams12);
        this.m = new Button(this);
        this.m.setId(3);
        this.m.setBackgroundDrawable(g.a(this, "get_code_button.9.png", "get_code_button_highlighted.9.png"));
        RelativeLayout.LayoutParams layoutParams13 = new RelativeLayout.LayoutParams(-2, g.a(this, 29));
        layoutParams13.rightMargin = g.a(this, 12);
        layoutParams13.addRule(11);
        layoutParams13.addRule(15);
        this.m.setPadding(18, 0, 18, 0);
        this.m.setLayoutParams(layoutParams13);
        this.m.setText(g.a(this, "Get code", "\u83b7\u53d6\u9a8c\u8bc1\u7801", "\u7372\u53d6\u9a57\u8b49\u78bc"));
        this.m.setTextSize(15.0f);
        h();
        relativeLayout2.addView(this.m);
        this.l = new EditText(this);
        this.l.setTextSize(2, 16.0f);
        this.l.setTextColor(-16777216);
        this.l.setHintTextColor(-4342339);
        this.l.setHint(g.a(this, "Verification code", "\u8bf7\u8f93\u5165\u9a8c\u8bc1\u7801", "\u8acb\u8f38\u5165\u9a57\u8b49\u78bc"));
        this.l.setBackgroundDrawable(null);
        RelativeLayout.LayoutParams layoutParams14 = new RelativeLayout.LayoutParams(-1, g.a(this, 48));
        layoutParams14.addRule(0, 3);
        this.l.setLayoutParams(layoutParams14);
        relativeLayout2.addView(this.l);
        linearLayout.addView(relativeLayout);
        linearLayout.addView(relativeLayout2);
        this.e.addView(linearLayout);
        this.m.setOnClickListener(this);
        this.n = new TextView(this);
        this.n.setTextSize(2, 13.0f);
        this.n.setTextColor(-2014941);
        this.n.setText("");
        this.n.setVisibility(4);
        LinearLayout.LayoutParams layoutParams15 = new LinearLayout.LayoutParams(-1, g.a(this, 36));
        layoutParams15.leftMargin = g.a(this, 12);
        this.n.setGravity(16);
        this.n.setLayoutParams(layoutParams15);
        this.e.addView(this.n);
        this.o = d();
        i();
        this.e.addView(this.o);
        TextView e = e();
        TextView f = f();
        this.e.addView(e);
        this.e.addView(f);
        this.d.addView(this.e);
        resizeableLayout.addView(this.d);
        l();
        this.j.setInputType(2);
        this.j.addTextChangedListener(new c(this, null));
        this.l.setInputType(2);
        this.l.addTextChangedListener(new a(this, null));
        this.k.setOnClickListener(this);
        this.j.setOnFocusChangeListener(this);
        this.o.setOnClickListener(this);
        this.g.setOnClickListener(this);
        resizeableLayout.setSizeChangeListener(this);
        setContentView(resizeableLayout);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: com.sina.weibo.sdk.register.mobile.MobileRegisterActivity$2  reason: invalid class name */
    public class AnonymousClass2 implements TitleBar.a {
        AnonymousClass2() {
        }

        @Override // com.sina.weibo.sdk.component.view.TitleBar.a
        public void a() {
            MobileRegisterActivity.this.setResult(0);
            MobileRegisterActivity.this.finish();
        }
    }

    private Button d() {
        Button button = new Button(this);
        button.setBackgroundDrawable(g.b(this, "common_button_big_blue.9.png", "common_button_big_blue_highlighted.9.png", "common_button_big_blue_disable.9.png"));
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, g.a(this, 46));
        int a2 = g.a(this, 12);
        layoutParams.rightMargin = a2;
        layoutParams.leftMargin = a2;
        button.setText(g.a(this, "OK", "\u786e\u5b9a", "\u78ba\u5b9a"));
        button.setTextSize(17.0f);
        button.setLayoutParams(layoutParams);
        return button;
    }

    private TextView e() {
        TextView textView = new TextView(this);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -2);
        layoutParams.topMargin = g.a(this, 12);
        layoutParams.leftMargin = g.a(this, 12);
        textView.setLayoutParams(layoutParams);
        textView.setTextSize(13.0f);
        textView.setGravity(3);
        textView.setTextColor(-8224126);
        textView.setText(g.a(this, "Service By Sina WeiBo", "\u6b64\u670d\u52a1\u7531\u5fae\u535a\u63d0\u4f9b", "\u6b64\u670d\u52d9\u7531\u5fae\u535a\u63d0\u4f9b"));
        return textView;
    }

    private TextView f() {
        String str;
        String str2;
        TextView textView = new TextView(this);
        textView.setTextSize(2, 13.0f);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -2);
        layoutParams.topMargin = g.a(this, 8);
        layoutParams.leftMargin = g.a(this, 12);
        layoutParams.rightMargin = g.a(this, 12);
        textView.setLayoutParams(layoutParams);
        textView.setTextSize(13.0f);
        textView.setGravity(3);
        textView.setTextColor(-8224126);
        Locale a2 = g.a();
        int i = 22;
        int i2 = 18;
        int i3 = 17;
        int i4 = 11;
        if (Locale.SIMPLIFIED_CHINESE.equals(a2)) {
            str2 = "zh_CN";
            str = "\u70b9\u51fb\u201c\u786e\u5b9a\u201d\u8868\u793a\u4f60\u540c\u610f\u670d\u52a1\u4f7f\u7528\u534f\u8bae\u548c\u9690\u79c1\u6761\u6b3e\u3002";
        } else if (Locale.TRADITIONAL_CHINESE.equals(a2)) {
            str = "\u9ede\u64ca\u201c\u78ba\u5b9a\u201d\u6a19\u793a\u4f60\u540c\u610f\u670d\u52d9\u4f7f\u7528\u5354\u8b70\u548c\u96b1\u79c1\u689d\u6b3e\u3002";
            str2 = "zh_HK";
        } else {
            i4 = 49;
            i3 = 66;
            i2 = 71;
            i = 85;
            str = "By clicking ok, you hereby agree to Weibo Online Service Agreement and Privacy Policy";
            str2 = "en_US";
        }
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(str);
        if (!(i4 == -1 || i3 == -1)) {
            spannableStringBuilder.setSpan(new d(this, "http://weibo.cn/dpool/ttt/h5/regagreement.php?from=h5&lang=" + str2), i4, i3, 33);
        }
        if (!(i2 == -1 || i == -1)) {
            spannableStringBuilder.setSpan(new d(this, "http://m.weibo.cn/reg/privacyagreement?from=h5&wm=3349&lang=" + str2), i2, i, 33);
        }
        textView.setText(spannableStringBuilder);
        textView.setMovementMethod(LinkMovementMethod.getInstance());
        textView.setFocusable(false);
        return textView;
    }

    /* access modifiers changed from: protected */
    @Override // android.app.Activity
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == 0 && intent != null) {
            this.t = intent.getStringExtra(Constants.KEY_HTTP_CODE);
            this.u = intent.getStringExtra("name");
            this.h.setText(this.t);
            this.i.setText(this.u);
        }
    }

    @Override // android.view.View.OnFocusChangeListener
    public void onFocusChange(View view, boolean z) {
        EditText editText = this.j;
        if (view == editText && !z) {
            if (b(editText.getText().toString())) {
                this.n.setVisibility(4);
                return;
            }
            this.n.setText(g.a(this, "Your phone number isn\u2019t 11-digit long", "\u60a8\u7684\u624b\u673a\u53f7\u4e0d\u662f11\u4f4d\u6570", "\u60a8\u7684\u624b\u6a5f\u865f\u4e0d\u662f11\u4f4d\u6578"));
            this.n.setVisibility(0);
        }
    }

    private boolean a(String str) {
        if (!f.b(this)) {
            k();
            return false;
        } else if (!b(str)) {
            this.n.setVisibility(0);
            this.n.setText(g.a(getApplicationContext(), "Your phone number isn\u2019t 11-digit long", "\u60a8\u7684\u624b\u673a\u53f7\u4e0d\u662f11\u4f4d\u6570", "\u60a8\u7684\u624b\u6a5f\u865f\u4e0d\u662f11\u4f4d\u6578"));
            return false;
        } else {
            this.n.setVisibility(4);
            return true;
        }
    }

    private boolean b(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        if (!Country.CHINA_CODE.equals(this.t) || str.trim().length() == 11) {
            return true;
        }
        return false;
    }

    private boolean c(String str) {
        if (!f.b(this)) {
            k();
            return false;
        } else if (d(str)) {
            this.n.setVisibility(4);
            return true;
        } else {
            this.n.setVisibility(0);
            this.n.setText(g.a(getApplicationContext(), "Your code isn\u2019t 6-digit long", "\u4f60\u7684\u9a8c\u8bc1\u7801\u4e0d\u662f6\u4f4d\u6570", "\u4f60\u7684\u9a57\u8b49\u78bc\u4e0d\u662f6\u4f4d\u6578"));
            j.a(getApplicationContext(), g.a(getApplicationContext(), "Your code isn\u2019t 6-digit long", "\u4f60\u7684\u9a8c\u8bc1\u7801\u4e0d\u662f6\u4f4d\u6570", "\u4f60\u7684\u9a57\u8b49\u78bc\u4e0d\u662f6\u4f4d\u6578"), 0);
            return false;
        }
    }

    private boolean d(String str) {
        return !TextUtils.isEmpty(str) && str.length() == 6;
    }

    private void g() {
        this.m.setEnabled(false);
        this.m.setTextColor(-4342339);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void h() {
        this.m.setEnabled(true);
        this.m.setTextColor(-11502161);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void i() {
        this.o.setTextColor(1308622847);
        this.o.setEnabled(false);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void j() {
        this.o.setEnabled(true);
        this.o.setTextColor(-1);
    }

    private void k() {
        j.a(getApplicationContext(), g.a(getApplicationContext(), "your network is  disabled  try again later", "\u60a8\u7684\u7f51\u7edc\u4e0d\u53ef\u7528\uff0c\u8bf7\u7a0d\u540e", "\u60a8\u7684\u7db2\u7d61\u4e0d\u53ef\u7528\uff0c\u8acb\u7a0d\u5f8c"), 0);
    }

    @Override // android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyUp(int i, KeyEvent keyEvent) {
        if (i != 4) {
            return super.onKeyUp(i, keyEvent);
        }
        setResult(0);
        finish();
        return true;
    }

    public void a() {
        ProgressDialog progressDialog = this.b;
        if (progressDialog != null && progressDialog.isShowing()) {
            this.b.dismiss();
        }
    }

    private void l() {
        this.b = new ProgressDialog(this);
        this.b.setCanceledOnTouchOutside(false);
        this.b.requestWindowFeature(1);
        this.b.setMessage(g.a(this, "please wait .... ", "\u6b63\u5728\u5904\u7406\u4e2d.....", "\u6b63\u5728\u8655\u7406\u4e2d....."));
    }

    /* access modifiers changed from: private */
    public class a implements TextWatcher {
        @Override // android.text.TextWatcher
        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        @Override // android.text.TextWatcher
        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        private a() {
        }

        /* synthetic */ a(MobileRegisterActivity mobileRegisterActivity, a aVar) {
            this();
        }

        @Override // android.text.TextWatcher
        public void afterTextChanged(Editable editable) {
            if (TextUtils.isEmpty(MobileRegisterActivity.this.j.getText().toString()) || TextUtils.isEmpty(MobileRegisterActivity.this.l.getText().toString())) {
                MobileRegisterActivity.this.i();
            } else {
                MobileRegisterActivity.this.j();
            }
        }
    }

    /* access modifiers changed from: private */
    public class c implements TextWatcher {
        @Override // android.text.TextWatcher
        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        private c() {
        }

        /* synthetic */ c(MobileRegisterActivity mobileRegisterActivity, c cVar) {
            this();
        }

        @Override // android.text.TextWatcher
        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            if (TextUtils.isEmpty(MobileRegisterActivity.this.j.getText().toString())) {
                MobileRegisterActivity.this.k.setVisibility(4);
            } else {
                MobileRegisterActivity.this.k.setVisibility(0);
            }
        }

        @Override // android.text.TextWatcher
        public void afterTextChanged(Editable editable) {
            if (TextUtils.isEmpty(MobileRegisterActivity.this.j.getText().toString()) || TextUtils.isEmpty(MobileRegisterActivity.this.l.getText().toString())) {
                MobileRegisterActivity.this.i();
            } else {
                MobileRegisterActivity.this.j();
            }
        }
    }

    /* access modifiers changed from: private */
    public class d extends ClickableSpan {
        private Context b;
        private String c;

        public d(Context context, String str) {
            this.b = context;
            this.c = str;
        }

        @Override // android.text.style.ClickableSpan
        public void onClick(View view) {
            Intent intent = new Intent(this.b, WeiboSdkBrowser.class);
            Bundle bundle = new Bundle();
            bundle.putString("key_url", this.c);
            intent.putExtras(bundle);
            MobileRegisterActivity.this.startActivity(intent);
        }

        @Override // android.text.style.ClickableSpan, android.text.style.CharacterStyle
        public void updateDrawState(TextPaint textPaint) {
            textPaint.setColor(-11502161);
            textPaint.setUnderlineText(false);
        }
    }

    public void a(String str, String str2) {
        e eVar = new e(this.p);
        eVar.a("appkey", this.p);
        eVar.a("packagename", this.q);
        eVar.a("key_hash", this.r);
        if (!Country.CHINA_CODE.equals(str2)) {
            str = String.valueOf(str2) + str;
        }
        eVar.a("phone", str);
        eVar.a("version", "0031405000");
        com.sina.weibo.sdk.net.b.a(this, "http://api.weibo.com/oauth2/sms_authorize/send", eVar, "GET", new AnonymousClass3());
    }

    /* access modifiers changed from: package-private */
    /* renamed from: com.sina.weibo.sdk.register.mobile.MobileRegisterActivity$3  reason: invalid class name */
    public class AnonymousClass3 implements com.sina.weibo.sdk.net.c {
        AnonymousClass3() {
        }

        @Override // com.sina.weibo.sdk.net.c
        public void a(WeiboException weiboException) {
            String str = MobileRegisterActivity.a;
            com.sina.weibo.sdk.a.d.a(str, "get onWeiboException " + weiboException.getMessage());
            String a = g.a(MobileRegisterActivity.this.getApplicationContext(), "the server is busy, please  wait", "\u670d\u52a1\u5668\u5fd9,\u8bf7\u7a0d\u540e\u518d\u8bd5", "\u670d\u52d9\u5668\u5fd9,\u8acb\u7a0d\u5f8c\u518d\u8a66");
            try {
                JSONObject jSONObject = new JSONObject(weiboException.getMessage());
                if (!TextUtils.isEmpty(jSONObject.optString("error_description"))) {
                    a = jSONObject.optString("error_description");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            j.a(MobileRegisterActivity.this.getApplicationContext(), a, 1);
        }

        @Override // com.sina.weibo.sdk.net.c
        public void a(String str) {
            String str2 = MobileRegisterActivity.a;
            com.sina.weibo.sdk.a.d.a(str2, "get onComplete : " + str);
            if (str != null) {
                try {
                    JSONObject jSONObject = new JSONObject(str);
                    MobileRegisterActivity.this.v = (String) jSONObject.get("cfrom");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void b(String str, String str2) {
        e eVar = new e(this.p);
        eVar.a("appkey", this.p);
        eVar.a("packagename", this.q);
        eVar.a("key_hash", this.r);
        eVar.a("phone", str);
        eVar.a("version", "0031405000");
        eVar.a(Constants.KEY_HTTP_CODE, str2);
        eVar.a("cfrom", this.v);
        this.b.show();
        com.sina.weibo.sdk.net.b.a(this, "http://api.weibo.com/oauth2/sms_authorize/submit", eVar, "GET", new AnonymousClass4(str));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: com.sina.weibo.sdk.register.mobile.MobileRegisterActivity$4  reason: invalid class name */
    public class AnonymousClass4 implements com.sina.weibo.sdk.net.c {
        private final /* synthetic */ String b;

        AnonymousClass4(String str) {
            this.b = str;
        }

        @Override // com.sina.weibo.sdk.net.c
        public void a(WeiboException weiboException) {
            String str = MobileRegisterActivity.a;
            com.sina.weibo.sdk.a.d.a(str, "get onWeiboException " + weiboException.getMessage());
            String a = g.a(MobileRegisterActivity.this.getApplicationContext(), "the server is busy, please  wait", "\u670d\u52a1\u5668\u5fd9,\u8bf7\u7a0d\u540e\u518d\u8bd5", "\u670d\u52d9\u5668\u5fd9,\u8acb\u7a0d\u5f8c\u518d\u8a66");
            try {
                JSONObject jSONObject = new JSONObject(weiboException.getMessage());
                if (!TextUtils.isEmpty(jSONObject.optString("error_description"))) {
                    a = jSONObject.optString("error_description");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            MobileRegisterActivity.this.n.setVisibility(0);
            MobileRegisterActivity.this.n.setText(a);
            MobileRegisterActivity.this.a();
        }

        @Override // com.sina.weibo.sdk.net.c
        public void a(String str) {
            MobileRegisterActivity.this.a();
            String str2 = MobileRegisterActivity.a;
            com.sina.weibo.sdk.a.d.a(str2, "get onComplete : " + str);
            if (str != null) {
                try {
                    JSONObject jSONObject = new JSONObject(str);
                    Intent intent = new Intent();
                    Bundle bundle = new Bundle();
                    bundle.putString("uid", jSONObject.optString("uid"));
                    bundle.putString("phone_num", this.b);
                    bundle.putString(com.tencent.connect.common.Constants.PARAM_ACCESS_TOKEN, jSONObject.optString("oauth_token"));
                    bundle.putString(com.tencent.connect.common.Constants.PARAM_EXPIRES_IN, jSONObject.optString("expires"));
                    intent.putExtras(bundle);
                    MobileRegisterActivity.this.setResult(-1, intent);
                    MobileRegisterActivity.this.finish();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private class b extends Handler {
        private b() {
        }

        /* synthetic */ b(MobileRegisterActivity mobileRegisterActivity, b bVar) {
            this();
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            int i = message.what;
            if (i == 0) {
                MobileRegisterActivity.this.f.setVisibility(0);
                MobileRegisterActivity.this.g.setVisibility(0);
            } else if (i == 1) {
                MobileRegisterActivity.this.f.setVisibility(8);
                MobileRegisterActivity.this.g.setVisibility(8);
            }
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view == this.m) {
            String editable = this.j.getText().toString();
            String charSequence = this.h.getText().toString();
            if (a(editable)) {
                this.y.start();
                g();
                a(editable, charSequence);
            }
        } else if (view == this.k) {
            this.j.setText("");
        } else if (view == this.o) {
            String editable2 = this.j.getText().toString();
            String editable3 = this.l.getText().toString();
            if (c(editable3)) {
                b(editable2, editable3);
            }
        } else if (view == this.g) {
            this.n.setVisibility(4);
            Intent intent = new Intent();
            intent.setClass(this, SelectCountryActivity.class);
            startActivityForResult(intent, 0);
        }
    }

    @Override // com.sina.weibo.sdk.component.view.ResizeableLayout.a
    public void a(int i, int i2, int i3, int i4) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        if (displayMetrics.widthPixels <= displayMetrics.heightPixels) {
            int i5 = this.x;
            if (i5 < i2) {
                i5 = i2;
            }
            this.x = i5;
            int i6 = 1;
            if (i2 >= i4 && ((i2 <= i4 || i2 >= this.x) && (i2 != i4 || i2 == this.x))) {
                i6 = 0;
            }
            this.w.sendEmptyMessage(i6);
        }
    }
}
