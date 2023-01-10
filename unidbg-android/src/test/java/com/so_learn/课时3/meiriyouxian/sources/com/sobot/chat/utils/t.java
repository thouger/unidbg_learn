package com.sobot.chat.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Environment;
import android.text.TextUtils;
import android.view.WindowManager;
import android.widget.ImageView;
import com.sobot.chat.imageloader.a;
import com.sobot.chat.imageloader.b;
import com.sobot.chat.imageloader.c;
import com.sobot.chat.imageloader.d;
import com.sobot.chat.imageloader.e;

/* compiled from: SobotBitmapUtil */
public class t {
    private static c a;

    private static final c a() {
        if (a == null) {
            synchronized (t.class) {
                if (a == null) {
                    if (a("com.bumptech.glide.request.RequestOptions")) {
                        a = new b();
                    } else if (a("com.bumptech.glide.Glide")) {
                        a = new a();
                    } else if (a("com.squareup.picasso.Picasso")) {
                        a = new d();
                    } else if (a("com.nostra13.universalimageloader.core.ImageLoader")) {
                        a = new e();
                    } else {
                        throw new RuntimeException("\u5fc5\u987b\u5728(Glide\u3001Picasso\u3001universal-image-loader)\u4e2d\u9009\u62e9\u4e00\u4e2a\u56fe\u7247\u52a0\u8f7d\u5e93\u6dfb\u52a0\u4f9d\u8d56,\u6216\u8005\u68c0\u67e5\u662f\u5426\u6dfb\u52a0\u4e86\u76f8\u5e94\u7684\u6df7\u6dc6\u914d\u7f6e");
                    }
                }
            }
        }
        return a;
    }

    private static final boolean a(String str) {
        try {
            Class.forName(str);
            return true;
        } catch (ClassNotFoundException unused) {
            return false;
        }
    }

    public static void a(Context context, String str, ImageView imageView, int i, int i2) {
        if (!TextUtils.isEmpty(str) && !str.startsWith("http")) {
            str = "file://" + str;
        }
        a().a(context, imageView, str, i, i2, imageView.getWidth(), imageView.getHeight(), (c.a) null);
    }

    public static void a(Context context, String str, ImageView imageView) {
        if (!TextUtils.isEmpty(str) && !str.startsWith("http")) {
            str = "file://" + str;
        }
        a().a(context, imageView, str, q.a(context, "drawable", "sobot_default_pic"), q.a(context, "drawable", "sobot_default_pic_err"), imageView.getWidth(), imageView.getHeight(), (c.a) null);
    }

    public static void a(Context context, int i, ImageView imageView) {
        a().a(context, imageView, i, 0, 0, imageView.getWidth(), imageView.getHeight(), (c.a) null);
    }

    public static void a(Context context, String str, ImageView imageView, int i) {
        a().a(context, imageView, str, i, i, imageView.getWidth(), imageView.getHeight(), (c.a) null);
    }

    public static Bitmap a(String str, Context context, boolean z) {
        if (Build.VERSION.SDK_INT >= 29 && !Environment.isExternalStorageLegacy() && !z) {
            return l.c(context, l.a(context, str));
        }
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(str, options);
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        options.inSampleSize = a(options, windowManager.getDefaultDisplay().getWidth(), windowManager.getDefaultDisplay().getHeight());
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeFile(str, options);
    }

    private static int a(BitmapFactory.Options options, int i, int i2) {
        int i3 = options.outHeight;
        int i4 = options.outWidth;
        if (i3 <= i2 && i4 <= i) {
            return 1;
        }
        int round = Math.round(((float) i3) / ((float) i2));
        int round2 = Math.round(((float) i4) / ((float) i));
        return round < round2 ? round : round2;
    }
}
