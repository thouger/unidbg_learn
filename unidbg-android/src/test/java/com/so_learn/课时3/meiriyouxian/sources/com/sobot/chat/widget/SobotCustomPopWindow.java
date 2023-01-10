package com.sobot.chat.widget;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.PopupWindow;

public class SobotCustomPopWindow implements PopupWindow.OnDismissListener {
    private Context a;
    private int b;
    private int c;
    private boolean d;
    private boolean e;
    private boolean f;
    private int g;
    private View h;
    private PopupWindow i;
    private int j;
    private boolean k;
    private boolean l;
    private int m;
    private PopupWindow.OnDismissListener n;
    private int o;
    private boolean p;
    private View.OnTouchListener q;
    private Window r;
    private boolean s;
    private float t;
    private boolean u;

    /* synthetic */ SobotCustomPopWindow(Context context, AnonymousClass1 r2) {
        this(context);
    }

    private SobotCustomPopWindow(Context context) {
        this.d = true;
        this.e = false;
        this.f = true;
        this.g = -1;
        this.j = -1;
        this.k = true;
        this.l = false;
        this.m = -1;
        this.o = -1;
        this.p = true;
        this.s = false;
        this.t = 0.0f;
        this.u = true;
        this.a = context;
    }

    public SobotCustomPopWindow a(View view, int i, int i2) {
        PopupWindow popupWindow = this.i;
        if (popupWindow != null) {
            try {
                popupWindow.showAsDropDown(view, i, i2);
            } catch (Exception unused) {
            }
        }
        return this;
    }

    private void a(PopupWindow popupWindow) {
        popupWindow.setClippingEnabled(this.k);
        if (this.l) {
            popupWindow.setIgnoreCheekPress();
        }
        int i = this.m;
        if (i != -1) {
            popupWindow.setInputMethodMode(i);
        }
        int i2 = this.o;
        if (i2 != -1) {
            popupWindow.setSoftInputMode(i2);
        }
        PopupWindow.OnDismissListener onDismissListener = this.n;
        if (onDismissListener != null) {
            popupWindow.setOnDismissListener(onDismissListener);
        }
        View.OnTouchListener onTouchListener = this.q;
        if (onTouchListener != null) {
            popupWindow.setTouchInterceptor(onTouchListener);
        }
        popupWindow.setTouchable(this.p);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private PopupWindow c() {
        int i;
        if (this.h == null) {
            this.h = LayoutInflater.from(this.a).inflate(this.g, (ViewGroup) null);
        }
        Activity activity = (Activity) this.h.getContext();
        if (activity != null && this.s) {
            float f = this.t;
            if (f <= 0.0f || f >= 1.0f) {
                f = 0.7f;
            }
            this.r = activity.getWindow();
            WindowManager.LayoutParams attributes = this.r.getAttributes();
            attributes.alpha = f;
            this.r.addFlags(2);
            this.r.setAttributes(attributes);
        }
        int i2 = this.b;
        if (i2 != 0 && (i = this.c) != 0) {
            this.i = new PopupWindow(this.h, i2, i);
        } else if (this.e) {
            this.i = new PopupWindow(this.h, -1, -2);
        } else {
            this.i = new PopupWindow(this.h, -2, -2);
        }
        int i3 = this.j;
        if (i3 != -1) {
            this.i.setAnimationStyle(i3);
        }
        a(this.i);
        if (this.b == 0 || this.c == 0) {
            this.i.getContentView().measure(0, 0);
            this.b = this.i.getContentView().getMeasuredWidth();
            this.c = this.i.getContentView().getMeasuredHeight();
        }
        this.i.setOnDismissListener(this);
        if (!this.u) {
            this.i.setFocusable(true);
            this.i.setOutsideTouchable(false);
            this.i.setBackgroundDrawable(null);
            this.i.getContentView().setFocusable(true);
            this.i.getContentView().setFocusableInTouchMode(true);
            this.i.getContentView().setOnKeyListener(new AnonymousClass1());
            this.i.setTouchInterceptor(new AnonymousClass2());
        } else {
            this.i.setFocusable(this.d);
            this.i.setBackgroundDrawable(new ColorDrawable(0));
            this.i.setOutsideTouchable(this.f);
        }
        this.i.update();
        return this.i;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: com.sobot.chat.widget.SobotCustomPopWindow$1  reason: invalid class name */
    public class AnonymousClass1 implements View.OnKeyListener {
        AnonymousClass1() {
        }

        @Override // android.view.View.OnKeyListener
        public boolean onKey(View view, int i, KeyEvent keyEvent) {
            if (i != 4) {
                return false;
            }
            SobotCustomPopWindow.this.i.dismiss();
            return true;
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: com.sobot.chat.widget.SobotCustomPopWindow$2  reason: invalid class name */
    public class AnonymousClass2 implements View.OnTouchListener {
        AnonymousClass2() {
        }

        @Override // android.view.View.OnTouchListener
        public boolean onTouch(View view, MotionEvent motionEvent) {
            int x = (int) motionEvent.getX();
            int y = (int) motionEvent.getY();
            if (motionEvent.getAction() == 0 && (x < 0 || x >= SobotCustomPopWindow.this.b || y < 0 || y >= SobotCustomPopWindow.this.c)) {
                Log.e("SobotCustomPopWindow", "out side ");
                Log.e("SobotCustomPopWindow", "width:" + SobotCustomPopWindow.this.i.getWidth() + "height:" + SobotCustomPopWindow.this.i.getHeight() + " x:" + x + " y  :" + y);
                return true;
            } else if (motionEvent.getAction() != 4) {
                return false;
            } else {
                Log.e("SobotCustomPopWindow", "out side ...");
                return true;
            }
        }
    }

    @Override // android.widget.PopupWindow.OnDismissListener
    public void onDismiss() {
        a();
    }

    public void a() {
        PopupWindow.OnDismissListener onDismissListener = this.n;
        if (onDismissListener != null) {
            onDismissListener.onDismiss();
        }
        Window window = this.r;
        if (window != null) {
            WindowManager.LayoutParams attributes = window.getAttributes();
            attributes.alpha = 1.0f;
            this.r.setAttributes(attributes);
        }
        PopupWindow popupWindow = this.i;
        if (popupWindow != null && popupWindow.isShowing()) {
            this.i.dismiss();
        }
    }

    public PopupWindow b() {
        return this.i;
    }

    public static class PopupWindowBuilder {
        private SobotCustomPopWindow a;

        public PopupWindowBuilder(Context context) {
            this.a = new SobotCustomPopWindow(context, null);
        }

        public PopupWindowBuilder a(boolean z) {
            this.a.d = z;
            return this;
        }

        public PopupWindowBuilder b(boolean z) {
            this.a.e = z;
            return this;
        }

        public PopupWindowBuilder a(View view) {
            this.a.h = view;
            this.a.g = -1;
            return this;
        }

        public PopupWindowBuilder c(boolean z) {
            this.a.f = z;
            return this;
        }

        public SobotCustomPopWindow a() {
            this.a.c();
            return this.a;
        }
    }
}
