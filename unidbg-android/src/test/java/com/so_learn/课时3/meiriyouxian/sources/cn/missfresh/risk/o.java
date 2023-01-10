package cn.missfresh.risk;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.media.MediaDrm;
import android.net.wifi.WifiEnterpriseConfig;
import android.os.Process;
import cn.missfresh.buttomline.abtest.uitl.FileOpt;
import cn.missfresh.risk.b.a;
import cn.missfresh.risk.bean.AppInfo;
import cn.missfresh.risk.bean.RiskBean;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;

/* compiled from: SysInfoReadUtils */
public class o {
    public static synchronized String a(String[] strArr, String str) throws IOException {
        String sb;
        synchronized (o.class) {
            AppMethodBeat.i(Process.WEBVIEW_ZYGOTE_UID, false);
            StringBuilder sb2 = new StringBuilder();
            try {
                ProcessBuilder processBuilder = new ProcessBuilder(strArr);
                InputStream inputStream = null;
                if (str != null) {
                    if (str != null && str.length() > 0) {
                        processBuilder.directory(new File(str));
                    }
                    processBuilder.redirectErrorStream(true);
                    inputStream = processBuilder.start().getInputStream();
                    byte[] bArr = new byte[1024];
                    while (true) {
                        int read = inputStream.read(bArr);
                        if (read == -1) {
                            break;
                        }
                        sb2.append(new String(bArr, 0, read));
                    }
                }
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            sb = sb2.toString();
            AppMethodBeat.o(Process.WEBVIEW_ZYGOTE_UID);
        }
        return sb;
    }

    public static String a() {
        AppMethodBeat.i(1057, false);
        try {
            String str = new String(new MediaDrm(new UUID(-1301668207276963122L, -6645017420763422227L)).getPropertyByteArray(MediaDrm.PROPERTY_DEVICE_UNIQUE_ID), "utf8");
            AppMethodBeat.o(1057);
            return str;
        } catch (Exception e) {
            e.printStackTrace();
            AppMethodBeat.o(1057);
            return "";
        }
    }

    public static List<AppInfo> a(Context context) {
        AppMethodBeat.i(1062, false);
        try {
            List<PackageInfo> installedPackages = context.getPackageManager().getInstalledPackages(0);
            if (installedPackages != null) {
                if (installedPackages.size() != 0) {
                    Collections.sort(installedPackages, new AnonymousClass1());
                    List arrayList = new ArrayList();
                    List arrayList2 = new ArrayList();
                    int i = 0;
                    while (true) {
                        if (i >= installedPackages.size()) {
                            break;
                        } else if (i >= a.d) {
                            break;
                        } else {
                            PackageInfo packageInfo = installedPackages.get(i);
                            AppInfo appInfo = new AppInfo();
                            appInfo.firstIntallTime = packageInfo.firstInstallTime;
                            appInfo.lastUpdateTime = packageInfo.lastUpdateTime;
                            appInfo.name = packageInfo.applicationInfo.loadLabel(context.getPackageManager()).toString();
                            appInfo.packetName = packageInfo.packageName;
                            appInfo.versionCode = packageInfo.versionName;
                            if ((packageInfo.applicationInfo.flags & 1) == 1) {
                                appInfo.type = 0;
                                arrayList2.add(appInfo);
                            } else {
                                appInfo.type = 1;
                                arrayList.add(appInfo);
                            }
                            i++;
                        }
                    }
                    if (arrayList.size() > a.d) {
                        arrayList = arrayList.subList(0, a.d);
                    }
                    Collections.sort(arrayList, new AnonymousClass2());
                    if (arrayList.size() >= a.d) {
                        AppMethodBeat.o(1062);
                        return arrayList;
                    }
                    if (arrayList.size() + arrayList2.size() > a.d) {
                        arrayList2 = arrayList2.subList(0, a.d - arrayList.size());
                    }
                    Collections.sort(arrayList2, new AnonymousClass3());
                    arrayList.addAll(arrayList2);
                    AppMethodBeat.o(1062);
                    return arrayList;
                }
            }
            AppMethodBeat.o(1062);
            return null;
        } catch (Exception e) {
            cn.missfresh.risk.exceptions.a.a(e.getMessage());
            AppMethodBeat.o(1062);
            return null;
        }
    }

    /* compiled from: SysInfoReadUtils */
    /* access modifiers changed from: package-private */
    /* renamed from: cn.missfresh.risk.o$1  reason: invalid class name */
    public static class AnonymousClass1 implements Comparator {
        AnonymousClass1() {
        }

        @Override // java.util.Comparator
        public int compare(Object obj, Object obj2) {
            if (!(obj == null || obj2 == null)) {
                PackageInfo packageInfo = (PackageInfo) obj;
                PackageInfo packageInfo2 = (PackageInfo) obj2;
                if (packageInfo2.firstInstallTime > packageInfo.firstInstallTime) {
                    return 1;
                }
                if (packageInfo2.firstInstallTime < packageInfo.firstInstallTime) {
                    return -1;
                }
            }
            return 0;
        }
    }

    /* compiled from: SysInfoReadUtils */
    /* access modifiers changed from: package-private */
    /* renamed from: cn.missfresh.risk.o$2  reason: invalid class name */
    public static class AnonymousClass2 implements Comparator {
        AnonymousClass2() {
        }

        @Override // java.util.Comparator
        public int compare(Object obj, Object obj2) {
            if (!(obj == null || obj2 == null)) {
                AppInfo appInfo = (AppInfo) obj;
                AppInfo appInfo2 = (AppInfo) obj2;
                if (appInfo2.firstIntallTime > appInfo.firstIntallTime) {
                    return 1;
                }
                if (appInfo2.firstIntallTime < appInfo.firstIntallTime) {
                    return -1;
                }
            }
            return 0;
        }
    }

    /* compiled from: SysInfoReadUtils */
    /* access modifiers changed from: package-private */
    /* renamed from: cn.missfresh.risk.o$3  reason: invalid class name */
    public static class AnonymousClass3 implements Comparator {
        AnonymousClass3() {
        }

        @Override // java.util.Comparator
        public int compare(Object obj, Object obj2) {
            if (!(obj == null || obj2 == null)) {
                AppInfo appInfo = (AppInfo) obj;
                AppInfo appInfo2 = (AppInfo) obj2;
                if (appInfo2.firstIntallTime > appInfo.firstIntallTime) {
                    return 1;
                }
                if (appInfo2.firstIntallTime < appInfo.firstIntallTime) {
                    return -1;
                }
            }
            return 0;
        }
    }

    public static List<String> b() {
        String substring;
        AppMethodBeat.i(1070, false);
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        ArrayList arrayList3 = new ArrayList();
        ArrayList arrayList4 = new ArrayList();
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream("/proc/" + Process.myPid() + "/maps"), FileOpt.ENCODE_STR));
            while (true) {
                String readLine = bufferedReader.readLine();
                if (readLine == null) {
                    break;
                } else if (readLine.endsWith(".so") || readLine.endsWith(".jar")) {
                    int lastIndexOf = readLine.lastIndexOf(WifiEnterpriseConfig.CA_CERT_ALIAS_DELIMITER);
                    if (lastIndexOf > -1 && (substring = readLine.substring(lastIndexOf + 1)) != null && !substring.startsWith("/system")) {
                        if (substring.startsWith("/data")) {
                            if (!arrayList2.contains(substring)) {
                                arrayList2.add(substring);
                            }
                        } else if (substring.startsWith("/vendor")) {
                            if (!arrayList3.contains(substring)) {
                                arrayList3.add(substring);
                            }
                        } else if (!arrayList4.contains(substring)) {
                            arrayList4.add(substring);
                        }
                    }
                }
            }
            if (arrayList2.size() > 0) {
                arrayList.addAll(arrayList2);
            }
            if (arrayList.size() <= a.g) {
                if (arrayList4.size() > 0) {
                    if (arrayList.size() + arrayList4.size() <= a.g) {
                        arrayList.addAll(arrayList4);
                    } else {
                        arrayList.addAll(arrayList4.subList(0, a.g - arrayList.size()));
                    }
                }
                if (arrayList3.size() > 0) {
                    if (arrayList.size() + arrayList3.size() <= a.g) {
                        arrayList.addAll(arrayList3);
                    } else {
                        arrayList.addAll(arrayList3.subList(0, a.g - arrayList.size()));
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        AppMethodBeat.o(1070);
        return arrayList;
    }

    public static void a(RiskBean riskBean) {
        AppMethodBeat.i(1075, false);
        if (riskBean == null) {
            AppMethodBeat.o(1075);
            return;
        }
        try {
            ProcessBuilder processBuilder = new ProcessBuilder("getprop");
            processBuilder.redirectErrorStream(true);
            InputStream inputStream = processBuilder.start().getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            while (true) {
                String readLine = bufferedReader.readLine();
                if (readLine == null) {
                    break;
                }
                String[] split = readLine.trim().replaceAll("\\[", "").replaceAll("\\]", "").split(":");
                if (!(split == null || split.length != 2 || split[0] == null)) {
                    if ("ro.boot.baseband".equals(split[0])) {
                        riskBean.setAinfoSyspropsRoBootBaseband(split[1]);
                    } else if ("ro.boot.bootloader".equals(split[0])) {
                        riskBean.setAinfoSyspropsRoBootBootloader(split[1]);
                    } else if ("ro.boot.hardware".equals(split[0])) {
                        riskBean.setAinfoSyspropsRoBootHardware(split[1]);
                    } else if ("ro.build.date.utc".equals(split[0])) {
                        riskBean.setAinfoSyspropsRoBuildDateUtc(split[1]);
                    } else if ("ro.build.display.id".equals(split[0])) {
                        riskBean.setAinfoSyspropsRoBuildDisplayId(split[1]);
                    } else if ("ro.build.version.release".equals(split[0])) {
                        riskBean.setAinfoSyspropsRoBuildVersionRelease(split[1]);
                    } else if ("ro.build.version.sdk".equals(split[0])) {
                        riskBean.setAinfoSyspropsRoBuildVersionSdk(split[1]);
                    } else if ("ro.debuggable".equals(split[0])) {
                        riskBean.setAinfoSyspropsRoDebuggable(split[1]);
                    } else if ("ro.kernel.qemu".equals(split[0])) {
                        riskBean.setAinfoSyspropsRoKernelQemu(split[1]);
                    } else if ("ro.product.board".equals(split[0])) {
                        riskBean.setAinfoSyspropsRoProductBoard(split[1]);
                    } else if ("ro.product.brand".equals(split[0])) {
                        riskBean.setAinfoSyspropsRoProductBrand(split[1]);
                    } else if ("ro.product.device".equals(split[0])) {
                        riskBean.setAinfoSyspropsRoProductDevice(split[1]);
                    } else if ("ro.product.manufacturer".equals(split[0])) {
                        riskBean.setAinfoSyspropsRoProductManufacturer(split[1]);
                    } else if ("ro.product.model".equals(split[0])) {
                        riskBean.setAinfoSyspropsRoProductModel(split[1]);
                    } else if ("ro.product.name".equals(split[0])) {
                        riskBean.setAinfoSyspropsRoProductName(split[1]);
                    } else if ("ro.secure".equals(split[0])) {
                        riskBean.setAinfoSyspropsRoSecure(split[1]);
                    } else if ("ro.serialno".equals(split[0])) {
                        riskBean.setAinfoSyspropsRoSerialno(split[1]);
                    } else if ("ro.baseband".equals(split[0])) {
                        riskBean.setAinfoSyspropsRoBootBaseband(split[1]);
                    } else if ("ro.bootloader".equals(split[0])) {
                        riskBean.setAinfoSyspropsRoBootBootloader(split[1]);
                    }
                }
            }
            if (inputStream != null) {
                inputStream.close();
            }
            if (bufferedReader != null) {
                bufferedReader.close();
            }
            riskBean.setAinfoSyspropsRoSerialno(j.f());
        } catch (Exception e) {
            e.printStackTrace();
        }
        AppMethodBeat.o(1075);
    }
}
