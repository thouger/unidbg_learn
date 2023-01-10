package com.sobot.chat.viewHolder;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import com.sobot.chat.api.model.ZhiChiMessageBase;
import com.sobot.chat.utils.q;
import com.sobot.chat.viewHolder.base.MessageHolderBase;

/* compiled from: SystemMessageHolder */
public class m extends MessageHolderBase {
    TextView a;

    public m(Context context, View view) {
        super(context, view);
        this.a = (TextView) view.findViewById(q.a(context, "id", "sobot_center_Remind_note"));
    }

    @Override // com.sobot.chat.viewHolder.base.MessageHolderBase
    public void a(Context context, ZhiChiMessageBase zhiChiMessageBase) {
        if (!TextUtils.isEmpty(zhiChiMessageBase.getMsg())) {
            this.a.setVisibility(0);
            this.a.setText(zhiChiMessageBase.getMsg());
        }
    }
}
