package com.sobot.chat.viewHolder;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.sobot.chat.api.model.SobotMultiDiaRespInfo;
import com.sobot.chat.api.model.ZhiChiMessageBase;
import com.sobot.chat.utils.c;
import com.sobot.chat.utils.q;
import com.sobot.chat.utils.r;
import com.sobot.chat.viewHolder.base.MessageHolderBase;
import java.util.List;
import java.util.Map;

/* compiled from: RobotTemplateMessageHolder5 */
public class j extends MessageHolderBase {
    public ZhiChiMessageBase a;
    private TextView b;
    private TextView c;
    private LinearLayout d;
    private TextView e;

    public j(Context context, View view) {
        super(context, view);
        this.c = (TextView) view.findViewById(q.a(context, "id", "sobot_template5_msg"));
        this.b = (TextView) view.findViewById(q.a(context, "id", "sobot_template5_title"));
        this.d = (LinearLayout) view.findViewById(q.a(context, "id", "sobot_ll_transferBtn"));
        this.e = (TextView) view.findViewById(q.a(context, "id", "sobot_tv_transferBtn"));
        this.e.setText(q.f(context, "sobot_transfer_to_customer_service"));
        this.b.setMaxWidth(r.a((Activity) this.n) - r.a(this.n, 102.0f));
        this.c.setMaxWidth(r.a((Activity) this.n) - r.a(this.n, 102.0f));
    }

    @Override // com.sobot.chat.viewHolder.base.MessageHolderBase
    public void a(Context context, ZhiChiMessageBase zhiChiMessageBase) {
        this.a = zhiChiMessageBase;
        if (!(zhiChiMessageBase.getAnswer() == null || zhiChiMessageBase.getAnswer().getMultiDiaRespInfo() == null)) {
            m();
            SobotMultiDiaRespInfo multiDiaRespInfo = zhiChiMessageBase.getAnswer().getMultiDiaRespInfo();
            this.c.setText(c.a(multiDiaRespInfo));
            a(this.c);
            List<Map<String, String>> interfaceRetList = multiDiaRespInfo.getInterfaceRetList();
            if (!"000000".equals(multiDiaRespInfo.getRetCode()) || interfaceRetList == null || interfaceRetList.size() <= 0) {
                l();
            } else {
                Map<String, String> map = interfaceRetList.get(0);
                if (map != null && map.size() > 0) {
                    k();
                    com.sobot.chat.utils.j.a(context).a(this.b, map.get("title"), i());
                }
            }
        }
        c();
    }

    private void k() {
        this.c.setVisibility(0);
        this.b.setVisibility(0);
    }

    private void l() {
        this.c.setVisibility(0);
        this.b.setVisibility(8);
    }

    private void m() {
        if (this.a.getTransferType() == 4) {
            b();
        } else {
            a();
        }
    }

    public void a() {
        this.d.setVisibility(8);
        this.e.setVisibility(8);
        ZhiChiMessageBase zhiChiMessageBase = this.a;
        if (zhiChiMessageBase != null) {
            zhiChiMessageBase.setShowTransferBtn(false);
        }
    }

    public void b() {
        this.e.setVisibility(0);
        this.d.setVisibility(0);
        ZhiChiMessageBase zhiChiMessageBase = this.a;
        if (zhiChiMessageBase != null) {
            zhiChiMessageBase.setShowTransferBtn(true);
        }
        this.d.setOnClickListener(new RobotTemplateMessageHolder5$1(this));
    }

    public void c() {
        if (this.a != null && this.C != null && this.D != null && this.A != null && this.B != null) {
            int revaluateState = this.a.getRevaluateState();
            if (revaluateState == 1) {
                d();
            } else if (revaluateState == 2) {
                f();
            } else if (revaluateState != 3) {
                e();
            } else {
                g();
            }
        }
    }

    public void d() {
        this.C.setVisibility(0);
        this.D.setVisibility(0);
        this.A.setVisibility(0);
        this.B.setVisibility(0);
        this.z.setVisibility(0);
        this.C.setEnabled(true);
        this.D.setEnabled(true);
        this.C.setSelected(false);
        this.D.setSelected(false);
        this.C.setOnClickListener(new RobotTemplateMessageHolder5$2(this));
        this.D.setOnClickListener(new RobotTemplateMessageHolder5$3(this));
    }

    /* access modifiers changed from: private */
    public void b(boolean z) {
        if (this.p != null && this.a != null) {
            this.p.b(z, this.a);
        }
    }

    public void e() {
        this.C.setVisibility(8);
        this.D.setVisibility(8);
        this.A.setVisibility(8);
        this.B.setVisibility(8);
        this.z.setVisibility(8);
    }

    public void f() {
        this.C.setSelected(true);
        this.C.setEnabled(false);
        this.D.setEnabled(false);
        this.D.setSelected(false);
        this.C.setVisibility(0);
        this.D.setVisibility(8);
        this.A.setVisibility(0);
        this.B.setVisibility(8);
        this.z.setVisibility(0);
    }

    public void g() {
        this.D.setSelected(true);
        this.D.setEnabled(false);
        this.C.setEnabled(false);
        this.C.setSelected(false);
        this.C.setVisibility(8);
        this.D.setVisibility(0);
        this.A.setVisibility(8);
        this.B.setVisibility(0);
        this.z.setVisibility(0);
    }
}
