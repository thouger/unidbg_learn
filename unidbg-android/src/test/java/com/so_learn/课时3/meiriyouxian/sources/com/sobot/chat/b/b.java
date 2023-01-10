package com.sobot.chat.b;

import android.app.Activity;
import android.graphics.Rect;
import android.os.Build;
import com.sobot.chat.b.a;
import com.sobot.chat.b.a.c;
import com.sobot.chat.b.a.d;
import com.sobot.chat.b.a.e;
import java.util.List;

/* compiled from: NotchScreenManager */
public class b {
    private static final b a = new b();
    private final a b = b();
    private boolean c = false;

    private b() {
    }

    public static b a() {
        return a;
    }

    public void a(Activity activity) {
        a aVar = this.b;
        if (aVar != null) {
            aVar.b(activity);
        }
    }

    public void a(Activity activity, a.AbstractC0139a aVar) {
        a.b bVar = new a.b();
        a aVar2 = this.b;
        if (aVar2 == null || !aVar2.a(activity)) {
            aVar.a(bVar);
        } else {
            this.b.a(activity, new AnonymousClass1(bVar, aVar));
        }
    }

    /* compiled from: NotchScreenManager */
    /* access modifiers changed from: package-private */
    /* renamed from: com.sobot.chat.b.b$1  reason: invalid class name */
    public class AnonymousClass1 implements a.c {
        final /* synthetic */ a.b a;
        final /* synthetic */ a.AbstractC0139a b;

        AnonymousClass1(a.b bVar, a.AbstractC0139a aVar) {
            this.a = bVar;
            this.b = aVar;
        }

        @Override // com.sobot.chat.b.a.c
        public void a(List<Rect> list) {
            if (list != null && list.size() > 0) {
                a.b bVar = this.a;
                bVar.a = true;
                bVar.b = list;
            }
            this.b.a(this.a);
        }
    }

    private a b() {
        if (Build.VERSION.SDK_INT >= 28) {
            return new com.sobot.chat.b.a.a();
        }
        if (Build.VERSION.SDK_INT >= 26) {
            if (com.sobot.chat.b.b.a.a()) {
                return new com.sobot.chat.b.a.b();
            }
            if (com.sobot.chat.b.b.a.d()) {
                return new d();
            }
            if (com.sobot.chat.b.b.a.b()) {
                return new e();
            }
            if (com.sobot.chat.b.b.a.c()) {
                return new c();
            }
        }
        return null;
    }
}
