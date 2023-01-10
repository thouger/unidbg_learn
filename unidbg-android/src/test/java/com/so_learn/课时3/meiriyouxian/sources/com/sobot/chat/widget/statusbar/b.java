package com.sobot.chat.widget.statusbar;

import android.os.Build;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/* compiled from: LightStatusBarCompat */
/* access modifiers changed from: package-private */
public class b {
    private static final a a;

    /* compiled from: LightStatusBarCompat */
    /* access modifiers changed from: package-private */
    public interface a {
        void a(Window window, boolean z);
    }

    static {
        if (C0149b.a()) {
            if (Build.VERSION.SDK_INT >= 23) {
                a = new AnonymousClass1();
            } else {
                a = new C0149b(null);
            }
        } else if (Build.VERSION.SDK_INT >= 23) {
            a = new c(null);
        } else if (d.a()) {
            a = new d(null);
        } else {
            a = new AnonymousClass2();
        }
    }

    /* compiled from: LightStatusBarCompat */
    /* renamed from: com.sobot.chat.widget.statusbar.b$1  reason: invalid class name */
    static class AnonymousClass1 extends c {
        private final a a = new C0149b(null);

        AnonymousClass1() {
            super(null);
        }

        @Override // com.sobot.chat.widget.statusbar.b.c, com.sobot.chat.widget.statusbar.b.a
        public void a(Window window, boolean z) {
            super.a(window, z);
            this.a.a(window, z);
        }
    }

    /* compiled from: LightStatusBarCompat */
    /* renamed from: com.sobot.chat.widget.statusbar.b$2  reason: invalid class name */
    static class AnonymousClass2 implements a {
        @Override // com.sobot.chat.widget.statusbar.b.a
        public void a(Window window, boolean z) {
        }

        AnonymousClass2() {
        }
    }

    static void a(Window window, boolean z) {
        a.a(window, z);
    }

    /* compiled from: LightStatusBarCompat */
    private static class c implements a {
        private c() {
        }

        /* synthetic */ c(AnonymousClass1 r1) {
            this();
        }

        @Override // com.sobot.chat.widget.statusbar.b.a
        public void a(Window window, boolean z) {
            View decorView = window.getDecorView();
            int systemUiVisibility = decorView.getSystemUiVisibility();
            decorView.setSystemUiVisibility(z ? systemUiVisibility | 8192 : systemUiVisibility & -8193);
        }
    }

    /* compiled from: LightStatusBarCompat */
    /* renamed from: com.sobot.chat.widget.statusbar.b$b  reason: collision with other inner class name */
    private static class C0149b implements a {
        private C0149b() {
        }

        /* synthetic */ C0149b(AnonymousClass1 r1) {
            this();
        }

        /* JADX WARNING: Removed duplicated region for block: B:19:0x0041 A[SYNTHETIC, Splitter:B:19:0x0041] */
        /* JADX WARNING: Removed duplicated region for block: B:27:0x0048 A[SYNTHETIC, Splitter:B:27:0x0048] */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        static boolean a() {
            /*
                r0 = 0
                r1 = 0
                java.io.FileInputStream r2 = new java.io.FileInputStream     // Catch:{ IOException -> 0x0045, all -> 0x003d }
                java.io.File r3 = new java.io.File     // Catch:{ IOException -> 0x0045, all -> 0x003d }
                java.io.File r4 = android.os.Environment.getRootDirectory()     // Catch:{ IOException -> 0x0045, all -> 0x003d }
                java.lang.String r5 = "build.prop"
                r3.<init>(r4, r5)     // Catch:{ IOException -> 0x0045, all -> 0x003d }
                r2.<init>(r3)     // Catch:{ IOException -> 0x0045, all -> 0x003d }
                java.util.Properties r1 = new java.util.Properties     // Catch:{ IOException -> 0x0046, all -> 0x003b }
                r1.<init>()     // Catch:{ IOException -> 0x0046, all -> 0x003b }
                r1.load(r2)     // Catch:{ IOException -> 0x0046, all -> 0x003b }
                java.lang.String r3 = "ro.miui.ui.version.code"
                java.lang.String r3 = r1.getProperty(r3)     // Catch:{ IOException -> 0x0046, all -> 0x003b }
                if (r3 != 0) goto L_0x0036
                java.lang.String r3 = "ro.miui.ui.version.name"
                java.lang.String r3 = r1.getProperty(r3)     // Catch:{ IOException -> 0x0046, all -> 0x003b }
                if (r3 != 0) goto L_0x0036
                java.lang.String r3 = "ro.miui.internal.storage"
                java.lang.String r1 = r1.getProperty(r3)     // Catch:{ IOException -> 0x0046, all -> 0x003b }
                if (r1 == 0) goto L_0x0037
            L_0x0036:
                r0 = 1
            L_0x0037:
                r2.close()     // Catch:{ IOException -> 0x003a }
            L_0x003a:
                return r0
            L_0x003b:
                r0 = move-exception
                goto L_0x003f
            L_0x003d:
                r0 = move-exception
                r2 = r1
            L_0x003f:
                if (r2 == 0) goto L_0x0044
                r2.close()     // Catch:{ IOException -> 0x0044 }
            L_0x0044:
                throw r0
            L_0x0045:
                r2 = r1
            L_0x0046:
                if (r2 == 0) goto L_0x004b
                r2.close()     // Catch:{ IOException -> 0x004b }
            L_0x004b:
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.sobot.chat.widget.statusbar.b.C0149b.a():boolean");
        }

        /* JADX DEBUG: Can't convert new array creation: APUT found in different block: 0x0035: APUT  
          (r2v3 java.lang.Object[])
          (0 ??[int, short, byte, char])
          (wrap: java.lang.Integer : 0x0031: INVOKE  (r10v2 java.lang.Integer) = (r10v1 int) type: STATIC call: java.lang.Integer.valueOf(int):java.lang.Integer)
         */
        @Override // com.sobot.chat.widget.statusbar.b.a
        public void a(Window window, boolean z) {
            Class<?> cls = window.getClass();
            try {
                Class<?> cls2 = Class.forName("android.view.MiuiWindowManager$LayoutParams");
                int i = cls2.getField("EXTRA_FLAG_STATUS_BAR_DARK_MODE").getInt(cls2);
                Method method = cls.getMethod("setExtraFlags", Integer.TYPE, Integer.TYPE);
                Object[] objArr = new Object[2];
                objArr[0] = Integer.valueOf(z ? i : 0);
                objArr[1] = Integer.valueOf(i);
                method.invoke(window, objArr);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /* compiled from: LightStatusBarCompat */
    private static class d implements a {
        private d() {
        }

        /* synthetic */ d(AnonymousClass1 r1) {
            this();
        }

        static boolean a() {
            return Build.DISPLAY.startsWith("Flyme");
        }

        @Override // com.sobot.chat.widget.statusbar.b.a
        public void a(Window window, boolean z) {
            WindowManager.LayoutParams attributes = window.getAttributes();
            try {
                Field declaredField = WindowManager.LayoutParams.class.getDeclaredField("MEIZU_FLAG_DARK_STATUS_BAR_ICON");
                Field declaredField2 = WindowManager.LayoutParams.class.getDeclaredField("meizuFlags");
                declaredField.setAccessible(true);
                declaredField2.setAccessible(true);
                int i = declaredField.getInt(null);
                int i2 = declaredField2.getInt(attributes);
                declaredField2.setInt(attributes, z ? i2 | i : (~i) & i2);
                window.setAttributes(attributes);
                declaredField.setAccessible(false);
                declaredField2.setAccessible(false);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
