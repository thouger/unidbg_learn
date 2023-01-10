package com.sobot.chat.widget.horizontalgridpage;

/* compiled from: PageBuilder */
public class a {
    private int a;
    private int[] b;
    private int[] c;
    private int d;
    private boolean e;
    private int[] f;
    private int g;
    private int h;
    private int i;
    private int j;

    private a(C0145a aVar) {
        this.a = aVar.a;
        this.b = aVar.b;
        this.c = aVar.c;
        this.d = aVar.d;
        this.g = aVar.e;
        this.f = aVar.f;
        this.h = aVar.g;
        this.e = aVar.h;
        this.i = aVar.i;
        this.j = aVar.j;
    }

    public int[] a() {
        return this.b;
    }

    public int b() {
        return this.d;
    }

    public int c() {
        return this.g;
    }

    public int[] d() {
        return this.f;
    }

    public int e() {
        return this.h;
    }

    public boolean f() {
        return this.e;
    }

    public int g() {
        return this.i;
    }

    public int h() {
        return this.j;
    }

    /* compiled from: PageBuilder */
    /* renamed from: com.sobot.chat.widget.horizontalgridpage.a$a  reason: collision with other inner class name */
    public static class C0145a {
        private int a = 10;
        private int[] b = {5, 5, 5, 5};
        private int[] c = {17301609, 17301611};
        private int d = 17;
        private int e = 0;
        private int[] f = {3, 4};
        private int g = 50;
        private boolean h = true;
        private int i = 10;
        private int j = 50;

        public C0145a a(int i) {
            this.a = i;
            return this;
        }

        public C0145a a(int i, int i2, int i3, int i4) {
            int[] iArr = this.b;
            iArr[0] = i;
            iArr[1] = i2;
            iArr[2] = i3;
            iArr[3] = i4;
            return this;
        }

        public C0145a a(int i, int i2) {
            int[] iArr = this.c;
            iArr[0] = i;
            iArr[1] = i2;
            return this;
        }

        public C0145a b(int i) {
            this.d = i;
            return this;
        }

        public C0145a c(int i) {
            this.e = i;
            return this;
        }

        public C0145a b(int i, int i2) {
            int[] iArr = this.f;
            iArr[0] = i;
            iArr[1] = i2;
            return this;
        }

        public C0145a d(int i) {
            this.g = i;
            return this;
        }

        public C0145a a(boolean z) {
            this.h = z;
            return this;
        }

        public C0145a e(int i) {
            this.i = i;
            return this;
        }

        public C0145a f(int i) {
            this.j = i;
            return this;
        }

        public a a() {
            return new a(this);
        }
    }
}
