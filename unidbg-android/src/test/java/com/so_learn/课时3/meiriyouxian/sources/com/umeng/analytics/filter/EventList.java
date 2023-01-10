package com.umeng.analytics.filter;

import android.content.Context;
import android.text.TextUtils;
import com.umeng.analytics.pro.c;
import com.umeng.commonsdk.debug.UMRTLog;
import com.umeng.commonsdk.framework.UMEnvelopeBuild;
import com.umeng.commonsdk.internal.crash.UMCrashManager;
import com.umeng.commonsdk.statistics.idtracking.ImprintHandler;
import com.umeng.commonsdk.utils.FileLockCallback;
import com.umeng.commonsdk.utils.FileLockUtil;
import com.umeng.commonsdk.utils.UMUtils;
import com.xiaomi.mipush.sdk.Constants;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class EventList extends c implements FileLockCallback {
    private static final int DELETE_LIST_DATA = 2;
    private static final int LOAD_LIST_DATA = 1;
    private static final int SAVE_LIST_DATA = 0;
    private Context mAppContext;
    protected String mEventList;
    protected String mEventListKey;
    protected String mEventListName;
    protected String mEventListVersionKey;
    private FileLockUtil mFileLock = new FileLockUtil();

    /* access modifiers changed from: protected */
    public void eventListChange() {
    }

    public boolean matchHit(String str) {
        return false;
    }

    @Override // com.umeng.commonsdk.utils.FileLockCallback
    public boolean onFileLock(String str) {
        return false;
    }

    @Override // com.umeng.commonsdk.utils.FileLockCallback
    public boolean onFileLock(String str, Object obj) {
        return false;
    }

    public void setMD5ClearFlag(boolean z) {
    }

    public EventList(String str, String str2) {
        this.mEventListName = str;
        this.mEventListKey = str;
        this.mEventListVersionKey = str2;
    }

    public boolean enabled() {
        synchronized (this) {
            if (this.mEventList == null) {
                return false;
            }
            return true;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:51:0x00b2  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private boolean loadEventListFromFile(android.content.Context r9, java.io.File r10) {
        /*
            r8 = this;
            boolean r0 = r10.exists()
            r1 = 0
            if (r0 == 0) goto L_0x00c7
            java.lang.String r0 = r8.mEventList
            boolean r0 = android.text.TextUtils.isEmpty(r0)
            if (r0 == 0) goto L_0x00c7
            r0 = 0
            java.io.FileReader r2 = new java.io.FileReader     // Catch:{ all -> 0x00a9 }
            r2.<init>(r10)     // Catch:{ all -> 0x00a9 }
            java.io.BufferedReader r10 = new java.io.BufferedReader     // Catch:{ all -> 0x00a9 }
            r10.<init>(r2)     // Catch:{ all -> 0x00a9 }
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ all -> 0x00a7 }
            r0.<init>()     // Catch:{ all -> 0x00a7 }
        L_0x001f:
            java.lang.String r2 = r10.readLine()     // Catch:{ all -> 0x00a7 }
            if (r2 == 0) goto L_0x0029
            r0.append(r2)     // Catch:{ all -> 0x00a7 }
            goto L_0x001f
        L_0x0029:
            java.lang.String r0 = r0.toString()     // Catch:{ all -> 0x00a7 }
            boolean r2 = android.text.TextUtils.isEmpty(r0)     // Catch:{ all -> 0x00a7 }
            if (r2 != 0) goto L_0x00a3
            java.lang.String r2 = com.umeng.commonsdk.statistics.common.HelperUtils.getMD5(r0)     // Catch:{ all -> 0x00a7 }
            java.lang.String r3 = r8.mEventListVersionKey     // Catch:{ all -> 0x00a7 }
            java.lang.String r4 = ""
            java.lang.String r3 = com.umeng.commonsdk.framework.UMEnvelopeBuild.imprintProperty(r9, r3, r4)     // Catch:{ all -> 0x00a7 }
            r8.mEventList = r0     // Catch:{ all -> 0x00a7 }
            r8.eventListChange()     // Catch:{ all -> 0x00a7 }
            java.lang.String r4 = "MobclickRT"
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ all -> 0x00a7 }
            r5.<init>()     // Catch:{ all -> 0x00a7 }
            java.lang.String r6 = "--->>> loadEventListFromFile: mEventList = "
            r5.append(r6)     // Catch:{ all -> 0x00a7 }
            java.lang.String r6 = r8.mEventList     // Catch:{ all -> 0x00a7 }
            r5.append(r6)     // Catch:{ all -> 0x00a7 }
            java.lang.String r5 = r5.toString()     // Catch:{ all -> 0x00a7 }
            com.umeng.commonsdk.debug.UMRTLog.i(r4, r5)     // Catch:{ all -> 0x00a7 }
            boolean r4 = com.umeng.commonsdk.utils.UMUtils.isMainProgress(r9)     // Catch:{ all -> 0x00a7 }
            r5 = 1
            if (r4 == 0) goto L_0x0086
            boolean r2 = r2.equalsIgnoreCase(r3)     // Catch:{ all -> 0x00a7 }
            if (r2 == 0) goto L_0x007a
            r8.mEventList = r0     // Catch:{ all -> 0x00a7 }
            r8.setMD5ClearFlag(r1)     // Catch:{ all -> 0x00a7 }
            r10.close()     // Catch:{ all -> 0x0075 }
            goto L_0x0079
        L_0x0075:
            r10 = move-exception
            com.umeng.commonsdk.internal.crash.UMCrashManager.reportCrash(r9, r10)
        L_0x0079:
            return r5
        L_0x007a:
            r8.setMD5ClearFlag(r5)
            r10.close()     // Catch:{ all -> 0x0081 }
            goto L_0x0085
        L_0x0081:
            r10 = move-exception
            com.umeng.commonsdk.internal.crash.UMCrashManager.reportCrash(r9, r10)
        L_0x0085:
            return r1
        L_0x0086:
            boolean r2 = r2.equalsIgnoreCase(r3)
            if (r2 == 0) goto L_0x009a
            r8.mEventList = r0
            r8.eventListChange()
            r10.close()     // Catch:{ all -> 0x0095 }
            goto L_0x0099
        L_0x0095:
            r10 = move-exception
            com.umeng.commonsdk.internal.crash.UMCrashManager.reportCrash(r9, r10)
        L_0x0099:
            return r5
        L_0x009a:
            r10.close()     // Catch:{ all -> 0x009e }
            goto L_0x00a2
        L_0x009e:
            r10 = move-exception
            com.umeng.commonsdk.internal.crash.UMCrashManager.reportCrash(r9, r10)
        L_0x00a2:
            return r1
        L_0x00a3:
            r10.close()     // Catch:{ all -> 0x00b6 }
            goto L_0x00c7
        L_0x00a7:
            r0 = move-exception
            goto L_0x00ad
        L_0x00a9:
            r10 = move-exception
            r7 = r0
            r0 = r10
            r10 = r7
        L_0x00ad:
            com.umeng.commonsdk.internal.crash.UMCrashManager.reportCrash(r9, r0)     // Catch:{ all -> 0x00bb }
            if (r10 == 0) goto L_0x00c7
            r10.close()
            goto L_0x00c7
        L_0x00b6:
            r10 = move-exception
            com.umeng.commonsdk.internal.crash.UMCrashManager.reportCrash(r9, r10)
            goto L_0x00c7
        L_0x00bb:
            r0 = move-exception
            if (r10 == 0) goto L_0x00c6
            r10.close()     // Catch:{ all -> 0x00c2 }
            goto L_0x00c6
        L_0x00c2:
            r10 = move-exception
            com.umeng.commonsdk.internal.crash.UMCrashManager.reportCrash(r9, r10)
        L_0x00c6:
            throw r0
        L_0x00c7:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.umeng.analytics.filter.EventList.loadEventListFromFile(android.content.Context, java.io.File):boolean");
    }

    private void deleteDataFile(File file) {
        if (this.mAppContext != null) {
            synchronized (this.mFileLock) {
                if (file.exists()) {
                    file.delete();
                }
            }
        }
    }

    private void saveEventListToFile(Context context, File file) {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            fileOutputStream.write(this.mEventList.getBytes());
            fileOutputStream.close();
        } catch (Throwable th) {
            UMCrashManager.reportCrash(context, th);
        }
    }

    public void register(Context context) {
        if (this.mAppContext == null) {
            this.mAppContext = context.getApplicationContext();
        }
        File file = new File(this.mAppContext.getFilesDir(), this.mEventListName);
        if (!TextUtils.isEmpty(UMEnvelopeBuild.imprintProperty(this.mAppContext, this.mEventListVersionKey, ""))) {
            if (file.exists()) {
                this.mFileLock.doFileOperateion(file, this, 1);
            } else {
                setMD5ClearFlag(true);
            }
        }
        if (UMUtils.isMainProgress(this.mAppContext)) {
            ImprintHandler.getImprintService(this.mAppContext).registPreProcessCallback(this.mEventListKey, this);
            ImprintHandler.getImprintService(this.mAppContext).registImprintCallback(this.mEventListVersionKey, this);
        }
    }

    @Override // com.umeng.analytics.filter.c, com.umeng.commonsdk.statistics.internal.UMImprintChangeCallback
    public void onImprintValueChanged(String str, String str2) {
        if (c.an.equals(str) && str2 == null) {
            UMRTLog.i(UMRTLog.RTLOG_TAG, "--->>> disable black list for ekv.");
            this.mFileLock.doFileOperateion(new File(this.mAppContext.getFilesDir(), this.mEventListName), this, 2);
        }
        if (c.ao.equals(str) && str2 == null) {
            UMRTLog.i(UMRTLog.RTLOG_TAG, "--->>> disable white list for ekv.");
            this.mFileLock.doFileOperateion(new File(this.mAppContext.getFilesDir(), this.mEventListName), this, 2);
        }
    }

    @Override // com.umeng.analytics.filter.c, com.umeng.commonsdk.statistics.internal.UMImprintPreProcessCallback
    public boolean onPreProcessImprintKey(String str, String str2) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return true;
        }
        int length = str2.length();
        UMRTLog.i(UMRTLog.RTLOG_TAG, "--->>> onPreProcessImprintKey: key = " + str + "; len of value=" + length);
        StringBuilder sb = new StringBuilder();
        sb.append("--->>> onPreProcessImprintKey: value = ");
        sb.append(str2);
        UMRTLog.i(UMRTLog.RTLOG_TAG, sb.toString());
        this.mEventList = str2;
        eventListChange();
        File file = new File(this.mAppContext.getFilesDir(), this.mEventListName);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException unused) {
                try {
                    file.createNewFile();
                } catch (IOException e) {
                    UMCrashManager.reportCrash(this.mAppContext, e);
                }
            }
        }
        this.mFileLock.doFileOperateion(file, this, 0);
        setMD5ClearFlag(false);
        return true;
    }

    public String toString() {
        if (TextUtils.isEmpty(this.mEventListName) || TextUtils.isEmpty(this.mEventListKey)) {
            return "Uninitialized EventList.";
        }
        StringBuilder sb = new StringBuilder("[");
        sb.append("EventListName:" + this.mEventListName + Constants.ACCEPT_TIME_SEPARATOR_SP);
        sb.append("listKey:" + this.mEventListKey + Constants.ACCEPT_TIME_SEPARATOR_SP);
        if (!TextUtils.isEmpty(this.mEventList)) {
            sb.append("listKeyValue:" + this.mEventList + "]");
        } else {
            sb.append("listKeyValue:empty,");
        }
        if (!TextUtils.isEmpty(this.mEventListVersionKey)) {
            sb.append("listKeyVer:" + this.mEventListVersionKey + "]");
        } else {
            sb.append("listKeyVer:empty]");
        }
        return sb.toString();
    }

    @Override // com.umeng.commonsdk.utils.FileLockCallback
    public boolean onFileLock(File file, int i) {
        if (i == 0) {
            synchronized (this) {
                saveEventListToFile(this.mAppContext, file);
            }
        } else if (i == 1) {
            synchronized (this) {
                if (loadEventListFromFile(this.mAppContext, file)) {
                    UMRTLog.i(UMRTLog.RTLOG_TAG, "--->>> find event list data file, load it.");
                } else {
                    UMRTLog.i(UMRTLog.RTLOG_TAG, "--->>> can't find event list file.");
                }
            }
        } else if (i == 2) {
            synchronized (this) {
                this.mEventList = null;
                deleteDataFile(file);
            }
        }
        return true;
    }
}
