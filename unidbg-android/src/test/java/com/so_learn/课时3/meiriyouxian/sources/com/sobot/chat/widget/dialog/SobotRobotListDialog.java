package com.sobot.chat.widget.dialog;

import android.app.Activity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.TextView;
import com.sobot.chat.adapter.k;
import com.sobot.chat.api.model.SobotRobot;
import com.sobot.chat.utils.q;
import java.util.Iterator;
import java.util.List;

public class SobotRobotListDialog extends com.sobot.chat.widget.dialog.a.a implements View.OnClickListener, AdapterView.OnItemClickListener {
    private final String b = SobotRobotListDialog.class.getSimpleName();
    private LinearLayout c;
    private LinearLayout d;
    private GridView e;
    private TextView f;
    private String g;
    private String h;
    private a i;
    private k j;

    public interface a {
        void a(SobotRobot sobotRobot);
    }

    /* access modifiers changed from: protected */
    @Override // com.sobot.chat.widget.dialog.a.a
    public String a() {
        return "sobot_layout_switch_robot";
    }

    public SobotRobotListDialog(Activity activity, String str, String str2, a aVar) {
        super(activity);
        this.g = str;
        this.h = str2;
        this.i = aVar;
    }

    /* access modifiers changed from: protected */
    @Override // com.sobot.chat.widget.dialog.a.a
    public View b() {
        if (this.c == null) {
            this.c = (LinearLayout) findViewById(b("sobot_container"));
        }
        return this.c;
    }

    /* access modifiers changed from: protected */
    @Override // com.sobot.chat.widget.dialog.a.a
    public void c() {
        this.d = (LinearLayout) findViewById(b("sobot_negativeButton"));
        this.f = (TextView) findViewById(b("sobot_tv_title"));
        this.f.setText(q.f(getContext(), "sobot_switch_robot_title"));
        this.e = (GridView) findViewById(b("sobot_gv"));
        this.e.setOnItemClickListener(this);
        this.d.setOnClickListener(this);
    }

    /* renamed from: com.sobot.chat.widget.dialog.SobotRobotListDialog$1  reason: invalid class name */
    class AnonymousClass1 implements com.sobot.chat.core.http.c.a<List<SobotRobot>> {
        @Override // com.sobot.chat.core.http.c.a
        public void a(Exception exc, String str) {
        }

        AnonymousClass1() {
        }

        public void a(List<SobotRobot> list) {
            Iterator<SobotRobot> it2 = list.iterator();
            while (true) {
                if (!it2.hasNext()) {
                    break;
                }
                SobotRobot next = it2.next();
                if (next.getRobotFlag() != null && next.getRobotFlag().equals(SobotRobotListDialog.this.h)) {
                    next.setSelected(true);
                    break;
                }
            }
            if (SobotRobotListDialog.this.j == null) {
                SobotRobotListDialog sobotRobotListDialog = SobotRobotListDialog.this;
                sobotRobotListDialog.j = new k(sobotRobotListDialog.getContext(), list);
                SobotRobotListDialog.this.e.setAdapter((ListAdapter) SobotRobotListDialog.this.j);
                return;
            }
            List d = SobotRobotListDialog.this.j.d();
            d.clear();
            d.addAll(list);
            SobotRobotListDialog.this.j.notifyDataSetChanged();
        }
    }

    /* access modifiers changed from: protected */
    @Override // com.sobot.chat.widget.dialog.a.a
    public void d() {
        com.sobot.chat.core.channel.a.a(getContext()).a().getRobotSwitchList(this.b, this.g, new AnonymousClass1());
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

    @Override // android.app.Dialog, android.view.Window.Callback
    public void onDetachedFromWindow() {
        com.sobot.chat.core.http.a.a().a(this.b);
        super.onDetachedFromWindow();
    }

    @Override // android.widget.AdapterView.OnItemClickListener
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        if (this.i != null) {
            SobotRobot sobotRobot = (SobotRobot) this.j.getItem(i);
            if (sobotRobot.getRobotFlag() != null && !sobotRobot.getRobotFlag().equals(this.h)) {
                this.i.a((SobotRobot) this.j.getItem(i));
            }
            dismiss();
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view == this.d) {
            dismiss();
        }
    }
}
