package com.google.android.exoplayer2.util;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

/* compiled from: SystemHandlerWrapper */
final class u implements h {
    private final Handler a;

    public u(Handler handler) {
        this.a = handler;
    }

    @Override // com.google.android.exoplayer2.util.h
    public Looper a() {
        return this.a.getLooper();
    }

    @Override // com.google.android.exoplayer2.util.h
    public Message a(int i, Object obj) {
        return this.a.obtainMessage(i, obj);
    }

    @Override // com.google.android.exoplayer2.util.h
    public Message a(int i, int i2, int i3) {
        return this.a.obtainMessage(i, i2, i3);
    }

    @Override // com.google.android.exoplayer2.util.h
    public Message a(int i, int i2, int i3, Object obj) {
        return this.a.obtainMessage(i, i2, i3, obj);
    }

    @Override // com.google.android.exoplayer2.util.h
    public boolean a(int i) {
        return this.a.sendEmptyMessage(i);
    }

    @Override // com.google.android.exoplayer2.util.h
    public boolean a(int i, long j) {
        return this.a.sendEmptyMessageAtTime(i, j);
    }

    @Override // com.google.android.exoplayer2.util.h
    public void b(int i) {
        this.a.removeMessages(i);
    }
}
