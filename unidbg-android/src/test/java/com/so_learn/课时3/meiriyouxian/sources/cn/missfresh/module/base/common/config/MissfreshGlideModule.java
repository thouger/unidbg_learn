package cn.missfresh.module.base.common.config;

import android.content.Context;
import android.os.Build;
import cn.missfresh.module.base.common.config.b.a;
import cn.missfresh.module.base.utils.f;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.Registry;
import com.bumptech.glide.integration.okhttp3.OkHttpUrlLoader;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.ImageHeaderParser;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.bitmap_recycle.LruBitmapPool;
import com.bumptech.glide.load.engine.cache.DiskCache;
import com.bumptech.glide.load.engine.cache.DiskLruCacheWrapper;
import com.bumptech.glide.load.engine.cache.LruResourceCache;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.load.resource.bitmap.ExifInterfaceImageHeaderParser;
import com.bumptech.glide.module.AppGlideModule;
import com.bumptech.glide.request.RequestOptions;
import java.io.File;
import java.io.InputStream;
import java.util.List;
import java.util.concurrent.TimeUnit;
import okhttp3.OkHttpClient;

public class MissfreshGlideModule extends AppGlideModule {
    @Override // com.bumptech.glide.module.AppGlideModule
    public boolean isManifestParsingEnabled() {
        return false;
    }

    @Override // com.bumptech.glide.module.LibraryGlideModule, com.bumptech.glide.module.RegistersComponents
    public void registerComponents(Context context, Glide glide, Registry registry) {
        int i = 0;
        AppMethodBeat.i(11178, false);
        registry.replace(GlideUrl.class, InputStream.class, new OkHttpUrlLoader.Factory(new OkHttpClient.Builder().connectTimeout(5, TimeUnit.SECONDS).readTimeout(10, TimeUnit.SECONDS).writeTimeout(10, TimeUnit.SECONDS).addNetworkInterceptor(new a()).build()));
        if (Build.VERSION.SDK_INT >= 29) {
            try {
                List<ImageHeaderParser> imageHeaderParsers = registry.getImageHeaderParsers();
                if (imageHeaderParsers != null) {
                    while (true) {
                        if (i >= imageHeaderParsers.size()) {
                            break;
                        } else if (imageHeaderParsers.get(i) instanceof ExifInterfaceImageHeaderParser) {
                            imageHeaderParsers.remove(i);
                            imageHeaderParsers.add(i, new g());
                            break;
                        } else {
                            i++;
                        }
                    }
                }
            } catch (Exception unused) {
            }
        }
        AppMethodBeat.o(11178);
    }

    @Override // com.bumptech.glide.module.AppGlideModule, com.bumptech.glide.module.AppliesOptions
    public void applyOptions(Context context, GlideBuilder glideBuilder) {
        AppMethodBeat.i(11180, false);
        glideBuilder.setDefaultRequestOptions(new RequestOptions().format(DecodeFormat.PREFER_ARGB_8888).skipMemoryCache(false).diskCacheStrategy(DiskCacheStrategy.ALL).disallowHardwareConfig());
        if (!f.o()) {
            glideBuilder.setMemoryCache(new LruResourceCache(209715200));
        } else {
            long maxMemory = Runtime.getRuntime().maxMemory();
            glideBuilder.setMemoryCache(new LruResourceCache(maxMemory / 6));
            glideBuilder.setBitmapPool(new LruBitmapPool(maxMemory / 8));
        }
        glideBuilder.setDiskCache(new AnonymousClass1());
        AppMethodBeat.o(11180);
    }

    /* renamed from: cn.missfresh.module.base.common.config.MissfreshGlideModule$1  reason: invalid class name */
    class AnonymousClass1 implements DiskCache.Factory {
        AnonymousClass1() {
        }

        @Override // com.bumptech.glide.load.engine.cache.DiskCache.Factory
        public DiskCache build() {
            AppMethodBeat.i(11176, false);
            DiskCache diskCache = DiskLruCacheWrapper.get(new File(c.e), 209715200);
            AppMethodBeat.o(11176);
            return diskCache;
        }
    }
}
