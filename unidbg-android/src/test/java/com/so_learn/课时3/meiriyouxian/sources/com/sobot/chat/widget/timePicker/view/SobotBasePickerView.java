package com.sobot.chat.widget.timePicker.view;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.media.TtmlUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import com.sobot.chat.utils.q;
import com.sobot.chat.widget.timePicker.b.b;
import com.sobot.chat.widget.timePicker.d.a;

public class SobotBasePickerView {
    private final FrameLayout.LayoutParams a = new FrameLayout.LayoutParams(-1, -2, 80);
    protected ViewGroup b;
    public ViewGroup c;
    protected int d = -16417281;
    protected int e = -4007179;
    protected int f = -657931;
    protected int g = -16777216;
    protected int h = -1;
    protected View i;
    private Context j;
    private ViewGroup k;
    private ViewGroup l;
    private b m;
    private boolean n;
    private Animation o;
    private Animation p;
    private boolean q;
    private int r = 80;
    private Dialog s;
    private boolean t;
    private boolean u = true;
    private View.OnKeyListener v = new AnonymousClass4();
    private final View.OnTouchListener w = new AnonymousClass5();

    public boolean b() {
        return false;
    }

    /* access modifiers changed from: protected */
    public void d() {
    }

    public SobotBasePickerView(Context context) {
        this.j = context;
    }

    /* access modifiers changed from: protected */
    public void a(int i) {
        LayoutInflater from = LayoutInflater.from(this.j);
        if (b()) {
            this.l = (ViewGroup) from.inflate(q.a(this.j, TtmlUtils.TAG_LAYOUT, "sobot_layout_basepickerview"), (ViewGroup) null, false);
            this.l.setBackgroundColor(0);
            this.b = (ViewGroup) this.l.findViewById(q.a(this.j, "id", "content_container"));
            FrameLayout.LayoutParams layoutParams = this.a;
            layoutParams.leftMargin = 30;
            layoutParams.rightMargin = 30;
            this.b.setLayoutParams(layoutParams);
            k();
            this.l.setOnClickListener(new AnonymousClass1());
        } else {
            if (this.c == null) {
                this.c = (ViewGroup) ((Activity) this.j).getWindow().getDecorView().findViewById(16908290);
            }
            this.k = (ViewGroup) from.inflate(q.a(this.j, TtmlUtils.TAG_LAYOUT, "sobot_layout_basepickerview"), this.c, false);
            this.k.setLayoutParams(new FrameLayout.LayoutParams(-1, -1));
            if (i != 0) {
                this.k.setBackgroundColor(i);
            }
            this.b = (ViewGroup) this.k.findViewById(q.a(this.j, "id", "content_container"));
            this.b.setLayoutParams(this.a);
        }
        a(true);
    }

    /* renamed from: com.sobot.chat.widget.timePicker.view.SobotBasePickerView$1  reason: invalid class name */
    class AnonymousClass1 implements View.OnClickListener {
        AnonymousClass1() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            SobotBasePickerView.this.g();
        }
    }

    /* access modifiers changed from: protected */
    public void c() {
        this.p = i();
        this.o = j();
    }

    public void a(View view) {
        this.i = view;
        e();
    }

    public void e() {
        if (b()) {
            l();
        } else if (!f()) {
            this.q = true;
            b(this.k);
            this.k.requestFocus();
        }
    }

    private void b(View view) {
        this.c.addView(view);
        if (this.u) {
            this.b.startAnimation(this.p);
        }
    }

    public boolean f() {
        if (b()) {
            return false;
        }
        if (this.k.getParent() != null || this.q) {
            return true;
        }
        return false;
    }

    public void g() {
        if (b()) {
            m();
        } else if (!this.n) {
            if (this.u) {
                this.o.setAnimationListener(new AnonymousClass2());
                this.b.startAnimation(this.o);
            } else {
                h();
            }
            this.n = true;
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: com.sobot.chat.widget.timePicker.view.SobotBasePickerView$2  reason: invalid class name */
    public class AnonymousClass2 implements Animation.AnimationListener {
        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationRepeat(Animation animation) {
        }

        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationStart(Animation animation) {
        }

        AnonymousClass2() {
        }

        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationEnd(Animation animation) {
            SobotBasePickerView.this.h();
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: com.sobot.chat.widget.timePicker.view.SobotBasePickerView$3  reason: invalid class name */
    public class AnonymousClass3 implements Runnable {
        AnonymousClass3() {
        }

        @Override // java.lang.Runnable
        public void run() {
            SobotBasePickerView.this.c.removeView(SobotBasePickerView.this.k);
            SobotBasePickerView.this.q = false;
            SobotBasePickerView.this.n = false;
            if (SobotBasePickerView.this.m != null) {
                SobotBasePickerView.this.m.a(SobotBasePickerView.this);
            }
        }
    }

    public void h() {
        this.c.post(new AnonymousClass3());
    }

    public Animation i() {
        return AnimationUtils.loadAnimation(this.j, a.a(this.j, this.r, true));
    }

    public Animation j() {
        return AnimationUtils.loadAnimation(this.j, a.a(this.j, this.r, false));
    }

    public void a(boolean z) {
        ViewGroup viewGroup;
        if (b()) {
            viewGroup = this.l;
        } else {
            viewGroup = this.k;
        }
        viewGroup.setFocusable(z);
        viewGroup.setFocusableInTouchMode(z);
        if (z) {
            viewGroup.setOnKeyListener(this.v);
        } else {
            viewGroup.setOnKeyListener(null);
        }
    }

    /* renamed from: com.sobot.chat.widget.timePicker.view.SobotBasePickerView$4  reason: invalid class name */
    class AnonymousClass4 implements View.OnKeyListener {
        AnonymousClass4() {
        }

        @Override // android.view.View.OnKeyListener
        public boolean onKey(View view, int i, KeyEvent keyEvent) {
            if (i != 4 || keyEvent.getAction() != 0 || !SobotBasePickerView.this.f()) {
                return false;
            }
            SobotBasePickerView.this.g();
            return true;
        }
    }

    /* access modifiers changed from: protected */
    public SobotBasePickerView b(boolean z) {
        ViewGroup viewGroup = this.k;
        if (viewGroup != null) {
            View findViewById = viewGroup.findViewById(q.a(this.j, "id", "outmost_container"));
            if (z) {
                findViewById.setOnTouchListener(this.w);
            } else {
                findViewById.setOnTouchListener(null);
            }
        }
        return this;
    }

    public void c(boolean z) {
        this.t = z;
    }

    /* renamed from: com.sobot.chat.widget.timePicker.view.SobotBasePickerView$5  reason: invalid class name */
    class AnonymousClass5 implements View.OnTouchListener {
        AnonymousClass5() {
        }

        @Override // android.view.View.OnTouchListener
        public boolean onTouch(View view, MotionEvent motionEvent) {
            if (motionEvent.getAction() != 0) {
                return false;
            }
            SobotBasePickerView.this.g();
            return false;
        }
    }

    public View b(int i) {
        return this.b.findViewById(i);
    }

    public void k() {
        if (this.l != null) {
            Context context = this.j;
            this.s = new Dialog(context, q.a(context, "style", "sobot_custom_dialog"));
            this.s.setCancelable(this.t);
            this.s.setContentView(this.l);
            Window window = this.s.getWindow();
            if (window != null) {
                window.setWindowAnimations(q.a(this.j, "style", "sobot_pickerview_dialogAnim"));
            }
            this.s.setOnDismissListener(new AnonymousClass6());
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: com.sobot.chat.widget.timePicker.view.SobotBasePickerView$6  reason: invalid class name */
    public class AnonymousClass6 implements DialogInterface.OnDismissListener {
        AnonymousClass6() {
        }

        @Override // android.content.DialogInterface.OnDismissListener
        public void onDismiss(DialogInterface dialogInterface) {
            if (SobotBasePickerView.this.m != null) {
                SobotBasePickerView.this.m.a(SobotBasePickerView.this);
            }
        }
    }

    public void l() {
        Dialog dialog = this.s;
        if (dialog != null) {
            dialog.show();
        }
    }

    public void m() {
        Dialog dialog = this.s;
        if (dialog != null) {
            dialog.dismiss();
        }
    }
}
