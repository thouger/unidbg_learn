package com.sobot.chat.widget.zxing;

import com.sobot.chat.widget.zxing.common.a.a;
import com.umeng.message.proguard.l;

/* compiled from: ResultPoint */
public class f {
    private final float a;
    private final float b;

    public f(float f, float f2) {
        this.a = f;
        this.b = f2;
    }

    public final float a() {
        return this.a;
    }

    public final float b() {
        return this.b;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof f)) {
            return false;
        }
        f fVar = (f) obj;
        if (this.a == fVar.a && this.b == fVar.b) {
            return true;
        }
        return false;
    }

    public final int hashCode() {
        return (Float.floatToIntBits(this.a) * 31) + Float.floatToIntBits(this.b);
    }

    public final String toString() {
        return l.s + this.a + ',' + this.b + ')';
    }

    public static void a(f[] fVarArr) {
        f fVar;
        f fVar2;
        f fVar3;
        float a = a(fVarArr[0], fVarArr[1]);
        float a2 = a(fVarArr[1], fVarArr[2]);
        float a3 = a(fVarArr[0], fVarArr[2]);
        if (a2 >= a && a2 >= a3) {
            fVar3 = fVarArr[0];
            fVar2 = fVarArr[1];
            fVar = fVarArr[2];
        } else if (a3 < a2 || a3 < a) {
            fVar3 = fVarArr[2];
            fVar2 = fVarArr[0];
            fVar = fVarArr[1];
        } else {
            fVar3 = fVarArr[1];
            fVar2 = fVarArr[0];
            fVar = fVarArr[2];
        }
        if (a(fVar2, fVar3, fVar) < 0.0f) {
            fVar = fVar2;
            fVar2 = fVar;
        }
        fVarArr[0] = fVar2;
        fVarArr[1] = fVar3;
        fVarArr[2] = fVar;
    }

    public static float a(f fVar, f fVar2) {
        return a.a(fVar.a, fVar.b, fVar2.a, fVar2.b);
    }

    private static float a(f fVar, f fVar2, f fVar3) {
        float f = fVar2.a;
        float f2 = fVar2.b;
        return ((fVar3.a - f) * (fVar.b - f2)) - ((fVar3.b - f2) * (fVar.a - f));
    }
}
