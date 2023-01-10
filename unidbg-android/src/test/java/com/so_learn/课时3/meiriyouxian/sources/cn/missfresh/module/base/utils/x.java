package cn.missfresh.module.base.utils;

import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import cn.missfresh.lib.image.c;
import cn.missfresh.lib.image.c.b;
import cn.missfresh.lib.image.d.a;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;

/* compiled from: GlideHelper */
public class x {
    public static void a(ImageView imageView, String str, int i) {
        AppMethodBeat.i(23312, false);
        if (a(str)) {
            c.a(imageView).e().a(str).c(1).a(i).a(imageView);
        } else {
            c.a(imageView).d().g().a(str).a(i).a(imageView);
        }
        AppMethodBeat.o(23312);
    }

    public static boolean a(String str) {
        boolean z = false;
        AppMethodBeat.i(23313, false);
        if (!TextUtils.isEmpty(str) && str.toLowerCase().contains(".gif")) {
            z = true;
        }
        AppMethodBeat.o(23313);
        return z;
    }

    public static void a(ImageView imageView, String str) {
        AppMethodBeat.i(23314, false);
        if (a(str)) {
            c.a(imageView).e().a(str).c(1).a(imageView);
        } else {
            c.a(imageView).d().g().a(str).a(imageView);
        }
        AppMethodBeat.o(23314);
    }

    public static void a(ImageView imageView, String str, int i, int i2) {
        AppMethodBeat.i(23315, false);
        c.a(imageView).d().a(str).a(new a(imageView.getContext(), (float) i).a(false, false, true, true)).g().a(i2).a(imageView);
        AppMethodBeat.o(23315);
    }

    public static void a(TextView textView, String str, int i, int i2) {
        AppMethodBeat.i(23316, false);
        c.a(textView).a(str).a(i, i2).a(new AnonymousClass1(textView));
        AppMethodBeat.o(23316);
    }

    /* compiled from: GlideHelper */
    /* renamed from: cn.missfresh.module.base.utils.x$1  reason: invalid class name */
    static class AnonymousClass1 extends b<Drawable> {
        final /* synthetic */ TextView a;

        AnonymousClass1(TextView textView) {
            this.a = textView;
        }

        public /* synthetic */ void a(Object obj) {
            AppMethodBeat.i(23307, false);
            d((Drawable) obj);
            AppMethodBeat.o(23307);
        }

        public void d(Drawable drawable) {
            AppMethodBeat.i(23306, false);
            this.a.setCompoundDrawables(null, null, drawable, null);
            AppMethodBeat.o(23306);
        }
    }

    public static void a(View view, String str) {
        AppMethodBeat.i(23317, false);
        c.a(view).a(str).a(new AnonymousClass2(view));
        AppMethodBeat.o(23317);
    }

    /* compiled from: GlideHelper */
    /* renamed from: cn.missfresh.module.base.utils.x$2  reason: invalid class name */
    static class AnonymousClass2 extends b<Drawable> {
        final /* synthetic */ View a;

        AnonymousClass2(View view) {
            this.a = view;
        }

        public /* synthetic */ void a(Object obj) {
            AppMethodBeat.i(23309, false);
            d((Drawable) obj);
            AppMethodBeat.o(23309);
        }

        public void d(Drawable drawable) {
            AppMethodBeat.i(23308, false);
            this.a.setBackground(drawable);
            AppMethodBeat.o(23308);
        }
    }

    public static void a(View view, String str, int i) {
        AppMethodBeat.i(23318, false);
        c.a(view).a(str).a(i).a(new AnonymousClass3(view));
        AppMethodBeat.o(23318);
    }

    /* compiled from: GlideHelper */
    /* renamed from: cn.missfresh.module.base.utils.x$3  reason: invalid class name */
    static class AnonymousClass3 extends b<Drawable> {
        final /* synthetic */ View a;

        AnonymousClass3(View view) {
            this.a = view;
        }

        public /* synthetic */ void a(Object obj) {
            AppMethodBeat.i(23311, false);
            d((Drawable) obj);
            AppMethodBeat.o(23311);
        }

        public void d(Drawable drawable) {
            AppMethodBeat.i(23310, false);
            this.a.setBackground(drawable);
            AppMethodBeat.o(23310);
        }
    }

    public static void b(ImageView imageView, String str, int i, int i2) {
        AppMethodBeat.i(23319, false);
        c.a(imageView).d().a(str).a(new a(imageView.getContext(), (float) i).a(false, false, false, false)).g().a(i2).a(imageView);
        AppMethodBeat.o(23319);
    }

    public static void c(ImageView imageView, String str, int i, int i2) {
        AppMethodBeat.i(23320, false);
        if (TextUtils.isEmpty(str) || !str.toLowerCase().contains(".gif")) {
            c.a(imageView).d().a(str).a(new a(imageView.getContext(), (float) i).a(false, false, false, false)).g().a(i2).a(imageView);
        } else {
            c.a(imageView).e().a(str).c(1).a(new a(imageView.getContext(), (float) i).a(false, false, false, false)).a(i2).a(imageView);
        }
        AppMethodBeat.o(23320);
    }

    public static void a(ImageView imageView, String str, int i, int i2, int i3, int i4) {
        AppMethodBeat.i(23321, false);
        if (a(str)) {
            c.a(imageView).e().a(str).c(1).a(i3, i4).a(new a(imageView.getContext(), (float) i).a(false, false, false, false)).a(i2).a(imageView);
        } else {
            c.a(imageView).d().a(str).a(i3, i4).a(new a(imageView.getContext(), (float) i).a(false, false, false, false)).g().a(i2).a(imageView);
        }
        AppMethodBeat.o(23321);
    }

    public static void a(ImageView imageView, String str, cn.missfresh.lib.image.b.a aVar) {
        AppMethodBeat.i(23322, false);
        if (a(str)) {
            c.a(imageView).e().a(str).a(aVar).a(imageView);
        } else {
            c.a(imageView).d().a(str).a(aVar).a(imageView);
        }
        AppMethodBeat.o(23322);
    }
}
