package com.sobot.chat.core.http.b;

/* compiled from: StStorageException */
public class c extends Exception {
    private static final long a = 178946465;

    public c() {
    }

    public c(String str) {
        super(str);
    }

    public c(String str, Throwable th) {
        super(str, th);
    }

    public c(Throwable th) {
        super(th);
    }

    public static c a() {
        return new c("SDCard isn't available, please check SD card and permission: WRITE_EXTERNAL_STORAGE, and you must pay attention to Android6.0 RunTime Permissions!");
    }
}
