package com.sobot.chat.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.sobot.chat.adapter.base.b;
import com.sobot.chat.api.model.SobotRobot;
import com.sobot.chat.utils.q;
import java.util.List;

/* compiled from: SobotRobotListAdapter */
public class k extends b<SobotRobot> {
    /* access modifiers changed from: protected */
    @Override // com.sobot.chat.adapter.base.b
    public String a() {
        return "sobot_list_item_robot";
    }

    public k(Context context, List<SobotRobot> list) {
        super(context, list);
    }

    /* access modifiers changed from: protected */
    @Override // com.sobot.chat.adapter.base.b
    public b.a a(Context context, View view) {
        return new a(context, view);
    }

    /* compiled from: SobotRobotListAdapter */
    private static class a extends b.a<SobotRobot> {
        private TextView c;
        private LinearLayout d;

        private a(Context context, View view) {
            super(context, view);
            this.d = (LinearLayout) view.findViewById(q.a(context, "id", "sobot_ll_content"));
            this.c = (TextView) view.findViewById(q.a(context, "id", "sobot_tv_content"));
        }

        public void a(SobotRobot sobotRobot, int i) {
            if (sobotRobot == null || TextUtils.isEmpty(sobotRobot.getOperationRemark())) {
                this.d.setVisibility(4);
                this.d.setSelected(false);
                this.c.setText("");
                return;
            }
            this.d.setVisibility(0);
            this.d.setSelected(sobotRobot.isSelected());
            this.c.setText(sobotRobot.getOperationRemark());
        }
    }
}
