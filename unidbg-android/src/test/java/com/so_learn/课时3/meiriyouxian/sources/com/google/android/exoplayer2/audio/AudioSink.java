package com.google.android.exoplayer2.audio;

import com.google.android.exoplayer2.q;
import com.umeng.message.proguard.l;
import java.nio.ByteBuffer;

public interface AudioSink {

    public interface a {
        void a();

        void a(int i);

        void a(int i, long j, long j2);
    }

    long a(boolean z);

    q a(q qVar);

    void a();

    void a(float f);

    void a(int i);

    void a(int i, int i2, int i3, int i4, int[] iArr, int i5, int i6) throws ConfigurationException;

    void a(a aVar);

    void a(b bVar);

    void a(i iVar);

    boolean a(int i, int i2);

    boolean a(ByteBuffer byteBuffer, long j) throws InitializationException, WriteException;

    void b();

    void c() throws WriteException;

    boolean d();

    boolean e();

    q f();

    void g();

    void h();

    void i();

    void j();

    public static final class ConfigurationException extends Exception {
        public ConfigurationException(Throwable th) {
            super(th);
        }

        public ConfigurationException(String str) {
            super(str);
        }
    }

    public static final class InitializationException extends Exception {
        public final int audioTrackState;

        public InitializationException(int i, int i2, int i3, int i4) {
            super("AudioTrack init failed: " + i + ", Config(" + i2 + ", " + i3 + ", " + i4 + l.t);
            this.audioTrackState = i;
        }
    }

    public static final class WriteException extends Exception {
        public final int errorCode;

        public WriteException(int i) {
            super("AudioTrack write failed: " + i);
            this.errorCode = i;
        }
    }
}
