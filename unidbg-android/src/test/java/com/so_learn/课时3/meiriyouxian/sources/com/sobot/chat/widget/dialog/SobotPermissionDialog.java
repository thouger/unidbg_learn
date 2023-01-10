package com.sobot.chat.widget.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Rect;
import android.media.TtmlUtils;
import android.os.Bundle;
import android.text.TextUtils;
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

public class SobotPermissionDialog extends Dialog implements View.OnClickListener {
    private Button a;
    private Button b;
    private LinearLayout c;
    private a d;
    private final int e;
    private TextView f;
    private String g;

    public interface a {
        void a(Context context, SobotPermissionDialog sobotPermissionDialog);

        void b(Context context, SobotPermissionDialog sobotPermissionDialog);
    }

    public SobotPermissionDialog(Activity activity, a aVar) {
        super(activity, q.a(activity, "style", "sobot_noAnimDialogStyle"));
        this.d = aVar;
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

    public SobotPermissionDialog(Activity activity, String str, a aVar) {
        this(activity, aVar);
        this.g = str;
    }

    /* access modifiers changed from: protected */
    @Override // android.app.Dialog
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(q.a(getContext(), TtmlUtils.TAG_LAYOUT, "sobot_permission_popup"));
        a();
    }

    @Override // android.app.Dialog
    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (motionEvent.getAction() != 0) {
            return true;
        }
        if (motionEvent.getX() >= -10.0f && motionEvent.getY() >= -10.0f && motionEvent.getY() > ((float) ((this.e - this.c.getHeight()) - 20))) {
            return true;
        }
        dismiss();
        return true;
    }

    private void a() {
        this.f = (TextView) findViewById(q.a(getContext(), "id", "sobot_dialog_title"));
        this.f.setText(q.f(getContext(), "sobot_no_permission_text"));
        if (!TextUtils.isEmpty(this.g)) {
            this.f.setText(this.g);
        }
        this.a = (Button) findViewById(q.a(getContext(), "id", "sobot_btn_left"));
        this.a.setText(q.f(getContext(), "sobot_btn_cancle"));
        this.b = (Button) findViewById(q.a(getContext(), "id", "sobot_btn_right"));
        this.b.setText(q.f(getContext(), "sobot_go_setting"));
        this.c = (LinearLayout) findViewById(q.a(getContext(), "id", "pop_layout"));
        this.a.setOnClickListener(this);
        this.b.setOnClickListener(this);
    }

    private void a(Context context, WindowManager.LayoutParams layoutParams) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((WindowManager) context.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay().getMetrics(displayMetrics);
        getWindow().getDecorView().getWindowVisibleDisplayFrame(new Rect());
        layoutParams.width = displayMetrics.widthPixels;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        a aVar;
        a aVar2;
        if (view == this.a && (aVar2 = this.d) != null) {
            aVar2.b(getContext(), this);
        }
        if (view == this.b && (aVar = this.d) != null) {
            aVar.a(getContext(), this);
        }
    }
}
