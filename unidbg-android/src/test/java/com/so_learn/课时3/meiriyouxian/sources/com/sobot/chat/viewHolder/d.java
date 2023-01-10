package com.sobot.chat.viewHolder;

import android.content.Context;
import android.net.wifi.WifiEnterpriseConfig;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import com.sobot.chat.api.model.ZhiChiMessageBase;
import com.sobot.chat.utils.q;
import com.sobot.chat.viewHolder.base.MessageHolderBase;

/* compiled from: RetractedMessageHolder */
public class d extends MessageHolderBase {
    TextView a;
    String b;

    public d(Context context, View view) {
        super(context, view);
        this.a = (TextView) view.findViewById(q.a(context, "id", "sobot_tv_tip"));
        this.b = q.f(context, "sobot_retracted_msg_tip_end");
    }

    @Override // com.sobot.chat.viewHolder.base.MessageHolderBase
    public void a(Context context, ZhiChiMessageBase zhiChiMessageBase) {
        String str;
        if (zhiChiMessageBase != null) {
            TextView textView = this.a;
            if (TextUtils.isEmpty(zhiChiMessageBase.getSenderName())) {
                str = "";
            } else {
                str = zhiChiMessageBase.getSenderName() + WifiEnterpriseConfig.CA_CERT_ALIAS_DELIMITER + this.b;
            }
            textView.setText(str);
        }
    }
}
