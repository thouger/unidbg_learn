package com.sobot.chat.viewHolder;

import android.view.View;
import com.sobot.chat.listener.NoDoubleClickListener;

/* access modifiers changed from: package-private */
public class RobotTemplateMessageHolder3$2 extends NoDoubleClickListener {
    final /* synthetic */ h a;

    RobotTemplateMessageHolder3$2(h hVar) {
        this.a = hVar;
    }

    @Override // com.sobot.chat.listener.NoDoubleClickListener
    public void a(View view) {
        if (this.a.p != null) {
            this.a.p.b();
        }
    }
}
