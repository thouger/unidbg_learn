package com.umeng.commonsdk.statistics.proto;

import com.umeng.analytics.pro.ai;
import com.umeng.analytics.pro.an;
import com.umeng.analytics.pro.aq;
import com.umeng.analytics.pro.aw;
import com.umeng.analytics.pro.ax;
import com.umeng.analytics.pro.bc;
import com.umeng.analytics.pro.bd;
import com.umeng.analytics.pro.bh;
import com.umeng.analytics.pro.bj;
import com.umeng.analytics.pro.bk;
import com.umeng.analytics.pro.bp;
import com.umeng.analytics.pro.bq;
import com.umeng.analytics.pro.bs;
import com.umeng.analytics.pro.bu;
import com.umeng.analytics.pro.bv;
import com.umeng.analytics.pro.bx;
import com.umeng.analytics.pro.by;
import com.umeng.analytics.pro.bz;
import com.umeng.analytics.pro.ca;
import com.umeng.analytics.pro.cb;
import com.umeng.message.proguard.l;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.BitSet;
import java.util.Collections;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Response implements aq<Response, e>, Serializable, Cloneable {
    private static final bk IMPRINT_FIELD_DESC = new bk(ai.X, (byte) 12, 3);
    private static final bk MSG_FIELD_DESC = new bk("msg", (byte) 11, 2);
    private static final bk RESP_CODE_FIELD_DESC = new bk("resp_code", (byte) 8, 1);
    private static final bu STRUCT_DESC = new bu("Response");
    private static final int __RESP_CODE_ISSET_ID = 0;
    public static final Map<e, bc> metaDataMap;
    private static final Map<Class<? extends bx>, by> schemes = new HashMap();
    private static final long serialVersionUID = -4549277923241195391L;
    private byte __isset_bitfield;
    public d imprint;
    public String msg;
    private e[] optionals;
    public int resp_code;

    static {
        schemes.put(bz.class, new b());
        schemes.put(ca.class, new d());
        EnumMap enumMap = new EnumMap(e.class);
        enumMap.put((EnumMap) e.RESP_CODE, (e) new bc("resp_code", (byte) 1, new bd((byte) 8)));
        enumMap.put((EnumMap) e.MSG, (e) new bc("msg", (byte) 2, new bd((byte) 11)));
        enumMap.put((EnumMap) e.IMPRINT, (e) new bc(ai.X, (byte) 2, new bh((byte) 12, d.class)));
        metaDataMap = Collections.unmodifiableMap(enumMap);
        bc.a(Response.class, metaDataMap);
    }

    public enum e implements ax {
        RESP_CODE(1, "resp_code"),
        MSG(2, "msg"),
        IMPRINT(3, ai.X);
        
        private static final Map<String, e> d = new HashMap();
        private final short e;
        private final String f;

        static {
            Iterator it2 = EnumSet.allOf(e.class).iterator();
            while (it2.hasNext()) {
                e eVar = (e) it2.next();
                d.put(eVar.b(), eVar);
            }
        }

        public static e a(int i) {
            if (i == 1) {
                return RESP_CODE;
            }
            if (i == 2) {
                return MSG;
            }
            if (i != 3) {
                return null;
            }
            return IMPRINT;
        }

        public static e b(int i) {
            e a = a(i);
            if (a != null) {
                return a;
            }
            throw new IllegalArgumentException("Field " + i + " doesn't exist!");
        }

        public static e a(String str) {
            return d.get(str);
        }

        private e(short s, String str) {
            this.e = s;
            this.f = str;
        }

        @Override // com.umeng.analytics.pro.ax
        public short a() {
            return this.e;
        }

        @Override // com.umeng.analytics.pro.ax
        public String b() {
            return this.f;
        }
    }

    public Response() {
        this.__isset_bitfield = 0;
        this.optionals = new e[]{e.MSG, e.IMPRINT};
    }

    public Response(int i) {
        this();
        this.resp_code = i;
        setResp_codeIsSet(true);
    }

    public Response(Response response) {
        this.__isset_bitfield = 0;
        this.optionals = new e[]{e.MSG, e.IMPRINT};
        this.__isset_bitfield = response.__isset_bitfield;
        this.resp_code = response.resp_code;
        if (response.isSetMsg()) {
            this.msg = response.msg;
        }
        if (response.isSetImprint()) {
            this.imprint = new d(response.imprint);
        }
    }

    @Override // com.umeng.analytics.pro.aq
    public Response deepCopy() {
        return new Response(this);
    }

    @Override // com.umeng.analytics.pro.aq
    public void clear() {
        setResp_codeIsSet(false);
        this.resp_code = 0;
        this.msg = null;
        this.imprint = null;
    }

    public int getResp_code() {
        return this.resp_code;
    }

    public Response setResp_code(int i) {
        this.resp_code = i;
        setResp_codeIsSet(true);
        return this;
    }

    public void unsetResp_code() {
        this.__isset_bitfield = an.b(this.__isset_bitfield, 0);
    }

    public boolean isSetResp_code() {
        return an.a(this.__isset_bitfield, 0);
    }

    public void setResp_codeIsSet(boolean z) {
        this.__isset_bitfield = an.a(this.__isset_bitfield, 0, z);
    }

    public String getMsg() {
        return this.msg;
    }

    public Response setMsg(String str) {
        this.msg = str;
        return this;
    }

    public void unsetMsg() {
        this.msg = null;
    }

    public boolean isSetMsg() {
        return this.msg != null;
    }

    public void setMsgIsSet(boolean z) {
        if (!z) {
            this.msg = null;
        }
    }

    public d getImprint() {
        return this.imprint;
    }

    public Response setImprint(d dVar) {
        this.imprint = dVar;
        return this;
    }

    public void unsetImprint() {
        this.imprint = null;
    }

    public boolean isSetImprint() {
        return this.imprint != null;
    }

    public void setImprintIsSet(boolean z) {
        if (!z) {
            this.imprint = null;
        }
    }

    @Override // com.umeng.analytics.pro.aq
    public e fieldForId(int i) {
        return e.a(i);
    }

    @Override // com.umeng.analytics.pro.aq
    public void read(bp bpVar) throws aw {
        schemes.get(bpVar.D()).b().b(bpVar, this);
    }

    @Override // com.umeng.analytics.pro.aq
    public void write(bp bpVar) throws aw {
        schemes.get(bpVar.D()).b().a(bpVar, this);
    }

    @Override // java.lang.Object
    public String toString() {
        StringBuilder sb = new StringBuilder("Response(");
        sb.append("resp_code:");
        sb.append(this.resp_code);
        if (isSetMsg()) {
            sb.append(", ");
            sb.append("msg:");
            String str = this.msg;
            if (str == null) {
                sb.append("null");
            } else {
                sb.append(str);
            }
        }
        if (isSetImprint()) {
            sb.append(", ");
            sb.append("imprint:");
            d dVar = this.imprint;
            if (dVar == null) {
                sb.append("null");
            } else {
                sb.append(dVar);
            }
        }
        sb.append(l.t);
        return sb.toString();
    }

    public void validate() throws aw {
        d dVar = this.imprint;
        if (dVar != null) {
            dVar.l();
        }
    }

    private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
        try {
            write(new bj(new cb(objectOutputStream)));
        } catch (aw e2) {
            throw new IOException(e2.getMessage());
        }
    }

    private void readObject(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        try {
            this.__isset_bitfield = 0;
            read(new bj(new cb(objectInputStream)));
        } catch (aw e2) {
            throw new IOException(e2.getMessage());
        }
    }

    private static class b implements by {
        private b() {
        }

        /* renamed from: a */
        public a b() {
            return new a();
        }
    }

    /* access modifiers changed from: private */
    public static class a extends bz<Response> {
        private a() {
        }

        /* renamed from: a */
        public void b(bp bpVar, Response response) throws aw {
            bpVar.j();
            while (true) {
                bk l = bpVar.l();
                if (l.b == 0) {
                    break;
                }
                short s = l.c;
                if (s != 1) {
                    if (s != 2) {
                        if (s != 3) {
                            bs.a(bpVar, l.b);
                        } else if (l.b == 12) {
                            response.imprint = new d();
                            response.imprint.read(bpVar);
                            response.setImprintIsSet(true);
                        } else {
                            bs.a(bpVar, l.b);
                        }
                    } else if (l.b == 11) {
                        response.msg = bpVar.z();
                        response.setMsgIsSet(true);
                    } else {
                        bs.a(bpVar, l.b);
                    }
                } else if (l.b == 8) {
                    response.resp_code = bpVar.w();
                    response.setResp_codeIsSet(true);
                } else {
                    bs.a(bpVar, l.b);
                }
                bpVar.m();
            }
            bpVar.k();
            if (response.isSetResp_code()) {
                response.validate();
                return;
            }
            throw new bq("Required field 'resp_code' was not found in serialized data! Struct: " + toString());
        }

        /* renamed from: b */
        public void a(bp bpVar, Response response) throws aw {
            response.validate();
            bpVar.a(Response.STRUCT_DESC);
            bpVar.a(Response.RESP_CODE_FIELD_DESC);
            bpVar.a(response.resp_code);
            bpVar.c();
            if (response.msg != null && response.isSetMsg()) {
                bpVar.a(Response.MSG_FIELD_DESC);
                bpVar.a(response.msg);
                bpVar.c();
            }
            if (response.imprint != null && response.isSetImprint()) {
                bpVar.a(Response.IMPRINT_FIELD_DESC);
                response.imprint.write(bpVar);
                bpVar.c();
            }
            bpVar.d();
            bpVar.b();
        }
    }

    private static class d implements by {
        private d() {
        }

        /* renamed from: a */
        public c b() {
            return new c();
        }
    }

    /* access modifiers changed from: private */
    public static class c extends ca<Response> {
        private c() {
        }

        public void a(bp bpVar, Response response) throws aw {
            bv bvVar = (bv) bpVar;
            bvVar.a(response.resp_code);
            BitSet bitSet = new BitSet();
            if (response.isSetMsg()) {
                bitSet.set(0);
            }
            if (response.isSetImprint()) {
                bitSet.set(1);
            }
            bvVar.a(bitSet, 2);
            if (response.isSetMsg()) {
                bvVar.a(response.msg);
            }
            if (response.isSetImprint()) {
                response.imprint.write(bvVar);
            }
        }

        public void b(bp bpVar, Response response) throws aw {
            bv bvVar = (bv) bpVar;
            response.resp_code = bvVar.w();
            response.setResp_codeIsSet(true);
            BitSet b = bvVar.b(2);
            if (b.get(0)) {
                response.msg = bvVar.z();
                response.setMsgIsSet(true);
            }
            if (b.get(1)) {
                response.imprint = new d();
                response.imprint.read(bvVar);
                response.setImprintIsSet(true);
            }
        }
    }
}
