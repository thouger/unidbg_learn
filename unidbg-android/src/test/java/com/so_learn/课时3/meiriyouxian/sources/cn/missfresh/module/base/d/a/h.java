package cn.missfresh.module.base.d.a;

import android.media.midi.MidiDeviceInfo;
import cn.missfresh.module.base.datastatistics.a.a.a;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;

/* compiled from: ProductScmStrategy */
public class h extends c {
    @Override // cn.missfresh.module.base.d.a.c, cn.missfresh.module.base.datastatistics.a.b.a
    public boolean a(a aVar) {
        AppMethodBeat.i(19071, false);
        if (MidiDeviceInfo.PROPERTY_PRODUCT.equals(aVar.b)) {
            String str = aVar.a;
            char c = '\uffff';
            int hashCode = str.hashCode();
            if (hashCode != -1236338690) {
                if (hashCode != -777411721) {
                    if (hashCode == 883937877 && str.equals("page_view")) {
                        c = 0;
                    }
                } else if (str.equals("click_cart")) {
                    c = 2;
                }
            } else if (str.equals("add_cart")) {
                c = 1;
            }
            if (c == 0 || c == 1 || c == 2) {
                AppMethodBeat.o(19071);
                return true;
            }
        }
        AppMethodBeat.o(19071);
        return false;
    }
}
