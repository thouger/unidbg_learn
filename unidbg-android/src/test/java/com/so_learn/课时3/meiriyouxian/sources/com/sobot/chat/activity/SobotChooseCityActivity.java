package com.sobot.chat.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.SparseArray;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import com.bangcle.andjni.JniLib;
import com.sobot.chat.activity.base.SobotDialogBaseActivity;
import com.sobot.chat.adapter.j;
import com.sobot.chat.api.ZhiChiApi;
import com.sobot.chat.api.model.SobotCityResult;
import com.sobot.chat.api.model.SobotCusFieldConfig;
import com.sobot.chat.api.model.SobotProvinInfo;
import com.sobot.chat.core.channel.a;
import com.sobot.chat.utils.ae;
import com.sobot.chat.utils.q;
import com.sobot.chat.widget.dialog.d;
import java.util.ArrayList;
import java.util.List;

public class SobotChooseCityActivity extends SobotDialogBaseActivity {
    private Bundle a;
    private SobotCusFieldConfig d;
    private ListView e;
    private SobotProvinInfo f;
    private LinearLayout g;
    private TextView h;
    private SparseArray<List<SobotProvinInfo.SobotProvinceModel>> i = new SparseArray<>();
    private List<SobotProvinInfo.SobotProvinceModel> j = new ArrayList();
    private j k;
    private int l = 1;
    private boolean m = false;
    private String n;
    private SobotProvinInfo.SobotProvinceModel o = new SobotProvinInfo.SobotProvinceModel();

    /* access modifiers changed from: protected */
    @Override // com.sobot.chat.activity.base.SobotBaseActivity
    public int a() {
        return q.a(this, "sobot_activity_cusfield");
    }

    private void d() {
        this.a = getIntent().getBundleExtra("sobot_intent_bundle_data");
        Bundle bundle = this.a;
        if (bundle != null) {
            if (bundle.getSerializable("cusFieldConfig") != null) {
                this.d = (SobotCusFieldConfig) this.a.getSerializable("cusFieldConfig");
            }
            this.f = (SobotProvinInfo) this.a.getSerializable("sobot_intent_bundle_data_provininfo");
        }
        SobotCusFieldConfig sobotCusFieldConfig = this.d;
        if (sobotCusFieldConfig != null && !TextUtils.isEmpty(sobotCusFieldConfig.getFieldName())) {
            this.h.setText(this.d.getFieldName());
        }
        this.n = this.a.getString("sobot_intent_bundle_data_field_id");
        SobotProvinInfo sobotProvinInfo = this.f;
        if (sobotProvinInfo != null && sobotProvinInfo.getProvinces() != null) {
            this.l = 1;
            this.i.put(1, this.f.getProvinces());
        }
    }

    @Override // com.sobot.chat.activity.base.SobotDialogBaseActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        u();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void u() {
        int i = this.l;
        if (i <= 1) {
            finish();
        } else if (!this.m) {
            this.l = i - 1;
            a((List) this.i.get(this.l));
        }
    }

    @Override // com.sobot.chat.activity.base.SobotBaseActivity
    public void b() {
        this.g = (LinearLayout) findViewById(q.a(this, "id", "sobot_btn_cancle"));
        this.h = (TextView) findViewById(q.a(this, "id", "sobot_tv_title"));
        this.e = (ListView) findViewById(q.g(getBaseContext(), "sobot_activity_cusfield_listview"));
        this.e.setOnItemClickListener(new AnonymousClass1(this));
        this.g.setOnClickListener(new AnonymousClass2(this));
    }

    /* renamed from: com.sobot.chat.activity.SobotChooseCityActivity$1  reason: invalid class name */
    class AnonymousClass1 implements AdapterView.OnItemClickListener {
        final /* synthetic */ SobotChooseCityActivity a;

        AnonymousClass1(SobotChooseCityActivity sobotChooseCityActivity) {
            JniLib.cV(this, sobotChooseCityActivity, 996);
        }

        @Override // android.widget.AdapterView.OnItemClickListener
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
            SobotProvinInfo.SobotProvinceModel sobotProvinceModel = (SobotProvinInfo.SobotProvinceModel) this.a.j.get(i);
            if (sobotProvinceModel.nodeFlag) {
                this.a.a(sobotProvinceModel);
                return;
            }
            SobotChooseCityActivity sobotChooseCityActivity = this.a;
            sobotChooseCityActivity.a(sobotChooseCityActivity.l - 1, sobotProvinceModel);
            Intent intent = new Intent();
            intent.putExtra("sobot_intent_bundle_data_provininfo", this.a.o);
            intent.putExtra("sobot_intent_bundle_data_field_id", this.a.n);
            this.a.setResult(106, intent);
            int i2 = 0;
            while (i2 < ((List) this.a.i.get(this.a.l)).size()) {
                ((SobotProvinInfo.SobotProvinceModel) this.a.j.get(i2)).isChecked = i2 == i;
                i2++;
            }
            this.a.k.notifyDataSetChanged();
            this.a.finish();
        }
    }

    /* renamed from: com.sobot.chat.activity.SobotChooseCityActivity$2  reason: invalid class name */
    class AnonymousClass2 implements View.OnClickListener {
        final /* synthetic */ SobotChooseCityActivity a;

        AnonymousClass2(SobotChooseCityActivity sobotChooseCityActivity) {
            JniLib.cV(this, sobotChooseCityActivity, 997);
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            this.a.u();
        }
    }

    @Override // com.sobot.chat.activity.base.SobotBaseActivity
    public void c() {
        d();
        SobotProvinInfo sobotProvinInfo = this.f;
        if (sobotProvinInfo != null && sobotProvinInfo.getProvinces() != null) {
            a((SobotProvinInfo.SobotProvinceModel) null);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void a(SobotProvinInfo.SobotProvinceModel sobotProvinceModel) {
        if (sobotProvinceModel == null) {
            a(1);
        } else if (!this.m) {
            this.m = true;
            ZhiChiApi a = a.a(getBaseContext()).a();
            String str = null;
            String str2 = sobotProvinceModel.level == 0 ? sobotProvinceModel.provinceId : null;
            if (sobotProvinceModel.level == 1) {
                str = sobotProvinceModel.cityId;
            }
            a.queryCity(this, str2, str, new AnonymousClass3(this, sobotProvinceModel));
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: com.sobot.chat.activity.SobotChooseCityActivity$3  reason: invalid class name */
    public class AnonymousClass3 implements com.sobot.chat.core.http.c.a<SobotCityResult> {
        final /* synthetic */ SobotProvinInfo.SobotProvinceModel a;
        final /* synthetic */ SobotChooseCityActivity b;

        AnonymousClass3(SobotChooseCityActivity sobotChooseCityActivity, SobotProvinInfo.SobotProvinceModel sobotProvinceModel) {
            JniLib.cV(this, sobotChooseCityActivity, sobotProvinceModel, 998);
        }

        public void a(SobotCityResult sobotCityResult) {
            this.b.m = false;
            SobotProvinInfo data = sobotCityResult.getData();
            if (data.getCitys() != null && data.getCitys().size() > 0) {
                this.b.a(data.getCitys(), this.a);
            }
            if (data.getAreas() != null && data.getAreas().size() > 0) {
                this.b.a(data.getAreas(), this.a);
            }
        }

        @Override // com.sobot.chat.core.http.c.a
        public void a(Exception exc, String str) {
            this.b.m = false;
            d.b(this.b);
            ae.a(this.b.getApplicationContext(), str);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void a(List<SobotProvinInfo.SobotProvinceModel> list, SobotProvinInfo.SobotProvinceModel sobotProvinceModel) {
        a(sobotProvinceModel.level, sobotProvinceModel);
        this.l++;
        this.i.put(this.l, list);
        a(this.l);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void a(int i, SobotProvinInfo.SobotProvinceModel sobotProvinceModel) {
        if (i == 0) {
            this.o.provinceId = sobotProvinceModel.provinceId;
            this.o.provinceName = sobotProvinceModel.provinceName;
        } else if (i != 1) {
            this.o.areaId = sobotProvinceModel.areaId;
            this.o.areaName = sobotProvinceModel.areaName;
        } else {
            this.o.cityId = sobotProvinceModel.cityId;
            this.o.cityName = sobotProvinceModel.cityName;
        }
    }

    private void a(int i) {
        ArrayList arrayList = (ArrayList) this.i.get(i);
        if (arrayList != null) {
            a(arrayList);
        }
    }

    private void a(List<SobotProvinInfo.SobotProvinceModel> list) {
        this.j.clear();
        this.j.addAll(list);
        j jVar = this.k;
        if (jVar != null) {
            jVar.notifyDataSetChanged();
            return;
        }
        this.k = new j(this, this, this.j);
        this.e.setAdapter((ListAdapter) this.k);
    }

    /* access modifiers changed from: protected */
    @Override // com.sobot.chat.activity.base.SobotBaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        d.b(this);
        super.onDestroy();
    }
}
