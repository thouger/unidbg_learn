package cn.missfresh.risk;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import cn.missfresh.risk.bean.BatteryBean;
import cn.missfresh.risk.bean.CpuInfoBean;
import cn.missfresh.risk.bean.RiskBean;
import cn.missfresh.risk.bean.SignaturesBean;
import cn.missfresh.risk.bean.TelephonyInfo;
import cn.missfresh.risk.e.a;
import cn.missfresh.risk.net.WifiInfoManager;
import cn.missfresh.risk.simcard.SimCardBean;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import cn.missfresh.utils.c;
import cn.missfresh.utils.e;
import com.android.internal.logging.nano.MetricsProto;
import java.io.File;

/* compiled from: RiskUtil */
public class k {
    public static String a() {
        return "2.0.0";
    }

    public static RiskBean a(Context context) {
        PackageInfo packageInfo;
        AppMethodBeat.i(MetricsProto.MetricsEvent.APP_TRANSITION_CALLING_PACKAGE_NAME, false);
        TelephonyInfo a = a.a(context);
        l lVar = new l();
        lVar.a(context);
        RiskBean riskBean = new RiskBean();
        riskBean.setSdkVersion(a());
        riskBean.setKernelVersion(j.n());
        a(context, riskBean);
        a(context, a, riskBean);
        a(riskBean);
        o.a(riskBean);
        cn.missfresh.risk.a.a.a(context, riskBean);
        riskBean.setAdId(j.a(context));
        if (a != null) {
            riskBean.setImei1(a.getImsiSIM1());
            riskBean.setImei2(a.getImsiSIM2());
            riskBean.setImei(a.getImei());
            riskBean.setImsi(a.getImsi());
            riskBean.setIccid(a.getIccid());
            riskBean.setOperator(a.getOperator());
            riskBean.setCell(a.getCellBeans());
            riskBean.setSimPhoneNumber(a.getSimPhoneNumber());
        }
        riskBean.setBootId(j.a());
        riskBean.setWidevineId(o.a());
        riskBean.setApps(o.a(context));
        try {
            PackageManager packageManager = context.getPackageManager();
            if (!(packageManager == null || (packageInfo = packageManager.getPackageInfo(context.getPackageName(), 16384)) == null)) {
                riskBean.setAppVersion(packageInfo.versionName);
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        riskBean.setAppName(j.f(context));
        riskBean.setName(j.g(context));
        riskBean.setProc(j.h(context));
        riskBean.setBootTime(j.r());
        riskBean.setCurrentTime(System.currentTimeMillis());
        riskBean.setUpTime(j.s());
        riskBean.setBssid(cn.missfresh.risk.net.a.d(context));
        riskBean.setMac(j.g());
        riskBean.setNet(cn.missfresh.risk.net.a.b());
        riskBean.setNetwork(cn.missfresh.risk.net.a.a(context));
        riskBean.setSsid(cn.missfresh.risk.net.a.c(context));
        riskBean.setWifiIp(cn.missfresh.risk.net.a.e(context));
        riskBean.setIp(cn.missfresh.risk.net.a.f(context));
        riskBean.setAps(cn.missfresh.risk.net.a.g(context));
        riskBean.setOs("android");
        riskBean.setOsVersion(j.l());
        riskBean.setModel(j.j());
        riskBean.setBrand(j.k());
        riskBean.setCpuCount(d.a());
        CpuInfoBean c = d.c();
        riskBean.setCpuModel(c.getCpuModel());
        riskBean.setCpuFreq(d.b());
        riskBean.setCpuNum(c.getCpuNum());
        riskBean.setBtmac(j.i());
        riskBean.setAbtmac(j.h());
        riskBean.setMemory(j.e(context));
        riskBean.setSensor(l.b(context));
        riskBean.setOrientation(lVar.a());
        riskBean.setBrightness(j.d(context));
        BatteryBean a2 = a.a(context);
        riskBean.setBatteryLevel((double) a2.getBatteryLevel());
        riskBean.setBatteryStatus(a2.getState());
        riskBean.setBatteryTemperature(a2.getBatteryTemperature());
        riskBean.setFreeSpace(j.q());
        riskBean.setTotalSpace(j.p());
        riskBean.setScreen(j.c(context));
        riskBean.setCamcnt(b.a());
        riskBean.setCamlight(b.a(context));
        riskBean.setCampermi(b.b(context) + "");
        riskBean.setBand(j.m());
        riskBean.setGpsSwitch(e.a(context));
        riskBean.setGpsAuthStatus(e.c(context));
        riskBean.setGps(e.b(context));
        SignaturesBean a3 = n.a(context);
        if (a3 != null) {
            riskBean.setSigndn(a3.getSignDn());
            riskBean.setSignMd5(a3.getSignMD5());
        }
        try {
            riskBean.setFiles(context.getFilesDir().getAbsolutePath());
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        riskBean.setMaps(o.b());
        riskBean.setInput(f.a(context));
        AppMethodBeat.o(MetricsProto.MetricsEvent.APP_TRANSITION_CALLING_PACKAGE_NAME);
        return riskBean;
    }

    public static void a(Context context, RiskBean riskBean) {
        AppMethodBeat.i(MetricsProto.MetricsEvent.FIELD_AUTOFILL_SERVICE, false);
        if (riskBean == null) {
            AppMethodBeat.o(MetricsProto.MetricsEvent.FIELD_AUTOFILL_SERVICE);
            return;
        }
        riskBean.setAinfoAbi(d.d());
        riskBean.setAinfoWifimac(WifiInfoManager.a());
        riskBean.setAinfoApmac(WifiInfoManager.a(context));
        riskBean.setAinfoResett(c.b("/system/"));
        riskBean.setAinfoIsVpn(WifiInfoManager.b() + "");
        riskBean.setAinfoDsMd5(a("/data/system/"));
        riskBean.setAinfoSfMd5(a("/system/framework/"));
        riskBean.setAinfoVlMd5(a("/vendor/lib/"));
        riskBean.setAinfoRoot(j.o() + "");
        riskBean.setAinfoTmprFw(j.b() + "");
        AppMethodBeat.o(MetricsProto.MetricsEvent.FIELD_AUTOFILL_SERVICE);
    }

    public static void a(Context context, TelephonyInfo telephonyInfo, RiskBean riskBean) {
        AppMethodBeat.i(MetricsProto.MetricsEvent.FIELD_AUTOFILL_FILTERTEXT_LEN, false);
        if (context == null || riskBean == null) {
            AppMethodBeat.o(MetricsProto.MetricsEvent.FIELD_AUTOFILL_FILTERTEXT_LEN);
            return;
        }
        riskBean.setPropsGsmNetworkType(cn.missfresh.risk.net.a.a(context));
        SimCardBean a = cn.missfresh.risk.simcard.c.a(context);
        if (a != null) {
            riskBean.setPropsGsmSimState(a.getSimCardState());
        }
        riskBean.setPropsPersistSysCountry(j.c());
        riskBean.setPropsPersistSysLanguage(j.d());
        riskBean.setPropsSysUsbState(j.b(context));
        riskBean.setPropsNetDns1(cn.missfresh.risk.net.a.a());
        riskBean.setPropsNetHostname(j.e());
        AppMethodBeat.o(MetricsProto.MetricsEvent.FIELD_AUTOFILL_FILTERTEXT_LEN);
    }

    public static void a(RiskBean riskBean) {
        AppMethodBeat.i(MetricsProto.MetricsEvent.AUTOFILL_DATASET_APPLIED, false);
        if (riskBean == null) {
            AppMethodBeat.o(MetricsProto.MetricsEvent.AUTOFILL_DATASET_APPLIED);
            return;
        }
        try {
            riskBean.setSysSerial(j.f());
            riskBean.setSysModel(Build.MODEL);
            riskBean.setSysDevice(Build.DEVICE);
            riskBean.setSysDisplay(Build.DISPLAY);
            riskBean.setSysCpuabi(Build.CPU_ABI);
            riskBean.setSysCpuabi2(Build.CPU_ABI2);
            riskBean.setSysManufacturer(Build.MANUFACTURER);
            riskBean.setSysBrand(Build.BRAND);
            riskBean.setSysBoard(Build.BOARD);
            riskBean.setSysHardware(Build.HARDWARE);
            riskBean.setSysProduct(Build.PRODUCT);
            riskBean.setSysFingerprint(Build.FINGERPRINT);
            riskBean.setSysVersionRelease(Build.VERSION.RELEASE);
            riskBean.setSysVersionSdk(Build.VERSION.SDK_INT + "");
            riskBean.setSysVersionIncremental(Build.VERSION.INCREMENTAL);
            riskBean.setSysBootloader(Build.BOOTLOADER);
            riskBean.setSysHost(Build.HOST);
            riskBean.setSysTags(Build.TAGS);
        } catch (Exception e) {
            e.printStackTrace();
        }
        AppMethodBeat.o(MetricsProto.MetricsEvent.AUTOFILL_DATASET_APPLIED);
    }

    /* JADX WARNING: Removed duplicated region for block: B:35:0x0068  */
    /* JADX WARNING: Removed duplicated region for block: B:40:0x0071 A[SYNTHETIC, Splitter:B:40:0x0071] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void a(android.content.Context r5, java.lang.String r6) {
        /*
            r0 = 919(0x397, float:1.288E-42)
            r1 = 0
            cn.missfresh.sherlock.trace.core.AppMethodBeat.i(r0, r1)
            r1 = 0
            int r2 = android.os.Build.VERSION.SDK_INT     // Catch:{ Exception -> 0x0062 }
            r3 = 29
            java.lang.String r4 = ".risk"
            if (r2 < r3) goto L_0x0015
            java.io.File r5 = cn.missfresh.utils.c.a(r5, r4)
            goto L_0x002e
        L_0x0015:
            java.io.File r5 = new java.io.File
            java.io.File r2 = android.os.Environment.getExternalStorageDirectory()
            r5.<init>(r2, r4)
            boolean r2 = r5.exists()
            if (r2 != 0) goto L_0x002e
            boolean r2 = r5.mkdir()
            if (r2 != 0) goto L_0x002e
            cn.missfresh.sherlock.trace.core.AppMethodBeat.o(r0)
            return
        L_0x002e:
            if (r5 != 0) goto L_0x0034
            cn.missfresh.sherlock.trace.core.AppMethodBeat.o(r0)
            return
        L_0x0034:
            java.io.File r2 = new java.io.File
            java.lang.String r3 = "risk.mid"
            r2.<init>(r5, r3)
            boolean r5 = r2.exists()
            if (r5 != 0) goto L_0x004c
            boolean r5 = r2.createNewFile()
            if (r5 != 0) goto L_0x004c
            cn.missfresh.sherlock.trace.core.AppMethodBeat.o(r0)
            return
        L_0x004c:
            java.io.FileWriter r5 = new java.io.FileWriter
            r5.<init>(r2)
            r5.write(r6)     // Catch:{ Exception -> 0x005c, all -> 0x0058 }
            r5.close()     // Catch:{ Exception -> 0x006b }
            goto L_0x006b
        L_0x0058:
            r6 = move-exception
            r1 = r5
            r5 = r6
            goto L_0x006f
        L_0x005c:
            r6 = move-exception
            r1 = r5
            r5 = r6
            goto L_0x0063
        L_0x0060:
            r5 = move-exception
            goto L_0x006f
        L_0x0062:
            r5 = move-exception
        L_0x0063:
            r5.printStackTrace()     // Catch:{ all -> 0x0060 }
            if (r1 == 0) goto L_0x006b
            r1.close()
        L_0x006b:
            cn.missfresh.sherlock.trace.core.AppMethodBeat.o(r0)
            return
        L_0x006f:
            if (r1 == 0) goto L_0x0074
            r1.close()     // Catch:{ Exception -> 0x0074 }
        L_0x0074:
            cn.missfresh.sherlock.trace.core.AppMethodBeat.o(r0)
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.missfresh.risk.k.a(android.content.Context, java.lang.String):void");
    }

    /* JADX WARNING: Removed duplicated region for block: B:55:0x0099 A[SYNTHETIC, Splitter:B:55:0x0099] */
    /* JADX WARNING: Removed duplicated region for block: B:59:0x009e A[SYNTHETIC, Splitter:B:59:0x009e] */
    /* JADX WARNING: Removed duplicated region for block: B:66:0x00a8 A[SYNTHETIC, Splitter:B:66:0x00a8] */
    /* JADX WARNING: Removed duplicated region for block: B:70:0x00ad A[SYNTHETIC, Splitter:B:70:0x00ad] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String b(android.content.Context r8) {
        /*
            java.lang.String r0 = ""
            r1 = 924(0x39c, float:1.295E-42)
            r2 = 0
            cn.missfresh.sherlock.trace.core.AppMethodBeat.i(r1, r2)
            r2 = 0
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0092, all -> 0x008f }
            r3.<init>()     // Catch:{ Exception -> 0x0092, all -> 0x008f }
            int r4 = android.os.Build.VERSION.SDK_INT     // Catch:{ Exception -> 0x0092, all -> 0x008f }
            r5 = 29
            java.lang.String r6 = ".risk"
            if (r4 < r5) goto L_0x001d
            java.io.File r8 = cn.missfresh.utils.c.a(r8, r6)
            goto L_0x0026
        L_0x001d:
            java.io.File r8 = new java.io.File
            java.io.File r4 = android.os.Environment.getExternalStorageDirectory()
            r8.<init>(r4, r6)
        L_0x0026:
            if (r8 == 0) goto L_0x008b
            boolean r4 = r8.exists()
            if (r4 == 0) goto L_0x008b
            boolean r4 = r8.isDirectory()
            if (r4 != 0) goto L_0x0036
            goto L_0x008b
        L_0x0036:
            java.io.File r4 = new java.io.File
            java.lang.String r5 = "risk.mid"
            r4.<init>(r8, r5)
            boolean r8 = r4.exists()
            if (r8 == 0) goto L_0x0070
            java.io.FileReader r8 = new java.io.FileReader
            r8.<init>(r4)
            java.io.BufferedReader r5 = new java.io.BufferedReader     // Catch:{ Exception -> 0x006b, all -> 0x0066 }
            java.io.FileReader r6 = new java.io.FileReader     // Catch:{ Exception -> 0x006b, all -> 0x0066 }
            r6.<init>(r4)     // Catch:{ Exception -> 0x006b, all -> 0x0066 }
            r5.<init>(r6)     // Catch:{ Exception -> 0x006b, all -> 0x0066 }
        L_0x0053:
            java.lang.String r2 = r5.readLine()     // Catch:{ Exception -> 0x0061, all -> 0x005f }
            if (r2 == 0) goto L_0x005d
            r3.append(r2)     // Catch:{ Exception -> 0x0061, all -> 0x005f }
            goto L_0x0053
        L_0x005d:
            r2 = r8
            goto L_0x0071
        L_0x005f:
            r0 = move-exception
            goto L_0x0068
        L_0x0061:
            r2 = move-exception
            r7 = r2
            r2 = r8
            r8 = r7
            goto L_0x0094
        L_0x0066:
            r0 = move-exception
            r5 = r2
        L_0x0068:
            r2 = r8
            r8 = r0
            goto L_0x00a6
        L_0x006b:
            r3 = move-exception
            r5 = r2
            r2 = r8
            r8 = r3
            goto L_0x0094
        L_0x0070:
            r5 = r2
        L_0x0071:
            int r8 = r3.length()     // Catch:{ Exception -> 0x0089 }
            if (r8 <= 0) goto L_0x007b
            java.lang.String r0 = r3.toString()     // Catch:{ Exception -> 0x0089 }
        L_0x007b:
            if (r2 == 0) goto L_0x0080
            r2.close()     // Catch:{ Exception -> 0x0080 }
        L_0x0080:
            if (r5 == 0) goto L_0x0085
            r5.close()     // Catch:{ Exception -> 0x0085 }
        L_0x0085:
            cn.missfresh.sherlock.trace.core.AppMethodBeat.o(r1)
            return r0
        L_0x0089:
            r8 = move-exception
            goto L_0x0094
        L_0x008b:
            cn.missfresh.sherlock.trace.core.AppMethodBeat.o(r1)
            return r0
        L_0x008f:
            r8 = move-exception
            r5 = r2
            goto L_0x00a6
        L_0x0092:
            r8 = move-exception
            r5 = r2
        L_0x0094:
            r8.printStackTrace()     // Catch:{ all -> 0x00a5 }
            if (r2 == 0) goto L_0x009c
            r2.close()     // Catch:{ Exception -> 0x009c }
        L_0x009c:
            if (r5 == 0) goto L_0x00a1
            r5.close()     // Catch:{ Exception -> 0x00a1 }
        L_0x00a1:
            cn.missfresh.sherlock.trace.core.AppMethodBeat.o(r1)
            return r0
        L_0x00a5:
            r8 = move-exception
        L_0x00a6:
            if (r2 == 0) goto L_0x00ab
            r2.close()     // Catch:{ Exception -> 0x00ab }
        L_0x00ab:
            if (r5 == 0) goto L_0x00b0
            r5.close()     // Catch:{ Exception -> 0x00b0 }
        L_0x00b0:
            cn.missfresh.sherlock.trace.core.AppMethodBeat.o(r1)
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.missfresh.risk.k.b(android.content.Context):java.lang.String");
    }

    public static String a(String str) {
        AppMethodBeat.i(MetricsProto.MetricsEvent.FIELD_QS_POSITION, false);
        if (!e.a(str)) {
            File file = new File(str);
            if (file.listFiles() != null && file.listFiles().length > 0) {
                StringBuilder sb = new StringBuilder();
                File[] listFiles = file.listFiles();
                for (File file2 : listFiles) {
                    sb.append(file2.getName() + file.lastModified());
                }
                String a = cn.missfresh.risk.f.e.a(sb.toString());
                AppMethodBeat.o(MetricsProto.MetricsEvent.FIELD_QS_POSITION);
                return a;
            }
        }
        AppMethodBeat.o(MetricsProto.MetricsEvent.FIELD_QS_POSITION);
        return "";
    }
}
