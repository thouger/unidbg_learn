package com.google.common.base;

/* compiled from: CharMatcher */
public abstract class b implements n<Character> {
    public abstract boolean b(char c2);

    public static b a(char c2) {
        return new c(c2);
    }

    public static b a(char c2, char c3) {
        return new C0101b(c2, c3);
    }

    protected b() {
    }

    public int a(CharSequence charSequence, int i) {
        int length = charSequence.length();
        m.b(i, length);
        while (i < length) {
            if (b(charSequence.charAt(i))) {
                return i;
            }
            i++;
        }
        return -1;
    }

    @Deprecated
    /* renamed from: a */
    public boolean apply(Character ch) {
        return b(ch.charValue());
    }

    public String toString() {
        return super.toString();
    }

    /* access modifiers changed from: private */
    public static String d(char c2) {
        char[] cArr = {'\\', 'u', 0, 0, 0, 0};
        for (int i = 0; i < 4; i++) {
            cArr[5 - i] = "0123456789ABCDEF".charAt(c2 & 15);
            c2 = (char) (c2 >> 4);
        }
        return String.copyValueOf(cArr);
    }

    /* compiled from: CharMatcher */
    static abstract class a extends b {
        a() {
        }

        @Override // com.google.common.base.b, com.google.common.base.n
        @Deprecated
        public /* synthetic */ boolean apply(Object obj) {
            return b.super.apply((Character) obj);
        }
    }

    /* compiled from: CharMatcher */
    private static final class c extends a {
        private final char a;

        c(char c) {
            this.a = c;
        }

        @Override // com.google.common.base.b
        public boolean b(char c) {
            return c == this.a;
        }

        @Override // com.google.common.base.b
        public String toString() {
            return "CharMatcher.is('" + b.d(this.a) + "')";
        }
    }

    /* compiled from: CharMatcher */
    /* renamed from: com.google.common.base.b$b  reason: collision with other inner class name */
    private static final class C0101b extends a {
        private final char a;
        private final char b;

        C0101b(char c, char c2) {
            m.a(c2 >= c);
            this.a = c;
            this.b = c2;
        }

        @Override // com.google.common.base.b
        public boolean b(char c) {
            return this.a <= c && c <= this.b;
        }

        @Override // com.google.common.base.b
        public String toString() {
            return "CharMatcher.inRange('" + b.d(this.a) + "', '" + b.d(this.b) + "')";
        }
    }
}
