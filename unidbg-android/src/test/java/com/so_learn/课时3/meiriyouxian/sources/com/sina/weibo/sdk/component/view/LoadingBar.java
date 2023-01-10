package com.sina.weibo.sdk.component.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Handler;
import android.widget.TextView;

public class LoadingBar extends TextView {
    private int a;
    private int b;
    private Paint c;
    private Handler d;
    private Runnable e = new AnonymousClass1();

    public LoadingBar(Context context) {
        super(context);
        a(context);
    }

    private void a(Context context) {
        this.d = new Handler();
        this.c = new Paint();
        a();
    }

    public void a() {
        this.b = -11693826;
    }

    /* access modifiers changed from: protected */
    @Override // android.widget.TextView, android.view.View
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        this.c.setColor(this.b);
        canvas.drawRect(getRect(), this.c);
    }

    private Rect getRect() {
        int left = getLeft();
        int top = getTop();
        return new Rect(0, 0, (getLeft() + (((getRight() - getLeft()) * this.a) / 100)) - left, getBottom() - top);
    }

    /* renamed from: com.sina.weibo.sdk.component.view.LoadingBar$1  reason: invalid class name */
    class AnonymousClass1 implements Runnable {
        AnonymousClass1() {
        }

        @Override // java.lang.Runnable
        public void run() {
            LoadingBar.this.a++;
            LoadingBar loadingBar = LoadingBar.this;
            loadingBar.a(loadingBar.a);
        }
    }

    public void a(int i) {
        if (i < 7) {
            this.d.postDelayed(this.e, 70);
        } else {
            this.d.removeCallbacks(this.e);
            this.a = i;
        }
        invalidate();
    }
}
