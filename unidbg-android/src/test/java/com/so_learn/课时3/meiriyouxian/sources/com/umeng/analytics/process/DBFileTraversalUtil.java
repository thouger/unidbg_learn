package com.umeng.analytics.process;

import com.umeng.commonsdk.debug.UMRTLog;
import com.umeng.commonsdk.utils.FileLockCallback;
import com.umeng.commonsdk.utils.FileLockUtil;
import java.io.File;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DBFileTraversalUtil {
    private static ExecutorService a = Executors.newSingleThreadExecutor();
    private static FileLockUtil b = new FileLockUtil();

    public interface a {
        void a();
    }

    public static void traverseDBFiles(String str, FileLockCallback fileLockCallback, a aVar) {
        File file = new File(str);
        if (file.exists() && file.isDirectory()) {
            a.execute(new AnonymousClass1(file, fileLockCallback, aVar));
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: com.umeng.analytics.process.DBFileTraversalUtil$1  reason: invalid class name */
    public static class AnonymousClass1 implements Runnable {
        final /* synthetic */ File a;
        final /* synthetic */ FileLockCallback b;
        final /* synthetic */ a c;

        AnonymousClass1(File file, FileLockCallback fileLockCallback, a aVar) {
            this.a = file;
            this.b = fileLockCallback;
            this.c = aVar;
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                File[] listFiles = this.a.listFiles();
                for (File file : listFiles) {
                    if (file.getName().endsWith(a.d)) {
                        DBFileTraversalUtil.b.doFileOperateion(file, this.b);
                        UMRTLog.i(UMRTLog.RTLOG_TAG, "--->>> file: " + file.getName());
                    }
                }
                if (this.c != null) {
                    this.c.a();
                }
            } catch (Throwable unused) {
            }
            UMRTLog.i(UMRTLog.RTLOG_TAG, "--->>> end *** ");
        }
    }
}
