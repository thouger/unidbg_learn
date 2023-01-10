package com.sobot.chat.b.a;

import android.app.Activity;
import android.view.Window;
import android.view.WindowManager;
import com.sobot.chat.b.a;
import java.util.ArrayList;

/* compiled from: HuaweiNotchScreen */
public class b implements a {
    @Override // com.sobot.chat.b.a
    public boolean a(Activity activity) {
        try {
            Class<?> loadClass = activity.getClassLoader().loadClass("com.huawei.android.util.HwNotchSizeUtil");
            return ((Boolean) loadClass.getMethod("hasNotchInScreen", new Class[0]).invoke(loadClass, new Object[0])).booleanValue();
        } catch (Throwable unused) {
            return false;
        }
    }

    @Override // com.sobot.chat.b.a
    public void b(Activity activity) {
        try {
            Window window = activity.getWindow();
            WindowManager.LayoutParams attributes = window.getAttributes();
            Class<?> cls = Class.forName("com.huawei.android.view.LayoutParamsEx");
            cls.getMethod("addHwFlags", Integer.TYPE).invoke(cls.getConstructor(WindowManager.LayoutParams.class).newInstance(attributes), 65536);
            window.getWindowManager().updateViewLayout(window.getDecorView(), window.getDecorView().getLayoutParams());
        } catch (Throwable unused) {
        }
    }

    @Override // com.sobot.chat.b.a
    public void a(Activity activity, a.c cVar) {
        try {
            Class<?> loadClass = activity.getClassLoader().loadClass("com.huawei.android.util.HwNotchSizeUtil");
            int[] iArr = (int[]) loadClass.getMethod("getNotchSize", new Class[0]).invoke(loadClass, new Object[0]);
            ArrayList arrayList = new ArrayList();
            arrayList.add(com.sobot.chat.b.b.b.a(activity, iArr[0], iArr[1]));
            cVar.a(arrayList);
        } catch (Throwable unused) {
            cVar.a(null);
        }
    }
}
