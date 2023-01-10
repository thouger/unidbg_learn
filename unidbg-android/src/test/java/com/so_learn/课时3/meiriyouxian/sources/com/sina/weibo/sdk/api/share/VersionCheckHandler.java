package com.sina.weibo.sdk.api.share;

import android.content.Context;
import android.text.TextUtils;
import com.sina.weibo.sdk.a.d;
import com.sina.weibo.sdk.api.CmdObject;
import com.sina.weibo.sdk.api.VoiceObject;
import com.sina.weibo.sdk.api.WeiboMessage;
import com.sina.weibo.sdk.api.WeiboMultiMessage;
import com.sina.weibo.sdk.b;

public class VersionCheckHandler implements IVersionCheckHandler {
    private static final String TAG = VersionCheckHandler.class.getName();

    @Override // com.sina.weibo.sdk.api.share.IVersionCheckHandler
    public boolean checkRequest(Context context, b.a aVar, WeiboMessage weiboMessage) {
        if (aVar == null || !aVar.c()) {
            return false;
        }
        String str = TAG;
        d.a(str, "WeiboMessage WeiboInfo package : " + aVar.a());
        String str2 = TAG;
        d.a(str2, "WeiboMessage WeiboInfo supportApi : " + aVar.b());
        if (aVar.b() < 10351 && weiboMessage.mediaObject != null && (weiboMessage.mediaObject instanceof VoiceObject)) {
            weiboMessage.mediaObject = null;
        }
        if (aVar.b() >= 10352 || weiboMessage.mediaObject == null || !(weiboMessage.mediaObject instanceof CmdObject)) {
            return true;
        }
        weiboMessage.mediaObject = null;
        return true;
    }

    @Override // com.sina.weibo.sdk.api.share.IVersionCheckHandler
    public boolean checkRequest(Context context, b.a aVar, WeiboMultiMessage weiboMultiMessage) {
        if (aVar == null || !aVar.c()) {
            return false;
        }
        String str = TAG;
        d.a(str, "WeiboMultiMessage WeiboInfo package : " + aVar.a());
        String str2 = TAG;
        d.a(str2, "WeiboMultiMessage WeiboInfo supportApi : " + aVar.b());
        if (aVar.b() < 10351) {
            return false;
        }
        if (aVar.b() < 10352 && weiboMultiMessage.mediaObject != null && (weiboMultiMessage.mediaObject instanceof CmdObject)) {
            weiboMultiMessage.mediaObject = null;
        }
        return true;
    }

    @Override // com.sina.weibo.sdk.api.share.IVersionCheckHandler
    public boolean checkResponse(Context context, String str, WeiboMessage weiboMessage) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        return checkRequest(context, b.a(context).a(str), weiboMessage);
    }

    @Override // com.sina.weibo.sdk.api.share.IVersionCheckHandler
    public boolean checkResponse(Context context, String str, WeiboMultiMessage weiboMultiMessage) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        return checkRequest(context, b.a(context).a(str), weiboMultiMessage);
    }
}
