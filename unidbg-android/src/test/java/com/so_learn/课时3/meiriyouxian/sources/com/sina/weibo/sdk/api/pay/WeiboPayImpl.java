package com.sina.weibo.sdk.api.pay;

import android.app.Activity;
import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import com.android.internal.logging.nano.MetricsProto;
import com.sina.weibo.sdk.a.d;
import com.sina.weibo.sdk.a.e;
import com.sina.weibo.sdk.a.k;
import com.sina.weibo.sdk.api.share.IWeiboDownloadListener;
import com.sina.weibo.sdk.b;
import com.sina.weibo.sdk.exception.WeiboShareException;

public class WeiboPayImpl {
    private static final String TAG = WeiboPayImpl.class.getName();
    private String mAppKey;
    private Context mContext;
    private Dialog mDownloadConfirmDialog = null;
    private IWeiboDownloadListener mDownloadListener;
    private boolean mNeedDownloadWeibo = true;
    private b.a mWeiboInfo = null;

    private boolean checkEnvironment(boolean z) throws WeiboShareException {
        return true;
    }

    public boolean isWeiboAppInstalled() {
        return true;
    }

    public WeiboPayImpl(Context context, String str, boolean z) {
        this.mContext = context;
        this.mAppKey = str;
        this.mWeiboInfo = b.a(context).a();
        b.a aVar = this.mWeiboInfo;
        if (aVar != null) {
            d.a(TAG, aVar.toString());
        } else {
            d.a(TAG, "WeiboInfo is null");
        }
        com.sina.weibo.sdk.a.b.a(context).a(str);
    }

    public boolean launchWeiboPay(Activity activity, String str) {
        Bundle bundle = new Bundle();
        bundle.putString("rawdata", str);
        bundle.putInt("_weibo_command_type", 4);
        bundle.putString("_weibo_transaction", String.valueOf(System.currentTimeMillis()));
        return launchWeiboActivity(activity, "com.sina.weibo.sdk.action.ACTION_WEIBO_PAY_ACTIVITY", this.mWeiboInfo.a(), this.mAppKey, bundle);
    }

    private boolean launchWeiboActivity(Activity activity, String str, String str2, String str3, Bundle bundle) {
        if (activity == null || TextUtils.isEmpty(str) || TextUtils.isEmpty(str2) || TextUtils.isEmpty(str3)) {
            d.c(TAG, "launchWeiboActivity fail, invalid arguments");
            return false;
        }
        Intent intent = new Intent();
        intent.setPackage(str2);
        intent.setAction(str);
        String packageName = activity.getPackageName();
        intent.putExtra("_weibo_sdkVersion", "0031405000");
        intent.putExtra("_weibo_appPackage", packageName);
        intent.putExtra("_weibo_appKey", str3);
        intent.putExtra("_weibo_flag", 538116905);
        intent.putExtra("_weibo_sign", e.a(k.a(activity, packageName)));
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        try {
            String str4 = TAG;
            d.a(str4, "launchWeiboActivity intent=" + intent + ", extra=" + intent.getExtras());
            activity.startActivityForResult(intent, MetricsProto.MetricsEvent.APP_SPECIAL_PERMISSION_BATTERY_DENY);
            return true;
        } catch (ActivityNotFoundException e) {
            d.c(TAG, e.getMessage());
            return false;
        }
    }

    public int getWeiboAppSupportAPI() {
        b.a aVar = this.mWeiboInfo;
        if (aVar == null || !aVar.c()) {
            return -1;
        }
        return this.mWeiboInfo.b();
    }

    public boolean isWeiboAppSupportAPI() {
        return getWeiboAppSupportAPI() >= 10350;
    }

    public boolean isSupportWeiboPay() {
        return getWeiboAppSupportAPI() >= 10353;
    }

    public void registerWeiboDownloadListener(IWeiboDownloadListener iWeiboDownloadListener) {
        this.mDownloadListener = iWeiboDownloadListener;
    }
}
