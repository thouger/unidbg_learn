package com.google.common.collect;

/* access modifiers changed from: package-private */
public class CartesianList$1 extends ImmutableList<E> {
    final /* synthetic */ l this$0;
    final /* synthetic */ int val$index;

    /* access modifiers changed from: package-private */
    @Override // com.google.common.collect.ImmutableCollection
    public boolean isPartialView() {
        return true;
    }

    CartesianList$1(l lVar, int i) {
        this.this$0 = lVar;
        this.val$index = i;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public int size() {
        return this.this$0.a.size();
    }

    /* JADX WARN: Type inference failed for: r3v3, types: [E, java.lang.Object] */
    /* JADX WARNING: Unknown variable types count: 1 */
    @Override // java.util.List
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public E get(int r3) {
        /*
            r2 = this;
            int r0 = r2.size()
            com.google.common.base.m.a(r3, r0)
            com.google.common.collect.l r0 = r2.this$0
            int r1 = r2.val$index
            int r0 = com.google.common.collect.l.a(r0, r1, r3)
            com.google.common.collect.l r1 = r2.this$0
            com.google.common.collect.ImmutableList r1 = com.google.common.collect.l.a(r1)
            java.lang.Object r3 = r1.get(r3)
            java.util.List r3 = (java.util.List) r3
            java.lang.Object r3 = r3.get(r0)
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.common.collect.CartesianList$1.get(int):java.lang.Object");
    }
}
