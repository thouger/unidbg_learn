package com.sina.weibo.sdk.api.share;

import android.content.Context;
import android.os.Bundle;
import com.sina.weibo.sdk.api.WeiboMultiMessage;
import com.sina.weibo.sdk.b;

public class SendMultiMessageToWeiboRequest extends BaseRequest {
    public WeiboMultiMessage multiMessage;

    @Override // com.sina.weibo.sdk.api.share.Base
    public int getType() {
        return 1;
    }

    public SendMultiMessageToWeiboRequest() {
    }

    public SendMultiMessageToWeiboRequest(Bundle bundle) {
        fromBundle(bundle);
    }

    @Override // com.sina.weibo.sdk.api.share.BaseRequest, com.sina.weibo.sdk.api.share.Base
    public void fromBundle(Bundle bundle) {
        super.fromBundle(bundle);
        this.multiMessage = new WeiboMultiMessage(bundle);
    }

    @Override // com.sina.weibo.sdk.api.share.BaseRequest, com.sina.weibo.sdk.api.share.Base
    public void toBundle(Bundle bundle) {
        super.toBundle(bundle);
        bundle.putAll(this.multiMessage.toBundle(bundle));
    }

    /* access modifiers changed from: package-private */
    @Override // com.sina.weibo.sdk.api.share.BaseRequest
    public final boolean check(Context context, b.a aVar, VersionCheckHandler versionCheckHandler) {
        if (this.multiMessage == null || aVar == null || !aVar.c()) {
            return false;
        }
        if (versionCheckHandler == null || versionCheckHandler.checkRequest(context, aVar, this.multiMessage)) {
            return this.multiMessage.checkArgs();
        }
        return false;
    }
}
