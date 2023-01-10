package com.vivo.push;

import android.os.Bundle;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import com.android.internal.logging.nano.MetricsProto;
import java.io.Serializable;
import java.util.ArrayList;

/* compiled from: BundleWapper */
public final class a {
    public Bundle a;
    String b;
    private String c;

    public a(String str, String str2, Bundle bundle) {
        this.b = str;
        this.c = str2;
        this.a = bundle;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:7:0x0027, code lost:
        if (android.text.TextUtils.isEmpty(r4) == false) goto L_0x002b;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.vivo.push.a a(android.content.Intent r7) {
        /*
            r0 = 835(0x343, float:1.17E-42)
            r1 = 0
            cn.missfresh.sherlock.trace.core.AppMethodBeat.i(r0, r1)
            java.lang.String r1 = "BundleWapper"
            r2 = 0
            if (r7 != 0) goto L_0x0016
            java.lang.String r7 = "create error : intent is null"
            com.vivo.push.util.n.a(r1, r7)
            cn.missfresh.sherlock.trace.core.AppMethodBeat.o(r0)
            return r2
        L_0x0016:
            android.os.Bundle r3 = r7.getExtras()
            if (r3 == 0) goto L_0x002a
            java.lang.String r4 = "client_pkgname"
            java.lang.String r4 = r3.getString(r4)
            boolean r5 = android.text.TextUtils.isEmpty(r4)
            if (r5 != 0) goto L_0x002a
            goto L_0x002b
        L_0x002a:
            r4 = r2
        L_0x002b:
            boolean r5 = android.text.TextUtils.isEmpty(r4)
            if (r5 == 0) goto L_0x0037
            java.lang.String r5 = "create warning: pkgName is null"
            com.vivo.push.util.n.b(r1, r5)
        L_0x0037:
            java.lang.String r5 = r7.getPackage()
            boolean r6 = android.text.TextUtils.isEmpty(r5)
            if (r6 == 0) goto L_0x005d
            android.content.ComponentName r5 = r7.getComponent()
            if (r5 != 0) goto L_0x0048
            goto L_0x0050
        L_0x0048:
            android.content.ComponentName r7 = r7.getComponent()
            java.lang.String r2 = r7.getPackageName()
        L_0x0050:
            r5 = r2
            boolean r7 = android.text.TextUtils.isEmpty(r5)
            if (r7 == 0) goto L_0x005d
            java.lang.String r7 = "create warning: targetPkgName is null"
            com.vivo.push.util.n.b(r1, r7)
        L_0x005d:
            com.vivo.push.a r7 = new com.vivo.push.a
            r7.<init>(r4, r5, r3)
            cn.missfresh.sherlock.trace.core.AppMethodBeat.o(r0)
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.vivo.push.a.a(android.content.Intent):com.vivo.push.a");
    }

    public final void a(String str, int i) {
        AppMethodBeat.i(MetricsProto.MetricsEvent.WIFI_NETWORK_RECOMMENDATION_CONNECTION_SUCCESS, false);
        if (this.a == null) {
            this.a = new Bundle();
        }
        this.a.putInt(str, i);
        AppMethodBeat.o(MetricsProto.MetricsEvent.WIFI_NETWORK_RECOMMENDATION_CONNECTION_SUCCESS);
    }

    public final void a(String str, long j) {
        AppMethodBeat.i(MetricsProto.MetricsEvent.STORAGE_FREE_UP_SPACE_NOW, false);
        if (this.a == null) {
            this.a = new Bundle();
        }
        this.a.putLong(str, j);
        AppMethodBeat.o(MetricsProto.MetricsEvent.STORAGE_FREE_UP_SPACE_NOW);
    }

    public final void a(String str, String str2) {
        AppMethodBeat.i(MetricsProto.MetricsEvent.DEFAULT_VOICE_INPUT_PICKER, false);
        if (this.a == null) {
            this.a = new Bundle();
        }
        this.a.putString(str, str2);
        AppMethodBeat.o(MetricsProto.MetricsEvent.DEFAULT_VOICE_INPUT_PICKER);
    }

    public final void a(String str, Serializable serializable) {
        AppMethodBeat.i(MetricsProto.MetricsEvent.FIELD_SETTINGS_BUILD_NUMBER_DEVELOPER_MODE_ENABLED, false);
        if (this.a == null) {
            this.a = new Bundle();
        }
        this.a.putSerializable(str, serializable);
        AppMethodBeat.o(MetricsProto.MetricsEvent.FIELD_SETTINGS_BUILD_NUMBER_DEVELOPER_MODE_ENABLED);
    }

    public final void a(String str, ArrayList<String> arrayList) {
        AppMethodBeat.i(MetricsProto.MetricsEvent.ACTION_SETTINGS_MENU_BATTERY_APPS_TOGGLE, false);
        if (this.a == null) {
            this.a = new Bundle();
        }
        this.a.putStringArrayList(str, arrayList);
        AppMethodBeat.o(MetricsProto.MetricsEvent.ACTION_SETTINGS_MENU_BATTERY_APPS_TOGGLE);
    }

    public final String a(String str) {
        AppMethodBeat.i(MetricsProto.MetricsEvent.FIELD_SETTINGS_PREFERENCE_CHANGE_VALUE, false);
        Bundle bundle = this.a;
        String string = bundle == null ? null : bundle.getString(str);
        AppMethodBeat.o(MetricsProto.MetricsEvent.FIELD_SETTINGS_PREFERENCE_CHANGE_VALUE);
        return string;
    }

    public final int b(String str, int i) {
        AppMethodBeat.i(MetricsProto.MetricsEvent.ACTION_NOTIFICATION_CHANNEL_GROUP, false);
        Bundle bundle = this.a;
        if (bundle == null) {
            AppMethodBeat.o(MetricsProto.MetricsEvent.ACTION_NOTIFICATION_CHANNEL_GROUP);
            return i;
        }
        int i2 = bundle.getInt(str, i);
        AppMethodBeat.o(MetricsProto.MetricsEvent.ACTION_NOTIFICATION_CHANNEL_GROUP);
        return i2;
    }

    public final ArrayList<String> b(String str) {
        AppMethodBeat.i(MetricsProto.MetricsEvent.ACTION_PHONE_EXISTS, false);
        Bundle bundle = this.a;
        ArrayList<String> stringArrayList = bundle == null ? null : bundle.getStringArrayList(str);
        AppMethodBeat.o(MetricsProto.MetricsEvent.ACTION_PHONE_EXISTS);
        return stringArrayList;
    }

    public final long b(String str, long j) {
        AppMethodBeat.i(MetricsProto.MetricsEvent.ACTION_SETTINGS_BLUETOOTH_CONNECT, false);
        Bundle bundle = this.a;
        if (bundle == null) {
            AppMethodBeat.o(MetricsProto.MetricsEvent.ACTION_SETTINGS_BLUETOOTH_CONNECT);
            return j;
        }
        long j2 = bundle.getLong(str, j);
        AppMethodBeat.o(MetricsProto.MetricsEvent.ACTION_SETTINGS_BLUETOOTH_CONNECT);
        return j2;
    }
}
