package com.sobot.chat.viewHolder;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import com.sobot.chat.activity.WebViewActivity;
import com.sobot.chat.api.apiUtils.GsonUtil;
import com.sobot.chat.api.model.SobotMultiDiaRespInfo;
import com.sobot.chat.api.model.ZhiChiMessageBase;
import com.sobot.chat.utils.c;
import com.sobot.chat.utils.j;
import com.sobot.chat.utils.q;
import com.sobot.chat.utils.r;
import com.sobot.chat.utils.s;
import com.sobot.chat.utils.y;
import com.sobot.chat.viewHolder.base.MessageHolderBase;
import com.sobot.chat.widget.horizontalgridpage.HorizontalGridPage;
import com.sobot.chat.widget.horizontalgridpage.PageGridAdapter;
import com.sobot.chat.widget.horizontalgridpage.PagerGridLayoutManager;
import com.sobot.chat.widget.horizontalgridpage.a;
import com.sobot.chat.widget.horizontalgridpage.b;
import com.sobot.chat.widget.lablesview.SobotLablesViewModel;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* compiled from: RobotTemplateMessageHolder2 */
public class g extends MessageHolderBase {
    public ZhiChiMessageBase a;
    private TextView b;
    private LinearLayout c;
    private TextView d;
    private TextView e;
    private TextView f;
    private LinearLayout g;
    private PageGridAdapter h;
    private HorizontalGridPage i;
    private Context j;
    private com.sobot.chat.widget.horizontalgridpage.a k;

    public g(Context context, View view) {
        super(context, view);
        this.b = (TextView) view.findViewById(q.a(context, "id", "sobot_template2_msg"));
        this.i = (HorizontalGridPage) view.findViewById(q.a(context, "id", "pageView"));
        this.c = (LinearLayout) view.findViewById(q.a(context, "id", "sobot_ll_transferBtn"));
        this.d = (TextView) view.findViewById(q.a(context, "id", "sobot_tv_transferBtn"));
        this.d.setText(q.f(context, "sobot_transfer_to_customer_service"));
        this.e = (TextView) view.findViewById(q.a(context, "id", "sobot_template2_item_previous_page"));
        this.f = (TextView) view.findViewById(q.a(context, "id", "sobot_template2_item_last_page"));
        this.g = (LinearLayout) view.findViewById(q.a(context, "id", "ll_sobot_template2_item_page"));
        this.j = context;
    }

    public void a(int i, int i2, String str) {
        Context context;
        float f;
        if (this.k == null) {
            a.C0145a e = new a.C0145a().b(i, i2).c(10).a(5, 10, 5, 10).a(10).a(17301609, 17301611).b(17).d(40).a(false).e(2);
            if ("0".equals(str)) {
                context = this.j;
                f = 42.0f;
            } else {
                context = this.j;
                f = 36.0f;
            }
            this.k = e.f(r.a(context, f)).a();
            this.h = new PageGridAdapter(new AnonymousClass1(str));
            this.i.a(this.k, this.a.getCurrentPageNum());
            this.h.a(this.k);
            this.i.a(this.h, this.a);
        }
    }

    /* compiled from: RobotTemplateMessageHolder2 */
    /* access modifiers changed from: package-private */
    /* renamed from: com.sobot.chat.viewHolder.g$1  reason: invalid class name */
    public class AnonymousClass1 implements b {
        final /* synthetic */ String a;

        @Override // com.sobot.chat.widget.horizontalgridpage.b
        public void b(View view, int i) {
        }

        AnonymousClass1(String str) {
            this.a = str;
        }

        @Override // com.sobot.chat.widget.horizontalgridpage.b
        public RecyclerView.ViewHolder a(ViewGroup viewGroup, int i) {
            return new a(LayoutInflater.from(viewGroup.getContext()).inflate(q.a(viewGroup.getContext(), "sobot_chat_msg_item_template2_item_l"), viewGroup, false), viewGroup.getContext());
        }

        @Override // com.sobot.chat.widget.horizontalgridpage.b
        public void a(RecyclerView.ViewHolder viewHolder, int i) {
            SobotLablesViewModel sobotLablesViewModel = (SobotLablesViewModel) g.this.h.a().get(i);
            a aVar = (a) viewHolder;
            aVar.a.setText(sobotLablesViewModel.getTitle());
            g.this.h.b().getAnswer().getMultiDiaRespInfo();
            if ("1".equals(this.a)) {
                aVar.b.setBackground(null);
                TextView textView = aVar.a;
                textView.setText((i + 1) + "\u3001 " + sobotLablesViewModel.getTitle());
                aVar.a.setGravity(19);
                aVar.a.setMaxLines(2);
                aVar.a.setPadding(0, 0, 0, 0);
                if (g.this.h.b().getSugguestionsFontColor() == 0) {
                    aVar.a.setTextColor(ContextCompat.getColor(g.this.j, q.c(g.this.j, "sobot_color_link")));
                } else {
                    aVar.a.setTextColor(ContextCompat.getColor(g.this.j, q.c(g.this.j, "sobot_common_gray1")));
                }
            }
        }

        @Override // com.sobot.chat.widget.horizontalgridpage.b
        public void a(View view, int i) {
            if (g.this.a != null && g.this.a.getAnswer() != null) {
                String b = s.b(g.this.j, "lastCid", "");
                if (g.this.h.b().getSugguestionsFontColor() == 0 && !TextUtils.isEmpty(g.this.h.b().getCid()) && b.equals(g.this.h.b().getCid())) {
                    if (g.this.h.b().getAnswer().getMultiDiaRespInfo().getClickFlag() != 0 || g.this.h.b().getClickCount() <= 0) {
                        g.this.h.b().addClickCount();
                        SobotMultiDiaRespInfo multiDiaRespInfo = g.this.a.getAnswer().getMultiDiaRespInfo();
                        SobotLablesViewModel sobotLablesViewModel = (SobotLablesViewModel) g.this.h.a().get(i);
                        if (multiDiaRespInfo == null || !multiDiaRespInfo.getEndFlag() || TextUtils.isEmpty(sobotLablesViewModel.getAnchor())) {
                            g.this.a(sobotLablesViewModel, multiDiaRespInfo, i);
                        } else if (y.b == null || !y.b.a(sobotLablesViewModel.getAnchor())) {
                            Intent intent = new Intent(g.this.j, WebViewActivity.class);
                            intent.putExtra("url", sobotLablesViewModel.getAnchor());
                            g.this.j.startActivity(intent);
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
                a(this.b);
                j.a(context).a(this.b, a2, i());
                this.v.setVisibility(0);
            } else {
                this.v.setVisibility(4);
            }
            k();
            if ("000000".equals(multiDiaRespInfo.getRetCode())) {
                List<Map<String, String>> interfaceRetList = multiDiaRespInfo.getInterfaceRetList();
                String[] inputContentList = multiDiaRespInfo.getInputContentList();
                ArrayList arrayList = new ArrayList();
                if (interfaceRetList != null && interfaceRetList.size() > 0) {
                    for (int i = 0; i < a(multiDiaRespInfo, interfaceRetList.size()); i++) {
                        Map<String, String> map = interfaceRetList.get(i);
                        SobotLablesViewModel sobotLablesViewModel = new SobotLablesViewModel();
                        sobotLablesViewModel.setTitle(map.get("title"));
                        sobotLablesViewModel.setAnchor(map.get("anchor"));
                        arrayList.add(sobotLablesViewModel);
                    }
                    if (arrayList.size() >= 10) {
                        a(10, 1, "0");
                        this.g.setVisibility(0);
                    } else {
                        a(arrayList.size(), (int) Math.ceil((double) (((float) arrayList.size()) / 10.0f)), "0");
                        this.g.setVisibility(8);
                    }
                    this.h.a(arrayList);
                    this.h.a(zhiChiMessageBase);
                } else if (inputContentList == null || inputContentList.length <= 0) {
                    this.i.setVisibility(8);
                } else {
                    for (int i2 = 0; i2 < a(multiDiaRespInfo, inputContentList.length); i2++) {
                        SobotLablesViewModel sobotLablesViewModel2 = new SobotLablesViewModel();
                        sobotLablesViewModel2.setTitle(inputContentList[i2]);
                        arrayList.add(sobotLablesViewModel2);
                    }
                    if (arrayList.size() >= 10) {
                        a(10, 1, multiDiaRespInfo.getTemplate());
                        this.g.setVisibility(0);
                    } else {
                        a(arrayList.size(), (int) Math.ceil((double) (((float) arrayList.size()) / 10.0f)), multiDiaRespInfo.getTemplate());
                        this.g.setVisibility(8);
                    }
                    this.h.a(arrayList);
                    this.h.a(zhiChiMessageBase);
                }
            } else {
                this.i.setVisibility(8);
            }
        }
        this.i.setPageListener(new AnonymousClass2(context));
        this.e.setOnClickListener(new RobotTemplateMessageHolder2$3(this, context));
        this.f.setOnClickListener(new RobotTemplateMessageHolder2$4(this, context));
        c();
        this.i.a();
    }

    /* compiled from: RobotTemplateMessageHolder2 */
    /* renamed from: com.sobot.chat.viewHolder.g$2  reason: invalid class name */
    class AnonymousClass2 implements PagerGridLayoutManager.a {
        final /* synthetic */ Context a;

        @Override // com.sobot.chat.widget.horizontalgridpage.PagerGridLayoutManager.a
        public void a(int i) {
        }

        AnonymousClass2(Context context) {
            this.a = context;
        }

        @Override // com.sobot.chat.widget.horizontalgridpage.PagerGridLayoutManager.a
        public void b(int i) {
            if (g.this.i.d()) {
                TextView textView = g.this.e;
                Context context = this.a;
                textView.setTextColor(ContextCompat.getColor(context, q.c(context, "sobot_common_gray3")));
                Drawable drawable = g.this.j.getResources().getDrawable(q.e(g.this.j, "sobot_no_pre_page"));
                if (drawable != null) {
                    drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                    g.this.e.setCompoundDrawables(null, null, drawable, null);
                }
            } else {
                TextView textView2 = g.this.e;
                Context context2 = this.a;
                textView2.setTextColor(ContextCompat.getColor(context2, q.c(context2, "sobot_common_gray2")));
                Drawable drawable2 = g.this.j.getResources().getDrawable(q.e(g.this.j, "sobot_pre_page"));
                if (drawable2 != null) {
                    drawable2.setBounds(0, 0, drawable2.getMinimumWidth(), drawable2.getMinimumHeight());
                    g.this.e.setCompoundDrawables(null, null, drawable2, null);
                }
            }
            if (g.this.i.e()) {
                TextView textView3 = g.this.f;
                Context context3 = this.a;
                textView3.setTextColor(ContextCompat.getColor(context3, q.c(context3, "sobot_common_gray3")));
                Drawable drawable3 = g.this.j.getResources().getDrawable(q.e(g.this.j, "sobot_no_last_page"));
                if (drawable3 != null) {
                    drawable3.setBounds(0, 0, drawable3.getMinimumWidth(), drawable3.getMinimumHeight());
                    g.this.f.setCompoundDrawables(null, null, drawable3, null);
                    return;
                }
                return;
            }
            TextView textView4 = g.this.f;
            Context context4 = this.a;
            textView4.setTextColor(ContextCompat.getColor(context4, q.c(context4, "sobot_common_gray2")));
            Drawable drawable4 = g.this.j.getResources().getDrawable(q.e(g.this.j, "sobot_last_page"));
            if (drawable4 != null) {
                drawable4.setBounds(0, 0, drawable4.getMinimumWidth(), drawable4.getMinimumHeight());
                g.this.f.setCompoundDrawables(null, null, drawable4, null);
            }
        }
    }

    public void a(Context context) {
        this.f.setTextColor(ContextCompat.getColor(context, q.c(context, "sobot_common_gray2")));
        Drawable drawable = this.j.getResources().getDrawable(q.e(this.j, "sobot_last_page"));
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        this.f.setCompoundDrawables(null, null, drawable, null);
        Drawable drawable2 = this.j.getResources().getDrawable(q.e(this.j, "sobot_pre_page"));
        this.e.setTextColor(ContextCompat.getColor(context, q.c(context, "sobot_common_gray2")));
        if (this.i.d()) {
            this.e.setTextColor(ContextCompat.getColor(context, q.c(context, "sobot_common_gray3")));
            drawable2 = this.j.getResources().getDrawable(q.e(this.j, "sobot_no_pre_page"));
        }
        if (drawable2 != null) {
            drawable2.setBounds(0, 0, drawable2.getMinimumWidth(), drawable2.getMinimumHeight());
            this.e.setCompoundDrawables(null, null, drawable2, null);
        }
    }

    public void b(Context context) {
        this.e.setTextColor(ContextCompat.getColor(context, q.c(context, "sobot_common_gray2")));
        Drawable drawable = this.j.getResources().getDrawable(q.e(this.j, "sobot_pre_page"));
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        this.e.setCompoundDrawables(null, null, drawable, null);
        this.f.setTextColor(ContextCompat.getColor(context, q.c(context, "sobot_common_gray2")));
        Drawable drawable2 = this.j.getResources().getDrawable(q.e(this.j, "sobot_last_page"));
        if (this.i.e()) {
            this.f.setTextColor(ContextCompat.getColor(context, q.c(context, "sobot_common_gray3")));
            drawable2 = this.j.getResources().getDrawable(q.e(this.j, "sobot_no_last_page"));
        }
        if (drawable2 != null) {
            drawable2.setBounds(0, 0, drawable2.getMinimumWidth(), drawable2.getMinimumHeight());
            this.f.setCompoundDrawables(null, null, drawable2, null);
        }
    }

    private int a(SobotMultiDiaRespInfo sobotMultiDiaRespInfo, int i) {
        if (sobotMultiDiaRespInfo == null) {
            return 0;
        }
        return Math.min(sobotMultiDiaRespInfo.getPageNum() * 30, i);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void a(SobotLablesViewModel sobotLablesViewModel, SobotMultiDiaRespInfo sobotMultiDiaRespInfo, int i) {
        if (sobotMultiDiaRespInfo != null) {
            String title = sobotLablesViewModel.getTitle();
            String[] outPutParamList = sobotMultiDiaRespInfo.getOutPutParamList();
            if (!(this.p == null || this.a == null)) {
                ZhiChiMessageBase zhiChiMessageBase = new ZhiChiMessageBase();
                HashMap hashMap = new HashMap();
                hashMap.put("level", sobotMultiDiaRespInfo.getLevel());
                hashMap.put("conversationId", sobotMultiDiaRespInfo.getConversationId());
                if (outPutParamList != null) {
                    if (outPutParamList.length == 1) {
                        hashMap.put(outPutParamList[0], sobotLablesViewModel.getTitle());
                    } else if (sobotMultiDiaRespInfo.getInterfaceRetList() != null && sobotMultiDiaRespInfo.getInterfaceRetList().size() > 0) {
                        for (String str : outPutParamList) {
                            hashMap.put(str, sobotMultiDiaRespInfo.getInterfaceRetList().get(i).get(str));
                        }
                    }
                }
                zhiChiMessageBase.setContent(GsonUtil.map2Str(hashMap));
                zhiChiMessageBase.setId(System.currentTimeMillis() + "");
                this.p.a(zhiChiMessageBase, 4, 2, title, title);
            }
        }
    }

    /* compiled from: RobotTemplateMessageHolder2 */
    class a extends RecyclerView.ViewHolder {
        TextView a;
        LinearLayout b;

        public a(View view, Context context) {
            super(view);
            this.b = (LinearLayout) view.findViewById(q.a(context, "id", "sobot_template_item_ll"));
            this.a = (TextView) view.findViewById(q.a(context, "id", "sobot_template_item_title"));
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
        this.c.setVisibility(8);
        this.d.setVisibility(8);
        ZhiChiMessageBase zhiChiMessageBase = this.a;
        if (zhiChiMessageBase != null) {
            zhiChiMessageBase.setShowTransferBtn(false);
        }
    }

    public void b() {
        this.d.setVisibility(0);
        this.c.setVisibility(0);
        ZhiChiMessageBase zhiChiMessageBase = this.a;
        if (zhiChiMessageBase != null) {
            zhiChiMessageBase.setShowTransferBtn(true);
        }
        this.c.setOnClickListener(new RobotTemplateMessageHolder2$5(this));
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
        this.C.setOnClickListener(new RobotTemplateMessageHolder2$6(this));
        this.D.setOnClickListener(new RobotTemplateMessageHolder2$7(this));
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
