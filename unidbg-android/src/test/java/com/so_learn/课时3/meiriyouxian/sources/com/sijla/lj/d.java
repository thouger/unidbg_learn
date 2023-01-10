package com.sijla.lj;

import java.lang.reflect.InvocationHandler;

public class d implements InvocationHandler {
    private c a;
    private L b;
    private c c = this.b.e("print");

    public d(c cVar) {
        this.a = cVar;
        this.b = cVar.b;
    }

    /* JADX WARNING: Removed duplicated region for block: B:39:0x0096  */
    @Override // java.lang.reflect.InvocationHandler
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.Object invoke(java.lang.Object r7, java.lang.reflect.Method r8, java.lang.Object[] r9) {
        /*
            r6 = this;
            com.sijla.lj.c r7 = r6.a
            com.sijla.lj.L r7 = r7.b
            monitor-enter(r7)
            java.lang.String r0 = r8.getName()     // Catch:{ all -> 0x00c3 }
            com.sijla.lj.c r1 = r6.a     // Catch:{ all -> 0x00c3 }
            com.sijla.lj.c r1 = r1.b(r0)     // Catch:{ all -> 0x00c3 }
            java.lang.Class r8 = r8.getReturnType()     // Catch:{ all -> 0x00c3 }
            boolean r2 = r1.d()     // Catch:{ all -> 0x00c3 }
            r3 = 0
            r4 = 0
            if (r2 == 0) goto L_0x0049
            java.lang.Class r9 = java.lang.Boolean.TYPE     // Catch:{ all -> 0x00c3 }
            boolean r9 = r8.equals(r9)     // Catch:{ all -> 0x00c3 }
            if (r9 != 0) goto L_0x0043
            java.lang.Class<java.lang.Boolean> r9 = java.lang.Boolean.class
            boolean r9 = r8.equals(r9)     // Catch:{ all -> 0x00c3 }
            if (r9 == 0) goto L_0x002c
            goto L_0x0043
        L_0x002c:
            boolean r9 = r8.isPrimitive()     // Catch:{ all -> 0x00c3 }
            if (r9 != 0) goto L_0x003d
            java.lang.Class<java.lang.Number> r9 = java.lang.Number.class
            boolean r8 = r9.isAssignableFrom(r8)     // Catch:{ all -> 0x00c3 }
            if (r8 == 0) goto L_0x003b
            goto L_0x003d
        L_0x003b:
            monitor-exit(r7)     // Catch:{ all -> 0x00c3 }
            return r3
        L_0x003d:
            java.lang.Integer r8 = java.lang.Integer.valueOf(r4)     // Catch:{ all -> 0x00c3 }
            monitor-exit(r7)     // Catch:{ all -> 0x00c3 }
            return r8
        L_0x0043:
            java.lang.Boolean r8 = java.lang.Boolean.valueOf(r4)     // Catch:{ all -> 0x00c3 }
            monitor-exit(r7)     // Catch:{ all -> 0x00c3 }
            return r8
        L_0x0049:
            java.lang.Class<java.lang.Void> r2 = java.lang.Void.class
            boolean r2 = r8.equals(r2)     // Catch:{ LException -> 0x0070 }
            if (r2 != 0) goto L_0x006c
            java.lang.Class r2 = java.lang.Void.TYPE     // Catch:{ LException -> 0x0070 }
            boolean r2 = r8.equals(r2)     // Catch:{ LException -> 0x0070 }
            if (r2 == 0) goto L_0x005a
            goto L_0x006c
        L_0x005a:
            java.lang.Object r3 = r1.b(r9)     // Catch:{ LException -> 0x0070 }
            if (r3 == 0) goto L_0x0094
            boolean r9 = r3 instanceof java.lang.Double     // Catch:{ LException -> 0x0070 }
            if (r9 == 0) goto L_0x0094
            r9 = r3
            java.lang.Double r9 = (java.lang.Double) r9     // Catch:{ LException -> 0x0070 }
            java.lang.Number r3 = com.sijla.lj.L.a(r9, r8)     // Catch:{ LException -> 0x0070 }
            goto L_0x0094
        L_0x006c:
            r1.b(r9)     // Catch:{ LException -> 0x0070 }
            goto L_0x0094
        L_0x0070:
            r9 = move-exception
            com.sijla.lj.c r1 = r6.c
            r2 = 1
            java.lang.Object[] r2 = new java.lang.Object[r2]
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            r5.append(r0)
            java.lang.String r0 = " "
            r5.append(r0)
            java.lang.String r9 = r9.getMessage()
            r5.append(r9)
            java.lang.String r9 = r5.toString()
            r2[r4] = r9
            r1.b(r2)
        L_0x0094:
            if (r3 != 0) goto L_0x00c1
            java.lang.Class r9 = java.lang.Boolean.TYPE
            boolean r9 = r8.equals(r9)
            if (r9 != 0) goto L_0x00bb
            java.lang.Class<java.lang.Boolean> r9 = java.lang.Boolean.class
            boolean r9 = r8.equals(r9)
            if (r9 == 0) goto L_0x00a7
            goto L_0x00bb
        L_0x00a7:
            boolean r9 = r8.isPrimitive()
            if (r9 != 0) goto L_0x00b5
            java.lang.Class<java.lang.Number> r9 = java.lang.Number.class
            boolean r8 = r9.isAssignableFrom(r8)
            if (r8 == 0) goto L_0x00c1
        L_0x00b5:
            java.lang.Integer r8 = java.lang.Integer.valueOf(r4)
            monitor-exit(r7)
            return r8
        L_0x00bb:
            java.lang.Boolean r8 = java.lang.Boolean.valueOf(r4)
            monitor-exit(r7)
            return r8
        L_0x00c1:
            monitor-exit(r7)
            return r3
        L_0x00c3:
            r8 = move-exception
            monitor-exit(r7)
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.sijla.lj.d.invoke(java.lang.Object, java.lang.reflect.Method, java.lang.Object[]):java.lang.Object");
    }
}
