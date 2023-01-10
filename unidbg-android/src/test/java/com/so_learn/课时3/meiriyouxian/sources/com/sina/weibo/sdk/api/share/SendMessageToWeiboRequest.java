package com.sina.weibo.sdk.api.share;

import android.content.Context;
import android.os.Bundle;
import com.sina.weibo.sdk.api.WeiboMessage;
import com.sina.weibo.sdk.b;

public class SendMessageToWeiboRequest extends BaseRequest {
    public WeiboMessage message;

    @Override // com.sina.weibo.sdk.api.share.Base
    public int getType() {
        return 1;
    }

    public SendMessageToWeiboRequest() {
    }

    public SendMessageToWeiboRequest(Bundle bundle) {
        fromBundle(bundle);
    }

    @Override // com.sina.weibo.sdk.api.share.BaseRequest, com.sina.weibo.sdk.api.share.Base
    public void fromBundle(Bundle bundle) {
        super.fromBundle(bundle);
        this.message = new WeiboMessage(bundle);
    }

    @Override // com.sina.weibo.sdk.api.share.BaseRequest, com.sina.weibo.sdk.api.share.Base
    public void toBundle(Bundle bundle) {
        super.toBundle(bundle);
        bundle.putAll(this.message.toBundle(bundle));
    }

    /* access modifiers changed from: package-private */
    @Override // com.sina.weibo.sdk.api.share.BaseRequest
    public final boolean check(Context context, b.a aVar, VersionCheckHandler versionCheckHandler) {
        if (this.message == null || aVar == null || !aVar.c()) {
            return false;
        }
        if (versionCheckHandler == null || versionCheckHandler.checkRequest(context, aVar, this.message)) {
            return this.message.checkArgs();
        }
        return false;
    }
}
