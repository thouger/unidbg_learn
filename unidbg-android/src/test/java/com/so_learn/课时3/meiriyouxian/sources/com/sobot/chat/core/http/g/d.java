package com.sobot.chat.core.http.g;

import com.sobot.chat.core.http.e.a;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* compiled from: SobotDownloadThreadPool */
public class d {
    private static final TimeUnit a = TimeUnit.HOURS;
    private int b = 3;
    private com.sobot.chat.core.http.e.d c;

    public com.sobot.chat.core.http.e.d a() {
        if (this.c == null) {
            synchronized (d.class) {
                if (this.c == null) {
                    this.c = new com.sobot.chat.core.http.e.d(this.b, 5, 1, a, new a(), Executors.defaultThreadFactory(), new ThreadPoolExecutor.AbortPolicy());
                }
            }
        }
        return this.c;
    }
}
