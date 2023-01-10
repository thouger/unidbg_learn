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
import com.sobot.chat.api.ResultCallBack;
import com.sobot.chat.api.ZhiChiApi;
import com.sobot.chat.api.apiUtils.ZhiChiConstants;
import com.sobot.chat.api.model.CommonModel;
import com.sobot.chat.api.model.Information;
import com.sobot.chat.api.model.SatisfactionSet;
import com.sobot.chat.api.model.SatisfactionSetBase;
import com.sobot.chat.api.model.SobotCommentParam;
import com.sobot.chat.api.model.ZhiChiInitModeBase;
import com.sobot.chat.utils.ae;
import com.sobot.chat.utils.c;
import com.sobot.chat.utils.d;
import com.sobot.chat.utils.q;
import com.sobot.chat.utils.r;
import com.sobot.chat.utils.s;
import com.sobot.chat.widget.SobotEditTextLayout;
import com.sobot.chat.widget.dialog.a.a;
import com.xiaomi.mipush.sdk.Constants;
import java.util.ArrayList;
import java.util.List;

/* compiled from: SobotEvaluateDialog */
public class e extends a {
    private View A;
    private EditText B;
    private TextView C;
    private TextView D;
    private TextView E;
    private TextView F;
    private TextView G;
    private TextView H;
    private TextView I;
    private RatingBar J;
    private String K;
    private LinearLayout L;
    private LinearLayout M;
    private LinearLayout N;
    private CheckBox O;
    private CheckBox P;
    private CheckBox Q;
    private CheckBox R;
    private CheckBox S;
    private CheckBox T;
    private SobotEditTextLayout U;
    private List<CheckBox> V = new ArrayList();
    private final String b = e.class.getSimpleName();
    private Activity c;
    private int d;
    private int e;
    private boolean f;
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
    private LinearLayout v;
    private RadioGroup w;
    private RadioButton x;
    private RadioButton y;
    private Button z;

    /* access modifiers changed from: protected */
    @Override // com.sobot.chat.widget.dialog.a.a
    public String a() {
        return "sobot_layout_evaluate";
    }

    public e(Activity activity, boolean z, boolean z2, boolean z3, ZhiChiInitModeBase zhiChiInitModeBase, int i, int i2, String str, int i3, int i4, String str2, boolean z4, boolean z5) {
        super(activity);
        this.c = activity;
        this.d = i3;
        this.h = z;
        this.f = z2;
        this.g = z3;
        this.k = zhiChiInitModeBase;
        this.m = i;
        this.n = i2;
        this.o = str;
        this.e = i4;
        this.K = str2;
        this.j = z4;
        this.i = z5;
    }

    public e(Activity activity, boolean z, boolean z2, boolean z3, ZhiChiInitModeBase zhiChiInitModeBase, int i, int i2, String str, int i3, int i4, String str2, boolean z4, boolean z5, int i5) {
        super(activity, i5);
        this.c = activity;
        this.d = i3;
        this.h = z;
        this.f = z2;
        this.g = z3;
        this.k = zhiChiInitModeBase;
        this.m = i;
        this.n = i2;
        this.o = str;
        this.e = i4;
        this.K = str2;
        this.j = z4;
        this.i = z5;
    }

    /* access modifiers changed from: protected */
    @Override // com.sobot.chat.widget.dialog.a.a
    public View b() {
        if (this.s == null) {
            this.s = (LinearLayout) findViewById(b("sobot_evaluate_container"));
        }
        return this.s;
    }

    /* access modifiers changed from: protected */
    @Override // com.sobot.chat.widget.dialog.a.a
    public void c() {
        this.l = (Information) s.d(getContext(), "sobot_last_current_info");
        this.z = (Button) findViewById(b(ZhiChiConstants.sobot_close_now));
        this.z.setText(q.f(this.c, "sobot_btn_submit_text"));
        this.w = (RadioGroup) findViewById(b("sobot_readiogroup"));
        this.C = (TextView) findViewById(b("sobot_tv_evaluate_title"));
        this.C.setText(q.f(this.c, "sobot_robot_customer_service_evaluation"));
        this.D = (TextView) findViewById(b("sobot_robot_center_title"));
        this.D.setText(q.f(this.c, "sobot_question"));
        this.E = (TextView) findViewById(b("sobot_text_other_problem"));
        this.F = (TextView) findViewById(b("sobot_custom_center_title"));
        this.F.setText(q.f(this.c, "sobot_please_evaluate"));
        this.G = (TextView) findViewById(b("sobot_ratingBar_title"));
        this.G.setText(q.f(this.c, "sobot_great_satisfaction"));
        this.I = (TextView) findViewById(b("sobot_tv_evaluate_title_hint"));
        this.H = (TextView) findViewById(b("sobot_evaluate_cancel"));
        this.H.setText(q.f(this.c, "sobot_temporarily_not_evaluation"));
        this.A = findViewById(q.a(this.c, "id", "sobot_ratingBar_split_view"));
        this.r = (LinearLayout) findViewById(b("sobot_negativeButton"));
        this.r.setOnClickListener(new SobotEvaluateDialog$1(this));
        Information information = this.l;
        if (information == null || !information.isCanBackWithNotEvaluation()) {
            this.H.setVisibility(8);
        } else if (this.i) {
            this.H.setVisibility(0);
        } else {
            this.H.setVisibility(8);
        }
        this.J = (RatingBar) findViewById(b("sobot_ratingBar"));
        this.L = (LinearLayout) findViewById(b("sobot_evaluate_ll_lable1"));
        this.M = (LinearLayout) findViewById(b("sobot_evaluate_ll_lable2"));
        this.N = (LinearLayout) findViewById(b("sobot_evaluate_ll_lable3"));
        this.O = (CheckBox) findViewById(b("sobot_evaluate_cb_lable1"));
        this.P = (CheckBox) findViewById(b("sobot_evaluate_cb_lable2"));
        this.Q = (CheckBox) findViewById(b("sobot_evaluate_cb_lable3"));
        this.R = (CheckBox) findViewById(b("sobot_evaluate_cb_lable4"));
        this.S = (CheckBox) findViewById(b("sobot_evaluate_cb_lable5"));
        this.T = (CheckBox) findViewById(b("sobot_evaluate_cb_lable6"));
        this.V.add(this.O);
        this.V.add(this.P);
        this.V.add(this.Q);
        this.V.add(this.R);
        this.V.add(this.S);
        this.V.add(this.T);
        this.B = (EditText) findViewById(b("sobot_add_content"));
        this.B.setHint(q.f(this.c, "sobot_edittext_hint"));
        this.x = (RadioButton) findViewById(b("sobot_btn_ok_robot"));
        this.x.setText(q.f(this.c, "sobot_evaluate_yes"));
        this.x.setChecked(true);
        this.y = (RadioButton) findViewById(b("sobot_btn_no_robot"));
        this.y.setText(q.f(this.c, "sobot_evaluate_no"));
        this.t = (LinearLayout) findViewById(b("sobot_robot_relative"));
        this.u = (LinearLayout) findViewById(b("sobot_custom_relative"));
        this.v = (LinearLayout) findViewById(b("sobot_hide_layout"));
        this.U = (SobotEditTextLayout) findViewById(b("setl_submit_content"));
        f();
        e();
        if (r.c(this.c)) {
            getWindow().setLayout(-1, -1);
        }
    }

    /* access modifiers changed from: protected */
    @Override // com.sobot.chat.widget.dialog.a.a
    public void d() {
        if (this.m == 302) {
            com.sobot.chat.core.channel.a.a(this.c).a().satisfactionMessage(this.b, this.k.getPartnerid(), new AnonymousClass1());
        }
    }

    /* compiled from: SobotEvaluateDialog */
    /* renamed from: com.sobot.chat.widget.dialog.e$1  reason: invalid class name */
    class AnonymousClass1 implements ResultCallBack<SatisfactionSet> {
        @Override // com.sobot.chat.api.ResultCallBack
        public void onFailure(Exception exc, String str) {
        }

        @Override // com.sobot.chat.api.ResultCallBack
        public void onLoading(long j, long j2, boolean z) {
        }

        AnonymousClass1() {
        }

        /* renamed from: a */
        public void onSuccess(SatisfactionSet satisfactionSet) {
            if (satisfactionSet != null && "1".equals(satisfactionSet.getCode()) && satisfactionSet.getData() != null && satisfactionSet.getData().size() != 0) {
                e.this.p = satisfactionSet.getData();
                if (!(e.this.n != 1 || e.this.p.get(0) == null || ((SatisfactionSetBase) e.this.p.get(0)).getDefaultType() == -1)) {
                    e eVar = e.this;
                    eVar.d = ((SatisfactionSetBase) eVar.p.get(0)).getDefaultType() == 0 ? 5 : 0;
                }
                if (e.this.d == -1) {
                    e.this.d = 5;
                }
                e.this.J.setRating((float) e.this.d);
                if (e.this.e == 0) {
                    e.this.x.setChecked(true);
                    e.this.y.setChecked(false);
                } else {
                    e.this.x.setChecked(false);
                    e.this.y.setChecked(true);
                }
                e eVar2 = e.this;
                eVar2.a(eVar2.d, e.this.p);
                if (e.this.d == 0) {
                    e.this.z.setVisibility(8);
                    e.this.G.setText(q.f(e.this.getContext(), "sobot_evaluate_zero_score_des"));
                    e.this.G.setTextColor(ContextCompat.getColor(e.this.getContext(), q.c(e.this.getContext(), "sobot_common_gray3")));
                } else {
                    e.this.z.setVisibility(0);
                    if (e.this.q != null) {
                        e.this.G.setText(e.this.q.getScoreExplain());
                    }
                    e.this.G.setTextColor(ContextCompat.getColor(e.this.getContext(), q.c(e.this.getContext(), "sobot_color_evaluate_ratingBar_des_tv")));
                }
                if (((SatisfactionSetBase) e.this.p.get(0)).getIsQuestionFlag()) {
                    e.this.t.setVisibility(0);
                    e.this.A.setVisibility(0);
                    return;
                }
                e.this.t.setVisibility(8);
                e.this.A.setVisibility(8);
            }
        }
    }

    /* compiled from: SobotEvaluateDialog */
    /* access modifiers changed from: package-private */
    /* renamed from: com.sobot.chat.widget.dialog.e$2  reason: invalid class name */
    public class AnonymousClass2 implements RatingBar.OnRatingBarChangeListener {
        AnonymousClass2() {
        }

        @Override // android.widget.RatingBar.OnRatingBarChangeListener
        public void onRatingChanged(RatingBar ratingBar, float f, boolean z) {
            e.this.z.setVisibility(0);
            int ceil = (int) Math.ceil((double) e.this.J.getRating());
            if (ceil == 0) {
                e.this.J.setRating(1.0f);
            }
            if (ceil > 0 && ceil <= 5) {
                e.this.z.setSelected(true);
                e eVar = e.this;
                eVar.a(ceil, eVar.p);
            }
        }
    }

    private void e() {
        this.J.setOnRatingBarChangeListener(new AnonymousClass2());
        this.w.setOnCheckedChangeListener(new AnonymousClass3());
        this.z.setOnClickListener(new SobotEvaluateDialog$5(this));
        this.H.setOnClickListener(new SobotEvaluateDialog$6(this));
    }

    /* compiled from: SobotEvaluateDialog */
    /* access modifiers changed from: package-private */
    /* renamed from: com.sobot.chat.widget.dialog.e$3  reason: invalid class name */
    public class AnonymousClass3 implements RadioGroup.OnCheckedChangeListener {
        AnonymousClass3() {
        }

        @Override // android.widget.RadioGroup.OnCheckedChangeListener
        public void onCheckedChanged(RadioGroup radioGroup, int i) {
            if (e.this.m == 301 && e.this.k != null) {
                if (i == e.this.b("sobot_btn_ok_robot")) {
                    e.this.v.setVisibility(8);
                    e.this.U.setVisibility(8);
                } else if (i == e.this.b("sobot_btn_no_robot")) {
                    e.this.v.setVisibility(0);
                    e.this.U.setVisibility(0);
                    String[] d = e.d(e.this.k.getRobotCommentTitle());
                    if (d == null || d.length <= 0) {
                        e.this.v.setVisibility(8);
                    } else {
                        e.this.a(d);
                    }
                }
            }
        }
    }

    @Override // com.sobot.chat.widget.dialog.a.a, android.app.Dialog, android.content.DialogInterface
    public void dismiss() {
        try {
            if (isShowing()) {
                super.dismiss();
            }
        } catch (Exception unused) {
        }
    }

    private void f() {
        this.v.setVisibility(8);
        this.U.setVisibility(8);
        this.L.setVisibility(8);
        this.M.setVisibility(8);
        this.N.setVisibility(8);
        this.O.setVisibility(8);
        this.P.setVisibility(8);
        this.Q.setVisibility(8);
        this.R.setVisibility(8);
        this.S.setVisibility(8);
        this.T.setVisibility(8);
        if (this.m == 301) {
            this.C.setText(c("sobot_robot_customer_service_evaluation"));
            TextView textView = this.D;
            textView.setText(this.k.getRobotName() + "" + c.a(this.c, "sobot_question"));
            this.t.setVisibility(0);
            this.u.setVisibility(8);
            return;
        }
        if (!s.b((Context) this.c, "sobot_chat_evaluation_completed_exit", false) || this.h) {
            this.I.setVisibility(8);
        } else {
            this.I.setText(c("sobot_evaluation_completed_exit"));
            this.I.setVisibility(0);
        }
        this.C.setText(c("sobot_please_evaluate_this_service"));
        TextView textView2 = this.D;
        textView2.setText(this.o + WifiEnterpriseConfig.CA_CERT_ALIAS_DELIMITER + c.a(this.c, "sobot_question"));
        TextView textView3 = this.F;
        textView3.setText(this.o + WifiEnterpriseConfig.CA_CERT_ALIAS_DELIMITER + c.a(this.c, "sobot_please_evaluate"));
        this.t.setVisibility(8);
        this.u.setVisibility(0);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void a(int i, List<SatisfactionSetBase> list) {
        this.q = b(i, list);
        for (int i2 = 0; i2 < this.V.size(); i2++) {
            this.V.get(i2).setChecked(false);
        }
        SatisfactionSetBase satisfactionSetBase = this.q;
        if (satisfactionSetBase != null) {
            this.G.setText(satisfactionSetBase.getScoreExplain());
            this.G.setTextColor(ContextCompat.getColor(getContext(), q.c(getContext(), "sobot_color_evaluate_ratingBar_des_tv")));
            if (TextUtils.isEmpty(this.q.getInputLanguage())) {
                this.B.setHint(String.format(c.a(this.c, "sobot_edittext_hint"), new Object[0]));
            } else if (this.q.getIsInputMust()) {
                this.B.setHint(c("sobot_required") + this.q.getInputLanguage().replace("<br/>", "\n"));
            } else {
                this.B.setHint(this.q.getInputLanguage().replace("<br/>", "\n"));
            }
            if (!TextUtils.isEmpty(this.q.getLabelName())) {
                a(d(this.q.getLabelName()));
            } else {
                a((String[]) null);
            }
            if (!this.l.isHideManualEvaluationLabels()) {
                this.G.setVisibility(0);
            } else {
                this.G.setVisibility(8);
            }
            if (i == 5) {
                this.U.setVisibility(0);
                this.G.setText(this.q.getScoreExplain());
                return;
            }
            this.U.setVisibility(0);
        } else if (!this.l.isHideManualEvaluationLabels()) {
            this.G.setVisibility(0);
        } else {
            this.G.setVisibility(8);
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
            this.v.setVisibility(8);
            return;
        }
        if (this.m == 301 && this.k != null) {
            if (!this.l.isHideRototEvaluationLabels()) {
                this.v.setVisibility(0);
            } else {
                this.v.setVisibility(8);
            }
        }
        if (this.m == 302 && this.k != null) {
            if (!this.l.isHideManualEvaluationLabels()) {
                this.v.setVisibility(0);
            } else {
                this.v.setVisibility(8);
            }
        }
        if (this.m == 302 && (satisfactionSetBase = this.q) != null) {
            if (TextUtils.isEmpty(satisfactionSetBase.getTagTips())) {
                this.E.setVisibility(8);
            } else {
                this.E.setVisibility(0);
                if (this.q.getIsTagMust()) {
                    this.E.setText(this.q.getTagTips());
                } else {
                    this.E.setText(this.q.getTagTips());
                }
            }
        }
        switch (strArr.length) {
            case 1:
                this.O.setText(strArr[0]);
                this.O.setVisibility(0);
                this.P.setVisibility(4);
                this.L.setVisibility(0);
                this.M.setVisibility(8);
                this.N.setVisibility(8);
                a(strArr, 0, this.O);
                return;
            case 2:
                this.O.setText(strArr[0]);
                this.O.setVisibility(0);
                this.P.setText(strArr[1]);
                this.P.setVisibility(0);
                this.L.setVisibility(0);
                this.M.setVisibility(8);
                this.N.setVisibility(8);
                a(strArr, 0, this.O);
                a(strArr, 1, this.P);
                return;
            case 3:
                this.O.setText(strArr[0]);
                this.O.setVisibility(0);
                this.P.setText(strArr[1]);
                this.P.setVisibility(0);
                this.L.setVisibility(0);
                this.Q.setText(strArr[2]);
                this.Q.setVisibility(0);
                this.R.setVisibility(4);
                this.M.setVisibility(0);
                this.N.setVisibility(8);
                a(strArr, 0, this.O);
                a(strArr, 1, this.P);
                a(strArr, 2, this.Q);
                return;
            case 4:
                this.O.setText(strArr[0]);
                this.O.setVisibility(0);
                this.P.setText(strArr[1]);
                this.P.setVisibility(0);
                this.L.setVisibility(0);
                this.Q.setText(strArr[2]);
                this.Q.setVisibility(0);
                this.R.setText(strArr[3]);
                this.R.setVisibility(0);
                this.M.setVisibility(0);
                this.N.setVisibility(8);
                a(strArr, 0, this.O);
                a(strArr, 1, this.P);
                a(strArr, 2, this.Q);
                a(strArr, 3, this.R);
                return;
            case 5:
                this.O.setText(strArr[0]);
                this.O.setVisibility(0);
                this.P.setText(strArr[1]);
                this.P.setVisibility(0);
                this.L.setVisibility(0);
                this.Q.setText(strArr[2]);
                this.Q.setVisibility(0);
                this.R.setText(strArr[3]);
                this.R.setVisibility(0);
                this.M.setVisibility(0);
                this.S.setText(strArr[4]);
                this.S.setVisibility(0);
                this.T.setVisibility(4);
                this.N.setVisibility(0);
                a(strArr, 0, this.O);
                a(strArr, 1, this.P);
                a(strArr, 2, this.Q);
                a(strArr, 3, this.R);
                a(strArr, 4, this.S);
                return;
            case 6:
                this.O.setText(strArr[0]);
                this.O.setVisibility(0);
                this.P.setText(strArr[1]);
                this.P.setVisibility(0);
                this.L.setVisibility(0);
                this.Q.setText(strArr[2]);
                this.Q.setVisibility(0);
                this.R.setText(strArr[3]);
                this.R.setVisibility(0);
                this.M.setVisibility(0);
                this.S.setText(strArr[4]);
                this.S.setVisibility(0);
                this.T.setText(strArr[5]);
                this.T.setVisibility(0);
                this.N.setVisibility(0);
                a(strArr, 0, this.O);
                a(strArr, 1, this.P);
                a(strArr, 2, this.Q);
                a(strArr, 3, this.R);
                a(strArr, 4, this.S);
                a(strArr, 5, this.T);
                return;
            default:
                return;
        }
    }

    private void a(String[] strArr, int i, CheckBox checkBox) {
        if (strArr != null && strArr.length > 0 && !TextUtils.isEmpty(this.K) && checkBox != null) {
            if (this.K.contains(strArr[i])) {
                checkBox.setChecked(true);
            } else {
                checkBox.setChecked(false);
            }
        }
    }

    private int g() {
        SatisfactionSetBase satisfactionSetBase;
        int i = this.m;
        if (i == 301) {
            return this.x.isChecked() ? 0 : 1;
        }
        if (i != 302 || (satisfactionSetBase = this.q) == null || !satisfactionSetBase.getIsQuestionFlag()) {
            return -1;
        }
        return this.x.isChecked() ? 0 : 1;
    }

    private SobotCommentParam h() {
        SobotCommentParam sobotCommentParam = new SobotCommentParam();
        String str = this.m == 301 ? "0" : "1";
        int ceil = (int) Math.ceil((double) this.J.getRating());
        String l = l();
        String obj = this.B.getText().toString();
        sobotCommentParam.setType(str);
        sobotCommentParam.setProblem(l);
        sobotCommentParam.setSuggest(obj);
        sobotCommentParam.setIsresolve(g());
        sobotCommentParam.setCommentType(this.n);
        if (this.m == 301) {
            sobotCommentParam.setRobotFlag(this.k.getRobotid());
        } else {
            sobotCommentParam.setScore(ceil + "");
        }
        return sobotCommentParam;
    }

    /* access modifiers changed from: private */
    public void i() {
        if (j()) {
            k();
        }
    }

    private boolean j() {
        int i = this.m;
        if (i == 302) {
            if (this.q != null) {
                SobotCommentParam h = h();
                if (!TextUtils.isEmpty(this.q.getLabelName()) && this.q.getIsTagMust() && TextUtils.isEmpty(h.getProblem()) && !this.l.isHideManualEvaluationLabels()) {
                    ae.a(this.c, c("sobot_the_label_is_required"));
                    return false;
                } else if (this.q.getIsInputMust() && TextUtils.isEmpty(h.getSuggest())) {
                    ae.a(this.c, c("sobot_suggestions_are_required"));
                    return false;
                }
            }
        } else if (i == 301) {
        }
        return true;
    }

    /* access modifiers changed from: private */
    public static String[] d(String str) {
        if (!TextUtils.isEmpty(str)) {
            return str.split(Constants.ACCEPT_TIME_SEPARATOR_SP);
        }
        return null;
    }

    private void k() {
        ZhiChiApi a = com.sobot.chat.core.channel.a.a(this.c).a();
        SobotCommentParam h = h();
        a.comment(this.b, this.k.getCid(), this.k.getPartnerid(), h, new AnonymousClass4(h));
    }

    /* compiled from: SobotEvaluateDialog */
    /* access modifiers changed from: package-private */
    /* renamed from: com.sobot.chat.widget.dialog.e$4  reason: invalid class name */
    public class AnonymousClass4 implements com.sobot.chat.core.http.c.a<CommonModel> {
        final /* synthetic */ SobotCommentParam a;

        AnonymousClass4(SobotCommentParam sobotCommentParam) {
            this.a = sobotCommentParam;
        }

        public void a(CommonModel commonModel) {
            Intent intent = new Intent();
            intent.setAction(ZhiChiConstants.dcrc_comment_state);
            intent.putExtra("commentState", true);
            intent.putExtra("isFinish", e.this.f);
            intent.putExtra("isExitSession", e.this.g);
            intent.putExtra("commentType", e.this.n);
            if (!TextUtils.isEmpty(this.a.getScore())) {
                intent.putExtra("score", Integer.parseInt(this.a.getScore()));
            }
            intent.putExtra("isResolved", this.a.getIsresolve());
            d.a(e.this.c, intent);
            e.this.dismiss();
        }

        @Override // com.sobot.chat.core.http.c.a
        public void a(Exception exc, String str) {
            try {
                ae.a(e.this.getContext(), str);
            } catch (Exception unused) {
            }
        }
    }

    private String l() {
        String str = "";
        for (int i = 0; i < this.V.size(); i++) {
            if (this.V.get(i).isChecked()) {
                str = str + ((Object) this.V.get(i).getText()) + Constants.ACCEPT_TIME_SEPARATOR_SP;
            }
        }
        if (str.length() > 0) {
            str = str.substring(0, str.length() - 1);
        }
        return str + "";
    }

    @Override // android.app.Dialog, android.view.Window.Callback
    public void onDetachedFromWindow() {
        com.sobot.chat.core.http.a.a().a(this.b);
        super.onDetachedFromWindow();
    }

    @Override // android.app.Dialog, android.view.Window.Callback
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        InputMethodManager inputMethodManager;
        if (motionEvent.getAction() == 0) {
            View currentFocus = getCurrentFocus();
            if (a(currentFocus, motionEvent) && (inputMethodManager = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE)) != null) {
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
