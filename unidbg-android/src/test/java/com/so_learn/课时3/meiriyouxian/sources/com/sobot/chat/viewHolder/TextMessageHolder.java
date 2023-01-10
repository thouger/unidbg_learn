package com.sobot.chat.viewHolder;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.sobot.chat.adapter.e;
import com.sobot.chat.api.model.ZhiChiMessageBase;
import com.sobot.chat.listener.NoDoubleClickListener;
import com.sobot.chat.utils.ae;
import com.sobot.chat.utils.j;
import com.sobot.chat.utils.q;
import com.sobot.chat.utils.r;
import com.sobot.chat.viewHolder.base.MessageHolderBase;

public class TextMessageHolder extends MessageHolderBase {
    TextView a;
    TextView b;

    public TextMessageHolder(Context context, View view) {
        super(context, view);
        this.a = (TextView) view.findViewById(q.g(context, "sobot_msg"));
        this.b = (TextView) view.findViewById(q.g(context, "sobot_tv_icon"));
        TextView textView = this.b;
        if (textView != null) {
            textView.setText(q.f(context, "sobot_leavemsg_title"));
        }
        this.z = (RelativeLayout) view.findViewById(q.a(context, "id", "sobot_right_empty_rl"));
        this.A = (LinearLayout) view.findViewById(q.a(context, "id", "sobot_ll_likeBtn"));
        this.B = (LinearLayout) view.findViewById(q.a(context, "id", "sobot_ll_dislikeBtn"));
        this.C = (TextView) view.findViewById(q.a(context, "id", "sobot_tv_likeBtn"));
        this.D = (TextView) view.findViewById(q.a(context, "id", "sobot_tv_dislikeBtn"));
        this.a.setMaxWidth(r.a((Activity) this.n) - r.a(this.n, 102.0f));
    }

    @Override // com.sobot.chat.viewHolder.base.MessageHolderBase
    public void a(Context context, ZhiChiMessageBase zhiChiMessageBase) {
        if (zhiChiMessageBase.getAnswer() == null || (TextUtils.isEmpty(zhiChiMessageBase.getAnswer().getMsg()) && TextUtils.isEmpty(zhiChiMessageBase.getAnswer().getMsgTransfer()))) {
            this.a.setText("");
        } else {
            String msgTransfer = !TextUtils.isEmpty(zhiChiMessageBase.getAnswer().getMsgTransfer()) ? zhiChiMessageBase.getAnswer().getMsgTransfer() : zhiChiMessageBase.getAnswer().getMsg();
            int i = 0;
            this.a.setVisibility(0);
            j a = j.a(context);
            TextView textView = this.a;
            boolean z = this.o;
            a.a(textView, msgTransfer, i());
            a(this.a);
            if (this.o) {
                try {
                    this.t.setClickable(true);
                    if (zhiChiMessageBase.getSendSuccessState() == 1) {
                        this.t.setVisibility(8);
                        this.u.setVisibility(8);
                    } else if (zhiChiMessageBase.getSendSuccessState() == 0) {
                        this.t.setVisibility(0);
                        this.u.setVisibility(8);
                        this.t.setOnClickListener(new ReSendTextLisenter(context, zhiChiMessageBase.getId(), msgTransfer, this.t, this.p));
                    } else if (zhiChiMessageBase.getSendSuccessState() == 2) {
                        this.u.setVisibility(0);
                        this.t.setVisibility(8);
                    }
                    if (this.b != null) {
                        TextView textView2 = this.b;
                        if (!zhiChiMessageBase.isLeaveMsgFlag()) {
                            i = 8;
                        }
                        textView2.setVisibility(i);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        this.a.setOnLongClickListener(new AnonymousClass1(context));
        a();
    }

    /* renamed from: com.sobot.chat.viewHolder.TextMessageHolder$1  reason: invalid class name */
    class AnonymousClass1 implements View.OnLongClickListener {
        final /* synthetic */ Context a;

        AnonymousClass1(Context context) {
            this.a = context;
        }

        @Override // android.view.View.OnLongClickListener
        public boolean onLongClick(View view) {
            if (!TextUtils.isEmpty(TextMessageHolder.this.a.getText().toString())) {
                ae.a(this.a, view, TextMessageHolder.this.a.getText().toString().replace("&amp;", "&"), 30, 0);
            }
            return false;
        }
    }

    public static class ReSendTextLisenter implements View.OnClickListener {
        private String a;
        private String b;
        private ImageView c;
        private Context d;
        private e.a e;

        public ReSendTextLisenter(Context context, String str, String str2, ImageView imageView, e.a aVar) {
            this.d = context;
            this.e = aVar;
            this.a = str;
            this.b = str2;
            this.c = imageView;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            ImageView imageView = this.c;
            if (imageView != null) {
                imageView.setClickable(false);
            }
            a(this.d, this.a, this.b, this.c);
        }

        /* access modifiers changed from: package-private */
        /* renamed from: com.sobot.chat.viewHolder.TextMessageHolder$ReSendTextLisenter$1  reason: invalid class name */
        public class AnonymousClass1 implements MessageHolderBase.a {
            final /* synthetic */ Context a;
            final /* synthetic */ String b;
            final /* synthetic */ String c;

            AnonymousClass1(Context context, String str, String str2) {
                this.a = context;
                this.b = str;
                this.c = str2;
            }

            @Override // com.sobot.chat.viewHolder.base.MessageHolderBase.a
            public void a() {
                ReSendTextLisenter.this.a(this.a, this.b, this.c);
            }
        }

        private void a(Context context, String str, String str2, ImageView imageView) {
            MessageHolderBase.a(context, imageView, new AnonymousClass1(context, str, str2));
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void a(Context context, String str, String str2) {
            if (this.e != null) {
                ZhiChiMessageBase zhiChiMessageBase = new ZhiChiMessageBase();
                zhiChiMessageBase.setContent(str2);
                zhiChiMessageBase.setId(str);
                this.e.a(zhiChiMessageBase, 1, 0, "");
            }
        }
    }

    public void a() {
        if (this.m != null && this.C != null && this.D != null && this.A != null && this.B != null) {
            int revaluateState = this.m.getRevaluateState();
            if (revaluateState == 1) {
                b();
            } else if (revaluateState == 2) {
                d();
            } else if (revaluateState != 3) {
                c();
            } else {
                e();
            }
        }
    }

    public void b() {
        this.C.setVisibility(0);
        this.D.setVisibility(0);
        this.A.setVisibility(0);
        this.B.setVisibility(0);
        this.z.setVisibility(0);
        this.a.setMinHeight(r.a(this.n, 52.0f));
        this.C.setEnabled(true);
        this.D.setEnabled(true);
        this.C.setSelected(false);
        this.D.setSelected(false);
        this.C.setOnClickListener(new AnonymousClass2());
        this.D.setOnClickListener(new AnonymousClass3());
    }

    /* access modifiers changed from: package-private */
    /* renamed from: com.sobot.chat.viewHolder.TextMessageHolder$2  reason: invalid class name */
    public class AnonymousClass2 extends NoDoubleClickListener {
        AnonymousClass2() {
        }

        @Override // com.sobot.chat.listener.NoDoubleClickListener
        public void a(View view) {
            TextMessageHolder.this.b(true);
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: com.sobot.chat.viewHolder.TextMessageHolder$3  reason: invalid class name */
    public class AnonymousClass3 extends NoDoubleClickListener {
        AnonymousClass3() {
        }

        @Override // com.sobot.chat.listener.NoDoubleClickListener
        public void a(View view) {
            TextMessageHolder.this.b(false);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void b(boolean z) {
        if (this.p != null && this.m != null) {
            this.p.b(z, this.m);
        }
    }

    public void c() {
        this.C.setVisibility(8);
        this.D.setVisibility(8);
        this.A.setVisibility(8);
        this.B.setVisibility(8);
        this.z.setVisibility(8);
        this.a.setMinHeight(r.a(this.n, 22.0f));
    }

    public void d() {
        this.C.setSelected(true);
        this.C.setEnabled(false);
        this.D.setEnabled(false);
        this.D.setSelected(false);
        this.C.setVisibility(0);
        this.D.setVisibility(8);
        this.A.setVisibility(0);
        this.B.setVisibility(8);
        this.z.setVisibility(0);
        this.a.setMinHeight(r.a(this.n, 52.0f));
    }

    public void e() {
        this.D.setSelected(true);
        this.D.setEnabled(false);
        this.C.setEnabled(false);
        this.C.setSelected(false);
        this.C.setVisibility(8);
        this.D.setVisibility(0);
        this.A.setVisibility(8);
        this.B.setVisibility(0);
        this.z.setVisibility(0);
        this.a.setMinHeight(r.a(this.n, 52.0f));
    }
}
