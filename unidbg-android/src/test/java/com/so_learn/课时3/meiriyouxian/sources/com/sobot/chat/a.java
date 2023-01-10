package com.sobot.chat;

/* compiled from: MarkConfig */
public class a {
    private static int a = 2;

    public static boolean a(int i) {
        return (a & i) == i;
    }

    public static void a(int i, boolean z) {
        if (z) {
            a = i | a;
            return;
        }
        a = (~i) & a;
    }
}
