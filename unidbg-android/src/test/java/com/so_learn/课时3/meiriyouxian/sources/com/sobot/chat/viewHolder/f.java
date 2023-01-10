package com.sobot.chat.viewHolder;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.sobot.chat.activity.WebViewActivity;
import com.sobot.chat.api.model.SobotMultiDiaRespInfo;
import com.sobot.chat.api.model.ZhiChiMessageBase;
import com.sobot.chat.utils.c;
import com.sobot.chat.utils.j;
import com.sobot.chat.utils.q;
import com.sobot.chat.utils.r;
import com.sobot.chat.utils.s;
import com.sobot.chat.utils.t;
import com.sobot.chat.utils.y;
import com.sobot.chat.viewHolder.base.MessageHolderBase;
import com.sobot.chat.widget.horizontalgridpage.HorizontalGridPage;
import com.sobot.chat.widget.horizontalgridpage.PageGridAdapter;
import com.sobot.chat.widget.horizontalgridpage.a;
import com.sobot.chat.widget.horizontalgridpage.b;
import com.sobot.chat.widget.image.SobotRCImageView;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/* compiled from: RobotTemplateMessageHolder1 */
public class f extends MessageHolderBase {
    public ZhiChiMessageBase a;
    private TextView b;
    private LinearLayout c;
    private LinearLayout d;
    private TextView e;
    private PageGridAdapter f;
    private HorizontalGridPage g;
    private Context h;
    private com.sobot.chat.widget.horizontalgridpage.a i;

    public f(Context context, View view) {
        super(context, view);
        this.b = (TextView) view.findViewById(q.a(context, "id", "sobot__template1_msg"));
        this.c = (LinearLayout) view.findViewById(q.a(context, "id", "sobot_ll_content"));
        this.g = (HorizontalGridPage) view.findViewById(q.a(context, "id", "pageView"));
        this.d = (LinearLayout) view.findViewById(q.a(context, "id", "sobot_ll_transferBtn"));
        this.e = (TextView) view.findViewById(q.a(context, "id", "sobot_tv_transferBtn"));
        this.e.setText(q.f(context, "sobot_transfer_to_customer_service"));
        this.h = context;
    }

    public void a(int i, int i2) {
        if (this.i == null) {
            this.i = new a.C0145a().b(i, i2).c(0).a(5, 10, 5, 10).a(10).a(17301609, 17301611).b(17).d(40).a(true).e(5).f(r.a(this.h, 125.0f)).a();
            this.f = new PageGridAdapter(new AnonymousClass1());
            this.g.a(this.i, this.a.getCurrentPageNum());
            this.f.a(this.i);
            this.g.a(this.f, this.a);
        }
    }

    /* compiled from: RobotTemplateMessageHolder1 */
    /* access modifiers changed from: package-private */
    /* renamed from: com.sobot.chat.viewHolder.f$1  reason: invalid class name */
    public class AnonymousClass1 implements b {
        @Override // com.sobot.chat.widget.horizontalgridpage.b
        public void b(View view, int i) {
        }

        AnonymousClass1() {
        }

        @Override // com.sobot.chat.widget.horizontalgridpage.b
        public RecyclerView.ViewHolder a(ViewGroup viewGroup, int i) {
            return new a(LayoutInflater.from(viewGroup.getContext()).inflate(q.a(viewGroup.getContext(), "sobot_chat_msg_item_template1_item_l"), viewGroup, false), viewGroup.getContext());
        }

        @Override // com.sobot.chat.widget.horizontalgridpage.b
        public void a(RecyclerView.ViewHolder viewHolder, int i) {
            Map map = (Map) f.this.f.a().get(i);
            if (!TextUtils.isEmpty((CharSequence) map.get("thumbnail"))) {
                a aVar = (a) viewHolder;
                aVar.b.setVisibility(0);
                aVar.d.setMaxLines(1);
                aVar.d.setEllipsize(TextUtils.TruncateAt.END);
                t.a(f.this.h, (String) map.get("thumbnail"), aVar.b, q.e(f.this.h, "sobot_bg_default_pic_img"), q.e(f.this.h, "sobot_bg_default_pic_img"));
            } else {
                ((a) viewHolder).b.setVisibility(8);
            }
            a aVar2 = (a) viewHolder;
            aVar2.c.setText((CharSequence) map.get("title"));
            aVar2.d.setText((CharSequence) map.get("summary"));
            aVar2.e.setText((CharSequence) map.get("label"));
            aVar2.f.setText((CharSequence) map.get("tag"));
            if (!TextUtils.isEmpty((CharSequence) map.get("label"))) {
                aVar2.e.setVisibility(0);
            } else {
                aVar2.e.setVisibility(8);
            }
        }

        @Override // com.sobot.chat.widget.horizontalgridpage.b
        public void a(View view, int i) {
            String b = s.b(f.this.h, "lastCid", "");
            if (f.this.f.b().getSugguestionsFontColor() == 0 && !TextUtils.isEmpty(f.this.f.b().getCid()) && b.equals(f.this.f.b().getCid())) {
                if (f.this.f.b().getAnswer().getMultiDiaRespInfo().getClickFlag() != 0 || f.this.f.b().getClickCount() <= 0) {
                    f.this.f.b().addClickCount();
                    SobotMultiDiaRespInfo multiDiaRespInfo = f.this.f.b().getAnswer().getMultiDiaRespInfo();
                    Map map = (Map) f.this.f.a().get(i);
                    if (f.this.h != null && multiDiaRespInfo != null && map != null) {
                        if (!multiDiaRespInfo.getEndFlag() || TextUtils.isEmpty((CharSequence) map.get("anchor"))) {
                            c.a(f.this.h, multiDiaRespInfo, map, f.this.p);
                        } else if (y.b == null || !y.b.a((String) map.get("anchor"))) {
                            Intent intent = new Intent(f.this.h, WebViewActivity.class);
                            intent.putExtra("url", (String) map.get("anchor"));
                            f.this.h.startActivity(intent);
                        }
                    }
                }
            }
        }
    }

    @Override // com.sobot.chat.viewHolder.base.MessageHolderBase
    public void a(Context context, ZhiChiMessageBase zhiChiMessageBase) {
        this.a = zhiChiMessageBase;
        if (!(zhiChiMessageBase.getAnswer() == null || zhiChiMessageBase.getAnswer().getMultiDiaRespInfo() == null)) {
            SobotMultiDiaRespInfo multiDiaRespInfo = zhiChiMessageBase.getAnswer().getMultiDiaRespInfo();
            String a2 = c.a(multiDiaRespInfo);
            if (!TextUtils.isEmpty(a2)) {
                j.a(context).a(this.b, a2.replaceAll("\n", "<br/>"), i());
                this.c.setVisibility(0);
            } else {
                this.c.setVisibility(4);
            }
            k();
            List<Map<String, String>> interfaceRetList = multiDiaRespInfo.getInterfaceRetList();
            if (!"000000".equals(multiDiaRespInfo.getRetCode()) || interfaceRetList == null || interfaceRetList.size() <= 0) {
                this.g.setVisibility(8);
            } else {
                this.g.setVisibility(0);
                if (interfaceRetList.size() >= 3) {
                    a(3, 1);
                } else {
                    a(interfaceRetList.size(), (int) Math.ceil((double) (((float) interfaceRetList.size()) / 3.0f)));
                }
                this.f.a((ArrayList) interfaceRetList);
                this.f.a(zhiChiMessageBase);
            }
        }
        a(this.b);
        c();
        this.g.a();
    }

    /* compiled from: RobotTemplateMessageHolder1 */
    class a extends RecyclerView.ViewHolder {
        LinearLayout a;
        SobotRCImageView b;
        TextView c;
        TextView d;
        TextView e;
        TextView f;

        public a(View view, Context context) {
            super(view);
            this.a = (LinearLayout) view.findViewById(q.a(context, "id", "sobot_template1_item_"));
            this.b = (SobotRCImageView) view.findViewById(q.a(context, "id", "sobot_template1_item_thumbnail"));
            this.c = (TextView) view.findViewById(q.a(context, "id", "sobot_template1_item_title"));
            this.d = (TextView) view.findViewById(q.a(context, "id", "sobot_template1_item_summary"));
            this.e = (TextView) view.findViewById(q.a(context, "id", "sobot_template1_item_lable"));
            this.f = (TextView) view.findViewById(q.a(context, "id", "sobot_template1_item_other_flag"));
        }
    }

    private void k() {
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
        this.d.setOnClickListener(new RobotTemplateMessageHolder1$2(this));
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
        this.C.setOnClickListener(new RobotTemplateMessageHolder1$3(this));
        this.D.setOnClickListener(new RobotTemplateMessageHolder1$4(this));
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
