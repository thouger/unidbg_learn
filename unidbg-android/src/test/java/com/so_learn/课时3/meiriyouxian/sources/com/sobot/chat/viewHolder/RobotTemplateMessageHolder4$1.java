package com.sobot.chat.viewHolder;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import com.sobot.chat.activity.WebViewActivity;
import com.sobot.chat.utils.y;
import java.util.Map;

class RobotTemplateMessageHolder4$1 implements View.OnClickListener {
    final /* synthetic */ Map a;
    final /* synthetic */ Context b;
    final /* synthetic */ i c;

    RobotTemplateMessageHolder4$1(i iVar, Map map, Context context) {
        this.c = iVar;
        this.a = map;
        this.b = context;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (y.b == null || !y.b.a((String) this.a.get("anchor"))) {
            Intent intent = new Intent(this.b, WebViewActivity.class);
            intent.putExtra("url", (String) this.a.get("anchor"));
            this.b.startActivity(intent);
        }
    }
}
