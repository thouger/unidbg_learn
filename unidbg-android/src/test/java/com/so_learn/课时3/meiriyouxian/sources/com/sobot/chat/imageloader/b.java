package com.sobot.chat.imageloader;

import android.content.Context;
import android.graphics.Bitmap;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.sobot.chat.imageloader.c;

/* compiled from: SobotGlideV4ImageLoader */
public class b extends c {
    @Override // com.sobot.chat.imageloader.c
    public void a(Context context, ImageView imageView, String str, int i, int i2, int i3, int i4, c.a aVar) {
        RequestBuilder centerCrop = Glide.with(context).asBitmap().load(str).placeholder(i).error(i2).centerCrop();
        if (!(i3 == 0 && i4 == 0)) {
            centerCrop.override(i3, i4);
        }
        centerCrop.listener(new AnonymousClass1(aVar, imageView, str)).into(imageView);
    }

    /* compiled from: SobotGlideV4ImageLoader */
    /* renamed from: com.sobot.chat.imageloader.b$1  reason: invalid class name */
    class AnonymousClass1 implements RequestListener<Bitmap> {
        final /* synthetic */ c.a a;
        final /* synthetic */ ImageView b;
        final /* synthetic */ String c;

        @Override // com.bumptech.glide.request.RequestListener
        public boolean onLoadFailed(GlideException glideException, Object obj, Target<Bitmap> target, boolean z) {
            return false;
        }

        AnonymousClass1(c.a aVar, ImageView imageView, String str) {
            this.a = aVar;
            this.b = imageView;
            this.c = str;
        }

        /* renamed from: a */
        public boolean onResourceReady(Bitmap bitmap, Object obj, Target<Bitmap> target, DataSource dataSource, boolean z) {
            c.a aVar = this.a;
            if (aVar == null) {
                return false;
            }
            aVar.a(this.b, this.c);
            return false;
        }
    }

    @Override // com.sobot.chat.imageloader.c
    public void a(Context context, ImageView imageView, int i, int i2, int i3, int i4, int i5, c.a aVar) {
        RequestBuilder centerCrop = Glide.with(context).asBitmap().load(Integer.valueOf(i)).placeholder(i2).error(i3).centerCrop();
        if (!(i4 == 0 && i5 == 0)) {
            centerCrop.override(i4, i5);
        }
        centerCrop.listener(new AnonymousClass2(aVar, imageView)).into(imageView);
    }

    /* compiled from: SobotGlideV4ImageLoader */
    /* renamed from: com.sobot.chat.imageloader.b$2  reason: invalid class name */
    class AnonymousClass2 implements RequestListener<Bitmap> {
        final /* synthetic */ c.a a;
        final /* synthetic */ ImageView b;

        @Override // com.bumptech.glide.request.RequestListener
        public boolean onLoadFailed(GlideException glideException, Object obj, Target<Bitmap> target, boolean z) {
            return false;
        }

        AnonymousClass2(c.a aVar, ImageView imageView) {
            this.a = aVar;
            this.b = imageView;
        }

        /* renamed from: a */
        public boolean onResourceReady(Bitmap bitmap, Object obj, Target<Bitmap> target, DataSource dataSource, boolean z) {
            c.a aVar = this.a;
            if (aVar == null) {
                return false;
            }
            aVar.a(this.b, "");
            return false;
        }
    }
}
