package com.sobot.chat.camera;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import com.sobot.chat.camera.a.b;
import com.sobot.chat.camera.a.c;
import com.sobot.chat.camera.a.e;
import com.sobot.chat.camera.a.f;
import com.sobot.chat.utils.q;

public class CaptureLayout extends FrameLayout {
    private b a;
    private f b;
    private e c;
    private c d;
    private c e;
    private CaptureButton f;
    private StTypeButton g;
    private StTypeButton h;
    private StReturnButton i;
    private ImageView j;
    private ImageView k;
    private TextView l;
    private int m;
    private int n;
    private int o;
    private int p;
    private int q;
    private boolean r;

    public void setTypeLisenter(f fVar) {
        this.b = fVar;
    }

    public void setCaptureLisenter(b bVar) {
        this.a = bVar;
    }

    public void setReturnLisenter(e eVar) {
        this.c = eVar;
    }

    public CaptureLayout(Context context) {
        this(context, null);
    }

    public CaptureLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public CaptureLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.p = 0;
        this.q = 0;
        this.r = true;
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((WindowManager) context.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay().getMetrics(displayMetrics);
        if (getResources().getConfiguration().orientation == 1) {
            this.m = displayMetrics.widthPixels;
        } else {
            this.m = displayMetrics.widthPixels / 2;
        }
        this.o = (int) (((float) this.m) / 4.5f);
        int i2 = this.o;
        this.n = i2 + ((i2 / 5) * 2) + 100;
        e();
        a();
    }

    /* access modifiers changed from: protected */
    @Override // android.widget.FrameLayout, android.view.View
    public void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        setMeasuredDimension(this.m, this.n);
    }

    public void a() {
        this.k.setVisibility(8);
        this.h.setVisibility(8);
        this.g.setVisibility(8);
    }

    public void b() {
        if (this.p != 0) {
            this.j.setVisibility(8);
        } else {
            this.i.setVisibility(8);
        }
        if (this.q != 0) {
            this.k.setVisibility(8);
        }
        this.f.setVisibility(8);
        this.h.setVisibility(0);
        this.g.setVisibility(0);
        this.h.setClickable(false);
        this.g.setClickable(false);
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this.h, "translationX", (float) (this.m / 4), 0.0f);
        ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(this.g, "translationX", (float) ((-this.m) / 4), 0.0f);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(ofFloat, ofFloat2);
        animatorSet.addListener(new AnonymousClass1());
        animatorSet.setDuration(200L);
        animatorSet.start();
    }

    /* access modifiers changed from: package-private */
    /* renamed from: com.sobot.chat.camera.CaptureLayout$1  reason: invalid class name */
    public class AnonymousClass1 extends AnimatorListenerAdapter {
        AnonymousClass1() {
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            super.onAnimationEnd(animator);
            CaptureLayout.this.h.setClickable(true);
            CaptureLayout.this.g.setClickable(true);
        }
    }

    private void e() {
        setWillNotDraw(false);
        this.f = new CaptureButton(getContext(), this.o);
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, -1);
        layoutParams.gravity = 17;
        layoutParams.topMargin = 20;
        this.f.setLayoutParams(layoutParams);
        this.f.setCaptureLisenter(new AnonymousClass2());
        this.h = new StTypeButton(getContext(), 1, this.o);
        FrameLayout.LayoutParams layoutParams2 = new FrameLayout.LayoutParams(-1, -1);
        layoutParams2.gravity = 16;
        layoutParams2.setMargins((this.m / 4) - (this.o / 2), 0, 0, 0);
        this.h.setLayoutParams(layoutParams2);
        this.h.setOnClickListener(new AnonymousClass3());
        this.g = new StTypeButton(getContext(), 2, this.o);
        FrameLayout.LayoutParams layoutParams3 = new FrameLayout.LayoutParams(-1, -1);
        layoutParams3.gravity = 21;
        layoutParams3.setMargins(0, 0, (this.m / 4) - (this.o / 2), 0);
        this.g.setLayoutParams(layoutParams3);
        this.g.setOnClickListener(new AnonymousClass4());
        this.i = new StReturnButton(getContext(), (int) (((float) this.o) / 2.5f));
        FrameLayout.LayoutParams layoutParams4 = new FrameLayout.LayoutParams(-2, -2);
        layoutParams4.gravity = 16;
        layoutParams4.setMargins(this.m / 6, 0, 0, 0);
        this.i.setLayoutParams(layoutParams4);
        this.i.setOnClickListener(new AnonymousClass5());
        this.j = new ImageView(getContext());
        int i = this.o;
        FrameLayout.LayoutParams layoutParams5 = new FrameLayout.LayoutParams((int) (((float) i) / 2.5f), (int) (((float) i) / 2.5f));
        layoutParams5.gravity = 16;
        layoutParams5.setMargins(this.m / 6, 0, 0, 0);
        this.j.setLayoutParams(layoutParams5);
        this.j.setOnClickListener(new AnonymousClass6());
        this.k = new ImageView(getContext());
        int i2 = this.o;
        FrameLayout.LayoutParams layoutParams6 = new FrameLayout.LayoutParams((int) (((float) i2) / 2.5f), (int) (((float) i2) / 2.5f));
        layoutParams6.gravity = 21;
        layoutParams6.setMargins(0, 0, this.m / 6, 0);
        this.k.setLayoutParams(layoutParams6);
        this.k.setOnClickListener(new AnonymousClass7());
        this.l = new TextView(getContext());
        FrameLayout.LayoutParams layoutParams7 = new FrameLayout.LayoutParams(-1, -2);
        layoutParams7.gravity = 1;
        layoutParams7.setMargins(0, 0, 0, 0);
        this.l.setText(q.f(getContext(), "sobot_tap_hold_camera"));
        this.l.setTextColor(-1);
        this.l.setGravity(17);
        this.l.setLayoutParams(layoutParams7);
        addView(this.f);
        addView(this.h);
        addView(this.g);
        addView(this.i);
        addView(this.j);
        addView(this.l);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: com.sobot.chat.camera.CaptureLayout$2  reason: invalid class name */
    public class AnonymousClass2 implements b {
        AnonymousClass2() {
        }

        @Override // com.sobot.chat.camera.a.b
        public void a() {
            if (CaptureLayout.this.a != null) {
                CaptureLayout.this.a.a();
            }
        }

        @Override // com.sobot.chat.camera.a.b
        public void a(long j) {
            if (CaptureLayout.this.a != null) {
                CaptureLayout.this.a.a(j);
            }
            CaptureLayout.this.d();
        }

        @Override // com.sobot.chat.camera.a.b
        public void b() {
            if (CaptureLayout.this.a != null) {
                CaptureLayout.this.a.b();
            }
            CaptureLayout.this.d();
        }

        @Override // com.sobot.chat.camera.a.b
        public void b(long j) {
            if (CaptureLayout.this.a != null) {
                CaptureLayout.this.a.b(j);
            }
            CaptureLayout.this.d();
            CaptureLayout.this.b();
        }

        @Override // com.sobot.chat.camera.a.b
        public void a(float f) {
            if (CaptureLayout.this.a != null) {
                CaptureLayout.this.a.a(f);
            }
        }

        @Override // com.sobot.chat.camera.a.b
        public void c() {
            if (CaptureLayout.this.a != null) {
                CaptureLayout.this.a.c();
            }
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: com.sobot.chat.camera.CaptureLayout$3  reason: invalid class name */
    public class AnonymousClass3 implements View.OnClickListener {
        AnonymousClass3() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (CaptureLayout.this.b != null) {
                CaptureLayout.this.b.a();
            }
            CaptureLayout.this.d();
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: com.sobot.chat.camera.CaptureLayout$4  reason: invalid class name */
    public class AnonymousClass4 implements View.OnClickListener {
        AnonymousClass4() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (CaptureLayout.this.b != null) {
                CaptureLayout.this.b.b();
            }
            CaptureLayout.this.d();
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: com.sobot.chat.camera.CaptureLayout$5  reason: invalid class name */
    public class AnonymousClass5 implements View.OnClickListener {
        AnonymousClass5() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (CaptureLayout.this.d != null && CaptureLayout.this.f.a()) {
                CaptureLayout.this.d.a();
            }
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: com.sobot.chat.camera.CaptureLayout$6  reason: invalid class name */
    public class AnonymousClass6 implements View.OnClickListener {
        AnonymousClass6() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (CaptureLayout.this.d != null && CaptureLayout.this.f.a()) {
                CaptureLayout.this.d.a();
            }
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: com.sobot.chat.camera.CaptureLayout$7  reason: invalid class name */
    public class AnonymousClass7 implements View.OnClickListener {
        AnonymousClass7() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (CaptureLayout.this.e != null && CaptureLayout.this.f.a()) {
                CaptureLayout.this.e.a();
            }
        }
    }

    public void c() {
        this.f.b();
        this.h.setVisibility(8);
        this.g.setVisibility(8);
        this.f.setVisibility(0);
        if (this.p != 0) {
            this.j.setVisibility(0);
        } else {
            this.i.setVisibility(0);
        }
        if (this.q != 0) {
            this.k.setVisibility(0);
        }
    }

    public void d() {
        if (this.r) {
            ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this.l, "alpha", 1.0f, 0.0f);
            ofFloat.setDuration(500L);
            ofFloat.start();
            this.r = false;
        }
    }

    public void setTextWithAnimation(String str) {
        this.l.setText(str);
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this.l, "alpha", 0.0f, 1.0f, 1.0f, 0.0f);
        ofFloat.setDuration(2500L);
        ofFloat.start();
    }

    public void setDuration(int i) {
        this.f.setDuration(i);
    }

    public void setButtonFeatures(int i) {
        this.f.setButtonFeatures(i);
    }

    public void setTip(String str) {
        this.l.setText(str);
    }

    public void a(int i, int i2) {
        this.p = i;
        this.q = i2;
        if (this.p != 0) {
            this.j.setImageResource(i);
            this.j.setVisibility(0);
            this.i.setVisibility(8);
        } else {
            this.j.setVisibility(8);
            this.i.setVisibility(0);
        }
        if (this.q != 0) {
            this.k.setImageResource(i2);
            this.k.setVisibility(0);
            return;
        }
        this.k.setVisibility(8);
    }

    public void setLeftClickListener(c cVar) {
        this.d = cVar;
    }

    public void setRightClickListener(c cVar) {
        this.e = cVar;
    }
}
