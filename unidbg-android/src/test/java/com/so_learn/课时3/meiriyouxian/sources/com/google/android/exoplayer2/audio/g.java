package com.google.android.exoplayer2.audio;

import android.media.AudioTimestamp;
import android.media.AudioTrack;
import android.util.TimedRemoteCaller;
import com.google.android.exoplayer2.util.z;

/* compiled from: AudioTimestampPoller */
/* access modifiers changed from: package-private */
public final class g {
    private final a a;
    private int b;
    private long c;
    private long d;
    private long e;
    private long f;

    public g(AudioTrack audioTrack) {
        if (z.a >= 19) {
            this.a = new a(audioTrack);
            e();
            return;
        }
        this.a = null;
        a(3);
    }

    public boolean a(long j) {
        a aVar = this.a;
        if (aVar == null || j - this.e < this.d) {
            return false;
        }
        this.e = j;
        boolean a2 = aVar.a();
        int i = this.b;
        if (i != 0) {
            if (i != 1) {
                if (i != 2) {
                    if (i != 3) {
                        if (i == 4) {
                            return a2;
                        }
                        throw new IllegalStateException();
                    } else if (!a2) {
                        return a2;
                    } else {
                        e();
                        return a2;
                    }
                } else if (a2) {
                    return a2;
                } else {
                    e();
                    return a2;
                }
            } else if (!a2) {
                e();
                return a2;
            } else if (this.a.c() <= this.f) {
                return a2;
            } else {
                a(2);
                return a2;
            }
        } else if (a2) {
            if (this.a.b() < this.c) {
                return false;
            }
            this.f = this.a.c();
            a(1);
            return a2;
        } else if (j - this.c <= 500000) {
            return a2;
        } else {
            a(3);
            return a2;
        }
    }

    public void a() {
        a(4);
    }

    public void b() {
        if (this.b == 4) {
            e();
        }
    }

    public boolean c() {
        int i = this.b;
        return i == 1 || i == 2;
    }

    public boolean d() {
        return this.b == 2;
    }

    public void e() {
        if (this.a != null) {
            a(0);
        }
    }

    public long f() {
        a aVar = this.a;
        if (aVar != null) {
            return aVar.b();
        }
        return -9223372036854775807L;
    }

    public long g() {
        a aVar = this.a;
        if (aVar != null) {
            return aVar.c();
        }
        return -1;
    }

    private void a(int i) {
        this.b = i;
        if (i == 0) {
            this.e = 0;
            this.f = -1;
            this.c = System.nanoTime() / 1000;
            this.d = TimedRemoteCaller.DEFAULT_CALL_TIMEOUT_MILLIS;
        } else if (i == 1) {
            this.d = TimedRemoteCaller.DEFAULT_CALL_TIMEOUT_MILLIS;
        } else if (i == 2 || i == 3) {
            this.d = 10000000;
        } else if (i == 4) {
            this.d = 500000;
        } else {
            throw new IllegalStateException();
        }
    }

    /* compiled from: AudioTimestampPoller */
    /* access modifiers changed from: private */
    public static final class a {
        private final AudioTrack a;
        private final AudioTimestamp b = new AudioTimestamp();
        private long c;
        private long d;
        private long e;

        public a(AudioTrack audioTrack) {
            this.a = audioTrack;
        }

        public boolean a() {
            boolean timestamp = this.a.getTimestamp(this.b);
            if (timestamp) {
                long j = this.b.framePosition;
                if (this.d > j) {
                    this.c++;
                }
                this.d = j;
                this.e = j + (this.c << 32);
            }
            return timestamp;
        }

        public long b() {
            return this.b.nanoTime / 1000;
        }

        public long c() {
            return this.e;
        }
    }
}
