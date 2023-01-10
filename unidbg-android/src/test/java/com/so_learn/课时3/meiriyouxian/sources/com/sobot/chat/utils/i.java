package com.sobot.chat.utils;

import android.net.TrafficStats;
import com.umeng.message.util.HttpRequest;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DecimalFormat;

/* compiled from: FileSizeUtil */
public class i {

    /* compiled from: FileSizeUtil */
    public interface a<T> {
        void a(T t);
    }

    public static double a(String str, int i) {
        long j;
        File file = new File(str);
        try {
            if (file.isDirectory()) {
                j = b(file);
            } else {
                j = a(file);
            }
        } catch (Exception e) {
            e.printStackTrace();
            m.c("\u83b7\u53d6\u6587\u4ef6\u5927\u5c0f\u5931\u8d25!");
            j = 0;
        }
        return a(j, i);
    }

    public static void a(String str, a<String> aVar) {
        if (str == null || "".equals(str)) {
            aVar.a("0B");
        }
        new Thread(new AnonymousClass1(str, aVar)).start();
    }

    /* compiled from: FileSizeUtil */
    /* renamed from: com.sobot.chat.utils.i$1  reason: invalid class name */
    static class AnonymousClass1 implements Runnable {
        final /* synthetic */ String a;
        final /* synthetic */ a b;

        AnonymousClass1(String str, a aVar) {
            this.a = str;
            this.b = aVar;
        }

        @Override // java.lang.Runnable
        public void run() {
            URL url;
            Throwable th;
            long j;
            HttpURLConnection httpURLConnection = null;
            try {
                url = new URL(this.a);
            } catch (MalformedURLException e) {
                e.printStackTrace();
                url = null;
            }
            try {
                HttpURLConnection httpURLConnection2 = (HttpURLConnection) url.openConnection();
                try {
                    httpURLConnection2.setRequestMethod("HEAD");
                    httpURLConnection2.setRequestProperty(HttpRequest.HEADER_USER_AGENT, "Mozilla/5.0 (Windows 7; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/47.0.2526.73 Safari/537.36 YNoteCef/5.8.0.1 (Windows)");
                    j = (long) httpURLConnection2.getContentLength();
                    httpURLConnection2.disconnect();
                } catch (IOException unused) {
                    httpURLConnection = httpURLConnection2;
                    try {
                        this.b.a("0B");
                        httpURLConnection.disconnect();
                        j = 0;
                        this.b.a(i.b(j));
                    } catch (Throwable th2) {
                        httpURLConnection2 = httpURLConnection;
                        th = th2;
                        httpURLConnection2.disconnect();
                        throw th;
                    }
                } catch (Throwable th3) {
                    th = th3;
                    httpURLConnection2.disconnect();
                    throw th;
                }
            } catch (IOException unused2) {
                this.b.a("0B");
                httpURLConnection.disconnect();
                j = 0;
                this.b.a(i.b(j));
            }
            this.b.a(i.b(j));
        }
    }

    private static long a(File file) {
        try {
            if (file.exists()) {
                return (long) new FileInputStream(file).available();
            }
            file.createNewFile();
            m.c("\u83b7\u53d6\u6587\u4ef6\u5927\u5c0f->\u6587\u4ef6\u4e0d\u5b58\u5728!");
            return 0;
        } catch (Exception unused) {
            return 0;
        }
    }

    private static long b(File file) throws Exception {
        long j;
        File[] listFiles = file.listFiles();
        long j2 = 0;
        for (int i = 0; i < listFiles.length; i++) {
            if (listFiles[i].isDirectory()) {
                j = b(listFiles[i]);
            } else {
                j = a(listFiles[i]);
            }
            j2 += j;
        }
        return j2;
    }

    /* access modifiers changed from: private */
    public static String b(long j) {
        DecimalFormat decimalFormat = new DecimalFormat("#.00");
        if (j == 0) {
            return "0B";
        }
        if (j < 1024) {
            return decimalFormat.format((double) j) + "B";
        } else if (j < 1048576) {
            return decimalFormat.format(((double) j) / 1024.0d) + "KB";
        } else if (j < TrafficStats.GB_IN_BYTES) {
            return decimalFormat.format(((double) j) / 1048576.0d) + "MB";
        } else {
            return decimalFormat.format(((double) j) / 1.073741824E9d) + "GB";
        }
    }

    private static double a(long j, int i) {
        if (i != 1) {
            if (i == 2) {
                j /= 1024;
            } else if (i == 3) {
                j /= 1048576;
            } else if (i != 4) {
                return 0.0d;
            } else {
                j /= TrafficStats.GB_IN_BYTES;
            }
        }
        return (double) j;
    }
}
