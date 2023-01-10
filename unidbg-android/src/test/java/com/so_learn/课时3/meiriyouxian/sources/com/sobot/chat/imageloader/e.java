package com.sobot.chat.imageloader;

import android.content.Context;
import android.graphics.Bitmap;
import android.widget.ImageView;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageSize;
import com.nostra13.universalimageloader.core.imageaware.ImageViewAware;
import com.nostra13.universalimageloader.core.listener.ImageLoadingProgressListener;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;
import com.sobot.chat.imageloader.c;

/* compiled from: SobotUILImageLoader */
public class e extends c {
    private void a(Context context) {
        if (!ImageLoader.getInstance().isInited()) {
            ImageLoader.getInstance().init(new ImageLoaderConfiguration.Builder(context.getApplicationContext()).threadPoolSize(3).defaultDisplayImageOptions(new DisplayImageOptions.Builder().cacheInMemory(true).cacheOnDisk(true).build()).build());
        }
    }

    @Override // com.sobot.chat.imageloader.c
    public void a(Context context, ImageView imageView, String str, int i, int i2, int i3, int i4, c.a aVar) {
        ImageSize imageSize;
        a(context);
        DisplayImageOptions build = new DisplayImageOptions.Builder().showImageOnLoading(i).showImageOnFail(i2).showImageForEmptyUri(i2).cacheInMemory(true).cacheOnDisk(true).bitmapConfig(Bitmap.Config.RGB_565).build();
        if (i3 == 0 && i4 == 0) {
            imageSize = null;
        } else {
            imageSize = new ImageSize(i3, i4);
        }
        ImageLoader.getInstance().displayImage(str, new ImageViewAware(imageView), build, imageSize, new AnonymousClass1(aVar), (ImageLoadingProgressListener) null);
    }

    /* compiled from: SobotUILImageLoader */
    /* renamed from: com.sobot.chat.imageloader.e$1  reason: invalid class name */
    class AnonymousClass1 extends SimpleImageLoadingListener {
        final /* synthetic */ c.a a;

        AnonymousClass1(c.a aVar) {
            this.a = aVar;
        }
    }

    @Override // com.sobot.chat.imageloader.c
    public void a(Context context, ImageView imageView, int i, int i2, int i3, int i4, int i5, c.a aVar) {
        ImageSize imageSize;
        a(context);
        DisplayImageOptions build = new DisplayImageOptions.Builder().showImageOnLoading(i2).showImageOnFail(i3).showImageForEmptyUri(i3).cacheInMemory(true).bitmapConfig(Bitmap.Config.RGB_565).build();
        if (i4 == 0 && i5 == 0) {
            imageSize = null;
        } else {
            imageSize = new ImageSize(i4, i5);
        }
        ImageLoader instance = ImageLoader.getInstance();
        instance.displayImage("drawable://" + i, new ImageViewAware(imageView), build, imageSize, new AnonymousClass2(aVar), (ImageLoadingProgressListener) null);
    }

    /* compiled from: SobotUILImageLoader */
    /* renamed from: com.sobot.chat.imageloader.e$2  reason: invalid class name */
    class AnonymousClass2 extends SimpleImageLoadingListener {
        final /* synthetic */ c.a a;

        AnonymousClass2(c.a aVar) {
            this.a = aVar;
        }
    }
}
