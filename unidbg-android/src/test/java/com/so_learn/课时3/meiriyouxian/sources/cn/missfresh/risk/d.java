package cn.missfresh.risk;

import android.os.Build;
import cn.missfresh.risk.bean.CpuInfoBean;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import com.android.internal.logging.nano.MetricsProto;
import com.xiaomi.mipush.sdk.Constants;
import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.util.regex.Pattern;

/* compiled from: CpuUtil */
public class d {

    /* compiled from: CpuUtil */
    /* access modifiers changed from: package-private */
    public class a implements FileFilter {
        a() {
        }

        @Override // java.io.FileFilter
        public boolean accept(File file) {
            AppMethodBeat.i(MetricsProto.MetricsEvent.ACTION_DELETION_HELPER_APPS_DELETION_FAIL, false);
            if (Pattern.matches("cpu[0-9]", file.getName())) {
                AppMethodBeat.o(MetricsProto.MetricsEvent.ACTION_DELETION_HELPER_APPS_DELETION_FAIL);
                return true;
            }
            AppMethodBeat.o(MetricsProto.MetricsEvent.ACTION_DELETION_HELPER_APPS_DELETION_FAIL);
            return false;
        }
    }

    public static int a() {
        AppMethodBeat.i(MetricsProto.MetricsEvent.ACTION_SELECT_SUPPORT_FRAGMENT, false);
        try {
            int length = new File("/sys/devices/system/cpu/").listFiles(new a()).length;
            AppMethodBeat.o(MetricsProto.MetricsEvent.ACTION_SELECT_SUPPORT_FRAGMENT);
            return length;
        } catch (Exception e) {
            e.printStackTrace();
            AppMethodBeat.o(MetricsProto.MetricsEvent.ACTION_SELECT_SUPPORT_FRAGMENT);
            return 1;
        }
    }

    public static String b() {
        String str;
        AppMethodBeat.i(480, false);
        try {
            InputStream inputStream = new ProcessBuilder("/system/bin/cat", "/sys/devices/system/cpu/cpu0/cpufreq/cpuinfo_max_freq").start().getInputStream();
            byte[] bArr = new byte[24];
            str = "";
            while (inputStream.read(bArr) != -1) {
                str = str + new String(bArr);
            }
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
            str = "N/A";
        }
        String trim = str.trim();
        AppMethodBeat.o(480);
        return trim;
    }

    public static CpuInfoBean c() {
        AppMethodBeat.i(489, false);
        CpuInfoBean cpuInfoBean = new CpuInfoBean();
        try {
            cpuInfoBean.setCpuNum("0000000000000000");
            ProcessBuilder processBuilder = new ProcessBuilder("cat", "/proc/cpuinfo");
            processBuilder.redirectErrorStream(true);
            InputStreamReader inputStreamReader = new InputStreamReader(processBuilder.start().getInputStream());
            LineNumberReader lineNumberReader = new LineNumberReader(inputStreamReader);
            while (true) {
                String readLine = lineNumberReader.readLine();
                if (readLine == null) {
                    break;
                } else if (readLine.indexOf("Serial") > -1) {
                    cpuInfoBean.setCpuNum(readLine.substring(readLine.indexOf(":") + 1, readLine.length()).trim());
                    break;
                } else if (readLine.indexOf("Hardware") > -1) {
                    cpuInfoBean.setCpuModel(readLine.substring(readLine.indexOf(":") + 2, readLine.length()).trim());
                }
            }
            lineNumberReader.close();
            inputStreamReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        AppMethodBeat.o(489);
        return cpuInfoBean;
    }

    public static String d() {
        AppMethodBeat.i(492, false);
        if (Build.VERSION.SDK_INT >= 21) {
            String[] strArr = Build.SUPPORTED_ABIS;
            String str = "";
            if (strArr != null) {
                String str2 = str;
                for (int i = 0; i < strArr.length; i++) {
                    str2 = str2 + strArr[0] + Constants.ACCEPT_TIME_SEPARATOR_SP;
                }
                str = str2;
            }
            AppMethodBeat.o(492);
            return str;
        }
        String str3 = Build.CPU_ABI;
        AppMethodBeat.o(492);
        return str3;
    }
}
