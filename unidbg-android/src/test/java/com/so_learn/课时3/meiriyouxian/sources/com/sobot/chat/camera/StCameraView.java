package com.sobot.chat.camera;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.media.MediaPlayer;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.VideoView;
import com.sobot.chat.camera.a;
import com.sobot.chat.camera.a.b;
import com.sobot.chat.camera.a.d;
import com.sobot.chat.camera.a.f;
import com.sobot.chat.camera.b.c;
import com.sobot.chat.camera.c.g;
import com.sobot.chat.camera.c.h;
import com.sobot.chat.utils.q;

public class StCameraView extends FrameLayout implements SurfaceHolder.Callback, a.AbstractC0141a, com.sobot.chat.camera.d.a {
    private c a;
    private int b;
    private com.sobot.chat.camera.a.a c;
    private com.sobot.chat.camera.a.c d;
    private com.sobot.chat.camera.a.c e;
    private VideoView f;
    private ImageView g;
    private ImageView h;
    private CaptureLayout i;
    private StFoucsView j;
    private MediaPlayer k;
    private int l;
    private float m;
    private Bitmap n;
    private Bitmap o;
    private String p;
    private int q;
    private int r;
    private int s;
    private int t;
    private int u;
    private int v;
    private int w;
    private boolean x;
    private float y;
    private d z;

    @Override // android.view.SurfaceHolder.Callback
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i2, int i3) {
    }

    public StCameraView(Context context) {
        this(context, null);
    }

    public StCameraView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public StCameraView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.b = 35;
        this.m = 0.0f;
        this.q = 0;
        this.r = 0;
        this.s = 0;
        this.t = 0;
        this.u = 0;
        this.v = 0;
        this.w = 0;
        this.x = true;
        this.y = 0.0f;
        e();
        f();
        g();
    }

    private void e() {
        this.q = (int) TypedValue.applyDimension(2, 30.0f, getResources().getDisplayMetrics());
        this.r = (int) TypedValue.applyDimension(2, 20.0f, getResources().getDisplayMetrics());
        this.s = q.e(getContext(), "sobot_ic_camera");
        this.t = q.e(getContext(), "sobot_ic_back");
        this.u = 0;
        this.v = 15000;
    }

    private void f() {
        this.l = g.b(getContext());
        this.w = (int) (((float) this.l) / 16.0f);
        h.a("zoom = " + this.w);
        this.a = new c(getContext(), this, this);
        a.a().c(getContext());
    }

    private void g() {
        setWillNotDraw(false);
        View inflate = LayoutInflater.from(getContext()).inflate(q.a(getContext(), "sobot_camera_view"), this);
        this.f = (VideoView) inflate.findViewById(q.g(getContext(), "video_preview"));
        this.g = (ImageView) inflate.findViewById(q.g(getContext(), "image_photo"));
        this.h = (ImageView) inflate.findViewById(q.g(getContext(), "image_switch"));
        this.h.setImageResource(this.s);
        this.i = (CaptureLayout) inflate.findViewById(q.g(getContext(), "capture_layout"));
        this.i.setDuration(this.v);
        this.i.a(this.t, this.u);
        this.j = (StFoucsView) inflate.findViewById(q.g(getContext(), "fouce_view"));
        this.f.getHolder().addCallback(this);
        this.h.setOnClickListener(new AnonymousClass1());
        this.i.setCaptureLisenter(new AnonymousClass2());
        this.i.setTypeLisenter(new AnonymousClass3());
        this.i.setLeftClickListener(new AnonymousClass4());
        this.i.setRightClickListener(new AnonymousClass5());
    }

    /* access modifiers changed from: package-private */
    /* renamed from: com.sobot.chat.camera.StCameraView$1  reason: invalid class name */
    public class AnonymousClass1 implements View.OnClickListener {
        AnonymousClass1() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            StCameraView.this.a.b(StCameraView.this.f.getHolder(), StCameraView.this.m);
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: com.sobot.chat.camera.StCameraView$2  reason: invalid class name */
    public class AnonymousClass2 implements b {
        AnonymousClass2() {
        }

        @Override // com.sobot.chat.camera.a.b
        public void a() {
            StCameraView.this.h.setVisibility(4);
            StCameraView.this.a.a();
        }

        @Override // com.sobot.chat.camera.a.b
        public void b() {
            StCameraView.this.h.setVisibility(4);
            StCameraView.this.a.a(StCameraView.this.f.getHolder().getSurface(), StCameraView.this.m);
        }

        @Override // com.sobot.chat.camera.a.b
        public void a(long j) {
            StCameraView.this.i.setTextWithAnimation(q.f(StCameraView.this.getContext(), "sobot_voice_time_short"));
            StCameraView.this.h.setVisibility(0);
            StCameraView.this.postDelayed(new AnonymousClass1(j), 1500 - j);
        }

        /* renamed from: com.sobot.chat.camera.StCameraView$2$1  reason: invalid class name */
        class AnonymousClass1 implements Runnable {
            final /* synthetic */ long a;

            AnonymousClass1(long j) {
                this.a = j;
            }

            @Override // java.lang.Runnable
            public void run() {
                StCameraView.this.a.a(true, this.a);
            }
        }

        @Override // com.sobot.chat.camera.a.b
        public void b(long j) {
            StCameraView.this.a.a(false, j);
        }

        @Override // com.sobot.chat.camera.a.b
        public void a(float f) {
            h.a("recordZoom");
            StCameraView.this.a.a(f, 144);
        }

        @Override // com.sobot.chat.camera.a.b
        public void c() {
            if (StCameraView.this.z != null) {
                StCameraView.this.z.b();
            }
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: com.sobot.chat.camera.StCameraView$3  reason: invalid class name */
    public class AnonymousClass3 implements f {
        AnonymousClass3() {
        }

        @Override // com.sobot.chat.camera.a.f
        public void a() {
            StCameraView.this.h();
            StCameraView.this.a.c(StCameraView.this.f.getHolder(), StCameraView.this.m);
        }

        @Override // com.sobot.chat.camera.a.f
        public void b() {
            StCameraView.this.a.b();
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: com.sobot.chat.camera.StCameraView$4  reason: invalid class name */
    public class AnonymousClass4 implements com.sobot.chat.camera.a.c {
        AnonymousClass4() {
        }

        @Override // com.sobot.chat.camera.a.c
        public void a() {
            if (StCameraView.this.d != null) {
                StCameraView.this.d.a();
            }
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: com.sobot.chat.camera.StCameraView$5  reason: invalid class name */
    public class AnonymousClass5 implements com.sobot.chat.camera.a.c {
        AnonymousClass5() {
        }

        @Override // com.sobot.chat.camera.a.c
        public void a() {
            if (StCameraView.this.e != null) {
                StCameraView.this.e.a();
            }
        }
    }

    /* access modifiers changed from: protected */
    @Override // android.widget.FrameLayout, android.view.View
    public void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        float measuredWidth = (float) this.f.getMeasuredWidth();
        float measuredHeight = (float) this.f.getMeasuredHeight();
        if (this.m == 0.0f) {
            this.m = measuredHeight / measuredWidth;
        }
    }

    @Override // com.sobot.chat.camera.a.AbstractC0141a
    public void a() {
        a.a().b(this.f.getHolder(), this.m);
    }

    public void b() {
        h.a("JCameraView onResume");
        a(4);
        a.a().a(getContext());
        a.a().a(this.h);
        this.a.a(this.f.getHolder(), this.m);
    }

    public void c() {
        h.a("JCameraView onPause");
        d();
        a(1);
        a.a().a(true, (a.d) null);
        a.a().a(false);
        a.a().b(getContext());
    }

    /* renamed from: com.sobot.chat.camera.StCameraView$6  reason: invalid class name */
    class AnonymousClass6 extends Thread {
        AnonymousClass6() {
        }

        /* renamed from: com.sobot.chat.camera.StCameraView$6$1  reason: invalid class name */
        class AnonymousClass1 implements Runnable {
            AnonymousClass1() {
            }

            @Override // java.lang.Runnable
            public void run() {
                StCameraView.this.b((float) (StCameraView.this.getWidth() / 2), (float) (StCameraView.this.getHeight() / 2));
            }
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            a.a().a(StCameraView.this);
            StCameraView.this.postDelayed(new AnonymousClass1(), 1000);
        }
    }

    @Override // android.view.SurfaceHolder.Callback
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        h.a("JCameraView SurfaceCreated");
        new AnonymousClass6().start();
    }

    @Override // android.view.SurfaceHolder.Callback
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        h.a("JCameraView SurfaceDestroyed");
        a.a().c();
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        int action = motionEvent.getAction();
        if (action == 0) {
            if (motionEvent.getPointerCount() == 1) {
                b(motionEvent.getX(), motionEvent.getY());
            }
            if (motionEvent.getPointerCount() == 2) {
                Log.i("CJT", "ACTION_DOWN = 2");
            }
        } else if (action == 1) {
            this.x = true;
        } else if (action == 2) {
            if (motionEvent.getPointerCount() == 1) {
                this.x = true;
            }
            if (motionEvent.getPointerCount() == 2) {
                float x = motionEvent.getX(0);
                float y = motionEvent.getY(0);
                float sqrt = (float) Math.sqrt(Math.pow((double) (x - motionEvent.getX(1)), 2.0d) + Math.pow((double) (y - motionEvent.getY(1)), 2.0d));
                if (this.x) {
                    this.y = sqrt;
                    this.x = false;
                }
                float f = this.y;
                if (((int) (sqrt - f)) / this.w != 0) {
                    this.x = true;
                    this.a.a(sqrt - f, 145);
                }
            }
        }
        return true;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: com.sobot.chat.camera.StCameraView$7  reason: invalid class name */
    public class AnonymousClass7 implements a.c {
        AnonymousClass7() {
        }

        @Override // com.sobot.chat.camera.a.c
        public void a() {
            StCameraView.this.j.setVisibility(4);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void b(float f, float f2) {
        this.a.a(f, f2, new AnonymousClass7());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void c(float f, float f2) {
        if (f > f2) {
            FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, (int) ((f2 / f) * ((float) getWidth())));
            layoutParams.gravity = 17;
            this.f.setLayoutParams(layoutParams);
        }
    }

    public void setSaveVideoPath(String str) {
        a.a().a(str);
    }

    public void setJCameraLisenter(com.sobot.chat.camera.a.a aVar) {
        this.c = aVar;
    }

    public void setErrorLisenter(d dVar) {
        this.z = dVar;
        a.a().a(dVar);
    }

    public void setFeatures(int i) {
        this.i.setButtonFeatures(i);
    }

    public void setMediaQuality(int i) {
        a.a().a(i);
    }

    @Override // com.sobot.chat.camera.d.a
    public void a(int i) {
        if (i == 1) {
            this.g.setVisibility(4);
        } else if (i == 2) {
            d();
            com.sobot.chat.camera.c.f.a(this.p);
            this.f.setLayoutParams(new FrameLayout.LayoutParams(-1, -1));
            this.a.a(this.f.getHolder(), this.m);
        } else if (i != 3 && i == 4) {
            this.f.setLayoutParams(new FrameLayout.LayoutParams(-1, -1));
        }
        this.h.setVisibility(0);
        this.i.c();
    }

    @Override // com.sobot.chat.camera.d.a
    public void b(int i) {
        if (i == 1) {
            this.g.setVisibility(4);
            com.sobot.chat.camera.a.a aVar = this.c;
            if (aVar != null) {
                aVar.a(this.n);
            }
        } else if (i == 2) {
            d();
            this.f.setLayoutParams(new FrameLayout.LayoutParams(-1, -1));
            this.a.a(this.f.getHolder(), this.m);
            com.sobot.chat.camera.a.a aVar2 = this.c;
            if (aVar2 != null) {
                aVar2.a(this.p, this.o);
            }
        }
        this.i.c();
    }

    @Override // com.sobot.chat.camera.d.a
    public void a(Bitmap bitmap, boolean z) {
        if (z) {
            this.g.setScaleType(ImageView.ScaleType.FIT_XY);
        } else {
            this.g.setScaleType(ImageView.ScaleType.FIT_CENTER);
        }
        this.n = bitmap;
        this.g.setImageBitmap(bitmap);
        this.g.setVisibility(0);
        this.i.d();
        this.i.b();
    }

    @Override // com.sobot.chat.camera.d.a
    public void a(Bitmap bitmap, String str) {
        this.p = str;
        this.o = bitmap;
        try {
            Surface surface = this.f.getHolder().getSurface();
            h.a("surface.isValid():" + surface.isValid());
            if (surface.isValid()) {
                if (this.k == null) {
                    this.k = new MediaPlayer();
                } else {
                    this.k.reset();
                }
                this.k.setDataSource(str);
                this.k.setSurface(surface);
                if (Build.VERSION.SDK_INT >= 16) {
                    this.k.setVideoScalingMode(1);
                }
                this.k.setAudioStreamType(3);
                this.k.setOnVideoSizeChangedListener(new AnonymousClass8());
                this.k.setOnPreparedListener(new AnonymousClass9());
                this.k.setLooping(true);
                this.k.prepareAsync();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* renamed from: com.sobot.chat.camera.StCameraView$8  reason: invalid class name */
    class AnonymousClass8 implements MediaPlayer.OnVideoSizeChangedListener {
        AnonymousClass8() {
        }

        @Override // android.media.MediaPlayer.OnVideoSizeChangedListener
        public void onVideoSizeChanged(MediaPlayer mediaPlayer, int i, int i2) {
            StCameraView stCameraView = StCameraView.this;
            stCameraView.c((float) stCameraView.k.getVideoWidth(), (float) StCameraView.this.k.getVideoHeight());
        }
    }

    /* renamed from: com.sobot.chat.camera.StCameraView$9  reason: invalid class name */
    class AnonymousClass9 implements MediaPlayer.OnPreparedListener {
        AnonymousClass9() {
        }

        @Override // android.media.MediaPlayer.OnPreparedListener
        public void onPrepared(MediaPlayer mediaPlayer) {
            StCameraView.this.k.start();
        }
    }

    public void d() {
        MediaPlayer mediaPlayer = this.k;
        if (mediaPlayer != null && mediaPlayer.isPlaying()) {
            this.k.stop();
            this.k.release();
            this.k = null;
        }
    }

    public void setTip(String str) {
        this.i.setTip(str);
    }

    @Override // com.sobot.chat.camera.d.a
    public boolean a(float f, float f2) {
        if (f2 > ((float) this.i.getTop())) {
            return false;
        }
        this.j.setVisibility(0);
        if (f < ((float) (this.j.getWidth() / 2))) {
            f = (float) (this.j.getWidth() / 2);
        }
        if (f > ((float) (this.l - (this.j.getWidth() / 2)))) {
            f = (float) (this.l - (this.j.getWidth() / 2));
        }
        if (f2 < ((float) (this.j.getWidth() / 2))) {
            f2 = (float) (this.j.getWidth() / 2);
        }
        if (f2 > ((float) (this.i.getTop() - (this.j.getWidth() / 2)))) {
            f2 = (float) (this.i.getTop() - (this.j.getWidth() / 2));
        }
        StFoucsView stFoucsView = this.j;
        stFoucsView.setX(f - ((float) (stFoucsView.getWidth() / 2)));
        StFoucsView stFoucsView2 = this.j;
        stFoucsView2.setY(f2 - ((float) (stFoucsView2.getHeight() / 2)));
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this.j, "scaleX", 1.0f, 0.6f);
        ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(this.j, "scaleY", 1.0f, 0.6f);
        ObjectAnimator ofFloat3 = ObjectAnimator.ofFloat(this.j, "alpha", 1.0f, 0.4f, 1.0f, 0.4f, 1.0f, 0.4f, 1.0f);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.play(ofFloat).with(ofFloat2).before(ofFloat3);
        animatorSet.setDuration(400L);
        animatorSet.start();
        return true;
    }

    public void setLeftClickListener(com.sobot.chat.camera.a.c cVar) {
        this.d = cVar;
    }

    public void setRightClickListener(com.sobot.chat.camera.a.c cVar) {
        this.e = cVar;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void h() {
        Bitmap bitmap = this.n;
        if (bitmap != null && !bitmap.isRecycled()) {
            this.n.recycle();
            this.n = null;
        }
        Bitmap bitmap2 = this.o;
        if (bitmap2 != null && !bitmap2.isRecycled()) {
            this.o.recycle();
            this.o = null;
        }
    }

    /* access modifiers changed from: protected */
    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        h();
        super.onDetachedFromWindow();
    }
}
