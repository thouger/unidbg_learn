package com.sina.weibo.sdk.api.share;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.sina.weibo.sdk.api.share.IWeiboHandler;
import com.sina.weibo.sdk.auth.a;
import com.sina.weibo.sdk.auth.c;

public interface IWeiboShareAPI {
    int getWeiboAppSupportAPI();

    boolean handleWeiboRequest(Intent intent, IWeiboHandler.Request request);

    boolean handleWeiboResponse(Intent intent, IWeiboHandler.Response response);

    boolean isSupportWeiboPay();

    boolean isWeiboAppInstalled();

    boolean isWeiboAppSupportAPI();

    boolean launchWeibo(Activity activity);

    boolean launchWeiboPay(Activity activity, String str);

    boolean launchWeiboPayLogin(Activity activity, String str);

    boolean registerApp();

    boolean sendRequest(Activity activity, BaseRequest baseRequest);

    boolean sendRequest(Activity activity, BaseRequest baseRequest, a aVar, String str, c cVar);

    boolean sendResponse(BaseResponse baseResponse);

    void shareMessageToWeiyou(Context context, Bundle bundle);
}
