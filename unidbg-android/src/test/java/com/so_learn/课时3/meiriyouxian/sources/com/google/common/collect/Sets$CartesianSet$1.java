package com.google.common.collect;

import java.util.List;

class Sets$CartesianSet$1 extends ImmutableList<List<E>> {
    final /* synthetic */ ImmutableList val$axes;

    /* access modifiers changed from: package-private */
    @Override // com.google.common.collect.ImmutableCollection
    public boolean isPartialView() {
        return true;
    }

    Sets$CartesianSet$1(ImmutableList immutableList) {
        this.val$axes = immutableList;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public int size() {
        return this.val$axes.size();
    }

    @Override // java.util.List
    public List<E> get(int i) {
        return this.val$axes.get(i).asList();
    }
}
