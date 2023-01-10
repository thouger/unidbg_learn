package cn.missfresh.module.base.utils;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.util.Log;
import android.widget.ImageView;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.load.resource.gif.GifDrawable;
import com.bumptech.glide.request.BaseRequestOptions;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/* compiled from: LoadOneTimeGif */
public class z {

    /* compiled from: LoadOneTimeGif */
    public interface a {
        void a();
    }

    public static void a(Context context, Object obj, ImageView imageView, a aVar) {
        AppMethodBeat.i(23336, false);
        RequestOptions diskCacheStrategy = new RequestOptions().skipMemoryCache(true).diskCacheStrategy(DiskCacheStrategy.NONE);
        if (context == null) {
            AppMethodBeat.o(23336);
            return;
        }
        if (Build.VERSION.SDK_INT >= 17 && (context instanceof Activity)) {
            Activity activity = (Activity) context;
            if (activity.isFinishing() && activity.isDestroyed()) {
                Log.e("LoadOneTimeGif", "activity\u9500\u6bc1\u4e86");
                AppMethodBeat.o(23336);
                return;
            }
        }
        Glide.with(context).asGif().load(obj).apply((BaseRequestOptions<?>) diskCacheStrategy).listener(new AnonymousClass1(aVar, imageView)).into(imageView);
        AppMethodBeat.o(23336);
    }

    /* compiled from: LoadOneTimeGif */
    /* renamed from: cn.missfresh.module.base.utils.z$1  reason: invalid class name */
    static class AnonymousClass1 implements RequestListener<GifDrawable> {
        final /* synthetic */ a a;
        final /* synthetic */ ImageView b;

        @Override // com.bumptech.glide.request.RequestListener
        public boolean onLoadFailed(GlideException glideException, Object obj, Target<GifDrawable> target, boolean z) {
            return false;
        }

        AnonymousClass1(a aVar, ImageView imageView) {
            this.a = aVar;
            this.b = imageView;
        }

        @Override // com.bumptech.glide.request.RequestListener
        public /* synthetic */ boolean onResourceReady(Object obj, Object obj2, Target target, DataSource dataSource, boolean z) {
            AppMethodBeat.i(23334, false);
            boolean a = a((GifDrawable) obj, obj2, target, dataSource, z);
            AppMethodBeat.o(23334);
            return a;
        }

        public boolean a(GifDrawable gifDrawable, Object obj, Target<GifDrawable> target, DataSource dataSource, boolean z) {
            AppMethodBeat.i(23333, false);
            try {
                Field declaredField = GifDrawable.class.getDeclaredField("state");
                declaredField.setAccessible(true);
                Field declaredField2 = Class.forName("com.bumptech.glide.load.resource.gif.GifDrawable$GifState").getDeclaredField("frameLoader");
                declaredField2.setAccessible(true);
                Field declaredField3 = Class.forName("com.bumptech.glide.load.resource.gif.GifFrameLoader").getDeclaredField("gifDecoder");
                declaredField3.setAccessible(true);
                Class<?> cls = Class.forName("com.bumptech.glide.gifdecoder.GifDecoder");
                Object obj2 = declaredField3.get(declaredField2.get(declaredField.get(gifDrawable)));
                Method declaredMethod = cls.getDeclaredMethod("getDelay", Integer.TYPE);
                declaredMethod.setAccessible(true);
                gifDrawable.setLoopCount(1);
                int frameCount = gifDrawable.getFrameCount();
                int i = 0;
                for (int i2 = 0; i2 < frameCount; i2++) {
                    i += ((Integer) declaredMethod.invoke(obj2, Integer.valueOf(i2))).intValue();
                }
                this.b.postDelayed(new b(this.a), (long) i);
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e2) {
                e2.printStackTrace();
            } catch (IllegalAccessException e3) {
                e3.printStackTrace();
            } catch (NoSuchMethodException e4) {
                e4.printStackTrace();
            } catch (InvocationTargetException e5) {
                e5.printStackTrace();
            } catch (Exception e6) {
                e6.printStackTrace();
            }
            AppMethodBeat.o(23333);
            return false;
        }
    }

    /* compiled from: LoadOneTimeGif */
    /* access modifiers changed from: private */
    public static class b implements Runnable {
        private a a;

        public b(a aVar) {
            this.a = aVar;
        }

        @Override // java.lang.Runnable
        public void run() {
            AppMethodBeat.i(23335, false);
            a aVar = this.a;
            if (aVar != null) {
                aVar.a();
            }
            AppMethodBeat.o(23335);
        }
    }
}
