package com.sobot.chat.widget.dialog;

import android.bluetooth.BluetoothClass;
import android.content.Intent;
import android.os.Process;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import com.bangcle.andjni.JniLib;
import com.sobot.chat.activity.base.SobotDialogBaseActivity;
import com.sobot.chat.api.apiUtils.ZhiChiConstants;
import com.sobot.chat.api.model.Information;
import com.sobot.chat.api.model.SobotUserTicketEvaluate;
import com.sobot.chat.utils.q;
import com.sobot.chat.utils.s;
import com.sobot.chat.widget.SobotEditTextLayout;
import com.sobot.chat.widget.kpswitch.util.c;
import java.util.List;

public class SobotTicketEvaluateActivity extends SobotDialogBaseActivity {
    private LinearLayout a;
    private RatingBar d;
    private TextView e;
    private EditText f;
    private TextView g;
    private SobotEditTextLayout h;
    private Button i;
    private SobotUserTicketEvaluate j;

    /* access modifiers changed from: protected */
    @Override // com.sobot.chat.activity.base.SobotBaseActivity
    public void c() {
    }

    /* access modifiers changed from: protected */
    @Override // com.sobot.chat.activity.base.SobotBaseActivity
    public int a() {
        return q.a(this, "sobot_layout_ticket_evaluate");
    }

    /* access modifiers changed from: protected */
    @Override // com.sobot.chat.activity.base.SobotBaseActivity
    public void b() {
        this.f = (EditText) findViewById(a("sobot_add_content"));
        this.f.setHint(q.f(this, "sobot_edittext_hint"));
        this.i = (Button) findViewById(a(ZhiChiConstants.sobot_close_now));
        this.i.setText(q.f(this, "sobot_btn_submit_text"));
        this.g = (TextView) findViewById(a("sobot_tv_evaluate_title"));
        this.g.setText(q.f(this, "sobot_please_comment"));
        this.d = (RatingBar) findViewById(a("sobot_ratingBar"));
        this.h = (SobotEditTextLayout) findViewById(a("setl_submit_content"));
        this.a = (LinearLayout) findViewById(a("sobot_negativeButton"));
        this.a.setOnClickListener(new AnonymousClass1(this));
        this.e = (TextView) findViewById(a("sobot_ratingBar_title"));
        this.j = (SobotUserTicketEvaluate) getIntent().getSerializableExtra("sobotUserTicketEvaluate");
        SobotUserTicketEvaluate sobotUserTicketEvaluate = this.j;
        if (sobotUserTicketEvaluate != null) {
            if (sobotUserTicketEvaluate.isOpen()) {
                this.f.setVisibility(this.j.isTxtFlag() ? 0 : 8);
            }
            d();
        }
    }

    /* renamed from: com.sobot.chat.widget.dialog.SobotTicketEvaluateActivity$1  reason: invalid class name */
    class AnonymousClass1 implements View.OnClickListener {
        final /* synthetic */ SobotTicketEvaluateActivity a;

        AnonymousClass1(SobotTicketEvaluateActivity sobotTicketEvaluateActivity) {
            JniLib.cV(this, sobotTicketEvaluateActivity, 1059);
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            this.a.finish();
        }
    }

    private void d() {
        if (!((Information) s.d(t(), "sobot_last_current_info")).isHideManualEvaluationLabels()) {
            this.e.setVisibility(0);
        } else {
            this.e.setVisibility(8);
        }
        this.d.setOnRatingBarChangeListener(new AnonymousClass2(this));
        this.d.setRating(5.0f);
        this.i.setOnClickListener(new AnonymousClass3(this));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: com.sobot.chat.widget.dialog.SobotTicketEvaluateActivity$2  reason: invalid class name */
    public class AnonymousClass2 implements RatingBar.OnRatingBarChangeListener {
        final /* synthetic */ SobotTicketEvaluateActivity a;

        AnonymousClass2(SobotTicketEvaluateActivity sobotTicketEvaluateActivity) {
            JniLib.cV(this, sobotTicketEvaluateActivity, Integer.valueOf((int) BluetoothClass.Device.AUDIO_VIDEO_SET_TOP_BOX));
        }

        @Override // android.widget.RatingBar.OnRatingBarChangeListener
        public void onRatingChanged(RatingBar ratingBar, float f, boolean z) {
            List<SobotUserTicketEvaluate.TicketScoreInfooListBean> ticketScoreInfooList;
            int ceil = (int) Math.ceil((double) this.a.d.getRating());
            if (ceil > 0 && ceil <= 5 && (ticketScoreInfooList = this.a.j.getTicketScoreInfooList()) != null && ticketScoreInfooList.size() >= ceil) {
                this.a.e.setText(ticketScoreInfooList.get(5 - ceil).getScoreExplain());
            }
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: com.sobot.chat.widget.dialog.SobotTicketEvaluateActivity$3  reason: invalid class name */
    public class AnonymousClass3 implements View.OnClickListener {
        final /* synthetic */ SobotTicketEvaluateActivity a;

        AnonymousClass3(SobotTicketEvaluateActivity sobotTicketEvaluateActivity) {
            JniLib.cV(this, sobotTicketEvaluateActivity, Integer.valueOf((int) Process.OTA_UPDATE_UID));
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            int ceil = (int) Math.ceil((double) this.a.d.getRating());
            c.b(this.a.f);
            Intent intent = new Intent();
            intent.putExtra("score", ceil);
            intent.putExtra("content", this.a.f.getText().toString());
            this.a.setResult(-1, intent);
            this.a.finish();
        }
    }
}
