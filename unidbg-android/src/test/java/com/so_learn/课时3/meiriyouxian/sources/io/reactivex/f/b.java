package io.reactivex.f;

import io.reactivex.internal.functions.a;
import java.util.concurrent.TimeUnit;

/* compiled from: Timed */
public final class b<T> {
    final T a;
    final long b;
    final TimeUnit c;

    public b(T t, long j, TimeUnit timeUnit) {
        this.a = t;
        this.b = j;
        this.c = (TimeUnit) a.a(timeUnit, "unit is null");
    }

    public T a() {
        return this.a;
    }

    public long b() {
        return this.b;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof b)) {
            return false;
        }
        b bVar = (b) obj;
        if (!a.a((Object) this.a, (Object) bVar.a) || this.b != bVar.b || !a.a(this.c, bVar.c)) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        T t = this.a;
        int hashCode = t != null ? t.hashCode() : 0;
        long j = this.b;
        return (((hashCode * 31) + ((int) (j ^ (j >>> 31)))) * 31) + this.c.hashCode();
    }

    public String toString() {
        return "Timed[time=" + this.b + ", unit=" + this.c + ", value=" + ((Object) this.a) + "]";
    }
}
