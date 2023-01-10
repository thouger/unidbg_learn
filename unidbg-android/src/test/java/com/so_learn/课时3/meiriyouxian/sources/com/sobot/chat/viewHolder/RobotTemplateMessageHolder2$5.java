package com.sobot.chat.viewHolder;

import android.view.View;
import com.sobot.chat.listener.NoDoubleClickListener;

/* access modifiers changed from: package-private */
public class RobotTemplateMessageHolder2$5 extends NoDoubleClickListener {
    final /* synthetic */ g a;

    RobotTemplateMessageHolder2$5(g gVar) {
        this.a = gVar;
    }

    @Override // com.sobot.chat.listener.NoDoubleClickListener
    public void a(View view) {
        if (this.a.p != null) {
            this.a.p.b();
        }
    }
}
