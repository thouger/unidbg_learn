package com.umeng.commonsdk.internal.utils;

import android.os.Process;
import com.huawei.hms.framework.common.ContainerUtils;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;

/* compiled from: ProcessUtil */
public class g {
    private static final String a = "\n";
    private static final byte[] b = "\nexit\n".getBytes();
    private static byte[] c = new byte[32];

    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:77:? */
    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:87:? */
    /* JADX DEBUG: Multi-variable search result rejected for r5v0, resolved type: java.io.BufferedReader */
    /* JADX DEBUG: Multi-variable search result rejected for r4v0, resolved type: java.io.InputStreamReader */
    /* JADX DEBUG: Multi-variable search result rejected for r5v1, resolved type: java.io.BufferedReader */
    /* JADX DEBUG: Multi-variable search result rejected for r4v1, resolved type: java.io.InputStreamReader */
    /* JADX DEBUG: Multi-variable search result rejected for r5v2, resolved type: java.io.BufferedReader */
    /* JADX DEBUG: Multi-variable search result rejected for r5v3, resolved type: java.io.BufferedReader */
    /* JADX DEBUG: Multi-variable search result rejected for r4v4, resolved type: java.io.InputStreamReader */
    /* JADX DEBUG: Multi-variable search result rejected for r5v4, resolved type: java.io.BufferedReader */
    /* JADX DEBUG: Multi-variable search result rejected for r4v5, resolved type: java.io.InputStreamReader */
    /* JADX DEBUG: Multi-variable search result rejected for r5v5, resolved type: java.io.BufferedReader */
    /* JADX DEBUG: Multi-variable search result rejected for r5v12, resolved type: java.io.BufferedReader */
    /* JADX DEBUG: Multi-variable search result rejected for r5v13, resolved type: java.io.BufferedReader */
    /* JADX DEBUG: Multi-variable search result rejected for r4v21, resolved type: java.io.InputStreamReader */
    /* JADX DEBUG: Multi-variable search result rejected for r4v22, resolved type: java.io.InputStreamReader */
    /* JADX DEBUG: Multi-variable search result rejected for r4v23, resolved type: java.io.InputStreamReader */
    /* JADX DEBUG: Multi-variable search result rejected for r4v24, resolved type: java.io.InputStreamReader */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x0061, code lost:
        if (r9 != null) goto L_0x0063;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x0063, code lost:
        c(r9);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x0068, code lost:
        r0 = th;
        r4 = r4;
        r5 = r5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:78:0x00c6, code lost:
        if (r9 != null) goto L_0x0063;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:88:0x00d3, code lost:
        if (r9 != null) goto L_0x0063;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:89:0x00d6, code lost:
        if (r7 != null) goto L_0x00d9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:90:0x00d8, code lost:
        return null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:92:0x00dd, code lost:
        return r7.toString();
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x0068 A[ExcHandler: all (th java.lang.Throwable), Splitter:B:13:0x0032] */
    /* JADX WARNING: Removed duplicated region for block: B:67:0x00b8  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String a(java.lang.String... r9) {
        /*
        // Method dump skipped, instructions count: 222
        */
        throw new UnsupportedOperationException("Method not decompiled: com.umeng.commonsdk.internal.utils.g.a(java.lang.String[]):java.lang.String");
    }

    private static void a(OutputStream outputStream, InputStream inputStream, InputStream inputStream2, InputStreamReader inputStreamReader, BufferedReader bufferedReader) {
        if (outputStream != null) {
            try {
                outputStream.close();
            } catch (IOException unused) {
            }
        }
        if (inputStream != null) {
            try {
                inputStream.close();
            } catch (IOException unused2) {
            }
        }
        if (inputStream2 != null) {
            try {
                inputStream2.close();
            } catch (IOException unused3) {
            }
        }
        if (inputStreamReader != null) {
            try {
                inputStreamReader.close();
            } catch (IOException unused4) {
            }
        }
        if (bufferedReader != null) {
            try {
                bufferedReader.close();
            } catch (IOException unused5) {
            }
        }
    }

    private static void a(Process process) {
        int b2 = b(process);
        if (b2 != 0) {
            try {
                Process.killProcess(b2);
            } catch (Exception unused) {
                try {
                    process.destroy();
                } catch (Exception unused2) {
                }
            }
        }
    }

    private static int b(Process process) {
        String obj = process.toString();
        try {
            return Integer.parseInt(obj.substring(obj.indexOf(ContainerUtils.KEY_VALUE_DELIMITER) + 1, obj.indexOf("]")));
        } catch (Exception unused) {
            return 0;
        }
    }

    private static void c(Process process) {
        if (process != null) {
            try {
                if (process.exitValue() != 0) {
                    a(process);
                }
            } catch (IllegalThreadStateException unused) {
                a(process);
            }
        }
    }
}
