package cn.missfresh.module.base.widget.imageWatcher;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import cn.missfresh.lib.image.c;
import cn.missfresh.lib.image.c.b;
import cn.missfresh.module.base.R;
import cn.missfresh.module.base.widget.imageWatcher.ImageWatcher;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import java.util.List;

/* compiled from: ImageWatcherHelper */
public class a {
    private static final int a = R.id.view_image_watcher;
    private static final int b = R.id.view_note_goods_list;
    private final ViewGroup c;
    private ImageWatcher d;
    private ImageWatcher.f e;
    private Integer f;
    private Integer g;
    private ImageWatcher.h h;
    private ImageWatcher.d i;
    private ImageWatcher.g j;
    private ImageWatcher.i k;

    private a(ViewGroup viewGroup) {
        this.c = viewGroup;
    }

    public static a a(View view, ImageWatcher.f fVar) {
        AppMethodBeat.i(24079, false);
        if (view instanceof ViewGroup) {
            a aVar = new a((ViewGroup) view);
            if (fVar == null) {
                aVar.e = new C0033a();
            } else {
                aVar.e = fVar;
            }
            AppMethodBeat.o(24079);
            return aVar;
        }
        NullPointerException nullPointerException = new NullPointerException("do not have viewGroup to add");
        AppMethodBeat.o(24079);
        throw nullPointerException;
    }

    public a a(int i) {
        AppMethodBeat.i(24081, false);
        this.g = Integer.valueOf(i);
        AppMethodBeat.o(24081);
        return this;
    }

    public a a(ImageWatcher.i iVar) {
        this.k = iVar;
        return this;
    }

    public void a(List<String> list, int i) {
        AppMethodBeat.i(24083, false);
        a();
        this.d.a(list, i);
        AppMethodBeat.o(24083);
    }

    private void a() {
        AppMethodBeat.i(24084, false);
        this.d = new ImageWatcher(this.c.getContext());
        this.d.setId(a);
        this.d.setLoader(this.e);
        this.d.a();
        Integer num = this.f;
        if (num != null) {
            this.d.setTranslucentStatus(num.intValue());
        }
        Integer num2 = this.g;
        if (num2 != null) {
            this.d.setErrorImageRes(num2.intValue());
        }
        ImageWatcher.h hVar = this.h;
        if (hVar != null) {
            this.d.setOnPictureLongPressListener(hVar);
        }
        ImageWatcher.d dVar = this.i;
        if (dVar != null) {
            this.d.setIndexProvider(dVar);
        }
        ImageWatcher.g gVar = this.j;
        if (gVar != null) {
            this.d.setLoadingUIProvider(gVar);
        }
        ImageWatcher.i iVar = this.k;
        if (iVar != null) {
            this.d.setOnStateChangedListener(iVar);
        }
        a(this.c);
        this.c.addView(this.d);
        AppMethodBeat.o(24084);
    }

    private void a(ViewGroup viewGroup) {
        AppMethodBeat.i(24087, false);
        for (int i = 0; i < viewGroup.getChildCount(); i++) {
            View childAt = viewGroup.getChildAt(i);
            if (childAt.getId() == a) {
                viewGroup.removeView(childAt);
            }
            if (childAt.getId() == b) {
                viewGroup.removeView(childAt);
            }
            if (childAt instanceof ViewGroup) {
                a((ViewGroup) childAt);
            }
        }
        AppMethodBeat.o(24087);
    }

    /* compiled from: ImageWatcherHelper */
    /* renamed from: cn.missfresh.module.base.widget.imageWatcher.a$a  reason: collision with other inner class name */
    private static class C0033a implements ImageWatcher.f {
        private C0033a() {
        }

        /* compiled from: ImageWatcherHelper */
        /* renamed from: cn.missfresh.module.base.widget.imageWatcher.a$a$1  reason: invalid class name */
        class AnonymousClass1 extends b<Drawable> {
            final /* synthetic */ ImageWatcher.e a;

            AnonymousClass1(ImageWatcher.e eVar) {
                this.a = eVar;
            }

            public /* synthetic */ void a(Object obj) {
                AppMethodBeat.i(24077, false);
                d((Drawable) obj);
                AppMethodBeat.o(24077);
            }

            public void d(Drawable drawable) {
                AppMethodBeat.i(24074, false);
                this.a.a(drawable);
                AppMethodBeat.o(24074);
            }

            @Override // cn.missfresh.lib.image.c.b
            public void b(Drawable drawable) {
                AppMethodBeat.i(24075, false);
                this.a.c(drawable);
                AppMethodBeat.o(24075);
            }

            @Override // cn.missfresh.lib.image.c.b
            public void a(Drawable drawable) {
                AppMethodBeat.i(24076, false);
                this.a.b(drawable);
                AppMethodBeat.o(24076);
            }
        }

        @Override // cn.missfresh.module.base.widget.imageWatcher.ImageWatcher.f
        public void a(Context context, String str, ImageWatcher.e eVar) {
            AppMethodBeat.i(24078, false);
            c.b(context).a(str).a(new AnonymousClass1(eVar));
            AppMethodBeat.o(24078);
        }
    }
}
