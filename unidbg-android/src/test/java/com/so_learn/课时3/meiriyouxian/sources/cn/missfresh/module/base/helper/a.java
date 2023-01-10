package cn.missfresh.module.base.helper;

import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;

/* compiled from: ArcAnimation */
public class a extends Animation {
    private final String a = getClass().getSimpleName();
    private View b;
    private float c;
    private float d;
    private float e;
    private float f;
    private float g;
    private float h = 0.7f;
    private float i = 180.0f;
    private boolean j = false;

    public a(View view, float f) {
        AppMethodBeat.i(12941, false);
        this.b = view;
        this.g = f;
        AppMethodBeat.o(12941);
    }

    @Override // android.view.animation.Animation
    public void initialize(int i, int i2, int i3, int i4) {
        AppMethodBeat.i(12944, false);
        super.initialize(i, i2, i3, i4);
        this.c = ((float) (this.b.getLeft() + i)) - this.g;
        this.d = (float) (this.b.getTop() + i2);
        this.e = (float) (this.b.getLeft() + i);
        this.f = this.d;
        AppMethodBeat.o(12944);
    }

    /* access modifiers changed from: protected */
    @Override // android.view.animation.Animation
    public void applyTransformation(float f, Transformation transformation) {
        AppMethodBeat.i(12946, false);
        if (f == 0.0f) {
            AppMethodBeat.o(12946);
            return;
        }
        float f2 = this.i;
        float f3 = ((f * f2) + f2) % f2;
        if (!this.j) {
            f3 = -f3;
        }
        double radians = (double) ((float) Math.toRadians((double) f3));
        float cos = this.e - ((float) (((double) this.c) + (((double) this.g) * Math.cos(radians))));
        float f4 = this.f;
        transformation.getMatrix().setTranslate(cos, f4 - ((float) (((double) this.d) + (((double) (this.g * this.h)) * Math.sin(radians)))));
        AppMethodBeat.o(12946);
    }
}
