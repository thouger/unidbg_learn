package cn.missfresh.module.base.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.ImageView;
import cn.missfresh.lib.image.c;
import cn.missfresh.lib.image.c.a;
import cn.missfresh.module.base.R;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import cn.missfresh.utils.b;

public class FitHeightImageView extends ImageView {
    private int a;

    public FitHeightImageView(Context context) {
        this(context, null);
    }

    public FitHeightImageView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public FitHeightImageView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public void setFixedWidth(int i) {
        this.a = i;
    }

    public void a(String str) {
        AppMethodBeat.i(23675, false);
        if (b.a(str) || this.a <= 0) {
            setVisibility(8);
            AppMethodBeat.o(23675);
            return;
        }
        setTag(R.id.cb_item_tag, str);
        setVisibility(0);
        c.a(this).a(str).d().a(new AnonymousClass1(this, str));
        AppMethodBeat.o(23675);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: cn.missfresh.module.base.widget.FitHeightImageView$1  reason: invalid class name */
    public class AnonymousClass1 extends a<ImageView> {
        final /* synthetic */ String b;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        AnonymousClass1(ImageView imageView, String str) {
            super(imageView);
            this.b = str;
        }

        public /* synthetic */ void a(Object obj) {
            AppMethodBeat.i(23674, false);
            a((Bitmap) obj);
            AppMethodBeat.o(23674);
        }

        public void a(Bitmap bitmap) {
            AppMethodBeat.i(23673, false);
            if (bitmap.getHeight() != 0) {
                int height = (bitmap.getHeight() * FitHeightImageView.this.a) / bitmap.getWidth();
                ViewGroup.LayoutParams layoutParams = FitHeightImageView.this.getLayoutParams();
                String str = (String) FitHeightImageView.this.getTag(R.id.cb_item_tag);
                if (!b.a(str) && !b.a(this.b) && !str.equalsIgnoreCase(this.b)) {
                    AppMethodBeat.o(23673);
                    return;
                } else if (layoutParams != null) {
                    layoutParams.width = FitHeightImageView.this.a;
                    layoutParams.height = height;
                    FitHeightImageView.this.setLayoutParams(layoutParams);
                    FitHeightImageView.this.setImageBitmap(bitmap);
                }
            }
            AppMethodBeat.o(23673);
        }
    }
}
