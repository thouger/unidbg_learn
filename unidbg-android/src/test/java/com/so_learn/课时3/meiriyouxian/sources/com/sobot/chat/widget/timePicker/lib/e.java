package com.sobot.chat.widget.timePicker.lib;

import androidx.vectordrawable.graphics.drawable.PathInterpolatorCompat;
import java.util.TimerTask;

/* compiled from: SobotSmoothScrollTimerTask */
/* access modifiers changed from: package-private */
public final class e extends TimerTask {
    int a = Integer.MAX_VALUE;
    int b = 0;
    int c;
    final SobotWheelView d;

    e(SobotWheelView sobotWheelView, int i) {
        this.d = sobotWheelView;
        this.c = i;
    }

    @Override // java.util.TimerTask, java.lang.Runnable
    public final void run() {
        if (this.a == Integer.MAX_VALUE) {
            this.a = this.c;
        }
        int i = this.a;
        this.b = (int) (((float) i) * 0.1f);
        if (this.b == 0) {
            if (i < 0) {
                this.b = -1;
            } else {
                this.b = 1;
            }
        }
        if (Math.abs(this.a) <= 1) {
            this.d.a();
            this.d.b.sendEmptyMessage(PathInterpolatorCompat.MAX_NUM_POINTS);
            return;
        }
        this.d.v += (float) this.b;
        if (!this.d.r) {
            float f = this.d.l;
            float f2 = ((float) (-this.d.w)) * f;
            float itemsCount = ((float) ((this.d.getItemsCount() - 1) - this.d.w)) * f;
            if (this.d.v <= f2 || this.d.v >= itemsCount) {
                this.d.v -= (float) this.b;
                this.d.a();
                this.d.b.sendEmptyMessage(PathInterpolatorCompat.MAX_NUM_POINTS);
                return;
            }
        }
        this.d.b.sendEmptyMessage(1000);
        this.a -= this.b;
    }
}
