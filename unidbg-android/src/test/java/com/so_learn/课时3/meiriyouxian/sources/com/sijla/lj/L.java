package com.sijla.lj;

import android.content.Context;

public class L {
    private static Class<?> a = Number.class;
    private static Class<?> b = Byte.class;
    private static Class<?> c = Short.class;
    private static Class<?> d = Integer.class;
    private static Class<?> e = Long.class;
    private static Class<?> f = Float.class;
    private static Class<?> g = Double.class;
    private long h;
    private Object i;

    private native synchronized int _LargError(long j, int i, String str);

    private native synchronized int _LcallMeta(long j, int i, String str);

    private native synchronized void _LcheckAny(long j, int i);

    private native synchronized int _LcheckInteger(long j, int i);

    private native synchronized double _LcheckNumber(long j, int i);

    private native synchronized void _LcheckStack(long j, int i, String str);

    private native synchronized String _LcheckString(long j, int i);

    private native synchronized void _LcheckType(long j, int i, int i2);

    private native synchronized int _LdoFile(long j, String str);

    private native synchronized int _LdoString(long j, String str);

    private native synchronized int _LgetMetaField(long j, int i, String str);

    private native synchronized void _LgetMetatable(long j, String str);

    private native synchronized String _Lgsub(long j, String str, String str2, String str3);

    private native synchronized int _LloadBuffer(long j, byte[] bArr, long j2, String str);

    private native synchronized int _LloadFile(long j, String str);

    private native synchronized int _LloadString(long j, String str);

    private native synchronized int _LnewMetatable(long j, String str);

    private native synchronized int _LoptInteger(long j, int i, int i2);

    private native synchronized double _LoptNumber(long j, int i, double d2);

    private native synchronized String _LoptString(long j, int i, String str);

    private native synchronized int _Lref(long j, int i);

    private native synchronized void _LunRef(long j, int i, int i2);

    private native synchronized void _Lwhere(long j, int i);

    private native synchronized void _call(long j, int i, int i2);

    private native synchronized int _checkStack(long j, int i);

    private native synchronized void _close(long j);

    private native synchronized int _compare(long j, int i, int i2, int i3);

    private native synchronized void _concat(long j, int i);

    private native synchronized void _copy(long j, int i, int i2);

    private native synchronized void _createTable(long j, int i, int i2);

    private native synchronized byte[] _dump(long j, int i);

    private native synchronized int _equal(long j, int i, int i2);

    private native synchronized int _error(long j);

    private native synchronized int _gc(long j, int i, int i2);

    private native synchronized int _getField(long j, int i, String str);

    private native synchronized int _getGlobal(long j, String str);

    private native synchronized int _getI(long j, int i, long j2);

    private native synchronized int _getMetaTable(long j, int i);

    private native synchronized Object _getObjectFromUserdata(long j, int i);

    private native synchronized int _getTable(long j, int i);

    private native synchronized int _getTop(long j);

    private native synchronized String _getUpValue(long j, int i, int i2);

    private native synchronized int _getUserValue(long j, int i);

    public static native synchronized String _iilc();

    private native synchronized void _insert(long j, int i);

    private native synchronized int _isBoolean(long j, int i);

    private native synchronized int _isCFunction(long j, int i);

    private native synchronized int _isFunction(long j, int i);

    private native synchronized int _isInteger(long j, int i);

    private native synchronized boolean _isJavaFunction(long j, int i);

    private native synchronized int _isNil(long j, int i);

    private native synchronized int _isNone(long j, int i);

    private native synchronized int _isNoneOrNil(long j, int i);

    private native synchronized int _isNumber(long j, int i);

    private native synchronized boolean _isObject(long j, int i);

    private native synchronized int _isString(long j, int i);

    private native synchronized int _isTable(long j, int i);

    private native synchronized int _isThread(long j, int i);

    private native synchronized int _isUserdata(long j, int i);

    private native synchronized int _isYieldable(long j);

    private native synchronized int _lessThan(long j, int i, int i2);

    private native synchronized void _newTable(long j);

    private native synchronized long _newstate();

    private native synchronized long _newthread(long j);

    private native synchronized int _next(long j, int i);

    private native synchronized int _objlen(long j, int i);

    private native synchronized void _olj(long j);

    private native synchronized void _openBase(long j);

    private native synchronized void _openDebug(long j);

    private native synchronized void _openIo(long j);

    private native synchronized void _openLibs(long j);

    private native synchronized void _openMath(long j);

    private native synchronized void _openOs(long j);

    private native synchronized void _openPackage(long j);

    private native synchronized void _openString(long j);

    private native synchronized void _openTable(long j);

    private native synchronized int _pcall(long j, int i, int i2, int i3);

    private native synchronized void _pop(long j, int i);

    private native synchronized void _pushBoolean(long j, int i);

    private native synchronized void _pushGlobalTable(long j);

    private native synchronized void _pushInteger(long j, long j2);

    private native synchronized void _pushJavaFunction(long j, JF jf);

    private native synchronized void _pushJavaObject(long j, Object obj);

    private native synchronized void _pushNil(long j);

    private native synchronized void _pushNumber(long j, double d2);

    private native synchronized void _pushString(long j, String str);

    private native synchronized void _pushString(long j, byte[] bArr, int i);

    private native synchronized void _pushValue(long j, int i);

    private native synchronized int _rawGet(long j, int i);

    private native synchronized int _rawGetI(long j, int i, long j2);

    private native synchronized void _rawSet(long j, int i);

    private native synchronized void _rawSetI(long j, int i, long j2);

    private native synchronized int _rawequal(long j, int i, int i2);

    private native synchronized int _rawlen(long j, int i);

    private native synchronized void _remove(long j, int i);

    private native synchronized void _replace(long j, int i);

    private native synchronized int _resume(long j, long j2, int i);

    private native synchronized void _rotate(long j, int i, int i2);

    public static native synchronized String _s();

    public static native synchronized String _s1();

    public static native synchronized String _s2();

    public static native synchronized String _s3();

    public static native synchronized String _s5();

    private native synchronized void _setField(long j, int i, String str);

    private native synchronized void _setGlobal(long j, String str);

    private native synchronized void _setI(long j, int i, long j2);

    private native synchronized int _setMetaTable(long j, int i);

    private native synchronized void _setTable(long j, int i);

    private native synchronized void _setTop(long j, int i);

    private native synchronized String _setUpValue(long j, int i, int i2);

    private native synchronized void _setUserValue(long j, int i);

    public static native synchronized String _sot();

    public static native synchronized long _sov();

    private native synchronized int _status(long j);

    private native synchronized int _strlen(long j, int i);

    private native synchronized int _toBoolean(long j, int i);

    private native synchronized long _toInteger(long j, int i);

    private native synchronized double _toNumber(long j, int i);

    private native synchronized String _toString(long j, int i);

    private native synchronized long _toThread(long j, int i);

    private native synchronized int _type(long j, int i);

    private native synchronized String _typeName(long j, int i);

    private native synchronized void _xmove(long j, long j2, int i);

    private native synchronized int _yield(long j, int i);

    public static native byte[] asi(Context context);

    public static native byte[] ds(int i, byte[] bArr, byte[] bArr2, byte[] bArr3);

    public static native byte[] m5(byte[] bArr);

    public static native synchronized String ms();

    public static native String r();

    static {
        try {
            System.loadLibrary("Qt");
        } catch (Throwable unused) {
        }
    }

    protected L() {
        this.h = _newstate();
    }

    protected L(long j) {
        this.h = j;
        f.a(this);
    }

    public long a() {
        return this.h;
    }

    public void a(Object obj) {
        this.i = obj;
    }

    public Object b() {
        return this.i;
    }

    public int c() {
        return _getTop(this.h);
    }

    public void a(int i) {
        _setTop(this.h, i);
    }

    public void b(int i) {
        _pushValue(this.h, i);
    }

    public void c(int i) {
        _remove(this.h, i);
    }

    public boolean d(int i) {
        return _isNumber(this.h, i) != 0;
    }

    public boolean e(int i) {
        return _isInteger(this.h, i) != 0;
    }

    public boolean f(int i) {
        return _isString(this.h, i) != 0;
    }

    public boolean g(int i) {
        return _isFunction(this.h, i) != 0;
    }

    public boolean h(int i) {
        return _isUserdata(this.h, i) != 0;
    }

    public boolean i(int i) {
        return _isTable(this.h, i) != 0;
    }

    public boolean j(int i) {
        return _isBoolean(this.h, i) != 0;
    }

    public boolean k(int i) {
        return _isNil(this.h, i) != 0;
    }

    public int l(int i) {
        return _type(this.h, i);
    }

    public String m(int i) {
        return _typeName(this.h, i);
    }

    public double n(int i) {
        return _toNumber(this.h, i);
    }

    public long o(int i) {
        return _toInteger(this.h, i);
    }

    public boolean p(int i) {
        return _toBoolean(this.h, i) != 0;
    }

    public String q(int i) {
        return _toString(this.h, i);
    }

    public int r(int i) {
        return _objlen(this.h, i);
    }

    public void d() {
        _pushNil(this.h);
    }

    public void a(double d2) {
        _pushNumber(this.h, d2);
    }

    public void a(long j) {
        _pushInteger(this.h, j);
    }

    public void a(String str) {
        if (str == null) {
            _pushNil(this.h);
        } else {
            _pushString(this.h, str);
        }
    }

    public void a(boolean z) {
        _pushBoolean(this.h, z ? 1 : 0);
    }

    public int s(int i) {
        return _getTable(this.h, i);
    }

    public int t(int i) {
        return _rawGet(this.h, i);
    }

    public int a(int i, long j) {
        return _rawGetI(this.h, i, j);
    }

    public void e() {
        _newTable(this.h);
    }

    public void u(int i) {
        _setTable(this.h, i);
    }

    public void a(int i, String str) {
        _setField(this.h, i, str);
    }

    public void b(int i, long j) {
        _rawSetI(this.h, i, j);
    }

    public int a(int i, int i2, int i3) {
        return _pcall(this.h, i, i2, i3);
    }

    public int a(int i, int i2) {
        return _gc(this.h, i, i2);
    }

    public int v(int i) {
        return _next(this.h, i);
    }

    public int w(int i) {
        return _Lref(this.h, i);
    }

    public void b(int i, int i2) {
        _LunRef(this.h, i, i2);
    }

    public int b(String str) {
        return _LloadFile(this.h, str);
    }

    public String f() {
        StringBuilder sb = new StringBuilder();
        int c2 = c();
        for (int i = 1; i <= c2; i++) {
            int l = l(i);
            sb.append(i);
            sb.append(": ");
            sb.append(m(l));
            if (l == 3) {
                sb.append(" = ");
                sb.append(n(i));
            } else if (l == 4) {
                sb.append(" = '");
                sb.append(q(i));
                sb.append("'");
            }
            sb.append(", ");
        }
        return sb.toString();
    }

    public void x(int i) {
        _pop(this.h, i);
    }

    public synchronized void g() {
        _pushGlobalTable(this.h);
    }

    public synchronized int c(String str) {
        return _getGlobal(this.h, str);
    }

    public synchronized void d(String str) {
        _setGlobal(this.h, str);
    }

    public void h() {
        _openLibs(this.h);
        _olj(this.h);
        i();
    }

    public Object y(int i) {
        return _getObjectFromUserdata(this.h, i);
    }

    public boolean z(int i) {
        return _isObject(this.h, i);
    }

    public void b(Object obj) {
        _pushJavaObject(this.h, obj);
    }

    public void a(JF jf) {
        _pushJavaFunction(this.h, jf);
    }

    public boolean A(int i) {
        return _isJavaFunction(this.h, i);
    }

    public void c(Object obj) {
        if (obj == null) {
            d();
        } else if (obj instanceof Boolean) {
            a(((Boolean) obj).booleanValue());
        } else if (obj instanceof Long) {
            a(((Long) obj).longValue());
        } else if (obj instanceof Integer) {
            a((long) ((Integer) obj).intValue());
        } else if (obj instanceof Short) {
            a((long) ((Short) obj).shortValue());
        } else if (obj instanceof Character) {
            a((long) ((Character) obj).charValue());
        } else if (obj instanceof Byte) {
            a((long) ((Byte) obj).byteValue());
        } else if (obj instanceof Float) {
            a((double) ((Float) obj).floatValue());
        } else if (obj instanceof Double) {
            a(((Double) obj).doubleValue());
        } else if (obj instanceof String) {
            a((String) obj);
        } else if (obj instanceof JF) {
            a((JF) obj);
        } else if (obj instanceof c) {
            c cVar = (c) obj;
            if (cVar.b() == this) {
                cVar.c();
            } else {
                b(cVar);
            }
        } else {
            b(obj);
        }
    }

    public synchronized Object B(int i) {
        Object obj;
        obj = null;
        if (j(i)) {
            obj = Boolean.valueOf(p(i));
        } else if (l(i) == 4) {
            obj = q(i);
        } else if (g(i)) {
            obj = C(i);
        } else if (i(i)) {
            obj = C(i);
        } else if (l(i) == 3) {
            if (e(i)) {
                obj = Long.valueOf(o(i));
            } else {
                obj = Double.valueOf(n(i));
            }
        } else if (!h(i)) {
            k(i);
        } else if (z(i)) {
            obj = y(i);
        } else {
            obj = C(i);
        }
        return obj;
    }

    public c e(String str) {
        g();
        a(str);
        t(-2);
        c C = C(-1);
        x(2);
        return C;
    }

    public c a(c cVar, String str) {
        return new c(cVar, str);
    }

    public c C(int i) {
        if (g(i)) {
            return new b(this, i);
        }
        if (i(i)) {
            return new g(this, i);
        }
        return new c(this, i);
    }

    public static Number a(Double d2, Class<?> cls) {
        if (cls.isPrimitive()) {
            if (cls == Integer.TYPE) {
                return Integer.valueOf(d2.intValue());
            }
            if (cls == Long.TYPE) {
                return Long.valueOf(d2.longValue());
            }
            if (cls == Float.TYPE) {
                return Float.valueOf(d2.floatValue());
            }
            if (cls == Double.TYPE) {
                return Double.valueOf(d2.doubleValue());
            }
            if (cls == Byte.TYPE) {
                return Byte.valueOf(d2.byteValue());
            }
            if (cls == Short.TYPE) {
                return Short.valueOf(d2.shortValue());
            }
            return null;
        } else if (!cls.isAssignableFrom(a)) {
            return null;
        } else {
            if (cls.isAssignableFrom(d)) {
                return new Integer(d2.intValue());
            }
            if (cls.isAssignableFrom(e)) {
                return new Long(d2.longValue());
            }
            if (cls.isAssignableFrom(f)) {
                return new Float(d2.floatValue());
            }
            if (cls.isAssignableFrom(g)) {
                return d2;
            }
            if (cls.isAssignableFrom(b)) {
                return new Byte(d2.byteValue());
            }
            if (cls.isAssignableFrom(c)) {
                return new Short(d2.shortValue());
            }
            return null;
        }
    }

    public static Number a(Long l, Class<?> cls) {
        if (cls.isPrimitive()) {
            if (cls == Integer.TYPE) {
                return Integer.valueOf(l.intValue());
            }
            if (cls == Long.TYPE) {
                return Long.valueOf(l.longValue());
            }
            if (cls == Float.TYPE) {
                return Float.valueOf(l.floatValue());
            }
            if (cls == Double.TYPE) {
                return Double.valueOf(l.doubleValue());
            }
            if (cls == Byte.TYPE) {
                return Byte.valueOf(l.byteValue());
            }
            if (cls == Short.TYPE) {
                return Short.valueOf(l.shortValue());
            }
            return null;
        } else if (!cls.isAssignableFrom(a)) {
            return null;
        } else {
            if (cls.isAssignableFrom(d)) {
                return new Integer(l.intValue());
            }
            if (cls.isAssignableFrom(e)) {
                return new Long(l.longValue());
            }
            if (cls.isAssignableFrom(f)) {
                return new Float(l.floatValue());
            }
            if (cls.isAssignableFrom(g)) {
                return l;
            }
            if (cls.isAssignableFrom(b)) {
                return new Byte(l.byteValue());
            }
            if (cls.isAssignableFrom(c)) {
                return new Short(l.shortValue());
            }
            return null;
        }
    }

    public void i() {
        b(Boolean.TYPE);
        d("boolean");
        b(Byte.TYPE);
        d("byte");
        b(Character.TYPE);
        d("char");
        b(Short.TYPE);
        d("short");
        b(Integer.TYPE);
        d("int");
        b(Long.TYPE);
        d("long");
        b(Float.TYPE);
        d("float");
        b(Double.TYPE);
        d("double");
    }
}
