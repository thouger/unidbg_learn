package com.alipay.sdk.widget;

import java.util.Iterator;
import java.util.Stack;

class f {
    private Stack<e> a = new Stack<>();

    f() {
    }

    /* access modifiers changed from: package-private */
    public e a() {
        return this.a.pop();
    }

    /* access modifiers changed from: package-private */
    public void a(e eVar) {
        this.a.push(eVar);
    }

    /* access modifiers changed from: package-private */
    public boolean b() {
        return this.a.isEmpty();
    }

    /* access modifiers changed from: package-private */
    public void c() {
        if (!b()) {
            Iterator<e> it2 = this.a.iterator();
            while (it2.hasNext()) {
                it2.next().a();
            }
            this.a.clear();
        }
    }
}
