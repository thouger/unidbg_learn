package cn.missfresh.module.base.utils;

import android.text.TextUtils;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import java.io.File;
import java.math.BigDecimal;

/* compiled from: DataClean */
public class s {
    public static long a(File file) throws Exception {
        long j;
        AppMethodBeat.i(23269, false);
        long j2 = 0;
        try {
            File[] listFiles = file.listFiles();
            if (listFiles != null) {
                if (listFiles.length != 0) {
                    for (int i = 0; i < listFiles.length; i++) {
                        if (listFiles[i].isDirectory()) {
                            j = a(listFiles[i]);
                        } else {
                            j = listFiles[i].length();
                        }
                        j2 += j;
                    }
                    AppMethodBeat.o(23269);
                    return j2;
                }
            }
            AppMethodBeat.o(23269);
            return 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void a(String str, boolean z) {
        File[] listFiles;
        AppMethodBeat.i(23270, false);
        if (!TextUtils.isEmpty(str)) {
            try {
                File file = new File(str);
                if (file.isDirectory()) {
                    for (File file2 : file.listFiles()) {
                        a(file2.getAbsolutePath(), true);
                    }
                }
                if (z) {
                    if (!file.isDirectory()) {
                        file.delete();
                    } else if (file.listFiles().length == 0) {
                        file.delete();
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        AppMethodBeat.o(23270);
    }

    public static String a(double d) {
        AppMethodBeat.i(23271, false);
        BigDecimal bigDecimal = new BigDecimal(Double.toString((d / 1024.0d) / 1024.0d));
        String str = bigDecimal.setScale(2, 4).toPlainString() + "MB";
        AppMethodBeat.o(23271);
        return str;
    }

    public static String b(File file) throws Exception {
        AppMethodBeat.i(23272, false);
        String a = a((double) a(file));
        AppMethodBeat.o(23272);
        return a;
    }
}
