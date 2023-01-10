package com.sobot.chat.viewHolder;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.sobot.chat.activity.WebViewActivity;
import com.sobot.chat.api.model.ConsultingContent;
import com.sobot.chat.api.model.ZhiChiMessageBase;
import com.sobot.chat.utils.d;
import com.sobot.chat.utils.q;
import com.sobot.chat.utils.t;
import com.sobot.chat.utils.y;
import com.sobot.chat.viewHolder.base.MessageHolderBase;

public class CardMessageHolder extends MessageHolderBase implements View.OnClickListener {
    private ImageView a;
    private TextView b;
    private TextView c;
    private TextView d;
    private View e;
    private int f;
    private ConsultingContent g;

    public CardMessageHolder(Context context, View view) {
        super(context, view);
        this.e = view.findViewById(q.g(context, "sobot_rl_hollow_container"));
        this.a = (ImageView) view.findViewById(q.g(context, "sobot_goods_pic"));
        this.b = (TextView) view.findViewById(q.g(context, "sobot_goods_title"));
        this.c = (TextView) view.findViewById(q.g(context, "sobot_goods_label"));
        this.d = (TextView) view.findViewById(q.g(context, "sobot_goods_des"));
        this.f = q.e(context, "sobot_icon_consulting_default_pic");
    }

    @Override // com.sobot.chat.viewHolder.base.MessageHolderBase
    public void a(Context context, ZhiChiMessageBase zhiChiMessageBase) {
        this.g = zhiChiMessageBase.getConsultingContent();
        if (zhiChiMessageBase.getConsultingContent() != null) {
            if (!TextUtils.isEmpty(d.b(zhiChiMessageBase.getConsultingContent().getSobotGoodsImgUrl()))) {
                this.a.setVisibility(0);
                this.d.setMaxLines(1);
                this.d.setEllipsize(TextUtils.TruncateAt.END);
                String b = d.b(zhiChiMessageBase.getConsultingContent().getSobotGoodsImgUrl());
                ImageView imageView = this.a;
                int i = this.f;
                t.a(context, b, imageView, i, i);
            } else {
                this.a.setVisibility(8);
            }
            this.b.setText(zhiChiMessageBase.getConsultingContent().getSobotGoodsTitle());
            this.c.setText(zhiChiMessageBase.getConsultingContent().getSobotGoodsLable());
            this.d.setText(zhiChiMessageBase.getConsultingContent().getSobotGoodsDescribe());
            if (this.o) {
                try {
                    this.t.setClickable(true);
                    if (zhiChiMessageBase.getSendSuccessState() == 1) {
                        this.t.setVisibility(8);
                        this.u.setVisibility(8);
                    } else if (zhiChiMessageBase.getSendSuccessState() == 0) {
                        this.t.setVisibility(0);
                        this.u.setVisibility(8);
                    } else if (zhiChiMessageBase.getSendSuccessState() == 2) {
                        this.u.setVisibility(0);
                        this.t.setVisibility(8);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        this.e.setOnClickListener(this);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view == this.e && this.g != null) {
            if (y.a != null) {
                y.a.a(this.g.getSobotGoodsFromUrl());
            } else if (y.b == null || !y.b.a(this.g.getSobotGoodsFromUrl())) {
                Intent intent = new Intent(this.n, WebViewActivity.class);
                intent.putExtra("url", this.g.getSobotGoodsFromUrl());
                intent.addFlags(268435456);
                this.n.startActivity(intent);
            }
        }
    }
}
