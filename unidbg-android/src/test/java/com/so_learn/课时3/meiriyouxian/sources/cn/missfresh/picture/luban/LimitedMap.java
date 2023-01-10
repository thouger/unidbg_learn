package cn.missfresh.picture.luban;

import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import java.util.LinkedHashMap;
import java.util.Map;

public class LimitedMap<K, V> extends LinkedHashMap<K, V> {
    private static final long serialVersionUID = 1;
    private int limit;

    public LimitedMap(int i) {
        this.limit = i;
    }

    /* access modifiers changed from: protected */
    @Override // java.util.LinkedHashMap
    public boolean removeEldestEntry(Map.Entry<K, V> entry) {
        boolean z = false;
        AppMethodBeat.i(18112, false);
        if (size() > this.limit) {
            z = true;
        }
        AppMethodBeat.o(18112);
        return z;
    }
}
