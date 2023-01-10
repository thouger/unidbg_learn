package cn.missfresh.sherlock.crash;

import android.net.wifi.WifiEnterpriseConfig;
import android.os.Build;
import android.text.TextUtils;
import cn.missfresh.buttomline.logtrace.bean.ConstantKey;
import com.taobao.accs.common.Constants;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.StringReader;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import xcrash.Util;

public class CrashParser {
    private static final Pattern a = Pattern.compile("^(.*):\\s'(.*?)'$");
    private static final Pattern b = Pattern.compile("^pid:\\s(.*),\\stid:\\s(.*),\\sname:\\s(.*)\\s+>>>\\s(.*)\\s<<<$");
    private static final Pattern c = Pattern.compile("^pid:\\s(.*)\\s+>>>\\s(.*)\\s<<<$");
    private static final Pattern d = Pattern.compile("^signal\\s(.*),\\scode\\s(.*),\\sfault\\saddr\\s(.*)$");
    private static final Pattern e = Pattern.compile("^(\\d{20})_(.*)__(.*)$");
    private static final Set<String> f = new HashSet(Arrays.asList("Tombstone maker", "Crash type", "Start time", "Crash time", "App ID", "App version", "Rooted", "API level", "OS version", "Kernel version", "ABI list", "Manufacturer", "Brand", "Model", "Build fingerprint", "ABI", "Abort message"));
    private static final Set<String> g = new HashSet(Arrays.asList("backtrace", "build id", "stack", "memory map", "logcat", "open files", "java stacktrace", "xcrash error", "xcrash error debug"));

    /* access modifiers changed from: private */
    public enum Status {
        UNKNOWN,
        HEAD,
        SECTION
    }

    /* access modifiers changed from: package-private */
    public static /* synthetic */ class a {
        static final /* synthetic */ int[] a = new int[Status.values().length];

        static {
            try {
                a[Status.UNKNOWN.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                a[Status.HEAD.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                a[Status.SECTION.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    public static Map<String, String> a(String str) {
        return a(str, (String) null);
    }

    public static Map<String, String> a(String str, String str2) {
        HashMap hashMap = new HashMap();
        if (str != null) {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(str));
            a((Map<String, String>) hashMap, bufferedReader, true);
            bufferedReader.close();
        }
        if (str2 != null) {
            BufferedReader bufferedReader2 = new BufferedReader(new StringReader(str2));
            a((Map<String, String>) hashMap, bufferedReader2, false);
            bufferedReader2.close();
        }
        a(hashMap, str);
        if (TextUtils.isEmpty((String) hashMap.get("App version"))) {
            String b2 = c.b();
            if (TextUtils.isEmpty(b2)) {
                b2 = "unknown";
            }
            hashMap.put("App version", b2);
        }
        a(hashMap);
        return hashMap;
    }

    private static void a(Map<String, String> map, String str) {
        String str2;
        if (str != null) {
            if (TextUtils.isEmpty(map.get("Crash time"))) {
                map.put("Crash time", new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ", Locale.US).format(new Date(new File(str).lastModified())));
            }
            String str3 = map.get("Start time");
            String str4 = map.get("App version");
            String str5 = map.get("pname");
            String str6 = map.get("Crash type");
            if (TextUtils.isEmpty(str3) || TextUtils.isEmpty(str4) || TextUtils.isEmpty(str5) || TextUtils.isEmpty(str6)) {
                String substring = str.substring(str.lastIndexOf(47) + 1);
                if (!substring.isEmpty() && substring.startsWith("sherlock_")) {
                    String substring2 = substring.substring(9);
                    if (substring2.endsWith(".java.xcrash")) {
                        if (TextUtils.isEmpty(str6)) {
                            map.put("Crash type", "java");
                        }
                        str2 = substring2.substring(0, substring2.length() - 12);
                    } else if (substring2.endsWith(".native.xcrash")) {
                        if (TextUtils.isEmpty(str6)) {
                            map.put("Crash type", "native");
                        }
                        str2 = substring2.substring(0, substring2.length() - 14);
                    } else if (substring2.endsWith(".anr.xcrash")) {
                        if (TextUtils.isEmpty(str6)) {
                            map.put("Crash type", "anr");
                        }
                        str2 = substring2.substring(0, substring2.length() - 11);
                    } else {
                        return;
                    }
                    if (TextUtils.isEmpty(str3) || TextUtils.isEmpty(str4) || TextUtils.isEmpty(str5)) {
                        Matcher matcher = e.matcher(str2);
                        if (matcher.find() && matcher.groupCount() == 3) {
                            if (TextUtils.isEmpty(str3)) {
                                map.put("Start time", new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ", Locale.US).format(new Date(Long.parseLong(matcher.group(1), 10) / 1000)));
                            }
                            if (TextUtils.isEmpty(str4)) {
                                map.put("App version", matcher.group(2));
                            }
                            if (TextUtils.isEmpty(str5)) {
                                map.put("pname", matcher.group(3));
                            }
                        }
                    }
                }
            }
        }
    }

    private static void a(Map<String, String> map) {
        if (TextUtils.isEmpty(map.get("App ID"))) {
            map.put("App ID", c.a());
        }
        if (TextUtils.isEmpty(map.get("Tombstone maker"))) {
            map.put("Tombstone maker", "7.0.7");
        }
        if (TextUtils.isEmpty(map.get("Rooted"))) {
            map.put("Rooted", Util.isRoot() ? "Yes" : "No");
        }
        if (TextUtils.isEmpty(map.get("API level"))) {
            map.put("API level", String.valueOf(Build.VERSION.SDK_INT));
        }
        if (TextUtils.isEmpty(map.get("OS version"))) {
            map.put("OS version", Build.VERSION.RELEASE);
        }
        if (TextUtils.isEmpty(map.get("Build fingerprint"))) {
            map.put("Model", Build.FINGERPRINT);
        }
        if (TextUtils.isEmpty(map.get("Manufacturer"))) {
            map.put("Manufacturer", Build.MANUFACTURER);
        }
        if (TextUtils.isEmpty(map.get("Brand"))) {
            map.put("Brand", Build.BRAND);
        }
        if (TextUtils.isEmpty(map.get("Model"))) {
            map.put("Model", Build.MODEL);
        }
        if (TextUtils.isEmpty(map.get("ABI list"))) {
            map.put("ABI list", Util.getAbiList());
        }
    }

    private static String a(BufferedReader bufferedReader) {
        try {
            bufferedReader.mark(2);
            for (int i = 0; i < 2; i++) {
                try {
                    int read = bufferedReader.read();
                    if (read == -1) {
                        bufferedReader.reset();
                        return null;
                    } else if (read > 0) {
                        bufferedReader.reset();
                        return bufferedReader.readLine();
                    }
                } catch (Exception unused) {
                    bufferedReader.reset();
                    return bufferedReader.readLine();
                }
            }
            bufferedReader.reset();
            return null;
        } catch (Exception unused2) {
            return bufferedReader.readLine();
        }
    }

    private static void a(Map<String, String> map, BufferedReader bufferedReader, boolean z) {
        Status status;
        int i;
        int i2;
        boolean z2;
        boolean z3;
        StringBuilder sb = new StringBuilder();
        Status status2 = Status.UNKNOWN;
        String a2 = z ? a(bufferedReader) : bufferedReader.readLine();
        int i3 = 1;
        Object obj = "";
        String str = null;
        boolean z4 = false;
        boolean z5 = false;
        String str2 = a2;
        if (a2 == null) {
            status = status2;
            i = 1;
        } else {
            status = status2;
            i = 0;
        }
        while (i == 0) {
            String a3 = z ? a(bufferedReader) : bufferedReader.readLine();
            int i4 = a3 == null ? i3 : 0;
            int i5 = a.a[status.ordinal()];
            if (i5 != i3) {
                if (i5 == 2) {
                    if (str2.startsWith("pid: ")) {
                        Matcher matcher = b.matcher(str2);
                        if (!matcher.find() || matcher.groupCount() != 4) {
                            Matcher matcher2 = c.matcher(str2);
                            if (matcher2.find() && matcher2.groupCount() == 2) {
                                a(map, ConstantKey.PID, matcher2.group(1));
                                a(map, "pname", matcher2.group(2));
                            }
                        } else {
                            a(map, ConstantKey.PID, matcher.group(1));
                            a(map, "tid", matcher.group(2));
                            a(map, "tname", matcher.group(3));
                            a(map, "pname", matcher.group(4));
                        }
                    } else if (str2.startsWith("signal ")) {
                        Matcher matcher3 = d.matcher(str2);
                        if (matcher3.find() && matcher3.groupCount() == 3) {
                            a(map, "signal", matcher3.group(1));
                            a(map, Constants.KEY_HTTP_CODE, matcher3.group(2));
                            a(map, "fault addr", matcher3.group(3));
                        }
                    } else {
                        Matcher matcher4 = a.matcher(str2);
                        if (matcher4.find() && matcher4.groupCount() == 2 && f.contains(matcher4.group(1))) {
                            a(map, matcher4.group(1), matcher4.group(2));
                        }
                    }
                    if (a3 == null || (!a3.startsWith("    r0 ") && !a3.startsWith("    x0 ") && !a3.startsWith("    eax ") && !a3.startsWith("    rax "))) {
                        z2 = z4;
                        z3 = z5;
                    } else {
                        status = Status.SECTION;
                        str = "registers";
                        obj = "";
                        z3 = false;
                        z2 = true;
                    }
                    if (a3 == null || a3.isEmpty()) {
                        status = Status.UNKNOWN;
                    }
                    z5 = z3;
                    z4 = z2;
                } else if (i5 == 3) {
                    if (str2.equals(obj) || i4 != 0) {
                        a(map, str, sb.toString(), z5);
                        sb.setLength(0);
                        status = Status.UNKNOWN;
                    } else {
                        if (z4) {
                            if (str.equals("java stacktrace") && str2.startsWith(WifiEnterpriseConfig.CA_CERT_ALIAS_DELIMITER)) {
                                str2 = str2.trim();
                            } else if (str2.startsWith("    ")) {
                                str2 = str2.substring(4);
                            }
                        }
                        sb.append(str2);
                        sb.append('\n');
                    }
                }
            } else if (str2.equals("*** *** *** *** *** *** *** *** *** *** *** *** *** *** *** ***")) {
                status = Status.HEAD;
            } else {
                if (str2.equals("--- --- --- --- --- --- --- --- --- --- --- --- --- --- --- ---")) {
                    status = Status.SECTION;
                    sb.append(str2);
                    sb.append('\n');
                    str = "other threads";
                    obj = "+++ +++ +++ +++ +++ +++ +++ +++ +++ +++ +++ +++ +++ +++ +++ +++";
                    i2 = 1;
                    z4 = false;
                    z5 = false;
                } else {
                    i2 = 1;
                    if (str2.length() > 1 && str2.endsWith(":")) {
                        status = Status.SECTION;
                        String substring = str2.substring(0, str2.length() - 1);
                        if (g.contains(substring)) {
                            str = substring;
                            z4 = substring.equals("backtrace") || substring.equals("build id") || substring.equals("stack") || substring.equals("memory map") || substring.equals("open files") || substring.equals("java stacktrace") || substring.equals("xcrash error debug");
                            z5 = substring.equals("xcrash error");
                            obj = "";
                        } else {
                            if (!substring.equals("memory info")) {
                                if (substring.startsWith("memory near ")) {
                                    sb.append(str2);
                                    sb.append('\n');
                                    substring = "memory near";
                                } else {
                                    obj = "";
                                    str = substring;
                                    z4 = false;
                                    z5 = false;
                                }
                            }
                            obj = "";
                            str = substring;
                            z5 = true;
                            z4 = false;
                        }
                        str2 = a3;
                        i3 = i2;
                        i = i4;
                    }
                }
                str2 = a3;
                i3 = i2;
                i = i4;
            }
            i2 = 1;
            str2 = a3;
            i3 = i2;
            i = i4;
        }
    }

    private static void a(Map<String, String> map, String str, String str2) {
        a(map, str, str2, false);
    }

    private static void a(Map<String, String> map, String str, String str2, boolean z) {
        if (str != null && !str.isEmpty() && str2 != null) {
            String str3 = map.get(str);
            if (z) {
                if (str3 != null) {
                    str2 = str3 + str2;
                }
                map.put(str, str2);
            } else if (str3 == null || (str3.isEmpty() && !str2.isEmpty())) {
                map.put(str, str2);
            }
        }
    }
}
