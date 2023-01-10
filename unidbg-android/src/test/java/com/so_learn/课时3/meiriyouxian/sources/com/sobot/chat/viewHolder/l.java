package com.sobot.chat.viewHolder;

import android.content.Context;
import android.view.View;
import android.widget.TextView;
import com.sobot.chat.api.model.ZhiChiMessageBase;
import com.sobot.chat.utils.q;
import com.sobot.chat.viewHolder.base.MessageHolderBase;
import java.util.List;
import java.util.Map;

/* compiled from: SobotChatMsgItemSDKHistoryR */
public class l extends MessageHolderBase {
    private TextView a;

    public l(Context context, View view) {
        super(context, view);
        this.a = (TextView) view.findViewById(q.a(context, "id", "sobot_sdk_history_msg"));
    }

    @Override // com.sobot.chat.viewHolder.base.MessageHolderBase
    public void a(Context context, ZhiChiMessageBase zhiChiMessageBase) {
        if (!(zhiChiMessageBase.getAnswer() == null || zhiChiMessageBase.getAnswer().getInterfaceRetList() == null || zhiChiMessageBase.getAnswer().getInterfaceRetList().size() <= 0)) {
            List<Map<String, String>> interfaceRetList = zhiChiMessageBase.getAnswer().getInterfaceRetList();
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < interfaceRetList.size(); i++) {
                Map<String, String> map = interfaceRetList.get(i);
                if (map != null && map.size() > 0) {
                    sb.append(map.get("title"));
                }
            }
            this.a.setText(sb);
        }
    }
}
