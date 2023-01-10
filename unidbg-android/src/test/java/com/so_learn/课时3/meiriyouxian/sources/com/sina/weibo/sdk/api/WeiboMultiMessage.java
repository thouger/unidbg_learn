package com.sina.weibo.sdk.api;

import android.os.Bundle;
import com.sina.weibo.sdk.a.d;

public final class WeiboMultiMessage {
    private static final String TAG = "WeiboMultiMessage";
    public ImageObject imageObject;
    public BaseMediaObject mediaObject;
    public TextObject textObject;

    public WeiboMultiMessage() {
    }

    public WeiboMultiMessage(Bundle bundle) {
        toBundle(bundle);
    }

    public Bundle toBundle(Bundle bundle) {
        TextObject textObject = this.textObject;
        if (textObject != null) {
            bundle.putParcelable("_weibo_message_text", textObject);
            bundle.putString("_weibo_message_text_extra", this.textObject.toExtraMediaString());
        }
        ImageObject imageObject = this.imageObject;
        if (imageObject != null) {
            bundle.putParcelable("_weibo_message_image", imageObject);
            bundle.putString("_weibo_message_image_extra", this.imageObject.toExtraMediaString());
        }
        BaseMediaObject baseMediaObject = this.mediaObject;
        if (baseMediaObject != null) {
            bundle.putParcelable("_weibo_message_media", baseMediaObject);
            bundle.putString("_weibo_message_media_extra", this.mediaObject.toExtraMediaString());
        }
        return bundle;
    }

    public WeiboMultiMessage toObject(Bundle bundle) {
        this.textObject = (TextObject) bundle.getParcelable("_weibo_message_text");
        TextObject textObject = this.textObject;
        if (textObject != null) {
            textObject.toExtraMediaObject(bundle.getString("_weibo_message_text_extra"));
        }
        this.imageObject = (ImageObject) bundle.getParcelable("_weibo_message_image");
        ImageObject imageObject = this.imageObject;
        if (imageObject != null) {
            imageObject.toExtraMediaObject(bundle.getString("_weibo_message_image_extra"));
        }
        this.mediaObject = (BaseMediaObject) bundle.getParcelable("_weibo_message_media");
        BaseMediaObject baseMediaObject = this.mediaObject;
        if (baseMediaObject != null) {
            baseMediaObject.toExtraMediaObject(bundle.getString("_weibo_message_media_extra"));
        }
        return this;
    }

    public boolean checkArgs() {
        TextObject textObject = this.textObject;
        if (textObject == null || textObject.checkArgs()) {
            ImageObject imageObject = this.imageObject;
            if (imageObject == null || imageObject.checkArgs()) {
                BaseMediaObject baseMediaObject = this.mediaObject;
                if (baseMediaObject != null && !baseMediaObject.checkArgs()) {
                    d.c(TAG, "checkArgs fail, mediaObject is invalid");
                    return false;
                } else if (this.textObject != null || this.imageObject != null || this.mediaObject != null) {
                    return true;
                } else {
                    d.c(TAG, "checkArgs fail, textObject and imageObject and mediaObject is null");
                    return false;
                }
            } else {
                d.c(TAG, "checkArgs fail, imageObject is invalid");
                return false;
            }
        } else {
            d.c(TAG, "checkArgs fail, textObject is invalid");
            return false;
        }
    }
}
