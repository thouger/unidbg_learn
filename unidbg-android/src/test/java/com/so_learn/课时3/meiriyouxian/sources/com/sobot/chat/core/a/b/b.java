package com.sobot.chat.core.a.b;

/* compiled from: StringValidation */
public class b {
    public static boolean a(String str, String str2) {
        if (str == null) {
            return false;
        }
        return str.matches(str2);
    }
}
