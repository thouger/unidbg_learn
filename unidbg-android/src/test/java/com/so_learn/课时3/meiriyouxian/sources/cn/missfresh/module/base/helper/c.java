package cn.missfresh.module.base.helper;

import android.text.TextUtils;
import android.widget.ImageView;
import cn.missfresh.lib.image.d.a;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;

/* compiled from: GlideHelper */
public class c {
    public static void a(ImageView imageView, String str, int i) {
        AppMethodBeat.i(12959, false);
        if (TextUtils.isEmpty(str) || !str.toLowerCase().contains(".gif")) {
            cn.missfresh.lib.image.c.a(imageView).d().g().a(str).a(i).a(imageView);
        } else {
            cn.missfresh.lib.image.c.a(imageView).e().a(str).c(1).a(i).a(imageView);
        }
        AppMethodBeat.o(12959);
    }

    public static void a(ImageView imageView, String str) {
        AppMethodBeat.i(12961, false);
        if (TextUtils.isEmpty(str) || !str.toLowerCase().contains(".gif")) {
            cn.missfresh.lib.image.c.a(imageView).d().g().a(str).a(imageView);
        } else {
            cn.missfresh.lib.image.c.a(imageView).e().a(str).c(1).a(imageView);
        }
        AppMethodBeat.o(12961);
    }

    public static void a(ImageView imageView, String str, int i, int i2) {
        AppMethodBeat.i(12966, false);
        cn.missfresh.lib.image.c.a(imageView).d().a(str).a(new a(imageView.getContext(), (float) i).a(false, false, false, false)).g().a(i2).a(imageView);
        AppMethodBeat.o(12966);
    }

    public static void b(ImageView imageView, String str, int i, int i2) {
        AppMethodBeat.i(12967, false);
        if (TextUtils.isEmpty(str) || !str.toLowerCase().contains(".gif")) {
            cn.missfresh.lib.image.c.a(imageView).d().a(str).a(new a(imageView.getContext(), (float) i).a(false, false, false, false)).g().a(i2).a(imageView);
        } else {
            cn.missfresh.lib.image.c.a(imageView).e().a(str).c(1).a(new a(imageView.getContext(), (float) i).a(false, false, false, false)).a(i2).a(imageView);
        }
        AppMethodBeat.o(12967);
    }
}
