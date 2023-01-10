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

/* compiled from: SobotBackDialog */
public class a extends Dialog {
    private Button a;
    private Button b;
    private TextView c;
    private LinearLayout d;
    private View.OnClickListener e;
    private final int f;

    public a(Activity activity, View.OnClickListener onClickListener) {
        super(activity, q.a(activity, "style", "sobot_noAnimDialogStyle"));
        this.e = onClickListener;
        this.f = r.b(activity);
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
        setContentView(q.a(getContext(), TtmlUtils.TAG_LAYOUT, "sobot_back_popup"));
        a();
    }

    @Override // android.app.Dialog
    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (motionEvent.getAction() != 0) {
            return true;
        }
        if (motionEvent.getX() >= -10.0f && motionEvent.getY() >= -10.0f && motionEvent.getY() > ((float) ((this.f - this.d.getHeight()) - 20))) {
            return true;
        }
        dismiss();
        return true;
    }

    private void a() {
        this.a = (Button) findViewById(q.a(getContext(), "id", "sobot_btn_cancle_conversation"));
        this.a.setText(q.f(getContext(), "sobot_cancle_conversation"));
        this.b = (Button) findViewById(q.a(getContext(), "id", "sobot_btn_temporary_leave"));
        this.b.setText(q.f(getContext(), "sobot_temporary_leave"));
        this.d = (LinearLayout) findViewById(q.a(getContext(), "id", "pop_layout"));
        this.c = (TextView) findViewById(q.a(getContext(), "id", "sobot_tv_will_end_conversation"));
        this.c.setText(q.f(getContext(), "sobot_will_end_conversation"));
        this.a.setOnClickListener(this.e);
        this.b.setOnClickListener(this.e);
    }

    private void a(Context context, WindowManager.LayoutParams layoutParams) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((WindowManager) context.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay().getMetrics(displayMetrics);
        getWindow().getDecorView().getWindowVisibleDisplayFrame(new Rect());
        layoutParams.width = displayMetrics.widthPixels;
    }
}
