package com.sobot.chat.viewHolder;

import android.content.Context;
import android.content.Intent;
import android.text.Html;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import com.sobot.chat.activity.WebViewActivity;
import com.sobot.chat.api.model.OrderCardContentModel;
import com.sobot.chat.api.model.ZhiChiMessageBase;
import com.sobot.chat.utils.d;
import com.sobot.chat.utils.f;
import com.sobot.chat.utils.q;
import com.sobot.chat.utils.t;
import com.sobot.chat.utils.y;
import com.sobot.chat.viewHolder.base.MessageHolderBase;
import com.sobot.chat.widget.image.SobotRCImageView;
import com.xiaomi.mipush.sdk.Constants;

public class OrderCardMessageHolder extends MessageHolderBase implements View.OnClickListener {
    private View a;
    private SobotRCImageView b;
    private TextView c;
    private TextView d;
    private TextView e;
    private TextView f;
    private TextView g;
    private TextView h;
    private View i;
    private int j;
    private OrderCardContentModel k;

    public OrderCardMessageHolder(Context context, View view) {
        super(context, view);
        this.a = view.findViewById(q.g(context, "sobot_rl_hollow_container"));
        this.b = (SobotRCImageView) view.findViewById(q.g(context, "sobot_goods_pic"));
        this.c = (TextView) view.findViewById(q.g(context, "sobot_goods_title"));
        this.d = (TextView) view.findViewById(q.g(context, "sobot_goods_count"));
        this.e = (TextView) view.findViewById(q.g(context, "sobot_goods_total_money"));
        this.i = view.findViewById(q.g(context, "sobot_goods_order_split"));
        this.f = (TextView) view.findViewById(q.g(context, "sobot_order_status"));
        this.g = (TextView) view.findViewById(q.g(context, "sobot_order_number"));
        this.h = (TextView) view.findViewById(q.g(context, "sobot_order_createtime"));
        this.j = q.e(context, "sobot_icon_consulting_default_pic");
    }

    @Override // com.sobot.chat.viewHolder.base.MessageHolderBase
    public void a(Context context, ZhiChiMessageBase zhiChiMessageBase) {
        String str;
        this.k = zhiChiMessageBase.getOrderCardContent();
        OrderCardContentModel orderCardContentModel = this.k;
        if (orderCardContentModel != null) {
            if (orderCardContentModel.getGoods() == null || this.k.getGoods().size() <= 0) {
                this.b.setVisibility(8);
                this.c.setVisibility(8);
            } else {
                this.c.setVisibility(0);
                OrderCardContentModel.Goods goods = this.k.getGoods().get(0);
                if (goods != null) {
                    if (TextUtils.isEmpty(goods.getPictureUrl())) {
                        this.b.setVisibility(8);
                    } else {
                        this.b.setVisibility(0);
                        String b = d.b(goods.getPictureUrl());
                        SobotRCImageView sobotRCImageView = this.b;
                        int i = this.j;
                        t.a(context, b, sobotRCImageView, i, i);
                    }
                    if (TextUtils.isEmpty(goods.getName())) {
                        this.c.setVisibility(8);
                    } else {
                        this.c.setVisibility(0);
                        this.c.setText(goods.getName());
                    }
                }
            }
            if ((this.k.getGoods() == null || this.k.getGoods().size() <= 0) && TextUtils.isEmpty(this.k.getGoodsCount()) && this.k.getTotalFee() <= 0) {
                this.i.setVisibility(8);
            } else {
                this.i.setVisibility(0);
            }
            String str2 = "";
            if (this.k.getOrderStatus() > 0) {
                this.f.setVisibility(0);
                switch (this.k.getOrderStatus()) {
                    case 1:
                        str = q.f(context, "sobot_order_status_1");
                        break;
                    case 2:
                        str = q.f(context, "sobot_order_status_2");
                        break;
                    case 3:
                        str = q.f(context, "sobot_order_status_3");
                        break;
                    case 4:
                        str = q.f(context, "sobot_order_status_4");
                        break;
                    case 5:
                        str = q.f(context, "sobot_completed");
                        break;
                    case 6:
                        str = q.f(context, "sobot_order_status_6");
                        break;
                    case 7:
                        str = q.f(context, "sobot_order_status_7");
                        break;
                    default:
                        str = str2;
                        break;
                }
                TextView textView = this.f;
                textView.setText(Html.fromHtml(q.f(context, "sobot_order_status_lable") + "\uff1a<b><font color='#E67F17'>" + str + "</font></b>"));
            } else {
                this.f.setVisibility(8);
            }
            this.e.setVisibility(0);
            TextView textView2 = this.e;
            StringBuilder sb = new StringBuilder();
            if (!TextUtils.isEmpty(this.k.getGoodsCount())) {
                str2 = Constants.ACCEPT_TIME_SEPARATOR_SP;
            }
            sb.append(str2);
            sb.append(q.f(context, "sobot_order_total_money"));
            sb.append(a(this.k.getTotalFee()));
            sb.append(q.f(context, "sobot_money_format"));
            textView2.setText(sb.toString());
            if (!TextUtils.isEmpty(this.k.getGoodsCount())) {
                this.d.setVisibility(0);
                TextView textView3 = this.d;
                textView3.setText(this.k.getGoodsCount() + q.f(context, "sobot_how_goods"));
            } else {
                this.d.setVisibility(8);
            }
            if (!TextUtils.isEmpty(this.k.getOrderCode())) {
                TextView textView4 = this.g;
                textView4.setText(q.f(context, "sobot_order_code_lable") + "\uff1a" + this.k.getOrderCode());
                this.g.setVisibility(0);
            } else {
                this.g.setVisibility(8);
            }
            if (!TextUtils.isEmpty(this.k.getCreateTime())) {
                TextView textView5 = this.h;
                textView5.setText(q.f(context, "sobot_order_time_lable") + "\uff1a" + f.a(Long.valueOf(Long.parseLong(this.k.getCreateTime())), "yyyy-MM-dd HH:mm:ss"));
                this.h.setVisibility(0);
            } else {
                this.h.setVisibility(8);
            }
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
        this.a.setOnClickListener(this);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view == this.a && this.k != null) {
            if (y.g != null) {
                y.g.a(this.k);
            } else if (y.a != null) {
                y.a.a(this.k.getOrderUrl());
            } else if (y.b == null || !y.b.a(this.k.getOrderUrl())) {
                Intent intent = new Intent(this.n, WebViewActivity.class);
                intent.putExtra("url", this.k.getOrderUrl());
                intent.addFlags(268435456);
                this.n.startActivity(intent);
            }
        }
    }

    private String a(int i) {
        if (this.n == null) {
            return "";
        }
        return "" + (((float) i) / 100.0f);
    }
}
