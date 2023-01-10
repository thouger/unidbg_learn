package com.sobot.chat.b.a;

import android.app.Activity;
import android.view.DisplayCutout;
import android.view.View;
import android.view.Window;
import android.view.WindowInsets;
import android.view.WindowManager;
import com.sobot.chat.b.a;

/* compiled from: AndroidPNotchScreen */
public class a implements com.sobot.chat.b.a {
    @Override // com.sobot.chat.b.a
    public boolean a(Activity activity) {
        return true;
    }

    @Override // com.sobot.chat.b.a
    public void b(Activity activity) {
        Window window = activity.getWindow();
        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.layoutInDisplayCutoutMode = 1;
        window.setAttributes(attributes);
        window.getDecorView().setSystemUiVisibility(1280);
    }

    /* compiled from: AndroidPNotchScreen */
    /* renamed from: com.sobot.chat.b.a.a$1  reason: invalid class name */
    class AnonymousClass1 implements Runnable {
        final /* synthetic */ View a;
        final /* synthetic */ a.c b;

        AnonymousClass1(View view, a.c cVar) {
            this.a = view;
            this.b = cVar;
        }

        @Override // java.lang.Runnable
        public void run() {
            DisplayCutout displayCutout;
            WindowInsets rootWindowInsets = this.a.getRootWindowInsets();
            if (rootWindowInsets == null || (displayCutout = rootWindowInsets.getDisplayCutout()) == null) {
                this.b.a(null);
            } else {
                this.b.a(displayCutout.getBoundingRects());
            }
        }
    }

    @Override // com.sobot.chat.b.a
    public void a(Activity activity, a.c cVar) {
        View decorView = activity.getWindow().getDecorView();
        decorView.post(new AnonymousClass1(decorView, cVar));
    }
}
