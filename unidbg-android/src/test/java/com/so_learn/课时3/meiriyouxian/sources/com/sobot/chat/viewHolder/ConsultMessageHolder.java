package com.sobot.chat.viewHolder;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.sobot.chat.activity.WebViewActivity;
import com.sobot.chat.api.model.ZhiChiMessageBase;
import com.sobot.chat.utils.d;
import com.sobot.chat.utils.m;
import com.sobot.chat.utils.q;
import com.sobot.chat.utils.t;
import com.sobot.chat.utils.y;
import com.sobot.chat.viewHolder.base.MessageHolderBase;

public class ConsultMessageHolder extends MessageHolderBase implements View.OnClickListener {
    private TextView a;
    private ImageView b;
    private Button c;
    private View d;
    private TextView e;
    private TextView f;
    private int g;
    private ZhiChiMessageBase h;

    public ConsultMessageHolder(Context context, View view) {
        super(context, view);
        this.c = (Button) view.findViewById(q.g(context, "sobot_goods_sendBtn"));
        this.c.setText(q.f(context, "sobot_send_cus_service"));
        this.d = view.findViewById(q.g(context, "sobot_container"));
        this.b = (ImageView) view.findViewById(q.g(context, "sobot_goods_pic"));
        this.a = (TextView) view.findViewById(q.g(context, "sobot_goods_title"));
        this.e = (TextView) view.findViewById(q.g(context, "sobot_goods_label"));
        this.f = (TextView) view.findViewById(q.g(context, "sobot_goods_des"));
        this.g = q.e(context, "sobot_icon_consulting_default_pic");
        this.d.setOnClickListener(this);
    }

    @Override // com.sobot.chat.viewHolder.base.MessageHolderBase
    public void a(Context context, ZhiChiMessageBase zhiChiMessageBase) {
        this.h = zhiChiMessageBase;
        String content = zhiChiMessageBase.getContent();
        String picurl = zhiChiMessageBase.getPicurl();
        String url = zhiChiMessageBase.getUrl();
        String aname = zhiChiMessageBase.getAname();
        String receiverFace = zhiChiMessageBase.getReceiverFace();
        if (!TextUtils.isEmpty(picurl)) {
            this.b.setVisibility(0);
            this.f.setMaxLines(1);
            this.f.setEllipsize(TextUtils.TruncateAt.END);
            String b = d.b(picurl);
            ImageView imageView = this.b;
            int i = this.g;
            t.a(context, b, imageView, i, i);
        } else {
            this.b.setVisibility(8);
            this.b.setImageResource(this.g);
        }
        this.f.setText(receiverFace);
        this.a.setText(content);
        if (!TextUtils.isEmpty(aname)) {
            this.e.setVisibility(0);
            this.e.setText(aname);
        } else if (!TextUtils.isEmpty(picurl)) {
            this.e.setVisibility(4);
        } else {
            this.e.setVisibility(8);
        }
        this.c.setOnClickListener(new AnonymousClass1(url));
    }

    /* renamed from: com.sobot.chat.viewHolder.ConsultMessageHolder$1  reason: invalid class name */
    class AnonymousClass1 implements View.OnClickListener {
        final /* synthetic */ String a;

        AnonymousClass1(String str) {
            this.a = str;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            m.d("\u53d1\u9001\u8fde\u63a5---->" + this.a);
            if (ConsultMessageHolder.this.p != null) {
                ConsultMessageHolder.this.p.a();
            }
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        ZhiChiMessageBase zhiChiMessageBase;
        if (view == this.d && (zhiChiMessageBase = this.h) != null && !TextUtils.isEmpty(zhiChiMessageBase.getUrl())) {
            if (y.a != null) {
                y.a.a(this.h.getUrl());
            } else if (y.b == null || !y.b.a(this.h.getUrl())) {
                Intent intent = new Intent(this.n, WebViewActivity.class);
                intent.putExtra("url", this.h.getUrl());
                intent.addFlags(268435456);
                this.n.startActivity(intent);
            }
        }
    }
}
