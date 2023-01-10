package com.umeng.analytics.pro;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.net.wifi.WifiEnterpriseConfig;
import android.text.TextUtils;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/* compiled from: UMDBUtils */
public class g {
    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0032, code lost:
        if (r2 != null) goto L_0x0034;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0034, code lost:
        r2.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x003f, code lost:
        if (0 == 0) goto L_0x0042;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0042, code lost:
        return r1;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean a(java.lang.String r7, android.database.sqlite.SQLiteDatabase r8) {
        /*
            java.lang.String r0 = "table"
            r1 = 0
            if (r7 != 0) goto L_0x0007
            return r1
        L_0x0007:
            r2 = 0
            r3 = 2
            java.lang.String[] r4 = new java.lang.String[r3]     // Catch:{ Exception -> 0x003f, all -> 0x0038 }
            r4[r1] = r0     // Catch:{ Exception -> 0x003f, all -> 0x0038 }
            java.lang.String r5 = r7.trim()     // Catch:{ Exception -> 0x003f, all -> 0x0038 }
            r6 = 1
            r4[r6] = r5     // Catch:{ Exception -> 0x003f, all -> 0x0038 }
            java.lang.String r4 = "select count(*) as c from sqlite_master where type=? and name=?"
            java.lang.String[] r3 = new java.lang.String[r3]     // Catch:{ Exception -> 0x003f, all -> 0x0038 }
            r3[r1] = r0     // Catch:{ Exception -> 0x003f, all -> 0x0038 }
            java.lang.String r7 = r7.trim()     // Catch:{ Exception -> 0x003f, all -> 0x0038 }
            r3[r6] = r7     // Catch:{ Exception -> 0x003f, all -> 0x0038 }
            android.database.Cursor r2 = r8.rawQuery(r4, r3)     // Catch:{ Exception -> 0x003f, all -> 0x0038 }
            boolean r7 = r2.moveToNext()     // Catch:{ Exception -> 0x003f, all -> 0x0038 }
            if (r7 == 0) goto L_0x0032
            int r7 = r2.getInt(r1)     // Catch:{ Exception -> 0x003f, all -> 0x0038 }
            if (r7 <= 0) goto L_0x0032
            r1 = r6
        L_0x0032:
            if (r2 == 0) goto L_0x0042
        L_0x0034:
            r2.close()
            goto L_0x0042
        L_0x0038:
            r7 = move-exception
            if (r2 == 0) goto L_0x003e
            r2.close()
        L_0x003e:
            throw r7
        L_0x003f:
            if (r2 == 0) goto L_0x0042
            goto L_0x0034
        L_0x0042:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.umeng.analytics.pro.g.a(java.lang.String, android.database.sqlite.SQLiteDatabase):boolean");
    }

    public static void a(Context context) {
        if (context != null) {
            try {
                File databasePath = context.getDatabasePath(d.b);
                if (databasePath != null && databasePath.exists()) {
                    databasePath.delete();
                }
                e.a(context).a();
            } catch (Throwable unused) {
            }
        }
    }

    public static String b(Context context) {
        File databasePath = context.getDatabasePath(d.b);
        return databasePath.getParent() + File.separator;
    }

    public static String c(Context context) {
        return b(context) + "subprocess/";
    }

    public static String a(List<String> list) {
        return TextUtils.join("!", list);
    }

    public static List<String> a(String str) {
        return new ArrayList(Arrays.asList(str.split("!")));
    }

    public static List<String> b(List<String> list) {
        ArrayList arrayList = new ArrayList();
        try {
            for (String str : list) {
                if (Collections.frequency(arrayList, str) < 1) {
                    arrayList.add(str);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return arrayList;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x002f, code lost:
        if (r0.isClosed() == false) goto L_0x0031;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0031, code lost:
        r0.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0048, code lost:
        if (r0.isClosed() == false) goto L_0x0031;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean a(android.database.sqlite.SQLiteDatabase r4, java.lang.String r5, java.lang.String r6) {
        /*
            r0 = 0
            r1 = 0
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0042, all -> 0x0035 }
            r2.<init>()     // Catch:{ Exception -> 0x0042, all -> 0x0035 }
            java.lang.String r3 = "SELECT * FROM "
            r2.append(r3)     // Catch:{ Exception -> 0x0042, all -> 0x0035 }
            r2.append(r5)     // Catch:{ Exception -> 0x0042, all -> 0x0035 }
            java.lang.String r5 = " LIMIT 0"
            r2.append(r5)     // Catch:{ Exception -> 0x0042, all -> 0x0035 }
            java.lang.String r5 = r2.toString()     // Catch:{ Exception -> 0x0042, all -> 0x0035 }
            android.database.Cursor r0 = r4.rawQuery(r5, r0)     // Catch:{ Exception -> 0x0042, all -> 0x0035 }
            if (r0 == 0) goto L_0x0029
            int r4 = r0.getColumnIndex(r6)     // Catch:{ Exception -> 0x0042, all -> 0x0035 }
            r5 = -1
            if (r4 == r5) goto L_0x0029
            r4 = 1
            r1 = r4
        L_0x0029:
            if (r0 == 0) goto L_0x004b
            boolean r4 = r0.isClosed()
            if (r4 != 0) goto L_0x004b
        L_0x0031:
            r0.close()
            goto L_0x004b
        L_0x0035:
            r4 = move-exception
            if (r0 == 0) goto L_0x0041
            boolean r5 = r0.isClosed()
            if (r5 != 0) goto L_0x0041
            r0.close()
        L_0x0041:
            throw r4
        L_0x0042:
            if (r0 == 0) goto L_0x004b
            boolean r4 = r0.isClosed()
            if (r4 != 0) goto L_0x004b
            goto L_0x0031
        L_0x004b:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.umeng.analytics.pro.g.a(android.database.sqlite.SQLiteDatabase, java.lang.String, java.lang.String):boolean");
    }

    public static void a(SQLiteDatabase sQLiteDatabase, String str, String str2, String str3) {
        sQLiteDatabase.execSQL("alter table " + str + " add " + str2 + WifiEnterpriseConfig.CA_CERT_ALIAS_DELIMITER + str3);
    }
}
