package com.vivo.push.util;

import android.content.Context;

public interface BaseNotifyLayoutAdapter {
    int getNotificationLayout();

    int getSuitIconId();

    int getTitleColor();

    void init(Context context);
}
