package com.sina.weibo.sdk.api.share;

import android.content.Context;
import android.os.Bundle;
import com.sina.weibo.sdk.api.WeiboMultiMessage;

public class ProvideMultiMessageForWeiboResponse extends BaseResponse {
    public WeiboMultiMessage multiMessage;

    @Override // com.sina.weibo.sdk.api.share.Base
    public int getType() {
        return 2;
    }

    public ProvideMultiMessageForWeiboResponse() {
    }

    public ProvideMultiMessageForWeiboResponse(Bundle bundle) {
        fromBundle(bundle);
    }

    @Override // com.sina.weibo.sdk.api.share.BaseResponse, com.sina.weibo.sdk.api.share.Base
    public void fromBundle(Bundle bundle) {
        super.fromBundle(bundle);
        this.multiMessage = new WeiboMultiMessage(bundle);
    }

    @Override // com.sina.weibo.sdk.api.share.BaseResponse, com.sina.weibo.sdk.api.share.Base
    public void toBundle(Bundle bundle) {
        super.toBundle(bundle);
        bundle.putAll(this.multiMessage.toBundle(bundle));
    }

    /* access modifiers changed from: package-private */
    @Override // com.sina.weibo.sdk.api.share.BaseResponse
    public final boolean check(Context context, VersionCheckHandler versionCheckHandler) {
        if (this.multiMessage == null) {
            return false;
        }
        if (versionCheckHandler == null || versionCheckHandler.checkResponse(context, this.reqPackageName, this.multiMessage)) {
            return this.multiMessage.checkArgs();
        }
        return false;
    }
}
