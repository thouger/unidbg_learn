package cn.missfresh.picture.filter;

import android.content.Context;
import cn.missfresh.picture.MimeType;
import cn.missfresh.picture.R;
import cn.missfresh.picture.filter.DefaultFilter;
import cn.missfresh.picture.internal.a.c;
import cn.missfresh.picture.internal.a.d;
import cn.missfresh.picture.internal.entity.LocalMedia;
import cn.missfresh.picture.internal.entity.b;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import java.io.File;
import java.util.Set;

/* compiled from: DefaultFilter */
public class a extends b {
    public Set<MimeType> a() {
        AppMethodBeat.i(18343, false);
        DefaultFilter.1 r1 = new DefaultFilter.1(this);
        AppMethodBeat.o(18343);
        return r1;
    }

    public b a(Context context, LocalMedia localMedia) {
        AppMethodBeat.i(18348, false);
        if (!b(context, localMedia)) {
            AppMethodBeat.o(18348);
            return null;
        }
        d.a(context.getContentResolver(), localMedia.a());
        if (localMedia.e == 0 || localMedia.f == 0) {
            b bVar = new b(0, context.getString(R.string.photo_size_error));
            AppMethodBeat.o(18348);
            return bVar;
        } else if (localMedia.e < 300 || localMedia.f < 300) {
            b bVar2 = new b(0, context.getString(R.string.photo_power_limit));
            AppMethodBeat.o(18348);
            return bVar2;
        } else {
            try {
                if (!new File(c.a(context, localMedia.a())).exists()) {
                    b bVar3 = new b(0, context.getString(R.string.photo_file_error));
                    AppMethodBeat.o(18348);
                    return bVar3;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            AppMethodBeat.o(18348);
            return null;
        }
    }
}
