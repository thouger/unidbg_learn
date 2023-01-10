package com.umeng.analytics.pro;

/* compiled from: TField */
public class bk {
    public final String a;
    public final byte b;
    public final short c;

    public bk() {
        this("", (byte) 0, 0);
    }

    public bk(String str, byte b, short s) {
        this.a = str;
        this.b = b;
        this.c = s;
    }

    public String toString() {
        return "<TField name:'" + this.a + "' type:" + ((int) this.b) + " field-id:" + ((int) this.c) + ">";
    }

    public boolean a(bk bkVar) {
        return this.b == bkVar.b && this.c == bkVar.c;
    }
}
