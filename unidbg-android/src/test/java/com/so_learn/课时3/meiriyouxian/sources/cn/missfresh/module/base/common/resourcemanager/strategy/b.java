package cn.missfresh.module.base.common.resourcemanager.strategy;

import cn.missfresh.module.base.common.resourcemanager.strategy.api.IResourceStrategy;

/* compiled from: H5ResourceStrategy */
public class b implements IResourceStrategy {
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0033, code lost:
        if (r1.equals(cn.missfresh.module.base.common.resourcemanager.bean.SkipBean.Type.URL) != false) goto L_0x0037;
     */
    /* JADX WARNING: Removed duplicated region for block: B:16:0x0048 A[ADDED_TO_REGION] */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x006f  */
    @Override // cn.missfresh.module.base.common.resourcemanager.strategy.api.IResourceStrategy
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void skip(cn.missfresh.module.base.common.resourcemanager.bean.SkipBean r8, java.util.Map<java.lang.String, java.lang.Object> r9) {
        /*
            r7 = this;
            r9 = 0
            r0 = 12292(0x3004, float:1.7225E-41)
            cn.missfresh.sherlock.trace.core.AppMethodBeat.i(r0, r9)
            if (r8 == 0) goto L_0x0096
            java.lang.String r1 = r8.type
            if (r1 != 0) goto L_0x000e
            goto L_0x0096
        L_0x000e:
            java.lang.String r1 = r8.type
            r2 = -1
            int r3 = r1.hashCode()
            r4 = 84303(0x1494f, float:1.18134E-40)
            r5 = 1
            if (r3 == r4) goto L_0x002c
            r9 = 812095439(0x306797cf, float:8.4253043E-10)
            if (r3 == r9) goto L_0x0021
            goto L_0x0036
        L_0x0021:
            java.lang.String r9 = "WEBPROMOTION"
            boolean r9 = r1.equals(r9)
            if (r9 == 0) goto L_0x0036
            r9 = r5
            goto L_0x0037
        L_0x002c:
            java.lang.String r3 = "URL"
            boolean r1 = r1.equals(r3)
            if (r1 == 0) goto L_0x0036
            goto L_0x0037
        L_0x0036:
            r9 = r2
        L_0x0037:
            java.lang.String r1 = "h5_url"
            java.lang.String r2 = "h5_title"
            java.lang.String r3 = ""
            java.lang.String r4 = "H5_channel"
            java.lang.String r6 = "/promotion/new_h5event"
            if (r9 == 0) goto L_0x006f
            if (r9 == r5) goto L_0x004b
            goto L_0x0092
        L_0x004b:
            java.lang.String r9 = r8.link
            boolean r9 = cn.missfresh.utils.b.a(r9)
            if (r9 != 0) goto L_0x0092
            com.alibaba.android.arouter.b.a r9 = com.alibaba.android.arouter.b.a.a()
            com.alibaba.android.arouter.facade.Postcard r9 = r9.a(r6)
            com.alibaba.android.arouter.facade.Postcard r9 = r9.withString(r4, r3)
            java.lang.String r3 = r8.name
            com.alibaba.android.arouter.facade.Postcard r9 = r9.withString(r2, r3)
            java.lang.String r8 = r8.link
            com.alibaba.android.arouter.facade.Postcard r8 = r9.withString(r1, r8)
            r8.navigation()
            goto L_0x0092
        L_0x006f:
            java.lang.String r9 = r8.link
            boolean r9 = cn.missfresh.utils.b.a(r9)
            if (r9 != 0) goto L_0x0092
            com.alibaba.android.arouter.b.a r9 = com.alibaba.android.arouter.b.a.a()
            com.alibaba.android.arouter.facade.Postcard r9 = r9.a(r6)
            com.alibaba.android.arouter.facade.Postcard r9 = r9.withString(r4, r3)
            java.lang.String r3 = r8.name
            com.alibaba.android.arouter.facade.Postcard r9 = r9.withString(r2, r3)
            java.lang.String r8 = r8.link
            com.alibaba.android.arouter.facade.Postcard r8 = r9.withString(r1, r8)
            r8.navigation()
        L_0x0092:
            cn.missfresh.sherlock.trace.core.AppMethodBeat.o(r0)
            return
        L_0x0096:
            cn.missfresh.sherlock.trace.core.AppMethodBeat.o(r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.missfresh.module.base.common.resourcemanager.strategy.b.skip(cn.missfresh.module.base.common.resourcemanager.bean.SkipBean, java.util.Map):void");
    }
}
