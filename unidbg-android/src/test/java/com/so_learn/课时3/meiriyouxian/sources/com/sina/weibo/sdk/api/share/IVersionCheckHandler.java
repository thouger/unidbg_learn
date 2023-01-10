package com.sina.weibo.sdk.api.share;

import android.content.Context;
import com.sina.weibo.sdk.api.WeiboMessage;
import com.sina.weibo.sdk.api.WeiboMultiMessage;
import com.sina.weibo.sdk.b;

interface IVersionCheckHandler {
    boolean checkRequest(Context context, b.a aVar, WeiboMessage weiboMessage);

    boolean checkRequest(Context context, b.a aVar, WeiboMultiMessage weiboMultiMessage);

    boolean checkResponse(Context context, String str, WeiboMessage weiboMessage);

    boolean checkResponse(Context context, String str, WeiboMultiMessage weiboMultiMessage);
}
