package com.vivo.push.cache;

import android.content.Context;
import android.text.TextUtils;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import com.vivo.push.model.a;
import com.vivo.push.util.ContextDelegate;
import com.vivo.push.util.n;
import com.xiaomi.mipush.sdk.Constants;
import java.util.HashSet;
import java.util.Set;

public class ClientConfigManagerImpl implements d {
    private static final String TAG = "ClientConfigManager";
    private static volatile ClientConfigManagerImpl sClientConfigManagerImpl;
    private a mAppConfigSettings = new a(this.mContext);
    private Context mContext;
    private e mPushConfigSettings = new e(this.mContext);

    public Set<String> getBlackEventList() {
        return null;
    }

    private ClientConfigManagerImpl(Context context) {
        AppMethodBeat.i(2797, false);
        this.mContext = ContextDelegate.getContext(context).getApplicationContext();
        AppMethodBeat.o(2797);
    }

    public static synchronized ClientConfigManagerImpl getInstance(Context context) {
        ClientConfigManagerImpl clientConfigManagerImpl;
        synchronized (ClientConfigManagerImpl.class) {
            AppMethodBeat.i(2801, false);
            if (sClientConfigManagerImpl == null) {
                sClientConfigManagerImpl = new ClientConfigManagerImpl(context);
            }
            clientConfigManagerImpl = sClientConfigManagerImpl;
            AppMethodBeat.o(2801);
        }
        return clientConfigManagerImpl;
    }

    public boolean isEnablePush() {
        AppMethodBeat.i(2806, false);
        prepareAppConfig();
        a c = this.mAppConfigSettings.c(this.mContext.getPackageName());
        if (c != null) {
            boolean equals = "1".equals(c.b);
            AppMethodBeat.o(2806);
            return equals;
        }
        AppMethodBeat.o(2806);
        return true;
    }

    private void prepareAppConfig() {
        AppMethodBeat.i(2809, false);
        a aVar = this.mAppConfigSettings;
        if (aVar == null) {
            this.mAppConfigSettings = new a(this.mContext);
            AppMethodBeat.o(2809);
            return;
        }
        aVar.c();
        AppMethodBeat.o(2809);
    }

    public void clearPush() {
        AppMethodBeat.i(2813, false);
        this.mAppConfigSettings.d();
        AppMethodBeat.o(2813);
    }

    @Override // com.vivo.push.cache.d
    public boolean isInBlackList(long j) {
        AppMethodBeat.i(2819, false);
        String c = preparePushConfigSettings().c("BL");
        if (!TextUtils.isEmpty(c)) {
            String[] split = c.split(Constants.ACCEPT_TIME_SEPARATOR_SP);
            for (String str : split) {
                try {
                    if (!TextUtils.isEmpty(str) && Long.parseLong(str) == j) {
                        AppMethodBeat.o(2819);
                        return true;
                    }
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
            }
        }
        AppMethodBeat.o(2819);
        return false;
    }

    public int getNotifyStyle() {
        int i = 0;
        AppMethodBeat.i(2824, false);
        try {
            String c = preparePushConfigSettings().c("DPL");
            if (!TextUtils.isEmpty(c)) {
                try {
                    i = Integer.parseInt(c);
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
            }
        } catch (NumberFormatException e2) {
            e2.printStackTrace();
        }
        AppMethodBeat.o(2824);
        return i;
    }

    /* JADX WARNING: Removed duplicated region for block: B:11:0x002a  */
    /* JADX WARNING: Removed duplicated region for block: B:9:0x0025  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean isCancleBroadcastReceiver() {
        /*
            r4 = this;
            r0 = 0
            r1 = 2827(0xb0b, float:3.961E-42)
            cn.missfresh.sherlock.trace.core.AppMethodBeat.i(r1, r0)
            com.vivo.push.cache.e r2 = r4.preparePushConfigSettings()
            java.lang.String r3 = "PSM"
            java.lang.String r2 = r2.c(r3)
            boolean r3 = android.text.TextUtils.isEmpty(r2)
            if (r3 != 0) goto L_0x0020
            int r2 = java.lang.Integer.parseInt(r2)     // Catch:{ NumberFormatException -> 0x001c }
            goto L_0x0021
        L_0x001c:
            r2 = move-exception
            r2.printStackTrace()
        L_0x0020:
            r2 = r0
        L_0x0021:
            r2 = r2 & 4
            if (r2 == 0) goto L_0x002a
            r0 = 1
            cn.missfresh.sherlock.trace.core.AppMethodBeat.o(r1)
            return r0
        L_0x002a:
            cn.missfresh.sherlock.trace.core.AppMethodBeat.o(r1)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.vivo.push.cache.ClientConfigManagerImpl.isCancleBroadcastReceiver():boolean");
    }

    private e preparePushConfigSettings() {
        AppMethodBeat.i(2830, false);
        e eVar = this.mPushConfigSettings;
        if (eVar == null) {
            this.mPushConfigSettings = new e(this.mContext);
        } else {
            eVar.c();
        }
        e eVar2 = this.mPushConfigSettings;
        AppMethodBeat.o(2830);
        return eVar2;
    }

    public String getSuitTag() {
        AppMethodBeat.i(2834, false);
        String c = preparePushConfigSettings().c("CSPT");
        AppMethodBeat.o(2834);
        return c;
    }

    public boolean isDebug() {
        AppMethodBeat.i(2837, false);
        this.mAppConfigSettings.c();
        boolean a = a.a(this.mAppConfigSettings.b());
        AppMethodBeat.o(2837);
        return a;
    }

    public boolean isDebug(int i) {
        AppMethodBeat.i(2841, false);
        boolean a = a.a(i);
        AppMethodBeat.o(2841);
        return a;
    }

    public String getValueByKey(String str) {
        AppMethodBeat.i(2844, false);
        if (TextUtils.isEmpty(str)) {
            AppMethodBeat.o(2844);
            return null;
        }
        this.mPushConfigSettings.c();
        String c = this.mPushConfigSettings.c(str);
        AppMethodBeat.o(2844);
        return c;
    }

    public Set<Long> getWhiteLogList() {
        AppMethodBeat.i(2846, false);
        HashSet hashSet = new HashSet();
        String valueByKey = getValueByKey("WLL");
        if (!TextUtils.isEmpty(valueByKey)) {
            for (String str : valueByKey.split(Constants.ACCEPT_TIME_SEPARATOR_SP)) {
                try {
                    hashSet.add(Long.valueOf(Long.parseLong(str)));
                } catch (Exception unused) {
                }
            }
        }
        n.d(TAG, " initWhiteLogList " + hashSet);
        AppMethodBeat.o(2846);
        return hashSet;
    }
}
