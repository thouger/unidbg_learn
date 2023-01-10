package cn.missfresh.module.base.common.config;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.TransitionOptions;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.Key;
import com.bumptech.glide.load.Option;
import com.bumptech.glide.load.Transformation;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.DownsampleStrategy;
import com.bumptech.glide.request.BaseRequestOptions;
import com.bumptech.glide.request.RequestListener;
import com.tencent.smtt.sdk.TbsReaderView;
import java.io.File;
import java.net.URL;

/* compiled from: GlideRequest */
public class e<TranscodeType> extends RequestBuilder<TranscodeType> implements Cloneable {
    @Override // com.bumptech.glide.RequestBuilder
    public /* synthetic */ RequestBuilder addListener(RequestListener requestListener) {
        AppMethodBeat.i(10992, false);
        e<TranscodeType> b = b(requestListener);
        AppMethodBeat.o(10992);
        return b;
    }

    @Override // com.bumptech.glide.request.BaseRequestOptions
    public /* synthetic */ BaseRequestOptions centerCrop() {
        AppMethodBeat.i(11055, false);
        e<TranscodeType> d = d();
        AppMethodBeat.o(11055);
        return d;
    }

    @Override // com.bumptech.glide.request.BaseRequestOptions
    public /* synthetic */ BaseRequestOptions centerInside() {
        AppMethodBeat.i(11050, false);
        e<TranscodeType> h = h();
        AppMethodBeat.o(11050);
        return h;
    }

    @Override // com.bumptech.glide.request.BaseRequestOptions
    public /* synthetic */ BaseRequestOptions circleCrop() {
        AppMethodBeat.i(11048, false);
        e<TranscodeType> j = j();
        AppMethodBeat.o(11048);
        return j;
    }

    @Override // com.bumptech.glide.request.BaseRequestOptions
    public /* synthetic */ BaseRequestOptions decode(Class cls) {
        AppMethodBeat.i(11069, false);
        e<TranscodeType> a = a((Class<?>) cls);
        AppMethodBeat.o(11069);
        return a;
    }

    @Override // com.bumptech.glide.request.BaseRequestOptions
    public /* synthetic */ BaseRequestOptions disallowHardwareConfig() {
        AppMethodBeat.i(11062, false);
        e<TranscodeType> b = b();
        AppMethodBeat.o(11062);
        return b;
    }

    @Override // com.bumptech.glide.request.BaseRequestOptions
    public /* synthetic */ BaseRequestOptions diskCacheStrategy(DiskCacheStrategy diskCacheStrategy) {
        AppMethodBeat.i(11086, false);
        e<TranscodeType> a = a(diskCacheStrategy);
        AppMethodBeat.o(11086);
        return a;
    }

    @Override // com.bumptech.glide.request.BaseRequestOptions
    public /* synthetic */ BaseRequestOptions dontAnimate() {
        AppMethodBeat.i(11013, false);
        e<TranscodeType> l = l();
        AppMethodBeat.o(11013);
        return l;
    }

    @Override // com.bumptech.glide.request.BaseRequestOptions
    public /* synthetic */ BaseRequestOptions dontTransform() {
        AppMethodBeat.i(11015, false);
        e<TranscodeType> k = k();
        AppMethodBeat.o(11015);
        return k;
    }

    @Override // com.bumptech.glide.request.BaseRequestOptions
    public /* synthetic */ BaseRequestOptions downsample(DownsampleStrategy downsampleStrategy) {
        AppMethodBeat.i(11061, false);
        e<TranscodeType> a = a(downsampleStrategy);
        AppMethodBeat.o(11061);
        return a;
    }

    @Override // com.bumptech.glide.request.BaseRequestOptions
    public /* synthetic */ BaseRequestOptions encodeFormat(Bitmap.CompressFormat compressFormat) {
        AppMethodBeat.i(11067, false);
        e<TranscodeType> a = a(compressFormat);
        AppMethodBeat.o(11067);
        return a;
    }

    @Override // com.bumptech.glide.request.BaseRequestOptions
    public /* synthetic */ BaseRequestOptions encodeQuality(int i) {
        AppMethodBeat.i(11065, false);
        e<TranscodeType> e = e(i);
        AppMethodBeat.o(11065);
        return e;
    }

    @Override // com.bumptech.glide.RequestBuilder
    public /* synthetic */ RequestBuilder error(RequestBuilder requestBuilder) {
        AppMethodBeat.i(10991, false);
        e<TranscodeType> a = a(requestBuilder);
        AppMethodBeat.o(10991);
        return a;
    }

    @Override // com.bumptech.glide.request.BaseRequestOptions
    public /* synthetic */ BaseRequestOptions error(int i) {
        AppMethodBeat.i(11078, false);
        e<TranscodeType> c = c(i);
        AppMethodBeat.o(11078);
        return c;
    }

    @Override // com.bumptech.glide.request.BaseRequestOptions
    public /* synthetic */ BaseRequestOptions error(Drawable drawable) {
        AppMethodBeat.i(11079, false);
        e<TranscodeType> c = c(drawable);
        AppMethodBeat.o(11079);
        return c;
    }

    @Override // com.bumptech.glide.request.BaseRequestOptions
    public /* synthetic */ BaseRequestOptions fallback(int i) {
        AppMethodBeat.i(11080, false);
        e<TranscodeType> b = b(i);
        AppMethodBeat.o(11080);
        return b;
    }

    @Override // com.bumptech.glide.request.BaseRequestOptions
    public /* synthetic */ BaseRequestOptions fallback(Drawable drawable) {
        AppMethodBeat.i(11081, false);
        e<TranscodeType> b = b(drawable);
        AppMethodBeat.o(11081);
        return b;
    }

    @Override // com.bumptech.glide.request.BaseRequestOptions
    public /* synthetic */ BaseRequestOptions fitCenter() {
        AppMethodBeat.i(11052, false);
        e<TranscodeType> f = f();
        AppMethodBeat.o(11052);
        return f;
    }

    @Override // com.bumptech.glide.request.BaseRequestOptions
    public /* synthetic */ BaseRequestOptions format(DecodeFormat decodeFormat) {
        AppMethodBeat.i(11063, false);
        e<TranscodeType> a = a(decodeFormat);
        AppMethodBeat.o(11063);
        return a;
    }

    @Override // com.bumptech.glide.request.BaseRequestOptions
    public /* synthetic */ BaseRequestOptions frame(long j) {
        AppMethodBeat.i(11064, false);
        e<TranscodeType> a = a(j);
        AppMethodBeat.o(11064);
        return a;
    }

    /* access modifiers changed from: protected */
    @Override // com.bumptech.glide.RequestBuilder
    public /* synthetic */ RequestBuilder getDownloadOnlyRequest() {
        AppMethodBeat.i(10961, false);
        e<File> a = a();
        AppMethodBeat.o(10961);
        return a;
    }

    @Override // com.bumptech.glide.RequestBuilder
    public /* synthetic */ RequestBuilder listener(RequestListener requestListener) {
        AppMethodBeat.i(10993, false);
        e<TranscodeType> a = a(requestListener);
        AppMethodBeat.o(10993);
        return a;
    }

    @Override // com.bumptech.glide.request.BaseRequestOptions
    public /* synthetic */ BaseRequestOptions onlyRetrieveFromCache(boolean z) {
        AppMethodBeat.i(11087, false);
        e<TranscodeType> c = c(z);
        AppMethodBeat.o(11087);
        return c;
    }

    @Override // com.bumptech.glide.request.BaseRequestOptions
    public /* synthetic */ BaseRequestOptions optionalCenterCrop() {
        AppMethodBeat.i(11057, false);
        e<TranscodeType> c = c();
        AppMethodBeat.o(11057);
        return c;
    }

    @Override // com.bumptech.glide.request.BaseRequestOptions
    public /* synthetic */ BaseRequestOptions optionalCenterInside() {
        AppMethodBeat.i(11051, false);
        e<TranscodeType> g = g();
        AppMethodBeat.o(11051);
        return g;
    }

    @Override // com.bumptech.glide.request.BaseRequestOptions
    public /* synthetic */ BaseRequestOptions optionalCircleCrop() {
        AppMethodBeat.i(11049, false);
        e<TranscodeType> i = i();
        AppMethodBeat.o(11049);
        return i;
    }

    @Override // com.bumptech.glide.request.BaseRequestOptions
    public /* synthetic */ BaseRequestOptions optionalFitCenter() {
        AppMethodBeat.i(11053, false);
        e<TranscodeType> e = e();
        AppMethodBeat.o(11053);
        return e;
    }

    @Override // com.bumptech.glide.request.BaseRequestOptions
    public /* synthetic */ BaseRequestOptions optionalTransform(Transformation transformation) {
        AppMethodBeat.i(11043, false);
        e<TranscodeType> b = b(transformation);
        AppMethodBeat.o(11043);
        return b;
    }

    @Override // com.bumptech.glide.request.BaseRequestOptions
    public /* synthetic */ BaseRequestOptions optionalTransform(Class cls, Transformation transformation) {
        AppMethodBeat.i(11019, false);
        e<TranscodeType> a = a(cls, transformation);
        AppMethodBeat.o(11019);
        return a;
    }

    @Override // com.bumptech.glide.request.BaseRequestOptions
    public /* synthetic */ BaseRequestOptions override(int i) {
        AppMethodBeat.i(11073, false);
        e<TranscodeType> d = d(i);
        AppMethodBeat.o(11073);
        return d;
    }

    @Override // com.bumptech.glide.request.BaseRequestOptions
    public /* synthetic */ BaseRequestOptions override(int i, int i2) {
        AppMethodBeat.i(11075, false);
        e<TranscodeType> a = a(i, i2);
        AppMethodBeat.o(11075);
        return a;
    }

    @Override // com.bumptech.glide.request.BaseRequestOptions
    public /* synthetic */ BaseRequestOptions placeholder(int i) {
        AppMethodBeat.i(11082, false);
        e<TranscodeType> a = a(i);
        AppMethodBeat.o(11082);
        return a;
    }

    @Override // com.bumptech.glide.request.BaseRequestOptions
    public /* synthetic */ BaseRequestOptions placeholder(Drawable drawable) {
        AppMethodBeat.i(11083, false);
        e<TranscodeType> a = a(drawable);
        AppMethodBeat.o(11083);
        return a;
    }

    @Override // com.bumptech.glide.request.BaseRequestOptions
    public /* synthetic */ BaseRequestOptions priority(Priority priority) {
        AppMethodBeat.i(11085, false);
        e<TranscodeType> a = a(priority);
        AppMethodBeat.o(11085);
        return a;
    }

    @Override // com.bumptech.glide.request.BaseRequestOptions
    public /* synthetic */ BaseRequestOptions set(Option option, Object obj) {
        AppMethodBeat.i(11070, false);
        e<TranscodeType> a = a((Option<Option>) option, (Option) obj);
        AppMethodBeat.o(11070);
        return a;
    }

    @Override // com.bumptech.glide.request.BaseRequestOptions
    public /* synthetic */ BaseRequestOptions signature(Key key) {
        AppMethodBeat.i(11072, false);
        e<TranscodeType> a = a(key);
        AppMethodBeat.o(11072);
        return a;
    }

    @Override // com.bumptech.glide.request.BaseRequestOptions
    public /* synthetic */ BaseRequestOptions sizeMultiplier(float f) {
        AppMethodBeat.i(11090, false);
        e<TranscodeType> a = a(f);
        AppMethodBeat.o(11090);
        return a;
    }

    @Override // com.bumptech.glide.request.BaseRequestOptions
    public /* synthetic */ BaseRequestOptions skipMemoryCache(boolean z) {
        AppMethodBeat.i(11076, false);
        e<TranscodeType> d = d(z);
        AppMethodBeat.o(11076);
        return d;
    }

    @Override // com.bumptech.glide.request.BaseRequestOptions
    public /* synthetic */ BaseRequestOptions theme(Resources.Theme theme) {
        AppMethodBeat.i(11077, false);
        e<TranscodeType> a = a(theme);
        AppMethodBeat.o(11077);
        return a;
    }

    @Override // com.bumptech.glide.RequestBuilder
    public /* synthetic */ RequestBuilder thumbnail(float f) {
        AppMethodBeat.i(10984, false);
        e<TranscodeType> b = b(f);
        AppMethodBeat.o(10984);
        return b;
    }

    @Override // com.bumptech.glide.RequestBuilder
    public /* synthetic */ RequestBuilder thumbnail(RequestBuilder requestBuilder) {
        AppMethodBeat.i(10989, false);
        e<TranscodeType> b = b(requestBuilder);
        AppMethodBeat.o(10989);
        return b;
    }

    @Override // com.bumptech.glide.RequestBuilder
    @SafeVarargs
    public /* synthetic */ RequestBuilder thumbnail(RequestBuilder[] requestBuilderArr) {
        AppMethodBeat.i(10986, false);
        e<TranscodeType> a = a(requestBuilderArr);
        AppMethodBeat.o(10986);
        return a;
    }

    @Override // com.bumptech.glide.request.BaseRequestOptions
    public /* synthetic */ BaseRequestOptions timeout(int i) {
        AppMethodBeat.i(11059, false);
        e<TranscodeType> f = f(i);
        AppMethodBeat.o(11059);
        return f;
    }

    @Override // com.bumptech.glide.request.BaseRequestOptions
    public /* synthetic */ BaseRequestOptions transform(Transformation transformation) {
        AppMethodBeat.i(11047, false);
        e<TranscodeType> a = a((Transformation<Bitmap>) transformation);
        AppMethodBeat.o(11047);
        return a;
    }

    @Override // com.bumptech.glide.request.BaseRequestOptions
    public /* synthetic */ BaseRequestOptions transform(Class cls, Transformation transformation) {
        AppMethodBeat.i(11017, false);
        e<TranscodeType> b = b(cls, transformation);
        AppMethodBeat.o(11017);
        return b;
    }

    @Override // com.bumptech.glide.request.BaseRequestOptions
    public /* synthetic */ BaseRequestOptions transform(Transformation[] transformationArr) {
        AppMethodBeat.i(11046, false);
        e<TranscodeType> a = a((Transformation<Bitmap>[]) transformationArr);
        AppMethodBeat.o(11046);
        return a;
    }

    @Override // com.bumptech.glide.request.BaseRequestOptions
    @Deprecated
    public /* synthetic */ BaseRequestOptions transforms(Transformation[] transformationArr) {
        AppMethodBeat.i(11045, false);
        e<TranscodeType> b = b(transformationArr);
        AppMethodBeat.o(11045);
        return b;
    }

    @Override // com.bumptech.glide.RequestBuilder
    public /* synthetic */ RequestBuilder transition(TransitionOptions transitionOptions) {
        AppMethodBeat.i(10994, false);
        e<TranscodeType> a = a(transitionOptions);
        AppMethodBeat.o(10994);
        return a;
    }

    @Override // com.bumptech.glide.request.BaseRequestOptions
    public /* synthetic */ BaseRequestOptions useAnimationPool(boolean z) {
        AppMethodBeat.i(11088, false);
        e<TranscodeType> b = b(z);
        AppMethodBeat.o(11088);
        return b;
    }

    @Override // com.bumptech.glide.request.BaseRequestOptions
    public /* synthetic */ BaseRequestOptions useUnlimitedSourceGeneratorsPool(boolean z) {
        AppMethodBeat.i(11089, false);
        e<TranscodeType> a = a(z);
        AppMethodBeat.o(11089);
        return a;
    }

    e(Class<TranscodeType> cls, RequestBuilder<?> requestBuilder) {
        super(cls, requestBuilder);
    }

    e(Glide glide, RequestManager requestManager, Class<TranscodeType> cls, Context context) {
        super(glide, requestManager, cls, context);
    }

    /* access modifiers changed from: protected */
    public e<File> a() {
        AppMethodBeat.i(10766, false);
        e<TranscodeType> a = new e(File.class, this).a((BaseRequestOptions<?>) DOWNLOAD_ONLY_OPTIONS);
        AppMethodBeat.o(10766);
        return a;
    }

    public e<TranscodeType> a(float f) {
        AppMethodBeat.i(10768, false);
        e<TranscodeType> eVar = (e) super.sizeMultiplier(f);
        AppMethodBeat.o(10768);
        return eVar;
    }

    public e<TranscodeType> a(boolean z) {
        AppMethodBeat.i(10773, false);
        e<TranscodeType> eVar = (e) super.useUnlimitedSourceGeneratorsPool(z);
        AppMethodBeat.o(10773);
        return eVar;
    }

    public e<TranscodeType> b(boolean z) {
        AppMethodBeat.i(10776, false);
        e<TranscodeType> eVar = (e) super.useAnimationPool(z);
        AppMethodBeat.o(10776);
        return eVar;
    }

    public e<TranscodeType> c(boolean z) {
        AppMethodBeat.i(10779, false);
        e<TranscodeType> eVar = (e) super.onlyRetrieveFromCache(z);
        AppMethodBeat.o(10779);
        return eVar;
    }

    public e<TranscodeType> a(DiskCacheStrategy diskCacheStrategy) {
        AppMethodBeat.i(10784, false);
        e<TranscodeType> eVar = (e) super.diskCacheStrategy(diskCacheStrategy);
        AppMethodBeat.o(10784);
        return eVar;
    }

    public e<TranscodeType> a(Priority priority) {
        AppMethodBeat.i(10788, false);
        e<TranscodeType> eVar = (e) super.priority(priority);
        AppMethodBeat.o(10788);
        return eVar;
    }

    public e<TranscodeType> a(Drawable drawable) {
        AppMethodBeat.i(10794, false);
        e<TranscodeType> eVar = (e) super.placeholder(drawable);
        AppMethodBeat.o(10794);
        return eVar;
    }

    public e<TranscodeType> a(int i) {
        AppMethodBeat.i(10797, false);
        e<TranscodeType> eVar = (e) super.placeholder(i);
        AppMethodBeat.o(10797);
        return eVar;
    }

    public e<TranscodeType> b(Drawable drawable) {
        AppMethodBeat.i(10801, false);
        e<TranscodeType> eVar = (e) super.fallback(drawable);
        AppMethodBeat.o(10801);
        return eVar;
    }

    public e<TranscodeType> b(int i) {
        AppMethodBeat.i(10804, false);
        e<TranscodeType> eVar = (e) super.fallback(i);
        AppMethodBeat.o(10804);
        return eVar;
    }

    public e<TranscodeType> c(Drawable drawable) {
        AppMethodBeat.i(10811, false);
        e<TranscodeType> eVar = (e) super.error(drawable);
        AppMethodBeat.o(10811);
        return eVar;
    }

    public e<TranscodeType> c(int i) {
        AppMethodBeat.i(10815, false);
        e<TranscodeType> eVar = (e) super.error(i);
        AppMethodBeat.o(10815);
        return eVar;
    }

    public e<TranscodeType> a(Resources.Theme theme) {
        AppMethodBeat.i(10819, false);
        e<TranscodeType> eVar = (e) super.theme(theme);
        AppMethodBeat.o(10819);
        return eVar;
    }

    public e<TranscodeType> d(boolean z) {
        AppMethodBeat.i(10822, false);
        e<TranscodeType> eVar = (e) super.skipMemoryCache(z);
        AppMethodBeat.o(10822);
        return eVar;
    }

    public e<TranscodeType> a(int i, int i2) {
        AppMethodBeat.i(10825, false);
        e<TranscodeType> eVar = (e) super.override(i, i2);
        AppMethodBeat.o(10825);
        return eVar;
    }

    public e<TranscodeType> d(int i) {
        AppMethodBeat.i(10828, false);
        e<TranscodeType> eVar = (e) super.override(i);
        AppMethodBeat.o(10828);
        return eVar;
    }

    public e<TranscodeType> a(Key key) {
        AppMethodBeat.i(10831, false);
        e<TranscodeType> eVar = (e) super.signature(key);
        AppMethodBeat.o(10831);
        return eVar;
    }

    public <Y> e<TranscodeType> a(Option<Y> option, Y y) {
        AppMethodBeat.i(TbsReaderView.READER_CHANNEL_TXT_ID, false);
        e<TranscodeType> eVar = (e) super.set(option, y);
        AppMethodBeat.o(TbsReaderView.READER_CHANNEL_TXT_ID);
        return eVar;
    }

    public e<TranscodeType> a(Class<?> cls) {
        AppMethodBeat.i(10840, false);
        e<TranscodeType> eVar = (e) super.decode(cls);
        AppMethodBeat.o(10840);
        return eVar;
    }

    public e<TranscodeType> a(Bitmap.CompressFormat compressFormat) {
        AppMethodBeat.i(10845, false);
        e<TranscodeType> eVar = (e) super.encodeFormat(compressFormat);
        AppMethodBeat.o(10845);
        return eVar;
    }

    public e<TranscodeType> e(int i) {
        AppMethodBeat.i(10848, false);
        e<TranscodeType> eVar = (e) super.encodeQuality(i);
        AppMethodBeat.o(10848);
        return eVar;
    }

    public e<TranscodeType> a(long j) {
        AppMethodBeat.i(10851, false);
        e<TranscodeType> eVar = (e) super.frame(j);
        AppMethodBeat.o(10851);
        return eVar;
    }

    public e<TranscodeType> a(DecodeFormat decodeFormat) {
        AppMethodBeat.i(10854, false);
        e<TranscodeType> eVar = (e) super.format(decodeFormat);
        AppMethodBeat.o(10854);
        return eVar;
    }

    public e<TranscodeType> b() {
        AppMethodBeat.i(10857, false);
        e<TranscodeType> eVar = (e) super.disallowHardwareConfig();
        AppMethodBeat.o(10857);
        return eVar;
    }

    public e<TranscodeType> a(DownsampleStrategy downsampleStrategy) {
        AppMethodBeat.i(10859, false);
        e<TranscodeType> eVar = (e) super.downsample(downsampleStrategy);
        AppMethodBeat.o(10859);
        return eVar;
    }

    public e<TranscodeType> f(int i) {
        AppMethodBeat.i(10863, false);
        e<TranscodeType> eVar = (e) super.timeout(i);
        AppMethodBeat.o(10863);
        return eVar;
    }

    public e<TranscodeType> c() {
        AppMethodBeat.i(10866, false);
        e<TranscodeType> eVar = (e) super.optionalCenterCrop();
        AppMethodBeat.o(10866);
        return eVar;
    }

    public e<TranscodeType> d() {
        AppMethodBeat.i(10869, false);
        e<TranscodeType> eVar = (e) super.centerCrop();
        AppMethodBeat.o(10869);
        return eVar;
    }

    public e<TranscodeType> e() {
        AppMethodBeat.i(10872, false);
        e<TranscodeType> eVar = (e) super.optionalFitCenter();
        AppMethodBeat.o(10872);
        return eVar;
    }

    public e<TranscodeType> f() {
        AppMethodBeat.i(10875, false);
        e<TranscodeType> eVar = (e) super.fitCenter();
        AppMethodBeat.o(10875);
        return eVar;
    }

    public e<TranscodeType> g() {
        AppMethodBeat.i(10876, false);
        e<TranscodeType> eVar = (e) super.optionalCenterInside();
        AppMethodBeat.o(10876);
        return eVar;
    }

    public e<TranscodeType> h() {
        AppMethodBeat.i(10880, false);
        e<TranscodeType> eVar = (e) super.centerInside();
        AppMethodBeat.o(10880);
        return eVar;
    }

    public e<TranscodeType> i() {
        AppMethodBeat.i(10883, false);
        e<TranscodeType> eVar = (e) super.optionalCircleCrop();
        AppMethodBeat.o(10883);
        return eVar;
    }

    public e<TranscodeType> j() {
        AppMethodBeat.i(10885, false);
        e<TranscodeType> eVar = (e) super.circleCrop();
        AppMethodBeat.o(10885);
        return eVar;
    }

    public e<TranscodeType> a(Transformation<Bitmap> transformation) {
        AppMethodBeat.i(10890, false);
        e<TranscodeType> eVar = (e) super.transform(transformation);
        AppMethodBeat.o(10890);
        return eVar;
    }

    public e<TranscodeType> a(Transformation<Bitmap>... transformationArr) {
        AppMethodBeat.i(10894, false);
        e<TranscodeType> eVar = (e) super.transform(transformationArr);
        AppMethodBeat.o(10894);
        return eVar;
    }

    @Deprecated
    public e<TranscodeType> b(Transformation<Bitmap>... transformationArr) {
        AppMethodBeat.i(10898, false);
        e<TranscodeType> eVar = (e) super.transforms(transformationArr);
        AppMethodBeat.o(10898);
        return eVar;
    }

    public e<TranscodeType> b(Transformation<Bitmap> transformation) {
        AppMethodBeat.i(10908, false);
        e<TranscodeType> eVar = (e) super.optionalTransform(transformation);
        AppMethodBeat.o(10908);
        return eVar;
    }

    public <Y> e<TranscodeType> a(Class<Y> cls, Transformation<Y> transformation) {
        AppMethodBeat.i(10912, false);
        e<TranscodeType> eVar = (e) super.optionalTransform(cls, transformation);
        AppMethodBeat.o(10912);
        return eVar;
    }

    public <Y> e<TranscodeType> b(Class<Y> cls, Transformation<Y> transformation) {
        AppMethodBeat.i(10915, false);
        e<TranscodeType> eVar = (e) super.transform(cls, transformation);
        AppMethodBeat.o(10915);
        return eVar;
    }

    public e<TranscodeType> k() {
        AppMethodBeat.i(10920, false);
        e<TranscodeType> eVar = (e) super.dontTransform();
        AppMethodBeat.o(10920);
        return eVar;
    }

    public e<TranscodeType> l() {
        AppMethodBeat.i(10925, false);
        e<TranscodeType> eVar = (e) super.dontAnimate();
        AppMethodBeat.o(10925);
        return eVar;
    }

    public e<TranscodeType> a(BaseRequestOptions<?> baseRequestOptions) {
        AppMethodBeat.i(10928, false);
        e<TranscodeType> eVar = (e) super.apply(baseRequestOptions);
        AppMethodBeat.o(10928);
        return eVar;
    }

    public e<TranscodeType> a(TransitionOptions<?, ? super TranscodeType> transitionOptions) {
        AppMethodBeat.i(10932, false);
        e<TranscodeType> eVar = (e) super.transition(transitionOptions);
        AppMethodBeat.o(10932);
        return eVar;
    }

    public e<TranscodeType> a(RequestListener<TranscodeType> requestListener) {
        AppMethodBeat.i(10933, false);
        e<TranscodeType> eVar = (e) super.listener(requestListener);
        AppMethodBeat.o(10933);
        return eVar;
    }

    public e<TranscodeType> b(RequestListener<TranscodeType> requestListener) {
        AppMethodBeat.i(10935, false);
        e<TranscodeType> eVar = (e) super.addListener(requestListener);
        AppMethodBeat.o(10935);
        return eVar;
    }

    public e<TranscodeType> a(RequestBuilder<TranscodeType> requestBuilder) {
        AppMethodBeat.i(10936, false);
        e<TranscodeType> eVar = (e) super.error(requestBuilder);
        AppMethodBeat.o(10936);
        return eVar;
    }

    public e<TranscodeType> b(RequestBuilder<TranscodeType> requestBuilder) {
        AppMethodBeat.i(10937, false);
        e<TranscodeType> eVar = (e) super.thumbnail(requestBuilder);
        AppMethodBeat.o(10937);
        return eVar;
    }

    @SafeVarargs
    public final e<TranscodeType> a(RequestBuilder<TranscodeType>... requestBuilderArr) {
        AppMethodBeat.i(10939, false);
        e<TranscodeType> eVar = (e) super.thumbnail(requestBuilderArr);
        AppMethodBeat.o(10939);
        return eVar;
    }

    public e<TranscodeType> b(float f) {
        AppMethodBeat.i(10942, false);
        e<TranscodeType> eVar = (e) super.thumbnail(f);
        AppMethodBeat.o(10942);
        return eVar;
    }

    public e<TranscodeType> a(Object obj) {
        AppMethodBeat.i(10944, false);
        e<TranscodeType> eVar = (e) super.load(obj);
        AppMethodBeat.o(10944);
        return eVar;
    }

    public e<TranscodeType> a(Bitmap bitmap) {
        AppMethodBeat.i(10946, false);
        e<TranscodeType> eVar = (e) super.load(bitmap);
        AppMethodBeat.o(10946);
        return eVar;
    }

    public e<TranscodeType> d(Drawable drawable) {
        AppMethodBeat.i(10948, false);
        e<TranscodeType> eVar = (e) super.load(drawable);
        AppMethodBeat.o(10948);
        return eVar;
    }

    public e<TranscodeType> a(String str) {
        AppMethodBeat.i(10950, false);
        e<TranscodeType> eVar = (e) super.load(str);
        AppMethodBeat.o(10950);
        return eVar;
    }

    public e<TranscodeType> a(Uri uri) {
        AppMethodBeat.i(10952, false);
        e<TranscodeType> eVar = (e) super.load(uri);
        AppMethodBeat.o(10952);
        return eVar;
    }

    public e<TranscodeType> a(File file) {
        AppMethodBeat.i(10954, false);
        e<TranscodeType> eVar = (e) super.load(file);
        AppMethodBeat.o(10954);
        return eVar;
    }

    public e<TranscodeType> a(Integer num) {
        AppMethodBeat.i(10955, false);
        e<TranscodeType> eVar = (e) super.load(num);
        AppMethodBeat.o(10955);
        return eVar;
    }

    @Deprecated
    public e<TranscodeType> a(URL url) {
        AppMethodBeat.i(10956, false);
        e<TranscodeType> eVar = (e) super.load(url);
        AppMethodBeat.o(10956);
        return eVar;
    }

    public e<TranscodeType> a(byte[] bArr) {
        AppMethodBeat.i(10957, false);
        e<TranscodeType> eVar = (e) super.load(bArr);
        AppMethodBeat.o(10957);
        return eVar;
    }

    public e<TranscodeType> m() {
        AppMethodBeat.i(10959, false);
        e<TranscodeType> eVar = (e) super.clone();
        AppMethodBeat.o(10959);
        return eVar;
    }
}
