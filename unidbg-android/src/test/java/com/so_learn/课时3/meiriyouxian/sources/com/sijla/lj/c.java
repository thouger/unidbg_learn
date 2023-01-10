package com.sijla.lj;

import com.sijla.common.e;
import com.xiaomi.mipush.sdk.Constants;
import java.lang.reflect.Proxy;
import java.util.StringTokenizer;

public class c {
    public int a;
    protected L b;

    public int a() {
        return this.a;
    }

    protected c(c cVar, String str) {
        synchronized (cVar.b()) {
            this.b = cVar.b();
            if (!cVar.k()) {
                if (!cVar.l()) {
                    throw new LException("Object parent should be a table or userdata .");
                }
            }
            cVar.c();
            this.b.a(str);
            this.b.s(-2);
            this.b.c(-2);
            a(-1);
            this.b.x(1);
        }
    }

    protected c(L l, int i) {
        synchronized (l) {
            this.b = l;
            a(i);
        }
    }

    public L b() {
        return this.b;
    }

    /* access modifiers changed from: protected */
    public void a(int i) {
        synchronized (this.b) {
            this.b.b(i);
            this.a = this.b.w(-1001000);
            e.d.add(this);
        }
    }

    public void c() {
        this.b.a(-1001000, (long) this.a);
    }

    public boolean d() {
        boolean k;
        synchronized (this.b) {
            c();
            k = this.b.k(-1);
            this.b.x(1);
        }
        return k;
    }

    public boolean e() {
        boolean j;
        synchronized (this.b) {
            c();
            j = this.b.j(-1);
            this.b.x(1);
        }
        return j;
    }

    public boolean f() {
        boolean d;
        synchronized (this.b) {
            c();
            d = this.b.d(-1);
            this.b.x(1);
        }
        return d;
    }

    public boolean g() {
        boolean f;
        synchronized (this.b) {
            c();
            f = this.b.f(-1);
            this.b.x(1);
        }
        return f;
    }

    public boolean h() {
        boolean g;
        synchronized (this.b) {
            c();
            g = this.b.g(-1);
            this.b.x(1);
        }
        return g;
    }

    public boolean i() {
        boolean z;
        synchronized (this.b) {
            c();
            z = this.b.z(-1);
            this.b.x(1);
        }
        return z;
    }

    public boolean j() {
        boolean A;
        synchronized (this.b) {
            c();
            A = this.b.A(-1);
            this.b.x(1);
        }
        return A;
    }

    public boolean k() {
        boolean i;
        synchronized (this.b) {
            c();
            i = this.b.i(-1);
            this.b.x(1);
        }
        return i;
    }

    public boolean l() {
        boolean h;
        synchronized (this.b) {
            c();
            h = this.b.h(-1);
            this.b.x(1);
        }
        return h;
    }

    public boolean m() {
        boolean p;
        synchronized (this.b) {
            c();
            p = this.b.p(-1);
            this.b.x(1);
        }
        return p;
    }

    public double n() {
        double n;
        synchronized (this.b) {
            c();
            n = this.b.n(-1);
            this.b.x(1);
        }
        return n;
    }

    public String o() {
        String q;
        synchronized (this.b) {
            c();
            q = this.b.q(-1);
            this.b.x(1);
        }
        return q;
    }

    public Object p() {
        Object y;
        synchronized (this.b) {
            c();
            y = this.b.y(-1);
            this.b.x(1);
        }
        return y;
    }

    public c b(String str) {
        return this.b.a(this, str);
    }

    public Object[] a(Object[] objArr, int i) {
        int i2;
        Object[] objArr2;
        String str;
        String str2;
        synchronized (this.b) {
            if (!h() && !k()) {
                if (!l()) {
                    throw new LException("Invalid object. Not a function, table or userdata .");
                }
            }
            int c = this.b.c();
            c();
            if (objArr != null) {
                for (Object obj : objArr) {
                    this.b.c(obj);
                }
            } else {
                i2 = 0;
            }
            int a = this.b.a(i2, i, 0);
            if (a != 0) {
                if (this.b.f(-1)) {
                    str = this.b.q(-1);
                    this.b.x(1);
                } else {
                    str = "";
                }
                if (a == 2) {
                    str2 = "Runtime error. " + str;
                } else if (a == 4) {
                    str2 = "Memory allocation error. " + str;
                } else if (a == 6) {
                    str2 = "Error while running the error handler function. " + str;
                } else {
                    str2 = "Lua Error code " + a + ". " + str;
                }
                throw new LException(str2);
            }
            if (i == -1) {
                i = this.b.c() - c;
            }
            if (this.b.c() - c >= i) {
                objArr2 = new Object[i];
                while (i > 0) {
                    objArr2[i - 1] = this.b.B(-1);
                    this.b.x(1);
                    i--;
                }
            } else {
                throw new LException("Invalid Number of Results .");
            }
        }
        return objArr2;
    }

    public Object b(Object... objArr) {
        return a(objArr, 1)[0];
    }

    public String toString() {
        synchronized (this.b) {
            try {
                if (d()) {
                    return "nil";
                }
                if (e()) {
                    return String.valueOf(m());
                } else if (f()) {
                    return String.valueOf(n());
                } else if (g()) {
                    return o();
                } else if (h()) {
                    return "Lua Function";
                } else {
                    if (i()) {
                        return p().toString();
                    } else if (l()) {
                        return "Userdata";
                    } else {
                        if (k()) {
                            return "Lua Table";
                        }
                        if (j()) {
                            return "Java Function";
                        }
                        return null;
                    }
                }
            } catch (LException unused) {
                return null;
            }
        }
    }

    public Object c(String str) {
        Object newProxyInstance;
        synchronized (this.b) {
            if (k()) {
                StringTokenizer stringTokenizer = new StringTokenizer(str, Constants.ACCEPT_TIME_SEPARATOR_SP);
                Class[] clsArr = new Class[stringTokenizer.countTokens()];
                int i = 0;
                while (stringTokenizer.hasMoreTokens()) {
                    clsArr[i] = Class.forName(stringTokenizer.nextToken());
                    i++;
                }
                newProxyInstance = Proxy.newProxyInstance(getClass().getClassLoader(), clsArr, new d(this));
            } else {
                throw new LException("Invalid Object. Must be Table.");
            }
        }
        return newProxyInstance;
    }

    public Object a(Class cls) {
        Object newProxyInstance;
        synchronized (this.b) {
            if (k()) {
                newProxyInstance = Proxy.newProxyInstance(cls.getClassLoader(), new Class[]{cls}, new d(this));
            } else {
                throw new LException("Invalid Object. Must be Table.");
            }
        }
        return newProxyInstance;
    }
}
