package com.google.common.base;

import com.umeng.message.proguard.l;
import java.io.Serializable;

final class FunctionalEquivalence<F, T> extends Equivalence<F> implements Serializable {
    private static final long serialVersionUID = 0;
    private final g<F, ? extends T> function;
    private final Equivalence<T> resultEquivalence;

    FunctionalEquivalence(g<F, ? extends T> gVar, Equivalence<T> equivalence) {
        this.function = (g) m.a(gVar);
        this.resultEquivalence = (Equivalence) m.a(equivalence);
    }

    /* access modifiers changed from: protected */
    @Override // com.google.common.base.Equivalence
    public boolean doEquivalent(F f, F f2) {
        return this.resultEquivalence.equivalent(this.function.apply(f), this.function.apply(f2));
    }

    /* access modifiers changed from: protected */
    @Override // com.google.common.base.Equivalence
    public int doHash(F f) {
        return this.resultEquivalence.hash(this.function.apply(f));
    }

    @Override // java.lang.Object
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof FunctionalEquivalence)) {
            return false;
        }
        FunctionalEquivalence functionalEquivalence = (FunctionalEquivalence) obj;
        return this.function.equals(functionalEquivalence.function) && this.resultEquivalence.equals(functionalEquivalence.resultEquivalence);
    }

    @Override // java.lang.Object
    public int hashCode() {
        return j.a(this.function, this.resultEquivalence);
    }

    @Override // java.lang.Object
    public String toString() {
        return this.resultEquivalence + ".onResultOf(" + this.function + l.t;
    }
}
