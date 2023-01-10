package com.sobot.chat.widget.timePicker.lib;

import java.util.TimerTask;

/* compiled from: SobotInertiaTimerTask */
/* access modifiers changed from: package-private */
public final class a extends TimerTask {
    float a = 2.14748365E9f;
    final float b;
    final SobotWheelView c;

    a(SobotWheelView sobotWheelView, float f) {
        this.c = sobotWheelView;
        this.b = f;
    }

    @Override // java.util.TimerTask, java.lang.Runnable
    public final void run() {
        if (this.a == 2.14748365E9f) {
            if (Math.abs(this.b) <= 2000.0f) {
                this.a = this.b;
            } else if (this.b > 0.0f) {
                this.a = 2000.0f;
            } else {
                this.a = -2000.0f;
            }
        }
        if (Math.abs(this.a) < 0.0f || Math.abs(this.a) > 20.0f) {
            SobotWheelView sobotWheelView = this.c;
            float f = (float) ((int) ((this.a * 10.0f) / 1000.0f));
            sobotWheelView.v -= f;
            if (!this.c.r) {
                float f2 = this.c.l;
                float f3 = ((float) (-this.c.w)) * f2;
                float itemsCount = ((float) ((this.c.getItemsCount() - 1) - this.c.w)) * f2;
                double d = ((double) f2) * 0.25d;
                if (((double) this.c.v) - d < ((double) f3)) {
                    f3 = this.c.v + f;
                } else if (((double) this.c.v) + d > ((double) itemsCount)) {
                    itemsCount = this.c.v + f;
                }
                if (this.c.v <= f3) {
                    this.a = 40.0f;
                    this.c.v = (float) ((int) f3);
                } else if (this.c.v >= itemsCount) {
                    this.c.v = (float) ((int) itemsCount);
                    this.a = -40.0f;
                }
            }
            float f4 = this.a;
            if (f4 < 0.0f) {
                this.a = f4 + 20.0f;
            } else {
                this.a = f4 - 20.0f;
            }
            this.c.b.sendEmptyMessage(1000);
            return;
        }
        this.c.a();
        this.c.b.sendEmptyMessage(2000);
    }
}
