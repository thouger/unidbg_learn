package com.sobot.chat.widget.photoview;

import android.content.Context;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import com.sobot.chat.widget.photoview.b;

public class PhotoView extends ImageView {
    private final b a;
    private ImageView.ScaleType b;

    public PhotoView(Context context) {
        this(context, null);
    }

    public PhotoView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public PhotoView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        super.setScaleType(ImageView.ScaleType.MATRIX);
        this.a = new b(this);
        ImageView.ScaleType scaleType = this.b;
        if (scaleType != null) {
            setScaleType(scaleType);
            this.b = null;
        }
    }

    public RectF getDisplayRect() {
        return this.a.b();
    }

    public float getMinScale() {
        return this.a.d();
    }

    public float getMidScale() {
        return this.a.e();
    }

    public float getMaxScale() {
        return this.a.f();
    }

    public float getScale() {
        return this.a.g();
    }

    @Override // android.widget.ImageView
    public ImageView.ScaleType getScaleType() {
        return this.a.h();
    }

    public void setAllowParentInterceptOnEdge(boolean z) {
        this.a.a(z);
    }

    public void setMinScale(float f) {
        this.a.a(f);
    }

    public void setMidScale(float f) {
        this.a.b(f);
    }

    public void setMaxScale(float f) {
        this.a.c(f);
    }

    @Override // android.widget.ImageView
    public void setImageDrawable(Drawable drawable) {
        super.setImageDrawable(drawable);
        b bVar = this.a;
        if (bVar != null) {
            bVar.i();
        }
    }

    @Override // android.widget.ImageView
    public void setImageResource(int i) {
        super.setImageResource(i);
        b bVar = this.a;
        if (bVar != null) {
            bVar.i();
        }
    }

    @Override // android.widget.ImageView
    public void setImageURI(Uri uri) {
        super.setImageURI(uri);
        b bVar = this.a;
        if (bVar != null) {
            bVar.i();
        }
    }

    public void setOnMatrixChangeListener(b.c cVar) {
        this.a.a(cVar);
    }

    @Override // android.view.View
    public void setOnLongClickListener(View.OnLongClickListener onLongClickListener) {
        this.a.a(onLongClickListener);
    }

    public void setOnPhotoTapListener(b.d dVar) {
        this.a.a(dVar);
    }

    public void setOnViewTapListener(b.e eVar) {
        this.a.a(eVar);
    }

    @Override // android.widget.ImageView
    public void setScaleType(ImageView.ScaleType scaleType) {
        b bVar = this.a;
        if (bVar != null) {
            bVar.a(scaleType);
        } else {
            this.b = scaleType;
        }
    }

    public void setZoomable(boolean z) {
        this.a.b(z);
    }

    /* access modifiers changed from: protected */
    @Override // android.widget.ImageView, android.view.View
    public void onDetachedFromWindow() {
        this.a.a();
        super.onDetachedFromWindow();
    }
}
