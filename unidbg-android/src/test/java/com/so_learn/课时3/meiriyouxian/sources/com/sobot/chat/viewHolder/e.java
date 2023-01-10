package com.sobot.chat.viewHolder;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.sobot.chat.api.model.SobotKeyWordTransfer;
import com.sobot.chat.api.model.ZhiChiGroupBase;
import com.sobot.chat.api.model.ZhiChiMessageBase;
import com.sobot.chat.utils.c;
import com.sobot.chat.utils.j;
import com.sobot.chat.utils.q;
import com.sobot.chat.viewHolder.base.MessageHolderBase;
import java.util.List;

/* compiled from: RobotKeyWordMessageHolder */
public class e extends MessageHolderBase {
    private TextView a;
    private LinearLayout b;
    private View.OnClickListener c = new RobotKeyWordMessageHolder$1(this);

    public e(Context context, View view) {
        super(context, view);
        this.a = (TextView) view.findViewById(q.a(context, "id", "sobot_keyword_tips_msg"));
        this.b = (LinearLayout) view.findViewById(q.a(context, "id", "sobot_keyword_grouplist"));
    }

    @Override // com.sobot.chat.viewHolder.base.MessageHolderBase
    public void a(Context context, ZhiChiMessageBase zhiChiMessageBase) {
        SobotKeyWordTransfer sobotKeyWordTransfer;
        if (!(zhiChiMessageBase == null || (sobotKeyWordTransfer = zhiChiMessageBase.getSobotKeyWordTransfer()) == null)) {
            if (sobotKeyWordTransfer.getTipsMessage() != null) {
                a(this.a);
                j a2 = j.a(context);
                TextView textView = this.a;
                String tipsMessage = sobotKeyWordTransfer.getTipsMessage();
                boolean z = this.o;
                a2.a(textView, tipsMessage, i());
            }
            List<ZhiChiGroupBase> groupList = sobotKeyWordTransfer.getGroupList();
            if (groupList == null || groupList.size() <= 0) {
                this.b.setVisibility(8);
                return;
            }
            this.b.removeAllViews();
            this.b.setVisibility(0);
            for (int i = 0; i < groupList.size(); i++) {
                a aVar = new a();
                aVar.a(groupList.get(i).getGroupId());
                aVar.b(sobotKeyWordTransfer.getKeyword());
                aVar.c(sobotKeyWordTransfer.getKeywordId());
                TextView a3 = c.a(context, false);
                a3.setText(groupList.get(i).getGroupName());
                a3.setTag(aVar);
                a3.setOnClickListener(this.c);
                this.b.addView(a3);
            }
        }
    }

    /* compiled from: RobotKeyWordMessageHolder */
    class a {
        private String b;
        private String c;
        private String d;

        a() {
        }

        public String a() {
            return this.b;
        }

        public void a(String str) {
            this.b = str;
        }

        public String b() {
            return this.c;
        }

        public void b(String str) {
            this.c = str;
        }

        public String c() {
            return this.d;
        }

        public void c(String str) {
            this.d = str;
        }
    }
}
