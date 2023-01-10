package com.sobot.chat.viewHolder;

import android.content.Context;
import android.net.wifi.WifiEnterpriseConfig;
import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.TextView;
import androidx.core.content.ContextCompat;
import com.sobot.chat.api.ResultCallBack;
import com.sobot.chat.api.model.Information;
import com.sobot.chat.api.model.SatisfactionSet;
import com.sobot.chat.api.model.SatisfactionSetBase;
import com.sobot.chat.api.model.SobotEvaluateModel;
import com.sobot.chat.api.model.ZhiChiInitModeBase;
import com.sobot.chat.api.model.ZhiChiMessageBase;
import com.sobot.chat.utils.c;
import com.sobot.chat.utils.m;
import com.sobot.chat.utils.q;
import com.sobot.chat.utils.s;
import com.sobot.chat.viewHolder.base.MessageHolderBase;
import com.xiaomi.mipush.sdk.Constants;
import java.util.ArrayList;
import java.util.List;

/* compiled from: CusEvaluateMessageHolder */
public class a extends MessageHolderBase implements RadioGroup.OnCheckedChangeListener, RatingBar.OnRatingBarChangeListener {
    private LinearLayout F;
    private LinearLayout G;
    private LinearLayout H;
    private CheckBox I;
    private CheckBox J;
    private CheckBox K;
    private CheckBox L;
    private CheckBox M;
    private CheckBox N;
    private LinearLayout O;
    private List<CheckBox> P = new ArrayList();
    private List<SatisfactionSetBase> Q;
    private int R = 5;
    TextView a;
    RadioGroup b;
    RadioButton c;
    RadioButton d;
    TextView e;
    RatingBar f;
    TextView g;
    TextView h;
    View i;
    Information j;
    SobotEvaluateModel k;
    public ZhiChiMessageBase l;

    public a(Context context, View view) {
        super(context, view);
        this.a = (TextView) view.findViewById(q.a(context, "id", "sobot_center_title"));
        this.b = (RadioGroup) view.findViewById(q.a(context, "id", "sobot_readiogroup"));
        this.c = (RadioButton) view.findViewById(q.a(context, "id", "sobot_btn_ok_robot"));
        this.c.setText(q.f(context, "sobot_evaluate_yes"));
        this.d = (RadioButton) view.findViewById(q.a(context, "id", "sobot_btn_no_robot"));
        this.d.setText(q.f(context, "sobot_evaluate_no"));
        this.e = (TextView) view.findViewById(q.a(context, "id", "sobot_tv_star_title"));
        this.e.setText(q.f(context, "sobot_please_evaluate"));
        this.f = (RatingBar) view.findViewById(q.a(context, "id", "sobot_ratingBar"));
        this.h = (TextView) view.findViewById(q.a(context, "id", "sobot_submit"));
        this.h.setText(q.f(context, "sobot_btn_submit_text"));
        this.i = view.findViewById(q.a(context, "id", "sobot_ratingBar_split_view"));
        this.c.setSelected(true);
        this.g = (TextView) view.findViewById(q.a(context, "id", "sobot_ratingBar_title"));
        this.g.setText(q.f(context, "sobot_great_satisfaction"));
        this.O = (LinearLayout) view.findViewById(q.a(context, "id", "sobot_hide_layout"));
        this.F = (LinearLayout) view.findViewById(q.a(context, "id", "sobot_evaluate_ll_lable1"));
        this.G = (LinearLayout) view.findViewById(q.a(context, "id", "sobot_evaluate_ll_lable2"));
        this.H = (LinearLayout) view.findViewById(q.a(context, "id", "sobot_evaluate_ll_lable3"));
        this.I = (CheckBox) view.findViewById(q.a(context, "id", "sobot_evaluate_cb_lable1"));
        this.J = (CheckBox) view.findViewById(q.a(context, "id", "sobot_evaluate_cb_lable2"));
        this.K = (CheckBox) view.findViewById(q.a(context, "id", "sobot_evaluate_cb_lable3"));
        this.L = (CheckBox) view.findViewById(q.a(context, "id", "sobot_evaluate_cb_lable4"));
        this.M = (CheckBox) view.findViewById(q.a(context, "id", "sobot_evaluate_cb_lable5"));
        this.N = (CheckBox) view.findViewById(q.a(context, "id", "sobot_evaluate_cb_lable6"));
        this.P.add(this.I);
        this.P.add(this.J);
        this.P.add(this.K);
        this.P.add(this.L);
        this.P.add(this.M);
        this.P.add(this.N);
    }

    @Override // com.sobot.chat.viewHolder.base.MessageHolderBase
    public void a(Context context, ZhiChiMessageBase zhiChiMessageBase) {
        this.j = (Information) s.d(context, "sobot_last_current_info");
        if (!this.j.isHideManualEvaluationLabels()) {
            this.g.setVisibility(0);
        } else {
            this.g.setVisibility(8);
        }
        this.h.setVisibility(8);
        this.l = zhiChiMessageBase;
        this.k = zhiChiMessageBase.getSobotEvaluateModel();
        List<SatisfactionSetBase> list = this.Q;
        if (list == null || list.size() == 0) {
            com.sobot.chat.core.channel.a.a(context).a().satisfactionMessage(this, ((ZhiChiInitModeBase) s.d(context, "sobot_last_current_initModel")).getPartnerid(), new AnonymousClass1(context));
        }
        TextView textView = this.a;
        textView.setText(zhiChiMessageBase.getSenderName() + WifiEnterpriseConfig.CA_CERT_ALIAS_DELIMITER + c.a(context, "sobot_question"));
        TextView textView2 = this.e;
        textView2.setText(zhiChiMessageBase.getSenderName() + WifiEnterpriseConfig.CA_CERT_ALIAS_DELIMITER + c.a(context, "sobot_please_evaluate"));
        b();
        a();
        this.b.setOnCheckedChangeListener(this);
        this.f.setOnRatingBarChangeListener(this);
        this.h.setOnClickListener(new CusEvaluateMessageHolder$2(this));
    }

    /* compiled from: CusEvaluateMessageHolder */
    /* renamed from: com.sobot.chat.viewHolder.a$1  reason: invalid class name */
    class AnonymousClass1 implements ResultCallBack<SatisfactionSet> {
        final /* synthetic */ Context a;

        @Override // com.sobot.chat.api.ResultCallBack
        public void onLoading(long j, long j2, boolean z) {
        }

        AnonymousClass1(Context context) {
            this.a = context;
        }

        /* renamed from: a */
        public void onSuccess(SatisfactionSet satisfactionSet) {
            a.this.h.setVisibility(0);
            if (satisfactionSet != null && "1".equals(satisfactionSet.getCode()) && satisfactionSet.getData() != null && satisfactionSet.getData().size() != 0) {
                a.this.Q = satisfactionSet.getData();
                int i = 5;
                if (!(a.this.Q.get(0) == null || ((SatisfactionSetBase) a.this.Q.get(0)).getDefaultType() == -1)) {
                    if (((SatisfactionSetBase) a.this.Q.get(0)).getDefaultType() != 0) {
                        i = 0;
                    }
                    a.this.R = i;
                }
                a.this.k.setScore(a.this.R);
                a.this.f.setRating((float) a.this.R);
                if (i == 0) {
                    a.this.O.setVisibility(8);
                    a.this.h.setVisibility(8);
                    a.this.g.setText(q.f(this.a, "sobot_evaluate_zero_score_des"));
                    TextView textView = a.this.g;
                    Context context = this.a;
                    textView.setTextColor(ContextCompat.getColor(context, q.c(context, "sobot_common_gray3")));
                } else {
                    if (!a.this.j.isHideManualEvaluationLabels()) {
                        a.this.O.setVisibility(0);
                    } else {
                        a.this.O.setVisibility(8);
                    }
                    a.this.h.setVisibility(0);
                    a.this.g.setText(((SatisfactionSetBase) a.this.Q.get(4)).getScoreExplain());
                    TextView textView2 = a.this.g;
                    Context context2 = this.a;
                    textView2.setTextColor(ContextCompat.getColor(context2, q.c(context2, "sobot_color_evaluate_ratingBar_des_tv")));
                }
                a aVar = a.this;
                SatisfactionSetBase a = aVar.a(i, aVar.Q);
                if (a == null || TextUtils.isEmpty(a.getLabelName())) {
                    a.this.a((String[]) null);
                    return;
                }
                a.this.a(a.b(a.getLabelName()));
            }
        }

        @Override // com.sobot.chat.api.ResultCallBack
        public void onFailure(Exception exc, String str) {
            a.this.h.setVisibility(8);
        }
    }

    private void b() {
        SobotEvaluateModel sobotEvaluateModel = this.k;
        if (sobotEvaluateModel != null) {
            if (c.a(sobotEvaluateModel)) {
                this.a.setVisibility(0);
                this.b.setVisibility(0);
                this.i.setVisibility(0);
                return;
            }
            this.a.setVisibility(8);
            this.b.setVisibility(8);
            this.i.setVisibility(8);
        }
    }

    public void a() {
        SobotEvaluateModel sobotEvaluateModel = this.k;
        if (sobotEvaluateModel != null) {
            if (sobotEvaluateModel.getEvaluateStatus() == 0) {
                d();
                if (this.Q != null) {
                    this.h.setVisibility(0);
                }
            } else if (1 == this.k.getEvaluateStatus()) {
                c();
                this.h.setVisibility(8);
            }
        }
    }

    private void c() {
        if (this.b.getVisibility() == 0) {
            if (this.k.getIsResolved() == -1) {
                this.c.setChecked(false);
                this.d.setChecked(false);
                this.c.setVisibility(0);
                this.d.setVisibility(0);
            } else if (this.k.getIsResolved() == 0) {
                this.c.setChecked(true);
                this.d.setChecked(false);
                this.c.setVisibility(0);
                this.d.setVisibility(8);
            } else {
                this.c.setChecked(false);
                this.d.setChecked(true);
                this.c.setVisibility(8);
                this.d.setVisibility(0);
            }
        }
        this.f.setEnabled(false);
    }

    private void d() {
        if (this.k != null) {
            if (this.b.getVisibility() == 0) {
                if (this.k.getIsResolved() == -1) {
                    this.c.setChecked(false);
                    this.d.setChecked(false);
                    this.c.setVisibility(0);
                    this.d.setVisibility(0);
                } else if (this.k.getIsResolved() == 0) {
                    this.c.setChecked(true);
                    this.d.setChecked(false);
                    this.c.setVisibility(0);
                    this.d.setVisibility(0);
                } else {
                    this.c.setChecked(false);
                    this.d.setChecked(true);
                    this.c.setVisibility(0);
                    this.d.setVisibility(0);
                }
            }
            this.f.setEnabled(true);
        }
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0040, code lost:
        if (r4.l.getSobotEvaluateModel().getScore() == 5) goto L_0x0029;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void a(boolean r5, int r6) {
        /*
            r4 = this;
            android.content.Context r0 = r4.n
            if (r0 == 0) goto L_0x006d
            com.sobot.chat.api.model.ZhiChiMessageBase r0 = r4.l
            if (r0 == 0) goto L_0x006d
            com.sobot.chat.api.model.SobotEvaluateModel r0 = r0.getSobotEvaluateModel()
            if (r0 == 0) goto L_0x006d
            com.sobot.chat.api.model.SobotEvaluateModel r0 = r4.k
            int r0 = r0.getIsResolved()
            com.sobot.chat.api.model.ZhiChiMessageBase r1 = r4.l
            com.sobot.chat.api.model.SobotEvaluateModel r1 = r1.getSobotEvaluateModel()
            boolean r1 = com.sobot.chat.utils.c.a(r1)
            r2 = 0
            if (r1 == 0) goto L_0x0043
            android.widget.RadioButton r1 = r4.c
            boolean r1 = r1.isChecked()
            if (r1 == 0) goto L_0x002b
        L_0x0029:
            r0 = r2
            goto L_0x0043
        L_0x002b:
            android.widget.RadioButton r1 = r4.d
            boolean r1 = r1.isChecked()
            if (r1 == 0) goto L_0x0035
            r0 = 1
            goto L_0x0043
        L_0x0035:
            com.sobot.chat.api.model.ZhiChiMessageBase r1 = r4.l
            com.sobot.chat.api.model.SobotEvaluateModel r1 = r1.getSobotEvaluateModel()
            int r1 = r1.getScore()
            r3 = 5
            if (r1 != r3) goto L_0x0043
            goto L_0x0029
        L_0x0043:
            com.sobot.chat.api.model.ZhiChiMessageBase r1 = r4.l
            com.sobot.chat.api.model.SobotEvaluateModel r1 = r1.getSobotEvaluateModel()
            r1.setIsResolved(r0)
            com.sobot.chat.api.model.ZhiChiMessageBase r0 = r4.l
            com.sobot.chat.api.model.SobotEvaluateModel r0 = r0.getSobotEvaluateModel()
            r0.setScore(r6)
            com.sobot.chat.api.model.ZhiChiMessageBase r6 = r4.l
            com.sobot.chat.api.model.SobotEvaluateModel r6 = r6.getSobotEvaluateModel()
            java.lang.String r0 = r4.e()
            r6.setProblem(r0)
            com.sobot.chat.adapter.e$a r6 = r4.p
            if (r6 == 0) goto L_0x006d
            com.sobot.chat.adapter.e$a r6 = r4.p
            com.sobot.chat.api.model.ZhiChiMessageBase r0 = r4.l
            r6.a(r5, r0)
        L_0x006d:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.sobot.chat.viewHolder.a.a(boolean, int):void");
    }

    @Override // android.widget.RadioGroup.OnCheckedChangeListener
    public void onCheckedChanged(RadioGroup radioGroup, int i) {
        if (this.k != null) {
            if (i == this.c.getId()) {
                this.k.setIsResolved(0);
                this.c.setChecked(true);
                this.d.setChecked(false);
                this.c.setSelected(true);
                this.d.setSelected(false);
            }
            if (i == this.d.getId()) {
                this.k.setIsResolved(1);
                this.c.setChecked(false);
                this.d.setChecked(true);
                this.c.setSelected(false);
                this.d.setSelected(true);
            }
        }
    }

    @Override // android.widget.RatingBar.OnRatingBarChangeListener
    public void onRatingChanged(RatingBar ratingBar, float f, boolean z) {
        m.d(this.k.getScore() + "-----" + this.R + "=====" + f);
        SobotEvaluateModel sobotEvaluateModel = this.k;
        if (sobotEvaluateModel != null && sobotEvaluateModel.getEvaluateStatus() == 0 && f > 0.0f) {
            double d = (double) f;
            if (this.R != ((int) Math.ceil(d))) {
                int ceil = (int) Math.ceil(d);
                this.k.setScore(ceil);
                this.f.setOnRatingBarChangeListener(null);
                this.f.setRating((float) this.R);
                this.f.setOnRatingBarChangeListener(this);
                a(false, ceil);
            }
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private SatisfactionSetBase a(int i, List<SatisfactionSetBase> list) {
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
    public static String[] b(String str) {
        if (!TextUtils.isEmpty(str)) {
            return str.split(Constants.ACCEPT_TIME_SEPARATOR_SP);
        }
        return null;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void a(String[] strArr) {
        if (strArr == null) {
            this.O.setVisibility(8);
            return;
        }
        if (!this.j.isHideManualEvaluationLabels()) {
            this.O.setVisibility(0);
        } else {
            this.O.setVisibility(8);
        }
        switch (strArr.length) {
            case 1:
                this.I.setText(strArr[0]);
                this.I.setVisibility(0);
                this.J.setVisibility(4);
                this.F.setVisibility(0);
                this.G.setVisibility(8);
                this.H.setVisibility(8);
                return;
            case 2:
                this.I.setText(strArr[0]);
                this.I.setVisibility(0);
                this.J.setText(strArr[1]);
                this.J.setVisibility(0);
                this.F.setVisibility(0);
                this.G.setVisibility(8);
                this.H.setVisibility(8);
                return;
            case 3:
                this.I.setText(strArr[0]);
                this.I.setVisibility(0);
                this.J.setText(strArr[1]);
                this.J.setVisibility(0);
                this.F.setVisibility(0);
                this.K.setText(strArr[2]);
                this.K.setVisibility(0);
                this.L.setVisibility(4);
                this.G.setVisibility(0);
                this.H.setVisibility(8);
                return;
            case 4:
                this.I.setText(strArr[0]);
                this.I.setVisibility(0);
                this.J.setText(strArr[1]);
                this.J.setVisibility(0);
                this.F.setVisibility(0);
                this.K.setText(strArr[2]);
                this.K.setVisibility(0);
                this.L.setText(strArr[3]);
                this.L.setVisibility(0);
                this.G.setVisibility(0);
                this.H.setVisibility(8);
                return;
            case 5:
                this.I.setText(strArr[0]);
                this.I.setVisibility(0);
                this.J.setText(strArr[1]);
                this.J.setVisibility(0);
                this.F.setVisibility(0);
                this.K.setText(strArr[2]);
                this.K.setVisibility(0);
                this.L.setText(strArr[3]);
                this.L.setVisibility(0);
                this.G.setVisibility(0);
                this.M.setText(strArr[4]);
                this.M.setVisibility(0);
                this.N.setVisibility(4);
                this.H.setVisibility(0);
                return;
            case 6:
                this.I.setText(strArr[0]);
                this.I.setVisibility(0);
                this.J.setText(strArr[1]);
                this.J.setVisibility(0);
                this.F.setVisibility(0);
                this.K.setText(strArr[2]);
                this.K.setVisibility(0);
                this.L.setText(strArr[3]);
                this.L.setVisibility(0);
                this.G.setVisibility(0);
                this.M.setText(strArr[4]);
                this.M.setVisibility(0);
                this.N.setText(strArr[5]);
                this.N.setVisibility(0);
                this.H.setVisibility(0);
                return;
            default:
                return;
        }
    }

    /* access modifiers changed from: private */
    public String e() {
        String str = new String();
        for (int i = 0; i < this.P.size(); i++) {
            if (this.P.get(i).isChecked()) {
                str = str + ((Object) this.P.get(i).getText()) + Constants.ACCEPT_TIME_SEPARATOR_SP;
            }
        }
        if (str.length() > 0) {
            str = str.substring(0, str.length() - 1);
        }
        return str + "";
    }
}
