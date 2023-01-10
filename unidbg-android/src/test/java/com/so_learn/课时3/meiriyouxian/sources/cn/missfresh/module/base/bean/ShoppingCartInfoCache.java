package cn.missfresh.module.base.bean;

import cn.missfresh.sherlock.trace.core.AppMethodBeat;

public class ShoppingCartInfoCache {
    public boolean isActive;
    public int totalQuantity;

    public ShoppingCartInfoCache() {
        this.isActive = true;
    }

    public ShoppingCartInfoCache(int i, boolean z) {
        this.totalQuantity = i;
        this.isActive = z;
    }

    public String toString() {
        AppMethodBeat.i(7660, false);
        String str = "ShoppingCartInfoCache{totalQuantity=" + this.totalQuantity + ", isActive=" + this.isActive + '}';
        AppMethodBeat.o(7660);
        return str;
    }
}
