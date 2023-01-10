package com.sina.weibo.sdk.api.share;

import android.app.Activity;
import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import com.android.internal.logging.nano.MetricsProto;
import com.sina.weibo.sdk.a;
import com.sina.weibo.sdk.a.d;
import com.sina.weibo.sdk.a.k;
import com.sina.weibo.sdk.api.WeiboMessage;
import com.sina.weibo.sdk.api.WeiboMultiMessage;
import com.sina.weibo.sdk.api.share.IWeiboHandler;
import com.sina.weibo.sdk.auth.c;
import com.sina.weibo.sdk.b;
import com.sina.weibo.sdk.cmd.e;
import com.sina.weibo.sdk.component.ShareRequestParam;
import com.sina.weibo.sdk.component.WeiboSdkBrowser;
import com.sina.weibo.sdk.exception.WeiboShareException;
import com.sina.weibo.sdk.statistic.WBAgent;
import java.util.HashMap;

/* access modifiers changed from: package-private */
public class WeiboShareAPIImpl implements IWeiboShareAPI {
    private static final String TAG = WeiboShareAPIImpl.class.getName();
    private String mAppKey;
    private Context mContext;
    private Dialog mDownloadConfirmDialog = null;
    private IWeiboDownloadListener mDownloadListener;
    private boolean mNeedDownloadWeibo = true;
    private b.a mWeiboInfo = null;

    public WeiboShareAPIImpl(Context context, String str, boolean z) {
        this.mContext = context;
        this.mAppKey = str;
        this.mNeedDownloadWeibo = z;
        this.mWeiboInfo = b.a(context).a();
        b.a aVar = this.mWeiboInfo;
        if (aVar != null) {
            d.a(TAG, aVar.toString());
        } else {
            d.a(TAG, "WeiboInfo is null");
        }
        com.sina.weibo.sdk.a.b.a(context).a(str);
    }

    @Override // com.sina.weibo.sdk.api.share.IWeiboShareAPI
    public int getWeiboAppSupportAPI() {
        b.a aVar = this.mWeiboInfo;
        if (aVar == null || !aVar.c()) {
            return -1;
        }
        return this.mWeiboInfo.b();
    }

    @Override // com.sina.weibo.sdk.api.share.IWeiboShareAPI
    public boolean isWeiboAppInstalled() {
        b.a aVar = this.mWeiboInfo;
        return aVar != null && aVar.c();
    }

    @Override // com.sina.weibo.sdk.api.share.IWeiboShareAPI
    public boolean isWeiboAppSupportAPI() {
        return getWeiboAppSupportAPI() >= 10350;
    }

    @Override // com.sina.weibo.sdk.api.share.IWeiboShareAPI
    public boolean isSupportWeiboPay() {
        return getWeiboAppSupportAPI() >= 10353;
    }

    @Override // com.sina.weibo.sdk.api.share.IWeiboShareAPI
    public boolean registerApp() {
        sendBroadcast(this.mContext, "com.sina.weibo.sdk.Intent.ACTION_WEIBO_REGISTER", this.mAppKey, null, null);
        return true;
    }

    @Override // com.sina.weibo.sdk.api.share.IWeiboShareAPI
    public boolean handleWeiboResponse(Intent intent, IWeiboHandler.Response response) {
        String stringExtra = intent.getStringExtra("_weibo_appPackage");
        String stringExtra2 = intent.getStringExtra("_weibo_transaction");
        if (TextUtils.isEmpty(stringExtra)) {
            d.c(TAG, "handleWeiboResponse faild appPackage is null");
            return false;
        } else if (!(response instanceof Activity)) {
            d.c(TAG, "handleWeiboResponse faild handler is not Activity");
            return false;
        } else {
            Activity activity = (Activity) response;
            String callingPackage = activity.getCallingPackage();
            String str = TAG;
            d.a(str, "handleWeiboResponse getCallingPackage : " + callingPackage);
            if (TextUtils.isEmpty(stringExtra2)) {
                d.c(TAG, "handleWeiboResponse faild intent _weibo_transaction is null");
                return false;
            } else if (a.a(this.mContext, stringExtra) || stringExtra.equals(activity.getPackageName())) {
                response.onResponse(new SendMessageToWeiboResponse(intent.getExtras()));
                return true;
            } else {
                d.c(TAG, "handleWeiboResponse faild appPackage validateSign faild");
                return false;
            }
        }
    }

    @Override // com.sina.weibo.sdk.api.share.IWeiboShareAPI
    public boolean handleWeiboRequest(Intent intent, IWeiboHandler.Request request) {
        if (intent == null || request == null) {
            return false;
        }
        String stringExtra = intent.getStringExtra("_weibo_appPackage");
        String stringExtra2 = intent.getStringExtra("_weibo_transaction");
        if (TextUtils.isEmpty(stringExtra)) {
            d.c(TAG, "handleWeiboRequest faild appPackage validateSign faild");
            request.onRequest(null);
            return false;
        } else if (TextUtils.isEmpty(stringExtra2)) {
            d.c(TAG, "handleWeiboRequest faild intent _weibo_transaction is null");
            request.onRequest(null);
            return false;
        } else if (!a.a(this.mContext, stringExtra)) {
            d.c(TAG, "handleWeiboRequest faild appPackage validateSign faild");
            request.onRequest(null);
            return false;
        } else {
            request.onRequest(new ProvideMessageForWeiboRequest(intent.getExtras()));
            return true;
        }
    }

    @Override // com.sina.weibo.sdk.api.share.IWeiboShareAPI
    public boolean launchWeibo(Activity activity) {
        if (!isWeiboAppInstalled()) {
            d.c(TAG, "launchWeibo faild WeiboInfo is null");
            return false;
        }
        try {
            activity.startActivity(activity.getPackageManager().getLaunchIntentForPackage(this.mWeiboInfo.a()));
            return true;
        } catch (Exception e) {
            d.c(TAG, e.getMessage());
            return false;
        }
    }

    @Override // com.sina.weibo.sdk.api.share.IWeiboShareAPI
    public boolean sendRequest(Activity activity, BaseRequest baseRequest) {
        if (baseRequest == null) {
            d.c(TAG, "sendRequest faild request is null");
            return false;
        }
        try {
            if (!checkEnvironment(this.mNeedDownloadWeibo)) {
                return false;
            }
            if (!baseRequest.check(this.mContext, this.mWeiboInfo, new VersionCheckHandler())) {
                d.c(TAG, "sendRequest faild request check faild");
                return false;
            }
            e.a(this.mContext, this.mAppKey).a();
            Bundle bundle = new Bundle();
            baseRequest.toBundle(bundle);
            return launchWeiboActivity(activity, "com.sina.weibo.sdk.action.ACTION_WEIBO_ACTIVITY", this.mWeiboInfo.a(), this.mAppKey, bundle, "share");
        } catch (Exception e) {
            d.c(TAG, e.getMessage());
            return false;
        }
    }

    @Override // com.sina.weibo.sdk.api.share.IWeiboShareAPI
    public boolean sendRequest(Activity activity, BaseRequest baseRequest, com.sina.weibo.sdk.auth.a aVar, String str, c cVar) {
        if (baseRequest == null) {
            d.c(TAG, "sendRequest faild request is null !");
            return false;
        } else if (!isWeiboAppInstalled() || !isWeiboAppSupportAPI()) {
            return startShareWeiboActivity(activity, str, baseRequest, cVar);
        } else {
            if (getWeiboAppSupportAPI() >= 10351) {
                return sendRequest(activity, baseRequest);
            }
            if (!(baseRequest instanceof SendMultiMessageToWeiboRequest)) {
                return sendRequest(activity, baseRequest);
            }
            SendMultiMessageToWeiboRequest sendMultiMessageToWeiboRequest = (SendMultiMessageToWeiboRequest) baseRequest;
            SendMessageToWeiboRequest sendMessageToWeiboRequest = new SendMessageToWeiboRequest();
            sendMessageToWeiboRequest.packageName = sendMultiMessageToWeiboRequest.packageName;
            sendMessageToWeiboRequest.transaction = sendMultiMessageToWeiboRequest.transaction;
            sendMessageToWeiboRequest.message = adapterMultiMessage2SingleMessage(sendMultiMessageToWeiboRequest.multiMessage);
            return sendRequest(activity, sendMessageToWeiboRequest);
        }
    }

    private WeiboMessage adapterMultiMessage2SingleMessage(WeiboMultiMessage weiboMultiMessage) {
        if (weiboMultiMessage == null) {
            return new WeiboMessage();
        }
        Bundle bundle = new Bundle();
        weiboMultiMessage.toBundle(bundle);
        return new WeiboMessage(bundle);
    }

    private boolean startShareWeiboActivity(Activity activity, String str, BaseRequest baseRequest, c cVar) {
        try {
            e.a(this.mContext, this.mAppKey).a();
            new Bundle();
            String packageName = activity.getPackageName();
            ShareRequestParam shareRequestParam = new ShareRequestParam(activity);
            shareRequestParam.e(str);
            shareRequestParam.f(this.mAppKey);
            shareRequestParam.d(packageName);
            shareRequestParam.a(baseRequest);
            shareRequestParam.b("\u5fae\u535a\u5206\u4eab");
            shareRequestParam.a(cVar);
            Intent intent = new Intent(activity, WeiboSdkBrowser.class);
            intent.putExtras(shareRequestParam.d());
            activity.startActivity(intent);
            return true;
        } catch (ActivityNotFoundException unused) {
            return false;
        }
    }

    @Override // com.sina.weibo.sdk.api.share.IWeiboShareAPI
    public boolean sendResponse(BaseResponse baseResponse) {
        if (baseResponse == null) {
            d.c(TAG, "sendResponse failed response null");
            return false;
        } else if (!baseResponse.check(this.mContext, new VersionCheckHandler())) {
            d.c(TAG, "sendResponse check fail");
            return false;
        } else {
            Bundle bundle = new Bundle();
            baseResponse.toBundle(bundle);
            sendBroadcast(this.mContext, "com.sina.weibo.sdk.Intent.ACTION_WEIBO_RESPONSE", this.mAppKey, baseResponse.reqPackageName, bundle);
            return true;
        }
    }

    private void registerWeiboDownloadListener(IWeiboDownloadListener iWeiboDownloadListener) {
        this.mDownloadListener = iWeiboDownloadListener;
    }

    private boolean checkEnvironment(boolean z) throws WeiboShareException {
        if (!isWeiboAppInstalled()) {
            if (z) {
                Dialog dialog = this.mDownloadConfirmDialog;
                if (dialog == null) {
                    this.mDownloadConfirmDialog = WeiboDownloader.createDownloadConfirmDialog(this.mContext, this.mDownloadListener);
                    this.mDownloadConfirmDialog.show();
                    return false;
                } else if (dialog.isShowing()) {
                    return false;
                } else {
                    this.mDownloadConfirmDialog.show();
                    return false;
                }
            } else {
                throw new WeiboShareException("Weibo is not installed!");
            }
        } else if (!isWeiboAppSupportAPI()) {
            throw new WeiboShareException("Weibo do not support share api!");
        } else if (a.a(this.mContext, this.mWeiboInfo.a())) {
            return true;
        } else {
            throw new WeiboShareException("Weibo signature is incorrect!");
        }
    }

    @Override // com.sina.weibo.sdk.api.share.IWeiboShareAPI
    public boolean launchWeiboPay(Activity activity, String str) {
        Bundle bundle = new Bundle();
        bundle.putString("rawdata", str);
        bundle.putInt("_weibo_command_type", 4);
        bundle.putString("_weibo_transaction", String.valueOf(System.currentTimeMillis()));
        return launchWeiboActivity(activity, "com.sina.weibo.sdk.action.ACTION_WEIBO_PAY_ACTIVITY", this.mWeiboInfo.a(), this.mAppKey, bundle, "pay");
    }

    @Override // com.sina.weibo.sdk.api.share.IWeiboShareAPI
    public boolean launchWeiboPayLogin(Activity activity, String str) {
        if (!k.d(activity).booleanValue()) {
            return launchWeiboPay(activity, str);
        }
        if (activity == null) {
            d.c(TAG, "launchWeiboActivity fail, invalid arguments");
            return false;
        }
        Bundle bundle = new Bundle();
        bundle.putString("rawdata", str);
        bundle.putInt("_weibo_command_type", 4);
        String valueOf = String.valueOf(System.currentTimeMillis());
        bundle.putString("_weibo_transaction", valueOf);
        Intent intent = new Intent();
        intent.setPackage(this.mWeiboInfo.a());
        intent.setData(Uri.parse("sinaweibo://sdkdeliver"));
        String packageName = activity.getPackageName();
        intent.putExtra("_weibo_sdkVersion", "0031405000");
        intent.putExtra("_weibo_appPackage", packageName);
        intent.putExtra("_weibo_appKey", this.mAppKey);
        intent.putExtra("_weibo_flag", 538116905);
        intent.putExtra("_weibo_sign", com.sina.weibo.sdk.a.e.a(k.a(activity, packageName)));
        intent.putExtra("sdk_real_action", "com.sina.weibo.sdk.action.ACTION_WEIBO_PAY_ACTIVITY");
        intent.putExtra("sdk_is_scheme", false);
        intent.putExtra("sdk_requestcode", MetricsProto.MetricsEvent.APP_SPECIAL_PERMISSION_BATTERY_DENY);
        intent.putExtra("_weibo_transaction", valueOf);
        addEventLog(activity, valueOf, "pay");
        intent.putExtras(bundle);
        try {
            String str2 = TAG;
            d.a(str2, "launchWeiboActivity intent=" + intent + ", extra=" + intent.getExtras());
            activity.startActivityForResult(intent, MetricsProto.MetricsEvent.APP_SPECIAL_PERMISSION_BATTERY_DENY);
            return true;
        } catch (ActivityNotFoundException e) {
            d.c(TAG, e.getMessage());
            return false;
        }
    }

    private boolean launchWeiboActivity(Activity activity, String str, String str2, String str3, Bundle bundle, String str4) {
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
        intent.putExtra("_weibo_sign", com.sina.weibo.sdk.a.e.a(k.a(activity, packageName)));
        String valueOf = String.valueOf(System.currentTimeMillis());
        intent.putExtra("_weibo_transaction", valueOf);
        addEventLog(activity, valueOf, str4);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        try {
            String str5 = TAG;
            d.a(str5, "launchWeiboActivity intent=" + intent + ", extra=" + intent.getExtras());
            activity.startActivityForResult(intent, MetricsProto.MetricsEvent.APP_SPECIAL_PERMISSION_BATTERY_DENY);
            return true;
        } catch (ActivityNotFoundException e) {
            d.c(TAG, e.getMessage());
            return false;
        }
    }

    private void sendBroadcast(Context context, String str, String str2, String str3, Bundle bundle) {
        Intent intent = new Intent(str);
        String packageName = context.getPackageName();
        intent.putExtra("_weibo_sdkVersion", "0031405000");
        intent.putExtra("_weibo_appPackage", packageName);
        intent.putExtra("_weibo_appKey", str2);
        intent.putExtra("_weibo_flag", 538116905);
        intent.putExtra("_weibo_sign", com.sina.weibo.sdk.a.e.a(k.a(context, packageName)));
        if (!TextUtils.isEmpty(str3)) {
            intent.setPackage(str3);
        }
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        String str4 = TAG;
        d.a(str4, "intent=" + intent + ", extra=" + intent.getExtras());
        context.sendBroadcast(intent, "com.sina.weibo.permission.WEIBO_SDK_PERMISSION");
    }

    @Override // com.sina.weibo.sdk.api.share.IWeiboShareAPI
    public void shareMessageToWeiyou(Context context, Bundle bundle) {
        k.a(context, "sinaweibo://extendthirdshare", bundle);
    }

    public void addEventLog(Context context, String str, String str2) {
        HashMap hashMap = new HashMap();
        hashMap.put("other_app_action_start_time", str);
        try {
            WBAgent.onEvent(context, str2, hashMap);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
