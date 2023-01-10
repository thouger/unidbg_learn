package com.sobot.chat.camera;

import android.content.Context;
import android.media.MediaPlayer;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;

/* compiled from: StProgressViewUpdateHelper */
public class b extends Handler {
    private MediaPlayer a;
    private Context b;
    private a c;
    private int d = 1000;
    private int e = 500;

    /* compiled from: StProgressViewUpdateHelper */
    public interface a {
        void a(int i, int i2);
    }

    public void a() {
        a(1);
    }

    public void b() {
        removeMessages(1);
    }

    public b(MediaPlayer mediaPlayer, Context context, a aVar) {
        super(Looper.getMainLooper());
        this.b = context;
        this.a = mediaPlayer;
        this.c = aVar;
    }

    @Override // android.os.Handler
    public void handleMessage(Message message) {
        int c;
        super.handleMessage(message);
        if (message.what == 1 && (c = c()) != -1) {
            a((long) c);
        }
    }

    private int c() {
        try {
            int currentPosition = this.a.getCurrentPosition();
            this.c.a(currentPosition, this.a.getDuration());
            if (!this.a.isPlaying()) {
                return -1;
            }
            int i = this.d;
            return Math.max(20, i - (currentPosition % i));
        } catch (Exception unused) {
            return -1;
        }
    }

    private void a(long j) {
        Message obtainMessage = obtainMessage(1);
        removeMessages(1);
        sendMessageDelayed(obtainMessage, j);
    }
}
