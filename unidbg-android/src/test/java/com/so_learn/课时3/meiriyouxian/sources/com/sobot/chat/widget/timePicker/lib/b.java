package com.sobot.chat.widget.timePicker.lib;

import android.view.GestureDetector;
import android.view.MotionEvent;

/* compiled from: SobotLoopViewGestureListener */
/* access modifiers changed from: package-private */
public final class b extends GestureDetector.SimpleOnGestureListener {
    final SobotWheelView a;

    b(SobotWheelView sobotWheelView) {
        this.a = sobotWheelView;
    }

    @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
    public final boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
        this.a.a(f2);
        return true;
    }
}
