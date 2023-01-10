package com.sobot.chat.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.bangcle.andjni.JniLib;
import com.sobot.chat.activity.base.SobotBaseActivity;
import com.sobot.chat.api.model.BaseCode;
import com.sobot.chat.api.model.ZhiChiInitModeBase;
import com.sobot.chat.core.http.c.a;
import com.sobot.chat.utils.ae;
import com.sobot.chat.utils.c;
import com.sobot.chat.utils.e;
import com.sobot.chat.utils.q;
import com.sobot.chat.utils.s;
import com.sobot.chat.widget.dialog.f;

public class SobotPostLeaveMsgActivity extends SobotBaseActivity implements View.OnClickListener {
    private String a;
    private String d;
    private String e;
    private TextView f;
    private EditText g;
    private TextView h;
    private Button i;
    private f j;

    public static Intent a(Context context, String str, String str2, String str3) {
        Intent intent = new Intent(context, SobotPostLeaveMsgActivity.class);
        intent.putExtra("EXTRA_MSG_LEAVE_TXT", str);
        intent.putExtra("EXTRA_MSG_LEAVE_CONTENT_TXT", str2);
        intent.putExtra("EXTRA_MSG_UID", str3);
        return intent;
    }

    public static String a(Intent intent) {
        if (intent != null) {
            return intent.getStringExtra("EXTRA_MSG_LEAVE_CONTENT");
        }
        return null;
    }

    /* access modifiers changed from: protected */
    @Override // com.sobot.chat.activity.base.SobotBaseActivity
    public int a() {
        return c("sobot_activity_post_leave_msg");
    }

    /* access modifiers changed from: protected */
    @Override // com.sobot.chat.activity.base.SobotBaseActivity
    public void a(Bundle bundle) {
        if (getIntent() != null) {
            this.a = getIntent().getStringExtra("EXTRA_MSG_LEAVE_TXT");
            this.d = getIntent().getStringExtra("EXTRA_MSG_LEAVE_CONTENT_TXT");
            this.e = getIntent().getStringExtra("EXTRA_MSG_UID");
        }
    }

    /* access modifiers changed from: protected */
    @Override // com.sobot.chat.activity.base.SobotBaseActivity
    public void b() {
        b(b("sobot_btn_back_selector"), "", true);
        setTitle(e("sobot_leavemsg_title"));
        this.f = (TextView) findViewById(a("sobot_tv_post_msg"));
        this.g = (EditText) findViewById(a("sobot_post_et_content"));
        this.h = (TextView) findViewById(a("sobot_tv_problem_description"));
        this.h.setText(q.f(this, "sobot_problem_description"));
        this.i = (Button) findViewById(a("sobot_btn_submit"));
        this.i.setText(q.f(this, "sobot_btn_submit_text"));
        this.i.setOnClickListener(this);
    }

    /* access modifiers changed from: protected */
    @Override // com.sobot.chat.activity.base.SobotBaseActivity
    public void c() {
        this.f.setText(this.a);
        this.g.setHint(this.d);
        ZhiChiInitModeBase zhiChiInitModeBase = (ZhiChiInitModeBase) s.d(this, "sobot_last_current_initModel");
        if (zhiChiInitModeBase != null && c.a(zhiChiInitModeBase.getAccountStatus())) {
            this.j = new f(this, new AnonymousClass1(this));
            f fVar = this.j;
            if (fVar != null && !fVar.isShowing()) {
                this.j.show();
            }
        }
    }

    /* renamed from: com.sobot.chat.activity.SobotPostLeaveMsgActivity$1  reason: invalid class name */
    class AnonymousClass1 implements View.OnClickListener {
        final /* synthetic */ SobotPostLeaveMsgActivity a;

        AnonymousClass1(SobotPostLeaveMsgActivity sobotPostLeaveMsgActivity) {
            JniLib.cV(this, sobotPostLeaveMsgActivity, 1023);
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            this.a.j.dismiss();
            this.a.finish();
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view == this.i) {
            String obj = this.g.getText().toString();
            if (TextUtils.isEmpty(obj) || TextUtils.isEmpty(this.e)) {
                e.a(this, q.f(this, "sobot_problem_description") + q.f(this, "sobot__is_null"), 1000).show();
                return;
            }
            com.sobot.chat.widget.kpswitch.util.c.b(this.g);
            this.b.leaveMsg(SobotPostLeaveMsgActivity.class, this.e, obj, new AnonymousClass2(this, obj));
        }
    }

    /* renamed from: com.sobot.chat.activity.SobotPostLeaveMsgActivity$2  reason: invalid class name */
    class AnonymousClass2 implements a<BaseCode> {
        final /* synthetic */ String a;
        final /* synthetic */ SobotPostLeaveMsgActivity b;

        AnonymousClass2(SobotPostLeaveMsgActivity sobotPostLeaveMsgActivity, String str) {
            JniLib.cV(this, sobotPostLeaveMsgActivity, str, 1024);
        }

        public void a(BaseCode baseCode) {
            e.a(this.b.getBaseContext(), q.f(this.b.getBaseContext(), "sobot_leavemsg_success_tip"), 1000, q.e(this.b.getBaseContext(), "sobot_iv_login_right")).show();
            Intent intent = new Intent();
            intent.putExtra("EXTRA_MSG_LEAVE_CONTENT", this.a);
            this.b.setResult(109, intent);
            this.b.finish();
        }

        @Override // com.sobot.chat.core.http.c.a
        public void a(Exception exc, String str) {
            ae.a(this.b.getApplicationContext(), str);
        }
    }
}
