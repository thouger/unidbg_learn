package com.a.a;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

/* compiled from: BaseSpringSystem */
public class b {
    private final Map<String, d> a = new HashMap();
    private final Set<d> b = new CopyOnWriteArraySet();
    private final g c;
    private final CopyOnWriteArraySet<i> d = new CopyOnWriteArraySet<>();
    private boolean e = true;

    public b(g gVar) {
        if (gVar != null) {
            this.c = gVar;
            this.c.a(this);
            return;
        }
        throw new IllegalArgumentException("springLooper is required");
    }

    public boolean a() {
        return this.e;
    }

    public d b() {
        d dVar = new d(this);
        a(dVar);
        return dVar;
    }

    /* access modifiers changed from: package-private */
    public void a(d dVar) {
        if (dVar == null) {
            throw new IllegalArgumentException("spring is required");
        } else if (!this.a.containsKey(dVar.a())) {
            this.a.put(dVar.a(), dVar);
        } else {
            throw new IllegalArgumentException("spring is already registered");
        }
    }

    /* access modifiers changed from: package-private */
    public void a(double d) {
        for (d dVar : this.b) {
            if (dVar.e()) {
                dVar.c(d / 1000.0d);
            } else {
                this.b.remove(dVar);
            }
        }
    }

    public void b(double d) {
        Iterator<i> it2 = this.d.iterator();
        while (it2.hasNext()) {
            it2.next().a(this);
        }
        a(d);
        if (this.b.isEmpty()) {
            this.e = true;
        }
        Iterator<i> it3 = this.d.iterator();
        while (it3.hasNext()) {
            it3.next().b(this);
        }
        if (this.e) {
            this.c.c();
        }
    }

    /* access modifiers changed from: package-private */
    public void a(String str) {
        d dVar = this.a.get(str);
        if (dVar != null) {
            this.b.add(dVar);
            if (a()) {
                this.e = false;
                this.c.b();
                return;
            }
            return;
        }
        throw new IllegalArgumentException("springId " + str + " does not reference a registered spring");
    }
}
