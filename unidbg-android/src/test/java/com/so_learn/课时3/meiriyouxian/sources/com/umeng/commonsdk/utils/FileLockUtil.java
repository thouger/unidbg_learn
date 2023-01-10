package com.umeng.commonsdk.utils;

import java.io.File;
import java.io.IOException;
import java.nio.channels.FileLock;

public class FileLockUtil {
    private final Object lockObject = new Object();

    public void doFileOperateion(File file, FileLockCallback fileLockCallback, Object obj) {
        if (file.exists()) {
            synchronized (this.lockObject) {
                FileLock fileLock = getFileLock(file.getAbsolutePath());
                if (fileLock != null) {
                    try {
                        fileLockCallback.onFileLock(file.getName(), obj);
                        try {
                            fileLock.release();
                            fileLock.channel().close();
                        } catch (IOException e) {
                            e = e;
                        }
                    } catch (Exception e2) {
                        e2.printStackTrace();
                        try {
                            fileLock.release();
                            fileLock.channel().close();
                        } catch (IOException e3) {
                            e = e3;
                        }
                    } catch (Throwable th) {
                        try {
                            fileLock.release();
                            fileLock.channel().close();
                        } catch (IOException e4) {
                            e4.printStackTrace();
                        }
                        throw th;
                    }
                }
            }
        }
        return;
        e.printStackTrace();
    }

    public void doFileOperateion(File file, FileLockCallback fileLockCallback, int i) {
        if (file.exists()) {
            synchronized (this.lockObject) {
                FileLock fileLock = getFileLock(file.getAbsolutePath());
                if (fileLock != null) {
                    try {
                        fileLockCallback.onFileLock(file, i);
                        try {
                            fileLock.release();
                            fileLock.channel().close();
                        } catch (Throwable th) {
                            th = th;
                        }
                    } catch (Exception e) {
                        try {
                            e.printStackTrace();
                            fileLock.release();
                            fileLock.channel().close();
                        } catch (Throwable th2) {
                            th2.printStackTrace();
                        }
                    } catch (Throwable th3) {
                        th = th3;
                    }
                }
            }
            return;
        }
        return;
        th.printStackTrace();
        return;
        throw th;
    }

    public void doFileOperateion(File file, FileLockCallback fileLockCallback) {
        if (file.exists()) {
            synchronized (this.lockObject) {
                FileLock fileLock = getFileLock(file.getAbsolutePath());
                if (fileLock != null) {
                    try {
                        fileLockCallback.onFileLock(file.getName());
                        try {
                            fileLock.release();
                            fileLock.channel().close();
                        } catch (IOException e) {
                            e = e;
                        }
                    } catch (Exception e2) {
                        e2.printStackTrace();
                        try {
                            fileLock.release();
                            fileLock.channel().close();
                        } catch (IOException e3) {
                            e = e3;
                        }
                    } catch (Throwable th) {
                        try {
                            fileLock.release();
                            fileLock.channel().close();
                        } catch (IOException e4) {
                            e4.printStackTrace();
                        }
                        throw th;
                    }
                }
            }
        }
        return;
        e.printStackTrace();
    }

    public void doFileOperateion(String str, FileLockCallback fileLockCallback) {
        File file = new File(str);
        if (file.exists()) {
            synchronized (this.lockObject) {
                FileLock fileLock = getFileLock(str);
                if (fileLock != null) {
                    try {
                        fileLockCallback.onFileLock(file.getName());
                        try {
                            fileLock.release();
                            fileLock.channel().close();
                        } catch (IOException e) {
                            e = e;
                        }
                    } catch (Exception e2) {
                        e2.printStackTrace();
                        try {
                            fileLock.release();
                            fileLock.channel().close();
                        } catch (IOException e3) {
                            e = e3;
                        }
                    } catch (Throwable th) {
                        try {
                            fileLock.release();
                            fileLock.channel().close();
                        } catch (IOException e4) {
                            e4.printStackTrace();
                        }
                        throw th;
                    }
                }
            }
        }
        return;
        e.printStackTrace();
    }

    /* JADX WARNING: Removed duplicated region for block: B:15:0x0023 A[SYNTHETIC, Splitter:B:15:0x0023] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static java.nio.channels.FileLock getFileLock(java.lang.String r3) {
        /*
            r0 = 0
            java.io.RandomAccessFile r1 = new java.io.RandomAccessFile     // Catch:{ FileNotFoundException -> 0x001c, IOException -> 0x0016 }
            java.lang.String r2 = "rw"
            r1.<init>(r3, r2)     // Catch:{ FileNotFoundException -> 0x001c, IOException -> 0x0016 }
            java.nio.channels.FileChannel r3 = r1.getChannel()     // Catch:{ FileNotFoundException -> 0x001c, IOException -> 0x0016 }
            java.nio.channels.FileLock r3 = r3.lock()     // Catch:{ FileNotFoundException -> 0x0014, IOException -> 0x0012 }
            return r3
        L_0x0012:
            r1 = move-exception
            goto L_0x0018
        L_0x0014:
            r1 = move-exception
            goto L_0x001e
        L_0x0016:
            r1 = move-exception
            r3 = r0
        L_0x0018:
            r1.printStackTrace()
            goto L_0x0021
        L_0x001c:
            r1 = move-exception
            r3 = r0
        L_0x001e:
            r1.printStackTrace()
        L_0x0021:
            if (r3 == 0) goto L_0x002b
            r3.close()     // Catch:{ IOException -> 0x0027 }
            goto L_0x002b
        L_0x0027:
            r3 = move-exception
            r3.printStackTrace()
        L_0x002b:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.umeng.commonsdk.utils.FileLockUtil.getFileLock(java.lang.String):java.nio.channels.FileLock");
    }
}
