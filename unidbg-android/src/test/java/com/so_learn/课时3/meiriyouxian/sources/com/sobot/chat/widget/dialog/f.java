package com.sobot.chat.widget.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Rect;
import android.media.TtmlUtils;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.sobot.chat.b;
import com.sobot.chat.utils.q;
import com.sobot.chat.utils.r;

/* compiled from: SobotFreeAccountTipDialog */
public class f extends Dialog {
    private Button a;
    private TextView b;
    private LinearLayout c;
    private View.OnClickListener d;
    private final int e;

    public f(Activity activity, View.OnClickListener onClickListener) {
        super(activity, q.a(activity, "style", "sobot_noAnimDialogStyle"));
        this.d = onClickListener;
        this.e = r.b(activity);
        Window window = getWindow();
        if (window != null) {
            WindowManager.LayoutParams attributes = window.getAttributes();
            attributes.gravity = 17;
            if (b.a(4) && b.a(1)) {
                attributes.flags = 8;
            }
            a(activity, attributes);
            window.setAttributes(attributes);
        }
    }

    /* access modifiers changed from: protected */
    @Override // android.app.Dialog
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(q.a(getContext(), TtmlUtils.TAG_LAYOUT, "sobot_free_account_tip_popup"));
        a();
    }

    @Override // android.app.Dialog
    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (motionEvent.getAction() != 0 || motionEvent.getX() < -10.0f || motionEvent.getY() < -10.0f) {
            return true;
        }
        motionEvent.getY();
        int i = this.e;
        this.c.getHeight();
        return true;
    }

    private void a() {
        this.a = (Button) findViewById(q.a(getContext(), "id", "sobot_btn_ok"));
        this.a.setText(q.f(getContext(), "sobot_btn_submit"));
        this.c = (LinearLayout) findViewById(q.a(getContext(), "id", "pop_layout"));
        this.b = (TextView) findViewById(q.a(getContext(), "id", "sobot_tv_tip"));
        this.b.setText(q.f(getContext(), "sobot_chat_free_account_tip"));
        this.a.setOnClickListener(this.d);
    }

    private void a(Context context, WindowManager.LayoutParams layoutParams) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((WindowManager) context.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay().getMetrics(displayMetrics);
        getWindow().getDecorView().getWindowVisibleDisplayFrame(new Rect());
        layoutParams.width = displayMetrics.widthPixels;
    }
}
