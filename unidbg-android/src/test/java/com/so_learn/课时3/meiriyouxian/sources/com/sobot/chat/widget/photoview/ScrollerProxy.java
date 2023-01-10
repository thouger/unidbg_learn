package com.sobot.chat.widget.photoview;

import android.content.Context;
import android.os.Build;
import android.widget.OverScroller;
import android.widget.Scroller;

public abstract class ScrollerProxy {
    public abstract void a(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, int i10);

    public abstract void a(boolean z);

    public abstract boolean a();

    public abstract int b();

    public abstract int c();

    public static ScrollerProxy a(Context context) {
        if (Build.VERSION.SDK_INT < 9) {
            return new PreGingerScroller(context);
        }
        return new GingerScroller(context);
    }

    /* access modifiers changed from: private */
    public static class GingerScroller extends ScrollerProxy {
        private OverScroller a;

        public GingerScroller(Context context) {
            this.a = new OverScroller(context);
        }

        @Override // com.sobot.chat.widget.photoview.ScrollerProxy
        public boolean a() {
            return this.a.computeScrollOffset();
        }

        @Override // com.sobot.chat.widget.photoview.ScrollerProxy
        public void a(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, int i10) {
            this.a.fling(i, i2, i3, i4, i5, i6, i7, i8, i9, i10);
        }

        @Override // com.sobot.chat.widget.photoview.ScrollerProxy
        public void a(boolean z) {
            this.a.forceFinished(z);
        }

        @Override // com.sobot.chat.widget.photoview.ScrollerProxy
        public int b() {
            return this.a.getCurrX();
        }

        @Override // com.sobot.chat.widget.photoview.ScrollerProxy
        public int c() {
            return this.a.getCurrY();
        }
    }

    /* access modifiers changed from: private */
    public static class PreGingerScroller extends ScrollerProxy {
        private Scroller a;

        public PreGingerScroller(Context context) {
            this.a = new Scroller(context);
        }

        @Override // com.sobot.chat.widget.photoview.ScrollerProxy
        public boolean a() {
            return this.a.computeScrollOffset();
        }

        @Override // com.sobot.chat.widget.photoview.ScrollerProxy
        public void a(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, int i10) {
            this.a.fling(i, i2, i3, i4, i5, i6, i7, i8);
        }

        @Override // com.sobot.chat.widget.photoview.ScrollerProxy
        public void a(boolean z) {
            this.a.forceFinished(z);
        }

        @Override // com.sobot.chat.widget.photoview.ScrollerProxy
        public int b() {
            return this.a.getCurrX();
        }

        @Override // com.sobot.chat.widget.photoview.ScrollerProxy
        public int c() {
            return this.a.getCurrY();
        }
    }
}
