package cn.missfresh.module.base.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ImageView;
import cn.missfresh.lib.image.c;
import cn.missfresh.module.base.R;
import cn.missfresh.module.base.common.interfaces.g;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import cn.missfresh.utils.b;

public class ProductPictureLayout extends ImageView implements g {
    private int a;
    private boolean b;

    @Override // cn.missfresh.module.base.common.interfaces.g
    public ImageView getImageView() {
        return this;
    }

    public ProductPictureLayout(Context context) {
        this(context, null);
    }

    public ProductPictureLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public ProductPictureLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.a = R.drawable.ic_default_100;
        this.b = true;
    }

    @Override // cn.missfresh.module.base.common.interfaces.g
    public void setPicture(String str) {
        AppMethodBeat.i(23824, false);
        if (!this.b) {
            AppMethodBeat.o(23824);
        } else if (b.a(str)) {
            AppMethodBeat.o(23824);
        } else {
            try {
                c.b(getContext()).a(str).a(this.a).b(this.a).a(this);
                AppMethodBeat.o(23824);
            } catch (Exception unused) {
                AppMethodBeat.o(23824);
            }
        }
    }

    public void setPicture(Drawable drawable) {
        AppMethodBeat.i(23825, false);
        if (!this.b) {
            AppMethodBeat.o(23825);
            return;
        }
        setImageDrawable(drawable);
        AppMethodBeat.o(23825);
    }

    public void setPicture(Bitmap bitmap) {
        AppMethodBeat.i(23826, false);
        if (!this.b) {
            AppMethodBeat.o(23826);
            return;
        }
        setImageBitmap(bitmap);
        AppMethodBeat.o(23826);
    }

    @Override // cn.missfresh.module.base.common.interfaces.m
    public void setShow(boolean z) {
        AppMethodBeat.i(23827, false);
        if (this.b == z) {
            AppMethodBeat.o(23827);
            return;
        }
        this.b = z;
        if (this.b) {
            setVisibility(0);
        } else {
            setVisibility(8);
        }
        AppMethodBeat.o(23827);
    }
}
