package com.sobot.chat.utils;

import android.media.AudioRecord;
import android.media.MediaRecorder;
import android.util.Log;
import com.huawei.hms.support.api.entity.core.JosStatusCodes;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

public class ExtAudioRecorder {
    private static final int[] a = {44100, 22050, 11025, JosStatusCodes.RTN_CODE_COMMON_ERROR};
    private boolean b;
    private AudioRecord c = null;
    private MediaRecorder d = null;
    private int e = 0;
    private String f = null;
    private State g;
    private RandomAccessFile h;
    private short i;
    private int j;
    private short k;
    private int l;
    private int m;
    private int n;
    private int o;
    private byte[] p;
    private int q;
    private AudioRecord.OnRecordPositionUpdateListener r = new AnonymousClass1();

    public enum State {
        INITIALIZING,
        READY,
        RECORDING,
        ERROR,
        STOPPED
    }

    public interface a {
        void a();

        void b();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private short a(byte b, byte b2) {
        return (short) (b | (b2 << 8));
    }

    public static ExtAudioRecorder a(Boolean bool) {
        ExtAudioRecorder extAudioRecorder;
        boolean z;
        boolean z2;
        if (bool.booleanValue()) {
            return new ExtAudioRecorder(false, 1, a[3], 2, 2);
        }
        int i = 0;
        do {
            extAudioRecorder = new ExtAudioRecorder(true, 1, a[3], 2, 2);
            z = true;
            i++;
            z2 = i < a.length;
            if (extAudioRecorder.a() == State.INITIALIZING) {
                z = false;
            }
        } while (z & z2);
        return extAudioRecorder;
    }

    public State a() {
        return this.g;
    }

    /* renamed from: com.sobot.chat.utils.ExtAudioRecorder$1  reason: invalid class name */
    class AnonymousClass1 implements AudioRecord.OnRecordPositionUpdateListener {
        @Override // android.media.AudioRecord.OnRecordPositionUpdateListener
        public void onMarkerReached(AudioRecord audioRecord) {
        }

        AnonymousClass1() {
        }

        @Override // android.media.AudioRecord.OnRecordPositionUpdateListener
        public void onPeriodicNotification(AudioRecord audioRecord) {
            int i = 0;
            ExtAudioRecorder.this.c.read(ExtAudioRecorder.this.p, 0, ExtAudioRecorder.this.p.length);
            try {
                ExtAudioRecorder.this.h.write(ExtAudioRecorder.this.p);
                ExtAudioRecorder.this.q += ExtAudioRecorder.this.p.length;
                if (ExtAudioRecorder.this.k == 16) {
                    while (i < ExtAudioRecorder.this.p.length / 2) {
                        int i2 = i * 2;
                        short a = ExtAudioRecorder.this.a(ExtAudioRecorder.this.p[i2], ExtAudioRecorder.this.p[i2 + 1]);
                        if (a > ExtAudioRecorder.this.e) {
                            ExtAudioRecorder.this.e = a;
                        }
                        i++;
                    }
                    return;
                }
                while (i < ExtAudioRecorder.this.p.length) {
                    if (ExtAudioRecorder.this.p[i] > ExtAudioRecorder.this.e) {
                        ExtAudioRecorder.this.e = ExtAudioRecorder.this.p[i];
                    }
                    i++;
                }
            } catch (IOException unused) {
            }
        }
    }

    public ExtAudioRecorder(boolean z, int i, int i2, int i3, int i4) {
        try {
            this.b = z;
            if (this.b) {
                if (i4 == 2) {
                    this.k = 16;
                } else {
                    this.k = 8;
                }
                if (i3 == 2) {
                    this.i = 1;
                } else {
                    this.i = 2;
                }
                this.m = i;
                this.j = i2;
                this.n = i4;
                this.o = (i2 * 120) / 1000;
                this.l = (((this.o * 2) * this.k) * this.i) / 8;
                if (this.l < AudioRecord.getMinBufferSize(i2, i3, i4)) {
                    this.l = AudioRecord.getMinBufferSize(i2, i3, i4);
                    this.o = this.l / (((this.k * 2) * this.i) / 8);
                    String name = ExtAudioRecorder.class.getName();
                    Log.w(name, "Increasing buffer size to " + Integer.toString(this.l));
                }
                this.c = new AudioRecord(i, i2, i3, i4, this.l);
                if (this.c.getState() == 1) {
                    this.c.setRecordPositionUpdateListener(this.r);
                    this.c.setPositionNotificationPeriod(this.o);
                } else {
                    throw new Exception("AudioRecord initialization failed");
                }
            } else {
                this.d = new MediaRecorder();
                this.d.setAudioSource(1);
                this.d.setOutputFormat(1);
                this.d.setAudioEncoder(1);
            }
            this.e = 0;
            this.f = null;
            this.g = State.INITIALIZING;
        } catch (Exception e) {
            if (e.getMessage() != null) {
                Log.e(ExtAudioRecorder.class.getName(), e.getMessage());
            } else {
                Log.e(ExtAudioRecorder.class.getName(), "Unknown error occured while initializing recording");
            }
            this.g = State.ERROR;
        }
    }

    public void a(String str) {
        try {
            if (this.g == State.INITIALIZING) {
                this.f = str;
                if (!this.b) {
                    this.d.setOutputFile(this.f);
                }
            }
        } catch (Exception e) {
            if (e.getMessage() != null) {
                Log.e(ExtAudioRecorder.class.getName(), e.getMessage());
            } else {
                Log.e(ExtAudioRecorder.class.getName(), "Unknown error occured while setting output path");
            }
            this.g = State.ERROR;
        }
    }

    public void b() {
        try {
            if (this.g != State.INITIALIZING) {
                c();
                this.g = State.ERROR;
            } else if (this.b) {
                if ((this.c.getState() == 1) && (this.f != null)) {
                    this.h = new RandomAccessFile(this.f, "rw");
                    this.h.setLength(0);
                    this.h.writeBytes("RIFF");
                    this.h.writeInt(0);
                    this.h.writeBytes("WAVE");
                    this.h.writeBytes("fmt ");
                    this.h.writeInt(Integer.reverseBytes(16));
                    this.h.writeShort(Short.reverseBytes(1));
                    this.h.writeShort(Short.reverseBytes(this.i));
                    this.h.writeInt(Integer.reverseBytes(this.j));
                    this.h.writeInt(Integer.reverseBytes(((this.j * this.k) * this.i) / 8));
                    this.h.writeShort(Short.reverseBytes((short) ((this.i * this.k) / 8)));
                    this.h.writeShort(Short.reverseBytes(this.k));
                    this.h.writeBytes("data");
                    this.h.writeInt(0);
                    this.p = new byte[(((this.o * this.k) / 8) * this.i)];
                    this.g = State.READY;
                    return;
                }
                Log.e(ExtAudioRecorder.class.getName(), "prepare() method called on uninitialized recorder");
                this.g = State.ERROR;
            } else {
                this.d.prepare();
                this.g = State.READY;
            }
        } catch (Exception e) {
            if (e.getMessage() != null) {
                Log.e(ExtAudioRecorder.class.getName(), e.getMessage());
            } else {
                Log.e(ExtAudioRecorder.class.getName(), "Unknown error occured in prepare()");
            }
            this.g = State.ERROR;
        }
    }

    public void c() {
        if (this.g == State.RECORDING) {
            d();
        } else {
            if ((this.g == State.READY) && this.b) {
                try {
                    this.h.close();
                } catch (IOException unused) {
                    Log.e(ExtAudioRecorder.class.getName(), "I/O exception occured while closing output file");
                }
                new File(this.f).delete();
            }
        }
        if (this.b) {
            AudioRecord audioRecord = this.c;
            if (audioRecord != null) {
                audioRecord.release();
                return;
            }
            return;
        }
        MediaRecorder mediaRecorder = this.d;
        if (mediaRecorder != null) {
            mediaRecorder.release();
        }
    }

    public void a(a aVar) {
        if (this.g == State.READY) {
            if (this.b) {
                this.q = 0;
                this.c.startRecording();
                AudioRecord audioRecord = this.c;
                byte[] bArr = this.p;
                int read = audioRecord.read(bArr, 0, bArr.length);
                m.d("volume----r:" + read);
                if (read > 0) {
                    aVar.a();
                } else {
                    this.g = State.RECORDING;
                    d();
                    c();
                    aVar.b();
                }
            } else {
                this.d.start();
            }
            this.g = State.RECORDING;
            return;
        }
        Log.e(ExtAudioRecorder.class.getName(), "start() called on illegal state");
        this.g = State.ERROR;
    }

    public void d() {
        if (this.g == State.RECORDING) {
            if (this.b) {
                this.c.stop();
                try {
                    this.h.seek(4);
                    this.h.writeInt(Integer.reverseBytes(this.q + 36));
                    this.h.seek(40);
                    this.h.writeInt(Integer.reverseBytes(this.q));
                    this.h.close();
                } catch (IOException unused) {
                    Log.e(ExtAudioRecorder.class.getName(), "I/O exception occured while closing output file");
                    this.g = State.ERROR;
                }
            } else {
                this.d.stop();
            }
            this.g = State.STOPPED;
            return;
        }
        Log.e(ExtAudioRecorder.class.getName(), "stop() called on illegal state");
        this.g = State.ERROR;
    }
}
