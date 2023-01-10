package cn.missfresh.lib.image;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.graphics.Bitmap;
import android.graphics.drawable.Animatable;
import android.os.Build;
import android.text.TextUtils;
import android.widget.ImageView;
import cn.missfresh.lib.image.config.ImageConfig;
import cn.missfresh.lib.progress.b;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import com.bumptech.glide.GenericTransitionOptions;
import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.Transformation;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.CenterInside;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.load.resource.bitmap.FitCenter;
import com.bumptech.glide.load.resource.gif.GifDrawable;
import com.bumptech.glide.request.BaseRequestOptions;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

/* compiled from: GlideLoader */
public class a implements b {
    private Object a;

    private float a(float f) {
        if (f < 0.0f || f > 1.0f) {
            return 1.0f;
        }
        return f;
    }

    private int a(int i) {
        if (i <= 0) {
            return Integer.MIN_VALUE;
        }
        return i;
    }

    private int b(int i) {
        if (i <= 0) {
            return -1;
        }
        return i;
    }

    static /* synthetic */ void a(a aVar, ImageConfig imageConfig) {
        AppMethodBeat.i(3934, false);
        aVar.e(imageConfig);
        AppMethodBeat.o(3934);
    }

    public a() {
        this(0.0f, 0.0f);
    }

    public a(float f, float f2) {
    }

    public void a(ImageConfig imageConfig) {
        AppMethodBeat.i(3832, false);
        if (imageConfig == null) {
            AppMethodBeat.o(3832);
            return;
        }
        RequestBuilder c = c(imageConfig);
        if (c == null) {
            AppMethodBeat.o(3832);
            return;
        }
        if (imageConfig.V()) {
            if (imageConfig.W() <= 0 || imageConfig.X() <= 0) {
                c.preload();
            } else {
                c.preload(imageConfig.W(), imageConfig.X());
            }
        } else if (imageConfig.Y() != null) {
            c.into((RequestBuilder) imageConfig.Y().a(imageConfig));
        } else if (imageConfig.ae() != null) {
            c.into((RequestBuilder) imageConfig.ae().a());
        } else if (imageConfig.H() != null) {
            c.into(imageConfig.H());
        }
        AppMethodBeat.o(3832);
    }

    public <T> T b(ImageConfig imageConfig) {
        AppMethodBeat.i(3836, false);
        if (imageConfig == null) {
            AppMethodBeat.o(3836);
            return null;
        }
        RequestBuilder c = c(imageConfig);
        if (c == null) {
            AppMethodBeat.o(3836);
            return null;
        }
        try {
            T t = (T) c.submit(a(imageConfig.ac()), a(imageConfig.ad())).get();
            AppMethodBeat.o(3836);
            return t;
        } catch (InterruptedException | ExecutionException unused) {
            AppMethodBeat.o(3836);
            return null;
        }
    }

    private RequestBuilder c(ImageConfig imageConfig) {
        AppMethodBeat.i(3842, false);
        RequestBuilder a = a(i(imageConfig), imageConfig);
        if (a == null) {
            AppMethodBeat.o(3842);
            return null;
        }
        RequestBuilder listener = a(a, imageConfig).apply((BaseRequestOptions<?>) g(imageConfig)).listener(d(imageConfig));
        AppMethodBeat.o(3842);
        return listener;
    }

    private RequestBuilder a(RequestBuilder requestBuilder, ImageConfig imageConfig) {
        AppMethodBeat.i(3846, false);
        if (imageConfig.T() || imageConfig.Z() <= 0) {
            AppMethodBeat.o(3846);
            return requestBuilder;
        }
        RequestBuilder transition = requestBuilder.transition(GenericTransitionOptions.with(imageConfig.Z()));
        AppMethodBeat.o(3846);
        return transition;
    }

    private <T> RequestListener d(ImageConfig imageConfig) {
        AppMethodBeat.i(3849, false);
        f(imageConfig);
        if (imageConfig.U() != null || !imageConfig.G() || imageConfig.f() > 0) {
            AnonymousClass1 r1 = new AnonymousClass1(imageConfig);
            AppMethodBeat.o(3849);
            return r1;
        }
        AppMethodBeat.o(3849);
        return null;
    }

    /* compiled from: GlideLoader */
    /* access modifiers changed from: package-private */
    /* renamed from: cn.missfresh.lib.image.a$1  reason: invalid class name */
    public class AnonymousClass1 implements RequestListener<T> {
        final /* synthetic */ ImageConfig a;

        AnonymousClass1(ImageConfig imageConfig) {
            this.a = imageConfig;
        }

        @Override // com.bumptech.glide.request.RequestListener
        public boolean onLoadFailed(GlideException glideException, Object obj, Target<T> target, boolean z) {
            AppMethodBeat.i(3811, false);
            a.a(a.this, this.a);
            if (this.a.U() != null) {
                boolean a = this.a.U().a(glideException, obj, z);
                AppMethodBeat.o(3811);
                return a;
            }
            AppMethodBeat.o(3811);
            return false;
        }

        @Override // com.bumptech.glide.request.RequestListener
        public boolean onResourceReady(T t, Object obj, Target<T> target, DataSource dataSource, boolean z) {
            AppMethodBeat.i(3813, false);
            a.a(a.this, this.a);
            if (t instanceof GifDrawable) {
                T t2 = t;
                int f = this.a.f();
                if (f < 0 && f != -1) {
                    f = -1;
                }
                t2.setLoopCount(f);
            }
            if (t instanceof Animatable) {
                T t3 = t;
                if (!t3.isRunning()) {
                    t3.start();
                }
            }
            if (this.a.U() != null) {
                boolean a = this.a.U().a(t, obj, z);
                AppMethodBeat.o(3813);
                return a;
            }
            AppMethodBeat.o(3813);
            return false;
        }
    }

    private void e(ImageConfig imageConfig) {
        AppMethodBeat.i(3852, false);
        if (this.a != null && imageConfig.aa()) {
            b.a().a(this.a);
        }
        AppMethodBeat.o(3852);
    }

    private void f(ImageConfig imageConfig) {
        AppMethodBeat.i(3855, false);
        if (imageConfig.aa() && this.a != null) {
            if (imageConfig.U() != null) {
                try {
                    Class.forName("cn.missfresh.lib.progress.a");
                    b.a().a(this.a, new AnonymousClass2(imageConfig));
                } catch (ClassNotFoundException unused) {
                    RuntimeException runtimeException = new RuntimeException("\u8fdb\u5ea6\u663e\u793a\u529f\u80fd\uff0c\u9700\u8981\u4f9d\u8d56 MFProgressLib\uff01");
                    AppMethodBeat.o(3855);
                    throw runtimeException;
                }
            } else {
                NullPointerException nullPointerException = new NullPointerException("LoadListener \u4e0d\u53ef\u4ee5\u4e3a\u7a7a\uff01");
                AppMethodBeat.o(3855);
                throw nullPointerException;
            }
        }
        AppMethodBeat.o(3855);
    }

    /* compiled from: GlideLoader */
    /* access modifiers changed from: package-private */
    /* renamed from: cn.missfresh.lib.image.a$2  reason: invalid class name */
    public class AnonymousClass2 implements cn.missfresh.lib.progress.a {
        final /* synthetic */ ImageConfig a;

        AnonymousClass2(ImageConfig imageConfig) {
            this.a = imageConfig;
        }
    }

    private RequestOptions g(ImageConfig imageConfig) {
        AppMethodBeat.i(3859, false);
        RequestOptions a = a(b(new RequestOptions().skipMemoryCache(imageConfig.P()).sizeMultiplier(a(imageConfig.C())).override(b(imageConfig.D()), b(imageConfig.E())).priority(a(imageConfig.I())).diskCacheStrategy(h(imageConfig)), imageConfig), imageConfig);
        if (imageConfig.T()) {
            a = a.dontAnimate();
        }
        if (imageConfig.ab()) {
            a = a.disallowHardwareConfig();
        }
        AppMethodBeat.o(3859);
        return a;
    }

    private RequestOptions a(RequestOptions requestOptions, ImageConfig imageConfig) {
        AppMethodBeat.i(3863, false);
        if (imageConfig.S()) {
            RequestOptions dontTransform = requestOptions.dontTransform();
            AppMethodBeat.o(3863);
            return dontTransform;
        }
        ArrayList arrayList = new ArrayList();
        if (imageConfig.i() != null && !imageConfig.i().isEmpty()) {
            arrayList.add(new cn.missfresh.lib.image.a.a(imageConfig.i()));
        }
        if (imageConfig.B()) {
            arrayList.add(new CircleCrop());
        }
        if (!arrayList.isEmpty()) {
            arrayList.add(a(imageConfig.A()));
            RequestOptions transform = requestOptions.transform((Transformation[]) arrayList.toArray(new Transformation[0]));
            AppMethodBeat.o(3863);
            return transform;
        }
        AppMethodBeat.o(3863);
        return requestOptions;
    }

    private DiskCacheStrategy h(ImageConfig imageConfig) {
        DiskCacheStrategy diskCacheStrategy;
        AppMethodBeat.i(3867, false);
        if (imageConfig.Q()) {
            DiskCacheStrategy diskCacheStrategy2 = DiskCacheStrategy.NONE;
            AppMethodBeat.o(3867);
            return diskCacheStrategy2;
        } else if (imageConfig.c() != null) {
            DiskCacheStrategy c = imageConfig.c();
            AppMethodBeat.o(3867);
            return c;
        } else {
            int R = imageConfig.R();
            if (R == 0) {
                diskCacheStrategy = DiskCacheStrategy.DATA;
            } else if (R == 1) {
                diskCacheStrategy = DiskCacheStrategy.RESOURCE;
            } else if (R == 2) {
                diskCacheStrategy = DiskCacheStrategy.NONE;
            } else if (R != 3) {
                diskCacheStrategy = DiskCacheStrategy.AUTOMATIC;
            } else {
                diskCacheStrategy = DiskCacheStrategy.ALL;
            }
            AppMethodBeat.o(3867);
            return diskCacheStrategy;
        }
    }

    private Priority a(PriorityModel priorityModel) {
        Priority priority;
        AppMethodBeat.i(3871, false);
        int i = 3.a[priorityModel.ordinal()];
        if (i == 1) {
            priority = Priority.HIGH;
        } else if (i == 2) {
            priority = Priority.IMMEDIATE;
        } else if (i != 3) {
            priority = Priority.NORMAL;
        } else {
            priority = Priority.LOW;
        }
        AppMethodBeat.o(3871);
        return priority;
    }

    private BitmapTransformation a(ImageView.ScaleType scaleType) {
        AppMethodBeat.i(3874, false);
        int i = 3.b[scaleType.ordinal()];
        if (i == 1) {
            CenterCrop centerCrop = new CenterCrop();
            AppMethodBeat.o(3874);
            return centerCrop;
        } else if (i == 2 || i == 3) {
            CenterInside centerInside = new CenterInside();
            AppMethodBeat.o(3874);
            return centerInside;
        } else {
            FitCenter fitCenter = new FitCenter();
            AppMethodBeat.o(3874);
            return fitCenter;
        }
    }

    private RequestOptions b(RequestOptions requestOptions, ImageConfig imageConfig) {
        AppMethodBeat.i(3878, false);
        RequestOptions c = c(d(e(requestOptions, imageConfig), imageConfig), imageConfig);
        AppMethodBeat.o(3878);
        return c;
    }

    private RequestOptions c(RequestOptions requestOptions, ImageConfig imageConfig) {
        AppMethodBeat.i(3881, false);
        if (imageConfig.O() != null) {
            RequestOptions fallback = requestOptions.fallback(imageConfig.O());
            AppMethodBeat.o(3881);
            return fallback;
        } else if (imageConfig.N() > 0) {
            RequestOptions fallback2 = requestOptions.fallback(imageConfig.N());
            AppMethodBeat.o(3881);
            return fallback2;
        } else {
            AppMethodBeat.o(3881);
            return requestOptions;
        }
    }

    private RequestOptions d(RequestOptions requestOptions, ImageConfig imageConfig) {
        AppMethodBeat.i(3884, false);
        if (imageConfig.M() != null) {
            RequestOptions error = requestOptions.error(imageConfig.M());
            AppMethodBeat.o(3884);
            return error;
        } else if (imageConfig.L() > 0) {
            RequestOptions error2 = requestOptions.error(imageConfig.L());
            AppMethodBeat.o(3884);
            return error2;
        } else {
            AppMethodBeat.o(3884);
            return requestOptions;
        }
    }

    private RequestOptions e(RequestOptions requestOptions, ImageConfig imageConfig) {
        AppMethodBeat.i(3887, false);
        if (imageConfig.K() != null) {
            RequestOptions placeholder = requestOptions.placeholder(imageConfig.K());
            AppMethodBeat.o(3887);
            return placeholder;
        } else if (imageConfig.J() > 0) {
            RequestOptions placeholder2 = requestOptions.placeholder(imageConfig.J());
            AppMethodBeat.o(3887);
            return placeholder2;
        } else {
            AppMethodBeat.o(3887);
            return requestOptions;
        }
    }

    private RequestManager i(ImageConfig imageConfig) {
        RequestManager requestManager;
        AppMethodBeat.i(3890, false);
        RequestManager requestManager2 = null;
        if (imageConfig.m() != null) {
            if (a(imageConfig.m())) {
                requestManager2 = Glide.with(imageConfig.m());
            }
        } else if (imageConfig.n() != null) {
            if (a((Activity) imageConfig.n())) {
                requestManager2 = Glide.with(imageConfig.n());
            }
        } else if (imageConfig.o() != null) {
            if (imageConfig.o().getActivity() != null && a(imageConfig.o().getActivity())) {
                requestManager2 = Glide.with(imageConfig.o());
            }
        } else if (imageConfig.p() != null) {
            if (imageConfig.p().getActivity() != null && a((Activity) imageConfig.p().getActivity())) {
                requestManager2 = Glide.with(imageConfig.p());
            }
        } else if (imageConfig.q() != null) {
            if (imageConfig.q().getContext() != null) {
                Activity c = c(imageConfig.q().getContext());
                if (c == null) {
                    requestManager = Glide.with(imageConfig.q().getContext());
                } else if (a(c)) {
                    requestManager = Glide.with(c);
                }
                requestManager2 = requestManager;
            }
        } else if (imageConfig.l() != null) {
            requestManager2 = Glide.with(imageConfig.l());
        }
        AppMethodBeat.o(3890);
        return requestManager2;
    }

    private Activity c(Context context) {
        AppMethodBeat.i(3894, false);
        if (context instanceof Activity) {
            Activity activity = (Activity) context;
            AppMethodBeat.o(3894);
            return activity;
        } else if (context instanceof ContextWrapper) {
            Activity c = c(((ContextWrapper) context).getBaseContext());
            AppMethodBeat.o(3894);
            return c;
        } else {
            AppMethodBeat.o(3894);
            return null;
        }
    }

    private boolean a(Activity activity) {
        boolean z = false;
        AppMethodBeat.i(3898, false);
        if (Build.VERSION.SDK_INT >= 17) {
            if (activity != null && !activity.isDestroyed()) {
                z = true;
            }
            AppMethodBeat.o(3898);
            return z;
        }
        if (activity != null && !activity.isFinishing()) {
            z = true;
        }
        AppMethodBeat.o(3898);
        return z;
    }

    private RequestBuilder a(RequestManager requestManager, ImageConfig imageConfig) {
        AppMethodBeat.i(3901, false);
        if (requestManager == null) {
            AppMethodBeat.o(3901);
            return null;
        } else if (imageConfig.F()) {
            RequestBuilder b = b(requestManager.asGif(), imageConfig);
            AppMethodBeat.o(3901);
            return b;
        } else if (imageConfig.G()) {
            RequestBuilder b2 = b(requestManager.asBitmap(), imageConfig);
            AppMethodBeat.o(3901);
            return b2;
        } else {
            RequestBuilder b3 = b(requestManager.asDrawable(), imageConfig);
            AppMethodBeat.o(3901);
            return b3;
        }
    }

    private RequestBuilder b(RequestBuilder requestBuilder, ImageConfig imageConfig) {
        AppMethodBeat.i(3904, false);
        if (!TextUtils.isEmpty(imageConfig.r())) {
            this.a = imageConfig.r();
            RequestBuilder load = requestBuilder.load(imageConfig.r());
            AppMethodBeat.o(3904);
            return load;
        } else if (imageConfig.x() != null) {
            this.a = imageConfig.x();
            RequestBuilder load2 = requestBuilder.load(imageConfig.x());
            AppMethodBeat.o(3904);
            return load2;
        } else if (imageConfig.w() != null) {
            this.a = imageConfig.w();
            RequestBuilder load3 = requestBuilder.load(imageConfig.w());
            AppMethodBeat.o(3904);
            return load3;
        } else if (imageConfig.y() != null) {
            this.a = imageConfig.y();
            RequestBuilder load4 = requestBuilder.load(imageConfig.y());
            AppMethodBeat.o(3904);
            return load4;
        } else if (imageConfig.t() > 0) {
            this.a = Integer.valueOf(imageConfig.t());
            RequestBuilder load5 = requestBuilder.load(Integer.valueOf(imageConfig.t()));
            AppMethodBeat.o(3904);
            return load5;
        } else if (imageConfig.s() != null) {
            this.a = imageConfig.s();
            RequestBuilder load6 = requestBuilder.load(imageConfig.s());
            AppMethodBeat.o(3904);
            return load6;
        } else if (imageConfig.u() != null) {
            this.a = imageConfig.u();
            RequestBuilder load7 = requestBuilder.load(imageConfig.u());
            AppMethodBeat.o(3904);
            return load7;
        } else if (imageConfig.v() != null) {
            this.a = imageConfig.v();
            RequestBuilder load8 = requestBuilder.load(imageConfig.v());
            AppMethodBeat.o(3904);
            return load8;
        } else if (imageConfig.z() != null) {
            this.a = imageConfig.z();
            RequestBuilder load9 = requestBuilder.load(imageConfig.z());
            AppMethodBeat.o(3904);
            return load9;
        } else {
            j(imageConfig);
            AppMethodBeat.o(3904);
            return null;
        }
    }

    private void j(ImageConfig imageConfig) {
        AppMethodBeat.i(3905, false);
        if (imageConfig.H() != null) {
            if (imageConfig.L() > 0) {
                imageConfig.H().setImageResource(imageConfig.L());
            } else if (imageConfig.M() != null) {
                imageConfig.H().setImageDrawable(imageConfig.M());
            }
        }
        if (imageConfig.U() != null) {
            imageConfig.U().a(new NullPointerException("\u8d44\u6e90\u8def\u5f84\u672a\u914d\u7f6e!"), (Object) null, true);
        }
        if (imageConfig.Y() != null) {
            imageConfig.Y().b(imageConfig.M());
        }
        AppMethodBeat.o(3905);
    }

    public void a(Context context) {
        AppMethodBeat.i(3916, false);
        if (context == null) {
            AppMethodBeat.o(3916);
            return;
        }
        Glide.get(context).clearDiskCache();
        AppMethodBeat.o(3916);
    }

    public void b(Context context) {
        AppMethodBeat.i(3920, false);
        if (context == null) {
            AppMethodBeat.o(3920);
            return;
        }
        Glide.get(context).clearMemory();
        AppMethodBeat.o(3920);
    }

    public void a(Context context, int i) {
        AppMethodBeat.i(3924, false);
        if (context == null) {
            AppMethodBeat.o(3924);
            return;
        }
        if (i >= 60) {
            Glide.get(context).trimMemory(20);
        }
        AppMethodBeat.o(3924);
    }

    public Bitmap a(int i, int i2, Bitmap.Config config) {
        AppMethodBeat.i(3932, false);
        if (!c.a()) {
            AppMethodBeat.o(3932);
            return null;
        } else if (c.c() == null) {
            AppMethodBeat.o(3932);
            return null;
        } else {
            BitmapPool bitmapPool = Glide.get(c.c()).getBitmapPool();
            if (bitmapPool == null) {
                AppMethodBeat.o(3932);
                return null;
            }
            Bitmap bitmap = bitmapPool.get(i, i2, config);
            AppMethodBeat.o(3932);
            return bitmap;
        }
    }
}
