package com.sobot.chat.widget.dialog;

import android.app.Activity;
import android.content.Context;
import com.sobot.chat.utils.q;

/* compiled from: SobotDialogUtils */
public class d {
    public static g a;

    public static void a(Context context) {
        if (context != null) {
            g gVar = a;
            if (gVar == null) {
                a = new g(context, q.f(context, "sobot_loading"));
            } else {
                gVar.a(q.f(context, "sobot_loading"));
            }
            try {
                a.show();
            } catch (Exception unused) {
            }
        }
    }

    public static void b(Context context) {
        g gVar = a;
        if (!(gVar == null || context == null || !gVar.isShowing())) {
            try {
                if (!((Activity) context).isFinishing()) {
                    a.dismiss();
                }
            } catch (Exception unused) {
            }
        }
        a = null;
    }
}
