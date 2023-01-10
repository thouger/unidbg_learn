package cn.missfresh.lib.image.a;

import android.graphics.Bitmap;
import cn.missfresh.lib.image.d.c;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;
import com.bumptech.glide.util.Util;
import java.security.MessageDigest;
import java.util.List;

/* compiled from: InnerTransformation */
public class a extends BitmapTransformation {
    private List<c> a;

    public a(List<c> list) {
        this.a = list;
    }

    /* access modifiers changed from: protected */
    @Override // com.bumptech.glide.load.resource.bitmap.BitmapTransformation
    public Bitmap transform(BitmapPool bitmapPool, Bitmap bitmap, int i, int i2) {
        AppMethodBeat.i(4330, false);
        List<c> list = this.a;
        if (list == null || list.isEmpty()) {
            AppMethodBeat.o(4330);
            return bitmap;
        }
        int size = this.a.size();
        for (int i3 = 0; i3 < size; i3++) {
            c cVar = this.a.get(i3);
            if (cVar != null) {
                bitmap = cVar.a(bitmap, i, i2);
            }
        }
        AppMethodBeat.o(4330);
        return bitmap;
    }

    @Override // com.bumptech.glide.load.Key
    public boolean equals(Object obj) {
        AppMethodBeat.i(4332, false);
        if (obj instanceof a) {
            a aVar = (a) obj;
            List<c> list = this.a;
            if (list != null) {
                boolean equals = list.equals(aVar.a);
                AppMethodBeat.o(4332);
                return equals;
            }
            List<c> list2 = aVar.a;
            if (list2 != null) {
                boolean equals2 = list2.equals(list);
                AppMethodBeat.o(4332);
                return equals2;
            }
            AppMethodBeat.o(4332);
            return true;
        }
        AppMethodBeat.o(4332);
        return false;
    }

    @Override // com.bumptech.glide.load.Key
    public int hashCode() {
        AppMethodBeat.i(4333, false);
        int hashCode = Util.hashCode("cn.missfresh.lib.image.glidetransform.InnerTransformation".hashCode(), Util.hashCode(this.a, 0));
        AppMethodBeat.o(4333);
        return hashCode;
    }

    @Override // com.bumptech.glide.load.Key
    public void updateDiskCacheKey(MessageDigest messageDigest) {
        AppMethodBeat.i(4335, false);
        List<c> list = this.a;
        if (list == null || list.isEmpty()) {
            messageDigest.update("cn.missfresh.lib.image.glidetransform.InnerTransformation".getBytes(CHARSET));
        } else {
            int size = this.a.size();
            for (int i = 0; i < size; i++) {
                c cVar = this.a.get(i);
                if (cVar != null) {
                    cVar.a(messageDigest);
                }
            }
        }
        AppMethodBeat.o(4335);
    }
}
