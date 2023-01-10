package com.google.android.exoplayer2.text.h;

import android.text.Layout;
import android.text.SpannableStringBuilder;
import com.google.android.exoplayer2.util.i;

/* compiled from: WebvttCue */
public final class e extends com.google.android.exoplayer2.text.a {
    public final long o;
    public final long p;

    public e(CharSequence charSequence) {
        this(0, 0, charSequence);
    }

    public e(long j, long j2, CharSequence charSequence) {
        this(j, j2, charSequence, null, Float.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Float.MIN_VALUE, Integer.MIN_VALUE, Float.MIN_VALUE);
    }

    public e(long j, long j2, CharSequence charSequence, Layout.Alignment alignment, float f, int i, int i2, float f2, int i3, float f3) {
        super(charSequence, alignment, f, i, i2, f2, i3, f3);
        this.o = j;
        this.p = j2;
    }

    public boolean a() {
        return this.d == Float.MIN_VALUE && this.g == Float.MIN_VALUE;
    }

    /* compiled from: WebvttCue */
    public static class a {
        private long a;
        private long b;
        private SpannableStringBuilder c;
        private Layout.Alignment d;
        private float e;
        private int f;
        private int g;
        private float h;
        private int i;
        private float j;

        public a() {
            a();
        }

        public void a() {
            this.a = 0;
            this.b = 0;
            this.c = null;
            this.d = null;
            this.e = Float.MIN_VALUE;
            this.f = Integer.MIN_VALUE;
            this.g = Integer.MIN_VALUE;
            this.h = Float.MIN_VALUE;
            this.i = Integer.MIN_VALUE;
            this.j = Float.MIN_VALUE;
        }

        public e b() {
            if (this.h != Float.MIN_VALUE && this.i == Integer.MIN_VALUE) {
                c();
            }
            return new e(this.a, this.b, this.c, this.d, this.e, this.f, this.g, this.h, this.i, this.j);
        }

        public a a(long j) {
            this.a = j;
            return this;
        }

        public a b(long j) {
            this.b = j;
            return this;
        }

        public a a(SpannableStringBuilder spannableStringBuilder) {
            this.c = spannableStringBuilder;
            return this;
        }

        public a a(Layout.Alignment alignment) {
            this.d = alignment;
            return this;
        }

        public a a(float f) {
            this.e = f;
            return this;
        }

        public a a(int i) {
            this.f = i;
            return this;
        }

        public a b(int i) {
            this.g = i;
            return this;
        }

        public a b(float f) {
            this.h = f;
            return this;
        }

        public a c(int i) {
            this.i = i;
            return this;
        }

        public a c(float f) {
            this.j = f;
            return this;
        }

        private a c() {
            if (this.d == null) {
                this.i = Integer.MIN_VALUE;
            } else {
                int i = AnonymousClass1.a[this.d.ordinal()];
                if (i == 1) {
                    this.i = 0;
                } else if (i == 2) {
                    this.i = 1;
                } else if (i != 3) {
                    i.c("WebvttCueBuilder", "Unrecognized alignment: " + this.d);
                    this.i = 0;
                } else {
                    this.i = 2;
                }
            }
            return this;
        }
    }

    /* compiled from: WebvttCue */
    /* access modifiers changed from: package-private */
    /* renamed from: com.google.android.exoplayer2.text.h.e$1  reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] a = new int[Layout.Alignment.values().length];

        static {
            try {
                a[Layout.Alignment.ALIGN_NORMAL.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                a[Layout.Alignment.ALIGN_CENTER.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                a[Layout.Alignment.ALIGN_OPPOSITE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }
}
