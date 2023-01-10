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

/* compiled from: SobotDeleteWorkOrderDialog */
public class c extends Dialog {
    private Button a;
    private Button b;
    private LinearLayout c;
    private TextView d;
    private View.OnClickListener e;
    private final int f;
    private String g;
    private int h;

    public int a() {
        return this.h;
    }

    public void a(int i) {
        this.h = i;
    }

    public c(Activity activity, String str, View.OnClickListener onClickListener) {
        super(activity, q.a(activity, "style", "sobot_noAnimDialogStyle"));
        this.e = onClickListener;
        this.g = str;
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
        setContentView(q.a(getContext(), TtmlUtils.TAG_LAYOUT, "sobot_delete_picture_popup"));
        b();
    }

    @Override // android.app.Dialog
    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (motionEvent.getAction() != 0) {
            return true;
        }
        if (motionEvent.getX() >= -10.0f && motionEvent.getY() >= -10.0f && motionEvent.getY() > ((float) ((this.f - this.c.getHeight()) - 20))) {
            return true;
        }
        dismiss();
        return true;
    }

    private void b() {
        this.d = (TextView) findViewById(q.a(getContext(), "id", "tv_photo_hint"));
        this.d.setText(q.f(getContext(), "sobot_title"));
        this.a = (Button) findViewById(q.a(getContext(), "id", "btn_pick_photo"));
        this.a.setText(q.f(getContext(), "sobot_delete"));
        this.b = (Button) findViewById(q.a(getContext(), "id", "btn_cancel"));
        this.b.setText(q.f(getContext(), "sobot_btn_cancle"));
        this.c = (LinearLayout) findViewById(q.a(getContext(), "id", "pop_layout"));
        this.a.setOnClickListener(this.e);
        this.b.setOnClickListener(this.e);
        this.d.setText(this.g);
    }

    private void a(Context context, WindowManager.LayoutParams layoutParams) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((WindowManager) context.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay().getMetrics(displayMetrics);
        getWindow().getDecorView().getWindowVisibleDisplayFrame(new Rect());
        layoutParams.width = displayMetrics.widthPixels;
    }
}
