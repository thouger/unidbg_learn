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
import com.sobot.chat.b;
import com.sobot.chat.utils.q;
import com.sobot.chat.utils.r;

/* compiled from: SobotSelectPicDialog */
public class h extends Dialog {
    private Button a;
    private Button b;
    private Button c;
    private Button d;
    private LinearLayout e;
    private View.OnClickListener f;
    private Context g;
    private final int h;

    public h(Activity activity, View.OnClickListener onClickListener) {
        super(activity, q.a(activity, "style", "sobot_clearHistoryDialogStyle"));
        this.g = activity;
        this.f = onClickListener;
        this.h = r.b(activity);
        Window window = getWindow();
        if (window != null) {
            WindowManager.LayoutParams attributes = window.getAttributes();
            if (!b.a(4) || !b.a(1)) {
                attributes.gravity = 81;
                a(activity, attributes);
            } else {
                attributes.gravity = 17;
                attributes.flags = 8;
            }
            window.setAttributes(attributes);
        }
    }

    /* access modifiers changed from: protected */
    @Override // android.app.Dialog
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(q.a(this.g, TtmlUtils.TAG_LAYOUT, "sobot_take_pic_pop"));
        a();
    }

    @Override // android.app.Dialog
    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (motionEvent.getAction() != 0) {
            return true;
        }
        if (motionEvent.getX() >= -10.0f && motionEvent.getY() >= -10.0f && motionEvent.getY() > ((float) ((this.h - this.e.getHeight()) - 20))) {
            return true;
        }
        dismiss();
        return true;
    }

    private void a(Context context, WindowManager.LayoutParams layoutParams) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((WindowManager) context.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay().getMetrics(displayMetrics);
        getWindow().getDecorView().getWindowVisibleDisplayFrame(new Rect());
        layoutParams.width = displayMetrics.widthPixels;
    }

    private void a() {
        this.a = (Button) findViewById(q.a(this.g, "id", "btn_take_photo"));
        this.a.setText(q.f(this.g, "sobot_attach_take_pic"));
        this.b = (Button) findViewById(q.a(this.g, "id", "btn_pick_photo"));
        this.b.setText(q.f(this.g, "sobot_choice_form_picture"));
        this.c = (Button) findViewById(q.a(this.g, "id", "btn_pick_vedio"));
        this.c.setText(q.f(this.g, "sobot_choice_form_vedio"));
        this.d = (Button) findViewById(q.a(this.g, "id", "btn_cancel"));
        this.d.setText(q.f(this.g, "sobot_btn_cancle"));
        this.e = (LinearLayout) findViewById(q.a(this.g, "id", "pop_layout"));
        this.a.setOnClickListener(this.f);
        this.b.setOnClickListener(this.f);
        this.c.setOnClickListener(this.f);
        this.d.setOnClickListener(this.f);
    }
}
