package com.sobot.chat.viewHolder;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.sobot.chat.api.model.SobotMultiDiaRespInfo;
import com.sobot.chat.api.model.ZhiChiMessageBase;
import com.sobot.chat.utils.c;
import com.sobot.chat.utils.j;
import com.sobot.chat.utils.q;
import com.sobot.chat.utils.t;
import com.sobot.chat.viewHolder.base.MessageHolderBase;
import java.util.List;
import java.util.Map;

/* compiled from: RobotTemplateMessageHolder4 */
public class i extends MessageHolderBase {
    public ZhiChiMessageBase a;
    private TextView b;
    private ImageView c;
    private TextView d;
    private TextView e;
    private TextView f;
    private LinearLayout g;
    private TextView h;

    public i(Context context, View view) {
        super(context, view);
        this.b = (TextView) view.findViewById(q.a(context, "id", "sobot_template4_temp_title"));
        this.c = (ImageView) view.findViewById(q.a(context, "id", "sobot_template4_thumbnail"));
        this.d = (TextView) view.findViewById(q.a(context, "id", "sobot_template4_title"));
        this.e = (TextView) view.findViewById(q.a(context, "id", "sobot_template4_summary"));
        this.f = (TextView) view.findViewById(q.a(context, "id", "sobot_template4_anchor"));
        this.f.setText(q.f(context, "sobot_see_detail"));
        this.g = (LinearLayout) view.findViewById(q.a(context, "id", "sobot_ll_transferBtn"));
        this.h = (TextView) view.findViewById(q.a(context, "id", "sobot_tv_transferBtn"));
        this.h.setText(q.f(context, "sobot_transfer_to_customer_service"));
    }

    @Override // com.sobot.chat.viewHolder.base.MessageHolderBase
    public void a(Context context, ZhiChiMessageBase zhiChiMessageBase) {
        this.a = zhiChiMessageBase;
        if (!(zhiChiMessageBase.getAnswer() == null || zhiChiMessageBase.getAnswer().getMultiDiaRespInfo() == null)) {
            m();
            SobotMultiDiaRespInfo multiDiaRespInfo = zhiChiMessageBase.getAnswer().getMultiDiaRespInfo();
            String a = c.a(multiDiaRespInfo);
            if (!TextUtils.isEmpty(a)) {
                j.a(context).a(this.b, a.replaceAll("\n", "<br/>"), i());
                this.v.setVisibility(0);
            } else {
                this.v.setVisibility(4);
            }
            if ("000000".equals(multiDiaRespInfo.getRetCode())) {
                List<Map<String, String>> interfaceRetList = multiDiaRespInfo.getInterfaceRetList();
                if (interfaceRetList == null || interfaceRetList.size() <= 0) {
                    this.d.setText(multiDiaRespInfo.getAnswerStrip());
                    l();
                } else {
                    Map<String, String> map = interfaceRetList.get(0);
                    if (map != null && map.size() > 0) {
                        k();
                        j.a(context).a(this.d, map.get("title"), i());
                        if (!TextUtils.isEmpty(map.get("thumbnail"))) {
                            t.a(context, map.get("thumbnail"), this.c, q.a(context, "drawable", "sobot_bg_default_long_pic"), q.a(context, "drawable", "sobot_bg_default_long_pic"));
                            this.c.setVisibility(0);
                        } else {
                            this.c.setVisibility(8);
                        }
                        this.e.setText(map.get("summary"));
                        if (multiDiaRespInfo.getEndFlag() && map.get("anchor") != null) {
                            this.f.setOnClickListener(new RobotTemplateMessageHolder4$1(this, map, context));
                        }
                    }
                }
            } else {
                this.d.setText(multiDiaRespInfo.getRetErrorMsg());
                l();
            }
        }
        c();
    }

    private void k() {
        this.d.setVisibility(0);
        this.c.setVisibility(0);
        this.e.setVisibility(0);
        this.f.setVisibility(0);
    }

    private void l() {
        this.d.setVisibility(0);
        this.c.setVisibility(8);
        this.b.setVisibility(8);
        this.e.setVisibility(8);
        this.f.setVisibility(8);
    }

    private void m() {
        if (this.a.getTransferType() == 4) {
            b();
        } else {
            a();
        }
    }

    public void a() {
        this.g.setVisibility(8);
        this.h.setVisibility(8);
        ZhiChiMessageBase zhiChiMessageBase = this.a;
        if (zhiChiMessageBase != null) {
            zhiChiMessageBase.setShowTransferBtn(false);
        }
    }

    public void b() {
        this.h.setVisibility(0);
        this.g.setVisibility(0);
        ZhiChiMessageBase zhiChiMessageBase = this.a;
        if (zhiChiMessageBase != null) {
            zhiChiMessageBase.setShowTransferBtn(true);
        }
        this.g.setOnClickListener(new RobotTemplateMessageHolder4$2(this));
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
        this.C.setOnClickListener(new RobotTemplateMessageHolder4$3(this));
        this.D.setOnClickListener(new RobotTemplateMessageHolder4$4(this));
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
