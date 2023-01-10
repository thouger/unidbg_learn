package com.sobot.chat.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import com.android.internal.logging.nano.MetricsProto;
import com.android.internal.telephony.RILConstants;
import com.bangcle.andjni.JniLib;
import com.sobot.chat.activity.base.SobotBaseActivity;
import com.sobot.chat.adapter.m;
import com.sobot.chat.api.ZhiChiApi;
import com.sobot.chat.api.model.Information;
import com.sobot.chat.api.model.SobotUserTicketEvaluate;
import com.sobot.chat.api.model.SobotUserTicketInfo;
import com.sobot.chat.api.model.StUserDealTicketInfo;
import com.sobot.chat.api.model.ZhiChiUploadAppFileModelResult;
import com.sobot.chat.b;
import com.sobot.chat.core.http.c.a;
import com.sobot.chat.utils.ac;
import com.sobot.chat.utils.ae;
import com.sobot.chat.utils.e;
import com.sobot.chat.utils.q;
import com.sobot.chat.utils.s;
import com.sobot.chat.widget.dialog.SobotReplyActivity;
import com.sobot.chat.widget.dialog.SobotTicketEvaluateActivity;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class SobotTicketDetailActivity extends SobotBaseActivity implements View.OnClickListener {
    private String a = "";
    private String d = "";
    private SobotUserTicketInfo e;
    private int f;
    private Information g;
    private List<Object> h = new ArrayList();
    private ListView i;
    private m j;
    private LinearLayout k;
    private LinearLayout l;
    private TextView m;
    private TextView n;
    private SobotUserTicketEvaluate o;
    private String p;
    private ArrayList<ZhiChiUploadAppFileModelResult> q = new ArrayList<>();

    public static Intent a(Context context, String str, String str2, SobotUserTicketInfo sobotUserTicketInfo) {
        Intent intent = new Intent(context, SobotTicketDetailActivity.class);
        intent.putExtra("intent_key_uid", str2);
        intent.putExtra("intent_key_companyid", str);
        intent.putExtra("intent_key_ticket_info", sobotUserTicketInfo);
        return intent;
    }

    /* access modifiers changed from: protected */
    @Override // com.sobot.chat.activity.base.SobotBaseActivity
    public int a() {
        return c("sobot_activity_ticket_detail");
    }

    /* access modifiers changed from: protected */
    @Override // com.sobot.chat.activity.base.SobotBaseActivity
    public void a(Bundle bundle) {
        if (getIntent() != null) {
            this.a = getIntent().getStringExtra("intent_key_uid");
            this.d = getIntent().getStringExtra("intent_key_companyid");
            this.e = (SobotUserTicketInfo) getIntent().getSerializableExtra("intent_key_ticket_info");
            SobotUserTicketInfo sobotUserTicketInfo = this.e;
            if (sobotUserTicketInfo != null) {
                this.f = sobotUserTicketInfo.getFlag();
            }
        }
    }

    /* access modifiers changed from: protected */
    @Override // com.sobot.chat.activity.base.SobotBaseActivity
    public void b() {
        b(b("sobot_btn_back_selector"), "", true);
        k().setOnClickListener(new AnonymousClass1(this));
        setTitle(e("sobot_message_details"));
        this.i = (ListView) findViewById(a("sobot_listview"));
        this.k = (LinearLayout) findViewById(a("sobot_evaluate_ll"));
        this.l = (LinearLayout) findViewById(a("sobot_reply_ll"));
        this.m = (TextView) findViewById(a("sobot_evaluate_tv"));
        this.m.setText(q.f(this, "sobot_str_bottom_satisfaction"));
        this.n = (TextView) findViewById(a("sobot_reply_tv"));
        this.n.setText(q.f(this, "sobot_reply"));
        this.l.setOnClickListener(this);
        this.k.setOnClickListener(new AnonymousClass2(this));
    }

    /* renamed from: com.sobot.chat.activity.SobotTicketDetailActivity$1  reason: invalid class name */
    class AnonymousClass1 implements View.OnClickListener {
        final /* synthetic */ SobotTicketDetailActivity a;

        AnonymousClass1(SobotTicketDetailActivity sobotTicketDetailActivity) {
            JniLib.cV(this, sobotTicketDetailActivity, Integer.valueOf((int) RILConstants.RIL_UNSOL_RIL_CONNECTED));
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            List list = (List) s.d(this.a, "showBackEvaluateTicketIds");
            if (this.a.g == null || !this.a.g.isShowLeaveDetailBackEvaluate() || this.a.k.getVisibility() != 0) {
                this.a.finish();
            } else if (list == null || !list.contains(this.a.e.getTicketId())) {
                if (list == null) {
                    list = new ArrayList();
                }
                list.add(this.a.e.getTicketId());
                s.a(this.a, "showBackEvaluateTicketIds", list);
                Intent intent = new Intent(this.a, SobotTicketEvaluateActivity.class);
                intent.putExtra("sobotUserTicketEvaluate", this.a.o);
                this.a.startActivityForResult(intent, MetricsProto.MetricsEvent.ACTION_TEXT_SELECTION_CUT);
            } else {
                this.a.finish();
            }
        }
    }

    /* renamed from: com.sobot.chat.activity.SobotTicketDetailActivity$2  reason: invalid class name */
    class AnonymousClass2 implements View.OnClickListener {
        final /* synthetic */ SobotTicketDetailActivity a;

        AnonymousClass2(SobotTicketDetailActivity sobotTicketDetailActivity) {
            JniLib.cV(this, sobotTicketDetailActivity, Integer.valueOf((int) RILConstants.RIL_UNSOL_VOICE_RADIO_TECH_CHANGED));
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (view == this.a.k && this.a.o != null) {
                Intent intent = new Intent(this.a, SobotTicketEvaluateActivity.class);
                intent.putExtra("sobotUserTicketEvaluate", this.a.o);
                this.a.startActivityForResult(intent, MetricsProto.MetricsEvent.ACTION_TEXT_SELECTION_COPY);
            }
        }
    }

    /* access modifiers changed from: protected */
    @Override // com.sobot.chat.activity.base.SobotBaseActivity
    public void c() {
        this.g = (Information) s.d(this, "sobot_last_current_info");
        this.k.setVisibility(8);
        this.l.setVisibility(8);
        if (this.e != null) {
            this.b.getUserDealTicketInfoList(this, this.a, this.d, this.e.getTicketId(), new AnonymousClass3(this));
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: com.sobot.chat.activity.SobotTicketDetailActivity$3  reason: invalid class name */
    public class AnonymousClass3 implements a<List<StUserDealTicketInfo>> {
        final /* synthetic */ SobotTicketDetailActivity a;

        AnonymousClass3(SobotTicketDetailActivity sobotTicketDetailActivity) {
            JniLib.cV(this, sobotTicketDetailActivity, Integer.valueOf((int) RILConstants.RIL_UNSOL_CELL_INFO_LIST));
        }

        public void a(List<StUserDealTicketInfo> list) {
            ZhiChiApi zhiChiApi = this.a.b;
            SobotTicketDetailActivity sobotTicketDetailActivity = this.a;
            zhiChiApi.updateUserTicketReplyInfo(sobotTicketDetailActivity, sobotTicketDetailActivity.d, this.a.g.getPartnerid(), this.a.e.getTicketId());
            if (list != null && list.size() > 0) {
                this.a.h.clear();
                Iterator<StUserDealTicketInfo> it2 = list.iterator();
                while (true) {
                    if (!it2.hasNext()) {
                        break;
                    }
                    StUserDealTicketInfo next = it2.next();
                    if (next.getFlag() == 1) {
                        this.a.e.setFileList(next.getFileList());
                        this.a.e.setContent(next.getContent());
                        if (ac.a((Object) this.a.e.getTimeStr())) {
                            this.a.e.setTimeStr(next.getTimeStr());
                        }
                    }
                }
                this.a.h.add(this.a.e);
                this.a.h.addAll(list);
                Iterator<StUserDealTicketInfo> it3 = list.iterator();
                while (true) {
                    if (!it3.hasNext()) {
                        break;
                    }
                    StUserDealTicketInfo next2 = it3.next();
                    if (next2.getFlag() == 3 && this.a.e.getFlag() != 3) {
                        this.a.e.setFlag(3);
                    }
                    if (this.a.e.getFlag() != 3 && this.a.e.getFlag() < next2.getFlag()) {
                        this.a.e.setFlag(next2.getFlag());
                    }
                    if (next2.getFlag() == 3 && next2.getEvaluate() != null) {
                        this.a.h.add(next2.getEvaluate());
                        this.a.o = next2.getEvaluate();
                        if (this.a.o.isOpen()) {
                            if (!this.a.o.isEvalution()) {
                                this.a.k.setVisibility(0);
                                break;
                            }
                            this.a.k.setVisibility(8);
                        } else {
                            this.a.k.setVisibility(8);
                        }
                    }
                }
                if (this.a.j == null) {
                    SobotTicketDetailActivity sobotTicketDetailActivity2 = this.a;
                    sobotTicketDetailActivity2.j = new m(sobotTicketDetailActivity2, sobotTicketDetailActivity2, sobotTicketDetailActivity2.h);
                    this.a.i.setAdapter((ListAdapter) this.a.j);
                } else {
                    this.a.j.notifyDataSetChanged();
                }
                if (b.a(2) || this.a.e.getFlag() != 3) {
                    this.a.l.setVisibility(0);
                } else {
                    this.a.l.setVisibility(8);
                }
            }
        }

        @Override // com.sobot.chat.core.http.c.a
        public void a(Exception exc, String str) {
            ae.a(this.a, str);
        }
    }

    public void a(int i, String str) {
        this.b.addTicketSatisfactionScoreInfo(this, this.a, this.d, this.e.getTicketId(), i, str, new AnonymousClass4(this, i, str));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: com.sobot.chat.activity.SobotTicketDetailActivity$4  reason: invalid class name */
    public class AnonymousClass4 implements a<String> {
        final /* synthetic */ int a;
        final /* synthetic */ String b;
        final /* synthetic */ SobotTicketDetailActivity c;

        AnonymousClass4(SobotTicketDetailActivity sobotTicketDetailActivity, int i, String str) {
            JniLib.cV(this, sobotTicketDetailActivity, Integer.valueOf(i), str, 1037);
        }

        public void a(String str) {
            SobotTicketDetailActivity sobotTicketDetailActivity = this.c;
            e.a(sobotTicketDetailActivity, q.f(sobotTicketDetailActivity, "sobot_leavemsg_success_tip"), 1000, q.e(this.c, "sobot_iv_login_right")).show();
            this.c.k.setVisibility(8);
            int i = 0;
            while (true) {
                if (i >= this.c.h.size()) {
                    break;
                }
                if (this.c.h.get(i) instanceof StUserDealTicketInfo) {
                    StUserDealTicketInfo stUserDealTicketInfo = (StUserDealTicketInfo) this.c.h.get(i);
                    if (stUserDealTicketInfo.getFlag() == 3 && stUserDealTicketInfo.getEvaluate() != null) {
                        SobotUserTicketEvaluate evaluate = stUserDealTicketInfo.getEvaluate();
                        evaluate.setScore(this.a);
                        evaluate.setRemark(this.b);
                        evaluate.setEvalution(true);
                        this.c.j.notifyDataSetChanged();
                        break;
                    }
                }
                i++;
            }
            this.c.d();
        }

        @Override // com.sobot.chat.core.http.c.a
        public void a(Exception exc, String str) {
            ae.a(this.c.getApplicationContext(), str);
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view == this.l) {
            Intent intent = new Intent(this, SobotReplyActivity.class);
            intent.putExtra("uid", this.a);
            intent.putExtra("companyId", this.d);
            intent.putExtra("ticketInfo", this.e);
            intent.putExtra("picTempList", this.q);
            intent.putExtra("replyTempContent", this.p);
            startActivityForResult(intent, 4097);
        }
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        List list = (List) s.d(this, "showBackEvaluateTicketIds");
        Information information = this.g;
        if (information == null || !information.isShowLeaveDetailBackEvaluate() || this.k.getVisibility() != 0 || (list != null && list.contains(this.e.getTicketId()))) {
            SobotUserTicketInfo sobotUserTicketInfo = this.e;
            if (!(sobotUserTicketInfo == null || this.f == sobotUserTicketInfo.getFlag())) {
                setResult(-1);
            }
            super.onBackPressed();
            return;
        }
        if (list == null) {
            list = new ArrayList();
        }
        list.add(this.e.getTicketId());
        s.a(this, "showBackEvaluateTicketIds", list);
        Intent intent = new Intent(this, SobotTicketEvaluateActivity.class);
        intent.putExtra("sobotUserTicketEvaluate", this.o);
        startActivityForResult(intent, MetricsProto.MetricsEvent.ACTION_TEXT_SELECTION_CUT);
    }

    /* access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onActivityResult(int i, int i2, Intent intent) {
        boolean z;
        super.onActivityResult(i, i2, intent);
        if (i2 == -1) {
            if (i == 4097) {
                if (intent != null) {
                    z = intent.getBooleanExtra("isTemp", false);
                    this.p = intent.getStringExtra("replyTempContent");
                    this.q = (ArrayList) intent.getSerializableExtra("picTempList");
                } else {
                    z = false;
                }
                if (!z) {
                    c();
                }
            }
            if (i == 1109) {
                a(intent.getIntExtra("score", 0), intent.getStringExtra("content"));
            }
            if (i == 1111) {
                int intExtra = intent.getIntExtra("score", 0);
                String stringExtra = intent.getStringExtra("content");
                this.b.addTicketSatisfactionScoreInfo(this, this.a, this.d, this.e.getTicketId(), intExtra, stringExtra, new AnonymousClass5(this, intExtra, stringExtra));
            }
        }
    }

    /* renamed from: com.sobot.chat.activity.SobotTicketDetailActivity$5  reason: invalid class name */
    class AnonymousClass5 implements a<String> {
        final /* synthetic */ int a;
        final /* synthetic */ String b;
        final /* synthetic */ SobotTicketDetailActivity c;

        AnonymousClass5(SobotTicketDetailActivity sobotTicketDetailActivity, int i, String str) {
            JniLib.cV(this, sobotTicketDetailActivity, Integer.valueOf(i), str, Integer.valueOf((int) RILConstants.RIL_UNSOL_SRVCC_STATE_NOTIFY));
        }

        public void a(String str) {
            this.c.k.setVisibility(8);
            int i = 0;
            while (true) {
                if (i >= this.c.h.size()) {
                    break;
                }
                if (this.c.h.get(i) instanceof StUserDealTicketInfo) {
                    StUserDealTicketInfo stUserDealTicketInfo = (StUserDealTicketInfo) this.c.h.get(i);
                    if (stUserDealTicketInfo.getFlag() == 3 && stUserDealTicketInfo.getEvaluate() != null) {
                        SobotUserTicketEvaluate evaluate = stUserDealTicketInfo.getEvaluate();
                        evaluate.setScore(this.a);
                        evaluate.setRemark(this.b);
                        evaluate.setEvalution(true);
                        this.c.j.notifyDataSetChanged();
                        break;
                    }
                }
                i++;
            }
            this.c.d();
            SobotTicketDetailActivity sobotTicketDetailActivity = this.c;
            ae.a(sobotTicketDetailActivity, q.f(sobotTicketDetailActivity, "sobot_leavemsg_success_tip"), 1000, new AnonymousClass1(this));
        }

        /* access modifiers changed from: package-private */
        /* renamed from: com.sobot.chat.activity.SobotTicketDetailActivity$5$1  reason: invalid class name */
        public class AnonymousClass1 implements ae.a {
            final /* synthetic */ AnonymousClass5 a;

            AnonymousClass1(AnonymousClass5 r5) {
                JniLib.cV(this, r5, Integer.valueOf((int) RILConstants.RIL_UNSOL_UICC_SUBSCRIPTION_STATUS_CHANGED));
            }

            @Override // com.sobot.chat.utils.ae.a
            public void a() {
                this.a.c.finish();
            }
        }

        @Override // com.sobot.chat.core.http.c.a
        public void a(Exception exc, String str) {
            ae.a(this.c.getApplicationContext(), str);
        }
    }

    public void d() {
        List list = (List) s.d(this, "showBackEvaluateTicketIds");
        SobotUserTicketInfo sobotUserTicketInfo = this.e;
        if (!(sobotUserTicketInfo == null || list == null)) {
            list.remove(sobotUserTicketInfo.getTicketId());
        }
        s.a(this, "showBackEvaluateTicketIds", list);
    }
}
