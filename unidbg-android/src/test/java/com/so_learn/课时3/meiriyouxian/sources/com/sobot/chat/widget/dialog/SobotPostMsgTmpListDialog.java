package com.sobot.chat.widget.dialog;

import android.app.Activity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.TextView;
import com.sobot.chat.adapter.i;
import com.sobot.chat.api.model.SobotPostMsgTemplate;
import com.sobot.chat.utils.q;
import java.util.ArrayList;

public class SobotPostMsgTmpListDialog extends com.sobot.chat.widget.dialog.a.a implements View.OnClickListener, AdapterView.OnItemClickListener {
    private final String b = SobotPostMsgTmpListDialog.class.getSimpleName();
    private LinearLayout c;
    private LinearLayout d;
    private GridView e;
    private ArrayList<SobotPostMsgTemplate> f;
    private a g;
    private i h;
    private TextView i;

    public interface a {
        void a(SobotPostMsgTemplate sobotPostMsgTemplate);
    }

    /* access modifiers changed from: protected */
    @Override // com.sobot.chat.widget.dialog.a.a
    public String a() {
        return "sobot_layout_post_msg_tmps";
    }

    public SobotPostMsgTmpListDialog(Activity activity, ArrayList<SobotPostMsgTemplate> arrayList, a aVar) {
        super(activity);
        this.f = arrayList;
        this.g = aVar;
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
        this.e = (GridView) findViewById(b("sobot_gv"));
        this.e.setOnItemClickListener(this);
        this.d.setOnClickListener(this);
        this.i = (TextView) findViewById(b("sobot_tv_title"));
        this.i.setText(q.f(getContext(), "sobot_choice_business"));
    }

    /* access modifiers changed from: protected */
    @Override // com.sobot.chat.widget.dialog.a.a
    public void d() {
        if (this.h == null) {
            this.h = new i(getContext(), this.f);
            this.e.setAdapter((ListAdapter) this.h);
        }
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

    @Override // android.widget.AdapterView.OnItemClickListener
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        if (this.g != null) {
            this.g.a((SobotPostMsgTemplate) this.h.getItem(i));
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
