package com.sobot.chat.widget.zxing.common.a;

/* compiled from: MathUtils */
public final class a {
    public static int a(float f) {
        return (int) (f + (f < 0.0f ? -0.5f : 0.5f));
    }

    public static float a(float f, float f2, float f3, float f4) {
        double d = (double) (f - f3);
        double d2 = (double) (f2 - f4);
        return (float) Math.sqrt((d * d) + (d2 * d2));
    }

    public static float a(int i, int i2, int i3, int i4) {
        double d = (double) (i - i3);
        double d2 = (double) (i2 - i4);
        return (float) Math.sqrt((d * d) + (d2 * d2));
    }
}
