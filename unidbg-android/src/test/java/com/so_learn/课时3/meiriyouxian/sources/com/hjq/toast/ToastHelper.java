package com.hjq.toast;

import android.app.Application;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.WindowManager;
import android.widget.Toast;

/* access modifiers changed from: package-private */
public final class ToastHelper extends Handler {
    private boolean isShow;
    private final String mPackageName;
    private final Toast mToast;
    private final WindowHelper mWindowHelper;

    ToastHelper(Toast toast, Application application) {
        super(Looper.getMainLooper());
        this.mToast = toast;
        this.mPackageName = application.getPackageName();
        this.mWindowHelper = WindowHelper.register(this, application);
    }

    @Override // android.os.Handler
    public void handleMessage(Message message) {
        cancel();
    }

    /* access modifiers changed from: package-private */
    public void show() {
        if (!this.isShow) {
            WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
            layoutParams.height = -2;
            layoutParams.width = -2;
            layoutParams.format = -3;
            layoutParams.windowAnimations = 16973828;
            layoutParams.flags = 152;
            layoutParams.packageName = this.mPackageName;
            layoutParams.gravity = this.mToast.getGravity();
            layoutParams.x = this.mToast.getXOffset();
            layoutParams.y = this.mToast.getYOffset();
            try {
                this.mWindowHelper.getWindowManager().addView(this.mToast.getView(), layoutParams);
                this.isShow = true;
                sendEmptyMessageDelayed(0, this.mToast.getDuration() == 1 ? 3500 : 2000);
            } catch (WindowManager.BadTokenException | IllegalStateException | NullPointerException unused) {
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void cancel() {
        removeMessages(0);
        if (this.isShow) {
            try {
                this.mWindowHelper.getWindowManager().removeViewImmediate(this.mToast.getView());
            } catch (IllegalArgumentException | NullPointerException unused) {
            }
            this.isShow = false;
        }
    }
}
