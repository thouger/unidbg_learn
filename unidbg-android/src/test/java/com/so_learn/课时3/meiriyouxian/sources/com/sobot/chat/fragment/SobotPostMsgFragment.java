package com.sobot.chat.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Html;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.core.content.ContextCompat;
import com.sobot.chat.activity.SobotPhotoActivity;
import com.sobot.chat.activity.SobotPostCategoryActivity;
import com.sobot.chat.activity.SobotPostMsgActivity;
import com.sobot.chat.activity.SobotVideoActivity;
import com.sobot.chat.adapter.g;
import com.sobot.chat.api.ResultCallBack;
import com.sobot.chat.api.ZhiChiApi;
import com.sobot.chat.api.model.CommonModelBase;
import com.sobot.chat.api.model.Information;
import com.sobot.chat.api.model.PostParamModel;
import com.sobot.chat.api.model.SobotCacheFile;
import com.sobot.chat.api.model.SobotFieldModel;
import com.sobot.chat.api.model.SobotLeaveMsgConfig;
import com.sobot.chat.api.model.SobotLeaveMsgParamModel;
import com.sobot.chat.api.model.ZhiChiMessage;
import com.sobot.chat.api.model.ZhiChiUploadAppFileModelResult;
import com.sobot.chat.application.MyApplication;
import com.sobot.chat.camera.c.f;
import com.sobot.chat.core.http.c.a;
import com.sobot.chat.listener.b;
import com.sobot.chat.utils.ac;
import com.sobot.chat.utils.ae;
import com.sobot.chat.utils.c;
import com.sobot.chat.utils.d;
import com.sobot.chat.utils.e;
import com.sobot.chat.utils.j;
import com.sobot.chat.utils.l;
import com.sobot.chat.utils.m;
import com.sobot.chat.utils.n;
import com.sobot.chat.utils.o;
import com.sobot.chat.utils.q;
import com.sobot.chat.utils.s;
import com.sobot.chat.utils.x;
import com.sobot.chat.widget.dialog.h;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SobotPostMsgFragment extends SobotBaseFragment implements View.OnClickListener, b {
    private LinearLayout A;
    private LinearLayout B;
    private LinearLayout C;
    private RelativeLayout D;
    private RelativeLayout E;
    private RelativeLayout F;
    private TextView G;
    private TextView H;
    private TextView I;
    private ArrayList<ZhiChiUploadAppFileModelResult> J = new ArrayList<>();
    private g K;
    private h L;
    private ArrayList<SobotFieldModel> M;
    private ArrayList<SobotFieldModel> N;
    private LinearLayout O;
    private SobotLeaveMsgConfig P;
    private Information Q;
    private String R = "";
    private String S = "";
    private boolean T;
    private int X = -1;
    private c.a Y = new AnonymousClass5();
    private View.OnClickListener Z = new AnonymousClass13();
    protected com.sobot.chat.widget.dialog.c a;
    public Handler b = new AnonymousClass1();
    private View c;
    private EditText d;
    private EditText e;
    private EditText f;
    private EditText g;
    private TextView h;
    private TextView i;
    private TextView j;
    private TextView k;
    private TextView l;
    private TextView m;
    private TextView n;
    private TextView o;
    private View p;
    private View q;
    private View r;
    private View s;
    private View t;
    private View u;
    private View v;
    private View w;
    private Button x;
    private GridView y;
    private LinearLayout z;

    /* renamed from: com.sobot.chat.fragment.SobotPostMsgFragment$1  reason: invalid class name */
    class AnonymousClass1 extends Handler {
        AnonymousClass1() {
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            if (message.what == 1) {
                if (SobotPostMsgFragment.this.X == 1) {
                    SobotPostMsgFragment.this.a(true);
                } else if (SobotPostMsgFragment.this.X == 2) {
                    SobotPostMsgFragment.this.Z().setResult(200);
                    SobotPostMsgFragment.this.a(false);
                } else {
                    SobotPostMsgFragment sobotPostMsgFragment = SobotPostMsgFragment.this;
                    sobotPostMsgFragment.a(sobotPostMsgFragment.T);
                }
            }
        }
    }

    public static SobotPostMsgFragment a(Bundle bundle) {
        Bundle bundle2 = new Bundle();
        bundle2.putBundle("sobot_bundle_information", bundle);
        SobotPostMsgFragment sobotPostMsgFragment = new SobotPostMsgFragment();
        sobotPostMsgFragment.setArguments(bundle2);
        return sobotPostMsgFragment;
    }

    @Override // com.sobot.chat.fragment.SobotBaseFragment, androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        Bundle bundle2;
        super.onCreate(bundle);
        if (getArguments() != null && (bundle2 = getArguments().getBundle("sobot_bundle_information")) != null) {
            this.R = bundle2.getString("intent_key_uid");
            this.S = bundle2.getString("intent_key_groupid");
            this.N = (ArrayList) bundle2.getSerializable("intent_key_cus_fields");
            this.X = bundle2.getInt("FLAG_EXIT_TYPE", -1);
            this.T = bundle2.getBoolean("FLAG_EXIT_SDK", false);
            this.P = (SobotLeaveMsgConfig) bundle2.getSerializable("intent_key_config");
        }
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.c = layoutInflater.inflate(g("sobot_fragment_post_msg"), viewGroup, false);
        b(this.c);
        return this.c;
    }

    @Override // androidx.fragment.app.Fragment
    public void onActivityCreated(Bundle bundle) {
        a();
        super.onActivityCreated(bundle);
    }

    /* access modifiers changed from: protected */
    public void b(View view) {
        this.C = (LinearLayout) view.findViewById(e("sobot_ll_content_img"));
        this.f = (EditText) view.findViewById(e("sobot_post_phone"));
        this.d = (EditText) view.findViewById(e("sobot_post_email"));
        this.g = (EditText) view.findViewById(e("sobot_post_title"));
        this.p = view.findViewById(e("sobot_frist_line"));
        this.q = view.findViewById(e("sobot_post_title_line"));
        this.r = view.findViewById(e("sobot_post_question_line"));
        this.s = view.findViewById(e("sobot_post_customer_line"));
        this.t = view.findViewById(e("sobot_post_title_sec_line"));
        this.u = view.findViewById(e("sobot_post_question_sec_line"));
        this.v = view.findViewById(e("sobot_post_customer_sec_line"));
        this.w = view.findViewById(e("sobot_phone_line"));
        this.e = (EditText) view.findViewById(e("sobot_post_et_content"));
        this.h = (TextView) view.findViewById(e("sobot_tv_post_msg"));
        this.i = (TextView) view.findViewById(e("sobot_post_email_lable"));
        this.j = (TextView) view.findViewById(e("sobot_post_phone_lable"));
        this.l = (TextView) view.findViewById(e("sobot_post_title_lable"));
        this.k = (TextView) view.findViewById(e("sobot_post_question_lable"));
        this.k.setText(Html.fromHtml(i("sobot_problem_types") + "<font color='#f9676f'>&nbsp;*</font>"));
        this.n = (TextView) view.findViewById(e("sobot_post_question_lable"));
        this.m = (TextView) view.findViewById(e("sobot_post_question_type"));
        this.O = (LinearLayout) view.findViewById(e("sobot_post_msg_layout"));
        this.z = (LinearLayout) view.findViewById(e("sobot_enclosure_container"));
        this.A = (LinearLayout) view.findViewById(e("sobot_post_customer_field"));
        this.D = (RelativeLayout) view.findViewById(e("sobot_post_email_rl"));
        this.H = (TextView) view.findViewById(e("sobot_post_email_lable_hint"));
        this.H.setHint(q.f(Z(), "sobot_please_input"));
        this.G = (TextView) view.findViewById(e("sobot_post_title_lable_hint"));
        this.G.setHint(q.f(Z(), "sobot_please_input"));
        this.E = (RelativeLayout) view.findViewById(e("sobot_post_phone_rl"));
        this.I = (TextView) view.findViewById(e("sobot_post_phone_lable_hint"));
        this.I.setHint(q.f(Z(), "sobot_please_input"));
        this.F = (RelativeLayout) view.findViewById(e("sobot_post_title_rl"));
        this.B = (LinearLayout) view.findViewById(e("sobot_post_question_ll"));
        this.B.setOnClickListener(this);
        this.o = (TextView) view.findViewById(e("sobot_tv_problem_description"));
        this.o.setText(q.f(Z(), "sobot_problem_description"));
        this.x = (Button) view.findViewById(e("sobot_btn_submit"));
        this.x.setText(q.f(Z(), "sobot_btn_submit_text"));
        this.x.setOnClickListener(this);
        this.A.setVisibility(8);
        if (this.P.isEmailShowFlag()) {
            this.D.setVisibility(0);
            this.D.setOnClickListener(new AnonymousClass2());
        } else {
            this.D.setVisibility(8);
        }
        this.d.setOnFocusChangeListener(new AnonymousClass7());
        if (this.P.isTelShowFlag()) {
            this.E.setVisibility(0);
            this.E.setOnClickListener(new AnonymousClass4());
        } else {
            this.E.setVisibility(8);
        }
        this.f.setOnFocusChangeListener(new AnonymousClass8());
        if (this.P.isTicketTitleShowFlag()) {
            this.F.setVisibility(0);
            this.q.setVisibility(0);
            this.t.setVisibility(0);
            this.F.setOnClickListener(new AnonymousClass6());
        } else {
            this.F.setVisibility(8);
        }
        this.g.setOnFocusChangeListener(new AnonymousClass9());
        if (this.P.isEmailShowFlag()) {
            this.p.setVisibility(0);
        } else {
            this.p.setVisibility(8);
        }
        this.w.setVisibility(this.P.isTelShowFlag() ? 0 : 8);
        String b = s.b(Z(), "sobot_user_phone", "");
        if (this.P.isTelShowFlag() && !TextUtils.isEmpty(b)) {
            this.f.setVisibility(0);
            this.f.setText(b);
            this.I.setVisibility(8);
            this.j.setTextColor(ContextCompat.getColor(Z(), q.c(Z(), "sobot_common_gray2")));
            this.j.setTextSize(12.0f);
        }
        String b2 = s.b(Z(), "sobot_user_email", "");
        if (this.P.isEmailShowFlag() && !TextUtils.isEmpty(b2)) {
            this.d.setVisibility(0);
            this.d.setText(b2);
            this.H.setVisibility(8);
            this.i.setTextColor(ContextCompat.getColor(Z(), q.c(Z(), "sobot_common_gray2")));
            this.i.setTextSize(12.0f);
        }
        if (this.P.isEnclosureShowFlag()) {
            this.z.setVisibility(0);
            f();
        } else {
            this.z.setVisibility(8);
        }
        if (this.P.isTicketTypeFlag()) {
            this.B.setVisibility(0);
            this.r.setVisibility(0);
            this.u.setVisibility(0);
        } else {
            this.B.setVisibility(8);
            this.m.setTag(this.P.getTicketTypeId());
        }
        a((View) this.h);
        a((View) this.i);
        a((View) this.j);
        a((View) this.k);
        a((View) this.l);
        a((View) this.m);
        a((View) this.n);
        a(this.C);
        a((View) this.d);
        a((View) this.f);
        a((View) this.g);
        a((View) this.G);
        a((View) this.H);
        a((View) this.I);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: com.sobot.chat.fragment.SobotPostMsgFragment$2  reason: invalid class name */
    public class AnonymousClass2 implements View.OnClickListener {
        AnonymousClass2() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            SobotPostMsgFragment.this.d.setVisibility(0);
            SobotPostMsgFragment.this.i.setTextColor(ContextCompat.getColor(SobotPostMsgFragment.this.Z(), q.c(SobotPostMsgFragment.this.Z(), "sobot_common_gray2")));
            SobotPostMsgFragment.this.i.setTextSize(12.0f);
            SobotPostMsgFragment.this.d.setFocusable(true);
            SobotPostMsgFragment.this.d.setFocusableInTouchMode(true);
            SobotPostMsgFragment.this.d.requestFocus();
            SobotPostMsgFragment.this.H.setVisibility(8);
            com.sobot.chat.widget.kpswitch.util.c.a(SobotPostMsgFragment.this.d);
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: com.sobot.chat.fragment.SobotPostMsgFragment$7  reason: invalid class name */
    public class AnonymousClass7 implements View.OnFocusChangeListener {
        AnonymousClass7() {
        }

        @Override // android.view.View.OnFocusChangeListener
        public void onFocusChange(View view, boolean z) {
            if (z) {
                SobotPostMsgFragment.this.H.setVisibility(8);
            } else if (TextUtils.isEmpty(SobotPostMsgFragment.this.d.getText().toString().trim())) {
                SobotPostMsgFragment.this.i.setTextSize(14.0f);
                SobotPostMsgFragment.this.i.setTextColor(ContextCompat.getColor(SobotPostMsgFragment.this.Z(), q.c(SobotPostMsgFragment.this.Z(), "sobot_common_gray1")));
                SobotPostMsgFragment.this.d.setVisibility(8);
                SobotPostMsgFragment.this.H.setVisibility(0);
            }
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: com.sobot.chat.fragment.SobotPostMsgFragment$4  reason: invalid class name */
    public class AnonymousClass4 implements View.OnClickListener {
        AnonymousClass4() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            SobotPostMsgFragment.this.f.setVisibility(0);
            SobotPostMsgFragment.this.j.setTextColor(ContextCompat.getColor(SobotPostMsgFragment.this.Z(), q.c(SobotPostMsgFragment.this.Z(), "sobot_common_gray2")));
            SobotPostMsgFragment.this.j.setTextSize(12.0f);
            SobotPostMsgFragment.this.f.setFocusable(true);
            SobotPostMsgFragment.this.f.setFocusableInTouchMode(true);
            SobotPostMsgFragment.this.f.requestFocus();
            SobotPostMsgFragment.this.I.setVisibility(8);
            com.sobot.chat.widget.kpswitch.util.c.a(SobotPostMsgFragment.this.f);
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: com.sobot.chat.fragment.SobotPostMsgFragment$8  reason: invalid class name */
    public class AnonymousClass8 implements View.OnFocusChangeListener {
        AnonymousClass8() {
        }

        @Override // android.view.View.OnFocusChangeListener
        public void onFocusChange(View view, boolean z) {
            if (z) {
                SobotPostMsgFragment.this.I.setVisibility(8);
            } else if (TextUtils.isEmpty(SobotPostMsgFragment.this.f.getText().toString().trim())) {
                SobotPostMsgFragment.this.j.setTextSize(14.0f);
                SobotPostMsgFragment.this.j.setTextColor(ContextCompat.getColor(SobotPostMsgFragment.this.Z(), q.c(SobotPostMsgFragment.this.Z(), "sobot_common_gray1")));
                SobotPostMsgFragment.this.f.setVisibility(8);
                SobotPostMsgFragment.this.I.setVisibility(0);
            }
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: com.sobot.chat.fragment.SobotPostMsgFragment$6  reason: invalid class name */
    public class AnonymousClass6 implements View.OnClickListener {
        AnonymousClass6() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            SobotPostMsgFragment.this.g.setVisibility(0);
            SobotPostMsgFragment.this.l.setTextColor(ContextCompat.getColor(SobotPostMsgFragment.this.Z(), q.c(SobotPostMsgFragment.this.Z(), "sobot_common_gray2")));
            SobotPostMsgFragment.this.l.setTextSize(12.0f);
            SobotPostMsgFragment.this.g.setFocusable(true);
            SobotPostMsgFragment.this.g.setFocusableInTouchMode(true);
            SobotPostMsgFragment.this.g.requestFocus();
            SobotPostMsgFragment.this.G.setVisibility(8);
            com.sobot.chat.widget.kpswitch.util.c.a(SobotPostMsgFragment.this.g);
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: com.sobot.chat.fragment.SobotPostMsgFragment$9  reason: invalid class name */
    public class AnonymousClass9 implements View.OnFocusChangeListener {
        AnonymousClass9() {
        }

        @Override // android.view.View.OnFocusChangeListener
        public void onFocusChange(View view, boolean z) {
            if (z) {
                SobotPostMsgFragment.this.G.setVisibility(8);
            } else if (TextUtils.isEmpty(SobotPostMsgFragment.this.g.getText().toString().trim())) {
                SobotPostMsgFragment.this.l.setTextSize(14.0f);
                SobotPostMsgFragment.this.l.setTextColor(ContextCompat.getColor(SobotPostMsgFragment.this.Z(), q.c(SobotPostMsgFragment.this.Z(), "sobot_common_gray1")));
                SobotPostMsgFragment.this.g.setVisibility(8);
                SobotPostMsgFragment.this.G.setVisibility(0);
            }
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: com.sobot.chat.fragment.SobotPostMsgFragment$10  reason: invalid class name */
    public class AnonymousClass10 implements a<SobotLeaveMsgParamModel> {
        AnonymousClass10() {
        }

        public void a(SobotLeaveMsgParamModel sobotLeaveMsgParamModel) {
            if (sobotLeaveMsgParamModel != null && sobotLeaveMsgParamModel.getField() != null && sobotLeaveMsgParamModel.getField().size() != 0) {
                SobotPostMsgFragment.this.s.setVisibility(0);
                SobotPostMsgFragment.this.v.setVisibility(0);
                SobotPostMsgFragment.this.M = sobotLeaveMsgParamModel.getField();
                com.sobot.chat.presenter.a.a(SobotPostMsgFragment.this.Z(), SobotPostMsgFragment.this.Z(), SobotPostMsgFragment.this.M, SobotPostMsgFragment.this.A, SobotPostMsgFragment.this);
            }
        }

        @Override // com.sobot.chat.core.http.c.a
        public void a(Exception exc, String str) {
            try {
                SobotPostMsgFragment.this.a(q.f(SobotPostMsgFragment.this.Z(), "sobot_try_again"));
            } catch (Exception unused) {
            }
        }

        /* renamed from: com.sobot.chat.fragment.SobotPostMsgFragment$10$1  reason: invalid class name */
        class AnonymousClass1 implements View.OnClickListener {
            final /* synthetic */ AnonymousClass3 a;

            AnonymousClass1(AnonymousClass3 r1) {
                this.a = r1;
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                SobotPostMsgFragment.this.a.dismiss();
                if (view.getId() == SobotPostMsgFragment.this.e("btn_pick_photo")) {
                    Log.e("onClick: ", SobotPostMsgFragment.this.a.a() + "");
                    SobotPostMsgFragment.this.J.remove(SobotPostMsgFragment.this.a.a());
                    SobotPostMsgFragment.this.K.a();
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    public void a() {
        this.Q = (Information) s.d(Z(), "sobot_last_current_info");
        this.U.getTemplateFieldsInfo(this, this.R, this.P.getTemplateId(), new AnonymousClass10());
        g();
        h();
    }

    private void d() {
        String a = com.sobot.chat.presenter.a.a(Z(), this.A, this.M);
        if (TextUtils.isEmpty(a)) {
            e();
        } else {
            a(a);
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:65:0x0207  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void e() {
        /*
        // Method dump skipped, instructions count: 607
        */
        throw new UnsupportedOperationException("Method not decompiled: com.sobot.chat.fragment.SobotPostMsgFragment.e():void");
    }

    public void a(String str) {
        e.a(Z(), str, 1000).show();
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (!(view != this.B || this.P.getType() == null || this.P.getType().size() == 0)) {
            Intent intent = new Intent(Z(), SobotPostCategoryActivity.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable("types", this.P.getType());
            TextView textView = this.m;
            if (textView != null && !TextUtils.isEmpty(textView.getText().toString()) && this.m.getTag() != null && !TextUtils.isEmpty(this.m.getTag().toString())) {
                bundle.putString("typeName", this.m.getText().toString());
                bundle.putString("typeId", this.m.getTag().toString());
            }
            intent.putExtra("bundle", bundle);
            startActivityForResult(intent, 304);
        }
        if (view == this.x) {
            d();
        }
    }

    private void a(String str, String str2, String str3) {
        PostParamModel postParamModel = new PostParamModel();
        postParamModel.setTemplateId(this.P.getTemplateId());
        postParamModel.setPartnerId(this.Q.getPartnerid());
        postParamModel.setUid(this.R);
        postParamModel.setTicketContent(this.e.getText().toString());
        postParamModel.setCustomerEmail(str2);
        postParamModel.setCustomerPhone(str);
        postParamModel.setTicketTitle(str3);
        postParamModel.setCompanyId(this.P.getCompanyId());
        postParamModel.setFileStr(c());
        postParamModel.setGroupId(this.S);
        Information information = this.Q;
        if (!(information == null || information.getLeaveParamsExtends() == null)) {
            postParamModel.setParamsExtends(x.a((Object) this.Q.getLeaveParamsExtends()));
        }
        if (this.m.getTag() != null && !TextUtils.isEmpty(this.m.getTag().toString())) {
            postParamModel.setTicketTypeId(this.m.getTag().toString());
        }
        ArrayList<SobotFieldModel> arrayList = this.N;
        if (arrayList != null && arrayList.size() > 0) {
            if (this.M == null) {
                this.M = new ArrayList<>();
            }
            this.M.addAll(this.N);
        }
        postParamModel.setExtendFields(com.sobot.chat.presenter.a.a(this.M));
        this.U.postMsg(this, postParamModel, new AnonymousClass12());
    }

    /* access modifiers changed from: package-private */
    /* renamed from: com.sobot.chat.fragment.SobotPostMsgFragment$12  reason: invalid class name */
    public class AnonymousClass12 implements a<CommonModelBase> {
        AnonymousClass12() {
        }

        public void a(CommonModelBase commonModelBase) {
            if (Integer.parseInt(commonModelBase.getStatus()) == 0) {
                SobotPostMsgFragment.this.a(commonModelBase.getMsg());
            } else if (Integer.parseInt(commonModelBase.getStatus()) == 1 && SobotPostMsgFragment.this.Z() != null) {
                com.sobot.chat.widget.kpswitch.util.c.b(SobotPostMsgFragment.this.Z().getCurrentFocus());
                Intent intent = new Intent();
                intent.setAction("sobot_action_show_completed_view");
                d.a(SobotPostMsgFragment.this.Z(), intent);
            }
        }

        @Override // com.sobot.chat.core.http.c.a
        public void a(Exception exc, String str) {
            try {
                SobotPostMsgFragment.this.a(q.f(SobotPostMsgFragment.this.Z(), "sobot_try_again"));
            } catch (Exception unused) {
            }
        }
    }

    public void b() {
        if (getView() != null) {
            com.sobot.chat.widget.kpswitch.util.c.b(((ViewGroup) getView()).getFocusedChild());
        }
        int i = this.X;
        if (i == 1 || i == 2) {
            a(false);
        } else {
            a(this.T);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void a(boolean z) {
        if (z) {
            MyApplication.getInstance().exit();
        } else if (Z() == null) {
            Activity lastActivity = MyApplication.getInstance().getLastActivity();
            if (lastActivity != null && (lastActivity instanceof SobotPostMsgActivity)) {
                lastActivity.finish();
                lastActivity.overridePendingTransition(q.a(lastActivity, "anim", "push_right_in"), q.a(lastActivity, "anim", "push_right_out"));
            }
        } else {
            Z().finish();
            Z().overridePendingTransition(q.a(Z(), "anim", "push_right_in"), q.a(Z(), "anim", "push_right_out"));
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroy() {
        com.sobot.chat.widget.dialog.d.b(Z());
        super.onDestroy();
    }

    private void f() {
        this.y = (GridView) this.c.findViewById(e("sobot_post_msg_pic"));
        this.K = new g(Z(), this.J);
        this.y.setAdapter((ListAdapter) this.K);
        this.K.a(new AnonymousClass3());
        this.K.a();
    }

    /* access modifiers changed from: package-private */
    /* renamed from: com.sobot.chat.fragment.SobotPostMsgFragment$3  reason: invalid class name */
    public class AnonymousClass3 implements g.b {
        AnonymousClass3() {
        }

        @Override // com.sobot.chat.adapter.g.b
        public void a(View view, int i, int i2) {
            ZhiChiUploadAppFileModelResult zhiChiUploadAppFileModelResult;
            com.sobot.chat.widget.kpswitch.util.c.b(view);
            if (i2 == 0) {
                SobotPostMsgFragment sobotPostMsgFragment = SobotPostMsgFragment.this;
                sobotPostMsgFragment.L = new h(sobotPostMsgFragment.Z(), SobotPostMsgFragment.this.Z);
                SobotPostMsgFragment.this.L.show();
            } else if (i2 == 1) {
                m.d("\u5f53\u524d\u9009\u62e9\u56fe\u7247\u4f4d\u7f6e\uff1a" + i);
                if (SobotPostMsgFragment.this.K != null && SobotPostMsgFragment.this.K.b() != null && (zhiChiUploadAppFileModelResult = SobotPostMsgFragment.this.K.b().get(i)) != null) {
                    if (TextUtils.isEmpty(zhiChiUploadAppFileModelResult.getFileLocalPath()) || !o.b(zhiChiUploadAppFileModelResult.getFileLocalPath())) {
                        Intent intent = new Intent(SobotPostMsgFragment.this.Z(), SobotPhotoActivity.class);
                        intent.putExtra("imageUrL", TextUtils.isEmpty(zhiChiUploadAppFileModelResult.getFileLocalPath()) ? zhiChiUploadAppFileModelResult.getFileUrl() : zhiChiUploadAppFileModelResult.getFileLocalPath());
                        SobotPostMsgFragment.this.Z().startActivity(intent);
                        return;
                    }
                    File file = new File(zhiChiUploadAppFileModelResult.getFileLocalPath());
                    SobotCacheFile sobotCacheFile = new SobotCacheFile();
                    sobotCacheFile.setFileName(file.getName());
                    sobotCacheFile.setUrl(zhiChiUploadAppFileModelResult.getFileUrl());
                    sobotCacheFile.setFilePath(zhiChiUploadAppFileModelResult.getFileLocalPath());
                    sobotCacheFile.setFileType(com.sobot.chat.widget.attachment.a.a(f.b(zhiChiUploadAppFileModelResult.getFileLocalPath())));
                    sobotCacheFile.setMsgId("" + System.currentTimeMillis());
                    SobotPostMsgFragment.this.Z().startActivity(SobotVideoActivity.a(SobotPostMsgFragment.this.Z(), sobotCacheFile));
                }
            } else if (i2 == 2) {
                String f = q.f(SobotPostMsgFragment.this.Z(), "sobot_do_you_delete_picture");
                if (SobotPostMsgFragment.this.K != null && SobotPostMsgFragment.this.K.b() != null) {
                    ZhiChiUploadAppFileModelResult zhiChiUploadAppFileModelResult2 = SobotPostMsgFragment.this.K.b().get(i);
                    if (zhiChiUploadAppFileModelResult2 != null && !TextUtils.isEmpty(zhiChiUploadAppFileModelResult2.getFileLocalPath()) && o.b(zhiChiUploadAppFileModelResult2.getFileLocalPath())) {
                        f = q.f(SobotPostMsgFragment.this.Z(), "sobot_do_you_delete_video");
                    }
                    if (SobotPostMsgFragment.this.a != null) {
                        SobotPostMsgFragment.this.a.dismiss();
                        SobotPostMsgFragment.this.a = null;
                    }
                    if (SobotPostMsgFragment.this.a == null) {
                        SobotPostMsgFragment sobotPostMsgFragment2 = SobotPostMsgFragment.this;
                        sobotPostMsgFragment2.a = new com.sobot.chat.widget.dialog.c(sobotPostMsgFragment2.Z(), f, new AnonymousClass10.AnonymousClass1(this));
                    }
                    SobotPostMsgFragment.this.a.a(i);
                    SobotPostMsgFragment.this.a.show();
                }
            }
        }
    }

    private void g() {
        Information information = this.Q;
        if (information != null && information.getLeaveMsgTemplateContent() != null) {
            this.e.setHint(Html.fromHtml(this.Q.getLeaveMsgTemplateContent().replace("<br/>", "")));
        } else if (!TextUtils.isEmpty(this.P.getMsgTmp())) {
            SobotLeaveMsgConfig sobotLeaveMsgConfig = this.P;
            sobotLeaveMsgConfig.setMsgTmp(sobotLeaveMsgConfig.getMsgTmp().replace("<br/>", "").replace("<p>", "").replace("</p>", ""));
            this.e.setHint(Html.fromHtml(this.P.getMsgTmp()));
        }
        Information information2 = this.Q;
        if (information2 != null && information2.getLeaveMsgGuideContent() != null) {
            if (TextUtils.isEmpty(this.Q.getLeaveMsgGuideContent())) {
                this.h.setVisibility(8);
            }
            j.a(Z().getApplicationContext()).a(this.h, this.Q.getLeaveMsgGuideContent().replace("<br/>", ""), q.a(Z(), "color", "sobot_postMsg_url_color"));
        } else if (!TextUtils.isEmpty(this.P.getMsgTxt())) {
            SobotLeaveMsgConfig sobotLeaveMsgConfig2 = this.P;
            sobotLeaveMsgConfig2.setMsgTxt(sobotLeaveMsgConfig2.getMsgTxt().replace("<br/>", "").replace("<p>", "").replace("</p>", "").replace("\n", ""));
            j.a(Z().getApplicationContext()).a(this.h, this.P.getMsgTxt(), q.a(Z(), "color", "sobot_postMsg_url_color"));
        } else {
            this.h.setVisibility(8);
        }
        this.O.setOnClickListener(new AnonymousClass11());
    }

    /* access modifiers changed from: package-private */
    /* renamed from: com.sobot.chat.fragment.SobotPostMsgFragment$11  reason: invalid class name */
    public class AnonymousClass11 implements View.OnClickListener {
        AnonymousClass11() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            com.sobot.chat.widget.kpswitch.util.c.b(SobotPostMsgFragment.this.O);
        }
    }

    private void h() {
        if (this.P.isEmailFlag()) {
            TextView textView = this.i;
            textView.setText(Html.fromHtml(i("sobot_email") + "<font color='#f9676f'>&nbsp;*</font>"));
        } else {
            this.i.setText(Html.fromHtml(i("sobot_email")));
        }
        if (this.P.isTelFlag()) {
            TextView textView2 = this.j;
            textView2.setText(Html.fromHtml(i("sobot_phone") + "<font color='#f9676f'>&nbsp;*</font>"));
        } else {
            this.j.setText(Html.fromHtml(i("sobot_phone")));
        }
        if (this.P.isTicketTitleShowFlag()) {
            TextView textView3 = this.l;
            textView3.setText(Html.fromHtml(i("sobot_title") + "<font color='#f9676f'>&nbsp;*</font>"));
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: com.sobot.chat.fragment.SobotPostMsgFragment$5  reason: invalid class name */
    public class AnonymousClass5 implements c.a {
        AnonymousClass5() {
        }

        /* renamed from: com.sobot.chat.fragment.SobotPostMsgFragment$5$1  reason: invalid class name */
        class AnonymousClass1 implements ResultCallBack<ZhiChiMessage> {
            final /* synthetic */ String a;

            @Override // com.sobot.chat.api.ResultCallBack
            public void onLoading(long j, long j2, boolean z) {
            }

            AnonymousClass1(String str) {
                this.a = str;
            }

            /* renamed from: a */
            public void onSuccess(ZhiChiMessage zhiChiMessage) {
                com.sobot.chat.widget.dialog.d.b(SobotPostMsgFragment.this.Z());
                if (zhiChiMessage.getData() != null) {
                    ZhiChiUploadAppFileModelResult zhiChiUploadAppFileModelResult = new ZhiChiUploadAppFileModelResult();
                    zhiChiUploadAppFileModelResult.setFileUrl(zhiChiMessage.getData().getUrl());
                    zhiChiUploadAppFileModelResult.setFileLocalPath(this.a);
                    zhiChiUploadAppFileModelResult.setViewState(1);
                    SobotPostMsgFragment.this.K.a(zhiChiUploadAppFileModelResult);
                }
            }

            @Override // com.sobot.chat.api.ResultCallBack
            public void onFailure(Exception exc, String str) {
                com.sobot.chat.widget.dialog.d.b(SobotPostMsgFragment.this.Z());
                SobotPostMsgFragment.this.a(q.f(SobotPostMsgFragment.this.Z(), "sobot_net_work_err"));
            }
        }

        @Override // com.sobot.chat.utils.c.a
        public void a(String str) {
            ZhiChiApi zhiChiApi = SobotPostMsgFragment.this.U;
            SobotPostMsgFragment sobotPostMsgFragment = SobotPostMsgFragment.this;
            zhiChiApi.fileUploadForPostMsg(sobotPostMsgFragment, sobotPostMsgFragment.P.getCompanyId(), SobotPostMsgFragment.this.R, str, new AnonymousClass1(str));
        }

        @Override // com.sobot.chat.utils.c.a
        public void a() {
            com.sobot.chat.widget.dialog.d.b(SobotPostMsgFragment.this.Z());
        }
    }

    public String c() {
        String str = "";
        if (!this.P.isEnclosureShowFlag()) {
            return str;
        }
        ArrayList<ZhiChiUploadAppFileModelResult> b = this.K.b();
        for (int i = 0; i < b.size(); i++) {
            str = str + b.get(i).getFileUrl() + ";";
        }
        return str;
    }

    @Override // androidx.fragment.app.Fragment
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i2 == -1) {
            if (i == 701) {
                if (intent == null || intent.getData() == null) {
                    a(i("sobot_did_not_get_picture_path"));
                } else {
                    Uri data = intent.getData();
                    if (data == null) {
                        data = l.a(intent, Z());
                    }
                    String a = l.a(Z(), data);
                    if (!ac.a((Object) a)) {
                        if (o.b(a)) {
                            MediaPlayer mediaPlayer = new MediaPlayer();
                            try {
                                mediaPlayer.setDataSource(Z(), data);
                                mediaPlayer.prepare();
                                if (mediaPlayer.getDuration() / 1000 > 15) {
                                    ae.a(Z(), i("sobot_upload_vodie_length"));
                                    return;
                                }
                                com.sobot.chat.widget.dialog.d.a(Z());
                                String a2 = n.a(a);
                                try {
                                    Activity Z = Z();
                                    this.Y.a(f.a(Z, data, a2 + f.b(a), a));
                                } catch (Exception e) {
                                    e.printStackTrace();
                                    ae.a(Z(), q.f(Z(), "sobot_pic_type_error"));
                                    return;
                                }
                            } catch (IOException e2) {
                                e2.printStackTrace();
                            }
                        } else {
                            com.sobot.chat.widget.dialog.d.a(Z());
                            c.a((Context) Z(), data, this.Y, false);
                        }
                    }
                }
            } else if (i == 702) {
                if (this.V == null || !this.V.exists()) {
                    a(i("sobot_pic_select_again"));
                } else {
                    com.sobot.chat.widget.dialog.d.a(Z());
                    c.a((Context) Z(), this.V.getAbsolutePath(), this.Y, true);
                }
            }
        }
        com.sobot.chat.presenter.a.a(Z(), intent, this.M, this.A);
        if (intent == null) {
            return;
        }
        if (i == 302) {
            this.K.a((List) intent.getExtras().getSerializable("sobot_keytype_pic_list"));
        } else if (i == 304 && !TextUtils.isEmpty(intent.getStringExtra("category_typeId"))) {
            String stringExtra = intent.getStringExtra("category_typeName");
            String stringExtra2 = intent.getStringExtra("category_typeId");
            if (!TextUtils.isEmpty(stringExtra)) {
                this.m.setText(stringExtra);
                this.m.setTag(stringExtra2);
                this.m.setVisibility(0);
                this.n.setTextColor(ContextCompat.getColor(Z(), q.c(Z(), "sobot_common_gray2")));
                this.n.setTextSize(12.0f);
            }
        }
    }

    /* renamed from: com.sobot.chat.fragment.SobotPostMsgFragment$13  reason: invalid class name */
    class AnonymousClass13 implements View.OnClickListener {
        AnonymousClass13() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            SobotPostMsgFragment.this.L.dismiss();
            if (view.getId() == SobotPostMsgFragment.this.e("btn_take_photo")) {
                m.d("\u62cd\u7167");
                SobotPostMsgFragment.this.V();
            }
            if (view.getId() == SobotPostMsgFragment.this.e("btn_pick_photo")) {
                m.d("\u9009\u62e9\u7167\u7247");
                SobotPostMsgFragment.this.W();
            }
            if (view.getId() == SobotPostMsgFragment.this.e("btn_pick_vedio")) {
                m.d("\u9009\u62e9\u89c6\u9891");
                SobotPostMsgFragment.this.X();
            }
        }
    }

    @Override // com.sobot.chat.listener.b
    public void a(View view, int i, SobotFieldModel sobotFieldModel) {
        if (i == 3 || i == 4) {
            com.sobot.chat.presenter.a.a(Z(), view, i);
        } else if (i == 6 || i == 7 || i == 8) {
            com.sobot.chat.presenter.a.a(Z(), this, sobotFieldModel);
        }
    }
}
