package com.umeng.analytics.process;

import android.content.ComponentName;
import android.content.Context;
import android.text.TextUtils;
import com.umeng.analytics.process.DBFileTraversalUtil;
import com.umeng.commonsdk.framework.UMWorkDispatch;
import com.umeng.commonsdk.statistics.AnalyticsConstants;
import com.umeng.commonsdk.utils.FileLockCallback;
import com.umeng.commonsdk.utils.FileLockUtil;
import com.umeng.commonsdk.utils.UMUtils;
import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

public class UMProcessDBHelper {
    private static UMProcessDBHelper mInstance;
    private InsertEventCallback ekvCallBack = new InsertEventCallback(this, null);
    private Context mContext;
    private FileLockUtil mFileLock = new FileLockUtil();

    private int getDataSource() {
        return 0;
    }

    /* access modifiers changed from: private */
    public class InsertEventCallback implements FileLockCallback {
        @Override // com.umeng.commonsdk.utils.FileLockCallback
        public boolean onFileLock(File file, int i) {
            return false;
        }

        @Override // com.umeng.commonsdk.utils.FileLockCallback
        public boolean onFileLock(String str) {
            return false;
        }

        private InsertEventCallback() {
        }

        /* synthetic */ InsertEventCallback(UMProcessDBHelper uMProcessDBHelper, AnonymousClass1 r2) {
            this();
        }

        @Override // com.umeng.commonsdk.utils.FileLockCallback
        public boolean onFileLock(String str, Object obj) {
            if (TextUtils.isEmpty(str)) {
                return true;
            }
            if (str.startsWith(a.c)) {
                str = str.replaceFirst(a.c, "");
            }
            UMProcessDBHelper.this.insertEvents(str.replace(a.d, ""), (JSONArray) obj);
            return true;
        }
    }

    private UMProcessDBHelper() {
    }

    private UMProcessDBHelper(Context context) {
        com.umeng.common.a.a().a(context);
    }

    public static UMProcessDBHelper getInstance(Context context) {
        if (mInstance == null) {
            synchronized (UMProcessDBHelper.class) {
                if (mInstance == null) {
                    mInstance = new UMProcessDBHelper(context);
                }
            }
        }
        UMProcessDBHelper uMProcessDBHelper = mInstance;
        uMProcessDBHelper.mContext = context;
        return uMProcessDBHelper;
    }

    public void createDBByProcess(String str) {
        try {
            c.a(this.mContext).a(str);
            c.a(this.mContext).b(str);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void insertEventsInSubProcess(String str, JSONArray jSONArray) {
        if (AnalyticsConstants.SUB_PROCESS_EVENT && !TextUtils.isEmpty(str)) {
            File file = new File(b.b(this.mContext, str));
            if (file.exists()) {
                this.mFileLock.doFileOperateion(file, this.ekvCallBack, jSONArray);
            } else {
                insertEvents(str, jSONArray);
            }
        }
    }

    public void insertEvents(String str, JSONArray jSONArray) {
        if (AnalyticsConstants.SUB_PROCESS_EVENT && !TextUtils.isEmpty(str)) {
            insertEvents_(str, datasAdapter(str, jSONArray));
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void processToMain(String str) {
        if (dbIsExists(str)) {
            List<a> readEventByProcess = readEventByProcess(str);
            if (!readEventByProcess.isEmpty() && insertEvents_(a.h, readEventByProcess)) {
                deleteEventDatas(str, null, readEventByProcess);
            }
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:62:0x0175  */
    /* JADX WARNING: Removed duplicated region for block: B:64:0x017a  */
    /* JADX WARNING: Removed duplicated region for block: B:71:0x018f  */
    /* JADX WARNING: Removed duplicated region for block: B:73:0x0194 A[SYNTHETIC, Splitter:B:73:0x0194] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public org.json.JSONObject readMainEvents(long r20, java.util.List<java.lang.Integer> r22) {
        /*
        // Method dump skipped, instructions count: 417
        */
        throw new UnsupportedOperationException("Method not decompiled: com.umeng.analytics.process.UMProcessDBHelper.readMainEvents(long, java.util.List):org.json.JSONObject");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:13:0x004b, code lost:
        if (0 == 0) goto L_0x0050;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x004d, code lost:
        r1.endTransaction();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0050, code lost:
        com.umeng.analytics.process.c.a(r7.mContext).b(com.umeng.analytics.process.a.h);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0059, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:7:0x0038, code lost:
        if (r1 != null) goto L_0x004d;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void deleteMainProcessEventDatasByIds(java.util.List<java.lang.Integer> r8) {
        /*
            r7 = this;
            java.lang.String r0 = "_main_"
            r1 = 0
            android.content.Context r2 = r7.mContext     // Catch:{ Exception -> 0x004b, all -> 0x003b }
            com.umeng.analytics.process.c r2 = com.umeng.analytics.process.c.a(r2)     // Catch:{ Exception -> 0x004b, all -> 0x003b }
            android.database.sqlite.SQLiteDatabase r1 = r2.a(r0)     // Catch:{ Exception -> 0x004b, all -> 0x003b }
            r1.beginTransaction()     // Catch:{ Exception -> 0x004b, all -> 0x003b }
            java.util.Iterator r8 = r8.iterator()     // Catch:{ Exception -> 0x004b, all -> 0x003b }
        L_0x0015:
            boolean r2 = r8.hasNext()     // Catch:{ Exception -> 0x004b, all -> 0x003b }
            if (r2 == 0) goto L_0x0035
            java.lang.Object r2 = r8.next()     // Catch:{ Exception -> 0x004b, all -> 0x003b }
            java.lang.Integer r2 = (java.lang.Integer) r2     // Catch:{ Exception -> 0x004b, all -> 0x003b }
            java.lang.String r3 = "__et_p"
            java.lang.String r4 = "id=?"
            r5 = 1
            java.lang.String[] r5 = new java.lang.String[r5]     // Catch:{ Exception -> 0x004b, all -> 0x003b }
            r6 = 0
            java.lang.String r2 = java.lang.String.valueOf(r2)     // Catch:{ Exception -> 0x004b, all -> 0x003b }
            r5[r6] = r2     // Catch:{ Exception -> 0x004b, all -> 0x003b }
            r1.delete(r3, r4, r5)     // Catch:{ Exception -> 0x004b, all -> 0x003b }
            goto L_0x0015
        L_0x0035:
            r1.setTransactionSuccessful()     // Catch:{ Exception -> 0x004b, all -> 0x003b }
            if (r1 == 0) goto L_0x0050
            goto L_0x004d
        L_0x003b:
            r8 = move-exception
            if (r1 == 0) goto L_0x0041
            r1.endTransaction()
        L_0x0041:
            android.content.Context r1 = r7.mContext
            com.umeng.analytics.process.c r1 = com.umeng.analytics.process.c.a(r1)
            r1.b(r0)
            throw r8
        L_0x004b:
            if (r1 == 0) goto L_0x0050
        L_0x004d:
            r1.endTransaction()
        L_0x0050:
            android.content.Context r8 = r7.mContext
            com.umeng.analytics.process.c r8 = com.umeng.analytics.process.c.a(r8)
            r8.b(r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.umeng.analytics.process.UMProcessDBHelper.deleteMainProcessEventDatasByIds(java.util.List):void");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0049, code lost:
        if (r0 != null) goto L_0x0063;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x0061, code lost:
        if (r0 != null) goto L_0x0063;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x0063, code lost:
        r0.endTransaction();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x0066, code lost:
        com.umeng.analytics.process.c.a(r4.mContext).b(r5);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x006f, code lost:
        return;
     */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x0053  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void deleteEventDatas(java.lang.String r5, java.lang.String r6, java.util.List<com.umeng.analytics.process.UMProcessDBHelper.a> r7) {
        /*
            r4 = this;
            boolean r6 = android.text.TextUtils.isEmpty(r5)
            if (r6 == 0) goto L_0x0007
            return
        L_0x0007:
            r6 = 0
            android.content.Context r0 = r4.mContext     // Catch:{ Exception -> 0x0060, all -> 0x004e }
            com.umeng.analytics.process.c r0 = com.umeng.analytics.process.c.a(r0)     // Catch:{ Exception -> 0x0060, all -> 0x004e }
            android.database.sqlite.SQLiteDatabase r0 = r0.a(r5)     // Catch:{ Exception -> 0x0060, all -> 0x004e }
            r0.beginTransaction()     // Catch:{ Exception -> 0x0061, all -> 0x004c }
            int r1 = r7.size()     // Catch:{ Exception -> 0x0061, all -> 0x004c }
            if (r7 == 0) goto L_0x0040
            if (r1 <= 0) goto L_0x0040
            r6 = 0
        L_0x001e:
            if (r6 >= r1) goto L_0x0046
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0061, all -> 0x004c }
            r2.<init>()     // Catch:{ Exception -> 0x0061, all -> 0x004c }
            java.lang.String r3 = "delete from __et_p where rowid="
            r2.append(r3)     // Catch:{ Exception -> 0x0061, all -> 0x004c }
            java.lang.Object r3 = r7.get(r6)     // Catch:{ Exception -> 0x0061, all -> 0x004c }
            com.umeng.analytics.process.UMProcessDBHelper$a r3 = (com.umeng.analytics.process.UMProcessDBHelper.a) r3     // Catch:{ Exception -> 0x0061, all -> 0x004c }
            int r3 = r3.a     // Catch:{ Exception -> 0x0061, all -> 0x004c }
            r2.append(r3)     // Catch:{ Exception -> 0x0061, all -> 0x004c }
            java.lang.String r2 = r2.toString()     // Catch:{ Exception -> 0x0061, all -> 0x004c }
            r0.execSQL(r2)     // Catch:{ Exception -> 0x0061, all -> 0x004c }
            int r6 = r6 + 1
            goto L_0x001e
        L_0x0040:
            java.lang.String r7 = "__et_p"
            r0.delete(r7, r6, r6)     // Catch:{ Exception -> 0x0061, all -> 0x004c }
        L_0x0046:
            r0.setTransactionSuccessful()     // Catch:{ Exception -> 0x0061, all -> 0x004c }
            if (r0 == 0) goto L_0x0066
            goto L_0x0063
        L_0x004c:
            r6 = move-exception
            goto L_0x0051
        L_0x004e:
            r7 = move-exception
            r0 = r6
            r6 = r7
        L_0x0051:
            if (r0 == 0) goto L_0x0056
            r0.endTransaction()
        L_0x0056:
            android.content.Context r7 = r4.mContext
            com.umeng.analytics.process.c r7 = com.umeng.analytics.process.c.a(r7)
            r7.b(r5)
            throw r6
        L_0x0060:
            r0 = r6
        L_0x0061:
            if (r0 == 0) goto L_0x0066
        L_0x0063:
            r0.endTransaction()
        L_0x0066:
            android.content.Context r6 = r4.mContext
            com.umeng.analytics.process.c r6 = com.umeng.analytics.process.c.a(r6)
            r6.b(r5)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.umeng.analytics.process.UMProcessDBHelper.deleteEventDatas(java.lang.String, java.lang.String, java.util.List):void");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0089, code lost:
        r9 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:?, code lost:
        r2.endTransaction();
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x0089 A[ExcHandler: all (th java.lang.Throwable), Splitter:B:8:0x001c] */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x008f A[SYNTHETIC, Splitter:B:27:0x008f] */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x00a0 A[SYNTHETIC, Splitter:B:37:0x00a0] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private boolean insertEvents_(java.lang.String r8, java.util.List<com.umeng.analytics.process.UMProcessDBHelper.a> r9) {
        /*
            r7 = this;
            boolean r0 = android.text.TextUtils.isEmpty(r8)
            r1 = 1
            if (r0 != 0) goto L_0x00ad
            if (r9 == 0) goto L_0x00ad
            boolean r0 = r9.isEmpty()
            if (r0 == 0) goto L_0x0011
            goto L_0x00ad
        L_0x0011:
            r0 = 0
            android.content.Context r2 = r7.mContext     // Catch:{ Exception -> 0x009c, all -> 0x008b }
            com.umeng.analytics.process.c r2 = com.umeng.analytics.process.c.a(r2)     // Catch:{ Exception -> 0x009c, all -> 0x008b }
            android.database.sqlite.SQLiteDatabase r2 = r2.a(r8)     // Catch:{ Exception -> 0x009c, all -> 0x008b }
            r2.beginTransaction()     // Catch:{ Exception -> 0x009d, all -> 0x0089 }
            java.util.Iterator r9 = r9.iterator()     // Catch:{ Exception -> 0x009d, all -> 0x0089 }
        L_0x0023:
            boolean r3 = r9.hasNext()     // Catch:{ Exception -> 0x009d, all -> 0x0089 }
            if (r3 == 0) goto L_0x0077
            java.lang.Object r3 = r9.next()     // Catch:{ Exception -> 0x009d, all -> 0x0089 }
            com.umeng.analytics.process.UMProcessDBHelper$a r3 = (com.umeng.analytics.process.UMProcessDBHelper.a) r3     // Catch:{ Exception -> 0x009d, all -> 0x0089 }
            android.content.ContentValues r4 = new android.content.ContentValues     // Catch:{ Exception -> 0x0023, all -> 0x0089 }
            r4.<init>()     // Catch:{ Exception -> 0x0023, all -> 0x0089 }
            java.lang.String r5 = "__i"
            java.lang.String r6 = r3.b     // Catch:{ Exception -> 0x0023, all -> 0x0089 }
            r4.put(r5, r6)     // Catch:{ Exception -> 0x0023, all -> 0x0089 }
            java.lang.String r5 = "__e"
            java.lang.String r6 = r3.c     // Catch:{ Exception -> 0x0023, all -> 0x0089 }
            r4.put(r5, r6)     // Catch:{ Exception -> 0x0023, all -> 0x0089 }
            java.lang.String r5 = "__t"
            int r6 = r3.e     // Catch:{ Exception -> 0x0023, all -> 0x0089 }
            java.lang.Integer r6 = java.lang.Integer.valueOf(r6)     // Catch:{ Exception -> 0x0023, all -> 0x0089 }
            r4.put(r5, r6)     // Catch:{ Exception -> 0x0023, all -> 0x0089 }
            java.lang.String r5 = "__pn"
            java.lang.String r6 = r3.f     // Catch:{ Exception -> 0x0023, all -> 0x0089 }
            r4.put(r5, r6)     // Catch:{ Exception -> 0x0023, all -> 0x0089 }
            java.lang.String r5 = "__av"
            java.lang.String r6 = r3.g     // Catch:{ Exception -> 0x0023, all -> 0x0089 }
            r4.put(r5, r6)     // Catch:{ Exception -> 0x0023, all -> 0x0089 }
            java.lang.String r5 = "__vc"
            java.lang.String r6 = r3.h     // Catch:{ Exception -> 0x0023, all -> 0x0089 }
            r4.put(r5, r6)     // Catch:{ Exception -> 0x0023, all -> 0x0089 }
            java.lang.String r5 = "__s"
            java.lang.String r3 = r3.d     // Catch:{ Exception -> 0x0023, all -> 0x0089 }
            r4.put(r5, r3)     // Catch:{ Exception -> 0x0023, all -> 0x0089 }
            java.lang.String r3 = "__et_p"
            r2.insert(r3, r0, r4)     // Catch:{ Exception -> 0x0023, all -> 0x0089 }
            goto L_0x0023
        L_0x0077:
            r2.setTransactionSuccessful()
            if (r2 == 0) goto L_0x007f
            r2.endTransaction()     // Catch:{ all -> 0x007f }
        L_0x007f:
            android.content.Context r9 = r7.mContext
            com.umeng.analytics.process.c r9 = com.umeng.analytics.process.c.a(r9)
            r9.b(r8)
            return r1
        L_0x0089:
            r9 = move-exception
            goto L_0x008d
        L_0x008b:
            r9 = move-exception
            r2 = r0
        L_0x008d:
            if (r2 == 0) goto L_0x0092
            r2.endTransaction()     // Catch:{ all -> 0x0092 }
        L_0x0092:
            android.content.Context r0 = r7.mContext
            com.umeng.analytics.process.c r0 = com.umeng.analytics.process.c.a(r0)
            r0.b(r8)
            throw r9
        L_0x009c:
            r2 = r0
        L_0x009d:
            r9 = 0
            if (r2 == 0) goto L_0x00a3
            r2.endTransaction()     // Catch:{ all -> 0x00a3 }
        L_0x00a3:
            android.content.Context r0 = r7.mContext
            com.umeng.analytics.process.c r0 = com.umeng.analytics.process.c.a(r0)
            r0.b(r8)
            return r9
        L_0x00ad:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.umeng.analytics.process.UMProcessDBHelper.insertEvents_(java.lang.String, java.util.List):boolean");
    }

    private List<a> datasAdapter(String str, JSONArray jSONArray) {
        ArrayList arrayList = new ArrayList();
        if (TextUtils.isEmpty(str)) {
            return arrayList;
        }
        for (int i = 0; i < jSONArray.length(); i++) {
            try {
                JSONObject jSONObject = jSONArray.getJSONObject(i);
                a aVar = new a(this, null);
                aVar.c = jSONObject.optString("id");
                aVar.g = UMUtils.getAppVersionName(this.mContext);
                aVar.h = UMUtils.getAppVersionCode(this.mContext);
                aVar.b = jSONObject.optString("__i");
                aVar.e = jSONObject.optInt("__t");
                aVar.f = str;
                if (jSONObject.has("ds")) {
                    jSONObject.remove("ds");
                }
                jSONObject.put("ds", getDataSource());
                jSONObject.remove("__i");
                jSONObject.remove("__t");
                aVar.d = com.umeng.common.a.a().a(jSONObject.toString());
                jSONObject.remove("ds");
                arrayList.add(aVar);
            } catch (Exception unused) {
            }
        }
        return arrayList;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0060, code lost:
        r0 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0062, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0063, code lost:
        r5 = null;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x0060 A[ExcHandler: all (th java.lang.Throwable), Splitter:B:6:0x002f] */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x0089 A[SYNTHETIC, Splitter:B:39:0x0089] */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x008e A[Catch:{ Exception -> 0x0091 }] */
    /* JADX WARNING: Removed duplicated region for block: B:48:0x00a0 A[SYNTHETIC, Splitter:B:48:0x00a0] */
    /* JADX WARNING: Removed duplicated region for block: B:51:0x00a5 A[Catch:{ Exception -> 0x00a8 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public org.json.JSONObject readVersionInfoFromColumId(java.lang.Integer r9) {
        /*
            r8 = this;
            java.lang.String r0 = "__vc"
            java.lang.String r1 = "__av"
            java.lang.String r2 = "_main_"
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r4 = "select *  from __et_p where rowid="
            r3.append(r4)
            r3.append(r9)
            java.lang.String r9 = r3.toString()
            r3 = 0
            android.content.Context r4 = r8.mContext     // Catch:{ Exception -> 0x0081, all -> 0x007d }
            com.umeng.analytics.process.c r4 = com.umeng.analytics.process.c.a(r4)     // Catch:{ Exception -> 0x0081, all -> 0x007d }
            android.database.sqlite.SQLiteDatabase r4 = r4.a(r2)     // Catch:{ Exception -> 0x0081, all -> 0x007d }
            r4.beginTransaction()     // Catch:{ Exception -> 0x007a }
            android.database.Cursor r9 = r4.rawQuery(r9, r3)     // Catch:{ Exception -> 0x007a }
            if (r9 == 0) goto L_0x0066
            boolean r5 = r9.moveToNext()     // Catch:{ Exception -> 0x0062, all -> 0x0060 }
            if (r5 == 0) goto L_0x0066
            org.json.JSONObject r5 = new org.json.JSONObject     // Catch:{ Exception -> 0x0062, all -> 0x0060 }
            r5.<init>()     // Catch:{ Exception -> 0x0062, all -> 0x0060 }
            int r3 = r9.getColumnIndex(r1)     // Catch:{ Exception -> 0x005e, all -> 0x0060 }
            java.lang.String r3 = r9.getString(r3)     // Catch:{ Exception -> 0x005e, all -> 0x0060 }
            int r6 = r9.getColumnIndex(r0)     // Catch:{ Exception -> 0x005e, all -> 0x0060 }
            java.lang.String r6 = r9.getString(r6)     // Catch:{ Exception -> 0x005e, all -> 0x0060 }
            boolean r7 = android.text.TextUtils.isEmpty(r3)     // Catch:{ Exception -> 0x005e, all -> 0x0060 }
            if (r7 != 0) goto L_0x0053
            r5.put(r1, r3)     // Catch:{ Exception -> 0x005e, all -> 0x0060 }
        L_0x0053:
            boolean r1 = android.text.TextUtils.isEmpty(r6)     // Catch:{ Exception -> 0x005e, all -> 0x0060 }
            if (r1 != 0) goto L_0x005c
            r5.put(r0, r6)     // Catch:{ Exception -> 0x005e, all -> 0x0060 }
        L_0x005c:
            r3 = r5
            goto L_0x0066
        L_0x005e:
            r0 = move-exception
            goto L_0x0064
        L_0x0060:
            r0 = move-exception
            goto L_0x009e
        L_0x0062:
            r0 = move-exception
            r5 = r3
        L_0x0064:
            r3 = r9
            goto L_0x0084
        L_0x0066:
            if (r9 == 0) goto L_0x006b
            r9.close()     // Catch:{ Exception -> 0x0070 }
        L_0x006b:
            if (r4 == 0) goto L_0x0070
            r4.endTransaction()     // Catch:{ Exception -> 0x0070 }
        L_0x0070:
            android.content.Context r9 = r8.mContext
            com.umeng.analytics.process.c r9 = com.umeng.analytics.process.c.a(r9)
            r9.b(r2)
            goto L_0x009b
        L_0x007a:
            r0 = move-exception
            r5 = r3
            goto L_0x0084
        L_0x007d:
            r0 = move-exception
            r9 = r3
            r4 = r9
            goto L_0x009e
        L_0x0081:
            r0 = move-exception
            r4 = r3
            r5 = r4
        L_0x0084:
            r0.printStackTrace()     // Catch:{ all -> 0x009c }
            if (r3 == 0) goto L_0x008c
            r3.close()     // Catch:{ Exception -> 0x0091 }
        L_0x008c:
            if (r4 == 0) goto L_0x0091
            r4.endTransaction()     // Catch:{ Exception -> 0x0091 }
        L_0x0091:
            android.content.Context r9 = r8.mContext
            com.umeng.analytics.process.c r9 = com.umeng.analytics.process.c.a(r9)
            r9.b(r2)
            r3 = r5
        L_0x009b:
            return r3
        L_0x009c:
            r0 = move-exception
            r9 = r3
        L_0x009e:
            if (r9 == 0) goto L_0x00a3
            r9.close()     // Catch:{ Exception -> 0x00a8 }
        L_0x00a3:
            if (r4 == 0) goto L_0x00a8
            r4.endTransaction()     // Catch:{ Exception -> 0x00a8 }
        L_0x00a8:
            android.content.Context r9 = r8.mContext
            com.umeng.analytics.process.c r9 = com.umeng.analytics.process.c.a(r9)
            r9.b(r2)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.umeng.analytics.process.UMProcessDBHelper.readVersionInfoFromColumId(java.lang.Integer):org.json.JSONObject");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0094, code lost:
        if (r3 != null) goto L_0x00b1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x00af, code lost:
        if (r3 != null) goto L_0x00b1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x00b1, code lost:
        r3.endTransaction();
     */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x00ac  */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x00c1 A[SYNTHETIC, Splitter:B:34:0x00c1] */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x00c6 A[Catch:{ Exception -> 0x00c9 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private java.util.List<com.umeng.analytics.process.UMProcessDBHelper.a> readEventByProcess(java.lang.String r8) {
        /*
            r7 = this;
            java.lang.String r0 = "select *  from __et_p"
            java.util.ArrayList r1 = new java.util.ArrayList
            r1.<init>()
            r2 = 0
            android.content.Context r3 = r7.mContext     // Catch:{ Exception -> 0x00a3, all -> 0x009f }
            com.umeng.analytics.process.c r3 = com.umeng.analytics.process.c.a(r3)     // Catch:{ Exception -> 0x00a3, all -> 0x009f }
            android.database.sqlite.SQLiteDatabase r3 = r3.a(r8)     // Catch:{ Exception -> 0x00a3, all -> 0x009f }
            r3.beginTransaction()     // Catch:{ Exception -> 0x009a, all -> 0x0097 }
            android.database.Cursor r0 = r3.rawQuery(r0, r2)     // Catch:{ Exception -> 0x009a, all -> 0x0097 }
            if (r0 == 0) goto L_0x008f
        L_0x001c:
            boolean r4 = r0.moveToNext()     // Catch:{ Exception -> 0x008d }
            if (r4 == 0) goto L_0x008f
            com.umeng.analytics.process.UMProcessDBHelper$a r4 = new com.umeng.analytics.process.UMProcessDBHelper$a     // Catch:{ Exception -> 0x008d }
            r4.<init>(r7, r2)     // Catch:{ Exception -> 0x008d }
            r5 = 0
            int r5 = r0.getInt(r5)     // Catch:{ Exception -> 0x008d }
            r4.a = r5     // Catch:{ Exception -> 0x008d }
            java.lang.String r5 = "__i"
            int r5 = r0.getColumnIndex(r5)     // Catch:{ Exception -> 0x008d }
            java.lang.String r5 = r0.getString(r5)     // Catch:{ Exception -> 0x008d }
            r4.b = r5     // Catch:{ Exception -> 0x008d }
            java.lang.String r5 = "__e"
            int r5 = r0.getColumnIndex(r5)     // Catch:{ Exception -> 0x008d }
            java.lang.String r5 = r0.getString(r5)     // Catch:{ Exception -> 0x008d }
            r4.c = r5     // Catch:{ Exception -> 0x008d }
            java.lang.String r5 = "__s"
            int r5 = r0.getColumnIndex(r5)     // Catch:{ Exception -> 0x008d }
            java.lang.String r5 = r0.getString(r5)     // Catch:{ Exception -> 0x008d }
            r4.d = r5     // Catch:{ Exception -> 0x008d }
            java.lang.String r5 = "__t"
            int r5 = r0.getColumnIndex(r5)     // Catch:{ Exception -> 0x008d }
            int r5 = r0.getInt(r5)     // Catch:{ Exception -> 0x008d }
            r4.e = r5     // Catch:{ Exception -> 0x008d }
            java.lang.String r5 = "__pn"
            int r5 = r0.getColumnIndex(r5)     // Catch:{ Exception -> 0x008d }
            java.lang.String r5 = r0.getString(r5)     // Catch:{ Exception -> 0x008d }
            r4.f = r5     // Catch:{ Exception -> 0x008d }
            java.lang.String r5 = "__av"
            int r5 = r0.getColumnIndex(r5)     // Catch:{ Exception -> 0x008d }
            java.lang.String r5 = r0.getString(r5)     // Catch:{ Exception -> 0x008d }
            r4.g = r5     // Catch:{ Exception -> 0x008d }
            java.lang.String r5 = "__vc"
            int r5 = r0.getColumnIndex(r5)     // Catch:{ Exception -> 0x008d }
            java.lang.String r5 = r0.getString(r5)     // Catch:{ Exception -> 0x008d }
            r4.h = r5     // Catch:{ Exception -> 0x008d }
            r1.add(r4)     // Catch:{ Exception -> 0x008d }
            goto L_0x001c
        L_0x008d:
            r2 = move-exception
            goto L_0x00a7
        L_0x008f:
            if (r0 == 0) goto L_0x0094
            r0.close()     // Catch:{ Exception -> 0x00b4 }
        L_0x0094:
            if (r3 == 0) goto L_0x00b4
            goto L_0x00b1
        L_0x0097:
            r1 = move-exception
            r0 = r2
            goto L_0x00bf
        L_0x009a:
            r0 = move-exception
            r6 = r2
            r2 = r0
            r0 = r6
            goto L_0x00a7
        L_0x009f:
            r1 = move-exception
            r0 = r2
            r3 = r0
            goto L_0x00bf
        L_0x00a3:
            r0 = move-exception
            r3 = r2
            r2 = r0
            r0 = r3
        L_0x00a7:
            r2.printStackTrace()     // Catch:{ all -> 0x00be }
            if (r0 == 0) goto L_0x00af
            r0.close()
        L_0x00af:
            if (r3 == 0) goto L_0x00b4
        L_0x00b1:
            r3.endTransaction()
        L_0x00b4:
            android.content.Context r0 = r7.mContext
            com.umeng.analytics.process.c r0 = com.umeng.analytics.process.c.a(r0)
            r0.b(r8)
            return r1
        L_0x00be:
            r1 = move-exception
        L_0x00bf:
            if (r0 == 0) goto L_0x00c4
            r0.close()     // Catch:{ Exception -> 0x00c9 }
        L_0x00c4:
            if (r3 == 0) goto L_0x00c9
            r3.endTransaction()     // Catch:{ Exception -> 0x00c9 }
        L_0x00c9:
            android.content.Context r0 = r7.mContext
            com.umeng.analytics.process.c r0 = com.umeng.analytics.process.c.a(r0)
            r0.b(r8)
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.umeng.analytics.process.UMProcessDBHelper.readEventByProcess(java.lang.String):java.util.List");
    }

    private boolean dbIsExists(String str) {
        try {
            return new File(b.b(this.mContext, str)).exists();
        } catch (Throwable unused) {
            return false;
        }
    }

    /* access modifiers changed from: private */
    public class a implements Serializable {
        int a;
        String b;
        String c;
        String d;
        int e;
        String f;
        String g;
        String h;

        private a() {
        }

        /* synthetic */ a(UMProcessDBHelper uMProcessDBHelper, AnonymousClass1 r2) {
            this();
        }
    }

    private class ProcessToMainCallback implements FileLockCallback {
        @Override // com.umeng.commonsdk.utils.FileLockCallback
        public boolean onFileLock(File file, int i) {
            return false;
        }

        @Override // com.umeng.commonsdk.utils.FileLockCallback
        public boolean onFileLock(String str, Object obj) {
            return false;
        }

        private ProcessToMainCallback() {
        }

        /* synthetic */ ProcessToMainCallback(UMProcessDBHelper uMProcessDBHelper, AnonymousClass1 r2) {
            this();
        }

        @Override // com.umeng.commonsdk.utils.FileLockCallback
        public boolean onFileLock(String str) {
            if (TextUtils.isEmpty(str)) {
                return true;
            }
            if (str.startsWith(a.c)) {
                str = str.replaceFirst(a.c, "");
            }
            UMProcessDBHelper.this.processToMain(str.replace(a.d, ""));
            return true;
        }
    }

    public void processDBToMain() {
        try {
            DBFileTraversalUtil.traverseDBFiles(b.a(this.mContext), new ProcessToMainCallback(this, null), new AnonymousClass1());
        } catch (Exception unused) {
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: com.umeng.analytics.process.UMProcessDBHelper$1  reason: invalid class name */
    public class AnonymousClass1 implements DBFileTraversalUtil.a {
        AnonymousClass1() {
        }

        @Override // com.umeng.analytics.process.DBFileTraversalUtil.a
        public void a() {
            if (AnalyticsConstants.SUB_PROCESS_EVENT) {
                UMWorkDispatch.sendEvent(UMProcessDBHelper.this.mContext, UMProcessDBDatasSender.UM_PROCESS_CONSTRUCTMESSAGE, UMProcessDBDatasSender.getInstance(UMProcessDBHelper.this.mContext), null);
            }
        }
    }

    private boolean processIsService(Context context) {
        try {
            if (context.getPackageManager().getServiceInfo(new ComponentName(context, this.mContext.getClass()), 0) != null) {
                return true;
            }
            return false;
        } catch (Exception unused) {
        }
    }
}
