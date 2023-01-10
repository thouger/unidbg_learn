package com.sobot.chat.widget.dialog.a;

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
import com.sobot.chat.utils.q;
import com.sobot.chat.widget.kpswitch.util.c;

/* compiled from: SobotActionSheet */
public abstract class a extends Dialog {
    protected View a;
    private final int b;

    /* access modifiers changed from: protected */
    public abstract String a();

    /* access modifiers changed from: protected */
    public abstract View b();

    /* access modifiers changed from: protected */
    public abstract void c();

    /* access modifiers changed from: protected */
    public abstract void d();

    public a(Activity activity) {
        this(activity, q.a(activity, "style", "sobot_clearHistoryDialogStyle"));
    }

    public a(Activity activity, int i) {
        super(activity, i);
        this.b = a(activity);
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
        if (motionEvent.getX() >= -10.0f && motionEvent.getY() >= -10.0f) {
            double y = (double) motionEvent.getY();
            int i = this.b;
            double d = (double) i;
            View view = this.a;
            if (y > (d - (view != null ? (double) view.getHeight() : ((double) i) * 0.7d)) - 20.0d) {
                return true;
            }
        }
        dismiss();
        return true;
    }

    /* access modifiers changed from: protected */
    @Override // android.app.Dialog
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(q.a(getContext(), TtmlUtils.TAG_LAYOUT, a()));
        c();
        this.a = b();
        this.a.measure(0, 0);
        d();
    }

    public static int a(Activity activity) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.heightPixels;
    }

    public int b(String str) {
        return q.a(getContext(), "id", str);
    }

    public String c(String str) {
        return q.f(getContext(), str);
    }

    @Override // android.app.Dialog, android.content.DialogInterface
    public void dismiss() {
        c.b(getCurrentFocus());
        super.dismiss();
    }
}
