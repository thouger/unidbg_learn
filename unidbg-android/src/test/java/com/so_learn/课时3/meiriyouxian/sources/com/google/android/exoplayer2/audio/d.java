package com.google.android.exoplayer2.audio;

import android.content.Context;
import android.media.AudioFocusRequest;
import android.media.AudioManager;
import com.google.android.exoplayer2.util.i;
import com.google.android.exoplayer2.util.z;

/* compiled from: AudioFocusManager */
public final class d {
    private final AudioManager a;
    private final a b;
    private final b c;
    private b d;
    private int e;
    private int f;
    private float g = 1.0f;
    private AudioFocusRequest h;
    private boolean i;

    /* compiled from: AudioFocusManager */
    public interface b {
        void a(float f);

        void b(int i);
    }

    private int b(boolean z) {
        return z ? 1 : -1;
    }

    public d(Context context, b bVar) {
        this.a = context == null ? null : (AudioManager) context.getApplicationContext().getSystemService("audio");
        this.c = bVar;
        this.b = new a();
        this.e = 0;
    }

    public float a() {
        return this.g;
    }

    public int a(b bVar, boolean z, int i) {
        if (this.d == null && bVar == null) {
            return z ? 1 : -1;
        }
        com.google.android.exoplayer2.util.a.a(this.a, "SimpleExoPlayer must be created with a context to handle audio focus.");
        if (!z.a(this.d, bVar)) {
            this.d = bVar;
            this.f = a(bVar);
            int i2 = this.f;
            com.google.android.exoplayer2.util.a.a(i2 == 1 || i2 == 0, "Automatic handling of audio focus is only available for USAGE_MEDIA and USAGE_GAME.");
            if (z && (i == 2 || i == 3)) {
                return c();
            }
        }
        if (i == 1) {
            return b(z);
        }
        return a(z);
    }

    public int a(boolean z) {
        if (this.a == null) {
            return 1;
        }
        if (z) {
            return c();
        }
        return -1;
    }

    public int a(boolean z, int i) {
        if (this.a == null) {
            return 1;
        }
        if (z) {
            return i == 1 ? b(z) : c();
        }
        d();
        return -1;
    }

    public void b() {
        if (this.a != null) {
            c(true);
        }
    }

    private int c() {
        int i;
        if (this.f == 0) {
            if (this.e != 0) {
                c(true);
            }
            return 1;
        }
        if (this.e == 0) {
            if (z.a >= 26) {
                i = f();
            } else {
                i = e();
            }
            this.e = i == 1 ? 1 : 0;
        }
        int i2 = this.e;
        if (i2 == 0) {
            return -1;
        }
        if (i2 == 2) {
            return 0;
        }
        return 1;
    }

    private void d() {
        c(false);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void c(boolean z) {
        if (this.f != 0 || this.e != 0) {
            if (this.f != 1 || this.e == -1 || z) {
                if (z.a >= 26) {
                    h();
                } else {
                    g();
                }
                this.e = 0;
            }
        }
    }

    private int e() {
        return ((AudioManager) com.google.android.exoplayer2.util.a.a(this.a)).requestAudioFocus(this.b, z.h(((b) com.google.android.exoplayer2.util.a.a(this.d)).d), this.f);
    }

    private int f() {
        if (this.h == null || this.i) {
            AudioFocusRequest audioFocusRequest = this.h;
            this.h = (audioFocusRequest == null ? new AudioFocusRequest.Builder(this.f) : new AudioFocusRequest.Builder(audioFocusRequest)).setAudioAttributes(((b) com.google.android.exoplayer2.util.a.a(this.d)).a()).setWillPauseWhenDucked(i()).setOnAudioFocusChangeListener(this.b).build();
            this.i = false;
        }
        return ((AudioManager) com.google.android.exoplayer2.util.a.a(this.a)).requestAudioFocus(this.h);
    }

    private void g() {
        ((AudioManager) com.google.android.exoplayer2.util.a.a(this.a)).abandonAudioFocus(this.b);
    }

    private void h() {
        if (this.h != null) {
            ((AudioManager) com.google.android.exoplayer2.util.a.a(this.a)).abandonAudioFocusRequest(this.h);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private boolean i() {
        b bVar = this.d;
        return bVar != null && bVar.b == 1;
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    private static int a(b bVar) {
        if (bVar == null) {
            return 0;
        }
        switch (bVar.d) {
            case 0:
                i.c("AudioFocusManager", "Specify a proper usage in the audio attributes for audio focus handling. Using AUDIOFOCUS_GAIN by default.");
                return 1;
            case 1:
            case 14:
                return 1;
            case 2:
            case 4:
                return 2;
            case 3:
                return 0;
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
            case 12:
            case 13:
                break;
            case 11:
                if (bVar.b == 1) {
                    return 2;
                }
                break;
            case 15:
            default:
                i.c("AudioFocusManager", "Unidentified audio usage: " + bVar.d);
                return 0;
            case 16:
                if (z.a >= 19) {
                    return 4;
                }
                return 2;
        }
        return 3;
    }

    /* compiled from: AudioFocusManager */
    /* access modifiers changed from: private */
    public class a implements AudioManager.OnAudioFocusChangeListener {
        private a() {
        }

        @Override // android.media.AudioManager.OnAudioFocusChangeListener
        public void onAudioFocusChange(int i) {
            if (i != -3) {
                if (i == -2) {
                    d.this.e = 2;
                } else if (i == -1) {
                    d.this.e = -1;
                } else if (i != 1) {
                    i.c("AudioFocusManager", "Unknown focus change type: " + i);
                    return;
                } else {
                    d.this.e = 1;
                }
            } else if (d.this.i()) {
                d.this.e = 2;
            } else {
                d.this.e = 3;
            }
            int i2 = d.this.e;
            if (i2 == -1) {
                d.this.c.b(-1);
                d.this.c(true);
            } else if (i2 != 0) {
                if (i2 == 1) {
                    d.this.c.b(1);
                } else if (i2 == 2) {
                    d.this.c.b(0);
                } else if (i2 != 3) {
                    throw new IllegalStateException("Unknown audio focus state: " + d.this.e);
                }
            }
            float f = d.this.e == 3 ? 0.2f : 1.0f;
            if (d.this.g != f) {
                d.this.g = f;
                d.this.c.a(f);
            }
        }
    }
}
