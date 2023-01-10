package com.sobot.chat.widget.dialog;

import android.content.Intent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.TextView;
import com.bangcle.andjni.JniLib;
import com.sobot.chat.activity.base.SobotDialogBaseActivity;
import com.sobot.chat.adapter.k;
import com.sobot.chat.api.model.SobotRobot;
import com.sobot.chat.core.channel.a;
import com.sobot.chat.utils.q;
import java.util.Iterator;
import java.util.List;

public class SobotRobotListActivity extends SobotDialogBaseActivity implements View.OnClickListener, AdapterView.OnItemClickListener {
    private final String a = SobotRobotListActivity.class.getSimpleName();
    private LinearLayout d;
    private GridView e;
    private TextView f;
    private String g;
    private String h;
    private k i;

    /* access modifiers changed from: protected */
    @Override // com.sobot.chat.activity.base.SobotBaseActivity
    public int a() {
        return c("sobot_layout_switch_robot");
    }

    /* access modifiers changed from: protected */
    @Override // com.sobot.chat.activity.base.SobotBaseActivity
    public void b() {
        Window window = getWindow();
        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.width = -1;
        attributes.height = -2;
        attributes.gravity = 80;
        window.setAttributes(attributes);
        this.d = (LinearLayout) findViewById(a("sobot_negativeButton"));
        this.f = (TextView) findViewById(a("sobot_tv_title"));
        this.f.setText(q.f(t(), "sobot_switch_robot_title"));
        this.e = (GridView) findViewById(a("sobot_gv"));
        this.e.setOnItemClickListener(this);
        this.d.setOnClickListener(this);
    }

    /* access modifiers changed from: protected */
    @Override // com.sobot.chat.activity.base.SobotBaseActivity
    public void c() {
        this.g = getIntent().getStringExtra("partnerid");
        this.h = getIntent().getStringExtra("robotFlag");
        a.a(getBaseContext()).a().getRobotSwitchList(this.a, this.g, new AnonymousClass1(this));
    }

    /* renamed from: com.sobot.chat.widget.dialog.SobotRobotListActivity$1  reason: invalid class name */
    class AnonymousClass1 implements com.sobot.chat.core.http.c.a<List<SobotRobot>> {
        final /* synthetic */ SobotRobotListActivity a;

        AnonymousClass1(SobotRobotListActivity sobotRobotListActivity) {
            JniLib.cV(this, sobotRobotListActivity, 1058);
        }

        @Override // com.sobot.chat.core.http.c.a
        public void a(Exception exc, String str) {
        }

        public void a(List<SobotRobot> list) {
            Iterator<SobotRobot> it2 = list.iterator();
            while (true) {
                if (!it2.hasNext()) {
                    break;
                }
                SobotRobot next = it2.next();
                if (next.getRobotFlag() != null && next.getRobotFlag().equals(this.a.h)) {
                    next.setSelected(true);
                    break;
                }
            }
            if (this.a.i == null) {
                SobotRobotListActivity sobotRobotListActivity = this.a;
                sobotRobotListActivity.i = new k(sobotRobotListActivity.getBaseContext(), list);
                this.a.e.setAdapter((ListAdapter) this.a.i);
                return;
            }
            List d = this.a.i.d();
            d.clear();
            d.addAll(list);
            this.a.i.notifyDataSetChanged();
        }
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public void onDetachedFromWindow() {
        com.sobot.chat.core.http.a.a().a(this.a);
        super.onDetachedFromWindow();
    }

    @Override // android.widget.AdapterView.OnItemClickListener
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        SobotRobot sobotRobot = (SobotRobot) this.i.getItem(i);
        if (sobotRobot.getRobotFlag() != null && !sobotRobot.getRobotFlag().equals(this.h)) {
            Intent intent = new Intent();
            intent.putExtra("sobotRobot", sobotRobot);
            setResult(-1, intent);
            finish();
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view == this.d) {
            finish();
        }
    }
}
