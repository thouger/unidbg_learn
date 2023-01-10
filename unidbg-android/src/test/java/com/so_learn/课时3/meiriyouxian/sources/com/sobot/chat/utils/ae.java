package com.sobot.chat.utils;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.media.TtmlUtils;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;
import java.util.Timer;
import java.util.TimerTask;

/* compiled from: ToastUtil */
public class ae {
    private static Toast a;
    private static Handler b = new AnonymousClass1();

    /* compiled from: ToastUtil */
    public interface a {
        void a();
    }

    /* compiled from: ToastUtil */
    /* renamed from: com.sobot.chat.utils.ae$1  reason: invalid class name */
    static class AnonymousClass1 extends Handler {
        AnonymousClass1() {
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            if (message.obj instanceof a) {
                ((a) message.obj).a();
            }
        }
    }

    public static void a(Context context, String str) {
        if (!TextUtils.isEmpty(str)) {
            try {
                e.a(context, str, 0).show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void b(Context context, String str) {
        Context applicationContext = context.getApplicationContext();
        Toast toast = a;
        if (toast == null) {
            a = Toast.makeText(applicationContext, str, 1);
        } else {
            toast.setText(str);
        }
        try {
            a.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void c(Context context, String str) {
        if (TextUtils.isEmpty(str)) {
            str = d.a(context, "sobot_server_request_wrong");
        }
        try {
            e.a(context, str, 0).show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void a(Context context, String str, int i) {
        if (TextUtils.isEmpty(str)) {
            str = d.a(context, "sobot_server_request_wrong");
        }
        try {
            e.a(context, str, 0, i).show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void a(Context context, String str, long j, a aVar) {
        if (TextUtils.isEmpty(str)) {
            str = d.a(context, "sobot_server_request_wrong");
        }
        try {
            e.a(context, str, 0).show();
            new Timer().schedule(new AnonymousClass2(aVar), j);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* compiled from: ToastUtil */
    /* renamed from: com.sobot.chat.utils.ae$2  reason: invalid class name */
    static class AnonymousClass2 extends TimerTask {
        final /* synthetic */ a a;

        AnonymousClass2(a aVar) {
            this.a = aVar;
        }

        @Override // java.util.TimerTask, java.lang.Runnable
        public void run() {
            a aVar = this.a;
            if (aVar != null) {
                ae.a(aVar);
            }
        }
    }

    public static void a(a aVar) {
        Message obtainMessage = b.obtainMessage();
        obtainMessage.obj = aVar;
        b.sendMessage(obtainMessage);
    }

    public static void a(Context context, View view, String str, int i, int i2) {
        View inflate = LayoutInflater.from(context).inflate(q.a(context, TtmlUtils.TAG_LAYOUT, "sobot_pop_chat_room_long_press"), (ViewGroup) null);
        PopupWindow popupWindow = new PopupWindow(inflate, -2, -2, true);
        popupWindow.setBackgroundDrawable(new ColorDrawable(0));
        inflate.measure(150, 150);
        int measuredWidth = inflate.getMeasuredWidth();
        int[] iArr = new int[2];
        view.getLocationOnScreen(iArr);
        popupWindow.showAtLocation(view, 0, ((iArr[0] + (view.getWidth() / 2)) - (measuredWidth / 2)) + i, (iArr[1] - (inflate.getMeasuredHeight() + 20)) + i2);
        popupWindow.update();
        ((TextView) inflate.findViewById(d.b(context, "sobot_tv_copy_txt"))).setText(q.f(context, "sobot_ctrl_copy"));
        inflate.findViewById(d.b(context, "sobot_tv_copy_txt")).setOnClickListener(new ToastUtil$3(context, str, popupWindow));
    }
}
