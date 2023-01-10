package com.sobot.chat.viewHolder;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.sobot.chat.api.apiUtils.GsonUtil;
import com.sobot.chat.api.model.SobotMultiDiaRespInfo;
import com.sobot.chat.api.model.ZhiChiMessageBase;
import com.sobot.chat.utils.c;
import com.sobot.chat.utils.j;
import com.sobot.chat.utils.q;
import com.sobot.chat.viewHolder.base.MessageHolderBase;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class RobotAnswerItemsMsgHolder extends MessageHolderBase implements View.OnClickListener {
    private TextView a;
    private LinearLayout b;
    private ZhiChiMessageBase c;

    public RobotAnswerItemsMsgHolder(Context context, View view) {
        super(context, view);
        this.a = (TextView) view.findViewById(q.a(context, "id", "sobot_template2_msg"));
        this.b = (LinearLayout) view.findViewById(q.a(context, "id", "sobot_answersList"));
    }

    @Override // com.sobot.chat.viewHolder.base.MessageHolderBase
    public void a(Context context, ZhiChiMessageBase zhiChiMessageBase) {
        this.c = zhiChiMessageBase;
        if (!(zhiChiMessageBase.getAnswer() == null || zhiChiMessageBase.getAnswer().getMultiDiaRespInfo() == null)) {
            SobotMultiDiaRespInfo multiDiaRespInfo = zhiChiMessageBase.getAnswer().getMultiDiaRespInfo();
            j.a(context).a(this.a, c.a(multiDiaRespInfo).replaceAll("\n", "<br/>"), i());
            if ("000000".equals(multiDiaRespInfo.getRetCode())) {
                List<Map<String, String>> icLists = multiDiaRespInfo.getIcLists();
                if (icLists == null || icLists.size() <= 0) {
                    this.b.setVisibility(8);
                    return;
                }
                this.b.setVisibility(0);
                this.b.removeAllViews();
                for (int i = 0; i < icLists.size(); i++) {
                    Map<String, String> map = icLists.get(i);
                    Iterator<Map.Entry<String, String>> it2 = map.entrySet().iterator();
                    if (it2.hasNext()) {
                        Map.Entry<String, String> next = it2.next();
                        TextView a = c.a(context, b(zhiChiMessageBase));
                        a.setOnClickListener(this);
                        a.setText(next.getKey() + ":" + next.getValue());
                        a.setTag(map);
                        this.b.addView(a);
                    }
                }
                return;
            }
            this.b.setVisibility(8);
        }
    }

    private void a(String str, Map<String, String> map, SobotMultiDiaRespInfo sobotMultiDiaRespInfo) {
        if (sobotMultiDiaRespInfo != null && this.p != null && this.c != null) {
            ZhiChiMessageBase zhiChiMessageBase = new ZhiChiMessageBase();
            HashMap hashMap = new HashMap();
            hashMap.put("level", sobotMultiDiaRespInfo.getLevel());
            hashMap.put("conversationId", sobotMultiDiaRespInfo.getConversationId());
            hashMap.putAll(map);
            zhiChiMessageBase.setContent(GsonUtil.map2Str(hashMap));
            zhiChiMessageBase.setId(System.currentTimeMillis() + "");
            this.p.a(zhiChiMessageBase, 4, 2, str, str);
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        ZhiChiMessageBase zhiChiMessageBase = this.c;
        if (zhiChiMessageBase != null && zhiChiMessageBase.getAnswer() != null && (view instanceof TextView) && view.getTag() != null && (view.getTag() instanceof Map)) {
            SobotMultiDiaRespInfo multiDiaRespInfo = this.c.getAnswer().getMultiDiaRespInfo();
            a(((TextView) view).getText().toString(), (Map) view.getTag(), multiDiaRespInfo);
        }
    }

    private boolean b(ZhiChiMessageBase zhiChiMessageBase) {
        return zhiChiMessageBase.getSugguestionsFontColor() == 1;
    }
}
