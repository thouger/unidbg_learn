package com.sobot.chat.widget.timePicker.lib;

import android.os.Handler;
import android.os.Message;
import com.sobot.chat.widget.timePicker.lib.SobotWheelView;

/* compiled from: SobotMessageHandler */
/* access modifiers changed from: package-private */
public final class c extends Handler {
    final SobotWheelView a;

    c(SobotWheelView sobotWheelView) {
        this.a = sobotWheelView;
    }

    @Override // android.os.Handler
    public final void handleMessage(Message message) {
        int i = message.what;
        if (i == 1000) {
            this.a.invalidate();
        } else if (i == 2000) {
            this.a.a(SobotWheelView.ACTION.FLING);
        } else if (i == 3000) {
            this.a.b();
        }
    }
}
