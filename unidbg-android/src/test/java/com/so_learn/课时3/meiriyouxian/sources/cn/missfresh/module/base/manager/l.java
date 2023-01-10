package cn.missfresh.module.base.manager;

/* compiled from: ShoppingCartManager */
public class l extends f {
    /* JADX WARNING: Removed duplicated region for block: B:21:0x0042 A[Catch:{ Exception -> 0x0061 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static cn.missfresh.module.base.bean.ShoppingCartNotice a() {
        /*
            java.lang.String r0 = "ShoppingCartManager"
            r1 = 14540(0x38cc, float:2.0375E-41)
            r2 = 0
            cn.missfresh.sherlock.trace.core.AppMethodBeat.i(r1, r2)
            java.lang.Class<cn.missfresh.module.base.bean.ShoppingCartActive> r3 = cn.missfresh.module.base.bean.ShoppingCartActive.class
            boolean r3 = c(r3)     // Catch:{ Exception -> 0x0035 }
            if (r3 == 0) goto L_0x0033
            java.lang.String r3 = "select sum(quantity) from buycar_active_new"
            android.database.Cursor r3 = a(r3)     // Catch:{ Exception -> 0x0035 }
            boolean r4 = r3.moveToFirst()     // Catch:{ Exception -> 0x0035 }
            if (r4 == 0) goto L_0x002c
            int r4 = r3.getCount()     // Catch:{ Exception -> 0x0035 }
            if (r4 <= 0) goto L_0x002c
            r3.move(r2)     // Catch:{ Exception -> 0x0035 }
            int r4 = r3.getInt(r2)     // Catch:{ Exception -> 0x0035 }
            goto L_0x002d
        L_0x002c:
            r4 = r2
        L_0x002d:
            r3.close()     // Catch:{ Exception -> 0x0031 }
            goto L_0x003a
        L_0x0031:
            r3 = move-exception
            goto L_0x0037
        L_0x0033:
            r4 = r2
            goto L_0x003a
        L_0x0035:
            r3 = move-exception
            r4 = r2
        L_0x0037:
            cn.missfresh.utils.a.d.a(r0, r3)
        L_0x003a:
            java.lang.Class<cn.missfresh.module.base.bean.ShoppingCartInActive> r3 = cn.missfresh.module.base.bean.ShoppingCartInActive.class
            boolean r3 = c(r3)     // Catch:{ Exception -> 0x0061 }
            if (r3 == 0) goto L_0x0065
            java.lang.String r3 = "select sum(quantity) from buycar_inactive_new"
            android.database.Cursor r3 = a(r3)     // Catch:{ Exception -> 0x0061 }
            boolean r5 = r3.moveToFirst()     // Catch:{ Exception -> 0x0061 }
            if (r5 == 0) goto L_0x005d
            int r5 = r3.getCount()     // Catch:{ Exception -> 0x0061 }
            if (r5 <= 0) goto L_0x005d
            r3.move(r2)     // Catch:{ Exception -> 0x0061 }
            int r5 = r3.getInt(r2)     // Catch:{ Exception -> 0x0061 }
            int r4 = r4 + r5
        L_0x005d:
            r3.close()     // Catch:{ Exception -> 0x0061 }
            goto L_0x0065
        L_0x0061:
            r3 = move-exception
            cn.missfresh.utils.a.d.a(r0, r3)
        L_0x0065:
            cn.missfresh.module.base.bean.ShoppingCartNotice r0 = new cn.missfresh.module.base.bean.ShoppingCartNotice
            r0.<init>(r4, r2, r2)
            cn.missfresh.sherlock.trace.core.AppMethodBeat.o(r1)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.missfresh.module.base.manager.l.a():cn.missfresh.module.base.bean.ShoppingCartNotice");
    }
}
