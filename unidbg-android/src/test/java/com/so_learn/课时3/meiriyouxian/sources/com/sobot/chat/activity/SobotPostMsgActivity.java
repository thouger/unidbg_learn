package com.sobot.chat.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.viewpager.widget.ViewPager;
import com.bangcle.andjni.JniLib;
import com.sobot.chat.activity.base.SobotBaseActivity;
import com.sobot.chat.adapter.StViewPagerAdapter;
import com.sobot.chat.api.model.Information;
import com.sobot.chat.api.model.SobotLeaveMsgConfig;
import com.sobot.chat.api.model.ZhiChiInitModeBase;
import com.sobot.chat.b;
import com.sobot.chat.fragment.SobotBaseFragment;
import com.sobot.chat.fragment.SobotPostMsgFragment;
import com.sobot.chat.fragment.SobotTicketInfoFragment;
import com.sobot.chat.utils.c;
import com.sobot.chat.utils.q;
import com.sobot.chat.utils.r;
import com.sobot.chat.utils.s;
import com.sobot.chat.widget.PagerSlidingTab;
import com.sobot.chat.widget.dialog.f;
import java.util.ArrayList;
import java.util.List;

public class SobotPostMsgActivity extends SobotBaseActivity implements View.OnClickListener {
    private SobotLeaveMsgConfig a;
    private String d = "";
    private String e = "";
    private String f = "";
    private String g = "";
    private boolean h;
    private boolean i;
    private boolean j;
    private int k = -1;
    private LinearLayout l;
    private LinearLayout m;
    private ViewPager n;
    private TextView o;
    private TextView p;
    private TextView q;
    private TextView r;
    private StViewPagerAdapter s;
    private PagerSlidingTab t;
    private ImageView u;
    private SobotPostMsgFragment v;
    private List<SobotBaseFragment> w = new ArrayList();
    private MessageReceiver x;
    private f y;

    /* access modifiers changed from: protected */
    @Override // com.sobot.chat.activity.base.SobotBaseActivity
    public int a() {
        return c("sobot_activity_post_msg");
    }

    /* access modifiers changed from: protected */
    @Override // com.sobot.chat.activity.base.SobotBaseActivity
    public void a(Bundle bundle) {
        if (getIntent() != null) {
            this.d = getIntent().getStringExtra("intent_key_uid");
            this.a = (SobotLeaveMsgConfig) getIntent().getSerializableExtra("intent_key_config");
            this.e = getIntent().getStringExtra("intent_key_groupid");
            this.f = getIntent().getStringExtra("intent_key_customerid");
            this.g = getIntent().getStringExtra("intent_key_companyid");
            this.k = getIntent().getIntExtra("FLAG_EXIT_TYPE", -1);
            this.h = getIntent().getBooleanExtra("FLAG_EXIT_SDK", false);
            this.i = getIntent().getBooleanExtra("intent_key_is_show_ticket", false);
        }
    }

    /* access modifiers changed from: protected */
    @Override // com.sobot.chat.activity.base.SobotBaseActivity
    public void b() {
        this.m = (LinearLayout) findViewById(a("sobot_ll_completed"));
        this.l = (LinearLayout) findViewById(a("sobot_ll_container"));
        this.o = (TextView) findViewById(a("sobot_tv_ticket"));
        this.o.setText(q.f(this, "sobot_leaveMsg_to_ticket"));
        this.p = (TextView) findViewById(a("sobot_tv_completed"));
        this.p.setText(q.f(this, "sobot_leaveMsg_create_complete"));
        this.n = (ViewPager) findViewById(a("sobot_viewPager"));
        this.t = (PagerSlidingTab) findViewById(a("sobot_pst_indicator"));
        this.u = (ImageView) findViewById(a("sobot_pst_back_iv"));
        if (this.u != null && b.a(1) && b.a(4)) {
            ((LinearLayout.LayoutParams) this.u.getLayoutParams()).leftMargin += 34;
        }
        this.q = (TextView) findViewById(a("sobot_tv_leaveMsg_create_success"));
        this.q.setText(q.f(this, "sobot_leavemsg_success_tip"));
        this.r = (TextView) findViewById(a("sobot_tv_leaveMsg_create_success_des"));
        this.r.setText(q.f(this, "sobot_leavemsg_success_tip"));
        this.o.setOnClickListener(this);
        this.p.setOnClickListener(this);
        this.u.setOnClickListener(this);
        t();
        if (b.a(1)) {
            ((LinearLayout.LayoutParams) this.p.getLayoutParams()).topMargin = r.a((Context) this, 40.0f);
        }
    }

    /* access modifiers changed from: protected */
    @Override // com.sobot.chat.activity.base.SobotBaseActivity
    public void c() {
        SobotLeaveMsgConfig sobotLeaveMsgConfig;
        ZhiChiInitModeBase zhiChiInitModeBase = (ZhiChiInitModeBase) s.d(this, "sobot_last_current_initModel");
        if (zhiChiInitModeBase != null && c.a(zhiChiInitModeBase.getAccountStatus())) {
            this.y = new f(this, new AnonymousClass1(this));
            f fVar = this.y;
            if (fVar != null && !fVar.isShowing()) {
                this.y.show();
            }
        }
        this.w.clear();
        if (!this.i) {
            if (this.a == null) {
                Information information = (Information) s.d(this, "sobot_last_current_info");
                this.a = new SobotLeaveMsgConfig();
                this.a.setEmailFlag(zhiChiInitModeBase.isEmailFlag());
                this.a.setEmailShowFlag(zhiChiInitModeBase.isEmailShowFlag());
                this.a.setEnclosureFlag(zhiChiInitModeBase.isEnclosureFlag());
                this.a.setEnclosureShowFlag(zhiChiInitModeBase.isEnclosureShowFlag());
                this.a.setTelFlag(zhiChiInitModeBase.isTelFlag());
                this.a.setTelShowFlag(zhiChiInitModeBase.isTelShowFlag());
                this.a.setTicketStartWay(zhiChiInitModeBase.isTicketStartWay());
                this.a.setTicketShowFlag(zhiChiInitModeBase.isTicketShowFlag());
                this.a.setCompanyId(zhiChiInitModeBase.getCompanyId());
                if (!TextUtils.isEmpty(information.getLeaveMsgTemplateContent())) {
                    this.a.setMsgTmp(information.getLeaveMsgTemplateContent());
                } else {
                    this.a.setMsgTmp(zhiChiInitModeBase.getMsgTmp());
                }
                if (!TextUtils.isEmpty(information.getLeaveMsgGuideContent())) {
                    this.a.setMsgTxt(information.getLeaveMsgGuideContent());
                } else {
                    this.a.setMsgTxt(zhiChiInitModeBase.getMsgTxt());
                }
            }
            Bundle bundle = new Bundle();
            bundle.putString("intent_key_uid", this.d);
            bundle.putString("intent_key_groupid", this.e);
            bundle.putInt("FLAG_EXIT_TYPE", this.k);
            bundle.putBoolean("FLAG_EXIT_SDK", this.h);
            bundle.putSerializable("intent_key_config", this.a);
            bundle.putSerializable("intent_key_cus_fields", getIntent().getSerializableExtra("intent_key_cus_fields"));
            this.v = SobotPostMsgFragment.a(bundle);
            this.w.add(this.v);
        }
        if (this.i || ((sobotLeaveMsgConfig = this.a) != null && sobotLeaveMsgConfig.isTicketShowFlag())) {
            Bundle bundle2 = new Bundle();
            bundle2.putString("intent_key_uid", this.d);
            bundle2.putString("intent_key_companyid", this.g);
            bundle2.putString("intent_key_customerid", this.f);
            this.w.add(SobotTicketInfoFragment.a(bundle2));
        }
        SobotLeaveMsgConfig sobotLeaveMsgConfig2 = this.a;
        if (sobotLeaveMsgConfig2 != null) {
            this.o.setVisibility(sobotLeaveMsgConfig2.isTicketShowFlag() ? 0 : 8);
        }
        this.s = new StViewPagerAdapter(this, getSupportFragmentManager(), new String[]{e("sobot_please_leave_a_message"), e("sobot_message_record")}, this.w);
        this.n.setAdapter(this.s);
        SobotLeaveMsgConfig sobotLeaveMsgConfig3 = this.a;
        if (sobotLeaveMsgConfig3 != null && sobotLeaveMsgConfig3.isTicketShowFlag() && !this.i) {
            if (!this.j) {
                this.m.setVisibility(0);
                this.l.setVisibility(0);
            }
            this.t.setViewPager(this.n);
        }
        if (this.i) {
            b(b("sobot_btn_back_selector"), "", true);
            setTitle(e("sobot_message_record"));
            d();
            j().setVisibility(0);
            return;
        }
        j().setVisibility(8);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: com.sobot.chat.activity.SobotPostMsgActivity$1  reason: invalid class name */
    public class AnonymousClass1 implements View.OnClickListener {
        final /* synthetic */ SobotPostMsgActivity a;

        AnonymousClass1(SobotPostMsgActivity sobotPostMsgActivity) {
            JniLib.cV(this, sobotPostMsgActivity, 1025);
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            this.a.y.dismiss();
            this.a.finish();
        }
    }

    private void d() {
        if (this.w.size() > 0) {
            int size = this.w.size() - 1;
            this.n.setCurrentItem(size);
            SobotBaseFragment sobotBaseFragment = this.w.get(size);
            if (sobotBaseFragment instanceof SobotTicketInfoFragment) {
                ((SobotTicketInfoFragment) sobotBaseFragment).a();
            }
        }
    }

    private void t() {
        if (this.x == null) {
            this.x = new MessageReceiver();
        }
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("sobot_action_show_completed_view");
        LocalBroadcastManager.getInstance(q()).registerReceiver(this.x, intentFilter);
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        SobotPostMsgFragment sobotPostMsgFragment = this.v;
        if (sobotPostMsgFragment != null) {
            sobotPostMsgFragment.b();
        } else {
            super.onBackPressed();
        }
    }

    /* access modifiers changed from: protected */
    @Override // com.sobot.chat.activity.base.SobotBaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        LocalBroadcastManager.getInstance(q()).unregisterReceiver(this.x);
        super.onDestroy();
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view == this.o) {
            this.m.setVisibility(8);
            this.n.setVisibility(0);
            SobotLeaveMsgConfig sobotLeaveMsgConfig = this.a;
            if (sobotLeaveMsgConfig != null && sobotLeaveMsgConfig.isTicketShowFlag()) {
                this.l.setVisibility(0);
            }
            d();
        }
        if (view == this.p) {
            onBackPressed();
        }
        if (view == this.u) {
            onBackPressed();
        }
    }

    public class MessageReceiver extends BroadcastReceiver {
        public MessageReceiver() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (intent != null && "sobot_action_show_completed_view".equals(intent.getAction())) {
                SobotPostMsgActivity.this.l.setVisibility(8);
                SobotPostMsgActivity.this.n.setVisibility(8);
                SobotPostMsgActivity.this.m.setVisibility(0);
                SobotPostMsgActivity.this.j = true;
                SobotPostMsgActivity.this.c();
            }
        }
    }
}
