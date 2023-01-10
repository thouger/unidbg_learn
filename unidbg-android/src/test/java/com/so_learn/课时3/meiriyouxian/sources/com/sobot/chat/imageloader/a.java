package com.sobot.chat.imageloader;

import android.content.Context;
import android.graphics.Bitmap;
import android.widget.ImageView;
import com.bumptech.glide.BitmapRequestBuilder;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestListener;
import com.sobot.chat.imageloader.c;

/* compiled from: SobotGlideImageLoader */
public class a extends c {
    @Override // com.sobot.chat.imageloader.c
    public void a(Context context, ImageView imageView, String str, int i, int i2, int i3, int i4, c.a aVar) {
        BitmapRequestBuilder centerCrop = Glide.with(context).load(str).asBitmap().placeholder(i).error(i2).centerCrop();
        if (!(i3 == 0 && i4 == 0)) {
            centerCrop.override(i3, i4);
        }
        centerCrop.listener(new AnonymousClass1(aVar, imageView, str)).into(imageView);
    }

    /* compiled from: SobotGlideImageLoader */
    /* renamed from: com.sobot.chat.imageloader.a$1  reason: invalid class name */
    class AnonymousClass1 implements RequestListener<String, Bitmap> {
        final /* synthetic */ c.a a;
        final /* synthetic */ ImageView b;
        final /* synthetic */ String c;

        AnonymousClass1(c.a aVar, ImageView imageView, String str) {
            this.a = aVar;
            this.b = imageView;
            this.c = str;
        }
    }

    @Override // com.sobot.chat.imageloader.c
    public void a(Context context, ImageView imageView, int i, int i2, int i3, int i4, int i5, c.a aVar) {
        BitmapRequestBuilder centerCrop = Glide.with(context).load(Integer.valueOf(i)).asBitmap().placeholder(i2).error(i3).centerCrop();
        if (!(i4 == 0 && i5 == 0)) {
            centerCrop.override(i4, i5);
        }
        centerCrop.listener(new AnonymousClass2(aVar, imageView)).into(imageView);
    }

    /* compiled from: SobotGlideImageLoader */
    /* renamed from: com.sobot.chat.imageloader.a$2  reason: invalid class name */
    class AnonymousClass2 implements RequestListener<Integer, Bitmap> {
        final /* synthetic */ c.a a;
        final /* synthetic */ ImageView b;

        AnonymousClass2(c.a aVar, ImageView imageView) {
            this.a = aVar;
            this.b = imageView;
        }
    }
}
