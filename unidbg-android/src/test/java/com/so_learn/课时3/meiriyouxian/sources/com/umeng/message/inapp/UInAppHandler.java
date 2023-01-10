package com.umeng.message.inapp;

import android.app.Activity;
import com.umeng.message.entity.UInAppMessage;

public interface UInAppHandler {
    void handleInAppMessage(Activity activity, UInAppMessage uInAppMessage, int i);
}
