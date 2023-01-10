package cn.missfresh.module.base.widget.wheelview.widget;

import android.content.Context;
import android.media.AudioSystem;
import android.os.Handler;
import android.os.Message;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.animation.Interpolator;
import android.widget.Scroller;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;

/* compiled from: WheelScroller */
public class f {
    private a a;
    private Context b;
    private GestureDetector c;
    private Scroller d;
    private int e;
    private float f;
    private boolean g;
    private GestureDetector.SimpleOnGestureListener h = new AnonymousClass1();
    private final int i = 0;
    private final int j = 1;
    private Handler k = new AnonymousClass2();

    /* compiled from: WheelScroller */
    public interface a {
        void a();

        void a(int i);

        void b();

        void c();
    }

    static /* synthetic */ void b(f fVar, int i) {
        AppMethodBeat.i(24409, false);
        fVar.a(i);
        AppMethodBeat.o(24409);
    }

    static /* synthetic */ void e(f fVar) {
        AppMethodBeat.i(24410, false);
        fVar.d();
        AppMethodBeat.o(24410);
    }

    public f(Context context, a aVar) {
        AppMethodBeat.i(24399, false);
        this.c = new GestureDetector(context, this.h);
        this.c.setIsLongpressEnabled(false);
        this.d = new Scroller(context);
        this.a = aVar;
        this.b = context;
        AppMethodBeat.o(24399);
    }

    public void a(Interpolator interpolator) {
        AppMethodBeat.i(24400, false);
        this.d.forceFinished(true);
        this.d = new Scroller(this.b, interpolator);
        AppMethodBeat.o(24400);
    }

    public void a(int i, int i2) {
        AppMethodBeat.i(24401, false);
        this.d.forceFinished(true);
        this.e = 0;
        Scroller scroller = this.d;
        if (i2 == 0) {
            i2 = 400;
        }
        scroller.startScroll(0, 0, 0, i, i2);
        a(0);
        e();
        AppMethodBeat.o(24401);
    }

    public void a() {
        AppMethodBeat.i(24402, false);
        this.d.forceFinished(true);
        AppMethodBeat.o(24402);
    }

    public boolean a(MotionEvent motionEvent) {
        int y;
        AppMethodBeat.i(24403, false);
        int action = motionEvent.getAction();
        if (action == 0) {
            this.f = motionEvent.getY();
            this.d.forceFinished(true);
            c();
        } else if (action == 2 && (y = (int) (motionEvent.getY() - this.f)) != 0) {
            e();
            this.a.a(y);
            this.f = motionEvent.getY();
        }
        if (!this.c.onTouchEvent(motionEvent) && motionEvent.getAction() == 1) {
            d();
        }
        AppMethodBeat.o(24403);
        return true;
    }

    /* compiled from: WheelScroller */
    /* renamed from: cn.missfresh.module.base.widget.wheelview.widget.f$1  reason: invalid class name */
    class AnonymousClass1 extends GestureDetector.SimpleOnGestureListener {
        @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
        public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
            return true;
        }

        AnonymousClass1() {
        }

        @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
        public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
            AppMethodBeat.i(24397, false);
            f.this.e = 0;
            f.this.d.fling(0, f.this.e, 0, (int) (-f2), 0, 0, AudioSystem.DEVICE_IN_COMMUNICATION, Integer.MAX_VALUE);
            f.b(f.this, 0);
            AppMethodBeat.o(24397);
            return true;
        }
    }

    private void a(int i) {
        AppMethodBeat.i(24404, false);
        c();
        this.k.sendEmptyMessage(i);
        AppMethodBeat.o(24404);
    }

    private void c() {
        AppMethodBeat.i(24405, false);
        this.k.removeMessages(0);
        this.k.removeMessages(1);
        AppMethodBeat.o(24405);
    }

    /* compiled from: WheelScroller */
    /* renamed from: cn.missfresh.module.base.widget.wheelview.widget.f$2  reason: invalid class name */
    class AnonymousClass2 extends Handler {
        AnonymousClass2() {
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            AppMethodBeat.i(24398, false);
            f.this.d.computeScrollOffset();
            int currY = f.this.d.getCurrY();
            int i = f.this.e - currY;
            f.this.e = currY;
            if (i != 0) {
                f.this.a.a(i);
            }
            if (Math.abs(currY - f.this.d.getFinalY()) < 1) {
                f.this.d.getFinalY();
                f.this.d.forceFinished(true);
            }
            if (!f.this.d.isFinished()) {
                f.this.k.sendEmptyMessage(message.what);
            } else if (message.what == 0) {
                f.e(f.this);
            } else {
                f.this.b();
            }
            AppMethodBeat.o(24398);
        }
    }

    private void d() {
        AppMethodBeat.i(24406, false);
        this.a.c();
        a(1);
        AppMethodBeat.o(24406);
    }

    private void e() {
        AppMethodBeat.i(24407, false);
        if (!this.g) {
            this.g = true;
            this.a.a();
        }
        AppMethodBeat.o(24407);
    }

    /* access modifiers changed from: package-private */
    public void b() {
        AppMethodBeat.i(24408, false);
        if (this.g) {
            this.a.b();
            this.g = false;
        }
        AppMethodBeat.o(24408);
    }
}
