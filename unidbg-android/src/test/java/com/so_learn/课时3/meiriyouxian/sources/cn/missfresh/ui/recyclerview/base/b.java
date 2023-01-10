package cn.missfresh.ui.recyclerview.base;

import androidx.collection.SparseArrayCompat;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;

/* compiled from: ItemViewDelegateManager */
public class b<T> {
    SparseArrayCompat<a<T>> a;

    public int a() {
        AppMethodBeat.i(1041, false);
        int size = this.a.size();
        AppMethodBeat.o(1041);
        return size;
    }

    public int a(T t, int i) {
        AppMethodBeat.i(1058, false);
        for (int size = this.a.size() - 1; size >= 0; size--) {
            if (((a) this.a.valueAt(size)).a(t, i)) {
                int keyAt = this.a.keyAt(size);
                AppMethodBeat.o(1058);
                return keyAt;
            }
        }
        IllegalArgumentException illegalArgumentException = new IllegalArgumentException("No ItemViewDelegate added that matches position=" + i + " in data source");
        AppMethodBeat.o(1058);
        throw illegalArgumentException;
    }

    public void a(ViewHolder viewHolder, T t, int i) {
        AppMethodBeat.i(1063, false);
        int size = this.a.size();
        for (int i2 = 0; i2 < size; i2++) {
            a aVar = (a) this.a.valueAt(i2);
            if (aVar.a(t, i)) {
                aVar.a(viewHolder, t, i);
                AppMethodBeat.o(1063);
                return;
            }
        }
        IllegalArgumentException illegalArgumentException = new IllegalArgumentException("No ItemViewDelegateManager added that matches position=" + i + " in data source");
        AppMethodBeat.o(1063);
        throw illegalArgumentException;
    }

    public a a(int i) {
        AppMethodBeat.i(1065, false);
        a aVar = (a) this.a.get(i);
        AppMethodBeat.o(1065);
        return aVar;
    }
}
