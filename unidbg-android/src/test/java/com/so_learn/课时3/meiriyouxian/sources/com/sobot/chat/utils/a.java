package com.sobot.chat.utils;

import android.view.View;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;

/* compiled from: AnimationUtil */
public class a {
    public static void a(View view) {
        RotateAnimation rotateAnimation = new RotateAnimation(0.0f, 360.0f, 1, 0.5f, 1, 0.5f);
        rotateAnimation.setDuration(1000);
        rotateAnimation.setRepeatCount(-1);
        rotateAnimation.setInterpolator(new LinearInterpolator());
        rotateAnimation.setRepeatMode(1);
        view.setAnimation(rotateAnimation);
    }
}
