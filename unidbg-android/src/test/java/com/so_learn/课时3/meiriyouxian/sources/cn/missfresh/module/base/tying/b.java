package cn.missfresh.module.base.tying;

import cn.missfresh.module.base.tying.bean.TyingProductRecord;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import cn.missfresh.utils.a.d;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/* compiled from: TyingProductCatch */
public class b {
    public HashMap<String, List<TyingProductRecord>> a = new HashMap<>();

    public b() {
        AppMethodBeat.i(22977, false);
        AppMethodBeat.o(22977);
    }

    public boolean a(String str, String str2, int i, int i2) {
        int i3;
        boolean z = false;
        AppMethodBeat.i(22978, false);
        if (cn.missfresh.utils.b.a(str) || cn.missfresh.utils.b.a(str2)) {
            AppMethodBeat.o(22978);
            return false;
        }
        boolean z2 = true;
        if (i2 == 1) {
            i++;
        } else if (i2 == 0) {
            i += 2;
        }
        List<TyingProductRecord> list = this.a.get(str);
        if (cn.missfresh.utils.b.a(list)) {
            if (list == null) {
                list = new ArrayList<>();
            }
            list.add(new TyingProductRecord(str, str2, i));
            this.a.put(str, list);
            AppMethodBeat.o(22978);
            return true;
        }
        int size = list.size() - 1;
        int i4 = -1;
        while (true) {
            if (size < 0) {
                size = 0;
                i3 = -1;
                break;
            }
            TyingProductRecord tyingProductRecord = list.get(size);
            if (tyingProductRecord.getPosition() > i) {
                i4 = tyingProductRecord.getPosition();
                size--;
            } else if (tyingProductRecord.getPosition() < i) {
                i3 = tyingProductRecord.getPosition();
            } else {
                AppMethodBeat.o(22978);
                return false;
            }
        }
        TyingProductRecord tyingProductRecord2 = new TyingProductRecord(str, str, i);
        if (i4 == -1) {
            if (list.get(list.size() - 1).getPosition() + i2 < i) {
                z = true;
            }
            if (z) {
                list.add(tyingProductRecord2);
            }
            AppMethodBeat.o(22978);
            return z;
        } else if (i3 == -1) {
            if (list.get(0).getPosition() - i < i2) {
                z2 = false;
            }
            if (z2) {
                list.add(0, tyingProductRecord2);
            }
            AppMethodBeat.o(22978);
            return z2;
        } else {
            if (i3 + i2 < i && i4 - i >= i2) {
                z = true;
            }
            if (z) {
                list.add(size + 1, tyingProductRecord2);
            }
            AppMethodBeat.o(22978);
            return z;
        }
    }

    public void a(String str, String str2, int i) {
        AppMethodBeat.i(22979, false);
        List<TyingProductRecord> list = this.a.get(str);
        if (cn.missfresh.utils.b.a(list)) {
            AppMethodBeat.o(22979);
            return;
        }
        int i2 = -1;
        for (TyingProductRecord tyingProductRecord : list) {
            i2++;
            if (tyingProductRecord != null && tyingProductRecord.getChannel().equals(str) && tyingProductRecord.getSku().equals(str2) && tyingProductRecord.getPosition() == i) {
                break;
            }
        }
        if (i2 != -1 && i2 < list.size()) {
            list.remove(i2);
        }
        AppMethodBeat.o(22979);
    }

    public void a(String str) {
        AppMethodBeat.i(22980, false);
        d.d("lhj", "clean channel:" + str);
        this.a.put(str, null);
        AppMethodBeat.o(22980);
    }

    public void a() {
        AppMethodBeat.i(22981, false);
        this.a.clear();
        AppMethodBeat.o(22981);
    }
}
