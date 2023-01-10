package com.sobot.chat.widget;

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
import com.sobot.chat.utils.q;

public class ClearHistoryDialog extends Dialog implements View.OnClickListener {
    private Button a;
    private Button b;
    private a c;
    private LinearLayout d;
    private final int e;

    public interface a {
        void a();
    }

    private void a() {
    }

    public ClearHistoryDialog(Activity activity) {
        super(activity, q.a(activity, "style", "sobot_clearHistoryDialogStyle"));
        this.e = a(activity);
        Window window = getWindow();
        if (window != null) {
            WindowManager.LayoutParams attributes = window.getAttributes();
            attributes.gravity = 81;
            a(activity, attributes);
            window.setAttributes(attributes);
        }
    }

    private void a(Context context, WindowManager.LayoutParams layoutParams) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((WindowManager) context.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay().getMetrics(displayMetrics);
        Rect rect = new Rect();
        if (getWindow() != null) {
            getWindow().getDecorView().getWindowVisibleDisplayFrame(rect);
            layoutParams.width = displayMetrics.widthPixels;
        }
    }

    @Override // android.app.Dialog
    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (motionEvent.getAction() != 0) {
            return true;
        }
        if (motionEvent.getX() >= -10.0f && motionEvent.getY() >= -10.0f && motionEvent.getY() > ((float) ((this.e - this.d.getHeight()) - 20))) {
            return true;
        }
        dismiss();
        return true;
    }

    /* access modifiers changed from: protected */
    @Override // android.app.Dialog
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(q.a(getContext(), TtmlUtils.TAG_LAYOUT, "sobot_clear_history_dialog"));
        b();
        a();
    }

    private void b() {
        this.a = (Button) findViewById(q.a(getContext(), "id", "sobot_btn_take_photo"));
        this.a.setText(q.f(getContext(), "sobot_save_pic"));
        this.b = (Button) findViewById(q.a(getContext(), "id", "sobot_btn_cancel"));
        this.b.setText(q.f(getContext(), "sobot_btn_cancle"));
        this.d = (LinearLayout) findViewById(q.a(getContext(), "id", "sobot_pop_layout"));
        this.a.setText(q.f(getContext(), "sobot_clear_history_message"));
        this.a.setTextColor(getContext().getResources().getColor(q.a(getContext(), "color", "sobot_text_delete_hismsg_color")));
        this.a.setOnClickListener(this);
        this.b.setOnClickListener(this);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        a aVar;
        dismiss();
        if (view == this.a && (aVar = this.c) != null) {
            aVar.a();
        }
    }

    public void a(a aVar) {
        this.c = aVar;
    }

    public static int a(Activity activity) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.heightPixels;
    }
}
