package com.google.common.hash;

import com.google.common.base.m;
import com.umeng.message.proguard.l;
import java.io.Serializable;
import java.nio.charset.Charset;
import java.util.Iterator;

public final class Funnels {

    private enum ByteArrayFunnel implements Funnel<byte[]> {
        INSTANCE;

        @Override // java.lang.Enum, java.lang.Object
        public String toString() {
            return "Funnels.byteArrayFunnel()";
        }

        public void funnel(byte[] bArr, i iVar) {
            iVar.c(bArr);
        }
    }

    private enum UnencodedCharsFunnel implements Funnel<CharSequence> {
        INSTANCE;

        @Override // java.lang.Enum, java.lang.Object
        public String toString() {
            return "Funnels.unencodedCharsFunnel()";
        }

        public void funnel(CharSequence charSequence, i iVar) {
            iVar.b(charSequence);
        }
    }

    public static Funnel<CharSequence> a(Charset charset) {
        return new StringCharsetFunnel(charset);
    }

    /* access modifiers changed from: private */
    public static class StringCharsetFunnel implements Funnel<CharSequence>, Serializable {
        private final Charset charset;

        StringCharsetFunnel(Charset charset) {
            this.charset = (Charset) m.a(charset);
        }

        public void funnel(CharSequence charSequence, i iVar) {
            iVar.b(charSequence, this.charset);
        }

        @Override // java.lang.Object
        public String toString() {
            return "Funnels.stringFunnel(" + this.charset.name() + l.t;
        }

        @Override // java.lang.Object
        public boolean equals(Object obj) {
            if (obj instanceof StringCharsetFunnel) {
                return this.charset.equals(((StringCharsetFunnel) obj).charset);
            }
            return false;
        }

        @Override // java.lang.Object
        public int hashCode() {
            return StringCharsetFunnel.class.hashCode() ^ this.charset.hashCode();
        }

        /* access modifiers changed from: package-private */
        public Object writeReplace() {
            return new SerializedForm(this.charset);
        }

        private static class SerializedForm implements Serializable {
            private static final long serialVersionUID = 0;
            private final String charsetCanonicalName;

            SerializedForm(Charset charset) {
                this.charsetCanonicalName = charset.name();
            }

            private Object readResolve() {
                return Funnels.a(Charset.forName(this.charsetCanonicalName));
            }
        }
    }

    private enum IntegerFunnel implements Funnel<Integer> {
        INSTANCE;

        @Override // java.lang.Enum, java.lang.Object
        public String toString() {
            return "Funnels.integerFunnel()";
        }

        public void funnel(Integer num, i iVar) {
            iVar.b(num.intValue());
        }
    }

    private static class SequentialFunnel<E> implements Funnel<Iterable<? extends E>>, Serializable {
        private final Funnel<E> elementFunnel;

        SequentialFunnel(Funnel<E> funnel) {
            this.elementFunnel = (Funnel) m.a(funnel);
        }

        public void funnel(Iterable<? extends E> iterable, i iVar) {
            Iterator<? extends E> it2 = iterable.iterator();
            while (it2.hasNext()) {
                this.elementFunnel.funnel(it2.next(), iVar);
            }
        }

        @Override // java.lang.Object
        public String toString() {
            return "Funnels.sequentialFunnel(" + this.elementFunnel + l.t;
        }

        @Override // java.lang.Object
        public boolean equals(Object obj) {
            if (obj instanceof SequentialFunnel) {
                return this.elementFunnel.equals(((SequentialFunnel) obj).elementFunnel);
            }
            return false;
        }

        @Override // java.lang.Object
        public int hashCode() {
            return SequentialFunnel.class.hashCode() ^ this.elementFunnel.hashCode();
        }
    }

    private enum LongFunnel implements Funnel<Long> {
        INSTANCE;

        @Override // java.lang.Enum, java.lang.Object
        public String toString() {
            return "Funnels.longFunnel()";
        }

        public void funnel(Long l, i iVar) {
            iVar.b(l.longValue());
        }
    }
}
