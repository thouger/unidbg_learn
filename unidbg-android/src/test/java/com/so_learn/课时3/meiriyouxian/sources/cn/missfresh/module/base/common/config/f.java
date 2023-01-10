package cn.missfresh.module.base.common.config;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.load.resource.gif.GifDrawable;
import com.bumptech.glide.manager.Lifecycle;
import com.bumptech.glide.manager.RequestManagerTreeNode;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.tencent.connect.common.Constants;
import java.io.File;
import java.net.URL;

/* compiled from: GlideRequests */
public class f extends RequestManager {
    @Override // com.bumptech.glide.RequestManager
    public /* synthetic */ RequestManager addDefaultRequestListener(RequestListener requestListener) {
        AppMethodBeat.i(11143, false);
        f a = a((RequestListener<Object>) requestListener);
        AppMethodBeat.o(11143);
        return a;
    }

    @Override // com.bumptech.glide.RequestManager
    public /* synthetic */ RequestManager applyDefaultRequestOptions(RequestOptions requestOptions) {
        AppMethodBeat.i(11146, false);
        f a = a(requestOptions);
        AppMethodBeat.o(11146);
        return a;
    }

    @Override // com.bumptech.glide.RequestManager
    public /* synthetic */ RequestBuilder as(Class cls) {
        AppMethodBeat.i(11117, false);
        e a = a(cls);
        AppMethodBeat.o(11117);
        return a;
    }

    @Override // com.bumptech.glide.RequestManager
    public /* synthetic */ RequestBuilder asBitmap() {
        AppMethodBeat.i(11141, false);
        e<Bitmap> a = a();
        AppMethodBeat.o(11141);
        return a;
    }

    @Override // com.bumptech.glide.RequestManager
    public /* synthetic */ RequestBuilder asDrawable() {
        AppMethodBeat.i(11138, false);
        e<Drawable> c = c();
        AppMethodBeat.o(11138);
        return c;
    }

    @Override // com.bumptech.glide.RequestManager
    public /* synthetic */ RequestBuilder asFile() {
        AppMethodBeat.i(11119, false);
        e<File> e = e();
        AppMethodBeat.o(11119);
        return e;
    }

    @Override // com.bumptech.glide.RequestManager
    public /* synthetic */ RequestBuilder asGif() {
        AppMethodBeat.i(11139, false);
        e<GifDrawable> b = b();
        AppMethodBeat.o(11139);
        return b;
    }

    @Override // com.bumptech.glide.RequestManager
    public /* synthetic */ RequestBuilder download(Object obj) {
        AppMethodBeat.i(11121, false);
        e<File> b = b(obj);
        AppMethodBeat.o(11121);
        return b;
    }

    @Override // com.bumptech.glide.RequestManager
    public /* synthetic */ RequestBuilder downloadOnly() {
        AppMethodBeat.i(11122, false);
        e<File> d = d();
        AppMethodBeat.o(11122);
        return d;
    }

    @Override // com.bumptech.glide.RequestManager
    public /* synthetic */ RequestManager setDefaultRequestOptions(RequestOptions requestOptions) {
        AppMethodBeat.i(11144, false);
        f b = b(requestOptions);
        AppMethodBeat.o(11144);
        return b;
    }

    public f(Glide glide, Lifecycle lifecycle, RequestManagerTreeNode requestManagerTreeNode, Context context) {
        super(glide, lifecycle, requestManagerTreeNode, context);
    }

    public <ResourceType> e<ResourceType> a(Class<ResourceType> cls) {
        AppMethodBeat.i(11092, false);
        e<ResourceType> eVar = new e<>(this.glide, this, cls, this.context);
        AppMethodBeat.o(11092);
        return eVar;
    }

    public synchronized f a(RequestOptions requestOptions) {
        f fVar;
        AppMethodBeat.i(11093, false);
        fVar = (f) super.applyDefaultRequestOptions(requestOptions);
        AppMethodBeat.o(11093);
        return fVar;
    }

    public synchronized f b(RequestOptions requestOptions) {
        f fVar;
        AppMethodBeat.i(11094, false);
        fVar = (f) super.setDefaultRequestOptions(requestOptions);
        AppMethodBeat.o(11094);
        return fVar;
    }

    public f a(RequestListener<Object> requestListener) {
        AppMethodBeat.i(11095, false);
        f fVar = (f) super.addDefaultRequestListener(requestListener);
        AppMethodBeat.o(11095);
        return fVar;
    }

    public e<Bitmap> a() {
        AppMethodBeat.i(11096, false);
        e<Bitmap> eVar = (e) super.asBitmap();
        AppMethodBeat.o(11096);
        return eVar;
    }

    public e<GifDrawable> b() {
        AppMethodBeat.i(11098, false);
        e<GifDrawable> eVar = (e) super.asGif();
        AppMethodBeat.o(11098);
        return eVar;
    }

    public e<Drawable> c() {
        AppMethodBeat.i(11100, false);
        e<Drawable> eVar = (e) super.asDrawable();
        AppMethodBeat.o(11100);
        return eVar;
    }

    public e<Drawable> a(Bitmap bitmap) {
        AppMethodBeat.i(Constants.REQUEST_AVATER, false);
        e<Drawable> eVar = (e) super.load(bitmap);
        AppMethodBeat.o(Constants.REQUEST_AVATER);
        return eVar;
    }

    public e<Drawable> a(Drawable drawable) {
        AppMethodBeat.i(Constants.REQUEST_OLD_SHARE, false);
        e<Drawable> eVar = (e) super.load(drawable);
        AppMethodBeat.o(Constants.REQUEST_OLD_SHARE);
        return eVar;
    }

    public e<Drawable> a(String str) {
        AppMethodBeat.i(Constants.REQUEST_OLD_QZSHARE, false);
        e<Drawable> eVar = (e) super.load(str);
        AppMethodBeat.o(Constants.REQUEST_OLD_QZSHARE);
        return eVar;
    }

    public e<Drawable> a(Uri uri) {
        AppMethodBeat.i(Constants.REQUEST_SOCIAL_API, false);
        e<Drawable> eVar = (e) super.load(uri);
        AppMethodBeat.o(Constants.REQUEST_SOCIAL_API);
        return eVar;
    }

    public e<Drawable> a(File file) {
        AppMethodBeat.i(Constants.REQUEST_SOCIAL_H5, false);
        e<Drawable> eVar = (e) super.load(file);
        AppMethodBeat.o(Constants.REQUEST_SOCIAL_H5);
        return eVar;
    }

    public e<Drawable> a(Integer num) {
        AppMethodBeat.i(11108, false);
        e<Drawable> eVar = (e) super.load(num);
        AppMethodBeat.o(11108);
        return eVar;
    }

    @Deprecated
    public e<Drawable> a(URL url) {
        AppMethodBeat.i(11110, false);
        e<Drawable> eVar = (e) super.load(url);
        AppMethodBeat.o(11110);
        return eVar;
    }

    public e<Drawable> a(byte[] bArr) {
        AppMethodBeat.i(11111, false);
        e<Drawable> eVar = (e) super.load(bArr);
        AppMethodBeat.o(11111);
        return eVar;
    }

    public e<Drawable> a(Object obj) {
        AppMethodBeat.i(11112, false);
        e<Drawable> eVar = (e) super.load(obj);
        AppMethodBeat.o(11112);
        return eVar;
    }

    public e<File> d() {
        AppMethodBeat.i(11113, false);
        e<File> eVar = (e) super.downloadOnly();
        AppMethodBeat.o(11113);
        return eVar;
    }

    public e<File> b(Object obj) {
        AppMethodBeat.i(11114, false);
        e<File> eVar = (e) super.download(obj);
        AppMethodBeat.o(11114);
        return eVar;
    }

    public e<File> e() {
        AppMethodBeat.i(11115, false);
        e<File> eVar = (e) super.asFile();
        AppMethodBeat.o(11115);
        return eVar;
    }

    /* access modifiers changed from: protected */
    @Override // com.bumptech.glide.RequestManager
    public void setRequestOptions(RequestOptions requestOptions) {
        AppMethodBeat.i(11116, false);
        if (requestOptions instanceof d) {
            super.setRequestOptions(requestOptions);
        } else {
            super.setRequestOptions(new d().a(requestOptions));
        }
        AppMethodBeat.o(11116);
    }
}
