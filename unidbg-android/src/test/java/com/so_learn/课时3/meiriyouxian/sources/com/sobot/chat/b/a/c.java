package com.sobot.chat.b.a;

import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.view.Window;
import com.sobot.chat.b.a;
import com.sobot.chat.b.b.b;
import java.util.ArrayList;

/* compiled from: MiNotchScreen */
public class c implements a {
    private static boolean a() {
        try {
            return ((Integer) Class.forName("android.os.SystemProperties").getMethod("getInt", new Class[]{String.class, Integer.TYPE}).invoke(null, new Object[]{"ro.miui.notch", 0})).intValue() == 1;
        } catch (Throwable unused) {
            return false;
        }
    }

    public static int a(Context context) {
        int identifier = context.getResources().getIdentifier("notch_height", "dimen", "android");
        if (identifier > 0) {
            return context.getResources().getDimensionPixelSize(identifier);
        }
        return 0;
    }

    public static int b(Context context) {
        int identifier = context.getResources().getIdentifier("notch_width", "dimen", "android");
        if (identifier > 0) {
            return context.getResources().getDimensionPixelSize(identifier);
        }
        return 0;
    }

    @Override // com.sobot.chat.b.a
    public boolean a(Activity activity) {
        return a();
    }

    @Override // com.sobot.chat.b.a
    public void b(Activity activity) {
        try {
            Window.class.getMethod("addExtraFlags", Integer.TYPE).invoke(activity.getWindow(), 1792);
        } catch (Exception unused) {
        }
    }

    @Override // com.sobot.chat.b.a
    public void a(Activity activity, a.c cVar) {
        Rect a = b.a(activity, b((Context) activity), a((Context) activity));
        ArrayList arrayList = new ArrayList();
        arrayList.add(a);
        cVar.a(arrayList);
    }
}
