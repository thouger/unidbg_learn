package com.hjq.toast;

import android.app.Application;
import android.os.Handler;
import android.os.Message;
import android.view.WindowManager;
import android.widget.Toast;
import java.lang.reflect.Field;

/* access modifiers changed from: package-private */
public final class SafeToast extends BaseToast {
    SafeToast(Application application) {
        super(application);
        try {
            Field declaredField = Toast.class.getDeclaredField("mTN");
            declaredField.setAccessible(true);
            Object obj = declaredField.get(this);
            Field declaredField2 = declaredField.getType().getDeclaredField("mHandler");
            declaredField2.setAccessible(true);
            declaredField2.set(obj, new SafeHandler((Handler) declaredField2.get(obj)));
        } catch (Exception unused) {
        }
    }

    private static final class SafeHandler extends Handler {
        private Handler mHandler;

        private SafeHandler(Handler handler) {
            this.mHandler = handler;
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            try {
                this.mHandler.handleMessage(message);
            } catch (WindowManager.BadTokenException unused) {
            }
        }
    }
}
