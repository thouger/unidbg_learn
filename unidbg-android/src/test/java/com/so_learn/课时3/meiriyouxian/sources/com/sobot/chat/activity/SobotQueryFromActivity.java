package com.sobot.chat.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.core.content.ContextCompat;
import com.bangcle.andjni.JniLib;
import com.sobot.chat.activity.base.SobotBaseActivity;
import com.sobot.chat.api.model.CommonModel;
import com.sobot.chat.api.model.SobotCityResult;
import com.sobot.chat.api.model.SobotCusFieldConfig;
import com.sobot.chat.api.model.SobotFieldModel;
import com.sobot.chat.api.model.SobotProvinInfo;
import com.sobot.chat.api.model.SobotQueryFormModel;
import com.sobot.chat.listener.b;
import com.sobot.chat.presenter.a;
import com.sobot.chat.utils.ae;
import com.sobot.chat.utils.e;
import com.sobot.chat.utils.j;
import com.sobot.chat.utils.m;
import com.sobot.chat.utils.q;
import com.sobot.chat.utils.r;
import com.sobot.chat.widget.dialog.d;
import com.sobot.chat.widget.kpswitch.util.c;
import java.util.ArrayList;

public class SobotQueryFromActivity extends SobotBaseActivity implements View.OnClickListener, b {
    private Bundle a;
    private String d;
    private SobotQueryFormModel e;
    private String f;
    private String g;
    private int h;
    private ArrayList<SobotFieldModel> i;
    private SobotProvinInfo.SobotProvinceModel j;
    private LinearLayout k;
    private TextView l;
    private Button m;
    private boolean n = false;

    /* access modifiers changed from: protected */
    @Override // com.sobot.chat.activity.base.SobotBaseActivity
    public void c() {
    }

    /* access modifiers changed from: protected */
    @Override // com.sobot.chat.activity.base.SobotBaseActivity
    public int a() {
        return c("sobot_activity_query_from");
    }

    /* access modifiers changed from: protected */
    @Override // com.sobot.chat.activity.base.SobotBaseActivity
    public void a(Bundle bundle) {
        if (bundle == null) {
            this.a = getIntent().getBundleExtra("sobot_intent_bundle_data");
        } else {
            this.a = bundle.getBundle("sobot_intent_bundle_data");
        }
        Bundle bundle2 = this.a;
        if (bundle2 != null) {
            b(bundle2);
        }
    }

    private void b(Bundle bundle) {
        this.d = bundle.getString("sobot_intent_bundle_data_groupid");
        this.f = bundle.getString("sobot_intent_bundle_data_groupname");
        this.e = (SobotQueryFormModel) bundle.getSerializable("sobot_intent_bundle_data_field");
        this.g = bundle.getString("sobot_intent_bundle_data_uid");
        this.h = bundle.getInt("sobot_intent_bundle_data_transfer_type", 0);
        SobotQueryFormModel sobotQueryFormModel = this.e;
        if (sobotQueryFormModel != null) {
            this.i = sobotQueryFormModel.getField();
        }
    }

    /* access modifiers changed from: protected */
    @Override // com.sobot.chat.activity.base.SobotBaseActivity
    public void b() {
        b(b("sobot_btn_back_selector"), "", true);
        this.m = (Button) findViewById(a("sobot_btn_submit"));
        this.m.setText(q.f(this, "sobot_btn_submit_text"));
        this.m.setOnClickListener(this);
        this.k = (LinearLayout) findViewById(a("sobot_container"));
        this.l = (TextView) findViewById(a("sobot_tv_doc"));
        SobotQueryFormModel sobotQueryFormModel = this.e;
        if (sobotQueryFormModel != null) {
            setTitle(sobotQueryFormModel.getFormTitle());
            j.a(q()).a(this.l, this.e.getFormDoc(), q.a(q(), "color", "sobot_color_link"));
        }
        displayInNotch(this.l);
        a.a(this, this, this.i, this.k, this);
    }

    /* access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onSaveInstanceState(Bundle bundle) {
        bundle.putBundle("sobot_intent_bundle_data", this.a);
        super.onSaveInstanceState(bundle);
    }

    private void d() {
        if (!this.n) {
            this.n = true;
            this.b.submitForm(this, this.g, a.a(this.i, this.j), new AnonymousClass1(this));
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: com.sobot.chat.activity.SobotQueryFromActivity$1  reason: invalid class name */
    public class AnonymousClass1 implements com.sobot.chat.core.http.c.a<CommonModel> {
        final /* synthetic */ SobotQueryFromActivity a;

        AnonymousClass1(SobotQueryFromActivity sobotQueryFromActivity) {
            JniLib.cV(this, sobotQueryFromActivity, 1029);
        }

        public void a(CommonModel commonModel) {
            this.a.n = false;
            if (commonModel != null && "1".equals(commonModel.getCode())) {
                e.a(this.a.getBaseContext(), q.f(this.a.getBaseContext(), "sobot_leavemsg_success_tip"), 1000, q.e(this.a.getBaseContext(), "sobot_iv_login_right")).show();
            }
            this.a.t();
        }

        @Override // com.sobot.chat.core.http.c.a
        public void a(Exception exc, String str) {
            this.a.n = false;
            ae.a(this.a.getApplicationContext(), str);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void t() {
        try {
            c.b(getCurrentFocus());
            Intent intent = new Intent();
            intent.putExtra("sobot_intent_bundle_data_groupid", this.d);
            intent.putExtra("sobot_intent_bundle_data_groupname", this.f);
            intent.putExtra("sobot_intent_bundle_data_transfer_type", this.h);
            setResult(104, intent);
            finish();
        } catch (Exception unused) {
        }
    }

    private boolean a(ArrayList<SobotFieldModel> arrayList) {
        if (!(arrayList == null || arrayList.size() == 0)) {
            for (int i = 0; i < arrayList.size(); i++) {
                if (arrayList.get(i).getCusFieldConfig() != null) {
                    if (1 == arrayList.get(i).getCusFieldConfig().getFillFlag()) {
                        if ("city".equals(arrayList.get(i).getCusFieldConfig().getFieldId())) {
                            if (arrayList.get(i).getCusFieldConfig().getProvinceModel() == null) {
                                ae.a(getApplicationContext(), arrayList.get(i).getCusFieldConfig().getFieldName() + "  " + e("sobot__is_null"));
                                return false;
                            }
                        } else if (TextUtils.isEmpty(arrayList.get(i).getCusFieldConfig().getValue())) {
                            ae.a(getApplicationContext(), arrayList.get(i).getCusFieldConfig().getFieldName() + "  " + e("sobot__is_null"));
                            return false;
                        }
                    }
                    if ("email".equals(arrayList.get(i).getCusFieldConfig().getFieldId()) && !TextUtils.isEmpty(arrayList.get(i).getCusFieldConfig().getValue()) && !r.b(arrayList.get(i).getCusFieldConfig().getValue())) {
                        ae.a(getApplicationContext(), e("sobot_email_dialog_hint"));
                        return false;
                    }
                }
            }
        }
        return true;
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        u();
    }

    private void u() {
        setResult(105, new Intent());
        finish();
    }

    @Override // com.sobot.chat.listener.b
    public void a(View view, int i, SobotFieldModel sobotFieldModel) {
        switch (i) {
            case 3:
            case 4:
                a.a(this, view, i);
                return;
            case 5:
            default:
                return;
            case 6:
            case 7:
            case 8:
                a.a(this, sobotFieldModel);
                return;
            case 9:
                m.d("\u70b9\u51fb\u4e86\u57ce\u5e02");
                d.a(this);
                this.b.queryCity(this, null, null, new AnonymousClass2(this, sobotFieldModel));
                return;
        }
    }

    /* renamed from: com.sobot.chat.activity.SobotQueryFromActivity$2  reason: invalid class name */
    class AnonymousClass2 implements com.sobot.chat.core.http.c.a<SobotCityResult> {
        final /* synthetic */ SobotFieldModel a;
        final /* synthetic */ SobotQueryFromActivity b;

        AnonymousClass2(SobotQueryFromActivity sobotQueryFromActivity, SobotFieldModel sobotFieldModel) {
            JniLib.cV(this, sobotQueryFromActivity, sobotFieldModel, 1030);
        }

        public void a(SobotCityResult sobotCityResult) {
            d.b(this.b);
            SobotProvinInfo data = sobotCityResult.getData();
            if (data.getProvinces() != null && data.getProvinces().size() > 0) {
                a.a(this.b, data, this.a);
            }
        }

        @Override // com.sobot.chat.core.http.c.a
        public void a(Exception exc, String str) {
            d.b(this.b);
            ae.a(this.b.getApplicationContext(), str);
        }
    }

    /* access modifiers changed from: protected */
    @Override // com.sobot.chat.activity.base.SobotBaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        d.b(this);
        super.onDestroy();
    }

    /* access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        a.a(this, intent, this.i, this.k);
        if (intent != null && i == 106) {
            String stringExtra = intent.getStringExtra("sobot_intent_bundle_data_field_id");
            this.j = (SobotProvinInfo.SobotProvinceModel) intent.getSerializableExtra("sobot_intent_bundle_data_provininfo");
            if (!(this.i == null || this.j == null || TextUtils.isEmpty(stringExtra))) {
                for (int i3 = 0; i3 < this.i.size(); i3++) {
                    SobotCusFieldConfig cusFieldConfig = this.i.get(i3).getCusFieldConfig();
                    if (cusFieldConfig != null && stringExtra.equals(cusFieldConfig.getFieldId())) {
                        cusFieldConfig.setChecked(true);
                        cusFieldConfig.setProvinceModel(this.j);
                        View findViewWithTag = this.k.findViewWithTag(stringExtra);
                        if (findViewWithTag != null) {
                            TextView textView = (TextView) findViewWithTag.findViewById(q.a(getApplicationContext(), "id", "work_order_customer_date_text_click"));
                            String str = "";
                            String str2 = this.j.provinceName == null ? str : this.j.provinceName;
                            String str3 = this.j.cityName == null ? str : this.j.cityName;
                            if (this.j.areaName != null) {
                                str = this.j.areaName;
                            }
                            textView.setText(str2 + str3 + str);
                            TextView textView2 = (TextView) findViewWithTag.findViewById(q.a(getBaseContext(), "id", "work_order_customer_field_text_lable"));
                            ((LinearLayout) findViewWithTag.findViewById(q.a(getBaseContext(), "id", "work_order_customer_field_ll"))).setVisibility(0);
                            textView2.setTextColor(ContextCompat.getColor(this, q.c(this, "sobot_common_gray2")));
                            textView2.setTextSize(12.0f);
                        }
                    }
                }
            }
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view == this.m && TextUtils.isEmpty(a.a(this, this.k, this.i)) && a(this.i)) {
            d();
        }
    }
}
