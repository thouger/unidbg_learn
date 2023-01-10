package cn.missfresh.module.base.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.ImageView;
import cn.missfresh.lib.image.c;
import cn.missfresh.lib.image.c.a;
import cn.missfresh.module.base.R;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import cn.missfresh.utils.b;

public class FitWidthImageView extends ImageView {
    private int a;

    public FitWidthImageView(Context context) {
        super(context);
    }

    public FitWidthImageView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public FitWidthImageView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public void setFixedHeight(int i) {
        this.a = i;
    }

    public void a(String str) {
        AppMethodBeat.i(23679, false);
        if (b.a(str) || this.a <= 0) {
            setVisibility(8);
            AppMethodBeat.o(23679);
            return;
        }
        setTag(R.id.cb_item_tag, str);
        setVisibility(0);
        c.a(this).a(str).d().a(new AnonymousClass1(this, str));
        AppMethodBeat.o(23679);
    }

    /* renamed from: cn.missfresh.module.base.widget.FitWidthImageView$1  reason: invalid class name */
    class AnonymousClass1 extends a<ImageView> {
        final /* synthetic */ String b;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        AnonymousClass1(ImageView imageView, String str) {
            super(imageView);
            this.b = str;
        }

        public /* synthetic */ void a(Object obj) {
            AppMethodBeat.i(23678, false);
            a((Bitmap) obj);
            AppMethodBeat.o(23678);
        }

        public void a(Bitmap bitmap) {
            AppMethodBeat.i(23676, false);
            if (bitmap.getHeight() != 0) {
                int width = (bitmap.getWidth() * FitWidthImageView.this.a) / bitmap.getHeight();
                ViewGroup.LayoutParams layoutParams = FitWidthImageView.this.getLayoutParams();
                String str = (String) FitWidthImageView.this.getTag(R.id.cb_item_tag);
                if (!b.a(str) && !b.a(this.b) && !str.equalsIgnoreCase(this.b)) {
                    AppMethodBeat.o(23676);
                    return;
                } else if (layoutParams != null) {
                    layoutParams.height = FitWidthImageView.this.a;
                    layoutParams.width = width;
                    FitWidthImageView.this.setLayoutParams(layoutParams);
                    FitWidthImageView.this.setImageBitmap(bitmap);
                }
            } else {
                FitWidthImageView.this.setVisibility(8);
            }
            AppMethodBeat.o(23676);
        }

        @Override // cn.missfresh.lib.image.c.d
        public void b(Drawable drawable) {
            AppMethodBeat.i(23677, false);
            FitWidthImageView.this.setVisibility(8);
            AppMethodBeat.o(23677);
        }
    }
}
