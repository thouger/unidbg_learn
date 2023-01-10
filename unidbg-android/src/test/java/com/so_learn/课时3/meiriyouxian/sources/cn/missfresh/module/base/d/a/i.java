package cn.missfresh.module.base.d.a;

import cn.missfresh.module.base.datastatistics.a.a.a;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import java.util.LinkedList;
import java.util.List;

/* compiled from: SearchResultScmStrategy */
public class i extends c {
    List<String> a = new LinkedList();
    private boolean b = false;

    public i() {
        AppMethodBeat.i(19078, false);
        AppMethodBeat.o(19078);
    }

    @Override // cn.missfresh.module.base.d.a.c, cn.missfresh.module.base.datastatistics.a.b.a
    public boolean a(a aVar) {
        AppMethodBeat.i(19080, false);
        if ("search_result".equals(aVar.b)) {
            String str = aVar.a;
            char c = '\uffff';
            int hashCode = str.hashCode();
            if (hashCode != -1671235145) {
                if (hashCode != 883937877) {
                    if (hashCode == 2022256952 && str.equals("click_product")) {
                        c = 2;
                    }
                } else if (str.equals("page_view")) {
                    c = 0;
                }
            } else if (str.equals("exposure_product")) {
                c = 1;
            }
            if (c == 0 || c == 1 || c == 2) {
                AppMethodBeat.o(19080);
                return true;
            }
        }
        AppMethodBeat.o(19080);
        return false;
    }

    /* JADX WARNING: Removed duplicated region for block: B:17:0x0042  */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x0095  */
    @Override // cn.missfresh.module.base.d.a.c, cn.missfresh.module.base.datastatistics.a.b.a
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void b(cn.missfresh.module.base.datastatistics.a.a.a r9) {
        /*
            r8 = this;
            r0 = 19083(0x4a8b, float:2.6741E-41)
            r1 = 0
            cn.missfresh.sherlock.trace.core.AppMethodBeat.i(r0, r1)
            java.lang.String r2 = r9.a
            int r3 = r2.hashCode()
            r4 = -1671235145(0xffffffff9c62f9b7, float:-7.5099876E-22)
            r5 = 2
            java.lang.String r6 = "exposure_product"
            r7 = 1
            if (r3 == r4) goto L_0x0037
            r4 = 883937877(0x34afd255, float:3.2749327E-7)
            if (r3 == r4) goto L_0x002c
            r4 = 2022256952(0x78893138, float:2.2260717E34)
            if (r3 == r4) goto L_0x0021
            goto L_0x003f
        L_0x0021:
            java.lang.String r3 = "click_product"
            boolean r2 = r2.equals(r3)
            if (r2 == 0) goto L_0x003f
            r2 = r5
            goto L_0x0040
        L_0x002c:
            java.lang.String r3 = "page_view"
            boolean r2 = r2.equals(r3)
            if (r2 == 0) goto L_0x003f
            r2 = r1
            goto L_0x0040
        L_0x0037:
            boolean r2 = r2.equals(r6)
            if (r2 == 0) goto L_0x003f
            r2 = r7
            goto L_0x0040
        L_0x003f:
            r2 = -1
        L_0x0040:
            if (r2 == 0) goto L_0x0095
            java.lang.String r3 = "result"
            if (r2 == r7) goto L_0x0069
            if (r2 == r5) goto L_0x004a
            goto L_0x0097
        L_0x004a:
            java.util.Map r1 = r9.f
            if (r1 == 0) goto L_0x0066
            java.util.Map r1 = r9.f
            java.lang.String r2 = "module"
            boolean r1 = r1.containsKey(r2)
            if (r1 == 0) goto L_0x0066
            java.util.Map r1 = r9.f
            java.lang.Object r1 = r1.get(r2)
            java.lang.String r1 = java.lang.String.valueOf(r1)
            r9.c = r1
            goto L_0x0097
        L_0x0066:
            r9.c = r3
            goto L_0x0097
        L_0x0069:
            r9.c = r3
            boolean r2 = r8.b
            if (r2 == 0) goto L_0x008f
            r8.b = r1
            java.util.List<java.lang.String> r1 = r8.a
            r1.add(r6)
            java.util.Map r1 = r9.f
            r2 = 0
            if (r1 == 0) goto L_0x0085
            java.util.Map r1 = r9.f
            java.lang.String r3 = "search_request_id"
            java.lang.Object r1 = r1.get(r3)
            goto L_0x0086
        L_0x0085:
            r1 = r2
        L_0x0086:
            if (r1 == 0) goto L_0x008c
            java.lang.String r2 = java.lang.String.valueOf(r1)
        L_0x008c:
            r9.e = r2
            goto L_0x0097
        L_0x008f:
            java.util.List<java.lang.String> r1 = r8.a
            r1.remove(r6)
            goto L_0x0097
        L_0x0095:
            r8.b = r7
        L_0x0097:
            super.b(r9)
            cn.missfresh.sherlock.trace.core.AppMethodBeat.o(r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.missfresh.module.base.d.a.i.b(cn.missfresh.module.base.datastatistics.a.a.a):void");
    }

    @Override // cn.missfresh.module.base.d.a.c
    public List<String> d() {
        return this.a;
    }
}
