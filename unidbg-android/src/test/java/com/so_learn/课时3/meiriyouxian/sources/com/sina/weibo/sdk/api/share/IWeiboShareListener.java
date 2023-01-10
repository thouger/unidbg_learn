package com.sina.weibo.sdk.api.share;

import com.sina.weibo.sdk.auth.b;
import com.sina.weibo.sdk.exception.WeiboException;

public interface IWeiboShareListener {
    void onAuthorizeCancel();

    void onAuthorizeComplete(b bVar);

    void onAuthorizeException(WeiboException weiboException);

    void onTokenError(String str);
}
