package com.sobot.chat.core.http.i;

import com.sobot.chat.core.http.e.a;
import com.sobot.chat.core.http.e.d;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* compiled from: SobotUploadThreadPool */
public class e {
    private static final TimeUnit a = TimeUnit.HOURS;
    private int b = 1;
    private d c;

    public d a() {
        if (this.c == null) {
            synchronized (e.class) {
                if (this.c == null) {
                    this.c = new d(this.b, 5, 1, a, new a(), Executors.defaultThreadFactory(), new ThreadPoolExecutor.AbortPolicy());
                }
            }
        }
        return this.c;
    }
}
