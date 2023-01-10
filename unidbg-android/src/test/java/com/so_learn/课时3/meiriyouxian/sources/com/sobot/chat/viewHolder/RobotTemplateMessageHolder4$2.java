package com.sobot.chat.viewHolder;

import android.view.View;
import com.sobot.chat.listener.NoDoubleClickListener;

/* access modifiers changed from: package-private */
public class RobotTemplateMessageHolder4$2 extends NoDoubleClickListener {
    final /* synthetic */ i a;

    RobotTemplateMessageHolder4$2(i iVar) {
        this.a = iVar;
    }

    @Override // com.sobot.chat.listener.NoDoubleClickListener
    public void a(View view) {
        if (this.a.p != null) {
            this.a.p.b();
        }
    }
}
