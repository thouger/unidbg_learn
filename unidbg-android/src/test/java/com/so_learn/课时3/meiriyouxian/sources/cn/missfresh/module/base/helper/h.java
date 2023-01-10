package cn.missfresh.module.base.helper;

import android.app.Activity;
import android.app.AlarmManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import cn.missfresh.module.base.R;
import cn.missfresh.module.base.utils.aw;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;

/* compiled from: PopToBackViewHelper */
public class h {
    private static final int b = aw.b(100);
    private static final int c = aw.b(150);
    private static h d = new h();
    View.OnClickListener a = new PopToBackViewHelper$2(this);
    private long e = -1;
    private Activity f;
    private WindowManager g;
    private WindowManager.LayoutParams h;
    private View i;
    private int j;
    private String k;
    private String l;
    private int m;
    private int n;
    private float o;
    private float p;
    private int q = -1;
    private int r = 0;
    private int s = -1;
    private boolean t = false;

    static {
        AppMethodBeat.i(13111, false);
        AppMethodBeat.o(13111);
    }

    private h() {
        AppMethodBeat.i(13087, false);
        AppMethodBeat.o(13087);
    }

    public static h a() {
        return d;
    }

    public void a(String str, String str2) {
        AppMethodBeat.i(13089, false);
        this.k = str;
        this.l = str2;
        this.e = System.currentTimeMillis() + AlarmManager.INTERVAL_HALF_HOUR;
        AppMethodBeat.o(13089);
    }

    public void a(int i) {
        this.q = i;
    }

    public void b(int i) {
        this.j = i;
    }

    public void a(Activity activity) {
        AppMethodBeat.i(13092, false);
        this.f = activity;
        this.g = activity.getWindowManager();
        if (this.s == -1) {
            this.s = h();
            this.r = 0;
        }
        this.m = aw.a(activity);
        this.n = aw.b(activity);
        if (c()) {
            if (System.currentTimeMillis() >= this.e) {
                b();
            }
        } else if (System.currentTimeMillis() < this.e) {
            f();
        }
        AppMethodBeat.o(13092);
    }

    public void b(Activity activity) {
        AppMethodBeat.i(13093, false);
        Activity activity2 = this.f;
        if (activity2 != null && activity2.equals(activity) && c()) {
            j();
        }
        AppMethodBeat.o(13093);
    }

    private void f() {
        AppMethodBeat.i(13095, false);
        if (c()) {
            AppMethodBeat.o(13095);
            return;
        }
        Activity activity = this.f;
        if (activity == null || activity.isFinishing()) {
            AppMethodBeat.o(13095);
            return;
        }
        this.i = g();
        this.h = i();
        this.g.addView(this.i, this.h);
        AppMethodBeat.o(13095);
    }

    public void b() {
        AppMethodBeat.i(13096, false);
        this.e = -1;
        this.s = -1;
        j();
        AppMethodBeat.o(13096);
    }

    /* compiled from: PopToBackViewHelper */
    /* access modifiers changed from: package-private */
    /* renamed from: cn.missfresh.module.base.helper.h$1  reason: invalid class name */
    public class AnonymousClass1 implements View.OnTouchListener {
        AnonymousClass1() {
        }

        /* JADX WARNING: Code restructure failed: missing block: B:7:0x0013, code lost:
            if (r1 != 3) goto L_0x00c5;
         */
        @Override // android.view.View.OnTouchListener
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public boolean onTouch(android.view.View r6, android.view.MotionEvent r7) {
            /*
                r5 = this;
                r6 = 13078(0x3316, float:1.8326E-41)
                r0 = 0
                cn.missfresh.sherlock.trace.core.AppMethodBeat.i(r6, r0)
                int r1 = r7.getActionMasked()
                if (r1 == 0) goto L_0x00b3
                r2 = 1
                if (r1 == r2) goto L_0x0095
                r2 = 2
                if (r1 == r2) goto L_0x0017
                r7 = 3
                if (r1 == r7) goto L_0x0095
                goto L_0x00c5
            L_0x0017:
                r7.getRawX()
                cn.missfresh.module.base.helper.h r1 = cn.missfresh.module.base.helper.h.this
                cn.missfresh.module.base.helper.h.a(r1)
                float r1 = r7.getRawY()
                cn.missfresh.module.base.helper.h r2 = cn.missfresh.module.base.helper.h.this
                float r2 = cn.missfresh.module.base.helper.h.b(r2)
                float r1 = r1 - r2
                cn.missfresh.module.base.helper.h r2 = cn.missfresh.module.base.helper.h.this
                android.view.WindowManager$LayoutParams r2 = cn.missfresh.module.base.helper.h.c(r2)
                int r3 = r2.y
                float r3 = (float) r3
                float r3 = r3 + r1
                cn.missfresh.module.base.helper.h r1 = cn.missfresh.module.base.helper.h.this
                float r4 = r7.getRawX()
                cn.missfresh.module.base.helper.h.a(r1, r4)
                cn.missfresh.module.base.helper.h r1 = cn.missfresh.module.base.helper.h.this
                float r7 = r7.getRawY()
                cn.missfresh.module.base.helper.h.b(r1, r7)
                int r7 = cn.missfresh.module.base.helper.h.d()
                float r7 = (float) r7
                int r7 = (r3 > r7 ? 1 : (r3 == r7 ? 0 : -1))
                if (r7 >= 0) goto L_0x0056
                int r7 = cn.missfresh.module.base.helper.h.d()
                r2.y = r7
                goto L_0x0078
            L_0x0056:
                int r7 = cn.missfresh.module.base.helper.h.e()
                float r7 = (float) r7
                float r7 = r7 + r3
                cn.missfresh.module.base.helper.h r1 = cn.missfresh.module.base.helper.h.this
                int r1 = cn.missfresh.module.base.helper.h.d(r1)
                float r1 = (float) r1
                int r7 = (r7 > r1 ? 1 : (r7 == r1 ? 0 : -1))
                if (r7 <= 0) goto L_0x0075
                cn.missfresh.module.base.helper.h r7 = cn.missfresh.module.base.helper.h.this
                int r7 = cn.missfresh.module.base.helper.h.d(r7)
                int r1 = cn.missfresh.module.base.helper.h.e()
                int r7 = r7 - r1
                r2.y = r7
                goto L_0x0078
            L_0x0075:
                int r7 = (int) r3
                r2.y = r7
            L_0x0078:
                cn.missfresh.module.base.helper.h r7 = cn.missfresh.module.base.helper.h.this
                android.view.WindowManager r7 = cn.missfresh.module.base.helper.h.f(r7)
                cn.missfresh.module.base.helper.h r1 = cn.missfresh.module.base.helper.h.this
                android.view.View r1 = cn.missfresh.module.base.helper.h.e(r1)
                cn.missfresh.module.base.helper.h r3 = cn.missfresh.module.base.helper.h.this
                android.view.WindowManager$LayoutParams r3 = cn.missfresh.module.base.helper.h.c(r3)
                r7.updateViewLayout(r1, r3)
                cn.missfresh.module.base.helper.h r7 = cn.missfresh.module.base.helper.h.this
                int r1 = r2.y
                cn.missfresh.module.base.helper.h.a(r7, r1)
                goto L_0x00c5
            L_0x0095:
                cn.missfresh.module.base.helper.h r7 = cn.missfresh.module.base.helper.h.this
                android.view.WindowManager$LayoutParams r7 = cn.missfresh.module.base.helper.h.c(r7)
                r7.x = r0
                cn.missfresh.module.base.helper.h r7 = cn.missfresh.module.base.helper.h.this
                android.view.WindowManager r7 = cn.missfresh.module.base.helper.h.f(r7)
                cn.missfresh.module.base.helper.h r1 = cn.missfresh.module.base.helper.h.this
                android.view.View r1 = cn.missfresh.module.base.helper.h.e(r1)
                cn.missfresh.module.base.helper.h r2 = cn.missfresh.module.base.helper.h.this
                android.view.WindowManager$LayoutParams r2 = cn.missfresh.module.base.helper.h.c(r2)
                r7.updateViewLayout(r1, r2)
                goto L_0x00c5
            L_0x00b3:
                cn.missfresh.module.base.helper.h r1 = cn.missfresh.module.base.helper.h.this
                float r2 = r7.getRawX()
                cn.missfresh.module.base.helper.h.a(r1, r2)
                cn.missfresh.module.base.helper.h r1 = cn.missfresh.module.base.helper.h.this
                float r7 = r7.getRawY()
                cn.missfresh.module.base.helper.h.b(r1, r7)
            L_0x00c5:
                cn.missfresh.sherlock.trace.core.AppMethodBeat.o(r6)
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: cn.missfresh.module.base.helper.h.AnonymousClass1.onTouch(android.view.View, android.view.MotionEvent):boolean");
        }
    }

    private void a(View view) {
        AppMethodBeat.i(13099, false);
        view.setOnTouchListener(new AnonymousClass1());
        AppMethodBeat.o(13099);
    }

    private View g() {
        AppMethodBeat.i(13100, false);
        View inflate = LayoutInflater.from(this.f).inflate(R.layout.dialog_toback_float_view, (ViewGroup) null);
        inflate.findViewById(R.id.v_toback_box).setOnClickListener(this.a);
        inflate.findViewById(R.id.v_close).setOnClickListener(this.a);
        TextView textView = (TextView) inflate.findViewById(R.id.tv_label);
        ImageView imageView = (ImageView) inflate.findViewById(R.id.iv_logo);
        if (this.j != 0) {
            imageView.setVisibility(0);
            imageView.setImageResource(this.j);
        }
        textView.setText(this.k);
        if (this.t) {
            a(inflate);
        }
        int i = this.q;
        if (i != -1) {
            inflate.setBackgroundResource(i);
        }
        inflate.getBackground().setAlpha(204);
        AppMethodBeat.o(13100);
        return inflate;
    }

    private int h() {
        AppMethodBeat.i(13101, false);
        Activity activity = this.f;
        if (activity == null || activity.isFinishing()) {
            AppMethodBeat.o(13101);
            return 0;
        }
        int b2 = aw.b(this.f) - c;
        AppMethodBeat.o(13101);
        return b2;
    }

    private WindowManager.LayoutParams i() {
        AppMethodBeat.i(13102, false);
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        layoutParams.width = -2;
        layoutParams.height = -2;
        layoutParams.x = this.r;
        layoutParams.y = this.s;
        layoutParams.dimAmount = 0.0f;
        layoutParams.format = 1;
        layoutParams.gravity = 51;
        layoutParams.flags = 1003;
        AppMethodBeat.o(13102);
        return layoutParams;
    }

    public boolean c() {
        boolean z = false;
        AppMethodBeat.i(13103, false);
        View view = this.i;
        if (view != null && view.isShown()) {
            z = true;
        }
        AppMethodBeat.o(13103);
        return z;
    }

    private void j() {
        AppMethodBeat.i(13104, false);
        if (c()) {
            this.g.removeViewImmediate(this.i);
        }
        AppMethodBeat.o(13104);
    }
}
