package com.sobot.chat.camera;

import android.content.Context;
import android.graphics.PorterDuff;
import android.media.MediaPlayer;
import android.media.TtmlUtils;
import android.os.Build;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.VideoView;
import com.sobot.chat.camera.a.g;
import com.sobot.chat.camera.b;
import com.sobot.chat.camera.c.h;
import com.sobot.chat.utils.q;
import java.io.File;

public class StVideoView extends FrameLayout implements MediaPlayer.OnCompletionListener, SurfaceHolder.Callback, View.OnClickListener, b.a {
    private ImageView a;
    private TextView b;
    private TextView c;
    private ImageButton d;
    private LinearLayout e;
    private SeekBar f;
    private VideoView g;
    private MediaPlayer h;
    private StPlayPauseDrawable i;
    private b j;
    private int k;
    private String l;
    private g m;

    private void f() {
    }

    @Override // android.view.SurfaceHolder.Callback
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i2, int i3) {
    }

    public StVideoView(Context context) {
        this(context, null);
    }

    public StVideoView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public StVideoView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.l = "";
        f();
        g();
        h();
    }

    private void g() {
        this.k = com.sobot.chat.camera.c.g.b(getContext());
    }

    private void h() {
        setWillNotDraw(false);
        View inflate = LayoutInflater.from(getContext()).inflate(q.a(getContext(), TtmlUtils.TAG_LAYOUT, "sobot_video_view"), this);
        this.g = (VideoView) inflate.findViewById(q.g(getContext(), "video_preview"));
        this.a = (ImageView) inflate.findViewById(q.g(getContext(), "iv_back"));
        this.d = (ImageButton) inflate.findViewById(q.g(getContext(), "ib_playBtn"));
        this.b = (TextView) inflate.findViewById(q.g(getContext(), "st_currentTime"));
        this.c = (TextView) inflate.findViewById(q.g(getContext(), "st_totalTime"));
        this.f = (SeekBar) inflate.findViewById(q.g(getContext(), "st_seekbar"));
        this.e = (LinearLayout) inflate.findViewById(q.g(getContext(), "st_progress_container"));
        this.i = new StPlayPauseDrawable(getContext());
        this.d.setImageDrawable(this.i);
        this.d.setColorFilter(-1, PorterDuff.Mode.SRC_IN);
        this.g.getHolder().addCallback(this);
        setOnClickListener(this);
        this.a.setOnClickListener(this);
        this.d.setOnClickListener(this);
    }

    public void a() {
        n();
    }

    public void b() {
        l();
        MediaPlayer mediaPlayer = this.h;
        if (mediaPlayer != null) {
            mediaPlayer.pause();
        }
    }

    public boolean c() {
        MediaPlayer mediaPlayer = this.h;
        return mediaPlayer != null && mediaPlayer.isPlaying();
    }

    @Override // android.view.SurfaceHolder.Callback
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        h.a("JCameraView SurfaceCreated");
        e();
    }

    @Override // android.view.SurfaceHolder.Callback
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        h.a("JCameraView SurfaceDestroyed");
        d();
    }

    public void d() {
        MediaPlayer mediaPlayer = this.h;
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            this.h.release();
            this.h = null;
        }
        m();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void a(float f, float f2) {
        if (f > f2) {
            FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, (int) ((f2 / f) * ((float) getWidth())));
            layoutParams.gravity = 17;
            this.g.setLayoutParams(layoutParams);
        }
    }

    @Override // com.sobot.chat.camera.b.a
    public void a(int i, int i2) {
        MediaPlayer mediaPlayer = this.h;
        if (mediaPlayer != null && mediaPlayer.isPlaying()) {
            this.f.setMax(i2);
            this.f.setProgress(i);
            this.c.setText(com.sobot.chat.camera.c.b.a((long) i2));
            this.b.setText(com.sobot.chat.camera.c.b.a((long) i));
        }
    }

    private void i() {
        g gVar = this.m;
        if (gVar != null) {
            gVar.a();
        }
    }

    private void j() {
        g gVar = this.m;
        if (gVar != null) {
            gVar.c();
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        g gVar;
        if (view == this) {
            h.a("dd");
            if (this.a.getVisibility() == 8) {
                this.a.setVisibility(0);
                this.e.setVisibility(0);
            } else {
                this.a.setVisibility(8);
                this.e.setVisibility(8);
            }
        }
        if (this.a == view && (gVar = this.m) != null) {
            gVar.d();
        }
        if (this.d == view) {
            a(!c());
        }
    }

    @Override // android.media.MediaPlayer.OnCompletionListener
    public void onCompletion(MediaPlayer mediaPlayer) {
        this.i.a(true);
        g gVar = this.m;
        if (gVar != null) {
            gVar.b();
        }
        this.f.setProgress(0);
    }

    public void setVideoPath(String str) {
        this.l = str;
    }

    public void setVideoLisenter(g gVar) {
        this.m = gVar;
    }

    public void e() {
        if (TextUtils.isEmpty(this.l)) {
            j();
            return;
        }
        File file = new File(this.l);
        if (!file.exists() || !file.isFile()) {
            j();
            return;
        }
        try {
            Surface surface = this.g.getHolder().getSurface();
            h.a("surface.isValid():" + surface.isValid());
            if (surface.isValid()) {
                if (this.h == null) {
                    this.h = new MediaPlayer();
                } else {
                    this.h.reset();
                }
                this.h.setDataSource(this.l);
                this.h.setSurface(surface);
                if (Build.VERSION.SDK_INT >= 16) {
                    this.h.setVideoScalingMode(1);
                }
                this.h.setAudioStreamType(3);
                this.h.setOnVideoSizeChangedListener(new AnonymousClass1());
                this.h.setOnPreparedListener(new AnonymousClass2());
                this.h.setLooping(false);
                this.h.prepareAsync();
                this.h.setOnCompletionListener(this);
            }
        } catch (Exception e) {
            e.printStackTrace();
            j();
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: com.sobot.chat.camera.StVideoView$1  reason: invalid class name */
    public class AnonymousClass1 implements MediaPlayer.OnVideoSizeChangedListener {
        AnonymousClass1() {
        }

        @Override // android.media.MediaPlayer.OnVideoSizeChangedListener
        public void onVideoSizeChanged(MediaPlayer mediaPlayer, int i, int i2) {
            StVideoView stVideoView = StVideoView.this;
            stVideoView.a((float) stVideoView.h.getVideoWidth(), (float) StVideoView.this.h.getVideoHeight());
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: com.sobot.chat.camera.StVideoView$2  reason: invalid class name */
    public class AnonymousClass2 implements MediaPlayer.OnPreparedListener {
        AnonymousClass2() {
        }

        @Override // android.media.MediaPlayer.OnPreparedListener
        public void onPrepared(MediaPlayer mediaPlayer) {
            StVideoView.this.n();
        }
    }

    private void k() {
        if (this.j == null) {
            this.j = new b(this.h, getContext(), this);
        }
        this.j.a();
    }

    private void l() {
        b bVar = this.j;
        if (bVar != null) {
            bVar.b();
        }
    }

    private void m() {
        l();
        this.j = null;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void n() {
        MediaPlayer mediaPlayer = this.h;
        if (mediaPlayer != null) {
            mediaPlayer.start();
            i();
            k();
        }
    }

    public void a(boolean z) {
        MediaPlayer mediaPlayer = this.h;
        if (mediaPlayer != null) {
            if (z) {
                n();
            } else {
                if (mediaPlayer.isPlaying()) {
                    this.h.pause();
                }
                l();
            }
            if (!c()) {
                this.i.a(true);
            } else {
                this.i.b(true);
            }
        }
    }
}
