package com.umeng.message.inapp;

import com.umeng.message.entity.UInAppMessage;

interface IUmengInAppMessageCallback {
    void onCardMessage(UInAppMessage uInAppMessage);

    void onSplashMessage(UInAppMessage uInAppMessage);
}
