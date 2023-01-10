package com.sina.weibo.sdk;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.text.TextUtils;
import java.util.List;

/* compiled from: WeiboAppManager */
public class b {
    private static final String a = b.class.getName();
    private static final Uri b = Uri.parse("content://com.sina.weibo.sdkProvider/query/package");
    private static b c;
    private Context d;

    /* compiled from: WeiboAppManager */
    public static class a {
        private String a;
        private int b;

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void a(String str) {
            this.a = str;
        }

        public String a() {
            return this.a;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void a(int i) {
            this.b = i;
        }

        public int b() {
            return this.b;
        }

        public boolean c() {
            return !TextUtils.isEmpty(this.a) && this.b > 0;
        }

        public String toString() {
            return "WeiboInfo: PackageName = " + this.a + ", supportApi = " + this.b;
        }
    }

    private b(Context context) {
        this.d = context.getApplicationContext();
    }

    public static synchronized b a(Context context) {
        b bVar;
        synchronized (b.class) {
            if (c == null) {
                c = new b(context);
            }
            bVar = c;
        }
        return bVar;
    }

    public synchronized a a() {
        return b(this.d);
    }

    private a b(Context context) {
        a c2 = c(context);
        a d = d(context);
        boolean z = true;
        boolean z2 = c2 != null;
        if (d == null) {
            z = false;
        }
        if (z2 && z) {
            return c2.b() >= d.b() ? c2 : d;
        }
        if (z2) {
            return c2;
        }
        if (z) {
            return d;
        }
        return null;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:24:0x005a, code lost:
        if (r0 != null) goto L_0x006f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x006d, code lost:
        if (r0 != null) goto L_0x006f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x006f, code lost:
        r0.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:0x0072, code lost:
        return null;
     */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x0076  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private com.sina.weibo.sdk.b.a c(android.content.Context r8) {
        /*
            r7 = this;
            android.content.ContentResolver r0 = r8.getContentResolver()
            r6 = 0
            android.net.Uri r1 = com.sina.weibo.sdk.b.b     // Catch:{ Exception -> 0x0062, all -> 0x005f }
            r2 = 0
            r3 = 0
            r4 = 0
            r5 = 0
            android.database.Cursor r0 = r0.query(r1, r2, r3, r4, r5)     // Catch:{ Exception -> 0x0062, all -> 0x005f }
            if (r0 != 0) goto L_0x0017
            if (r0 == 0) goto L_0x0016
            r0.close()
        L_0x0016:
            return r6
        L_0x0017:
            java.lang.String r1 = "support_api"
            int r1 = r0.getColumnIndex(r1)     // Catch:{ Exception -> 0x005d }
            java.lang.String r2 = "package"
            int r2 = r0.getColumnIndex(r2)     // Catch:{ Exception -> 0x005d }
            boolean r3 = r0.moveToFirst()     // Catch:{ Exception -> 0x005d }
            if (r3 == 0) goto L_0x005a
            r3 = -1
            java.lang.String r1 = r0.getString(r1)     // Catch:{ Exception -> 0x005d }
            int r3 = java.lang.Integer.parseInt(r1)     // Catch:{ NumberFormatException -> 0x0035 }
            goto L_0x0039
        L_0x0035:
            r1 = move-exception
            r1.printStackTrace()
        L_0x0039:
            java.lang.String r1 = r0.getString(r2)
            boolean r2 = android.text.TextUtils.isEmpty(r1)
            if (r2 != 0) goto L_0x005a
            boolean r8 = com.sina.weibo.sdk.a.a(r8, r1)
            if (r8 == 0) goto L_0x005a
            com.sina.weibo.sdk.b$a r8 = new com.sina.weibo.sdk.b$a
            r8.<init>()
            com.sina.weibo.sdk.b.a.a(r8, r1)
            com.sina.weibo.sdk.b.a.a(r8, r3)
            if (r0 == 0) goto L_0x0059
            r0.close()
        L_0x0059:
            return r8
        L_0x005a:
            if (r0 == 0) goto L_0x0072
            goto L_0x006f
        L_0x005d:
            r8 = move-exception
            goto L_0x0064
        L_0x005f:
            r8 = move-exception
            r0 = r6
            goto L_0x0074
        L_0x0062:
            r8 = move-exception
            r0 = r6
        L_0x0064:
            java.lang.String r1 = com.sina.weibo.sdk.b.a     // Catch:{ all -> 0x0073 }
            java.lang.String r8 = r8.getMessage()     // Catch:{ all -> 0x0073 }
            com.sina.weibo.sdk.a.d.c(r1, r8)     // Catch:{ all -> 0x0073 }
            if (r0 == 0) goto L_0x0072
        L_0x006f:
            r0.close()
        L_0x0072:
            return r6
        L_0x0073:
            r8 = move-exception
        L_0x0074:
            if (r0 == 0) goto L_0x0079
            r0.close()
        L_0x0079:
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.sina.weibo.sdk.b.c(android.content.Context):com.sina.weibo.sdk.b$a");
    }

    private a d(Context context) {
        a a2;
        Intent intent = new Intent("com.sina.weibo.action.sdkidentity");
        intent.addCategory(Intent.CATEGORY_DEFAULT);
        List<ResolveInfo> queryIntentServices = context.getPackageManager().queryIntentServices(intent, 0);
        a aVar = null;
        if (queryIntentServices == null || queryIntentServices.isEmpty()) {
            return null;
        }
        for (ResolveInfo resolveInfo : queryIntentServices) {
            if (!(resolveInfo.serviceInfo == null || resolveInfo.serviceInfo.applicationInfo == null || TextUtils.isEmpty(resolveInfo.serviceInfo.applicationInfo.packageName) || (a2 = a(resolveInfo.serviceInfo.applicationInfo.packageName)) == null)) {
                if (aVar == null || aVar.b() < a2.b()) {
                    aVar = a2;
                }
            }
        }
        return aVar;
    }

    /* JADX WARNING: Removed duplicated region for block: B:39:0x009c A[SYNTHETIC, Splitter:B:39:0x009c] */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x00ad  */
    /* JADX WARNING: Removed duplicated region for block: B:50:0x00be  */
    /* JADX WARNING: Removed duplicated region for block: B:55:0x00cf  */
    /* JADX WARNING: Removed duplicated region for block: B:61:0x00e1 A[SYNTHETIC, Splitter:B:61:0x00e1] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.sina.weibo.sdk.b.a a(java.lang.String r9) {
        /*
        // Method dump skipped, instructions count: 240
        */
        throw new UnsupportedOperationException("Method not decompiled: com.sina.weibo.sdk.b.a(java.lang.String):com.sina.weibo.sdk.b$a");
    }
}
