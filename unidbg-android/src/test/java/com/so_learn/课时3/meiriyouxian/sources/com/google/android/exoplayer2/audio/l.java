package com.google.android.exoplayer2.audio;

import com.google.android.exoplayer2.audio.AudioProcessor;
import com.google.android.exoplayer2.util.z;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/* compiled from: FloatResamplingAudioProcessor */
final class l implements AudioProcessor {
    private static final int b = Float.floatToIntBits(Float.NaN);
    private int c = -1;
    private int d = -1;
    private int e = 0;
    private ByteBuffer f = a;
    private ByteBuffer g = a;
    private boolean h;

    @Override // com.google.android.exoplayer2.audio.AudioProcessor
    public int c() {
        return 4;
    }

    @Override // com.google.android.exoplayer2.audio.AudioProcessor
    public boolean a(int i, int i2, int i3) throws AudioProcessor.UnhandledFormatException {
        if (!z.d(i3)) {
            throw new AudioProcessor.UnhandledFormatException(i, i2, i3);
        } else if (this.c == i && this.d == i2 && this.e == i3) {
            return false;
        } else {
            this.c = i;
            this.d = i2;
            this.e = i3;
            return true;
        }
    }

    @Override // com.google.android.exoplayer2.audio.AudioProcessor
    public boolean a() {
        return z.d(this.e);
    }

    @Override // com.google.android.exoplayer2.audio.AudioProcessor
    public int b() {
        return this.d;
    }

    @Override // com.google.android.exoplayer2.audio.AudioProcessor
    public int d() {
        return this.c;
    }

    @Override // com.google.android.exoplayer2.audio.AudioProcessor
    public void a(ByteBuffer byteBuffer) {
        boolean z = this.e == 1073741824;
        int position = byteBuffer.position();
        int limit = byteBuffer.limit();
        int i = limit - position;
        if (!z) {
            i = (i / 3) * 4;
        }
        if (this.f.capacity() < i) {
            this.f = ByteBuffer.allocateDirect(i).order(ByteOrder.nativeOrder());
        } else {
            this.f.clear();
        }
        if (z) {
            while (position < limit) {
                a((byteBuffer.get(position) & 255) | ((byteBuffer.get(position + 1) & 255) << 8) | ((byteBuffer.get(position + 2) & 255) << 16) | ((byteBuffer.get(position + 3) & 255) << 24), this.f);
                position += 4;
            }
        } else {
            while (position < limit) {
                a(((byteBuffer.get(position) & 255) << 8) | ((byteBuffer.get(position + 1) & 255) << 16) | ((byteBuffer.get(position + 2) & 255) << 24), this.f);
                position += 3;
            }
        }
        byteBuffer.position(byteBuffer.limit());
        this.f.flip();
        this.g = this.f;
    }

    @Override // com.google.android.exoplayer2.audio.AudioProcessor
    public void e() {
        this.h = true;
    }

    @Override // com.google.android.exoplayer2.audio.AudioProcessor
    public ByteBuffer f() {
        ByteBuffer byteBuffer = this.g;
        this.g = a;
        return byteBuffer;
    }

    @Override // com.google.android.exoplayer2.audio.AudioProcessor
    public boolean g() {
        return this.h && this.g == a;
    }

    @Override // com.google.android.exoplayer2.audio.AudioProcessor
    public void h() {
        this.g = a;
        this.h = false;
    }

    @Override // com.google.android.exoplayer2.audio.AudioProcessor
    public void i() {
        h();
        this.c = -1;
        this.d = -1;
        this.e = 0;
        this.f = a;
    }

    private static void a(int i, ByteBuffer byteBuffer) {
        int floatToIntBits = Float.floatToIntBits((float) (((double) i) * 4.656612875245797E-10d));
        if (floatToIntBits == b) {
            floatToIntBits = Float.floatToIntBits(0.0f);
        }
        byteBuffer.putInt(floatToIntBits);
    }
}
