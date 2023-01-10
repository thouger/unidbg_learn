package com.umeng.analytics.pro;

import com.umeng.analytics.pro.bj;
import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;

/* compiled from: TSerializer */
public class az {
    private final ByteArrayOutputStream a;
    private final cb b;
    private bp c;

    public az() {
        this(new bj.a());
    }

    public az(br brVar) {
        this.a = new ByteArrayOutputStream();
        this.b = new cb(this.a);
        this.c = brVar.a(this.b);
    }

    public byte[] a(aq aqVar) throws aw {
        this.a.reset();
        aqVar.write(this.c);
        return this.a.toByteArray();
    }

    public String a(aq aqVar, String str) throws aw {
        try {
            return new String(a(aqVar), str);
        } catch (UnsupportedEncodingException unused) {
            throw new aw("JVM DOES NOT SUPPORT ENCODING: " + str);
        }
    }

    public String b(aq aqVar) throws aw {
        return new String(a(aqVar));
    }
}
