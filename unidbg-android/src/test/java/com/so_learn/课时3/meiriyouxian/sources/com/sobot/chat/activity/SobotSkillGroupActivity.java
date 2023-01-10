package com.sobot.chat.activity;

import android.content.Context;
import android.content.Intent;
import android.telecom.Logging.Session;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.TextView;
import com.bangcle.andjni.JniLib;
import com.sobot.chat.activity.base.SobotDialogBaseActivity;
import com.sobot.chat.adapter.l;
import com.sobot.chat.api.ZhiChiApi;
import com.sobot.chat.api.apiUtils.ZhiChiConstants;
import com.sobot.chat.api.model.ZhiChiGroup;
import com.sobot.chat.api.model.ZhiChiGroupBase;
import com.sobot.chat.application.MyApplication;
import com.sobot.chat.core.http.a;
import com.sobot.chat.presenter.b;
import com.sobot.chat.utils.d;
import com.sobot.chat.utils.q;
import com.sobot.chat.utils.s;
import com.umeng.message.common.inter.ITagManager;
import java.util.ArrayList;
import java.util.List;

public class SobotSkillGroupActivity extends SobotDialogBaseActivity {
    private LinearLayout a;
    private GridView d;
    private TextView e;
    private l f;
    private List<ZhiChiGroupBase> g = new ArrayList();
    private boolean h;
    private String i = null;
    private String j = null;
    private String k = null;
    private String l = null;
    private String m = null;
    private String n = null;
    private int o;
    private ZhiChiApi p;
    private int q = -1;
    private int r = 0;
    private b s;

    /* access modifiers changed from: protected */
    @Override // com.sobot.chat.activity.base.SobotBaseActivity
    public int a() {
        return q.a(this, "sobot_activity_skill_group");
    }

    /* access modifiers changed from: protected */
    @Override // com.sobot.chat.activity.base.SobotBaseActivity
    public void b() {
        this.e = (TextView) findViewById(q.a(this, "id", "sobot_tv_title"));
        this.e.setText(q.f(this, "sobot_switch_robot_title_2"));
        this.s = b.a(this, this);
        this.a = (LinearLayout) findViewById(q.a(this, "id", "sobot_btn_cancle"));
        this.d = (GridView) findViewById(q.a(this, "id", "sobot_gv_skill"));
        this.f = new l(this, this.g, this.r);
        this.d.setAdapter((ListAdapter) this.f);
        this.d.setOnItemClickListener(new AnonymousClass1(this));
        this.a.setOnClickListener(new AnonymousClass2(this));
        a(this, this.d);
    }

    /* renamed from: com.sobot.chat.activity.SobotSkillGroupActivity$1  reason: invalid class name */
    class AnonymousClass1 implements AdapterView.OnItemClickListener {
        final /* synthetic */ SobotSkillGroupActivity a;

        AnonymousClass1(SobotSkillGroupActivity sobotSkillGroupActivity) {
            JniLib.cV(this, sobotSkillGroupActivity, 1031);
        }

        @Override // android.widget.AdapterView.OnItemClickListener
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
            if (this.a.g != null && this.a.g.size() > 0) {
                if (ITagManager.STATUS_TRUE.equals(((ZhiChiGroupBase) this.a.g.get(i)).isOnline())) {
                    if (!TextUtils.isEmpty(((ZhiChiGroupBase) this.a.g.get(i)).getGroupName())) {
                        Intent intent = new Intent();
                        intent.putExtra("groupIndex", i);
                        intent.putExtra("transferType", this.a.o);
                        this.a.setResult(100, intent);
                        this.a.finish();
                    }
                } else if (this.a.r == 0) {
                    Intent intent2 = new Intent();
                    intent2.putExtra("toLeaveMsg", true);
                    this.a.setResult(100, intent2);
                    this.a.finish();
                }
            }
        }
    }

    /* renamed from: com.sobot.chat.activity.SobotSkillGroupActivity$2  reason: invalid class name */
    class AnonymousClass2 implements View.OnClickListener {
        final /* synthetic */ SobotSkillGroupActivity a;

        AnonymousClass2(SobotSkillGroupActivity sobotSkillGroupActivity) {
            JniLib.cV(this, sobotSkillGroupActivity, 1032);
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            this.a.d();
        }
    }

    @Override // com.sobot.chat.activity.base.SobotDialogBaseActivity, android.app.Activity
    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (motionEvent.getAction() != 0 || motionEvent.getY() > 0.0f) {
            return true;
        }
        d();
        return true;
    }

    /* access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        c();
    }

    /* access modifiers changed from: protected */
    @Override // com.sobot.chat.activity.base.SobotBaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        this.s.a();
        a.a().a(this);
        MyApplication.getInstance().deleteActivity(this);
        super.onDestroy();
    }

    /* access modifiers changed from: protected */
    @Override // com.sobot.chat.activity.base.SobotBaseActivity
    public void c() {
        if (getIntent() != null) {
            this.i = getIntent().getStringExtra("uid");
            this.j = getIntent().getStringExtra("companyId");
            this.k = getIntent().getStringExtra("customerId");
            this.l = getIntent().getStringExtra("appkey");
            this.h = getIntent().getBooleanExtra("FLAG_EXIT_SDK", false);
            this.q = getIntent().getIntExtra("type", -1);
            this.m = getIntent().getStringExtra("msgTmp");
            this.n = getIntent().getStringExtra("msgTxt");
            this.r = getIntent().getIntExtra("msgFlag", 0);
            this.o = getIntent().getIntExtra("transferType", 0);
        }
        this.p = com.sobot.chat.core.channel.a.a(getApplicationContext()).a();
        this.p.getGroupList(this, this.l, this.i, new AnonymousClass3(this));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: com.sobot.chat.activity.SobotSkillGroupActivity$3  reason: invalid class name */
    public class AnonymousClass3 implements com.sobot.chat.core.http.c.a<ZhiChiGroup> {
        final /* synthetic */ SobotSkillGroupActivity a;

        AnonymousClass3(SobotSkillGroupActivity sobotSkillGroupActivity) {
            JniLib.cV(this, sobotSkillGroupActivity, 1033);
        }

        @Override // com.sobot.chat.core.http.c.a
        public void a(Exception exc, String str) {
        }

        public void a(ZhiChiGroup zhiChiGroup) {
            this.a.g = zhiChiGroup.getData();
            if (this.a.g != null && this.a.g.size() > 0) {
                SobotSkillGroupActivity sobotSkillGroupActivity = this.a;
                sobotSkillGroupActivity.f = new l(sobotSkillGroupActivity.getApplicationContext(), this.a.g, this.a.r);
                this.a.d.setAdapter((ListAdapter) this.a.f);
            }
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void d() {
        Context applicationContext = getApplicationContext();
        if (s.b(applicationContext, this.l + Session.SESSION_SEPARATION_CHAR_CHILD + "initType", -1) == 2) {
            finish();
            a(1);
        } else if (!this.h) {
            finish();
            a(2);
        } else {
            MyApplication.getInstance().exit();
        }
    }

    private void a(int i) {
        Intent intent = new Intent();
        if (i == 1) {
            intent.setAction(ZhiChiConstants.sobot_close_now_clear_cache);
        } else {
            intent.setAction(ZhiChiConstants.sobot_click_cancle);
        }
        d.a(getApplicationContext(), intent);
    }

    @Override // com.sobot.chat.activity.base.SobotDialogBaseActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        d();
    }

    /* access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i2 == 200) {
            finish();
        }
    }
}
