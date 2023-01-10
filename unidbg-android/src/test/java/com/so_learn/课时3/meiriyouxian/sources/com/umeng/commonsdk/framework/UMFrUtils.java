package com.umeng.commonsdk.framework;

import android.app.ActivityManager;
import android.app.backup.FullBackup;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Process;
import android.text.TextUtils;
import com.umeng.analytics.pro.ai;
import com.umeng.commonsdk.framework.UMLogDataProtocol;
import com.umeng.commonsdk.internal.crash.UMCrashManager;
import com.umeng.commonsdk.statistics.common.ULog;
import com.umeng.commonsdk.statistics.internal.PreferenceWrapper;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

public class UMFrUtils {
    private static final String KEY_LAST_INSTANT_SUCC_BUILD_TIME = "last_instant_build_time";
    private static final String KEY_LAST_SUCC_BUILD_TIME = "last_successful_build_time";
    private static String mDefaultEnvelopeDir = "envelope";
    private static String mDefaultEnvelopeDirPath = null;
    private static Object mEnvelopeBuildTimeLock = new Object();
    private static Object mEnvelopeFileLock = new Object();
    private static String sCurrentProcessName = "";

    /* JADX WARNING: Removed duplicated region for block: B:15:0x0038 A[SYNTHETIC, Splitter:B:15:0x0038] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static java.lang.String getProcessName(int r5) {
        /*
            r0 = 0
            java.io.BufferedReader r1 = new java.io.BufferedReader     // Catch:{ all -> 0x0035 }
            java.io.FileReader r2 = new java.io.FileReader     // Catch:{ all -> 0x0035 }
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ all -> 0x0035 }
            r3.<init>()     // Catch:{ all -> 0x0035 }
            java.lang.String r4 = "/proc/"
            r3.append(r4)     // Catch:{ all -> 0x0035 }
            r3.append(r5)     // Catch:{ all -> 0x0035 }
            java.lang.String r5 = "/cmdline"
            r3.append(r5)     // Catch:{ all -> 0x0035 }
            java.lang.String r5 = r3.toString()     // Catch:{ all -> 0x0035 }
            r2.<init>(r5)     // Catch:{ all -> 0x0035 }
            r1.<init>(r2)     // Catch:{ all -> 0x0035 }
            java.lang.String r5 = r1.readLine()     // Catch:{ all -> 0x0036 }
            boolean r2 = android.text.TextUtils.isEmpty(r5)     // Catch:{ all -> 0x0036 }
            if (r2 != 0) goto L_0x0031
            java.lang.String r5 = r5.trim()     // Catch:{ all -> 0x0036 }
        L_0x0031:
            r1.close()     // Catch:{ all -> 0x0034 }
        L_0x0034:
            return r5
        L_0x0035:
            r1 = r0
        L_0x0036:
            if (r1 == 0) goto L_0x003b
            r1.close()     // Catch:{ all -> 0x003b }
        L_0x003b:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.umeng.commonsdk.framework.UMFrUtils.getProcessName(int):java.lang.String");
    }

    public static String getCurrentProcessName(Context context) {
        List<ActivityManager.RunningAppProcessInfo> runningAppProcesses;
        if (TextUtils.isEmpty(sCurrentProcessName)) {
            try {
                int myPid = Process.myPid();
                String processName = getProcessName(myPid);
                if (!TextUtils.isEmpty(processName)) {
                    sCurrentProcessName = processName;
                } else {
                    ActivityManager activityManager = (ActivityManager) context.getSystemService("activity");
                    if (activityManager != null && (runningAppProcesses = activityManager.getRunningAppProcesses()) != null && runningAppProcesses.size() > 0) {
                        Iterator<ActivityManager.RunningAppProcessInfo> it2 = runningAppProcesses.iterator();
                        while (true) {
                            if (!it2.hasNext()) {
                                break;
                            }
                            ActivityManager.RunningAppProcessInfo next = it2.next();
                            if (next.pid == myPid) {
                                sCurrentProcessName = next.processName;
                                break;
                            }
                        }
                    }
                }
            } catch (Throwable th) {
                UMCrashManager.reportCrash(context.getApplicationContext(), th);
            }
        }
        return sCurrentProcessName;
    }

    public static String getSubProcessName(Context context) {
        String str = "";
        try {
            String currentProcessName = getCurrentProcessName(context);
            int indexOf = currentProcessName.indexOf(":");
            if (indexOf >= 0) {
                str = currentProcessName.substring(indexOf + 1);
            }
            if (indexOf < 0) {
                String packageName = context.getPackageName();
                if (currentProcessName.length() > packageName.length()) {
                    currentProcessName = currentProcessName.substring(packageName.length() + 1, currentProcessName.length());
                }
            } else {
                currentProcessName = str;
            }
            return currentProcessName;
        } catch (Throwable th) {
            UMCrashManager.reportCrash(context.getApplicationContext(), th);
            return str;
        }
    }

    private static boolean checkPermission(Context context, String str) {
        if (context == null) {
            return false;
        }
        Context applicationContext = context.getApplicationContext();
        if (Build.VERSION.SDK_INT >= 23) {
            try {
                if (((Integer) Class.forName("android.content.Context").getMethod("checkSelfPermission", String.class).invoke(context, str)).intValue() != 0) {
                    return false;
                }
            } catch (Throwable th) {
                UMCrashManager.reportCrash(applicationContext, th);
                return false;
            }
        } else {
            try {
                if (applicationContext.getPackageManager().checkPermission(str, applicationContext.getPackageName()) != 0) {
                    return false;
                }
            } catch (Throwable th2) {
                UMCrashManager.reportCrash(applicationContext, th2);
                return false;
            }
        }
        return true;
    }

    public static boolean isOnline(Context context) {
        ConnectivityManager connectivityManager;
        NetworkInfo activeNetworkInfo;
        try {
            if (!checkPermission(context, "android.permission.ACCESS_NETWORK_STATE") || (connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE)) == null || (activeNetworkInfo = connectivityManager.getActiveNetworkInfo()) == null) {
                return false;
            }
            return activeNetworkInfo.isConnectedOrConnecting();
        } catch (Throwable th) {
            UMCrashManager.reportCrash(context.getApplicationContext(), th);
            return false;
        }
    }

    public static int envelopeFileNumber(Context context) {
        String[] list;
        if (context != null) {
            try {
                File file = new File(getEnvelopeDirPath(context));
                synchronized (mEnvelopeFileLock) {
                    if (file.isDirectory() && (list = file.list()) != null) {
                        return list.length;
                    }
                }
            } catch (Throwable th) {
                UMCrashManager.reportCrash(context, th);
            }
        }
        return 0;
    }

    private static long getDistanceDays(long j, long j2) {
        return (j < j2 ? j2 - j : j - j2) / 86400000;
    }

    public static void removeRedundantEnvelopeFiles(Context context, int i) {
        File file = new File(getEnvelopeDirPath(context));
        synchronized (mEnvelopeFileLock) {
            File[] listFiles = file.listFiles();
            if (listFiles != null) {
                if (listFiles.length > i) {
                    Arrays.sort(listFiles, new AnonymousClass1());
                    if (listFiles.length > i) {
                        for (int i2 = 0; i2 < listFiles.length - i; i2++) {
                            try {
                                if (!listFiles[i2].delete()) {
                                    ULog.d("--->>> remove [" + i2 + "] file fail.");
                                }
                            } catch (Throwable th) {
                                UMCrashManager.reportCrash(context, th);
                            }
                        }
                    }
                }
            }
        }
    }

    /* renamed from: com.umeng.commonsdk.framework.UMFrUtils$1  reason: invalid class name */
    static class AnonymousClass1 implements Comparator<File> {
        AnonymousClass1() {
        }

        /* renamed from: a */
        public int compare(File file, File file2) {
            int i = ((file.lastModified() - file2.lastModified()) > 0 ? 1 : ((file.lastModified() - file2.lastModified()) == 0 ? 0 : -1));
            if (i > 0) {
                return 1;
            }
            return i == 0 ? 0 : -1;
        }
    }

    public static File getEnvelopeFile(Context context) {
        if (context == null) {
            return null;
        }
        File file = new File(getEnvelopeDirPath(context));
        synchronized (mEnvelopeFileLock) {
            File[] listFiles = file.listFiles();
            if (listFiles != null) {
                if (listFiles.length != 0) {
                    Arrays.sort(listFiles, new AnonymousClass2());
                    return listFiles[0];
                }
            }
            return null;
        }
    }

    /* renamed from: com.umeng.commonsdk.framework.UMFrUtils$2  reason: invalid class name */
    static class AnonymousClass2 implements Comparator<File> {
        AnonymousClass2() {
        }

        /* renamed from: a */
        public int compare(File file, File file2) {
            int i = ((file.lastModified() - file2.lastModified()) > 0 ? 1 : ((file.lastModified() - file2.lastModified()) == 0 ? 0 : -1));
            if (i > 0) {
                return 1;
            }
            return i == 0 ? 0 : -1;
        }
    }

    public static void syncLegacyEnvelopeIfNeeded(Context context) {
        if (context != null) {
            try {
                String legacyEnvelopeDir = getLegacyEnvelopeDir(context);
                if (!TextUtils.isEmpty(legacyEnvelopeDir) && !legacyEnvelopeDir.equals(mDefaultEnvelopeDir)) {
                    File file = new File(context.getFilesDir().getAbsolutePath() + "/." + legacyEnvelopeDir);
                    if (file.exists()) {
                        File[] listFiles = file.listFiles();
                        if (listFiles == null || listFiles.length == 0) {
                            try {
                                if (file.isDirectory()) {
                                    file.delete();
                                }
                            } catch (Throwable th) {
                                UMCrashManager.reportCrash(context, th);
                            }
                        } else {
                            try {
                                String envelopeDirPath = getEnvelopeDirPath(context);
                                for (int i = 0; i < listFiles.length; i++) {
                                    File file2 = listFiles[i];
                                    file2.renameTo(new File(envelopeDirPath + File.separator + listFiles[i].getName()));
                                }
                                if (file.isDirectory()) {
                                    file.delete();
                                }
                            } catch (Throwable th2) {
                                UMCrashManager.reportCrash(context, th2);
                            }
                        }
                    }
                }
            } catch (Throwable th3) {
                UMCrashManager.reportCrash(context, th3);
            }
        }
    }

    public static String getLegacyEnvelopeDir(Context context) {
        try {
            String processName = getProcessName(Process.myPid());
            if (!TextUtils.isEmpty(processName)) {
                String replace = processName.replace(':', '_');
                ULog.d("--->>> getEnvelopeDir: use current process name as envelope directory.");
                return replace;
            }
            ActivityManager activityManager = (ActivityManager) context.getSystemService("activity");
            if (activityManager != null) {
                List<ActivityManager.RunningAppProcessInfo> runningAppProcesses = activityManager.getRunningAppProcesses();
                if (runningAppProcesses == null) {
                    ULog.d("--->>> getEnvelopeDir: can't get process name, use default envelope directory.");
                    return mDefaultEnvelopeDir;
                } else if (runningAppProcesses.size() == 0) {
                    return mDefaultEnvelopeDir;
                } else {
                    try {
                        for (ActivityManager.RunningAppProcessInfo runningAppProcessInfo : runningAppProcesses) {
                            if (runningAppProcessInfo.pid == Process.myPid()) {
                                String replace2 = runningAppProcessInfo.processName.replace(':', '_');
                                ULog.d("--->>> getEnvelopeDir: use current process name as envelope directory.");
                                return replace2;
                            }
                        }
                    } catch (Throwable th) {
                        UMCrashManager.reportCrash(context, th);
                    }
                }
            }
            return mDefaultEnvelopeDir;
        } catch (Throwable th2) {
            UMCrashManager.reportCrash(context, th2);
        }
    }

    public static String getEnvelopeDirPath(Context context) {
        String str;
        synchronized (mEnvelopeFileLock) {
            try {
                if (mDefaultEnvelopeDirPath == null) {
                    mDefaultEnvelopeDirPath = context.getFilesDir().getAbsolutePath() + File.separator + "." + mDefaultEnvelopeDir;
                }
                File file = new File(mDefaultEnvelopeDirPath);
                if (!file.exists() && !file.mkdir()) {
                    ULog.d("--->>> Create Envelope Directory failed!!!");
                }
            } catch (Throwable th) {
                UMCrashManager.reportCrash(context, th);
            }
            str = mDefaultEnvelopeDirPath;
        }
        return str;
    }

    public static long getLastSuccessfulBuildTime(Context context) {
        long j;
        synchronized (mEnvelopeBuildTimeLock) {
            j = PreferenceWrapper.getDefault(context).getLong(KEY_LAST_SUCC_BUILD_TIME, 0);
        }
        return j;
    }

    public static long getLastInstantBuildTime(Context context) {
        long j;
        synchronized (mEnvelopeBuildTimeLock) {
            j = PreferenceWrapper.getDefault(context).getLong(KEY_LAST_INSTANT_SUCC_BUILD_TIME, 0);
        }
        return j;
    }

    private static void updateLastSuccessfulBuildTime(Context context) {
        synchronized (mEnvelopeBuildTimeLock) {
            SharedPreferences sharedPreferences = PreferenceWrapper.getDefault(context);
            sharedPreferences.edit().putLong(KEY_LAST_SUCC_BUILD_TIME, System.currentTimeMillis()).commit();
        }
    }

    private static void updateLastInstantBuildTime(Context context) {
        synchronized (mEnvelopeBuildTimeLock) {
            SharedPreferences sharedPreferences = PreferenceWrapper.getDefault(context);
            sharedPreferences.edit().putLong(KEY_LAST_INSTANT_SUCC_BUILD_TIME, System.currentTimeMillis()).commit();
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:28:0x005c A[SYNTHETIC, Splitter:B:28:0x005c] */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x0068 A[SYNTHETIC, Splitter:B:35:0x0068] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static int saveEnvelopeFile(android.content.Context r5, java.lang.String r6, byte[] r7) {
        /*
            r0 = 101(0x65, float:1.42E-43)
            if (r7 != 0) goto L_0x0005
            return r0
        L_0x0005:
            java.io.File r1 = new java.io.File
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = getEnvelopeDirPath(r5)
            r2.append(r3)
            java.lang.String r3 = java.io.File.separator
            r2.append(r3)
            r2.append(r6)
            java.lang.String r2 = r2.toString()
            r1.<init>(r2)
            java.lang.Object r2 = com.umeng.commonsdk.framework.UMFrUtils.mEnvelopeFileLock
            monitor-enter(r2)
            r3 = 0
            java.io.FileOutputStream r4 = new java.io.FileOutputStream     // Catch:{ IOException -> 0x0056 }
            r4.<init>(r1)     // Catch:{ IOException -> 0x0056 }
            r4.write(r7)     // Catch:{ IOException -> 0x0051, all -> 0x004e }
            r4.close()     // Catch:{ IOException -> 0x0051, all -> 0x004e }
            com.umeng.commonsdk.statistics.internal.a r7 = com.umeng.commonsdk.statistics.internal.a.a(r5)     // Catch:{ all -> 0x0071 }
            boolean r7 = r7.a(r6)     // Catch:{ all -> 0x0071 }
            com.umeng.commonsdk.statistics.internal.a r0 = com.umeng.commonsdk.statistics.internal.a.a(r5)     // Catch:{ all -> 0x0071 }
            boolean r6 = r0.b(r6)     // Catch:{ all -> 0x0071 }
            if (r7 == 0) goto L_0x0046
            updateLastSuccessfulBuildTime(r5)     // Catch:{ all -> 0x0071 }
        L_0x0046:
            if (r6 == 0) goto L_0x004b
            updateLastInstantBuildTime(r5)     // Catch:{ all -> 0x0071 }
        L_0x004b:
            r5 = 0
            monitor-exit(r2)     // Catch:{ all -> 0x0071 }
            return r5
        L_0x004e:
            r6 = move-exception
            r3 = r4
            goto L_0x0066
        L_0x0051:
            r6 = move-exception
            r3 = r4
            goto L_0x0057
        L_0x0054:
            r6 = move-exception
            goto L_0x0066
        L_0x0056:
            r6 = move-exception
        L_0x0057:
            com.umeng.commonsdk.internal.crash.UMCrashManager.reportCrash(r5, r6)     // Catch:{ all -> 0x0054 }
            if (r3 == 0) goto L_0x0064
            r3.close()     // Catch:{ all -> 0x0060 }
            goto L_0x0064
        L_0x0060:
            r6 = move-exception
            com.umeng.commonsdk.internal.crash.UMCrashManager.reportCrash(r5, r6)
        L_0x0064:
            monitor-exit(r2)
            return r0
        L_0x0066:
            if (r3 == 0) goto L_0x0070
            r3.close()     // Catch:{ all -> 0x006c }
            goto L_0x0070
        L_0x006c:
            r7 = move-exception
            com.umeng.commonsdk.internal.crash.UMCrashManager.reportCrash(r5, r7)
        L_0x0070:
            throw r6
        L_0x0071:
            r5 = move-exception
            monitor-exit(r2)
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.umeng.commonsdk.framework.UMFrUtils.saveEnvelopeFile(android.content.Context, java.lang.String, byte[]):int");
    }

    public static boolean removeEnvelopeFile(File file) {
        Context appContext = UMModuleRegister.getAppContext();
        synchronized (mEnvelopeFileLock) {
            if (file != null) {
                try {
                    if (file.exists()) {
                        return file.delete();
                    }
                } catch (Throwable th) {
                    UMCrashManager.reportCrash(appContext, th);
                }
            }
            return true;
        }
    }

    public static byte[] toByteArray(String str) throws IOException {
        FileChannel fileChannel;
        Throwable th;
        IOException e;
        byte[] bArr;
        Context appContext = UMModuleRegister.getAppContext();
        synchronized (mEnvelopeFileLock) {
            try {
                fileChannel = new RandomAccessFile(str, FullBackup.ROOT_TREE_TOKEN).getChannel();
                try {
                    MappedByteBuffer load = fileChannel.map(FileChannel.MapMode.READ_ONLY, 0, fileChannel.size()).load();
                    bArr = new byte[((int) fileChannel.size())];
                    if (load.remaining() > 0) {
                        load.get(bArr, 0, load.remaining());
                    }
                    try {
                        fileChannel.close();
                    } catch (Throwable th2) {
                        UMCrashManager.reportCrash(appContext, th2);
                    }
                } catch (IOException e2) {
                    e = e2;
                    try {
                        UMCrashManager.reportCrash(appContext, e);
                        throw e;
                    } catch (Throwable th3) {
                        th = th3;
                        try {
                            fileChannel.close();
                        } catch (Throwable th4) {
                            UMCrashManager.reportCrash(appContext, th4);
                        }
                        throw th;
                    }
                }
            } catch (IOException e3) {
                e = e3;
                fileChannel = null;
                UMCrashManager.reportCrash(appContext, e);
                throw e;
            } catch (Throwable th5) {
                th = th5;
                fileChannel = null;
                fileChannel.close();
                throw th;
            }
        }
        return bArr;
    }

    public static boolean hasEnvelopeFile(Context context, UMLogDataProtocol.UMBusinessType uMBusinessType) {
        String str = uMBusinessType == UMLogDataProtocol.UMBusinessType.U_INTERNAL ? "i" : "a";
        if (uMBusinessType == UMLogDataProtocol.UMBusinessType.U_ZeroEnv) {
            str = ai.aB;
        }
        String envelopeDirPath = getEnvelopeDirPath(context);
        if (envelopeDirPath == null) {
            return false;
        }
        File file = new File(envelopeDirPath);
        synchronized (mEnvelopeFileLock) {
            try {
                File[] listFiles = file.listFiles();
                if (listFiles != null) {
                    if (listFiles.length != 0) {
                        for (File file2 : listFiles) {
                            if (file2.getName().startsWith(str)) {
                                return true;
                            }
                        }
                        return false;
                    }
                }
                return false;
            } catch (Throwable th) {
                UMCrashManager.reportCrash(context, th);
            }
        }
    }
}
