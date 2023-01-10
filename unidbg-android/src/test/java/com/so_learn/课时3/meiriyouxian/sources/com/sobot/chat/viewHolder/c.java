package com.sobot.chat.viewHolder;

import android.content.Context;
import android.net.wifi.WifiEnterpriseConfig;
import android.text.Html;
import android.text.TextUtils;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.CycleInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.sobot.chat.api.model.ZhiChiMessageBase;
import com.sobot.chat.utils.j;
import com.sobot.chat.utils.q;
import com.sobot.chat.utils.s;
import com.sobot.chat.viewHolder.base.MessageHolderBase;

/* compiled from: RemindMessageHolder */
public class c extends MessageHolderBase {
    TextView a;
    TextView b;
    TextView c;
    RelativeLayout d;
    TextView e;

    public c(Context context, View view) {
        super(context, view);
        this.a = (TextView) view.findViewById(q.a(context, "id", "sobot_center_Remind_note"));
        this.b = (TextView) view.findViewById(q.a(context, "id", "sobot_center_Remind_note1"));
        this.c = (TextView) view.findViewById(q.a(context, "id", "sobot_center_Remind_note2"));
        this.d = (RelativeLayout) view.findViewById(q.a(context, "id", "rl_not_read"));
        this.e = (TextView) view.findViewById(q.a(context, "id", "sobot_center_Remind_note5"));
        this.e.setText(q.f(context, "sobot_no_read"));
    }

    @Override // com.sobot.chat.viewHolder.base.MessageHolderBase
    public void a(Context context, ZhiChiMessageBase zhiChiMessageBase) {
        if (zhiChiMessageBase.getAnswer() != null && !TextUtils.isEmpty(zhiChiMessageBase.getAnswer().getMsg())) {
            if (zhiChiMessageBase.getAnswer().getRemindType() == 6) {
                this.d.setVisibility(8);
                this.c.setVisibility(8);
                this.a.setVisibility(8);
                this.b.setVisibility(0);
                this.b.setText(zhiChiMessageBase.getAnswer().getMsg());
            } else if (zhiChiMessageBase.getAnswer().getRemindType() == 7) {
                this.d.setVisibility(0);
                this.a.setVisibility(8);
                this.b.setVisibility(8);
                this.c.setVisibility(8);
            } else if (zhiChiMessageBase.getAnswer().getRemindType() == 9) {
                this.d.setVisibility(8);
                this.a.setVisibility(8);
                this.b.setVisibility(8);
                this.c.setVisibility(0);
                j.a(context).a(this.c, zhiChiMessageBase.getAnswer().getMsg(), q.a(context, "color", "sobot_color_link_remind"));
            } else {
                this.d.setVisibility(8);
                this.c.setVisibility(8);
                this.a.setVisibility(0);
                this.b.setVisibility(8);
                int remindType = zhiChiMessageBase.getAnswer().getRemindType();
                if ("action_remind_info_post_msg".equals(zhiChiMessageBase.getAction())) {
                    if (remindType == 1) {
                        if (zhiChiMessageBase.isShake()) {
                            this.a.setAnimation(a(5));
                        }
                        a(context, this.a, zhiChiMessageBase, false);
                    }
                    if (remindType == 2) {
                        if (zhiChiMessageBase.isShake()) {
                            this.a.setAnimation(a(5));
                        }
                        a(context, this.a, zhiChiMessageBase, true);
                    }
                } else if ("action_remind_info_paidui".equals(zhiChiMessageBase.getAction())) {
                    if (remindType == 3) {
                        if (zhiChiMessageBase.isShake()) {
                            this.a.setAnimation(a(5));
                        }
                        a(context, this.a, zhiChiMessageBase, false);
                    }
                } else if ("action_remind_connt_success".equals(zhiChiMessageBase.getAction())) {
                    if (remindType == 4) {
                        this.a.setText(Html.fromHtml(zhiChiMessageBase.getAnswer().getMsg()));
                    }
                } else if ("sobot_outline_leverByManager".equals(zhiChiMessageBase.getAction()) || "action_remind_past_time".equals(zhiChiMessageBase.getAction())) {
                    j.a(context).a(this.a, zhiChiMessageBase.getAnswer().getMsg(), q.a(context, "color", "sobot_color_link_remind"));
                } else if (remindType == 8 || remindType == 4) {
                    this.a.setText(zhiChiMessageBase.getAnswer().getMsg());
                }
            }
            if (zhiChiMessageBase.isShake()) {
                this.a.setAnimation(a(5));
                zhiChiMessageBase.setShake(false);
            }
        }
    }

    private void a(Context context, TextView textView, ZhiChiMessageBase zhiChiMessageBase, boolean z) {
        int b = s.b(context, "sobot_msg_flag", 0);
        StringBuilder sb = new StringBuilder();
        sb.append(z ? "\uff0c" : WifiEnterpriseConfig.CA_CERT_ALIAS_DELIMITER);
        sb.append(q.f(context, "sobot_can"));
        sb.append("<a href='sobot:SobotPostMsgActivity'> ");
        sb.append(q.f(context, "sobot_str_bottom_message"));
        sb.append("</a>");
        String sb2 = sb.toString();
        String replace = zhiChiMessageBase.getAnswer().getMsg().replace("<br/>", "").replace("<p>", "").replace("</p>", "");
        if (b == 0) {
            replace = replace + sb2;
        }
        j.a(context).a(textView, replace, q.a(context, "color", "sobot_color_link_remind"));
        textView.setEnabled(true);
        zhiChiMessageBase.setShake(false);
    }

    public static Animation a(int i) {
        TranslateAnimation translateAnimation = new TranslateAnimation(0.0f, 10.0f, 0.0f, 0.0f);
        translateAnimation.setInterpolator(new CycleInterpolator((float) i));
        translateAnimation.setDuration(1000);
        return translateAnimation;
    }
}
