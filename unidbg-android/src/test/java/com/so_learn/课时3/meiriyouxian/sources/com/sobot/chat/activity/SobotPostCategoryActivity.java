package com.sobot.chat.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.SparseArray;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import com.bangcle.andjni.JniLib;
import com.sobot.chat.activity.base.SobotDialogBaseActivity;
import com.sobot.chat.adapter.h;
import com.sobot.chat.api.model.SobotTypeModel;
import com.sobot.chat.utils.q;
import java.util.ArrayList;
import java.util.List;

public class SobotPostCategoryActivity extends SobotDialogBaseActivity {
    private h a;
    private ListView d;
    private LinearLayout e;
    private TextView f;
    private ImageView g;
    private List<SobotTypeModel> h = new ArrayList();
    private SparseArray<List<SobotTypeModel>> i = new SparseArray<>();
    private List<SobotTypeModel> j = new ArrayList();
    private int k = 1;
    private String l;
    private String m;

    static /* synthetic */ int c(SobotPostCategoryActivity sobotPostCategoryActivity) {
        int i = sobotPostCategoryActivity.k;
        sobotPostCategoryActivity.k = i + 1;
        return i;
    }

    /* access modifiers changed from: protected */
    @Override // com.sobot.chat.activity.base.SobotBaseActivity
    public int a() {
        return q.a(this, "sobot_activity_post_category");
    }

    /* access modifiers changed from: protected */
    @Override // com.sobot.chat.activity.base.SobotBaseActivity
    public void b() {
        this.e = (LinearLayout) findViewById(q.a(this, "id", "sobot_btn_cancle"));
        this.f = (TextView) findViewById(q.a(this, "id", "sobot_tv_title"));
        this.g = (ImageView) findViewById(q.a(this, "id", "sobot_btn_back"));
        this.d = (ListView) findViewById(q.g(getBaseContext(), "sobot_activity_post_category_listview"));
        this.d.setOnItemClickListener(new AnonymousClass1(this));
        this.e.setOnClickListener(new AnonymousClass2(this));
        this.g.setOnClickListener(new AnonymousClass3(this));
    }

    /* renamed from: com.sobot.chat.activity.SobotPostCategoryActivity$1  reason: invalid class name */
    class AnonymousClass1 implements AdapterView.OnItemClickListener {
        final /* synthetic */ SobotPostCategoryActivity a;

        AnonymousClass1(SobotPostCategoryActivity sobotPostCategoryActivity) {
            JniLib.cV(this, sobotPostCategoryActivity, 1020);
        }

        @Override // android.widget.AdapterView.OnItemClickListener
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
            if (1 == ((SobotTypeModel) ((List) this.a.i.get(this.a.k)).get(i)).getNodeFlag()) {
                SobotPostCategoryActivity.c(this.a);
                this.a.a(i);
                return;
            }
            Intent intent = new Intent();
            intent.putExtra("category_typeName", ((SobotTypeModel) ((List) this.a.i.get(this.a.k)).get(i)).getTypeName());
            intent.putExtra("category_typeId", ((SobotTypeModel) ((List) this.a.i.get(this.a.k)).get(i)).getTypeId());
            this.a.setResult(304, intent);
            int i2 = 0;
            while (i2 < ((List) this.a.i.get(this.a.k)).size()) {
                ((SobotTypeModel) ((List) this.a.i.get(this.a.k)).get(i2)).setChecked(i2 == i);
                i2++;
            }
            this.a.a.notifyDataSetChanged();
            this.a.finish();
        }
    }

    /* renamed from: com.sobot.chat.activity.SobotPostCategoryActivity$2  reason: invalid class name */
    class AnonymousClass2 implements View.OnClickListener {
        final /* synthetic */ SobotPostCategoryActivity a;

        AnonymousClass2(SobotPostCategoryActivity sobotPostCategoryActivity) {
            JniLib.cV(this, sobotPostCategoryActivity, 1021);
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            this.a.finish();
        }
    }

    /* renamed from: com.sobot.chat.activity.SobotPostCategoryActivity$3  reason: invalid class name */
    class AnonymousClass3 implements View.OnClickListener {
        final /* synthetic */ SobotPostCategoryActivity a;

        AnonymousClass3(SobotPostCategoryActivity sobotPostCategoryActivity) {
            JniLib.cV(this, sobotPostCategoryActivity, 1022);
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            this.a.d();
        }
    }

    /* access modifiers changed from: protected */
    @Override // com.sobot.chat.activity.base.SobotBaseActivity
    public void c() {
        ArrayList arrayList;
        this.h.clear();
        Bundle bundleExtra = getIntent().getBundleExtra("bundle");
        if (bundleExtra != null) {
            this.l = bundleExtra.getString("typeName");
            this.m = bundleExtra.getString("typeId");
            arrayList = (ArrayList) bundleExtra.getSerializable("types");
        } else {
            arrayList = null;
        }
        if (arrayList != null) {
            this.h.addAll(arrayList);
        }
        this.f.setText(q.f(getBaseContext(), "sobot_choice_classification"));
        this.k = 1;
        this.i.put(1, this.h);
        List<SobotTypeModel> list = this.h;
        if (!(list == null || list.size() == 0)) {
            a(-1);
        }
        this.g.setVisibility(8);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void a(int i) {
        this.g.setVisibility(this.k > 1 ? 0 : 8);
        if (i >= 0) {
            SparseArray<List<SobotTypeModel>> sparseArray = this.i;
            int i2 = this.k;
            sparseArray.put(i2, ((SobotTypeModel) ((List) sparseArray.get(i2 - 1)).get(i)).getItems());
        }
        ArrayList<SobotTypeModel> arrayList = (ArrayList) this.i.get(this.k);
        if (arrayList != null) {
            a(arrayList);
            a((List<SobotTypeModel>) arrayList);
        }
    }

    private void a(List<SobotTypeModel> list) {
        this.j.clear();
        this.j.addAll(list);
        h hVar = this.a;
        if (hVar != null) {
            hVar.notifyDataSetChanged();
            return;
        }
        this.a = new h(this, this, this.j);
        this.d.setAdapter((ListAdapter) this.a);
    }

    @Override // com.sobot.chat.activity.base.SobotDialogBaseActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        d();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void d() {
        int i = this.k;
        if (i <= 1) {
            finish();
            return;
        }
        this.k = i - 1;
        if (this.k == 1) {
            this.g.setVisibility(8);
        }
        if (this.k > 1) {
            this.g.setVisibility(0);
        }
        a((List) this.i.get(this.k));
    }

    private void a(ArrayList<SobotTypeModel> arrayList) {
        for (int i = 0; i < arrayList.size(); i++) {
            if (!TextUtils.isEmpty(this.m) && this.m.equals(arrayList.get(i).getTypeId())) {
                arrayList.get(i).setChecked(true);
            }
        }
    }
}
