package cn.missfresh.risk.bean;

import android.Manifest;
import android.content.Context;
import android.os.Build;
import android.telephony.CellInfo;
import android.telephony.CellInfoCdma;
import android.telephony.CellInfoGsm;
import android.telephony.CellInfoWcdma;
import android.telephony.TelephonyManager;
import android.telephony.gsm.GsmCellLocation;
import cn.missfresh.basiclib.utils.permission.c;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import cn.missfresh.utils.b;
import java.util.ArrayList;
import java.util.List;

public final class TelephonyInfo {
    private static TelephonyInfo telephonyInfo;
    private String iccid;
    private String imei;
    private String imeiSIM1;
    private String imeiSIM2;
    private String imsi;
    private boolean isSIM1Ready;
    private boolean isSIM2Ready;
    private List<CellBean> mCellBeans;
    private String operator;
    private String simPhoneNumber = "";

    public String getImsiSIM1() {
        return this.imeiSIM1;
    }

    public String getImsiSIM2() {
        return this.imeiSIM2;
    }

    public boolean isSIM1Ready() {
        return this.isSIM1Ready;
    }

    public boolean isSIM2Ready() {
        return this.isSIM2Ready;
    }

    public boolean isDualSIM() {
        return this.imeiSIM2 != null;
    }

    public String getImsi() {
        return this.imsi;
    }

    public String getIccid() {
        return this.iccid;
    }

    public String getOperator() {
        return this.operator;
    }

    public String getImei() {
        return this.imei;
    }

    public void setImei(String str) {
        this.imei = str;
    }

    public List<CellBean> getCellBeans() {
        return this.mCellBeans;
    }

    public void setCellBeans(List<CellBean> list) {
        this.mCellBeans = list;
    }

    public String getSimPhoneNumber() {
        return this.simPhoneNumber;
    }

    private TelephonyInfo() {
    }

    public static TelephonyInfo getInstance(Context context) {
        GsmCellLocation gsmCellLocation;
        AppMethodBeat.i(2171, false);
        if (context == null) {
            TelephonyInfo telephonyInfo2 = new TelephonyInfo();
            AppMethodBeat.o(2171);
            return telephonyInfo2;
        }
        if (telephonyInfo == null) {
            c cVar = new c();
            TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
            telephonyInfo = new TelephonyInfo();
            telephonyInfo.mCellBeans = new ArrayList();
            try {
                if (cVar.a(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION)) {
                    if (!(telephonyManager.getCellLocation() == null || (gsmCellLocation = (GsmCellLocation) telephonyManager.getCellLocation()) == null)) {
                        telephonyInfo.mCellBeans.add(new CellBean(gsmCellLocation.getLac(), gsmCellLocation.getCid()));
                    }
                    if (Build.VERSION.SDK_INT >= 17) {
                        List<CellInfo> allCellInfo = telephonyManager.getAllCellInfo();
                        if (!b.a(allCellInfo)) {
                            for (CellInfo cellInfo : allCellInfo) {
                                if ((cellInfo instanceof CellInfoCdma) && ((CellInfoCdma) cellInfo).getCellIdentity() != null) {
                                    telephonyInfo.mCellBeans.add(new CellBean(((CellInfoCdma) cellInfo).getCellIdentity().getLatitude(), ((CellInfoCdma) cellInfo).getCellIdentity().getLongitude()));
                                } else if ((cellInfo instanceof CellInfoGsm) && ((CellInfoGsm) cellInfo).getCellIdentity() != null) {
                                    telephonyInfo.mCellBeans.add(new CellBean(((CellInfoGsm) cellInfo).getCellIdentity().getLac(), ((CellInfoGsm) cellInfo).getCellIdentity().getCid()));
                                } else if (Build.VERSION.SDK_INT >= 18 && (cellInfo instanceof CellInfoWcdma) && ((CellInfoWcdma) cellInfo).getCellIdentity() != null) {
                                    telephonyInfo.mCellBeans.add(new CellBean(((CellInfoWcdma) cellInfo).getCellIdentity().getLac(), ((CellInfoWcdma) cellInfo).getCellIdentity().getCid()));
                                }
                            }
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (!cVar.a("android.permission.READ_PHONE_STATE")) {
                TelephonyInfo telephonyInfo3 = telephonyInfo;
                AppMethodBeat.o(2171);
                return telephonyInfo3;
            }
            try {
                telephonyInfo.imeiSIM1 = telephonyManager.getDeviceId();
                telephonyInfo.imeiSIM2 = null;
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            try {
                telephonyInfo.imeiSIM1 = getDeviceIdBySlot(context, "getDeviceIdGemini", 0);
                telephonyInfo.imeiSIM2 = getDeviceIdBySlot(context, "getDeviceIdGemini", 1);
            } catch (Exception e3) {
                e3.printStackTrace();
                try {
                    telephonyInfo.imeiSIM1 = getDeviceIdBySlot(context, "getDeviceId", 0);
                    telephonyInfo.imeiSIM2 = getDeviceIdBySlot(context, "getDeviceId", 1);
                } catch (Exception e4) {
                    e4.printStackTrace();
                }
            }
            try {
                telephonyInfo.isSIM1Ready = telephonyManager.getSimState() == 5;
                telephonyInfo.isSIM2Ready = false;
            } catch (Exception e5) {
                e5.printStackTrace();
            }
            try {
                telephonyInfo.isSIM1Ready = getSIMStateBySlot(context, "getSimStateGemini", 0);
                telephonyInfo.isSIM2Ready = getSIMStateBySlot(context, "getSimStateGemini", 1);
            } catch (Exception e6) {
                e6.printStackTrace();
                try {
                    telephonyInfo.isSIM1Ready = getSIMStateBySlot(context, "getSimState", 0);
                    telephonyInfo.isSIM2Ready = getSIMStateBySlot(context, "getSimState", 1);
                } catch (GeminiMethodNotFoundException e7) {
                    e7.printStackTrace();
                }
            }
            try {
                telephonyInfo.imsi = telephonyManager.getSubscriberId();
                telephonyInfo.iccid = telephonyManager.getSimSerialNumber();
                telephonyInfo.operator = telephonyManager.getSimOperator();
                telephonyInfo.imei = telephonyManager.getDeviceId();
                telephonyInfo.simPhoneNumber = telephonyManager.getLine1Number();
            } catch (Exception e8) {
                e8.printStackTrace();
            }
        }
        TelephonyInfo telephonyInfo4 = telephonyInfo;
        AppMethodBeat.o(2171);
        return telephonyInfo4;
    }

    private static String getDeviceIdBySlot(Context context, String str, int i) throws GeminiMethodNotFoundException {
        AppMethodBeat.i(2176, false);
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
        try {
            Object invoke = Class.forName(telephonyManager.getClass().getName()).getMethod(str, Integer.TYPE).invoke(telephonyManager, Integer.valueOf(i));
            String obj = invoke != null ? invoke.toString() : null;
            AppMethodBeat.o(2176);
            return obj;
        } catch (Exception e) {
            e.printStackTrace();
            GeminiMethodNotFoundException geminiMethodNotFoundException = new GeminiMethodNotFoundException(str);
            AppMethodBeat.o(2176);
            throw geminiMethodNotFoundException;
        }
    }

    private static boolean getSIMStateBySlot(Context context, String str, int i) throws GeminiMethodNotFoundException {
        boolean z = false;
        AppMethodBeat.i(2181, false);
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
        try {
            Object invoke = Class.forName(telephonyManager.getClass().getName()).getMethod(str, Integer.TYPE).invoke(telephonyManager, Integer.valueOf(i));
            if (invoke != null && Integer.parseInt(invoke.toString()) == 5) {
                z = true;
            }
            AppMethodBeat.o(2181);
            return z;
        } catch (Exception e) {
            e.printStackTrace();
            GeminiMethodNotFoundException geminiMethodNotFoundException = new GeminiMethodNotFoundException(str);
            AppMethodBeat.o(2181);
            throw geminiMethodNotFoundException;
        }
    }

    /* access modifiers changed from: private */
    public static class GeminiMethodNotFoundException extends Exception {
        private static final long serialVersionUID = -996812356902545308L;

        public GeminiMethodNotFoundException(String str) {
            super(str);
        }
    }
}
