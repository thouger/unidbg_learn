package cn.missfresh.module.base.common.config;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.Key;
import com.bumptech.glide.load.Option;
import com.bumptech.glide.load.Transformation;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.DownsampleStrategy;
import com.bumptech.glide.request.BaseRequestOptions;
import com.bumptech.glide.request.RequestOptions;

/* compiled from: GlideOptions */
public final class d extends RequestOptions implements Cloneable {
    @Override // com.bumptech.glide.request.BaseRequestOptions
    public /* synthetic */ BaseRequestOptions apply(BaseRequestOptions baseRequestOptions) {
        AppMethodBeat.i(10629, false);
        d a = a(baseRequestOptions);
        AppMethodBeat.o(10629);
        return a;
    }

    @Override // com.bumptech.glide.request.BaseRequestOptions
    public /* synthetic */ BaseRequestOptions autoClone() {
        AppMethodBeat.i(10624, false);
        d n = n();
        AppMethodBeat.o(10624);
        return n;
    }

    @Override // com.bumptech.glide.request.BaseRequestOptions
    public /* synthetic */ BaseRequestOptions centerCrop() {
        AppMethodBeat.i(10674, false);
        d d = d();
        AppMethodBeat.o(10674);
        return d;
    }

    @Override // com.bumptech.glide.request.BaseRequestOptions
    public /* synthetic */ BaseRequestOptions centerInside() {
        AppMethodBeat.i(10660, false);
        d h = h();
        AppMethodBeat.o(10660);
        return h;
    }

    @Override // com.bumptech.glide.request.BaseRequestOptions
    public /* synthetic */ BaseRequestOptions circleCrop() {
        AppMethodBeat.i(10651, false);
        d j = j();
        AppMethodBeat.o(10651);
        return j;
    }

    @Override // com.bumptech.glide.request.BaseRequestOptions
    public /* synthetic */ BaseRequestOptions decode(Class cls) {
        AppMethodBeat.i(10702, false);
        d a = a(cls);
        AppMethodBeat.o(10702);
        return a;
    }

    @Override // com.bumptech.glide.request.BaseRequestOptions
    public /* synthetic */ BaseRequestOptions disallowHardwareConfig() {
        AppMethodBeat.i(10689, false);
        d b = b();
        AppMethodBeat.o(10689);
        return b;
    }

    @Override // com.bumptech.glide.request.BaseRequestOptions
    public /* synthetic */ BaseRequestOptions diskCacheStrategy(DiskCacheStrategy diskCacheStrategy) {
        AppMethodBeat.i(10738, false);
        d a = a(diskCacheStrategy);
        AppMethodBeat.o(10738);
        return a;
    }

    @Override // com.bumptech.glide.request.BaseRequestOptions
    public /* synthetic */ BaseRequestOptions dontAnimate() {
        AppMethodBeat.i(10630, false);
        d l = l();
        AppMethodBeat.o(10630);
        return l;
    }

    @Override // com.bumptech.glide.request.BaseRequestOptions
    public /* synthetic */ BaseRequestOptions dontTransform() {
        AppMethodBeat.i(10632, false);
        d k = k();
        AppMethodBeat.o(10632);
        return k;
    }

    @Override // com.bumptech.glide.request.BaseRequestOptions
    public /* synthetic */ BaseRequestOptions downsample(DownsampleStrategy downsampleStrategy) {
        AppMethodBeat.i(10686, false);
        d a = a(downsampleStrategy);
        AppMethodBeat.o(10686);
        return a;
    }

    @Override // com.bumptech.glide.request.BaseRequestOptions
    public /* synthetic */ BaseRequestOptions encodeFormat(Bitmap.CompressFormat compressFormat) {
        AppMethodBeat.i(10700, false);
        d a = a(compressFormat);
        AppMethodBeat.o(10700);
        return a;
    }

    @Override // com.bumptech.glide.request.BaseRequestOptions
    public /* synthetic */ BaseRequestOptions encodeQuality(int i) {
        AppMethodBeat.i(10696, false);
        d e = e(i);
        AppMethodBeat.o(10696);
        return e;
    }

    @Override // com.bumptech.glide.request.BaseRequestOptions
    public /* synthetic */ BaseRequestOptions error(int i) {
        AppMethodBeat.i(10722, false);
        d c = c(i);
        AppMethodBeat.o(10722);
        return c;
    }

    @Override // com.bumptech.glide.request.BaseRequestOptions
    public /* synthetic */ BaseRequestOptions error(Drawable drawable) {
        AppMethodBeat.i(10724, false);
        d c = c(drawable);
        AppMethodBeat.o(10724);
        return c;
    }

    @Override // com.bumptech.glide.request.BaseRequestOptions
    public /* synthetic */ BaseRequestOptions fallback(int i) {
        AppMethodBeat.i(10726, false);
        d b = b(i);
        AppMethodBeat.o(10726);
        return b;
    }

    @Override // com.bumptech.glide.request.BaseRequestOptions
    public /* synthetic */ BaseRequestOptions fallback(Drawable drawable) {
        AppMethodBeat.i(10727, false);
        d b = b(drawable);
        AppMethodBeat.o(10727);
        return b;
    }

    @Override // com.bumptech.glide.request.BaseRequestOptions
    public /* synthetic */ BaseRequestOptions fitCenter() {
        AppMethodBeat.i(10665, false);
        d f = f();
        AppMethodBeat.o(10665);
        return f;
    }

    @Override // com.bumptech.glide.request.BaseRequestOptions
    public /* synthetic */ BaseRequestOptions format(DecodeFormat decodeFormat) {
        AppMethodBeat.i(10691, false);
        d a = a(decodeFormat);
        AppMethodBeat.o(10691);
        return a;
    }

    @Override // com.bumptech.glide.request.BaseRequestOptions
    public /* synthetic */ BaseRequestOptions frame(long j) {
        AppMethodBeat.i(10693, false);
        d a = a(j);
        AppMethodBeat.o(10693);
        return a;
    }

    @Override // com.bumptech.glide.request.BaseRequestOptions
    public /* synthetic */ BaseRequestOptions lock() {
        AppMethodBeat.i(10627, false);
        d m = m();
        AppMethodBeat.o(10627);
        return m;
    }

    @Override // com.bumptech.glide.request.BaseRequestOptions
    public /* synthetic */ BaseRequestOptions onlyRetrieveFromCache(boolean z) {
        AppMethodBeat.i(10741, false);
        d c = c(z);
        AppMethodBeat.o(10741);
        return c;
    }

    @Override // com.bumptech.glide.request.BaseRequestOptions
    public /* synthetic */ BaseRequestOptions optionalCenterCrop() {
        AppMethodBeat.i(10677, false);
        d c = c();
        AppMethodBeat.o(10677);
        return c;
    }

    @Override // com.bumptech.glide.request.BaseRequestOptions
    public /* synthetic */ BaseRequestOptions optionalCenterInside() {
        AppMethodBeat.i(10663, false);
        d g = g();
        AppMethodBeat.o(10663);
        return g;
    }

    @Override // com.bumptech.glide.request.BaseRequestOptions
    public /* synthetic */ BaseRequestOptions optionalCircleCrop() {
        AppMethodBeat.i(10657, false);
        d i = i();
        AppMethodBeat.o(10657);
        return i;
    }

    @Override // com.bumptech.glide.request.BaseRequestOptions
    public /* synthetic */ BaseRequestOptions optionalFitCenter() {
        AppMethodBeat.i(10668, false);
        d e = e();
        AppMethodBeat.o(10668);
        return e;
    }

    @Override // com.bumptech.glide.request.BaseRequestOptions
    public /* synthetic */ BaseRequestOptions optionalTransform(Transformation transformation) {
        AppMethodBeat.i(10641, false);
        d b = b(transformation);
        AppMethodBeat.o(10641);
        return b;
    }

    @Override // com.bumptech.glide.request.BaseRequestOptions
    public /* synthetic */ BaseRequestOptions optionalTransform(Class cls, Transformation transformation) {
        AppMethodBeat.i(10638, false);
        d a = a(cls, transformation);
        AppMethodBeat.o(10638);
        return a;
    }

    @Override // com.bumptech.glide.request.BaseRequestOptions
    public /* synthetic */ BaseRequestOptions override(int i) {
        AppMethodBeat.i(10712, false);
        d d = d(i);
        AppMethodBeat.o(10712);
        return d;
    }

    @Override // com.bumptech.glide.request.BaseRequestOptions
    public /* synthetic */ BaseRequestOptions override(int i, int i2) {
        AppMethodBeat.i(10715, false);
        d a = a(i, i2);
        AppMethodBeat.o(10715);
        return a;
    }

    @Override // com.bumptech.glide.request.BaseRequestOptions
    public /* synthetic */ BaseRequestOptions placeholder(int i) {
        AppMethodBeat.i(10730, false);
        d a = a(i);
        AppMethodBeat.o(10730);
        return a;
    }

    @Override // com.bumptech.glide.request.BaseRequestOptions
    public /* synthetic */ BaseRequestOptions placeholder(Drawable drawable) {
        AppMethodBeat.i(10733, false);
        d a = a(drawable);
        AppMethodBeat.o(10733);
        return a;
    }

    @Override // com.bumptech.glide.request.BaseRequestOptions
    public /* synthetic */ BaseRequestOptions priority(Priority priority) {
        AppMethodBeat.i(10736, false);
        d a = a(priority);
        AppMethodBeat.o(10736);
        return a;
    }

    @Override // com.bumptech.glide.request.BaseRequestOptions
    public /* synthetic */ BaseRequestOptions set(Option option, Object obj) {
        AppMethodBeat.i(10705, false);
        d a = a((Option<Option>) option, (Option) obj);
        AppMethodBeat.o(10705);
        return a;
    }

    @Override // com.bumptech.glide.request.BaseRequestOptions
    public /* synthetic */ BaseRequestOptions signature(Key key) {
        AppMethodBeat.i(10710, false);
        d a = a(key);
        AppMethodBeat.o(10710);
        return a;
    }

    @Override // com.bumptech.glide.request.BaseRequestOptions
    public /* synthetic */ BaseRequestOptions sizeMultiplier(float f) {
        AppMethodBeat.i(10750, false);
        d a = a(f);
        AppMethodBeat.o(10750);
        return a;
    }

    @Override // com.bumptech.glide.request.BaseRequestOptions
    public /* synthetic */ BaseRequestOptions skipMemoryCache(boolean z) {
        AppMethodBeat.i(10718, false);
        d d = d(z);
        AppMethodBeat.o(10718);
        return d;
    }

    @Override // com.bumptech.glide.request.BaseRequestOptions
    public /* synthetic */ BaseRequestOptions theme(Resources.Theme theme) {
        AppMethodBeat.i(10719, false);
        d a = a(theme);
        AppMethodBeat.o(10719);
        return a;
    }

    @Override // com.bumptech.glide.request.BaseRequestOptions
    public /* synthetic */ BaseRequestOptions timeout(int i) {
        AppMethodBeat.i(10680, false);
        d f = f(i);
        AppMethodBeat.o(10680);
        return f;
    }

    @Override // com.bumptech.glide.request.BaseRequestOptions
    public /* synthetic */ BaseRequestOptions transform(Transformation transformation) {
        AppMethodBeat.i(10649, false);
        d a = a((Transformation<Bitmap>) transformation);
        AppMethodBeat.o(10649);
        return a;
    }

    @Override // com.bumptech.glide.request.BaseRequestOptions
    public /* synthetic */ BaseRequestOptions transform(Class cls, Transformation transformation) {
        AppMethodBeat.i(10635, false);
        d b = b(cls, transformation);
        AppMethodBeat.o(10635);
        return b;
    }

    @Override // com.bumptech.glide.request.BaseRequestOptions
    @SafeVarargs
    public /* synthetic */ BaseRequestOptions transform(Transformation[] transformationArr) {
        AppMethodBeat.i(10646, false);
        d a = a(transformationArr);
        AppMethodBeat.o(10646);
        return a;
    }

    @Override // com.bumptech.glide.request.BaseRequestOptions
    @SafeVarargs
    @Deprecated
    public /* synthetic */ BaseRequestOptions transforms(Transformation[] transformationArr) {
        AppMethodBeat.i(10644, false);
        d b = b(transformationArr);
        AppMethodBeat.o(10644);
        return b;
    }

    @Override // com.bumptech.glide.request.BaseRequestOptions
    public /* synthetic */ BaseRequestOptions useAnimationPool(boolean z) {
        AppMethodBeat.i(10745, false);
        d b = b(z);
        AppMethodBeat.o(10745);
        return b;
    }

    @Override // com.bumptech.glide.request.BaseRequestOptions
    public /* synthetic */ BaseRequestOptions useUnlimitedSourceGeneratorsPool(boolean z) {
        AppMethodBeat.i(10747, false);
        d a = a(z);
        AppMethodBeat.o(10747);
        return a;
    }

    public d a(float f) {
        AppMethodBeat.i(10494, false);
        d dVar = (d) super.sizeMultiplier(f);
        AppMethodBeat.o(10494);
        return dVar;
    }

    public d a(boolean z) {
        AppMethodBeat.i(10498, false);
        d dVar = (d) super.useUnlimitedSourceGeneratorsPool(z);
        AppMethodBeat.o(10498);
        return dVar;
    }

    public d b(boolean z) {
        AppMethodBeat.i(10504, false);
        d dVar = (d) super.useAnimationPool(z);
        AppMethodBeat.o(10504);
        return dVar;
    }

    public d c(boolean z) {
        AppMethodBeat.i(10508, false);
        d dVar = (d) super.onlyRetrieveFromCache(z);
        AppMethodBeat.o(10508);
        return dVar;
    }

    public d a(DiskCacheStrategy diskCacheStrategy) {
        AppMethodBeat.i(10511, false);
        d dVar = (d) super.diskCacheStrategy(diskCacheStrategy);
        AppMethodBeat.o(10511);
        return dVar;
    }

    public d a(Priority priority) {
        AppMethodBeat.i(10513, false);
        d dVar = (d) super.priority(priority);
        AppMethodBeat.o(10513);
        return dVar;
    }

    public d a(Drawable drawable) {
        AppMethodBeat.i(10516, false);
        d dVar = (d) super.placeholder(drawable);
        AppMethodBeat.o(10516);
        return dVar;
    }

    public d a(int i) {
        AppMethodBeat.i(10519, false);
        d dVar = (d) super.placeholder(i);
        AppMethodBeat.o(10519);
        return dVar;
    }

    public d b(Drawable drawable) {
        AppMethodBeat.i(10522, false);
        d dVar = (d) super.fallback(drawable);
        AppMethodBeat.o(10522);
        return dVar;
    }

    public d b(int i) {
        AppMethodBeat.i(10525, false);
        d dVar = (d) super.fallback(i);
        AppMethodBeat.o(10525);
        return dVar;
    }

    public d c(Drawable drawable) {
        AppMethodBeat.i(10528, false);
        d dVar = (d) super.error(drawable);
        AppMethodBeat.o(10528);
        return dVar;
    }

    public d c(int i) {
        AppMethodBeat.i(10532, false);
        d dVar = (d) super.error(i);
        AppMethodBeat.o(10532);
        return dVar;
    }

    public d a(Resources.Theme theme) {
        AppMethodBeat.i(10537, false);
        d dVar = (d) super.theme(theme);
        AppMethodBeat.o(10537);
        return dVar;
    }

    public d d(boolean z) {
        AppMethodBeat.i(10539, false);
        d dVar = (d) super.skipMemoryCache(z);
        AppMethodBeat.o(10539);
        return dVar;
    }

    public d a(int i, int i2) {
        AppMethodBeat.i(10542, false);
        d dVar = (d) super.override(i, i2);
        AppMethodBeat.o(10542);
        return dVar;
    }

    public d d(int i) {
        AppMethodBeat.i(10544, false);
        d dVar = (d) super.override(i);
        AppMethodBeat.o(10544);
        return dVar;
    }

    public d a(Key key) {
        AppMethodBeat.i(10546, false);
        d dVar = (d) super.signature(key);
        AppMethodBeat.o(10546);
        return dVar;
    }

    public d a() {
        AppMethodBeat.i(10548, false);
        d dVar = (d) super.clone();
        AppMethodBeat.o(10548);
        return dVar;
    }

    public <Y> d a(Option<Y> option, Y y) {
        AppMethodBeat.i(10553, false);
        d dVar = (d) super.set(option, y);
        AppMethodBeat.o(10553);
        return dVar;
    }

    public d a(Class<?> cls) {
        AppMethodBeat.i(10556, false);
        d dVar = (d) super.decode(cls);
        AppMethodBeat.o(10556);
        return dVar;
    }

    public d a(Bitmap.CompressFormat compressFormat) {
        AppMethodBeat.i(10557, false);
        d dVar = (d) super.encodeFormat(compressFormat);
        AppMethodBeat.o(10557);
        return dVar;
    }

    public d e(int i) {
        AppMethodBeat.i(10559, false);
        d dVar = (d) super.encodeQuality(i);
        AppMethodBeat.o(10559);
        return dVar;
    }

    public d a(long j) {
        AppMethodBeat.i(10560, false);
        d dVar = (d) super.frame(j);
        AppMethodBeat.o(10560);
        return dVar;
    }

    public d a(DecodeFormat decodeFormat) {
        AppMethodBeat.i(10563, false);
        d dVar = (d) super.format(decodeFormat);
        AppMethodBeat.o(10563);
        return dVar;
    }

    public d b() {
        AppMethodBeat.i(10565, false);
        d dVar = (d) super.disallowHardwareConfig();
        AppMethodBeat.o(10565);
        return dVar;
    }

    public d a(DownsampleStrategy downsampleStrategy) {
        AppMethodBeat.i(10568, false);
        d dVar = (d) super.downsample(downsampleStrategy);
        AppMethodBeat.o(10568);
        return dVar;
    }

    public d f(int i) {
        AppMethodBeat.i(10570, false);
        d dVar = (d) super.timeout(i);
        AppMethodBeat.o(10570);
        return dVar;
    }

    public d c() {
        AppMethodBeat.i(10572, false);
        d dVar = (d) super.optionalCenterCrop();
        AppMethodBeat.o(10572);
        return dVar;
    }

    public d d() {
        AppMethodBeat.i(10573, false);
        d dVar = (d) super.centerCrop();
        AppMethodBeat.o(10573);
        return dVar;
    }

    public d e() {
        AppMethodBeat.i(10575, false);
        d dVar = (d) super.optionalFitCenter();
        AppMethodBeat.o(10575);
        return dVar;
    }

    public d f() {
        AppMethodBeat.i(10579, false);
        d dVar = (d) super.fitCenter();
        AppMethodBeat.o(10579);
        return dVar;
    }

    public d g() {
        AppMethodBeat.i(10581, false);
        d dVar = (d) super.optionalCenterInside();
        AppMethodBeat.o(10581);
        return dVar;
    }

    public d h() {
        AppMethodBeat.i(10584, false);
        d dVar = (d) super.centerInside();
        AppMethodBeat.o(10584);
        return dVar;
    }

    public d i() {
        AppMethodBeat.i(10588, false);
        d dVar = (d) super.optionalCircleCrop();
        AppMethodBeat.o(10588);
        return dVar;
    }

    public d j() {
        AppMethodBeat.i(10593, false);
        d dVar = (d) super.circleCrop();
        AppMethodBeat.o(10593);
        return dVar;
    }

    public d a(Transformation<Bitmap> transformation) {
        AppMethodBeat.i(10596, false);
        d dVar = (d) super.transform(transformation);
        AppMethodBeat.o(10596);
        return dVar;
    }

    @SafeVarargs
    public final d a(Transformation<Bitmap>... transformationArr) {
        AppMethodBeat.i(10599, false);
        d dVar = (d) super.transform(transformationArr);
        AppMethodBeat.o(10599);
        return dVar;
    }

    @SafeVarargs
    @Deprecated
    public final d b(Transformation<Bitmap>... transformationArr) {
        AppMethodBeat.i(10603, false);
        d dVar = (d) super.transforms(transformationArr);
        AppMethodBeat.o(10603);
        return dVar;
    }

    public d b(Transformation<Bitmap> transformation) {
        AppMethodBeat.i(10605, false);
        d dVar = (d) super.optionalTransform(transformation);
        AppMethodBeat.o(10605);
        return dVar;
    }

    public <Y> d a(Class<Y> cls, Transformation<Y> transformation) {
        AppMethodBeat.i(10608, false);
        d dVar = (d) super.optionalTransform(cls, transformation);
        AppMethodBeat.o(10608);
        return dVar;
    }

    public <Y> d b(Class<Y> cls, Transformation<Y> transformation) {
        AppMethodBeat.i(10611, false);
        d dVar = (d) super.transform(cls, transformation);
        AppMethodBeat.o(10611);
        return dVar;
    }

    public d k() {
        AppMethodBeat.i(10614, false);
        d dVar = (d) super.dontTransform();
        AppMethodBeat.o(10614);
        return dVar;
    }

    public d l() {
        AppMethodBeat.i(10617, false);
        d dVar = (d) super.dontAnimate();
        AppMethodBeat.o(10617);
        return dVar;
    }

    public d a(BaseRequestOptions<?> baseRequestOptions) {
        AppMethodBeat.i(10619, false);
        d dVar = (d) super.apply(baseRequestOptions);
        AppMethodBeat.o(10619);
        return dVar;
    }

    public d m() {
        AppMethodBeat.i(10620, false);
        d dVar = (d) super.lock();
        AppMethodBeat.o(10620);
        return dVar;
    }

    public d n() {
        AppMethodBeat.i(10621, false);
        d dVar = (d) super.autoClone();
        AppMethodBeat.o(10621);
        return dVar;
    }
}
