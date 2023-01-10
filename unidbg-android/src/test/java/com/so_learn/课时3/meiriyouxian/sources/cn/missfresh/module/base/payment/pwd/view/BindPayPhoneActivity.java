package cn.missfresh.module.base.payment.pwd.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import cn.missfresh.module.base.R;
import cn.missfresh.module.base.base.BaseFragmentActivity;
import cn.missfresh.module.base.payment.pwd.b.a;
import cn.missfresh.module.base.support.dialog.f;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import cn.missfresh.utils.b;
import com.bangcle.andjni.JniLib;

public class BindPayPhoneActivity extends BaseFragmentActivity implements TextWatcher, a {
    public a a;
    private EditText j;
    private TextView k;
    private EditText l;
    private Button m;
    private View n;
    private TextView o;
    private String p;
    private String v = "";

    private void D() {
        JniLib.cV(this, 278);
    }

    public static void a(Activity activity, String str) {
        JniLib.cV(activity, str, 279);
    }

    @Override // cn.missfresh.module.base.payment.pwd.view.a
    public void a() {
        JniLib.cV(this, 260);
    }

    @Override // cn.missfresh.module.base.payment.pwd.view.a
    public void a(long j) {
        JniLib.cV(this, Long.valueOf(j), 261);
    }

    @Override // cn.missfresh.module.base.payment.pwd.view.a
    public void a(String str, String str2) {
        JniLib.cV(this, str, str2, 262);
    }

    public void a(boolean z, String str) {
        JniLib.cV(this, Boolean.valueOf(z), str, 263);
    }

    @Override // android.text.TextWatcher
    public void afterTextChanged(Editable editable) {
    }

    public void b(String str, String str2) {
        JniLib.cV(this, str, str2, 264);
    }

    @Override // android.text.TextWatcher
    public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        JniLib.cV(this, charSequence, Integer.valueOf(i), Integer.valueOf(i2), Integer.valueOf(i3), 265);
    }

    @Override // cn.missfresh.module.base.payment.pwd.view.a
    public void c(String str) {
        JniLib.cV(this, str, 266);
    }

    @Override // cn.missfresh.module.base.payment.pwd.view.a
    public void d(String str) {
        JniLib.cV(this, str, 267);
    }

    @Override // cn.missfresh.module.base.payment.pwd.view.a
    public void e(String str) {
        JniLib.cV(this, str, 268);
    }

    @Override // cn.missfresh.module.base.payment.pwd.view.a
    public void f(String str) {
        JniLib.cV(this, str, 269);
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int i, int i2, Intent intent) {
        JniLib.cV(this, Integer.valueOf(i), Integer.valueOf(i2), intent, 270);
    }

    /* access modifiers changed from: protected */
    @Override // cn.missfresh.module.base.base.BaseFragmentActivity
    public void onDestroy() {
        JniLib.cV(this, 271);
    }

    @Override // android.text.TextWatcher
    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        JniLib.cV(this, charSequence, Integer.valueOf(i), Integer.valueOf(i2), Integer.valueOf(i3), 272);
    }

    @Override // cn.missfresh.module.base.base.BaseFragmentActivity
    public void onWindowFocusChanged(boolean z) {
        JniLib.cV(this, Boolean.valueOf(z), 273);
    }

    @Override // cn.missfresh.module.base.payment.pwd.view.a
    public void r() {
        JniLib.cV(this, 274);
    }

    public void t() {
        JniLib.cV(this, 275);
    }

    public void u() {
        JniLib.cV(this, 276);
    }

    public String x() {
        return (String) JniLib.cL(this, 277);
    }

    /* access modifiers changed from: protected */
    @Override // cn.missfresh.module.base.base.BaseFragmentActivity
    public void onCreate(Bundle bundle) {
        AppMethodBeat.i(16461, false);
        super.onCreate(bundle);
        setContentView(R.layout.activity_bind_pay_phone);
        this.e.getBottomDivider().setVisibility(8);
        E();
        D();
        AppMethodBeat.o(16461);
    }

    private void E() {
        AppMethodBeat.i(16463, false);
        this.e.setLeftButtonVisibility(0);
        this.e.setCenterVisibility(0);
        this.e.setCenterTxt("\u9a8c\u8bc1");
        this.j = (EditText) findViewById(R.id.tv_password_phone);
        this.k = (TextView) findViewById(R.id.btn_password_check_code);
        this.l = (EditText) findViewById(R.id.et_password_check_code);
        this.m = (Button) findViewById(R.id.btn_password_bind_phone);
        this.n = findViewById(R.id.ll_voice_verification_container);
        this.o = (TextView) findViewById(R.id.btn_voice_verification_code);
        this.o.getPaint().setFlags(8);
        this.o.setOnClickListener(this);
        this.k.setOnClickListener(this);
        this.m.setOnClickListener(this);
        this.l.addTextChangedListener(new AnonymousClass1(this));
        AppMethodBeat.o(16463);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: cn.missfresh.module.base.payment.pwd.view.BindPayPhoneActivity$1  reason: invalid class name */
    public class AnonymousClass1 implements TextWatcher {
        final /* synthetic */ BindPayPhoneActivity a;

        AnonymousClass1(BindPayPhoneActivity bindPayPhoneActivity) {
            JniLib.cV(this, bindPayPhoneActivity, 259);
        }

        @Override // android.text.TextWatcher
        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            JniLib.cV(this, charSequence, Integer.valueOf(i), Integer.valueOf(i2), Integer.valueOf(i3), 257);
        }

        @Override // android.text.TextWatcher
        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            JniLib.cV(this, charSequence, Integer.valueOf(i), Integer.valueOf(i2), Integer.valueOf(i3), 258);
        }

        @Override // android.text.TextWatcher
        public void afterTextChanged(Editable editable) {
            boolean z = false;
            AppMethodBeat.i(16449, false);
            if (this.a.l.getText().toString().length() >= 4) {
                z = true;
            }
            this.a.m.setBackgroundResource(!z ? R.drawable.shape_corners_4_red_grey_selector : R.drawable.shape_corners_4_red_ff);
            this.a.m.setEnabled(z);
            AppMethodBeat.o(16449);
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for r10v0, resolved type: cn.missfresh.module.base.payment.pwd.view.BindPayPhoneActivity */
    /* JADX WARN: Multi-variable type inference failed */
    private void F() {
        AppMethodBeat.i(16476, false);
        new f(this, getString(R.string.get_verification_code), getString(R.string.voice_verification_code_hint3), getString(R.string.pop_i_know), "", null, null).show();
        AppMethodBeat.o(16476);
    }

    @Override // cn.missfresh.module.base.payment.pwd.view.a
    public void s() {
        AppMethodBeat.i(16487, false);
        this.k.setText(getString(R.string.login_get_check_code_re));
        v();
        AppMethodBeat.o(16487);
    }

    public void v() {
        AppMethodBeat.i(16495, false);
        this.k.setEnabled(true);
        this.k.setTextColor(getResources().getColor(R.color.red_ff));
        this.k.setAlpha(1.0f);
        this.k.setBackgroundResource(R.drawable.shape_corners_4_red_solid_white);
        AppMethodBeat.o(16495);
    }

    public void w() {
        AppMethodBeat.i(16497, false);
        this.k.setEnabled(false);
        this.k.setTextColor(getResources().getColor(R.color.gray_b8));
        this.k.setAlpha(0.65f);
        this.k.setBackgroundResource(R.drawable.shape_corners_5_4b_no_solid);
        AppMethodBeat.o(16497);
    }

    @Override // cn.missfresh.module.base.base.BaseFragmentActivity, android.view.View.OnClickListener
    public void onClick(View view) {
        AppMethodBeat.i(16500, true);
        super.onClick(view);
        int id = view.getId();
        if (id == R.id.btn_password_check_code) {
            if (b.a(x())) {
                cn.missfresh.ui.a.a.a("\u624b\u673a\u53f7\u4e0d\u80fd\u4e3a\u7a7a");
                AppMethodBeat.o(16500);
                return;
            }
            if (b.a(this.p)) {
                this.p = getString(R.string.count_down_format);
            }
            if (b.a(this.a.a())) {
                this.a.a("SMS");
            } else {
                c("SMS");
            }
            a(false, "SMS");
        } else if (id == R.id.btn_voice_verification_code) {
            if (b.a(x())) {
                cn.missfresh.ui.a.a.a("\u624b\u673a\u53f7\u4e0d\u80fd\u4e3a\u7a7a");
                AppMethodBeat.o(16500);
                return;
            }
            if (b.a(this.a.a())) {
                this.a.a("VOICE");
            } else {
                c("VOICE");
            }
            a(false, "VOICE");
        } else if (id == R.id.btn_password_bind_phone) {
            b(x(), this.l.getText().toString().trim());
        }
        AppMethodBeat.o(16500);
    }
}
