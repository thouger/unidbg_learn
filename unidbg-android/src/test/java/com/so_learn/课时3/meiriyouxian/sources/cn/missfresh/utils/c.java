package cn.missfresh.utils;

import android.content.ClipDescription;
import android.content.Context;
import android.os.Environment;
import android.text.TextUtils;
import android.webkit.MimeTypeMap;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import cn.missfresh.utils.a.d;
import java.io.BufferedReader;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

/* compiled from: MFFileUtils */
public class c {
    public static File a(Context context, String str) {
        AppMethodBeat.i(13627, false);
        File externalFilesDir = (context == null || !a()) ? null : context.getExternalFilesDir(str);
        if (externalFilesDir == null || !a(externalFilesDir, false)) {
            AppMethodBeat.o(13627);
            return null;
        }
        AppMethodBeat.o(13627);
        return externalFilesDir;
    }

    public static String a(Context context) {
        AppMethodBeat.i(13634, false);
        File b = b(context, (String) null);
        String absolutePath = b != null ? b.getAbsolutePath() : "";
        AppMethodBeat.o(13634);
        return absolutePath;
    }

    public static File b(Context context, String str) {
        AppMethodBeat.i(13637, false);
        File externalCacheDir = (context == null || !a()) ? null : context.getExternalCacheDir();
        if (externalCacheDir == null) {
            AppMethodBeat.o(13637);
            return null;
        }
        if (!TextUtils.isEmpty(str)) {
            externalCacheDir = new File(externalCacheDir, str);
        }
        if (!a(externalCacheDir, false)) {
            externalCacheDir = null;
        }
        AppMethodBeat.o(13637);
        return externalCacheDir;
    }

    private static boolean a() {
        AppMethodBeat.i(13642, false);
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
            AppMethodBeat.o(13642);
            return true;
        }
        AppMethodBeat.o(13642);
        return false;
    }

    public static boolean a(File file) {
        boolean z = false;
        AppMethodBeat.i(13644, false);
        if (file != null && file.isFile()) {
            z = true;
        }
        AppMethodBeat.o(13644);
        return z;
    }

    public static long a(String str) {
        long j;
        AppMethodBeat.i(13668, false);
        if (!TextUtils.isEmpty(str)) {
            File file = new File(str);
            if (file.isFile()) {
                j = file.length();
                AppMethodBeat.o(13668);
                return j;
            }
        }
        j = 0;
        AppMethodBeat.o(13668);
        return j;
    }

    public static String a(String str, boolean z) {
        AppMethodBeat.i(13672, false);
        if (e.a(str)) {
            AppMethodBeat.o(13672);
            return "";
        } else if (!z || new File(str).isFile()) {
            int lastIndexOf = str.lastIndexOf(".");
            if (lastIndexOf < 0) {
                AppMethodBeat.o(13672);
                return "";
            }
            String lowerCase = str.substring(lastIndexOf + 1).toLowerCase();
            AppMethodBeat.o(13672);
            return lowerCase;
        } else {
            AppMethodBeat.o(13672);
            return "";
        }
    }

    public static String b(File file) throws IOException {
        FileInputStream fileInputStream;
        Throwable th;
        BufferedReader bufferedReader;
        AppMethodBeat.i(13675, false);
        BufferedReader bufferedReader2 = null;
        if (!a(file)) {
            AppMethodBeat.o(13675);
            return null;
        }
        try {
            StringBuilder sb = new StringBuilder();
            fileInputStream = new FileInputStream(file);
            try {
                bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));
            } catch (Throwable th2) {
                th = th2;
                a(bufferedReader2);
                a(fileInputStream);
                AppMethodBeat.o(13675);
                throw th;
            }
            try {
                String readLine = bufferedReader.readLine();
                if (readLine != null) {
                    sb.append(readLine);
                    while (true) {
                        String readLine2 = bufferedReader.readLine();
                        if (readLine2 == null) {
                            break;
                        }
                        sb.append(File.separator);
                        sb.append(readLine2);
                    }
                }
                String sb2 = sb.toString();
                a(bufferedReader);
                a(fileInputStream);
                AppMethodBeat.o(13675);
                return sb2;
            } catch (Throwable th3) {
                th = th3;
                bufferedReader2 = bufferedReader;
                a(bufferedReader2);
                a(fileInputStream);
                AppMethodBeat.o(13675);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            fileInputStream = null;
            a(bufferedReader2);
            a(fileInputStream);
            AppMethodBeat.o(13675);
            throw th;
        }
    }

    public static boolean a(String str, File file, boolean z) throws IOException {
        Throwable th;
        FileOutputStream fileOutputStream;
        PrintWriter printWriter;
        AppMethodBeat.i(13680, false);
        if (file == null) {
            AppMethodBeat.o(13680);
            return false;
        }
        if (!file.isFile()) {
            try {
                if (!b(file, z)) {
                    AppMethodBeat.o(13680);
                    return false;
                }
            } catch (Exception e) {
                d.a("MFFileUtils", e);
            }
        }
        if (file.isFile() && !TextUtils.isEmpty(str)) {
            PrintWriter printWriter2 = null;
            try {
                fileOutputStream = new FileOutputStream(file, true);
                try {
                    printWriter = new PrintWriter(fileOutputStream);
                } catch (Throwable th2) {
                    th = th2;
                    a(printWriter2);
                    a(fileOutputStream);
                    AppMethodBeat.o(13680);
                    throw th;
                }
                try {
                    printWriter.write(str);
                    a(printWriter);
                    a(fileOutputStream);
                } catch (Throwable th3) {
                    th = th3;
                    printWriter2 = printWriter;
                    a(printWriter2);
                    a(fileOutputStream);
                    AppMethodBeat.o(13680);
                    throw th;
                }
            } catch (Throwable th4) {
                th = th4;
                fileOutputStream = null;
                a(printWriter2);
                a(fileOutputStream);
                AppMethodBeat.o(13680);
                throw th;
            }
        }
        AppMethodBeat.o(13680);
        return true;
    }

    public static boolean a(String str, String str2, boolean z) throws IOException {
        AppMethodBeat.i(13682, false);
        if (e.a(str2)) {
            AppMethodBeat.o(13682);
            return false;
        }
        boolean a = a(str, new File(str2), z);
        AppMethodBeat.o(13682);
        return a;
    }

    private static void a(Closeable closeable) {
        AppMethodBeat.i(13684, false);
        if (closeable != null) {
            try {
                closeable.close();
            } catch (RuntimeException e) {
                AppMethodBeat.o(13684);
                throw e;
            } catch (Exception e2) {
                d.a("MFFileUtils", e2);
            }
        }
        AppMethodBeat.o(13684);
    }

    public static boolean a(String str, String str2) {
        AppMethodBeat.i(13688, false);
        try {
            File file = new File(str);
            File file2 = new File(str2);
            if (!a(file2.getParentFile(), false)) {
                AppMethodBeat.o(13688);
                return false;
            }
            d.d("MFFileUtils", file.getName() + ":move");
            boolean renameTo = file.renameTo(file2);
            AppMethodBeat.o(13688);
            return renameTo;
        } catch (Exception e) {
            d.a("MFFileUtils", e);
            AppMethodBeat.o(13688);
            return false;
        }
    }

    public static boolean b(String str, String str2, boolean z) {
        AppMethodBeat.i(13691, false);
        try {
            File file = new File(str);
            if (!file.exists()) {
                AppMethodBeat.o(13691);
                return false;
            }
            File file2 = new File(str2);
            if (!a(file2, false)) {
                AppMethodBeat.o(13691);
                return false;
            }
            File[] listFiles = file.listFiles();
            if (listFiles == null || listFiles.length == 0) {
                boolean exists = file2.exists();
                AppMethodBeat.o(13691);
                return exists;
            }
            for (File file3 : listFiles) {
                d.d("MFFileUtils", file3.getName() + ":move");
                if (!file3.renameTo(new File(file2, file3.getName())) && z) {
                    AppMethodBeat.o(13691);
                    return false;
                }
            }
            AppMethodBeat.o(13691);
            return true;
        } catch (Exception e) {
            d.a("MFFileUtils", e);
            AppMethodBeat.o(13691);
            return false;
        }
    }

    public static long b(String str) {
        AppMethodBeat.i(13693, false);
        if (!e.a(str)) {
            try {
                long lastModified = new File(str).lastModified();
                AppMethodBeat.o(13693);
                return lastModified;
            } catch (Exception e) {
                d.a("MFFileUtils", e);
            }
        }
        AppMethodBeat.o(13693);
        return 0;
    }

    public static String b(String str, boolean z) {
        AppMethodBeat.i(13696, false);
        String a = a(str, z);
        if (TextUtils.isEmpty(a)) {
            AppMethodBeat.o(13696);
            return "";
        }
        MimeTypeMap singleton = MimeTypeMap.getSingleton();
        String mimeTypeFromExtension = singleton != null ? singleton.getMimeTypeFromExtension(a) : ClipDescription.MIMETYPE_TEXT_PLAIN;
        if (mimeTypeFromExtension == null) {
            mimeTypeFromExtension = "";
        }
        AppMethodBeat.o(13696);
        return mimeTypeFromExtension;
    }

    public static boolean a(File file, boolean z) {
        boolean z2 = false;
        AppMethodBeat.i(13701, false);
        if (file == null) {
            AppMethodBeat.o(13701);
            return false;
        }
        if (z) {
            c(file);
        }
        if (file.isDirectory() || file.mkdirs()) {
            z2 = true;
        }
        AppMethodBeat.o(13701);
        return z2;
    }

    public static boolean b(File file, boolean z) {
        AppMethodBeat.i(13707, false);
        if (file == null) {
            AppMethodBeat.o(13707);
            return false;
        }
        if (file.exists()) {
            if (z) {
                c(file, false);
            } else {
                AppMethodBeat.o(13707);
                return false;
            }
        } else if (!a(file.getParentFile(), false)) {
            AppMethodBeat.o(13707);
            return false;
        }
        try {
            boolean createNewFile = file.createNewFile();
            AppMethodBeat.o(13707);
            return createNewFile;
        } catch (Exception e) {
            d.a("MFFileUtils", e);
            AppMethodBeat.o(13707);
            return false;
        }
    }

    public static boolean c(String str, boolean z) {
        AppMethodBeat.i(13709, false);
        if (e.a(str)) {
            AppMethodBeat.o(13709);
            return false;
        }
        boolean c = c(new File(str), z);
        AppMethodBeat.o(13709);
        return c;
    }

    public static boolean c(File file, boolean z) {
        AppMethodBeat.i(13711, false);
        if (!file.exists()) {
            AppMethodBeat.o(13711);
            return true;
        } else if (file.isFile()) {
            boolean c = c(file);
            AppMethodBeat.o(13711);
            return c;
        } else if (file.isDirectory()) {
            boolean d = d(file, z);
            AppMethodBeat.o(13711);
            return d;
        } else {
            AppMethodBeat.o(13711);
            return false;
        }
    }

    public static boolean c(File file) {
        AppMethodBeat.i(13717, false);
        if (file == null) {
            AppMethodBeat.o(13717);
            return false;
        } else if (!file.exists()) {
            AppMethodBeat.o(13717);
            return true;
        } else if (file.isFile()) {
            boolean delete = file.delete();
            AppMethodBeat.o(13717);
            return delete;
        } else {
            AppMethodBeat.o(13717);
            return false;
        }
    }

    public static boolean d(File file, boolean z) {
        AppMethodBeat.i(13721, false);
        if (file == null) {
            AppMethodBeat.o(13721);
            return false;
        }
        boolean z2 = true;
        if (!file.exists()) {
            AppMethodBeat.o(13721);
            return true;
        } else if (!file.isDirectory()) {
            AppMethodBeat.o(13721);
            return false;
        } else {
            File[] listFiles = file.listFiles();
            int i = 0;
            while (true) {
                if (i >= listFiles.length) {
                    break;
                } else if (listFiles[i].isFile()) {
                    if (!c(listFiles[i]) && z) {
                        break;
                    }
                    i++;
                } else {
                    if (listFiles[i].isDirectory() && !d(listFiles[i], z) && z) {
                        break;
                    }
                    i++;
                }
            }
            z2 = false;
            if (!z2) {
                AppMethodBeat.o(13721);
                return false;
            }
            boolean delete = file.delete();
            AppMethodBeat.o(13721);
            return delete;
        }
    }
}
