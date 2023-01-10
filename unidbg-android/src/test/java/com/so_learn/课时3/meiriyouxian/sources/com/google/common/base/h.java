package com.google.common.base;

import java.io.IOException;
import java.util.Arrays;
import java.util.Iterator;

/* compiled from: Joiner */
public class h {
    private final String a;

    /* synthetic */ h(h hVar, AnonymousClass1 r2) {
        this(hVar);
    }

    public static h a(String str) {
        return new h(str);
    }

    public static h a(char c) {
        return new h(String.valueOf(c));
    }

    private h(String str) {
        this.a = (String) m.a(str);
    }

    private h(h hVar) {
        this.a = hVar.a;
    }

    public <A extends Appendable> A a(A a, Iterator<?> it2) throws IOException {
        m.a(a);
        if (it2.hasNext()) {
            a.append(a(it2.next()));
            while (it2.hasNext()) {
                a.append(this.a);
                a.append(a(it2.next()));
            }
        }
        return a;
    }

    public final StringBuilder a(StringBuilder sb, Iterator<?> it2) {
        try {
            a((h) sb, it2);
            return sb;
        } catch (IOException e) {
            throw new AssertionError(e);
        }
    }

    public final String a(Iterable<?> iterable) {
        return a(iterable.iterator());
    }

    public final String a(Iterator<?> it2) {
        return a(new StringBuilder(), it2).toString();
    }

    public final String a(Object[] objArr) {
        return a((Iterable<?>) Arrays.asList(objArr));
    }

    /* compiled from: Joiner */
    /* renamed from: com.google.common.base.h$1  reason: invalid class name */
    class AnonymousClass1 extends h {
        final /* synthetic */ String a;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        AnonymousClass1(h hVar, String str) {
            super(hVar, null);
            this.a = str;
        }

        /* access modifiers changed from: package-private */
        @Override // com.google.common.base.h
        public CharSequence a(Object obj) {
            return obj == null ? this.a : h.this.a(obj);
        }

        @Override // com.google.common.base.h
        public h b(String str) {
            throw new UnsupportedOperationException("already specified useForNull");
        }
    }

    public h b(String str) {
        m.a(str);
        return new AnonymousClass1(this, str);
    }

    /* access modifiers changed from: package-private */
    public CharSequence a(Object obj) {
        m.a(obj);
        return obj instanceof CharSequence ? (CharSequence) obj : obj.toString();
    }
}
