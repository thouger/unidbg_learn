package com.sina.weibo.sdk.api.share;

import android.content.Context;
import android.os.Bundle;
import com.sina.weibo.sdk.b;

public class ProvideMessageForWeiboRequest extends BaseRequest {
    /* access modifiers changed from: package-private */
    @Override // com.sina.weibo.sdk.api.share.BaseRequest
    public final boolean check(Context context, b.a aVar, VersionCheckHandler versionCheckHandler) {
        return true;
    }

    @Override // com.sina.weibo.sdk.api.share.Base
    public int getType() {
        return 2;
    }

    public ProvideMessageForWeiboRequest() {
    }

    public ProvideMessageForWeiboRequest(Bundle bundle) {
        fromBundle(bundle);
    }
}
