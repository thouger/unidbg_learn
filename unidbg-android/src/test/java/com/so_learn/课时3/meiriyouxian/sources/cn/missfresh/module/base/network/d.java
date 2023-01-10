package cn.missfresh.module.base.network;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.fragment.app.Fragment;
import cn.missfresh.lib.image.config.ImageConfig;
import cn.missfresh.module.base.R;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import com.bangcle.andjni.JniLib;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.target.DrawableImageViewTarget;
import com.bumptech.glide.request.target.ImageViewTarget;
import java.io.File;

/* compiled from: ImageLoader */
@Deprecated
public class d {

    /* compiled from: ImageLoader */
    /* renamed from: cn.missfresh.module.base.network.d$4  reason: invalid class name */
    static class AnonymousClass4 implements View.OnAttachStateChangeListener {
        final /* synthetic */ ImageViewTarget a;

        AnonymousClass4(ImageViewTarget imageViewTarget) {
            JniLib.cV(this, imageViewTarget, 142);
        }

        @Override // android.view.View.OnAttachStateChangeListener
        public void onViewAttachedToWindow(View view) {
            JniLib.cV(this, view, 140);
        }

        @Override // android.view.View.OnAttachStateChangeListener
        public void onViewDetachedFromWindow(View view) {
            JniLib.cV(this, view, 141);
        }
    }

    /* compiled from: ImageLoader */
    public interface a<T extends View> {
        void a();

        void a(T t, Drawable drawable);
    }

    /* compiled from: ImageLoader */
    public interface b<T> {
        void a(Exception exc, Drawable drawable);

        void a(T t);
    }

    private static cn.missfresh.lib.image.b.a a(Activity activity, ImageView imageView, boolean z) {
        return (cn.missfresh.lib.image.b.a) JniLib.cL(activity, imageView, Boolean.valueOf(z), 144);
    }

    public static ImageConfig a(Activity activity) {
        return (ImageConfig) JniLib.cL(activity, 145);
    }

    public static ImageConfig a(Context context) {
        return (ImageConfig) JniLib.cL(context, 146);
    }

    public static ImageConfig a(View view) {
        return (ImageConfig) JniLib.cL(view, 147);
    }

    public static ImageConfig a(Fragment fragment) {
        return (ImageConfig) JniLib.cL(fragment, 148);
    }

    private static ImageConfig a(ImageConfig imageConfig, boolean z, boolean z2, DiskCacheStrategy diskCacheStrategy, int i, int i2, int i3, int i4, cn.missfresh.lib.image.b.a aVar, int i5) {
        return (ImageConfig) JniLib.cL(imageConfig, Boolean.valueOf(z), Boolean.valueOf(z2), diskCacheStrategy, Integer.valueOf(i), Integer.valueOf(i2), Integer.valueOf(i3), Integer.valueOf(i4), aVar, Integer.valueOf(i5), 149);
    }

    private static void a(Activity activity, int i, boolean z, DiskCacheStrategy diskCacheStrategy, int i2, int i3, int i4, int i5, ImageView imageView, cn.missfresh.lib.image.b.a aVar) {
        JniLib.cV(activity, Integer.valueOf(i), Boolean.valueOf(z), diskCacheStrategy, Integer.valueOf(i2), Integer.valueOf(i3), Integer.valueOf(i4), Integer.valueOf(i5), imageView, aVar, 150);
    }

    public static void a(Context context, int i, ImageView imageView) {
        JniLib.cV(context, Integer.valueOf(i), imageView, 151);
    }

    private static void a(Context context, String str, DiskCacheStrategy diskCacheStrategy, ImageView imageView) {
        JniLib.cV(context, str, diskCacheStrategy, imageView, 152);
    }

    private static void a(Context context, String str, boolean z, DiskCacheStrategy diskCacheStrategy, int i, int i2, int i3, int i4, ImageView imageView, cn.missfresh.lib.image.b.a aVar) {
        JniLib.cV(context, str, Boolean.valueOf(z), diskCacheStrategy, Integer.valueOf(i), Integer.valueOf(i2), Integer.valueOf(i3), Integer.valueOf(i4), imageView, aVar, 153);
    }

    private static ImageConfig b(Context context, String str, int i, boolean z, boolean z2, DiskCacheStrategy diskCacheStrategy, int i2, int i3, int i4, int i5, cn.missfresh.lib.image.b.a aVar) {
        return (ImageConfig) JniLib.cL(context, str, Integer.valueOf(i), Boolean.valueOf(z), Boolean.valueOf(z2), diskCacheStrategy, Integer.valueOf(i2), Integer.valueOf(i3), Integer.valueOf(i4), Integer.valueOf(i5), aVar, 154);
    }

    public static void e(Context context, String str, ImageView imageView) {
        JniLib.cV(context, str, imageView, 155);
    }

    public static void a(Activity activity, String str, ImageView imageView) {
        AppMethodBeat.i(15402, false);
        imageView.setScaleType(ImageView.ScaleType.CENTER);
        a(activity, str, 0, false, false, DiskCacheStrategy.RESOURCE, R.drawable.ic_default_100, R.drawable.ic_default_100, -1, -1, null).a(imageView);
        AppMethodBeat.o(15402);
    }

    public static void a(Context context, String str, int i, int i2, ImageView imageView) {
        AppMethodBeat.i(15403, false);
        a(context, str, 0, true, false, DiskCacheStrategy.DATA, i, i2, -1, -1, null).a(imageView);
        AppMethodBeat.o(15403);
    }

    public static void a(Context context, String str, ImageView imageView) {
        AppMethodBeat.i(15404, false);
        try {
            imageView.setScaleType(ImageView.ScaleType.CENTER);
            a(context, str, 0, false, false, DiskCacheStrategy.DATA, R.drawable.ic_default_100, R.drawable.ic_default_100, -1, -1, null).a(imageView);
        } catch (NullPointerException unused) {
            imageView.setImageResource(R.drawable.ic_default_100);
        }
        AppMethodBeat.o(15404);
    }

    public static void b(Context context, String str, ImageView imageView) {
        AppMethodBeat.i(15407, false);
        imageView.setScaleType(ImageView.ScaleType.CENTER);
        a(context, str, 0, false, false, DiskCacheStrategy.DATA, -1, -1, -1, -1, null).a(imageView);
        AppMethodBeat.o(15407);
    }

    public static void c(Context context, String str, ImageView imageView) {
        AppMethodBeat.i(15409, false);
        imageView.setScaleType(ImageView.ScaleType.CENTER);
        a(context, str, 0, false, true, DiskCacheStrategy.NONE, -1, -1, -1, Integer.MIN_VALUE, null).a(imageView);
        AppMethodBeat.o(15409);
    }

    public static void a(Context context, String str, int i, int i2, b bVar) {
        AppMethodBeat.i(15410, false);
        a(context, str, 0, true, false, DiskCacheStrategy.RESOURCE, -1, -1, i, i2, null).a(new AnonymousClass1(bVar));
        AppMethodBeat.o(15410);
    }

    /* compiled from: ImageLoader */
    /* access modifiers changed from: package-private */
    /* renamed from: cn.missfresh.module.base.network.d$1  reason: invalid class name */
    public static class AnonymousClass1 extends cn.missfresh.lib.image.c.b<Bitmap> {
        final /* synthetic */ b a;

        public void a(Bitmap bitmap) {
            JniLib.cV(this, bitmap, 134);
        }

        @Override // cn.missfresh.lib.image.c.b
        public void b(Drawable drawable) {
            JniLib.cV(this, drawable, 135);
        }

        @Override // cn.missfresh.lib.image.c.b
        public void c(Drawable drawable) {
        }

        AnonymousClass1(b bVar) {
            this.a = bVar;
        }

        public /* synthetic */ void a(Object obj) {
            AppMethodBeat.i(15322, false);
            a((Bitmap) obj);
            AppMethodBeat.o(15322);
        }
    }

    public static void a(Context context, String str, int i, int i2, int i3, int i4, ImageView imageView) {
        AppMethodBeat.i(15415, false);
        a(context, str, 0, true, false, DiskCacheStrategy.DATA, i, i2, i3, i4, null).a(imageView);
        AppMethodBeat.o(15415);
    }

    public static void d(Context context, String str, ImageView imageView) {
        AppMethodBeat.i(15418, false);
        a(context, str, DiskCacheStrategy.RESOURCE, imageView);
        AppMethodBeat.o(15418);
    }

    public static void a(Activity activity, String str, int i, ImageView imageView) {
        AppMethodBeat.i(15419, false);
        a(activity, str, 0, true, false, DiskCacheStrategy.RESOURCE, i, -1, -1, -1, a(activity, imageView, false)).a(imageView);
        AppMethodBeat.o(15419);
    }

    private static ImageConfig a(Context context, String str, int i, boolean z, boolean z2, DiskCacheStrategy diskCacheStrategy, int i2, int i3, int i4, int i5, cn.missfresh.lib.image.b.a aVar) {
        AppMethodBeat.i(15423, false);
        ImageConfig f = b(context, str, i, z, z2, diskCacheStrategy, i2, i3, i4, i5, aVar).d().f(R.anim.anim_alpha_show);
        AppMethodBeat.o(15423);
        return f;
    }

    public static void a(Context context, int i, int i2, int i3, ImageView imageView) {
        AppMethodBeat.i(15427, false);
        a(context.getApplicationContext()).a(Integer.valueOf(i)).a().a(true).a(i2, i3).a(DiskCacheStrategy.NONE).a(imageView);
        AppMethodBeat.o(15427);
    }

    public static void a(Context context, File file, ImageView imageView, int i) {
        AppMethodBeat.i(15430, false);
        a(context).a(file).a(i, i).a().a(R.drawable.ic_default_100).a(imageView);
        AppMethodBeat.o(15430);
    }

    public static void a(Activity activity, int i, ImageView imageView) {
        AppMethodBeat.i(15431, false);
        a(activity, i, true, DiskCacheStrategy.NONE, R.drawable.ic_default_banner, -1, -1, -1, imageView, a(activity, imageView, false));
        AppMethodBeat.o(15431);
    }

    public static void a(Context context, String str, View view, int i, int i2, boolean z, a aVar) {
        AppMethodBeat.i(15435, false);
        if (view == null || context == null || cn.missfresh.utils.b.a(str)) {
            AppMethodBeat.o(15435);
            return;
        }
        b(context, str, 0, true, false, DiskCacheStrategy.DATA, z ? R.drawable.ic_default_100 : -1, -1, -1, -1, null).a(new AnonymousClass2(context, view, i, i2, aVar));
        AppMethodBeat.o(15435);
    }

    /* compiled from: ImageLoader */
    /* renamed from: cn.missfresh.module.base.network.d$2  reason: invalid class name */
    static class AnonymousClass2 extends cn.missfresh.lib.image.c.b<Drawable> {
        final /* synthetic */ Context a;
        final /* synthetic */ View b;
        final /* synthetic */ int c;
        final /* synthetic */ int d;
        final /* synthetic */ a e;

        @Override // cn.missfresh.lib.image.c.b
        public void b(Drawable drawable) {
            JniLib.cV(this, drawable, 136);
        }

        public void d(Drawable drawable) {
            JniLib.cV(this, drawable, 137);
        }

        AnonymousClass2(Context context, View view, int i, int i2, a aVar) {
            this.a = context;
            this.b = view;
            this.c = i;
            this.d = i2;
            this.e = aVar;
        }

        public /* synthetic */ void a(Object obj) {
            AppMethodBeat.i(15338, false);
            d((Drawable) obj);
            AppMethodBeat.o(15338);
        }

        @Override // cn.missfresh.lib.image.c.b
        public void c(Drawable drawable) {
            AppMethodBeat.i(15336, false);
            if (this.b != null) {
                if (Build.VERSION.SDK_INT >= 16) {
                    this.b.setBackground(drawable);
                } else {
                    this.b.setBackgroundDrawable(drawable);
                }
            }
            AppMethodBeat.o(15336);
        }
    }

    public static void b(Context context, String str, int i, int i2, b bVar) {
        AppMethodBeat.i(15448, false);
        b(context, str, 0, false, false, DiskCacheStrategy.DATA, -1, -1, i, i2, null).a(new AnonymousClass3(bVar));
        AppMethodBeat.o(15448);
    }

    /* compiled from: ImageLoader */
    /* renamed from: cn.missfresh.module.base.network.d$3  reason: invalid class name */
    static class AnonymousClass3 extends cn.missfresh.lib.image.c.b<Drawable> {
        final /* synthetic */ b a;

        @Override // cn.missfresh.lib.image.c.b
        public void b(Drawable drawable) {
            JniLib.cV(this, drawable, 138);
        }

        @Override // cn.missfresh.lib.image.c.b
        public void c(Drawable drawable) {
        }

        public void d(Drawable drawable) {
            JniLib.cV(this, drawable, 139);
        }

        AnonymousClass3(b bVar) {
            this.a = bVar;
        }

        public /* synthetic */ void a(Object obj) {
            AppMethodBeat.i(15350, false);
            d((Drawable) obj);
            AppMethodBeat.o(15350);
        }
    }

    public static void a(Context context, ImageView imageView, String str, int i) {
        AppMethodBeat.i(15456, false);
        if (cn.missfresh.utils.b.a(str) || imageView == null) {
            AppMethodBeat.o(15456);
            return;
        }
        b(context, str, 0, false, false, DiskCacheStrategy.DATA, -1, -1, -1, -1, null).e(i).a(imageView);
        AppMethodBeat.o(15456);
    }

    public static void f(Context context, String str, ImageView imageView) {
        AppMethodBeat.i(15460, false);
        b(context, str, 0, false, false, DiskCacheStrategy.DATA, -1, -1, -1, -1, null).a(imageView);
        AppMethodBeat.o(15460);
    }

    public static void b(Context context, String str, int i, int i2, ImageView imageView) {
        AppMethodBeat.i(15462, false);
        if (cn.missfresh.utils.b.a(str) || imageView == null) {
            AppMethodBeat.o(15462);
            return;
        }
        ImageViewTarget imageViewTarget = (ImageViewTarget) imageView.getTag(R.id.tag_gif_target);
        if (imageViewTarget == null) {
            imageViewTarget = new DrawableImageViewTarget(imageView);
            imageView.setTag(R.id.tag_gif_target, imageViewTarget);
        } else {
            String str2 = (String) imageView.getTag(R.id.tag_url);
            if (!cn.missfresh.utils.b.a(str2) && TextUtils.equals(str2, str)) {
                imageViewTarget = null;
            }
        }
        if (imageView.getTag(R.id.tag_attache) == null) {
            View.OnAttachStateChangeListener r2 = new AnonymousClass4(imageViewTarget);
            imageView.setTag(R.id.tag_attache, r2);
            imageView.addOnAttachStateChangeListener(r2);
        }
        a(context, str, false, DiskCacheStrategy.DATA, i, i2, -1, -1, imageView, (cn.missfresh.lib.image.b.a) new AnonymousClass5(imageView, str));
        AppMethodBeat.o(15462);
    }

    /* compiled from: ImageLoader */
    /* renamed from: cn.missfresh.module.base.network.d$5  reason: invalid class name */
    static class AnonymousClass5 extends cn.missfresh.lib.image.b.b<Drawable> {
        final /* synthetic */ ImageView a;
        final /* synthetic */ String b;

        AnonymousClass5(ImageView imageView, String str) {
            this.a = imageView;
            this.b = str;
        }

        public /* synthetic */ boolean a(Object obj, Object obj2, boolean z) {
            AppMethodBeat.i(15363, false);
            boolean a = a((Drawable) obj, obj2, z);
            AppMethodBeat.o(15363);
            return a;
        }

        public boolean a(Drawable drawable, Object obj, boolean z) {
            AppMethodBeat.i(15362, false);
            this.a.setTag(R.id.tag_url, this.b);
            if (drawable instanceof Animatable) {
                ((Animatable) drawable).start();
            }
            AppMethodBeat.o(15362);
            return false;
        }
    }

    public static void a(Context context, ImageView imageView, Uri uri, int i) {
        AppMethodBeat.i(15464, false);
        a(context).a(uri).a(DiskCacheStrategy.DATA).e(i).a(imageView);
        AppMethodBeat.o(15464);
    }

    /* compiled from: ImageLoader */
    /* renamed from: cn.missfresh.module.base.network.d$6  reason: invalid class name */
    static class AnonymousClass6 extends cn.missfresh.lib.image.b.b {
        final /* synthetic */ Activity a;
        final /* synthetic */ ImageView b;
        final /* synthetic */ boolean c;

        public boolean a(Object obj, Object obj2, boolean z) {
            return JniLib.cZ(this, obj, obj2, Boolean.valueOf(z), 143);
        }

        AnonymousClass6(Activity activity, ImageView imageView, boolean z) {
            this.a = activity;
            this.b = imageView;
            this.c = z;
        }
    }

    public static void a(ImageView imageView, Object obj, int i, boolean z) {
        int i2;
        int i3 = 0;
        AppMethodBeat.i(15477, false);
        if (imageView == null || obj == null) {
            AppMethodBeat.o(15477);
            return;
        }
        if (imageView.getScaleType() != ImageView.ScaleType.FIT_XY) {
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        }
        if (obj instanceof Drawable) {
            Drawable drawable = (Drawable) obj;
            i3 = drawable.getIntrinsicWidth();
            i2 = drawable.getIntrinsicHeight();
        } else if (obj instanceof Bitmap) {
            Bitmap bitmap = (Bitmap) obj;
            i3 = bitmap.getWidth();
            i2 = bitmap.getHeight();
        } else {
            i2 = 0;
        }
        if (i3 > 0 && i2 > 0) {
            ViewGroup.LayoutParams layoutParams = imageView.getLayoutParams();
            float paddingLeft = ((float) ((i - imageView.getPaddingLeft()) - imageView.getPaddingRight())) / ((float) i3);
            layoutParams.height = Math.round(((float) i2) * paddingLeft) + imageView.getPaddingTop() + imageView.getPaddingBottom();
            imageView.setLayoutParams(layoutParams);
            if (z) {
                imageView.setTag(R.id.iv_dialog_bg, Float.valueOf(paddingLeft));
            }
        }
        AppMethodBeat.o(15477);
    }
}
