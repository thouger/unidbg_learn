package com.sobot.chat.widget.timePicker.lib;

/* compiled from: SobotOnItemSelectedRunnable */
/* access modifiers changed from: package-private */
public final class d implements Runnable {
    final SobotWheelView a;

    d(SobotWheelView sobotWheelView) {
        this.a = sobotWheelView;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.a.c.a(this.a.getCurrentItem());
    }
}
