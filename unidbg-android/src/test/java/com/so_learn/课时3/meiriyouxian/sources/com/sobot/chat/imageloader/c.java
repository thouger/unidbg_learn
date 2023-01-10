package com.sobot.chat.imageloader;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

/* compiled from: SobotImageLoader */
public abstract class c {

    /* compiled from: SobotImageLoader */
    public interface a {
        void a(View view, String str);
    }

    public abstract void a(Context context, ImageView imageView, int i, int i2, int i3, int i4, int i5, a aVar);

    public abstract void a(Context context, ImageView imageView, String str, int i, int i2, int i3, int i4, a aVar);
}
