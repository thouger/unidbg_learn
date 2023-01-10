package com.sobot.chat.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.sobot.chat.adapter.base.b;
import com.sobot.chat.api.model.SobotPostMsgTemplate;
import com.sobot.chat.utils.q;
import java.util.List;

/* compiled from: SobotPostMsgTmpListAdapter */
public class i extends b<SobotPostMsgTemplate> {
    /* access modifiers changed from: protected */
    @Override // com.sobot.chat.adapter.base.b
    public String a() {
        return "sobot_list_item_robot";
    }

    public i(Context context, List<SobotPostMsgTemplate> list) {
        super(context, list);
    }

    /* access modifiers changed from: protected */
    @Override // com.sobot.chat.adapter.base.b
    public b.a a(Context context, View view) {
        return new a(context, view);
    }

    /* compiled from: SobotPostMsgTmpListAdapter */
    private static class a extends b.a<SobotPostMsgTemplate> {
        private TextView c;
        private LinearLayout d;

        private a(Context context, View view) {
            super(context, view);
            this.d = (LinearLayout) view.findViewById(q.a(context, "id", "sobot_ll_content"));
            this.c = (TextView) view.findViewById(q.a(context, "id", "sobot_tv_content"));
        }

        public void a(SobotPostMsgTemplate sobotPostMsgTemplate, int i) {
            if (sobotPostMsgTemplate == null || TextUtils.isEmpty(sobotPostMsgTemplate.getTemplateName())) {
                this.d.setVisibility(4);
                this.d.setSelected(false);
                this.c.setText("");
                return;
            }
            this.d.setVisibility(0);
            this.c.setText(sobotPostMsgTemplate.getTemplateName());
        }
    }
}
