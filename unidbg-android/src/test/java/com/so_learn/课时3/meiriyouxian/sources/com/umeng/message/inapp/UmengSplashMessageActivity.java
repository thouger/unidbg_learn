package com.umeng.message.inapp;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.wifi.WifiEnterpriseConfig;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.os.SystemClock;
import android.text.TextUtils;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.umeng.message.entity.UInAppMessage;
import com.umeng.message.inapp.UImageLoadTask;
import com.umeng.message.proguard.j;
import java.util.Calendar;

public class UmengSplashMessageActivity extends Activity {
    private static final String a = UmengSplashMessageActivity.class.getName();
    private static int s = 2000;
    private static int t = 1000;
    private Activity b;
    private UImageLoadTask c;
    private ImageView d;
    private ImageView e;
    private TextView f;
    private boolean g = true;
    private boolean h = true;
    private a i;
    private a j;
    private UInAppMessage k;
    private UInAppHandler l;
    private boolean m = false;
    private boolean n = false;
    private boolean o = false;
    private boolean p = false;
    private long q;
    private long r;
    private UImageLoadTask.ImageLoaderCallback u = new AnonymousClass1();
    private IUmengInAppMessageCallback v = new AnonymousClass2();

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private int a(boolean z) {
        return z ? 1 : 0;
    }

    @Override // android.app.Activity
    public final void onBackPressed() {
    }

    public boolean onCustomPretreatment() {
        return false;
    }

    /* renamed from: com.umeng.message.inapp.UmengSplashMessageActivity$1  reason: invalid class name */
    class AnonymousClass1 implements UImageLoadTask.ImageLoaderCallback {
        AnonymousClass1() {
        }

        @Override // com.umeng.message.inapp.UImageLoadTask.ImageLoaderCallback
        public void onLoadImage(Bitmap[] bitmapArr) {
            if (!UmengSplashMessageActivity.this.e()) {
                if (UmengSplashMessageActivity.this.i != null) {
                    UmengSplashMessageActivity.this.i.a();
                    UmengSplashMessageActivity.this.i = null;
                }
                try {
                    if (bitmapArr.length == 1) {
                        UmengSplashMessageActivity.this.d.setOnClickListener(new AnonymousClass1());
                        UmengSplashMessageActivity.this.e.setVisibility(8);
                        UmengSplashMessageActivity.this.d.setImageBitmap(bitmapArr[0]);
                        UmengSplashMessageActivity.this.a(UmengSplashMessageActivity.this.d);
                    }
                    if (bitmapArr.length == 2) {
                        UmengSplashMessageActivity.this.d.setOnClickListener(new AnonymousClass2());
                        UmengSplashMessageActivity.this.e.setOnClickListener(new AnonymousClass3());
                        UmengSplashMessageActivity.this.d.setImageBitmap(bitmapArr[0]);
                        UmengSplashMessageActivity.this.e.setImageBitmap(bitmapArr[1]);
                        UmengSplashMessageActivity.this.a(UmengSplashMessageActivity.this.d);
                        UmengSplashMessageActivity.this.a(UmengSplashMessageActivity.this.e);
                    }
                    UmengSplashMessageActivity.this.q = SystemClock.elapsedRealtime();
                    if (!UmengSplashMessageActivity.this.k.display_button) {
                        UmengSplashMessageActivity.this.f.setVisibility(8);
                    } else {
                        UmengSplashMessageActivity.this.f.setVisibility(0);
                        UmengSplashMessageActivity.this.f.setOnClickListener(new AnonymousClass4());
                    }
                    InAppMessageManager.getInstance(UmengSplashMessageActivity.this.b).a(UmengSplashMessageActivity.this.k);
                    InAppMessageManager.getInstance(UmengSplashMessageActivity.this.b).a(UmengSplashMessageActivity.this.k.msg_id, 1);
                    InAppMessageManager.getInstance(UmengSplashMessageActivity.this.b).h();
                    UmengSplashMessageActivity.this.g = false;
                    UmengSplashMessageActivity.this.j = new a((long) (UmengSplashMessageActivity.this.k.display_time * 1000), (long) UmengSplashMessageActivity.t);
                    UmengSplashMessageActivity.this.j.b();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        /* renamed from: com.umeng.message.inapp.UmengSplashMessageActivity$1$1  reason: invalid class name */
        class AnonymousClass1 implements View.OnClickListener {
            AnonymousClass1() {
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                UmengSplashMessageActivity.this.m = true;
                if (!TextUtils.equals("none", UmengSplashMessageActivity.this.k.action_type)) {
                    UmengSplashMessageActivity.this.r += SystemClock.elapsedRealtime() - UmengSplashMessageActivity.this.q;
                    d.a(UmengSplashMessageActivity.this.b).a(UmengSplashMessageActivity.this.k.msg_id, UmengSplashMessageActivity.this.k.msg_type, 1, 1, 0, 0, 0, (int) UmengSplashMessageActivity.this.r, 0);
                    UmengSplashMessageActivity.this.f();
                    UmengSplashMessageActivity.this.l.handleInAppMessage(UmengSplashMessageActivity.this.b, UmengSplashMessageActivity.this.k, 16);
                    UmengSplashMessageActivity.this.finish();
                }
            }
        }

        /* renamed from: com.umeng.message.inapp.UmengSplashMessageActivity$1$2  reason: invalid class name */
        class AnonymousClass2 implements View.OnClickListener {
            AnonymousClass2() {
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                UmengSplashMessageActivity.this.n = true;
                if (!TextUtils.equals("none", UmengSplashMessageActivity.this.k.action_type)) {
                    UmengSplashMessageActivity.this.r += SystemClock.elapsedRealtime() - UmengSplashMessageActivity.this.q;
                    d.a(UmengSplashMessageActivity.this.b).a(UmengSplashMessageActivity.this.k.msg_id, UmengSplashMessageActivity.this.k.msg_type, 1, 0, 1, UmengSplashMessageActivity.this.a(UmengSplashMessageActivity.this.o), 0, (int) UmengSplashMessageActivity.this.r, 0);
                    UmengSplashMessageActivity.this.f();
                    UmengSplashMessageActivity.this.l.handleInAppMessage(UmengSplashMessageActivity.this.b, UmengSplashMessageActivity.this.k, 16);
                    UmengSplashMessageActivity.this.finish();
                }
            }
        }

        /* renamed from: com.umeng.message.inapp.UmengSplashMessageActivity$1$3  reason: invalid class name */
        class AnonymousClass3 implements View.OnClickListener {
            AnonymousClass3() {
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                UmengSplashMessageActivity.this.o = true;
                if (!TextUtils.equals("none", UmengSplashMessageActivity.this.k.bottom_action_type)) {
                    UmengSplashMessageActivity.this.r += SystemClock.elapsedRealtime() - UmengSplashMessageActivity.this.q;
                    d.a(UmengSplashMessageActivity.this.b).a(UmengSplashMessageActivity.this.k.msg_id, UmengSplashMessageActivity.this.k.msg_type, 1, 0, UmengSplashMessageActivity.this.a(UmengSplashMessageActivity.this.n), 1, 0, (int) UmengSplashMessageActivity.this.r, 0);
                    UmengSplashMessageActivity.this.f();
                    UmengSplashMessageActivity.this.l.handleInAppMessage(UmengSplashMessageActivity.this.b, UmengSplashMessageActivity.this.k, 17);
                    UmengSplashMessageActivity.this.finish();
                }
            }
        }

        /* renamed from: com.umeng.message.inapp.UmengSplashMessageActivity$1$4  reason: invalid class name */
        class AnonymousClass4 implements View.OnClickListener {
            AnonymousClass4() {
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                UmengSplashMessageActivity.this.r += SystemClock.elapsedRealtime() - UmengSplashMessageActivity.this.q;
                d.a(UmengSplashMessageActivity.this.b).a(UmengSplashMessageActivity.this.k.msg_id, UmengSplashMessageActivity.this.k.msg_type, 1, UmengSplashMessageActivity.this.a(UmengSplashMessageActivity.this.m), UmengSplashMessageActivity.this.a(UmengSplashMessageActivity.this.n), UmengSplashMessageActivity.this.a(UmengSplashMessageActivity.this.o), 1, (int) UmengSplashMessageActivity.this.r, 0);
                UmengSplashMessageActivity.this.f();
                UmengSplashMessageActivity.this.finish();
            }
        }
    }

    /* renamed from: com.umeng.message.inapp.UmengSplashMessageActivity$2  reason: invalid class name */
    class AnonymousClass2 implements IUmengInAppMessageCallback {
        @Override // com.umeng.message.inapp.IUmengInAppMessageCallback
        public void onCardMessage(UInAppMessage uInAppMessage) {
        }

        AnonymousClass2() {
        }

        /* JADX WARNING: Removed duplicated region for block: B:13:0x0056  */
        /* JADX WARNING: Removed duplicated region for block: B:8:0x0026 A[ADDED_TO_REGION] */
        @Override // com.umeng.message.inapp.IUmengInAppMessageCallback
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void onSplashMessage(com.umeng.message.entity.UInAppMessage r7) {
            /*
            // Method dump skipped, instructions count: 352
            */
            throw new UnsupportedOperationException("Method not decompiled: com.umeng.message.inapp.UmengSplashMessageActivity.AnonymousClass2.onSplashMessage(com.umeng.message.entity.UInAppMessage):void");
        }
    }

    /* access modifiers changed from: protected */
    @Override // android.app.Activity
    public final void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.b = this;
        if ((getIntent().getFlags() & 4194304) > 0) {
            finish();
        } else if (!onCustomPretreatment()) {
            setRequestedOrientation(1);
            setContentView(c());
            d();
            this.l = InAppMessageManager.getInstance(this.b).getInAppHandler();
            this.i = new a((long) s, (long) t);
            this.i.b();
        }
    }

    @Override // android.app.Activity
    public final void onCreate(Bundle bundle, PersistableBundle persistableBundle) {
        super.onCreate(bundle, persistableBundle);
    }

    private View c() {
        FrameLayout frameLayout = new FrameLayout(this.b);
        frameLayout.setLayoutParams(new FrameLayout.LayoutParams(-1, -1));
        LinearLayout linearLayout = new LinearLayout(this.b);
        linearLayout.setLayoutParams(new LinearLayout.LayoutParams(-1, -1));
        linearLayout.setOrientation(1);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, 0, 13.0f);
        this.d = new ImageView(this.b);
        this.d.setLayoutParams(layoutParams);
        this.d.setScaleType(ImageView.ScaleType.FIT_XY);
        linearLayout.addView(this.d);
        LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(-1, 0, 3.0f);
        this.e = new ImageView(this.b);
        this.e.setLayoutParams(layoutParams2);
        this.e.setScaleType(ImageView.ScaleType.FIT_XY);
        linearLayout.addView(this.e);
        frameLayout.addView(linearLayout);
        FrameLayout.LayoutParams layoutParams3 = new FrameLayout.LayoutParams(-2, -2);
        layoutParams3.gravity = 5;
        layoutParams3.rightMargin = j.a(this.b, 30.0f);
        layoutParams3.topMargin = j.a(this.b, 20.0f);
        this.f = new TextView(this.b);
        this.f.setLayoutParams(layoutParams3);
        int a2 = j.a(this.b, 6.0f);
        int i = a2 / 3;
        this.f.setPadding(a2, i, a2, i);
        this.f.setTextSize(14.0f);
        this.f.setBackgroundColor(Color.parseColor("#80000000"));
        this.f.setTextColor(-1);
        this.f.setVisibility(8);
        frameLayout.addView(this.f);
        return frameLayout;
    }

    private void d() {
        if (InAppMessageManager.a) {
            d.a(this).a(this.v);
        } else if (System.currentTimeMillis() - InAppMessageManager.getInstance(this.b).d() > ((long) InAppMessageManager.b)) {
            d.a(this).a(this.v);
        } else {
            this.v.onSplashMessage(null);
        }
    }

    /* access modifiers changed from: protected */
    @Override // android.app.Activity
    public final void onStart() {
        super.onStart();
    }

    /* access modifiers changed from: protected */
    @Override // android.app.Activity
    public void onResume() {
        super.onResume();
        a aVar = this.i;
        if (aVar != null) {
            aVar.d();
        }
        if (this.j != null) {
            this.q = SystemClock.elapsedRealtime();
            this.j.d();
        }
    }

    /* access modifiers changed from: protected */
    @Override // android.app.Activity
    public final void onPause() {
        super.onPause();
        a aVar = this.i;
        if (aVar != null) {
            aVar.c();
        }
        if (this.j != null) {
            this.r += SystemClock.elapsedRealtime() - this.q;
            this.j.c();
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private synchronized boolean e() {
        boolean z;
        z = this.p;
        this.p = true;
        return z;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private synchronized void f() {
        if (this.h) {
            this.h = false;
            Intent intent = new Intent();
            intent.setClassName(this.b, InAppMessageManager.getInstance(this).a());
            intent.setFlags(536870912);
            try {
                this.b.startActivity(intent);
            } catch (Exception unused) {
            }
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private boolean g() {
        Calendar instance = Calendar.getInstance();
        instance.setTimeInMillis(InAppMessageManager.getInstance(this.b).i());
        Calendar instance2 = Calendar.getInstance();
        if (instance.get(6) == instance2.get(6) && instance.get(1) == instance2.get(1)) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void a(View view) {
        AlphaAnimation alphaAnimation = new AlphaAnimation(0.3f, 1.0f);
        alphaAnimation.setDuration(500);
        view.startAnimation(alphaAnimation);
    }

    /* access modifiers changed from: package-private */
    public class a extends c {
        a(long j, long j2) {
            super(j, j2);
        }

        @Override // com.umeng.message.inapp.c
        public void a(long j) {
            if (!UmengSplashMessageActivity.this.g) {
                UmengSplashMessageActivity.this.f.setVisibility(0);
                TextView textView = UmengSplashMessageActivity.this.f;
                textView.setText(((int) Math.ceil((((double) j) * 1.0d) / ((double) UmengSplashMessageActivity.t))) + WifiEnterpriseConfig.CA_CERT_ALIAS_DELIMITER + UmengSplashMessageActivity.this.k.display_name);
            }
        }

        @Override // com.umeng.message.inapp.c
        public void e() {
            if (!UmengSplashMessageActivity.this.e() || !UmengSplashMessageActivity.this.g) {
                if (!UmengSplashMessageActivity.this.g) {
                    d a = d.a(UmengSplashMessageActivity.this.b);
                    String str = UmengSplashMessageActivity.this.k.msg_id;
                    int i = UmengSplashMessageActivity.this.k.msg_type;
                    UmengSplashMessageActivity umengSplashMessageActivity = UmengSplashMessageActivity.this;
                    int a2 = umengSplashMessageActivity.a(umengSplashMessageActivity.m);
                    UmengSplashMessageActivity umengSplashMessageActivity2 = UmengSplashMessageActivity.this;
                    int a3 = umengSplashMessageActivity2.a(umengSplashMessageActivity2.n);
                    UmengSplashMessageActivity umengSplashMessageActivity3 = UmengSplashMessageActivity.this;
                    a.a(str, i, 1, a2, a3, umengSplashMessageActivity3.a(umengSplashMessageActivity3.o), 0, UmengSplashMessageActivity.this.k.display_time * 1000, 0);
                }
                UmengSplashMessageActivity.this.f();
                UmengSplashMessageActivity.this.finish();
            }
        }
    }

    /* access modifiers changed from: protected */
    @Override // android.app.Activity
    public final void onDestroy() {
        a aVar = this.i;
        if (aVar != null) {
            aVar.a();
        }
        a aVar2 = this.j;
        if (aVar2 != null) {
            aVar2.a();
        }
        UImageLoadTask uImageLoadTask = this.c;
        if (uImageLoadTask != null) {
            uImageLoadTask.a((UImageLoadTask.ImageLoaderCallback) null);
        }
        this.p = false;
        this.m = false;
        this.n = false;
        this.o = false;
        super.onDestroy();
    }
}
