package com.google.android.exoplayer2.audio;

import com.google.android.exoplayer2.audio.AudioProcessor;
import com.google.android.exoplayer2.util.a;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Arrays;

/* compiled from: ChannelMappingAudioProcessor */
final class j implements AudioProcessor {
    private int b = -1;
    private int c = -1;
    private int[] d;
    private boolean e;
    private int[] f;
    private ByteBuffer g = a;
    private ByteBuffer h = a;
    private boolean i;

    @Override // com.google.android.exoplayer2.audio.AudioProcessor
    public int c() {
        return 2;
    }

    public void a(int[] iArr) {
        this.d = iArr;
    }

    @Override // com.google.android.exoplayer2.audio.AudioProcessor
    public boolean a(int i, int i2, int i3) throws AudioProcessor.UnhandledFormatException {
        boolean z = !Arrays.equals(this.d, this.f);
        this.f = this.d;
        if (this.f == null) {
            this.e = false;
            return z;
        } else if (i3 != 2) {
            throw new AudioProcessor.UnhandledFormatException(i, i2, i3);
        } else if (!z && this.c == i && this.b == i2) {
            return false;
        } else {
            this.c = i;
            this.b = i2;
            this.e = i2 != this.f.length;
            int i4 = 0;
            while (true) {
                int[] iArr = this.f;
                if (i4 >= iArr.length) {
                    return true;
                }
                int i5 = iArr[i4];
                if (i5 < i2) {
                    this.e = (i5 != i4) | this.e;
                    i4++;
                } else {
                    throw new AudioProcessor.UnhandledFormatException(i, i2, i3);
                }
            }
        }
    }

    @Override // com.google.android.exoplayer2.audio.AudioProcessor
    public boolean a() {
        return this.e;
    }

    @Override // com.google.android.exoplayer2.audio.AudioProcessor
    public int b() {
        int[] iArr = this.f;
        return iArr == null ? this.b : iArr.length;
    }

    @Override // com.google.android.exoplayer2.audio.AudioProcessor
    public int d() {
        return this.c;
    }

    @Override // com.google.android.exoplayer2.audio.AudioProcessor
    public void a(ByteBuffer byteBuffer) {
        a.b(this.f != null);
        int position = byteBuffer.position();
        int limit = byteBuffer.limit();
        int length = ((limit - position) / (this.b * 2)) * this.f.length * 2;
        if (this.g.capacity() < length) {
            this.g = ByteBuffer.allocateDirect(length).order(ByteOrder.nativeOrder());
        } else {
            this.g.clear();
        }
        while (position < limit) {
            for (int i : this.f) {
                this.g.putShort(byteBuffer.getShort((i * 2) + position));
            }
            position += this.b * 2;
        }
        byteBuffer.position(limit);
        this.g.flip();
        this.h = this.g;
    }

    @Override // com.google.android.exoplayer2.audio.AudioProcessor
    public void e() {
        this.i = true;
    }

    @Override // com.google.android.exoplayer2.audio.AudioProcessor
    public ByteBuffer f() {
        ByteBuffer byteBuffer = this.h;
        this.h = a;
        return byteBuffer;
    }

    @Override // com.google.android.exoplayer2.audio.AudioProcessor
    public boolean g() {
        return this.i && this.h == a;
    }

    @Override // com.google.android.exoplayer2.audio.AudioProcessor
    public void h() {
        this.h = a;
        this.i = false;
    }

    @Override // com.google.android.exoplayer2.audio.AudioProcessor
    public void i() {
        h();
        this.g = a;
        this.b = -1;
        this.c = -1;
        this.f = null;
        this.d = null;
        this.e = false;
    }
}
