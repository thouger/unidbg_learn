package cn.missfresh.module.base.support.view.texturevideo;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.Surface;
import android.view.View;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.MediaController;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import java.io.IOException;
import java.util.Map;

public class TextureVideoView extends ScalableTextureView implements MediaController.MediaPlayerControl {
    MediaPlayer.OnVideoSizeChangedListener a;
    MediaPlayer.OnPreparedListener b;
    private Uri c;
    private Map<String, String> d;
    private int e;
    private int f;
    private Surface g;
    private MediaPlayer h;
    private int i;
    private MediaController j;
    private MediaPlayer.OnCompletionListener k;
    private MediaPlayer.OnPreparedListener l;
    private int m;
    private MediaPlayer.OnErrorListener n;
    private MediaPlayer.OnInfoListener o;
    private int p;
    private boolean q;
    private boolean r;
    private boolean s;
    private boolean t;
    private String u;
    private MediaPlayer.OnCompletionListener v;
    private MediaPlayer.OnInfoListener w;
    private MediaPlayer.OnErrorListener x;
    private MediaPlayer.OnBufferingUpdateListener y;

    @Override // android.view.View
    public void onInitializeAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        AppMethodBeat.i(22946, false);
        super.onInitializeAccessibilityEvent(accessibilityEvent);
        accessibilityEvent.setClassName(TextureVideoView.class.getName());
        AppMethodBeat.o(22946);
    }

    @Override // android.view.View
    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        AppMethodBeat.i(22947, false);
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        accessibilityNodeInfo.setClassName(TextureVideoView.class.getName());
        AppMethodBeat.o(22947);
    }

    public void setVideoPath(String str) {
        AppMethodBeat.i(22949, false);
        this.u = str;
        setVideoURI(Uri.parse(str));
        AppMethodBeat.o(22949);
    }

    public String getPath() {
        return this.u;
    }

    public void setVideoURI(Uri uri) {
        AppMethodBeat.i(22950, false);
        a(uri, null);
        AppMethodBeat.o(22950);
    }

    public void a(Uri uri, Map<String, String> map) {
        AppMethodBeat.i(22951, false);
        this.c = uri;
        this.d = map;
        this.p = 0;
        b();
        requestLayout();
        invalidate();
        AppMethodBeat.o(22951);
    }

    private void b() {
        AppMethodBeat.i(22954, false);
        if (this.c == null || this.g == null) {
            AppMethodBeat.o(22954);
            return;
        }
        a(false);
        if (this.t) {
            ((AudioManager) getContext().getApplicationContext().getSystemService("audio")).requestAudioFocus(null, 3, 1);
        }
        try {
            this.h = new MediaPlayer();
            if (this.i != 0) {
                this.h.setAudioSessionId(this.i);
            } else {
                this.i = this.h.getAudioSessionId();
            }
            this.h.setOnPreparedListener(this.b);
            this.h.setOnVideoSizeChangedListener(this.a);
            this.h.setOnCompletionListener(this.v);
            this.h.setOnErrorListener(this.x);
            this.h.setOnInfoListener(this.w);
            this.h.setOnBufferingUpdateListener(this.y);
            this.m = 0;
            this.h.setDataSource(getContext().getApplicationContext(), this.c, this.d);
            this.h.setSurface(this.g);
            this.h.setAudioStreamType(3);
            this.h.setScreenOnWhilePlaying(true);
            this.h.prepareAsync();
            this.e = 1;
            c();
            AppMethodBeat.o(22954);
        } catch (IOException unused) {
            this.e = -1;
            this.f = -1;
            this.x.onError(this.h, 1, 0);
            AppMethodBeat.o(22954);
        } catch (IllegalArgumentException unused2) {
            this.e = -1;
            this.f = -1;
            this.x.onError(this.h, 1, 0);
            AppMethodBeat.o(22954);
        }
    }

    public void setMediaController(MediaController mediaController) {
        AppMethodBeat.i(22955, false);
        MediaController mediaController2 = this.j;
        if (mediaController2 != null) {
            mediaController2.hide();
        }
        this.j = mediaController;
        c();
        AppMethodBeat.o(22955);
    }

    private void c() {
        MediaController mediaController;
        AppMethodBeat.i(22956, false);
        if (!(this.h == null || (mediaController = this.j) == null)) {
            mediaController.setMediaPlayer(this);
            this.j.setAnchorView(getParent() instanceof View ? (View) getParent() : this);
            this.j.setEnabled(e());
        }
        AppMethodBeat.o(22956);
    }

    public void setOnPreparedListener(MediaPlayer.OnPreparedListener onPreparedListener) {
        this.l = onPreparedListener;
    }

    public void setOnCompletionListener(MediaPlayer.OnCompletionListener onCompletionListener) {
        this.k = onCompletionListener;
    }

    public void setOnErrorListener(MediaPlayer.OnErrorListener onErrorListener) {
        this.n = onErrorListener;
    }

    public void setOnInfoListener(MediaPlayer.OnInfoListener onInfoListener) {
        this.o = onInfoListener;
    }

    private void a(boolean z) {
        AppMethodBeat.i(22957, false);
        MediaPlayer mediaPlayer = this.h;
        if (mediaPlayer != null) {
            mediaPlayer.reset();
            this.h.release();
            this.h = null;
            this.e = 0;
            if (z) {
                this.f = 0;
            }
            if (this.t) {
                ((AudioManager) getContext().getApplicationContext().getSystemService("audio")).abandonAudioFocus(null);
            }
        }
        AppMethodBeat.o(22957);
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        AppMethodBeat.i(22958, false);
        if (e() && this.j != null) {
            d();
        }
        AppMethodBeat.o(22958);
        return false;
    }

    @Override // android.view.View
    public boolean onTrackballEvent(MotionEvent motionEvent) {
        AppMethodBeat.i(22959, false);
        if (e() && this.j != null) {
            d();
        }
        AppMethodBeat.o(22959);
        return false;
    }

    @Override // android.view.View, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        boolean z = false;
        AppMethodBeat.i(22960, false);
        if (!(i == 4 || i == 24 || i == 25 || i == 164 || i == 82 || i == 5 || i == 6)) {
            z = true;
        }
        if (e() && z && this.j != null) {
            if (i == 79 || i == 85) {
                if (this.h.isPlaying()) {
                    pause();
                    this.j.show();
                } else {
                    start();
                    this.j.hide();
                }
                AppMethodBeat.o(22960);
                return true;
            } else if (i == 126) {
                if (!this.h.isPlaying()) {
                    start();
                    this.j.hide();
                }
                AppMethodBeat.o(22960);
                return true;
            } else if (i == 86 || i == 127) {
                if (this.h.isPlaying()) {
                    pause();
                    this.j.show();
                }
                AppMethodBeat.o(22960);
                return true;
            } else {
                d();
            }
        }
        boolean onKeyDown = super.onKeyDown(i, keyEvent);
        AppMethodBeat.o(22960);
        return onKeyDown;
    }

    private void d() {
        AppMethodBeat.i(22961, false);
        if (this.j.isShowing()) {
            this.j.hide();
        } else {
            this.j.show();
        }
        AppMethodBeat.o(22961);
    }

    @Override // android.widget.MediaController.MediaPlayerControl
    public void start() {
        AppMethodBeat.i(22962, false);
        if (e()) {
            this.h.start();
            this.e = 3;
        }
        this.f = 3;
        AppMethodBeat.o(22962);
    }

    @Override // android.widget.MediaController.MediaPlayerControl
    public void pause() {
        AppMethodBeat.i(22963, false);
        if (e() && this.h.isPlaying()) {
            this.h.pause();
            this.e = 4;
        }
        this.f = 4;
        AppMethodBeat.o(22963);
    }

    @Override // android.widget.MediaController.MediaPlayerControl
    public int getDuration() {
        AppMethodBeat.i(22966, false);
        if (e()) {
            int duration = this.h.getDuration();
            AppMethodBeat.o(22966);
            return duration;
        }
        AppMethodBeat.o(22966);
        return -1;
    }

    @Override // android.widget.MediaController.MediaPlayerControl
    public int getCurrentPosition() {
        AppMethodBeat.i(22967, false);
        if (e()) {
            int currentPosition = this.h.getCurrentPosition();
            AppMethodBeat.o(22967);
            return currentPosition;
        }
        AppMethodBeat.o(22967);
        return 0;
    }

    @Override // android.widget.MediaController.MediaPlayerControl
    public void seekTo(int i) {
        AppMethodBeat.i(22968, false);
        if (e()) {
            this.h.seekTo(i);
            this.p = 0;
        } else {
            this.p = i;
        }
        AppMethodBeat.o(22968);
    }

    @Override // android.widget.MediaController.MediaPlayerControl
    public boolean isPlaying() {
        boolean z = false;
        AppMethodBeat.i(22969, false);
        if (e() && this.h.isPlaying()) {
            z = true;
        }
        AppMethodBeat.o(22969);
        return z;
    }

    @Override // android.widget.MediaController.MediaPlayerControl
    public int getBufferPercentage() {
        if (this.h != null) {
            return this.m;
        }
        return 0;
    }

    private boolean e() {
        int i;
        return (this.h == null || (i = this.e) == -1 || i == 0 || i == 1) ? false : true;
    }

    @Override // android.widget.MediaController.MediaPlayerControl
    public boolean canPause() {
        return this.q;
    }

    @Override // android.widget.MediaController.MediaPlayerControl
    public boolean canSeekBackward() {
        return this.r;
    }

    @Override // android.widget.MediaController.MediaPlayerControl
    public boolean canSeekForward() {
        return this.s;
    }

    @Override // android.widget.MediaController.MediaPlayerControl
    public int getAudioSessionId() {
        AppMethodBeat.i(22970, false);
        if (this.i == 0) {
            MediaPlayer mediaPlayer = new MediaPlayer();
            this.i = mediaPlayer.getAudioSessionId();
            mediaPlayer.release();
        }
        int i = this.i;
        AppMethodBeat.o(22970);
        return i;
    }

    public void setShouldRequestAudioFocus(boolean z) {
        this.t = z;
    }
}
