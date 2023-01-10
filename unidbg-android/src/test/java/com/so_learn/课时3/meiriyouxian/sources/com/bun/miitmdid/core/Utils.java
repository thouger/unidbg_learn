package com.bun.miitmdid.core;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.util.Log;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import com.huawei.hms.support.api.push.pushselfshow.prepare.NotificationIconUtil;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.lang.reflect.Method;
import java.util.zip.CRC32;

public class Utils {
    public static final String CPU_ABI_X86 = "x86";

    private static String CPUABI() {
        String str = "arm";
        AppMethodBeat.i(4808, false);
        try {
            if (new BufferedReader(new InputStreamReader(Runtime.getRuntime().exec("getprop ro.product.cpu.abi").getInputStream())).readLine().contains(CPU_ABI_X86)) {
                str = CPU_ABI_X86;
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
        AppMethodBeat.o(4808);
        return str;
    }

    public static void PrintClassMethod(Class<?> cls) {
        AppMethodBeat.i(4811, false);
        for (Method method : cls.getMethods()) {
            System.out.println(method.getName());
        }
        AppMethodBeat.o(4811);
    }

    public static void PrintObjectType(Class<?> cls) {
        AppMethodBeat.i(4814, false);
        String name = cls.getName();
        PrintStream printStream = System.out;
        printStream.println("PrintObjectType:" + name);
        AppMethodBeat.o(4814);
    }

    public static void PrintObjectType(Object obj) {
        AppMethodBeat.i(4816, false);
        String name = obj.getClass().getName();
        PrintStream printStream = System.out;
        printStream.println("PrintObjectType:" + name);
        AppMethodBeat.o(4816);
    }

    public static long getFileCRC(String str) {
        long j;
        AppMethodBeat.i(4819, false);
        try {
            File file = new File(str);
            if (!file.exists()) {
                j = -1;
                AppMethodBeat.o(4819);
                return j;
            }
            int length = (int) file.length();
            FileInputStream fileInputStream = new FileInputStream(str);
            CRC32 crc32 = new CRC32();
            byte[] bArr = new byte[length];
            for (int i = 0; i < length; i += fileInputStream.read(bArr, i, length - i)) {
            }
            crc32.update(length);
            j = crc32.getValue();
            AppMethodBeat.o(4819);
            return j;
        } catch (IOException unused) {
        }
    }

    public static void getFileListame(String str) {
        AppMethodBeat.i(4824, false);
        File[] listFiles = new File(str).listFiles();
        if (listFiles != null) {
            for (int i = 0; i < listFiles.length; i++) {
                Log.i("Utils", listFiles[i].getName());
                if (listFiles[i].isDirectory()) {
                    getFileListame(listFiles[i].getAbsolutePath());
                    Log.i("Utils", String.valueOf(listFiles[i].getAbsolutePath()) + listFiles[i].getName());
                }
            }
        }
        AppMethodBeat.o(4824);
    }

    public static String getLibraryDir(Context context) {
        AppMethodBeat.i(4827, false);
        String str = context.getApplicationInfo().nativeLibraryDir;
        AppMethodBeat.o(4827);
        return str;
    }

    public static String getUserDir(Context context) {
        AppMethodBeat.i(4829, false);
        String parent = context.getFilesDir().getParent();
        AppMethodBeat.o(4829);
        return parent;
    }

    public static String getXdataDir(Context context, String str) {
        AppMethodBeat.i(4833, false);
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(String.valueOf(getUserDir(context)) + NotificationIconUtil.SPLIT_CHAR + JLibrary.xdata + NotificationIconUtil.SPLIT_CHAR + str);
        String stringBuffer2 = stringBuffer.toString();
        AppMethodBeat.o(4833);
        return stringBuffer2;
    }

    public static String getYdataDir(Context context, String str) {
        AppMethodBeat.i(4835, false);
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(String.valueOf(getUserDir(context)) + NotificationIconUtil.SPLIT_CHAR + JLibrary.ydata + NotificationIconUtil.SPLIT_CHAR + str);
        String stringBuffer2 = stringBuffer.toString();
        AppMethodBeat.o(4835);
        return stringBuffer2;
    }

    public static boolean isX86() {
        boolean z = false;
        AppMethodBeat.i(4839, false);
        if (Build.CPU_ABI.equals(CPU_ABI_X86) || CPUABI().equals(CPU_ABI_X86)) {
            z = true;
        }
        AppMethodBeat.o(4839);
        return z;
    }

    public static boolean update(Context context) throws Exception {
        boolean z = false;
        AppMethodBeat.i(4844, false);
        long zipCrc = ZipUtils.getZipCrc(new File(context.getApplicationInfo().sourceDir));
        SharedPreferences sharedPreferences = context.getSharedPreferences("update", 0);
        if (zipCrc != sharedPreferences.getLong("crc", 0)) {
            z = true;
        }
        sharedPreferences.edit().putLong("crc", zipCrc).commit();
        AppMethodBeat.o(4844);
        return z;
    }

    public static void x0xooXdata(InputStream inputStream, String str, Context context) throws Exception {
        AppMethodBeat.i(4849, false);
        try {
            File file = new File(str);
            byte[] bArr = new byte[65536];
            BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(file));
            while (true) {
                int read = bufferedInputStream.read(bArr);
                if (read <= 0) {
                    bufferedOutputStream.flush();
                    bufferedOutputStream.close();
                    bufferedInputStream.close();
                    AppMethodBeat.o(4849);
                    return;
                }
                bufferedOutputStream.write(bArr, 0, read);
            }
        } catch (Exception e) {
            AppMethodBeat.o(4849);
            throw e;
        }
    }
}
