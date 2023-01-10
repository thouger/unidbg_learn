package com.sobot.chat.viewHolder;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.core.content.ContextCompat;
import com.sobot.chat.activity.SobotFileDetailActivity;
import com.sobot.chat.activity.SobotVideoActivity;
import com.sobot.chat.activity.WebViewActivity;
import com.sobot.chat.adapter.e;
import com.sobot.chat.api.model.ChatMessageRichListModel;
import com.sobot.chat.api.model.SobotCacheFile;
import com.sobot.chat.api.model.Suggestions;
import com.sobot.chat.api.model.ZhiChiMessageBase;
import com.sobot.chat.camera.c.f;
import com.sobot.chat.listener.NoDoubleClickListener;
import com.sobot.chat.utils.ae;
import com.sobot.chat.utils.c;
import com.sobot.chat.utils.j;
import com.sobot.chat.utils.m;
import com.sobot.chat.utils.q;
import com.sobot.chat.utils.r;
import com.sobot.chat.utils.t;
import com.sobot.chat.utils.y;
import com.sobot.chat.viewHolder.base.MessageHolderBase;
import com.sobot.chat.widget.attachment.a;
import java.util.ArrayList;

public class RichTextMessageHolder extends MessageHolderBase implements View.OnClickListener {
    private LinearLayout F;
    private TextView G;
    private TextView H;
    private LinearLayout I;
    private TextView J;
    private View K;
    int a = (r.a((Activity) this.n) - r.a(this.n, 102.0f));
    private TextView b;
    private LinearLayout c;
    private TextView d;
    private LinearLayout e;
    private TextView f;
    private LinearLayout g;
    private LinearLayout h;
    private TextView i;
    private RelativeLayout j;
    private LinearLayout k;
    private LinearLayout l;

    public RichTextMessageHolder(Context context, View view) {
        super(context, view);
        this.b = (TextView) view.findViewById(q.a(context, "id", "sobot_msg"));
        this.c = (LinearLayout) view.findViewById(q.a(context, "id", "sobot_rich_ll"));
        this.d = (TextView) view.findViewById(q.a(context, "id", "sobot_msgStripe"));
        this.g = (LinearLayout) view.findViewById(q.a(context, "id", "sobot_chat_more_action"));
        this.h = (LinearLayout) view.findViewById(q.a(context, "id", "sobot_ll_transferBtn"));
        this.l = (LinearLayout) view.findViewById(q.a(context, "id", "sobot_ll_likeBtn"));
        this.F = (LinearLayout) view.findViewById(q.a(context, "id", "sobot_ll_dislikeBtn"));
        this.k = (LinearLayout) view.findViewById(q.a(context, "id", "sobot_ll_content"));
        this.I = (LinearLayout) view.findViewById(q.a(context, "id", "sobot_ll_switch"));
        this.J = (TextView) view.findViewById(q.a(context, "id", "sobot_tv_switch"));
        this.J.setText(q.f(context, "sobot_switch"));
        this.K = view.findViewById(q.a(context, "id", "sobot_view_split"));
        this.j = (RelativeLayout) view.findViewById(q.a(context, "id", "sobot_right_empty_rl"));
        this.f = (TextView) view.findViewById(q.a(context, "id", "sobot_stripe"));
        this.e = (LinearLayout) view.findViewById(q.a(context, "id", "sobot_answersList"));
        this.i = (TextView) view.findViewById(q.a(context, "id", "sobot_tv_transferBtn"));
        this.i.setText(q.f(context, "sobot_transfer_to_customer_service"));
        this.G = (TextView) view.findViewById(q.a(context, "id", "sobot_tv_likeBtn"));
        this.H = (TextView) view.findViewById(q.a(context, "id", "sobot_tv_dislikeBtn"));
        this.I.setOnClickListener(this);
        this.b.setMaxWidth(this.a);
    }

    @Override // com.sobot.chat.viewHolder.base.MessageHolderBase
    public void a(Context context, ZhiChiMessageBase zhiChiMessageBase) {
        if (zhiChiMessageBase.getAnswer() != null) {
            b(context, zhiChiMessageBase);
            if (!TextUtils.isEmpty(zhiChiMessageBase.getAnswer().getMsgStripe())) {
                this.d.setVisibility(0);
                this.d.setText(zhiChiMessageBase.getAnswer().getMsgStripe());
            } else {
                this.d.setVisibility(8);
            }
        }
        String trim = zhiChiMessageBase.getStripe() != null ? zhiChiMessageBase.getStripe().trim() : "";
        if (!TextUtils.isEmpty(trim)) {
            String replace = trim.replace("<p>", "").replace("</p>", "");
            this.f.setVisibility(0);
            j.a(context).a(this.f, replace, i());
        } else {
            this.f.setText((CharSequence) null);
            this.f.setVisibility(8);
        }
        if (!zhiChiMessageBase.isGuideGroupFlag() || zhiChiMessageBase.getListSuggestions() == null || zhiChiMessageBase.getGuideGroupNum() <= -1 || zhiChiMessageBase.getListSuggestions().size() <= 0 || zhiChiMessageBase.getGuideGroupNum() >= zhiChiMessageBase.getListSuggestions().size()) {
            this.I.setVisibility(8);
            this.K.setVisibility(8);
        } else {
            this.I.setVisibility(0);
            this.K.setVisibility(0);
        }
        if (zhiChiMessageBase.getSugguestions() == null || zhiChiMessageBase.getSugguestions().length <= 0) {
            this.e.setVisibility(8);
        } else {
            k();
        }
        m();
        this.b.setOnLongClickListener(new AnonymousClass1(zhiChiMessageBase, context));
        a(this.b);
        c();
    }

    /* renamed from: com.sobot.chat.viewHolder.RichTextMessageHolder$1  reason: invalid class name */
    class AnonymousClass1 implements View.OnLongClickListener {
        final /* synthetic */ ZhiChiMessageBase a;
        final /* synthetic */ Context b;

        AnonymousClass1(ZhiChiMessageBase zhiChiMessageBase, Context context) {
            this.a = zhiChiMessageBase;
            this.b = context;
        }

        @Override // android.view.View.OnLongClickListener
        public boolean onLongClick(View view) {
            if (!TextUtils.isEmpty(this.a.getAnswer().getMsg())) {
                ae.a(this.b, view, this.a.getAnswer().getMsg(), 30, 0);
            }
            return false;
        }
    }

    private void k() {
        int i;
        int i2;
        if (this.m != null) {
            int i3 = 0;
            if (this.m.getListSuggestions() == null || this.m.getListSuggestions().size() <= 0) {
                String[] sugguestions = this.m.getSugguestions();
                this.e.removeAllViews();
                this.e.setVisibility(0);
                while (i3 < sugguestions.length) {
                    TextView a = c.a(this.n, true);
                    int i4 = i3 + 1;
                    a.setText(a(this.m, i4) + sugguestions[i3]);
                    this.e.addView(a);
                    i3 = i4;
                }
            } else {
                ArrayList<Suggestions> listSuggestions = this.m.getListSuggestions();
                this.e.removeAllViews();
                this.e.setVisibility(0);
                int size = listSuggestions.size();
                if (!this.m.isGuideGroupFlag() || this.m.getGuideGroupNum() <= -1) {
                    i = size;
                    i2 = 0;
                } else {
                    i2 = this.m.getCurrentPageNum() * this.m.getGuideGroupNum();
                    i = Math.min(this.m.getGuideGroupNum() + i2, listSuggestions.size());
                }
                while (i2 < i) {
                    TextView a2 = c.a(this.n, false);
                    int i5 = i2 + 1;
                    a2.setOnClickListener(new AnsWerClickLisenter(this.n, null, listSuggestions.get(i2).getQuestion(), null, listSuggestions.get(i2).getDocId(), this.p));
                    a2.setText(a(this.m, i5) + listSuggestions.get(i2).getQuestion());
                    this.e.addView(a2);
                    i2 = i5;
                }
            }
            l();
        }
    }

    private void l() {
        ViewGroup.LayoutParams layoutParams = this.k.getLayoutParams();
        layoutParams.width = r.a((Activity) this.n) - r.a(this.n, 72.0f);
        this.k.setLayoutParams(layoutParams);
    }

    private void m() {
        if (this.m.isShowTransferBtn()) {
            b();
        } else {
            a();
        }
    }

    private void n() {
        if (this.m.isShowTransferBtn() || this.m.getRevaluateState() != 0) {
            this.g.setVisibility(0);
        } else {
            this.g.setVisibility(8);
        }
    }

    public void a() {
        n();
        this.h.setVisibility(8);
        this.i.setVisibility(8);
        if (this.m != null) {
            this.m.setShowTransferBtn(false);
        }
    }

    public void b() {
        this.g.setVisibility(0);
        this.i.setVisibility(0);
        this.h.setVisibility(0);
        if (this.m != null) {
            this.m.setShowTransferBtn(true);
        }
        this.h.setOnClickListener(new AnonymousClass2());
    }

    /* access modifiers changed from: package-private */
    /* renamed from: com.sobot.chat.viewHolder.RichTextMessageHolder$2  reason: invalid class name */
    public class AnonymousClass2 extends NoDoubleClickListener {
        AnonymousClass2() {
        }

        @Override // com.sobot.chat.listener.NoDoubleClickListener
        public void a(View view) {
            if (RichTextMessageHolder.this.p != null) {
                RichTextMessageHolder.this.p.b();
            }
        }
    }

    public void c() {
        int revaluateState = this.m.getRevaluateState();
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

    public void d() {
        this.g.setVisibility(0);
        this.G.setVisibility(0);
        this.H.setVisibility(0);
        this.l.setVisibility(0);
        this.F.setVisibility(0);
        this.j.setVisibility(0);
        this.G.setEnabled(true);
        this.H.setEnabled(true);
        this.G.setSelected(false);
        this.H.setSelected(false);
        this.b.setMinHeight(r.a(this.n, 52.0f));
        LinearLayout linearLayout = this.c;
        if (linearLayout != null && linearLayout.getChildCount() == 1) {
            for (int i = 0; i < this.c.getChildCount(); i++) {
                View childAt = this.c.getChildAt(i);
                if (childAt instanceof TextView) {
                    ((TextView) childAt).setMinHeight(r.a(this.n, 52.0f));
                }
            }
        }
        this.G.setOnClickListener(new AnonymousClass3());
        this.H.setOnClickListener(new AnonymousClass4());
    }

    /* access modifiers changed from: package-private */
    /* renamed from: com.sobot.chat.viewHolder.RichTextMessageHolder$3  reason: invalid class name */
    public class AnonymousClass3 extends NoDoubleClickListener {
        AnonymousClass3() {
        }

        @Override // com.sobot.chat.listener.NoDoubleClickListener
        public void a(View view) {
            RichTextMessageHolder.this.b(true);
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: com.sobot.chat.viewHolder.RichTextMessageHolder$4  reason: invalid class name */
    public class AnonymousClass4 extends NoDoubleClickListener {
        AnonymousClass4() {
        }

        @Override // com.sobot.chat.listener.NoDoubleClickListener
        public void a(View view) {
            RichTextMessageHolder.this.b(false);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void b(boolean z) {
        if (this.p != null) {
            this.p.b(z, this.m);
        }
    }

    public void e() {
        n();
        this.G.setVisibility(8);
        this.H.setVisibility(8);
        this.l.setVisibility(8);
        this.j.setVisibility(8);
        this.F.setVisibility(8);
        this.b.setMinHeight(r.a(this.n, 22.0f));
    }

    public void f() {
        this.G.setSelected(true);
        this.G.setEnabled(false);
        this.H.setEnabled(false);
        this.H.setSelected(false);
        this.g.setVisibility(0);
        this.G.setVisibility(0);
        this.H.setVisibility(8);
        this.l.setVisibility(0);
        this.j.setVisibility(0);
        this.F.setVisibility(8);
        this.b.setMinHeight(r.a(this.n, 52.0f));
    }

    public void g() {
        this.H.setSelected(true);
        this.H.setEnabled(false);
        this.G.setEnabled(false);
        this.G.setSelected(false);
        this.g.setVisibility(0);
        this.G.setVisibility(8);
        this.H.setVisibility(0);
        this.j.setVisibility(0);
        this.l.setVisibility(8);
        this.F.setVisibility(0);
        this.b.setMinHeight(r.a(this.n, 52.0f));
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view == this.I && this.m != null && this.m.getListSuggestions() != null && this.m.getListSuggestions().size() > 0) {
            m.d(this.m.getCurrentPageNum() + "==================");
            int currentPageNum = this.m.getCurrentPageNum() + 1;
            int size = this.m.getListSuggestions().size();
            int guideGroupNum = this.m.getGuideGroupNum();
            int i = size % guideGroupNum;
            int i2 = size / guideGroupNum;
            if (i != 0) {
                i2++;
            }
            m.d(i2 + "=========maxNum=========");
            if (currentPageNum >= i2) {
                currentPageNum = 0;
            }
            this.m.setCurrentPageNum(currentPageNum);
            m.d(this.m.getCurrentPageNum() + "==================");
            k();
        }
    }

    public static class AnsWerClickLisenter implements View.OnClickListener {
        private String a;
        private String b;
        private ImageView c;
        private String d;
        private Context e;
        private e.a f;

        public AnsWerClickLisenter(Context context, String str, String str2, ImageView imageView, String str3, e.a aVar) {
            this.e = context;
            this.a = str2;
            this.b = str;
            this.c = imageView;
            this.d = str3;
            this.f = aVar;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            ImageView imageView = this.c;
            if (imageView != null) {
                imageView.setVisibility(8);
            }
            e.a aVar = this.f;
            if (aVar != null) {
                aVar.c();
                ZhiChiMessageBase zhiChiMessageBase = new ZhiChiMessageBase();
                zhiChiMessageBase.setContent(this.a);
                zhiChiMessageBase.setId(this.b);
                this.f.a(zhiChiMessageBase, 0, 1, this.d);
            }
        }
    }

    private void b(Context context, ZhiChiMessageBase zhiChiMessageBase) {
        String str;
        if (zhiChiMessageBase.getAnswer() == null || zhiChiMessageBase.getAnswer().getRichList() == null || zhiChiMessageBase.getAnswer().getRichList().size() <= 0) {
            this.c.setVisibility(8);
            if (zhiChiMessageBase.getAnswer() == null || TextUtils.isEmpty(zhiChiMessageBase.getAnswer().getMsg())) {
                this.b.setVisibility(8);
                return;
            }
            this.b.setVisibility(0);
            if ("9".equals(zhiChiMessageBase.getAnswer().getMsgType())) {
                str = zhiChiMessageBase.getAnswer().getMultiDiaRespInfo() != null ? zhiChiMessageBase.getAnswer().getMultiDiaRespInfo().getAnswer() : "";
            } else {
                str = zhiChiMessageBase.getAnswer().getMsg();
            }
            j.a(context).a(this.b, str, i());
            return;
        }
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -2);
        layoutParams.setMargins(0, r.a(context, 3.0f), 0, 0);
        this.c.removeAllViews();
        for (int i = 0; i < zhiChiMessageBase.getAnswer().getRichList().size(); i++) {
            ChatMessageRichListModel chatMessageRichListModel = zhiChiMessageBase.getAnswer().getRichList().get(i);
            if (chatMessageRichListModel != null) {
                if (chatMessageRichListModel.getType() == 0) {
                    TextView textView = new TextView(this.n);
                    textView.setLayoutParams(layoutParams);
                    textView.setMaxWidth(this.a);
                    if (TextUtils.isEmpty(chatMessageRichListModel.getName()) || !j.a(chatMessageRichListModel.getMsg())) {
                        textView.setTextColor(ContextCompat.getColor(this.n, q.c(this.n, "sobot_left_msg_text_color")));
                        j.a(this.n).a(textView, chatMessageRichListModel.getMsg(), i());
                    } else {
                        textView.setTextColor(ContextCompat.getColor(this.n, q.c(this.n, "sobot_color_link")));
                        textView.setOnClickListener(new AnonymousClass5(chatMessageRichListModel, context));
                        textView.setText(chatMessageRichListModel.getName());
                    }
                    this.c.addView(textView);
                } else if (chatMessageRichListModel.getType() == 1 && j.a(chatMessageRichListModel.getMsg())) {
                    LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(this.a, r.a(context, 200.0f));
                    layoutParams2.setMargins(0, r.a(context, 3.0f), 0, 0);
                    ImageView imageView = new ImageView(this.n);
                    imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
                    imageView.setLayoutParams(layoutParams2);
                    t.a(this.n, chatMessageRichListModel.getMsg(), imageView);
                    imageView.setOnClickListener(new MessageHolderBase.ImageClickLisenter(context, chatMessageRichListModel.getMsg(), this.o));
                    this.c.addView(imageView);
                } else if (chatMessageRichListModel.getType() == 3 && j.a(chatMessageRichListModel.getMsg())) {
                    TextView textView2 = new TextView(this.n);
                    textView2.setMaxWidth(this.a);
                    j.a(this.n).a(textView2, TextUtils.isEmpty(chatMessageRichListModel.getName()) ? chatMessageRichListModel.getMsg() : chatMessageRichListModel.getName(), i());
                    textView2.setLayoutParams(layoutParams);
                    textView2.setTextColor(ContextCompat.getColor(this.n, q.c(this.n, "sobot_color_link")));
                    this.c.addView(textView2);
                    textView2.setOnClickListener(new AnonymousClass6(chatMessageRichListModel, zhiChiMessageBase));
                } else if ((chatMessageRichListModel.getType() == 4 || chatMessageRichListModel.getType() == 2) && j.a(chatMessageRichListModel.getMsg())) {
                    TextView textView3 = new TextView(this.n);
                    textView3.setMaxWidth(this.a);
                    j.a(this.n).a(textView3, TextUtils.isEmpty(chatMessageRichListModel.getName()) ? chatMessageRichListModel.getMsg() : chatMessageRichListModel.getName(), i());
                    textView3.setLayoutParams(layoutParams);
                    textView3.setTextColor(ContextCompat.getColor(this.n, q.c(this.n, "sobot_color_link")));
                    this.c.addView(textView3);
                    textView3.setOnClickListener(new AnonymousClass7(chatMessageRichListModel, zhiChiMessageBase));
                }
            }
        }
        this.c.setVisibility(0);
        this.b.setVisibility(8);
        LinearLayout.LayoutParams layoutParams3 = new LinearLayout.LayoutParams(-2, -2);
        layoutParams3.leftMargin = r.a(context, 12.0f);
        this.k.setLayoutParams(layoutParams3);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: com.sobot.chat.viewHolder.RichTextMessageHolder$5  reason: invalid class name */
    public class AnonymousClass5 implements View.OnClickListener {
        final /* synthetic */ ChatMessageRichListModel a;
        final /* synthetic */ Context b;

        AnonymousClass5(ChatMessageRichListModel chatMessageRichListModel, Context context) {
            this.a = chatMessageRichListModel;
            this.b = context;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (y.b == null || !y.b.a(this.a.getMsg())) {
                Intent intent = new Intent(this.b, WebViewActivity.class);
                intent.putExtra("url", this.a.getMsg());
                this.b.startActivity(intent);
            }
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: com.sobot.chat.viewHolder.RichTextMessageHolder$6  reason: invalid class name */
    public class AnonymousClass6 implements View.OnClickListener {
        final /* synthetic */ ChatMessageRichListModel a;
        final /* synthetic */ ZhiChiMessageBase b;

        AnonymousClass6(ChatMessageRichListModel chatMessageRichListModel, ZhiChiMessageBase zhiChiMessageBase) {
            this.a = chatMessageRichListModel;
            this.b = zhiChiMessageBase;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            SobotCacheFile sobotCacheFile = new SobotCacheFile();
            sobotCacheFile.setFileName(this.a.getName());
            sobotCacheFile.setUrl(this.a.getMsg());
            sobotCacheFile.setFileType(a.a(f.b(this.a.getMsg())));
            sobotCacheFile.setMsgId(this.b.getMsgId() + this.a.getMsg());
            RichTextMessageHolder.this.n.startActivity(SobotVideoActivity.a(RichTextMessageHolder.this.n, sobotCacheFile));
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: com.sobot.chat.viewHolder.RichTextMessageHolder$7  reason: invalid class name */
    public class AnonymousClass7 implements View.OnClickListener {
        final /* synthetic */ ChatMessageRichListModel a;
        final /* synthetic */ ZhiChiMessageBase b;

        AnonymousClass7(ChatMessageRichListModel chatMessageRichListModel, ZhiChiMessageBase zhiChiMessageBase) {
            this.a = chatMessageRichListModel;
            this.b = zhiChiMessageBase;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            Intent intent = new Intent(RichTextMessageHolder.this.n, SobotFileDetailActivity.class);
            SobotCacheFile sobotCacheFile = new SobotCacheFile();
            sobotCacheFile.setFileName(this.a.getName());
            sobotCacheFile.setUrl(this.a.getMsg());
            sobotCacheFile.setFileType(a.a(f.b(this.a.getMsg())));
            sobotCacheFile.setMsgId(this.b.getMsgId() + this.a.getMsg());
            intent.putExtra("sobot_intent_data_selected_file", sobotCacheFile);
            intent.setFlags(268435456);
            RichTextMessageHolder.this.n.startActivity(intent);
        }
    }

    private String a(ZhiChiMessageBase zhiChiMessageBase, int i) {
        if (zhiChiMessageBase != null && zhiChiMessageBase.getAnswer() != null && zhiChiMessageBase.getAnswer().getMultiDiaRespInfo() != null && zhiChiMessageBase.getAnswer().getMultiDiaRespInfo().getIcLists() != null) {
            return "\u2022";
        }
        return i + ".";
    }
}
