package com.sobot.chat.viewHolder;

import android.view.View;
import com.sobot.chat.listener.NoDoubleClickListener;

/* access modifiers changed from: package-private */
public class RobotTemplateMessageHolder6$1 extends NoDoubleClickListener {
    final /* synthetic */ k a;

    RobotTemplateMessageHolder6$1(k kVar) {
        this.a = kVar;
    }

    @Override // com.sobot.chat.listener.NoDoubleClickListener
    public void a(View view) {
        if (this.a.p != null) {
            this.a.p.b();
        }
    }
}
