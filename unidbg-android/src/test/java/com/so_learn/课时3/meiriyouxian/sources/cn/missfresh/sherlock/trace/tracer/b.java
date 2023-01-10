package cn.missfresh.sherlock.trace.tracer;

import android.app.backup.FullBackup;
import android.content.Context;
import android.net.wifi.WifiEnterpriseConfig;
import android.os.Build;
import android.os.Process;
import android.text.TextUtils;
import cn.missfresh.sherlock.tool.Utils;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.RandomAccessFile;

/* compiled from: CpuMananger */
public class b {
    private RandomAccessFile a;
    private RandomAccessFile b;
    private Long c;
    private Long d;

    /* compiled from: CpuMananger */
    /* access modifiers changed from: private */
    /* renamed from: cn.missfresh.sherlock.trace.tracer.b$b  reason: collision with other inner class name */
    public static class C0052b {
        private static b a = new b();
    }

    public static b a() {
        return C0052b.a;
    }

    private float b() {
        Process process = null;
        try {
            process = Runtime.getRuntime().exec("top -n 1");
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            int i = -1;
            while (true) {
                String readLine = bufferedReader.readLine();
                if (readLine != null) {
                    String trim = readLine.trim();
                    if (!TextUtils.isEmpty(trim)) {
                        int a2 = a(trim);
                        if (a2 != -1) {
                            i = a2;
                        } else if (!trim.startsWith(String.valueOf(Process.myPid()))) {
                            continue;
                        } else if (i != -1) {
                            String[] split = trim.split("\\s+");
                            if (split.length > i) {
                                String str = split[i];
                                if (str.endsWith("%")) {
                                    str = str.substring(0, str.lastIndexOf("%"));
                                }
                                float parseFloat = Float.parseFloat(str) / ((float) Runtime.getRuntime().availableProcessors());
                                if (process != null) {
                                    process.destroy();
                                }
                                return parseFloat;
                            }
                        }
                    }
                } else if (process == null) {
                    return 0.0f;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            if (0 == 0) {
                return 0.0f;
            }
        } catch (Throwable th) {
            if (0 != 0) {
                process.destroy();
            }
            throw th;
        }
        process.destroy();
        return 0.0f;
    }

    public float a(Context context) {
        try {
            if (!Utils.h(context)) {
                return 0.0f;
            }
            if (Build.VERSION.SDK_INT >= 26) {
                return b();
            }
            return c();
        } catch (Exception e) {
            e.printStackTrace();
            return 0.0f;
        }
    }

    private b() {
    }

    private int a(String str) {
        if (!str.contains("CPU")) {
            return -1;
        }
        String[] split = str.split("\\s+");
        for (int i = 0; i < split.length; i++) {
            if (split[i].contains("CPU")) {
                return i;
            }
        }
        return -1;
    }

    private float c() {
        long parseLong;
        long parseLong2;
        try {
            if (this.a != null) {
                if (this.b != null) {
                    this.a.seek(0);
                    this.b.seek(0);
                    String readLine = this.a.readLine();
                    String readLine2 = this.b.readLine();
                    String[] split = readLine.split(WifiEnterpriseConfig.CA_CERT_ALIAS_DELIMITER);
                    String[] split2 = readLine2.split(WifiEnterpriseConfig.CA_CERT_ALIAS_DELIMITER);
                    parseLong = Long.parseLong(split[2]) + Long.parseLong(split[3]) + Long.parseLong(split[4]) + Long.parseLong(split[5]) + Long.parseLong(split[6]) + Long.parseLong(split[7]) + Long.parseLong(split[8]);
                    parseLong2 = Long.parseLong(split2[13]) + Long.parseLong(split2[14]);
                    if (this.c == null || this.d != null) {
                        float longValue = (((float) (parseLong2 - this.d.longValue())) / ((float) (parseLong - this.c.longValue()))) * 100.0f;
                        this.c = Long.valueOf(parseLong);
                        this.d = Long.valueOf(parseLong2);
                        return longValue;
                    }
                    this.c = Long.valueOf(parseLong);
                    this.d = Long.valueOf(parseLong2);
                    return 0.0f;
                }
            }
            this.a = new RandomAccessFile("/proc/stat", FullBackup.ROOT_TREE_TOKEN);
            this.b = new RandomAccessFile("/proc/" + Process.myPid() + "/stat", FullBackup.ROOT_TREE_TOKEN);
            String readLine = this.a.readLine();
            String readLine2 = this.b.readLine();
            String[] split = readLine.split(WifiEnterpriseConfig.CA_CERT_ALIAS_DELIMITER);
            String[] split2 = readLine2.split(WifiEnterpriseConfig.CA_CERT_ALIAS_DELIMITER);
            parseLong = Long.parseLong(split[2]) + Long.parseLong(split[3]) + Long.parseLong(split[4]) + Long.parseLong(split[5]) + Long.parseLong(split[6]) + Long.parseLong(split[7]) + Long.parseLong(split[8]);
            parseLong2 = Long.parseLong(split2[13]) + Long.parseLong(split2[14]);
            if (this.c == null) {
            }
            float longValue = (((float) (parseLong2 - this.d.longValue())) / ((float) (parseLong - this.c.longValue()))) * 100.0f;
            this.c = Long.valueOf(parseLong);
            this.d = Long.valueOf(parseLong2);
            return longValue;
        } catch (Exception e) {
            e.printStackTrace();
            return 0.0f;
        }
    }
}
