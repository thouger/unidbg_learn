package com.sina.weibo.sdk.api;

import android.os.Bundle;
import com.sina.weibo.sdk.a.d;

public final class WeiboMessage {
    public BaseMediaObject mediaObject;

    public WeiboMessage() {
    }

    public WeiboMessage(Bundle bundle) {
        toBundle(bundle);
    }

    public Bundle toBundle(Bundle bundle) {
        BaseMediaObject baseMediaObject = this.mediaObject;
        if (baseMediaObject != null) {
            bundle.putParcelable("_weibo_message_media", baseMediaObject);
            bundle.putString("_weibo_message_media_extra", this.mediaObject.toExtraMediaString());
        }
        return bundle;
    }

    public WeiboMessage toObject(Bundle bundle) {
        this.mediaObject = (BaseMediaObject) bundle.getParcelable("_weibo_message_media");
        BaseMediaObject baseMediaObject = this.mediaObject;
        if (baseMediaObject != null) {
            baseMediaObject.toExtraMediaObject(bundle.getString("_weibo_message_media_extra"));
        }
        return this;
    }

    public boolean checkArgs() {
        BaseMediaObject baseMediaObject = this.mediaObject;
        if (baseMediaObject == null) {
            d.c("Weibo.WeiboMessage", "checkArgs fail, mediaObject is null");
            return false;
        } else if (baseMediaObject == null || baseMediaObject.checkArgs()) {
            return true;
        } else {
            d.c("Weibo.WeiboMessage", "checkArgs fail, mediaObject is invalid");
            return false;
        }
    }
}
