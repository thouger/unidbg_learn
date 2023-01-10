package cn.missfresh.risk.net;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.util.Log;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import java.net.NetworkInterface;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;

public class WifiInfoManager {

    public static class InOutWifiScanResultsReceiver extends BroadcastReceiver {
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            AppMethodBeat.i(2313, false);
            List<ScanResult> a = a(context);
            Log.d("ScanResults", "Received results (" + a.size() + " AP's)");
            AppMethodBeat.o(2313);
        }

        private List<ScanResult> a(Context context) {
            AppMethodBeat.i(2318, false);
            try {
                List<ScanResult> scanResults = ((WifiManager) context.getSystemService("wifi")).getScanResults();
                AppMethodBeat.o(2318);
                return scanResults;
            } catch (SecurityException unused) {
                ArrayList arrayList = new ArrayList();
                AppMethodBeat.o(2318);
                return arrayList;
            }
        }
    }

    public static String a() {
        AppMethodBeat.i(2340, false);
        StringBuffer stringBuffer = new StringBuffer();
        try {
            NetworkInterface byName = NetworkInterface.getByName("eth1");
            if (byName == null) {
                byName = NetworkInterface.getByName("wlan0");
            }
            if (byName == null) {
                AppMethodBeat.o(2340);
                return "02:00:00:00:00:02";
            }
            byte[] hardwareAddress = byName.getHardwareAddress();
            if (hardwareAddress != null && hardwareAddress.length > 0) {
                int length = hardwareAddress.length;
                for (int i = 0; i < length; i++) {
                    stringBuffer.append(String.format("%02X:", Byte.valueOf(hardwareAddress[i])));
                }
            }
            if (stringBuffer.length() > 0) {
                stringBuffer.deleteCharAt(stringBuffer.length() - 1);
            }
            String stringBuffer2 = stringBuffer.toString();
            AppMethodBeat.o(2340);
            return stringBuffer2;
        } catch (Exception e) {
            e.printStackTrace();
            AppMethodBeat.o(2340);
            return "02:00:00:00:00:02";
        }
    }

    public static String a(Context context) {
        AppMethodBeat.i(2346, false);
        String str = null;
        try {
            WifiManager wifiManager = (WifiManager) context.getApplicationContext().getSystemService("wifi");
            if (wifiManager != null) {
                List<ScanResult> scanResults = wifiManager.getScanResults();
                WifiInfo connectionInfo = scanResults != null ? wifiManager.getConnectionInfo() : null;
                if (!(scanResults == null || connectionInfo == null)) {
                    for (int i = 0; i < scanResults.size(); i++) {
                        ScanResult scanResult = scanResults.get(i);
                        if (connectionInfo.getBSSID().equals(scanResult.BSSID)) {
                            str = scanResult.BSSID;
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        AppMethodBeat.o(2346);
        return str;
    }

    public static boolean b() {
        AppMethodBeat.i(2353, false);
        try {
            Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
            if (networkInterfaces != null) {
                ArrayList<NetworkInterface> list = Collections.list(networkInterfaces);
                if (list != null) {
                    if (list.size() >= 1) {
                        for (NetworkInterface networkInterface : list) {
                            if (networkInterface.isUp()) {
                                if (networkInterface.getInterfaceAddresses().size() != 0) {
                                    if ("tun0".equals(networkInterface.getName()) || "ppp0".equals(networkInterface.getName())) {
                                        AppMethodBeat.o(2353);
                                        return true;
                                    }
                                }
                            }
                        }
                    }
                }
                AppMethodBeat.o(2353);
                return false;
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
        AppMethodBeat.o(2353);
        return false;
    }
}
