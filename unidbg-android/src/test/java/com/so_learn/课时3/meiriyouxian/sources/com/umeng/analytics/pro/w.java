package com.umeng.analytics.pro;

import android.content.Context;
import android.content.SharedPreferences;
import com.umeng.analytics.AnalyticsConfig;
import com.umeng.commonsdk.statistics.internal.PreferenceWrapper;
import com.umeng.commonsdk.utils.UMUtils;

/* compiled from: SessionIdGenerateServiceImpl */
/* access modifiers changed from: package-private */
public class w implements v {
    private long a = AnalyticsConfig.kContinueSessionMillis;

    w() {
    }

    @Override // com.umeng.analytics.pro.v
    public void a(long j) {
        this.a = j;
    }

    @Override // com.umeng.analytics.pro.v
    public long a() {
        return this.a;
    }

    @Override // com.umeng.analytics.pro.v
    public String a(Context context) {
        String appkey = UMUtils.getAppkey(context);
        long currentTimeMillis = System.currentTimeMillis();
        if (appkey != null) {
            return UMUtils.MD5(currentTimeMillis + appkey + "02:00:00:00:00:00");
        }
        throw new RuntimeException("Appkey is null or empty, Please check!");
    }

    @Override // com.umeng.analytics.pro.v
    public boolean a(long j, long j2) {
        long currentTimeMillis = System.currentTimeMillis();
        if ((j == 0 || currentTimeMillis - j >= this.a) && j2 > 0 && currentTimeMillis - j2 > this.a) {
            return true;
        }
        return false;
    }

    @Override // com.umeng.analytics.pro.v
    public void a(Context context, String str) {
        long currentTimeMillis = System.currentTimeMillis();
        try {
            SharedPreferences.Editor edit = PreferenceWrapper.getDefault(context).edit();
            edit.putString("session_id", str);
            edit.putLong(t.b, 0);
            edit.putLong(t.e, currentTimeMillis);
            edit.putLong(t.f, 0);
            edit.commit();
        } catch (Exception unused) {
        }
    }
}
