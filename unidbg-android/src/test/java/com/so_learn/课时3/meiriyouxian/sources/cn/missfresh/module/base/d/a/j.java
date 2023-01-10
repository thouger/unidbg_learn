package cn.missfresh.module.base.d.a;

import cn.missfresh.module.base.datastatistics.a.a.a;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import java.util.LinkedList;
import java.util.List;

/* compiled from: SearchScmStrategy */
public class j extends c {
    List<String> a = new LinkedList();

    public j() {
        AppMethodBeat.i(19088, false);
        this.a.add("click_search");
        this.a.add("click_searchHot");
        this.a.add("click_searchHistory");
        this.a.add("click_searchBox");
        AppMethodBeat.o(19088);
    }

    @Override // cn.missfresh.module.base.d.a.c, cn.missfresh.module.base.datastatistics.a.b.a
    public boolean a(a aVar) {
        AppMethodBeat.i(19090, false);
        if ("search".equals(aVar.b)) {
            String str = aVar.a;
            char c = '\uffff';
            switch (str.hashCode()) {
                case -1388367019:
                    if (str.equals("click_searchHistory")) {
                        c = 3;
                        break;
                    }
                    break;
                case 598758668:
                    if (str.equals("click_searchBox")) {
                        c = 4;
                        break;
                    }
                    break;
                case 598764430:
                    if (str.equals("click_searchHot")) {
                        c = 2;
                        break;
                    }
                    break;
                case 692900927:
                    if (str.equals("click_search")) {
                        c = 1;
                        break;
                    }
                    break;
                case 883937877:
                    if (str.equals("page_view")) {
                        c = 0;
                        break;
                    }
                    break;
            }
            if (c == 0 || c == 1 || c == 2 || c == 3 || c == 4) {
                AppMethodBeat.o(19090);
                return true;
            }
        }
        AppMethodBeat.o(19090);
        return false;
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARNING: Code restructure failed: missing block: B:3:0x001a, code lost:
        if (r2.equals("click_search") != false) goto L_0x003f;
     */
    @Override // cn.missfresh.module.base.d.a.c, cn.missfresh.module.base.datastatistics.a.b.a
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void b(cn.missfresh.module.base.datastatistics.a.a.a r8) {
        /*
            r7 = this;
            r0 = 0
            r1 = 19092(0x4a94, float:2.6754E-41)
            cn.missfresh.sherlock.trace.core.AppMethodBeat.i(r1, r0)
            java.lang.String r2 = r8.a
            int r3 = r2.hashCode()
            r4 = 3
            r5 = 2
            r6 = 1
            switch(r3) {
                case -1388367019: goto L_0x0033;
                case 598758668: goto L_0x0028;
                case 598764430: goto L_0x001d;
                case 692900927: goto L_0x0013;
                default: goto L_0x0012;
            }
        L_0x0012:
            goto L_0x003e
        L_0x0013:
            java.lang.String r3 = "click_search"
            boolean r2 = r2.equals(r3)
            if (r2 == 0) goto L_0x003e
            goto L_0x003f
        L_0x001d:
            java.lang.String r0 = "click_searchHot"
            boolean r0 = r2.equals(r0)
            if (r0 == 0) goto L_0x003e
            r0 = r6
            goto L_0x003f
        L_0x0028:
            java.lang.String r0 = "click_searchBox"
            boolean r0 = r2.equals(r0)
            if (r0 == 0) goto L_0x003e
            r0 = r4
            goto L_0x003f
        L_0x0033:
            java.lang.String r0 = "click_searchHistory"
            boolean r0 = r2.equals(r0)
            if (r0 == 0) goto L_0x003e
            r0 = r5
            goto L_0x003f
        L_0x003e:
            r0 = -1
        L_0x003f:
            if (r0 == 0) goto L_0x005a
            if (r0 == r6) goto L_0x0054
            if (r0 == r5) goto L_0x004e
            if (r0 == r4) goto L_0x0048
            goto L_0x005f
        L_0x0048:
            java.lang.String r0 = "sug"
            r8.c = r0
            goto L_0x005f
        L_0x004e:
            java.lang.String r0 = "history"
            r8.c = r0
            goto L_0x005f
        L_0x0054:
            java.lang.String r0 = "hot"
            r8.c = r0
            goto L_0x005f
        L_0x005a:
            java.lang.String r0 = "search"
            r8.c = r0
        L_0x005f:
            super.b(r8)
            cn.missfresh.sherlock.trace.core.AppMethodBeat.o(r1)
            return
            switch-data {-1388367019->0x0033, 598758668->0x0028, 598764430->0x001d, 692900927->0x0013, }
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.missfresh.module.base.d.a.j.b(cn.missfresh.module.base.datastatistics.a.a.a):void");
    }

    @Override // cn.missfresh.module.base.d.a.c
    public List<String> d() {
        return this.a;
    }
}
