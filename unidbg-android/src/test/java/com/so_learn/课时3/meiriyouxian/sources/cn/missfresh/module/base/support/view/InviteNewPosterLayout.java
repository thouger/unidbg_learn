package cn.missfresh.module.base.support.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ShapeDrawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import cn.missfresh.lib.image.c;
import cn.missfresh.module.base.R;
import cn.missfresh.module.base.bean.PosterInfo;
import cn.missfresh.module.base.manager.e;
import cn.missfresh.module.base.utils.an;
import cn.missfresh.module.base.utils.aw;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import cn.missfresh.utils.a.d;
import com.android.internal.logging.nano.MetricsProto;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import io.reactivex.c.g;
import io.reactivex.c.h;
import io.reactivex.disposables.a;
import io.reactivex.disposables.b;
import io.reactivex.q;

public class InviteNewPosterLayout extends ConstraintLayout {
    private ImageView a;
    private ImageView b;
    private TextView c;
    private AppCompatTextView d;
    private ImageView e;
    private String f;
    private String g;
    private Bitmap h;
    private a i;

    static /* synthetic */ void a(InviteNewPosterLayout inviteNewPosterLayout, b bVar) {
        AppMethodBeat.i(22738, false);
        inviteNewPosterLayout.a(bVar);
        AppMethodBeat.o(22738);
    }

    public InviteNewPosterLayout(Context context) {
        this(context, null);
    }

    public InviteNewPosterLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public InviteNewPosterLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        AppMethodBeat.i(22723, false);
        this.h = null;
        a(context);
        AppMethodBeat.o(22723);
    }

    private void a(Context context) {
        AppMethodBeat.i(22725, false);
        LayoutInflater.from(context).inflate(R.layout.layout_invite_new_post, (ViewGroup) this, true);
        this.a = (ImageView) findViewById(R.id.iv_post_bg);
        this.b = (ImageView) findViewById(R.id.iv_header);
        this.c = (TextView) findViewById(R.id.tv_act_introduction);
        this.d = (AppCompatTextView) findViewById(R.id.tv_act_content);
        this.e = (ImageView) findViewById(R.id.iv_qrcode);
        this.i = new a();
        AppMethodBeat.o(22725);
    }

    public void setData(PosterInfo posterInfo) {
        AppMethodBeat.i(22726, false);
        this.c.setText(posterInfo.posterCopywriter);
        this.d.setText(posterInfo.withManyAvailable);
        this.e.setImageBitmap(an.a(posterInfo.inviteUrl, aw.b(60), 0, null));
        this.f = posterInfo.bgUrl;
        this.g = posterInfo.portrait;
        AppMethodBeat.o(22726);
    }

    public q<Bitmap> getBitmap() {
        AppMethodBeat.i(22727, false);
        if (this.h != null) {
            d.b("InviteNewPosterLayout", "mBitmap!=null");
            q<Bitmap> a = q.a(this.h);
            AppMethodBeat.o(22727);
            return a;
        }
        q<Bitmap> b = b();
        AppMethodBeat.o(22727);
        return b;
    }

    private q<? extends Drawable> getHeaderObservable() {
        AppMethodBeat.i(22729, false);
        if (TextUtils.isEmpty(this.g)) {
            q<? extends Drawable> a = q.a(new ShapeDrawable());
            AppMethodBeat.o(22729);
            return a;
        }
        q<? extends Drawable> b = q.a(e.p().getPortrait()).b((h) new AnonymousClass3()).d(new AnonymousClass2()).d(new AnonymousClass1()).b(io.reactivex.f.a.b());
        AppMethodBeat.o(22729);
        return b;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: cn.missfresh.module.base.support.view.InviteNewPosterLayout$3  reason: invalid class name */
    public class AnonymousClass3 implements h<String, Drawable> {
        AnonymousClass3() {
        }

        @Override // io.reactivex.c.h
        public /* synthetic */ Object apply(Object obj) throws Exception {
            AppMethodBeat.i(22695, false);
            Drawable a = a((String) obj);
            AppMethodBeat.o(22695);
            return a;
        }

        public Drawable a(String str) throws Exception {
            AppMethodBeat.i(22693, false);
            d.b("InviteNewPosterLayout", "\u5f00\u59cb\u751f\u6210\u5934\u50cf");
            Drawable drawable = (Drawable) c.a(InviteNewPosterLayout.this).h().a(InviteNewPosterLayout.this.g).a(R.drawable.ic_default_header).b(R.drawable.ic_default_header).b().c(InviteNewPosterLayout.this.getWidth(), InviteNewPosterLayout.this.getHeight());
            AppMethodBeat.o(22693);
            return drawable;
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: cn.missfresh.module.base.support.view.InviteNewPosterLayout$2  reason: invalid class name */
    public class AnonymousClass2 implements h<Throwable, Drawable> {
        AnonymousClass2() {
        }

        @Override // io.reactivex.c.h
        public /* synthetic */ Object apply(Object obj) throws Exception {
            AppMethodBeat.i(22692, false);
            Drawable a = a((Throwable) obj);
            AppMethodBeat.o(22692);
            return a;
        }

        public Drawable a(Throwable th) throws Exception {
            AppMethodBeat.i(22690, false);
            d.b("InviteNewPosterLayout", "\u751f\u6210\u5934\u50cf\u5f02\u5e38 throwable==" + th);
            ShapeDrawable shapeDrawable = new ShapeDrawable();
            AppMethodBeat.o(22690);
            return shapeDrawable;
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: cn.missfresh.module.base.support.view.InviteNewPosterLayout$1  reason: invalid class name */
    public class AnonymousClass1 implements g<b> {
        AnonymousClass1() {
        }

        @Override // io.reactivex.c.g
        public /* synthetic */ void accept(Object obj) throws Exception {
            AppMethodBeat.i(22685, false);
            a((b) obj);
            AppMethodBeat.o(22685);
        }

        public void a(b bVar) throws Exception {
            AppMethodBeat.i(22684, false);
            d.b("InviteNewPosterLayout", "\u751f\u6210\u5934\u50cf\u5f00\u59cb doOnSubscribe");
            InviteNewPosterLayout.a(InviteNewPosterLayout.this, bVar);
            AppMethodBeat.o(22684);
        }
    }

    private q<? extends Drawable> getObservable() {
        AppMethodBeat.i(22730, false);
        if (TextUtils.isEmpty(this.f)) {
            q<? extends Drawable> a = q.a(new ShapeDrawable());
            AppMethodBeat.o(22730);
            return a;
        }
        q<? extends Drawable> b = q.a(this.f).b((h) new AnonymousClass6()).d(new AnonymousClass5()).d(new AnonymousClass4()).b(io.reactivex.f.a.b());
        AppMethodBeat.o(22730);
        return b;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: cn.missfresh.module.base.support.view.InviteNewPosterLayout$6  reason: invalid class name */
    public class AnonymousClass6 implements h<String, Drawable> {
        AnonymousClass6() {
        }

        @Override // io.reactivex.c.h
        public /* synthetic */ Object apply(Object obj) throws Exception {
            AppMethodBeat.i(22704, false);
            Drawable a = a((String) obj);
            AppMethodBeat.o(22704);
            return a;
        }

        public Drawable a(String str) throws Exception {
            AppMethodBeat.i(22703, false);
            if (TextUtils.isEmpty(str)) {
                d.b("InviteNewPosterLayout", "\u80cc\u666f\u56feurl\u4e3a\u7a7a");
                ShapeDrawable shapeDrawable = new ShapeDrawable();
                AppMethodBeat.o(22703);
                return shapeDrawable;
            }
            try {
                d.b("InviteNewPosterLayout", "\u5f00\u59cb\u751f\u6210\u80cc\u666f\u56fe");
                Drawable drawable = (Drawable) c.a(InviteNewPosterLayout.this).h().a(DiskCacheStrategy.ALL).a(str).c(InviteNewPosterLayout.this.getWidth(), InviteNewPosterLayout.this.getHeight());
                AppMethodBeat.o(22703);
                return drawable;
            } catch (OutOfMemoryError unused) {
                d.b("InviteNewPosterLayout", "\u5185\u5b58\u6ea2\u51fa");
                ShapeDrawable shapeDrawable2 = new ShapeDrawable();
                AppMethodBeat.o(22703);
                return shapeDrawable2;
            }
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: cn.missfresh.module.base.support.view.InviteNewPosterLayout$5  reason: invalid class name */
    public class AnonymousClass5 implements h<Throwable, Drawable> {
        AnonymousClass5() {
        }

        @Override // io.reactivex.c.h
        public /* synthetic */ Object apply(Object obj) throws Exception {
            AppMethodBeat.i(22701, false);
            Drawable a = a((Throwable) obj);
            AppMethodBeat.o(22701);
            return a;
        }

        public Drawable a(Throwable th) throws Exception {
            AppMethodBeat.i(22700, false);
            d.b("InviteNewPosterLayout", "\u751f\u6210\u80cc\u666f\u56fe\u51fa\u73b0\u5f02\u5e38\uff1athrowable==" + th);
            ShapeDrawable shapeDrawable = new ShapeDrawable();
            AppMethodBeat.o(22700);
            return shapeDrawable;
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: cn.missfresh.module.base.support.view.InviteNewPosterLayout$4  reason: invalid class name */
    public class AnonymousClass4 implements g<b> {
        AnonymousClass4() {
        }

        @Override // io.reactivex.c.g
        public /* synthetic */ void accept(Object obj) throws Exception {
            AppMethodBeat.i(22698, false);
            a((b) obj);
            AppMethodBeat.o(22698);
        }

        public void a(b bVar) throws Exception {
            AppMethodBeat.i(22697, false);
            d.b("InviteNewPosterLayout", "\u751f\u6210\u80cc\u666f\u56fe\u5f00\u59cbdoOnSubscribe");
            InviteNewPosterLayout.a(InviteNewPosterLayout.this, bVar);
            AppMethodBeat.o(22697);
        }
    }

    private q<Bitmap> b() {
        AppMethodBeat.i(22731, false);
        d.b("InviteNewPosterLayout", "\u751f\u6210\u56fe\u7247\u5f00\u59cb");
        int b = getLayoutParams() == null ? aw.b(306) : getLayoutParams().width;
        int b2 = getLayoutParams() == null ? aw.b((int) MetricsProto.MetricsEvent.DIALOG_ZEN_ACCESS_REVOKE) : getLayoutParams().height;
        int makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(b, 1073741824);
        int makeMeasureSpec2 = View.MeasureSpec.makeMeasureSpec(b2, 1073741824);
        measure(makeMeasureSpec, makeMeasureSpec2);
        if (getChildCount() > 0) {
            int childCount = getChildCount();
            for (int i = 0; i < childCount; i++) {
                measureChild(getChildAt(i), makeMeasureSpec, makeMeasureSpec2);
            }
        }
        layout(0, 0, getMeasuredWidth(), getMeasuredHeight());
        q<Bitmap> b3 = q.b(getObservable(), getHeaderObservable(), new AnonymousClass9()).d(new AnonymousClass8()).d(new AnonymousClass7()).b(io.reactivex.f.a.a());
        AppMethodBeat.o(22731);
        return b3;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: cn.missfresh.module.base.support.view.InviteNewPosterLayout$9  reason: invalid class name */
    public class AnonymousClass9 implements io.reactivex.c.c<Drawable, Drawable, Bitmap> {
        AnonymousClass9() {
        }

        @Override // io.reactivex.c.c
        public /* synthetic */ Object apply(Object obj, Object obj2) throws Exception {
            AppMethodBeat.i(22721, false);
            Bitmap a = a((Drawable) obj, (Drawable) obj2);
            AppMethodBeat.o(22721);
            return a;
        }

        public Bitmap a(Drawable drawable, Drawable drawable2) throws Exception {
            AppMethodBeat.i(22719, false);
            d.b("InviteNewPosterLayout", "\u751f\u6210\u80cc\u666f\u56fe\u56de\u8c03\u5165\u53e3---");
            if (!(drawable instanceof ShapeDrawable) || !(drawable2 instanceof ShapeDrawable)) {
                InviteNewPosterLayout.this.a.setImageDrawable(drawable);
                InviteNewPosterLayout.this.b.setImageDrawable(drawable2);
                try {
                    d.b("InviteNewPosterLayout", "\u8bfb\u53d6\u6574\u4e2aview\u7684\u80cc\u666f");
                    InviteNewPosterLayout.this.h = c.a(InviteNewPosterLayout.this.getWidth(), InviteNewPosterLayout.this.getHeight(), Bitmap.Config.ARGB_8888);
                    Canvas canvas = new Canvas(InviteNewPosterLayout.this.h);
                    canvas.concat(new Matrix());
                    canvas.translate((float) (-InviteNewPosterLayout.this.getScrollX()), (float) (-InviteNewPosterLayout.this.getScrollY()));
                    Drawable background = InviteNewPosterLayout.this.getBackground();
                    if (background != null) {
                        background.draw(canvas);
                    } else {
                        canvas.drawColor(-1);
                    }
                    InviteNewPosterLayout.this.draw(canvas);
                    d.b("InviteNewPosterLayout", "\u8bfb\u53d6\u6574\u56fe\u7684\u80cc\u666f\u7ed3\u675f---");
                    Bitmap bitmap = InviteNewPosterLayout.this.h;
                    AppMethodBeat.o(22719);
                    return bitmap;
                } catch (OutOfMemoryError unused) {
                    d.b("InviteNewPosterLayout", "\u5185\u5b58\u6ea2\u51fa");
                    AppMethodBeat.o(22719);
                    return null;
                }
            } else {
                d.b("InviteNewPosterLayout", "\u56fe\u7247\u5747\u751f\u6210\u5931\u8d25 drawable== " + drawable + "  drawable3==" + drawable2);
                AppMethodBeat.o(22719);
                return null;
            }
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: cn.missfresh.module.base.support.view.InviteNewPosterLayout$8  reason: invalid class name */
    public class AnonymousClass8 implements h<Throwable, Bitmap> {
        AnonymousClass8() {
        }

        @Override // io.reactivex.c.h
        public /* synthetic */ Object apply(Object obj) throws Exception {
            AppMethodBeat.i(22718, false);
            Bitmap a = a((Throwable) obj);
            AppMethodBeat.o(22718);
            return a;
        }

        public Bitmap a(Throwable th) throws Exception {
            AppMethodBeat.i(22717, false);
            d.b("InviteNewPosterLayout", "drawbitmap\u4efb\u52a1\u5931\u8d25");
            AppMethodBeat.o(22717);
            return null;
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: cn.missfresh.module.base.support.view.InviteNewPosterLayout$7  reason: invalid class name */
    public class AnonymousClass7 implements g<b> {
        AnonymousClass7() {
        }

        @Override // io.reactivex.c.g
        public /* synthetic */ void accept(Object obj) throws Exception {
            AppMethodBeat.i(22707, false);
            a((b) obj);
            AppMethodBeat.o(22707);
        }

        public void a(b bVar) throws Exception {
            AppMethodBeat.i(22706, false);
            d.b("InviteNewPosterLayout", "drawbitmap\u5f00\u59cb---");
            InviteNewPosterLayout.a(InviteNewPosterLayout.this, bVar);
            AppMethodBeat.o(22706);
        }
    }

    private void a(b bVar) {
        AppMethodBeat.i(22732, false);
        if (this.i == null) {
            this.i = new a();
        }
        this.i.a(bVar);
        AppMethodBeat.o(22732);
    }

    /* access modifiers changed from: protected */
    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        AppMethodBeat.i(22734, false);
        a();
        super.onDetachedFromWindow();
        AppMethodBeat.o(22734);
    }

    public void a() {
        AppMethodBeat.i(22736, false);
        a aVar = this.i;
        if (aVar != null && !aVar.isDisposed()) {
            this.i.dispose();
            this.i = null;
        }
        AppMethodBeat.o(22736);
    }
}
