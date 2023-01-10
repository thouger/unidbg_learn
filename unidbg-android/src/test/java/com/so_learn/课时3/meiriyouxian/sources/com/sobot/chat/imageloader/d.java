package com.sobot.chat.imageloader;

import android.content.Context;
import android.graphics.Bitmap;
import android.text.TextUtils;
import android.widget.ImageView;
import com.sobot.chat.imageloader.c;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.d;
import com.squareup.picasso.o;

/* compiled from: SobotPicassoImageLoader */
public class d extends c {
    @Override // com.sobot.chat.imageloader.c
    public void a(Context context, ImageView imageView, String str, int i, int i2, int i3, int i4, c.a aVar) {
        o a = Picasso.a(context).a(TextUtils.isEmpty(str) ? "error" : str);
        if (i != 0) {
            a.a(i);
        }
        if (i2 != 0) {
            a.b(i2);
        }
        a.a(Bitmap.Config.RGB_565);
        if (i3 == 0 && i4 == 0) {
            a.a().c();
        } else {
            a.a(i3, i4).c();
        }
        a.a(imageView, new AnonymousClass1(aVar, imageView, str));
    }

    /* compiled from: SobotPicassoImageLoader */
    /* renamed from: com.sobot.chat.imageloader.d$1  reason: invalid class name */
    class AnonymousClass1 extends d.a {
        final /* synthetic */ c.a a;
        final /* synthetic */ ImageView b;
        final /* synthetic */ String c;

        AnonymousClass1(c.a aVar, ImageView imageView, String str) {
            this.a = aVar;
            this.b = imageView;
            this.c = str;
        }

        @Override // com.squareup.picasso.d.a, com.squareup.picasso.d
        public void a() {
            c.a aVar = this.a;
            if (aVar != null) {
                aVar.a(this.b, this.c);
            }
        }
    }

    @Override // com.sobot.chat.imageloader.c
    public void a(Context context, ImageView imageView, int i, int i2, int i3, int i4, int i5, c.a aVar) {
        o a = Picasso.a(context).a(i).a(Bitmap.Config.RGB_565);
        if (i2 != 0) {
            a.a(i2);
        }
        if (i3 != 0) {
            a.b(i3);
        }
        if (i4 == 0 && i5 == 0) {
            a.a().c();
        } else {
            a.a(i4, i5).c();
        }
        a.a(imageView, new AnonymousClass2(aVar, imageView));
    }

    /* compiled from: SobotPicassoImageLoader */
    /* renamed from: com.sobot.chat.imageloader.d$2  reason: invalid class name */
    class AnonymousClass2 extends d.a {
        final /* synthetic */ c.a a;
        final /* synthetic */ ImageView b;

        AnonymousClass2(c.a aVar, ImageView imageView) {
            this.a = aVar;
            this.b = imageView;
        }

        @Override // com.squareup.picasso.d.a, com.squareup.picasso.d
        public void a() {
            c.a aVar = this.a;
            if (aVar != null) {
                aVar.a(this.b, "");
            }
        }
    }
}
