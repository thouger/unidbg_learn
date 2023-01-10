package com.sina.weibo.sdk.api.share;

import android.content.Context;
import android.os.Bundle;

public abstract class BaseResponse extends Base {
    public int errCode;
    public String errMsg;
    public String reqPackageName;

    /* access modifiers changed from: package-private */
    public abstract boolean check(Context context, VersionCheckHandler versionCheckHandler);

    @Override // com.sina.weibo.sdk.api.share.Base
    public void toBundle(Bundle bundle) {
        bundle.putInt("_weibo_command_type", getType());
        bundle.putInt("_weibo_resp_errcode", this.errCode);
        bundle.putString("_weibo_resp_errstr", this.errMsg);
        bundle.putString("_weibo_transaction", this.transaction);
    }

    @Override // com.sina.weibo.sdk.api.share.Base
    public void fromBundle(Bundle bundle) {
        this.errCode = bundle.getInt("_weibo_resp_errcode");
        this.errMsg = bundle.getString("_weibo_resp_errstr");
        this.transaction = bundle.getString("_weibo_transaction");
        this.reqPackageName = bundle.getString("_weibo_appPackage");
    }
}
