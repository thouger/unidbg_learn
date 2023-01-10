package com.sobot.chat.activity;

import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListAdapter;
import android.widget.TextView;
import com.bangcle.andjni.JniLib;
import com.sobot.chat.activity.base.SobotBaseHelpCenterActivity;
import com.sobot.chat.adapter.d;
import com.sobot.chat.api.model.StCategoryModel;
import com.sobot.chat.b;
import com.sobot.chat.core.channel.a;
import com.sobot.chat.utils.ae;
import com.sobot.chat.utils.q;
import java.util.List;

public class SobotHelpCenterActivity extends SobotBaseHelpCenterActivity implements View.OnClickListener, AdapterView.OnItemClickListener {
    private View e;
    private View f;
    private TextView g;
    private TextView h;
    private GridView i;
    private d j;
    private TextView k;
    private TextView l;
    private TextView m;

    /* access modifiers changed from: protected */
    @Override // com.sobot.chat.activity.base.SobotBaseActivity
    public int a() {
        return c("sobot_activity_help_center");
    }

    /* access modifiers changed from: protected */
    @Override // com.sobot.chat.activity.base.SobotBaseActivity
    public void b() {
        setTitle(e("sobot_help_center_title"));
        b(b("sobot_btn_back_grey_selector"), "", true);
        this.e = findViewById(a("ll_empty_view"));
        this.f = findViewById(a("ll_bottom"));
        this.g = (TextView) findViewById(a("tv_sobot_layout_online_service"));
        this.h = (TextView) findViewById(a("tv_sobot_layout_online_tel"));
        this.i = (GridView) findViewById(a("sobot_gv"));
        this.k = (TextView) findViewById(a("tv_sobot_help_center_no_data"));
        this.k.setText(q.f(this, "sobot_help_center_no_data"));
        this.l = (TextView) findViewById(a("tv_sobot_help_center_no_data_describe"));
        this.l.setText(q.f(this, "sobot_help_center_no_data_describe"));
        this.m = (TextView) findViewById(a("tv_sobot_layout_online_service"));
        this.m.setText(q.f(this, "sobot_help_center_online_service"));
        this.g.setOnClickListener(this);
        this.h.setOnClickListener(this);
        this.i.setOnItemClickListener(this);
        if (this.d == null || TextUtils.isEmpty(this.d.getHelpCenterTelTitle()) || TextUtils.isEmpty(this.d.getHelpCenterTel())) {
            this.h.setVisibility(8);
        } else {
            this.h.setVisibility(0);
            this.h.setText(this.d.getHelpCenterTelTitle());
        }
        displayInNotch(this.i);
    }

    /* access modifiers changed from: protected */
    @Override // com.sobot.chat.activity.base.SobotBaseActivity
    public void c() {
        a.a(getApplicationContext()).a().getCategoryList(this, this.d.getApp_key(), new AnonymousClass1(this));
    }

    /* renamed from: com.sobot.chat.activity.SobotHelpCenterActivity$1  reason: invalid class name */
    class AnonymousClass1 implements com.sobot.chat.core.http.c.a<List<StCategoryModel>> {
        final /* synthetic */ SobotHelpCenterActivity a;

        AnonymousClass1(SobotHelpCenterActivity sobotHelpCenterActivity) {
            JniLib.cV(this, sobotHelpCenterActivity, 1011);
        }

        public void a(List<StCategoryModel> list) {
            if (list == null || list.size() <= 0) {
                this.a.e.setVisibility(0);
                this.a.i.setVisibility(8);
                return;
            }
            this.a.e.setVisibility(8);
            this.a.i.setVisibility(0);
            if (this.a.j == null) {
                SobotHelpCenterActivity sobotHelpCenterActivity = this.a;
                sobotHelpCenterActivity.j = new d(sobotHelpCenterActivity.getApplicationContext(), list);
                this.a.i.setAdapter((ListAdapter) this.a.j);
                return;
            }
            List<StCategoryModel> d = this.a.j.d();
            d.clear();
            d.addAll(list);
            this.a.j.notifyDataSetChanged();
        }

        @Override // com.sobot.chat.core.http.c.a
        public void a(Exception exc, String str) {
            ae.a(this.a.getApplicationContext(), str);
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view == this.g) {
            b.a(getApplicationContext(), this.d);
        }
        if (view == this.h && !TextUtils.isEmpty(this.d.getHelpCenterTel())) {
            com.sobot.chat.utils.d.a(this.d.getHelpCenterTel(), q());
        }
    }

    @Override // android.widget.AdapterView.OnItemClickListener
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        startActivity(SobotProblemCategoryActivity.a(getApplicationContext(), this.d, this.j.d().get(i)));
    }
}
