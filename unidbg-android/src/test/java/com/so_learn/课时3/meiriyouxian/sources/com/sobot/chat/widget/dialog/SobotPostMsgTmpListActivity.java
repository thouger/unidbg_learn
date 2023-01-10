package com.sobot.chat.widget.dialog;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.TextView;
import com.bangcle.andjni.JniLib;
import com.sobot.chat.activity.base.SobotDialogBaseActivity;
import com.sobot.chat.adapter.i;
import com.sobot.chat.api.apiUtils.ZhiChiConstants;
import com.sobot.chat.api.model.SobotLeaveMsgConfig;
import com.sobot.chat.api.model.SobotPostMsgTemplate;
import com.sobot.chat.core.http.c.a;
import com.sobot.chat.utils.d;
import com.sobot.chat.utils.q;
import java.util.ArrayList;

public class SobotPostMsgTmpListActivity extends SobotDialogBaseActivity implements View.OnClickListener, AdapterView.OnItemClickListener {
    private LinearLayout a;
    private GridView d;
    private TextView e;
    private ArrayList<SobotPostMsgTemplate> f;
    private i g;

    /* access modifiers changed from: protected */
    @Override // com.sobot.chat.activity.base.SobotBaseActivity
    public int a() {
        return q.a(t(), "sobot_layout_post_msg_tmps");
    }

    /* access modifiers changed from: protected */
    @Override // com.sobot.chat.activity.base.SobotBaseActivity
    public void b() {
        this.a = (LinearLayout) findViewById(a("sobot_negativeButton"));
        this.d = (GridView) findViewById(a("sobot_gv"));
        this.d.setOnItemClickListener(this);
        this.a.setOnClickListener(this);
        this.e = (TextView) findViewById(a("sobot_tv_title"));
        this.e.setText(q.f(t(), "sobot_choice_business"));
    }

    /* access modifiers changed from: protected */
    @Override // com.sobot.chat.activity.base.SobotBaseActivity
    public void c() {
        this.f = (ArrayList) getIntent().getSerializableExtra("sobotPostMsgTemplateList");
        if (this.g == null) {
            this.g = new i(t(), this.f);
            this.d.setAdapter((ListAdapter) this.g);
        }
    }

    @Override // android.widget.AdapterView.OnItemClickListener
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        this.b.getMsgTemplateConfig(t(), getIntent().getStringExtra("uid"), ((SobotPostMsgTemplate) this.g.getItem(i)).getTemplateId(), new AnonymousClass1(this));
    }

    /* renamed from: com.sobot.chat.widget.dialog.SobotPostMsgTmpListActivity$1  reason: invalid class name */
    class AnonymousClass1 implements a<SobotLeaveMsgConfig> {
        final /* synthetic */ SobotPostMsgTmpListActivity a;

        AnonymousClass1(SobotPostMsgTmpListActivity sobotPostMsgTmpListActivity) {
            JniLib.cV(this, sobotPostMsgTmpListActivity, 1050);
        }

        @Override // com.sobot.chat.core.http.c.a
        public void a(Exception exc, String str) {
        }

        public void a(SobotLeaveMsgConfig sobotLeaveMsgConfig) {
            if (sobotLeaveMsgConfig != null) {
                Intent intent = new Intent();
                intent.setAction(ZhiChiConstants.SOBOT_POST_MSG_TMP_BROCAST);
                intent.putExtra("sobotLeaveMsgConfig", sobotLeaveMsgConfig);
                intent.putExtra("uid", this.a.getIntent().getStringExtra("uid"));
                intent.putExtra("mflag_exit_sdk", this.a.getIntent().getBooleanExtra("flag_exit_sdk", false));
                intent.putExtra("mIsShowTicket", this.a.getIntent().getBooleanExtra("isShowTicket", false));
                d.a(this.a.t(), intent);
                this.a.finish();
            }
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view == this.a) {
            finish();
        }
    }
}
