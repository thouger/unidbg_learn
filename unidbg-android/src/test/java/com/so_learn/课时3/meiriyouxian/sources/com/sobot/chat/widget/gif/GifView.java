package com.sobot.chat.widget.gif;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import java.io.InputStream;

public class GifView extends View implements a {
    private b a;
    private Bitmap b;
    private boolean c;
    private boolean d;
    private int e;
    private Rect f;
    private a g;
    private GifImageType h;
    private Handler i;

    public enum GifImageType {
        WAIT_FINISH(0),
        SYNC_DECODER(1),
        COVER(2);
        
        final int nativeInt;

        private GifImageType(int i) {
            this.nativeInt = i;
        }
    }

    private void setGifDecoderImage(byte[] bArr) {
        b bVar = this.a;
        if (bVar != null) {
            bVar.a();
            this.a = null;
        }
        this.a = new b(bArr, this);
        this.a.start();
    }

    private void setGifDecoderImage(InputStream inputStream) {
        b bVar = this.a;
        if (bVar != null) {
            bVar.a();
            this.a = null;
        }
        this.a = new b(inputStream, this);
        this.a.start();
    }

    public void setGifImage(byte[] bArr) {
        setGifDecoderImage(bArr);
    }

    public void setGifImage(InputStream inputStream) {
        setGifDecoderImage(inputStream);
    }

    public void setGifImage(int i) {
        setGifDecoderImage(getResources().openRawResource(i));
    }

    /* access modifiers changed from: protected */
    @Override // android.view.View
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        b bVar = this.a;
        if (bVar != null) {
            if (this.b == null) {
                this.b = bVar.c();
            }
            if (this.b != null) {
                int saveCount = canvas.getSaveCount();
                canvas.save();
                canvas.translate((float) getPaddingLeft(), (float) getPaddingTop());
                if (this.e == -1) {
                    canvas.drawBitmap(this.b, 0.0f, 0.0f, (Paint) null);
                } else {
                    canvas.drawBitmap(this.b, (Rect) null, this.f, (Paint) null);
                }
                canvas.restoreToCount(saveCount);
            }
        }
    }

    /* access modifiers changed from: protected */
    @Override // android.view.View
    public void onMeasure(int i, int i2) {
        int i3;
        int paddingLeft = getPaddingLeft();
        int paddingRight = getPaddingRight();
        int paddingTop = getPaddingTop();
        int paddingBottom = getPaddingBottom();
        b bVar = this.a;
        int i4 = 1;
        if (bVar == null) {
            i3 = 1;
        } else {
            i4 = bVar.a;
            i3 = this.a.b;
        }
        setMeasuredDimension(resolveSize(Math.max(i4 + paddingLeft + paddingRight, getSuggestedMinimumWidth()), i), resolveSize(Math.max(i3 + paddingTop + paddingBottom, getSuggestedMinimumHeight()), i2));
    }

    public void setGifImageType(GifImageType gifImageType) {
        if (this.a == null) {
            this.h = gifImageType;
        }
    }

    /* renamed from: com.sobot.chat.widget.gif.GifView$2  reason: invalid class name */
    static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] a = new int[GifImageType.values().length];

        static {
            try {
                a[GifImageType.WAIT_FINISH.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                a[GifImageType.COVER.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                a[GifImageType.SYNC_DECODER.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    @Override // com.sobot.chat.widget.gif.a
    public void a(boolean z, int i) {
        if (!z) {
            return;
        }
        if (this.a != null) {
            int i2 = AnonymousClass2.a[this.h.ordinal()];
            if (i2 != 1) {
                if (i2 != 2) {
                    if (i2 == 3) {
                        if (i == 1) {
                            this.b = this.a.c();
                            a();
                        } else if (i == -1) {
                            a();
                        } else if (this.g == null) {
                            this.g = new a(this, null);
                            this.g.start();
                        }
                    }
                } else if (i == 1) {
                    this.b = this.a.c();
                    a();
                } else if (i != -1) {
                } else {
                    if (this.a.b() <= 1) {
                        a();
                    } else if (this.g == null) {
                        this.g = new a(this, null);
                        this.g.start();
                    }
                }
            } else if (i != -1) {
            } else {
                if (this.a.b() > 1) {
                    new a(this, null).start();
                } else {
                    a();
                }
            }
        } else {
            Log.e("gif", "parse error");
        }
    }

    private void a() {
        Handler handler = this.i;
        if (handler != null) {
            this.i.sendMessage(handler.obtainMessage());
        }
    }

    /* renamed from: com.sobot.chat.widget.gif.GifView$1  reason: invalid class name */
    class AnonymousClass1 extends Handler {
        final /* synthetic */ GifView a;

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            this.a.invalidate();
        }
    }

    private class a extends Thread {
        private a() {
        }

        /* synthetic */ a(GifView gifView, AnonymousClass1 r2) {
            this();
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            if (GifView.this.a != null) {
                while (GifView.this.c) {
                    if (!GifView.this.d) {
                        c d = GifView.this.a.d();
                        GifView.this.b = d.a;
                        long j = (long) d.b;
                        if (GifView.this.i != null) {
                            GifView.this.i.sendMessage(GifView.this.i.obtainMessage());
                            SystemClock.sleep(j);
                        } else {
                            return;
                        }
                    } else {
                        SystemClock.sleep(10);
                    }
                }
            }
        }
    }
}
