package io.reactivex.internal.util;

/* compiled from: Pow2 */
public final class g {
    public static int a(int i) {
        return 1 << (32 - Integer.numberOfLeadingZeros(i - 1));
    }
}
