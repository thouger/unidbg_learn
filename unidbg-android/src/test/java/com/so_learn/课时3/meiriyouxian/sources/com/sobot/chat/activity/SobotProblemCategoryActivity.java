package com.sobot.chat.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import com.bangcle.andjni.JniLib;
import com.sobot.chat.activity.base.SobotBaseHelpCenterActivity;
import com.sobot.chat.adapter.a;
import com.sobot.chat.api.model.Information;
import com.sobot.chat.api.model.StCategoryModel;
import com.sobot.chat.api.model.StDocModel;
import com.sobot.chat.utils.ae;
import com.sobot.chat.utils.q;
import java.util.List;

public class SobotProblemCategoryActivity extends SobotBaseHelpCenterActivity implements AdapterView.OnItemClickListener {
    private StCategoryModel e;
    private ListView f;
    private TextView g;
    private a h;

    /* access modifiers changed from: protected */
    @Override // com.sobot.chat.activity.base.SobotBaseActivity
    public int a() {
        return c("sobot_activity_problem_category");
    }

    public static Intent a(Context context, Information information, StCategoryModel stCategoryModel) {
        Intent intent = new Intent(context, SobotProblemCategoryActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("sobot_bundle_info", information);
        intent.putExtra("sobot_bundle_information", bundle);
        intent.putExtra("EXTRA_KEY_CATEGORY", stCategoryModel);
        return intent;
    }

    /* access modifiers changed from: protected */
    @Override // com.sobot.chat.activity.base.SobotBaseHelpCenterActivity, com.sobot.chat.activity.base.SobotBaseActivity
    public void a(Bundle bundle) {
        super.a(bundle);
        Intent intent = getIntent();
        if (intent != null) {
            this.e = (StCategoryModel) intent.getSerializableExtra("EXTRA_KEY_CATEGORY");
        }
    }

    /* access modifiers changed from: protected */
    @Override // com.sobot.chat.activity.base.SobotBaseActivity
    public void b() {
        b(b("sobot_btn_back_grey_selector"), "", true);
        this.f = (ListView) findViewById(a("sobot_listview"));
        this.g = (TextView) findViewById(a("sobot_tv_empty"));
        this.g.setText(q.f(this, "sobot_no_content"));
        setTitle(this.e.getCategoryName());
        this.f.setOnItemClickListener(this);
    }

    /* access modifiers changed from: protected */
    @Override // com.sobot.chat.activity.base.SobotBaseActivity
    public void c() {
        com.sobot.chat.core.channel.a.a(getApplicationContext()).a().getHelpDocByCategoryId(this, this.e.getAppId(), this.e.getCategoryId(), new AnonymousClass1(this));
    }

    /* renamed from: com.sobot.chat.activity.SobotProblemCategoryActivity$1  reason: invalid class name */
    class AnonymousClass1 implements com.sobot.chat.core.http.c.a<List<StDocModel>> {
        final /* synthetic */ SobotProblemCategoryActivity a;

        AnonymousClass1(SobotProblemCategoryActivity sobotProblemCategoryActivity) {
            JniLib.cV(this, sobotProblemCategoryActivity, 1026);
        }

        public void a(List<StDocModel> list) {
            if (list != null) {
                if (this.a.h == null) {
                    SobotProblemCategoryActivity sobotProblemCategoryActivity = this.a;
                    sobotProblemCategoryActivity.h = new a(sobotProblemCategoryActivity.getApplicationContext(), this.a, list);
                    this.a.f.setAdapter((ListAdapter) this.a.h);
                } else {
                    List<StDocModel> d = this.a.h.d();
                    d.clear();
                    d.addAll(list);
                    this.a.h.notifyDataSetChanged();
                }
            }
            if (list == null || list.size() == 0) {
                this.a.g.setVisibility(0);
                this.a.f.setVisibility(8);
                return;
            }
            this.a.g.setVisibility(8);
            this.a.f.setVisibility(0);
        }

        @Override // com.sobot.chat.core.http.c.a
        public void a(Exception exc, String str) {
            ae.a(this.a.getApplicationContext(), str);
        }
    }

    @Override // android.widget.AdapterView.OnItemClickListener
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        startActivity(SobotProblemDetailActivity.a(getApplicationContext(), this.d, this.h.d().get(i)));
    }
}
