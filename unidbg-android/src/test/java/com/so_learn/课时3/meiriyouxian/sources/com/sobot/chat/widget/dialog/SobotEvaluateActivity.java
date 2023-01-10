package com.sobot.chat.widget.dialog;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.WifiEnterpriseConfig;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.TextView;
import androidx.core.content.ContextCompat;
import com.android.internal.telephony.RILConstants;
import com.bangcle.andjni.JniLib;
import com.sobot.chat.activity.base.SobotDialogBaseActivity;
import com.sobot.chat.api.ResultCallBack;
import com.sobot.chat.api.ZhiChiApi;
import com.sobot.chat.api.apiUtils.ZhiChiConstants;
import com.sobot.chat.api.model.CommonModel;
import com.sobot.chat.api.model.Information;
import com.sobot.chat.api.model.SatisfactionSet;
import com.sobot.chat.api.model.SatisfactionSetBase;
import com.sobot.chat.api.model.SobotCommentParam;
import com.sobot.chat.api.model.ZhiChiInitModeBase;
import com.sobot.chat.core.channel.a;
import com.sobot.chat.utils.ae;
import com.sobot.chat.utils.c;
import com.sobot.chat.utils.d;
import com.sobot.chat.utils.q;
import com.sobot.chat.utils.r;
import com.sobot.chat.utils.s;
import com.sobot.chat.widget.SobotEditTextLayout;
import com.xiaomi.mipush.sdk.Constants;
import java.util.ArrayList;
import java.util.List;

public class SobotEvaluateActivity extends SobotDialogBaseActivity {
    private EditText A;
    private TextView B;
    private TextView C;
    private TextView D;
    private TextView E;
    private TextView F;
    private TextView G;
    private TextView H;
    private RatingBar I;
    private String J;
    private LinearLayout K;
    private LinearLayout L;
    private LinearLayout M;
    private CheckBox N;
    private CheckBox O;
    private CheckBox P;
    private CheckBox Q;
    private CheckBox R;
    private CheckBox S;
    private SobotEditTextLayout T;
    private List<CheckBox> U = new ArrayList();
    private final String a = SobotEvaluateActivity.class.getSimpleName();
    private Activity d;
    private int e;
    private int f;
    private boolean g;
    private boolean h;
    private boolean i;
    private boolean j;
    private ZhiChiInitModeBase k;
    private Information l;
    private int m;
    private int n;
    private String o;
    private List<SatisfactionSetBase> p;
    private SatisfactionSetBase q;
    private LinearLayout r;
    private LinearLayout s;
    private LinearLayout t;
    private LinearLayout u;
    private RadioGroup v;
    private RadioButton w;
    private RadioButton x;
    private Button y;
    private View z;

    @Override // com.sobot.chat.activity.base.SobotDialogBaseActivity
    public Activity t() {
        return this;
    }

    /* access modifiers changed from: protected */
    @Override // com.sobot.chat.activity.base.SobotBaseActivity
    public int a() {
        return q.a(this, "sobot_layout_evaluate");
    }

    /* access modifiers changed from: protected */
    @Override // com.sobot.chat.activity.base.SobotBaseActivity
    public void b() {
        this.l = (Information) s.d(t(), "sobot_last_current_info");
        this.d = t();
        this.e = getIntent().getIntExtra("score", 0);
        this.J = getIntent().getStringExtra("evaluateChecklables");
        this.g = getIntent().getBooleanExtra("isFinish", false);
        this.i = getIntent().getBooleanExtra("isSessionOver", false);
        this.h = getIntent().getBooleanExtra("isExitSession", false);
        this.j = getIntent().getBooleanExtra("isBackShowEvaluate", false);
        this.k = (ZhiChiInitModeBase) getIntent().getSerializableExtra("initModel");
        this.m = getIntent().getIntExtra("current_model", 0);
        this.n = getIntent().getIntExtra("commentType", 0);
        this.o = getIntent().getStringExtra("customName");
        this.f = getIntent().getIntExtra("isSolve", 0);
        this.y = (Button) findViewById(a(ZhiChiConstants.sobot_close_now));
        this.y.setText(q.f(this.d, "sobot_btn_submit_text"));
        this.v = (RadioGroup) findViewById(a("sobot_readiogroup"));
        this.B = (TextView) findViewById(a("sobot_tv_evaluate_title"));
        this.B.setText(q.f(this.d, "sobot_robot_customer_service_evaluation"));
        this.C = (TextView) findViewById(a("sobot_robot_center_title"));
        this.C.setText(q.f(this.d, "sobot_question"));
        this.D = (TextView) findViewById(a("sobot_text_other_problem"));
        this.E = (TextView) findViewById(a("sobot_custom_center_title"));
        this.E.setText(q.f(this.d, "sobot_please_evaluate"));
        this.F = (TextView) findViewById(a("sobot_ratingBar_title"));
        this.F.setText(q.f(this.d, "sobot_great_satisfaction"));
        this.H = (TextView) findViewById(a("sobot_tv_evaluate_title_hint"));
        this.G = (TextView) findViewById(a("sobot_evaluate_cancel"));
        this.G.setText(q.f(this.d, "sobot_temporarily_not_evaluation"));
        this.z = findViewById(q.a(this.d, "id", "sobot_ratingBar_split_view"));
        this.r = (LinearLayout) findViewById(a("sobot_negativeButton"));
        this.r.setOnClickListener(new AnonymousClass1(this));
        Information information = this.l;
        if (information == null || !information.isCanBackWithNotEvaluation()) {
            this.G.setVisibility(8);
        } else {
            this.G.setVisibility(0);
        }
        this.I = (RatingBar) findViewById(a("sobot_ratingBar"));
        this.K = (LinearLayout) findViewById(a("sobot_evaluate_ll_lable1"));
        this.L = (LinearLayout) findViewById(a("sobot_evaluate_ll_lable2"));
        this.M = (LinearLayout) findViewById(a("sobot_evaluate_ll_lable3"));
        this.N = (CheckBox) findViewById(a("sobot_evaluate_cb_lable1"));
        this.O = (CheckBox) findViewById(a("sobot_evaluate_cb_lable2"));
        this.P = (CheckBox) findViewById(a("sobot_evaluate_cb_lable3"));
        this.Q = (CheckBox) findViewById(a("sobot_evaluate_cb_lable4"));
        this.R = (CheckBox) findViewById(a("sobot_evaluate_cb_lable5"));
        this.S = (CheckBox) findViewById(a("sobot_evaluate_cb_lable6"));
        this.U.add(this.N);
        this.U.add(this.O);
        this.U.add(this.P);
        this.U.add(this.Q);
        this.U.add(this.R);
        this.U.add(this.S);
        this.A = (EditText) findViewById(a("sobot_add_content"));
        this.A.setHint(q.f(this.d, "sobot_edittext_hint"));
        this.w = (RadioButton) findViewById(a("sobot_btn_ok_robot"));
        this.w.setText(q.f(this.d, "sobot_evaluate_yes"));
        this.w.setChecked(true);
        this.x = (RadioButton) findViewById(a("sobot_btn_no_robot"));
        this.x.setText(q.f(this.d, "sobot_evaluate_no"));
        this.s = (LinearLayout) findViewById(a("sobot_robot_relative"));
        this.t = (LinearLayout) findViewById(a("sobot_custom_relative"));
        this.u = (LinearLayout) findViewById(a("sobot_hide_layout"));
        this.T = (SobotEditTextLayout) findViewById(a("setl_submit_content"));
        u();
        d();
        if (r.c(this.d)) {
            getWindow().setLayout(-1, -1);
        }
        displayInNotch(this.T);
    }

    /* renamed from: com.sobot.chat.widget.dialog.SobotEvaluateActivity$1  reason: invalid class name */
    class AnonymousClass1 implements View.OnClickListener {
        final /* synthetic */ SobotEvaluateActivity a;

        AnonymousClass1(SobotEvaluateActivity sobotEvaluateActivity) {
            JniLib.cV(this, sobotEvaluateActivity, Integer.valueOf((int) RILConstants.RIL_UNSOL_ON_SS));
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            this.a.finish();
        }
    }

    /* access modifiers changed from: protected */
    @Override // com.sobot.chat.activity.base.SobotBaseActivity
    public void c() {
        if (this.m == 302) {
            a.a(this.d).a().satisfactionMessage(this.a, this.k.getPartnerid(), new AnonymousClass2(this));
        }
    }

    /* renamed from: com.sobot.chat.widget.dialog.SobotEvaluateActivity$2  reason: invalid class name */
    class AnonymousClass2 implements ResultCallBack<SatisfactionSet> {
        final /* synthetic */ SobotEvaluateActivity a;

        AnonymousClass2(SobotEvaluateActivity sobotEvaluateActivity) {
            JniLib.cV(this, sobotEvaluateActivity, 1044);
        }

        @Override // com.sobot.chat.api.ResultCallBack
        public void onFailure(Exception exc, String str) {
        }

        @Override // com.sobot.chat.api.ResultCallBack
        public void onLoading(long j, long j2, boolean z) {
        }

        /* renamed from: a */
        public void onSuccess(SatisfactionSet satisfactionSet) {
            if (satisfactionSet != null && "1".equals(satisfactionSet.getCode()) && satisfactionSet.getData() != null && satisfactionSet.getData().size() != 0) {
                this.a.p = satisfactionSet.getData();
                if (!(this.a.n != 1 || this.a.p.get(0) == null || ((SatisfactionSetBase) this.a.p.get(0)).getDefaultType() == -1)) {
                    SobotEvaluateActivity sobotEvaluateActivity = this.a;
                    sobotEvaluateActivity.e = ((SatisfactionSetBase) sobotEvaluateActivity.p.get(0)).getDefaultType() == 0 ? 5 : 0;
                }
                if (this.a.e == -1) {
                    this.a.e = 5;
                }
                this.a.I.setRating((float) this.a.e);
                if (this.a.f == 0) {
                    this.a.w.setChecked(true);
                    this.a.x.setChecked(false);
                } else {
                    this.a.w.setChecked(false);
                    this.a.x.setChecked(true);
                }
                SobotEvaluateActivity sobotEvaluateActivity2 = this.a;
                sobotEvaluateActivity2.a(sobotEvaluateActivity2.e, this.a.p);
                if (this.a.e == 0) {
                    this.a.y.setVisibility(8);
                    this.a.F.setText(q.f(this.a.t(), "sobot_evaluate_zero_score_des"));
                    this.a.F.setTextColor(ContextCompat.getColor(this.a.t(), q.c(this.a.t(), "sobot_common_gray3")));
                } else {
                    this.a.y.setVisibility(0);
                    if (this.a.q != null) {
                        this.a.F.setText(this.a.q.getScoreExplain());
                    }
                    this.a.F.setTextColor(ContextCompat.getColor(this.a.t(), q.c(this.a.t(), "sobot_color_evaluate_ratingBar_des_tv")));
                }
                if (((SatisfactionSetBase) this.a.p.get(0)).getIsQuestionFlag()) {
                    this.a.s.setVisibility(0);
                    this.a.z.setVisibility(0);
                    return;
                }
                this.a.s.setVisibility(8);
                this.a.z.setVisibility(8);
            }
        }
    }

    private void d() {
        this.I.setOnRatingBarChangeListener(new AnonymousClass3(this));
        this.v.setOnCheckedChangeListener(new AnonymousClass4(this));
        this.y.setOnClickListener(new AnonymousClass5(this));
        this.G.setOnClickListener(new AnonymousClass6(this));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: com.sobot.chat.widget.dialog.SobotEvaluateActivity$3  reason: invalid class name */
    public class AnonymousClass3 implements RatingBar.OnRatingBarChangeListener {
        final /* synthetic */ SobotEvaluateActivity a;

        AnonymousClass3(SobotEvaluateActivity sobotEvaluateActivity) {
            JniLib.cV(this, sobotEvaluateActivity, Integer.valueOf((int) RILConstants.RIL_UNSOL_LCEDATA_RECV));
        }

        @Override // android.widget.RatingBar.OnRatingBarChangeListener
        public void onRatingChanged(RatingBar ratingBar, float f, boolean z) {
            this.a.y.setVisibility(0);
            int ceil = (int) Math.ceil((double) this.a.I.getRating());
            if (ceil == 0) {
                this.a.I.setRating(1.0f);
            }
            if (ceil > 0 && ceil <= 5) {
                this.a.y.setSelected(true);
                SobotEvaluateActivity sobotEvaluateActivity = this.a;
                sobotEvaluateActivity.a(ceil, sobotEvaluateActivity.p);
            }
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: com.sobot.chat.widget.dialog.SobotEvaluateActivity$4  reason: invalid class name */
    public class AnonymousClass4 implements RadioGroup.OnCheckedChangeListener {
        final /* synthetic */ SobotEvaluateActivity a;

        AnonymousClass4(SobotEvaluateActivity sobotEvaluateActivity) {
            JniLib.cV(this, sobotEvaluateActivity, Integer.valueOf((int) RILConstants.RIL_UNSOL_PCO_DATA));
        }

        @Override // android.widget.RadioGroup.OnCheckedChangeListener
        public void onCheckedChanged(RadioGroup radioGroup, int i) {
            if (this.a.m == 301 && this.a.k != null) {
                if (i == this.a.a("sobot_btn_ok_robot")) {
                    this.a.u.setVisibility(8);
                    this.a.T.setVisibility(8);
                } else if (i == this.a.a("sobot_btn_no_robot")) {
                    this.a.u.setVisibility(0);
                    this.a.T.setVisibility(0);
                    String[] h = SobotEvaluateActivity.h(this.a.k.getRobotCommentTitle());
                    if (h == null || h.length <= 0) {
                        this.a.u.setVisibility(8);
                    } else {
                        this.a.a(h);
                    }
                }
            }
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: com.sobot.chat.widget.dialog.SobotEvaluateActivity$5  reason: invalid class name */
    public class AnonymousClass5 implements View.OnClickListener {
        final /* synthetic */ SobotEvaluateActivity a;

        AnonymousClass5(SobotEvaluateActivity sobotEvaluateActivity) {
            JniLib.cV(this, sobotEvaluateActivity, 1047);
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            this.a.x();
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: com.sobot.chat.widget.dialog.SobotEvaluateActivity$6  reason: invalid class name */
    public class AnonymousClass6 implements View.OnClickListener {
        final /* synthetic */ SobotEvaluateActivity a;

        AnonymousClass6(SobotEvaluateActivity sobotEvaluateActivity) {
            JniLib.cV(this, sobotEvaluateActivity, 1048);
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            this.a.finish();
            Intent intent = new Intent();
            intent.setAction(ZhiChiConstants.sobot_close_now);
            intent.putExtra("isBackShowEvaluate", this.a.j);
            d.a(this.a.d.getApplicationContext(), intent);
        }
    }

    private void u() {
        this.u.setVisibility(8);
        this.T.setVisibility(8);
        this.K.setVisibility(8);
        this.L.setVisibility(8);
        this.M.setVisibility(8);
        this.N.setVisibility(8);
        this.O.setVisibility(8);
        this.P.setVisibility(8);
        this.Q.setVisibility(8);
        this.R.setVisibility(8);
        this.S.setVisibility(8);
        if (this.m == 301) {
            this.B.setText(e("sobot_robot_customer_service_evaluation"));
            TextView textView = this.C;
            textView.setText(this.k.getRobotName() + c.a(this.d, "sobot_question"));
            this.s.setVisibility(0);
            this.t.setVisibility(8);
            return;
        }
        if (!s.b((Context) this.d, "sobot_chat_evaluation_completed_exit", false) || this.i) {
            this.H.setVisibility(8);
        } else {
            this.H.setText(e("sobot_evaluation_completed_exit"));
            this.H.setVisibility(0);
        }
        this.B.setText(e("sobot_please_evaluate_this_service"));
        TextView textView2 = this.C;
        textView2.setText(this.o + WifiEnterpriseConfig.CA_CERT_ALIAS_DELIMITER + c.a(this.d, "sobot_question"));
        TextView textView3 = this.E;
        textView3.setText(this.o + WifiEnterpriseConfig.CA_CERT_ALIAS_DELIMITER + c.a(this.d, "sobot_please_evaluate"));
        this.s.setVisibility(8);
        this.t.setVisibility(0);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void a(int i, List<SatisfactionSetBase> list) {
        this.q = b(i, list);
        for (int i2 = 0; i2 < this.U.size(); i2++) {
            this.U.get(i2).setChecked(false);
        }
        SatisfactionSetBase satisfactionSetBase = this.q;
        if (satisfactionSetBase != null) {
            this.F.setText(satisfactionSetBase.getScoreExplain());
            this.F.setTextColor(ContextCompat.getColor(t(), q.c(t(), "sobot_color_evaluate_ratingBar_des_tv")));
            if (TextUtils.isEmpty(this.q.getInputLanguage())) {
                this.A.setHint(String.format(c.a(this.d, "sobot_edittext_hint"), new Object[0]));
            } else if (this.q.getIsInputMust()) {
                this.A.setHint(e("sobot_required") + this.q.getInputLanguage().replace("<br/>", "\n"));
            } else {
                this.A.setHint(this.q.getInputLanguage().replace("<br/>", "\n"));
            }
            if (!TextUtils.isEmpty(this.q.getLabelName())) {
                a(h(this.q.getLabelName()));
            } else {
                a((String[]) null);
            }
            if (!this.l.isHideManualEvaluationLabels()) {
                this.F.setVisibility(0);
            } else {
                this.F.setVisibility(8);
            }
            if (i == 5) {
                this.T.setVisibility(0);
                this.F.setText(this.q.getScoreExplain());
                return;
            }
            this.T.setVisibility(0);
        } else if (!this.l.isHideManualEvaluationLabels()) {
            this.F.setVisibility(0);
        } else {
            this.F.setVisibility(8);
        }
    }

    private SatisfactionSetBase b(int i, List<SatisfactionSetBase> list) {
        if (list == null) {
            return null;
        }
        for (int i2 = 0; i2 < list.size(); i2++) {
            String score = list.get(i2).getScore();
            if (score.equals(i + "")) {
                return list.get(i2);
            }
        }
        return null;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void a(String[] strArr) {
        SatisfactionSetBase satisfactionSetBase;
        if (strArr == null) {
            this.u.setVisibility(8);
            return;
        }
        if (this.m == 301 && this.k != null) {
            if (!this.l.isHideRototEvaluationLabels()) {
                this.u.setVisibility(0);
            } else {
                this.u.setVisibility(8);
            }
        }
        if (this.m == 302 && this.k != null) {
            if (!this.l.isHideManualEvaluationLabels()) {
                this.u.setVisibility(0);
            } else {
                this.u.setVisibility(8);
            }
        }
        if (this.m == 302 && (satisfactionSetBase = this.q) != null) {
            if (TextUtils.isEmpty(satisfactionSetBase.getTagTips())) {
                this.D.setVisibility(8);
            } else {
                this.D.setVisibility(0);
                if (this.q.getIsTagMust()) {
                    this.D.setText(this.q.getTagTips());
                } else {
                    this.D.setText(this.q.getTagTips());
                }
            }
        }
        switch (strArr.length) {
            case 1:
                this.N.setText(strArr[0]);
                this.N.setVisibility(0);
                this.O.setVisibility(4);
                this.K.setVisibility(0);
                this.L.setVisibility(8);
                this.M.setVisibility(8);
                a(strArr, 0, this.N);
                return;
            case 2:
                this.N.setText(strArr[0]);
                this.N.setVisibility(0);
                this.O.setText(strArr[1]);
                this.O.setVisibility(0);
                this.K.setVisibility(0);
                this.L.setVisibility(8);
                this.M.setVisibility(8);
                a(strArr, 0, this.N);
                a(strArr, 1, this.O);
                return;
            case 3:
                this.N.setText(strArr[0]);
                this.N.setVisibility(0);
                this.O.setText(strArr[1]);
                this.O.setVisibility(0);
                this.K.setVisibility(0);
                this.P.setText(strArr[2]);
                this.P.setVisibility(0);
                this.Q.setVisibility(4);
                this.L.setVisibility(0);
                this.M.setVisibility(8);
                a(strArr, 0, this.N);
                a(strArr, 1, this.O);
                a(strArr, 2, this.P);
                return;
            case 4:
                this.N.setText(strArr[0]);
                this.N.setVisibility(0);
                this.O.setText(strArr[1]);
                this.O.setVisibility(0);
                this.K.setVisibility(0);
                this.P.setText(strArr[2]);
                this.P.setVisibility(0);
                this.Q.setText(strArr[3]);
                this.Q.setVisibility(0);
                this.L.setVisibility(0);
                this.M.setVisibility(8);
                a(strArr, 0, this.N);
                a(strArr, 1, this.O);
                a(strArr, 2, this.P);
                a(strArr, 3, this.Q);
                return;
            case 5:
                this.N.setText(strArr[0]);
                this.N.setVisibility(0);
                this.O.setText(strArr[1]);
                this.O.setVisibility(0);
                this.K.setVisibility(0);
                this.P.setText(strArr[2]);
                this.P.setVisibility(0);
                this.Q.setText(strArr[3]);
                this.Q.setVisibility(0);
                this.L.setVisibility(0);
                this.R.setText(strArr[4]);
                this.R.setVisibility(0);
                this.S.setVisibility(4);
                this.M.setVisibility(0);
                a(strArr, 0, this.N);
                a(strArr, 1, this.O);
                a(strArr, 2, this.P);
                a(strArr, 3, this.Q);
                a(strArr, 4, this.R);
                return;
            case 6:
                this.N.setText(strArr[0]);
                this.N.setVisibility(0);
                this.O.setText(strArr[1]);
                this.O.setVisibility(0);
                this.K.setVisibility(0);
                this.P.setText(strArr[2]);
                this.P.setVisibility(0);
                this.Q.setText(strArr[3]);
                this.Q.setVisibility(0);
                this.L.setVisibility(0);
                this.R.setText(strArr[4]);
                this.R.setVisibility(0);
                this.S.setText(strArr[5]);
                this.S.setVisibility(0);
                this.M.setVisibility(0);
                a(strArr, 0, this.N);
                a(strArr, 1, this.O);
                a(strArr, 2, this.P);
                a(strArr, 3, this.Q);
                a(strArr, 4, this.R);
                a(strArr, 5, this.S);
                return;
            default:
                return;
        }
    }

    private int v() {
        SatisfactionSetBase satisfactionSetBase;
        int i = this.m;
        if (i == 301) {
            return this.w.isChecked() ? 0 : 1;
        }
        if (i != 302 || (satisfactionSetBase = this.q) == null || !satisfactionSetBase.getIsQuestionFlag()) {
            return -1;
        }
        return this.w.isChecked() ? 0 : 1;
    }

    private SobotCommentParam w() {
        SobotCommentParam sobotCommentParam = new SobotCommentParam();
        String str = this.m == 301 ? "0" : "1";
        int ceil = (int) Math.ceil((double) this.I.getRating());
        String A = A();
        String obj = this.A.getText().toString();
        sobotCommentParam.setType(str);
        sobotCommentParam.setProblem(A);
        sobotCommentParam.setSuggest(obj);
        sobotCommentParam.setIsresolve(v());
        sobotCommentParam.setCommentType(this.n);
        if (this.m == 301) {
            sobotCommentParam.setRobotFlag(this.k.getRobotid());
        } else {
            sobotCommentParam.setScore(ceil + "");
        }
        return sobotCommentParam;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void x() {
        if (y()) {
            z();
        }
    }

    private boolean y() {
        int i = this.m;
        if (i == 302) {
            if (this.q != null) {
                SobotCommentParam w = w();
                if (!TextUtils.isEmpty(this.q.getLabelName()) && this.q.getIsTagMust() && TextUtils.isEmpty(w.getProblem())) {
                    ae.a(this.d, e("sobot_the_label_is_required"));
                    return false;
                } else if (this.q.getIsInputMust() && TextUtils.isEmpty(w.getSuggest())) {
                    ae.a(this.d, e("sobot_suggestions_are_required"));
                    return false;
                }
            }
        } else if (i == 301) {
        }
        return true;
    }

    /* access modifiers changed from: private */
    public static String[] h(String str) {
        if (!TextUtils.isEmpty(str)) {
            return str.split(Constants.ACCEPT_TIME_SEPARATOR_SP);
        }
        return null;
    }

    private void z() {
        ZhiChiApi a = a.a(this.d).a();
        SobotCommentParam w = w();
        a.comment(this.a, this.k.getCid(), this.k.getPartnerid(), w, new AnonymousClass7(this, w));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: com.sobot.chat.widget.dialog.SobotEvaluateActivity$7  reason: invalid class name */
    public class AnonymousClass7 implements com.sobot.chat.core.http.c.a<CommonModel> {
        final /* synthetic */ SobotCommentParam a;
        final /* synthetic */ SobotEvaluateActivity b;

        AnonymousClass7(SobotEvaluateActivity sobotEvaluateActivity, SobotCommentParam sobotCommentParam) {
            JniLib.cV(this, sobotEvaluateActivity, sobotCommentParam, Integer.valueOf((int) RILConstants.RIL_UNSOL_NETWORK_SCAN_RESULT));
        }

        public void a(CommonModel commonModel) {
            Intent intent = new Intent();
            intent.setAction(ZhiChiConstants.dcrc_comment_state);
            intent.putExtra("commentState", true);
            intent.putExtra("isFinish", this.b.g);
            intent.putExtra("isExitSession", this.b.h);
            intent.putExtra("commentType", this.b.n);
            if (!TextUtils.isEmpty(this.a.getScore())) {
                intent.putExtra("score", Integer.parseInt(this.a.getScore()));
            }
            intent.putExtra("isResolved", this.a.getIsresolve());
            d.a(this.b.d, intent);
            this.b.finish();
        }

        @Override // com.sobot.chat.core.http.c.a
        public void a(Exception exc, String str) {
            try {
                ae.a(this.b.t(), str);
            } catch (Exception unused) {
            }
        }
    }

    private void a(String[] strArr, int i, CheckBox checkBox) {
        if (strArr != null && strArr.length > 0 && !TextUtils.isEmpty(this.J) && checkBox != null) {
            if (this.J.contains(strArr[i])) {
                checkBox.setChecked(true);
            } else {
                checkBox.setChecked(false);
            }
        }
    }

    private String A() {
        String str = "";
        for (int i = 0; i < this.U.size(); i++) {
            if (this.U.get(i).isChecked()) {
                str = str + ((Object) this.U.get(i).getText()) + Constants.ACCEPT_TIME_SEPARATOR_SP;
            }
        }
        if (str.length() > 0) {
            str = str.substring(0, str.length() - 1);
        }
        return str + "";
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public void onDetachedFromWindow() {
        com.sobot.chat.core.http.a.a().a(this.a);
        super.onDetachedFromWindow();
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        InputMethodManager inputMethodManager;
        if (motionEvent.getAction() == 0) {
            View currentFocus = getCurrentFocus();
            if (a(currentFocus, motionEvent) && (inputMethodManager = (InputMethodManager) t().getSystemService(Context.INPUT_METHOD_SERVICE)) != null) {
                inputMethodManager.hideSoftInputFromWindow(currentFocus.getWindowToken(), 0);
            }
            return super.dispatchTouchEvent(motionEvent);
        } else if (getWindow().superDispatchTouchEvent(motionEvent)) {
            return true;
        } else {
            return onTouchEvent(motionEvent);
        }
    }

    public boolean a(View view, MotionEvent motionEvent) {
        if (view == null || !(view instanceof EditText)) {
            return false;
        }
        int[] iArr = {0, 0};
        view.getLocationInWindow(iArr);
        int i = iArr[0];
        int i2 = iArr[1];
        int height = view.getHeight() + i2;
        int width = view.getWidth() + i;
        if (motionEvent.getX() <= ((float) i) || motionEvent.getX() >= ((float) width) || motionEvent.getY() <= ((float) i2) || motionEvent.getY() >= ((float) height)) {
            return true;
        }
        return false;
    }
}
