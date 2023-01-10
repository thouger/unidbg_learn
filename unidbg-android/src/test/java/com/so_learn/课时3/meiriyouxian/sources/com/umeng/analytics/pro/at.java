package com.umeng.analytics.pro;

import com.umeng.analytics.pro.bj;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;

/* compiled from: TDeserializer */
public class at {
    private final bp a;
    private final cc b;

    public at() {
        this(new bj.a());
    }

    public at(br brVar) {
        this.b = new cc();
        this.a = brVar.a(this.b);
    }

    public void a(aq aqVar, byte[] bArr) throws aw {
        try {
            this.b.a(bArr);
            aqVar.read(this.a);
        } finally {
            this.b.e();
            this.a.B();
        }
    }

    public void a(aq aqVar, String str, String str2) throws aw {
        try {
            a(aqVar, str.getBytes(str2));
            this.a.B();
        } catch (UnsupportedEncodingException unused) {
            throw new aw("JVM DOES NOT SUPPORT ENCODING: " + str2);
        } catch (Throwable th) {
            this.a.B();
            throw th;
        }
    }

    public void a(aq aqVar, byte[] bArr, ax axVar, ax... axVarArr) throws aw {
        try {
            if (j(bArr, axVar, axVarArr) != null) {
                aqVar.read(this.a);
            }
            this.b.e();
            this.a.B();
        } catch (Exception e) {
            throw new aw(e);
        } catch (Throwable th) {
            this.b.e();
            this.a.B();
            throw th;
        }
    }

    public Boolean a(byte[] bArr, ax axVar, ax... axVarArr) throws aw {
        return (Boolean) a((byte) 2, bArr, axVar, axVarArr);
    }

    public Byte b(byte[] bArr, ax axVar, ax... axVarArr) throws aw {
        return (Byte) a((byte) 3, bArr, axVar, axVarArr);
    }

    public Double c(byte[] bArr, ax axVar, ax... axVarArr) throws aw {
        return (Double) a((byte) 4, bArr, axVar, axVarArr);
    }

    public Short d(byte[] bArr, ax axVar, ax... axVarArr) throws aw {
        return (Short) a((byte) 6, bArr, axVar, axVarArr);
    }

    public Integer e(byte[] bArr, ax axVar, ax... axVarArr) throws aw {
        return (Integer) a((byte) 8, bArr, axVar, axVarArr);
    }

    public Long f(byte[] bArr, ax axVar, ax... axVarArr) throws aw {
        return (Long) a((byte) 10, bArr, axVar, axVarArr);
    }

    public String g(byte[] bArr, ax axVar, ax... axVarArr) throws aw {
        return (String) a((byte) 11, bArr, axVar, axVarArr);
    }

    public ByteBuffer h(byte[] bArr, ax axVar, ax... axVarArr) throws aw {
        return (ByteBuffer) a((byte) 100, bArr, axVar, axVarArr);
    }

    public Short i(byte[] bArr, ax axVar, ax... axVarArr) throws aw {
        Short sh;
        try {
            if (j(bArr, axVar, axVarArr) != null) {
                this.a.j();
                sh = Short.valueOf(this.a.l().c);
            } else {
                sh = null;
            }
            this.b.e();
            this.a.B();
            return sh;
        } catch (Exception e) {
            throw new aw(e);
        } catch (Throwable th) {
            this.b.e();
            this.a.B();
            throw th;
        }
    }

    private Object a(byte b, byte[] bArr, ax axVar, ax... axVarArr) throws aw {
        Object obj;
        try {
            bk j = j(bArr, axVar, axVarArr);
            if (j != null) {
                if (b != 2) {
                    if (b != 3) {
                        if (b != 4) {
                            if (b != 6) {
                                if (b != 8) {
                                    if (b != 100) {
                                        if (b != 10) {
                                            if (b == 11) {
                                                if (j.b == 11) {
                                                    obj = this.a.z();
                                                    this.b.e();
                                                    this.a.B();
                                                    return obj;
                                                }
                                            }
                                        } else if (j.b == 10) {
                                            obj = Long.valueOf(this.a.x());
                                            this.b.e();
                                            this.a.B();
                                            return obj;
                                        }
                                    } else if (j.b == 11) {
                                        obj = this.a.A();
                                        this.b.e();
                                        this.a.B();
                                        return obj;
                                    }
                                } else if (j.b == 8) {
                                    obj = Integer.valueOf(this.a.w());
                                    this.b.e();
                                    this.a.B();
                                    return obj;
                                }
                            } else if (j.b == 6) {
                                obj = Short.valueOf(this.a.v());
                                this.b.e();
                                this.a.B();
                                return obj;
                            }
                        } else if (j.b == 4) {
                            obj = Double.valueOf(this.a.y());
                            this.b.e();
                            this.a.B();
                            return obj;
                        }
                    } else if (j.b == 3) {
                        obj = Byte.valueOf(this.a.u());
                        this.b.e();
                        this.a.B();
                        return obj;
                    }
                } else if (j.b == 2) {
                    obj = Boolean.valueOf(this.a.t());
                    this.b.e();
                    this.a.B();
                    return obj;
                }
            }
            obj = null;
            this.b.e();
            this.a.B();
            return obj;
        } catch (Exception e) {
            throw new aw(e);
        } catch (Throwable th) {
            this.b.e();
            this.a.B();
            throw th;
        }
    }

    private bk j(byte[] bArr, ax axVar, ax... axVarArr) throws aw {
        this.b.a(bArr);
        ax[] axVarArr2 = new ax[(axVarArr.length + 1)];
        int i = 0;
        axVarArr2[0] = axVar;
        int i2 = 0;
        while (i2 < axVarArr.length) {
            int i3 = i2 + 1;
            axVarArr2[i3] = axVarArr[i2];
            i2 = i3;
        }
        this.a.j();
        bk bkVar = null;
        while (i < axVarArr2.length) {
            bkVar = this.a.l();
            if (bkVar.b == 0 || bkVar.c > axVarArr2[i].a()) {
                return null;
            }
            if (bkVar.c != axVarArr2[i].a()) {
                bs.a(this.a, bkVar.b);
                this.a.m();
            } else {
                i++;
                if (i < axVarArr2.length) {
                    this.a.j();
                }
            }
        }
        return bkVar;
    }

    public void a(aq aqVar, String str) throws aw {
        a(aqVar, str.getBytes());
    }
}
