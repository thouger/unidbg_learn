package com.sobot.chat.b.a;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import com.sobot.chat.b.a;
import com.sobot.chat.b.b.b;
import java.util.ArrayList;

/* compiled from: VivoNotchScreen */
public class e implements a {
    @Override // com.sobot.chat.b.a
    @Deprecated
    public void b(Activity activity) {
    }

    public static boolean a() {
        try {
            Class<?> cls = Class.forName("android.util.FtFeature");
            return ((Boolean) cls.getMethod("isFtFeatureSupport", Integer.TYPE).invoke(cls.newInstance(), 32)).booleanValue();
        } catch (Exception e) {
            Log.e("tag", "get error() ", e);
            return false;
        }
    }

    public static int a(Context context) {
        return (int) (c(context) * 27.0f);
    }

    public static int b(Context context) {
        return (int) (c(context) * 100.0f);
    }

    private static float c(Context context) {
        return context.getResources().getDisplayMetrics().density;
    }

    @Override // com.sobot.chat.b.a
    public boolean a(Activity activity) {
        return a();
    }

    @Override // com.sobot.chat.b.a
    public void a(Activity activity, a.c cVar) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(b.a(activity, b((Context) activity), a((Context) activity)));
        cVar.a(arrayList);
    }
}
