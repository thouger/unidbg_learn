package cn.missfresh.sherlock.trace;

import android.app.ActivityManager;
import android.app.job.JobInfo;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Point;
import android.os.Build;
import android.util.DisplayMetrics;
import android.util.TimedRemoteCaller;
import android.view.Display;
import android.view.WindowManager;
import cn.missfresh.sherlock.to.DeviceLevel;
import com.umeng.analytics.pro.ai;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.List;

/* compiled from: DeviceInfoHelper */
public class a {
    private static final FileFilter a = new C0045a();

    /* compiled from: DeviceInfoHelper */
    /* renamed from: cn.missfresh.sherlock.trace.a$a  reason: collision with other inner class name */
    static class C0045a implements FileFilter {
        C0045a() {
        }

        @Override // java.io.FileFilter
        public boolean accept(File file) {
            String name = file.getName();
            if (!name.startsWith(ai.w)) {
                return false;
            }
            for (int i = 3; i < name.length(); i++) {
                if (!Character.isDigit(name.charAt(i))) {
                    return false;
                }
            }
            return true;
        }
    }

    public static int a() {
        int i = -1;
        for (int i2 = 0; i2 < b(); i2++) {
            try {
                File file = new File("/sys/devices/system/cpu/cpu" + i2 + "/cpufreq/cpuinfo_max_freq");
                if (file.exists() && file.canRead()) {
                    byte[] bArr = new byte[128];
                    FileInputStream fileInputStream = new FileInputStream(file);
                    try {
                        fileInputStream.read(bArr);
                        int i3 = 0;
                        while (Character.isDigit(bArr[i3]) && i3 < bArr.length) {
                            i3++;
                        }
                        Integer valueOf = Integer.valueOf(Integer.parseInt(new String(bArr, 0, i3)));
                        if (valueOf.intValue() > i) {
                            i = valueOf.intValue();
                        }
                    } catch (NumberFormatException unused) {
                    } catch (Throwable th) {
                        fileInputStream.close();
                        throw th;
                    }
                    fileInputStream.close();
                }
            } catch (IOException unused2) {
                return -1;
            }
        }
        if (i != -1) {
            return i;
        }
        FileInputStream fileInputStream2 = new FileInputStream("/proc/cpuinfo");
        try {
            int a2 = a("cpu MHz", fileInputStream2) * 1000;
            if (a2 <= i) {
                a2 = i;
            }
            return a2;
        } finally {
            fileInputStream2.close();
        }
    }

    public static long a(Context context) {
        if (Build.VERSION.SDK_INT >= 16) {
            ActivityManager.MemoryInfo memoryInfo = new ActivityManager.MemoryInfo();
            ((ActivityManager) context.getSystemService("activity")).getMemoryInfo(memoryInfo);
            return memoryInfo.totalMem;
        }
        try {
            FileInputStream fileInputStream = new FileInputStream("/proc/meminfo");
            try {
                return ((long) a("MemTotal", fileInputStream)) * 1024;
            } finally {
                fileInputStream.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
            return -1;
        }
    }

    public static int b() {
        if (Build.VERSION.SDK_INT <= 10) {
            return 1;
        }
        try {
            int a2 = a("/sys/devices/system/cpu/possible");
            if (a2 == -1) {
                a2 = a("/sys/devices/system/cpu/present");
            }
            return a2 == -1 ? new File("/sys/devices/system/cpu/").listFiles(a).length : a2;
        } catch (NullPointerException | SecurityException unused) {
            return -1;
        }
    }

    private static int b(String str) {
        if (str == null || !str.matches("0-[\\d]+$")) {
            return -1;
        }
        return Integer.valueOf(str.substring(2)).intValue() + 1;
    }

    public static int d(Context context) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        if (windowManager == null) {
            return 0;
        }
        windowManager.getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.widthPixels;
    }

    public static int f(Context context) {
        Display defaultDisplay = ((WindowManager) context.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        try {
            Class.forName("android.view.Display").getMethod("getRealMetrics", DisplayMetrics.class).invoke(defaultDisplay, displayMetrics);
            return displayMetrics.heightPixels;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public static int g(Context context) {
        Resources resources = context.getResources();
        return resources.getDimensionPixelSize(resources.getIdentifier("status_bar_height", "dimen", "android"));
    }

    public static double h(Context context) {
        int i;
        int i2;
        try {
            Display defaultDisplay = ((WindowManager) context.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
            DisplayMetrics displayMetrics = new DisplayMetrics();
            defaultDisplay.getMetrics(displayMetrics);
            if (Build.VERSION.SDK_INT >= 17) {
                Point point = new Point();
                defaultDisplay.getRealSize(point);
                i = point.x;
                i2 = point.y;
            } else if (Build.VERSION.SDK_INT >= 17 || Build.VERSION.SDK_INT < 14) {
                i = displayMetrics.widthPixels;
                i2 = displayMetrics.heightPixels;
            } else {
                Method method = Display.class.getMethod("getRawHeight", new Class[0]);
                int intValue = ((Integer) Display.class.getMethod("getRawWidth", new Class[0]).invoke(defaultDisplay, new Object[0])).intValue();
                i2 = ((Integer) method.invoke(defaultDisplay, new Object[0])).intValue();
                i = intValue;
            }
            float f = (float) i;
            float f2 = (f / displayMetrics.xdpi) * (f / displayMetrics.xdpi);
            float f3 = (float) i2;
            return a(Math.sqrt((double) (f2 + ((f3 / displayMetrics.ydpi) * (f3 / displayMetrics.ydpi)))), 1);
        } catch (Exception e) {
            e.printStackTrace();
            return 0.0d;
        }
    }

    public static float c(Context context) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((WindowManager) context.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.density;
    }

    public static String c() {
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader("/proc/cpuinfo"));
            String str = "";
            while (true) {
                String readLine = bufferedReader.readLine();
                if (readLine == null) {
                    break;
                }
                str = readLine;
            }
            if (str.contains("Hardware")) {
                return str.split(":\\s+", 2)[1];
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e2) {
            e2.printStackTrace();
        }
        return Build.HARDWARE;
    }

    public static int d() {
        int a2 = a();
        if (a2 == -1) {
            return -1;
        }
        return a2 / 10000;
    }

    public static int e(Context context) {
        return f(context) - g(context);
    }

    /* JADX WARNING: Removed duplicated region for block: B:15:0x0027 A[SYNTHETIC, Splitter:B:15:0x0027] */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x002e A[SYNTHETIC, Splitter:B:22:0x002e] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static int a(java.lang.String r2) {
        /*
            r0 = 0
            java.io.FileInputStream r1 = new java.io.FileInputStream     // Catch:{ IOException -> 0x002b, all -> 0x0024 }
            r1.<init>(r2)     // Catch:{ IOException -> 0x002b, all -> 0x0024 }
            java.io.BufferedReader r2 = new java.io.BufferedReader     // Catch:{ IOException -> 0x0022, all -> 0x001f }
            java.io.InputStreamReader r0 = new java.io.InputStreamReader     // Catch:{ IOException -> 0x0022, all -> 0x001f }
            r0.<init>(r1)     // Catch:{ IOException -> 0x0022, all -> 0x001f }
            r2.<init>(r0)     // Catch:{ IOException -> 0x0022, all -> 0x001f }
            java.lang.String r0 = r2.readLine()     // Catch:{ IOException -> 0x0022, all -> 0x001f }
            r2.close()     // Catch:{ IOException -> 0x0022, all -> 0x001f }
            int r2 = b(r0)     // Catch:{ IOException -> 0x0022, all -> 0x001f }
            r1.close()     // Catch:{ IOException -> 0x001e }
        L_0x001e:
            return r2
        L_0x001f:
            r2 = move-exception
            r0 = r1
            goto L_0x0025
        L_0x0022:
            r0 = r1
            goto L_0x002b
        L_0x0024:
            r2 = move-exception
        L_0x0025:
            if (r0 == 0) goto L_0x002a
            r0.close()     // Catch:{ IOException -> 0x002a }
        L_0x002a:
            throw r2
        L_0x002b:
            r2 = -1
            if (r0 == 0) goto L_0x0031
            r0.close()     // Catch:{ IOException -> 0x0031 }
        L_0x0031:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.missfresh.sherlock.trace.a.a(java.lang.String):int");
    }

    private static int a(String str, FileInputStream fileInputStream) {
        byte[] bArr = new byte[1024];
        try {
            int read = fileInputStream.read(bArr);
            int i = 0;
            while (i < read) {
                if (bArr[i] == 10 || i == 0) {
                    if (bArr[i] == 10) {
                        i++;
                    }
                    int i2 = i;
                    while (true) {
                        if (i2 >= read) {
                            continue;
                            break;
                        }
                        int i3 = i2 - i;
                        if (bArr[i2] != str.charAt(i3)) {
                            break;
                        } else if (i3 == str.length() - 1) {
                            return a(bArr, i2);
                        } else {
                            i2++;
                        }
                    }
                }
                i++;
            }
            return -1;
        } catch (IOException | NumberFormatException unused) {
            return -1;
        }
    }

    private static int a(byte[] bArr, int i) {
        while (i < bArr.length && bArr[i] != 10) {
            if (Character.isDigit(bArr[i])) {
                int i2 = i + 1;
                while (i2 < bArr.length && Character.isDigit(bArr[i2])) {
                    i2++;
                }
                return Integer.parseInt(new String(bArr, 0, i, i2 - i));
            }
            i++;
        }
        return -1;
    }

    public static long b(Context context) {
        ActivityManager.MemoryInfo memoryInfo = new ActivityManager.MemoryInfo();
        ((ActivityManager) context.getSystemService("activity")).getMemoryInfo(memoryInfo);
        return memoryInfo.availMem;
    }

    private static double a(double d, int i) {
        return new BigDecimal(d).setScale(i, 4).doubleValue();
    }

    public static String a(List<DeviceLevel> list, Context context) {
        if (list == null || list.isEmpty()) {
            return null;
        }
        try {
            String[][] strArr = (String[][]) Array.newInstance(String.class, list.size(), 5);
            int b = b();
            int a2 = a() / 1000;
            long a3 = a(context) / 1048576;
            float c = c(context);
            for (int i = 0; i < list.size(); i++) {
                strArr[i][0] = list.get(i).getName();
                if (b >= list.get(i).getCpuNum()) {
                    strArr[i][1] = String.valueOf(1);
                }
                if (a2 >= list.get(i).getCpuFreqKHz()) {
                    strArr[i][2] = String.valueOf(1);
                }
                if (a3 >= ((long) list.get(i).getTotalRam())) {
                    strArr[i][3] = String.valueOf(1);
                }
                if (c >= ((float) list.get(i).getDensity())) {
                    strArr[i][4] = String.valueOf(1);
                }
            }
            for (int i2 = 0; i2 < strArr.length; i2++) {
                for (int i3 = 0; i3 < strArr[i2].length; i3++) {
                    if ("1".equals(strArr[i2][1]) && "1".equals(strArr[i2][2])) {
                        if ("1".equals(strArr[i2][3])) {
                            if ("1".equals(strArr[i2][4])) {
                                return strArr[i2][0];
                            }
                        }
                    }
                }
            }
            return "deviceLevelDefault";
        } catch (Exception e) {
            e.printStackTrace();
            return "deviceLevelDefault";
        }
    }

    public static int a(Context context, boolean z) {
        long j;
        int i;
        if (z) {
            j = a(context);
        } else {
            j = b(context);
        }
        if (j == -1) {
            return -1;
        }
        long j2 = j / 1048576;
        if (j2 > 0 && j2 <= 1000) {
            i = 1;
        } else if (j2 <= 2000) {
            i = 2;
        } else if (j2 <= 3000) {
            i = 3;
        } else if (j2 <= 4000) {
            i = 4;
        } else if (j2 <= TimedRemoteCaller.DEFAULT_CALL_TIMEOUT_MILLIS) {
            i = 5;
        } else if (j2 <= 6000) {
            i = 6;
        } else if (j2 <= 7000) {
            i = 7;
        } else if (j2 <= 8000) {
            i = 8;
        } else if (j2 <= 9000) {
            i = 9;
        } else if (j2 <= JobInfo.MIN_BACKOFF_MILLIS) {
            i = 10;
        } else if (j2 <= 11000) {
            i = 11;
        } else if (j2 <= 12000) {
            i = 12;
        } else if (j2 <= 13000) {
            i = 13;
        } else if (j2 <= 14000) {
            i = 14;
        } else if (j2 <= 15000) {
            i = 15;
        } else if (j2 <= 16000) {
            i = 16;
        } else if (j2 <= 32000) {
            i = 32;
        } else if (j2 <= 64000) {
            i = 64;
        } else if (j2 > 128000) {
            return -1;
        } else {
            i = 128;
        }
        return i;
    }
}
