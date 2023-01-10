package com.sobot.chat.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telecom.Logging.Session;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import com.bangcle.andjni.JniLib;
import com.sobot.chat.activity.base.SobotDialogBaseActivity;
import com.sobot.chat.adapter.b;
import com.sobot.chat.api.apiUtils.ZhiChiConstants;
import com.sobot.chat.api.model.SobotCusFieldConfig;
import com.sobot.chat.api.model.SobotCusFieldDataInfo;
import com.sobot.chat.api.model.SobotFieldModel;
import com.sobot.chat.application.MyApplication;
import com.sobot.chat.core.http.a;
import com.sobot.chat.utils.d;
import com.sobot.chat.utils.q;
import com.sobot.chat.utils.r;
import com.sobot.chat.utils.s;
import com.xiaomi.mipush.sdk.Constants;
import java.util.ArrayList;
import java.util.List;

public class SobotCusFieldActivity extends SobotDialogBaseActivity {
    private int a;
    private SobotCusFieldConfig d;
    private List<SobotCusFieldDataInfo> e = new ArrayList();
    private SobotFieldModel f;
    private ListView g;
    private b h;
    private Bundle i;
    private StringBuffer j = new StringBuffer();
    private String k;
    private StringBuffer l = new StringBuffer();
    private LinearLayout m;
    private TextView n;
    private Button o;
    private EditText p;
    private LinearLayout q;
    private LinearLayout r;
    private float s;

    /* access modifiers changed from: protected */
    @Override // com.sobot.chat.activity.base.SobotBaseActivity
    public int a() {
        return q.a(this, "sobot_activity_cusfield");
    }

    @Override // com.sobot.chat.activity.base.SobotBaseActivity
    public void b() {
        this.s = ((float) r.b(this)) * 0.7f;
        this.n = (TextView) findViewById(q.a(this, "id", "sobot_tv_title"));
        this.m = (LinearLayout) findViewById(q.a(this, "id", "sobot_btn_cancle"));
        this.p = (EditText) findViewById(q.a(this, "id", "sobot_et_search"));
        this.p.setHint(q.f(this, "sobot_search"));
        this.q = (LinearLayout) findViewById(q.a(this, "id", "sobot_ll_search"));
        this.o = (Button) findViewById(q.a(this, "id", "sobot_btn_submit"));
        this.o.setText(q.f(this, "sobot_btn_submit"));
        this.r = (LinearLayout) findViewById(q.a(this, "id", "sobot_ll_submit"));
        this.g = (ListView) findViewById(q.g(getBaseContext(), "sobot_activity_cusfield_listview"));
        this.g.setOnItemClickListener(new AnonymousClass1(this));
        this.m.setOnClickListener(new AnonymousClass2(this));
        this.o.setOnClickListener(new AnonymousClass3(this));
        this.p.addTextChangedListener(new AnonymousClass4(this));
        a(this, this.p);
    }

    /* renamed from: com.sobot.chat.activity.SobotCusFieldActivity$1  reason: invalid class name */
    class AnonymousClass1 implements AdapterView.OnItemClickListener {
        final /* synthetic */ SobotCusFieldActivity a;

        AnonymousClass1(SobotCusFieldActivity sobotCusFieldActivity) {
            JniLib.cV(this, sobotCusFieldActivity, 1005);
        }

        @Override // android.widget.AdapterView.OnItemClickListener
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
            if (!(this.a.e == null || this.a.e.size() == 0)) {
                if (this.a.a == 7) {
                    this.a.j.delete(0, this.a.j.length());
                    this.a.l.delete(0, this.a.l.length());
                    if (((SobotCusFieldDataInfo) this.a.e.get(i)).isChecked()) {
                        ((SobotCusFieldDataInfo) this.a.e.get(i)).setChecked(false);
                    } else {
                        ((SobotCusFieldDataInfo) this.a.e.get(i)).setChecked(true);
                    }
                    SobotCusFieldActivity sobotCusFieldActivity = this.a;
                    sobotCusFieldActivity.k = ((SobotCusFieldDataInfo) sobotCusFieldActivity.e.get(0)).getFieldId();
                    for (int i2 = 0; i2 < this.a.e.size(); i2++) {
                        if (((SobotCusFieldDataInfo) this.a.e.get(i2)).isChecked()) {
                            this.a.j.append(((SobotCusFieldDataInfo) this.a.e.get(i2)).getDataName() + Constants.ACCEPT_TIME_SEPARATOR_SP);
                            this.a.l.append(((SobotCusFieldDataInfo) this.a.e.get(i2)).getDataValue() + Constants.ACCEPT_TIME_SEPARATOR_SP);
                        }
                    }
                    this.a.h.notifyDataSetChanged();
                    return;
                }
                Intent intent = new Intent();
                intent.putExtra("CATEGORYSMALL", "CATEGORYSMALL");
                intent.putExtra("fieldType", this.a.a);
                ((SobotCusFieldDataInfo) this.a.e.get(i)).setChecked(true);
                for (int i3 = 0; i3 < this.a.e.size(); i3++) {
                    if (i3 != i) {
                        ((SobotCusFieldDataInfo) this.a.e.get(i3)).setChecked(false);
                    }
                }
                intent.putExtra("category_typeName", ((SobotCusFieldDataInfo) this.a.e.get(i)).getDataName());
                intent.putExtra("category_fieldId", ((SobotCusFieldDataInfo) this.a.e.get(i)).getFieldId());
                intent.putExtra("category_typeValue", ((SobotCusFieldDataInfo) this.a.e.get(i)).getDataValue());
                this.a.setResult(304, intent);
                this.a.h.notifyDataSetChanged();
                this.a.finish();
            }
        }
    }

    /* renamed from: com.sobot.chat.activity.SobotCusFieldActivity$2  reason: invalid class name */
    class AnonymousClass2 implements View.OnClickListener {
        final /* synthetic */ SobotCusFieldActivity a;

        AnonymousClass2(SobotCusFieldActivity sobotCusFieldActivity) {
            JniLib.cV(this, sobotCusFieldActivity, 1006);
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            this.a.u();
        }
    }

    /* renamed from: com.sobot.chat.activity.SobotCusFieldActivity$3  reason: invalid class name */
    class AnonymousClass3 implements View.OnClickListener {
        final /* synthetic */ SobotCusFieldActivity a;

        AnonymousClass3(SobotCusFieldActivity sobotCusFieldActivity) {
            JniLib.cV(this, sobotCusFieldActivity, 1007);
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            this.a.d();
        }
    }

    /* renamed from: com.sobot.chat.activity.SobotCusFieldActivity$4  reason: invalid class name */
    class AnonymousClass4 implements TextWatcher {
        final /* synthetic */ SobotCusFieldActivity a;

        AnonymousClass4(SobotCusFieldActivity sobotCusFieldActivity) {
            JniLib.cV(this, sobotCusFieldActivity, 1008);
        }

        @Override // android.text.TextWatcher
        public void afterTextChanged(Editable editable) {
        }

        @Override // android.text.TextWatcher
        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        @Override // android.text.TextWatcher
        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            if (this.a.h != null) {
                this.a.h.getFilter().filter(charSequence);
            }
        }
    }

    /* access modifiers changed from: protected */
    public void d() {
        if (this.j.length() == 0 || this.k.length() == 0 || this.l.length() == 0) {
            Intent intent = new Intent();
            intent.putExtra("CATEGORYSMALL", "CATEGORYSMALL");
            intent.putExtra("fieldType", this.a);
            intent.putExtra("category_typeName", "");
            intent.putExtra("category_typeValue", "");
            intent.putExtra("category_fieldId", this.k + "");
            setResult(304, intent);
        } else {
            Intent intent2 = new Intent();
            intent2.putExtra("CATEGORYSMALL", "CATEGORYSMALL");
            intent2.putExtra("fieldType", this.a);
            intent2.putExtra("category_typeName", ((Object) this.j) + "");
            intent2.putExtra("category_typeValue", ((Object) this.l) + "");
            intent2.putExtra("category_fieldId", this.k + "");
            setResult(304, intent2);
        }
        finish();
    }

    @Override // com.sobot.chat.activity.base.SobotDialogBaseActivity, android.app.Activity
    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (motionEvent.getAction() != 0 || motionEvent.getY() > 0.0f) {
            return true;
        }
        u();
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
        a.a().a(this);
        MyApplication.getInstance().deleteActivity(this);
        super.onDestroy();
    }

    @Override // com.sobot.chat.activity.base.SobotBaseActivity
    public void c() {
        String[] g;
        this.i = getIntent().getBundleExtra("bundle");
        Bundle bundle = this.i;
        if (bundle != null) {
            this.a = bundle.getInt("fieldType");
            if (this.i.getSerializable("cusFieldConfig") != null) {
                this.d = (SobotCusFieldConfig) this.i.getSerializable("cusFieldConfig");
            }
            if (this.i.getSerializable("cusFieldList") != null) {
                this.f = (SobotFieldModel) this.i.getSerializable("cusFieldList");
            }
        }
        SobotCusFieldConfig sobotCusFieldConfig = this.d;
        if (sobotCusFieldConfig != null && !TextUtils.isEmpty(sobotCusFieldConfig.getFieldName())) {
            this.n.setText(this.d.getFieldName());
        }
        int i = this.a;
        if (7 == i) {
            this.r.setVisibility(0);
            this.q.setVisibility(8);
        } else if (6 == i) {
            this.r.setVisibility(8);
            this.q.setVisibility(0);
        }
        SobotFieldModel sobotFieldModel = this.f;
        if (!(sobotFieldModel == null || sobotFieldModel.getCusFieldDataInfoList().size() == 0)) {
            this.e = this.f.getCusFieldDataInfoList();
            for (int i2 = 0; i2 < this.e.size(); i2++) {
                if (7 == this.a) {
                    if (!(TextUtils.isEmpty(this.d.getId()) || (g = g(this.d.getValue())) == null || g.length == 0)) {
                        for (String str : g) {
                            if (str.equals(this.e.get(i2).getDataValue())) {
                                this.e.get(i2).setChecked(true);
                            }
                        }
                    }
                } else if (!TextUtils.isEmpty(this.d.getId()) && this.d.getFieldId().equals(this.e.get(i2).getFieldId()) && this.d.isChecked() && this.d.getValue().equals(this.e.get(i2).getDataValue())) {
                    this.e.get(i2).setChecked(true);
                }
            }
            b bVar = this.h;
            if (bVar == null) {
                this.h = new b(this, this, this.e, this.a);
                this.g.setAdapter((ListAdapter) this.h);
            } else {
                bVar.notifyDataSetChanged();
            }
            a(this.g, 5, 0);
        }
    }

    private String[] g(String str) {
        return str.split(Constants.ACCEPT_TIME_SEPARATOR_SP);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void u() {
        String b = s.b(getBaseContext(), "sobot_last_current_appkey", "");
        Context applicationContext = getApplicationContext();
        if (s.b(applicationContext, b + Session.SESSION_SEPARATION_CHAR_CHILD + "initType", -1) == 2) {
            finish();
            a(1);
            return;
        }
        finish();
        a(2);
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
        u();
    }

    private void a(ListView listView, int i, int i2) {
        LinearLayout.LayoutParams layoutParams;
        ListAdapter adapter = listView.getAdapter();
        if (adapter != null) {
            View view = adapter.getView(0, null, listView);
            view.measure(0, 0);
            int measuredHeight = view.getMeasuredHeight() * adapter.getCount();
            if (this.s < ((float) (r.a((Context) this, 60.0f) + measuredHeight))) {
                layoutParams = new LinearLayout.LayoutParams(-1, (int) (this.s - ((float) r.a((Context) this, 60.0f))));
            } else {
                layoutParams = new LinearLayout.LayoutParams(-1, measuredHeight);
            }
            listView.setLayoutParams(layoutParams);
        }
    }
}
