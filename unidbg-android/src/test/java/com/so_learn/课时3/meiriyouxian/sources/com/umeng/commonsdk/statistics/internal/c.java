package com.umeng.commonsdk.statistics.internal;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.text.TextUtils;
import com.umeng.analytics.CoreProtocol;
import com.umeng.analytics.pro.k;
import com.umeng.commonsdk.config.FieldManager;
import com.umeng.commonsdk.debug.UMRTLog;
import com.umeng.commonsdk.framework.UMEnvelopeBuild;
import com.umeng.commonsdk.framework.UMWorkDispatch;
import com.umeng.commonsdk.internal.a;
import com.umeng.commonsdk.internal.crash.UMCrashManager;
import com.umeng.commonsdk.statistics.AnalyticsConstants;
import com.umeng.commonsdk.statistics.SdkVersion;
import com.umeng.commonsdk.statistics.UMServerURL;
import com.umeng.commonsdk.statistics.b;
import com.umeng.commonsdk.statistics.common.DataHelper;
import com.umeng.commonsdk.statistics.common.DeviceConfig;
import com.umeng.commonsdk.statistics.idtracking.ImprintHandler;
import java.io.File;

/* compiled from: NetworkHelper */
public class c {
    private static boolean e;
    private static boolean f;
    private static boolean g;
    private String a = "10.0.0.172";
    private int b = 80;
    private Context c;
    private b d;

    public c(Context context) {
        this.c = context;
    }

    public void a(b bVar) {
        this.d = bVar;
    }

    private void a() {
        String imprintProperty = UMEnvelopeBuild.imprintProperty(this.c, "domain_p", "");
        String imprintProperty2 = UMEnvelopeBuild.imprintProperty(this.c, "domain_s", "");
        if (!TextUtils.isEmpty(imprintProperty)) {
            UMServerURL.DEFAULT_URL = DataHelper.assembleURL(imprintProperty);
        }
        if (!TextUtils.isEmpty(imprintProperty2)) {
            UMServerURL.SECONDARY_URL = DataHelper.assembleURL(imprintProperty2);
        }
        AnalyticsConstants.APPLOG_URL_LIST = new String[]{UMServerURL.DEFAULT_URL, UMServerURL.SECONDARY_URL};
    }

    private void b() {
        String imprintProperty = UMEnvelopeBuild.imprintProperty(this.c, "domain_p", "");
        String imprintProperty2 = UMEnvelopeBuild.imprintProperty(this.c, "domain_s", "");
        if (!TextUtils.isEmpty(imprintProperty)) {
            UMServerURL.DEFAULT_URL = DataHelper.assembleURL(imprintProperty);
        }
        if (!TextUtils.isEmpty(imprintProperty2)) {
            UMServerURL.SECONDARY_URL = DataHelper.assembleURL(imprintProperty2);
        }
        String imprintProperty3 = UMEnvelopeBuild.imprintProperty(this.c, "oversea_domain_p", "");
        String imprintProperty4 = UMEnvelopeBuild.imprintProperty(this.c, "oversea_domain_s", "");
        if (!TextUtils.isEmpty(imprintProperty3)) {
            UMServerURL.OVERSEA_DEFAULT_URL = DataHelper.assembleURL(imprintProperty3);
        }
        if (!TextUtils.isEmpty(imprintProperty4)) {
            UMServerURL.OVERSEA_SECONDARY_URL = DataHelper.assembleURL(imprintProperty4);
        }
        AnalyticsConstants.APPLOG_URL_LIST = new String[]{UMServerURL.OVERSEA_DEFAULT_URL, UMServerURL.OVERSEA_SECONDARY_URL};
        if (TextUtils.isEmpty(b.b)) {
            return;
        }
        if (b.b.startsWith("460") || b.b.startsWith("461")) {
            AnalyticsConstants.APPLOG_URL_LIST = new String[]{UMServerURL.DEFAULT_URL, UMServerURL.SECONDARY_URL};
        }
    }

    private void c() {
        if (!g) {
            ImprintHandler.getImprintService(this.c).registImprintCallback(AnalyticsConstants.CFG_FIELD_KEY, new AnonymousClass1());
            g = true;
        }
    }

    /* compiled from: NetworkHelper */
    /* access modifiers changed from: package-private */
    /* renamed from: com.umeng.commonsdk.statistics.internal.c$1  reason: invalid class name */
    public class AnonymousClass1 implements UMImprintChangeCallback {
        AnonymousClass1() {
        }

        @Override // com.umeng.commonsdk.statistics.internal.UMImprintChangeCallback
        public void onImprintValueChanged(String str, String str2) {
            if (FieldManager.b()) {
                FieldManager.a().a(c.this.c, str2);
                UMRTLog.e(UMRTLog.RTLOG_TAG, "--->>> apply imprint change and exit: key=" + str + "; value= " + str2);
                UMWorkDispatch.sendEvent(c.this.c, 32788, com.umeng.commonsdk.internal.b.a(c.this.c).a(), null);
            }
        }
    }

    private void d() {
        if (!f) {
            UMRTLog.e(UMRTLog.RTLOG_TAG, "--->>> \u6ce8\u518c\u96f6\u53f7\u62a5\u6587 imprint \u5e94\u7b54\u5904\u7406\u56de\u8c03\u3002");
            ImprintHandler.getImprintService(this.c).registPreProcessCallback(AnalyticsConstants.ZERO_RESPONSE_FLAG, new AnonymousClass2());
            ImprintHandler.getImprintService(this.c).registImprintCallback(AnalyticsConstants.CFG_FIELD_KEY, new AnonymousClass3());
            f = true;
        }
    }

    /* compiled from: NetworkHelper */
    /* access modifiers changed from: package-private */
    /* renamed from: com.umeng.commonsdk.statistics.internal.c$2  reason: invalid class name */
    public class AnonymousClass2 implements UMImprintPreProcessCallback {
        AnonymousClass2() {
        }

        @Override // com.umeng.commonsdk.statistics.internal.UMImprintPreProcessCallback
        public boolean onPreProcessImprintKey(String str, String str2) {
            UMRTLog.e(UMRTLog.RTLOG_TAG, "--->>> onImprintValueChanged: key=" + str + "; value= " + str2);
            FieldManager.a().a(c.this.c);
            UMWorkDispatch.sendEvent(c.this.c, a.s, com.umeng.commonsdk.internal.b.a(c.this.c).a(), null);
            ImprintHandler.getImprintService(c.this.c).a(AnalyticsConstants.ZERO_RESPONSE_FLAG);
            return true;
        }
    }

    /* compiled from: NetworkHelper */
    /* access modifiers changed from: package-private */
    /* renamed from: com.umeng.commonsdk.statistics.internal.c$3  reason: invalid class name */
    public class AnonymousClass3 implements UMImprintChangeCallback {
        AnonymousClass3() {
        }

        @Override // com.umeng.commonsdk.statistics.internal.UMImprintChangeCallback
        public void onImprintValueChanged(String str, String str2) {
            UMRTLog.e(UMRTLog.RTLOG_TAG, "--->>> first onImprintValueChanged: key=" + str + "; value= " + str2);
            FieldManager.a().a(c.this.c, str2);
            UMWorkDispatch.sendEvent(c.this.c, a.s, com.umeng.commonsdk.internal.b.a(c.this.c).a(), null);
            if (FieldManager.allow(com.umeng.commonsdk.utils.b.E)) {
                UMRTLog.i(UMRTLog.RTLOG_TAG, "--->>> recv zcfg response: foregound count timer enabled.");
                if (!UMWorkDispatch.eventHasExist()) {
                    UMWorkDispatch.sendEventEx(c.this.c, 8213, CoreProtocol.getInstance(c.this.c), null, 0);
                }
            }
            if (FieldManager.allow(com.umeng.commonsdk.utils.b.F)) {
                UMRTLog.i(UMRTLog.RTLOG_TAG, "--->>> recv zcfg response: FirstResumeTrigger enabled.");
                k.a(c.this.c).b(c.this.c);
            }
            ImprintHandler.getImprintService(c.this.c).unregistImprintCallback(AnalyticsConstants.CFG_FIELD_KEY, this);
        }
    }

    public byte[] a(byte[] bArr, boolean z, boolean z2, String str) {
        if (SdkVersion.SDK_TYPE == 0) {
            a();
        } else {
            UMServerURL.DEFAULT_URL = UMServerURL.OVERSEA_DEFAULT_URL;
            UMServerURL.SECONDARY_URL = UMServerURL.OVERSEA_SECONDARY_URL;
            b();
        }
        int i = 0;
        byte[] bArr2 = null;
        while (true) {
            if (i >= AnalyticsConstants.APPLOG_URL_LIST.length) {
                break;
            }
            String str2 = AnalyticsConstants.APPLOG_URL_LIST[i];
            if (z2) {
                d();
            } else {
                c();
            }
            bArr2 = a(bArr, str2 + File.separator + str);
            if (bArr2 != null) {
                b bVar = this.d;
                if (bVar != null) {
                    bVar.onRequestSucceed(z);
                }
            } else {
                b bVar2 = this.d;
                if (bVar2 != null) {
                    bVar2.onRequestFailed();
                }
                i++;
            }
        }
        return bArr2;
    }

    private boolean e() {
        NetworkInfo activeNetworkInfo;
        String extraInfo;
        if (this.c.getPackageManager().checkPermission("android.permission.ACCESS_NETWORK_STATE", this.c.getPackageName()) != 0) {
            return false;
        }
        try {
            ConnectivityManager connectivityManager = (ConnectivityManager) this.c.getSystemService(Context.CONNECTIVITY_SERVICE);
            if (!DeviceConfig.checkPermission(this.c, "android.permission.ACCESS_NETWORK_STATE") || connectivityManager == null || (activeNetworkInfo = connectivityManager.getActiveNetworkInfo()) == null || activeNetworkInfo.getType() == 1 || (extraInfo = activeNetworkInfo.getExtraInfo()) == null || (!extraInfo.equals("cmwap") && !extraInfo.equals("3gwap") && !extraInfo.equals("uniwap"))) {
                return false;
            }
            return true;
        } catch (Throwable th) {
            UMCrashManager.reportCrash(this.c, th);
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:102:0x0185 A[SYNTHETIC, Splitter:B:102:0x0185] */
    /* JADX WARNING: Removed duplicated region for block: B:65:0x0137 A[SYNTHETIC, Splitter:B:65:0x0137] */
    /* JADX WARNING: Removed duplicated region for block: B:70:0x0143 A[SYNTHETIC, Splitter:B:70:0x0143] */
    /* JADX WARNING: Removed duplicated region for block: B:82:0x0158 A[SYNTHETIC, Splitter:B:82:0x0158] */
    /* JADX WARNING: Removed duplicated region for block: B:87:0x0164 A[SYNTHETIC, Splitter:B:87:0x0164] */
    /* JADX WARNING: Removed duplicated region for block: B:97:0x0179 A[SYNTHETIC, Splitter:B:97:0x0179] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public byte[] a(byte[] r10, java.lang.String r11) {
        /*
        // Method dump skipped, instructions count: 426
        */
        throw new UnsupportedOperationException("Method not decompiled: com.umeng.commonsdk.statistics.internal.c.a(byte[], java.lang.String):byte[]");
    }
}
