package com.sobot.chat.b;

import android.app.Activity;
import android.graphics.Rect;
import java.util.List;

/* compiled from: INotchScreen */
public interface a {

    /* compiled from: INotchScreen */
    /* renamed from: com.sobot.chat.b.a$a  reason: collision with other inner class name */
    public interface AbstractC0139a {
        void a(b bVar);
    }

    /* compiled from: INotchScreen */
    public static class b {
        public boolean a;
        public List<Rect> b;
    }

    /* compiled from: INotchScreen */
    public interface c {
        void a(List<Rect> list);
    }

    void a(Activity activity, c cVar);

    boolean a(Activity activity);

    void b(Activity activity);
}
