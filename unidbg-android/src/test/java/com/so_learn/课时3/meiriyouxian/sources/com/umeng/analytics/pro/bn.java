package com.umeng.analytics.pro;

/* compiled from: TMessage */
public final class bn {
    public final String a;
    public final byte b;
    public final int c;

    public bn() {
        this("", (byte) 0, 0);
    }

    public bn(String str, byte b, int i) {
        this.a = str;
        this.b = b;
        this.c = i;
    }

    public String toString() {
        return "<TMessage name:'" + this.a + "' type: " + ((int) this.b) + " seqid:" + this.c + ">";
    }

    public boolean equals(Object obj) {
        if (obj instanceof bn) {
            return a((bn) obj);
        }
        return false;
    }

    public boolean a(bn bnVar) {
        return this.a.equals(bnVar.a) && this.b == bnVar.b && this.c == bnVar.c;
    }
}
